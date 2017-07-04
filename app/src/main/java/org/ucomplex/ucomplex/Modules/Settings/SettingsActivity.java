package org.ucomplex.ucomplex.Modules.Settings;


import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ProgressBar;

import org.ucomplex.ucomplex.Common.base.BaseActivity;
import org.ucomplex.ucomplex.R;

public class SettingsActivity extends BaseActivity {

    public static Intent creteIntent(Context context) {
        return new Intent(context, SettingsActivity.class);
    }

    private SettingsProfileFragment settingsProfileFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentViewWithNavDrawer(R.layout.activity_settings);
        setupToolbar(getString(R.string.settings), R.drawable.ic_menu);
        mProgress = (ProgressBar) findViewById(R.id.progressBar);
        setupDrawer();
        settingsProfileFragment = (SettingsProfileFragment) addFragment(SettingsProfileFragment.class, savedInstanceState, getString(R.string.settings), R.id.container);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_settings, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                return true;
            case R.id.settings_done:
                settingsProfileFragment.saveSettings();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        settingsProfileFragment = null;
    }
}