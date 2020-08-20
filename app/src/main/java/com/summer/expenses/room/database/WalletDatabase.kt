package com.summer.expenses.room.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import com.summer.expenses.room.dao.WalletDao
import com.summer.expenses.room.model.Wallet

@Database(
    entities = [Wallet::class],
    version = 1,
    exportSchema = false
)
public  abstract class WalletDatabase : RoomDatabase() {
    abstract fun walletDao(): WalletDao?

    companion object {
        private var instance: WalletDatabase? = null
        @Synchronized
        public fun getInstance(context: Context): WalletDatabase? {
            if (instance == null) {
                instance = Room.databaseBuilder(
                    context,
                    WalletDatabase::class.java, "my_wallet_table_database"
                )
                    .fallbackToDestructiveMigration()
                    .addCallback(roomCallback)
                    .build()
            }
            return instance
        }

        private val roomCallback: Callback = object : Callback() {
            override fun onCreate(db: SupportSQLiteDatabase) {
                super.onCreate(db)
            }
        }
    }
}