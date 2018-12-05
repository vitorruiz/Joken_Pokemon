package br.com.vitorruiz.jokenpokemon

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.activity_login.*

class LoginActivity : AppCompatActivity() {

    private val SIGN_UP_CODE = 1
    private lateinit var mAuth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        mAuth = FirebaseAuth.getInstance()

        if (mAuth.currentUser != null) {
            startActivity(Intent(this, MainActivity::class.java))
        }

        btnLogin.setOnClickListener {
            mAuth.signInWithEmailAndPassword(
                    etEmail.text.toString(),
                    etPassword.text.toString()
            ).addOnCompleteListener {
                if (it.isSuccessful) {
                    startActivity(Intent(this, MainActivity::class.java))
                } else {
                    Toast.makeText(this@LoginActivity, it.exception?.message, Toast.LENGTH_SHORT).show()
                }
            }
        }

        btnSignUp.setOnClickListener {
            startActivityForResult(Intent(this, CreateAccountActivity::class.java), SIGN_UP_CODE)
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode == SIGN_UP_CODE) {
            etEmail.setText(data?.getStringExtra("email"))
        }
    }
}
