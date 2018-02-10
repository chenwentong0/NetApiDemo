package chen.wentong.netapidemo.bean;

/**
 * Created by wentong.chen on 18/2/10.
 * 功能：
 */

public class LocationInfo {
    private String longitude;                   //经度
    private String latitude;                    //纬度

    /**
     * 经度信息
     * @return
     */
    public String getLongitude() {
        return longitude;
    }

    /**
     * 纬度信息
     * @param longitude
     */
    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }

    /**
     * 纬度信息
     * @return
     */
    public String getLatitude() {
        return latitude;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }
}
