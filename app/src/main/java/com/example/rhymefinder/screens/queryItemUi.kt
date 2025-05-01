package com.example.rhymefinder.screens

import android.widget.Toast
import androidx.compose.animation.Crossfade
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Check
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.example.rhymefinder.models.RhymeFind
import com.example.rhymefinder.models.SavedRhymes
import com.orhanobut.hawk.Hawk

//The item template in Search screen
@Composable
fun QueryItem(modifier: Modifier = Modifier, query: String, rhyme: String) {
    val context = LocalContext.current
    var done by remember { mutableStateOf(false) }
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 20.dp)
            .size(100.dp, 60.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Center
    ) {
        Text(rhyme, modifier = Modifier.weight(1f))
        Spacer(Modifier.weight(2f))
        //Using Crossfade for a smoother animation in the buttons
        Crossfade(done) {
            Button(modifier = Modifier
                .size(60.dp, 60.dp),
                colors = if (it) ButtonDefaults.buttonColors(containerColor = MaterialTheme.colorScheme.error) else ButtonDefaults.buttonColors(
                    containerColor = MaterialTheme.colorScheme.primaryContainer
                ),
                onClick = {
                    if (!done) {
                        val dbList: List<SavedRhymes>? = Hawk.get("savedRhymes", emptyList())
                        val finalList = dbList?.toMutableList()
                        finalList?.add(SavedRhymes(query, rhyme))
                        Hawk.put("savedRhymes", finalList)
                        done = true
                        Toast.makeText(
                            context,
                            "کلمه ی $query هم قافیه ی $rhyme ذخیره شد.",
                            Toast.LENGTH_SHORT
                        ).show()
                    }
                }) {
                Icon(
                    if (it) Icons.Default.Check else Icons.Default.Add,
                    "",
                    tint = if (it) MaterialTheme.colorScheme.onError
                    else MaterialTheme.colorScheme.onPrimaryContainer,
                    modifier = Modifier.size(50.dp, 50.dp)
                )
            }
        }
    }
}