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
public class ShangYingRecycleAdapter extends RecyclerView.Adapter<ShangYingRecycleAdapter.ShangYingViewHolder> {

    private List<ShangYingBean.ResultBean> mList = new ArrayList<>();

    public void addAdd(List<ShangYingBean.ResultBean> mList1) {
        if (mList1 != null)
            this.mList.addAll(mList1);
    }

    @NonNull
    @Override
    public ShangYingRecycleAdapter.ShangYingViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.shangying_recycle_layout, viewGroup, false);
        ShangYingViewHolder shangYingViewHolder = new ShangYingViewHolder(view);
        return shangYingViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ShangYingRecycleAdapter.ShangYingViewHolder shangYingViewHolder, int i) {
        shangYingViewHolder.sy_name.setText(mList.get(i).name);
        Date date = new Date(mList.get(i).releaseTime);
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        shangYingViewHolder.sy_time.setText(simpleDateFormat.format(date));
        shangYingViewHolder.sy_ren.setText(String.valueOf(mList.get(i).wantSeeNum+"想看"));
        Glide.with(shangYingViewHolder.itemView.getContext()).load(mList.get(i).imageUrl).into(shangYingViewHolder.sy_img);
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    public class ShangYingViewHolder extends RecyclerView.ViewHolder {

        private final ImageView sy_img;
        private final TextView sy_name;
        private final TextView sy_time;
        private final TextView sy_ren;
        private final Button sy_btn_gp;

        public ShangYingViewHolder(@NonNull View itemView) {
            super(itemView);
            sy_img = itemView.findViewById(R.id.sy_img);
            sy_name = itemView.findViewById(R.id.sy_name);
            sy_time = itemView.findViewById(R.id.sy_time);
            sy_ren = itemView.findViewById(R.id.sy_ren);
            sy_btn_gp = itemView.findViewById(R.id.sy_btn_gp);

        }
    }
}
