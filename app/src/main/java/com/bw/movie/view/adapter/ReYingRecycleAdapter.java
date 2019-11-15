package com.bw.movie.view.adapter;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.RoundedCorners;
import com.bumptech.glide.request.RequestOptions;
import com.bw.movie.R;
import com.bw.movie.model.bean.ReYingBean;
import com.bw.movie.view.activity.AllGouPiaoActivity;
import com.bw.movie.view.activity.MovieDetailsActivity;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>文件描述：<p>
 * <p>作者：吴新仲<p>
 * <p>创建时间：2019/11/7/007<p>
 * <p>更改时间：2019/11/7/007<p>
 */
public class ReYingRecycleAdapter extends RecyclerView.Adapter<ReYingRecycleAdapter.ReYingViewHolder> {

    private List<ReYingBean.ResultBean> mList = new ArrayList<>();

    public void addAdd(List<ReYingBean.ResultBean> mList1) {
        if (mList1 != null)
            this.mList.addAll(mList1);
    }

    @NonNull
    @Override
    public ReYingRecycleAdapter.ReYingViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.reying_recycle_layout, viewGroup, false);
        ReYingViewHolder reYingViewHolder = new ReYingViewHolder(view);
        return reYingViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ReYingRecycleAdapter.ReYingViewHolder reYingViewHolder, int i) {
        reYingViewHolder.ry_name.setText(mList.get(i).name);
        reYingViewHolder.ry_pf.setText(String.valueOf(mList.get(i).score));
        Glide.with(reYingViewHolder.itemView.getContext())
                .load(mList.get(i).imageUrl)
                .apply(RequestOptions.bitmapTransform(new RoundedCorners(30)))
                .into(reYingViewHolder.ry_img);

        reYingViewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(reYingViewHolder.itemView.getContext(), MovieDetailsActivity.class);
                intent.putExtra("movieId", mList.get(i).movieId);
                reYingViewHolder.itemView.getContext().startActivity(intent);
            }
        });

        reYingViewHolder.ry_btn_gp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                reYingViewHolder.itemView.getContext().startActivity(new Intent(reYingViewHolder.itemView.getContext(), AllGouPiaoActivity.class));
            }
        });
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    public class ReYingViewHolder extends RecyclerView.ViewHolder {

        private final ImageView ry_img;
        private final TextView ry_name;
        private final TextView ry_pf;
        private final Button ry_btn_gp;

        public ReYingViewHolder(@NonNull View itemView) {
            super(itemView);
            ry_img = itemView.findViewById(R.id.ry_img);
            ry_name = itemView.findViewById(R.id.ry_name);
            ry_pf = itemView.findViewById(R.id.ry_pf);
            ry_btn_gp = itemView.findViewById(R.id.ry_btn_gp);

        }
    }
}
