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
        Log.d("TAG", "initView: 이곳은 Main")
    }

    override fun initViewModel() { }

    fun changeToolbar(
        isVisible: Boolean = true
    ) {
        binding.layoutToolbar.visibility =
            if (isVisible) {
                View.VISIBLE
            } else {
                View.GONE
            }

        binding.toolbar.title = ""
        setSupportActionBar(binding.toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setHomeButtonEnabled(true)
    }

    fun changeBottomNav(isVisible: Boolean = false) {
        binding.bottomNav.visibility =
            if (isVisible) {
                View.VISIBLE
            } else {
                View.GONE
            }
    }

    private fun refreshCurrentFragment(){
        val id = navController.currentDestination?.id
        navController.popBackStack(id!!,true)
        navController.navigate(id)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            android.R.id.home -> navController.navigateUp()
            else -> true
        }
    }
}