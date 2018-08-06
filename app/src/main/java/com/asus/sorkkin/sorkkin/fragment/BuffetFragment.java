package com.asus.sorkkin.sorkkin.fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.asus.sorkkin.sorkkin.R;
import com.asus.sorkkin.sorkkin.dao.PhotoList;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.List;


public class BuffetFragment extends Fragment {

    public interface FragmentListener {
        void onPhotoItemClicked(PhotoList dao);
    }

    RecyclerView mPhotoListItem;
    DatabaseReference mDatabase;
    BuffetFragment.MyAdapter adapter;
    List<PhotoList> listphotolist;
    FirebaseDatabase firebaseDatabase;

    public BuffetFragment() {
        super();
    }

    public static BuffetFragment newInstance() {
        BuffetFragment fragment = new BuffetFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        init(savedInstanceState);

        if (savedInstanceState != null)
            onRestoreInstanceState(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_buffet, container, false);
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

        mDatabase = FirebaseDatabase.getInstance().getReference().child("restaurants/buffet");
        mDatabase.keepSynced(true);

        mPhotoListItem = (RecyclerView) rootView.findViewById(R.id.recycleViewBuffet);
        mPhotoListItem.setHasFixedSize(true);

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getContext());
        mPhotoListItem.setLayoutManager(layoutManager);
        mPhotoListItem.setItemAnimator(new DefaultItemAnimator());
        mPhotoListItem.addItemDecoration(new DividerItemDecoration(getContext(),LinearLayoutManager.VERTICAL));

        listphotolist = new ArrayList<>();
        adapter = new BuffetFragment.MyAdapter(listphotolist);
        firebaseDatabase = FirebaseDatabase.getInstance();

        mPhotoListItem.setAdapter(adapter);

        getDataFirebase();



    }
    void getDataFirebase() {
        mDatabase.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {

                PhotoList photoList = dataSnapshot.getValue(PhotoList.class);
                listphotolist.add(photoList);
                adapter.notifyDataSetChanged();

                Log.e("onChildAdded", dataSnapshot.getValue(PhotoList.class).getName().toString() + "");
            }

            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String s) {

                PhotoList photoList = dataSnapshot.getValue(PhotoList.class);
                listphotolist.add(photoList);
                listphotolist.notify();
                adapter.notifyDataSetChanged();


                Log.e("onChildChanged", dataSnapshot.getValue(PhotoList.class).getName().toString() + "");

            }

            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot) {

            }

            @Override
            public void onChildMoved(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }

    public class MyAdapter extends RecyclerView.Adapter<BuffetFragment.MyAdapter.MyViewHolder> {

        List<PhotoList> listArray;
        public  MyAdapter(List<PhotoList> List) {
            this.listArray = List;

        }

        @NonNull
        @Override
        public BuffetFragment.MyAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.itemview,parent,false);

            Animation anim = AnimationUtils.loadAnimation(parent.getContext(),
                    R.anim.up_from_bottom);
            view.startAnimation(anim);

            return new BuffetFragment.MyAdapter.MyViewHolder(view);
        }

        @Override
        public void onBindViewHolder(BuffetFragment.MyAdapter.MyViewHolder holder, int position) {
            PhotoList photoList = listArray.get(position);

            holder.tvName.setText(photoList.getName());
            holder.tvDescription.setText(photoList.getAddress());

            final int itemPosition = position;


            Glide.with(getContext())
                    .load(photoList.getImg())
                    .diskCacheStrategy(DiskCacheStrategy.ALL)
                    .placeholder(R.drawable.loading)
                    .into(holder.imgView);

            holder.rootView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    PhotoList dao = listphotolist.get(itemPosition);
                    BuffetFragment.FragmentListener listener = (BuffetFragment.FragmentListener) getActivity();
                    listener.onPhotoItemClicked(dao);

                    Toast.makeText(getContext(),"Position:"+itemPosition,Toast.LENGTH_SHORT)
                            .show();
                    Log.e("Position", "" + itemPosition);
                }
            });
        }
        public class MyViewHolder extends RecyclerView.ViewHolder {

            TextView tvName;
            TextView tvDescription;
            ImageView imgView;
            View rootView;


            public MyViewHolder(View itemView) {
                super(itemView);

                tvName = (TextView) itemView.findViewById(R.id.tvName);
                tvDescription = (TextView) itemView.findViewById(R.id.tvDescription);
                imgView = (ImageView) itemView.findViewById(R.id.imgView);
                rootView = (View) itemView.findViewById(R.id.rootView);
            }
        }

        @Override
        public int getItemCount() {
            return listArray.size();

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

}
