package com.asus.sorkkin.sorkkin.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import com.asus.sorkkin.sorkkin.R;
import com.asus.sorkkin.sorkkin.dao.PhotoList;
import com.asus.sorkkin.sorkkin.fragment.MoreInfoFragment;

public class MoreInfoActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_more_info);
        
        initInstances();

        PhotoList dao = getIntent().getParcelableExtra("dao");

        if (savedInstanceState == null) {
            getSupportFragmentManager()
                    .beginTransaction()
                    .add(R.id.contentContainerCafe,MoreInfoFragment.newInstance(dao))
                    .commit();
        }

    }

    private void initInstances() {
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);



    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        if (item.getItemId() == android.R.id.home) {
        finish();
        return true;
        }
        return super.onOptionsItemSelected(item);
    }


}
