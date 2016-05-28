package com.romankaarayo.util;

import com.sun.jna.Platform;

/**
 * @author Chathura Widanage
 */
public class AppConst {
    public static String imageLocation;//="/opt/";

    static {
        // "static constructor"
        if (Platform.isWindows()) {
            imageLocation =  "E:\\SDK\\images";
        } else if (Platform.isLinux()) {
            imageLocation = "/opt";
        }
    }
}
