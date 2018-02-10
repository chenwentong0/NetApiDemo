package chen.wentong.netapidemo.ui;

import android.arch.lifecycle.Lifecycle;

import com.trello.rxlifecycle2.LifecycleProvider;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import chen.wentong.netapidemo.base.BaseModel;
import chen.wentong.netapidemo.bean.P;
import chen.wentong.netapidemo.bean.SearchRequestBody;
import chen.wentong.netapidemo.net.api.NetService;
import chen.wentong.netapidemo.net.api.TvNetService;
import chen.wentong.netapidemo.net.core.NetConstants;
import chen.wentong.netapidemo.net.core.NetGlobalConfig;
import chen.wentong.netapidemo.net.core.NetUtil;
import chen.wentong.netapidemo.net.core.SslContextFactory;
import io.reactivex.Observable;
import okhttp3.Interceptor;
import okhttp3.MediaType;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.ResponseBody;

/**
 * Created by wentong.chen on 18/2/8.
 * 功能：
 */

public class MainModel extends BaseModel implements IMainContract.Model {
    private final TvNetService mTvNetService;
    private final NetService mUserService;

    public MainModel(LifecycleProvider<Lifecycle.Event> provider) {
        super(provider);
        mTvNetService = NetUtil.getInstance()
                .setBaseUrl(TvNetService.baseUrl)
                //设置超时时间
                .setConnectTimeout(NetConstants.DEFAULT_TIMEOUT)
                .setReadTimeout(NetConstants.DEFAULT_TIMEOUT)
                //设置请求api
                .setServiceClass(TvNetService.class)
                .build();
        mUserService = NetUtil.getInstance()
                .setBaseUrl(NetService.githubBaseUrl)
                .setServiceClass(NetService.class)
                .build();
    }

    @Override
    public Observable 表单请求() {
        Map<String, String> map = new HashMap<>();
        map.put("header", "header");
        map.put("header", "header");
        map.put("ver", "4");
        return mTvNetService.表单请求(map)
                .compose(mProvider.bindToLifecycle());
    }

    @Override
    public Observable get无参请求() {
        return mTvNetService.get无参请求()
                .compose(mProvider.bindToLifecycle());
    }

    @Override
    public Observable get_path参数请求() {
        return mTvNetService.get_path参数请求("index", "recommend")
                .compose(mProvider.bindToLifecycle());
    }

    @Override
    public Observable get_query参数请求() {
        return mTvNetService.get_query参数请求("1", "4")
                .compose(mProvider.bindToLifecycle());
    }

    @Override
    public Observable get_queryMap请求() {
        Map<String, String> map = new HashMap<>();
//        ?v=3.0.1&os=1&ver=4
        map.put("os", "1");
        map.put("ver", "4");
        return mTvNetService.get_queryMap请求( map)
                .compose(mProvider.bindToLifecycle());
    }

    @Override
    public Observable get_静态请求头添加() {
        return mUserService
                .get_静态请求头添加("test")
                .compose(mProvider.bindToLifecycle());
    }

    @Override
    public Observable get_动态请求头添加() {
        return mTvNetService.get_动态请求头添加("os")
                .compose(mProvider.bindToLifecycle());
    }

    @Override
    public Observable get_拦截器请求头添加() {
        return ((TvNetService)(NetUtil.getInstance()
                .setBaseUrl(TvNetService.baseUrl)
                .setServiceClass(TvNetService.class)
                //添加定位信息请求头
                .setToggleLocationHeader(true)
                .build()))
                .get_拦截器请求头添加();
    }

    @Override
    public Observable get_body参数请求() {
        SearchRequestBody searchRequestBody = new SearchRequestBody();
        P p = new P();
        searchRequestBody.setP(p);
        return mTvNetService.get_body参数请求(searchRequestBody)
                .compose(mProvider.bindToLifecycle());
    }

    @Override
    public Observable get_url参数请求() {
        return mTvNetService.get_url参数请求("json/rooms/1/info.json?v=3.0.1&os=1&ver=4")
                .compose(mProvider.bindToLifecycle());
    }

    @Override
    public Observable 下载文件请求() {
        return mUserService.下载文件请求("下载文件")
                .compose(mProvider.bindToLifecycle());
    }

    @Override
    public Observable 上传文件请求() {
        HashMap<String, Object> map = new HashMap<>();
        return mUserService.上传文件请求(map, new File("chen/wentong/netapidemo/test.txt"))
                .compose(mProvider.bindToLifecycle());
    }

    @Override
    public Observable 配合网络请求配置使用() {
        return ((TvNetService)(NetUtil.getInstance()
                .setBaseUrl(TvNetService.baseUrl)
                //设置请求头添加定位信息
                .setToggleLocationHeader(true)
                //设置请求api
                .setServiceClass(TvNetService.class)
                .setReadTimeout(1)
                //设置连接超时时间
                .setConnectTimeout(1)
                //设置是否验证https请求
                .setToggleHttps(true)
                //设置https证书
                .setSSLSocketFactory(SslContextFactory.getSSLSocketFactory(
                        NetGlobalConfig.getContext()))
                //添加自定义的拦截器
                .addInterceptor(new Interceptor() {
                    @Override
                    public Response intercept(Chain chain) throws IOException {
                        Request request = chain.request();
                        Request build = request.newBuilder()
                                //添加请求拦截
                                .build();
                        Response proceed = chain.proceed(build);
                        MediaType mediaType = proceed.body().contentType();
                        Response response = proceed
                                .newBuilder()
                                //添加响应拦截, 将响应的code变为500
                                .code(500)
                                .body(ResponseBody.create(mediaType, "我是被拦截的响应值"))
                                .build();
                        return response;
                    }
                })
                .build()))
                .get无参请求();
    }

    @Override
    public Observable 使用前初始化默认的baseUrl() {
        return null;
    }

    @Override
    public Observable https证书验证请求() {
        return null;
    }

    @Override
    public Observable 待补充() {
        return null;
    }

}
