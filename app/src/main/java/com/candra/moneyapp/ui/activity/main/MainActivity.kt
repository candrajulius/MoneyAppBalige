package com.candra.moneyapp.ui.activity.main

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.lifecycle.lifecycleScope
import com.candra.moneyapp.R
import com.candra.moneyapp.data.Village
import com.candra.moneyapp.databinding.ActivityMainBinding
import com.candra.moneyapp.helper.Constant
import com.candra.moneyapp.helper.Utils
import com.candra.moneyapp.ui.viewmodel.MainViewModel
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {

    private lateinit var mainBinding: ActivityMainBinding
    private val mainViewModel by viewModels<MainViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mainBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(mainBinding.root)
        mainBinding.btnLogin.setOnClickListener {
            validationInputUser()
        }
        setToolbar()
    }

    private fun validationInputUser(){
        mainBinding.apply {
            val username = textFieldName.editText?.text.toString().lowercase()
            val password = textYourPassword.editText?.text.toString().lowercase()

            if (username.isEmpty() || password.isEmpty()){
                Utils.makeToast(getString(R.string.password_and_username_empty),this@MainActivity)
            }else if (username != Constant.USERNAME_LOGIN || password != Constant.PASSWORD_LOGIN){
                Utils.makeToast(getString(R.string.passsword_and_username_wrong),this@MainActivity)
            }else{
                lifecycleScope.launch{
                    mainViewModel.loginAccount(this@MainActivity,username,password)
                        .also {
                            startActivity(Intent(this@MainActivity,VillageActivity::class.java))
                        }.also { finish() }
                }
            }
        }
    }

    private fun setToolbar(){
        supportActionBar?.apply {
            subtitle = Constant.TITLE_LOGIN.uppercase()
        }
    }
}