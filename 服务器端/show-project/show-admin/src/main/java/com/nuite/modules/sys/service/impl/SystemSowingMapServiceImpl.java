package com.nuite.modules.sys.service.impl;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.google.common.base.Joiner;
import com.nuite.common.utils.FileUtils;
import com.nuite.common.utils.MapUtils;
import com.nuite.common.utils.PageUtils;
import com.nuite.modules.sys.dao.ShoeViewDao;
import com.nuite.modules.sys.dao.SystemSowingMapDao;
import com.nuite.modules.sys.entity.PageInfo;
import com.nuite.modules.sys.entity.PeriodEntity;
import com.nuite.modules.sys.entity.ShoeViewEntity;
import com.nuite.modules.sys.entity.SystemSowingMapEntity;
import com.nuite.modules.sys.service.PeriodService;
import com.nuite.modules.sys.service.SystemSowingMapService;
import com.nuite.modules.sys.utils.ConfigUtils;


@Service
public class SystemSowingMapServiceImpl extends ServiceImpl<SystemSowingMapDao, SystemSowingMapEntity> implements SystemSowingMapService {

    @Autowired
    private SystemSowingMapDao systemSowingMapDao;
    @Autowired
    private ConfigUtils configUtils;
    @Autowired
    private PeriodService periodService;
    
    @Autowired
    private ShoeViewDao shoeViewDao;

    @Override
    public PageUtils sowingMapList(Integer brandSeq) {
        List<SystemSowingMapEntity> sowingMapList;

        EntityWrapper<SystemSowingMapEntity> ew = new EntityWrapper<SystemSowingMapEntity>();
        ew.eq("Brand_Seq", brandSeq).eq("Del", 0);
        sowingMapList = systemSowingMapDao.selectList(ew);
        for (SystemSowingMapEntity systemSowingMapEntity : sowingMapList) {
            String imageSrc = configUtils.getPictureBaseUrl() + "/"
                    + configUtils.getSowingMap() + "/" + systemSowingMapEntity.getBrandSeq() + "/"
                    + systemSowingMapEntity.getImage();
            systemSowingMapEntity.setImage(imageSrc);
        }
        return new PageUtils(sowingMapList, sowingMapList.size(), 1, 1);
    }

    @Override
    public PageUtils goodList(Integer brandSeq, PageInfo pageInfo) {
        if (brandSeq == null) {
            return new PageUtils(Collections.emptyList(), 0, 1, 1);
        }
        List<Integer> periodSeqList = new ArrayList<Integer>();
        List<PeriodEntity> goodsPeriodList = periodService.selectByMap(new MapUtils().put("BrandSeq", brandSeq).put("Del", 0));
        for (PeriodEntity goodsPeriodEntity : goodsPeriodList) {
            if (goodsPeriodEntity != null) {
                periodSeqList.add(goodsPeriodEntity.getSeq());
            }
        }

        if (periodSeqList.isEmpty()) {
            return new PageUtils(Collections.emptyList(), 0, 1, 1);
        }

        Page<Map<String, Object>> page = new Page<>(pageInfo.getNowpage(), pageInfo.getSize());
        page.setAsc("asc".equalsIgnoreCase(pageInfo.getOrder()));
        page.setOrderByField(pageInfo.getSort());
        pageInfo.getCondition().put("periodSeq", Joiner.on(",").join(periodSeqList));
        List<Map<String, Object>> list = systemSowingMapDao.selectGoodsList(page, pageInfo.getCondition());
        for (Map<String, Object> maps : list) {
        	ShoeViewEntity shoeViewEntity = shoeViewDao.selectById((Integer)maps.get("seq"));
            maps.put("img1", getShoePictureUrl(shoeViewEntity.getPeriodSeq(), shoeViewEntity.getShoeID()));
        }
        return new PageUtils(list, page.getTotal(), pageInfo.getSize(), pageInfo.getNowpage());
    }

    @Override
    public SystemSowingMapEntity edit(Integer seq) {
        SystemSowingMapEntity systemSowingMapEntity = new SystemSowingMapEntity();
        if (seq != null) {
            systemSowingMapEntity.setSeq(seq);
            systemSowingMapEntity = systemSowingMapDao.selectById(systemSowingMapEntity);
            String imageSrc = configUtils.getPictureBaseUrl() + "/" 
                    + configUtils.getSowingMap() + "/" + systemSowingMapEntity.getBrandSeq() + "/"
                    + systemSowingMapEntity.getImage();
            systemSowingMapEntity.setImage(imageSrc);
        }
        return systemSowingMapEntity;
    }

    @Override
    public Integer getCreatedNumber(Integer brandSeq) {
        List<SystemSowingMapEntity> sowingMapList;
        EntityWrapper<SystemSowingMapEntity> ew = new EntityWrapper<SystemSowingMapEntity>();
        ew.eq("Brand_Seq", brandSeq).eq("Del", 0);
        sowingMapList = systemSowingMapDao.selectList(ew);

        return sowingMapList.size();
    }

    @Override
    public void save(Integer brandSeq, SystemSowingMapEntity systemSowingMapEntity) throws IOException {
        systemSowingMapEntity.setBrandSeq(brandSeq);

        String imageGoodsDir = FileUtils.getWebAppsPath() + configUtils.getPictureBaseUploadProject()
                + File.separator + configUtils.getSowingMap() + File.separator
                + brandSeq + File.separator;

        MultipartFile image = systemSowingMapEntity.getImageFile();
        if (image == null || image.getSize() <= 0) {
            return;
        } else {
            String fileName = FileUtils.upLoadFile(imageGoodsDir, null, image);
            systemSowingMapEntity.setImage(fileName);
        }
        systemSowingMapDao.insert(systemSowingMapEntity);
    }

    @Override
    public void update(Integer brandSeq, SystemSowingMapEntity systemSowingMapEntity) throws IOException {
        if (systemSowingMapEntity.getSeq() != null) {
            systemSowingMapEntity.setBrandSeq(brandSeq);

            if (systemSowingMapEntity.getImageFile() != null) {

                String imageGoodsDir = FileUtils.getWebAppsPath() + configUtils.getPictureBaseUploadProject()
                        + File.separator + configUtils.getSowingMap()
                        + File.separator + brandSeq + File.separator;

                MultipartFile imageFile = systemSowingMapEntity.getImageFile();
                if (imageFile == null || imageFile.getSize() <= 0) {
                    return;
                } else {
                    String fileName = FileUtils.upLoadFile(imageGoodsDir, null, imageFile);
                    systemSowingMapEntity.setImage(fileName);
                }
            }
            systemSowingMapDao.updateById(systemSowingMapEntity);
        }

    }

    /**
     * 波次列表
     */
	@Override
	public PageUtils periodList(Integer brandSeq, Map<String, Object> params) {
        Page<PeriodEntity> nowPage = new Page<PeriodEntity>(Integer.parseInt((String)params.get("page")), Integer.parseInt((String)params.get("limit")));
        
        nowPage.setAsc("asc".equalsIgnoreCase((String) params.get("order")));
        nowPage.setOrderByField((String) params.get("sidx"));
        Wrapper<PeriodEntity> wrapper = new EntityWrapper<PeriodEntity>();
		wrapper.where("BrandSeq = {0} AND isValid=1", 1);
        Page<PeriodEntity> page = periodService.selectPage(nowPage, wrapper);
        return new PageUtils(page);
    }
	public String getShoePictureUrl(Integer periodSeq, String shoeId) {
		String baseUrl = "http://www.nuite.com.cn/ShoesManage/Resource/ShoesResource/ShoesIcons";
		return baseUrl + "/" + periodSeq + "/" + shoeId + ".jpg";
	}
}
