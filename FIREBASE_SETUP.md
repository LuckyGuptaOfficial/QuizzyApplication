# Firebase Setup Guide - Step by Step

## Problem
आपका `google-services.json` file placeholder है, इसलिए Firebase connect नहीं हो रहा। Network error आ रहा है क्योंकि Firebase को proper configuration नहीं मिल रही।

## Solution - Firebase Setup करें

### Step 1: Firebase Project बनाएं

1. **Firebase Console पर जाएं:**
   - https://console.firebase.google.com/ पर जाएं
   - Google account से login करें

2. **New Project बनाएं:**
   - "Add project" या "Create a project" पर click करें
   - Project name दें (जैसे: "QuizzyApp")
   - Google Analytics enable/disable करें (optional)
   - "Create project" पर click करें

### Step 2: Android App Add करें

1. **Project dashboard पर:**
   - Android icon पर click करें (या "Add app" → Android)

2. **App details भरें:**
   - **Package name**: `com.example.homeandroid` (यह exact होना चाहिए)
   - **App nickname**: Quizzy (optional)
   - **Debug signing certificate SHA-1**: (अभी skip कर सकते हैं)
   - "Register app" पर click करें

### Step 3: google-services.json Download करें

1. **Download page पर:**
   - `google-services.json` file download करें
   - File को save करें

2. **File को project में copy करें:**
   - Downloaded `google-services.json` file को open करें
   - सारा content copy करें
   - `app/google-services.json` file में paste करें (placeholder को replace करें)

### Step 4: Firebase Authentication Enable करें

1. **Firebase Console में:**
   - Left sidebar में "Authentication" पर click करें
   - "Get started" पर click करें (अगर पहली बार है)

2. **Sign-in method enable करें:**
   - "Sign-in method" tab पर जाएं
   - "Email/Password" provider पर click करें
   - "Enable" toggle ON करें
   - "Save" पर click करें

### Step 5: Test User बनाएं

1. **Authentication → Users tab:**
   - "Add user" button पर click करें
   - Email: `test@quizzy.com` (या कोई भी email)
   - Password: `test123456` (या कोई भी password)
   - "Add user" पर click करें

2. **Note:** App में login करते समय:
   - अगर username में "@" नहीं है, तो app automatically "@quizzy.com" append करेगा
   - Example: अगर आप `test` enter करेंगे, तो `test@quizzy.com` use होगा

### Step 6: Project Sync करें

1. **Android Studio में:**
   - File → Sync Project with Gradle Files
   - या Gradle sync button पर click करें

2. **Build करें:**
   - Build → Rebuild Project
   - अगर कोई error आए तो check करें

### Step 7: Test करें

1. **App run करें:**
   - Emulator या physical device पर app install करें
   - Login screen पर जाएं

2. **Login करें:**
   - Email/Username: `test` (या जो भी आपने Firebase में बनाया)
   - Password: `test123456` (या जो भी password दिया)
   - "Sign in" पर click करें

## Important Notes

⚠️ **google-services.json file:**
- यह file sensitive है, इसे Git में commit न करें (अगर public repo है)
- हर developer को अपना own Firebase project बनाना होगा
- File path: `app/google-services.json` (exact location)

⚠️ **Package Name:**
- Package name `com.example.homeandroid` exact होना चाहिए
- अगर package name change करेंगे, तो Firebase में भी update करना होगा

⚠️ **Network Error:**
- अगर अभी भी network error आ रहा है:
  1. Internet connection check करें
  2. Firebase project properly setup है या नहीं check करें
  3. google-services.json file सही location में है या नहीं check करें
  4. Gradle sync करें

## Troubleshooting

### Error: "Failed to get FirebaseApp"
- Solution: google-services.json file सही location में है या नहीं check करें (`app/` folder में)

### Error: "Network error"
- Solution: 
  1. Internet connection check करें
  2. Firebase project properly configured है या नहीं check करें
  3. Authentication method enable है या नहीं check करें

### Error: "User not found" या "Wrong password"
- Solution: Firebase Console → Authentication → Users में user exist करता है या नहीं check करें

## Quick Checklist

- [ ] Firebase project created
- [ ] Android app added with package name `com.example.homeandroid`
- [ ] google-services.json downloaded और `app/` folder में placed
- [ ] Email/Password authentication enabled
- [ ] Test user created in Firebase
- [ ] Gradle sync completed
- [ ] App rebuilt और tested

## Alternative: Testing Without Firebase (Temporary)

अगर आप अभी Firebase setup नहीं कर सकते, तो temporarily authentication को bypass कर सकते हैं (development के लिए only):

```kotlin
// AuthRepository.kt में temporary bypass
suspend fun signIn(email: String, password: String): Result<FirebaseUser> {
    // Temporary: Allow any credentials for testing
    if (email.isNotBlank() && password.isNotBlank()) {
        // Create a mock user or navigate directly
        return Result.success(/* mock user */)
    }
    return Result.failure(Exception("Invalid credentials"))
}
```

**Note:** Production में यह use न करें, सिर्फ testing के लिए है।

