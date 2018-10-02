package com.syana.saudi.syanh

import android.content.Context
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_companies_list.*
import kotlinx.android.synthetic.main.companies_ticket.view.*

class CompaniesListActivity : AppCompatActivity() {

    var listOFCompanies = ArrayList<ComList>()

    var adapter:ComListAdapter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_companies_list)

        loadComList()

        adapter = ComListAdapter( listOFCompanies, this )

        lvCompanies.adapter = adapter


    }

    fun loadComList(){
        listOFCompanies.add(ComList("آبل" , R.drawable.apple))
        listOFCompanies.add( ComList("سامسونج" , R.drawable.samsung) )
        listOFCompanies.add( ComList("هواوي" , R.drawable.huawei) )
    }

    class ComListAdapter:BaseAdapter{

        var listOFCompaniesLocal = ArrayList<ComList>()
        var context:Context? = null

        constructor(listOFCompanies:ArrayList<ComList>, context:Context){
            this.listOFCompaniesLocal = listOFCompanies
            this.context = context
        }

        override fun getView(p0: Int, p1: View?, p2: ViewGroup?): View {

            val comList = listOFCompaniesLocal[p0]

            val inflater = context!!.getSystemService( Context.LAYOUT_INFLATER_SERVICE ) as LayoutInflater
            val comListView = inflater.inflate( R.layout.companies_ticket, null )

            comListView.tvName.text = comList.name
            comListView.ivImage.setImageResource( comList.image!! )

            comListView.setOnClickListener {
                val intent = Intent(context, MainRepairActivity::class.java)
                intent.putExtra("name", comList.name)
                context!!.startActivity( intent )
            }

            return comListView
        }

        override fun getItem(p0: Int): Any {
            return listOFCompaniesLocal[p0]
        }

        override fun getItemId(p0: Int): Long {
            return p0.toLong()
        }

        override fun getCount(): Int {
            return listOFCompaniesLocal.size
        }

    }

}
