package com.example.runningapp.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.runningapp.R
import com.example.runningapp.db.RunObject
import com.example.runningapp.utilities.TrackingUtility
import kotlinx.android.synthetic.main.item_run.view.*
import java.text.SimpleDateFormat
import java.util.*

class RunAdapter: RecyclerView.Adapter<RunAdapter.RunViewHolder>() {

    val diffCallback = object : DiffUtil.ItemCallback<RunObject>() {
        override fun areItemsTheSame(oldItem: RunObject, newItem: RunObject): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: RunObject, newItem: RunObject): Boolean {
            return oldItem.hashCode() == newItem.hashCode()
        }

    }

    val differ = AsyncListDiffer(this, diffCallback)

    fun submitList(list: List<RunObject>) = differ.submitList(list)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RunViewHolder {
        return RunViewHolder(LayoutInflater.from(parent.context)
            .inflate(R.layout.item_run, parent, false))
    }

    override fun onBindViewHolder(holder: RunViewHolder, position: Int) {
        val run = differ.currentList[position]
        holder.itemView.apply {
            Glide.with(this)
                .load(run.img)
                .into(ivRunImage)

            val calendar = Calendar.getInstance().apply {
                timeInMillis = run.timestamp
            }
            val dateFormat = SimpleDateFormat("dd.MM.yy", Locale.getDefault())
            tvDate.text = dateFormat.format(calendar.time)

            val averageSpeed = "${run.avergeSpeed} km/h"
            tvAvgSpeed.text = averageSpeed

            val distanceInKm = "${run.distance / 1000} km"
            tvDistance.text = distanceInKm

            tvTime.text = TrackingUtility.getFormattedStopWatchTime(run.timeInMilies)

            val caloriesBurn = "${run.caloriesBurned} kcal"
            tvCalories.text = caloriesBurn


        }
    }

    override fun getItemCount(): Int {
        return differ.currentList.size
    }

    class RunViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    }
}