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
    String MOBILE_BASEURL = "http://apis.juhe.cn/";
    String GITHUB_BASEURL = "https://api.github.com/";
    String HEADER_API_VERSION = "Accept: application/vnd.github.v3+json";  //添加请求头

    /**
     * 静态请求头添加
     * @param username 用户名
     * @return 用户信息
     */
    @Headers({HEADER_API_VERSION})
    @GET("users/{username}")
    Observable<UserInfo> staticHeaderRequest(@Path("username") String username);

    /**
     * 文件下载
     * @param params 请求参数
     * @return 下载文件
     */
    @Streaming//标记为下载
    @GET("mobile/get")
    Observable<String> downloadFile(@Query("params") String params);

    /**
     * 文件上传
     * @param options 参数设置
     * @param file 上传的文件
     * @return 上传成功失败的信息
     */
    @Multipart//标记上传
    @POST("mobile/get")
    Observable<String> uploadFile(@QueryMap Map<String, Object> options,
                                  @Part("file") File file);

}
