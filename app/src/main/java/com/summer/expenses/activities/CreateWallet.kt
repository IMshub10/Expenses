package com.summer.expenses.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.summer.expenses.R
import com.summer.expenses.databinding.ActivityCreateWalletBinding
import com.summer.expenses.databinding.ActivityMainBinding
import com.summer.expenses.utils.Utils
import com.summer.expenses.viewmodels.CreateWalletViewModel
import com.summer.expenses.viewmodels.MainViewModel
import kotlinx.android.synthetic.main.activity_create_wallet.*

class CreateWallet : AppCompatActivity() {

    private  var createWalletViewModel:CreateWalletViewModel? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_create_wallet)
         createWalletViewModel = ViewModelProvider.AndroidViewModelFactory(application)
            .create(CreateWalletViewModel(application)::class.java)

        val binding = DataBindingUtil.setContentView<ActivityCreateWalletBinding>(
            this@CreateWallet,
            R.layout.activity_create_wallet
        )
            .apply {
                this.lifecycleOwner = this@CreateWallet
                this.viewModel = createWalletViewModel
            }
        binding.executePendingBindings()
        setSupportActionBar(binding.toolbarCreateWallet)

        createWalletViewModel!!.nameEdittext.observe(this, Observer {
            if (it.trim().isNotEmpty()){
                createWalletViewModel!!.changeNameIcon(it)
            }else{
                createWalletViewModel!!.changeNameIcon("?")
            }
        })
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.createwallet_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == R.id.check) {
            if (createWalletViewModel!!.textsAreEmpty()){
                Utils.showToast(this,"Enter Text")
            }else{
                createWalletViewModel!!.insert()
            }
            return true
        }
        return super.onOptionsItemSelected(item)
    }
}