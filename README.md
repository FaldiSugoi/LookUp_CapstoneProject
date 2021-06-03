# LookUp
---
Team ID: B21-CAP0099


Active Member ID and Name:
1.   A0050551 - Faldi Sugih Bimazahran
2.   A0050552 - Gregorius Wisnu Wijanarko Tiba
3.   C0141357 - Ni Made Rai Nirmala Santhi
4.   C0050547 - Constantine Dylan
5.   M3222890 - Fina Noviantika
6.   M0053537 - Ivana Angelia Khusuma

Based on a survey from KIC, 30%-60% Indonesian affected by hoaxes when talking about
the internet. One of the most popular forms of hoax is deep fake. Our team is wondering how to
educate Indonesian about deepfake awareness to avoid false statements and how to train models
with machine learning to provide accurate results providing whether a data is deep fake or not.
Why? Because in the last 5 year in Indonesia there have been several unique dramas, and the
problem is hoax, but most Indonesian believe it because they only look by the title and
photos/videos. Our goal is to create application that will be the platform to check whether the
content (videos) is deep fake or not.

Project Resources :

● Google Collab
● Kaggle
● Google Cloud Platform
● Tensorflow
● Pandas
● Altair
● Keras
● Android Studio
● Figma
● Firebase

---
# The Process of creating the project

Mobile developer :
1. Designing the Ui design
2. Constructing the architecture of the application
3. Connecting the ViewModel to the Firesore Database
4. Insert Media Player to play the selected video
5. Create a function to send video from local data storage to Firebase storage while sending the attributes to Firestore Database 
6. Create a function to recieve the attributes from database and show it using recyclerview on Main Activity

Machine Learning :
1. Preparing the model for the application
2. Do a quick run of a train model of the application
3. Evaluate the Model (improve the model based on variance and loss)
4. Create set test that can give a good result with small variance

Cloud Computing : 
1. Link a Billing Account for the Project to access Google Cloud Services
2. Create Roles for team member in Cloud IAM
3. Create Cloud Bucket for dataset to store the model and the optout from the model itself
4. Create Jupyter Notebook VM suitable for Machine Learning Purposes
5. Create Cloud Firestore
6. Create Cloud Function to trigger the model when data is created from the aplication

---
# Getting started

1. When you first open the application, you will then be greeted by splash screen and onBoarding screen which gave the user an introduction regarding the application.

![unknown](https://user-images.githubusercontent.com/61868927/120620464-32645f80-c487-11eb-8501-8f175b4eaaa1.png)![Screenshot 2021-06-03 162149](https://user-images.githubusercontent.com/61868927/120621360-01d0f580-c488-11eb-8b75-f76127bc87d3.png)
![unknown1](https://user-images.githubusercontent.com/61868927/120620834-9129d900-c487-11eb-903a-e92b7e0a2c78.png)![Screenshot 2021-06-03 162209](https://user-images.githubusercontent.com/61868927/120621250-eb2a9e80-c487-11eb-89c6-3145aaf6bf1d.png)

2. After you get through the OnBoarding screen, then you will navigate to the Main Activity

![mainpage](https://user-images.githubusercontent.com/61868927/120621680-4bb9db80-c488-11eb-9c52-706ed3188513.png)

3. On the Main page you can see the history of your previosly uploaded video, if you click the main button on the middle, you will then navigate to the Uploud video activity

![uploudpage](https://user-images.githubusercontent.com/61868927/120622369-efa38700-c488-11eb-9283-a749e45a4af5.png)![local](https://user-images.githubusercontent.com/61868927/120622561-1feb2580-c489-11eb-80db-c18f525b5a52.png)


4. Uploud activity is the page where you can uploud your selected video from the local storage, and input the title for the video itself


![detailpage](https://user-images.githubusercontent.com/61868927/120622774-5032c400-c489-11eb-8917-57d976f7085b.png)

5. You can access Detail activity when you clicked one of the item from the history , on your Main activity
6. On Detail activity you can see the attributes from the video that have been uplouded ,such as video title,date uplouded, status and the most important thing the credibility of the video itself (Deepfake or real)
