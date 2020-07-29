package com.nuite.controller;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.nuite.annotation.Login;
import com.nuite.annotation.LoginUser;
import com.nuite.common.utils.R;
import com.nuite.entity.AnnouncementEntity;
import com.nuite.entity.CategoryEntity;
import com.nuite.entity.PeriodEntity;
import com.nuite.entity.UeditorRecordEntity;
import com.nuite.entity.UserEntity;
import com.nuite.service.CategoryService;
import com.nuite.service.ProductHomepageService;
import com.nuite.service.UeditorService;
import com.nuite.service.PeriodService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import springfox.documentation.annotations.ApiIgnore;


/**
 * 2019产品版： 首页接口
 * @author yy
 * @date 2019-07-24 13:47
 */
@RestController
@RequestMapping("/api/product/homepage")
@Api(tags = "2019产品版： 首页接口")
public class ProductHomepageController extends BaseController {
	
	private Logger logger = LoggerFactory.getLogger(getClass());
    
    @Autowired
    private ProductHomepageService homepageService;
    
    @Autowired
    private CategoryService categoryService;
    
    @Autowired
    private PeriodService periodService;
    
    @Autowired
    private UeditorService ueditorService;
    
    
    
	/**
     * 轮播图列表
     */
    @Login
    @GetMapping("carousel")
    @ApiOperation("轮播图列表")
    public R getImgMainUrl(@ApiIgnore @LoginUser UserEntity loginUser) {
    	try {
    		List<Map<String, Object>> carouselList =  homepageService.getHomeCarouselList(loginUser.getBrandSeq());

    		for(Map<String, Object> carousel : carouselList) {
    			//图片路径
    			carousel.put("image", getHomeCarouselPictureUrl(carousel.get("brandSeq").toString()) + carousel.get("image"));
    			
    			int type = (int) carousel.get("type");
    			//关联分类或波次，添加标题
    			if(type == 2 || type == 3) {
	    			StringBuilder title = new StringBuilder();
	    			List<String> linkSeq = Arrays.asList(carousel.get("linkSeq").toString().split(","));
	    			if(type == 2) {
	    				//拼接分类名称
	    				List<CategoryEntity> goodsCategoryList = categoryService.selectBatchIds(linkSeq);
	    				for(CategoryEntity goodsCategory : goodsCategoryList) {
	    					title.append(goodsCategory.getName()).append(",");
	    				}
	    			} else if (type == 3) {
	    				//拼接波次名称
	    				List<PeriodEntity> goodsPeriodList = periodService.selectBatchIds(linkSeq);
	    				for(PeriodEntity goodsPeriod : goodsPeriodList) {
	    					title.append(goodsPeriod.getName()).append(",");
	    				}
	    			}
	    			title.deleteCharAt(title.length() - 1);
	    			
	    			carousel.put("title", title);
    			}
    		}
    		
			return R.ok(carouselList);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(e.getMessage(), e);
			return R.error("服务器异常");
		}

    }
    
    
    
    
	/**
     * 公告列表
     */
    @Login
    @GetMapping("announcement")
    @ApiOperation("公告列表")
    public R getAnnouncement(@ApiIgnore @LoginUser UserEntity loginUser) {
    	try {
    		//根据Company_Seq获取公告
    		List<AnnouncementEntity> announcementList =  homepageService.getAnnouncementByCompanySeq(loginUser.getCompanySeq());
			if (announcementList != null && announcementList.size() > 0) {
				for (AnnouncementEntity announcement : announcementList) {
					if (announcement.getType() == 1) {
						announcement.setTypeName("新品");
					} else if (announcement.getType() == 2) {
						announcement.setTypeName("直播");
					}
				}
			}
			return R.ok(announcementList);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(e.getMessage(), e);
			return R.error("服务器异常");
		}

    }
    
    
    
    
    /**
     * 自定义首页
     */
    @Login
    @GetMapping("ueditorHtml")
    @ApiOperation("自定义首页")
    public R getUeditorContent(@ApiIgnore @LoginUser UserEntity loginUser) {
        try {
            Integer company_Seq = loginUser.getCompanySeq();
            UeditorRecordEntity ur = ueditorService.getByCompanySeqAndUsed(company_Seq);
            if (ur != null) {
                String ueditorContent = ur.getContent();
                String[] uc = {ueditorContent};
                return R.ok().put("result", uc);
            } else {
                logger.info("ueditor is null");
                return R.error();
            }
        } catch (Exception e) {
            e.printStackTrace();
            logger.error(e.getMessage(), e);
            return R.error();
        }
    }
    

}
