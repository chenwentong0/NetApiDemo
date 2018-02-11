package chen.wentong.netapidemo.base;


import android.arch.lifecycle.Lifecycle;

import com.trello.rxlifecycle2.LifecycleProvider;

/**
 * BasePresenter
 * create by wentong.chen 2018.2.11
 * @param <T> baseView
 */
public abstract  class BasePresenter<T extends IBaseView> {
    protected final String TAG = getClass().getSimpleName();

    /**
     * 绑定生命周期请求请加上.compose(provider.bindToLifecycle())
     */
    protected LifecycleProvider<Lifecycle.Event> mProvider;
    protected T mView;

    public BasePresenter(T view, LifecycleProvider<Lifecycle.Event> provider) {
        this.mProvider = provider;
        this.mView = view;
    }

    /**
     * 销毁方法
     */
    public void onDestroy() {
        mView = null;
    }
}
