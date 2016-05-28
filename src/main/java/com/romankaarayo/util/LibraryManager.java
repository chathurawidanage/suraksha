package com.romankaarayo.util;

import java.lang.reflect.Field;

import com.sun.jna.Platform;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public final class LibraryManager {
	private static final Logger logger = LogManager.getLogger(LibraryManager.class);
	// ===========================================================
	// Private static fields
	// ===========================================================
	private static final String MAC_OS = "/Library/Frameworks/";

	// ===========================================================
	// Public static methods
	// ===========================================================

	public static void initLibraryPath() {
		String libraryPath = getLibraryPath();
		logger.debug(libraryPath);
		String jnaLibraryPath = System.getProperty("jna.library.path");
		if (Utils.isNullOrEmpty(jnaLibraryPath)) {
			System.setProperty("jna.library.path", libraryPath.toString());
		} else {
			System.setProperty("jna.library.path", String.format("%s%s%s", jnaLibraryPath, Utils.PATH_SEPARATOR, libraryPath.toString()));
		}
		System.setProperty("java.library.path",String.format("%s%s%s", System.getProperty("java.library.path"), Utils.PATH_SEPARATOR, libraryPath.toString()));

		try {
			Field fieldSysPath = ClassLoader.class.getDeclaredField("sys_paths");
			fieldSysPath.setAccessible(true);
			fieldSysPath.set(null, null);
		} catch (SecurityException e) {
			e.printStackTrace();
		} catch (NoSuchFieldException e) {
		} catch (IllegalArgumentException e) {
		} catch (IllegalAccessException e) {
		}
	}

	public static String getLibraryPath() {
		StringBuilder path = new StringBuilder();
		if (Platform.isWindows()) {
				path.append("E:\\SDK\\Neurotec_Biometric_6_0_SDK_Trial\\Bin\\Win64_x64");
		} else if (Platform.isLinux()) {
			path.append("/media/softwares/temp/Neurotec_Biometric_6_0_SDK_Trial/Lib/Linux_x86_64");
		} else if (Platform.isMac()) {
			path.append(MAC_OS);
		}

		return path.toString();
	}
}
