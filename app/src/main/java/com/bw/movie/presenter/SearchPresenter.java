package com.bw.movie.presenter;

import com.bw.movie.constraint.Constraint;
import com.bw.movie.model.bean.SearchBean;
import com.bw.movie.model.http.HttpUtils;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

/**
 * <p>文件描述：<p>
 * <p>作者：吴新仲<p>
 * <p>创建时间：2019/11/12/012<p>
 * <p>更改时间：2019/11/12/012<p>
 */
public class SearchPresenter extends BasePresenter<Constraint.ISearchView> {

    private int page;

    //搜索
    public void search(boolean isRefresh,String keyword){
        if (isRefresh)
            page = 1;
        else
            page++;

        HttpUtils.getInstance().getConstant().SEARCH_BEAN(keyword,page,7)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<SearchBean>() {
                    @Override
                    public void accept(SearchBean searchBean) throws Exception {
                        getView().searchSuccess(searchBean);
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        getView().searchError(throwable.getMessage());
                    }
                });

    }
}
