package com.zgb.plmm;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.app.ListFragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Window;
import android.view.WindowManager;

import com.zgb.plmm.db.ImgDBManager;
import com.zgb.plmm.db.ImgGroupDBManager;
import com.zgb.plmm.fragment.ShowLargeImgFragment;
import com.zgb.plmm.model.ImgGroup;
import com.zgb.plmm.model.ImgModel;

import java.util.List;

/**
 * Created by zhou on 2017/5/14.
 */

public class ShowLargeImgActivity extends AppCompatActivity {
    private ImgGroup imgGroup;
    private int index;

    private static final String ARG_ImgGroup = "ImgGroup";
    private static final String ARG_Position = "Position";

    private ViewPager viewPager;

    public static void start(Context context, ImgGroup imgGroup, int position) {
        Intent intent = new Intent(context, ShowLargeImgActivity.class);
        intent.putExtra(ARG_Position, position);
        intent.putExtra(ARG_ImgGroup, imgGroup);
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
//        this.requestWindowFeature(Window.FEATURE_NO_TITLE);//去掉标题栏
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,  WindowManager.LayoutParams.FLAG_FULLSCREEN);
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_layge_img);
        initArgs();
        initView();
    }

    private void initArgs() {
        Intent intent = getIntent();
        if (intent != null) {
            imgGroup = (ImgGroup) intent.getSerializableExtra(ARG_ImgGroup);
            index = intent.getIntExtra(ARG_Position, 0);
        }
    }

    private void initView() {
        viewPager = (ViewPager) findViewById(R.id.viewpager);
        viewPager.setAdapter(new PictureSlidePagerAdapter(getSupportFragmentManager()));
        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                Log.e("", "下一页：" + position);
            }

            @Override
            public void onPageSelected(int position) {

            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }

    private class PictureSlidePagerAdapter extends FragmentStatePagerAdapter {

        public PictureSlidePagerAdapter(FragmentManager fm) {
            super(fm);
            imgModelList = ImgDBManager.get().getNeedShowImgModelList(imgGroup);
            startPosition = 0;
            endPosition = imgModelList.size() - 1;
        }

        @Override
        public Fragment getItem(int position) {
            ImgModel imgModel = null;
            if (startPosition < 0 || endPosition < 0) {

            } else if (position >= startPosition && position <= endPosition) {
                imgModel = imgModelList.get(position - startPosition);
            } else if (position < startPosition) {
                List<ImgGroup> imgGroups = ImgGroupDBManager.get().getNeedShowImgGroup(index - 1, 1);
                if (imgGroups.size() > 0) {
                    imgGroup = imgGroups.get(0);
                    imgModelList = ImgDBManager.get().getNeedShowImgModelList(imgGroup);
                    endPosition = startPosition;
                    startPosition = endPosition - imgModelList.size();
                    imgModel = imgModelList.get(position - startPosition);
                }
            } else if (position > endPosition) {
                List<ImgGroup> imgGroups = ImgGroupDBManager.get().getNeedShowImgGroup(index + 1, 1);
                if (imgGroups.size() > 0) {
                    imgGroup = imgGroups.get(0);
                    imgModelList = ImgDBManager.get().getNeedShowImgModelList(imgGroup);
                    startPosition = endPosition;
                    endPosition = endPosition + imgModelList.size();
                    imgModel = imgModelList.get(position - startPosition);
                }
            }
            return ShowLargeImgFragment.newInstance(imgModel);//返回展示不同网络图片的PictureSlideFragment
        }

        @Override
        public int getCount() {
            return Integer.MAX_VALUE;//指定ViewPager的总页数
        }
    }

    private int startPosition = -1;
    private int endPosition = -1;
    private List<ImgModel> imgModelList;

}
