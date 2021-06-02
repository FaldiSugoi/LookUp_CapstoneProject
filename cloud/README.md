# **Creating a Cloud Infrastructure for Machine Learning**
---
**Warning:** Following the steps of what's in here may cost you money (Google Cloud is a paid service), be sure to shut down any Google Cloud service you no longer need to use to avoid charges.


## **What is in here?**
Code, Files, and a step by step guide of creating a Cloud Infrastructure for Machine Learning.


## **What do I need to get started?**
*   [Google Cloud account](https://cloud.google.com/gcp) 
*   [Google Cloud Project](https://cloud.google.com/resource-manager/docs/creating-managing-projects)

## **What will i end up with**
If you go through the steps below you should end up with a Deployed ML model that is running in the Cloud using VM instances that can be called to predict data sent from Firestore Database.

## **Start the Project**

#### **1. Link a Billing Account for the Project**
You need a Billing Account for every project so that you can access Google Cloud Services 


1.   On the right-hand side search Billing in the menu and click it
2.   Click Manage Billing Account
3.   Create Billing Account if you haven't
4.   Set your project to the Billing Account you've just set up

![Billing](https://user-images.githubusercontent.com/53257440/120513621-a9054c80-c3f6-11eb-8f34-035a11f4105a.png)


#### **2. Create Roles for team member in Cloud IAM**

IAM & Admins is a Role Manager in Google Cloud Platform. It gives and revokes access to a certain person through an email or a service account.

1.   On the right-hand side search IAM & Admin in the menu and click it
2.   Click Add Member
3.   Type in the email address
4.   Add roles
5.   Repeat steps 2 - 4 for multiple accounts

![image](https://user-images.githubusercontent.com/53257440/120513968-fa154080-c3f6-11eb-9129-166cb21cf000.png)


#### **3. Create Cloud Bucket for dataset**

Cloud Storage is a service for storing data. For this project's purposes is to store the dataset for the ML training and store the model and the output for that model.

1. On the right-hand side search **Cloud Storage** in the menu and click it
2. Click Create Bucket
3. Set a name for the bucket and the region

You're set for storing data, *later on we will add the dataset using jupyter notebooks*

![Bucket](https://user-images.githubusercontent.com/53257440/120517977-42cef880-c3fb-11eb-8662-25682f157ab1.png)



#### **4. Create Jupyter Notebook VM suitable for Machine Learning Purposes**

Jupyter Notebooks are an environment for developing your code in a customizable way combining programming languages and markdown to visualize your code better.

**Creating a Jupyter Notebook VM**
1. On the right-hand side search **Vertex AI** in the menu and click it
2. Again on the right-hand side click notebooks
3. Click Create Notebook
4. Choose a VM name and the region you want to build the VM, we wanted to have a GPU for our VM Tensorflow so pick Python 3 (CUDA Toolkit 11.0) don't forget to choose with GPU (in this case the T4 GPU). *make sure you have a quota for the T4 GPU (you can request quota in IAM & Admins tab)*

Wait for the vm to instantiate, once done the next step is to import the dataset to the VM and to Cloud Storage

![Jupyter Notebook](https://user-images.githubusercontent.com/53257440/120512312-7149d500-c3f5-11eb-8f4e-eaba4e8e150a.png)


**Importing Dataset from Kaggle**
1. Click open JupyterLab
2. on the JupyterLabs, Click new file and name it "import-dataset"
3. use this [Code](https://github.com/FaldiSugoi/LookUp_CapstoneProject/blob/master/cloud/import-dataset.ipynb) to copy the dataset into the VM and then import them to the cloud storage (*make sure to change the Cloud Storage URI to your own cloud name)

![Import Dataset](https://user-images.githubusercontent.com/53257440/120513248-4613b580-c3f6-11eb-8ae3-6769de9f18ee.png)


#### **5. Create Cloud Firestore**
Cloud Firestore is one of the services that is integrated with Firebase. We will use it to integrate it with the app that we're building. Firestore is a non-SQL database so that it is easy for us to use it interchangably with the app

1. On the right-hand side search **Firestore** in the menu and click it
2. Click Start Collection
3. Click add Document
4. Click Add field to create the data in that document

![Firestore](https://user-images.githubusercontent.com/53257440/120511385-912cc900-c3f4-11eb-83aa-9fc7037aa311.png)

Done, you can now go test your model using the dataset you've imported. Great Job



#### **6. Create Cloud Function**
Cloud Function is a serverless service that is triggered by an event based metric. We will use this function for triggering the model when a data is created from the app.

1. On the right-hand side search **Cloud Function** in the menu and click it
2. Click Creat Functions
3. Create a name and pick a region for the function
4. Change Trigger type to Firestore, event type to create, Document path to Videos/{videoid}/
5. Click Save and then click Next
6. For Runtime pick Pyhton 3.8, change Entry point to ***(funcname)***
7. in main.py copy and paste this [code]()
8. in requirements.txt copy and paste this [code]()
9. Click Deploy
 
Wait for the function to be deployed and then you are done with setting up the Cloud Function.


