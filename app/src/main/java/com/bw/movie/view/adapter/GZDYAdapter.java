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
import com.bw.movie.model.bean.GuanZhuDianYingBean;
import com.bw.movie.model.bean.GuanZhuYingYuanBean;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>文件描述：<p>
 * <p>作者：张自磊<p>
 * <p>创建时间：2019/11/15<p>
 * <p>更改时间：2019/11/15<p>
 */
public class GZDYAdapter extends RecyclerView.Adapter<GZDYAdapter.GZDYViewHodle> {

    List<GuanZhuDianYingBean.ResultBean> list=new ArrayList<>();

    public void GXYYaddAll(List<GuanZhuDianYingBean.ResultBean> li){
        if (li != null) {
            list.addAll(li);
        }
    }

    @NonNull
    @Override
    public GZDYViewHodle onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View inflate = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.gzdy_adapter, viewGroup, false);
        return new GZDYViewHodle(inflate);
    }

    @Override
    public void onBindViewHolder(@NonNull GZDYViewHodle gzdyViewHodle, int i) {
        Glide.with(gzdyViewHodle.itemView.getContext()).load(list.get(i).imageUrl).into(gzdyViewHodle.gzdyimg);
        gzdyViewHodle.name.setText(list.get(i).name);
        gzdyViewHodle.gzdydy.setText(list.get(i).director);
        gzdyViewHodle.gzdyyy.setText(list.get(i).starring);
        gzdyViewHodle.gzdypf.setText(list.get(i).score+"");
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    class GZDYViewHodle extends RecyclerView.ViewHolder {

        private final ImageView gzdyimg;
        private final TextView name,gzdydy,gzdyyy,gzdypf;

        public GZDYViewHodle(@NonNull View itemView) {
            super(itemView);
            gzdyimg = itemView.findViewById(R.id.gzdyimg);
            name = itemView.findViewById(R.id.gzdyname);
            gzdydy = itemView.findViewById(R.id.gzdydy);
            gzdyyy = itemView.findViewById(R.id.gzdyyy);
            gzdypf = itemView.findViewById(R.id.gzdypf);
        }
    }
}
