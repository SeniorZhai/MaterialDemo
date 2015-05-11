package io.github.seniorzhai.materialdemo;

import android.os.Bundle;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;


public class MainActivity extends AppCompatActivity {

    private Toolbar toolbar;
    private DrawerLayout mDrawerLayout;
    private View logo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        logo = findViewById(R.id.ivLogo);
        toolbar.setTitle("");
        initDrawer();
        if (savedInstanceState == null) {
            startIntroAnimation();
        }
    }

    private void initDrawer() {
        setSupportActionBar(toolbar);
        getSupportActionBar().setHomeButtonEnabled(true); //设置返回键可用
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        ActionBarDrawerToggle actionBarDrawerToggle = new ActionBarDrawerToggle(this, mDrawerLayout, toolbar, R.string.navigation_drawer_open,  /* "open drawer" description for accessibility */
                R.string.navigation_drawer_close);
        actionBarDrawerToggle.syncState();
        mDrawerLayout.setDrawerListener(actionBarDrawerToggle);
    }

    private void startIntroAnimation() {
        int actionBarSize = Utils.dpToPx(56);
        toolbar.setTranslationY(-actionBarSize);
        logo.setTranslationY(-actionBarSize);
        toolbar.animate().translationY(0).setDuration(300).setStartDelay(300);
        logo.animate().translationY(0).setDuration(300).setStartDelay(450);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

}
