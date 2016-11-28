package com.kevin.tech.bottomnavigationbarforandroid;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.kevin.tech.bottomnavigationbarforandroid.fragment.NavigationFragment;
import com.kevin.tech.bottomnavigationbarforandroid.fragment.RadioFragment;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener, View.OnClickListener {
    private DrawerLayout mDrawerLayout;
    private Toolbar mToolbar;
    private NavigationView mNavigationView;
    private ActionBarDrawerToggle mDrawerToggle;
    private NavigationFragment mNavigationFragment;
    private RadioFragment mRadioFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        mToolbar = (Toolbar) findViewById(R.id.tool_bar);
        mNavigationView = (NavigationView) findViewById(R.id.navigation_view);

        setSupportActionBar(mToolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        mDrawerToggle = new ActionBarDrawerToggle(this, mDrawerLayout, mToolbar, R.string.drawer_layout_open, R.string.drawer_layout_close);
        mDrawerToggle.syncState();
        mDrawerToggle.setDrawerIndicatorEnabled(true);
        mDrawerLayout.setDrawerListener(mDrawerToggle);
        mDrawerLayout.setStatusBarBackgroundColor(ContextCompat.getColor(this, R.color.colorPrimaryDark));

        View headerView = mNavigationView.getHeaderView(0);
        headerView.setOnClickListener(this);
        mNavigationView.setItemIconTintList(null);
        mNavigationView.setNavigationItemSelectedListener(this);
        setCurrentFragment();
    }

    private void setCurrentFragment() {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        mNavigationFragment = NavigationFragment.newInstance(getString(R.string.navigation_navigation_bar));
        transaction.replace(R.id.frame_content, mNavigationFragment).commit();
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        android.support.v4.app.FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        switch (item.getItemId()) {
            case R.id.menu_bottom_navigation_bar:
                if (mNavigationFragment == null) {
                    mNavigationFragment = NavigationFragment.newInstance(getString(R.string.navigation_navigation_bar));
                }
                transaction.replace(R.id.frame_content, mNavigationFragment);
                Snackbar.make(mDrawerLayout, "NavigationBar", Snackbar.LENGTH_SHORT).show();
                break;
            case R.id.menu_radio_group:
                if (mRadioFragment == null) {
                    mRadioFragment = RadioFragment.newInstance(getString(R.string.navigation_radio_bar));
                }
                transaction.replace(R.id.frame_content, mRadioFragment);
                Snackbar.make(mDrawerLayout, "RadioGroup", Snackbar.LENGTH_SHORT).show();
                break;
            case R.id.menu_view_pager:
                Snackbar.make(mDrawerLayout, "ViewPager", Snackbar.LENGTH_SHORT).show();
                break;
            case R.id.menu_text_view:
                Snackbar.make(mDrawerLayout, "TextView", Snackbar.LENGTH_SHORT).show();
                break;

        }
        mDrawerLayout.closeDrawers();
        transaction.commit();
        return true;
    }

    @Override
    public void onClick(View view) {

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.home:
                mDrawerLayout.openDrawer(GravityCompat.START);
                return true;
            case R.id.settings:
                Snackbar.make(mDrawerLayout, "Settings", Snackbar.LENGTH_SHORT).show();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
