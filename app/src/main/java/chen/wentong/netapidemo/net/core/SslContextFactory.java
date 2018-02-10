package chen.wentong.netapidemo.net.core;

import android.content.Context;

import com.apkfuns.logutils.LogUtils;

import java.io.InputStream;
import java.security.KeyStore;

import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.TrustManagerFactory;

import chen.wentong.netapidemo.R;

/**
 * ssl证书
 */
public class SslContextFactory {
    static final String CLIENT_TRUST_PASSWORD = "123456";//信任证书密码，该证书默认密码是123456
    static final String CLIENT_AGREEMENT = "TLS";//使用协议
    static final String CLIENT_TRUST_KEYSTORE = "BKS";
    /**
     * 获取bks文件的sslsocketfactory
     * @param context
     * @return
     */
    public static SSLSocketFactory getSSLSocketFactory(Context context) {

        SSLContext sslContext = null;
        try {
            //取得SSL的SSLContext实例
            sslContext = SSLContext.getInstance(CLIENT_AGREEMENT);
            //取得TrustManagerFactory的X509密钥管理器实例
            TrustManagerFactory trustManager = TrustManagerFactory.getInstance(TrustManagerFactory.getDefaultAlgorithm());
            //取得BKS密库实例
            KeyStore tks = KeyStore.getInstance(CLIENT_TRUST_KEYSTORE);
            InputStream is = context.getResources().openRawResource(R.raw.traint);
            try {
                tks.load(is, CLIENT_TRUST_PASSWORD.toCharArray());
            } finally {
                is.close();
            }
            //初始化密钥管理器
            trustManager.init(tks);
            //初始化SSLContext
            sslContext.init(null, trustManager.getTrustManagers(), null);
        } catch (Exception e) {
            e.printStackTrace();
            LogUtils.e("SslContextFactory", e.getMessage());
        }
        return sslContext.getSocketFactory();
    }
}