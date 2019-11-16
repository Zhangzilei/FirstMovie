package com.bw.movie.view.adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bw.movie.R;
import com.bw.movie.model.bean.GuanZhuYingYuanBean;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>文件描述：<p>
 * <p>作者：张自磊<p>
 * <p>创建时间：2019/11/15<p>
 * <p>更改时间：2019/11/15<p>
 */
public class GZYYAdapter extends RecyclerView.Adapter<GZYYAdapter.GZYYViewHodle> {

    List<GuanZhuYingYuanBean.ResultBean> list=new ArrayList<>();

    public void GXYYaddAll(List<GuanZhuYingYuanBean.ResultBean> li){
        if (li != null) {
            list.addAll(li);
        }
    }

    @NonNull
    @Override
    public GZYYViewHodle onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View inflate = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.gzyy_adapter, viewGroup, false);
        return new GZYYViewHodle(inflate);
    }

    @Override
    public void onBindViewHolder(@NonNull GZYYViewHodle gzyyViewHodle, int i) {
        Glide.with(gzyyViewHodle.itemView.getContext()).load(list.get(i).logo).into(gzyyViewHodle.img);
        gzyyViewHodle.name.setText(list.get(i).name);
        gzyyViewHodle.gzyyaddress.setText(list.get(i).address);
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    class GZYYViewHodle extends RecyclerView.ViewHolder {

        private final ImageView img;
        private final TextView name,gzyyaddress;

        public GZYYViewHodle(@NonNull View itemView) {
            super(itemView);
            img = itemView.findViewById(R.id.gzyyimg);
            name = itemView.findViewById(R.id.gzyyname);
            gzyyaddress = itemView.findViewById(R.id.gzyyaddress);
        }
    }
}
