package com.nuite.modules.sys.utils;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@ConfigurationProperties(prefix = "smart-data")
@Component
public class ConfigUtils {
    private String pictureBaseUrl;
    private String pictureBaseUrlShow;
    private String pictureBaseUploadProject;
    
    private String baseDir;
    private String baseBrand;
    private String baseUser;
    private String goodsShoes;
    private String sowingMap;
    
    private String websocketHost;
    private Integer websocketPort;
    private Integer ytgzCompanyseq;

   

    public String getPictureBaseUrl() {
        return pictureBaseUrl;
    }

    public void setPictureBaseUrl(String pictureBaseUrl) {
        this.pictureBaseUrl = pictureBaseUrl;
    }

    public String getPictureBaseUploadProject() {
		return pictureBaseUploadProject;
	}

	public void setPictureBaseUploadProject(String pictureBaseUploadProject) {
		this.pictureBaseUploadProject = pictureBaseUploadProject;
	}

	public String getBaseDir() {
        return baseDir;
    }

    public void setBaseDir(String baseDir) {
        this.baseDir = baseDir;
    }

    public String getGoodsShoes() {
        return goodsShoes;
    }

    public void setGoodsShoes(String goodsShoes) {
        this.goodsShoes = goodsShoes;
    }

    public String getBaseUser() {
        return baseUser;
    }

    public void setBaseUser(String baseUser) {
        this.baseUser = baseUser;
    }

    public String getBaseBrand() {
		return baseBrand;
	}

	public void setBaseBrand(String baseBrand) {
		this.baseBrand = baseBrand;
	}

	

    public String getSowingMap() {
        return sowingMap;
    }

    public void setSowingMap(String sowingMap) {
        this.sowingMap = sowingMap;
    }

	public String getWebsocketHost() {
		return websocketHost;
	}

	public void setWebsocketHost(String websocketHost) {
		this.websocketHost = websocketHost;
	}

	public Integer getWebsocketPort() {
		return websocketPort;
	}

	public void setWebsocketPort(Integer websocketPort) {
		this.websocketPort = websocketPort;
	}

	public Integer getYtgzCompanyseq() {
		return ytgzCompanyseq;
	}

	public void setYtgzCompanyseq(Integer ytgzCompanyseq) {
		this.ytgzCompanyseq = ytgzCompanyseq;
	}

    public String getPictureBaseUrlShow() {
        return pictureBaseUrlShow;
    }

    public void setPictureBaseUrlShow(String pictureBaseUrlShow) {
        this.pictureBaseUrlShow = pictureBaseUrlShow;
    }
}
