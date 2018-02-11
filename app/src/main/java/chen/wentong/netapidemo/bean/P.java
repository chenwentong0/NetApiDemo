package chen.wentong.netapidemo.bean;

import com.apkfuns.logutils.LogUtils;

/**
 * p信息
 */
public class P {

    public static final int DEFAULT_SIZE = 10;

    private int page;

    private String key;

    private int categoryId;

    private int size = DEFAULT_SIZE;

    public P() {

    }

    public P(int page, String key) {
        this.page = page;
        this.key = key;
    }

    public P(int page, String key, int size) {
        this.page = page;
        this.key = key;
        this.size = size;
        LogUtils.d(toString());
    }

    public P(int page, String key, int categoryId, int size) {
        this.page = page;
        this.key = key;
        this.categoryId = categoryId;
        this.size = size;
    }

    /**
     *
     * @return 页数
     */
    public int getPage() {
        return page;
    }

    /**
     *
     * @param page 页数
     */
    public void setPage(int page) {
        this.page = page;
    }

    /**
     *
     * @return key
     */
    public String getKey() {
        return key;
    }

    /**
     *
     * @param key key
     */
    public void setKey(String key) {
        this.key = key;
    }

    /**
     * 分类id
     * @return categoryId
     */
    public int getCategoryId() {
        return categoryId;
    }

    /**
     *
     * @param categoryId 分类id
     */
    public void setCategoryId(int categoryId) {
        this.categoryId = categoryId;
    }

    /**
     *
     * @return 每一页个数
     */
    public int getSize() {
        return size;
    }

    /**
     *
     * @param size 每一页个数
     */
    public void setSize(int size) {
        this.size = size;
    }

    @Override
    public String toString() {
        return "P{"
                + "page=" + page
                + ", key='" + key + '\''
                + ", categoryId=" + categoryId
                + ", size=" + size
                + '}';
    }
}
