# Android Base project Guildlines

 This is Alta Labs Android base started project to quickly help boostrap your Android project. 

## Getting Started

Simply ```Clone``` this project to your local and ```import``` to Android Studio.

### Prerequisites

* Android Studio 3.0+
* Android SDK 28+

## Project Architecture
This project is based on MVVM(Model View View-Model) architecture pattern using the Android Jetpack Components.
 
 
### Components

* **Model (Data layer):** This is responsible for saving, retrieving and caching data. It communicates with the local storage  and other external store services. This part is divided in two: **Repository  and  Helpers** -  This part manages communication with the local database  and external service like network service using helpers.  The Repository combines the data results for the ViewModel which are most times observables or LiveData.


* **View(UI layer):** These are the UI-Controllers (Activities and Fragments) responsible for displaying data from the ViewModel to the users.  User interactions and triggers are handled at this point and the viewmodel is triggered when needed.


* **View-Model(UI Data manager):** It store and manage UI-related data in a lifecycle aware manner. The viewmodel subscribes to observables or livedata returned from the repository. It handles data presentation and modifications and updates the repository.


![Architecture Diagram](http://res.cloudinary.com/hngfun/image/upload/v1531926027/Group_7_1_1_fibk7v.png)



### Explaining the above diagram

**Activity/Fragment:**  This is the standard Android Component with lifecycle and view inflating methods. The handle user interactions and clicks which calls specific Viewmodel methods.


**ViewModel:** These subscribe and process repository data and pass it to the UI. These part of the architecture handles the user interaction with the repository.


**Repository:** This  interacts with the helper classes to satisfy request coming from the ViewModel. The response observables is emit to complete the process. This part handle the in datastore layer and listens for update and modifications


**Helpers:** These are helper classes for specific responsibilities ranging from API services, Database models , SharedPreference and other 3rd party components

* **Database Helper:** This handles the interactions with the local storage and emit observable results. E.g Room , Sqlite and Realm Db
* **Network Service Helper:**  Network service interface that talks to an API service in most cases it’s a Retrofit base class.
* **Preference Helper:** It saves and retrieves data from SharedPreference



## Built With

* [Retrofit](http://square.github.io/retrofit/) - Network Library
* [Dagger](https://github.com/google/dagger) - Dependency Injector
* [Rxjava](https://github.com/ReactiveX/RxJava) - Asyncronise task handling
* [RxAandroid](https://github.com/ReactiveX/RxAndroid) - Reactive Extensions for Android
* [Android Jetpack](https://developer.android.com/jetpack/)  - Android Architecture Components
* [Room](https://developer.android.com/topic/libraries/architecture/room) - Room persistence Library

## Authors

* [Nsikak Thompson](https://github.com/Nsikaktopdown) 

## License

This project is licensed under the MIT License - see the [LICENSE.md](LICENSE.md) file for details

## Acknowledgments

* [Alta Labs](https://altalabs.io/)
* [Celestine Omin](https://github.com/cyberomin)