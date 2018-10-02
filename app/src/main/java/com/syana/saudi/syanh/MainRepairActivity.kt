package com.syana.saudi.syanh

import android.content.Context
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import kotlinx.android.synthetic.main.activity_main_repair.*
import kotlinx.android.synthetic.main.main_repair_ticket.view.*

class MainRepairActivity : AppCompatActivity() {

    var listOfMRepair = ArrayList<MRepair>()
    var adapter:MRepairAdapter?=null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main_repair)

        loadMRepair()

        adapter = MRepairAdapter(listOfMRepair, this)
        lvRepair.adapter = adapter

    }

    fun loadMRepair(){
        listOfMRepair.add(MRepair("الشاشة", "شاشة مكسورة أو اللمس لا يعمل", R.drawable.brokenscreen))
        listOfMRepair.add(MRepair("البطارية ومدخل الشاحن", "البطارية تنقص بسرعة أو لا تشحن", R.drawable.battery))
        listOfMRepair.add(MRepair("الهاتف لايعمل", "الهاتف لا يعمل أو يشتغل تماماً", R.drawable.tools))
    }

    class MRepairAdapter:BaseAdapter{

        var listOfMRepairLocal = ArrayList<MRepair>()
        var context:Context?=null
        constructor(listOfMRepair:ArrayList<MRepair>, context:Context){
            this.listOfMRepairLocal = listOfMRepair
            this.context = context
        }

        override fun getView(p0: Int, p1: View?, p2: ViewGroup?): View {
            val mRepair = listOfMRepairLocal[p0]


            val inflater = context!!.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
            val mRepairView = inflater.inflate(R.layout.main_repair_ticket, null)

            mRepairView.tvTitle.text = mRepair.title
            mRepairView.tvDesc.text = mRepair.description
            mRepairView.ivImage.setImageResource( mRepair.image!! )

            mRepairView.setOnClickListener {
                val intent = Intent(context, ProblemDesc::class.java)
                intent.putExtra("title", mRepair.title)
                context!!.startActivity( intent )
            }

            return mRepairView
        }

        override fun getItem(p0: Int): Any {
            return listOfMRepairLocal[p0]
        }

        override fun getItemId(p0: Int): Long {
            return p0.toLong()
        }

        override fun getCount(): Int {
            return listOfMRepairLocal.size
        }

    }
}
