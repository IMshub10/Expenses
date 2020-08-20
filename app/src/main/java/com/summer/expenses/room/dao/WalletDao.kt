package com.summer.expenses.room.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import com.summer.expenses.room.model.Wallet

@Dao
interface WalletDao{
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insert(wallet: Wallet)

    @Update
    fun update(wallet: Wallet)

    @Delete
    fun delete(wallet: Wallet)

    @Query("Delete From wallet_table ")
    fun deleteAllWallets()

    @Query("SELECT * FROM wallet_table  ORDER BY name")
    fun getAllWallets(): LiveData<List<Wallet>>
}