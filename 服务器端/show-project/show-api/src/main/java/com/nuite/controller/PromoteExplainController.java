package com.nuite.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.nuite.annotation.Login;
import com.nuite.annotation.LoginUser;
import com.nuite.common.utils.R;
import com.nuite.dao.PromoteExplainDao;
import com.nuite.entity.PromoteExplainEntity;
import com.nuite.entity.UserEntity;
import com.nuite.service.PromoteExplainService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import springfox.documentation.annotations.ApiIgnore;


/**
 * 报表 - 促销说明接口
 * @author yy
 * @date 2019-01-07 17:15:57
 */
@RestController
@RequestMapping("/api/report/promoteExplain")
@Api(tags="报表 - 促销说明接口")
public class PromoteExplainController {
	private Logger logger = LoggerFactory.getLogger(getClass());
	
    @Autowired
    private PromoteExplainService promoteExplainService;
    
    @Autowired
    private PromoteExplainDao promoteExplainDao;
    
    
    
    
	/**
	 * 新增或修改促销说明
	 */
	@Login
	@PostMapping("addOrUpdatePromoteExplain")
	@ApiOperation("新增或修改促销说明")
	public R addOrUpdatePromoteExplain(@ApiIgnore @LoginUser UserEntity loginUser,
			@ApiParam("年") @RequestParam("year") Integer year,
			@ApiParam("周") @RequestParam("week") Integer week,
			@ApiParam("周一至周四促销说明") @RequestParam("explain1") String explain1,
			@ApiParam("周五至周日促销说明") @RequestParam("explain2") String explain2,
			HttpServletRequest request) {
		try {
    		if (loginUser.getRoleSeq() != 4){
				return R.error(HttpStatus.FORBIDDEN.value(), "该功能仅限门店账号使用");
			}
    		
//    		Seq			int	0	0	0	-1	0	0		0	0	0	0			-1			
//    		ShopSeq		int	0	0	0	0	0	0		0	0	0	0		门店序号		0			
//    		Year		int	0	0	0	0	0	0		0	0	0	0		年		0			
//    		Week		int	0	0	0	0	0	0		0	0	0	0		周		0			
//    		Explain1	varchar	500	0	-1	0	0	0		0	0	0	0	周一至周四促销说明	Chinese_PRC_CI_AS	0			
//    		Explain2	varchar	500	0	-1	0	0	0		0	0	0	0	周五至周日促销说明	Chinese_PRC_CI_AS	0			
//    		InputTime	datetime	0	0	-1	0	0	0	(getdate())	0	0	0	0	插入时间		0			
//    		Del			int	0	0	0	0	0	0	((0))	0	0	0	0	删除标识		0			
	
    		
    		PromoteExplainEntity promoteExplainEntity = new PromoteExplainEntity();
    		promoteExplainEntity.setShopSeq(loginUser.getUserAreaSeq());
    		promoteExplainEntity.setYear(year);
    		promoteExplainEntity.setWeek(week);
    		promoteExplainEntity.setExplain1(explain1);
    		promoteExplainEntity.setExplain2(explain2);

    		promoteExplainEntity.setInputTime(new Date());
    		promoteExplainEntity.setDel(0);
    		
    		promoteExplainService.addOrUpdatePromoteExplain(promoteExplainEntity);
    		
			return R.ok("保存成功");
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(e.getMessage(), e);
			return R.error("保存失败");
		}
	}
	
	
	
	
	
	
	/**
     *  获取一条促销说明（用于编辑）
     */
	@Login
	@GetMapping("getPromoteExplain")
	@ApiOperation("获取一条促销说明（用于编辑）")
	public R getPromoteExplain(@ApiIgnore @LoginUser UserEntity loginUser,
			@ApiParam("年") @RequestParam("year") Integer year,
			@ApiParam("周") @RequestParam("week") Integer week,
			HttpServletRequest request) {
		try {
    		if (loginUser.getRoleSeq() != 4){
				return R.error(HttpStatus.FORBIDDEN.value(), "该功能仅限门店账号使用");
			}
    		
    		PromoteExplainEntity promoteExplainEntity = new PromoteExplainEntity();
    		promoteExplainEntity.setShopSeq(loginUser.getUserAreaSeq());
    		promoteExplainEntity.setYear(year);
    		promoteExplainEntity.setWeek(week);
    		promoteExplainEntity = promoteExplainDao.selectOne(promoteExplainEntity);
			
			List<PromoteExplainEntity> list = new ArrayList<PromoteExplainEntity>();
			list.add(promoteExplainEntity);
			
			return R.ok(list);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(e.getMessage(), e);
			return R.error("服务器开小差了");
		}
	}
	
    
    
    

}
