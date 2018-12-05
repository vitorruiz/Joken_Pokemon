package br.com.vitorruiz.jokenpokemon

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import br.com.vitorruiz.jokenpokemon.model.User
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.FirebaseDatabase
import kotlinx.android.synthetic.main.activity_create_account.*

class CreateAccountActivity : AppCompatActivity() {

    private lateinit var mAuth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_create_account)

        mAuth = FirebaseAuth.getInstance()

        btnCreate.setOnClickListener {
            mAuth.createUserWithEmailAndPassword(
                    etEmail.text.toString(),
                    etPassword.text.toString()
            ).addOnCompleteListener {
                if (it.isSuccessful) {
                    saveInRealTimeDatabase()
                } else {
                    Toast.makeText(this@CreateAccountActivity, it.exception?.message, Toast.LENGTH_SHORT).show()
                }
            }
        }
    }


    private fun saveInRealTimeDatabase() {
        val user = User(etEmail.text.toString(), etName.text.toString(),
                etNick.text.toString(), etPassword.text.toString())

        FirebaseDatabase.getInstance().getReference("Users")
                .child(FirebaseAuth.getInstance().currentUser!!.uid)
                .setValue(user)
                .addOnCompleteListener {
                    if (it.isSuccessful) {
                        Toast.makeText(this, "Usuário criado com sucesso", Toast.LENGTH_SHORT).show()
                        val returnIntent = Intent()
                        returnIntent.putExtra("email", etEmail.text.toString())
                        setResult(RESULT_OK, returnIntent)
                        finish()
                    } else {
                        Toast.makeText(this, "Erro ao criar o usuário", Toast.LENGTH_SHORT).show()
                    }
                }
    }
}
