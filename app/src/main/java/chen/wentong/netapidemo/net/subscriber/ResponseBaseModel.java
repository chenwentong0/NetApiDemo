package chen.wentong.netapidemo.net.subscriber;

/**
 * Created by ${wentong.chen} on 18/1/23.
 * 数据返回样式
 * @param <T> 转换的bean类型
 */
public class ResponseBaseModel<T> {
    private String ret;
    private String msg;
    private T info;

    public String getRet() {
        return ret;
    }

    public void setRet(String ret) {
        this.ret = ret;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public T getInfo() {
        return info;
    }

    public void setInfo(T info) {
        this.info = info;
    }
}
