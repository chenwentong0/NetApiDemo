package chen.wentong.netapidemo.ui;

import io.reactivex.Observable;

/**
 * Created by wentong.chen on 18/2/8.
 * 功能：
 */
public interface IMainContract {
    /**
     * 数据处理后回调给ui的更新
     */
    interface View {
        /**
         * 显示网络加载回的信息
         * @param data 数据
         */
        void showData(String data);
    }

    /**
     * 请求数据回来后的业务回调
     */
    interface Presenter {
        /**
         * formRequest
         */
        void formRequest();

        /**
         * 无参数请求
         */
        void noParamsRequest();

        /**
         * path 参数请求
         */
        void pathParamsRequest();

        /**
         * query请求
         */
        void queryParamsRequest();

        /**
         * queryMap请求
         */
        void queryMapRequest();

        /**
         * 静态请求头添加
         */
        void staticHeaderRequest();

        /**
         * 动态请求头添加
         */
        void changeHeaderRequest();

        /**
         * 拦截器请求头添加
         */
        void interceptorRequest();

        /**
         * body参数请求
         */
        void bodyRequest();

        /**
         * urlRequest
         */
        void urlRequest();

        /**
         * downloadFile
         */
        void downloadFile();

        /**
         * uploadFileRequest
         */
        void uploadFileRequest();

        /**
         * netRequestSet
         */
        void netRequestSet();

        /**
         * useDefaultBaseurl
         */
        void useDefaultBaseurl();

        /**
         * httpsRequest
         */
        void httpsRequest();

        /**
         * waitAdd
         */
        void waitAdd();
    }

    /**
     * 请求数据model
     */
    interface Model {
        /**
         * formRequest
         * @return 数据主题
         */
        Observable formRequest();
        /**
         * 无参数请求
         * @return 数据主题
         */
        Observable noParamsRequest();

        /**
         * path参数请求
         * @return 数据主题
         */
        Observable pathParamsRequest();

        /**
         * query参数请求
         * @return 数据主题
         */
        Observable queryParamsRequest();

        /**
         * queryMap参数请求
         * @return 数据主题
         */
        Observable queryMapRequest();

        /**
         * 静态请求头添加
         * @return 数据主题
         */
        Observable staticHeaderRequest();

        /**
         * 动态请求头添加
         * @return 数据主题
         */
        Observable changeHeaderRequest();

        /**
         * 拦截器请求头添加
         * @return 数据主题
         */
        Observable interceptorRequest();

        /**
         * body请求
         * @return 数据主题
         */
        Observable bodyRequest();

        /**
         * url参数请求
         * @return 数据主题
         */
        Observable urlRequest();

        /**
         * downloadFile
         * @return 数据主题
         */
        Observable downloadFile();

        /**
         * uploadFile
         * @return 成功失败返回的数据
         */
        Observable uploadFile();

        /**
         * 网络请求配置使用
         * @return 数据主题
         */
        Observable netRequestSet();

        /**
         * 使用默认配置的网络请求api
         * @return 数据主题
         */
        Observable useDefaultBaseurl();

        /**
         * https请求
         * @return 数据主题
         */
        Observable httpsRequest();

        /**
         * 等待补充
         * @return 数据主题
         */
        Observable waitAdd();
    }
}
