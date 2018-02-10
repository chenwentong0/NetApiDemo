package chen.wentong.netapidemo.net.core;

import android.content.Context;

/**
 * Created by wentong.chen on 18/2/10.
 * 功能：网络请求的默认配置
 */

public class NetGlobalConfig {
    private static Context sContext;
    private static String sBaseUrl;
    private static Class<?> sNetServiceClass;
    private static NetGlobalConfig sNetGlobalConfig = new NetGlobalConfig();

    private NetGlobalConfig(){}

    public static NetGlobalConfig getInstance() {
        return sNetGlobalConfig;
    }

    public NetGlobalConfig init(Context context) {
        sContext = context;
        return sNetGlobalConfig;
    }

    public NetGlobalConfig setBaseUrl(String baseUrl) {
        sBaseUrl = baseUrl;
        return sNetGlobalConfig;
    }

    public static NetGlobalConfig setNetServiceClass(Class<?> netServiceClass) {
        sNetServiceClass = netServiceClass;
        return sNetGlobalConfig;
    }

    public static Context getContext() {
        return sContext;
    }

    public static String getBaseUrl() {
        return sBaseUrl;
    }

    public static Class<?> getNetServiceClass() {
        return sNetServiceClass;
    }
}
