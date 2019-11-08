package com.bw.movie.view.adapter;

import android.support.annotation.NonNull;
import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bw.movie.R;
import com.bw.movie.view.activity.MainActivity;

import java.util.List;

/**
 * <p>文件描述：<p>
 * <p>作者：吴新仲<p>
 * <p>创建时间：2019/11/6/006<p>
 * <p>更改时间：2019/11/6/006<p>
 */
public class XLBootPageAdapter extends PagerAdapter {
    private List<Integer> mIntegers;

    public XLBootPageAdapter(List<Integer> integers) {
        mIntegers = integers;
    }

    @Override
    public int getCount() {
        return mIntegers.size();
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object o) {
        return view == o;
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull final ViewGroup container, int position) {
        //添加图片到布局中
        View view = View.inflate(container.getContext(), R.layout.boot_page_page_img_layout, null);
        ImageView img = view.findViewById(R.id.boot_page_view_img);
        Glide.with(container.getContext()).load(mIntegers.get(position)).into(img);
        container.addView(view);
        return view;
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        container.removeView((View) object);
    }
}
