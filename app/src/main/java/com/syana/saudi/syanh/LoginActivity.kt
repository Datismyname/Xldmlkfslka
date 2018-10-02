package com.syana.saudi.syanh

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.FirebaseDatabase
import kotlinx.android.synthetic.main.activity_login2.*

class LoginActivity : AppCompatActivity() {

    private var mAuth: FirebaseAuth? = null
    private var database = FirebaseDatabase.getInstance()
    private var myRef = database.reference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login2)

        /*loginBtn.setOnClickListener {


            if (!etRegEm.text.toString().isEmpty() && !etRegPass.text.toString().isEmpty()){

                progressBar.visibility = View.VISIBLE

                mAuth!!.signInWithEmailAndPassword( etRegEm.text.toString() , etRegPass.text.toString() ).addOnCompleteListener(this){ task->

                    if ( task.isSuccessful ){

                        sendToMain()

                    }else{

                        val errorMessage = task.exception!!.message
                        Toast.makeText(applicationContext, "Fail Login, Error: $errorMessage", Toast.LENGTH_LONG).show()

                    }
                    progressBar.visibility = View.INVISIBLE

                }

            }


        }*/

    }

    fun loginBtnEvent(view:View){

        if (!etEm.text.toString().isEmpty() && !etPass.text.toString().isEmpty()){

            progressBar.visibility = View.VISIBLE

            mAuth!!.signInWithEmailAndPassword( etEm.text.toString().trim() , etPass.text.toString() ).addOnCompleteListener(this){ task->

                if ( task.isSuccessful ){

                    sendToMain()

                }else{

                    val errorMessage = task.exception!!.message
                    Toast.makeText(applicationContext, "Fail Login, Error: $errorMessage", Toast.LENGTH_LONG).show()

                }
                progressBar.visibility = View.INVISIBLE

            }

        }
    }

    fun loginPageRigBtnEvent(view:View){

        val intent = Intent( this, RegisterActivity::class.java )
        startActivity( intent )

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
