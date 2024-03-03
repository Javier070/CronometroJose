package com.example.reloj
import android.os.CountDownTimer

class Cronometro {

    private var cronometro: CountDownTimer? = null
    var tiempoTranscurrido: Long = 0


    fun iniciar() {
        cronometro = object : CountDownTimer(Long.MAX_VALUE, 1000) {
            override fun onTick(millisUntilFinished: Long) {
                tiempoTranscurrido += 1
            }


            override fun onFinish() {
            }
        }
        cronometro?.start()
    }

    fun detener() {
        cronometro?.cancel()
    }

    fun estaCorriendo(): Boolean {
        return cronometro != null
    }

    fun iniciarCuentaRegresiva(tiempo: Long) {
        object : CountDownTimer(tiempo * 1000, 1000) {
            override fun onTick(millisUntilFinished: Long) {
                println("Segundos restantes: ${millisUntilFinished / 1000}")
            }

            override fun onFinish() {
                println("¡Tiempo agotado!")
            }
        }.start()

    }


}
