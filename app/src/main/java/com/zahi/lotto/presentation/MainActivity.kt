package com.zahi.lotto.presentation

import android.view.MenuItem
import androidx.core.view.isVisible
import com.zahi.lotto.R
import com.zahi.lotto.databinding.ActivityMainBinding
import com.zahi.lotto.base.BaseActivity

class MainActivity : BaseActivity<ActivityMainBinding>(R.layout.activity_main) {

    override fun initView() {
        setSupportActionBar(binding.toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setHomeButtonEnabled(true)

        binding.apply {
            changeToolbar()
        }
    }

    override fun initViewModel() { }

    fun changeToolbar(
        title: String = "",
        isVisible: Boolean = false
    ) {
        binding.toolbar.isVisible = isVisible
        binding.toolbar.title = title
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        onBackPressed()
        return true
    }
}