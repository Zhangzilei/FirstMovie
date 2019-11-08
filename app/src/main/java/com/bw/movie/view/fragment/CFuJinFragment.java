package com.bw.movie.view.fragment;


import android.support.v7.widget.LinearLayoutManager;
import android.view.View;

import com.bw.movie.R;
import com.bw.movie.constraint.Constraint;
import com.bw.movie.model.bean.CTuiJianBean;
import com.bw.movie.presenter.CFuJinPresenter;
import com.bw.movie.view.adapter.CTuiJianAdapter;
import com.jcodecraeer.xrecyclerview.XRecyclerView;

import java.util.List;

/**
 * date:19/11/7
 * author:张自磊(lenovo)
 * function:
 */
public class CFuJinFragment extends BaseFragment<CFuJinPresenter> implements Constraint.TuiJianView {

    private XRecyclerView frecycler;
    private CTuiJianAdapter tuiJianAdapter;
    int page=1;
    int userId=0;
    String sessionId=null;

    @Override
    public void tuijianSuccess(CTuiJianBean cTuiJianBean) {
        List<CTuiJianBean.ResultBean> tuiJians = cTuiJianBean.result;
        tuiJianAdapter.onAddAll(tuiJians);

        tuiJianAdapter.notifyDataSetChanged();
    }

    @Override
    public void tuijianError(String s) {

    }

    @Override
    void initData() {
        presenter.FuJin(userId,sessionId,null,null,page,10);

        LinearLayoutManager linearLayoutManager=new LinearLayoutManager(getContext());
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        frecycler.setLayoutManager(linearLayoutManager);
        tuiJianAdapter = new CTuiJianAdapter();
        frecycler.setAdapter(tuiJianAdapter);

        frecycler.setLoadingListener(new XRecyclerView.LoadingListener() {
            @Override
            public void onRefresh() {
                page=1;
                presenter.FuJin(userId,sessionId,null,null,page,10);
                frecycler.refreshComplete();
            }

            @Override
            public void onLoadMore() {
                page++;
                presenter.FuJin(userId,sessionId,null,null,page,10);
                frecycler.loadMoreComplete();
            }
        });
    }

    @Override
    CFuJinPresenter getPresenter() {
        return new CFuJinPresenter();
    }

    @Override
    void initListener() {

    }

    @Override
    int initLayout() {
        return R.layout.cfujin_fragment;
    }

    @Override
    void initView(View view) {
        frecycler = view.findViewById(R.id.frecycler);
    }
}
