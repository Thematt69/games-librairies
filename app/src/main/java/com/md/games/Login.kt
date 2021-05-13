package com.md.games

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

class Login : AppCompatActivity() {
    private lateinit var auth: FirebaseAuth
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.i("MD","onCreate => Login")
        setContentView(R.layout.activity_login)

        // Initialize Firebase Auth
        auth = Firebase.auth
    }

    public override fun onStart() {
        super.onStart()
        Log.i("MD","onStart => Login")
        // Check if user is signed in (non-null) and update UI accordingly.
        val currentUser = auth.currentUser
        if(currentUser != null){
            Log.i("MD","autoLogin => Login")
            updateUI(currentUser)
        }
    }

    fun onLogin(view: View){
        Log.i("MD","onLogin => Login")
        val editTextEmail = findViewById<EditText>(R.id.textInputEditTextEmailAddress)
        var email = editTextEmail.text.toString()
        val editTextPassword = findViewById<EditText>(R.id.textInputEditTextPassword)
        var password = editTextPassword.text.toString()
        if(email != "" && password != "") {
            auth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(this) { task ->
                    if (task.isSuccessful) {
                        // Sign in success, update UI with the signed-in user's information
                        Log.d("FireBase Auth", "signInWithEmail:success")
                        Toast.makeText(
                            baseContext, R.string.succesAuthentification,
                            Toast.LENGTH_SHORT
                        ).show()
                        val currentUser = auth.currentUser
                        updateUI(currentUser)
                    } else {
                        // If sign in fails, display a message to the user.
                        Log.w("FireBase Auth", "signInWithEmail:failure", task.exception)
                        Toast.makeText(
                            baseContext, R.string.errorAuthentification,
                            Toast.LENGTH_SHORT
                        ).show()
                    }
                }
        }
    }

    private fun updateUI(user: FirebaseUser?) {
        Log.i("MD","updateUI => Login")
        val intent = Intent(this,MainActivity::class.java)
        val displayName = user?.displayName
        val email = user?.email
        val uid = user?.uid
        intent.putExtra("displayName",displayName)
        intent.putExtra("email",email)
        intent.putExtra("uid",uid)
        startActivity(intent)
    }
}