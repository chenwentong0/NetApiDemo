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
 * 功能：
 */

public interface TvNetService {
//    http://www.quanmin.tv/ 全民直播
    String baseUrl = "http://www.quanmin.tv/";
    @FormUrlEncoded //标记表单提交
    @POST("json/page/app-data/info.json")
    Observable<String> 表单请求(@FieldMap Map<String,String> map);
    /**
     * 获取App启动页信息
     * @return
     */
    @GET("json/page/app-data/info.json?v=3.0.1&os=1&ver=4")
    Observable<String> get无参请求();

    /**
     * 获取分类列表
     * @return
     *
     * categories/list.json
     */
    @GET("json/app/index/category/info-android.json?v=3.0.1&os=1&ver=4")
    Observable<String> getAllCategories();

    /**
     * 获取推荐列表
     * @return
     */
    @GET("json/app/{index}/{recommend}/list-android.json?v=3.0.1&os=1&ver=4")
    Observable<String> get_path参数请求(@Path("index")String index, @Path("recommend") String recommend);

    /**
     * 获取直播列表
     * @return
     */
    @GET("json/play/list.json")
    Observable<String> get_query参数请求(@Query("os")String os, @Query("ver")String ver);


    @GET("json/play/list.json")
    Observable<String> get_queryMap请求(@QueryMap Map<String, String> map);

    /**
     * 进入房间
     * @param uid
     * @return
     */
    @GET()
    Observable<String> get_url参数请求(@Url String uid);

    /**
     * 搜索
     * @param searchRequestBody
     * @return
     */
    @POST("site/search")
    Observable<String> get_body参数请求(@Body SearchRequestBody searchRequestBody);

    @GET("json/play/list.json")
    Observable<String> get_动态请求头添加(@Header("header") String header);

    @GET("json/play/list.json")
    Observable<String> get_拦截器请求头添加();
}
