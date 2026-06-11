package com.example.examen3.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.examen3.model.Usuario

@Dao
interface UsuarioDao {

    @Insert
    suspend fun registrarUsuario(usuario: Usuario)

    @Query("SELECT * FROM usuarios_table WHERE nombre = :nombre AND password = :contrasena LIMIT 1")
    suspend fun login(nombre: String, contrasena: String): Usuario?
}