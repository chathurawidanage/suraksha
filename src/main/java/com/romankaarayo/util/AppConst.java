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
}
