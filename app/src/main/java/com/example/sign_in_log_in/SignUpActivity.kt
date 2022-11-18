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

class SignUpActivity : AppCompatActivity() {

    private lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_up)
        // Initialize Firebase Auth
        auth = Firebase.auth

        val loginActivity: TextView = findViewById(R.id.textView_login)
        loginActivity.setOnClickListener{
            val intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)
        }

        val signUpButton : Button = findViewById(R.id.button_signUp)


        signUpButton.setOnClickListener {
            performSignUp()
        }
        //email and pass from user

    }

    private fun performSignUp(){
        val email = findViewById<EditText>(R.id.editText_email_signup)
        val pass = findViewById<EditText>(R.id.editTextText_password_signup)

        if(email.text.isEmpty() || pass.text.isEmpty()){
            Toast.makeText(this, "Enter all fields", Toast.LENGTH_LONG)
                .show()
            return
        }

        val inputEmail = email.text.toString()
        val inputPass = email.text.toString()

        auth.createUserWithEmailAndPassword(inputEmail,inputPass)
       .addOnCompleteListener(this) { task ->
            if (task.isSuccessful) {
                // Sign in success, move to the next activity
                val intent = Intent(this, MainActivity::class.java) //change according to the project next activity
                startActivity(intent)

                Toast.makeText(
                    baseContext, "success",
                    Toast.LENGTH_SHORT
                ).show()

            } else {
                // If sign in fails, display a message to the user.

                Toast.makeText(
                    baseContext, "Authentication failed. $",
                    Toast.LENGTH_SHORT
                ).show()

            }
        }
            .addOnFailureListener{
                Toast.makeText(this , "Error ${it.localizedMessage}", Toast.LENGTH_LONG)
                    .show()
            }
    }
}