package com.candra.moneyapp.ui.adapter

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.candra.moneyapp.R
import com.candra.moneyapp.data.Village
import com.candra.moneyapp.databinding.ListVillageBinding
import com.candra.moneyapp.helper.Constant
import com.candra.moneyapp.ui.activity.main.SubdistrictActivity

class AdapterRegency(
    private val context: Context,
    private val onClick: (Village) -> Unit,
    private val onDelete: (Village) -> Unit
): RecyclerView.Adapter<AdapterRegency.ViewHolder>()
{
    inner class ViewHolder(
        private val binding: ListVillageBinding
    ): RecyclerView.ViewHolder(binding.root)
    {
        fun bind(data: Village){
            with(binding){
                tvRegency.text = context.getString(R.string.subdistrict,data.regency)
                btnDeleteData.setOnClickListener {
                    onDelete(data)
                }
                btnEditData.setOnClickListener {
                    onClick(data)
                }
                itemCard.setOnClickListener {
                    Intent(context,SubdistrictActivity::class.java).apply {
                        putExtra(Constant.EXTRA_VILLAGE,data.regency).also { context.startActivity(it) }
                    }
                }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AdapterRegency.ViewHolder {
        return  ViewHolder(
            ListVillageBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        )
    }

    override fun onBindViewHolder(holder: AdapterRegency.ViewHolder, position: Int) {
        holder.bind(differ.currentList[position])
    }
    override fun getItemCount(): Int = differ.currentList.size

    private val differ = AsyncListDiffer(this,object: DiffUtil.ItemCallback<Village>(){
        override fun areItemsTheSame(oldItem: Village, newItem: Village): Boolean  =
            oldItem.id == newItem.id

        override fun areContentsTheSame(oldItem: Village, newItem: Village): Boolean =
            oldItem == newItem

    })

    fun submitListData(it: List<Village>){
        differ.submitList(it)
    }

}