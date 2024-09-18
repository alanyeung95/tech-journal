## Azure Data Factory, Azure SQL DB and Blob storage

1. Create Resource group (data-factory, sql-db and blob-storage)
2. create a pipeline in **Azure Data Factory** and copy file like gender_jobs_data.csv file from the Blob storage account to Azure SQL DB.
    - preview data
    - sink (output) setting
    - data mapping
3. Create scheduler for running the pipline (Schedule Trigger, Tumbling Window Trigger (periodic time interval), Storage Events Trigger)

## Machine Learning Use Case

Building a multi-class classifier using Azure cloud services

### Step 1: Set Up Your Azure Environment

1. **Create an Azure Machine Learning Workspace**:
   - Click on “Create a resource” and search for “Machine Learning”.
   - Navigate to Azure Machine Learning: Find your Azure Machine Learning workspace 
   - click on the “Launch studio” button to open Azure ML Studio.

### Step 2: Prepare Your Data

- **Data Collection**: Gather the data you will use for training your model. Your data should be labeled for a multi-class classification task.
  
- **Data Upload**: Upload your data to Azure Blob Storage for easy access.
  - You can do this through the Azure portal or using Azure Storage Explorer.
  - Or using restful API to upload: https://learn.microsoft.com/en-us/rest/api/storageservices/put-blob?tabs=microsoft-entra-id

### Step 3: Develop Your Model Using Azure ML Studio

1. **Access Azure ML Studio**:
   - Navigate to your Machine Learning workspace in the Azure portal and launch Studio.

2. **Create a Compute Instance**:
   - In Azure ML Studio, go to “Compute” and create a new compute instance that you will use for training your model.

3. **Import Data**:
   - Use Azure ML datasets to import and manage your data directly within ML Studio.

4. **Create an Experiment/Pipeline**:
   - Go to “Designer” under the “Author” section to visually create your machine learning model. This interface allows you to drag and drop datasets and modules to create your experiment.

5. **Select and Configure the Classifier**:
   - For multi-class classification, you can use algorithms like Multiclass Decision Forest, Multiclass Logistic Regression, or any other suitable multi-class classifier available in Azure ML.
   - Drag the appropriate classification module to your canvas and connect your dataset.

6. **Split the Data**:
   - Use the “Split Data” module to divide your data into training and testing datasets.

7. **Train the Model**:
   - Connect your classifier to the training dataset output from the “Split Data” module.
   - Add the “Train Model” module, select your target column, and connect it to the classifier.

8. **Score and Evaluate the Model**:
   - Use the “Score Model” module connected to the trained model and the testing dataset to make predictions.
   - Then, use the “Evaluate Model” module to assess the performance of your classifier.

### Step 4: Deploy the Model

1. **Deploy to container instance (accessed by Endpoint option inside studio)**:
   - Convert your training pipeline to an inference pipeline.
  
2. **Deploy the Model as a Web Service**:
   - Register the Model: You can register your trained model in the Azure ML workspace, which allows you to manage and version your models centrally.
   - Create a deployment cluster if not already available.
   - Deploy your model to the cluster to make it accessible via an API.

4. **Consume the Model**:
   - Use the provided endpoint and API keys to integrate your model into applications or send data for predictions.

### Step 5: Monitor and Manage Your Model

- **Use Azure ML monitoring tools** to track the usage and health of your web service.
