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
    public void formRequest() {
        mMainModel.formRequest()
                .subscribe(new AbsObjectSubscriber<String>() {
                    @Override
                    public void onSuccess(String response) {
                        mView.showData("formRequest 成功 " + response.toString());
                    }

                    @Override
                    public void onFailure(Throwable e) {
                        mView.showData("formRequest 失败 " + e.getMessage());
                    }
                });
    }

    @Override
    public void noParamsRequest() {
        mMainModel.noParamsRequest()
                .subscribe(new AbsObjectSubscriber<String>() {

                    @Override
                    public void onSuccess(String response) {
                        mView.showData("noParamsRequest 成功 " + response);
                    }

                    @Override
                    public void onFailure(Throwable e) {
                        mView.showData("noParamsRequest 失败 " + e.getMessage());
                    }
                });
    }

    @Override
    public void pathParamsRequest() {
        mMainModel.pathParamsRequest()
                .subscribe(new AbsObjectSubscriber<String>() {
                    @Override
                    public void onSuccess(String response) {
                        mView.showData("pathParamsRequest 成功 " + response);
                    }

                    @Override
                    public void onFailure(Throwable e) {
                        mView.showData("pathParamsRequest 失败 " + e.getMessage());
                    }
                });
    }

    @Override
    public void queryParamsRequest() {
        mMainModel.queryParamsRequest()
                .subscribe(new AbsObjectSubscriber<String>() {
                    @Override
                    public void onSuccess(String response) {
                        mView.showData("queryParamsRequest 成功 " + response);
                    }

                    @Override
                    public void onFailure(Throwable e) {
                        mView.showData("queryParamsRequest 失败 " + e.getMessage());
                    }
                });
    }

    @Override
    public void queryMapRequest() {
        mMainModel.queryMapRequest()
                .subscribe(new AbsObjectSubscriber<String>() {
                    @Override
                    public void onSuccess(String response) {
                        mView.showData("queryMapRequest 成功 " + response);
                    }

                    @Override
                    public void onFailure(Throwable e) {
                        mView.showData("queryMapRequest 失败 " + e.getMessage());
                    }
                });
    }

    @Override
    public void staticHeaderRequest() {
        mMainModel.staticHeaderRequest()
                .subscribe(new AbsObjectSubscriber<UserInfo>() {
                    @Override
                    public void onSuccess(UserInfo response) {
                        mView.showData("staticHeaderRequest 成功 " + response.toString());
                    }

                    @Override
                    public void onFailure(Throwable e) {
                        mView.showData("staticHeaderRequest 失败 " + e.getMessage());
                    }
                });
    }

    @Override
    public void changeHeaderRequest() {
        mMainModel.changeHeaderRequest()
                .subscribe(new AbsObjectSubscriber<String>() {
                    @Override
                    public void onSuccess(String response) {
                        mView.showData("changeHeaderRequest 成功 " + response);
                    }

                    @Override
                    public void onFailure(Throwable e) {
                        mView.showData("changeHeaderRequest 失败 " + e.getMessage());
                    }
                });
    }

    @Override
    public void interceptorRequest() {
        mMainModel.interceptorRequest()
                .subscribe(new AbsObjectSubscriber<String>() {
                    @Override
                    public void onFailure(Throwable e) {
                        mView.showData("interceptorRequest 失败 " + e.getMessage());
                    }

                    @Override
                    public void onSuccess(String s) {
                        mView.showData("interceptorRequest 成功 " + s.toString());
                    }
                });
    }

    @Override
    public void bodyRequest() {
        mMainModel.bodyRequest()
                .subscribe(new AbsObjectSubscriber<String>() {
                    @Override
                    public void onFailure(Throwable e) {
                        mView.showData("bodyRequest 失败 " + e.getMessage());
                    }

                    @Override
                    public void onSuccess(String s) {
                        mView.showData("bodyRequest 成功 " + s.toString());
                    }
                });
    }

    @Override
    public void urlRequest() {
        mMainModel.urlRequest()
                .subscribe(new AbsObjectSubscriber<String>() {
                    @Override
                    public void onFailure(Throwable e) {
                        mView.showData("urlRequest 失败 " + e.getMessage());
                    }

                    @Override
                    public void onSuccess(String s) {
                        mView.showData("urlRequest 成功 " + s.toString());
                    }
                });
    }

    @Override
    public void downloadFile() {
        mMainModel.pathParamsRequest()
                .subscribe(new AbsObjectSubscriber<String>() {
                    @Override
                    public void onFailure(Throwable e) {
                        mView.showData("downloadFile 失败 " + e.getMessage());
                    }

                    @Override
                    public void onSuccess(String s) {
                        mView.showData("downloadFile 成功 " + s.toString());
                    }
                });
    }

    @Override
    public void uploadFileRequest() {
        mMainModel.pathParamsRequest()
                .subscribe(new AbsObjectSubscriber<String>() {
                    @Override
                    public void onFailure(Throwable e) {
                        mView.showData("uploadFileRequest 失败 " + e.getMessage());
                    }

                    @Override
                    public void onSuccess(String s) {
                        mView.showData("uploadFileRequest 成功 " + s.toString());
                    }
                });
    }

    @Override
    public void netRequestSet() {
        mMainModel.pathParamsRequest()
                .subscribe(new AbsObjectSubscriber<String>() {
                    @Override
                    public void onFailure(Throwable e) {
                        mView.showData("netRequestSet 失败 " + e.getMessage());
                    }

                    @Override
                    public void onSuccess(String s) {
                        mView.showData("netRequestSet 成功 " + s.toString());
                    }
                });
    }

    @Override
    public void useDefaultBaseurl() {

    }

    @Override
    public void httpsRequest() {

    }

    @Override
    public void waitAdd() {

    }
}
