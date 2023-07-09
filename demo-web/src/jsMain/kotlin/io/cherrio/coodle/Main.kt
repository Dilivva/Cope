package io.cherrio.coodle

import androidx.compose.runtime.*
import io.cope.core.content.setContent
import io.cope.core.layout.Alignment
import io.cope.core.layout.Box
import io.cope.core.layout.Column
import io.cope.core.layout.Row
import io.cope.core.modifier.*
import io.cope.foundation.button.BasicButton
import io.cope.foundation.button.Button
import io.cope.foundation.text.BasicText
import io.cope.foundation.text.Text
import io.cope.foundation.text.TextAlign
import io.cope.foundation.textfield.TextField
import io.nacular.doodle.application.Modules
import io.nacular.doodle.drawing.Color
import io.nacular.doodle.drawing.Font

import io.nacular.doodle.theme.basic.BasicTheme
import io.nacular.doodle.theme.native.NativeTheme


fun main() {

    // Moving on to material design YAAY!

    val themes = listOf(
        BasicTheme.basicButtonBehavior(),
        BasicTheme.basicLabelBehavior(),
        NativeTheme.nativeTextFieldBehavior(),
        BasicTheme.basicSwitchBehavior(),
        NativeTheme.nativeScrollPanelBehavior(),
        BasicTheme.basicListBehavior(),
    )

    val controls = listOf(Modules.PointerModule, Modules.ImageModule, Modules.FontModule)
    val allModules = themes + controls

    setContent(allModules) {
        var welcomeCount by remember { mutableStateOf(0) }


        Column(
            modifier = Modifier
                .background(Color.Yellow)
                .padding(15)
                .shadow(4),
            //verticalAlignment = Alignment.Vertical.CenterVertically
        ) {

            Text(
                text = "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum.  $welcomeCount",
                modifier = Modifier
                    .padding(15)
                    .width(700)
                    //.align(Alignment.Vertical.Bottom)
                    .weight(1f)
            )


            Button(
                modifier = Modifier
                    .padding(10)
                    //.height(100)
                    //.width(500)
                    //.align(Alignment.Vertical.Top)
                    .weight(1f)
                ,
                text = "Be Top",
            )



            Button(
                modifier = Modifier
                    .padding(10)
                    //.width(500)
                    //.height(100)
                    .weight(1f)
                    .clickable {
                        welcomeCount++
                    },
                text = "Login here",
            )
//
            BasicButton(
                modifier = Modifier
                    .padding(15)
                    .size(50, 200)
                    //.align(Alignment.Vertical.Top)
                ,
                onClick = { welcomeCount++ }
            ){
                BasicText(
                    modifier = Modifier.size(23, 70).align(Alignment.Center),
                    text = "Welcome",
                    color = Color.Black,
                    fontSize = 16,
                    fontWeight = 400,
                    fontStyle = Font.Style.Normal,
                    wrapWords = false
                )
            }
        }
    }
//    renderComposable(root = "roo"){
//        Div {
//            org.jetbrains.compose.web.dom.Button {  }
//        }
//    }
}

@Composable
fun DebugScreen(){

}

@Composable
fun LoginScreen2() {

    val firstMessage by remember { mutableStateOf("Welcome:") }
    val secondMessage by remember { mutableStateOf("Login hereR") }
    val thirdMessage by remember { mutableStateOf("ContinueR") }

    Box(modifier = Modifier.fillMaxSize().background(Color.Pink)) {
        Column(
            modifier = Modifier
                .fillMaxWidth(2)
                .padding(10)
                .background(Color.Orange)
                //.clip(RoundedCorner(15))
                .border(Color.Blue, 3.0, RoundedCorner(15))
                .align(io.cope.core.layout.Alignment.Center)
        ) {
            Column(modifier = Modifier
                //.fillMaxWidth()
                .padding(15)
                .background(Color.Pink)
                .shadow(4)
            ) {
                Text(
                    text = "Welcome",
                    modifier = Modifier
                        .background(Color.Pink)
                        .weight(1f)
                        .fillMaxWidth()
                )
                Text(
                    text = "Login here",
                    modifier = Modifier
                        .background(Color.Magenta)
                        .weight(1f)
                        .fillMaxWidth()
                )
                Text(
                    text = "Continue",
                    modifier = Modifier
                        .background(Color.Red)
                        .weight(1f)
                        .fillMaxWidth()
                )
            }
            Row(modifier = Modifier
                //.fillMaxWidth()
                .padding(15)
                .background(Color.Pink)
                .shadow(4)
            ) {
                Text(
                    text = "Welcome",
                    modifier = Modifier
                        //.background(Color.Pink)
                        .weight(1f)
                        .height(40)
                        .clickable {
                            //welcomeCount++
                        }
                )
                Text(
                    text = "",
                    modifier = Modifier
                        //.background(Color.Magenta)
                        .weight(1f)
                        .height(40)
                )
                Text(
                    text = "Continue",
                    modifier = Modifier
                        //.background(Color.Red)
                        .weight(1f)
                        .height(40)
                )
                Button(
                    modifier = Modifier
                        .weight(1f)
                        .height(40),
                    text = "Login",
                )
            }

            TextField(
                modifier = Modifier
                    .padding(10)
                    .fillMaxWidth()
                    .height(40)
            ) {

            }
            TextField(
                modifier = Modifier
                    .padding(10)
                    .fillMaxWidth()
                    .height(40)
            ) {

            }

            Button(
                modifier = Modifier
                    .padding(10)
                    .fillMaxWidth()
                    .background(Color.Green)
                    .height(50),
                text = "Login",
                textColor = Color.White
            )
        }
    }
}