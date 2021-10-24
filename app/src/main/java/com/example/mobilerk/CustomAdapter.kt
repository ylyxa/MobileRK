package com.example.mobilerk

import android.os.Build
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.annotation.RequiresApi
import androidx.recyclerview.widget.RecyclerView
import java.time.ZoneId
import java.util.*


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
    @RequiresApi(Build.VERSION_CODES.O)
    override fun onBindViewHolder(viewHolder: ViewHolder, position: Int) {

        // Get element from your dataset at this position and replace the
        // contents of the view with that element
        if (dataSet != null) {
            val days = dataSet.days.days.reversed()
            viewHolder.topTextView.text = java.time.format.DateTimeFormatter.ISO_LOCAL_DATE.withLocale(
                Locale.UK
            ).withZone(ZoneId.of("UTC+3")).format(java.time.Instant.ofEpochSecond(
                days[position].time.toLong()
            ))
            viewHolder.leftTextView.text = "High: " + days[position].high.toString()
            viewHolder.rightTextView.text = "Low: " + days[position].low.toString()
        }
    }

    // Return the size of your dataset (invoked by the layout manager)
    override fun getItemCount(): Int {
        return dataSet?.days?.days?.size ?: 0
    }
}
