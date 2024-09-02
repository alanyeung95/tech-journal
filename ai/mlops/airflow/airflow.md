## What is Apache Airflow?
Apache Airflow is a workflow management platform that enables scheduling, monitoring, and executing tasks within a data pipeline. It is based on Python and follows a “Workflow as Code” approach, making it easy to create and maintain complex workflows.

## DAG
A key element in the context of Apache Airflow is the concept of a Directed Acyclic Graph (DAG). A DAG is a visual representation of a workflow consisting of a set of interconnected tasks. In this context, tasks are represented by the nodes of the graph, and dependencies between tasks are represented by edges.

## Pros and Cons of airflow
### Pros
1. good UI
2. workflow as code, easier to manage

### Cons
1. means one more module to maintain
2. more services to setup: https://airflow.apache.org/docs/apache-airflow/2.0.2/start/docker.html

## Examples

official example: https://airflow.apache.org/docs/apache-airflow/stable/tutorial/pipeline.html

youtube example: https://www.youtube.com/watch?v=Hlvy4fPW6nE

other examples: https://airflow.apache.org/docs/apache-airflow/stable/tutorial/pipeline.html

## Trigger pipeline

To trigger the `bert_model_training_pipeline.py` Airflow DAG from a frontend UI upon a "train" button click or after successful data upload, you can use one of several approaches depending on your system architecture and requirements. Here's a step-by-step guide covering two main methods:

### Method 1: Trigger DAG via Airflow's REST API

With Airflow 2.0 and above, you can use the stable REST API to trigger DAGs programmatically. This is useful if you want to integrate DAG execution directly into your frontend or backend system.

#### Step 1: Enable the Airflow REST API
First, ensure that the REST API is enabled in your Airflow configuration (`airflow.cfg`):

```ini
[api]
auth_backend = airflow.api.auth.backend.basic_auth
```

#### Step 2: Set Up Authentication
For security, configure authentication for the API. Airflow supports several authentication backends, such as Basic Auth, OAuth, and Kerberos. You might need to create a user in Airflow with permissions to trigger DAGs.

#### Step 3: Trigger the DAG from Your Application
When the "train" button is clicked or data is successfully uploaded, make an HTTP POST request to the Airflow API to trigger the DAG. Here’s an example using JavaScript with fetch API:

```javascript
const triggerDAG = async () => {
  const url = 'http://<airflow-url>/api/v1/dags/bert_model_training_pipeline/dagRuns';
  const body = {
    conf: {},  // You can pass configuration parameters here
  };

  try {
    const response = await fetch(url, {
      method: 'POST',
      headers: {
        'Content-Type': 'application/json',
        'Authorization': 'Basic ' + btoa('username:password')
      },
      body: JSON.stringify(body)
    });

    if (!response.ok) {
      throw new Error('Failed to trigger DAG');
    }

    const data = await response.json();
    console.log('DAG triggered successfully:', data);
  } catch (error) {
    console.error('Error triggering DAG:', error);
  }
};

// Call this function when the "train" button is clicked or after successful file upload
triggerDAG();
```
Ensure you replace `<airflow-url>`, `username`, and `password` with the actual values.

### Method 2: Using Airflow Hooks or Sensors

If you prefer to have Airflow periodically check for conditions such as a new file in an S3 bucket (indicating a successful upload), you can use sensors.

#### Step 1: Configure a Sensor in Your DAG
You could modify your DAG to include a sensor that waits for a new file to appear in an S3 bucket:

```python
from airflow.providers.amazon.aws.sensors.s3_key import S3KeySensor

wait_for_data = S3KeySensor(
    task_id='wait_for_data',
    bucket_key='s3://your-bucket/path/to/data.csv',
    wildcard_match=True,
    aws_conn_id='aws_default',
    timeout=18 * 3600,  // Adjust the timeout to your needs
    poke_interval=300,  // Check every 5 minutes
    dag=dag,
)

wait_for_data >> preprocess_data_task >> train_model_task >> upload_model
```

This setup uses an `S3KeySensor` to wait for the data to appear before proceeding with the rest of the pipeline.
