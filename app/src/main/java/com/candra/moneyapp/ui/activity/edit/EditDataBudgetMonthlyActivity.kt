package com.candra.moneyapp.ui.activity.edit

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.MenuItem
import android.view.View
import androidx.activity.viewModels
import androidx.lifecycle.lifecycleScope
import com.candra.moneyapp.R
import com.candra.moneyapp.data.BudgetChanneled
import com.candra.moneyapp.databinding.ActivityEditDataBudgetMonthlyBinding
import com.candra.moneyapp.helper.Constant
import com.candra.moneyapp.helper.Utils
import com.candra.moneyapp.ui.activity.main.HomeActivity
import com.candra.moneyapp.ui.viewmodel.ChanneledBudgetViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class EditDataBudgetMonthlyActivity : AppCompatActivity() {
    private var idVillage = ""
    private var position = 0
    private lateinit var binding: ActivityEditDataBudgetMonthlyBinding
    private val editViewModel by viewModels<ChanneledBudgetViewModel>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityEditDataBudgetMonthlyBinding.inflate(layoutInflater)
        setContentView(binding.root)
        position = intent.getIntExtra(Constant.EXTRA_POSITION,0)
        actionToButtonConfirmation()
        receivedData()
        intent.getStringExtra(Constant.EXTRA_ID_VILLAGE)?.let {
            idVillage = it
            binding.edtIdVillage.setText(idVillage)
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {

        if (item.itemId == android.R.id.home){
            finish()
        }
        return super.onOptionsItemSelected(item)
    }

    private fun setToolbar(subTitle: String){
        supportActionBar?.apply {
            title = getString(R.string.app_name)
            subtitle = subTitle
            setDisplayHomeAsUpEnabled(true)
        }
    }

   private fun actionToButtonConfirmation(){
       binding.apply {
           setToolbar(Constant.TEXT_ADD_DATA)
           btnConfirmation.apply {
               text = Constant.TEXT_ADD_DATA
               intent.getStringExtra(Constant.EXTRA_ID_VILLAGE)?.let {idVillage ->
                   edtIdVillage.setText(idVillage)
                   setOnClickListener {
                       val idVillage2 = tillIdVillage.editText?.text.toString()
                       val channeled = tillChanneled.editText?.text.toString()
                       val rangeRpm = tillRangeKpm.editText?.text.toString()
                       val month = tillMonth.editText?.text.toString()

                       if (channeled.isEmpty() || rangeRpm.isEmpty() || month.isEmpty())
                       {
                           Utils.makeToast(getString(R.string.toast_dialog_not_empty),this@EditDataBudgetMonthlyActivity)
                       }else{
                           val convertMonth = Utils.convertStringToMonth(month)
                           val channeledBudget = BudgetChanneled(0,idVillage,channeled,rangeRpm,convertMonth)
                           if (position == 2){
                               lifecycleScope.launch { editViewModel.insertBudgetChanneled(channeledBudget).also {
                                   Intent(this@EditDataBudgetMonthlyActivity,HomeActivity::class.java).apply {
                                       putExtra(Constant.EXTRA_POSITION,3)
                                       putExtra(Constant.EXTRA_ID_VILLAGE,idVillage2)
                                   }.also { startActivity(it) }
                                   finish()
                               }
                               }
                               Log.d("DATA", "actionToButtonConfirmation: ${channeledBudget.toString()}")
                           }
                       }
                   }
               }
           }
       }
   }

    private fun receivedData(){
        if (position == 1){
            setToolbar(getString(R.string.edit_data_village))
            intent.extras?.getParcelable<BudgetChanneled>(Constant.EXTRA_CHANNELED)?.let { channeled ->
                Log.d("TAG", "receivedData: ${channeled.id_village},${channeled.channeled_budget}")
                binding.apply {
                    tillIdVillage.visibility = View.GONE
                    edtChanneled.setText(channeled.channeled_budget)
                    edtRangeKpm.setText(channeled.range_budget)
                    edtMonth.setText(Utils.convertMonthToString(channeled.month))
                    btnConfirmation.apply {
                        text = getString(R.string.edit_data_village)
                        setOnClickListener {
                            val totalBudget = tillChanneled.editText?.text.toString().lowercase()
                            val rangeKpm = tillRangeKpm.editText?.text.toString().lowercase()
                            val month = tillMonth.editText?.text.toString().lowercase()
                            val convertMonth = Utils.convertStringToMonth(month)
                            if (totalBudget.isEmpty() || rangeKpm.isEmpty() || month.isEmpty()){
                                Utils.makeToast(getString(R.string.toast_dialog_not_empty),this@EditDataBudgetMonthlyActivity)
                            }else{
                                val budgetChanneled = BudgetChanneled(channeled.id,channeled.id_village,totalBudget,rangeKpm,convertMonth)
                                lifecycleScope.launch {
                                    editViewModel.updateBudgetChanneled(
                                        budgetChanneled
                                    ).also { finish() }
                                    Log.d("TAG", "receivedData: $budgetChanneled")
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}