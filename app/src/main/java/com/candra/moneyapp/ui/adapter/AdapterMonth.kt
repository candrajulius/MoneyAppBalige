package com.candra.moneyapp.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.candra.moneyapp.data.BudgetChanneled
import com.candra.moneyapp.databinding.ListItemMonthBinding
import com.candra.moneyapp.helper.Utils

class AdapterMonth(
    private val onClick: (BudgetChanneled) -> Unit,
    private val onDelete: (BudgetChanneled) -> Unit,
): RecyclerView.Adapter<AdapterMonth.ViewHolder>()
{
    inner class ViewHolder(
        private val binding: ListItemMonthBinding
    ): RecyclerView.ViewHolder(
        binding.root)
    {
        fun bind(data: BudgetChanneled)
        {
            with(binding){
                tvBudgetChanneled.text = Utils.convertToRupiah(data.channeled_budget.toDouble())
                tvRangeKpm.text = data.range_budget
                tvMonth.text = data.month
                btnEditData.setOnClickListener {
                    onClick(data)
                }
                btnDelete.setOnClickListener {
                    onDelete(data)
                }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AdapterMonth.ViewHolder {
        return ViewHolder(
            ListItemMonthBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        )
    }

    override fun onBindViewHolder(holder: AdapterMonth.ViewHolder, position: Int) {
         holder.bind(differ.currentList[position])
    }

    override fun getItemCount(): Int =
        differ.currentList.size

    val differ = AsyncListDiffer(this,object: DiffUtil.ItemCallback<BudgetChanneled>(){
        override fun areItemsTheSame(oldItem: BudgetChanneled, newItem: BudgetChanneled): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: BudgetChanneled, newItem: BudgetChanneled): Boolean {
            return oldItem == newItem
        }
    })

    fun submitListData(it: List<BudgetChanneled>){
        differ.submitList(it)
    }

}