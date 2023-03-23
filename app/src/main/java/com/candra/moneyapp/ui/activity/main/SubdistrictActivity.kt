package com.candra.moneyapp.ui.activity.main

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import androidx.activity.viewModels
import androidx.appcompat.app.AlertDialog
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.candra.moneyapp.R
import com.candra.moneyapp.data.Village
import com.candra.moneyapp.databinding.ActivitySubdistrictBinding
import com.candra.moneyapp.helper.Constant
import com.candra.moneyapp.helper.Utils
import com.candra.moneyapp.ui.activity.edit.EditActivity
import com.candra.moneyapp.ui.adapter.AdapterVillage
import com.candra.moneyapp.ui.viewmodel.ChanneledBudgetViewModel
import com.candra.moneyapp.ui.viewmodel.VillageViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class SubdistrictActivity : AppCompatActivity() {
    private lateinit var subsdistrictBinding: ActivitySubdistrictBinding
    private var regency = ""
    private val subsdistrictViewModel by viewModels<VillageViewModel>()
    private val channeledViewModel by viewModels<ChanneledBudgetViewModel>()
    private val subdistrictAdapter by lazy { AdapterVillage(
        this@SubdistrictActivity,::onClick,::onChanged,::onDelete) }
    private val villageViewModel by viewModels<VillageViewModel>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        subsdistrictBinding = ActivitySubdistrictBinding.inflate(layoutInflater)
        setContentView(subsdistrictBinding.root)
        intent.getStringExtra(Constant.EXTRA_VILLAGE)?.let {
            regency = it
        }
        supportActionBar?.apply {
            title = getString(R.string.app_name)
            subtitle = getString(R.string.subdistrict,regency)
            setDisplayHomeAsUpEnabled(true)
        }
        setAdapter()
        showAllData(regency)
        showForData()
        addData()
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == android.R.id.home){
            finish()
        }
        return super.onOptionsItemSelected(item)
    }

    override fun onBackPressed() {
        finish()
    }

    private fun showForData(){
        intent?.getIntExtra(Constant.EXTRA_POSITION,0)?.let {
            if (it == 1){
                showAllData(regency)
            }
        }
    }

    private fun showAllData(it: String){
        subsdistrictViewModel.showAllVillage("%${it}%").observe(this,this::enterData)
    }

    private fun enterData(it: List<Village>){
        if (it.isEmpty()){
            Utils.showTextView(true,subsdistrictBinding.tvNotFound,subsdistrictBinding.rvList)
        }else{
            subdistrictAdapter.submitListData(it)
            Utils.showTextView(false,subsdistrictBinding.tvNotFound,subsdistrictBinding.rvList)
        }
    }

    private fun setAdapter(){
        subsdistrictBinding.rvList.apply {
            layoutManager = LinearLayoutManager(this@SubdistrictActivity)
            adapter = subdistrictAdapter
        }
    }

    private fun addData(){
        subsdistrictBinding.btnAddData.setOnClickListener {
            Intent(this@SubdistrictActivity,EditActivity::class.java).apply{
                putExtra(Constant.EXTRA_POSITION,2)
            }.also { startActivity(it) }
        }
    }

    private fun onClick(data: Village){
        Intent(this@SubdistrictActivity,HomeActivity::class.java).apply {
            putExtra(Constant.EXTRA_POSITION,3)
            putExtra(Constant.EXTRA_ID_VILLAGE,data.id_village)
        }.also { startActivity(it) }
    }

    private fun onChanged(data: Village){
        Intent(this@SubdistrictActivity,EditActivity::class.java).apply {
            putExtra(Constant.EXTRA_POSITION,1)
            putExtra(Constant.EXTRA_VILLAGE,data)
        }.also { startActivity(it) }
    }

    private fun onDelete(data: Village)
    {
        val builder = AlertDialog.Builder(this@SubdistrictActivity)
        builder.apply {
            setPositiveButton(getString(R.string.yes_delete_data)){_,_ ->
                lifecycleScope.launch {
                    villageViewModel.deleteVillage(data)
                    channeledViewModel.deleteAllBudgetChanneledByIdVillage(data.id_village)
                    Utils.makeToast(getString(R.string.toast_delete_success),this@SubdistrictActivity)
                }
            }
            setNegativeButton(getString(R.string.no_delete_data)){_,_ ->
                Utils.makeToast(getString(R.string.toast_delete_failed),this@SubdistrictActivity)
            }
            setCancelable(false)
            setTitle(getString(R.string.title_dialog_delete_data,data.name_village))
            create().show()
        }
    }
}