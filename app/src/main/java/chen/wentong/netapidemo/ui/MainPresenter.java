package chen.wentong.netapidemo.ui;

import com.trello.rxlifecycle2.LifecycleProvider;

import chen.wentong.netapidemo.base.BasePresenter;
import chen.wentong.netapidemo.base.IBaseView;
import chen.wentong.netapidemo.bean.UserInfo;
import chen.wentong.netapidemo.net.subscriber.AbsObjectSubscriber;

/**
 * Created by wentong.chen on 18/2/8.
 * 功能：
 */

public class MainPresenter extends BasePresenter implements IMainContract.Presenter {
    private IMainContract.View mView;
    private final MainModel mMainModel;

    public MainPresenter(IBaseView view, LifecycleProvider provider) {
        super(view, provider);
        this.mView = (IMainContract.View) view;
        mMainModel = new MainModel(provider);
    }

    @Override
    public void 表单请求() {
        mMainModel.表单请求()
                .subscribe(new AbsObjectSubscriber<String>() {
                    @Override
                    public void onSuccess(String response) {
                        mView.showData("表单请求 成功 " + response.toString());
                    }

                    @Override
                    public void onFailure(Throwable e) {
                        mView.showData("表单请求 失败 " + e.getMessage());
                    }
                });
    }

    @Override
    public void get无参请求() {
        mMainModel.get无参请求()
                .subscribe(new AbsObjectSubscriber<String>() {

                    @Override
                    public void onSuccess(String response) {
                        mView.showData("get无参请求 成功 " + response);
                    }

                    @Override
                    public void onFailure(Throwable e) {
                        mView.showData("get无参请求 失败 " + e.getMessage());
                    }
                });
    }

    @Override
    public void get_path参数请求() {
        mMainModel.get_path参数请求()
                .subscribe(new AbsObjectSubscriber<String>() {
                    @Override
                    public void onSuccess(String response) {
                        mView.showData("get_path参数请求 成功 " + response);
                    }

                    @Override
                    public void onFailure(Throwable e) {
                        mView.showData("get_path参数请求 失败 " + e.getMessage());
                    }
                });
    }

    @Override
    public void get_query参数请求() {
        mMainModel.get_query参数请求()
                .subscribe(new AbsObjectSubscriber<String>() {
                    @Override
                    public void onSuccess(String response) {
                        mView.showData("get_query参数请求 成功 " + response);
                    }

                    @Override
                    public void onFailure(Throwable e) {
                        mView.showData("get_query参数请求 失败 " + e.getMessage());
                    }
                });
    }

    @Override
    public void get_queryMap请求() {
        mMainModel.get_queryMap请求()
                .subscribe(new AbsObjectSubscriber<String>() {
                    @Override
                    public void onSuccess(String response) {
                        mView.showData("get_queryMap请求 成功 " + response);
                    }

                    @Override
                    public void onFailure(Throwable e) {
                        mView.showData("get_queryMap请求 失败 " + e.getMessage());
                    }
                });
    }

    @Override
    public void get_静态请求头添加() {
        mMainModel.get_静态请求头添加()
                .subscribe(new AbsObjectSubscriber<UserInfo>() {
                    @Override
                    public void onSuccess(UserInfo response) {
                        mView.showData("get_静态请求头添加 成功 " + response.toString());
                    }

                    @Override
                    public void onFailure(Throwable e) {
                        mView.showData("get_静态请求头添加 失败 " + e.getMessage());
                    }
                });
    }

    @Override
    public void get_动态请求头添加() {
        mMainModel.get_动态请求头添加()
                .subscribe(new AbsObjectSubscriber<String>() {
                    @Override
                    public void onSuccess(String response) {
                        mView.showData("get_动态请求头添加 成功 " + response);
                    }

                    @Override
                    public void onFailure(Throwable e) {
                        mView.showData("get_动态请求头添加 失败 " + e.getMessage());
                    }
                });
    }

    @Override
    public void get_拦截器请求头添加() {
        mMainModel.get_拦截器请求头添加()
                .subscribe(new AbsObjectSubscriber<String>() {
                    @Override
                    public void onFailure(Throwable e) {
                        mView.showData("get_拦截器请求头添加 失败 " + e.getMessage());
                    }

                    @Override
                    public void onSuccess(String s) {
                        mView.showData("get_拦截器请求头添加 成功 " + s.toString());
                    }
                });
    }

    @Override
    public void get_body参数请求() {
        mMainModel.get_body参数请求()
                .subscribe(new AbsObjectSubscriber<String>() {
                    @Override
                    public void onFailure(Throwable e) {
                        mView.showData("get_body参数请求 失败 " + e.getMessage());
                    }

                    @Override
                    public void onSuccess(String s) {
                        mView.showData("get_body参数请求 成功 " + s.toString());
                    }
                });
    }

    @Override
    public void get_url参数请求() {
        mMainModel.get_url参数请求()
                .subscribe(new AbsObjectSubscriber<String>() {
                    @Override
                    public void onFailure(Throwable e) {
                        mView.showData("get_url参数请求 失败 " + e.getMessage());
                    }

                    @Override
                    public void onSuccess(String s) {
                        mView.showData("get_url参数请求 成功 " + s.toString());
                    }
                });
    }

    @Override
    public void 下载文件请求() {
        mMainModel.get_path参数请求()
                .subscribe(new AbsObjectSubscriber<String>() {
                    @Override
                    public void onFailure(Throwable e) {
                        mView.showData("下载文件请求 失败 " + e.getMessage());
                    }

                    @Override
                    public void onSuccess(String s) {
                        mView.showData("下载文件请求 成功 " + s.toString());
                    }
                });
    }

    @Override
    public void 上传文件请求() {
        mMainModel.get_path参数请求()
                .subscribe(new AbsObjectSubscriber<String>() {
                    @Override
                    public void onFailure(Throwable e) {
                        mView.showData("上传文件请求 失败 " + e.getMessage());
                    }

                    @Override
                    public void onSuccess(String s) {
                        mView.showData("上传文件请求 成功 " + s.toString());
                    }
                });
    }

    @Override
    public void 配合网络请求配置使用() {
        mMainModel.get_path参数请求()
                .subscribe(new AbsObjectSubscriber<String>() {
                    @Override
                    public void onFailure(Throwable e) {
                        mView.showData("配合网络请求配置使用 失败 " + e.getMessage());
                    }

                    @Override
                    public void onSuccess(String s) {
                        mView.showData("配合网络请求配置使用 成功 " + s.toString());
                    }
                });
    }

    @Override
    public void 使用前初始化默认的baseUrl() {

    }

    @Override
    public void https证书验证请求() {

    }

    @Override
    public void 待补充() {

    }
}
