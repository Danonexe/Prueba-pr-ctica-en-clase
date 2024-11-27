package com.example.pruebapracticaclase.scenes

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
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
//esto es el registro donde se ven los datos
fun registro(navControlador: NavHostController, string: String?) {
    if(string !=null){
        //espo para poder mandarle el string seguro
        MostrarDatos(navControlador,string)
    }
}


@Composable
fun MostrarDatos(navControlador: NavHostController, string: String) {
    //obtenemos las variables haciendo un split
    val (nombre,apellidos,DNI,edad) = string.split("|")

    Column(
        modifier = Modifier.background(color = colorResource(R.color.grisbonito)).fillMaxWidth()
            .fillMaxHeight().padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Text(
            "Bienvenido $nombre $apellidos", fontSize = 20.sp,
            fontWeight = FontWeight.Bold,
            color = Color.White
        )
        Spacer(Modifier.height(30.dp))
        Text("DNI: $DNI", fontSize = 20.sp, color = Color.White)
        Spacer(Modifier.height(30.dp))
        Text("Edad: $edad", fontSize = 20.sp, color = Color.White)
        Spacer(Modifier.height(30.dp))
        //por si quiere volver a registrarse
        Button(onClick = { navControlador.navigate(route = AppScene.formulario.route) },
            colors = ButtonDefaults.buttonColors(
                containerColor = Color(0xFF36393F)
            )) {
            Text("Volver a Registrarte")
        }
    }
}