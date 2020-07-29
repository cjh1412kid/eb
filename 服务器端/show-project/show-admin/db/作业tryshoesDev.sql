USE [msdb]
GO

/****** Object:  Job [从tryshoesDev同步基础数据]    Script Date: 2018/12/5 10:42:28 ******/
BEGIN TRANSACTION
DECLARE @ReturnCode INT
SELECT @ReturnCode = 0
/****** Object:  JobCategory [[Uncategorized (Local)]]    Script Date: 2018/12/5 10:42:28 ******/
IF NOT EXISTS (SELECT name FROM msdb.dbo.syscategories WHERE name=N'[Uncategorized (Local)]' AND category_class=1)
BEGIN
EXEC @ReturnCode = msdb.dbo.sp_add_category @class=N'JOB', @type=N'LOCAL', @name=N'[Uncategorized (Local)]'
IF (@@ERROR <> 0 OR @ReturnCode <> 0) GOTO QuitWithRollback

END

DECLARE @jobId BINARY(16)
EXEC @ReturnCode =  msdb.dbo.sp_add_job @job_name=N'从tryshoesDev同步基础数据', 
		@enabled=1, 
		@notify_level_eventlog=0, 
		@notify_level_email=0, 
		@notify_level_netsend=0, 
		@notify_level_page=0, 
		@delete_level=0, 
		@description=N'无描述。', 
		@category_name=N'[Uncategorized (Local)]', 
		@owner_login_name=N'sa', @job_id = @jobId OUTPUT
IF (@@ERROR <> 0 OR @ReturnCode <> 0) GOTO QuitWithRollback
/****** Object:  Step [步骤1：同步品牌表NWBase_Brand]    Script Date: 2018/12/5 10:42:28 ******/
EXEC @ReturnCode = msdb.dbo.sp_add_jobstep @job_id=@jobId, @step_name=N'步骤1：同步品牌表NWBase_Brand', 
		@step_id=1, 
		@cmdexec_success_code=0, 
		@on_success_action=3, 
		@on_success_step_id=0, 
		@on_fail_action=3, 
		@on_fail_step_id=0, 
		@retry_attempts=0, 
		@retry_interval=0, 
		@os_run_priority=0, @subsystem=N'TSQL', 
		@command=N'TRUNCATE TABLE YHSmartDataRetail.dbo.NWBase_Brand
GO

INSERT INTO YHSmartDataRetail.dbo.NWBase_Brand
(
	Seq,
	IdentifyCode,
	InnerKey,
	BrandName,
	CompanySeq,
	Del
)
(SELECT
		SEQ,
		IdentifyCode,
		[Key],
		BrandName,
		CompanySeq,
		DEL
	 FROM [TRYSHOES_OLD].tryshoesDev.dbo.NWBASE_BRAND)
GO
', 
		@database_name=N'master', 
		@flags=0
IF (@@ERROR <> 0 OR @ReturnCode <> 0) GOTO QuitWithRollback
/****** Object:  Step [步骤2：同步公司表NWBase_Company]    Script Date: 2018/12/5 10:42:28 ******/
EXEC @ReturnCode = msdb.dbo.sp_add_jobstep @job_id=@jobId, @step_name=N'步骤2：同步公司表NWBase_Company', 
		@step_id=2, 
		@cmdexec_success_code=0, 
		@on_success_action=3, 
		@on_success_step_id=0, 
		@on_fail_action=3, 
		@on_fail_step_id=0, 
		@retry_attempts=0, 
		@retry_interval=0, 
		@os_run_priority=0, @subsystem=N'TSQL', 
		@command=N'TRUNCATE TABLE YHSmartDataRetail.dbo.NWBase_Company
GO


INSERT INTO YHSmartDataRetail.dbo.NWBase_Company
(
	Seq,
	InnerKey,
	Name,
	Del
)
(SELECT
	SEQ,
	[KEY],
	name,
	DEL
	 FROM [TRYSHOES_OLD].tryshoesDev.dbo.NWBASE_COMPANY)
GO
', 
		@database_name=N'master', 
		@flags=0
IF (@@ERROR <> 0 OR @ReturnCode <> 0) GOTO QuitWithRollback
/****** Object:  Step [步骤3：同步区域表NWbase_Area]    Script Date: 2018/12/5 10:42:28 ******/
EXEC @ReturnCode = msdb.dbo.sp_add_jobstep @job_id=@jobId, @step_name=N'步骤3：同步区域表NWbase_Area', 
		@step_id=3, 
		@cmdexec_success_code=0, 
		@on_success_action=3, 
		@on_success_step_id=0, 
		@on_fail_action=3, 
		@on_fail_step_id=0, 
		@retry_attempts=0, 
		@retry_interval=0, 
		@os_run_priority=0, @subsystem=N'TSQL', 
		@command=N'TRUNCATE TABLE YHSmartDataRetail.dbo.NWBase_Area
GO

INSERT INTO YHSmartDataRetail.dbo.NWBase_Area
(
	Seq,
	ParentSeq,
	BrandSeq,
	AreaName,
	Bound,
	InputTime,
	Del
)
(SELECT
	SEQ,
	PARENTSEQ,
	BrandSeq,
	AREANAME,
	BOUND,
	CREATTIME,
	DEL
	 FROM [TRYSHOES_OLD].tryshoesDev.dbo.NWBase_AreaBase)
GO', 
		@database_name=N'master', 
		@flags=0
IF (@@ERROR <> 0 OR @ReturnCode <> 0) GOTO QuitWithRollback
/****** Object:  Step [步骤4：同步门店表NWBase_Shop]    Script Date: 2018/12/5 10:42:28 ******/
EXEC @ReturnCode = msdb.dbo.sp_add_jobstep @job_id=@jobId, @step_name=N'步骤4：同步门店表NWBase_Shop', 
		@step_id=4, 
		@cmdexec_success_code=0, 
		@on_success_action=3, 
		@on_success_step_id=0, 
		@on_fail_action=3, 
		@on_fail_step_id=0, 
		@retry_attempts=0, 
		@retry_interval=0, 
		@os_run_priority=0, @subsystem=N'TSQL', 
		@command=N'TRUNCATE TABLE YHSmartDataRetail.dbo.NWBase_Shop
GO

INSERT INTO YHSmartDataRetail.dbo.NWBase_Shop
(
	Seq,
	AreaSeq,
	ShopID,
	Name,
	Address,
	Lat,
	Lng,
	InstallDate,
	InputTime,
	Del
)
(SELECT
	A.SEQ,
	A.areabaseseq,
	A.ShopID,
	A.ShopName,
	A.ShopAddress,
	B.Lat,
	B.Lng,
	B.installDate,
	A.InputDate,
	A.Del
	 FROM [TRYSHOES_OLD].tryshoesDev.dbo.NWBASE_SHOPBASEINFO A 
	 LEFT JOIN [TRYSHOES_OLD].tryshoesDev.dbo.NWBase_ShopDetailInfo B ON A.SEQ = B.shopSeq AND B.Del = 0
)
GO', 
		@database_name=N'master', 
		@flags=0
IF (@@ERROR <> 0 OR @ReturnCode <> 0) GOTO QuitWithRollback
/****** Object:  Step [步骤5：同步硬件参数设置表NWBase_HardwareParameterSet]    Script Date: 2018/12/5 10:42:28 ******/
EXEC @ReturnCode = msdb.dbo.sp_add_jobstep @job_id=@jobId, @step_name=N'步骤5：同步硬件参数设置表NWBase_HardwareParameterSet', 
		@step_id=5, 
		@cmdexec_success_code=0, 
		@on_success_action=1, 
		@on_success_step_id=0, 
		@on_fail_action=2, 
		@on_fail_step_id=0, 
		@retry_attempts=0, 
		@retry_interval=0, 
		@os_run_priority=0, @subsystem=N'TSQL', 
		@command=N'TRUNCATE TABLE YHSmartDataRetail.dbo.NWBase_HardwareParameterSet
GO

INSERT INTO YHSmartDataRetail.dbo.NWBase_HardwareParameterSet
(
	Seq,
	ShopSeq,
	HardwareID,
	Name,
	UseAntennaPort,
	ReaderType,
	IP,
	Port,
	OutPutPower,
	RFIDEffectTime,
	Minimumfrequency,
	Maxmumfrequency,
	FrequencySpace,
	BleeperMode,
	ProFileMode,
	ReturnLoss,
	InputTime,
	Del
)
(SELECT
	SEQ,
	FK_ShopBaseInfo_SEQ,
	HardwareID,
	HardwareName,
	UseAntennaPort,
	ReaderType,
	IP,
	Port,
	OutPutPower,
	RFIDEffectTime,
	Minimumfrequency,
	Maxmumfrequency,
	frequencyspace,
	bleepermode,
	ProFileMode,
	ReturnLoss,
	InputDate,
	Del
	 FROM [TRYSHOES_OLD].tryshoesDev.dbo.NWBASE_HARDWAREPARAMETERSET
)
GO', 
		@database_name=N'master', 
		@flags=0
IF (@@ERROR <> 0 OR @ReturnCode <> 0) GOTO QuitWithRollback
EXEC @ReturnCode = msdb.dbo.sp_update_job @job_id = @jobId, @start_step_id = 1
IF (@@ERROR <> 0 OR @ReturnCode <> 0) GOTO QuitWithRollback
EXEC @ReturnCode = msdb.dbo.sp_add_jobschedule @job_id=@jobId, @name=N'计划1：每2小时执行一次', 
		@enabled=0, 
		@freq_type=4, 
		@freq_interval=1, 
		@freq_subday_type=8, 
		@freq_subday_interval=2, 
		@freq_relative_interval=0, 
		@freq_recurrence_factor=0, 
		@active_start_date=20181116, 
		@active_end_date=99991231, 
		@active_start_time=0, 
		@active_end_time=235959, 
		@schedule_uid=N'65fb83db-e326-4d49-bf53-b34c16c0b504'
IF (@@ERROR <> 0 OR @ReturnCode <> 0) GOTO QuitWithRollback
EXEC @ReturnCode = msdb.dbo.sp_add_jobserver @job_id = @jobId, @server_name = N'(local)'
IF (@@ERROR <> 0 OR @ReturnCode <> 0) GOTO QuitWithRollback
COMMIT TRANSACTION
GOTO EndSave
QuitWithRollback:
    IF (@@TRANCOUNT > 0) ROLLBACK TRANSACTION
EndSave:
GO

