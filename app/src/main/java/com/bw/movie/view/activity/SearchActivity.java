package com.bw.movie.view.activity;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bw.movie.R;
import com.bw.movie.constraint.Constraint;
import com.bw.movie.model.bean.SearchBean;
import com.bw.movie.presenter.SearchPresenter;
import com.bw.movie.view.adapter.SearchRecycleAdapter;
import com.jcodecraeer.xrecyclerview.XRecyclerView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class SearchActivity extends BaseActivity<SearchPresenter> implements Constraint.ISearchView {

    @BindView(R.id.search_back)
    ImageView searchBack;
    @BindView(R.id.search_sou)
    EditText searchSou;
    @BindView(R.id.search_recycle)
    XRecyclerView searchRecycle;
    private SearchRecycleAdapter mSearchRecycleAdapter;


    @OnClick(R.id.search_back)
    public void onViewClicked() {
        finish();
    }

    @Override
    public void searchSuccess(SearchBean searchBean) {
        if (searchBean.status.equals("0000")) {

            if (searchBean.message.equals("查询成功")) {
                searchRecycle.loadMoreComplete();
                searchRecycle.refreshComplete();

                mSearchRecycleAdapter.addAll(searchBean.result);
                mSearchRecycleAdapter.notifyDataSetChanged();
            } else {
                Toast.makeText(this, searchBean.message, Toast.LENGTH_SHORT).show();
            }

        }else {
            Toast.makeText(this, searchBean.message, Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void searchError(String s) {

    }

    @Override
    void initData() {

//        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
//        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
//        searchRecycle.setLayoutManager(linearLayoutManager);
//
//        mSearchRecycleAdapter = new SearchRecycleAdapter();
//        searchRecycle.setAdapter(mSearchRecycleAdapter);

//        String s = searchSou.getText().toString();
//        presenter.search(true, s);
    }

    @Override
    SearchPresenter getPresenter() {
        return new SearchPresenter();
    }

    @Override
    void initListener() {
        searchSou.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (actionId == EditorInfo.IME_ACTION_SEARCH || actionId == EditorInfo.IME_ACTION_UNSPECIFIED) {

                    String keytag = searchSou.getText().toString().trim();

                    if (TextUtils.isEmpty(keytag)) {
                        Toast.makeText(SearchActivity.this, "请输入搜索关键字", Toast.LENGTH_SHORT).show();

                        return false;
                    }

                    // 搜索功能主体

                    LinearLayoutManager linearLayoutManager = new LinearLayoutManager(SearchActivity.this);
                    linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
                    searchRecycle.setLayoutManager(linearLayoutManager);

                    mSearchRecycleAdapter = new SearchRecycleAdapter();
                    searchRecycle.setAdapter(mSearchRecycleAdapter);

                    presenter.search(true, keytag);

                    return true;
                }
                return false;
            }
        });
    }

    @Override
    int initLayout() {
        return R.layout.activity_search;
    }
}
