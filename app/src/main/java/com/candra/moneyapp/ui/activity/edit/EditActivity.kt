package com.candra.moneyapp.ui.activity.edit

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import androidx.activity.viewModels
import androidx.lifecycle.lifecycleScope
import com.candra.moneyapp.R
import com.candra.moneyapp.data.Village
import com.candra.moneyapp.databinding.ActivityEditBinding
import com.candra.moneyapp.helper.Constant
import com.candra.moneyapp.helper.Utils
import com.candra.moneyapp.ui.viewmodel.VillageViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class EditActivity : AppCompatActivity() {
    private lateinit var editBinding: ActivityEditBinding
    private val villageViewModel by viewModels<VillageViewModel>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        editBinding = ActivityEditBinding.inflate(layoutInflater)
        setContentView(editBinding.root)
        receivedDataFromPosition()
    }

    private fun setToolbar(subTitle: String){
        supportActionBar?.apply {
            title = getString(R.string.app_name)
            subtitle = subTitle
            setDisplayHomeAsUpEnabled(true)
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == android.R.id.home){
//             Intent(this@EditActivity,SubdistrictActivity::class.java).apply {
//                 putExtra(Constant.EXTRA_POSITION,1)
//             }.also { startActivity(it) }
            finish()
        }

        return super.onOptionsItemSelected(item)
    }

    override fun onBackPressed() {
        finish()
    }

    private fun receivedDataFromPosition(){
        val position = intent.getIntExtra(Constant.EXTRA_POSITION,0)
        when(position){
            1 -> receivedDataVillage(1)
            2 -> {
                editBinding.btnConfirmation.apply {
                    text = Constant.TEXT_ADD_DATA
                    setOnClickListener {
                        setValidation(2)
                    }
                }
            }
            3 -> receivedDataVillage(3)
        }
    }

    private fun setValidation(position: Int) {
        editBinding.apply {
            val subdistrict = tilRegency.editText?.text.toString().lowercase()
            val idVillage = tillIdVillage.editText?.text.toString().lowercase()
            val nameVillage = tillNameVillage.editText?.text.toString().lowercase()
            val year = tillYear.editText?.text.toString().lowercase()
            val totalBudget = tillTotalBudget.editText?.text.toString().lowercase()
            val totalBudgetJuny = tillTotalBudgetJuni.editText?.text.toString().lowercase()
            val totalBudgetDecember = tillTotalBudgetDes.editText?.text.toString().lowercase()

            if (subdistrict.isEmpty() || idVillage.isEmpty() || nameVillage.isEmpty() || year.isEmpty()
                || totalBudget.isEmpty() || totalBudgetJuny.isEmpty() || totalBudgetDecember.isEmpty())
            {
                Utils.makeToast(getString(R.string.toast_dialog_not_empty),this@EditActivity)
            }else{
                val dataVillage = Village(0,subdistrict,idVillage,nameVillage,totalBudget,
                    year,totalBudgetJuny,totalBudgetDecember)
                if (position == 1){
                    lifecycleScope.launch {
                        villageViewModel.updateVillage(dataVillage)
                        Utils.makeToast(Constant.SUCCESS_CHANGE_DATA,this@EditActivity)
                        setToolbar(Constant.SUCCESS_CHANGE_DATA)
                        finish()
                    }
                }else if (position == 2){
                    lifecycleScope.launch {
                        villageViewModel.insertVillage(village = dataVillage)
                        Utils.makeToast(Constant.SUCCESS_ADD_DATA,this@EditActivity)
                        setToolbar(Constant.SUCCESS_ADD_DATA)
                        finish()
                    }
                }else if (position == 3){
                   tillIdVillage.isEnabled = false
                   tillNameVillage.isEnabled = false
                   tillTotalBudget.isEnabled = false
                   tillTotalBudgetDes.isEnabled = false
                   tillTotalBudgetJuni.isEnabled = false
                   tillYear.isEnabled = false
                   lifecycleScope.launch {
                       villageViewModel.updateVillage(dataVillage)
                       finish()
                   }
                }
            }
        }
    }

    private fun receivedDataVillage(position: Int){
        intent.extras?.getParcelable<Village>(Constant.EXTRA_VILLAGE)?.let { village ->
            editBinding.apply {
                val totalBudgetConvertToDouble = village.total_budget.toDouble()
                val totalBudgetJunyConvertToDouble = village.blt_suskeudes_village_juny.toDouble()
                val totalBudgetDecemberConvertToDouble = village.blt_suskeudes_village_december.toDouble()
                edtRegency.setText(village.regency)
                edtIdVillage.setText(village.id_village)
                edtNameVillage.setText(village.name_village)
                edtTotalBudget.setText(Utils.convertToRupiah(totalBudgetConvertToDouble))
                edtYear.setText(village.year_village)
                edtTotalBudgetJuni.setText(Utils.convertToRupiah(totalBudgetJunyConvertToDouble))
                edtTotalBudgetDesember.setText(Utils.convertToRupiah(totalBudgetDecemberConvertToDouble))
                btnConfirmation.apply {
                    text = getString(R.string.edit_data_village)
                    setOnClickListener {
                        setValidation(position)
                    }
                }
            }
        }
    }

}