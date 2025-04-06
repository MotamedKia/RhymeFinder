package com.example.rhymefinder.screens

import android.widget.Toast
import androidx.compose.animation.Crossfade
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material.icons.filled.Refresh
import androidx.compose.material3.Card
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.rhymefinder.R
import com.example.rhymefinder.logics.RandomIndex
import com.example.rhymefinder.logics.getRhyme
import com.example.rhymefinder.models.DataItem
import com.example.rhymefinder.models.RhymeFind
import com.example.rhymefinder.models.poemList
import com.example.rhymefinder.models.poems
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator
import kotlinx.coroutines.launch
import kotlin.math.tanh

@Destination(start = true)
@Composable
fun HomeScreen(modifier: Modifier = Modifier) {
    val context = LocalContext.current
    var rhymeState by remember { mutableStateOf<RhymeFind?>(null) }
    var rand by remember { mutableStateOf(RandomIndex(poemList)) }
    val poems by remember { mutableStateOf<List<poems>>(poemList) }
    var rhymeQuery by remember { mutableStateOf(poems[rand].rhyme) }
    var available by remember { mutableStateOf(true) }

    val coroutineScope = rememberCoroutineScope()

    LaunchedEffect(key1 = Unit) {
        if (available) {
            try {
                rhymeState = getRhyme(rhymeQuery)
                available = true
            } catch (e: Exception) {
                Toast.makeText(context, "این کلمه پیدا نشد!", Toast.LENGTH_SHORT).show()
                available = false
            }
        }
    }

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(end = 20.dp, top = 15.dp),
        horizontalArrangement = Arrangement.End, verticalAlignment = Alignment.Top
    ) {
        IconButton(onClick = {
            rand = RandomIndex(poemList)
            rhymeQuery = poems[rand].rhyme
            coroutineScope.launch {
                try {
                    rhymeState = getRhyme(rhymeQuery)
                    available = true
                } catch (e: Exception) {
                    Toast.makeText(context, "این کلمه پیدا نشد!", Toast.LENGTH_SHORT).show()
                    available = false
                }
            }
        }) {
            Icon(
                Icons.Default.Refresh,
                "",
                Modifier.size(30.dp, 30.dp),
                tint = MaterialTheme.colorScheme.primary
            )
        }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 15.dp)
            .statusBarsPadding(),
        verticalArrangement = Arrangement.Bottom
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 20.dp),
            horizontalArrangement = Arrangement.End
        ) {
            Crossfade(poems) {
                Text(
                    text = it[rand].poem,
                    modifier = modifier.padding(vertical = 15.dp),
                    textAlign = TextAlign.End,
                    fontFamily = FontFamily(Font(R.font.sher_font)),
                    fontSize = 26.sp,
                    color = MaterialTheme.colorScheme.primary
                )
            }
        }
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(end = 8.dp, start = 15.dp,/* top = 15.dp,*/ bottom = 70.dp),
            horizontalArrangement = Arrangement.Start
        ) {
            Crossfade(poems) {
                Text(
                    text = it[rand].poet,
                    modifier = modifier,
                    textAlign = TextAlign.Start,
                    fontFamily = FontFamily(Font(R.font.sher_font)),
                    fontSize = 22.sp,
                    color = MaterialTheme.colorScheme.secondary
                )
            }
        }
        Column(
            verticalArrangement = Arrangement.Bottom,
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 24.dp)
                .size(width = 100.dp, height = 200.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Card(modifier = Modifier.fillMaxSize(), shape = RoundedCornerShape(25.dp)) {
                Column(
                    horizontalAlignment = Alignment.End, modifier = Modifier
                        .fillMaxWidth()
                        .padding(end = 24.dp, top = 14.dp, start = 24.dp)
                ) {
                    Crossfade(poems) { Text(it[rand].rhyme, fontSize = 18.sp) }
                    HorizontalDivider(
                        Modifier.padding(horizontal = 8.dp, vertical = 10.dp),
                        color = MaterialTheme.colorScheme.onSecondary
                    )
                    if (rhymeState != null) {
                        LazyColumn(
                            modifier = Modifier.fillMaxSize(),
                            verticalArrangement = Arrangement.Top,
                            horizontalAlignment = Alignment.End
                        ) {
                            items(rhymeState!!.data_items) {
                                if (available) {
                                    Text(
                                        it.word,
                                        textAlign = TextAlign.End
                                    )
                                }
                            }
                        }
                    } else {
                        Column(
                            modifier = Modifier.fillMaxSize(),
                            verticalArrangement = Arrangement.Center,
                            horizontalAlignment = Alignment.CenterHorizontally
                        ) { CircularProgressIndicator() }
                    }
                }
            }
        }
    }
}