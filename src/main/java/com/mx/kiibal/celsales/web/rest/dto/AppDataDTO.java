
package com.mx.kiibal.celsales.web.rest.dto;

import java.io.Serializable;
import javax.persistence.Lob;

/**
 *
 * @author cbautista
 */
public class AppDataDTO implements Serializable{
    private static final long serialVersionUID = 3170509101854110230L;
    
    private String appName;
    private String appPackage;
    private String appVersion;
    private String appInstalled;
    private String appLastModify;
    @Lob
    private byte[] appEncodedIcon;

    public AppDataDTO() {}

    public AppDataDTO(String appName, String appPackage, String appVersion, 
            String appInstalled, String appLastModify,byte[] appEncodedIcon) {
        this.appName = appName;
        this.appPackage = appPackage;
        this.appVersion = appVersion;
        this.appInstalled = appInstalled;
        this.appLastModify = appLastModify;
        this.appEncodedIcon = appEncodedIcon;
    }

    public String getAppName() {
        return appName;
    }

    public void setAppName(String appName) {
        this.appName = appName;
    }

    public String getAppPackage() {
        return appPackage;
    }

    public void setAppPackage(String appPackage) {
        this.appPackage = appPackage;
    }

    public String getAppVersion() {
        return appVersion;
    }

    public void setAppVersion(String appVersion) {
        this.appVersion = appVersion;
    }

    public String getAppInstalled() {
        return appInstalled;
    }

    public void setAppInstalled(String appInstalled) {
        this.appInstalled = appInstalled;
    }

    public String getAppLastModify() {
        return appLastModify;
    }

    public void setAppLastModify(String appLastModify) {
        this.appLastModify = appLastModify;
    }

    public byte[] getAppEncodedIcon() {
        return appEncodedIcon;
    }

    public void setAppEncodedIcon(byte[] appEncodedIcon) {
        this.appEncodedIcon = appEncodedIcon;
    }

    @Override
    public String toString() {
        return "AppDataDTO{" + "appName=" + appName + ", appPackage=" + appPackage 
                + ", appVersion=" + appVersion + ", appInstalled=" + appInstalled 
                + ", appLastModify=" + appLastModify + '}';
    }
    
    
}
