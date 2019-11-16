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
import com.bw.movie.model.bean.MovieXqBean;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>文件描述：<p>
 * <p>作者：吴新仲<p>
 * <p>创建时间：2019/11/14/014<p>
 * <p>更改时间：2019/11/14/014<p>
 */
public class MovieXqJieshaoYyAapter extends RecyclerView.Adapter<MovieXqJieshaoYyAapter.YyViewHolder> {

    private List<MovieXqBean.ResultBean.MovieActorBean> mMovieActorBeans = new ArrayList<>();

    public void addAll(List<MovieXqBean.ResultBean.MovieActorBean> mMovieActor) {
        if (mMovieActor != null)
            this.mMovieActorBeans.addAll(mMovieActor);
    }

    @NonNull
    @Override
    public MovieXqJieshaoYyAapter.YyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.movie_xq_jieshao_yanyuan_layout, viewGroup, false);
        return new YyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MovieXqJieshaoYyAapter.YyViewHolder dyViewHolder, int i) {
        dyViewHolder.xq_yy_name.setText(mMovieActorBeans.get(i).name);
        dyViewHolder.xq_yy_shiname.setText("饰:  " + mMovieActorBeans.get(i).role);
        Glide.with(dyViewHolder.itemView.getContext()).load(mMovieActorBeans.get(i).photo).into(dyViewHolder.xq_yy_img);
    }

    @Override
    public int getItemCount() {
        return mMovieActorBeans.size();
    }

    public class YyViewHolder extends RecyclerView.ViewHolder {

        private final ImageView xq_yy_img;
        private final TextView xq_yy_name, xq_yy_shiname;

        public YyViewHolder(@NonNull View itemView) {
            super(itemView);
            xq_yy_img = itemView.findViewById(R.id.xq_yy_img);
            xq_yy_name = itemView.findViewById(R.id.xq_yy_name);
            xq_yy_shiname = itemView.findViewById(R.id.xq_yy_shiname);
        }
    }
}
