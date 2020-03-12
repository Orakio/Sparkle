package com.example.myproject.screens;

import android.os.Bundle;
import android.view.MenuItem;

import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.example.myproject.screens.events.list.EventsFragment;
import com.example.myproject.screens.news.list.NewsFragment;
import com.google.android.material.navigation.NavigationView;


import com.example.myproject.R;
import com.example.myproject.screens.movies.list.MoviesFragment;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends BaseActivity {

    @BindView(R.id.drawer_layout)
    DrawerLayout drawerLayout;
    @BindView(R.id.navigationView)
    NavigationView navigationView;

    public void launchFragment(Fragment fragment) {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.container, fragment);
        transaction.commit();

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        setupDrawerIcon();
        setupDrawerContent();

        launchFragment(MoviesFragment.newInstance());
    }



    //icon for  drawerLayout
    private void setupDrawerIcon() {
        ActionBarDrawerToggle mDrawerToggle = new ActionBarDrawerToggle(this, drawerLayout, 0, 0);
        drawerLayout.addDrawerListener(mDrawerToggle);
        mDrawerToggle.syncState();
    }

    //Click element
    private void setupDrawerContent() {

        navigationView.setNavigationItemSelectedListener(menuItem -> {

            //Close menu
            drawerLayout.closeDrawers();

            //Choose fragment
            switch (menuItem.getItemId()) {
                case R.id.maps:
                    launchFragment(EventsFragment.newInstance());
                    break;
                case R.id.news:
                    launchFragment(NewsFragment.newInstance());
                    break;
                case R.id.movies:
                    launchFragment(MoviesFragment.newInstance());
                    break;
                case R.id.info:
                    launchFragment(InfoFragment.newInstance());
                    break;
            }

            return false;
        });
    }


    //Burger icon click.
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                //Close drawerLayout , on click arrow
                if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
                    drawerLayout.closeDrawers();
                } else {//open drawerLayout, hamburger icon
                    drawerLayout.openDrawer(GravityCompat.START);
                }
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }


}
