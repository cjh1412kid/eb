package com.nuite.modules.erp.dao;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.Date;
import java.util.List;
import java.util.Map;

@Mapper
public interface ErpCopyDao {
    Date maxTimeOfDb();
    List<Map<String, Object>> selectDbList(@Param("startTime") String startTime);
    Map<String, Object> selectDbById(@Param("id") String dbno);
    void updateDbById(Map<String, Object> data);
    void insertDb(Map<String, Object> data);


    List<Map<String, Object>> selectColorList();
    Map<String, Object> selectColorById(@Param("id") String color_no);
    void updateColorById(Map<String, Object> data);
    void insertColor(Map<String, Object> data);


    Date maxTimeOfCltypeb();
    List<Map<String, Object>> selectCltypebList(@Param("startTime") String startTime);
    Map<String, Object> selectCltypebById(@Param("id") String classno);
    void updateCltypebById(Map<String, Object> data);
    void insertCltypeb(Map<String, Object> data);


    Date maxTimeOfCltypep();
    List<Map<String, Object>> selectCltypepList(@Param("startTime") String startTime);
    Map<String, Object> selectCltypepById(@Param("typeno") String typeno, @Param("colthno") String colthno, @Param("classno") String classno);
    void updateCltypepById(Map<String, Object> data);
    void insertCltypep(Map<String, Object> data);


    Date maxTimeOfColoth();
    List<Map<String, Object>> selectColoth(@Param("startTime") String startTime);
    Map<String, Object> selectColothById(@Param("colthno") String colthno);
    void updateColothById(Map<String, Object> data);
    void insertColoth(Map<String, Object> data);


    Date maxTimeOfTrun();
    List<Map<String, Object>> selectTrun(@Param("startTime") String startTime, @Param("endTime") String endTime);
    void deleteOldTrunData(@Param("list") List<String> orderNums);
    void insertTrunList(@Param("list") List<Map<String, Object>> datas);
    void insertTrunbList(@Param("list") List<String> orderNums);


    Date maxTimeOfRt();
    List<Map<String, Object>> selectRt(@Param("startTime") String startTime, @Param("endTime") String endTime);
    void deleteOldRtData(@Param("list") List<String> orderNums);
    void insertRtList(@Param("list") List<Map<String, Object>> datas);
    void insertH2ortList(@Param("list") List<String> orderNums);


    Date maxTimeOfSa();
    List<Map<String, Object>> selectSa(@Param("startTime") String startTime, @Param("endTime") String endTime);
    void deleteOldSaData(@Param("list") List<String> orderNums);
    void insertSaList(@Param("list") List<Map<String, Object>> datas);
    void insertH2osaList(@Param("list") List<String> orderNums);


    Date maxTimeOfBuy();
    List<Map<String, Object>> selectBuy(@Param("startTime") String startTime, @Param("endTime") String endTime);
    void deleteOldBuyData(@Param("list") List<String> orderNums);
    void insertBuyList(@Param("list") List<Map<String, Object>> datas);
    void insertH2oinList(@Param("list") List<String> orderNums);
    
    
	Date maxTimeOfZgckacc();
	List<Map<String, Object>> selectZgckaccList(@Param("startTime") String startTime);
	Map<String, Object> selectZgckaccById(@Param("ckaccno") String ckaccno);
	void updateZgckaccById(Map<String, Object> data);
	void insertZgckacc(Map<String, Object> data);
	
	
	List<Map<String, Object>> selectZgstainfoList();
	Map<String, Object> selectZgstainfoById(@Param("stanos") String stanos);
	void updateZgstainfoById(Map<String, Object> data);
	void insertZgstainfo(Map<String, Object> data);
	
	
	
}
