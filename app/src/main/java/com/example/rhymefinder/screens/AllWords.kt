package com.example.rhymefinder.screens

import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.example.rhymefinder.logics.getRhyme
import com.example.rhymefinder.models.RhymeFind
import com.ramcosta.composedestinations.annotation.Destination
import kotlinx.coroutines.launch

//this is Search screen, it's too late to rename it!
@Destination
@Composable
fun AllWords(modifier: Modifier = Modifier) {
    val context = LocalContext.current
    var rhymeState by remember { mutableStateOf<RhymeFind?>(null) }
    var rhymeQuery by remember { mutableStateOf("") }
    var available by remember { mutableStateOf(true) }

    val coroutineScope = rememberCoroutineScope()

    LaunchedEffect(key1 = Unit) {
        if (available) {
            try {
                rhymeState = getRhyme(rhymeQuery)
                available = true
            } catch (e: Exception) {
                available = false
            }
        }
    }

    Column(
        Modifier
            .fillMaxSize(),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Spacer(Modifier.height(12.dp))
        //the search bar
            /*there were other default searchBars that I have personally used in my other projects,
            but honestly, none of them seemed practical,
            because they all used a pre-instructed list,
            but in here we have the list after the action of searching!*/
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 20.dp)
                .size(100.dp, 60.dp),
            shape = RoundedCornerShape(100.dp),
            colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.primary)
        ) {
            Row(
                Modifier.fillMaxSize(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center
            ) {
                TextField(
                    colors = TextFieldDefaults.colors(
                        unfocusedContainerColor = MaterialTheme.colorScheme.primary,
                        focusedContainerColor = MaterialTheme.colorScheme.primary,
                        focusedTextColor = Color.Black,
                        focusedLabelColor = MaterialTheme.colorScheme.onPrimary,
                        unfocusedLabelColor = MaterialTheme.colorScheme.onPrimary
                    ),
                    modifier = Modifier.weight(5f),
                    value = rhymeQuery,
                    onValueChange = { rhymeQuery = it },
                    label = {
                        Text(
                            "جستجو",
                            textAlign = TextAlign.End,
                            modifier = Modifier.fillMaxWidth()
                        )
                    }, textStyle = TextStyle(textAlign = TextAlign.End), singleLine = true
                )
                Spacer(Modifier.weight(0.25f))
                IconButton(onClick = {
                    coroutineScope.launch {
                        try {
                            rhymeState = getRhyme(rhymeQuery)
                            available = true
                        } catch (e: Exception) {
                            Toast.makeText(context, "این کلمه پیدا نشد!", Toast.LENGTH_SHORT).show()
                            available = false
                        }
                    }
                }, modifier = Modifier.weight(1f)) {
                    Icon(
                        Icons.Default.Search,
                        "",
                        tint = MaterialTheme.colorScheme.onPrimary,
                    )
                }
                Spacer(Modifier.weight(0.25f))
            }
        }
        Spacer(Modifier.height(30.dp))
        if (rhymeState != null) {
            LazyColumn(
                modifier = Modifier.fillMaxSize(),
                verticalArrangement = Arrangement.Top,
                horizontalAlignment = Alignment.End
            ) {
                items(rhymeState!!.data_items) {
                    if (available) {
                        QueryItem(query = rhymeQuery, rhyme = it.word)
                        HorizontalDivider(Modifier.padding(horizontal = 30.dp, vertical = 15.dp))
                    }
                }
            }
        }
        Spacer(Modifier.height(12.dp))
    }
}