package com.example.ra.view

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.ra.R
import com.example.ra.model.MainViewModel

class MainActivity : ComponentActivity() {
    val objeto_view_Model: MainViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
            ) {
                Spacer(modifier = Modifier.padding(1.dp))
                Text(
                    text = "COTIZADOR DE AUTOS NUEVOS",
                    fontStyle = FontStyle.Normal,
                    fontWeight = FontWeight.Black,
                    fontSize = 22.sp,
                    color = Color.Black
                )
                MiImagen()
                Divider(modifier = Modifier.padding(5.dp), color = Color.DarkGray,
                    thickness=(5.dp),
                    startIndent=(15.dp))
                opcion1()
                Row(modifier = Modifier
                    .align(Alignment.CenterHorizontally)
                    .padding(1.dp)) {
                    Column(horizontalAlignment = Alignment.CenterHorizontally){
                    Text(text = "Marca: ", fontWeight = FontWeight.W600)
                    generarSpinner(objeto_view_Model,"")}
                }
                Row(modifier = Modifier
                    .align(Alignment.CenterHorizontally)
                    .padding(1.dp)){
                    Spacer(modifier = Modifier.size(1.dp))
                    Column(horizontalAlignment = Alignment.CenterHorizontally) {
                        Text(text = "Enganche: ", fontWeight = FontWeight.W600)
                        generarSpinner2(objeto_view_Model,"")
                    }

                }
                Row(modifier = Modifier
                    .align(Alignment.CenterHorizontally)
                    .padding(1.dp)){
                    Spacer(modifier = Modifier.size(10.dp))
                    Column(horizontalAlignment = Alignment.CenterHorizontally){
                    Text(text = "Financiamiento(Anual): ", fontWeight = FontWeight.W600)
                    generarSpinner3(objeto_view_Model, "")}
                }

                Divider(modifier = Modifier.padding(5.dp), color = Color.DarkGray,
                    thickness=(5.dp),
                    startIndent=(15.dp))
                var salida=remember { mutableStateOf("") }
                Button(
                    onClick = { salida.value= objeto_view_Model.conocerDatos()},
                    colors = ButtonDefaults.buttonColors(backgroundColor = Color.Red),
                    contentPadding = PaddingValues(15.dp, 15.dp),
                    modifier = Modifier.align(alignment = Alignment.CenterHorizontally),
                    shape = CircleShape,
                    border = BorderStroke(width = 2.dp, color = Color.Black)
                ) {
                    Text(
                        text = "COTIZAR",
                        fontSize = 15.sp,
                        fontWeight = FontWeight.W600,
                        color = Color.White
                    )
                }
                    Text(text = salida.value)

                Button(
                    onClick = {objeto_view_Model.reset() },
                    colors = ButtonDefaults.buttonColors(backgroundColor = Color.LightGray),
                    contentPadding = PaddingValues(5.dp, 5.dp),
                    modifier = Modifier.align(alignment = Alignment.End),
                    border = BorderStroke(width = 2.dp, color = Color.Gray)
                )
                {
                    Text(text = "REINICIAR")
                }
            }
        }
    }
    @Composable
    fun opcion1() {
        Text(text = "Nombre: ", fontWeight = FontWeight.W600,)
        Row(modifier = Modifier.padding(5.dp))
        {
            //Get data
            var text = remember { mutableStateOf("") }
            TextField(
                value = text.value, onValueChange = {
                    text.value = it
                }, keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text),
                modifier = Modifier
                    .width(240.dp)
                    .padding(end = 20.dp)
            )
            objeto_view_Model.ConocerNombre(text.value)
        }
    }

    @Composable
    fun generarSpinner(objeto: MainViewModel, texto: String) {
        var expanded by remember { mutableStateOf(false) }
        val suggestions = arrayOf(
                            arrayOf("Honda Accord  $678,026.22","Honda Accord", 678026.22),
                            arrayOf("Vw Touareg  $879,266.15","Vw Touareg", 879266.15),
                            arrayOf("BMW X5  $1,025,366.87","BMW X5", 1025366.87 ),
                            arrayOf("Mazda CX7  $988,641.02","Mazda CX7", 988641.02 )
        )
        Box {
            Button(
                onClick = { expanded = !expanded },
                colors = ButtonDefaults.buttonColors(backgroundColor = Color.Black),
                contentPadding = PaddingValues(10.dp, 10.dp)
            ) {
                Text("SELECCIONE UNA OPCIÓN", color = Color.Magenta, fontSize = 14.sp)
                Icon(
                    imageVector = Icons.Filled.ArrowDropDown, tint = Color.Magenta,
                    contentDescription = null,
                )
            }

            DropdownMenu(
                expanded = expanded,
                onDismissRequest = { expanded = false },
            ) {
                suggestions.forEach { label ->
                    DropdownMenuItem(onClick = {
                        expanded = false
                        objeto_view_Model.ConocerMarca(label[1].toString(),
                        label[2].toString().toDouble())

                    }) {
                        Text(text = label[0].toString())
                    }
                }

            }
        }
    }

    @Composable
    fun generarSpinner2(objeto: MainViewModel, texto: String) {
        var expanded by remember { mutableStateOf(false) }
        val suggestions = arrayOf(
                            arrayOf("20%" ,20),
                            arrayOf("40%", 40),
                            arrayOf("60%", 60))
        Box {
            Button(
                onClick = { expanded = !expanded },
                colors = ButtonDefaults.buttonColors(backgroundColor = Color.Black),
                contentPadding = PaddingValues(10.dp, 9.dp),
                border = BorderStroke(width = 2.dp, color = Color.Black)
            ) {
                Text("SELECCIONE", color = Color.Yellow, fontSize = 14.sp)
                Icon(
                    imageVector = Icons.Filled.ArrowDropDown, tint = Color.Yellow,
                    contentDescription = null,
                )
            }

            DropdownMenu(
                expanded = expanded,
                onDismissRequest = { expanded = false },
            ) {
                suggestions.forEach { label ->
                    DropdownMenuItem(onClick = {
                        expanded = false
                        objeto_view_Model.ConocerEnganche(label[1].toString().toDouble())

                    }) {
                        Text(text = label[0].toString())
                    }
                }

            }
        }
    }

    @Composable
    fun generarSpinner3(objeto: MainViewModel, texto: String) {
        var expanded by remember { mutableStateOf(false) }
        val suggestions = arrayOf(
                            arrayOf("1 año al 7.5%",1 , 7.5),
                            arrayOf("2 años al 9.5%",2 , 9.5),
                            arrayOf("3 años al 10.3%",3 , 10.3),
                            arrayOf("4 años al 12.6%",4 , 12.6),
                            arrayOf("5 años al 13.5%",5, 13.5)
        )
        Box {
            Button(
                onClick = { expanded = !expanded },
                colors = ButtonDefaults.buttonColors(backgroundColor = Color.Black),
                contentPadding = PaddingValues(10.dp, 9.dp)
            ) {
                Text("SELECCIONE PLAZO", color = Color.Cyan, fontSize = 14.sp)
                Icon(
                    imageVector = Icons.Filled.ArrowDropDown, tint = Color.Cyan,
                    contentDescription = null,
                )
            }

            DropdownMenu(
                expanded = expanded,
                onDismissRequest = { expanded = false },
            ) {
                suggestions.forEach { label ->
                    DropdownMenuItem(onClick = {
                        expanded = false
                        objeto_view_Model.ConocerFinanciamiento(label[1].toString().toInt(),
                        label[2].toString().toDouble())

                    }) {
                        Text(text = label[0].toString())
                    }
                }

            }
        }
    }

    @Composable
    fun MiImagen()
    {
        Image(
            painterResource(id = R.drawable.auto), "Coche"
        )
    }

}



