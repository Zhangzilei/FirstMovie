package com.bw.movie.presenter;

import com.bw.movie.view.interfaces.IBaseView;

/**
 * <p>文件描述：<p>
 * <p>作者：吴新仲<p>
 * <p>创建时间：2019/11/5/005<p>
 * <p>更改时间：2019/11/5/005<p>
 */
public class BasePresenter<V extends IBaseView> {
    public V mV;

    public void attachView(V v){
        mV = v;
    }

    public void detachView(){
        mV = null;
    }

    public V getView(){
        return mV;
    }
}
