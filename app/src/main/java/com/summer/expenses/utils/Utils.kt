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
package com.summer.expenses.utils

import android.R
import android.app.Activity
import android.content.Context
import android.graphics.Color
import android.graphics.drawable.Drawable
import android.os.Build
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.annotation.IdRes
import java.text.DecimalFormat
import java.util.*

/**
 * Created by andrea on 03/02/18.
 */
object Utils {
    private val PALETTE = intArrayOf(
        Color.rgb(204, 198, 24),
        Color.rgb(229, 163, 25),
        Color.rgb(232, 111, 40),
        Color.rgb(212, 75, 145),
        Color.rgb(117, 96, 165),
        Color.rgb(54, 142, 92),
        Color.rgb(129, 191, 22),
        Color.rgb(224, 184, 26),
        Color.rgb(229, 138, 24),
        Color.rgb(235, 89, 92),
        Color.rgb(167, 78, 160),
        Color.rgb(66, 117, 138),
        Color.rgb(85, 169, 48)
    )

    fun getHexColor(color: Int): String {
        return String.format("#%06X", 0xFFFFFF and color)
    }

    fun isColorLight(color: Int): Boolean {
        val red = Color.red(color)
        val green = Color.green(color)
        val blue = Color.blue(color)
        return red * 0.299 + green * 0.587 + blue * 0.114 > 186
    }

    fun showToast(context: Context?, text: String?) {
        Toast.makeText(context, text, Toast.LENGTH_SHORT).show()
    }

    fun getBestColor(color: Int): Int {
        return if (isColorLight(color)) {
            Color.BLACK
        } else Color.WHITE
    }

    fun readableFileSize(size: Long): String {
        if (size <= 0) return "0"
        val units = arrayOf("B", "kB", "MB", "GB", "TB")
        val digitGroups = (Math.log10(size.toDouble()) / Math.log10(1024.0)).toInt()
        return DecimalFormat("#,##0.#").format(
            size / Math.pow(
                1024.0,
                digitGroups.toDouble()
            )
        ) + " " + units[digitGroups]
    }

    val isAtLeastLollipop: Boolean
        get() = Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP

    fun setBackgroundCompat(view: View?, drawable: Drawable?) {
        if (view != null) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
                view.background = drawable
            } else {
                view.setBackgroundDrawable(drawable)
            }
        }
    }

    /*
    public static int getFileIcon(String extension) {
        if (extension != null) {
            switch (extension) {
                case BackupManager.BACKUP_EXTENSION_LEGACY:
                case BackupManager.BACKUP_EXTENSION_STANDARD:
                case BackupManager.BACKUP_EXTENSION_PROTECTED:
                    return R.drawable.ic_notification;
                case ".txt":
                case ".doc":
                case ".docx":
                    return R.drawable.ic_file_document_24dp;
                case ".png":
                case ".jpg":
                case ".jpeg":
                case ".gif":
                case ".bmp":
                    return R.drawable.ic_file_image_24dp;
                case ".mp4":
                    return R.drawable.ic_file_video_24dp;
                case ".pdf":
                    return R.drawable.ic_file_pdf_24dp;
            }
        }
        return R.drawable.ic_file_outline_24dp;
    }


 */
    fun getRandomMDColor(index: Int): Int {
        return PALETTE[index % PALETTE.size]
    }

    val randomMDColor: Int
        get() = PALETTE[Random()
            .nextInt(PALETTE.size)]

    fun findViewGroupByIds(activity: Activity, @IdRes vararg resIds: Int): ViewGroup? {
        val rootView = activity.window.decorView
        val contentView = rootView.findViewById<View>(R.id.content)
        return findViewGroupByIds(contentView ?: rootView, *resIds)
    }

    fun findViewGroupByIds(root: View, @IdRes vararg resIds: Int): ViewGroup? {
        for (redId in resIds) {
            val view = root.findViewById<ViewGroup>(redId)
            if (view != null) {
                return view
            }
        }
        return null
    }

    fun wrapAsArrayList(list: List<IFile?>?): ArrayList<IFile?> {
        return if (list is ArrayList<*>) {
            list as ArrayList<IFile?>
        } else {
            val wrappedList = ArrayList<IFile?>()
            wrappedList.addAll(list!!)
            wrappedList
        }
    }
}