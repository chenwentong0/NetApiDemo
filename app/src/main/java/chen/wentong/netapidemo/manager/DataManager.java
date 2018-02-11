package chen.wentong.netapidemo.manager;

import chen.wentong.netapidemo.bean.LocationInfo;

/**
 * Created by wentong.chen on 18/2/10.
 * 功能：数据管理类
 */
public final class DataManager {
    private static DataManager sDataManager = new DataManager();
    private LocationInfo mLocationInfo;
    private DataManager() {

    }

    public static DataManager getInstance() {
        return sDataManager;
    }

    /**
     * 获取此时的定位信息
     * @return 定位信息
     */
    public LocationInfo getLocationInfo() {
        if (mLocationInfo == null) {
            mLocationInfo = new LocationInfo();
            mLocationInfo.setLatitude("1");
            mLocationInfo.setLongitude("4");
        }
        return mLocationInfo;
    }
}
