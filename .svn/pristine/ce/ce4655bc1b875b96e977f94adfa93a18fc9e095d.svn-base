package com.nuite.modules.sys.controller;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.CellReference;
import org.apache.poi.hssf.util.HSSFColor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.nuite.common.utils.PageUtils;
import com.nuite.common.utils.R;
import com.nuite.common.validator.ValidatorUtils;
import com.nuite.modules.sys.dao.AreaDao;
import com.nuite.modules.sys.dao.PeriodDao;
import com.nuite.modules.sys.entity.AreaEntity;
import com.nuite.modules.sys.entity.ColorEntity;
import com.nuite.modules.sys.entity.GoodsInitialDataEntity;
import com.nuite.modules.sys.entity.PatchEntity;
import com.nuite.modules.sys.entity.PeriodEntity;
import com.nuite.modules.sys.entity.ShoeEntity;
import com.nuite.modules.sys.entity.ShoesDataEntity;
import com.nuite.modules.sys.entity.UserEntity;
import com.nuite.modules.sys.service.AreaService;
import com.nuite.modules.sys.service.ColorService;
import com.nuite.modules.sys.service.GoodsInitialDataService;
import com.nuite.modules.sys.service.PatchDetailService;
import com.nuite.modules.sys.service.PatchService;
import com.nuite.modules.sys.service.PeriodService;
import com.nuite.modules.sys.service.ShoeService;
import com.nuite.modules.sys.service.ShoesDataService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiParam;


/**
 * 分公司补单表
 * @author yy
 * @date 2019-10-21 10:20:05
 */
@RestController
@RequestMapping("sys/patch")
@Api(tags="分公司补单表接口")
public class PatchController extends AbstractController{
	private Logger logger = LoggerFactory.getLogger(getClass());
	
    @Autowired
    private PatchService patchService;

    @Autowired
    private AreaDao areaDao;
    
    @Autowired
    private PeriodDao periodDao;
    
    @Autowired
    private PeriodService periodService;
    
    @Autowired
    private AreaService areaService;
    
    @Autowired
    private ShoeService shoeService;
    
    @Autowired
    private GoodsInitialDataService goodsInitialDataService;
    
    @Autowired
    private PatchDetailService patchDetailService;
    
    @Autowired
    private ShoesDataService shoesDataService;
    
    @Autowired
    private ColorService colorService;
    
    /**
     * 列表
     */
    @RequestMapping("/list")
    public R list(@RequestParam Map<String, Object> params){
        PageUtils page = patchService.queryPage(params);
        
        List<PatchEntity> list = (List<PatchEntity>) page.getList();
        for(PatchEntity patchEntity : list) {
        	Integer branchOfficeSeq = patchEntity.getBranchOfficeSeq();
        	AreaEntity branchOffice = areaDao.selectById(branchOfficeSeq);
        	patchEntity.setBranchOfficeName(branchOffice.getAreaName());
        	
        	Integer areaSeq = patchEntity.getAreaSeq();
        	AreaEntity area = areaDao.selectById(areaSeq);
        	patchEntity.setAreaName(area.getAreaName());
        	
        	Integer periodSeq = patchEntity.getPeriodSeq();
        	PeriodEntity periodEntity = periodDao.selectById(periodSeq);
        	patchEntity.setPeriodName(periodEntity.getName());
        	
        	Integer state = patchEntity.getState();
        	if(state == 0) {
        		patchEntity.setStateName("待处理");
        	}
        }
        
        return R.ok().put("page", page);
    }
    
    
    @RequestMapping("/weeks")
    public R weeks(){
    	UserEntity userEntity=getUser();
        //判断当前用户是否为管理员
    	List<Object> weeks=new ArrayList<Object>();
     	if(userEntity.getRoleSeq()==3) {
     		//区域用户
     		Integer areaSeq=userEntity.getUserAreaSeq();
     		weeks=patchService.getWeeks(areaSeq);
     	}else {
     		//全国用户
     		weeks=patchService.getWeeks(null);
     	}
         return R.ok(weeks);
     }
    
    @RequestMapping("/period")
    public R periods(@ApiParam("周次") @RequestParam("week") Integer week){
    	UserEntity userEntity=getUser();
        //判断当前用户是否为管理员
    	List<Object> periods=new ArrayList<Object>();
     	if(userEntity.getRoleSeq()==3) {
     		//区域用户
     		periods=patchService.getPeriods(userEntity.getSeq(),week);
     	}else {
     		//全国用户
     		periods=patchService.getPeriods(null,week);
     	}
     	
     	List<Map<String, Object>> maps=new ArrayList<Map<String,Object>>();
     	for (Object seq : periods) {
     		Map<String, Object> map=new HashMap<String, Object>();
     		PeriodEntity periodEntity=periodService.selectById((Integer)seq);
     		map.put("seq", seq);
     		map.put("name", periodEntity.getName());
     		maps.add(map);
		}
         return R.ok(maps);
     }
    
    @RequestMapping("/patchList")
    public R patchList(@ApiParam("波次") @RequestParam("periodSeq") Integer periodSeq,
			@ApiParam("品类（中文）") @RequestParam("sxValue") String sxValue,
			@ApiParam("周次") @RequestParam("week") Integer week
    		){
    		//获取区域列表
    		UserEntity userEntity=getUser();
    		Integer isAdmin=0;
    		if(userEntity.getRoleSeq()==3) {
    			isAdmin=1;
    		}else {
    			isAdmin=0;
    		}
    		List<Map<String, Object>> areas=new ArrayList<Map<String,Object>>();
    		if(userEntity.getRoleSeq()==3) {
    			Integer areaSeq=userEntity.getUserAreaSeq();
    			AreaEntity areaEntity=areaService.selectById(areaSeq);
    			Map<String, Object> map=new HashMap<String, Object>();
    			map.put("BranchOfficeSeq", areaSeq);
    			map.put("areaName", areaEntity.getAreaName());
    			areas.add(map);
    		}else {
    			areas=patchService.getAreas(periodSeq, sxValue, week);
    			for (Map<String, Object> map : areas) {
					Integer areaSeq=(Integer) map.get("BranchOfficeSeq");
					AreaEntity areaEntity=areaService.selectById(areaSeq);
					map.put("BranchOfficeSeq", areaSeq);
	    			map.put("areaName", areaEntity.getAreaName());
				}
    		}
    		
    		List<Object> ShoeSeqs=new ArrayList<Object>();
    		//根据条件查询货号
    		if(userEntity.getRoleSeq()==3) {
    			ShoeSeqs=patchService.getShoeSeqs(periodSeq, sxValue, week, (Integer)areas.get(0).get("BranchOfficeSeq"));
    		}else {
    			ShoeSeqs=patchService.getShoeSeqs(periodSeq, sxValue, week,null);
    		}
    		List<Map<String, Object>> goods=new ArrayList<Map<String,Object>>();
    		if(userEntity.getRoleSeq()==3) {
    			for (Object shoeSeq : ShoeSeqs) {
        			Map<String, Object> map=new HashMap<String, Object>();
        			ShoeEntity shoeEntity=shoeService.selectById((Integer)shoeSeq);
        			map.put("shoeSeq", shoeSeq);
        			map.put("shoeID", shoeEntity.getShoeID());
        			List<PatchEntity> PatchEntitys=new ArrayList<PatchEntity>();
        			List<PatchEntity> patchEntities=patchService.getPatchs(periodSeq, sxValue, week, (Integer)shoeSeq, (Integer)areas.get(0).get("BranchOfficeSeq"));
        			if(patchEntities!=null&&patchEntities.size()>0) {
    					PatchEntitys.add(patchEntities.get(0));
    					if( patchEntities.get(0).getState()==0) {
    						map.put("state", "待处理");
    					}else if( patchEntities.get(0).getState()==1) {
    						map.put("state", "已确认");
    					}else {
    						map.put("state", "已取消");
    					}
    				}else {
    					PatchEntitys.add(new PatchEntity());
    					map.put("state",  "待处理");
    				}
        			map.put("patchNum", PatchEntitys);
        			goods.add(map);
    			}
    		}else {
    			for (Object shoeSeq : ShoeSeqs) {
    			Map<String, Object> map=new HashMap<String, Object>();
    			ShoeEntity shoeEntity=shoeService.selectById((Integer)shoeSeq);
    			map.put("shoeSeq", shoeSeq);
    			map.put("shoeID", shoeEntity.getShoeID());
    			List<PatchEntity> PatchEntitys=new ArrayList<PatchEntity>();
    			for (Map<String, Object> area : areas) {
    				List<PatchEntity> patchEntities=patchService.getPatchs(periodSeq, sxValue, week, (Integer)shoeSeq, (Integer)area.get("BranchOfficeSeq"));
    				if(patchEntities!=null&&patchEntities.size()>0) {
    					PatchEntitys.add(patchEntities.get(0));
    				}else {
    					PatchEntitys.add(new PatchEntity());
    				}
    			}
    			Integer count=0;
    			for (PatchEntity PatchEntity : PatchEntitys) {
					Integer patchNum=PatchEntity.getPatchNum();
					count+=patchNum;
				}
    			map.put("total", count);
    			//根据货号查询预留数据
    			GoodsInitialDataEntity goodsInitialDataEntity=goodsInitialDataService.getShoeInitialData(1, 0, (int)shoeSeq);
    			if(goodsInitialDataEntity!=null&&goodsInitialDataEntity.getReserveNum()!=null) {
    				map.put("reserved", goodsInitialDataEntity.getReserveNum());
    			}else {
    				map.put("reserved",0);
    			}
    			map.put("patchNum", PatchEntitys);
    			goods.add(map);
			}
    		}
        return R.ok().put("area", areas).put("goods", goods).put("admin", isAdmin);
    }
    
    /**
     * 
     * @param periodSeq
     * @param sxValue
     * @param week
     * @return
     */
    @RequestMapping("/exportRecord")
    public void exportRecord(@ApiParam("波次") @RequestParam("periodSeq") Integer periodSeq,
			@ApiParam("品类（中文）") @RequestParam("sxValue") String sxValue,
			@ApiParam("周次") @RequestParam("week") Integer week, HttpServletResponse response
    		){
    	
		ServletOutputStream out = null;
    	//导出excel
		try {
		// 2.创建Excel
					response.setCharacterEncoding("UTF-8");
					response.setHeader("content-Type", "application/vnd.ms-excel");
					response.setHeader("Content-Disposition",

							"attachment;filename=" + URLEncoder.encode("补单上报格式和汇总格式.xlsx", "UTF-8"));



					// 创建excel2007工作簿
					HSSFWorkbook	wb = new HSSFWorkbook();
					//列标题单元格样式
					HSSFCellStyle headCellStyle = wb.createCellStyle();
					//设置居中:
					headCellStyle.setAlignment(HSSFCellStyle.ALIGN_CENTER); // 居中
					headCellStyle.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);//垂直居中
					//设置字体:
					HSSFFont font = wb.createFont();
					font.setFontName("仿宋_GB2312");
					font.setFontHeightInPoints((short) 10);
					headCellStyle.setFont(font);//选择需要用到的字体格式

					//内容单元格样式
					HSSFCellStyle contentCellStyle = wb.createCellStyle();
					contentCellStyle.setFillBackgroundColor(HSSFColor.YELLOW.index);
					//设置居中:
					contentCellStyle.setAlignment(HSSFCellStyle.ALIGN_CENTER); // 居中
					contentCellStyle.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);//垂直居中
					
					HSSFCellStyle contentCellStyle1 = wb.createCellStyle();
					contentCellStyle1.setFillBackgroundColor(HSSFColor.YELLOW.index);
					
					//汇总数据
					HSSFSheet sheet = wb.createSheet("格式-模板");
					// 默认单元格宽度和高度
					sheet.setDefaultColumnWidth(8);
					sheet.setDefaultRowHeightInPoints(16);

					// 不同列的宽度
					sheet.setColumnWidth(0, 18 * 256);
					//根据波次，品类，周次获取所有的SizeAllotTemplate_Seq
					List<Map<String, Object>> sizeAllotTemplates=patchDetailService.getSizeAllotTemplates(periodSeq, sxValue, week);
					HSSFRow row = sheet.createRow(0);
					row.setHeightInPoints(24);

					List<String> title = new ArrayList<>(30);
					title.add("类型");
					title.add("大区");
					title.add("分公司");
					title.add("类别");
					title.add("货号");
					title.add("颜色");
					title.add("配码");
					title.add("件数");
					title.add("数量");
					title.add("下单");
					title.add("过单备注");
					title.add("补单说明");
					title.add("调单方式");
					title.add("补单方式备注");
			
					for (int i = 0; i < title.size(); i++) {
						HSSFCell cell = row.createCell(i);
						cell.setCellStyle(headCellStyle);
						cell.setCellValue(title.get(i));
					}
					for (int i = 0; i < sizeAllotTemplates.size(); i++) {
						 row = sheet.createRow(i+1);
						Map<String, Object> sizeAllotTemplate=sizeAllotTemplates.get(i);
						List<String> body = new ArrayList<>(30);
						body.add("零售");
						//获取大区名称
						Integer areaSeq=(Integer) sizeAllotTemplate.get("areaSeq");
						AreaEntity areaEntity=areaService.selectById(areaSeq);
						body.add(areaEntity.getAreaName().substring(0,areaEntity.getAreaName().length()-2));
						
						//获取分公司名称
						Integer branchOfficeSeq=(Integer) sizeAllotTemplate.get("branchOfficeSeq");
						AreaEntity  branchOfficeEntity=areaService.selectById(branchOfficeSeq);
						body.add(branchOfficeEntity.getAreaName().substring(0, branchOfficeEntity.getAreaName().length()-3));
						
						//类别名称
						String sXValue=sizeAllotTemplate.get("sXValue").toString();
						body.add(sXValue);
						
						//获取货号名称，颜色
						Integer shoeSeq=(Integer) sizeAllotTemplate.get("shoeSeq");
						ShoeEntity shoeEntity=shoeService.selectById(shoeSeq);
						body.add(shoeEntity.getShoeID());
						
						ShoesDataEntity shoesDataEntity=shoesDataService.getOneByShoeSeq(shoeSeq);
						ColorEntity colorEntity=colorService.selectById(shoesDataEntity.getColorSeq());
						body.add(colorEntity.getName());
						
						//获取配码名称
						String name= sizeAllotTemplate.get("name").toString();
						body.add(name);
						//获取件数
						Integer boxCount=(Integer) sizeAllotTemplate.get("boxCount");
						body.add(boxCount.toString());
						//获取数量
						Integer patchNum=(Integer) sizeAllotTemplate.get("patchNum");
						body.add(patchNum.toString());
						
						for (int j = 0; j < body.size(); j++) {
							HSSFCell cell = row.createCell(j);
							cell.setCellStyle(headCellStyle);
							cell.setCellValue(body.get(j));
						}
					}
					
					
					//新的sheet
					HSSFSheet sheet1 = wb.createSheet("汇总-模板");
					// 默认单元格宽度和高度
					sheet1.setDefaultColumnWidth(8);
					sheet1.setDefaultRowHeightInPoints(16);

					// 不同列的宽度
					sheet1.setColumnWidth(0, 18 * 256);
					//根据波次，品类，周次获取所有的SizeAllotTemplate_Seq
					HSSFRow row1 = sheet1.createRow(0);
					row1.setHeightInPoints(24);
					
					//获取本周所有的大区
					List<Map<String, Object>> areas=patchDetailService.getAreasByWeek(periodSeq, week);
					//根据大区获取本周所有的办事处
					HSSFCell cell = row1.createCell(8);
					cell.setCellValue("总计");
					List<Map<String, Object>> allBranchOffices=new ArrayList<Map<String,Object>>();
					int index=9;
					for (int i = 0; i < areas.size(); i++) {
						Map<String, Object> areaMap=areas.get(i);
						Integer areaSeq=(Integer) areaMap.get("areaSeq");
						List<Map<String, Object>> branchOffices=patchDetailService.getAreasByWeekAndArea(periodSeq, week, areaSeq);
						cell = row1.createCell(index);
						cell.setCellValue(areaMap.get("areaName").toString().substring(0,areaMap.get("areaName").toString().length()-2));
						index=index+branchOffices.size()-1;
						allBranchOffices.addAll(branchOffices);
					}
					row1 = sheet1.createRow(1);
					cell = row1.createCell(0);
					cell.setCellValue("类别2");
					cell = row1.createCell(1);
					cell.setCellValue("货号");
					cell = row1.createCell(2);
					cell.setCellValue("供应商");
					cell = row1.createCell(3);
					cell.setCellValue("靴高");
					cell = row1.createCell(4);
					cell.setCellValue("备注");
					cell = row1.createCell(5);
					cell.setCellValue("剩余预留");
					cell = row1.createCell(6);
					cell.setCellValue("下单");
					cell = row1.createCell(7);
					cell.setCellValue("预留");
					for (int i = 0; i < allBranchOffices.size(); i++) {
						cell = row1.createCell(i+9);
						Map<String, Object> branchOfficeMap=allBranchOffices.get(i);
						cell.setCellValue(branchOfficeMap.get("areaName").toString().substring(0,branchOfficeMap.get("areaName").toString().length()-3));
					}	
					
					//获取本周所有品类
					List<Map<String, Object>> sXValues=patchDetailService.getsXvalueByWeek(periodSeq, week);
					//查询各分类下的货号
					index=3;
					List<Integer> indexList=new ArrayList<>();
					for (int i = 0; i < sXValues.size(); i++) {
						Map<String, Object> sXValueMap=sXValues.get(i);
						List<Map<String, Object>> shoes=patchDetailService.getAllShoeSeq(periodSeq, sXValueMap.get("sXValue").toString(), week);
						row1 = sheet1.createRow(index);
						row1.setRowStyle(contentCellStyle);
						cell = row1.createCell(0);
						cell.setCellValue(sXValueMap.get("sXValue")+"汇总");
						cell = row1.createCell(8);
						String colString = CellReference.convertNumToColString(8);  //长度转成ABC列
						cell.setCellFormula("SUM(" + colString +(index+2) +":" + colString +  (shoes.size()+index+1) + ")");
						indexList.add(index+1);
						for (int j = 0; j < allBranchOffices.size(); j++) {
							cell = row1.createCell(9+j);
							 colString = CellReference.convertNumToColString(9+j);  //长度转成ABC列
							cell.setCellFormula("SUM(" + colString +(index+2) +":" + colString + (shoes.size()+index+1) + ")");
						}
						for (int j = 0; j < shoes.size(); j++) {
							Map<String, Object> shoeMap=shoes.get(j);
							row1 = sheet1.createRow(index+j+1);
							cell = row1.createCell(1);
							cell.setCellValue(shoeMap.get("shoeID").toString());
							cell = row1.createCell(8);
							String colString1 = CellReference.convertNumToColString(9);  //长度转成ABC列
							String colString2 = CellReference.convertNumToColString(8+allBranchOffices.size());  //长度转成ABC列
							cell.setCellFormula("SUM(" + colString1 +(index+j+2) +":" + colString2 +(index+j+2) + ")");
							for (int k= 0; k < allBranchOffices.size(); k++) {
								//根据区域seq和shoesSeq,week,periodSeq查询数量
								PatchEntity patchEntity=patchService.getOnByParam(periodSeq,sXValueMap.get("sXValue").toString(), week,(Integer)shoeMap.get("shoeSeq"),(Integer)allBranchOffices.get(k).get("branchOfficeSeq"));
								if(patchEntity!=null) {
									cell = row1.createCell(9+k);
									cell.setCellValue(patchEntity.getPatchNum());
								}
							}
						}
						index=index+shoes.size()+1;
					}
					row1 = sheet1.createRow(2);
					row1.setRowStyle(contentCellStyle);
					cell = row1.createCell(0);
					cell.setCellValue("合计");
					cell = row1.createCell(8);
					String colString = CellReference.convertNumToColString(8);  //长度转成ABC列
					String sumString="SUM(" ;
					for (int i = 0; i < indexList.size(); i++) {
						sumString=sumString+colString+indexList.get(i)+",";
					}
					sumString=sumString+")";
					cell.setCellFormula(sumString);
					for (int i = 0; i <  allBranchOffices.size(); i++) {
						cell = row1.createCell(9+i);
						String colString1=CellReference.convertNumToColString(9+i);
						String sumString1="SUM(";
						for (int j = 0; j < indexList.size(); j++) {
							sumString1=sumString1+colString1+indexList.get(j)+",";
						}
						sumString1=sumString1+")";
						cell.setCellFormula(sumString1);
					}
					out = response.getOutputStream();
					wb.write(out);
					out.flush();
					
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
			logger.error("补单汇总: 文件名编码异常");
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("补单汇总: " + e.getMessage(), e);
		}
		
		
    	
    	
    }

    /**
     * 信息
     */
    @RequestMapping("/info/{seq}")
    public R info(@PathVariable("seq") Integer seq){
        PatchEntity patch = patchService.selectById(seq);

        return R.ok().put("patch", patch);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    public R save(@RequestBody PatchEntity patch){
        patchService.insert(patch);

        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    public R update(@RequestBody PatchEntity patch){
        ValidatorUtils.validateEntity(patch);
        patchService.updateAllColumnById(patch);//全部更新
        
        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    public R delete(@RequestBody Integer[] seqs){
        patchService.deleteBatchIds(Arrays.asList(seqs));

        return R.ok();
    }
    @PostMapping("updateAll")
    public R updateAll(@RequestBody PatchEntity[] PatchEntitys ) {
    	for (PatchEntity patchEntity : PatchEntitys) {
    		patchEntity.setState(1);
    		patchService.insertOrUpdate(patchEntity);
		}
    	return R.ok();
    }
    
    
	/**
     * 确认提交补单
     */
    @PostMapping("submit")
    public R submit(@ApiParam("年份") @RequestParam("selectPeriodName") String selectPeriodName,
    		@ApiParam("波次") @RequestParam("periodSeq") Integer periodSeq,
			@ApiParam("品类（中文）") @RequestParam("sxValue") String sxValue,
			@ApiParam("开始时间") @RequestParam("startDate") Date startDate,
			@ApiParam("结束时间") @RequestParam("endDate") Date endDate,
			@ApiParam("鞋子seq、货号、补单量、配码详情") @RequestParam("shoeAndNum") String shoeAndNum,
			HttpServletRequest request) {
    	try {
    		UserEntity loginUser = getUser();
	    	if(loginUser.getRoleSeq() != 3) {
	    		return R.error("只有分公司账号可以提交补单");
	    	}
	    	
	    	Integer branchOfficeSeq = loginUser.getUserAreaSeq();
	    	Integer areaSeq = patchService.getAreaSeqByBranchOfficeSeq(branchOfficeSeq);
    		
    		Integer year = Integer.parseInt(selectPeriodName.substring(0, 4));
    		
    		//获取日期是一年的第几周
    		Calendar calendar = Calendar.getInstance();
    		calendar.setFirstDayOfWeek(Calendar.MONDAY);
    		calendar.setTime(startDate);
    		Integer week = calendar.get(Calendar.WEEK_OF_YEAR);
    		
    		patchService.submit(year, week, startDate, endDate, areaSeq, branchOfficeSeq, loginUser.getSeq(), periodSeq, sxValue, shoeAndNum);
    		
            return R.ok("提交成功");
    	} catch (Exception e) {
			e.printStackTrace();
			logger.error(e.getMessage(), e);
			return R.error("服务器异常");
		}
    }
    
    

}
