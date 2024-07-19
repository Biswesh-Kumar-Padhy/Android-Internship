package com.biswesh.a17_firebase_firestore

import android.content.ContentValues.TAG
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.service.controls.ControlsProviderService
import android.util.Log
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.compose.setContent
import androidx.activity.result.contract.ActivityResultContracts
import androidx.annotation.RequiresApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import coil.compose.rememberAsyncImagePainter
import com.biswesh.a17_firebase_firestore.ui.theme._17FirebaseFirestoreTheme
import com.google.firebase.Firebase
import com.google.firebase.firestore.firestore
import com.google.firebase.storage.storage
import java.util.UUID

class MainActivity : ComponentActivity() {
    @RequiresApi(Build.VERSION_CODES.R)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            _17FirebaseFirestoreTheme {
                Column {
                    AddUserScreen()

                    val userList = remember { mutableStateOf<List<User>>(emptyList()) }
                    LaunchedEffect(Unit) { //for callback, suspend fun to call in Views
                        fetchStudents { users->
                            userList.value = users
                        }
                    }
                    LazyColumn(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(20.dp),
                    ) {
                        items(userList.value) {user ->
                            Text(text = "Name: ${user.name}, Age: ${user.age}, SIC: ${user.sic}")
                        }
                    }
                    Row(
                        modifier = Modifier.fillMaxWidth().height(250.dp),
                        horizontalArrangement = Arrangement.Center,
                        verticalAlignment = Alignment.Bottom
                    ) {
                        ImageUploadScreen()
                    }
                }
            }
        }
    }


    private val db = Firebase.firestore
    @RequiresApi(Build.VERSION_CODES.R)
    fun addUser(name: String, age: Int, sic: String) {
        val user = User(name, age, sic) //user is an object of User class which takes the parameters name and age passed in addUser
        db.collection("users")
            .add(user)
            .addOnSuccessListener { docRef ->
                Log.d(ControlsProviderService.TAG, "Document SNAPSHOT ADDED WITH ID: ${docRef.id}")
            }
            .addOnFailureListener { e ->
                Log.w(ControlsProviderService.TAG, "Error Adding Document: $e")

            }
    }
    private fun setUser(name: String, age: Int, sic: String) {
        val user = User(name, age, sic)
        db.collection("students")
            .document(sic)
            .set(user)

    }

    @RequiresApi(Build.VERSION_CODES.R)
    @Composable
    fun AddUserScreen() {
        var name by remember { mutableStateOf("") }
        var age by remember { mutableStateOf("") }
        var sic by remember { mutableStateOf("") }


        Column(
            modifier = Modifier.padding(16.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            TextField(
                value = name,
                onValueChange = { name = it },
                label = { Text(text = "Enter Name") },
                modifier = Modifier.fillMaxWidth()
            )
            Spacer(modifier = Modifier.height(8.dp))
            TextField(
                value = age,
                onValueChange = { age = it },
                label = { Text(text = "Enter Age") },
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                modifier = Modifier.fillMaxWidth()
            )
            Spacer(modifier = Modifier.height(8.dp))
            TextField(
                value = sic,
                onValueChange = { sic = it },
                label = { Text(text = "Enter SIC") },
                modifier = Modifier.fillMaxWidth()
            )
            Spacer(modifier = Modifier.height(8.dp))
            Button(onClick = { addUser(name, age.toInt(), sic) }) {
                Text(text = "Add User")
            }
            Spacer(modifier = Modifier.height(8.dp))
            Button(onClick = { setUser(name, age.toInt(), sic) }) {
                Text(text = "Set User")
            }
        }
    }

    private fun fetchStudents(onResult: (List<User>) -> Unit) {
        db.collection("users")
            .get()
            .addOnSuccessListener { result ->
                val userList = result.map { document -> document.toObject(User::class.java) }
                onResult(userList)
                Log.d(TAG, "Fetch Document: $onResult")
            }
            .addOnFailureListener { e ->
                Log.w(TAG, "Error getting Document: $e")
            }
    }

    private val storage = Firebase.storage
    private val storageRef = storage.reference
    private fun uploadImage(uri: Uri, context: android.content.Context) {
        val filename = "images/${UUID.randomUUID()}.jpg"
        val imageRef = storageRef.child(filename)

        imageRef.putFile(uri)
            .addOnSuccessListener {
                imageRef.downloadUrl.addOnSuccessListener { uri ->
                    Toast.makeText(context, "Image Uploaded Successfully: $uri", Toast.LENGTH_LONG)
                        .show()
                }
            }
            .addOnFailureListener { e ->
                Toast.makeText(context, "Image Uploaded Failed: ${e.message}", Toast.LENGTH_SHORT)
                    .show()
            }
    }

    @Composable
    fun ImageUploadScreen() {
        val context = LocalContext.current
        val imageUri = remember {
            mutableStateOf<Uri?>(null)
        }
        val launcher =
            rememberLauncherForActivityResult(ActivityResultContracts.GetContent()) { uri: Uri? ->
                imageUri.value = uri
                uri?.let { uploadImage(uri, context) }
            }

        Column(
            modifier = Modifier.padding(20.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Button(onClick = { launcher.launch("image/*") }) {
                Text(text = "Select Image")
            }
            Spacer(modifier = Modifier.height(20.dp))
            imageUri.value?.let { uri ->
                Image(
                    painter = rememberAsyncImagePainter(uri),
                    contentDescription = null,
                    modifier = Modifier.size(200.dp)
                )
            }
        }
    }
}

//data class
data class User(
    val name: String = "",
    val age: Int = 0,
    val sic: String = ""
)