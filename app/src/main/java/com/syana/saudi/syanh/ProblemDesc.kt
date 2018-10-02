package com.syana.saudi.syanh

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.android.synthetic.main.activity_problem_desc.*

class ProblemDesc : AppCompatActivity() {

    private var mAuth: FirebaseAuth? = null
    private var database = FirebaseDatabase.getInstance()
    private var myRef = database.reference
    private val firebaseFirestore = FirebaseFirestore.getInstance()

    val bundle = intent.extras

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_problem_desc)



        tvTitle.text = bundle.getString("title")

        mAuth = FirebaseAuth.getInstance()


    }


    fun buSendOrderEvent(view: View){

        val userID = mAuth!!.currentUser!!.uid

        val orderMap = HashMap< String, Any >()

        //userMap["order_number"] =  "" server side
        orderMap["company"] =  bundle.getString("title")
        orderMap["predefined_problem"] = bundle.getString("title")

        orderMap["phone_type"] = etPhoneType.text.toString()
        orderMap["phone_number"] = ""
        orderMap["user_defined_problem"] = etProblem.text.toString()
        orderMap["location"] = ""
        orderMap["order_status"] = "new"
        orderMap["what_happened"] = "user cancel by phone"
        //userMap["price"] = "100" seller side
        //userMap["order_time"] = "" server side
        //userMap["done_time"] = "" server side
        orderMap["warranty"] = "one week"

        firebaseFirestore.collection("Fix Orders").document( userID ).set( orderMap ).addOnCompleteListener( this ){ task ->
            if( task.isSuccessful ){

            }else{

            }
        }


    }


}
