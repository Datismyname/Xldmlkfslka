package com.syana.saudi.syanh

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.android.synthetic.main.activity_setup.*

class SetupActivity : AppCompatActivity() {

    private var mAuth: FirebaseAuth? = null
    private var database = FirebaseDatabase.getInstance()
    private var myRef = database.reference
    private val firebaseFirestore = FirebaseFirestore.getInstance()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_setup)

        mAuth = FirebaseAuth.getInstance()


    }

    fun buSaveSetupEvent(view:View){
        var userID = mAuth!!.currentUser!!.uid

        Toast.makeText(applicationContext, "save button clicked $userID", Toast.LENGTH_SHORT).show()


        val userMap = HashMap<String,Any>()

        userMap["name"] = etSetupName.text.toString()
        userMap["number"] = etSetupPhoneNumber.text.toString()

        setupProgressBar.visibility = View.VISIBLE

        firebaseFirestore.collection("Users").document( userID ).set( userMap ).addOnCompleteListener(this) {task ->

            if ( task.isSuccessful ){
                Toast.makeText(applicationContext, "successfully added to database $userID", Toast.LENGTH_SHORT).show()

                val intent = Intent( this, MainActivity::class.java )
                startActivity( intent )
                finish()


            }else{

                val errorMessage = task.exception!!.message
                Toast.makeText(applicationContext, "Fail to update setup, Error: $errorMessage", Toast.LENGTH_LONG).show()


            }
            setupProgressBar.visibility = View.INVISIBLE

        }




    }






}
