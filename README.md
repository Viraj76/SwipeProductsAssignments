# Assignment by Swipe for Android Developer role


## APK Download

Please click the link below to download the APK:

[Download APK](https://drive.google.com/file/d/1Sjo-I7oe3jWOc92OXQKh3Ocmz7u5N7Ms/view?usp=sharing)

### Installation Instructions

1. Click the download link above to download the APK file.
2. Once downloaded, open the APK file on your device.
3. If prompted, allow the installation from unknown sources.
4. Follow the on-screen instructions to complete the installation.

## Watch Demonstration Video 

Please click the link below to watch demo video of the complete app

[Download APK](https://drive.google.com/file/d/1v1RwA8-chgdndYQnh6YvnkrdZm_--ahM/view?usp=drive_link)



## Tech stacks
- **Language**: Kotlin, XML
- **Architecture**: Clean Architecture with MVVM pattern
- **Notifications**: Firebase for sending notifications (FCM - Firebase Cloud Messaging)
- **Networking**: Retrofit for network calls
- **Caching**: Room DB for caching data
- **Dependency Injection**: Koin for dependency injection
- **Async Programming**: Flows, Coroutines, LiveData
- **Navigation**: Navigation Component for navigating between fragments
- **Image Loading**: Coil for loading images
- **Animations**: Lottie Animation for enhanced user experience
- **Loading Indicators**: Shimmer Effect for displaying loading states
- **Persistent Storage**: DataStore for storing user's app preferences
- **Splash Screen**: Splash API for displaying the splash screen


## Feature
- **Single Activity App**: Hosts two fragments within one activity
- **RecyclerView**: Displays the product list
- **Network Manager**: Monitors the internet connection throughout the app
- **Search Functionality**: Allows searching by name, tax, price, and type
- **Notifications and Dialogs**: Sends notifications and shows appropriate dialogs after a product is posted
- **Landscape Mode**: Fully functional in landscape orientation
- **Dialog Design**: Displays beautiful and appropriate dialogs
- **Dark Mode Compatibility**: Supports dark mode

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

6. Optionally, click the add image icon to select an image for the new product. Image should of format JPEG or PNG only with 1:1 aspect ratio.

7. Click the "Add Product" button to add the product. A dialog will shown while uploading and once product is posted a done dialog will apear and you will also recieve a notification


## Note -:
Image is not being uploaded , because the folder where we should upload it, is not provided. Altough it was optional to upload an image


## Screenshots (Light Mode)

Product Fragment

| ![1](https://github.com/Viraj76/SwipeProductsAssignments/assets/98775599/b7addb16-29eb-4241-b990-f334fe047b60) | ![2](https://github.com/Viraj76/SwipeProductsAssignments/assets/98775599/0686e5a8-dc22-481b-adc7-86422a22404e) | ![3](https://github.com/Viraj76/SwipeProductsAssignments/assets/98775599/0c086ca4-82a6-4b79-aa8d-737f6bc6819a) |
|--------------------------------------------------|--------------------------------------------------|--------------------------------------------------|
| Splash Screen                                          | Products Fragment when no internet                                          | Fetching products when we turn on internet                                          |



| ![1](https://github.com/Viraj76/SwipeProductsAssignments/assets/98775599/b7addb16-29eb-4241-b990-f334fe047b60) | ![2](https://github.com/Viraj76/SwipeProductsAssignments/assets/98775599/0686e5a8-dc22-481b-adc7-86422a22404e) | ![3](https://github.com/Viraj76/SwipeProductsAssignments/assets/98775599/0c086ca4-82a6-4b79-aa8d-737f6bc6819a) |
|---------------------------------------------------------------------------------------------------------------------|---------------------------------------------------------------------------------------------------------------------|---------------------------------------------------------------------------------------------------------------------|
| Product Fragment when Products has been fetched                                                                     | Searching Products                                                                                                  | Showing Lottie animation if no data found while searching                                                         |


Add Product Fragment

| ![9](https://github.com/Viraj76/SwipeProductsAssignments/assets/98775599/ccdeee1d-38ce-4ea4-ab5b-f97f30fb2bd3) | ![10](https://github.com/Viraj76/SwipeProductsAssignments/assets/98775599/d738e3d4-460c-447f-9185-9cf0fa5e8285) | ![11](https://github.com/Viraj76/SwipeProductsAssignments/assets/98775599/0e7c9af1-c0d7-494b-b926-ef3bcc81e6f6) |
|---------------------------------------------------------------------------------------------------------------------|----------------------------------------------------------------------------------------------------------------------|---------------------------------------------------------------------------------------------------------------------|
| Asking to turn on internet then only we can post the products                                                        | Layouts for adding product detail when internet is on                                                               | No field should be empty                                                                                           |

| ![12](https://github.com/Viraj76/SwipeProductsAssignments/assets/98775599/1df2d550-845b-414c-a5a2-fcbb56312c52) | ![13](https://github.com/Viraj76/SwipeProductsAssignments/assets/98775599/ce3476bc-6821-4543-98c9-4f1031b786fc) | ![14](https://github.com/Viraj76/SwipeProductsAssignments/assets/98775599/5bad1ff6-ab48-4d11-afbf-b9b41f51f996) |
|---------------------------------------------------------------------------------------------------------------------|----------------------------------------------------------------------------------------------------------------------|---------------------------------------------------------------------------------------------------------------------|
| Showing toast if the image is not PNG or JPEG (Optional)                                                            | Showing toast if the image is not having aspect ration 1:1                                                          | Selected Image which has PNG or JPEG format and 1:1 aspect ratio                                                  |

| ![15](https://github.com/Viraj76/SwipeProductsAssignments/assets/98775599/69479d87-1956-401b-8518-6c460f710806) | ![16](https://github.com/Viraj76/SwipeProductsAssignments/assets/98775599/c70dd465-0385-4aca-8a7c-03f0686bf510) | ![18](https://github.com/Viraj76/SwipeProductsAssignments/assets/98775599/21ec362b-f9c2-4b98-9afe-14ecdcc2c083) |
|---------------------------------------------------------------------------------------------------------------------|----------------------------------------------------------------------------------------------------------------------|---------------------------------------------------------------------------------------------------------------------|
| Showing posting product dialog                                                                                      | Showing posting done dialog once product is posted                                                                  | Recieved notification when product is posted                                                                        |

| ![20](https://github.com/Viraj76/SwipeProductsAssignments/assets/98775599/b45802e1-40d9-4978-a395-dcf2c83a8618) |
|-------------------------------------------------------------------------------------------------------------|
| Product has been posted, Searching the product that we have just posted.                                    |



## Screenshots (Dark Mode)




