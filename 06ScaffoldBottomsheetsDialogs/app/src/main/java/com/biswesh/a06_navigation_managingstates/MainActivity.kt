package com.biswesh.a06_navigation_managingstates

//import androidx.compose.material3.AlertDialogDefaults
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.text.BasicText
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Done
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TextField
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults.topAppBarColors
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.biswesh.a06_navigation_managingstates.ui.theme._06NavigationManagingStatesTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            _06NavigationManagingStatesTheme {
                ScaffoldExample()
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ScaffoldExample() {
    Scaffold(topBar = {
        TopAppBar(colors = topAppBarColors(
            containerColor = MaterialTheme.colorScheme.primary,
            titleContentColor = MaterialTheme.colorScheme.error
        ), title = { Text(text = "Top app bar") })
    }, bottomBar = {
        BottomAppBar(
            containerColor = MaterialTheme.colorScheme.primary,
            contentColor = MaterialTheme.colorScheme.error
        ) {
            Text(text = "MyBottomApp bar")
        }
    }, floatingActionButton = {
        FloatingActionButton(onClick = { }) {
            Icon(Icons.Filled.Add, contentDescription = "Add button")
        }
    }) { innerPadding ->
        val sheetState = rememberModalBottomSheetState()
        var showBottomSheet by remember { mutableStateOf(false) }
        var showAlert by remember { mutableStateOf(false) }

        Column(
            modifier = Modifier.padding(innerPadding),
            verticalArrangement = Arrangement.spacedBy(16.dp),
        ) {
            //Scaffold Text
            Text(
                modifier = Modifier.padding(8.dp),
                text = "This is my Scaffold area where I can put a lot of things simultaneously"
            )
            //Bottom Sheet
            Button(onClick = { showBottomSheet = true }) {
                Text(text = "Show Bottom Sheet")
            }
            if (showBottomSheet) {
                ModalBottomSheet(
                    onDismissRequest = { showBottomSheet = false },
                    sheetState = sheetState,
                    containerColor = MaterialTheme.colorScheme.error
                ) {
                    Column(
                        modifier = Modifier
                            .padding(10.dp)
                            .fillMaxSize()
                    ) {
                        Text(text = "Hi, my name is Biswesh")
                    }
                }
            }
            //Alert Dialog
            Button(onClick = { showAlert = true }) {
                Text(text = "Show Alert")
            }
            if (showAlert) {
                AlertDialogExample(onDismissRequest = { showAlert = false }, onConfirmation = {
                    showAlert = false
                    println("Confirmed")
                })
            }
            //Text Field
            Column(
                modifier = Modifier.padding(20.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {

                var text = ""
                TextField(value = text,
                    onValueChange = { text = it },
                    label = { Text("Username: ") })
                Text(text)
            }
            Row {
                //Lazy Column
                val itemsList = List(50) { "items : $it" }
                Text(text = "List")
                LazyColumn(
                    modifier = Modifier
                        .height(400.dp)
                        .width(100.dp)
                        .padding(5.dp)
                        .border(2.dp, Color.Black)
                ) {
                    items(itemsList) { item ->
                        BasicText(text = item, modifier = Modifier.padding(2.dp))
                    }
                }
                //Lazy Vertical Grid
                Text(text = "Grid")
                LazyVerticalGrid(
                    columns = GridCells.Adaptive(100.dp),
                    modifier = Modifier
                        .height(500.dp)
                        .padding(2.dp)
                        .border(2.dp, Color.Black)
                ) {
                    items(itemsList.size) { index ->
                        Box(
                            modifier = Modifier
                                .padding(4.dp)
                                .border(1.dp, Color.Gray)
                                .padding(8.dp)
                        ) {
                            BasicText(text = itemsList[index])
                        }
                    }
                }
            }
        }
    }
}


@Composable
fun AlertDialogExample(
    onDismissRequest: () -> Unit,
    onConfirmation: () -> Unit,
) {

    AlertDialog(icon = {
        Icon(Icons.Filled.Done, contentDescription = "Done")
    }, title = {
        Text(text = "Emergency")
    }, text = {
        Text(text = "Jetpack course is updating its library")
    }, onDismissRequest = {
        onDismissRequest()
    }, confirmButton = {
        TextButton(onClick = {
            onConfirmation()
        }) {
            Text(text = "Alright")
        }
    }, dismissButton = {
        TextButton(onClick = {
            onDismissRequest()
        }) {
            Text("Dismiss")
        }
    })
}

@Preview(showBackground = true)
@Composable
fun AlertDialogExamplePreview() {
    _06NavigationManagingStatesTheme {
//        AlertDialogExample()
    }
}

@Preview(showBackground = true)
@Composable
fun ScaffoldExamplePreview() {
    _06NavigationManagingStatesTheme {
        ScaffoldExample()
    }
}