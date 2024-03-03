package com.example.reloj


import android.os.Bundle
import android.os.CountDownTimer
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Close
import androidx.compose.material.icons.rounded.PlayArrow
import androidx.compose.material.icons.rounded.Refresh
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.reloj.ui.theme.RelojTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            RelojTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    CronometroHola()
                }
            }
        }
    }
}

@Composable
fun CronometroHola() {
    var tiempoTranscurrido by remember { mutableStateOf(0L) }
    var cronometro: CountDownTimer? by remember { mutableStateOf(null) }
    var registroTiempo by remember { mutableStateOf(0L) }

    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "Tiempo transcurrido: $tiempoTranscurrido segundos",
            modifier = Modifier.padding(16.dp)
        )

        Row(
            horizontalArrangement = Arrangement.spacedBy(8.dp),
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.padding(horizontal = 16.dp, vertical = 8.dp)
        ) { IconButton(
            onClick = {
                cronometro = object : CountDownTimer(Long.MAX_VALUE, 1000) {
                    override fun onTick(millisUntilFinished: Long) {
                        tiempoTranscurrido += 1
                    }

                    override fun onFinish() {
                        // Aquí puedes manejar las acciones que desees al terminar el cronómetro
                    }
                }
                cronometro?.start()
            }
        ) {
            Icon(Icons.Rounded.PlayArrow, contentDescription = null)
        }

            Button(
                onClick = {
                    cronometro?.cancel()
                }
            ) {
                Text("Detener")
            }


            IconButton(
                onClick = {
                    tiempoTranscurrido = 0L
                }
            ) {
                Icon(Icons.Rounded.Refresh, contentDescription = null)
            }
        }

        Button(
            onClick = {
                registroTiempo = tiempoTranscurrido
            }
        ) {
            Text("Registrar tiempo")
        }

        Text(
            text = "Tiempo registrado: $registroTiempo segundos",
            modifier = Modifier.padding(16.dp)
        )

        CuentaRegresivaButton(tiempo = 10)
        CuentaRegresivaButton(tiempo = 30)
        CuentaRegresivaButton(tiempo = 60)
    }
}

@Composable
fun CuentaRegresivaButton(tiempo: Int) {
    var contador by remember { mutableStateOf(tiempo) }
    var cuentaRegresiva: CountDownTimer? by remember { mutableStateOf(null) }

    Button(
        onClick = {
            cuentaRegresiva = object : CountDownTimer(tiempo * 1000L, 1000) {
                override fun onTick(millisUntilFinished: Long) {
                    contador -= 1
                }

                override fun onFinish() {
                    contador = 0
                }
            }
            cuentaRegresiva?.start()
        },
        modifier = Modifier.padding(8.dp)
    ) {
        Text("Cuenta Regresiva $tiempo")
    }

    DisposableEffect(Unit) {
        onDispose {
            cuentaRegresiva?.cancel()
        }
    }

    Text(
        text = "Segundos restantes: $contador",
        modifier = Modifier.padding(horizontal = 8.dp, vertical = 4.dp)
    )
}

@Preview
@Composable
fun CronometroHolaPreview() {
    RelojTheme {
        CronometroHola()
    }
}
