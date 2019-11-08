package com.bw.movie.view.adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bw.movie.R;
import com.bw.movie.model.bean.PopularMovieBean;
import com.bw.movie.model.bean.ShangYingBean;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * <p>文件描述：<p>
 * <p>作者：吴新仲<p>
 * <p>创建时间：2019/11/7/007<p>
 * <p>更改时间：2019/11/7/007<p>
 */
public class PopularRecycleAdapter extends RecyclerView.Adapter<PopularRecycleAdapter.PopularViewHolder> {

    private List<PopularMovieBean.ResultBean> mList = new ArrayList<>();

    public void addAdd(List<PopularMovieBean.ResultBean> mList1) {
        if (mList1 != null)
            this.mList.addAll(mList1);
    }

    @NonNull
    @Override
    public PopularRecycleAdapter.PopularViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.popular_last_recycle_layout, viewGroup, false);
        PopularViewHolder popularViewHolder = new PopularViewHolder(view);
        return popularViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull PopularRecycleAdapter.PopularViewHolder popularViewHolder, int i) {
        popularViewHolder.pop_last_name.setText(mList.get(i).name);
        popularViewHolder.pop_last_pf.setText(String.valueOf(mList.get(i).score));
        Glide.with(popularViewHolder.itemView.getContext()).load(mList.get(i).imageUrl).into(popularViewHolder.pop_last_img);
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    public class PopularViewHolder extends RecyclerView.ViewHolder {

        private final ImageView pop_last_img;
        private final TextView pop_last_name;
        private final TextView pop_last_pf;
        private final Button pop_last_btn_gp;

        public PopularViewHolder(@NonNull View itemView) {
            super(itemView);
            pop_last_img = itemView.findViewById(R.id.pop_last_img);
            pop_last_name = itemView.findViewById(R.id.pop_last_name);
            pop_last_pf = itemView.findViewById(R.id.pop_last_pf);
            pop_last_btn_gp = itemView.findViewById(R.id.pop_last_btn_gp);

        }
    }
}
