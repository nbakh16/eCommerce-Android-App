package com.nbakh.ecomadmin.Adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.nbakh.ecomadmin.databinding.DashboardItemRowBinding
import com.nbakh.ecomadmin.model.DashboardItem
import com.nbakh.ecomadmin.model.DashboardItemType
import com.nbakh.ecomadmin.model.dashboardItemList

class DashboardAdapter(val callback: (DashboardItemType) -> Unit) : RecyclerView.Adapter<DashboardAdapter.DashboardItemViewHolder>(){


    class DashboardItemViewHolder(val binding: DashboardItemRowBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(item: DashboardItem) {
            binding.item = item
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DashboardItemViewHolder {
        val binding = DashboardItemRowBinding.inflate(
            LayoutInflater.from(parent.context), parent, false
        )
        return DashboardItemViewHolder(binding)
    }

    override fun onBindViewHolder(holder: DashboardItemViewHolder, position: Int) {
        val item = dashboardItemList.get(position)
        holder.bind(item)
        holder.itemView.setOnClickListener {
            callback(item.type)
        }
    }

    override fun getItemCount() = dashboardItemList.size
}