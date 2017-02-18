package cc.fs.sample.recyclerview;

import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

/**
 * RecyclerView LayoutManager 学习
 */
public class CustomLayoutManager extends RecyclerView.LayoutManager {

    LinearLayoutManager linearLayoutManager;
    GridLayoutManager gridLayoutManager;

    //view 普通布局
    @Override
    public RecyclerView.LayoutParams generateDefaultLayoutParams() {
        return null;
    }

    //对view layout 放置
    @Override
    public void onLayoutChildren(RecyclerView.Recycler recycler, RecyclerView.State state) {
        super.onLayoutChildren(recycler, state);
    }

    /**
     * 两种缓存方式
     * detach:缓存view及数据，不需要重新绑定数据
     * remove:缓存view，复用，需要重新绑定数据
     */

    //移除屏幕的view缓存起来，不需要重复绑定数据
    @Override
    public void detachAndScrapView(View child, RecyclerView.Recycler recycler) {
        super.detachAndScrapView(child, recycler);
    }

    //移除屏幕的view recycle以备后续循环利用
    @Override
    public void removeAndRecycleView(View child, RecyclerView.Recycler recycler) {
        super.removeAndRecycleView(child, recycler);
    }

    //是否横向滑动
    @Override
    public boolean canScrollHorizontally() {
        return super.canScrollHorizontally();
    }

    //是否竖向滑动
    @Override
    public boolean canScrollVertically() {
        return super.canScrollVertically();
    }
}
