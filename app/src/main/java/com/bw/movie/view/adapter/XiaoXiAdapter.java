package com.bw.movie.view.adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.bw.movie.R;
import com.bw.movie.model.bean.XiaoXiBean;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * <p>文件描述：<p>
 * <p>作者：张自磊<p>
 * <p>创建时间：2019/11/13<p>
 * <p>更改时间：2019/11/13<p>
 */
public class XiaoXiAdapter extends RecyclerView.Adapter<XiaoXiAdapter.XXViewHodle> {

    List<XiaoXiBean.ResultBean> list=new ArrayList<>();

    public void XXAddAll(List<XiaoXiBean.ResultBean> list1){
        if (list1!=null){
            list.addAll(list1);
        }
    }

    @NonNull
    @Override
    public XXViewHodle onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View inflate = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.xiaoxi_adapter, viewGroup, false);
        return new XXViewHodle(inflate);
    }

    @Override
    public void onBindViewHolder(@NonNull XXViewHodle xxViewHodle, int i) {
        xxViewHodle.adcontent.setText(list.get(i).content);
        xxViewHodle.adname.setText(list.get(i).title);

        Date date=new Date(list.get(i).pushTime);
        SimpleDateFormat simpleDateFormat=new SimpleDateFormat("yyyy-MM-dd");
        xxViewHodle.adtime.setText(simpleDateFormat.format(date));
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    class XXViewHodle extends RecyclerView.ViewHolder {

        private final TextView adname,adtime,adcontent;

        public XXViewHodle(@NonNull View itemView) {
            super(itemView);
            adname = itemView.findViewById(R.id.adname);
            adtime = itemView.findViewById(R.id.adtime);
            adcontent = itemView.findViewById(R.id.adcontent);
        }
    }
}
