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
package com.summer.expenses.customviews.text

/**
 * Created by andrea on 01/05/18.
 */
enum class Mode(  /*package-local*/
    var mMode: Int
) {
    STANDARD(0), FLOATING_LABEL(1);

    val mode: Mode?
        get() = getMode(mMode)

    companion object {
        fun getMode(value: Int): Mode? {
            when (value) {
                0 -> return STANDARD
                1 -> return FLOATING_LABEL
            }
            return null
        }
    }
}