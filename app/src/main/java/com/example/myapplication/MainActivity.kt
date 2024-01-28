package com.example.myapplication

import android.os.Bundle
import android.widget.NumberPicker.OnValueChangeListener
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.textInputServiceFactory
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.myapplication.ui.theme.MyApplicationTheme
import javax.security.auth.Subject

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MyApplicationTheme {
                // A surface container using the 'background' color from the theme
                Surface(modifier = Modifier.fillMaxSize(), color = MaterialTheme.colorScheme.background) {

                }
            }
        }
    }
}



@Composable
fun CGPA(){
    Column(modifier = Modifier.fillMaxSize().padding(10.dp) ) {
        Text(
            text = "Welcome to our \nCGPA Calculator",
            modifier = Modifier.fillMaxWidth(),
            style = TextStyle(
                fontSize = 20.sp,
                textAlign = TextAlign.Center,
                fontFamily = FontFamily.Monospace,
                fontWeight = FontWeight.W800
            )
        )
        Spacer(modifier = Modifier.padding(top = 10.dp))
        subjectText(subject = "Subject 1");
        GradeTextField(grade = "Ali", onValueChange = {})
        Spacer8dp()
        CreditTextField(credit = "sher", onValueChange = {})
    }
}

@Composable
fun subjectText(subject: String) {
    Text(text = subject,
        modifier = Modifier.fillMaxWidth(),
        style = TextStyle(fontSize = 16.sp,
            //textAlign = TextAlign.Center,
            fontFamily = FontFamily.Monospace,
            fontWeight = FontWeight.W800 )
    )
}
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun GradeTextField(grade: String, onValueChange:(String)->Unit){
    TextField(value = grade, onValueChange = {text ->
        onValueChange(text)
    }, modifier = Modifier
        .fillMaxWidth()
        .height(50.dp),
        label = { Text(text = "Enter Grade", color = Color.White, fontSize = 14.sp)},
        colors = TextFieldDefaults.textFieldColors(
            focusedIndicatorColor = Color.Transparent,
            unfocusedIndicatorColor = Color.Transparent,
            containerColor = Color(0xFF7E57C2)
        ), shape = RoundedCornerShape(15.dp),
        textStyle = TextStyle(fontSize = 12.sp, color = Color.White)
        )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CreditTextField(credit: String, onValueChange:(String)->Unit){
    TextField(value = credit, onValueChange = {text ->
        onValueChange(text)
    }, modifier = Modifier
        .fillMaxWidth()
        .height(50.dp),
        label = { Text(text = "Enter Credit", color = Color.White, fontSize = 14.sp)},
        colors = TextFieldDefaults.textFieldColors(
            focusedIndicatorColor = Color.Transparent,
            unfocusedIndicatorColor = Color.Transparent,
            containerColor = Color(0xFF7D8CCED)
        ), shape = RoundedCornerShape(15.dp),
        textStyle = TextStyle(fontSize = 12.sp, color = Color.White)
    )
}
@Composable
fun Spacer8dp()
{
    Spacer(modifier = Modifier.padding(top = 8.dp))
}
@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    MyApplicationTheme {
        CGPA();
    }
}

//@Preview(showBackground = true)
//@Composable
//fun GradeTextField() {
//    MyApplicationTheme {
//        GradeTextField();
//    }
//}