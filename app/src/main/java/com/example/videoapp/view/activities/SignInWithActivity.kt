package com.example.videoapp.view.activities

import android.content.ContentValues.TAG
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import com.example.videoapp.R
import com.example.videoapp.databinding.ActivitySignInWithBinding
import com.google.android.gms.auth.api.identity.BeginSignInRequest
import com.google.android.gms.auth.api.identity.Identity
import com.google.android.gms.auth.api.identity.SignInClient
import com.google.android.gms.common.api.ApiException
import com.google.android.gms.common.api.CommonStatusCodes
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.GoogleAuthProvider
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

class SignInWithActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySignInWithBinding

    private lateinit var oneTapClient: SignInClient
    private lateinit var signInRequest: BeginSignInRequest

    private lateinit var signInWithGoogleButton: Button

    private val reqOneTap = 2
    private var showOneTapUI = true

    private lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySignInWithBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        auth = Firebase.auth

        signInWithGoogleButton = binding.signInWithGoogleButton
        signInWithGoogleButton.setOnClickListener {
            signInWithGoogle()
        }
    }

    private fun signInWithGoogle() {
        oneTapClient = Identity.getSignInClient(this)
        signInRequest = BeginSignInRequest.builder()
            .setPasswordRequestOptions(BeginSignInRequest.PasswordRequestOptions.builder()
                .setSupported(true)
                .build())
            .setGoogleIdTokenRequestOptions(
                BeginSignInRequest.GoogleIdTokenRequestOptions.builder()
                    .setSupported(true)
                    .setServerClientId(getString(R.string.default_web_client_id))
                    .setFilterByAuthorizedAccounts(true)
                    .build())
            .setAutoSelectEnabled(true)
            .build()

        /*oneTapClient.beginSignIn(signInRequest)
            .addOnSuccessListener(this) { result ->
                try {
                    startIntentSenderForResult(
                        result.pendingIntent.intentSender, reqOneTap,
                        null, 0, 0, 0, null)
                } catch (e: IntentSender.SendIntentException) {
                    Log.e(TAG, "Couldn't start One Tap UI: ${e.localizedMessage}")
                }
            }
            .addOnFailureListener(this) { e ->
                // No saved credentials found. Launch the One Tap sign-up flow, or
                // do nothing and continue presenting the signed-out UI.
                Log.d(TAG, e.localizedMessage)
            } */
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        when (requestCode) {
            reqOneTap -> {
                try {
                    val credential = oneTapClient.getSignInCredentialFromIntent(data)
                    val idToken = credential.googleIdToken
                    when {
                        idToken != null -> {
                            val firebaseCredential = GoogleAuthProvider.getCredential(idToken, null)
                            auth.signInWithCredential(firebaseCredential)
                                .addOnCompleteListener(this) { task ->
                                    if (task.isSuccessful) {
                                        Log.d(TAG, "signInWithCredential:success")
                                        val navigationActivityIntent =
                                            Intent(this@SignInWithActivity, NavigationActivity::class.java)
                                        startActivity(navigationActivityIntent)
                                        finish()

                                    } else {
                                        Log.w(TAG, "signInWithCredential:failure", task.exception)
                                    }
                                }
                        }
                        else -> {
                            Log.d(TAG, "No ID token!")
                        }
                    }
                } catch (e: ApiException) {
                    when (e.statusCode) {
                        CommonStatusCodes.CANCELED -> {
                            Log.d(TAG, "One-tap dialog was closed.")
                            showOneTapUI = false
                        }
                        CommonStatusCodes.NETWORK_ERROR -> {
                            Log.d(TAG, "One-tap encountered a network error.")
                        }
                        else -> {
                            Log.d(TAG, "Couldn't get credential from result." +
                                    " (${e.localizedMessage})")
                        }
                    }
                }
            }
        }
    }
}