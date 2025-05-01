package com.example.rhymefinder.logics

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import com.example.rhymefinder.R
import com.example.rhymefinder.models.SavedRhymes
import com.orhanobut.hawk.Hawk

//when the item in Saved screen is clicked, this Dialog is called
/*the clickables are handled inn Saved screen*/
@Composable
fun DeleteDialog(modifier: Modifier = Modifier, onConfirmClick: () -> Unit, onDismiss: () -> Unit) {

    Dialog(onDismissRequest = { onDismiss() }) {
        Card(shape = RoundedCornerShape(50.dp)) {
            Column(
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier.padding(horizontal = 48.dp, vertical = 32.dp)
            ) {
                Text(
                    "آیا میخواهید این مورد را حذف کنید؟",
                    fontFamily = FontFamily(Font(R.font.vazirmatn_medium))
                )
                Spacer(Modifier.height(42.dp))
                Row {
                    Button(
                        onClick = {
                            onConfirmClick()
                        }, modifier = Modifier.weight(1f),
                        colors = ButtonDefaults.buttonColors(containerColor = MaterialTheme.colorScheme.error)
                    ) {
                        Text(
                            "بله", color = MaterialTheme.colorScheme.onError,
                            fontFamily = FontFamily(Font(R.font.sher_font)), fontSize = 25.sp
                        )
                    }
                    Spacer(Modifier.weight(1f))
                    Button(
                        onClick = { onDismiss() },
                        modifier = Modifier.weight(1f)
                    ) {
                        Text(
                            "خیر",
                            fontFamily = FontFamily(Font(R.font.sher_font)), fontSize = 25.sp
                        )
                    }
                }
            }
        }
    }
}