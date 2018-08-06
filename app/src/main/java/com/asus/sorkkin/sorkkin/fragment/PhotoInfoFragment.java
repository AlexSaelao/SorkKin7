package com.asus.sorkkin.sorkkin.fragment;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.asus.sorkkin.sorkkin.R;
import com.asus.sorkkin.sorkkin.dao.PhotoList;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;


/**
 * Created by nuuneoi on 11/16/2014.
 */
public class PhotoInfoFragment extends Fragment {

    PhotoList dao;
    ImageView imgView;
    TextView tvName;
    TextView tvDesription;
    TextView tvTel;
 /*   Button btnCall;*/
    private static int CALL_REQUEST = 1;

    public PhotoInfoFragment() {
        super();
    }

    public static PhotoInfoFragment newInstance(PhotoList dao) {
        PhotoInfoFragment fragment = new PhotoInfoFragment();
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
        View rootView = inflater.inflate(R.layout.fragment_photo_info, container, false);
        initInstances(rootView, savedInstanceState);
        return rootView;
    }

    @SuppressWarnings("UnusedParameters")
    private void init(Bundle savedInstanceState) {
        // Init Fragment level's variable(s) here
    }

    @SuppressWarnings("UnusedParameters")
    private void initInstances(View rootView, Bundle savedInstanceState) {
        // Init 'View' instance(s) with rootView.findViewById here
        // Note: State of variable initialized here could not be saved
        //       in onSavedInstanceState

        imgView = (ImageView) rootView.findViewById(R.id.imgView);
        tvName = (TextView) rootView.findViewById(R.id.tvName);
        tvDesription = (TextView) rootView.findViewById(R.id.tvDescription);
        tvTel = (TextView) rootView.findViewById(R.id.tvTel);
        /*btnCall = (Button) rootView.findViewById(R.id.btnCall);*/

        tvName.setText(dao.getName());
        tvDesription.setText(dao.getAddress());
        tvTel.setText(dao.getTel());


        Glide.with(getContext())
                .load(dao.getImg())
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .placeholder(R.drawable.loading)
                .into(imgView);

        /*btnCall.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_CALL);
                intent.setData(Uri.parse(dao.getTel()));

                if (ActivityCompat.checkSelfPermission(getContext(), Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
                    ActivityCompat.requestPermissions(getActivity().getParent(),new String[]{Manifest.permission.CALL_PHONE},CALL_REQUEST);
                    return;
                }

                startActivity(intent);
            }
        });*/



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

}
