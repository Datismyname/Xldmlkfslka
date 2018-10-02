package com.syana.saudi.syanh

import android.content.Context
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import kotlinx.android.synthetic.main.activity_apple_list.*
import kotlinx.android.synthetic.main.apple_ticket.view.*

class AppleListActivity : AppCompatActivity() {

    var appleList = ArrayList<AppleList>()

    var adapter:AppleListAdapter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_apple_list)

        val bundle = intent.extras
        val name = bundle.getString("name")

        loadAppleList( name )

        adapter = AppleListAdapter(appleList, this)

        lvApple.adapter = adapter


    }

    fun loadAppleList(name:String){
        if (name == "آبل") {
            appleList.add(AppleList("آيفون", R.drawable.apple))
            appleList.add(AppleList("آيباد", R.drawable.apple))
        }
    }


    class AppleListAdapter:BaseAdapter{

        var appleListLocal = ArrayList<AppleList>()
        var context:Context? = null

        constructor( appleList:ArrayList<AppleList>, context:Context){
            this.appleListLocal = appleList
            this.context = context
        }


        override fun getView(p0: Int, p1: View?, p2: ViewGroup?): View {

            val aList = appleListLocal[p0]

            val inflater = context!!.getSystemService( Context.LAYOUT_INFLATER_SERVICE ) as LayoutInflater

            val appleListView = inflater.inflate( R.layout.apple_ticket, null )

            appleListView.tvTitle.text = aList.title
            appleListView.ivImage.setImageResource( aList.image!! )

            return appleListView


        }

        override fun getItem(p0: Int): Any {
            return appleListLocal[p0]
        }

        override fun getItemId(p0: Int): Long {
            return p0.toLong()
        }

        override fun getCount(): Int {
            return appleListLocal.size
        }

    }

}
