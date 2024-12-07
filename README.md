
# Dose Reminder

A mobile application designed to help users remember to take their medications on time, with the added functionality of retrieving detailed medicine information from the OpenFDA API.


## Description

Course: ADEV-3007 (254278) Mobile Application Development
Instructor: Michael Bialowas
Author: Monica Lopez

The Dose Reminder App is an Android application developed as the final project for a Mobile Development course. The app addresses the issue of missed medication doses by providing timely reminders. It also allows users to search for medication details using the OpenFDA API and stores this information locally using SQLite.

This project was built in Android Studio Ladybug | 2024.2.1 Patch 2, with a focus on combining modern Android development practices, Firebase authentication, and API integration to deliver a practical and user-friendly experience.
## Features

- Medication Reminders
- Medication Search
- Local Storage
- User Authentication
- Intuitive Design


## Architecture

The app follows the Model-View-Controller (MVC) architecture:

- Model: Manages the SQLite database for storing medications and reminders.
- View: Provides a clean and responsive UI using Jetpack Compose.
- Controller: Handles logic like API communication and Firebase authentication.
## Installation

Requirements
- Android Studio: Version Ladybug | 2024.2.1 Patch 2 or newer.
- Minimum SDK: Android API 24 (Android 7.0).
- Firebase: Add your google-services.json file for Firebase integration.
- API Key: Obtain an API key from OpenFDA if needed for additional configurations.
    
## Usage

- Sign In: Log in or register using Firebase authentication.
- Search for Medications: Use the search bar to fetch medicine details from the OpenFDA API.
- Add Medications: Save medicine details locally in the app.
- Set Reminders: Schedule reminders for medications based on prescribed doses and frequencies.
- View Reminders: View upcoming reminders and receive notifications when it’s time to take medications.

## Technologies Used

- Programming Language: Kotlin
- IDE: Android Studio Ladybug | 2024.2.1 Patch 2
- UI Framework: Jetpack Compose
- Database: SQLite
- Authentication: Firebase Authentication
- API: OpenFDA API
## Authors

- https://github.com/monicalopezoliveros

