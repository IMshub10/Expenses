package com.summer.expenses.room.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "wallet_table")
class Wallet(
    @field:PrimaryKey
    var key: String,
    val name:String,
    val currency: String,
    val startMoney: String,
    val remainingMoney: String
)