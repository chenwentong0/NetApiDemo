package chen.wentong.netapidemo.net.core;

import android.text.TextUtils;
import android.util.Log;

import java.io.IOException;
import java.lang.reflect.Proxy;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.TimeUnit;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.SSLSession;
import javax.net.ssl.SSLSocketFactory;

import chen.wentong.netapidemo.manager.DataManager;
import okhttp3.Interceptor;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.ResponseBody;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;

/**
 * Created by wentong.chen on 18/2/10.
 * 功能：网络请求工具类
 */
public final class NetUtil {
    private static final String TAG = NetUtil.class.getSimpleName();
    private static final double TIME_DELAY = 1e6d;
    private long mConnectTimeout = NetConstants.DEFAULT_TIMEOUT;                           //设置默认的连接超时时间30秒
    private long mReadTimeout = NetConstants.DEFAULT_TIMEOUT;                              //读超时
    private SSLSocketFactory mSSLSocketFactory;                                            //验证书
    private boolean mToggleHttps;                                                          //是否https验证
    private CopyOnWriteArrayList<Interceptor> mInterceptors;                               //请求拦截器
    private boolean mToggleLocationHeader;                                                 //请求头添加定位信息
    private String mBaseUrl = NetGlobalConfig.getBaseUrl();                                //请求baseUrl
    private Class<?> serviceClass = NetGlobalConfig.getNetServiceClass();                  //请求接口api

    private NetUtil() { }

    public static NetUtil getInstance() {
        return new NetUtil();
    }

    /**
     * 根据设置生成网路请求api接口对象
     * @param <S> 网路请求api接口对象
     * @return 网路请求api接口对象
     */
    public <S> S build() {
        if (TextUtils.isEmpty(mBaseUrl) || !mBaseUrl.endsWith("/")) {
            throw new IllegalArgumentException("必须设置网络请求的baseUrl， 且baseUrl必须以/结束");
        }

        S s = (S) getRetrofit().create(serviceClass);
        //动态代理（默认请求在子线程中进行， callback在主线程中执行）
        return (S) Proxy.newProxyInstance(serviceClass.getClassLoader(), new Class[]{serviceClass},
                new NetInvocationHandler<>(s));
    }

    /**
     * 设置连接超时时间
     * @param connectTimeout 连接超时时间
     * @return NetUtil
     */
    public NetUtil setConnectTimeout(long connectTimeout) {
        this.mConnectTimeout = connectTimeout;
        return this;
    }

    /**
     * 设置读取网络数据超时时间
     * @param readTimeout 读取网络数据超时时间
     * @return NetUtil
     */
    public NetUtil setReadTimeout(long readTimeout) {
        this.mReadTimeout = readTimeout;
        return this;
    }

    /**
     * https验证书
     * @param sslSocketFactory https证书生产工具
     * @return NetUtil
     */
    public NetUtil setSSLSocketFactory(SSLSocketFactory sslSocketFactory) {
        this.mSSLSocketFactory = sslSocketFactory;
        return this;
    }

    /**
     * 是否验证https
     * @param toggleHttps 是否验证https请求
     * @return NetUtil
     */
    public NetUtil setToggleHttps(boolean toggleHttps) {
        this.mToggleHttps = toggleHttps;
        return this;
    }

    /**
     * 添加自定义拦截器
     * @param interceptor 网络请求拦截器
     * @return NetUtil
     */
    public NetUtil addInterceptor(Interceptor interceptor) {
        if (mInterceptors == null) {
            mInterceptors = new CopyOnWriteArrayList<>();
        }
        if (!mInterceptors.contains(interceptor)) {
            mInterceptors.add(interceptor);
        }
        return this;
    }

    /**
     * 是否在请求头上添加定位信息
     * @param toggleLocationHeader true添加在请求头上添加定位信息， 否则false
     * @return NetUtil
     */
    public NetUtil setToggleLocationHeader(boolean toggleLocationHeader) {
        mToggleLocationHeader = toggleLocationHeader;
        return this;
    }

    /**
     * 设置网络请求的baseUrl
     * @param baseUrl  baseUrl
     * @return  NetUtil
     */
    public NetUtil setBaseUrl(String baseUrl) {
        mBaseUrl = baseUrl;
        return this;
    }

    /**
     *  设置网络请求接口api对象
     * @param serviceClass serviceClass
     * @return NetUtil
     */
    public NetUtil setServiceClass(Class<?> serviceClass) {
        this.serviceClass = serviceClass;
        return this;
    }

    /**
     * 获取Retrofit
     * @return Retrofit
     */
    private Retrofit getRetrofit() {
        Retrofit.Builder builder = new Retrofit.Builder()
                .baseUrl(mBaseUrl)
                .client(getClient())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(RxGsonConvertFactory.create());
        Retrofit retrofit = builder.build();
        return retrofit;
    }

    /**
     * 获取网络连接client
     * @return OkHttpClient
     */
    private OkHttpClient getClient() {
        OkHttpClient.Builder builder = new OkHttpClient.Builder();
        if (mConnectTimeout > 0) {
            builder.connectTimeout(mConnectTimeout, TimeUnit.MILLISECONDS);
        }
        if (mReadTimeout > 0) {
            builder.readTimeout(mReadTimeout, TimeUnit.MILLISECONDS);
        }
        //cookie 设置
//        CookieJar cookieJar = provider.configCookie();
//        if (cookieJar != null) {
//            builder.cookieJar(cookieJar);
//        }
        //是否验证https证书
        builder.hostnameVerifier(new HostnameVerifier() {
            @Override
            public boolean verify(String hostname, SSLSession session) {
                //false是验证， true是不验证
                return !mToggleHttps;
            }
        });
        //配置https或者http请求
        if (mSSLSocketFactory != null) {
            builder.sslSocketFactory(mSSLSocketFactory);
        }
        if (mInterceptors != null && !mInterceptors.isEmpty()) {
            for (Interceptor interceptor : mInterceptors) {
                builder.addInterceptor(interceptor);
            }
        }
        builder.addInterceptor(new Interceptor() {
            @Override
            public Response intercept(Chain chain) throws IOException {
                return setInterceptor(chain);
            }
        });
        OkHttpClient client = builder.build();
        return client;
    }

    /**
     * 在拦截器上添加日志打印， 和请求头上添加定位信息
     * @param chain 请求串
     * @return 网络响应
     * @throws IOException io异常
     */
    private Response setInterceptor(Interceptor.Chain chain) throws IOException {
        Request request = chain.request();
        if (mToggleLocationHeader) {
            request = request.newBuilder()
                    //添加经纬度信息
                    .addHeader("os", DataManager.getInstance().getLocationInfo().getLatitude())
                    .addHeader("ver", DataManager.getInstance().getLocationInfo().getLongitude())
                    .build();
        }
        //添加请求日志打印
        long t1 = System.nanoTime();
        Log.d(TAG, String.format("Send request %s%n%s",
                request.url(), request.headers()));
        Response response = chain.proceed(request);
        MediaType contentType = response.body().contentType();
        String body = response.body().string();
        long t2 = System.nanoTime();
        Log.d(TAG, String.format("Receive response for %s in %.1fms%n%s%s",
                response.request().url(), (t2 - t1) / TIME_DELAY, response.headers(), body));
        ResponseBody responseBody = ResponseBody.create(contentType, body);
        return response.newBuilder().body(responseBody).build();
    }
}
