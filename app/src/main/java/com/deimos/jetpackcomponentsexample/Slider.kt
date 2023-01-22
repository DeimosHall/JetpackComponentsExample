package com.deimos.jetpackcomponentsexample

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material.RangeSlider
import androidx.compose.material.Slider
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun BasicSlider() {
    Column(modifier = Modifier.padding(20.dp)) {
        var sliderPosition by rememberSaveable { mutableStateOf(0f) }
        Slider(value = sliderPosition, onValueChange = { sliderPosition = it })
        Text(text = sliderPosition.toString())
    }
}

@Composable
fun AdvancedSlider() {
    Column(modifier = Modifier.padding(20.dp)) {
        var sliderPosition by rememberSaveable { mutableStateOf(0f) }
        var completedValue by rememberSaveable { mutableStateOf("") }
        Slider(
            value = sliderPosition,
            onValueChange = { sliderPosition = it },
            onValueChangeFinished = { completedValue = sliderPosition.toString() },
            valueRange = 8f..20f,
            steps = 11
        )
        Text(text = completedValue)
    }
}