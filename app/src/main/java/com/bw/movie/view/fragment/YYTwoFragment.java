package com.bw.movie.view.fragment;


import android.support.v7.widget.LinearLayoutManager;
import android.view.View;
import android.widget.Toast;

import com.bw.movie.R;
import com.bw.movie.constraint.Constraint;
import com.bw.movie.model.bean.YYPingLunBean;
import com.bw.movie.presenter.YYPingLunPresenter;
import com.bw.movie.view.adapter.YYTwoFragmentAdapter;
import com.jcodecraeer.xrecyclerview.XRecyclerView;

import java.util.List;

/**
 * date:19/11/9
 * author:张自磊(lenovo)
 * function:
 */
public class YYTwoFragment extends BaseFragment<YYPingLunPresenter> implements Constraint.YYPingJiaView {
    private XRecyclerView twoxrecycler;
    private YYTwoFragmentAdapter twoFragmentAdapter;

    @Override
    void initData() {


        LinearLayoutManager linearLayoutManager=new LinearLayoutManager(getContext());
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        twoxrecycler.setLayoutManager(linearLayoutManager);
        twoFragmentAdapter = new YYTwoFragmentAdapter();
        twoxrecycler.setAdapter(twoFragmentAdapter);

        presenter.PingJia(13766,"157329010384313766",1,1,5);
    }

    @Override
    YYPingLunPresenter getPresenter() {
        return new YYPingLunPresenter();
    }

    @Override
    void initListener() {

    }

    @Override
    int initLayout() {
        return R.layout.yy_two_fragment;
    }

    protected void initView(View inflate) {
        twoxrecycler = (XRecyclerView) inflate.findViewById(R.id.twoxrecycler);
    }

    @Override
    public void pinglunSuccess(YYPingLunBean yyPingLunBean) {
        List<YYPingLunBean.ResultBean> result = yyPingLunBean.result;

        twoFragmentAdapter.onAddAll(result);

        twoFragmentAdapter.notifyDataSetChanged();
    }

    @Override
    public void pinglunError(String s) {
        Toast.makeText(getContext(), s, Toast.LENGTH_SHORT).show();
    }
}
