package com.biswesh.a09_mvvm_architecture

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import com.biswesh.a09_mvvm_architecture.ui.theme._09MVVMArchitectureTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            _09MVVMArchitectureTheme {
                CounterView()
//                val myViewModel: CounterViewModel = viewModel()
                val intContent = GenericClass(120)
                val stringContent = GenericClass("Biswesh")
                fun <T> printSomething(content: T){
                    println(content)
                }
            }
        }
    }
}

@Composable
fun CounterView(counterVM: CounterViewModel = viewModel()){
    val counterState = counterVM.counter.value
    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(text = "Current Counter Value ${counterState.count}")

        Row {
            Button(onClick = { counterVM.incrementCounter() }) {
                Text(text = "Increment Value")
            }
            Spacer(modifier = Modifier.width(20.dp))
            Button(onClick = { counterVM.decrementCounter() }) {
                Text(text = "Decrement Value")
            }
        }
        Spacer(modifier = Modifier.width(20.dp))
        Button(onClick = { counterVM.resetCounter() }) {
            Text(text = "Reset Value")
        }
    }

}

//Model Class
data class Counter(val count: Int)
//data class User(val username: String, val password: String)

//ViewModel Class
class CounterViewModel: ViewModel() {
    private val _counter = mutableStateOf(Counter(0))
    val counter: State<Counter> = _counter

    fun incrementCounter() {
        _counter.value = Counter(_counter.value.count + 1)
    }
    fun decrementCounter() {
        _counter.value = Counter(_counter.value.count - 1)
    }
    fun resetCounter(){
        _counter.value = Counter(0)
    }
}

class GenericClass<T>(var content: T)

