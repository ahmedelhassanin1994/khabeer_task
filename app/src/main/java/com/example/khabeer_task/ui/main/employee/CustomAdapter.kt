package com.example.khabeer_task.ui.main.employee

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.khabeer_task.R
import com.example.khabeer_task.data.responeses.employee.Allowences
import kotlin.math.log

class CustomAdapter(private val mList: ArrayList<Allowences>) : RecyclerView.Adapter<CustomAdapter.ViewHolder>() {

    private  val TAG = "CustomAdapter"
    // create new views
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        // inflates the card_view_design view
        // that is used to hold list item
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_layout, parent, false)

        return ViewHolder(view)
    }

    // binds the list items to a view
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        val ItemsViewModel = mList[position]

        // sets the image to the imageview from our itemHolder class

        Log.d(TAG, "onBindViewHolder:  ${mList.size} $position")
        if(position == 0) {
            holder.itemView.setBackgroundResource(R.color.white)
        }else if(position == 2){
            holder.itemView.setBackgroundResource(R.color.purple_700)

        }
        else{
            holder.itemView.setBackgroundResource(R.color.white)

        }

            // sets the text to the textview from our itemHolder class
        holder.textView_id.text = ItemsViewModel.SALCOMPCODE.toString()
        holder.textView_type.text = ItemsViewModel.COMPDESCEN
        holder.textView_value.text = ItemsViewModel.SALVALUE.toString()



    }

    // return the number of the items in the list
    override fun getItemCount(): Int {
        return mList.size
    }

    // Holds the views for adding it to image and text
    class ViewHolder(ItemView: View) : RecyclerView.ViewHolder(ItemView) {
        val textView_id: TextView = itemView.findViewById(R.id.text_id)
        val textView_type: TextView = itemView.findViewById(R.id.text_type)
        val textView_value: TextView = itemView.findViewById(R.id.text_value)


    }
}