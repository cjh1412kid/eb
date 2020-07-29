package com.nuite.modules.sys.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.nuite.common.utils.R;
import com.nuite.modules.sys.dao.SizeDao;
import com.nuite.modules.sys.entity.SizeEntity;
import com.nuite.modules.sys.service.ShoesDataService;

import io.swagger.annotations.ApiParam;


/**
 * 分公司补单建议接口
 * @author yy
 */
@RestController
@RequestMapping("sys/shoesData")
public class ShoesDataController extends AbstractController{
	private Logger logger = LoggerFactory.getLogger(getClass());
	
    @Autowired
    private ShoesDataService shoesDataService;
    
    @Autowired
    private SizeDao sizeDao;
    
    
    
	/**
     * 获取区域某鞋子每个尺码的总进货量、库存、总销量
     */
    @GetMapping("getShoeSizeNumStockSale")
    public R getShoeSizeNumStockSale(@ApiParam("区域类型") @RequestParam("areaType") Integer areaType,
    		@ApiParam("区域序号") @RequestParam("areaSeq") Integer areaSeq,
    		@ApiParam("鞋子序号") @RequestParam("shoeSeq") Integer shoeSeq,
			HttpServletRequest request) {
    	try {
    		List<Map<String, Object>> list = shoesDataService.getShoeSizeNumStockSale(areaType, areaSeq, shoeSeq);
    		
			//按顺序展示的尺码数组
			String[] allSizeArr = {"33","34","35","36","37","38","39","40","35.5","36.5","37.5"};
			Integer[] numArr = {null, null, null, null, null, null, null, null, null, null, null};
			Integer[] stockArr = {null, null, null, null, null, null, null, null, null, null, null};
			Integer[] saleNumArr = {null, null, null, null, null, null, null, null, null, null, null};
			
			for(Map<String, Object> map : list) {
				Integer sizeSeq = (Integer) map.get("sizeSeq");
				SizeEntity sizeEntity = sizeDao.selectById(sizeSeq);
				String size = sizeEntity.getName();
				
				Integer num = (Integer) map.get("num");
				Integer stock = (Integer) map.get("stock");
				Integer saleNum = (Integer) map.get("saleNum");
				
				for(int i = 0; i < allSizeArr.length; i++) {
					if(allSizeArr[i].equals(size)) {
						numArr[i] = num;
						stockArr[i] = stock;
						saleNumArr[i] = saleNum;
						break;
					}
				}
			}
			
			Map<String, Object> resultMap = new HashMap<String, Object>();
			resultMap.put("numArr", numArr);
			resultMap.put("stockArr", stockArr);
			resultMap.put("saleNumArr", saleNumArr);
			return R.ok(resultMap);
			
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(e.getMessage(), e);
			return R.error("服务器异常");
		}
    }

}
