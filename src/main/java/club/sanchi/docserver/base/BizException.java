package club.sanchi.docserver.base;

/**
 * 自定义异常类
 * Created by wangpeng on 2018/12/5 20:24
 */
public class BizException extends RuntimeException{

    public static final String PARAM_ERR = "system-0001";

    private String code;

    public BizException(String code, String message) {
        super(message);
        this.code = code;
    }
}