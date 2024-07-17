package com.biswesh.a15_firebaselogin_auth

import android.content.ContentValues.TAG
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import com.biswesh.a15_firebaselogin_auth.ui.theme._15FirebaseLoginAuthTheme
import com.google.firebase.FirebaseException
import com.google.firebase.FirebaseTooManyRequestsException
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException
import com.google.firebase.auth.FirebaseAuthMissingActivityForRecaptchaException
import com.google.firebase.auth.PhoneAuthCredential
import com.google.firebase.auth.PhoneAuthOptions
import com.google.firebase.auth.PhoneAuthProvider
import com.google.firebase.auth.PhoneAuthProvider.ForceResendingToken
import java.util.concurrent.TimeUnit

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            _15FirebaseLoginAuthTheme {
                LoginScreen()
            }
        }
    }


    private val auth = FirebaseAuth.getInstance()
    var storedVerificationId: String? = null
    lateinit var resendToken: ForceResendingToken

    private val callbacks = object : PhoneAuthProvider.OnVerificationStateChangedCallbacks() {

        override fun onVerificationCompleted(credential: PhoneAuthCredential) {
            // This callback will be invoked in two situations:
            // 1 - Instant verification. In some cases the phone number can be instantly
            //     verified without needing to send or enter a verification code.
            // 2 - Auto-retrieval. On some devices Google Play services can automatically
            //     detect the incoming verification SMS and perform verification without
            //     user action.
            Log.d(TAG, "onVerificationCompleted:$credential")
            signInWithPhoneAuthCredential(credential)
        }

        override fun onVerificationFailed(e: FirebaseException) {
            // This callback is invoked in an invalid request for verification is made,
            // for instance if the the phone number format is not valid.
            Log.w(TAG, "onVerificationFailed", e)

            when (e) {
                is FirebaseAuthInvalidCredentialsException -> {
                    // Invalid request
                    println("ERROR: $e")
                }

                is FirebaseTooManyRequestsException -> {
                    // The SMS quota for the project has been exceeded
                    println("ERROR: $e")
                }

                is FirebaseAuthMissingActivityForRecaptchaException -> {
                    // reCAPTCHA verification attempted with null Activity
                    println("ERROR: $e")
                }
            }
            // Show a message and update the UI
        }

        override fun onCodeSent(
            verificationId: String,
            token: ForceResendingToken,
        ) {
            // The SMS verification code has been sent to the provided phone number, we
            // now need to ask the user to enter the code and then construct a credential
            // by combining the code with a verification ID.
            Log.d(TAG, "onCodeSent:$verificationId")

            // Save verification ID and resending token so we can use them later
            storedVerificationId = verificationId
            resendToken = token
        }
    }

    private fun startPhoneNumberVerification(phoneNumber: String) {
        val options = PhoneAuthOptions.newBuilder(auth)
            .setPhoneNumber(phoneNumber) // Phone number to verify
            .setTimeout(60L, TimeUnit.SECONDS) // Timeout and unit
            .setActivity(this) // Activity (for callback binding)
            .setCallbacks(callbacks) // OnVerificationStateChangedCallbacks
            .build()
        PhoneAuthProvider.verifyPhoneNumber(options)
    }

    private fun verifyOTP(otp: String){
        val credential = PhoneAuthProvider.getCredential(storedVerificationId!!, otp)
        signInWithPhoneAuthCredential(credential)
    }
    private fun signInWithPhoneAuthCredential(credential: PhoneAuthCredential) {
        auth.signInWithCredential(credential)
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                    // Sign in success, update UI with the signed-in user's information
                    Log.d(TAG, "signInWithCredential:success")

                    val user = task.result?.user
                } else {
                    // Sign in failed, display a message and update the UI
                    Log.w(TAG, "signInWithCredential:failure", task.exception)
                    if (task.exception is FirebaseAuthInvalidCredentialsException) {
                        // The verification code entered was invalid
                    }
                    // Update UI
                }
            }
    }

    @Composable
    fun LoginScreen() {
        val phoneNumber = remember { mutableStateOf("") }
        val otp = remember { mutableStateOf("") }
        Scaffold { innerPadding ->
            Column(
                modifier = Modifier
                    .padding(innerPadding)
                    .padding(50.dp)
            ) {
                Card(
                    modifier = Modifier
                        .height(500.dp)
                        .width(700.dp)
                ) {
                    Column(modifier = Modifier.padding(20.dp)) {
                        Spacer(modifier = Modifier.height(50.dp))
                        TextField(
                            value = phoneNumber.value,
                            onValueChange = { phoneNumber.value = it },
                            label = { Text(text = "Phone Number") },
                            modifier = Modifier
                                .fillMaxWidth()
                                .clip(CircleShape)
                        )
                        Spacer(modifier = Modifier.height(40.dp))
                        Button(onClick = {
                            startPhoneNumberVerification(phoneNumber.value)
                        }) {
                            Text(text = "Sent OTP")
                        }
                        Spacer(modifier = Modifier.height(30.dp))
                        TextField(
                            value = otp.value,
                            onValueChange = { otp.value = it },
                            label = { Text(text = "OTP") },
                            modifier = Modifier
                                .fillMaxWidth()
                                .clip(CircleShape),
                        )
                        Spacer(modifier = Modifier.height(40.dp))
                        Button(onClick = {
                            verifyOTP(otp.value)
                        }) {
                            Text(text = "Validate OTP")
                        }
                    }
                }
            }
        }
    }
}

