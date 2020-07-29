package com.nuite.common.utils;

import javax.servlet.http.HttpServletRequest;
import java.io.File;

/**
 * @Author: yangchuang
 * @Date: 2019/1/13 11:26
 * @Version: 1.0
 */

public class PathUtils {

    public static String getWebAppsPath() {
        String filePath = System.getProperty("catalina.home");
        return filePath + File.separator + "webapps" + File.separator;
    }


    public static String getProjectPath(HttpServletRequest request){
        return request.getServletContext().getRealPath("/");
    }
}
