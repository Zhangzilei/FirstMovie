package com.bw.movie.view.fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bw.movie.R;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.Map;

/**
 * date:19/11/9
 * author:张自磊(lenovo)
 * function:
 */
public class YYOneFragment extends Fragment {
    private ImageView oneimgad;
    private TextView onead;
    private ImageView oneimgph;
    private TextView oneph;
    private TextView onexian;
    private TextView onefangshi;
    private String address;
    private String vehic;
    private String phone;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View inflate = inflater.inflate(R.layout.yy_one_fragment, container, false);
        initView(inflate);
        EventBus.getDefault().register(this);
        return inflate;
    }

    @Subscribe(threadMode = ThreadMode.MAIN,sticky = true)
    public void getData(Map<String,String> map){
        address = map.get("address");
        vehic = map.get("vehic");
        phone = map.get("phone");
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        onead.setText(address);
        onefangshi.setText(vehic);
        oneph.setText(phone);
    }

    private void initView(View inflate) {
        oneimgad = (ImageView) inflate.findViewById(R.id.oneimgad);
        onead = (TextView) inflate.findViewById(R.id.onead);
        oneimgph = (ImageView) inflate.findViewById(R.id.oneimgph);
        oneph = (TextView) inflate.findViewById(R.id.oneph);
        onexian = (TextView) inflate.findViewById(R.id.onexian);
        onefangshi = (TextView) inflate.findViewById(R.id.onefangshi);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        EventBus.getDefault().unregister(this);
    }
}
