USE [msdb]
GO

/****** Object:  Job [从tryshoesAnalysis同步实时数据]    Script Date: 2018/12/5 10:42:10 ******/
BEGIN TRANSACTION
DECLARE @ReturnCode INT
SELECT @ReturnCode = 0
/****** Object:  JobCategory [[Uncategorized (Local)]]    Script Date: 2018/12/5 10:42:10 ******/
IF NOT EXISTS (SELECT name FROM msdb.dbo.syscategories WHERE name=N'[Uncategorized (Local)]' AND category_class=1)
BEGIN
EXEC @ReturnCode = msdb.dbo.sp_add_category @class=N'JOB', @type=N'LOCAL', @name=N'[Uncategorized (Local)]'
IF (@@ERROR <> 0 OR @ReturnCode <> 0) GOTO QuitWithRollback

END

DECLARE @jobId BINARY(16)
EXEC @ReturnCode =  msdb.dbo.sp_add_job @job_name=N'从tryshoesAnalysis同步实时数据', 
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
/****** Object:  Step [步骤1：同步试穿数据NWGoods_TryShoesDetail]    Script Date: 2018/12/5 10:42:11 ******/
EXEC @ReturnCode = msdb.dbo.sp_add_jobstep @job_id=@jobId, @step_name=N'步骤1：同步试穿数据NWGoods_TryShoesDetail', 
		@step_id=1, 
		@cmdexec_success_code=0, 
		@on_success_action=3, 
		@on_success_step_id=0, 
		@on_fail_action=3, 
		@on_fail_step_id=0, 
		@retry_attempts=0, 
		@retry_interval=0, 
		@os_run_priority=0, @subsystem=N'TSQL', 
		@command=N'-- 查询新表数据最后更新时间

declare @lastUpdateTime datetime

select @lastUpdateTime = MAX(InputTime) from YHSmartDataRetail.[dbo].NWGoods_TryShoesDetail

-- 初次同步，表为空，插入所有数据
if @lastUpdateTime IS NULL
begin
	exec YHSmartDataRetail.[dbo].[insertTryShoesDetail]
end
-- 不为空，根据最后更新时间，更新、插入数据
else
begin
	exec YHSmartDataRetail.[dbo].[updateTryShoesDetail] @lastUpdateTime
end', 
		@database_name=N'master', 
		@flags=0
IF (@@ERROR <> 0 OR @ReturnCode <> 0) GOTO QuitWithRollback
/****** Object:  Step [步骤2：同步销售数据NWGoods_SaleShoesDetail]    Script Date: 2018/12/5 10:42:11 ******/
EXEC @ReturnCode = msdb.dbo.sp_add_jobstep @job_id=@jobId, @step_name=N'步骤2：同步销售数据NWGoods_SaleShoesDetail', 
		@step_id=2, 
		@cmdexec_success_code=0, 
		@on_success_action=3, 
		@on_success_step_id=0, 
		@on_fail_action=3, 
		@on_fail_step_id=0, 
		@retry_attempts=0, 
		@retry_interval=0, 
		@os_run_priority=0, @subsystem=N'TSQL', 
		@command=N'-- 查询新表数据最后更新时间

declare @lastUpdateTime datetime

select @lastUpdateTime = MAX(InputTime) from YHSmartDataRetail.[dbo].NWGoods_SaleShoesDetail

-- 初次同步，表为空，插入所有数据
if @lastUpdateTime IS NULL
begin
	exec YHSmartDataRetail.[dbo].[insertSaleShoesDetail]
end
-- 不为空，不同步。', 
		@database_name=N'master', 
		@flags=0
IF (@@ERROR <> 0 OR @ReturnCode <> 0) GOTO QuitWithRollback
/****** Object:  Step [步骤3：同步默认播放列表NWBase_DefaultPlayList]    Script Date: 2018/12/5 10:42:11 ******/
EXEC @ReturnCode = msdb.dbo.sp_add_jobstep @job_id=@jobId, @step_name=N'步骤3：同步默认播放列表NWBase_DefaultPlayList', 
		@step_id=3, 
		@cmdexec_success_code=0, 
		@on_success_action=3, 
		@on_success_step_id=0, 
		@on_fail_action=3, 
		@on_fail_step_id=0, 
		@retry_attempts=0, 
		@retry_interval=0, 
		@os_run_priority=0, @subsystem=N'TSQL', 
		@command=N'TRUNCATE TABLE YHSmartDataRetail.dbo.NWBase_DefaultPlayList
GO

INSERT INTO YHSmartDataRetail.dbo.NWBase_DefaultPlayList
(
	Seq,
	BrandSeq,
	Name,
	FileName,
	IsSelect,
	MD5Code,
	RelativeURL,
	Flag,
	InputTime,
	Del
)
(SELECT
	SEQ,
	BrandSEQ,
	Name,
	FileName,
	[Select],
	MD5Code,
	RelativeURL,
	Flag,
	InputTime,
	DEL
	 FROM [TRYSHOES_OLD].tryshoesDev.dbo.NWProduct_DefaultPlayList
)
GO', 
		@database_name=N'master', 
		@flags=0
IF (@@ERROR <> 0 OR @ReturnCode <> 0) GOTO QuitWithRollback
/****** Object:  Step [步骤4：同步系统版本更新表NWOther_UpSystem]    Script Date: 2018/12/5 10:42:11 ******/
EXEC @ReturnCode = msdb.dbo.sp_add_jobstep @job_id=@jobId, @step_name=N'步骤4：同步系统版本更新表NWOther_UpSystem', 
		@step_id=4, 
		@cmdexec_success_code=0, 
		@on_success_action=3, 
		@on_success_step_id=0, 
		@on_fail_action=3, 
		@on_fail_step_id=0, 
		@retry_attempts=0, 
		@retry_interval=0, 
		@os_run_priority=0, @subsystem=N'TSQL', 
		@command=N'TRUNCATE TABLE YHSmartDataRetail.dbo.NWOther_UpSystem
GO

INSERT INTO YHSmartDataRetail.dbo.NWOther_UpSystem
(
	Seq,
	US_FileName,
	US_SystemFile,
	US_RegFile,
	US_Version,
	CompanySeq,
	US_FilePath
)
(SELECT
	Seq,
	US_FILENAME,
	US_SYSTEMFILE,
	US_REGFILE,
	US_VERSION,
	CompanySeq,
	US_FilePath
	 FROM [TRYSHOES_OLD].tryshoesDev.dbo.NWOTHER_UPSYSTEM
)
GO', 
		@database_name=N'master', 
		@flags=0
IF (@@ERROR <> 0 OR @ReturnCode <> 0) GOTO QuitWithRollback
/****** Object:  Step [步骤5：同步鞋子素材NWGoods_Material]    Script Date: 2018/12/5 10:42:11 ******/
EXEC @ReturnCode = msdb.dbo.sp_add_jobstep @job_id=@jobId, @step_name=N'步骤5：同步鞋子素材NWGoods_Material', 
		@step_id=5, 
		@cmdexec_success_code=0, 
		@on_success_action=3, 
		@on_success_step_id=0, 
		@on_fail_action=3, 
		@on_fail_step_id=0, 
		@retry_attempts=0, 
		@retry_interval=0, 
		@os_run_priority=0, @subsystem=N'TSQL', 
		@command=N'-- 查询新表数据最后更新时间

declare @lastUpdateTime datetime

select @lastUpdateTime = MAX(InputTime) from YHSmartDataRetail.[dbo].NWGoods_Material

-- 初次同步，表为空，插入所有数据
if @lastUpdateTime IS NULL
begin
	exec YHSmartDataRetail.[dbo].[insertGoodsMaterial]
end
-- 不为空，根据最后更新时间，更新、插入数据
else
begin
	exec YHSmartDataRetail.[dbo].[updateGoodsMaterial] @lastUpdateTime
end', 
		@database_name=N'master', 
		@flags=0
IF (@@ERROR <> 0 OR @ReturnCode <> 0) GOTO QuitWithRollback
/****** Object:  Step [步骤6：同步鞋子RFID关系NWGoods_ShoeRFID]    Script Date: 2018/12/5 10:42:11 ******/
EXEC @ReturnCode = msdb.dbo.sp_add_jobstep @job_id=@jobId, @step_name=N'步骤6：同步鞋子RFID关系NWGoods_ShoeRFID', 
		@step_id=6, 
		@cmdexec_success_code=0, 
		@on_success_action=1, 
		@on_success_step_id=0, 
		@on_fail_action=2, 
		@on_fail_step_id=0, 
		@retry_attempts=0, 
		@retry_interval=0, 
		@os_run_priority=0, @subsystem=N'TSQL', 
		@command=N'-- 查询新表数据最后更新时间

declare @lastUpdateTime datetime

select @lastUpdateTime = MAX(InputTime) from YHSmartDataRetail.[dbo].NWGoods_ShoeRFID

-- 初次同步，表为空，插入所有数据
if @lastUpdateTime IS NULL
begin
	exec YHSmartDataRetail.[dbo].[insertShoeRFID]
end
-- 不为空，根据最后更新时间，更新、插入数据
else
begin
	exec YHSmartDataRetail.[dbo].[updateShoeRFID] @lastUpdateTime
end', 
		@database_name=N'master', 
		@flags=0
IF (@@ERROR <> 0 OR @ReturnCode <> 0) GOTO QuitWithRollback
EXEC @ReturnCode = msdb.dbo.sp_update_job @job_id = @jobId, @start_step_id = 1
IF (@@ERROR <> 0 OR @ReturnCode <> 0) GOTO QuitWithRollback
EXEC @ReturnCode = msdb.dbo.sp_add_jobschedule @job_id=@jobId, @name=N'计划1：每5分钟同步一次', 
		@enabled=0, 
		@freq_type=4, 
		@freq_interval=1, 
		@freq_subday_type=4, 
		@freq_subday_interval=5, 
		@freq_relative_interval=0, 
		@freq_recurrence_factor=0, 
		@active_start_date=20181119, 
		@active_end_date=99991231, 
		@active_start_time=0, 
		@active_end_time=235959, 
		@schedule_uid=N'10a5ae22-bbbc-40d8-bc0e-8a6233b08f95'
IF (@@ERROR <> 0 OR @ReturnCode <> 0) GOTO QuitWithRollback
EXEC @ReturnCode = msdb.dbo.sp_add_jobserver @job_id = @jobId, @server_name = N'(local)'
IF (@@ERROR <> 0 OR @ReturnCode <> 0) GOTO QuitWithRollback
COMMIT TRANSACTION
GOTO EndSave
QuitWithRollback:
    IF (@@TRANCOUNT > 0) ROLLBACK TRANSACTION
EndSave:
GO

