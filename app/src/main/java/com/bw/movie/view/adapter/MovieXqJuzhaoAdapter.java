package com.bw.movie.view.adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.RoundedCorners;
import com.bumptech.glide.request.RequestOptions;
import com.bw.movie.R;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>文件描述：<p>
 * <p>作者：吴新仲<p>
 * <p>创建时间：2019/11/14/014<p>
 * <p>更改时间：2019/11/14/014<p>
 */
public class MovieXqJuzhaoAdapter extends RecyclerView.Adapter<MovieXqJuzhaoAdapter.JuzhaoViewHolder> {

    private List<String> mResultBeans1 = new ArrayList<>();

    public void addAll1(List<String> mResult1) {
        if (mResult1 != null)
            this.mResultBeans1.addAll(mResult1);
    }

    @NonNull
    @Override
    public MovieXqJuzhaoAdapter.JuzhaoViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.movie_xq_juzhao_layout, viewGroup, false);
        return new JuzhaoViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MovieXqJuzhaoAdapter.JuzhaoViewHolder juzhaoViewHolder, int i) {

        Glide.with(juzhaoViewHolder.itemView.getContext())
                .load(mResultBeans1.get(i))
                .apply(RequestOptions.bitmapTransform(new RoundedCorners(10)))
                .into(juzhaoViewHolder.juzhao_img);
    }

    @Override
    public int getItemCount() {
        return mResultBeans1.size();
    }

    public class JuzhaoViewHolder extends RecyclerView.ViewHolder {

        private final ImageView juzhao_img;

        public JuzhaoViewHolder(@NonNull View itemView) {
            super(itemView);
            juzhao_img = itemView.findViewById(R.id.juzhao_img);
        }
    }
}
