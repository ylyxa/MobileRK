package com.example.mobilerk

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView


class CustomAdapter(private val dataSet: WebData?) :
    RecyclerView.Adapter<CustomAdapter.ViewHolder>() {

    /**
     * Provide a reference to the type of views that you are using
     * (custom ViewHolder).
     */
    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val topTextView: TextView = view.findViewById(R.id.topText)
        val leftTextView: TextView = view.findViewById(R.id.leftText)
        val rightTextView: TextView = view.findViewById(R.id.rightText)
    }

    // Create new views (invoked by the layout manager)
    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): ViewHolder {
        // Create a new view, which defines the UI of the list item
        val view = LayoutInflater.from(viewGroup.context)
            .inflate(R.layout.listitem, viewGroup, false)

        return ViewHolder(view)
    }

    // Replace the contents of a view (invoked by the layout manager)
    override fun onBindViewHolder(viewHolder: ViewHolder, position: Int) {

        // Get element from your dataset at this position and replace the
        // contents of the view with that element
        if (dataSet != null) {
            viewHolder.topTextView.text = dataSet.days.days[position].time.toString()
            viewHolder.leftTextView.text = dataSet.days.days[position].high.toString()
            viewHolder.rightTextView.text = dataSet.days.days[position].high.toString()
        }
    }

    // Return the size of your dataset (invoked by the layout manager)
    override fun getItemCount(): Int {
        return dataSet?.days?.days?.size ?: 0
    }
}
