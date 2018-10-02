package com.syana.saudi.syanh

import android.content.Context
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.TextView
import kotlinx.android.synthetic.main.activity_orders_list.*
import kotlinx.android.synthetic.main.orders_ticket.view.*

class OrdersListActivity : AppCompatActivity() {

    var listOfOrders = ArrayList<OrdersList>()

    var adapter:OrderListAdapter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_orders_list)

        loadOrders()

        lvOrders.adapter = adapter

    }

    fun loadOrders(){


        listOfOrders.add( OrdersList( "", "", "" ) )

    }




    class OrderListAdapter:BaseAdapter{

        var listOfOrdersLocal = ArrayList<OrdersList>()

        var context:Context? = null

        constructor( listOfOrders:ArrayList<OrdersList>, context:Context ){

            this.listOfOrdersLocal = listOfOrders
            this.context = context

        }




        override fun getView(p0: Int, p1: View?, p2: ViewGroup?): View {

            val order = listOfOrdersLocal[p0]

            val inflater = context!!.getSystemService( Context.LAYOUT_INFLATER_SERVICE ) as LayoutInflater

            val orderView = inflater.inflate( R.layout.orders_ticket, null )

            orderView.tvName.text = order.name
            orderView.tvPhone.text = order.title
            orderView.tvProblem.text = order.subTitle

            orderView.setOnClickListener {
                val intent = Intent(context, MainRepairActivity::class.java)
                //intent.putExtra("name", name)
                context!!.startActivity( intent )
            }



            return orderView

        }

        override fun getItem(p0: Int): Any {
            return listOfOrdersLocal[p0]
        }

        override fun getItemId(p0: Int): Long {
            return p0.toLong()
        }

        override fun getCount(): Int {
            return listOfOrdersLocal.size
        }

    }
}
