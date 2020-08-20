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
import android.content.SharedPreferences
import android.graphics.Color
import android.view.View
import android.view.ViewGroup
import androidx.annotation.UiThread
import androidx.core.graphics.ColorUtils
import java.util.*

/**
 * This class is responsible to dynamically theme the user interface at runtime.
 */
class ThemeEngine private constructor(context: Context) : ITheme {
    private val mPreferences: SharedPreferences
    override val colorPrimary: Int
        get() = noAlpha(mPreferences.getInt(COLOR_PRIMARY, DEFAULT_COLOR_PRIMARY))
    override val colorPrimaryDark: Int
        get() = noAlpha(mPreferences.getInt(COLOR_PRIMARY_DARK, DEFAULT_COLOR_PRIMARY_DARK))
    override val colorAccent: Int
        get() = noAlpha(mPreferences.getInt(COLOR_ACCENT, DEFAULT_COLOR_ACCENT))

    private fun noAlpha(color: Int): Int {
        return ColorUtils.setAlphaComponent(color, 255)
    }

    // the stored value has been manually altered
    override val mode: Mode
        get() = when (mPreferences.getInt(MODE, DEFAULT_MODE.index)) {
            INDEX_MODE_LIGHT -> Mode.LIGHT
            INDEX_MODE_DARK -> Mode.DARK
            INDEX_MODE_DEEP_DARK -> Mode.DEEP_DARK
            else ->                 // the stored value has been manually altered
                Mode.LIGHT
        }
    override val isDark: Boolean
        get() = mode != Mode.LIGHT
    override val textColorPrimary: Int
        get() = DEFAULT_TEXT_COLOR_PRIMARY[mode.index]
    override val textColorSecondary: Int
        get() = DEFAULT_TEXT_COLOR_SECONDARY[mode.index]
    override val textColorPrimaryInverse: Int
        get() = DEFAULT_TEXT_COLOR_PRIMARY_INVERSE[mode.index]
    override val textColorSecondaryInverse: Int
        get() = DEFAULT_TEXT_COLOR_SECONDARY_INVERSE[mode.index]
    override val colorCardBackground: Int
        get() = DEFAULT_COLOR_CARD_BACKGROUND[mode.index]
    override val colorWindowForeground: Int
        get() = DEFAULT_COLOR_WINDOW_FOREGROUND[mode.index]
    override val colorWindowBackground: Int
        get() = DEFAULT_COLOR_WINDOW_BACKGROUND[mode.index]
    override val colorRipple: Int
        get() = DEFAULT_COLOR_RIPPLE[mode.index]
    override val iconColor: Int
        get() = DEFAULT_ICON_COLOR[mode.index]
    override val hintTextColor: Int
        get() = DEFAULT_HINT_TEXT_COLOR[mode.index]
    override val errorColor: Int
        get() = Color.RED
    override val dialogBackgroundColor: Int
        get() = DEFAULT_DIALOG_BACKGROUND_COLOR[mode.index]
    override val drawerBackgroundColor: Int
        get() = DRAWER_BACKGROUND_COLOR[mode.index]
    override val drawerIconColor: Int
        get() = DRAWER_ICON_COLOR[mode.index]
    override val drawerSelectedIconColor: Int
        get() = colorPrimary
    override val drawerTextColor: Int
        get() = DRAWER_TEXT_COLOR[mode.index]
    override val drawerSelectedTextColor: Int
        get() = colorPrimary
    override val drawerSelectedItemColor: Int
        get() = DRAWER_SELECTED_ITEM_COLOR[mode.index]

    override fun getBestColor(background: Int): Int {
        return if (Util.isColorLight(background)) Color.BLACK else Color.WHITE
    }

    override fun getBestTextColor(background: Int): Int {
        val index = if (Util.isColorLight(background)) INDEX_MODE_LIGHT else INDEX_MODE_DARK
        return DEFAULT_TEXT_COLOR_PRIMARY[index]
    }

    override fun getBestHintColor(background: Int): Int {
        val index = if (Util.isColorLight(background)) INDEX_MODE_LIGHT else INDEX_MODE_DARK
        return DEFAULT_HINT_TEXT_COLOR[index]
    }

    override fun getBestIconColor(background: Int): Int {
        val index = if (Util.isColorLight(background)) INDEX_MODE_LIGHT else INDEX_MODE_DARK
        return DEFAULT_ICON_COLOR[index]
    }

    private fun getColorByMode(colorLight: Int, colorDark: Int): Int {
        return when (mPreferences.getInt(
            MODE,
            DEFAULT_MODE.index
        )) {
            INDEX_MODE_LIGHT -> colorLight
            else -> colorDark
        }
    }

    enum class Mode  /*package-local*/(  /*package-local*/val index: Int) {
        LIGHT(INDEX_MODE_LIGHT), DARK(INDEX_MODE_DARK), DEEP_DARK(INDEX_MODE_DEEP_DARK);

    }

    interface ThemeObserver {
        fun onThemeChanged(theme: ITheme?)
    }

    interface ThemeConsumer {
        fun onApplyTheme(theme: ITheme?)
    }

    companion object {
        private const val FILE_NAME = "theme.config"
        private const val COLOR_PRIMARY = "color_primary"
        private const val COLOR_PRIMARY_DARK = "color_primary_dark"
        private const val COLOR_ACCENT = "color_accent"
        private const val MODE = "mode"
        private val DEFAULT_COLOR_PRIMARY = Color.parseColor("#3f51b5")
        private val DEFAULT_COLOR_PRIMARY_DARK = Color.parseColor("#303F9F")
        private val DEFAULT_COLOR_ACCENT = Color.parseColor("#FF4081")
        private val DEFAULT_MODE = Mode.LIGHT
        private val DEFAULT_TEXT_COLOR_PRIMARY = intArrayOf(
            Color.parseColor("#DE000000"),  // OK
            Color.parseColor("#FFFFFFFF"),  // OK
            Color.parseColor("#FFFFFFFF") // OK
        )
        private val DEFAULT_TEXT_COLOR_PRIMARY_INVERSE = intArrayOf(
            Color.parseColor("#DEFFFFFF"),  // OK
            Color.parseColor("#FF000000"),  // OK
            Color.parseColor("#FF000000") // OK
        )
        private val DEFAULT_TEXT_COLOR_SECONDARY = intArrayOf(
            Color.parseColor("#8A000000"),  // OK
            Color.parseColor("#B3FFFFFF"),  // OK
            Color.parseColor("#B3FFFFFF") // OK
        )
        private val DEFAULT_TEXT_COLOR_SECONDARY_INVERSE = intArrayOf(
            Color.parseColor("#8AFFFFFF"),  // OK
            Color.parseColor("#B3000000"),  // OK
            Color.parseColor("#B3000000")
        )
        private val DEFAULT_ICON_COLOR = intArrayOf(
            Color.parseColor("#8A000000"),  // OK
            Color.parseColor("#8AFFFFFF"),  // OK
            Color.parseColor("#8AFFFFFF") // OK
        )
        private val DEFAULT_HINT_TEXT_COLOR = intArrayOf(
            Color.parseColor("#61000000"),  // OK
            Color.parseColor("#80FFFFFF"),  // OK
            Color.parseColor("#80FFFFFF") // OK
        )
        private val DEFAULT_COLOR_CARD_BACKGROUND = intArrayOf(
            Color.parseColor("#FFFFFF"),  // OK
            Color.parseColor("#424242"),  // OK
            Color.parseColor("#424242") // OK
        )
        private val DEFAULT_COLOR_WINDOW_FOREGROUND = intArrayOf(
            Color.parseColor("#FFFFFF"),  // OK
            Color.parseColor("#303030"),  // OK
            Color.parseColor("#000000") // OK
        )
        private val DEFAULT_COLOR_WINDOW_BACKGROUND = intArrayOf(
            Color.parseColor("#FAFAFA"),  // OK
            Color.parseColor("#212121"),  // OK
            Color.parseColor("#212121") // OK
        )
        private val DEFAULT_COLOR_RIPPLE = intArrayOf(
            Color.parseColor("#1f000000"),  // OK
            Color.parseColor("#33ffffff"),  // OK
            Color.parseColor("#33ffffff") // OK
        )
        private val DEFAULT_DIALOG_BACKGROUND_COLOR = intArrayOf(
            Color.parseColor("#FFFFFF"),  // OK
            Color.parseColor("#424242"),  // OK
            Color.parseColor("#424242") // OK
        )
        private val DRAWER_BACKGROUND_COLOR = intArrayOf(
            Color.parseColor("#F9F9F9"),
            Color.parseColor("#303030"),
            Color.parseColor("#303030")
        )
        private val DRAWER_ICON_COLOR = intArrayOf(
            Color.parseColor("#8A000000"),
            Color.parseColor("#8AFFFFFF"),
            Color.parseColor("#8AFFFFFF")
        )
        private val DRAWER_TEXT_COLOR = intArrayOf(
            Color.parseColor("#DE000000"),
            Color.parseColor("#DEFFFFFF"),
            Color.parseColor("#DEFFFFFF")
        )
        private val DRAWER_SELECTED_ITEM_COLOR = intArrayOf(
            Color.parseColor("#E8E8E8"),
            Color.parseColor("#202020"),
            Color.parseColor("#202020")
        )
        private const val INDEX_MODE_LIGHT = 0
        private const val INDEX_MODE_DARK = 1
        private const val INDEX_MODE_DEEP_DARK = 2
        private val mThemeObserverList: MutableList<ThemeObserver> = ArrayList()
        private var sInstance: ThemeEngine? = null
        fun initialize(context: Context) {
            if (sInstance == null) {
                sInstance = ThemeEngine(context)
            }
        }

        fun registerObserver(observer: ThemeObserver) {
            mThemeObserverList.add(observer)
        }

        fun unregisterObserver(observer: ThemeObserver?) {
            mThemeObserverList.remove(observer)
        }

        @UiThread
        fun setColorPrimary(colorPrimary: Int) {
            if (sInstance != null) {
                if (colorPrimary != sInstance!!.colorPrimary) {
                    sInstance!!.mPreferences.edit().putInt(COLOR_PRIMARY, colorPrimary).apply()
                    sInstance!!.mPreferences.edit()
                        .putInt(COLOR_PRIMARY_DARK, Util.darkenColor(colorPrimary)).apply()
                    notifyObservers()
                }
            } else {
                throw RuntimeException("ThemeEngine not initialized!")
            }
        }

        @UiThread
        fun setColorAccent(colorAccent: Int) {
            if (sInstance != null) {
                if (colorAccent != sInstance!!.colorAccent) {
                    sInstance!!.mPreferences.edit().putInt(COLOR_ACCENT, colorAccent).apply()
                    notifyObservers()
                }
            } else {
                throw RuntimeException("ThemeEngine not initialized!")
            }
        }

        @UiThread
        fun setMode(mode: Mode?) {
            if (sInstance != null && mode != null) {
                if (mode != sInstance!!.mode) {
                    sInstance!!.mPreferences.edit().putInt(MODE, mode.index).apply()
                    notifyObservers()
                }
            } else {
                throw RuntimeException("ThemeEngine not initialized!")
            }
        }

        @UiThread
        private fun notifyObservers() {
            if (sInstance != null) {
                for (observer in mThemeObserverList) {
                    observer.onThemeChanged(sInstance)
                }
            }
        }

        val theme: ITheme?
            get() = if (sInstance != null) {
                sInstance
            } else {
                throw RuntimeException("ThemeEngine not initialized!")
            }

        fun applyTheme(view: View?, propagate: Boolean) {
            if (view is ThemeConsumer) {
                (view as ThemeConsumer).onApplyTheme(sInstance)
            }
            if (propagate && view is ViewGroup) {
                for (i in 0 until view.childCount) {
                    applyTheme(view.getChildAt(i), true)
                }
            }
        }
    }

    init {
        mPreferences = context.getSharedPreferences(FILE_NAME, Context.MODE_PRIVATE)
    }
}