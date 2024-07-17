package com.biswesh.a15_firebaselogin_auth

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
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
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import com.biswesh.a15_firebaselogin_auth.ui.theme._15FirebaseLoginAuthTheme
import com.google.firebase.auth.FirebaseAuth

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            _15FirebaseLoginAuthTheme {
                LoginScreen()
            }
        }
    }
}

private val auth = FirebaseAuth.getInstance()
fun signUp(email: String, password: String) {
    auth.createUserWithEmailAndPassword(email, password)
        .addOnSuccessListener { task ->
            if (task.user != null) {
                println("User Created")
                val user = auth.currentUser
                println(user?.uid)
            }
        }
}

fun signIn(email: String, password: String) {
    auth.signInWithEmailAndPassword(email, password)
        .addOnSuccessListener { task ->
            if (task.user != null) {
                println("User Logged In")
                val user = auth.currentUser
                println(user?.uid)
            }
        }
}
@Composable
fun LoginScreen() {
    val email = remember { mutableStateOf("") }
    val password = remember { mutableStateOf("") }
    Scaffold { innerPadding ->
        Column(
            modifier = Modifier
                .padding(innerPadding)
                .padding(50.dp)
        ) {
            Card(modifier = Modifier
                .height(500.dp)
                .width(700.dp)) {
                Column(modifier = Modifier.padding(20.dp)) {
                    Spacer(modifier = Modifier.height(50.dp))
                    TextField(
                        value = email.value,
                        onValueChange = { email.value = it },
                        label = { Text(text = "Email") },
                        modifier = Modifier
                            .fillMaxWidth()
                            .clip(CircleShape)
                    )
                    Spacer(modifier = Modifier.height(10.dp))
                    TextField(
                        value = password.value,
                        onValueChange = { password.value = it },
                        label = { Text(text = "Password") },
                        modifier = Modifier
                            .fillMaxWidth()
                            .clip(CircleShape),
                        visualTransformation = PasswordVisualTransformation()
                    )
                    Spacer(modifier = Modifier.height(50.dp))
                    Row {
                        Button(onClick = {
                            signUp(email.value, password.value)
                        }) {
                            Text(text = "SignUp") //Register
                        }
                        Spacer(modifier = Modifier.width(70.dp))
                        Button(onClick = {
                            signIn(email.value, password.value)
                        }) {
                            Text(text = "SignIn") //Login
                        }
                    }
                }
            }
        }
    }
}