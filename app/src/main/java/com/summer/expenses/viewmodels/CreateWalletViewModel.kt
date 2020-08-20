package com.summer.expenses.viewmodels

import android.app.Application
import android.graphics.drawable.Drawable
import androidx.annotation.DrawableRes
import androidx.databinding.BindingAdapter
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.summer.expenses.customviews.ColorIcon
import com.summer.expenses.room.model.Wallet
import com.summer.expenses.room.repositories.WalletRepository
import de.hdodenhof.circleimageview.CircleImageView
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.util.*


class CreateWalletViewModel(application: Application) : AndroidViewModel(application) {
    val nameEdittext = MutableLiveData<String>()
    val initialAmountEdittext = MutableLiveData<String>()
    val noteEdittext = MutableLiveData<String>()

    private val nameIcon = MutableLiveData<Drawable>()
    val nameicon: LiveData<Drawable>
        get() = nameIcon


    var walletRepository: WalletRepository? = null


    init {
        walletRepository = WalletRepository(application)
        nameEdittext.value = ""
        initialAmountEdittext.value = ""
        noteEdittext.value = ""
        nameIcon.value= ColorIcon(
            "?"
        ).drawable
    }

    fun changeNameIcon(name:String){
        nameIcon.value = ColorIcon(
            name.trim()
        ).drawable
    }

    object DataBindingAdapter {
        @BindingAdapter("myimage")
        @JvmStatic
        fun setImageByRes(imageView: CircleImageView, resId: Drawable) {
            imageView.setImageDrawable(resId)
        }
    }


    fun insert() {
        /*
        CoroutineScope(Dispatchers.IO).launch {
            val id = UUID.randomUUID().toString()
            walletRepository!!.insert(
                Wallet(
                    id,
                    nameEdittext.value.toString(),
                    "₹",
                    initialAmountEdittext.value.toString(),
                    initialAmountEdittext.value.toString()
                )
            )
        }

         */
    }

    fun textsAreEmpty(): Boolean {
        return (nameEdittext.value.toString().isEmpty()
                || initialAmountEdittext.value.toString().isEmpty()
                || noteEdittext.value.toString().isEmpty())

    }

}