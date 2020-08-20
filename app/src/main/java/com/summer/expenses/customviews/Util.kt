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

import android.graphics.Color
import androidx.annotation.ColorInt
import androidx.annotation.FloatRange

/**
 * Created by andrea on 10/04/18.
 */
/*package-local*/
internal object Util {
    @ColorInt /*package-local*/    fun adjustAlpha(@ColorInt color: Int, factor: Float): Int {
        val alpha = Math.round(Color.alpha(color) * factor)
        val red = Color.red(color)
        val green = Color.green(color)
        val blue = Color.blue(color)
        return Color.argb(alpha, red, green, blue)
    }

    @ColorInt /*package-local*/    fun shiftColor(
        @ColorInt color: Int,
        @FloatRange(from = 0.0, to = 2.0) by: Float
    ): Int {
        if (by == 1f) return color
        val hsv = FloatArray(3)
        Color.colorToHSV(color, hsv)
        hsv[2] *= by // value component
        return Color.HSVToColor(hsv)
    }

    @ColorInt /*package-local*/    fun darkenColor(@ColorInt color: Int): Int {
        return shiftColor(color, 0.9f)
    }

    /*package-local*/
    fun isColorLight(@ColorInt color: Int): Boolean {
        if (color == Color.BLACK) {
            return false
        } else if (color == Color.WHITE || color == Color.TRANSPARENT) {
            return true
        }
        return 1 - (0.299 * Color.red(color) + 0.587 * Color.green(color) + 0.114 * Color.blue(color)) / 255 < 0.4
    }

    /*package-local*/
    fun isColorLight(@ColorInt color: Int, @ColorInt bgColor: Int): Boolean {
        return if (Color.alpha(color) < 128) {
            isColorLight(bgColor)
        } else isColorLight(
            color
        )
    }

    /*package-local*/
    fun isColorVisible(@ColorInt color: Int, @ColorInt bgColor: Int): Boolean {
        val redOffset = Math.abs(Color.red(color) - Color.red(bgColor))
        val greenOffset = Math.abs(Color.green(color) - Color.green(bgColor))
        val blueOffset = Math.abs(Color.blue(color) - Color.blue(bgColor))
        return redOffset + greenOffset + blueOffset > 50
    }

    /*package-local*/
    fun stripAlpha(@ColorInt color: Int): Int {
        return Color.rgb(Color.red(color), Color.green(color), Color.blue(color))
    }
}