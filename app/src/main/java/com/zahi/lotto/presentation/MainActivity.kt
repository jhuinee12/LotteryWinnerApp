package com.zahi.lotto.presentation

import android.util.Log
import android.view.MenuItem
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
                        navController.navigate(R.id.WinnerFragment)
                    }
                    R.id.action_favorite -> {
                        navController.navigate(R.id.RecommendedFragment)
                    }
                }
//                navController.popBackStack()
                return@setOnItemSelectedListener true
            }
        }
    }

    override fun initViewModel() { }

    fun changeToolbar(
        isVisible: Boolean = true,
        title: String = ""
    ) {
        binding.layoutToolbar.visibility =
            if (isVisible) {
                View.VISIBLE
            } else {
                View.GONE
            }

        binding.toolbar.title = title
        setSupportActionBar(binding.toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setHomeButtonEnabled(true)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            android.R.id.home -> navController.navigateUp()
            else -> true
        }
    }
}