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
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.kevin.tech.bottomnavigationbarforandroid.fragment.NavigationFragment;
import com.kevin.tech.bottomnavigationbarforandroid.fragment.RadioFragment;
import com.kevin.tech.bottomnavigationbarforandroid.fragment.TabLayoutFragment;
import com.kevin.tech.bottomnavigationbarforandroid.fragment.TabLayoutFragment2;
import com.kevin.tech.bottomnavigationbarforandroid.fragment.TextTabFragment;
import com.kevin.tech.bottomnavigationbarforandroid.utils.SnackBarUtils;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener, View.OnClickListener {
    private DrawerLayout mDrawerLayout;
    private Toolbar mToolbar;
    private NavigationView mNavigationView;
    private ActionBarDrawerToggle mDrawerToggle;
    private NavigationFragment mNavigationFragment;
    private RadioFragment mRadioFragment;
    private LinearLayout mRadioBadge;//the badge for radioGroup menu
    private TextView mRadioMsg;
    private TextTabFragment mTextTabFragment;
    private TabLayoutFragment mTabLayoutFragment;
    private TabLayoutFragment2 mTabLayoutFragment2;
    private NightModeHelper mNightModeHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
        mNightModeHelper = new NightModeHelper(this, R.style.BaseTheme);
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
        mNavigationView.setItemTextColor(ContextCompat.getColorStateList(this, R.color.bg_drawer_navigation));
        mNavigationView.setItemIconTintList(ContextCompat.getColorStateList(this, R.color.bg_drawer_navigation));
//        mRadioBadge = (LinearLayout) mNavigationView.getMenu().findItem(R.id.menu_radio_group).getActionView();
//        mRadioMsg = (TextView) mRadioBadge.findViewById(R.id.msg);
//        mRadioMsg.setText("8");
        setNavigationViewChecked(0);
        setCurrentFragment();

    }

    private void setNavigationViewChecked(int position) {
        mNavigationView.getMenu().getItem(position).setChecked(true);
        Log.i("Kevin", "the count of menu item is--->" + mNavigationView.getMenu().size() + "");
        for (int i = 0; i < mNavigationView.getMenu().size(); i++) {
            if (i != position) {
                mNavigationView.getMenu().getItem(i).setChecked(false);
            }
        }
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
                setNavigationViewChecked(0);
                break;
            case R.id.menu_radio_group:
                if (mRadioFragment == null) {
                    mRadioFragment = RadioFragment.newInstance(getString(R.string.navigation_radio_bar));
                }
                transaction.replace(R.id.frame_content, mRadioFragment);
//                Snackbar.make(mDrawerLayout, "RadioGroup", Snackbar.LENGTH_SHORT).show();
                SnackBarUtils.showSnackBar(mDrawerLayout, getString(R.string.navigation_radio_bar), this);
                setNavigationViewChecked(1);
                break;
            case R.id.menu_text_view:
                if (mTextTabFragment == null) {
                    mTextTabFragment = TextTabFragment.newInstance(getString(R.string.navigation_text_tab));
                }
                transaction.replace(R.id.frame_content, mTextTabFragment);
                Snackbar.make(mDrawerLayout, "TextView + LinearLayout", Snackbar.LENGTH_SHORT).show();
                setNavigationViewChecked(2);
                break;
            case R.id.menu_tab_layout:
                if(mTabLayoutFragment == null){
                mTabLayoutFragment = TabLayoutFragment.newInstance(getString(R.string.navigation_tab_layout));
                }
                transaction.replace(R.id.frame_content, mTabLayoutFragment);
                setNavigationViewChecked(3);
                Snackbar.make(mDrawerLayout, "TabLayout + ViewPager", Snackbar.LENGTH_SHORT).show();
                break;
            case R.id.menu_tab_layout2:
                if (mTabLayoutFragment2 == null) {
                    mTabLayoutFragment2 = TabLayoutFragment2.newInstance(getString(R.string.navigation_tab_layout2));
                }
                transaction.replace(R.id.frame_content, mTabLayoutFragment2);
                setNavigationViewChecked(4);
                Snackbar.make(mDrawerLayout, "TabLayout + ViewPager 2", Snackbar.LENGTH_SHORT).show();
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
            case R.id.share:
                mNightModeHelper.toggle();
//                Configuration newConfig = new Configuration(getResources().getConfiguration());
//                newConfig.uiMode &= ~Configuration.UI_MODE_NIGHT_MASK;
//                newConfig.uiMode |= uiNightMode;
//                getResources().updateConfiguration(newConfig, null);
//                recreate();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

}
