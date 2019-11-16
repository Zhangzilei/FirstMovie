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
public class MovieXqJieshaoDyAapter extends RecyclerView.Adapter<MovieXqJieshaoDyAapter.DyViewHolder> {

    private List<MovieXqBean.ResultBean.MovieDirectorBean> mDirectorBeans = new ArrayList<>();

    public void addAll(List<MovieXqBean.ResultBean.MovieDirectorBean> mDirector) {
        if (mDirector != null)
            this.mDirectorBeans.addAll(mDirector);
    }

    @NonNull
    @Override
    public MovieXqJieshaoDyAapter.DyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.movie_xq_jieshao_daoyan_layout, viewGroup, false);
        return new DyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MovieXqJieshaoDyAapter.DyViewHolder dyViewHolder, int i) {
        dyViewHolder.dy_name.setText(mDirectorBeans.get(i).name);
        Glide.with(dyViewHolder.itemView.getContext()).load(mDirectorBeans.get(i).photo).into(dyViewHolder.dy_img);
    }

    @Override
    public int getItemCount() {
        return mDirectorBeans.size();
    }

    public class DyViewHolder extends RecyclerView.ViewHolder {

        private final ImageView dy_img;
        private final TextView dy_name;

        public DyViewHolder(@NonNull View itemView) {
            super(itemView);
            dy_img = itemView.findViewById(R.id.dy_img);
            dy_name = itemView.findViewById(R.id.dy_name);
        }
    }
}
