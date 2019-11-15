package com.bw.movie.view.adapter;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bw.movie.R;
import com.bw.movie.model.bean.PaiQiBean;
import com.bw.movie.model.bean.SearchBean;
import com.bw.movie.view.activity.AllGouPiaoActivity;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>文件描述：<p>
 * <p>作者：张自磊<p>
 * <p>创建时间：2019/11/14<p>
 * <p>更改时间：2019/11/14<p>
 */
public class PaiQiAdapter extends RecyclerView.Adapter<PaiQiAdapter.PaiQiViewHodler> {

    List<PaiQiBean.ResultBean> list=new ArrayList<>();

    public void PaiQiAddAll(List<PaiQiBean.ResultBean> li){
        if (li != null) {
            list.addAll(li);
        }
    }

    @NonNull
    @Override
    public PaiQiViewHodler onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View inflate = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.search_movie_layout, viewGroup, false);
        return new PaiQiViewHodler(inflate);
    }

    @Override
    public void onBindViewHolder(@NonNull PaiQiViewHodler paiQiViewHodler, int i) {
        PaiQiBean.ResultBean resultBean = list.get(i);
        paiQiViewHodler.search_movie_name.setText(resultBean.name);
        paiQiViewHodler.search_movie_dy.setText("导演 : " + resultBean.director);
        paiQiViewHodler.search_movie_yy.setText("主演 : " + resultBean.starring);
        paiQiViewHodler.search_movie_pf.setText(String.valueOf("评分 : " + resultBean.score + "分"));
        Glide.with(paiQiViewHodler.itemView.getContext()).load(resultBean.imageUrl).into(paiQiViewHodler.search_movie_img);

        paiQiViewHodler.search_movie_shop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                paiQiViewHodler.itemView.getContext().startActivity(new Intent(paiQiViewHodler.itemView.getContext(), AllGouPiaoActivity.class));
            }
        });

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    class PaiQiViewHodler extends RecyclerView.ViewHolder {

        private final ImageView search_movie_img;
        private final TextView search_movie_name;
        private final TextView search_movie_dy;
        private final TextView search_movie_yy;
        private final TextView search_movie_pf;
        private final TextView search_movie_shop;

        public PaiQiViewHodler(@NonNull View itemView) {
            super(itemView);
            search_movie_img = itemView.findViewById(R.id.search_movie_img);
            search_movie_name = itemView.findViewById(R.id.search_movie_name);
            search_movie_dy = itemView.findViewById(R.id.search_movie_dy);
            search_movie_yy = itemView.findViewById(R.id.search_movie_yy);
            search_movie_pf = itemView.findViewById(R.id.search_movie_pf);
            search_movie_shop = itemView.findViewById(R.id.search_movie_shop);
        }
    }
}
