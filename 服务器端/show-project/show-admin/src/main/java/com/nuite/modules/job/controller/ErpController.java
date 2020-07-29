package com.nuite.modules.job.controller;

import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.nuite.common.annotation.SysLog;
import com.nuite.common.utils.AESUtils;
import com.nuite.common.utils.R;
import com.nuite.modules.job.task.EBlanErpTask;
import com.nuite.modules.sys.dao.ShoesDataDailyDetailDao;
import com.nuite.modules.sys.dao.ShoesDataDao;
import com.nuite.modules.sys.entity.ShoesDataDailyDetailEntity;
import com.nuite.modules.sys.entity.ShoesDataEntity;
import com.nuite.modules.sys.service.ShoesDataService;

/**
 * erp定时推送数据接口
 */
@RestController
@RequestMapping("/erp")
public class ErpController {
	private Logger logger = LoggerFactory.getLogger(getClass());
	
	
    @Autowired
    private EBlanErpTask eBlanErpTask;
    
    @Autowired
    private ShoesDataDailyDetailDao shoesDataDailyDetailDao;
    
    @Autowired
    private ShoesDataDao shoesDataDao;
    
    @Autowired
    private ShoesDataService shoesDataService;
    
    
    
    
    @SysLog
    @RequestMapping("sales")
    public R sales(String data) {
        R result = new R();
        if (StringUtils.isBlank(data)) {
            result.put("code", R.DATA_BLANK);
            result.put("msg", "data为空");
        } else {
            try {
                String decryptStr = AESUtils.decrypt(data);

                System.out.println(decryptStr);
                result.put("msg", "请求成功");
            } catch (Exception e) {
                result.put("code", R.SYSTEM_ERROR);
                result.put("msg", "数据错误");
            }
        }
        return result;
    }

    @SysLog
    @RequestMapping("goods")
    public R goods(String data) {
        R result = new R();
        if (StringUtils.isBlank(data)) {
            result.put("code", R.DATA_BLANK);
            result.put("msg", "data为空");
        } else {
            try {
                String decryptStr = AESUtils.decrypt(data);
                System.out.println(decryptStr);
                result.put("msg", "请求成功");
            } catch (Exception e) {
                result.put("code", R.SYSTEM_ERROR);
                result.put("msg", "数据错误");
            }
        }
        return result;
    }

    
    @RequestMapping("copyDataFromErp")
    public R copyDataFromErp(String password) {
    	if(password.equals("Nxd123456")) {
	        new Thread(() -> eBlanErpTask.copyErpData()).start();
	        return R.ok("开始复制数据");
    	} else {
    		return R.error();
    	}
    }
    
    
    @RequestMapping("syncErp")
    public R syncErp(String password) {
    	if(password.equals("Nxd123456")) {
	        new Thread(() -> eBlanErpTask.erpData()).start();
	        return R.ok("开始同步");
    	} else {
    		return R.error();
    	}
    }
    
    
    
    /**
     * 修复由于出现两种颜色导致的库存错误问题，从进出库表中计算出每条库存记录的总进货量+总销量
     * @param password
     * @return
     */
    @RequestMapping("repairShoesData")
    public R syncShoesData(String password) {
    	if(password.equals("Nxd123456")) {
    		
    		try {
				logger.info("############开始修复库存表！！！！！！");
				
				List<ShoesDataEntity> shoesDataList = getTopStockNullShoesDataList(1000);
				while(shoesDataList != null && shoesDataList.size() > 0) {
					
					logger.info("##########查询到null库存数据大小：" + shoesDataList.size());
					
					for(ShoesDataEntity shoesDataEntity : shoesDataList) {
						Integer shopSeq = shoesDataEntity.getShopSeq();
						Integer shoeSeq = shoesDataEntity.getShoeSeq();
						Integer colorSeq = shoesDataEntity.getColorSeq();
						Integer sizeSeq = shoesDataEntity.getSizeSeq();
						
						//查询type = 0 仓出店数量
						Integer type0num =  getTypeNum(shopSeq, shoeSeq, colorSeq, sizeSeq, 0);
						//查询type = 1 店退仓数量
						Integer type1num =  getTypeNum(shopSeq, shoeSeq, colorSeq, sizeSeq, 1);
						//查询type = 2 仓转店数量
						Integer type2num =  getTypeNum(shopSeq, shoeSeq, colorSeq, sizeSeq, 2);
						//查询type = 3 销售数量
						Integer type3num =  getTypeNum(shopSeq, shoeSeq, colorSeq, sizeSeq, 3);
						
						
						//总进货量计算
						Integer num = type0num*1 + type1num*-1 + type2num*1;
						
						//库存计算
						Integer stock = type0num*1 + type1num*-1 + type2num*1 + type3num*-1;
						
						
						//更新库存表
						shoesDataEntity.setNum(num);
						shoesDataEntity.setStock(stock);
					}
					shoesDataService.updateBatchById(shoesDataList, 100);
				
					shoesDataList = getTopStockNullShoesDataList(1000);
				}
				
				logger.info("###########修复库存表完毕！！！！！！");
				return R.ok("修复库存表完毕");
				
			} catch (Exception e) {
				e.printStackTrace();
				logger.error("###########修复库存表出错！！！！！！");
				logger.error(e.getMessage(), e);
				return R.error();
			}
    	} else {
    		return R.error();
    	}
    }
    
    
    
    private List<ShoesDataEntity> getTopStockNullShoesDataList(int num){
		Wrapper<ShoesDataEntity> wrapper = new EntityWrapper<ShoesDataEntity>();
		wrapper.setSqlSelect("TOP " + num + " * ").where("Stock is null AND Num is null");
		List<ShoesDataEntity> shoesDataList = shoesDataDao.selectList(wrapper);
		return shoesDataList;
    }
    
    private Integer getTypeNum(Integer shopSeq,  Integer shoeSeq, Integer colorSeq, Integer sizeSeq, int type){
		Wrapper<ShoesDataDailyDetailEntity> wrapper = new EntityWrapper<ShoesDataDailyDetailEntity>();
		wrapper.setSqlSelect("SUM (Count) ").where("Shop_Seq = {0}  AND Shoes_Seq = {1} AND Color = {2} AND Size = {3} AND Type = {4}", 
				shopSeq, shoeSeq, colorSeq, sizeSeq, type);

		List<Object> list =  shoesDataDailyDetailDao.selectObjs(wrapper);
		if(list != null && list.size() > 0 && list.get(0) != null)  {
			return (Integer)list.get(0);
		} else {
			return 0;
		}
    }
    
}
