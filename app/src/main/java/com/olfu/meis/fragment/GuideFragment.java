package com.olfu.meis.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.olfu.meis.R;

import static android.content.ContentValues.TAG;

public class GuideFragment extends Fragment {

    public int background[] = {R.drawable.guide1, R.drawable.guide2, R.drawable.guide3,
            R.drawable.guide4, R.drawable.guide5, R.drawable.guide6, R.drawable.guide7,
            R.drawable.guide8, R.drawable.guide9, R.drawable.guide10, R.drawable.guide11};

    int page = 0;

//    public static GuideFragment newInstance(int page) {
//        GuideFragment fragment = new GuideFragment();
//        Bundle args = new Bundle();
//        args.putInt("someInt", page);
//        return fragment;
//    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_guide, container, false);
        ImageView iv = (ImageView) view.findViewById(R.id.background);
        page = getArguments().getInt("page", 0);
        Log.d(TAG, "Count: " + page);
        iv.setImageResource(background[page]);
        return view;
    }

}