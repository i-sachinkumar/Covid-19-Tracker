package com.ihrsachin.covid_19tracker

import android.content.ActivityNotFoundException
import android.content.Context
import android.content.Intent
import android.net.Uri
import android.provider.AlarmClock.EXTRA_MESSAGE
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.TextView
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.core.content.ContextCompat.startActivity
import com.ihrsachin.covid_19tracker.data.State
import java.net.MalformedURLException

class StateAdapter(context: Context, list: List<State>) : ArrayAdapter<State>(context, 0 , list){
    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        val current : State? = getItem(position)
        var listViewItem = convertView

        if(listViewItem == null){
            listViewItem = LayoutInflater.from(context).inflate(R.layout.list_item,parent,false)
        }
        val state_name_textView : TextView = listViewItem!!.findViewById(R.id.state_name)
        state_name_textView.text = current!!.state_name

        val total_confirmed_textView : TextView = listViewItem.findViewById(R.id.total_confirmed)
        total_confirmed_textView.text = current.total_confirmed.toString()

        val total_cured_textView : TextView = listViewItem.findViewById(R.id.total_cured)
        total_cured_textView.text = current.total_cured.toString()

        val total_death_textView : TextView = listViewItem.findViewById(R.id.total_death)
        total_death_textView.text = current.total_death.toString()

        val total_active_textView : TextView = listViewItem.findViewById(R.id.total_active)
        total_active_textView.text = (current.total_confirmed - current.total_cured - current.total_death).toString()

        val day_confirm_textView : TextView = listViewItem.findViewById(R.id.day_confirm)
        day_confirm_textView.text = current.day_confirm.toString()

        val day_cured_textView : TextView = listViewItem.findViewById(R.id.day_cured)
        day_cured_textView.text = current.day_cured.toString()

        val day_death_textView : TextView = listViewItem.findViewById(R.id.day_death)
        day_death_textView.text = current.day_death.toString()

        val day_active_textView : TextView = listViewItem.findViewById(R.id.day_active)
        day_active_textView.text = current.day_confirm.toString()


        // on click -> open url about clicked earthquake in browser
        listViewItem.setOnClickListener{
            try {
               val intent = Intent(context, MoreDetails::class.java)
                intent.putExtra("State", current)
                context.startActivity(intent)
            } catch (e : ActivityNotFoundException){
                Toast.makeText(context,"No Supported Application found!", Toast.LENGTH_SHORT)
                    .show()
            } catch (e : MalformedURLException) {
                Toast.makeText(context, "Invalid Url is provided", Toast.LENGTH_SHORT)
                    .show()
            }
        }

        return listViewItem
    }

}