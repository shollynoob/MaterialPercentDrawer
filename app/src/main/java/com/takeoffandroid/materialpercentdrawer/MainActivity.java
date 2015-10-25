package com.takeoffandroid.materialpercentdrawer;

import android.app.Activity;
import android.content.res.TypedArray;
import android.os.Bundle;
import android.support.percent.PercentRelativeLayout;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.view.animation.Animation;
import android.view.animation.TranslateAnimation;

import java.util.ArrayList;


public class MainActivity extends Activity implements View.OnClickListener,OnListItemClickListener {

    private RecyclerView mRecyclerView;
    private PercentRelativeLayout mPercentRelativeLayout;

    private MainAdapter mAdapter;
    private ArrayList<MainModel> mMainList = new ArrayList<MainModel> ();

    private int[] colors;
    private String[] names;
    private TypedArray avatarIcons;


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mRecyclerView = (RecyclerView)findViewById(R.id.list);
        mPercentRelativeLayout = (PercentRelativeLayout)findViewById(R.id.percent_drawer);

        colors = getResources ().getIntArray(R.array.color_codes);
        names = getResources ().getStringArray(R.array.names);
        avatarIcons = getResources().obtainTypedArray(R.array.avator_icons);

        for(int i = 0; i<names.length; i++) {
            MainModel mainModel = new MainModel();
            mainModel.setColors(colors[i]);
            mainModel.setNames(names[i]);
            mainModel.setAvatorIcons(avatarIcons.getDrawable(i));
            mMainList.add (mainModel);
        }

        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mRecyclerView.setItemAnimator(new DefaultItemAnimator());
        mRecyclerView.addItemDecoration(
                new DividerItemDecoration(MainActivity.this));

        mAdapter = new MainAdapter(this,mMainList);
        mRecyclerView.setAdapter(mAdapter);
    }


    @Override
    public void onClick(View view) {

    }



    private Animation slideInAnimation() {

        Animation inFromRight = new TranslateAnimation(
                Animation.RELATIVE_TO_PARENT,  +1.0f, Animation.RELATIVE_TO_PARENT,  0.0f,
                Animation.RELATIVE_TO_PARENT,  0.0f, Animation.RELATIVE_TO_PARENT,   0.0f
        );
        inFromRight.setDuration(500);
        inFromRight.setInterpolator(new AccelerateDecelerateInterpolator());
        return inFromRight;
    }

    private Animation slideOutAnimation() {
        Animation outtoRight = new TranslateAnimation(
                Animation.RELATIVE_TO_PARENT,  0.0f, Animation.RELATIVE_TO_PARENT,  +1.0f,
                Animation.RELATIVE_TO_PARENT,  0.0f, Animation.RELATIVE_TO_PARENT,   0.0f
        );
        outtoRight.setDuration(500);
        outtoRight.setInterpolator(new AccelerateDecelerateInterpolator());
        return outtoRight;
    }

    @Override
    public void openDrawer(View v, int position, MainModel mainModel) {

        mPercentRelativeLayout.setAnimation(slideInAnimation());
        mPercentRelativeLayout.setVisibility(View.VISIBLE);
    }

    @Override
    public void closeDrawer(View v, int position, MainModel mainModel) {

        mPercentRelativeLayout.setAnimation(slideOutAnimation());
        mPercentRelativeLayout.setVisibility(View.GONE);
    }
}
