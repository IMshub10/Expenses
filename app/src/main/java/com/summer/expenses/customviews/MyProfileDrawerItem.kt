package com.summer.expenses.customviews

import com.mikepenz.materialdrawer.holder.ImageHolder
import com.mikepenz.materialdrawer.model.ProfileDrawerItem

class MyProfileDrawerItem : ProfileDrawerItem() {
    override var icon: ImageHolder?
        get() = super.icon
        set(imageHolder) {
            super.icon = imageHolder
        }
}