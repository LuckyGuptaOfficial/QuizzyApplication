# Troubleshooting Network Error

## Problem
Network error आ रहा है: "A network error (such as timeout, interrupted connection or unreachable host) has occurred."

## Possible Causes और Solutions

### 1. ✅ Firebase Authentication Enable नहीं है

**Check करें:**
1. Firebase Console → Authentication → Sign-in method
2. "Email/Password" provider enable है या नहीं

**Solution:**
- "Email/Password" पर click करें
- "Enable" toggle ON करें
- "Save" करें

### 2. ✅ User Firebase में Exist नहीं करता

**Check करें:**
- Firebase Console → Authentication → Users
- User list में आपका email है या नहीं

**Solution:**
- "Add user" button पर click करें
- Email और password enter करें
- User create करें

**Important:** 
- App में login करते समय exact email use करें
- अगर username में "@" नहीं है, तो app automatically "@quizzy.com" append करेगा
- Example: अगर आप `test` enter करेंगे, तो Firebase में `test@quizzy.com` होना चाहिए

### 3. ✅ Internet Connection Issue

**Check करें:**
- Device/Emulator का internet connection
- WiFi/Mobile data ON है या नहीं

**Solution:**
- Internet connection check करें
- Browser में कुछ website open करके test करें

### 4. ✅ Gradle Sync नहीं हुआ

**Check करें:**
- Android Studio में Gradle sync complete हुआ या नहीं

**Solution:**
1. File → Sync Project with Gradle Files
2. Build → Rebuild Project
3. App को uninstall करके फिर से install करें

### 5. ✅ google-services.json File Issue

**Check करें:**
- File location: `app/google-services.json`
- File में actual values हैं या placeholder (YOUR_PROJECT_NUMBER, etc.)

**Solution:**
- अगर placeholder है, तो Firebase से actual file download करें
- File को `app/` folder में place करें
- Gradle sync करें

### 6. ✅ Firebase Project Configuration

**Check करें:**
- Firebase Console → Project Settings
- Android app properly added है या नहीं
- Package name match कर रहा है या नहीं (`com.example.homeandroid`)

### 7. ✅ Emulator Network Issue

**अगर Emulator use कर रहे हैं:**
- Emulator का internet connection check करें
- Physical device पर test करें

**Solution:**
- Emulator restart करें
- Cold boot करें (Emulator → Cold Boot Now)

## Step-by-Step Debugging

### Step 1: Firebase Console Check
1. https://console.firebase.google.com/ पर जाएं
2. अपना project select करें
3. Authentication → Sign-in method
4. Email/Password enable है या नहीं check करें

### Step 2: User Check
1. Authentication → Users
2. User list check करें
3. अगर user नहीं है, तो create करें

### Step 3: App में Test
1. App को uninstall करें
2. Clean build करें (Build → Clean Project)
3. Rebuild करें (Build → Rebuild Project)
4. App install करें
5. Login try करें

### Step 4: Error Message Check
अब improved error messages आएंगी:
- "User not found" - User Firebase में नहीं है
- "Wrong password" - Password गलत है
- "Network error" - Internet connection issue
- "Email/Password sign-in is not enabled" - Firebase में enable नहीं है

## Common Error Messages और Solutions

| Error Message | Solution |
|--------------|----------|
| "User not found" | Firebase Console में user create करें |
| "Wrong password" | Correct password enter करें |
| "Network error" | Internet connection check करें |
| "Email/Password sign-in is not enabled" | Firebase Console → Authentication → Sign-in method → Email/Password enable करें |
| "Too many requests" | कुछ समय wait करके फिर try करें |

## Quick Test

1. **Firebase Console में:**
   - Authentication → Users → Add user
   - Email: `test@quizzy.com`
   - Password: `test123456`

2. **App में:**
   - Email/Username: `test@quizzy.com`
   - Password: `test123456`
   - Sign in करें

अगर यह work करता है, तो problem user creation में थी।

## Still Not Working?

अगर अभी भी problem है, तो:

1. **Logcat check करें:**
   - Android Studio → Logcat
   - Error messages देखें
   - Firebase related errors search करें

2. **Firebase Console → Authentication → Users:**
   - Login attempt दिख रहा है या नहीं check करें
   - Failed attempts देखें

3. **Network Security:**
   - AndroidManifest.xml में internet permission है
   - Network security config check करें

