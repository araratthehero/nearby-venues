# Nearby venues

The app is created to show recommended nearby venues. It uses the [Foursquare Places API](https://location.foursquare.com/developer/reference/places-api-overview). 

Toolkit
========
To build this app, I used:
* Macbook Pro (M1 Pro) | 32 GB
* Android Studio Giraffe | 2022.3.1
* Google Pixel 7 Pro (API 33) and various emulator instances for testing

Architecture
===========
As Android apps grow in size, it's important to define an architecture that allows the app to scale, increases the app's robustness, and makes the app easier to test. For that purpose I used [Clean architecture](https://blog.cleancoder.com/uncle-bob/2012/08/13/the-clean-architecture.html).

![image](https://github.com/araratthehero/nearby-venues/assets/8832594/e46fdc1f-5ca2-426c-94e3-bd9b389cbf84)

In Nearby venues app, I used the following layers:  
* UI (:app) - Responsible to display application data on the screen  
* Domain (:domain) - Contains the business logic of the app  
* Data (:data) - Responsible for requesting and loading data

UI
========
### The app supports Dark and Light modes

<center>
  <table>
    <tr>
      <td><img width="300" src="https://github.com/araratthehero/nearby-venues/assets/8832594/320e1ce5-9dd7-4f39-900f-0087f1a97249"></td>
      <td><img width="300" src="https://github.com/araratthehero/nearby-venues/assets/8832594/c06a2b0f-bbcc-4108-8537-7d762db725f3"></td>
    </tr>
  </table>
</center>

### On supported devices it uses Material 3 dynamic colors

<center>
  <table>
    <tr>
      <td><img width="300" src="https://github.com/araratthehero/nearby-venues/assets/8832594/4edc34f6-162e-44da-9d22-af1c77ac16e4"></td>
      <td><img width="300" src="https://github.com/araratthehero/nearby-venues/assets/8832594/090e8b93-455a-4ecc-94ba-e3d75d818e90"></td>
    </tr>
  </table>
</center>

### Location fetching

If available, the app fetches the last known location of the user, otherwise the app requests the current location of the user before showing the results.

https://github.com/araratthehero/nearby-venues/assets/8832594/ea245df6-6327-4df1-aa46-920167aa0766

### Errors

In case of errors, the app shows an error screen with an option to Retry loading

<center>
  <table>
    <tr>
      <td><img width="300" src="https://github.com/araratthehero/nearby-venues/assets/8832594/5e7354f1-6fe2-4759-8d88-702f754d5b9a"></td>
      <td><img width="300" src="https://github.com/araratthehero/nearby-venues/assets/8832594/80898935-a4ce-464e-814e-5112a8cc2262"></td>
      <td><img width="300" src="https://github.com/araratthehero/nearby-venues/assets/8832594/c6866690-874c-4a2a-b6b4-5849f0fca6d3"></td>
      <td><img width="300" src="https://github.com/araratthehero/nearby-venues/assets/8832594/2dbe1a9c-d141-452b-84f2-d42a77ed253c"></td>
    </tr>
  </table>
</center>

Libraries
============
Here is the list of the libraries I used in this project:
* App
  * Hilt
  * Navigation
  * Compose
  * Gms location
* Network
  * Retrofit 2
  * Okhttp3
  * Gson
* Testing
  * Junit
  * Mockito
