package com.romankaarayo.services;
/**
 * @author KRV
 */

import com.neurotec.biometrics.*;
import com.neurotec.biometrics.client.NBiometricClient;
import com.neurotec.images.NImage;
import com.neurotec.io.NBuffer;
import com.neurotec.licensing.NLicense;


public class BiometricService{

    public BiometricService() {
        //LibraryManager.initLibraryPath();
        String components = "Biometrics.FaceExtraction,Biometrics.FaceMatching";
        String additionalComponents = "Biometrics.FaceSegmentsDetection";
        try {
            if (!NLicense.obtainComponents("/local", 5000, components)) {
                System.err.format("Could not obtain licenses for components: %s%n", components);
                System.exit(-1);
            }
            if (!NLicense.obtainComponents("/local", 5000, additionalComponents)) {
                components += "," + additionalComponents;
            }

            NBiometricClient client = FaceTools.getInstance().getClient();
            client.setDatabaseConnectionToSQLite("test.db");
            client.setMatchingThreshold(48);
            client.setFacesMatchingSpeed(NMatchingSpeed.LOW);
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}