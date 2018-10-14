package com.syana.saudi.syanh

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import kotlinx.android.synthetic.main.activity_login.*
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.DatabaseReference






class Login : AppCompatActivity() {

    private var mAuth: FirebaseAuth? = null
    private var database = FirebaseDatabase.getInstance()
    private var myRef = database.reference


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        mAuth = FirebaseAuth.getInstance()
    }

    fun buLoginEvent(view:View){

        loginToFirebase( etEmail.text.toString(), etPassword.text.toString() )
    }

    fun loginToFirebase( email:String, pass:String ){

        mAuth!!.createUserWithEmailAndPassword( email, pass )
                .addOnCompleteListener( this ){ task ->
                    if ( task.isSuccessful ){
                        Toast.makeText(applicationContext, "Successful Login", Toast.LENGTH_LONG).show()

                        var currentUser = mAuth!!.currentUser
                        
                        loadMain()
                    }else{
                        Toast.makeText(applicationContext, "Fail Login", Toast.LENGTH_LONG).show()
                    }
                }

    }

    override fun onStart() {
        super.onStart()
        loadMain()
    }
    fun loadMain(){

        var currentUser = mAuth!!.currentUser

        if ( currentUser != null ) {
            myRef.child("Users").child(currentUser.uid).setValue(currentUser.email)

            var intent = Intent(this, MainActivity::class.java)
            intent.putExtra("email", currentUser!!.email)
            intent.putExtra("uid", currentUser.uid)
            intent.putExtra("phone", currentUser.phoneNumber)

            startActivity(intent)
        }
    }
}
