package arete.arete.volleyballstatkeeper

import android.content.res.Resources
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import arete.arete.volleyballstatkeeper.ui.theme.VolleyballStatKeeperTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            VolleyballStatKeeperTheme() {
                VolleyStatKeeperApp()
            }
        }
    }
}

@Composable
fun VolleyStatKeeperApp() {
    Scaffold(
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight(),
        topBar = {
            TopAppBar(title = {
                Text(text = "Stat Keeper", textAlign = TextAlign.End)
            },
                navigationIcon = {
                    Icon(
                        painter = painterResource(id = R.drawable.ic_sports_volleyball_24px),
                        contentDescription = "Volleyball Icon"
                    )
                }
            )
        },
        content = {
            //TODO add content
        }
    )
}

@Preview
@Composable
fun PointScreenBody() {
    Surface(
        color = MaterialTheme.colors.background,
        modifier = Modifier.padding(
            vertical = 4.dp,
            horizontal = 8.dp
        )
    ) {
        Column(modifier = Modifier.padding(horizontal = 8.dp)) {
            Spacer(modifier = Modifier.size(8.dp))
            Row(
                modifier = Modifier,
                horizontalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                PlayerButton({}, "player guy")
                PlayerButton({}, "player guy")
                PlayerButton({}, "player guy")
            }
            Spacer(modifier = Modifier.size(8.dp))
            Row(
                modifier = Modifier,
                horizontalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                PlayerButton({}, "player guy")
                PlayerButton({}, "player guy")
                PlayerButton({}, "player guy")
            }
            Spacer(modifier = Modifier.size(8.dp))
        }
    }
}

@Composable
fun PlayerButton(
    onClick: () -> Unit,
    text: String
) {
    Button(
        onClick = onClick,
        shape = RoundedCornerShape(35),
        colors = ButtonDefaults.buttonColors(
            backgroundColor = Color.DarkGray,
            contentColor = Color.White
        )
    ) {
        Text(text = text)
    }
}
