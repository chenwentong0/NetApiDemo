package chen.wentong.netapidemo.base;

import android.arch.lifecycle.Lifecycle;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.widget.Toast;

import com.trello.lifecycle2.android.lifecycle.AndroidLifecycle;
import com.trello.rxlifecycle2.LifecycleProvider;

import butterknife.ButterKnife;

/**
 * Created by wentong.chen on 18/2/8.
 * 功能：
 */

public class BaseActivity extends RxLifeActivity implements IBaseView{
    protected final String TAG = getClass().getSimpleName();
    //生命周期绑定 Rxjava中 compose（mLifecycleProvider.bindToLifecycle()）
    protected final LifecycleProvider<Lifecycle.Event> mLifecycleProvider
            = AndroidLifecycle.createLifecycleProvider(this);

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    public void showLongToast(CharSequence toast) {
        Toast.makeText(this, toast, Toast.LENGTH_LONG).show();
    }
}
