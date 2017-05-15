package com.zgb.plmm;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;

import com.xiaomi.market.sdk.XiaomiUpdateAgent;
import com.zgb.plmm.adapter.ImgGridAdapter;
import com.zgb.plmm.model.ImgGroup;
import com.zgb.plmm.util.SpacesItemDecoration;

public class MainActivity extends AppCompatActivity {

    RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        XiaomiUpdateAgent.update(this);

        recyclerView = (RecyclerView) findViewById(R.id.rv);
//        recyclerView.setLayoutManager(new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL));
        recyclerView.setLayoutManager(new GridLayoutManager(this, 2));
        ImgGridAdapter adapter = new ImgGridAdapter(this);
        recyclerView.setAdapter(adapter);
        //设置item之间的间隔
        SpacesItemDecoration decoration = new SpacesItemDecoration(8);
        recyclerView.addItemDecoration(decoration);
        adapter.setItemClickCallBack(new ImgGridAdapter.ItemClickCallBack() {

            @Override
            public void itemClick(ImgGroup imgGroup, int position) {
                ShowLargeImgActivity.start(MainActivity.this, imgGroup, position);
            }
        });
    }
}
