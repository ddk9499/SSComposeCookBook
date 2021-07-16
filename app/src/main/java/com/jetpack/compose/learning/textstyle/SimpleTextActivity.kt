package com.jetpack.compose.learning.textstyle

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.selection.SelectionContainer
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.BlendMode.Companion.Color
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Color.Companion.White
import androidx.compose.ui.graphics.Color.Companion.Green
import androidx.compose.ui.graphics.Shadow
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.*
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.jetpack.compose.learning.R

class SimpleTextActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            Column(

                modifier = Modifier.padding(10.dp),
                verticalArrangement = Arrangement.Top,
                horizontalAlignment = Alignment.CenterHorizontally,
            ) {
                SimpleText()
                TextFromResource()
                ColorText()
                TextColorFromResource()
                DifferentFonts()
                TextUnderLine()
                MultipleStylesInText()
                SelectableText()
                BackGroundText()
                ShadowText()
            }


        }
    }
}

@Preview
@Composable
fun SimpleText() {
    // Text composable is used to display some text
    Text(text = "This is Simple Text", Modifier.padding(bottom = 10.dp), fontSize = 30.sp)
}

@Preview
@Composable
fun TextFromResource() {
    // Text composable is used to display some text
    Text(
        stringResource(R.string.text_from_resource),
        Modifier.padding(bottom = 10.dp),
        fontSize = 20.sp
    )
}
@Preview
@Composable
fun ShadowText() {
    // Text composable is used to display some text
    Text(
       "Shadow on text",
        Modifier.padding(bottom = 10.dp),
        fontSize = 20.sp,style = TextStyle(shadow=Shadow(
            color = androidx.compose.ui.graphics.Color.Cyan, blurRadius = 5f,
            offset = Offset(2f, 2f)
        )))

}

@Preview
@Composable
fun BoldText() {
    Text(
        "Text style is bold",
        Modifier.padding(bottom = 10.dp),
        fontWeight = FontWeight.Bold,
        fontSize = 20.sp
    )
}

@Composable
fun ColorText() {
    Text(
        "Text color is Blue ",
        Modifier.padding(bottom = 10.dp),
        color = androidx.compose.ui.graphics.Color.Blue,
        fontSize = 20.sp
    )
}

@Composable
fun TextColorFromResource() {
    Text(
        "Text color from colors.xml ",
        color = Color(R.color.teal_700),
        fontSize = 20.sp,
        modifier = Modifier.padding(bottom = 10.dp)
    )
}

@Composable
fun DifferentFonts() {
    Column {
        Text(
            "This is FontFamily.Serif",
            fontSize = 20.sp,
            fontFamily = FontFamily.Serif,
            modifier = Modifier.padding(bottom = 10.dp)
        )
    }
}

@Composable
fun TextUnderLine() {
    Column {
        Text(
            "This is text with under Line",
            textDecoration = TextDecoration.LineThrough,
            fontSize = 20.sp,
            fontFamily = FontFamily.Serif,
            modifier = Modifier.padding(bottom = 10.dp)
        )
    }
}

@Composable
fun MultipleStylesInText() {
    Text(
        buildAnnotatedString {
            withStyle(style = SpanStyle(color = androidx.compose.ui.graphics.Color.Cyan)) {
                append("Different")
            }
            append(" Color")

            withStyle(
                style = SpanStyle(
                    fontWeight = FontWeight.Bold,
                    color = androidx.compose.ui.graphics.Color.Gray,
                )
            ) {
                append(" Same ")
            }
            append(" TEXT")
        },
        modifier = Modifier.padding(bottom = 10.dp), fontSize = 20.sp,
    )
}

@Composable
fun SelectableText() {
        SelectionContainer {
            Text(
                "Only this text is selectable",
                modifier = Modifier.padding(bottom = 10.dp),
                fontSize = 20.sp
            )
        }

}

@Composable
fun BackGroundText() {
    Surface(
        color = androidx.compose.ui.graphics.Color.Yellow,
        modifier = Modifier.padding(16.dp)
    ) {

        Text(
            "This text field has background",
            modifier = Modifier.padding(bottom = 10.dp),
            fontSize = 20.sp
        )
    }
}
