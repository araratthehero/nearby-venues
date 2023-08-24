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

![image](https://github.com/araratthehero/nearby-venues/assets/8832594/1cbdbfcc-3ba6-4e08-adf1-9b34ce552dda)

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
      <td><img width="300" src="https://github.com/araratthehero/nearby-venues/assets/8832594/0339bdac-79c9-40a9-ba28-612fbb7244aa"></td>
      <td><img width="300" src="https://github.com/araratthehero/nearby-venues/assets/8832594/80c667cc-c706-495e-9e4f-dd48051dfcbc"></td>
    </tr>
  </table>
</center>

### On supported devices it uses Material 3 dynamic colors

<center>
  <table>
    <tr>
      <td><img width="300" src="https://github.com/araratthehero/nearby-venues/assets/8832594/255d9521-3118-48bc-ac8b-c46dc2e10e85"></td>
      <td><img width="300" src="https://github.com/araratthehero/nearby-venues/assets/8832594/67ed853c-9bd0-43a8-97da-f83cb83f0036"></td>
    </tr>
  </table>
</center>

### Location fetching

If available, the app fetches the last known location of the user, otherwise the app requests the current location of the user before showing the results.

https://github.com/araratthehero/nearby-venues/assets/8832594/d7b1c531-7e22-4adb-8958-4bf20c741a60

### Errors

In case of errors, the app shows an error screen with an option to Retry loading

<center>
  <table>
    <tr>
      <td><img width="300" src="https://github.com/araratthehero/nearby-venues/assets/8832594/b96ee390-d18e-4fe8-b8ae-993eb94c03b9"></td>
      <td><img width="300" src="https://github.com/araratthehero/nearby-venues/assets/8832594/2cff80dd-d1be-445d-8566-37582400faa3"></td>
      <td><img width="300" src="https://github.com/araratthehero/nearby-venues/assets/8832594/3e31d10b-2b51-4982-9ddd-f80f1cb26384"></td>
      <td><img width="300" src="https://github.com/araratthehero/nearby-venues/assets/8832594/65496c93-3982-42c9-b046-bd23e449e4b9"></td>
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
