package learn.yh.code;

/**
 * Description:
 * Created on:  2019/12/9 下午8:35
 *
 * @author yanghuang3@jd.com
 */
public enum Operation {
    PRASE("p")
    ;
    private String code;

    Operation(String code) {
        this.code = code;
    }

    public String getCode() {
        return code;
    }
}
