package co.com.sena.practica.loginapi

import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide


class HomeActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)


        val email = intent.getStringExtra("email")
        val nombreCompleto = intent.getStringExtra("nombreCompleto")
        val genero = intent.getStringExtra("genero")
        val fotoUrl = intent.getStringExtra("fotoUrl")


        // Mostrar la información en los elementos de la interfaz de usuario
        val emailTextView: TextView = findViewById(R.id.emailTextView)
        val nombreCompletoTextView: TextView = findViewById(R.id.nombreCompletoTextView)
        val generoTextView: TextView = findViewById(R.id.generoTextView)


        emailTextView.text = "Email: $email"
        nombreCompletoTextView.text = "Nombre Completo: $nombreCompleto"
        generoTextView.text = "Género: $genero"


        // Cargar la foto del usuario utilizando Glide
        Glide.with(this)
            .load(fotoUrl)


    }
}
