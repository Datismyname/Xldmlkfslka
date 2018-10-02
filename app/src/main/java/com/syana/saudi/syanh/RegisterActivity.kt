package com.syana.saudi.syanh

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.FirebaseDatabase
import kotlinx.android.synthetic.main.activity_register.*

class RegisterActivity : AppCompatActivity() {

    private var mAuth: FirebaseAuth? = null
    private var database = FirebaseDatabase.getInstance()
    private var myRef = database.reference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)




    }

    fun lgBtnEvento(view: View){
        finish()

    }
    fun regBtnEvento(view:View){

        Toast.makeText(applicationContext, "Register button clicked", Toast.LENGTH_LONG).show()

        val email = etRegEma.text.toString().trim()
        val password = etRegPassw.text.toString()
        val confirmPassword = etRegConfPassw.text.toString()

        if (!email.isEmpty() && !password.isEmpty() && !confirmPassword.isEmpty()){

            if ( password == confirmPassword ){
                Toast.makeText(applicationContext, "passwords good!", Toast.LENGTH_LONG).show()

                regProgressBar.visibility = View.VISIBLE

                mAuth!!.createUserWithEmailAndPassword( email , password ).addOnCompleteListener(this) { task ->

                    if (task.isSuccessful) {
                        Toast.makeText(applicationContext, "Register successfully", Toast.LENGTH_LONG).show()

                        val intent = Intent( this, SetupActivity::class.java )
                        startActivity( intent )


                    } else {

                        val errorMessage = task.exception!!.message
                        Toast.makeText(applicationContext, "Fail Login, Error: $errorMessage", Toast.LENGTH_LONG).show()

                    }

                    regProgressBar.visibility = View.INVISIBLE
                }

            }else{
                Toast.makeText(applicationContext, "Passwords not matches", Toast.LENGTH_LONG).show()
            }

        }
    }


    override fun onStart() {
        super.onStart()

        mAuth = FirebaseAuth.getInstance()

        var currentUser = mAuth!!.currentUser

        if ( currentUser != null ){

            sendToMain()

        }
    }


    fun sendToMain(){
        val intent = Intent( this, MainActivity::class.java )
        startActivity( intent )
        finish()
    }


}
