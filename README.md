# Plantae-Android
This is a sample app that uses Android Architecture Components and JetPack to show List of Plants and their details from public repository. The App follows Android development best practices with Android Jetpack as advised by Google. It loads data from network, uses Room for persists data locally if app is used without internet and also uses Jetpack's Paging3 Library for Pagination. It also provides example of Unit Testing.

**Plant List Api From:**

https://trefle.io

## Introduction
### Functionality
The app is composed of 2 main screens.

#### MainFragment(Plant List)
Displays all the plants with images using Pagination. Clicking on Plant Item Opens Plant Detail Screen.


#### DetailsFragment
Displays details of a plant; synonyms, origin family, genus, author, year etc.


### Building
You can open the project in Android studio and press run.

## Libraries
- Android Support Library
- Android Architecture Components
- Android Navigation
- Android Data Binding
- Hilt for dependency injection
- Paging3 for Pagination
- Retrofit for REST api communication
- Room for Accessing app's SQLite database with in-app objects and compile-time checks.
- Lifecycles - Create a UI that automatically responds to lifecycle events.
- LiveData - Build data objects that notify views when the underlying database changes.
- ViewModel - Store UI-related data that isn't destroyed on app rotations. Easily schedule asynchronous tasks for optimal execution.
- Glide for image loading
- Kotlin Coroutines for managing background threads with simplified code and reducing needs for callbacks
- JUnit, Espresso, Mockito for Unit Testing(Local and Instrumented)
