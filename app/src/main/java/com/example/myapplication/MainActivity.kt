package com.example.myapplication

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.toUpperCase
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.myapplication.ui.theme.MyApplicationTheme

data class Semester(val grade: String, val credit: Int?)
class MainActivity : ComponentActivity() {
    private var semester:MutableList<Semester> = mutableListOf()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MyApplicationTheme {
                // A surface container using the 'background' color from the theme
                Surface(modifier = Modifier.fillMaxSize(), color = MaterialTheme.colorScheme.background) {
                    CGPA(semester)
                }
            }
        }
    }
}



@Composable
fun CGPA(semesters : MutableList<Semester>){
    var grade1 by remember { mutableStateOf("") }
    var Credit1 by remember { mutableStateOf<Int?>( null) }
    var grade2 by remember { mutableStateOf("") }
    var Credit2 by remember { mutableStateOf<Int?>( null) }
    var grade3 by remember { mutableStateOf("") }
    var Credit3 by remember { mutableStateOf<Int?>( null) }
    var grade4 by remember { mutableStateOf("") }
    var Credit4 by remember { mutableStateOf<Int?>( null) }
    var cgpa by remember { mutableStateOf(0.0) }

    Column(modifier = Modifier
        .fillMaxSize()
        .padding(10.dp) ) {
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
        Spacer8dp()
        GradeTextField(grade1){grade1=it}
        Spacer8dp()
        CreditTextField(Credit1) {Credit1=it}
        subjectText(subject = "Subject 2");
        Spacer8dp()
        GradeTextField(grade2){grade2=it}
        Spacer8dp()
        CreditTextField(Credit2) {Credit2=it}
        subjectText(subject = "Subject 3");
        Spacer8dp()
        GradeTextField(grade3){grade3=it}
        Spacer8dp()
        CreditTextField(Credit3) {Credit3=it}
        subjectText(subject = "Subject 4");
        Spacer8dp()
        GradeTextField(grade4){grade4=it}
        Spacer8dp()
        CreditTextField(Credit4) {Credit4=it}
        Spacer8dp()

        Row() {
            Column(modifier = Modifier.fillMaxHeight(),
                verticalArrangement = Arrangement.SpaceBetween) {
                Button(onClick = { /*TODO*/
                    val semester = Semester(grade1, Credit1 ?: 0)
                    semesters.add(semester);
                    val total_credit = semesters.sumOf { it.credit }
                    val total_grade_point = semesters.sumOf { calculateGradePoint(it.grade, it.credit) }

                    if(total_credit == 0){
                        cgpa = total_grade_point / total_credit.toDouble()
                    }
                    else{
                        cgpa = 0.0
                    }


                    
                    grade1 = "";
                    Credit1 = null;
                    grade2 = "";
                    Credit2 = null;
                    grade3 = "";
                    Credit3 = null;
                    grade4 = "";
                    Credit4 = null;

                                 },
                    colors = ButtonDefaults.buttonColors(Color(0xFFBEABED)
                        ),shape = RoundedCornerShape(15.dp))
                {
                    Text(text = "Calculate CGPA", fontSize = 16.sp, color = Color.Black,
                        fontFamily = FontFamily.Monospace,
                        fontWeight = FontWeight.W500)
                }
                Surface(modifier = Modifier
                    .width(179.dp)
                    .wrapContentHeight(), color = Color(0xFF263238),
                    shape = RoundedCornerShape(13.dp)) {
                    Text(modifier = Modifier.padding(start = 10.dp),
                        text = "Your all time \nCPGA: $cgpa",
                        style = TextStyle(fontFamily = FontFamily.Monospace,
                            fontSize = 16.sp, color = Color(0xFFFFFFFF)))
                }
            }
            Surface(modifier = Modifier
                .fillMaxSize()
                .padding(start = 10.dp), color = Color(0xFF263238), shape = RoundedCornerShape(15.dp)) {
                Column() {
                    Text(
                        modifier = Modifier.padding(start = 10.dp),
                        text = "Previous Semester ",
                        textAlign = TextAlign.Center,
                        style = TextStyle(
                            fontFamily = FontFamily.Monospace,
                            fontSize = 16.sp, color = Color(0xFFFFFFFF)
                        )
                    )
                    if(semesters.isNotEmpty()) {
                        for (semester in semesters){
                            Text(text = "Grade: , Credit: ",
                                color = Color.White,
                                fontFamily = FontFamily.Monospace,
                                fontSize = 16.sp,
                                textAlign = TextAlign.Center,
                                modifier = Modifier.fillMaxWidth())
                        }
                    }
                }
            }
        }


    }
}


fun calculateGradePoint(grade: String, credit: Int?) : Double{
    return when(grade.uppercase())
    {
        "A"-> 4.0
        "B"-> 3.0
        "C"-> 2.0
        "D"-> 1.0
        else -> 0.0
    } * credit!!

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
        textStyle = TextStyle(fontSize = 12.sp, color = Color.Black)
        )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CreditTextField(credit: Int?, onValueChange:(Int?)->Unit){
    TextField(value = credit?.toString() ?:"", onValueChange = {text ->
        onValueChange(text.toIntOrNull())
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
//@Preview(showBackground = true)
//@Composable
//fun GreetingPreview() {
//    MyApplicationTheme {
//        CGPA();
//    }
//}

//@Preview(showBackground = true)
//@Composable
//fun GradeTextField() {
//    MyApplicationTheme {
//        GradeTextField();
//    }
//}