package com.example.rhymefinder.screens

import android.widget.Toast
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import com.example.rhymefinder.logics.DeleteDialog
import com.example.rhymefinder.models.SavedRhymes
import com.orhanobut.hawk.Hawk
import com.ramcosta.composedestinations.annotation.Destination

@Destination
@Composable
fun SavedScreen(modifier: Modifier = Modifier) {
    var savedListState by remember { mutableStateOf<List<SavedRhymes>?>(null) }
    var showItemDialog by remember { mutableStateOf<SavedRhymes?>(null) }
    val context = LocalContext.current

    LaunchedEffect(key1 = Unit) {
        savedListState = Hawk.get("savedRhymes", emptyList())
    }
    Column {
        Spacer(Modifier.height(22.dp))
        LazyColumn {
            items(savedListState ?: emptyList()) {
                SavedScreenItem(modifier, it, onDeleteClick = {
                    showItemDialog = it
                })
                Spacer(Modifier.height(12.dp))
            }
        }
    }
    showItemDialog?.let { item ->
        DeleteDialog(onConfirmClick = {
            val updatedList: List<SavedRhymes>? =
                Hawk.get("savedRhymes", emptyList())
            val finalList = updatedList?.toMutableList()
            finalList?.remove(item)
            Hawk.put("savedRhymes", finalList)
            savedListState = Hawk.get("savedRhymes", emptyList())
            Toast.makeText(context, "${item.input} و ${item.output} حذف شدند.", Toast.LENGTH_SHORT)
                .show()
            showItemDialog = null
        }, onDismiss = { showItemDialog = null })
    }
}