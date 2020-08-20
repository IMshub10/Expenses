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
import android.util.AttributeSet
import com.summer.expenses.R
import com.summer.expenses.customviews.BackgroundColor
import com.summer.expenses.customviews.BackgroundColor.Companion.fromValue
import com.summer.expenses.customviews.BackgroundColor.Companion.getValue
import com.summer.expenses.customviews.ThemeEngine.ThemeConsumer
import com.summer.expenses.customviews.text.MaterialEditText

/**
 * Created by andrea on 20/08/18.
 */
class ThemedMaterialEditText : MaterialEditText, ThemeConsumer {
    private var mBackgroundColor: BackgroundColor? = null

    constructor(context: Context) : super(context) {
        initialize(context, null, 0)
    }

    constructor(context: Context, attrs: AttributeSet?) : super(context, attrs) {
        initialize(context, attrs, 0)
    }

    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int) : super(
        context,
        attrs,
        defStyleAttr
    ) {
        initialize(context, attrs, defStyleAttr)
    }

    private fun initialize(context: Context, attrs: AttributeSet?, defStyleAttr: Int) {
        val typedArray = context.obtainStyledAttributes(
            attrs,
            R.styleable.ThemedMaterialEditText,
            defStyleAttr,
            0
        )
        try {
            mBackgroundColor = fromValue(
                typedArray.getInt(
                    R.styleable.ThemedMaterialEditText_theme_backgroundColor,
                    getValue(BackgroundColor.COLOR_WINDOW_FOREGROUND)
                )
            )
        } catch (e: Exception) {
            e.printStackTrace()
        } finally {
            typedArray.recycle()
        }
    }

    override fun onApplyTheme(theme: ITheme?) {
        if (mBackgroundColor != null) {
            val backgroundColor = mBackgroundColor!!.getColor(theme!!)
            setTextColor(theme.getBestTextColor(backgroundColor))
            setFloatingLabelColorNormal(theme.getBestHintColor(backgroundColor))
            setHintTextColor(theme.getBestHintColor(backgroundColor))
            setFloatingLabelColorFocused(theme.colorAccent)
            setLeftIconColorNormal(theme.getBestIconColor(backgroundColor))
            setLeftIconColorFocused(theme.colorAccent)
            setBottomLineColorNormal(theme.getBestIconColor(backgroundColor))
            setBottomLineColorFocused(theme.colorAccent)
            setBottomLineColorError(theme.errorColor)
            TintHelper.setCursorTint(this, theme.colorAccent)
        }
    }
}