package chen.wentong.netapidemo.bean;

/**
 * Created by wentong.chen on 18/2/10.
 * 功能：经纬度信息
 */
public class LocationInfo {
    private String longitude;                   //经度
    private String latitude;                    //纬度

    /**
     * 经度信息
     * @return 经度信息
     */
    public String getLongitude() {
        return longitude;
    }

    /**
     * 设置经度信息
     * @param longitude 经度值
     */
    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }

    /**
     * 纬度信息
     * @return  纬度信息
     */
    public String getLatitude() {
        return latitude;
    }

    /**
     * 设置纬度值
     * @param latitude 纬度值
     */
    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }
}
