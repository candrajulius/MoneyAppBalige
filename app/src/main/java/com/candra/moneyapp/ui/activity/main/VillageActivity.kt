package com.candra.moneyapp.ui.activity.main

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.Menu
import android.view.MenuItem
import android.view.View
import androidx.activity.viewModels
import androidx.appcompat.app.AlertDialog
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.candra.moneyapp.R
import com.candra.moneyapp.data.Village
import com.candra.moneyapp.databinding.ActivityVillageBinding
import com.candra.moneyapp.databinding.DialogProfileUserBinding
import com.candra.moneyapp.helper.Constant
import com.candra.moneyapp.helper.Utils
import com.candra.moneyapp.ui.activity.edit.EditActivity
import com.candra.moneyapp.ui.adapter.AdapterRegency
import com.candra.moneyapp.ui.adapter.AdapterVillage
import com.candra.moneyapp.ui.viewmodel.ChanneledBudgetViewModel
import com.candra.moneyapp.ui.viewmodel.MainViewModel
import com.candra.moneyapp.ui.viewmodel.VillageViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class VillageActivity : AppCompatActivity() {

    private lateinit var villageBinding: ActivityVillageBinding
    private val channeledViewModel by viewModels<ChanneledBudgetViewModel>()
    private val villageViewModel by viewModels<VillageViewModel>()
    private val mainViewModel by viewModels<MainViewModel>()
    private val villageAdapter by lazy { AdapterRegency(
        this@VillageActivity,::onClick, ::onDelete) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        villageBinding = ActivityVillageBinding.inflate(layoutInflater)
        setContentView(villageBinding.root)
        actionAddFloatingButton()
        setToolbar()
        setAdapterForVillage()
        showDataSubdistrictForViewModel()
        supportActionBar?.title = Constant.REGENCY.uppercase()
    }

    private fun showAllDataSubdistrict(it: List<Village>)
    {
        if (it.isEmpty()){
            Utils.showTextView(true,villageBinding.txtNotFound,villageBinding.rvList)
        }else{
            villageAdapter.submitListData(it)
            Utils.showTextView(false,villageBinding.txtNotFound,villageBinding.rvList)
        }
    }

    private fun showDataSubdistrictForViewModel(){
        villageViewModel.showAllRegency.observe(this@VillageActivity,this::showAllDataSubdistrict)
    }


    private fun setAdapterForVillage(){
        with(villageBinding){
            rvList.apply {
                layoutManager = LinearLayoutManager(this@VillageActivity)
                adapter = villageAdapter
            }
        }
    }


    private fun actionAddFloatingButton(){
        villageBinding.addBtnSubdistrict.setOnClickListener {
            Intent(this@VillageActivity,EditActivity::class.java).apply {
                putExtra(Constant.EXTRA_POSITION,2)
            }.also { startActivity(it) }
        }
    }

    private fun onClick(data: Village){
        Intent(this@VillageActivity,EditActivity::class.java).apply {
            putExtra(Constant.EXTRA_POSITION,3)
            putExtra(Constant.EXTRA_VILLAGE,data)
        }.also { startActivity(it) }
    }

    private fun onDelete(data: Village)
    {
        val builder = AlertDialog.Builder(this@VillageActivity)
        builder.apply {
            setPositiveButton(getString(R.string.yes_delete_data)){_,_ ->
                lifecycleScope.launch {
                    villageViewModel.deleteVillage(data)
                    channeledViewModel.deleteAllBudgetChanneledByIdVillage(data.id_village)
                    villageAdapter.notifyItemRemoved(data.id)
                    Utils.makeToast(getString(R.string.toast_delete_success),this@VillageActivity)
                }
            }
            setNegativeButton(getString(R.string.no_delete_data)){_,_ ->
                Utils.makeToast(getString(R.string.toast_delete_failed),this@VillageActivity)
            }
            setTitle(getString(R.string.title_dialog_delete_data,data.regency))
            create().show()
        }
    }

    private fun setToolbar(){
        supportActionBar?.apply {
            title = Constant.TITLE_VILLAGE.uppercase()
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu,menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {


        if (item.itemId == R.id.btn_profile){
            showDialogProfile()
        }
        return super.onOptionsItemSelected(item)
    }

    private fun showDialogProfile(){
        mainViewModel.getUserLogin(this@VillageActivity).observe(this){ user ->
            val builder = AlertDialog.Builder(this@VillageActivity).create()
            val view2 = DialogProfileUserBinding.inflate(LayoutInflater.from(this@VillageActivity))
            builder.setView(view2.root)
            view2.apply {
                edtUsername.setText(user.username)
                edtPassword.setText(user.password)
                tvGreeting.text = user.username?.let { Utils.greetingMessage(it) }
                btnLogout.setOnClickListener {
                    lifecycleScope.launch {
                        mainViewModel.logoutUser(this@VillageActivity).also { builder.dismiss() }
                        finish()
                    }
                }
                imgClose.setOnClickListener {
                    builder.dismiss()
                }
            }
            builder.show()
        }
    }
}