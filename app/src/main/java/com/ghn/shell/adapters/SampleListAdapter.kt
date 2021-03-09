package com.ghn.shell.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.ghn.shell.R
import com.ghn.shell.data.Sample
import com.ghn.shell.adapters.SampleListAdapter.SampleViewHolder

class SampleListAdapter : ListAdapter<Sample, SampleViewHolder>(SampleComparator()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SampleViewHolder {
        return SampleViewHolder.create(parent)
    }

    override fun onBindViewHolder(holder: SampleViewHolder, position: Int) {
        val current = getItem(position)
        holder.bind(current.name, current.imgUrl)
    }

    class SampleViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val sampleName: TextView = itemView.findViewById(R.id.sample_name)
        private val sampleImgUrl: TextView = itemView.findViewById(R.id.sample_imgUrl)

        fun bind(name: String?, imgUrl: String?) {
            sampleName.text = name
            sampleImgUrl.text = imgUrl
        }

        companion object {
            fun create(parent: ViewGroup): SampleViewHolder {
                val view: View = LayoutInflater.from(parent.context)
                    .inflate(R.layout.recyclerview_sample, parent, false)
                return SampleViewHolder(view)
            }
        }
    }

    class SampleComparator : DiffUtil.ItemCallback<Sample>() {
        override fun areItemsTheSame(oldItem: Sample, newItem: Sample): Boolean {
            return oldItem === newItem
        }

        override fun areContentsTheSame(oldItem: Sample, newItem: Sample): Boolean {
            return oldItem.id == newItem.id
        }
    }

}