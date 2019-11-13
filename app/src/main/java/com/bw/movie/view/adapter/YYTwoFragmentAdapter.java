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
import com.bw.movie.constraint.Constraint;
import com.bw.movie.model.bean.YYGuanZhuBean;
import com.bw.movie.model.bean.YYPingLunBean;
import com.jcodecraeer.xrecyclerview.XRecyclerView;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * date:19/11/9
 * author:张自磊(lenovo)
 * function:
 */
public class YYTwoFragmentAdapter extends XRecyclerView.Adapter<YYTwoFragmentAdapter.TwoViewHodler> implements Constraint.YYDianZanView {

    private List<YYPingLunBean.ResultBean> list=new ArrayList<>();
    private YYDianZanPresenter yyDianZanPresenter;

    public void onAddAll(List<YYPingLunBean.ResultBean> li){
        if (li != null) {
            list.addAll(li);
        }
    }

    @NonNull
    @Override
    public TwoViewHodler onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View inflate = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.yy_two_adapter, viewGroup, false);
        yyDianZanPresenter = new YYDianZanPresenter();
        return new TwoViewHodler(inflate);
    }

    @Override
    public void onBindViewHolder(@NonNull TwoViewHodler twoViewHodler, int i) {

        twoViewHodler.twoname.setText(list.get(i).commentUserName);
        Glide.with(twoViewHodler.itemView.getContext()).load(list.get(i).commentHeadPic)
                .into(twoViewHodler.twoimg);
        Date date=new Date(list.get(i).commentTime);
        SimpleDateFormat simpleDateFormat=new SimpleDateFormat("yyyy-MM-dd");
        twoViewHodler.twotime.setText(simpleDateFormat.format(date));
        twoViewHodler.twopinglun.setText(list.get(i).commentContent);

        twoViewHodler.twozan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                yyDianZanPresenter.YYdianzan(13766,"157329010384313766",list.get(i).commentId);
            }
        });

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    @Override
    public void dianzanSuccess(YYGuanZhuBean guanZhuBean) {
    }

    @Override
    public void dianzanError(String s) {

    }

    class TwoViewHodler extends RecyclerView.ViewHolder {

        private final ImageView twoimg,twozan;
        private final TextView twoname,twopinglun,twotime;

        public TwoViewHodler(@NonNull View itemView) {
            super(itemView);
            twoimg = itemView.findViewById(R.id.twoimg);
            twoname = itemView.findViewById(R.id.twoname);
            twopinglun = itemView.findViewById(R.id.twopinglun);
            twotime = itemView.findViewById(R.id.twotime);
            twozan = itemView.findViewById(R.id.twozan);
        }
    }
}
