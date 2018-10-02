package com.syana.saudi.syanh

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private var mAuth: FirebaseAuth? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        mAuth = FirebaseAuth.getInstance()


        bNext.setOnClickListener {

            mAuth!!.signOut()

            val intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)
            finish()
        }

        btRepair.setOnClickListener{
            val intent = Intent( this, CompaniesListActivity::class.java)

            startActivity( intent )
        }
    }

    override fun onStart() {
        super.onStart()

        val currentUser = FirebaseAuth.getInstance().currentUser

        if ( currentUser == null ){
            val intent = Intent( this, LoginActivity::class.java )
            startActivity( intent )
            finish()
        }

    }


}

