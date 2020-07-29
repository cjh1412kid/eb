package com.nuite.modules.sys.service.impl;

import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.nuite.common.utils.PageUtils;
import com.nuite.common.utils.Query;
import com.nuite.modules.sys.dao.AreaDao;
import com.nuite.modules.sys.dao.PatchDao;
import com.nuite.modules.sys.dao.PatchDetailDao;
import com.nuite.modules.sys.entity.AreaEntity;
import com.nuite.modules.sys.entity.PatchDetailEntity;
import com.nuite.modules.sys.entity.PatchEntity;
import com.nuite.modules.sys.service.PatchService;



@Service
public class PatchServiceImpl extends ServiceImpl<PatchDao, PatchEntity> implements PatchService {

    @Autowired
    private AreaDao areaDao;
    
    @Autowired
    private PatchDao patchDao;
    
    @Autowired
    private PatchDetailDao patchDetailDao;
    
    
    
    
    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        Page<PatchEntity> page = this.selectPage(
                new Query<PatchEntity>(params).getPage(),
                new EntityWrapper<PatchEntity>()
        );

        return new PageUtils(page);
    }

    
    
    /**
     * 根据分公司序号获取区域序号
     */
	@Override
	public Integer getAreaSeqByBranchOfficeSeq(Integer branchOfficeSeq) {
		AreaEntity areaEntity = areaDao.selectById(branchOfficeSeq);
		return areaEntity.getParentSeq();
	}



	/**
	 * 提交补单
	 */
	@Override
    @Transactional(propagation = Propagation.REQUIRED)
	public void submit(Integer year, Integer week, Date startDate, Date endDate, Integer areaSeq, Integer branchOfficeSeq,
			Integer userSeq, Integer periodSeq, String sxValue, String shoeAndNum) {
		
		
        // 删除可能已存在的补单 + 补单详情
		Wrapper<PatchEntity> wrapper = new EntityWrapper<PatchEntity>();
		wrapper.setSqlSelect("Seq");
		wrapper.where("Year = {0} AND Week = {1} AND BranchOfficeSeq = {2} AND PeriodSeq = {3} AND SXValue = {4}", year, week, branchOfficeSeq, periodSeq, sxValue);
		List<Object> patchSeqList = patchDao.selectObjs(wrapper);
		
		Wrapper<PatchDetailEntity> detailWrapper = new EntityWrapper<PatchDetailEntity>();
		detailWrapper.in("Patch_Seq", patchSeqList);
		patchDetailDao.delete(detailWrapper);
		
		patchDao.delete(wrapper);
		
		
		
		Date nowDate = new Date();
		
		PatchEntity patchEntity = new PatchEntity();
//		Seq			int	0	0	0	-1	0	0		0	0	0	0	序号(主键)		-1			
//		Year		int	0	0	-1	0	0	0		0	0	0	0	年份		0			
//		Week		int	0	0	-1	0	0	0		0	0	0	0	周		0			
//		StartDate	date	0	0	-1	0	0	0		0	0	0	0	开始日期		0			
//		EndDate		date	0	0	-1	0	0	0		0	0	0	0	结束日期		0			
//		AreaSeq		int	0	0	-1	0	0	0		0	0	0	0	大区seq		0			
//		BranchOfficeSeq	int	0	0	-1	0	0	0		0	0	0	0	分公司序号		0			
//		UserSeq		int	0	0	-1	0	0	0		0	0	0	0	用户Seq		0			
//		PeriodSeq	int	0	0	-1	0	0	0		0	0	0	0	季节		0			
//		SXValue		varchar	50	0	-1	0	0	0		0	0	0	0	品类	Chinese_PRC_CI_AS	0			
//		ShoeSeq		int	0	0	-1	0	0	0		0	0	0	0	鞋子seq		0			
//		ShoeID		varchar	50	0	-1	0	0	0		0	0	0	0	货号	Chinese_PRC_CI_AS	0			
//		PatchNum	int	0	0	-1	0	0	0		0	0	0	0	补单数量		0			
//		State		int	0	0	-1	0	0	0		0	0	0	0	状态：0待处理 1已确认 2已取消		0			
//		InputTime	datetime	0	0	-1	0	0	0	(getdate())	0	0	0	0	入库时间		0			
//		Del			int	0	0	0	0	0	0	((0))	0	0	0	0	删除标识( 0 : 未删除、  1 : 删除 )		0			
		patchEntity.setYear(year);
		patchEntity.setWeek(week);
		patchEntity.setStartDate(startDate);
		patchEntity.setEndDate(endDate);
		patchEntity.setAreaSeq(areaSeq);
		patchEntity.setBranchOfficeSeq(branchOfficeSeq);
		patchEntity.setUserSeq(userSeq);
		patchEntity.setPeriodSeq(periodSeq);
		patchEntity.setSxValue(sxValue);

		patchEntity.setState(0);
		patchEntity.setInputTime(nowDate);
		patchEntity.setDel(0);
		
		
//		[{
//			"shoeSeq": "704",
//			"shoeId": "B9502001A01",
//			"totalNum": 9,
//			"sizeAllotDetail": {
//				"2": 1,
//				"4": 2
//			}
//		}, {
//			"shoeSeq": "834",
//			"shoeId": "B9597811A01",
//			"totalNum": 84,
//			"sizeAllotDetail": {
//				"46": 5,
//				"52": 9
//			}
//		}]
		JSONArray shoeAndNumArray = JSONArray.parseArray(shoeAndNum);
		for(int i = 0; i < shoeAndNumArray.size(); i++) {
        	JSONObject shoeAndNumObj = shoeAndNumArray.getJSONObject(i);
            Integer shoeSeq = shoeAndNumObj.getInteger("shoeSeq");
            String shoeId = shoeAndNumObj.getString("shoeId");
            Integer totalNum = shoeAndNumObj.getInteger("totalNum");
        	if(totalNum == null || totalNum <= 0) {
        		continue;
        	}
            
			patchEntity.setShoeSeq(shoeSeq);
			patchEntity.setShoeID(shoeId);
			patchEntity.setPatchNum(totalNum);
			patchDao.insert(patchEntity);
			
			//添加配码详情
//			Seq				int	0	0	0	-1	0	0		0	0	0	0	序号		-1			
//			Patch_Seq		int	0	0	-1	0	0	0		0	0	0	0	补单记录序号		0			
//			SizeAllotTemplate_Seq	int	0	0	-1	0	0	0		0	0	0	0	配码模板序号		0			
//			BoxCount		int	0	0	-1	0	0	0		0	0	0	0	件数		0			
//			InputTime		datetime	0	0	-1	0	0	0	(getdate())	0	0	0	0	创建时间		0			
//			Del				int	0	0	-1	0	0	0	((0))	0	0	0	0	删除标识		0			
//			PerBoxNum		int	0	0	-1	0	0	0		0	0	0	0	每件数量（冗余字段）		0			
			PatchDetailEntity patchDetailEntity = new PatchDetailEntity();
			patchDetailEntity.setPatchSeq(patchEntity.getSeq());
			patchDetailEntity.setInputTime(nowDate);
			patchDetailEntity.setDel(0);
			
            JSONObject sizeAllotDetail = shoeAndNumObj.getJSONObject("sizeAllotDetail");
            
            for(String key : sizeAllotDetail.keySet()) {
            	Integer sizeAllotTemplateSeq = Integer.parseInt(key);
            	Integer boxCount = sizeAllotDetail.getInteger(key);
            	if(boxCount == null || boxCount <= 0) {
            		continue;
            	}
    			patchDetailEntity.setSizeAllotTemplateSeq(sizeAllotTemplateSeq);
    			patchDetailEntity.setBoxCount(boxCount);
    			patchDetailDao.insert(patchDetailEntity);
            }
            
		}
		
		
	}



	@Override
	public List<Map<String, Object>> getAreas(Integer periodSeq, String sxValue, Integer week) {
		Wrapper<PatchEntity> wrapper=new EntityWrapper<PatchEntity>();
		wrapper.setSqlSelect("distinct BranchOfficeSeq");
		wrapper.where(" Week = {0} AND PeriodSeq={1} AND SXValue={2}",week,periodSeq,sxValue);
		return patchDao.selectMaps(wrapper);
	}



	@Override
	public List<Object> getWeeks(Integer areaSeq) {
		Wrapper<PatchEntity> wrapper=new EntityWrapper<PatchEntity>();
		wrapper.setSqlSelect("distinct Week");
		if(areaSeq!=null) {
		wrapper.where("BranchOfficeSeq = {0}", areaSeq);
		}
		List<Object> weeks=patchDao.selectObjs(wrapper);
		return weeks;
	}



	@Override
	public List<Object> getPeriods(Integer userSeq,Integer week) {
		Wrapper<PatchEntity> wrapper=new EntityWrapper<PatchEntity>();
		wrapper.setSqlSelect("distinct PeriodSeq");
		if(userSeq!=null) {
		wrapper.where("UserSeq = {0} AND Week = {1}", userSeq,week);
		}else {
			wrapper.where(" Week = {0}",week);
		}
		List<Object> weeks=patchDao.selectObjs(wrapper);
		return weeks;
	}



	@Override
	public List<PatchEntity> getPatchs(Integer periodSeq, String sxValue, Integer week, Integer shoeSeq,Integer branchOfficeSeq) {
		Wrapper<PatchEntity> wrapper=new EntityWrapper<PatchEntity>();
		wrapper.where(" Week = {0} AND PeriodSeq={1} AND SXValue={2} AND ShoeSeq={3} AND BranchOfficeSeq={4}",week,periodSeq,sxValue,shoeSeq,branchOfficeSeq);
		return patchDao.selectList(wrapper);
	}



	@Override
	public List<Object> getShoeSeqs(Integer periodSeq, String sxValue, Integer week,Integer areaSeq) {
		Wrapper<PatchEntity> wrapper=new EntityWrapper<PatchEntity>();
		wrapper.setSqlSelect("distinct ShoeSeq");
		if(areaSeq!=null) {
			wrapper.where(" Week = {0} AND PeriodSeq={1} AND SXValue={2} AND BranchOfficeSeq={3}",week,periodSeq,sxValue,areaSeq);
			}else {
				wrapper.where(" Week = {0} AND PeriodSeq={1} AND SXValue={2}",week,periodSeq,sxValue);
			}
		return patchDao.selectObjs(wrapper);
	}



	@Override
	public Integer allPatchNum(Integer shoesSeq,Integer branchOfficeSeq) {
		return patchDao.allPatchNum(shoesSeq,branchOfficeSeq);
	}


	@Override
	public PatchEntity getOnByParam(Integer periodSeq, String sxValue, Integer week, Integer shoesSeq,Integer branchOfficeSeq) {
		PatchEntity patchEntity=new PatchEntity();
		patchEntity.setPeriodSeq(periodSeq);
		patchEntity.setWeek(week);
		patchEntity.setSxValue(sxValue);
		patchEntity.setShoeSeq(shoesSeq);
		patchEntity.setBranchOfficeSeq(branchOfficeSeq);
		patchEntity=patchDao.selectOne(patchEntity);
		return patchEntity;
	}



	/**
	 * 查询分公司已提交的补单
	 */
	@Override
	public List<PatchEntity> getBranchofficeAlreadyPatchList(Integer branchOfficeSeq, Integer periodSeq, String sxValue, Date startDate, Date endDate) {
		
		//获取日期是一年的第几周
		Calendar calendar = Calendar.getInstance();
		calendar.setFirstDayOfWeek(Calendar.MONDAY);
		calendar.setTime(startDate);
		Integer week = calendar.get(Calendar.WEEK_OF_YEAR);
		
		Wrapper<PatchEntity> wrapper = new EntityWrapper<PatchEntity>();
		wrapper.where("Week = {0} AND BranchOfficeSeq = {1} AND PeriodSeq = {2} AND SXValue = {3}", week, branchOfficeSeq, periodSeq, sxValue);
		List<PatchEntity> patchEntityList = patchDao.selectList(wrapper);
		
		//添加补单配码详情
		if(patchEntityList != null) {
			for(PatchEntity patchEntity : patchEntityList) {
				
				Wrapper<PatchDetailEntity> detailWrapper = new EntityWrapper<PatchDetailEntity>();
				detailWrapper.where("Patch_Seq = {0}", patchEntity.getSeq());
				List<PatchDetailEntity> patchDetailEntityList = patchDetailDao.selectList(detailWrapper);
				
				Map<Integer, Integer> patchDetailMap = new HashMap<Integer, Integer>();
				if(patchDetailEntityList != null) {
					for(PatchDetailEntity patchDetailEntity : patchDetailEntityList) {
						patchDetailMap.put(patchDetailEntity.getSizeAllotTemplateSeq(), patchDetailEntity.getBoxCount());
					}
				}
				patchEntity.setPatchDetailMap(patchDetailMap);
				
			}
		}
		
		return patchEntityList;
	}

}
