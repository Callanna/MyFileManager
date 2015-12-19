/*
 * Copyright (c) 2010-2011, The MiCode Open Source Community (www.micode.net)
 *
 * This file is part of FileExplorer.
 *
 * FileExplorer is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * FileExplorer is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with SwiFTP.  If not, see <http://www.gnu.org/licenses/>.
 */

package com.github.callanna.myfilemanager.activities;

import android.app.ActionBar;
import android.app.Activity;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;

import com.github.callanna.myfilemanager.R;
import com.github.callanna.myfilemanager.fragments.RemoteFragment;

public class ServerControlActivity extends AppCompatActivity {
    int theme, skinStatusBar;
    String skin, fabSkin;

    @Override
    public void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_servercontrol);

        skin = PreferenceManager.getDefaultSharedPreferences(getApplicationContext()).getString("skin_color", "#3f51b5");
        setTheme(R.style.pref_accent_light_pink);
        getSupportActionBar().setDisplayOptions(ActionBar.DISPLAY_HOME_AS_UP | ActionBar.DISPLAY_SHOW_HOME | ActionBar.DISPLAY_SHOW_TITLE);
        getSupportActionBar().setBackgroundDrawable(new ColorDrawable(Color.parseColor(skin)));
        getSupportActionBar().setTitle("Remote Manager");
        replaceFragment();
    }
   public void replaceFragment(){
    android.support.v4.app.FragmentManager fragmentManager = getSupportFragmentManager();
    android.support.v4.app.FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
    fragmentTransaction.add(R.id.server_content_fragment,new RemoteFragment(), "Remote").commit();
}
    @Override
    public void onBackPressed() {
        Intent in = new Intent( this, MainActivity.class);
        in.setAction(Intent.ACTION_MAIN);
        final int enter_anim = android.R.anim.fade_in;
        final int exit_anim = android.R.anim.fade_out;
        Activity activity=this;
        activity.overridePendingTransition(enter_anim, exit_anim);
        activity.finish();
        activity.overridePendingTransition(enter_anim, exit_anim);
        activity.startActivity(in);

    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                // Navigate "up" the demo structure to the launchpad activity.
                Intent in = new Intent( this, MainActivity.class);
                in.setAction(Intent.ACTION_MAIN);
                final int enter_anim = android.R.anim.fade_in;
                final int exit_anim = android.R.anim.fade_out;
                Activity activity=this;
                activity.overridePendingTransition(enter_anim, exit_anim);
                activity.finish();
                activity.overridePendingTransition(enter_anim, exit_anim);
                activity.startActivity(in);
                return true;

        }
        return true;
    }


    public interface IBackPressedListener {
        /**
         * 处理back事件。
         * @return True: 表示已经处理; False: 没有处理，让基类处理。
         */
        boolean onBack();
    }
}
