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
import com.bw.movie.model.bean.CHaiLeftBean;
import com.bw.movie.model.bean.CTuiJianBean;

import java.util.ArrayList;
import java.util.List;

/**
 * date:19/11/8
 * author:张自磊(lenovo)
 * function:
 */
public class LeftAdapter extends RecyclerView.Adapter<LeftAdapter.LeftViewHoler> {

    private List<CHaiLeftBean.ResultBean> list=new ArrayList<>();

    public void onAddAll(List<CHaiLeftBean.ResultBean> list1){
        if (list1 != null) {
            list.addAll(list1);
        }
    }

    @NonNull
    @Override
    public LeftViewHoler onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View inflate = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.left_adapter, viewGroup, false);
        return new LeftViewHoler(inflate);
    }

    @Override
    public void onBindViewHolder(@NonNull final LeftViewHoler leftViewHoler, final int i) {
        leftViewHoler.leftname.setText(list.get(i).regionName);

        leftViewHoler.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (leftBack != null) {
                    leftBack.onLeft(list.get(i).regionId);
                }
            }
        });

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    class LeftViewHoler extends RecyclerView.ViewHolder {

        private final TextView leftname;

        public LeftViewHoler(@NonNull View itemView) {
            super(itemView);
            leftname = itemView.findViewById(R.id.leftname);
        }
    }

    private LeftBack leftBack;

    public void setLeftBack(LeftBack leftBack) {
        this.leftBack = leftBack;
    }

    public interface LeftBack{
        void onLeft(int isId);
    }

}
