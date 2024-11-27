package com.example.pruebapracticaclase.scenes

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.pruebapracticaclase.R
import com.example.pruebapracticaclase.navigation.AppScene

@Composable
//esto es el formulario que va a coger los datos
fun formulario(navControlador: NavHostController) {
    App(navControlador)
}
// funcion para saber si un string es un número
fun isNumeric(s: String): Boolean {
    return try {
        s.toInt()
        true
    } catch (e: NumberFormatException) {
        false
    }
}
//robada de internet loca pero es se que crea un patron y luego mira si el patron es el mismo
fun validarDni(dni: String): Boolean {
    val regex = "^[0-9]{8}[A-Za-z]$".toRegex()
    return dni.matches(regex)
}

@Composable
fun App(navControlador: NavHostController) {
    Column(
        modifier = Modifier.background(color = colorResource(R.color.grisbonito)).fillMaxWidth().fillMaxHeight().padding(16.dp)
        ,
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
    Text("Formulario Registro", fontSize = 29.sp,
        fontWeight = FontWeight.Bold,
        color = Color.White)
        Spacer(Modifier.height(30.dp))
        //los texField para obtener las variables
        //nombre
        var nombre by remember { mutableStateOf("") }

        TextField(
            value = nombre,
            onValueChange = { nombre = it },
            label = {
                Text("Nombre:")
            }
        )
        Spacer(Modifier.height(20.dp))
        //apellidos
        var apellidos by remember { mutableStateOf("") }

        TextField(
            value = apellidos,
            onValueChange = { apellidos = it },
            label = {
                Text("Apellidos:")
            }
        )
        Spacer(Modifier.height(20.dp))
        //dni
        var dni by remember { mutableStateOf("") }

        TextField(
            value = dni,
            onValueChange = { dni = it },
            label = { Text("DNI:") }
        )
        Spacer(Modifier.height(20.dp))
        //edad
        var edad by remember { mutableStateOf("") }

        TextField(
            value = edad,
            onValueChange = { edad = it },
            label = { Text("Edad:") }
        )
        Spacer(Modifier.height(20.dp))
        //variables para el aviso
        val avisoError = remember { mutableStateOf(false)}
        //botón que comprueba los datos y si está todo correcto te manda a registro, si no activa el aviso
        Button(onClick = {
            var valido=true
            if (nombre==""){
                valido=false
            }
            if(apellidos==""){
                valido=false
            }
            if (validarDni(dni) ==false){
                valido=false
            }
            if(isNumeric(edad)==false || edad==""){
                valido=false
            }
            if(valido==true){
                val datos:String
                datos=nombre+"|"+apellidos+"|"+dni+"|"+edad
                navControlador.navigate(route = AppScene.registro.route+"/$datos")
            }else{
                avisoError.value = true
            }
                         },
            shape = RoundedCornerShape(8.dp),
            colors = ButtonDefaults.buttonColors(
                containerColor = Color(0xFF36393F)
            )) {
            Text("Registrarse")
        }
            //Aviso error
        if (avisoError.value) {
            AlertDialog(
                onDismissRequest = { avisoError.value = false },
                title = { Text("Error en el formulario") },
                text = { Text("Niguno de los campos puede ir vacío, el DNI tiene que ser válido y la edad es solo el número") },
                confirmButton = {
                    Button(onClick = { avisoError.value = false },
                        colors = ButtonDefaults.buttonColors(
                            containerColor = Color(0xFF36393F)
                        )) {
                        Text("Cerrar")
                    }
                }
            )
        }
    }
}