package com.deimos.jetpackcomponentsexample

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.ClickableText
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
import androidx.compose.ui.text.AnnotatedString
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
                    var status by rememberSaveable { mutableStateOf(false) }
                    val checkInfo = CheckInfo(
                        title = "Example 1",
                        selected = status,
                        onCheckedChange = { status = it })

                    //MyCheckboxAdvanced(checkInfo = checkInfo)

                    val myOptions = getOptions(listOf("Example 1", "Example 2", "Example 3"))
                    /*
                    Column() {
                        myOptions.forEach { MyCheckboxAdvanced(checkInfo = it) }
                    } */

                    // Radio buttons
                    var radioSelected by rememberSaveable { mutableStateOf("Kotlin") }
                    //MyRadioButtons(name = radioSelected, onItemSelected = { radioSelected = it })

                    // Card
                    //MyCard()

                    // BadgeBox
                    //MyBadgeBox()

                    // Divider
                    /*
                    Column(modifier = Modifier.fillMaxSize()) {
                        MyDivider()
                    } */

                    //AdvancedSlider()
                    ShowDialog()
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    JetpackComponentsExampleTheme {
        //MyButtonsExample()
        //MyImage()
        //MyIcon()
        MyProgressBar()
    }
}

@Composable
fun ShowDialog() {
    var show by rememberSaveable { mutableStateOf(false) }
    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
        Button(onClick = { show = true }) {
            Text(text = "Show")
        }
    }
    //MyAlertDialog(show = show, onCancel = {show = false}, onConfirm = {show = false})
    //MySimpleCustomDialog(show = show, onCancel = { show = false })
    //MyCustomDialog(show = show, onDismiss = { show = false })
    MyConfirmationDialog(show = show, onDismiss = { show = false })
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

@Composable
fun MyProgressBar() {
    var showLoader by rememberSaveable {
        mutableStateOf(false)
    }

    Column(
        Modifier
            .fillMaxSize()
            .padding(24.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        if (showLoader) {
            CircularProgressIndicator(color = Color.Red, strokeWidth = 4.dp)
            LinearProgressIndicator(modifier = Modifier.padding(top = 25.dp))
        }
        Spacer(modifier = Modifier.padding(10.dp))
        Button(onClick = { showLoader = !showLoader }) {
            Text(text = "Show/hide")
        }
    }
}

@Composable
fun MySwitch() {
    var state by rememberSaveable { mutableStateOf(false) }

    Switch(
        checked = state, onCheckedChange = { state = !state }, colors = SwitchDefaults.colors(
            uncheckedThumbColor = Color.Red,
            checkedThumbColor = Color.Green,
            uncheckedTrackColor = Color.Magenta
        )
    )
}

@Composable
fun MyCheckBox() {
    var state by rememberSaveable { mutableStateOf(false) }

    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Row(verticalAlignment = Alignment.CenterVertically) {
            Checkbox(checked = state, onCheckedChange = { state = !state })
            ClickableText(
                text = AnnotatedString("My Checkbox"),
                onClick = { state = !state }
            )
        }
    }
}

@Composable
fun MyCheckboxAdvanced(checkInfo: CheckInfo) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Start
    ) {
        Checkbox(
            checked = checkInfo.selected,
            onCheckedChange = { checkInfo.onCheckedChange(!checkInfo.selected) })
        ClickableText(
            text = AnnotatedString(checkInfo.title),
            onClick = { checkInfo.onCheckedChange(!checkInfo.selected) })
    }
}

@Composable
fun getOptions(titles: List<String>): List<CheckInfo> {
    return titles.map {
        var status by rememberSaveable { mutableStateOf(false) }
        CheckInfo(
            title = it,
            selected = status,
            onCheckedChange = { status = it }
        )
    }
}

@Composable
fun MyRadioButtons(name: String, onItemSelected: (String) -> Unit) {
    Column(modifier = Modifier.fillMaxWidth()) {
        Row(verticalAlignment = Alignment.CenterVertically) {
            RadioButton(selected = name == "Kotlin", onClick = { onItemSelected("Kotlin") })
            Text(text = "Kotlin")
        }
        Row(verticalAlignment = Alignment.CenterVertically) {
            RadioButton(selected = name == "Java", onClick = { onItemSelected("Java") })
            Text(text = "Java")
        }
        Row(verticalAlignment = Alignment.CenterVertically) {
            RadioButton(selected = name == "C++", onClick = { onItemSelected("C++") })
            Text(text = "C++")
        }
    }
}

@Composable
fun MyCard() {
    Column {
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            elevation = 16.dp,
            //shape = MaterialTheme.shapes.small
            shape = RoundedCornerShape(16.dp)
        ) {
            Column(modifier = Modifier.padding(16.dp)) {
                Text(text = "Text 1")
                Text(text = "Text 2")
                Text(text = "Text 3")
            }
        }
    }
}

@Composable
fun MyBadgeBox() {
    Box(modifier = Modifier.padding(16.dp)) {
        BadgedBox(badge = {
            Badge { Text(text = "1") }
        }) {
            Icon(
                imageVector = Icons.Default.Star,
                contentDescription = "",
                modifier = Modifier.size(32.dp),
                tint = Color.Blue
            )
        }
    }
}

@Composable
fun MyDivider() {
    Divider(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 16.dp)
    )
}

@Composable
fun MyDropDownMenu() {
    var selectedText by rememberSaveable { mutableStateOf("") }
    var expanded by rememberSaveable { mutableStateOf(false) }
    val desserts = listOf("Ice cream", "Coffee", "Chocolate")

    Box(modifier = Modifier.fillMaxWidth()) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .background(color = Color.Cyan)
                .padding(16.dp)
        ) {
            OutlinedTextField(
                value = selectedText,
                onValueChange = { selectedText = it },
                enabled = false,
                readOnly = true,
                modifier = Modifier
                    .clickable { expanded = true }
                    .fillMaxWidth()
            )
            DropdownMenu(
                expanded = expanded,
                onDismissRequest = { expanded = false },
                modifier = Modifier.fillMaxWidth()
            ) {
                desserts.forEach { dessert ->
                    DropdownMenuItem(onClick = {
                        expanded = false
                        selectedText = dessert
                    }) {
                        Text(text = dessert)
                    }
                }
            }
        }
    }
}