package com.zgb.plmm.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.zgb.plmm.R;
import com.zgb.plmm.model.ImgGroup;
import com.zgb.plmm.model.ImgModel;

public class ShowLargeImgFragment extends Fragment {
    private static final String ARG_ImgModel = "ImgModel";
    private ImgModel imgModel;
    private ImageView imageView;

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @return A new instance of fragment ShowLargeImgFragment.
     */
    public static ShowLargeImgFragment newInstance(ImgModel imgModel) {
        ShowLargeImgFragment fragment = new ShowLargeImgFragment();
        Bundle args = new Bundle();
        args.putSerializable(ARG_ImgModel, imgModel);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            imgModel = (ImgModel) getArguments().getSerializable(ARG_ImgModel);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_large_img, container, false);
        initView(view);
        return view;
    }

    private void initView(View view) {
        imageView = (ImageView) view.findViewById(R.id.img);
        Glide.with(getContext()).load(imgModel.getUrl()).into(imageView);
    }
}
