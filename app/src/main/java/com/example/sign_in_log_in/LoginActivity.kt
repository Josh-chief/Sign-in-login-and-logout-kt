package com.example.sign_in_log_in

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

class LoginActivity : AppCompatActivity() {
    private lateinit var auth: FirebaseAuth
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        auth = Firebase.auth

        val signUpActivity: TextView = findViewById(R.id.textView_signup)

        signUpActivity.setOnClickListener{
            val intent = Intent(this, SignUpActivity::class.java)
            startActivity(intent)
        }

        val loginButton: Button = findViewById(R.id.button_login)

        loginButton.setOnClickListener{
            performLogin()
        }
    }

    private fun performLogin() {
        //input from user
        val email:EditText = findViewById(R.id.editText_email_login)
        val pass:EditText = findViewById(R.id.editTextText_password_login)

        //validation checks
        if(email.text.isEmpty() || pass.text.isEmpty()){
            Toast.makeText(this, "Fill all the fields", Toast.LENGTH_LONG)
                .show()
            return
        }
        val emailInput = email.text.toString()
        val passInput = pass.text.toString()
        auth.createUserWithEmailAndPassword(emailInput, passInput)
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                    // Sign in success, go to main activity
                    val intent = Intent(this, MainActivity::class.java) //change according to the project next activity
                    startActivity(intent)

                    Toast.makeText(
                        baseContext, "success",
                        Toast.LENGTH_SHORT
                    ).show()

                } else {
                    // If sign in fails, display a message to the user.

                    Toast.makeText(baseContext, "Authentication failed.",
                        Toast.LENGTH_SHORT).show()

                }
            }
            .addOnFailureListener{
                Toast.makeText(baseContext, "Authentication failed.${it.localizedMessage}",
                    Toast.LENGTH_SHORT).show()
            }
    }
}