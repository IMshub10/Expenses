package com.summer.expenses.activities

import android.content.Intent
import android.content.res.Configuration
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.app.ActionBarDrawerToggle
import com.mikepenz.iconics.IconicsDrawable
import com.mikepenz.iconics.typeface.library.googlematerial.GoogleMaterial
import com.mikepenz.iconics.utils.paddingDp
import com.mikepenz.materialdrawer.holder.BadgeStyle
import com.mikepenz.materialdrawer.holder.ColorHolder
import com.mikepenz.materialdrawer.holder.ImageHolder
import com.mikepenz.materialdrawer.iconics.iconicsIcon
import com.mikepenz.materialdrawer.model.ProfileDrawerItem
import com.mikepenz.materialdrawer.model.ProfileSettingDrawerItem
import com.mikepenz.materialdrawer.model.interfaces.*
import com.mikepenz.materialdrawer.widget.AccountHeaderView
import com.summer.expenses.R
import com.summer.expenses.customviews.ColorIcon
import com.summer.expenses.customviews.MyProfileDrawerItem
import kotlinx.android.synthetic.main.activity_main.*



class MainActivity : AppCompatActivity() {
    private lateinit var headerView: AccountHeaderView
    private lateinit var actionBarDrawerToggle: ActionBarDrawerToggle

    companion object {
        private const val PROFILE_SETTING = 100000
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        // Handle Toolbar
        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(false)
        supportActionBar?.setHomeButtonEnabled(true)
        actionBarDrawerToggle = ActionBarDrawerToggle(
            this,
            root,
            toolbar,
            com.mikepenz.materialdrawer.R.string.material_drawer_open,
            com.mikepenz.materialdrawer.R.string.material_drawer_close
        )
        root.addDrawerListener(actionBarDrawerToggle)

        // Create a few sample profile
        // NOTE you have to define the loader logic too. See the CustomApplication for more details
        val profile = MyProfileDrawerItem().apply {
            icon = ImageHolder(
                ColorIcon(
                    "S"
                ).drawable
            ); nameText = "Mike Penz"; descriptionText = "mikepenz@gmail.com"; identifier = 100
        }
        val profile2 = ProfileDrawerItem().apply {
            nameText = "Demo User"; descriptionText = "demo@github.com"; iconUrl =
            "https://avatars2.githubusercontent.com/u/3597376?v=3&s=460"; identifier = 101
        }
        val profile3 = ProfileDrawerItem().apply {
            nameText = "Max Muster"; descriptionText = "max.mustermann@gmail.com";iconUrl =
            "https://avatars2.githubusercontent.com/u/3597376?v=3&s=460"; iconRes =
            R.drawable.ic_launcher_foreground; identifier = 102
        }
        val profile4 = ProfileDrawerItem().apply {
            nameText = "Felix House"; descriptionText = "felix.house@gmail.com"; iconRes =
            R.drawable.ic_launcher_foreground; identifier = 103
        }
        val profile5 = ProfileDrawerItem().apply {
            nameText = "Mr. X"; descriptionText = "mister.x.super@gmail.com"; iconRes =
            R.drawable.ic_launcher_foreground; identifier = 104
        }
        val profile6 = ProfileDrawerItem().apply {
            nameText = "Batman"; descriptionText = "batman@gmail.com"; iconRes =
            R.drawable.ic_launcher_foreground; identifier = 105; badgeText = "123";
            badgeStyle = BadgeStyle().apply {
                textColor = ColorHolder.fromColor(Color.BLACK)
                color = ColorHolder.fromColor(Color.WHITE)
            }
        }

        // Create the AccountHeader
        headerView = AccountHeaderView(this).apply {
            attachToSliderView(slider)
            addProfiles(
                profile,
                profile2,
                profile3,
                profile4,
                profile5,
                profile6,MyProfileDrawerItem().apply {
                    icon = ImageHolder(
                        ColorIcon(
                            "A"
                        ).drawable
                    ); nameText = "Mike Penz"; descriptionText = "mikepenz@gmail.com"; identifier = 100
                },
                MyProfileDrawerItem().apply {
                    icon = ImageHolder(
                        ColorIcon(
                            "B"
                        ).drawable
                    ); nameText = "Mike Penz"; descriptionText = "mikepenz@gmail.com"; identifier = 100
                },
                //don't ask but google uses 14dp for the add account icon in gmail but 20dp for the normal icons (like manage account)
                ProfileSettingDrawerItem().apply {
                    nameText = "Add Account"; descriptionText =
                    "Add new GitHub Account"; iconDrawable = IconicsDrawable(
                    context,
                    GoogleMaterial.Icon.gmd_add
                ).apply { actionBar; paddingDp = 5 }.mutate(); isIconTinted = true; identifier =
                    PROFILE_SETTING.toLong()
                },
                ProfileSettingDrawerItem().apply {
                    nameText = "Manage Account"; iconicsIcon =
                    GoogleMaterial.Icon.gmd_settings; identifier = 100001
                }
            )
            onAccountHeaderListener = { view, profile, current ->
                //sample usage of the onProfileChanged listener
                //if the clicked item has the identifier 1 add a new profile ;)
                if (profile is IDrawerItem<*> && profile.identifier == PROFILE_SETTING.toLong()) {
                    val intent= Intent(this@MainActivity,CreateWallet::class.java)
                    startActivity(intent)
                }

                //false if you have not consumed the event and it should close the drawer
                false
            }
            withSavedInstance(savedInstanceState)
        }
    }

    override fun onConfigurationChanged(newConfig: Configuration) {
        super.onConfigurationChanged(newConfig)
        actionBarDrawerToggle.onConfigurationChanged(newConfig)
    }

    override fun onResume() {
        super.onResume()
        actionBarDrawerToggle.syncState()
    }



    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return super.onOptionsItemSelected(item)
    }
}