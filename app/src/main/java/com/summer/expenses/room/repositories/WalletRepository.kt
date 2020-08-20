package com.summer.expenses.room.repositories

import android.app.Application
import androidx.lifecycle.LiveData
import com.summer.expenses.room.dao.WalletDao
import com.summer.expenses.room.database.WalletDatabase
import com.summer.expenses.room.model.Wallet

class WalletRepository (application: Application) {
    private val walletDao: WalletDao

    init {
        val chatMessageDatabase =
            WalletDatabase.getInstance(application.applicationContext)
        walletDao = chatMessageDatabase!!.walletDao()!!
    }
    suspend fun insert(wallet: Wallet){
        walletDao.insert(wallet)
    }
    suspend fun update(wallet: Wallet){
        walletDao.update(wallet)
    }
    suspend fun delete(wallet: Wallet){
        walletDao.delete(wallet)
    }
    suspend fun deleteAll(){
        walletDao.deleteAllWallets()
    }
    fun getAllMyChatmessages(): LiveData<List<Wallet>> {
        return walletDao.getAllWallets()
    }
}