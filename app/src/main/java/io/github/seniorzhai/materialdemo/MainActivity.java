package io.github.seniorzhai.materialdemo;

import android.animation.Animator;
import android.os.Bundle;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.OvershootInterpolator;
import android.widget.ImageButton;


public class MainActivity extends AppCompatActivity {

    private Toolbar toolbar;
    private DrawerLayout mDrawerLayout;
    private View logo;
    private RecyclerView recyclerView;
    ImageButton btnCreate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        recyclerView = (RecyclerView) findViewById(R.id.rvFeed);
        logo = findViewById(R.id.ivLogo);
        btnCreate = (ImageButton) findViewById(R.id.btnCreate);
        toolbar.setTitle("");
        initDrawer();
        initFeed();
        if (savedInstanceState == null) {
            startIntroAnimation();
        } else {
            feedAdapter.updateItems(false);
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
        btnCreate.setTranslationY(2 * getResources().getDimensionPixelOffset(R.dimen.btn_fab_size));
        int actionBarSize = Utils.dpToPx(56);
        toolbar.setTranslationY(-actionBarSize);
        logo.setTranslationY(-actionBarSize);
        toolbar.animate().translationY(0).setDuration(300).setStartDelay(300);
        logo.animate().translationY(0).setDuration(300).setStartDelay(450).setListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animation) {

            }

            @Override
            public void onAnimationEnd(Animator animation) {
//                feedAdapter.updateItems(true);
                btnCreate.animate()
                        .translationY(0)
                        .setInterpolator(new OvershootInterpolator(1.f))
                        .setStartDelay(300)
                        .setDuration(400)
                        .start();
            }

            @Override
            public void onAnimationCancel(Animator animation) {

            }

            @Override
            public void onAnimationRepeat(Animator animation) {

            }
        });
    }

    private FeedAdapter feedAdapter;

    private void initFeed() {
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this) {
            @Override
            protected int getExtraLayoutSpace(RecyclerView.State state) {
                return 300;
            }
        };
        recyclerView.setLayoutManager(linearLayoutManager);
        feedAdapter = new FeedAdapter(this);
        recyclerView.setAdapter(feedAdapter);
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
