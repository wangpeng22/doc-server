package club.sanchi.docserver.util;

import club.sanchi.docserver.base.BizException;

/**
 * 断言工具
 * Created by wangpeng on 2018/12/5 20:22
 */
public class AssertUtil {

    public static void isEmpty(String s, String msg) {
        if (null == s || "".equals(s)) {
            throw new BizException(BizException.PARAM_ERR, null == msg || "".equals(msg) ? "参数为空" : msg);
        }
    }
}
