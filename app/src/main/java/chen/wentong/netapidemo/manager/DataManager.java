package chen.wentong.netapidemo.manager;

import chen.wentong.netapidemo.bean.LocationInfo;

/**
 * Created by wentong.chen on 18/2/10.
 * 功能：
 */

public class DataManager {
    private static DataManager sDataManager = new DataManager();
    private LocationInfo mLocationInfo;
    private DataManager() {

    }

    public static DataManager getInstance() {
        return sDataManager;
    }

    public LocationInfo getLocationInfo() {
        if (mLocationInfo == null) {
            mLocationInfo = new LocationInfo();
            mLocationInfo.setLatitude("1");
            mLocationInfo.setLongitude("4");
        }
        return mLocationInfo;
    }
}
