package org.ucomplex.ucomplex.Common.base;

import android.app.Application;

import org.ucomplex.ucomplex.Common.AppDiComponent;
import org.ucomplex.ucomplex.Common.DaggerAppDiComponent;
import org.ucomplex.ucomplex.Common.FacadeCommon;
import org.ucomplex.ucomplex.Common.FacadePreferences;
import org.ucomplex.ucomplex.Domain.Users.User;
import org.ucomplex.ucomplex.Domain.Users.UserInterface;

import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;

import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;

/**
 * ---------------------------------------------------
 * Created by Sermilion on 04/03/2017.
 * Project: OneAccount
 * ---------------------------------------------------
 * <a href="http://www.ucomplex.org">www.ucomplex.org</a>
 * <a href="http://www.github.com/sermilion>github</a>
 * ---------------------------------------------------
 */

public class UCApplication extends Application {

    public static final String BASE_URL = "https://ucomplex.org/";
    public static final String BASE_FILES_URL = "https://storage.ucomplex.org/";
    public static final String PHOTOS_URL = BASE_URL + "files/photos/";
    public static final String PHOTOS_ORIGINAL_URL = BASE_URL + "files/original/";
    public static final String FORMAT_JPG = ".jpg";

    private static UCApplication INSTANCE;
    public  static UCApplication getInstance() {
        return INSTANCE;
    }
    private String authString;
    private UserInterface loggedUser;
    private AppDiComponent appDiComponent;
    private boolean connectedToInternet;
    private boolean eventsCached;

    @Override 
    public void onCreate() {
        super.onCreate();
        INSTANCE = this;
        authString = FacadePreferences.getTokenFromPref(this);
        loggedUser = FacadePreferences.getUserDataFromPref(this);
        appDiComponent = DaggerAppDiComponent.builder().build();
        configureConnectionTrust();
        connectedToInternet = FacadeCommon.isNetworkConnected(this);
        eventsCached = false;
    }

    void configureConnectionTrust(){
        HttpsURLConnection.setDefaultHostnameVerifier((hostname, session) -> true);
        SSLContext context;
        try {
            context = SSLContext.getInstance("TLS");
            context.init(null, trustAllCerts, new java.security.SecureRandom());
            HttpsURLConnection.setDefaultSSLSocketFactory(context.getSocketFactory());
        } catch (NoSuchAlgorithmException | KeyManagementException e) {
            e.printStackTrace();
        }
    }

    public void saveLoginData(String authString, UserInterface user) {
        String encodedToken = FacadeCommon.encodeLoginData(authString);
        this.setAuthString(encodedToken);
        FacadePreferences.setTokenToPref(this, encodedToken, true);
        FacadePreferences.setUserDataToPrefSync(this, user);
        this.setLoggedUser(user);
    }

    public AppDiComponent getAppDiComponent() {
        return appDiComponent;
    }

    public String getAuthString() {
        return authString;
    }

    public void setAuthString(String authString) {
        this.authString = authString;
    }

    public UserInterface getLoggedUser() {
        return loggedUser;
    }

    public void setLoggedUser(UserInterface loggedUser) {
        this.loggedUser = loggedUser;
    }

    public boolean isConnectedToInternet() {
        return connectedToInternet;
    }

    public void setConnectedToInternet(boolean connectedToInternet) {
        this.connectedToInternet = connectedToInternet;
    }

    public boolean isEventsCached() {
        return eventsCached;
    }

    public void setEventsCached(boolean eventsCached) {
        this.eventsCached = eventsCached;
    }

    private static TrustManager[] trustAllCerts = new TrustManager[] {
            new X509TrustManager() {

                @Override
                public void checkClientTrusted(java.security.cert.X509Certificate[] x509Certificates, String s) throws java.security.cert.CertificateException {
                    // not implemented
                }

                @Override
                public void checkServerTrusted(java.security.cert.X509Certificate[] x509Certificates, String s) throws java.security.cert.CertificateException {
                    // not implemented
                }

                @Override
                public java.security.cert.X509Certificate[] getAcceptedIssuers() {
                    return null;
                }

            }
    };
}
