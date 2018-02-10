package chen.wentong.netapidemo.ui;

import android.os.Bundle;
import android.view.View;

import butterknife.ButterKnife;
import butterknife.OnClick;
import chen.wentong.netapidemo.R;
import chen.wentong.netapidemo.base.BaseActivity;

public class MainActivity extends BaseActivity implements IMainContract.View, View.OnClickListener{

    private MainPresenter mPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        mPresenter = new MainPresenter(this, mLifecycleProvider);
        findViewById(R.id.form_request).setOnClickListener(this);
        findViewById(R.id.get_noparams).setOnClickListener(this);
        findViewById(R.id.get_path).setOnClickListener(this);
        findViewById(R.id.get_query).setOnClickListener(this);
        findViewById(R.id.get_querymap).setOnClickListener(this);
        findViewById(R.id.get_static_header).setOnClickListener(this);
        findViewById(R.id.get_change_header).setOnClickListener(this);
        findViewById(R.id.get_interceptor_header).setOnClickListener(this);
        findViewById(R.id.get_body).setOnClickListener(this);
        findViewById(R.id.get_url).setOnClickListener(this);
        findViewById(R.id.post_streaming).setOnClickListener(this);
        findViewById(R.id.post_multipart).setOnClickListener(this);
        findViewById(R.id.netconfig_use).setOnClickListener(this);
        findViewById(R.id.application_init_default).setOnClickListener(this);
        findViewById(R.id.https_sslsocket).setOnClickListener(this);
        findViewById(R.id.wait_add).setOnClickListener(this);
    }

    @Override
    public void showData(String data) {
        showLongToast(data);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.form_request:
                mPresenter.表单请求();
                break;
            case R.id.get_noparams:
                mPresenter.get无参请求();
                break;
            case R.id.get_path:
                mPresenter.get_path参数请求();
                break;
            case R.id.get_query:
                mPresenter.get_query参数请求();
                break;
            case R.id.get_querymap:
                mPresenter.get_queryMap请求();
                break;
            case R.id.get_static_header:
                mPresenter.get_静态请求头添加();
                break;
            case R.id.get_change_header:
                mPresenter.get_动态请求头添加();
                break;
            case R.id.get_interceptor_header:
                mPresenter.get_拦截器请求头添加();
                break;
            case R.id.get_body:
                mPresenter.get_body参数请求();
                break;
            case R.id.get_url:
                mPresenter.get_url参数请求();
                break;
            case R.id.post_streaming:
                mPresenter.下载文件请求();
                break;
            case R.id.post_multipart:
                mPresenter.上传文件请求();
                break;
            case R.id.netconfig_use:
                mPresenter.配合网络请求配置使用();
                break;
            case R.id.application_init_default:
                showLongToast("见MyApplication");
                break;
            case R.id.https_sslsocket:
                showLongToast("待补充添加证书测试");
                break;
            case R.id.wait_add:
                showLongToast("待补充");
                break;
        }
    }
}
