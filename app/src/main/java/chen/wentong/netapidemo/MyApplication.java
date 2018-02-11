package chen.wentong.netapidemo;

import android.app.Application;

import chen.wentong.netapidemo.net.api.TvNetService;
import chen.wentong.netapidemo.net.core.NetGlobalConfig;


/**
 * Created by ${wentong.chen} on 18/1/22.
 */

public class MyApplication extends Application {
    private static MyApplication mInstance;


    @Override
    public void onCreate() {
        super.onCreate();
        mInstance = this;
        //设置网路请求中默认的baseUrl和默认的Service
        NetGlobalConfig.getInstance().setBaseUrl(TvNetService.BASE_URL)
                .setNetServiceClass(TvNetService.class);
    }

    public static MyApplication getInstance() {
        return mInstance;
    }
}
