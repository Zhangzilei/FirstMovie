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
import com.bw.movie.model.bean.CHaiRightBean;
import com.bw.movie.model.bean.CTuiJianBean;

import java.util.ArrayList;
import java.util.List;

/**
 * date:19/11/8
 * author:张自磊(lenovo)
 * function:
 */
public class RightAdapter extends RecyclerView.Adapter<RightAdapter.RightViewHodler> {

    private List<CHaiRightBean.ResultBean> list=new ArrayList<>();

    public void onAddAll(List<CHaiRightBean.ResultBean> list1){
        if (list1 != null) {
            list.addAll(list1);
        }
    }

    @NonNull
    @Override
    public RightViewHodler onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View inflate = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.right_adapter, viewGroup, false);
        return new RightViewHodler(inflate);
    }

    @Override
    public void onBindViewHolder(@NonNull RightViewHodler rightViewHoler, int i) {
        rightViewHoler.rightname.setText(list.get(i).name);

        rightViewHoler.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (rightBack != null) {
                    rightBack.onRightId(list.get(i).id);
                }
            }
        });

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    class RightViewHodler extends RecyclerView.ViewHolder {

        private final TextView rightname;

        public RightViewHodler(@NonNull View itemView) {
            super(itemView);
            rightname = itemView.findViewById(R.id.rightname);
        }
    }

    private RightBack rightBack;

    public void setRightBack(RightBack rightBack) {
        this.rightBack = rightBack;
    }

    public interface RightBack{
        void onRightId(int rightid);
    }

}
