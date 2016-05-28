package com.romankaarayo.util;

import com.sun.jna.Platform;

/**
 * @author Chathura Widanage
 */
public class AppConst {
    public static String getImageLocation() {
        // "static constructor"
        if (Platform.isWindows()) {
            return "E:\\SDK\\images\\";
        }
        return "/opt/";
    }

    public static String getDbLocation() {
        if (Platform.isWindows()) {
            return "E:\\SDK\\images\\test.db";
        }
        return "/opt/test.db";
    }
}
