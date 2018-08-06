package com.asus.sorkkin.sorkkin.view;

import android.content.Context;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.util.AttributeSet;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import com.asus.sorkkin.sorkkin.R;


public class PhotoListItem extends FrameLayout {



    public PhotoListItem(@NonNull Context context) {
        super(context);
        initInflate();
        initInstances();

    }

    public PhotoListItem(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        initInflate();
        initInstances();
    }

    public PhotoListItem(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initInflate();
        initInstances();
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public PhotoListItem(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        initInflate();
        initInstances();
    }

    private void initInflate() {
        inflate(getContext(), R.layout.list_item_photo, this);
    }

    private void initInstances() {

    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int width = MeasureSpec.getSize(widthMeasureSpec);
        int height = width * 4 / 5;
        int newHeightMeasureSpec = MeasureSpec.makeMeasureSpec(
                height,
                MeasureSpec.EXACTLY
        );
        //child View
        super.onMeasure(widthMeasureSpec, newHeightMeasureSpec);
        //self
        setMeasuredDimension(width, height);
    }
}
