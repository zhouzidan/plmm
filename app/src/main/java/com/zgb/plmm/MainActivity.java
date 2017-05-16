package com.zgb.plmm;

import android.annotation.TargetApi;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.util.Log;

import com.bumptech.glide.GenericRequestBuilder;
import com.bumptech.glide.Glide;
import com.bumptech.glide.ListPreloader;
import com.bumptech.glide.MemoryCategory;
import com.bumptech.glide.RequestManager;
import com.xiaomi.market.sdk.XiaomiUpdateAgent;
import com.zgb.plmm.adapter.ImgGridAdapter;
import com.zgb.plmm.db.ImgGroupDBManager;
import com.zgb.plmm.glide.RecyclerViewPreloader;
import com.zgb.plmm.model.ImgGroup;
import com.zgb.plmm.util.SpacesItemDecoration;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        XiaomiUpdateAgent.update(this);
        Glide.get(this).setMemoryCategory(MemoryCategory.HIGH);

        recyclerView = (RecyclerView) findViewById(R.id.rv);
//        recyclerView.setLayoutManager(new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL));
        recyclerView.setLayoutManager(new GridLayoutManager(this, 2));
        ImgGridAdapter adapter = new ImgGridAdapter(this);
        recyclerView.setAdapter(adapter);
        //设置item之间的间隔
        SpacesItemDecoration decoration = new SpacesItemDecoration(8);
        recyclerView.addItemDecoration(decoration);
        recyclerView.setItemViewCacheSize(0);
        recyclerView.setHasFixedSize(true);
//        recyclerView.addOnScrollListener(new RecyclerViewPreloader<>(this, new ListPreloader.PreloadModelProvider<ImgGroup>() {
//            @Override
//            public List<ImgGroup> getPreloadItems(int position) {
//                return ImgGroupDBManager.get().getNeedShowImgGroup(position, 6);
//            }
//
//            @Override
//            public GenericRequestBuilder getPreloadRequestBuilder(ImgGroup item) {
//                return Glide.with(MainActivity.this).load(item.getFirstImgUrl());
//            }
//        }, new ListPreloader.PreloadSizeProvider<ImgGroup>() {
//            @Override
//            public int[] getPreloadSize(ImgGroup item, int adapterPosition, int perItemPosition) {
//                return new int[]{100, 100};
//            }
//        }, 6));
        adapter.setItemClickCallBack(new ImgGridAdapter.ItemClickCallBack() {

            @Override
            public void itemClick(ImgGroup imgGroup, int position) {
                ShowLargeImgActivity.start(MainActivity.this, imgGroup, position);
            }
        });
    }


    @TargetApi(Build.VERSION_CODES.ICE_CREAM_SANDWICH)
    @Override
    public void onTrimMemory(int level) {
        Log.e("hehe", "onTrimMemory--level:" + level);
        super.onTrimMemory(level);
        Glide.get(this).trimMemory(level);
    }

    @Override
    public void onLowMemory() {
        Log.e("hehe", "onLowMemory");
        super.onLowMemory();
        Glide.get(this).clearMemory();
    }

/*    private RecyclerViewPreloader listPreloader = new RecyclerViewPreloader<ImgGroup>(this, new ListPreloader.PreloadModelProvider<ImgGroup>() {
        @Override
        public List<ImgGroup> getPreloadItems(int position) {
            return ImgGroupDBManager.get().getNeedShowImgGroup(position, 6);
        }

        @Override
        public GenericRequestBuilder getPreloadRequestBuilder(ImgGroup item) {
            return Glide.with(MainActivity.this).load(item.getFirstImgUrl());
        }
    }, new ListPreloader.PreloadSizeProvider<ImgGroup>() {
        @Override
        public int[] getPreloadSize(ImgGroup item, int adapterPosition, int perItemPosition) {
            return new int[]{500, 500};
        }
    }, 10);*/

}
