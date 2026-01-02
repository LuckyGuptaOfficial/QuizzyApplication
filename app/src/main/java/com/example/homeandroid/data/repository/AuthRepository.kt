package com.example.homeandroid.data.repository

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseAuthException
import com.google.firebase.auth.FirebaseUser
import kotlinx.coroutines.tasks.await

class AuthRepository {
    private val firebaseAuth = FirebaseAuth.getInstance()

    suspend fun signIn(email: String, password: String): Result<FirebaseUser> {
        return try {
            val result = firebaseAuth.signInWithEmailAndPassword(email, password).await()
            val user = result.user
            if (user != null) {
                Result.success(user)
            } else {
                Result.failure(Exception("Sign in failed: User is null"))
            }
        } catch (e: FirebaseAuthException) {
            val errorMessage = when (e.errorCode) {
                "ERROR_INVALID_EMAIL" -> "Invalid email address"
                "ERROR_WRONG_PASSWORD" -> "Wrong password"
                "ERROR_USER_NOT_FOUND" -> "User not found. Please check your email"
                "ERROR_USER_DISABLED" -> "User account has been disabled"
                "ERROR_TOO_MANY_REQUESTS" -> "Too many requests. Please try again later"
                "ERROR_OPERATION_NOT_ALLOWED" -> "Email/Password sign-in is not enabled"
                "ERROR_NETWORK_REQUEST_FAILED" -> "Network error. Please check your internet connection"
                else -> e.message ?: "Authentication failed: ${e.errorCode}"
            }
            Result.failure(Exception(errorMessage))
        } catch (e: Exception) {
            val errorMessage = when {
                e.message?.contains("network", ignoreCase = true) == true -> 
                    "Network error. Please check your internet connection"
                e.message?.contains("timeout", ignoreCase = true) == true -> 
                    "Connection timeout. Please try again"
                e.message?.contains("unreachable", ignoreCase = true) == true -> 
                    "Cannot reach server. Please check your internet connection"
                else -> e.message ?: "Login failed. Please try again"
            }
            Result.failure(Exception(errorMessage))
        }
    }

    fun getCurrentUser(): FirebaseUser? {
        return firebaseAuth.currentUser
    }

    fun signOut() {
        firebaseAuth.signOut()
    }

    fun isUserLoggedIn(): Boolean {
        return firebaseAuth.currentUser != null
    }
}

