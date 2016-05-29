package com.romankaarayo.util;

import com.sun.jna.Platform;

/**
 * @author Chathura Widanage
 */
public class AppConst {
    private static String imagePath;

    public static void setImagePath(String path) {
        imagePath = path;
    }

    public static String getImageLocation() {
        if (imagePath != null) {
            return imagePath;
        }

        // "static constructor"
        if (Platform.isWindows()) {
            return "E:\\SDK\\images\\";
        }
        return "/opt/images/";
    }

    public static String getDbLocation() {
        if (Platform.isWindows()) {
            return "E:\\SDK\\images\\test.db";
        }
        return "/opt/test.db";
    }
}
