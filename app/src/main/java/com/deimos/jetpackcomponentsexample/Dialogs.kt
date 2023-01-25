package com.deimos.jetpackcomponentsexample

import androidx.annotation.DrawableRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.setValue
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties

@Composable
fun MyConfirmationDialog(show: Boolean, onDismiss: () -> Unit) {
    if (show) {
        Dialog(onDismissRequest = { onDismiss() }) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(MaterialTheme.colors.background)
            ) {
                MyTitle(text = "Phone ringtone")
                Divider(modifier = Modifier.fillMaxWidth(), color = Color.LightGray)
                var status by rememberSaveable { mutableStateOf("") }
                MyRadioButtons(name = status, onItemSelected = { status = it })
                Divider(modifier = Modifier.fillMaxWidth(), color = Color.LightGray)
                Row(modifier = Modifier.align(Alignment.End)) {
                    TextButton(onClick =  {}) {
                        Text(text = "Cancel")
                    }
                    TextButton(onClick =  {}) {
                        Text(text = "Ok")
                    }
                }
            }
        }
    }
}

@Composable
fun MyCustomDialog(show: Boolean, onDismiss: () -> Unit) {
    if (show) {
        Dialog(onDismissRequest = { onDismiss() }) {
            Column(
                modifier = Modifier
                    .background(MaterialTheme.colors.background)
                    .padding(24.dp)
                    .fillMaxWidth()
            ) {
                MyTitle(text = "Add backup account")
                AccountItem(email = "example@gmail.com", drawable = R.drawable.ic_avatar)
                AccountItem(email = "example@gmail.com", drawable = R.drawable.ic_avatar)
                AccountItem(email = "Add account", drawable = R.drawable.ic_add)
            }
        }
    }
}

@Composable
fun MyTitle(text: String = "Title") {
    Text(
        text = text,
        fontWeight = FontWeight.SemiBold,
        fontSize = 20.sp,
        modifier = Modifier.padding(12.dp)
    )
}

@Composable
fun AccountItem(email: String, @DrawableRes drawable: Int) {
    Row(verticalAlignment = Alignment.CenterVertically) {
        Image(
            painter = painterResource(id = drawable),
            contentDescription = "avatar",
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .padding(8.dp)
                .size(40.dp)
                .clip(CircleShape)
        )
        Text(text = email, fontSize = 14.sp, color = Color.Gray, modifier = Modifier.padding(8.dp))
    }
}

@Composable
fun MySimpleCustomDialog(show: Boolean, onCancel: () -> Unit) {
    if (show) {
        Dialog(
            onDismissRequest = { onCancel() },
            properties = DialogProperties(dismissOnBackPress = false, dismissOnClickOutside = false)
        ) {
            // Here goes the view
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(MaterialTheme.colors.background)
                    .padding(24.dp)
            ) {
                Text(text = "Dialog")
            }
        }
    }
}

@Composable
fun MyAlertDialog(show: Boolean, onConfirm: () -> Unit, onCancel: () -> Unit) {
    if (show) {
        AlertDialog(
            onDismissRequest = { onCancel() },
            title = { Text(text = "Title") },
            text = { Text(text = "This is the description") },
            confirmButton = {
                TextButton(onClick = { onConfirm() }) {
                    Text(text = "Confirm")
                }
            },
            dismissButton = {
                TextButton(onClick = { onCancel() }) {
                    Text(text = "Cancel")
                }
            }
        )
    }
}