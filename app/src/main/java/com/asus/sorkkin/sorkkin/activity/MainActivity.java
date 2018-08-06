package com.asus.sorkkin.sorkkin.activity;

import android.content.Intent;
import android.content.res.Configuration;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.asus.sorkkin.sorkkin.R;
import com.asus.sorkkin.sorkkin.dao.PhotoList;
import com.asus.sorkkin.sorkkin.fragment.BuffetFragment;
import com.asus.sorkkin.sorkkin.fragment.CafeFragment;
import com.asus.sorkkin.sorkkin.fragment.NoodleFragment;
import com.asus.sorkkin.sorkkin.fragment.ResAndBarFragment;
import com.asus.sorkkin.sorkkin.fragment.SaladFragment;

import static com.asus.sorkkin.sorkkin.fragment.BuffetFragment.*;

public class MainActivity extends AppCompatActivity implements
        CafeFragment.FragmentListener,
        BuffetFragment.FragmentListener,
        NoodleFragment.FragmentListener,
        ResAndBarFragment.FragmentListener,
        SaladFragment.FragmentListener {

    private DrawerLayout mDrawerLayout;
    ActionBarDrawerToggle actionBarDrawerToggle;
    android.support.v7.widget.Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initInstances();

        if (savedInstanceState == null) {
            getSupportFragmentManager()
                    .beginTransaction()
                    .add(R.id.contentContainer, new CafeFragment())
                    .commit();
        }
    }

    private void initInstances() {
        toolbar = (android.support.v7.widget.Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        mDrawerLayout = findViewById(R.id.drawerLayout);

        NavigationView navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(
                new NavigationView.OnNavigationItemSelectedListener() {
                    @Override
                    public boolean onNavigationItemSelected(MenuItem menuItem) {
                        // set item as selected to persist highlight
                        menuItem.setChecked(true);
                        // close drawer when item is tapped
                        mDrawerLayout.closeDrawers();
//                        Toast.makeText(MainActivity.this, "Sabaidee", Toast.LENGTH_SHORT).show();

                        // Add code here to update the UI based on the item selected
                        // For example, swap UI fragments here


                        switch(menuItem.getItemId()) {
                            case R.id.ic_cafe :
                                getSupportFragmentManager().beginTransaction()
                                        .replace(R.id.contentContainer,
                                                CafeFragment.newInstance())
                                        .commit();
                                Toast.makeText(MainActivity.this, "ຄາເຟ່", Toast.LENGTH_SHORT).show();
                                break;

                            case R.id.ic_buffet :
                                getSupportFragmentManager().beginTransaction()
                                        .replace(R.id.contentContainer,
                                                newInstance())
                                        .commit();
                                Toast.makeText(MainActivity.this, "ບຸບເຟ່", Toast.LENGTH_SHORT).show();
                                break;

                            case R.id.ic_resandbar :
                                getSupportFragmentManager().beginTransaction()
                                        .replace(R.id.contentContainer,
                                                ResAndBarFragment.newInstance())
                                        .commit();
                                Toast.makeText(MainActivity.this, "ຮ້ານ ອາຫານ ເເລະ ບາ", Toast.LENGTH_SHORT).show();
                                break;
                            case R.id.ic_salad :
                                getSupportFragmentManager().beginTransaction()
                                        .replace(R.id.contentContainer,
                                                SaladFragment.newInstance())
                                        .commit();
                                Toast.makeText(MainActivity.this, "ຕໍາ", Toast.LENGTH_SHORT).show();
                                break;
                            case R.id.ic_noodle :
                                getSupportFragmentManager().beginTransaction()
                                        .replace(R.id.contentContainer,
                                                NoodleFragment.newInstance())
                                        .commit();
                                Toast.makeText(MainActivity.this, "ເສັ້ນ", Toast.LENGTH_SHORT).show();
                                break;
                        }

                        return true;
                    }
                });

        actionBarDrawerToggle = new ActionBarDrawerToggle(
                MainActivity.this,
                mDrawerLayout,
                R.string.open_drawer,
                R.string.close_drawer
        );
        mDrawerLayout.setDrawerListener(actionBarDrawerToggle);
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        navigationView.setCheckedItem(R.id.ic_noodle);

        mDrawerLayout.addDrawerListener(new DrawerLayout.DrawerListener() {
            @Override
            public void onDrawerSlide(@NonNull View drawerView, float slideOffset) {
                // Respond when the drawer's position changes
            }

            @Override
            public void onDrawerOpened(@NonNull View drawerView) {
                // Respond when the drawer is opened
            }

            @Override
            public void onDrawerClosed(@NonNull View drawerView) {
                // Respond when the drawer is closed
            }

            @Override
            public void onDrawerStateChanged(int newState) {
                // Respond when the drawer motion state changes
            }
        });
    }


    @Override
    protected void onPostCreate(@Nullable Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        actionBarDrawerToggle.syncState();
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        actionBarDrawerToggle.onConfigurationChanged(newConfig);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (actionBarDrawerToggle.onOptionsItemSelected(item))
            return true;
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onPhotoItemClicked(PhotoList dao) {

/*
        FrameLayout moreInfoContianer = (FrameLayout)
                findViewById(R.id.moreInfoContainer);

        if (moreInfoContianer == null) {
*/

            Intent intent = new Intent(MainActivity.this,
                    MoreInfoActivity.class);
            intent.putExtra("dao", dao);

            startActivity(intent);
/*        } else {
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.moreInfoContainer, MoreInfoFragment.newInstance(dao))
                    .commit();

        }*/
    }
}
