package com.asus.sorkkin.sorkkin.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.MenuCompat;
import android.support.v4.view.MenuItemCompat;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.ShareActionProvider;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import com.asus.sorkkin.sorkkin.R;
import com.asus.sorkkin.sorkkin.dao.PhotoList;

import java.util.ArrayList;
import java.util.List;


/**
 * Created by nuuneoi on 11/16/2014.
 */
public class MoreInfoFragment extends Fragment {

    TabLayout tab;
    ViewPager viewPager;
    PhotoList dao;

    public MoreInfoFragment() {
        super();
    }

    public static MoreInfoFragment newInstance(PhotoList dao) {
        MoreInfoFragment fragment = new MoreInfoFragment();
        Bundle args = new Bundle();
        args.putParcelable("dao", dao);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        init(savedInstanceState);

        dao = getArguments().getParcelable("dao");

        if (savedInstanceState != null)
            onRestoreInstanceState(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_more_info, container, false);
        initInstances(rootView, savedInstanceState);
        return rootView;

    }

    @SuppressWarnings("UnusedParameters")
    private void init(Bundle savedInstanceState) {
        // Init Fragment level's variable(s) here
        setHasOptionsMenu(true);
    }

    @SuppressWarnings("UnusedParameters")
    private void initInstances(View rootView, Bundle savedInstanceState) {
        // Init 'View' instance(s) with rootView.findViewById here
        // Note: State of variable initialized here could not be saved
        //       in onSavedInstanceState
        viewPager = (ViewPager) rootView.findViewById(R.id.viewPager);
        viewPager.setAdapter(new FragmentStatePagerAdapter(getChildFragmentManager()) {
            @Override
            public Fragment getItem(int position) {
                switch (position) {
                    case 0:

                        return PhotoInfoFragment.newInstance(dao);

                    case 1:

                        return MapFragment.newInstance(dao);

                    default:

                        return null;

                }
            }

            @Override
            public int getCount() {
                return 3;
            }
        });

        tab = (TabLayout) rootView.findViewById(R.id.tab);

        setUpViewPager(viewPager);
        tab.setupWithViewPager(viewPager);


    }

    private void setUpViewPager(ViewPager viewPager) {
        MoreInfoFragment.Adapter adapter = new MoreInfoFragment.Adapter(getChildFragmentManager());
        adapter.addFragment(PhotoInfoFragment.newInstance(dao), "ຂໍ້ມູນ");
        adapter.addFragment(MapFragment.newInstance(dao), "ແຜນທີ່");
        viewPager.setAdapter(adapter);
    }
    static class Adapter extends FragmentPagerAdapter {
        private final List<Fragment> mFragmentList = new ArrayList<>();
        private final List<String> mFragmentTitleList = new ArrayList<>();

        public Adapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            return mFragmentList.get(position);
        }

        @Override
        public int getCount() {
            return mFragmentList.size();
        }

        public void addFragment(Fragment fragment, String title) {
            mFragmentList.add(fragment);
            mFragmentTitleList.add(title);
        }

        @Nullable
        @Override
        public CharSequence getPageTitle(int position) {
            return mFragmentTitleList.get(position);
        }
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        // Save Instance (Fragment level's variables) State here
    }

    @SuppressWarnings("UnusedParameters")
    private void onRestoreInstanceState(Bundle savedInstanceState) {
        // Restore Instance (Fragment level's variables) State here
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        inflater.inflate(R.menu.menu_more_info,menu);

        MenuItem menuItem = (MenuItem) menu.findItem(R.id.action_share);
        ShareActionProvider shareActionProvider = (ShareActionProvider)
                MenuItemCompat.getActionProvider(menuItem);
        shareActionProvider.setShareIntent(getShareIntent());
    }
    private Intent getShareIntent() {

        Intent intent = new Intent(Intent.ACTION_SEND);
        /*intent.setType("text/plain");*/
        intent.setType("image/jpeg");
        /*intent.putExtra(Intent.EXTRA_SUBJECT,dao.getName());*/
        intent.putExtra(Intent.EXTRA_FROM_STORAGE,dao.getImg());
        /*intent.putExtra(Intent.EXTRA_TEXT,dao.getAddress() + dao.getTel());*/
        return intent;

    }
}
