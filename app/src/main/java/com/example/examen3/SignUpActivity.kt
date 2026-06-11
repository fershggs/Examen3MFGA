package com.example.examen3

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.examen3.databinding.ActivitySignUpBinding
import com.example.examen3.db.AppDatabase
import com.example.examen3.model.Usuario
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class SignUpActivity : AppCompatActivity() {

    lateinit var binding: ActivitySignUpBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySignUpBinding.inflate(layoutInflater)
        enableEdgeToEdge()
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        val db = MyApplication.getDatabase(this)
        val usuarioDao = db.usuarioDao()

        binding.btnSURegister.setOnClickListener {
            val nombreInput = binding.ettSUUserName.text.toString()
            val contrasenaInput = binding.ettSUPassword.text.toString()

            val usuario = Usuario(0, nombreInput, contrasenaInput)

            // Inserción directa en la base de datos en segundo plano
            lifecycleScope.launch(Dispatchers.IO) {
                usuarioDao.registrarUsuario(usuario)
            }
        }
    }
}

