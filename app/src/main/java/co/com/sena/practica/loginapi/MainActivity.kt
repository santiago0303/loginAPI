package co.com.sena.practica.loginapi

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.ProgressBar
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley
import org.json.JSONException
import org.json.JSONObject

class MainActivity : AppCompatActivity() {
    private lateinit var user: EditText
    private lateinit var pass: EditText
    private lateinit var boton: Button
    private lateinit var progreso: ProgressBar

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        user = findViewById(R.id.user)
        pass = findViewById(R.id.pass)
        boton = findViewById(R.id.boton)
        progreso = findViewById(R.id.progreso)

        boton.setOnClickListener {
            val username = User("kminchelle", "0lelplR")
            if (user.text.toString() == username.username && pass.text.toString() == username.password) {
                // Credenciales válidas, mostrar ProgressBar y hacer la solicitud HTTP
                progreso.visibility = ProgressBar.VISIBLE
                boton.visibility = Button.GONE
                hacerSolicitudHTTP()
            } else {
                // Credenciales inválidas, muestra un mensaje de error
                Toast.makeText(this, "Credenciales incorrectas", Toast.LENGTH_SHORT).show()
                pass.text.clear() // Limpiar el campo de contraseña
            }
        }
    }

    fun hacerSolicitudHTTP() {
        val url = "https://dummyjson.com/auth/login"

        // Crear un JSONObject a partir de la HashMap
        val jsonBody = JSONObject()
        jsonBody.put("username", user.text.toString())
        jsonBody.put("password", pass.text.toString())

        val request = JsonObjectRequest(Request.Method.POST, url, jsonBody,
            Response.Listener { response ->
                // Resto del código sigue igual
                progreso.visibility = ProgressBar.GONE
                boton.visibility = Button.VISIBLE

                try {
                    val email = response.getString("email")
                    val nombreCompleto = response.getString("nombreCompleto")
                    val genero = response.getString("genero")
                    val fotoUrl = response.getString("foto")

                    val intent = Intent(this, HomeActivity::class.java)
                    intent.putExtra("email", email)
                    intent.putExtra("nombreCompleto", nombreCompleto)
                    intent.putExtra("genero", genero)
                    intent.putExtra("fotoUrl", fotoUrl)
                    startActivity(intent)
                    finish()
                } catch (e: JSONException) {
                    Log.e("MainActivity", "Error al procesar la respuesta JSON", e)

                }
            },
            Response.ErrorListener {
                progreso.visibility = ProgressBar.GONE
                boton.visibility = Button.VISIBLE

                Toast.makeText(this, "Error en la solicitud HTTP", Toast.LENGTH_SHORT).show()
            })

        val queue = Volley.newRequestQueue(this)
        queue.add(request)
    }

}
