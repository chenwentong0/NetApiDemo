package chen.wentong.netapidemo.net.api;


import java.util.Map;

import chen.wentong.netapidemo.bean.SearchRequestBody;
import io.reactivex.Observable;
import retrofit2.http.Body;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;
import retrofit2.http.QueryMap;
import retrofit2.http.Url;

/**
 * Created by wentong.chen on 18/2/8.
 * 功能：全名直播接口
 */

public interface TvNetService {
    String BASE_URL = "http://www.quanmin.tv/";

    /**
     * 表单提交 (只能用post请求， 并且需要添加@FormUrlEncoded)
     * @param map 请求参数
     * @return 网络请求返回的数据
     */
    @FormUrlEncoded //标记表单提交
    @POST("json/page/app-data/info.json")
    Observable<String> formRequest(@FieldMap Map<String, String> map);

    /**
     * 无参数的网络请求
     * @return 网络请求返回的数据
     */
    @GET("json/page/app-data/info.json?v=3.0.1&os=1&ver=4")
    Observable<String> noParamsRequest();

    /**
     * path 请求 url中的index要加{} 且要和path里面的index名称一致
     * @param index 动态url
     * @param recommend 动态url
     * @return 网络请求返回的数据
     */
    @GET("json/app/{index}/{recommend}/list-android.json?v=3.0.1&os=1&ver=4")
    Observable<String> pathRequest(@Path("index")String index, @Path("recommend") String recommend);

    /**
     * query类型的请求参数请求
     * @param os 请求参数
     * @param ver 请求参数
     * @return 网络请求返回的数据
     */
    @GET("json/play/list.json")
    Observable<String> queryRequest(@Query("os")String os, @Query("ver")String ver);

    /**
     * queryMap请求
     * @param map 请求参数集合
     * @return 网络请求返回的数据
     */
    @GET("json/play/list.json")
    Observable<String> queryMapRequest(@QueryMap Map<String, String> map);

    /**
     * 多个动态值拼接的url， @get()里面不要放url
     * @param uid 动态url
     * @return 网络请求返回的数据
     */
    @GET()
    Observable<String> urlParamsRequest(@Url String uid);

    /**
     * body请求
     * @param searchRequestBody 请求参数放在请求体
     * @return 网络请求返回的数据
     */
    @POST("site/search")
    Observable<String> bodyParamsRequest(@Body SearchRequestBody searchRequestBody);

    /**
     * 动态请求头设置
     * @param header 请求头中添加的动态信息
     * @return 网络请求返回的数据
     */
    @GET("json/play/list.json")
    Observable<String> changeHeaderRequest(@Header("header") String header);

    /**
     * 拦截器添加
     * @return 网络请求返回的数据
     */
    @GET("json/play/list.json")
    Observable<String> interceptorRequest();
}
