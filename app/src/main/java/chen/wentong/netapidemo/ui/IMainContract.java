package chen.wentong.netapidemo.ui;

import org.json.JSONObject;

import chen.wentong.netapidemo.bean.UserInfo;
import io.reactivex.Flowable;
import io.reactivex.Observable;

/**
 * Created by wentong.chen on 18/2/8.
 * 功能：
 */

public interface IMainContract {
    interface View {
        void showData(String data);
    }

    interface Presenter {
        void 表单请求();

        void get无参请求();

        void get_path参数请求();

        void get_query参数请求();

        void get_queryMap请求();

        void get_静态请求头添加();

        void get_动态请求头添加();

        void get_拦截器请求头添加();

        void get_body参数请求();

        void get_url参数请求();

        void 下载文件请求();

        void 上传文件请求();

        void 配合网络请求配置使用();

        void 使用前初始化默认的baseUrl();

        void https证书验证请求();

        void 待补充();
    }

    interface Model {
        Observable 表单请求();

        Observable get无参请求();

        Observable get_path参数请求();

        Observable get_query参数请求();

        Observable get_queryMap请求();

        Observable get_静态请求头添加();

        Observable get_动态请求头添加();

        Observable get_拦截器请求头添加();

        Observable get_body参数请求();

        Observable get_url参数请求();

        Observable 下载文件请求();

        Observable 上传文件请求();

        Observable 配合网络请求配置使用();

        Observable 使用前初始化默认的baseUrl();

        Observable https证书验证请求();

        Observable 待补充();
    }
}
