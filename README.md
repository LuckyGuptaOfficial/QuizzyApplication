# Quizzy - Android App

A simple Android application with Login → Home → Logout flow built using Kotlin, Jetpack Compose, and MVVM architecture.

## Features

- **Authentication**: Firebase Authentication for user login
- **Login Screen**: Beautiful login interface matching the Figma design
- **Home Screen**: Student dashboard displaying:
  - Daily metrics (Availability, Quiz attempts, Accuracy)
  - Today's summary with recommended video
  - Weekly overview (Quiz streak, Accuracy, Performance by topic)
- **Logout**: Secure logout functionality
- **MVVM Architecture**: Clean separation of concerns
- **API Integration**: Fetches dashboard data from provided Mock API

## Tech Stack

- **Language**: Kotlin
- **UI Framework**: Jetpack Compose
- **Architecture**: MVVM (Model-View-ViewModel)
- **Authentication**: Firebase Authentication
- **Networking**: Retrofit + OkHttp
- **Navigation**: Jetpack Navigation Compose
- **Image Loading**: Coil

## Setup Instructions

### Prerequisites

- Android Studio Hedgehog (2023.1.1) or later
- JDK 11 or higher
- Android SDK with API level 24+ (Android 7.0+)

### Firebase Setup

1. Create a new Firebase project at [Firebase Console](https://console.firebase.google.com/)
2. Add an Android app to your Firebase project with package name: `com.example.homeandroid`
3. Download the `google-services.json` file
4. Replace the placeholder `google-services.json` file in `app/` directory with your downloaded file
5. Enable **Email/Password** authentication in Firebase Console:
   - Go to Authentication → Sign-in method
   - Enable Email/Password provider

### Building the App

1. Clone the repository:
   ```bash
   git clone <repository-url>
   cd HomeAndroid
   ```

2. Open the project in Android Studio

3. Sync Gradle files (File → Sync Project with Gradle Files)

4. Build the project:
   ```bash
   ./gradlew build
   ```

5. Run the app on an emulator or physical device

### Creating Test User

Before testing the login functionality, you need to create a user in Firebase:

1. Go to Firebase Console → Authentication → Users
2. Click "Add user"
3. Enter an email (e.g., `SGGP782001@quizzy.com`) and password (e.g., `SG211|`)
4. Save the user

Alternatively, you can create users programmatically or use Firebase Auth's email verification flow.

### Default Credentials

The app comes with pre-filled credentials in the login screen:
- Username: `SGGP782001`
- Password: `SG211|`

**Note**: These credentials need to be registered in Firebase Authentication. The app will automatically append `@quizzy.com` to the username if it doesn't contain an `@` symbol.

## Project Structure

```
app/src/main/java/com/example/homeandroid/
├── data/
│   ├── api/              # Retrofit API service and client
│   ├── model/            # Data models for API responses
│   └── repository/       # Repository layer for data access
├── ui/
│   ├── navigation/       # Navigation setup
│   ├── screens/          # UI screens (Login, Home)
│   ├── theme/            # App theme and colors
│   └── viewmodel/        # ViewModels for MVVM architecture
└── MainActivity.kt       # Main activity entry point
```

## API Endpoint

The app fetches dashboard data from:
```
https://firebasestorage.googleapis.com/v0/b/user-contacts-ade83.appspot.com/o/student_dashboard.json?alt=media&token=0091b4c2-2ee2-4326-99cd-96d5312b34bd
```

## Generating APK

To generate a release APK:

1. Build the release APK:
   ```bash
   ./gradlew assembleRelease
   ```

2. The APK will be located at:
   ```
   app/build/outputs/apk/release/app-release.apk
   ```

For a signed APK, configure signing in `app/build.gradle.kts` and use:
```bash
./gradlew assembleRelease
```

## Error Handling

The app includes basic error handling for:
- Network failures
- Authentication errors
- Invalid credentials
- API response errors

## Screenshots

The app implements the following screens as per Figma designs:
- **Login Screen**: Dark background with welcome message and sign-in form
- **Home Screen**: Student dashboard with metrics, summary, and weekly overview
- **Settings/Logout**: Accessible via settings icon in the top bar

## Notes

- The app uses Firebase Authentication for secure user management
- All network requests are logged for debugging (can be disabled in production)
- The app follows Material Design 3 guidelines
- MVVM architecture ensures testability and maintainability

## License

This project is confidential and proprietary. Do not share, upload, or publish without permission.


