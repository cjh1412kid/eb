package com.nuite.common.utils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 返回数据
 *
 * @author chenshun
 * @email sunlightcs@gmail.com
 * @date 2016年10月27日 下午9:59:27
 */
public class R extends HashMap<String, Object> {
    private static final long serialVersionUID = 1L;

    public static final int SUCCESS = 1;
    public static final int DATA_ERROR = 0;
    public static final int SYSTEM_ERROR = -1;
    public static final int DATA_BLANK = -10001;

    public R() {
        put("code", SUCCESS);
        put("msg", "操作成功");
    }

    public static R error() {
        return error(500, "未知异常，请联系管理员");
    }

    public static R error(String msg) {
        return error(500, msg);
    }

    public static R error(int code, String msg) {
        R r = new R();
        r.put("code", code);
        r.put("msg", msg);
        return r;
    }

    public static R ok() {
        return new R();
    }

    public static R ok(String msg) {
        R r = new R();
        r.put("msg", msg);
        return r;
    }

    public static R ok(Map<String, Object> map) {
        R r = new R();
        List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
        if (map != null) {
            list.add(map);
        }
        r.put("result", list);
        return r;
    }

    public static R ok(List<Map<String, Object>> list) {
        R r = new R();
        r.put("result", list);
        return r;
    }

    public static R ok(Object obj) {
        R r = new R();
        r.put("result", obj);
        return r;
    }

    public R result(Object obj) {
        super.put("result", obj);
        return this;
    }

    @Override
    public R put(String key, Object value) {
        super.put(key, value);
        return this;
    }
}
