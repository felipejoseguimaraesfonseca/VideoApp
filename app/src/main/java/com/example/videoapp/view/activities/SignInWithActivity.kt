package com.example.videoapp.view.activities

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.videoapp.R
import com.example.videoapp.databinding.ActivitySignInWithBinding
import com.facebook.AccessToken
import com.facebook.CallbackManager
import com.facebook.FacebookCallback
import com.facebook.FacebookException
import com.facebook.login.LoginResult
import com.facebook.login.widget.LoginButton
import com.google.firebase.auth.FacebookAuthProvider
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

class SignInWithActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySignInWithBinding
    private lateinit var auth: FirebaseAuth
    private lateinit var callbackManager: CallbackManager
    private lateinit var signInWithFacebookButton: LoginButton

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySignInWithBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        auth = Firebase.auth

        signInWithFacebookButton = binding.signInWithFacebookButton as LoginButton
        signInWithFacebookButton.setOnClickListener {
            callbackManager = CallbackManager.Factory.create()

            signInWithFacebookButton.setPermissions("email", "public-profile")
            signInWithFacebookButton.registerCallback(callbackManager, object : FacebookCallback<LoginResult> {

                @SuppressLint("LogConditional")
                override fun onSuccess(result: LoginResult) {
                    Log.d(TAG, "facebook:onSuccess:$result")
                    handleFacebookAccessToken(result.accessToken)
                }

                override fun onCancel() {
                    Log.d(TAG, "facebook:onCancel")
                }

                override fun onError(error: FacebookException) {
                    Log.d(TAG, "facebook:onError", error)
                }
            })
        }

    }

    override fun onStart() {
        super.onStart()
        val currentUser = auth.currentUser
        updateUI(currentUser)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        callbackManager.onActivityResult(requestCode, resultCode, data)
    }

    @SuppressLint("LogConditional")
    internal fun handleFacebookAccessToken(token: AccessToken) {
        Log.d(TAG, "handleFacebookAccessToken:$token")

        val credential = FacebookAuthProvider.getCredential(token.token)
        auth.signInWithCredential(credential).addOnCompleteListener(this) { task ->
            if (task.isSuccessful) {
                Log.d(TAG, "signInWithCredential:success")
                val user = auth.currentUser
                updateUI(user)

                val intent = Intent(this, NavigationActivity::class.java)
                startActivity(intent)
                finish()
            } else {
                Log.w(TAG, "signInWithCredential:failure", task.exception)
                Toast.makeText(
                    baseContext, 
                    R.string.authenticationFailed, 
                    Toast.LENGTH_SHORT
                ).show()
                updateUI(null)
            }
        }
    }

    private fun updateUI(user: FirebaseUser?){}
    
    companion object {
        private const val TAG = "FacebookLogin"
    }
}