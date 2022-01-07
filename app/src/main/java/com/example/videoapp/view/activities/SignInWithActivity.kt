package com.example.videoapp.view.activities

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.example.videoapp.databinding.ActivitySignInWithBinding

abstract class SignInWithActivity : AppCompatActivity(), View.OnClickListener {

    private lateinit var binding: ActivitySignInWithBinding
    /*private lateinit var auth: FirebaseAuth
    private lateinit var callbackManager: CallbackManager
    private lateinit var loginButton: LoginButton*/

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySignInWithBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        //setListeners()
    }

    /*override fun onClick(view: View) {
        val id = view.id

        if (id == R.id.sign_in_with_facebook_button) {
            signInWithFacebook()
        }
    }*/

    /*private fun signInWithFacebook() {
        auth = Firebase.auth
        callbackManager = CallbackManager.Factory.create()

        loginButton.setPermissions("email", "public-profile")
        loginButton.registerCallback(callbackManager, object : FacebookCallback<LoginResult>{

            override fun onSuccess(result: LoginResult) {
                Log.d(TAG, "facebook:onSuccess:$result")
                handleFacebookAccessToken(result.accessToken)
            }

            override fun onCancel() {
                Log.d(TAG,"facebook:onCancel")
            }

            override fun onError(error: FacebookException) {
                Log.d(TAG, "facebook:onError", error)
            }
        })
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

    private fun handleFacebookAccessToken(token: AccessToken) {
        Log.d(TAG, "handleFacebookAccessToken:$token")
        
        val credential = FacebookAuthProvider.getCredential(token.token)
        auth.signInWithCredential(credential).addOnCompleteListener(this) { task ->
            if (task.isSuccessful) {
                Log.d(TAG, "signInWithCredential:success")
                val user = auth.currentUser
                updateUI(user)

                val intent = Intent(this, NavigationActivity::class.java)
                startActivity(intent)
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
    }*/

    /*private fun setListeners() {
        binding.signInWithFacebookButton.setOnClickListener(this)
    }
    
    abstract fun updateUI(user: FirebaseUser?)
    
    companion object {
        private const val TAG = "FacebookLogin"
    }*/
}