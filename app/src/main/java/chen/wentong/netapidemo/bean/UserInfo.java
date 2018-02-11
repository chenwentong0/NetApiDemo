package chen.wentong.netapidemo.bean;

import java.io.Serializable;

/**
 * Created by ${wentong.chen} on 18/1/24.
 * 用户信息
 */
public class UserInfo implements Serializable{
    private int id;
    private String login;
    private String avatar_url;

    public String getAvatarUrl() {
        if (avatar_url.isEmpty()) return avatar_url;
        return avatar_url.split("\\?")[0];
    }

    public int getId() {
        return id;
    }

    public String getLogin() {
        return login;
    }

    @Override public String toString() {
        return "id -> " + id + " login -> " + login;
    }
}
