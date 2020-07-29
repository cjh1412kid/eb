package com.nuite.utils;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@ConfigurationProperties(prefix = "smart-data")
@Component
public class ConfigUtils {
    private String pictureUrl;
    private String pictureUploadProject;
    private String nwUser;
    private String assistant;
    private String sowingMap;
    
    
	public String getPictureUrl() {
		return pictureUrl;
	}
	public void setPictureUrl(String pictureUrl) {
		this.pictureUrl = pictureUrl;
	}
	public String getPictureUploadProject() {
		return pictureUploadProject;
	}
	public void setPictureUploadProject(String pictureUploadProject) {
		this.pictureUploadProject = pictureUploadProject;
	}
	public String getNwUser() {
		return nwUser;
	}
	public void setNwUser(String nwUser) {
		this.nwUser = nwUser;
	}
	public String getAssistant() {
		return assistant;
	}
	public void setAssistant(String assistant) {
		this.assistant = assistant;
	}
	public String getSowingMap() {
		return sowingMap;
	}
	public void setSowingMap(String sowingMap) {
		this.sowingMap = sowingMap;
	}
	
}
