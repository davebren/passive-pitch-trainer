package org.eski.pitch.ui.game

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.Settings
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import org.eski.ui.util.grid6


@Composable
fun InfoDialog(
    onDismiss: () -> Unit,
    onOpenSettings: () -> Unit
) {
    Dialog(
        onDismissRequest = onDismiss,
        properties = DialogProperties(
            dismissOnBackPress = true,
            dismissOnClickOutside = true,
            usePlatformDefaultWidth = false
        )
    ) {
        Card(
            modifier = Modifier
                .fillMaxWidth(0.85f)
                .fillMaxHeight(0.85f),
            shape = RoundedCornerShape(16.dp),
            backgroundColor = Color(0xFF333333),
            elevation = 8.dp
        ) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(16.dp)
            ) {
                // Header with title and close button
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = "Welcome to PassivePitch!",
                        color = Color.White,
                        fontSize = 22.sp,
                        fontWeight = FontWeight.Bold,
                        modifier = Modifier.weight(1f)
                    )
                    
                    IconButton(onClick = onDismiss) {
                        Icon(
                            imageVector = Icons.Default.Close,
                            contentDescription = "Close",
                            tint = Color.White
                        )
                    }
                }
                
                Divider(
                    color = Color.Gray.copy(alpha = 0.5f),
                    thickness = 1.dp,
                    modifier = Modifier.padding(vertical = 8.dp)
                )
                
                // Content scrollable area
                Column(
                    modifier = Modifier
                        .weight(1f)
                        .verticalScroll(rememberScrollState())
                        .padding(vertical = 8.dp)
                ) {
                    // Game explanation
                    SectionTitle("What is PassivePitch?")
                    
                    Text(
                        text = "PassivePitch is a method of musical ear training for the development of perfect pitch and relative pitch.",
                        color = Color.White,
                        fontSize = 15.sp,
                        modifier = Modifier.padding(bottom = 12.dp)
                    )
                    
                    SectionTitle("What makes PassivePitch unique?")
                    
                    Text(
                        text = "1. Each level of progression increases the difficulty by a tiny amount:\n" +
                            "   • You will start out by identifying only two notes, easy.\n" +
                            "   • As you progress octaves and instruments will be slowly added so that you master the first two notes before adding an additional note to identify.\n" +
                            "   • This helps prevent plateaus and keeps you making measurable progress without stress.\n\n" +
                                "2. Passive audio lessons can be generated for any level and customized for your preferences:\n" +
                            "   • This maximizes the time you can spend practicing by incorporating it into your daily routine.\n" +
                            "   • Having more time between identifying notes promotes long term memory and recall.\n" +
                            "   • You can practice in a more relaxed manner which is crucial to perfect pitch development.\n\n" +
                                "3. Your performance will be tracked and problem notes will be identified for focused practice sessions.",
                        color = Color.White,
                        fontSize = 15.sp,
                        modifier = Modifier.padding(bottom = 12.dp)
                    )

                    SectionTitle("Tips")
                    Text(
                        text = "• Let the notes come to you. Do not try to force hearing them \"correctly\". Try to relax while practicing." +
                            "• As you build a long term memory of a note played by a variety of instruments, your brain will automatically learn the identifying characteristic of that note.\n" +
                            "• Play passive audio lessons with 30-60 second intervals and replay the note in your head while waiting. Try to reproduce it as accurately as possible.\n" +
                            "• Play passive audio lessons while falling asleep.\n" +
                            "• Play passive audio lessons in the background and actively listen whenever you can throughout the day.\n" +
                            "• Have patience. This is a marathon, not a sprint. The mind takes time to form abstract memories and connections.",
                        color = Color.White,
                        fontSize = 15.sp,
                        modifier = Modifier.padding(bottom = 12.dp)
                    )

                    SectionTitle("Understanding Pitch")
                    Text(
                        text = "• Pitch is caused by vibrations in air pressure (a pressure wave) that cause your eardrum to vibrate and send electrical signals that resemble the original vibration into your brain. Crazy right?\n" +
                            "• At very low frequencies, we can perceive the vibrations directly, but at higher frequencies, we can only detect the highness or lowness of a note, and the note's \"color\".\n" +
                            "• This \"color\", or flavor of a note is what gives each note its unique sound that is consistent between octaves and instruments.\n" +
                            "• In learning perfect pitch, we can both get better at identifying both the \"highness\" and the \"color\" of a note.\n" +
                            "• However the highness alone can be unreliable because of the effect of relative pitch on how notes are perceived.",
                        color = Color.White,
                        fontSize = 15.sp,
                        modifier = Modifier.padding(bottom = 12.dp)
                    )
                    
                    SectionTitle("Game Controls - Keyboard")
                    
                    Text(
                        text = "Keybindings can be modified in Settings, here are the defaults:\n" +
                                "• Left/Right Arrows: Move piece horizontally\n" +
                              "• Down Arrow: Move piece down\n" +
                              "• Up Arrow: Rotate piece 180°\n" +
                              "• X: Rotate piece clockwise\n" +
                              "• Z: Rotate piece counter-clockwise\n" +
                              "• Spacebar: Drop piece\n" +
                              "• C: Shape match\n" +
                              "• V: Color match\n" +
                              "• Enter: Start game\n" +
                              "• Escape: Pause game",
                        color = Color.White,
                        fontSize = 15.sp,
                        modifier = Modifier.padding(bottom = 12.dp)
                    )
                    
                    // Game settings explanation
                    SectionTitle("Game Settings")
                    
                    Text(
                        text = "• N-Back Level: Adjust how many positions back you need to remember\n" +
                              "• Color Mode: Toggle matching by color in addition to shape\n" +
                              "• Game Duration: Set how long each game session lasts\n" +
                              "• Visual Feedback: Choose how you receive feedback on matches\n" +
                              "• Controls: Toggle on-screen controls or use keyboard shortcuts",
                        color = Color.White,
                        fontSize = 15.sp,
                        modifier = Modifier.padding(bottom = 12.dp)
                    )
                }
                
                // Footer with buttons
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 16.dp),
                    horizontalArrangement = Arrangement.End
                ) {
                    Button(
                        onClick = onOpenSettings,
                        colors = ButtonDefaults.buttonColors(
                            backgroundColor = Color.Gray
                        ),
                        modifier = Modifier.padding(end = 8.dp).height(grid6)
                    ) {
                        Icon(
                            imageVector = Icons.Default.Settings,
                            contentDescription = "Settings",
                            modifier = Modifier.padding(end = 4.dp),
                            tint = Color.White
                        )
                        Text("Game Settings", color = Color.White)
                    }
                    
                    Button(
                        onClick = onDismiss,
                        colors = ButtonDefaults.buttonColors(
                            backgroundColor = Color(0xFF4CAF50)
                        ),
                        modifier = Modifier.height(grid6)
                    ) {
                        Text("Let's Play!", color = Color.White)
                    }
                }
            }
        }
    }
}

@Composable
private fun SectionTitle(title: String) {
    Text(
        text = title,
        color = Color.Cyan,
        fontSize = 18.sp,
        fontWeight = FontWeight.Bold,
        modifier = Modifier.padding(vertical = 8.dp)
    )
}

@Composable
fun GameInfoButton(
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    IconButton(
        onClick = onClick,
        modifier = modifier
    ) {
        Icon(
            imageVector = Icons.Default.Info,
            contentDescription = "Game Information",
            tint = Color.White
        )
    }
}