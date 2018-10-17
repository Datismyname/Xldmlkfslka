package com.syana.saudi.syanh

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_find_store.*

class FindByRatingActivity:Fragment(){

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        return inflater!!.inflate(R.layout.fragment_rating, container, false)
    }

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        if (activity.fab.visibility != View.GONE){
            activity.fab.visibility = View.GONE
            Toast.makeText(context,"Find by all says: it's not GONE !!", Toast.LENGTH_SHORT).show()
        }
    }

}