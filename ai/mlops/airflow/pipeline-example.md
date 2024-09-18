## purpose: 

To design an Apache Airflow pipeline that handles the complete machine learning workflow—from downloading data from an S3 bucket, preprocessing it, training a BERT model, evaluating it, and then uploading the trained model back to S3—you can follow this detailed step-by-step process:

### Step 1: Create airflow pipeline file

Create a Python script (e.g., `bert_model_training_pipeline.py`) and include all necessary imports, task definitions, and DAG setup. Below is the complete script combining all components:

```python
from airflow import DAG
from datetime import datetime, timedelta
from airflow.operators.python_operator import PythonOperator
from airflow.providers.amazon.aws.transfers.s3_to_local import S3ToLocalFilesystemOperator
from airflow.providers.amazon.aws.transfers.local_to_s3 import LocalToS3FilesystemOperator
import pandas as pd
from nltk.corpus import stopwords
from sklearn.model_selection import train_test_split
from transformers import BertForSequenceClassification, Trainer, TrainingArguments

default_args = {
    'owner': 'airflow',
    'depends_on_past': False,
    'start_date': datetime(2024, 1, 1),
    'email': ['example@email.com'],
    'email_on_failure': False,
    'email_on_retry': False,
    'retries': 1,
    'retry_delay': timedelta(minutes=5),
}

dag = DAG(
    'bert_model_training_pipeline',
    default_args=default_args,
    description='An end-to-end pipeline for training a BERT model',
    schedule_interval=timedelta(days=1),
    catchup=False,
    tags=['ml'],
)

def preprocess_data():
    # Load data
    df = pd.read_csv('/path/to/local/data.csv')

    # Remove stopwords
    stop = stopwords.words('english')
    df['text'] = df['text'].apply(lambda x: ' '.join([word for word in x.split() if word not in stop]))

    # Split data
    train, test = train_test_split(df, test_size=0.2)

    # Save data
    train.to_csv('/path/to/local/train.csv', index=False)
    test.to_csv('/path/to/local/test.csv', index=False)

def train_model():
    # Placeholder for loading datasets
    train_dataset = # load your dataset
    test_dataset = # load your dataset

    model = BertForSequenceClassification.from_pretrained('bert-base-uncased')
    training_args = TrainingArguments(
        output_dir='./results',
        num_train_epochs=3,
        per_device_train_batch_size=16,
        per_device_eval_batch_size=64,
        warmup_steps=500,
        weight_decay=0.01,
        evaluate_during_training=True,
        logging_dir='./logs',
    )

    trainer = Trainer(
        model=model,
        args=training_args,
        train_dataset=train_dataset,
        eval_dataset=test_dataset
    )

    trainer.train()
    model.save_pretrained('/path/to/local/saved_model')

download_data = S3ToLocalFilesystemOperator(
    task_id='download_data',
    aws_conn_id='aws_default',
    bucket='your-s3-bucket',
    key='path/to/your/data.csv',
    dest_file='/path/to/local/data.csv',
    dag=dag,
)

preprocess_data_task = PythonOperator(
    task_id='preprocess_data',
    python_callable=preprocess_data,
    dag=dag,
)

train_model_task = PythonOperator(
    task_id='train_model',
    python_callable=train_model,
    dag=dag,
)

upload_model = LocalToS3FilesystemOperator(
    task_id='upload_model',
    aws_conn_id='aws_default',
    bucket='your-s3-bucket',
    key='path/to/your/saved_model',
    dest_file='/path/to/local/saved_model',
    dag=dag,
)

download_data >> preprocess_data_task >> train_model_task >> upload_model
```

### Step 2: Deploy the Airflow Script

1. **Save the Script**: Save `bert_model_training_pipeline.py` in the `dags` folder of your Airflow environment. This is typically located at `/home/airflow/airflow/dags` in a default Airflow setup.

2. **Restart Airflow**: Depending on your installation, you might need to restart Airflow to recognize the new DAG. You can do this by restarting the Airflow web server and scheduler. Use the following commands if you're using a system with systemd:
   ```bash
   sudo systemctl restart airflow-webserver
   sudo systemctl restart airflow-scheduler
   ```

3. **Access Airflow Web UI**: Open the Airflow web interface, typically available at `http://localhost:8080`. You should see your DAG `bert_model_training_pipeline` listed.

4. **Trigger the DAG**: You can manually trigger the DAG by clicking on it in the web interface and using the "Trigger DAG" button. This is useful for testing to ensure everything works as expected.

5. **Monitor Execution**: Use the Airflow UI to monitor the DAG's execution, check logs, and troubleshoot if needed.

## Host the model in Sagemaker

### Step 1: Add SageMaker Operators to DAG

First, you'll need to import additional operators from Airflow’s Amazon provider package that are specifically designed to interact with SageMaker:

```python
from airflow.providers.amazon.aws.operators.sagemaker_endpoint import SageMakerEndpointOperator
from airflow.providers.amazon.aws.operators.sagemaker_model import SageMakerModelOperator
from airflow.providers.amazon.aws.operators.sagemaker_endpoint_config import SageMakerEndpointConfigOperator
```

### Step 2: Define SageMaker Model Creation Task

This task will create a SageMaker model using the model artifacts uploaded to S3.

```python
create_model = SageMakerModelOperator(
    task_id='create_sagemaker_model',
    aws_conn_id='aws_default',
    model_name='bert-classification-model',  # Ensure this is unique in the account
    image_uri='763104351884.dkr.ecr.us-west-2.amazonaws.com/huggingface-pytorch-inference:1.7.1-transformers4.6.1-cpu-py36-ubuntu18.04',
    model_s3_location='s3://your-s3-bucket/path/to/your/saved_model/model.tar.gz',  # Path to your model tar.gz file
    role='arn:aws:iam::your-account-id:role/service-role/YourSageMakerRole',  # SageMaker execution role ARN
    dag=dag,
)
```

### Step 3: Define SageMaker Endpoint Configuration Task

This task will configure the endpoint for deploying the model.

```python
configure_endpoint = SageMakerEndpointConfigOperator(
    task_id='configure_sagemaker_endpoint',
    aws_conn_id='aws_default',
    endpoint_config_name='bert-classification-endpoint-config',
    model_name='bert-classification-model',  # Must match the model name used in create_model
    initial_instance_count=1,
    instance_type='ml.m4.xlarge',
    dag=dag,
)
```

### Step 4: Define SageMaker Endpoint Deployment Task

This task will deploy the model as an endpoint, enabling real-time predictions.

```python
deploy_model = SageMakerEndpointOperator(
    task_id='deploy_sagemaker_endpoint',
    aws_conn_id='aws_default',
    endpoint_name='bert-classification-endpoint',
    endpoint_config_name='bert-classification-endpoint-config',
    update=False,  # Set to True if updating an existing endpoint
    dag=dag,
)
```

### Step 5: Chain the Tasks in the DAG

Now, add these tasks to your existing DAG and define the order of execution.

```python
download_data >> preprocess_data_task >> train_model_task >> upload_model
upload_model >> create_model >> configure_endpoint >> deploy_model
```

### Notes

- **Model Artifacts**: Ensure your model artifacts uploaded to S3 are in the expected format by SageMaker, typically a `.tar.gz` file containing your model files.
- **SageMaker IAM Role**: The IAM role specified must have policies allowing it to access S3, manage SageMaker resources, and any other required permissions.
- **Instance Type**: Choose the instance type for deployment based on your model size and expected latency.
