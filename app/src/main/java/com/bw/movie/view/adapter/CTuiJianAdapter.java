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
import com.bw.movie.model.bean.CTuiJianBean;

import java.util.ArrayList;
import java.util.List;

/**
 * date:19/11/8
 * author:张自磊(lenovo)
 * function:
 */
public class CTuiJianAdapter extends RecyclerView.Adapter<CTuiJianAdapter.TuiJianViewHoler> {

    private List<CTuiJianBean.ResultBean> list=new ArrayList<>();

    public void onAddAll(List<CTuiJianBean.ResultBean> list1){
        if (list1 != null) {
            list.addAll(list1);
        }
    }

    @NonNull
    @Override
    public TuiJianViewHoler onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View inflate = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.ctuijian_adapter, viewGroup, false);
        return new TuiJianViewHoler(inflate);
    }

    @Override
    public void onBindViewHolder(@NonNull TuiJianViewHoler tuiJianViewHoler, int i) {
        tuiJianViewHoler.tuijianaddress.setText(list.get(i).address);
        tuiJianViewHoler.tuijianname.setText(list.get(i).name);
        Glide.with(tuiJianViewHoler.itemView.getContext()).load(list.get(i).logo)
                .into(tuiJianViewHoler.tuijianimg);
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    class TuiJianViewHoler extends RecyclerView.ViewHolder {

        private final ImageView tuijianimg;
        private final TextView tuijianname,tuijianaddress;

        public TuiJianViewHoler(@NonNull View itemView) {
            super(itemView);
            tuijianimg = itemView.findViewById(R.id.tuijian_img);
            tuijianname = itemView.findViewById(R.id.tuijian_name);
            tuijianaddress = itemView.findViewById(R.id.tuijian_address);
        }
    }
}
