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

/**
 * Created by andrea on 13/04/18.
 */
/*package-local*/
internal enum class BackgroundColor  /*package-local*/(private val mValue: Int) {
    COLOR_PRIMARY(0), COLOR_PRIMARY_DARK(1), COLOR_ACCENT(2), COLOR_WINDOW_BACKGROUND(3), COLOR_WINDOW_FOREGROUND(
        4
    );

    /*package-local*/
    fun getColor(theme: ITheme): Int {
        return when (mValue) {
            0 -> theme.colorPrimary
            1 -> theme.colorPrimaryDark
            2 -> theme.colorAccent
            4 -> theme.colorWindowForeground
            else -> theme.colorWindowBackground
        }
    }

    companion object {
        /*package-local*/
        @JvmStatic
        fun getValue(backgroundColor: BackgroundColor): Int {
            return backgroundColor.mValue
        }

        /*package-local*/
        @JvmStatic
        fun fromValue(value: Int): BackgroundColor? {
            return when (value) {
                0 -> COLOR_PRIMARY
                1 -> COLOR_PRIMARY_DARK
                2 -> COLOR_ACCENT
                3 -> COLOR_WINDOW_BACKGROUND
                4 -> COLOR_WINDOW_FOREGROUND
                else -> null
            }
        }
    }
}