package chen.wentong.netapidemo.ui;

import android.os.Bundle;
import android.view.View;

import butterknife.ButterKnife;
import chen.wentong.netapidemo.R;
import chen.wentong.netapidemo.base.BaseActivity;

/**
 * 首页
 */
public class MainActivity extends BaseActivity implements IMainContract.View, View.OnClickListener {

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
                mPresenter.formRequest();
                break;
            case R.id.get_noparams:
                mPresenter.noParamsRequest();
                break;
            case R.id.get_path:
                mPresenter.pathParamsRequest();
                break;
            case R.id.get_query:
                mPresenter.queryParamsRequest();
                break;
            case R.id.get_querymap:
                mPresenter.queryMapRequest();
                break;
            case R.id.get_static_header:
                mPresenter.staticHeaderRequest();
                break;
            case R.id.get_change_header:
                mPresenter.changeHeaderRequest();
                break;
            case R.id.get_interceptor_header:
                mPresenter.interceptorRequest();
                break;
            case R.id.get_body:
                mPresenter.bodyRequest();
                break;
            case R.id.get_url:
                mPresenter.urlRequest();
                break;
            case R.id.post_streaming:
                mPresenter.downloadFile();
                break;
            case R.id.post_multipart:
                mPresenter.uploadFileRequest();
                break;
            case R.id.netconfig_use:
                mPresenter.netRequestSet();
                break;
            case R.id.application_init_default:
                showLongToast("见MyApplication");
                break;
            case R.id.https_sslsocket:
                showLongToast("待补充添加证书测试");
                break;
            case R.id.wait_add:
                showLongToast("waitAdd");
                break;
            default:
                break;
        }
    }
}
