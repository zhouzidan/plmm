package com.zgb.plmm.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.zgb.plmm.R;
import com.zgb.plmm.db.ImgGroupDBManager;
import com.zgb.plmm.model.ImgGroup;

import java.util.List;

/**
 * Created by zhou on 2017/5/12.
 */

public class ImgGridAdapter extends RecyclerView.Adapter<ImgGridAdapter.ViewHodler> {

    private LayoutInflater inflater;
    private Context mContext;

    public ImgGridAdapter(Context context) {
        this.mContext = context;
        inflater = LayoutInflater.from(mContext);
    }

    @Override
    public ViewHodler onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.item_img, parent, false);
        return new ViewHodler(view);
    }

    @Override
    public void onBindViewHolder(ViewHodler holder, final int position) {
        final ImgGroup imgGroup = getImgGroups(position);
        Glide.with(mContext).load(imgGroup.getFirstImgUrl()).into(holder.imageView);
        holder.imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (itemClickCallBack != null)
                    itemClickCallBack.itemClick(imgGroup, position);
            }
        });
    }

    public ImgGroup getImgGroups(int position) {
        List<ImgGroup> imgGroups = ImgGroupDBManager.get().getNeedShowImgGroup(position, 1);
        if (imgGroups != null && imgGroups.size() > 0)
            return imgGroups.get(0);
        return null;
    }

    @Override
    public int getItemCount() {
        return Integer.MAX_VALUE;
    }

    public static class ViewHodler extends RecyclerView.ViewHolder {
        ImageView imageView;

        public ViewHodler(View itemView) {
            super(itemView);
            imageView = (ImageView) itemView.findViewById(R.id.imageView);
        }
    }

    private ItemClickCallBack itemClickCallBack;

    public void setItemClickCallBack(ItemClickCallBack itemClickCallBack) {
        this.itemClickCallBack = itemClickCallBack;
    }

    public static interface ItemClickCallBack {
        public void itemClick(ImgGroup imgGroup, int position);
    }

}