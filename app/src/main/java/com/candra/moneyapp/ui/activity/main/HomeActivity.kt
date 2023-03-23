package com.candra.moneyapp.ui.activity.main

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import androidx.appcompat.app.AlertDialog
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.candra.moneyapp.R
import com.candra.moneyapp.data.BudgetChanneled
import com.candra.moneyapp.databinding.ActivityHomeBinding
import com.candra.moneyapp.helper.Constant
import com.candra.moneyapp.helper.Utils
import com.candra.moneyapp.ui.activity.edit.EditDataBudgetMonthlyActivity
import com.candra.moneyapp.ui.adapter.AdapterMonth
import com.candra.moneyapp.ui.viewmodel.ChanneledBudgetViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class HomeActivity : AppCompatActivity() {

    private lateinit var homeBinding: ActivityHomeBinding
    private val homeAdapter by lazy { AdapterMonth(::onClick,::onDelete) }
    private val homeViewModel by viewModels<ChanneledBudgetViewModel>()
    private var idVillage = ""
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        homeBinding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(homeBinding.root)
        onAddData()
        setAdapter()
        intent.getStringExtra(Constant.EXTRA_ID_VILLAGE)?.let { idVillage ->
            showAllData("%${idVillage}%")
            homeBinding.tvIdVillage.text = idVillage
        }
//        showAllDataFromSessionHomeActivity()
        deleteAllData()
        supportActionBar?.apply {
            title = getString(R.string.app_name)
            subtitle = Constant.HOME_DATA
        }
    }

    private fun setAdapter(){
        homeBinding.rvListData.apply {
            layoutManager = LinearLayoutManager(this@HomeActivity)
            adapter = homeAdapter
        }
    }

    private fun enterDataToViewRecyclerview(it: List<BudgetChanneled>){
        if (it.isEmpty()){
            showTextView(isShow = true)
        }else{
            homeBinding.apply {
                val data = it.sumOf { it.channeled_budget.toDouble() }
                tvTotalBudget.text = Utils.convertToRupiah(data)
            }
            homeAdapter.submitListData(it)
            showTextView(isShow = false)
        }
    }


    private fun showAllData(it: String){
        homeViewModel.showAllDataFromIdVillage(it).observe(this,this::enterDataToViewRecyclerview)
    }


    private fun deleteAllData(){
        homeBinding.btnDeleteAll.setOnClickListener {
            lifecycleScope.launch {
                homeViewModel.deleteAll()
            }
        }
    }

    private fun onClick(data: BudgetChanneled){
        Intent(this@HomeActivity,EditDataBudgetMonthlyActivity::class.java).apply {
            putExtra(Constant.EXTRA_POSITION,1)
            putExtra(Constant.EXTRA_CHANNELED,data)
            putExtra(Constant.EXTRA_ID_VILLAGE,idVillage)
        }.also { startActivity(it) }
    }

    private fun showTextView(isShow: Boolean){
        homeBinding.apply {
            tvNotFound.visibility = if (isShow) View.VISIBLE else View.GONE
            rvListData.visibility = if (isShow) View.GONE else View.VISIBLE
            btnDeleteAll.visibility = if (isShow) View.GONE else View.VISIBLE
            cvTotal.visibility = if (isShow) View.GONE else View.VISIBLE
        }
    }

    private fun onDelete(data: BudgetChanneled){
        val builder = AlertDialog.Builder(this@HomeActivity)
        builder.apply {
            setPositiveButton(getString(R.string.yes_delete_data)){_,_ ->
                lifecycleScope.launch {
                    homeViewModel.deleteBudgetChanneled(data)
                    homeAdapter.notifyItemRemoved(data.id)
                    Utils.makeToast(getString(R.string.toast_delete_success),this@HomeActivity)
                }
            }
            setNegativeButton(getString(R.string.no_delete_data)){_,_ ->
                Utils.makeToast(getString(R.string.toast_delete_failed),this@HomeActivity)
            }
            setTitle(getString(R.string.title_dialog_delete_data,data.id_village))
            create().show()
        }
    }

    private fun onAddData(){
        homeBinding.addDataBlt.setOnClickListener{
            val position = intent.getIntExtra(Constant.EXTRA_POSITION,0)
            if (position == 3){
                intent.getStringExtra(Constant.EXTRA_ID_VILLAGE).let { idVillage ->
                    Intent(this@HomeActivity,EditDataBudgetMonthlyActivity::class.java).apply {
                        putExtra(Constant.EXTRA_ID_VILLAGE, idVillage)
                        putExtra(Constant.EXTRA_POSITION,2)
                    }.also { startActivity(it) }
                    finish()
                }
            }
        }

    }
}