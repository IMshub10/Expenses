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

import android.annotation.SuppressLint
import android.content.Context
import android.content.res.ColorStateList
import android.graphics.Color
import android.graphics.PorterDuff
import android.graphics.drawable.Drawable
import android.graphics.drawable.RippleDrawable
import android.os.Build
import android.widget.*
import androidx.annotation.CheckResult
import androidx.annotation.ColorInt
import androidx.appcompat.widget.SwitchCompat
import androidx.core.content.ContextCompat
import androidx.core.graphics.drawable.DrawableCompat
import com.summer.expenses.R
import com.summer.expenses.utils.Utils

/**
 * Created by andrea on 11/04/18.
 */
/*package-local*/
internal object TintHelper {
    private val COLOR_BUTTON_DISABLED_LIGHT = Color.parseColor("#1F000000")
    private val COLOR_BUTTON_DISABLED_DARK = Color.parseColor("#1F000000")
    private val COLOR_CONTROL_DISABLED_DARK = Color.parseColor("#43000000")
    private val COLOR_CONTROL_DISABLED_LIGHT = Color.parseColor("#4DFFFFFF")
    private val COLOR_CONTROL_NORMAL_DARK = Color.parseColor("#B3FFFFFF")
    private val COLOR_CONTROL_NORMAL_LIGHT = Color.parseColor("#8A000000")
    private val COLOR_SWITCH_THUMB_DISABLED_LIGHT = Color.parseColor("#FFBDBDBD")
    private val COLOR_SWITCH_THUMB_DISABLED_DARK = Color.parseColor("#FF424242")
    private val COLOR_SWITCH_THUMB_NORMAL_LIGHT = Color.parseColor("#FFFAFAFA")
    private val COLOR_SWITCH_THUMB_NORMAL_DARK = Color.parseColor("#FFBDBDBD")
    private val COLOR_SWITCH_TRACK_DISABLED_LIGHT = Color.parseColor("#1F000000")
    private val COLOR_SWITCH_TRACK_DISABLED_DARK = Color.parseColor("#1AFFFFFF")
    private val COLOR_SWITCH_TRACK_NORMAL_LIGHT = Color.parseColor("#43000000")
    private val COLOR_SWITCH_TRACK_NORMAL_DARK = Color.parseColor("#4DFFFFFF")

    // This returns a NEW Drawable because of the mutate() call. The mutate() call is necessary because Drawables with the same resource have shared states otherwise.
    @CheckResult
    fun createTintedDrawable(drawable: Drawable?, @ColorInt color: Int): Drawable? {
        var drawable: Drawable? = drawable ?: return null
        drawable = DrawableCompat.wrap(drawable!!.mutate())
        DrawableCompat.setTintMode(drawable, PorterDuff.Mode.SRC_IN)
        DrawableCompat.setTint(drawable, color)
        return drawable
    }

    @CheckResult
    fun createTintedDrawable(drawable: Drawable?, colorStateList: ColorStateList): Drawable? {
        var drawable: Drawable? = drawable ?: return null
        drawable = DrawableCompat.wrap(drawable!!.mutate())
        DrawableCompat.setTintList(drawable, colorStateList)
        return drawable
    }

    private fun modifySwitchDrawable(
        context: Context,
        from: Drawable,
        @ColorInt tint: Int,
        thumb: Boolean,
        useDarker: Boolean
    ): Drawable? {
        var tint = tint
        if (useDarker) {
            tint = Util.shiftColor(tint, 1.1f)
        }
        tint = Util.adjustAlpha(tint, if (thumb) 1.0f else 0.5f)
        val disabled: Int
        val normal: Int
        if (thumb) {
            disabled =
                if (useDarker) COLOR_SWITCH_THUMB_DISABLED_DARK else COLOR_SWITCH_THUMB_DISABLED_LIGHT
            normal =
                if (useDarker) COLOR_SWITCH_THUMB_NORMAL_DARK else COLOR_SWITCH_THUMB_NORMAL_LIGHT
        } else {
            disabled =
                if (useDarker) COLOR_SWITCH_TRACK_DISABLED_DARK else COLOR_SWITCH_TRACK_DISABLED_LIGHT
            normal =
                if (useDarker) COLOR_SWITCH_TRACK_NORMAL_DARK else COLOR_SWITCH_TRACK_NORMAL_LIGHT
        }
        val stateList = ColorStateList(
            arrayOf(
                intArrayOf(-android.R.attr.state_enabled),
                intArrayOf(
                    android.R.attr.state_enabled,
                    -android.R.attr.state_activated,
                    -android.R.attr.state_checked
                ),
                intArrayOf(android.R.attr.state_enabled, android.R.attr.state_activated),
                intArrayOf(android.R.attr.state_enabled, android.R.attr.state_checked)
            ), intArrayOf(disabled, normal, tint, tint)
        )
        return createTintedDrawable(from, stateList)
    }

    /*package-local*/
    fun applyTint(
        button: Button,
        @ColorInt color: Int,
        @ColorInt rippleColor: Int,
        useDarker: Boolean
    ) {
        val disabled = if (useDarker) COLOR_BUTTON_DISABLED_DARK else COLOR_BUTTON_DISABLED_LIGHT
        val pressed = Util.shiftColor(color, if (useDarker) 0.9f else 1.1f)
        val activated = Util.shiftColor(color, if (useDarker) 1.1f else 0.9f)
        val colorStateList = ColorStateList(
            arrayOf(
                intArrayOf(-android.R.attr.state_enabled),
                intArrayOf(android.R.attr.state_enabled)
            ), intArrayOf(disabled, color)
        )
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP && button.background is RippleDrawable) {
            val rippleDrawable = button.background as RippleDrawable
            rippleDrawable.setColor(ColorStateList.valueOf(rippleColor))
        }
        var drawable = button.background
        if (drawable != null) {
            drawable = createTintedDrawable(drawable, colorStateList)
            Utils.setBackgroundCompat(button, drawable)
        }
    }

    /*package-local*/
    fun applyTint(checkBox: CheckBox, @ColorInt color: Int, useDarker: Boolean) {
        val context = checkBox.context
        val stateList = ColorStateList(
            arrayOf(
                intArrayOf(-android.R.attr.state_enabled),
                intArrayOf(android.R.attr.state_enabled, -android.R.attr.state_checked),
                intArrayOf(android.R.attr.state_enabled, android.R.attr.state_checked)
            ), intArrayOf(
                Util.stripAlpha(if (useDarker) COLOR_CONTROL_DISABLED_DARK else COLOR_CONTROL_DISABLED_LIGHT),
                if (useDarker) COLOR_CONTROL_NORMAL_DARK else COLOR_CONTROL_NORMAL_LIGHT,
                color
            )
        )
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            checkBox.buttonTintList = stateList
        } else {
            @SuppressLint("PrivateResource") val resource = R.drawable.abc_btn_radio_material
            var drawable = ContextCompat.getDrawable(context, resource)
            drawable = createTintedDrawable(drawable, stateList)
            checkBox.buttonDrawable = drawable
        }
    }

    /*package-local*/
    fun applyTint(radioButton: RadioButton, @ColorInt color: Int, useDarker: Boolean) {
        val context = radioButton.context
        val stateList = ColorStateList(
            arrayOf(
                intArrayOf(-android.R.attr.state_enabled),
                intArrayOf(android.R.attr.state_enabled, -android.R.attr.state_checked),
                intArrayOf(android.R.attr.state_enabled, android.R.attr.state_checked)
            ), intArrayOf(
                Util.stripAlpha(if (useDarker) COLOR_CONTROL_DISABLED_DARK else COLOR_CONTROL_DISABLED_LIGHT),
                if (useDarker) COLOR_CONTROL_NORMAL_DARK else COLOR_CONTROL_NORMAL_LIGHT,
                color
            )
        )
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            radioButton.buttonTintList = stateList
        } else {
            @SuppressLint("PrivateResource") val resource = R.drawable.abc_btn_radio_material
            var drawable = ContextCompat.getDrawable(context, resource)
            drawable = createTintedDrawable(drawable, stateList)
            radioButton.buttonDrawable = drawable
        }
    }

    /*package-local*/
    fun applyTint(switchCompat: SwitchCompat, @ColorInt color: Int, useDarker: Boolean) {
        if (switchCompat.trackDrawable != null) {
            switchCompat.trackDrawable = modifySwitchDrawable(
                switchCompat.context,
                switchCompat.trackDrawable,
                color,
                false,
                useDarker
            )
        }
        if (switchCompat.thumbDrawable != null) {
            switchCompat.thumbDrawable = modifySwitchDrawable(
                switchCompat.context,
                switchCompat.thumbDrawable,
                color,
                true,
                useDarker
            )
        }
    }

    /*package-local*/
    fun applyTint(seekBar: SeekBar, @ColorInt color: Int, useDarker: Boolean) {
        val colorStateList = ColorStateList(
            arrayOf(
                intArrayOf(-android.R.attr.state_enabled),
                intArrayOf(android.R.attr.state_enabled)
            ), intArrayOf(
                if (useDarker) COLOR_CONTROL_DISABLED_DARK else COLOR_CONTROL_DISABLED_LIGHT,
                color
            )
        )
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            seekBar.thumbTintList = colorStateList
            seekBar.progressTintList = colorStateList
        } else {
            val progressDrawable = createTintedDrawable(seekBar.progressDrawable, colorStateList)
            seekBar.progressDrawable = progressDrawable
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
                val thumbDrawable = createTintedDrawable(seekBar.thumb, colorStateList)
                seekBar.thumb = thumbDrawable
            }
        }
    }

    /*package-local*/
    fun setCursorTint(editText: EditText, @ColorInt color: Int) {
        try {
            val fCursorDrawableRes = TextView::class.java.getDeclaredField("mCursorDrawableRes")
            fCursorDrawableRes.isAccessible = true
            val mCursorDrawableRes = fCursorDrawableRes.getInt(editText)
            val fEditor = TextView::class.java.getDeclaredField("mEditor")
            fEditor.isAccessible = true
            val editor = fEditor[editText]
            val clazz: Class<*> = editor.javaClass
            val fCursorDrawable = clazz.getDeclaredField("mCursorDrawable")
            fCursorDrawable.isAccessible = true
            val drawables = arrayOfNulls<Drawable>(2)
            drawables[0] = ContextCompat.getDrawable(editText.context, mCursorDrawableRes)
            drawables[0] = createTintedDrawable(drawables[0], color)
            drawables[1] = ContextCompat.getDrawable(editText.context, mCursorDrawableRes)
            drawables[1] = createTintedDrawable(drawables[1], color)
            fCursorDrawable[editor] = drawables
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }
}