package chen.wentong.netapidemo.net.api;


import java.io.File;
import java.util.Map;

import chen.wentong.netapidemo.bean.UserInfo;
import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.Path;
import retrofit2.http.Query;
import retrofit2.http.QueryMap;
import retrofit2.http.Streaming;

/**
 * Created by ${wentong.chen} on 18/1/23.
 * 网络请求接口定义
 */

public interface NetService {
    String mobileBaseurl = "http://apis.juhe.cn/";
    String githubBaseUrl = "https://api.github.com/";
    String HEADER_API_VERSION = "Accept: application/vnd.github.v3+json";  //添加请求头

    @Headers({HEADER_API_VERSION})
    @GET("users/{username}")
    Observable<UserInfo> get_静态请求头添加(@Path("username") String username);

    @Streaming//标记为下载
    @GET("mobile/get")
    Observable<String> 下载文件请求(@Query("params") String params);


    @Multipart//标记上传
    @POST("mobile/get")
    Observable<String> 上传文件请求(@QueryMap Map<String, Object> options,
                                  @Part("file") File file);

}
