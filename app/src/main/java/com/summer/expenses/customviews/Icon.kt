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
import android.os.Parcelable
import android.widget.ImageView
import org.json.JSONException
import org.json.JSONObject

/**
 * Created by andrea on 23/01/18.
 */
abstract class Icon  /*package-local*/
internal constructor() : Parcelable {
    enum class Type(private val mType: String) {
        RESOURCE("resource"), COLOR("color");

        override fun toString(): String {
            return mType
        }

        companion object {
            operator fun get(type: String?): Type? {
                if (type != null) {
                    when (type) {
                        "resource" -> return RESOURCE
                        "color" -> return COLOR
                    }
                }
                return null
            }
        }
    }

    abstract val type: Type
    @Throws(JSONException::class)
    protected abstract fun writeJSON(jsonObject: JSONObject?)
    abstract fun getDrawable(context: Context?): Drawable?
    abstract fun apply(imageView: ImageView?): Boolean
    override fun toString(): String {
        try {
            val jsonObject = JSONObject()
            jsonObject.put(TYPE, type.toString())
            writeJSON(jsonObject)
            return jsonObject.toString()
        } catch (e: JSONException) {
            e.printStackTrace()
        }
        return null.toString()
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object {
        private const val TYPE = "type"
        @Throws(JSONException::class)
        fun getType(jsonObject: JSONObject): Type? {
            return Type[jsonObject.getString(TYPE)]
        }

        @JvmStatic
        fun getDrawableId(context: Context, resource: String?): Int {
            return context.resources.getIdentifier(resource, "drawable", context.packageName)
        }
    }
}