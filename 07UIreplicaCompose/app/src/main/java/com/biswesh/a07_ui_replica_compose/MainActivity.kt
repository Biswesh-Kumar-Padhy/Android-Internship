package com.biswesh.a07_ui_replica_compose

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.FilledTonalButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.biswesh.a07_ui_replica_compose.ui.theme._07UIreplicaComposeTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            _07UIreplicaComposeTheme {
                //Main Column
                Column(
                    modifier = Modifier.padding(20.dp),
                ) {
                    //Card Box
                    ElevatedCard(
                        elevation = CardDefaults.cardElevation(
                            defaultElevation = 1.dp
                        ),
                        colors = CardDefaults.cardColors(
                            containerColor = Color(230,230,230,255),
                        ),
                        modifier = Modifier.size(width = 400.dp, height = 620.dp),
                    ) {
                        //Center Align
                        Column(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(25.dp),
                            horizontalAlignment = Alignment.CenterHorizontally
                        ) {
                            //Title
                            Text(
                                text = "Jetpack Compose",
                                fontSize = 30.sp,
                                color = Color(6, 74, 124, 255),

                            )
                            //Icon
                            Image(
                                painter = painterResource(id = R.drawable.jetpackicon),
                                contentDescription = "",
                                modifier = Modifier
                                    .padding(20.dp)
                                    .size(110.dp)
                            )
                        }

                        //Login Icon
                        Text(
                            text = "Login",
                            fontSize = 31.sp,
                            color = Color(10, 102, 65, 255),
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(30.dp,0.dp,0.dp,0.dp)
//                                .background(Color.Cyan),

                            )
                        Column(
                            modifier = Modifier.padding(30.dp,30.dp,30.dp,10.dp),
                        ) {
                            //Email ID
                            var text by remember { mutableStateOf("") }
                            OutlinedTextField(value = text,
                                onValueChange = { text = it },
                                label = { Text("Email ID or Mobile Number")},
                                modifier = Modifier.padding(0.dp,0.dp,0.dp,28.dp).fillMaxWidth()
                            )
                            //Password
                            var password by rememberSaveable { mutableStateOf("") }
                            var passwordVisible by remember { mutableStateOf(false) }
                            OutlinedTextField(
                                value = password,
                                onValueChange = { password = it },
                                label = { Text("Password") },
                                modifier = Modifier.fillMaxWidth(),
                                singleLine = true,
                                visualTransformation = if (passwordVisible) VisualTransformation.None else PasswordVisualTransformation(),
                                trailingIcon = {
                                    IconButton(onClick = { passwordVisible = !passwordVisible }) {
                                        Icon(
                                            imageVector = if (passwordVisible) Icons.Default.Visibility else Icons.Default.VisibilityOff,
                                            contentDescription = "Toggle password visibility"
                                        )
                                    }
                                }
                            )
                        }
                        //Forget Password
                        Column(
                            modifier = Modifier.padding(0.dp,0.dp,30.dp,0.dp).fillMaxWidth(),
                            horizontalAlignment = Alignment.End
                        ) {
                            Text(
                                "Forget Password?",
                                color = Color(26, 115, 87, 255)
                            )
                        }
                        //Login Icon
                        FilledTonalButton(
                            onClick = { println("Button is Clicked") },
                            colors = ButtonDefaults.filledTonalButtonColors(Color(0, 99, 154, 255)),
                            contentPadding = PaddingValues(horizontal = 35.dp, vertical = 17.dp),
                            modifier = Modifier.padding(30.dp,23.dp,0.dp,0.dp)
                        ) {
                            Text(text = "Login", color = Color.White , fontSize = 17.sp)
                        }
                    }
                    //Last line
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .size(width = 100.dp,height = 90.dp),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.Center
                    ) {
                        Text(
                            text = "Don't have an account? ",
                        )
                        Text(
                            text = "Register",
                            color = Color(16, 98, 145, 255)
                        )

                    }
                }
            }
        }
    }

}