package chen.wentong.netapidemo.net.subscriber;



import com.apkfuns.logutils.LogUtils;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

/**
 * Created by wentong.chen on 18/1/17.
 *  Rxjava订阅者的二次封装
 */

public abstract class AbsObjectSubscriber<T> implements Observer<T> {

    @Override
    public void onSubscribe(Disposable d) {
        LogUtils.d( "onSubscribe: ");
    }

    @Override
    public void onNext(T t) {
        onSuccess(t);
    }


    @Override
    public void onComplete() {
        LogUtils.d("onComplete: Over!");
    }


    @Override
    public void onError(Throwable t) {
        t.printStackTrace();
        onFailure(t);
    }

    /**
     * 请求失败回调
     * @param e
     */
    public abstract void onFailure(Throwable e);

    /**
     * 请求成功回调
     * @param t 请求成功拿到的bean对象
     */
    public abstract void onSuccess(T t);
}
