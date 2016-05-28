package com.romankaarayo.services;
/**
 * @author KRV
 */

import com.neurotec.biometrics.NMatchingSpeed;
import com.neurotec.biometrics.client.NBiometricClient;
import com.neurotec.licensing.NLicense;
import com.romankaarayo.util.AppConst;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public final class FaceTools {
	private final Logger logger = LogManager.getLogger(FaceTools.class);
	// ===========================================================
	// Private static fields
	// ===========================================================

	private static final String ADDRESS = "/local";
	private static final String PORT = "5000";
	private static FaceTools instance;

	// ===========================================================
	// Public static methods
	// ===========================================================

	public static FaceTools getInstance() {
		synchronized (FaceTools.class) {
			if (instance == null) {
				instance = new FaceTools();
			}
			return instance;
		}
	}

	// ===========================================================
	// Private fields
	// ===========================================================

	private final NBiometricClient client;
	private final NBiometricClient defaultClient;
	private final Map<String, Boolean> obtainedLicenses;

	// ===========================================================
	// Private constructor
	// ===========================================================

	private FaceTools() {
		obtainedLicenses = new HashMap<String, Boolean>();
		client = new NBiometricClient();
		defaultClient = new NBiometricClient();

		String components = "Biometrics.FaceExtraction,Biometrics.FaceMatching,Biometrics.FaceDetection,Biometrics.FaceQualityAssessment,Biometrics.FaceSegmentation";
		String additionalComponents = "Biometrics.FaceSegmentsDetection";
		try {
			if (!NLicense.obtainComponents("/local", 5000, components)) {
				System.err.format("Could not obtain licenses for components: %s%n", components);
				logger.error(String.format("Could not obtain licenses for components: %s%n", components));
				System.exit(-1);
			}
			if (!NLicense.obtainComponents("/local", 5000, additionalComponents)) {
				components += "," + additionalComponents;
			}

			//NBiometricClient client = FaceTools.getInstance().getClient();
//			client.setDatabaseConnectionToSQLite("test.db");
			client.setDatabaseConnectionToSQLite(AppConst.getDbLocation());
			client.setMatchingThreshold(24);
            /*
            100 	% 0
            10 	    % 12
            1 	    % 24
            0.1 	% 36
            0.01    % 48
            0.001   % 60 */
			client.setFacesMatchingSpeed(NMatchingSpeed.LOW);
		}catch (Exception e){
			e.printStackTrace();
			logger.error(e.toString());
		}
	}

	// ===========================================================
	// Private methods
	// ===========================================================

	private boolean obtain(String address, String port, List<String> licenses) throws Exception {
		if (licenses == null) {
			throw new RuntimeException("Null license list");
		}

		boolean result = true;
		for (String license : licenses) {
			if (!isLicenseObtained(license)) {
				boolean state = NLicense.obtainComponents(address, port, license);
				obtainedLicenses.put(license, state);
				System.out.println(license + ": " + (state ? "obtainted" : "not obtained"));
			} else {
				System.out.println(license + ": " + " already obtained");
			}
		}
		return result;
	}

	private boolean isLicenseObtained(String license) {
		if (obtainedLicenses.containsKey(license)) {
			return obtainedLicenses.get(license);
		} else {
			return false;
		}
	}

	private synchronized void release(List<String> licenses) {
		if (licenses != null && !licenses.isEmpty()) {
			String components = licenses.toString().replace("[", "").replace("]", "").replace(" ", "");
			try {
				System.out.println("Releasing licenses: " + components);
				NLicense.releaseComponents(components);
				licenses.clear();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	// ===========================================================
	// Public methods
	// ===========================================================

	public void resetParameters() {
		client.reset();
	}

	public boolean obtainLicenses(List<String> licenses) throws Exception {
		return obtain(ADDRESS, PORT, licenses);
	}

	public void releaseLicenses() {
		release(new ArrayList<String>(obtainedLicenses.keySet()));
	}

	public NBiometricClient getClient() {
		return client;
	}

	public NBiometricClient getDefaultClient() {
		return defaultClient;
	}

	public Map<String, Boolean> getLicenses() {
		return obtainedLicenses;
	}

}
