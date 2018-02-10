package chen.wentong.netapidemo.net.error;

/**
 * Created by wentong.chen on 18/1/26.
 * 功能：网络异常基类
 */

public class BaseException extends Exception {
    private int code;
    private String message;

    public BaseException(Exception e) {
        super(e);
    }

    public BaseException(String message) {
        super(message);
    }

    public BaseException(int code, String message) {
        super(message);
        this.code = code;
    }

    /**
     * 获取的错误信息
     * @return
     */
    @Override
    public String getMessage() {
       return message;
    }

    /**
     * 获取错误码
     * @return
     */
    public int getCode() {
       return code;
    }
}
