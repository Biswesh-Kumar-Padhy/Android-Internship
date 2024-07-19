package com.biswesh.a19_shared_preference

import android.content.Context
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.biswesh.a19_shared_preference.ui.theme._19SharedPreferenceTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            _19SharedPreferenceTheme {
//                LocalStateExample()
                SharedPrefExample(context = this)
            }
        }
    }

    @Composable
    fun LocalStateExample(){
        var text by remember { mutableStateOf("") }
        var savedText by remember { mutableStateOf("") }
        Column(
                modifier = Modifier.padding(20.dp),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
        ) {
            TextField(value = text, onValueChange = {text = it},
                    label = { Text(text = "Enter some text")})
            Spacer(modifier = Modifier.height(20.dp))
            Text(text = "Saved Text: $savedText")
            androidx.compose.material3.Button(onClick = {savedText = text}){
                Text(text = "Save")
            }
        }

    }

    @Composable
    fun SharedPrefExample(context: Context){
        val sharedPref = context.getSharedPreferences("my_pref", Context.MODE_PRIVATE)
        val editor  = sharedPref.edit()
        var text by remember {
            mutableStateOf(sharedPref.getString("saved_text", "") ?: "")
        }

        Column(
            modifier = Modifier.padding(20.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            TextField(value = text, onValueChange = {text = it},
                label = { Text(text = "Enter some text")})
            Spacer(modifier = Modifier.height(20.dp))
            Text(text = "Saved Text: ${sharedPref.getString("saved_text", "")}")
            androidx.compose.material3.Button(onClick = {
                editor.putString("saved_text", text)
                editor.apply()
            }){
                Text(text = "Save to shared pref")
            }
        }
    }
}

