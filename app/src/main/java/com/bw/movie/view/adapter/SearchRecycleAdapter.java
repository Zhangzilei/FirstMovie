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
import com.bw.movie.model.bean.SearchBean;
import com.jcodecraeer.xrecyclerview.XRecyclerView;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>文件描述：<p>
 * <p>作者：吴新仲<p>
 * <p>创建时间：2019/11/12/012<p>
 * <p>更改时间：2019/11/12/012<p>
 */
public class SearchRecycleAdapter extends XRecyclerView.Adapter<SearchRecycleAdapter.SearchViewHolder> {

    private List<SearchBean.ResultBean> mList = new ArrayList<>();

    public void addAll(List<SearchBean.ResultBean> mList1) {
        if (mList1 != null)
            this.mList.addAll(mList1);
    }

    @NonNull
    @Override
    public SearchRecycleAdapter.SearchViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.search_movie_layout, viewGroup, false);
        return new SearchViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull SearchRecycleAdapter.SearchViewHolder searchViewHolder, int i) {
        SearchBean.ResultBean resultBean = mList.get(i);
        searchViewHolder.search_movie_name.setText("" + resultBean.name);
        searchViewHolder.search_movie_dy.setText("导演 : " + resultBean.director);
        searchViewHolder.search_movie_yy.setText("主演 : " + resultBean.starring);
        searchViewHolder.search_movie_pf.setText(String.valueOf("评分 : " + resultBean.score + "分"));
        Glide.with(searchViewHolder.itemView.getContext()).load(resultBean.imageUrl).into(searchViewHolder.search_movie_img);
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    public class SearchViewHolder extends RecyclerView.ViewHolder {

        private final ImageView search_movie_img;
        private final TextView search_movie_name;
        private final TextView search_movie_dy;
        private final TextView search_movie_yy;
        private final TextView search_movie_pf;
        private final TextView search_movie_shop;

        public SearchViewHolder(@NonNull View itemView) {
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
