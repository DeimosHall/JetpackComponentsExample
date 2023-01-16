package com.deimos.jetpackcomponentsexample

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Star
import androidx.compose.runtime.Composable
import androidx.compose.runtime.setValue
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.deimos.jetpackcomponentsexample.ui.theme.JetpackComponentsExampleTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            JetpackComponentsExampleTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(), color = MaterialTheme.colors.background
                ) {
                    MyButtonsExample()
                }
            }
        }
    }
}

@Composable
fun MyText() {
    Column(
        Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(text = "Hello", fontSize = 25.sp)
        Text(text = "Text in blue", color = Color.Blue, fontSize = 25.sp)
        Text(text = "Bold text", fontWeight = FontWeight.Bold, fontSize = 25.sp)
        Text(text = "Cursive text", fontFamily = FontFamily.Cursive, fontSize = 25.sp)
        Text(text = "Text decoration", textDecoration = TextDecoration.Underline, fontSize = 25.sp)
        MyTextField()
        MyTextFieldAdvanced()
        MyTextFieldOutlined()
    }
}

@Composable
fun MyTextFieldOutlined() {
    var myTextField by rememberSaveable { mutableStateOf("") }
    OutlinedTextField(
        value = myTextField,
        onValueChange = { myTextField = it.toUpperCase() },
        label = { Text(text = "This is the hint") },
        colors = TextFieldDefaults.outlinedTextFieldColors(
            focusedBorderColor = Color.Cyan, unfocusedBorderColor = Color.Cyan
        )
    )
}

@Composable
fun MyButtonsExample() {
    var enable by rememberSaveable {
        mutableStateOf(true)
    }
    Column(
        Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Button(
            onClick = { enable = false }, enabled = enable, colors = ButtonDefaults.buttonColors(
                backgroundColor = Color.Red, contentColor = Color.LightGray
            ), border = BorderStroke(5.dp, Color.Black)
        ) {
            Text(text = "Button", fontSize = 30.sp)
        }
        Spacer(modifier = Modifier.height(20.dp))
        OutlinedButton(
            onClick = { enable = false }, enabled = enable, colors = ButtonDefaults.buttonColors(
                backgroundColor = Color.Transparent,
                contentColor = Color.Black,
                disabledBackgroundColor = Color.Cyan,
                disabledContentColor = Color.DarkGray
            )
        ) {
            Text(text = "Button", fontSize = 30.sp)
        }
        TextButton(onClick = { enable = true }) {
            Box(
                modifier = Modifier
                    .border(1.dp, color = Color.DarkGray, shape = CircleShape)
                    .padding(10.dp)
            ) {
                Text(text = "Enable buttons", fontSize = 30.sp)
            }
        }
    }
}

@Composable
fun MyTextFieldAdvanced() {
    var myTextField by rememberSaveable { mutableStateOf("") }
    TextField(value = myTextField,
        onValueChange = { myTextField = it.toUpperCase() },
        label = { Text(text = "This is the hint") })
}

@Composable
fun MyTextField() {
    var myTextField by rememberSaveable { mutableStateOf("") }
    TextField(value = myTextField, onValueChange = { myTextField = it })
}

@Composable
fun MyImage() {
    Image(
        painter = painterResource(id = R.drawable.ic_launcher_background),
        contentDescription = "Example",
        modifier = Modifier
            .clip(CircleShape)
            .border(5.dp, Color.Red, CircleShape)
    )
}

@Composable
fun MyIcon() {
    Icon(imageVector = Icons.Default.Star, contentDescription = "Example", tint = Color.Cyan)
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    JetpackComponentsExampleTheme {
        //MyButtonsExample()
        //MyImage()
        MyIcon()
    }
}