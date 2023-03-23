package com.candra.moneyapp.ui.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.candra.moneyapp.R
import com.candra.moneyapp.data.Village
import com.candra.moneyapp.databinding.ListLayoutBinding
import com.candra.moneyapp.helper.Utils

class AdapterVillage(
    private val context: Context,
    private val onClick: (Village) -> Unit,
    private val onChange: (Village) -> Unit,
    private val onDelete:(Village) -> Unit
): RecyclerView.Adapter<AdapterVillage.ViewHolder>()
{

    inner class ViewHolder(
        private val binding: ListLayoutBinding
    ): RecyclerView.ViewHolder(binding.root)
    {
        fun bind(data: Village)
        {
           with(binding){
               idVillage.text = data.id_village
               nameVillage.text = data.name_village
               yearVillage.text = Utils.convertStringToYear(data.year_village)
               budgetVillage.text = Utils.convertToRupiah(data.total_budget.toDouble())
               sukeudesBudgetJun.text = Utils.convertToRupiah(data.blt_suskeudes_village_juny.toDouble())
               sukeudesBudgetDes.text = Utils.convertToRupiah(data.blt_suskeudes_village_december.toDouble())
               sudistrict.text = context.getString(R.string.subdistrict,data.regency)

               itemCard.setOnClickListener {
                   onClick(data)
               }

               btnEditData.setOnClickListener {
                   onChange(data)
               }
               btnDelete.setOnClickListener {
                   onDelete(data)
               }
           }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AdapterVillage.ViewHolder {
        return ViewHolder(
            ListLayoutBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        )
    }

    override fun onBindViewHolder(holder: AdapterVillage.ViewHolder, position: Int) {
        holder.bind(data = differ.currentList[position])
    }

    override fun getItemCount(): Int = differ.currentList.size

    val differ = AsyncListDiffer(this,object: DiffUtil.ItemCallback<Village>(){
        override fun areItemsTheSame(oldItem: Village, newItem: Village): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: Village, newItem: Village): Boolean {
            return oldItem == newItem
        }
    })

    fun submitListData(it: List<Village>){
        differ.submitList(it)
    }

}