package com.candra.moneyapp.ui.activity

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import androidx.activity.viewModels
import com.candra.moneyapp.R
import com.candra.moneyapp.databinding.ActivitySplashScreenBinding
import com.candra.moneyapp.helper.Constant
import com.candra.moneyapp.ui.activity.main.MainActivity
import com.candra.moneyapp.ui.activity.main.VillageActivity
import com.candra.moneyapp.ui.viewmodel.MainViewModel

@SuppressLint("CustomSplashScreen")
class SplashScreen : AppCompatActivity(){

    private lateinit var splashScreenBinding: ActivitySplashScreenBinding
    private val mainViewModel by viewModels<MainViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        splashScreenBinding = ActivitySplashScreenBinding.inflate(layoutInflater)
        setContentView(splashScreenBinding.root)
        supportActionBar?.hide()
        splashScreenBinding.textTitleSplashscreen.text = Constant.TITLE_SPLASHSCREEN.uppercase()
        checkUserLoginOrNot()
    }

    private fun checkUserLoginOrNot(){
        Handler(mainLooper).postDelayed({
            mainViewModel.getUserLogin(this@SplashScreen).observe(this){user ->
                val targetActivity = if (user.isLogin == true) VillageActivity::class.java else MainActivity::class.java
                startActivity(Intent(this@SplashScreen,targetActivity)).also {
                    finish()
                }
            }
        },Constant.TIME_OF_DELAYED)
    }
}