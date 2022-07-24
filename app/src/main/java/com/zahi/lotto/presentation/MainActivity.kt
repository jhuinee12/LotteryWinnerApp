package com.zahi.lotto.presentation

import android.view.View
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import com.zahi.lotto.R
import com.zahi.lotto.databinding.ActivityMainBinding
import com.zahi.lotto.base.BaseActivity

class MainActivity : BaseActivity<ActivityMainBinding>(R.layout.activity_main) {
    private val navHostFragment: NavHostFragment by lazy {
        (supportFragmentManager.findFragmentById(R.id.fragmentContainerView) as NavHostFragment)
    }
    private val navController: NavController by lazy { navHostFragment.navController }

    override fun initView() {
        binding.apply {
            this.bottomNav.setOnItemSelectedListener {
                when (it.itemId) {
                    R.id.action_home -> {
                        navController.navigate(R.id.actionHomeFragment)
                    }
                    R.id.action_winner -> {
                        navController.navigate(R.id.actionWinnerFragment)
                    }
                    R.id.action_recommended -> {
                        navController.navigate(R.id.actionRecommendedFragment)
                    }
                }
                return@setOnItemSelectedListener true
            }
        }
    }

    override fun initViewModel() { }
}