package com.bw.movie.view.adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.bw.movie.R;
import com.bw.movie.model.bean.MovieXqYingpingBean;
import com.jcodecraeer.xrecyclerview.XRecyclerView;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * <p>文件描述：<p>
 * <p>作者：吴新仲<p>
 * <p>创建时间：2019/11/15/015<p>
 * <p>更改时间：2019/11/15/015<p>
 */
public class MovieXqYpAdapter extends XRecyclerView.Adapter<MovieXqYpAdapter.YpViewHoler> {

    private List<MovieXqYingpingBean.ResultBean> mResultBean = new ArrayList<>();

    public void addAll(List<MovieXqYingpingBean.ResultBean> mResult) {
        if (mResult != null)
            this.mResultBean.addAll(mResult);
    }

    @NonNull
    @Override
    public MovieXqYpAdapter.YpViewHoler onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.movie_xq_yingping_adapter_layout, viewGroup, false);
        return new YpViewHoler(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MovieXqYpAdapter.YpViewHoler ypViewHoler, int i) {
        MovieXqYingpingBean.ResultBean resultBean = mResultBean.get(i);
        Glide.with(ypViewHoler.itemView.getContext())
                .load(resultBean.commentHeadPic)
                .apply(RequestOptions.circleCropTransform())
                .into(ypViewHoler.xq_yingping_img);
        ypViewHoler.xq_yingping_name.setText(resultBean.commentUserName);
        ypViewHoler.xq_yingping_pf.setText(resultBean.score + "分");
        ypViewHoler.xq_yingping_bar.setRating((float) resultBean.score);
        ypViewHoler.xq_yingping_bar.setStepSize(0.5f);
        ypViewHoler.xq_yingping_bar.setMax(10);

        Date date = new Date(resultBean.commentTime);
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd  HH:mm");
        ypViewHoler.xq_yingping_time.setText(simpleDateFormat.format(date));

        ypViewHoler.xq_yingping_pinglun.setText(resultBean.commentContent);

        if (ypViewHoler.xq_yingping_check_zan.isChecked()) {
            ypViewHoler.xq_yingping_sum_zan.setText(String.valueOf(resultBean.greatNum + 1));
        } else {
            ypViewHoler.xq_yingping_sum_zan.setText(String.valueOf(resultBean.greatNum));
        }

        ypViewHoler.xq_yingping_henzan.setText(String.valueOf(resultBean.replyNum));

    }

    @Override
    public int getItemCount() {
        return mResultBean.size();
    }

    public void clear() {
        mResultBean.clear();
    }

    public class YpViewHoler extends RecyclerView.ViewHolder {

        private final ImageView xq_yingping_img;
        private final TextView xq_yingping_name;
        private final RatingBar xq_yingping_bar;
        private final TextView xq_yingping_pf;
        private final TextView xq_yingping_time;
        private final TextView xq_yingping_pinglun;
        private final CheckBox xq_yingping_check_zan;
        private final TextView xq_yingping_sum_zan;
        private final TextView xq_yingping_henzan;

        public YpViewHoler(@NonNull View itemView) {
            super(itemView);
            xq_yingping_img = itemView.findViewById(R.id.xq_yingping_img);
            xq_yingping_name = itemView.findViewById(R.id.xq_yingping_name);
            xq_yingping_bar = itemView.findViewById(R.id.xq_yingping_bar);
            xq_yingping_pf = itemView.findViewById(R.id.xq_yingping_pf);
            xq_yingping_time = itemView.findViewById(R.id.xq_yingping_time);
            xq_yingping_pinglun = itemView.findViewById(R.id.xq_yingping_pinglun);
            xq_yingping_check_zan = itemView.findViewById(R.id.xq_yingping_check_zan);
            xq_yingping_sum_zan = itemView.findViewById(R.id.xq_yingping_sum_zan);
            xq_yingping_henzan = itemView.findViewById(R.id.xq_yingping_henzan);
        }
    }
}
