package com.example.rhymefinder.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowLeft
import androidx.compose.material.icons.filled.KeyboardArrowRight
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedCard
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.example.rhymefinder.models.SavedRhymes

//The item template in Saved screen
@Composable
fun SavedScreenItem(
    modifier: Modifier = Modifier,
    list: SavedRhymes,
    onDeleteClick: () -> Unit
) {
    val context = LocalContext.current
    OutlinedCard(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 20.dp)
            .size(100.dp, 90.dp),
        shape = RoundedCornerShape(100.dp), onClick = {
            onDeleteClick()
        }
    ) {
        Row(
            modifier = Modifier.fillMaxSize(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center
        ) {
            Spacer(Modifier.weight(1f))
            Text(modifier = Modifier.weight(1f), text = list.output)
            Icon(Icons.Default.KeyboardArrowLeft, "", modifier = Modifier.weight(1f))
            Spacer(Modifier.weight(1f))
            Icon(Icons.Default.KeyboardArrowRight, "", modifier = Modifier.weight(1f))
            Text(modifier = Modifier.weight(1f), text = list.input, textAlign = TextAlign.End)
            Spacer(Modifier.weight(1f))
        }
    }
}