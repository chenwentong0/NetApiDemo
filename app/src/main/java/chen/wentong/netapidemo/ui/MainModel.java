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
import chen.wentong.netapidemo.net.core.MySslContextFactory;
import chen.wentong.netapidemo.net.core.NetConstants;
import chen.wentong.netapidemo.net.core.NetGlobalConfig;
import chen.wentong.netapidemo.net.core.NetUtil;
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
                .setBaseUrl(TvNetService.BASE_URL)
                //设置超时时间
                .setConnectTimeout(NetConstants.DEFAULT_TIMEOUT)
                .setReadTimeout(NetConstants.DEFAULT_TIMEOUT)
                //设置请求api
                .setServiceClass(TvNetService.class)
                .build();
        mUserService = NetUtil.getInstance()
                .setBaseUrl(NetService.GITHUB_BASEURL)
                .setServiceClass(NetService.class)
                .build();
    }

    @Override
    public Observable formRequest() {
        Map<String, String> map = new HashMap<>();
        map.put("header", "header");
        map.put("header", "header");
        map.put("ver", "4");
        return mTvNetService.formRequest(map)
                .compose(mProvider.bindToLifecycle());
    }

    @Override
    public Observable noParamsRequest() {
        return mTvNetService.noParamsRequest()
                .compose(mProvider.bindToLifecycle());
    }

    @Override
    public Observable pathParamsRequest() {
        return mTvNetService.pathRequest("index", "recommend")
                .compose(mProvider.bindToLifecycle());
    }

    @Override
    public Observable queryParamsRequest() {
        return mTvNetService.queryRequest("1", "4")
                .compose(mProvider.bindToLifecycle());
    }

    @Override
    public Observable queryMapRequest() {
        Map<String, String> map = new HashMap<>();
//        ?v=3.0.1&os=1&ver=4
        map.put("os", "1");
        map.put("ver", "4");
        return mTvNetService.queryMapRequest(map)
                .compose(mProvider.bindToLifecycle());
    }

    @Override
    public Observable staticHeaderRequest() {
        return mUserService
                .staticHeaderRequest("test")
                .compose(mProvider.bindToLifecycle());
    }

    @Override
    public Observable changeHeaderRequest() {
        return mTvNetService.changeHeaderRequest("os")
                .compose(mProvider.bindToLifecycle());
    }

    @Override
    public Observable interceptorRequest() {
        TvNetService tvNetService = NetUtil.getInstance()
                .setBaseUrl(TvNetService.BASE_URL)
                .setServiceClass(TvNetService.class)
                //添加定位信息请求头
                .setToggleLocationHeader(true)
                .build();
        return tvNetService.interceptorRequest();
    }

    @Override
    public Observable bodyRequest() {
        SearchRequestBody searchRequestBody = new SearchRequestBody();
        P p = new P();
        searchRequestBody.setP(p);
        return mTvNetService.bodyParamsRequest(searchRequestBody)
                .compose(mProvider.bindToLifecycle());
    }

    @Override
    public Observable urlRequest() {
        return mTvNetService.urlParamsRequest("json/rooms/1/info.json?v=3.0.1&os=1&ver=4")
                .compose(mProvider.bindToLifecycle());
    }

    @Override
    public Observable downloadFile() {
        return mUserService.downloadFile("下载文件")
                .compose(mProvider.bindToLifecycle());
    }

    @Override
    public Observable uploadFile() {
        HashMap<String, Object> map = new HashMap<>();
        return mUserService.uploadFile(map, new File("chen/wentong/netapidemo/test.txt"))
                .compose(mProvider.bindToLifecycle());
    }

    @Override
    public Observable netRequestSet() {
        TvNetService tvNetService = NetUtil.getInstance()
                .setBaseUrl(TvNetService.BASE_URL)
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
                .setSSLSocketFactory(MySslContextFactory.getSSLSocketFactory(
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
                                //添加响应拦截,
                                .body(ResponseBody.create(mediaType, "我是被拦截的响应值"))
                                .build();
                        return response;
                    }
                })
                .build();
        return tvNetService.noParamsRequest();
    }

    @Override
    public Observable useDefaultBaseurl() {
        return null;
    }

    @Override
    public Observable httpsRequest() {
        return null;
    }

    @Override
    public Observable waitAdd() {
        return null;
    }

}
