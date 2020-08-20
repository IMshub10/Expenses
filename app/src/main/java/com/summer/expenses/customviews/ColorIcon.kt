/*
 * Copyright (c) 2018.
 *
 * This file is part of MoneyWallet.
 *
 * MoneyWallet is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * MoneyWallet is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with MoneyWallet.  If not, see <http://www.gnu.org/licenses/>.
 */
package com.summer.expenses.customviews

import android.content.Context
import android.graphics.drawable.Drawable
import android.os.Parcel
import android.os.Parcelable
import android.widget.ImageView
import com.summer.expenses.utils.Utils
import org.json.JSONException
import org.json.JSONObject

/**
 * Created by andrea on 23/01/18.
 */
class ColorIcon(name: String) : Icon() {
    private val mName: String
    var backgroundColor = 0

    @JvmName("getType1")
    fun getType(): Type {
        return Type.COLOR
    }


    @JvmName("apply1")
    fun apply(imageView: ImageView): Boolean {
        val drawable = drawable
        imageView!!.setImageDrawable(drawable)
        return true
    }



    val drawable: Drawable
        get() {
            backgroundColor = setBackgroundColor(mName.toLowerCase())
            return TextDrawable.builder()
                .beginConfig()
                .width(80)
                .height(80)
                .textColor(Utils.getBestColor(backgroundColor))
                .endConfig()
                .buildRound(mName, backgroundColor)
        }
    override val type: Type
        get() = TODO("Not yet implemented")

    override fun writeJSON(jsonObject: JSONObject?) {

    }

    override fun getDrawable(context: Context?): Drawable? {
        return drawable
    }

    override fun apply(imageView: ImageView?): Boolean {
        return  false
    }

    override fun writeToParcel(dest: Parcel, flags: Int) {}
    private fun setBackgroundColor(text: String): Int {
        return when (text) {
            "a" -> -0x57aad7
            "b" -> -0x709680
            "c" -> -0x967088
            "d" -> -0xb07dc0
            "e" -> -0xd6576c
            "f" -> -0x57d6c3
            "g" -> -0x56769d
            "h" -> -0xa6c2be
            "i" -> -0xc2bda7
            "j" -> -0xabc2a7
            "k" -> -0xbda6c3
            "l" -> -0x897f77
            "m" -> -0xd07d76
            "n" -> -0x75d0ab
            "o" -> -0x9c552d
            "p" -> -0xeb997d
            "q" -> -0xe02201
            "r" -> -0x325bbf
            "s" -> -0x663367
            "t" -> -0x3e34
            "u" -> -0x8cb06a
            "v" -> -0xffcc9a
            "w" -> -0x5b00
            "x" -> -0xff8b8c
            "y" -> -0x2800
            "z" -> -0x69874a
            else -> -0xf0f0f1
        }
    }

    companion object {
        private const val COLOR = "color"
        private const val NAME = "name"
        val CREATOR: Parcelable.Creator<ColorIcon?> = object : Parcelable.Creator<ColorIcon?> {
            override fun createFromParcel(source: Parcel): ColorIcon? {
                return null
            }

            override fun newArray(size: Int): Array<ColorIcon?> {
                return arrayOfNulls(size)
            }
        }
    }

    init {
        mName = name.substring(0, 1).toUpperCase()
    }
}