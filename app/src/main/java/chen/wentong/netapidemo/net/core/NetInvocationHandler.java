package chen.wentong.netapidemo.net.core;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

import io.reactivex.Observable;
import io.reactivex.ObservableOperator;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

/**
 * 网络请求代理类 Created by ${wentong.chen} on 18/1/24.
 * @param <T> 代理对象
 */
public final class NetInvocationHandler<T> implements InvocationHandler {
    private T actualService;

    public NetInvocationHandler(T var1) {
        this.actualService = var1;
    }

    /**
     * 代理对象的代理方法做线程指定处理
     * @param proxy 代理的对象
     * @param method 代理的方法
     * @param args 代理方法参数
     * @return 代理对象
     * @throws Throwable 异常
     */
    public Object invoke(Object proxy, Method method, Object... args) throws Throwable {
        Object result = method.invoke(this.actualService, args);
        if (result != null && result instanceof Observable) {
            Observable observable = (Observable) result;
            observable = observable
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .lift(new ObservableOperator() {
                        @Override
                        public Observer apply(Observer observer) throws Exception {
                            return observer;
                        }
                    });

            return observable;
        } else {
            return result;
        }
    }
}
