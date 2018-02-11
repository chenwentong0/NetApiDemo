package chen.wentong.netapidemo.base;

import android.arch.lifecycle.Lifecycle;

import com.trello.rxlifecycle2.LifecycleProvider;

/**
 * Created by wentong.chen on 18/1/30.
 * 功能：
 */
public class BaseModel {
    protected LifecycleProvider<Lifecycle.Event> mProvider;

    public BaseModel(LifecycleProvider<Lifecycle.Event> provider) {
        this.mProvider = provider;
    }
}
