package chen.wentong.netapidemo.net.core;

import android.content.Context;

/**
 * Created by wentong.chen on 18/2/10.
 * 功能：网络请求的默认配置
 */
public final class NetGlobalConfig {
    private static Context sContext;
    private static String sBaseUrl;
    private static Class<?> sNetServiceClass;
    private static NetGlobalConfig sNetGlobalConfig = new NetGlobalConfig();

    private NetGlobalConfig() { }

    public static NetGlobalConfig getInstance() {
        return sNetGlobalConfig;
    }

    /**
     * 初始化applicationContext
     * @param context applicationContext
     * @return NetGlobalConfig
     */
    public NetGlobalConfig init(Context context) {
        sContext = context;
        return sNetGlobalConfig;
    }

    /**
     * 全局设置默认的baseurl
     * @param baseUrl 网络请求baseurl
     * @return NetGlobalConfig
     */
    public NetGlobalConfig setBaseUrl(String baseUrl) {
        sBaseUrl = baseUrl;
        return sNetGlobalConfig;
    }

    /**
     * 设置网络请求api接口字节码对象
     * @param netServiceClass 网络请求api接口字节码对象
     * @return NetGlobalConfig
     */
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
