package uz.foursquare.swipereveal

import android.widget.Toast
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material3.Card
import androidx.compose.material3.Snackbar
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun ContactScreen(modifier: Modifier = Modifier, showMessage: (String) -> Unit) {
    val contacts = remember {
        mutableStateListOf(
            *(1..100).map {
                ContactUi(id = it, name = "Contact $it", isOptionsRevealed = false)
            }.toTypedArray()
        )
    }

    LazyColumn(modifier = modifier.fillMaxSize()) {
        itemsIndexed(items = contacts, key = { _, contact -> contact.id }) { index, contact ->
            SwipeableItemWithActions(
                isRevealed = contact.isOptionsRevealed,
                actions = {
                    ActionIcon(
                        onClick = {
                            contacts[index] = contact.copy(isOptionsRevealed = false)
                            contacts.remove(contact)
                            showMessage("Contact ${contact.name} was archived!")
                        },
                        backgroundColor = Color.Gray,
                        icon = R.drawable.icon_archive_24,
                        tint = Color.White,
                        modifier = Modifier.fillMaxHeight()
                    )
                    ActionIcon(
                        onClick = {
                            contacts[index] = contact.copy(isOptionsRevealed = false)
                            showMessage("Contact ${contact.name} was edited!")
                        },
                        backgroundColor = Color.Yellow,
                        icon = R.drawable.icon_edit_24,
                        tint = Color.White,
                        modifier = Modifier.fillMaxHeight()
                    )
                }
            ) {
                Text(
                    contact.name,
                    modifier = Modifier.padding(16.dp),
                    maxLines = 1
                )
            }
        }
    }
}


@Preview
@Composable
fun ContactScreenPreview() {
    ContactScreen(){

    }
}