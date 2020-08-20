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

package com.summer.expenses.customviews;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.os.Parcel;
import android.os.Parcelable;
import android.widget.ImageView;


import com.summer.expenses.utils.Utils;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by andrea on 23/01/18.
 */
public class ColorIcon extends Icon {

    private static final String COLOR = "color";
    private static final String NAME = "name";


    private final String mName;
    int backgroundColor;


    public ColorIcon(String name) {
        mName = name.substring(0,1).toUpperCase();
    }


    @Override
    public Type getType() {
        return Type.COLOR;
    }

    @Override
    protected void writeJSON(JSONObject jsonObject) throws JSONException {
    }

    @Override
    public boolean apply(ImageView imageView) {
        Drawable drawable = getDrawable();
        imageView.setImageDrawable(drawable);
        return true;
    }


    @Override
    public Drawable getDrawable(Context context) {
        return getDrawable();
    }

    public Drawable getDrawable() {
        backgroundColor = setBackgroundColor(mName.toLowerCase());
        return TextDrawable.builder()
                .beginConfig()
                .width(80)
                .height(80)
                .textColor(Utils.getBestColor(backgroundColor))
                .endConfig()
                .buildRound(mName, backgroundColor);
    }

    public static final Parcelable.Creator<ColorIcon> CREATOR = new Parcelable.Creator<ColorIcon>() {
        @Override
        public ColorIcon createFromParcel(Parcel source) {
            return null;
        }
        @Override
        public ColorIcon[] newArray(int size) {
            return new ColorIcon[size];
        }
    };

    @Override
    public void writeToParcel(Parcel dest, int flags) {
    }

    private int setBackgroundColor(String text) {
        switch (text) {
            case "a":
                return 0xffa85529;
            case "b":
                return 0xff8f6980;
            case "c":
                return 0xff698f78;
            case "d":
                return 0xff4f8240;
            case "e":
                return 0xff29a894;
            case "f":
                return 0xffa8293d;
            case "g":
                return 0xffa98963;
            case "h":
                return 0xff593d42;
            case "i":
                return 0xff3d4259;
            case "j":
                return 0xff543d59;
            case "k":
                return 0xff42593d;
            case "l":
                return 0xff768089;
            case "m":
                return 0xff2f828a;
            case "n":
                return 0xff8a2f55;
            case  "o":
                return 0xff63aad3;
            case "p":
                return 0xff146683;
            case "q":
                return 0xff1fddff;
            case  "r":
                return 0xffcda441;
            case "s":
                return 0xff99cc99;
            case "t":
                return 0xffffc1cc;
            case  "u":
                return 0xff734f96;
            case  "v":
                return 0xff003366;
            case  "w":
                return 0xffffa500;
            case  "x":
                return 0xff007474;
            case  "y":
                return 0xffffd800;
            case  "z":
                return 0xff9678b6;
            default:
                return 0xff0f0f0f;
        }

    }
}