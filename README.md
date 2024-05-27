# Assignment by Swipe for Android Developer role

## Tech stacks
* Kotlin , XML
* Clean Architecure with MVVM pattern
* Firebase - For sending notification (FCM - Firebase cloud messaging)
* Retrofit - For network call
* Room DB - For caching
* Koin - For Dependency injection
* Flows , Coroutines , Live Data
* Navigation Component - For navigating between two fragments
* Coil - to load images
* Lottie Animation - For better User experience
* Shimmer Effect - to show loading
* DataStore - to store user's app entry
* Splash API - to show splash screen


## Feature
* Single Activity app - Hosted 2 fragments in one activity
* Recycler View - to show the product list
* Network Manager - Monitoring Internet connection througout the app
* Searching list with their name , tax , price , type. 
* Sending notification and showing appropriate dialogs once the product has been posted.
* Works fine with the landscape mode also.
* Showing beatiful and appropriate dialogs.
* Dark Mode compatible.

## Getting Started

Follow these steps to get the app up and running on your local machine.

### Method - 1

1. Click on Code(above green button) and download the zip.

2. Unzip it , where it was downloaded. 

3. Open Android Studio and select "open" from the File menu.

4. Now go to the directory where the unzipped code is placed, you will see an green android logo select it.  

5. Wait for Gradle to sync and build the project.

6. Connect an Android device or start an Android emulator with API level 27 or later.

7. Run the app on the connected device or emulator using the Run button in Android Studio.

### Method - 2 (Git should be connected with Android studio for this method)

1. Click on Code(above green button) and copy HTTPS link

2. Go to Android Studio , File -> New -> Project from version control

3. Paste the copied url and click on ok.

5. Wait for Gradle to sync and build the project.

6. Connect an Android device or start an Android emulator with API level 27 or later.

7. Run the app on the connected device or emulator using the Run button in Android Studio.


## How to Use the App

1. Ensure your device is connected to the internet. The app opens to the product listing screen where you can view a list of products.

2. Use the search bar at the top to find products by name, type, price, or tax.

3. Scroll through the list to see all available products.

4. To add a new product, click on floating action button at bottom right.

5. On the Add Product fragment, enter the product name, select the product type from the dropdown menu, and input the selling price and tax rate.

6. Optionally, click the add image icon to select an image for the new product.

7. Click the "Add Product" button to add the product. A dialog will shown while uploading and once p



### Note
Currently Image is not being uploaded to api as I can't figure out where to upload the image in the cloud and retrieve image link to store cloud image address in Product object.

Please elaborate me about this so I could implement this functionality.
## API Endpoint

