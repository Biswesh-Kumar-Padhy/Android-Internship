package com.biswesh.jetpackbasics

//import androidx.activity.enableEdgeToEdge
//import androidx.compose.foundation.layout.fillMaxSize
//import androidx.compose.foundation.layout.padding
//import androidx.compose.material3.Scaffold
//import androidx.compose.ui.Modifier
//import androidx.compose.ui.tooling.preview.Preview
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.basicMarquee
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.text.selection.SelectionContainer
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shadow
import androidx.compose.ui.text.ParagraphStyle
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.style.LineBreak
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.biswesh.jetpackbasics.ui.theme.JetPackBasicsTheme

class MainActivity : ComponentActivity() {

    //        override fun onStart() {
//        super.onStart()
//        println("LifeCycle On Create Started")
//    }
//
//    override fun onResume() {
//        super.onResume()
//        println("LifeCycle On Resume Started")
//    }
//
//    override fun onPause() {
//        super.onPause()
//        println("LifeCycle On Pause Started")
//    }
//
//    override fun onStop() {
//        super.onStop()
//        println("LifeCycle On Stop Started")
//    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            JetPackBasicsTheme {
                Column(
                    modifier = Modifier.padding(20.dp).fillMaxSize(),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center
                ) {
                    Text(
                        text = "Biswesh-Kumar-Padhy",
                        color = Color.Cyan,
                        fontSize = 30.sp,
                        fontStyle = FontStyle.Italic,
                        fontWeight = FontWeight.Bold
                    )
                    //Change text color
                    Text("Biswesh", color = Color.Blue)
                    //Change text size
                    Text("Biswesh", fontSize = 30.sp)
                    //Make text italic
                    Text("Hello World", fontStyle = FontStyle.Italic)
                    //Make text bold
                    Text("Hello World", fontWeight = FontWeight.Bold)
                    //Add shadow
                    val offset = Offset(5.0f, 10.0f)
                    Text(
                        text = "Hello world!", style = TextStyle(
                            fontSize = 24.sp, shadow = Shadow(
                                color = Color.Blue, offset = offset, blurRadius = 3f
                            )
                        )
                    )
                    //Add multiple styles in text
                    Text(buildAnnotatedString {
                        withStyle(style = SpanStyle(color = Color.Blue)) {
                            append("H")
                        }
                        append("ello ")

                        withStyle(
                            style = SpanStyle(
                                fontWeight = FontWeight.Bold, color = Color.Red
                            )
                        ) {
                            append("W")
                        }
                        append("orld")
                    })
                    //Use a brush for text styling
                    TextStyledBrushSnippet("The Text composable has multiple optional parameters to style its content. Below, we’ve listed parameters that cover the most ")
                    //Apply a brush to a span of text
                    val rainbowColors: List<Color> = listOf(Color.Red, Color.Blue)
                    Text(text = buildAnnotatedString {
                        append("Do not allow people to dim your shine\n")
                        withStyle(
                            SpanStyle(
                                brush = Brush.linearGradient(
                                    colors = rainbowColors
                                )
                            )
                        ) {
                            append("because they are blinded.")
                        }
                    })
                    //Opacity in a span of text
                    val brush = Brush.linearGradient(colors = rainbowColors)
                    Text(text = buildAnnotatedString {
                        withStyle(
                            SpanStyle(
                                brush = brush, alpha = .5f
                            )
                        ) {
                            append("Text in ")
                        }
                        withStyle(
                            SpanStyle(
                                brush = brush, alpha = 1f
                            )
                        ) {
                            append("Compose ❤️")
                        }
                    })
                    //Apply marquee effect to text
                    BasicMarqueeSample()
                    //Selectable Text
                    SelectionContainer {
                        Text("This text is selectable")
                    }
                    //Simple Outlined Text Field Sample
                    var text by remember { mutableStateOf("") }
                    OutlinedTextField(value = text,
                        onValueChange = { text = it },
                        label = { Text("Label") })
                    //Styled Text Field
                    var value by remember { mutableStateOf("Hello\nWorld\nInvisible") }
                    TextField(value = value,
                        onValueChange = { value = it },
                        label = { Text("Enter text") },
                        maxLines = 2,
                        textStyle = TextStyle(color = Color.Blue, fontWeight = FontWeight.Bold),
                        modifier = Modifier.padding(20.dp)
                    )
                    //Password Text Field
                    var password by rememberSaveable { mutableStateOf("") }
                    TextField(value = password,
                        onValueChange = { password = it },
                        label = { Text("Enter password") },
                        visualTransformation = PasswordVisualTransformation(),
                        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password)
                    )
                    //No Leading Zeroes
                    var input by rememberSaveable { mutableStateOf("") }
                    TextField(value = input, onValueChange = { newText ->
                        input = newText.trimStart { it == '0' }
                    })
                    //multiple styles in a paragraph
                    Text(buildAnnotatedString {
                        withStyle(style = ParagraphStyle(lineHeight = 30.sp)) {
                            withStyle(style = SpanStyle(color = Color.Blue)) {
                                append("Biswesh\n")
                            }
                            withStyle(
                                style = SpanStyle(
                                    fontWeight = FontWeight.Bold, color = Color.Red
                                )
                            ) {
                                append("Kumar\n")
                            }
                            append("Padhy")
                        }
                    })
                    //Linebreak
                    val customTitleLineBreak = LineBreak(
                        strategy = LineBreak.Strategy.HighQuality,
                        strictness = LineBreak.Strictness.Strict,
                        wordBreak = LineBreak.WordBreak.Phrase
                    )
                    Text(
                        text = "あなたに寄り添う最先端のテクノロジ This is not Readable :)",
                        modifier = Modifier.width(250.dp),
                        fontSize = 14.sp,
                        style = TextStyle.Default.copy(
                            lineBreak = customTitleLineBreak
                        )
                    )

                }

            }
        }
    }
}


@OptIn(ExperimentalFoundationApi::class)
@Composable
fun BasicMarqueeSample() {
    // Marquee only animates when the content doesn't fit in the max width.
    Column(Modifier.width(400.dp)) {
        Text(
            "Learn about why it's great to use Jetpack Compose",
            modifier = Modifier.basicMarquee(),
            fontSize = 30.sp
        )
    }
}

@Composable
fun TextStyledBrushSnippet(text: String) {
    val lightBlue = Color(0xFF0066FF)
    val purple = Color(0xFF800080)
    val gradientColors = listOf(Color.Cyan, lightBlue, purple /*...*/)

    Text(
        text = text, style = TextStyle(
            brush = Brush.linearGradient(
                colors = gradientColors
            )
        )
    )
}