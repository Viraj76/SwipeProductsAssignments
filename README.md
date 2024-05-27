# Content
- [APK Download](#apk-download)
- [Watch Demonstration Video](#watch-demonstration-video)
- [Tech Stacks](#tech-stacks)
- [Feature](#feature)
- [Getting Started](#getting-started)
- [How to Use the App](#how-to-use-the-app)
- [Screenshots (Light Mode)](#screenshots-light-mode)
- [Screenshots (Dark Mode)](#screenshots-dark-mode)


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

[Watch the Video](https://drive.google.com/file/d/1v1RwA8-chgdndYQnh6YvnkrdZm_--ahM/view?usp=drive_link)



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


## Features
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



| ![4](https://github.com/Viraj76/SwipeProductsAssignments/assets/98775599/42769d40-a160-45ac-acf1-1f910973abb0) | ![7](https://github.com/Viraj76/SwipeProductsAssignments/assets/98775599/4ace530a-83f1-4149-b2f5-d61efd6d4d4e) | ![8](https://github.com/Viraj76/SwipeProductsAssignments/assets/98775599/356d4037-0e91-4cbc-b560-48d926920883) |
|---------------------------------------------------------------------------------------------------------------------|---------------------------------------------------------------------------------------------------------------------|---------------------------------------------------------------------------------------------------------------------|
| Product Fragment when Products has been fetched                                                                     | Searching Products                                                                                                  | Showing Lottie animation if no data found while searching                                                         |


Add Product Fragment

| ![9](https://github.com/Viraj76/SwipeProductsAssignments/assets/98775599/ccdeee1d-38ce-4ea4-ab5b-f97f30fb2bd3) | ![10](https://github.com/Viraj76/SwipeProductsAssignments/assets/98775599/d738e3d4-460c-447f-9185-9cf0fa5e8285) | ![11](https://github.com/Viraj76/SwipeProductsAssignments/assets/98775599/0e7c9af1-c0d7-494b-b926-ef3bcc81e6f6) |
|---------------------------------------------------------------------------------------------------------------------|----------------------------------------------------------------------------------------------------------------------|---------------------------------------------------------------------------------------------------------------------|
| Asking user to turn on internet then only we can post the products                                                        | Layouts for adding product detail when internet is on                                                               | No field should be empty                                                                                           |




| ![21](https://github.com/Viraj76/SwipeProductsAssignments/assets/98775599/3dff1aab-3f23-4153-ac5f-66d12bc511ac) | ![22](https://github.com/Viraj76/SwipeProductsAssignments/assets/98775599/2ef54092-eeef-4c91-8909-257576b37fca) | ![12](https://github.com/Viraj76/SwipeProductsAssignments/assets/98775599/d4c1cdf8-22db-4814-aa94-fe37d91300a9) |
|-------------------------------------------------------------------------------------------------------------|-------------------------------------------------------------------------------------------------------------|-------------------------------------------------------------------------------------------------------------|
| Selecting Product Type                                                                                     | Entering price and tax                                                                                     | Showing toast if the image is not PNG or JPEG (Optional)                                                  |





| ![13](https://github.com/Viraj76/SwipeProductsAssignments/assets/98775599/ce3476bc-6821-4543-98c9-4f1031b786fc) | ![14](https://github.com/Viraj76/SwipeProductsAssignments/assets/98775599/5bad1ff6-ab48-4d11-afbf-b9b41f51f996) | ![15](https://github.com/Viraj76/SwipeProductsAssignments/assets/98775599/69479d87-1956-401b-8518-6c460f710806) |
|-------------------------------------------------------------------------------------------------------------|-------------------------------------------------------------------------------------------------------------|-------------------------------------------------------------------------------------------------------------|
| Showing toast if the image is not having aspect ratio 1:1                                                      | Selected Image which has PNG or JPEG format and 1:1 aspect ratio                                             | Showing posting product dialog                                                                             |





| ![16](https://github.com/Viraj76/SwipeProductsAssignments/assets/98775599/c70dd465-0385-4aca-8a7c-03f0686bf510) | ![18](https://github.com/Viraj76/SwipeProductsAssignments/assets/98775599/21ec362b-f9c2-4b98-9afe-14ecdcc2c083) | ![20](https://github.com/Viraj76/SwipeProductsAssignments/assets/98775599/b45802e1-40d9-4978-a395-dcf2c83a8618) |
|-------------------------------------------------------------------------------------------------------------|-------------------------------------------------------------------------------------------------------------|-------------------------------------------------------------------------------------------------------------|
| Showing posting done dialog once product is posted                                                             | Received notification when product is posted                                                                | Product has been posted, Searching the product that we have just posted.                                    |



## Screenshots (Dark Mode)

Product Fragment

| ![1](https://github.com/Viraj76/SwipeProductsAssignments/assets/98775599/9915fbd0-6327-4276-a082-f983993e84dc) | ![Screenshot_2024-05-27-20-23-56-82_c2d16710d642a9dce14ef796728f67af](https://github.com/Viraj76/SwipeProductsAssignments/assets/98775599/c300ff55-9c80-4e61-90ec-f51f497562c7) | ![Screenshot_2024-05-27-20-24-05-74_c2d16710d642a9dce14ef796728f67af](https://github.com/Viraj76/SwipeProductsAssignments/assets/98775599/ea475bc0-f372-40c3-9ae5-f09e82fdb284) |
|-------------------------------------------------------------------------------------------------------------|---------------------------------------------------------------------------------------------------------------------------------------------------------------------|---------------------------------------------------------------------------------------------------------------------------------------------------------------------|
| Splash Screen                                                                                               | Products Fragment when no internet                                                                                                                                    | Fetching products when we turn on internet                                                                                                                          |






| ![Screenshot_2024-05-27-20-24-20-19_c2d16710d642a9dce14ef796728f67af](https://github.com/Viraj76/SwipeProductsAssignments/assets/98775599/a472c56a-c8ea-4319-85e3-cba611b9951e) | ![Screenshot_2024-05-27-20-24-37-34_c2d16710d642a9dce14ef796728f67af](https://github.com/Viraj76/SwipeProductsAssignments/assets/98775599/6820ef8e-2e8a-410a-bc30-6900d8f0a76a) | ![Screenshot_2024-05-27-20-24-42-63_c2d16710d642a9dce14ef796728f67af](https://github.com/Viraj76/SwipeProductsAssignments/assets/98775599/998c13e6-cbd6-4c87-af78-26b1b26d0107) |
|----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------|----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------|----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------|
| Product Fragment when Products has been fetched                                                                                                                              | Searching Products                                                                                                                                                        | Showing Lottie animation if no data found while searching                                                                                                                   |


Add Product Fragment



| ![Screenshot_2024-05-27-20-25-03-60_c2d16710d642a9dce14ef796728f67af](https://github.com/Viraj76/SwipeProductsAssignments/assets/98775599/0fcb8ff6-b6b5-4c37-9b1f-97cd20e341aa) | ![Screenshot_2024-05-27-20-25-13-55_c2d16710d642a9dce14ef796728f67af](https://github.com/Viraj76/SwipeProductsAssignments/assets/98775599/6fea6169-cae4-4135-9bd8-3cf01806ca3c) | ![Screenshot_2024-05-27-20-25-22-14_c2d16710d642a9dce14ef796728f67af](https://github.com/Viraj76/SwipeProductsAssignments/assets/98775599/71d71348-f55f-497d-8134-f7e9b7bd0b4f) |
|----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------|----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------|----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------|
| Asking user to turn on internet then only we can post the products                                                                                                          | Layouts for adding product detail when internet is on                                                                                                                    | No field should be empty                                                                                                                                                |




| ![Screenshot_2024-05-27-20-26-05-22_c2d16710d642a9dce14ef796728f67af](https://github.com/Viraj76/SwipeProductsAssignments/assets/98775599/9ce363de-7be6-4e3f-aed0-f5b26ff93456) | ![Screenshot_2024-05-27-20-26-11-37_c2d16710d642a9dce14ef796728f67af](https://github.com/Viraj76/SwipeProductsAssignments/assets/98775599/a840c0ef-8295-4a03-87a3-a1738bd2c1c8) | ![Screenshot_2024-05-27-20-26-28-45_c2d16710d642a9dce14ef796728f67af](https://github.com/Viraj76/SwipeProductsAssignments/assets/98775599/84405734-81cc-4918-a4fc-382ceb2c046d) |
|----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------|----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------|----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------|
| Selecting Product Type                                                                                                                                                   | Entering price and tax                                                                                                                                                | Showing toast if the image is not PNG or JPEG (Optional)                                                                                                               |





| ![Screenshot_2024-05-27-20-26-32-72_c2d16710d642a9dce14ef796728f67af](https://github.com/Viraj76/SwipeProductsAssignments/assets/98775599/27c5e223-73f6-4278-b9ab-9b0acb6fbec6) | ![Screenshot_2024-05-27-20-26-44-25_c2d16710d642a9dce14ef796728f67af](https://github.com/Viraj76/SwipeProductsAssignments/assets/98775599/95b302f1-0154-4e14-b8ef-2c42db8d5888) | ![Screenshot_2024-05-27-20-26-51-61_c2d16710d642a9dce14ef796728f67af](https://github.com/Viraj76/SwipeProductsAssignments/assets/98775599/035de23a-335d-4766-9ad6-42934f57920c) |
|----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------|----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------|----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------|
| Showing toast if the image is not having aspect ratio 1:1                                                                                                                | Selected Image which has PNG or JPEG format and 1:1 aspect ratio                                                                                                       | Showing posting product dialog                                                                                                                                         |






| ![Screenshot_2024-05-27-20-35-30-82_c2d16710d642a9dce14ef796728f67af](https://github.com/Viraj76/SwipeProductsAssignments/assets/98775599/fe9b26e0-5db6-4983-8578-56f01e9a5a69) | ![Screenshot_2024-05-27-20-35-38-53_c2d16710d642a9dce14ef796728f67af](https://github.com/Viraj76/SwipeProductsAssignments/assets/98775599/10c971ac-451f-4485-99f7-3c76ef96403a) | ![Screenshot_2024-05-27-20-42-04-25_c2d16710d642a9dce14ef796728f67af](https://github.com/Viraj76/SwipeProductsAssignments/assets/98775599/08df4dc3-19a0-4bef-98a5-6bb099984d40) |
|----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------|----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------|----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------|
| Showing posting done dialog once product is posted                                                                                                                         | Received notification when product is posted                                                                                                                            | Product has been posted, Searching the product that we have just posted.                                                                                                |



