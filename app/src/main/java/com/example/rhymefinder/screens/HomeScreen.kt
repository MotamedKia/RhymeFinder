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
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.rhymefinder.R
import com.example.rhymefinder.logics.RandomIndex
import com.example.rhymefinder.logics.getRhyme
import com.example.rhymefinder.models.RhymeFind
import com.example.rhymefinder.models.poemList
import com.example.rhymefinder.models.poems
import com.example.rhymefinder.screens.destinations.AllWordsDestination
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator
import kotlinx.coroutines.launch

@Destination(start = true)
@Composable
fun HomeScreen(modifier: Modifier = Modifier, navigator: DestinationsNavigator) {
    val context = LocalContext.current
    var rhymeState by remember { mutableStateOf<RhymeFind?>(null) }
    var rand by remember { mutableStateOf(RandomIndex(poemList)) } //for the random poem
    val poem by remember { mutableStateOf<List<poems>>(poemList) } //for the random poem
    var rhymeQuery by remember { mutableStateOf(poem[rand].rhyme) } //for the preview below the Home screen
    var available by remember { mutableStateOf(true) } //to see if api is successful or not
    var errorProof by remember { mutableStateOf(true) } //to see if api is successful or not

    val coroutineScope = rememberCoroutineScope()

    LaunchedEffect(key1 = Unit) {
        if (available) {
            try {
                rhymeState = getRhyme(rhymeQuery)
                available = true
            } catch (e: Exception) {
                available = false
                errorProof = false
            }
        }
    }

    //to see if there is a proper connection
    if (errorProof) {

        //the random-giving-poem button
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 20.dp, top = 15.dp), verticalAlignment = Alignment.Top
        ) {
            IconButton(onClick = {
                rand = RandomIndex(poemList)
                rhymeQuery = poem[rand].rhyme
                coroutineScope.launch {
                    try {
                        rhymeState = getRhyme(rhymeQuery)
                        available = true
                    } catch (e: Exception) {
                        Toast.makeText(context, "این کلمه پیدا نشد!", Toast.LENGTH_SHORT).show()
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

        //the rest of Home screen
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
                    .padding(horizontal = 20.dp)
            ) {
                //to give a smoother animation to the changing poem
                Crossfade(poem) {
                    Text(
                        text = it[rand].poem,
                        modifier = modifier.padding(vertical = 15.dp),
                        fontFamily = FontFamily(Font(R.font.sher_font)),
                        fontSize = 26.sp,
                        color = MaterialTheme.colorScheme.primary
                    )
                }
            }
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(end = 15.dp, start = 8.dp,/* top = 15.dp,*/ bottom = 70.dp),
                horizontalArrangement = Arrangement.End
            ) {
                //to give a smoother animation to the changing rhyme
                Crossfade(poem) {
                    Text(
                        text = it[rand].poet,
                        modifier = modifier.padding(vertical = 15.dp),
                        textAlign = TextAlign.End,
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
                Card(
                    modifier = Modifier.fillMaxSize(),
                    shape = RoundedCornerShape(25.dp),
                    onClick = {
                        navigator.navigate(AllWordsDestination(query = poem[rand].rhyme).route)
                        Toast.makeText(
                            context,
                            "جستجوی هم\u200Cقافیه های کلمه\u200Cی \"${poem[rand].rhyme}\"",
                            Toast.LENGTH_SHORT
                        ).show()
                    }) {
                    Column(
                        horizontalAlignment = Alignment.Start, modifier = Modifier
                            .fillMaxWidth()
                            .padding(end = 24.dp, top = 14.dp, start = 24.dp)
                    ) {
                        //to give a smoother animation to the changing list
                        Crossfade(poem) { Text(it[rand].rhyme, fontSize = 18.sp) }
                        HorizontalDivider(
                            Modifier.padding(horizontal = 8.dp, vertical = 10.dp),
                            color = MaterialTheme.colorScheme.onSecondary
                        )
                        if (rhymeState != null) {
                            LazyColumn(
                                modifier = Modifier.fillMaxSize(),
                                verticalArrangement = Arrangement.Top
                            ) {
                                items(rhymeState!!.data_items) {
                                    if (available) {
                                        Text(
                                            it.word,
                                            textAlign = TextAlign.Start
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

    } else {
        //if there is no internet connection
        Column(
            Modifier
                .fillMaxSize()
                .padding(30.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Icon(
                painter = painterResource(R.drawable.baseline_signal_wifi_statusbar_connected_no_internet_4_24),
                "no internet connection",
                modifier = Modifier.size(200.dp),
                tint = MaterialTheme.colorScheme.onBackground
            )
            Text(
                "مشکل در اتصال به اینترنت",
                textAlign = TextAlign.Center,
                fontFamily = FontFamily(Font(R.font.onvan_font)),
                fontSize = 35.sp,
                modifier = Modifier.padding(top = 12.dp),
                color = MaterialTheme.colorScheme.onBackground
            )
        }
    }
}