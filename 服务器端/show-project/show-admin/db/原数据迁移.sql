INSERT INTO [YHSmartDataRetail].[dbo].[NWGoods_Period]
SELECT SEQ AS Seq,
       BRANDSEQ AS BrandSeq,
       [Key] AS InnerKey,
       replace(replace(NAME,'年',''),'新款','') AS Name,
       FILEDOWNLOADDATE AS FileDownLoadDate,
       SALEDATE AS SaleDate,
       isValid,
       INPUTDATE AS InputTime,
       DEL AS Del
FROM [tryshoes].[dbo].[NWBASE_SHOESPERIOD]
WHERE NAME NOT LIKE '%订货%'
    AND NAME NOT LIKE '%展销%'
    AND DEL=0


INSERT INTO [YHSmartDataRetail].[dbo].[NWGoods_Color] ([Seq],[BrandSeq],[Code],[Name],[InputTime],[Del])
VALUES 
(1,1,'00','无色',getDate(),0),
(2,1,'01','黑色',getDate(),0),
(3,1,'02','咖啡',getDate(),0),
(4,1,'03','白色',getDate(),0),
(5,1,'04','米色',getDate(),0),
(6,1,'05','棕色',getDate(),0),
(7,1,'06','红色',getDate(),0),
(8,1,'07','绿色',getDate(),0),
(9,1,'08','蓝色',getDate(),0),
(10,1,'09','灰色',getDate(),0),
(11,1,'10','驼色',getDate(),0),
(12,1,'11','褐色',getDate(),0),
(13,1,'12','黄色',getDate(),0),
(14,1,'13','杏色',getDate(),0),
(15,1,'14','紫色',getDate(),0),
(16,1,'15','卡其',getDate(),0),
(17,1,'16','古铜',getDate(),0),
(18,1,'17','桔色',getDate(),0),
(19,1,'18','银色',getDate(),0),
(20,1,'19','金色',getDate(),0),
(21,1,'20','粉红色',getDate(),0),
(22,1,'21','裸桃红',getDate(),0),
(23,1,'22','粉金',getDate(),0),
(24,1,'23','黑白蓝',getDate(),0),
(25,1,'24','锡色',getDate(),0),
(26,1,'25','米粉色',getDate(),0),
(27,1,'26','米灰色',getDate(),0),
(28,1,'27','浅金色',getDate(),0),
(29,1,'28','浅蓝色',getDate(),0),
(30,1,'29','桃红色',getDate(),0),
(31,1,'30','银粉色',getDate(),0),
(32,1,'31','橙色',getDate(),0),
(33,1,'32','粉色',getDate(),0),
(34,1,'33','黑色(NL)',getDate(),0),
(35,1,'34','黑米',getDate(),0),
(36,1,'38','米咖',getDate(),0),
(37,1,'40','米色(NL)',getDate(),0),
(38,1,'46','幻彩紫',getDate(),0),
(39,1,'47','黑棕',getDate(),0),
(40,1,'53','棕色(NL)',getDate(),0)

INSERT INTO [YHSmartDataRetail].[dbo].[NWGoods_Size] ([Seq],[BrandSeq],[Category],[Code],[Name],[InputTime],[Del])
VALUES
(1,1,'女','33','33',getDate(),0),
(2,1,'女','34','34',getDate(),0),
(3,1,'女','35','35',getDate(),0),
(4,1,'女','35.5','35.5',getDate(),0),
(5,1,'女','36','36',getDate(),0),
(6,1,'女','36.5','36.5',getDate(),0),
(7,1,'女','37','37',getDate(),0),
(8,1,'女','37.5','37.5',getDate(),0),
(9,1,'女','38','38',getDate(),0),
(10,1,'女','39','39',getDate(),0),
(11,1,'女','40','40',getDate(),0),
(12,1,'女','41','41',getDate(),0),
(13,1,'男','38','38',getDate(),0),
(14,1,'男','39','39',getDate(),0),
(15,1,'男','40','40',getDate(),0),
(16,1,'男','41','41',getDate(),0),
(17,1,'男','42','42',getDate(),0),
(18,1,'男','43','43',getDate(),0),
(19,1,'男','44','44',getDate(),0),
(20,1,'男','45','45',getDate(),0)


INSERT INTO [YHSmartDataRetail].[dbo].[NWGoods_Category] ([Seq],[ParentSeq],[BrandSeq],[Name],[CategoryCode],[Visible],[InputTime],[Del])
VALUES
(1,0,1,'女鞋','001',1,getDate(),0),
(2,0,1,'男鞋','002',1,getDate(),0)


INSERT INTO [YHSmartDataRetail].[dbo].[NWGoods_SX] ([Seq],[BrandSeq],[SXID],[SXName],[Visible],[InputTime],[Del])
VALUES
(1,1,'SX1','大类',1,getDate(),0),
(2,1,'SX2','小类',1,getDate(),0),
(3,1,'SX3','鞋面材质',1,getDate(),0),
(4,1,'SX4','里料材质',1,getDate(),0),
(5,1,'SX5','鞋底材质',1,getDate(),0),
(6,1,'SX6','闭合方式',1,getDate(),0),
(7,1,'SX7','跟底款式',1,getDate(),0),
(8,1,'SX8','鞋头款式',1,getDate(),0),
(9,1,'SX9','后跟高',1,getDate(),0)


/** 鞋面材质 同步*/
declare @intMinId3 int,@intMaxId3 int,@optionMaxSeq3 int,@TypeValue3 varchar(50),@CodeValue3 varchar(10)
SELECT RowID=IDENTITY(INT,1,1),SURFACEMATERIAL as typeValue into #templist3 
  FROM [tryshoes].[dbo].[NWBASE_SHOESBASEINFO] where DEL=0 and ([SHOESPERIOD_SEQ] in (select Seq from [YHSmartDataRetail].[dbo].[NWGoods_Period])) and (substring([GOODID],9,1) in ('A','B','C','X')) and (len(SURFACEMATERIAL)>1) group by SURFACEMATERIAL
SELECT @intMinId3 =MIN(RowID),@intMaxId3=MAX(RowID) FROM #templist3
while @intMinId3<=@intMaxId3
begin
  SELECT @TypeValue3=typeValue FROM #templist3 WHERE RowID=@intMinId3
  select @optionMaxSeq3=MAX(Seq) from [YHSmartDataRetail].[dbo].[NWGoods_SXOption];
  if @optionMaxSeq3 is null
  begin
	set @optionMaxSeq3=1
  end
  else
  begin
	set @optionMaxSeq3=@optionMaxSeq3+1
  end

  set @CodeValue3=CAST(@intMinId3 as varchar(10))
  if len(@CodeValue3)=1
  begin
    set @CodeValue3 = '00'+@CodeValue3
  end
  if len(@CodeValue3)=2
  begin
    set @CodeValue3 = '0'+@CodeValue3
  end

  insert into [YHSmartDataRetail].[dbo].[NWGoods_SXOption] ([Seq],[SXSeq],[Code],[Value],[InputTime],[Del]) VALUES  (@optionMaxSeq3,3,@CodeValue3,@TypeValue3,GETDATE(),0)
  set @intMinId3 = @intMinId3+1
end
DROP TABLE #templist3

/** 里料材质 同步*/
declare @intMinId4 int,@intMaxId4 int,@optionMaxSeq4 int,@TypeValue4 varchar(50),@CodeValue4 varchar(10)
SELECT RowID=IDENTITY(INT,1,1),INNERMATERIAL as typeValue into #templist4 
  FROM [tryshoes].[dbo].[NWBASE_SHOESBASEINFO] where DEL=0 and ([SHOESPERIOD_SEQ] in (select Seq from [YHSmartDataRetail].[dbo].[NWGoods_Period])) and (substring([GOODID],9,1) in ('A','B','C','X')) and (len(INNERMATERIAL)>1) group by INNERMATERIAL
SELECT @intMinId4 =MIN(RowID),@intMaxId4=MAX(RowID) FROM #templist4
while @intMinId4<=@intMaxId4
begin
  SELECT @TypeValue4=typeValue FROM #templist4 WHERE RowID=@intMinId4
  select @optionMaxSeq4=MAX(Seq) from [YHSmartDataRetail].[dbo].[NWGoods_SXOption];
  if @optionMaxSeq4 is null
  begin
	set @optionMaxSeq4=1
  end
  else
  begin
	set @optionMaxSeq4=@optionMaxSeq4+1
  end

  set @CodeValue4=CAST(@intMinId4 as varchar(10))
  if len(@CodeValue4)=1
  begin
    set @CodeValue4 = '00'+@CodeValue4
  end
  if len(@CodeValue4)=2
  begin
    set @CodeValue4 = '0'+@CodeValue4
  end
  insert into [YHSmartDataRetail].[dbo].[NWGoods_SXOption] ([Seq],[SXSeq],[Code],[Value],[InputTime],[Del]) VALUES  (@optionMaxSeq4,4,@CodeValue4,@TypeValue4,GETDATE(),0)
  set @intMinId4 = @intMinId4+1
end
DROP TABLE #templist4

/** 鞋底材质 同步*/
declare @intMinId5 int,@intMaxId5 int,@optionMaxSeq5 int,@TypeValue5 varchar(50),@CodeValue5 varchar(10)
SELECT RowID=IDENTITY(INT,1,1),SOLEMATERIAL as typeValue into #templist5 
  FROM [tryshoes].[dbo].[NWBASE_SHOESBASEINFO] where DEL=0 and ([SHOESPERIOD_SEQ] in (select Seq from [YHSmartDataRetail].[dbo].[NWGoods_Period])) and (substring([GOODID],9,1) in ('A','B','C','X')) and (len(SOLEMATERIAL)>1) group by SOLEMATERIAL
SELECT @intMinId5 =MIN(RowID),@intMaxId5=MAX(RowID) FROM #templist5
while @intMinId5<=@intMaxId5
begin
  SELECT @TypeValue5=typeValue FROM #templist5 WHERE RowID=@intMinId5
  select @optionMaxSeq5=MAX(Seq) from [YHSmartDataRetail].[dbo].[NWGoods_SXOption];
  if @optionMaxSeq5 is null
  begin
	set @optionMaxSeq5=1
  end
  else
  begin
	set @optionMaxSeq5=@optionMaxSeq5+1
  end

  set @CodeValue5=CAST(@intMinId5 as varchar(10))
  if len(@CodeValue5)=1
  begin
    set @CodeValue5 = '00'+@CodeValue5
  end
  if len(@CodeValue5)=2
  begin
    set @CodeValue5 = '0'+@CodeValue5
  end

  insert into [YHSmartDataRetail].[dbo].[NWGoods_SXOption] ([Seq],[SXSeq],[Code],[Value],[InputTime],[Del]) VALUES  (@optionMaxSeq5,5,@CodeValue5,@TypeValue5,GETDATE(),0)
  set @intMinId5 = @intMinId5+1
end
DROP TABLE #templist5

/** 闭合方式 同步*/
declare @intMinId6 int,@intMaxId6 int,@optionMaxSeq6 int,@TypeValue6 varchar(50),@CodeValue6 varchar(10)
SELECT RowID=IDENTITY(INT,1,1),CLOSEFORM as typeValue into #templist6 
  FROM [tryshoes].[dbo].[NWBASE_SHOESBASEINFO] where DEL=0 and ([SHOESPERIOD_SEQ] in (select Seq from [YHSmartDataRetail].[dbo].[NWGoods_Period])) and (substring([GOODID],9,1) in ('A','B','C','X')) and (len(CLOSEFORM)>1) group by CLOSEFORM
SELECT @intMinId6 =MIN(RowID),@intMaxId6=MAX(RowID) FROM #templist6
while @intMinId6<=@intMaxId6
begin
  SELECT @TypeValue6=typeValue FROM #templist6 WHERE RowID=@intMinId6
  select @optionMaxSeq6=MAX(Seq) from [YHSmartDataRetail].[dbo].[NWGoods_SXOption];
  if @optionMaxSeq6 is null
  begin
	set @optionMaxSeq6=1
  end
  else
  begin
	set @optionMaxSeq6=@optionMaxSeq6+1
  end

  set @CodeValue6=CAST(@intMinId6 as varchar(10))
  if len(@CodeValue6)=1
  begin
    set @CodeValue6 = '00'+@CodeValue6
  end
  if len(@CodeValue6)=2
  begin
    set @CodeValue6 = '0'+@CodeValue6
  end

  insert into [YHSmartDataRetail].[dbo].[NWGoods_SXOption] ([Seq],[SXSeq],[Code],[Value],[InputTime],[Del]) VALUES  (@optionMaxSeq6,6,@CodeValue6,@TypeValue6,GETDATE(),0)
  set @intMinId6 = @intMinId6+1
end
DROP TABLE #templist6

/** 跟底款式 同步*/
declare @intMinId7 int,@intMaxId7 int,@optionMaxSeq7 int,@TypeValue7 varchar(50),@CodeValue7 varchar(10)
SELECT RowID=IDENTITY(INT,1,1),HEELSHAPE as typeValue into #templist7 
  FROM [tryshoes].[dbo].[NWBASE_SHOESBASEINFO] where DEL=0 and ([SHOESPERIOD_SEQ] in (select Seq from [YHSmartDataRetail].[dbo].[NWGoods_Period])) and (substring([GOODID],9,1) in ('A','B','C','X')) and (len(HEELSHAPE)>1) group by HEELSHAPE
SELECT @intMinId7 =MIN(RowID),@intMaxId7=MAX(RowID) FROM #templist7
while @intMinId7<=@intMaxId7
begin
  SELECT @TypeValue7=typeValue FROM #templist7 WHERE RowID=@intMinId7
  select @optionMaxSeq7=MAX(Seq) from [YHSmartDataRetail].[dbo].[NWGoods_SXOption];
  if @optionMaxSeq7 is null
  begin
	set @optionMaxSeq7=1
  end
  else
  begin
	set @optionMaxSeq7=@optionMaxSeq7+1
  end

  set @CodeValue7=CAST(@intMinId7 as varchar(10))
  if len(@CodeValue7)=1
  begin
    set @CodeValue7 = '00'+@CodeValue7
  end
  if len(@CodeValue7)=2
  begin
    set @CodeValue7 = '0'+@CodeValue7
  end

  insert into [YHSmartDataRetail].[dbo].[NWGoods_SXOption] ([Seq],[SXSeq],[Code],[Value],[InputTime],[Del]) VALUES  (@optionMaxSeq7,7,@CodeValue7,@TypeValue7,GETDATE(),0)
  set @intMinId7 = @intMinId7+1
end
DROP TABLE #templist7

/** 鞋头款式 同步*/
declare @intMinId8 int,@intMaxId8 int,@optionMaxSeq8 int,@TypeValue8 varchar(50),@CodeValue8 varchar(10)
SELECT RowID=IDENTITY(INT,1,1),TOESTYLE as typeValue into #templist8 
  FROM [tryshoes].[dbo].[NWBASE_SHOESBASEINFO] where DEL=0 and ([SHOESPERIOD_SEQ] in (select Seq from [YHSmartDataRetail].[dbo].[NWGoods_Period])) and (substring([GOODID],9,1) in ('A','B','C','X')) and (len(TOESTYLE)>1) group by TOESTYLE
SELECT @intMinId8 =MIN(RowID),@intMaxId8=MAX(RowID) FROM #templist8
while @intMinId8<=@intMaxId8
begin
  SELECT @TypeValue8=typeValue FROM #templist8 WHERE RowID=@intMinId8
  select @optionMaxSeq8=MAX(Seq) from [YHSmartDataRetail].[dbo].[NWGoods_SXOption];
  if @optionMaxSeq8 is null
  begin
	set @optionMaxSeq8=1
  end
  else
  begin
	set @optionMaxSeq8=@optionMaxSeq8+1
  end

  set @CodeValue8=CAST(@intMinId8 as varchar(10))
  if len(@CodeValue8)=1
  begin
    set @CodeValue8 = '00'+@CodeValue8
  end
  if len(@CodeValue8)=2
  begin
    set @CodeValue8 = '0'+@CodeValue8
  end

  insert into [YHSmartDataRetail].[dbo].[NWGoods_SXOption] ([Seq],[SXSeq],[Code],[Value],[InputTime],[Del]) VALUES  (@optionMaxSeq8,8,@CodeValue8,@TypeValue8,GETDATE(),0)
  set @intMinId8 = @intMinId8+1
end
DROP TABLE #templist8

/** 后跟高 同步*/
declare @intMinId9 int,@intMaxId9 int,@optionMaxSeq9 int,@TypeValue9 varchar(50),@CodeValue9 varchar(10)
SELECT RowID=IDENTITY(INT,1,1),HEELHEIGHT as typeValue into #templist9 
  FROM [tryshoes].[dbo].[NWBASE_SHOESBASEINFO] where DEL=0 and ([SHOESPERIOD_SEQ] in (select Seq from [YHSmartDataRetail].[dbo].[NWGoods_Period])) and (substring([GOODID],9,1) in ('A','B','C','X')) and (len(HEELHEIGHT)>0) group by HEELHEIGHT
SELECT @intMinId9 =MIN(RowID),@intMaxId9=MAX(RowID) FROM #templist9
while @intMinId9<=@intMaxId9
begin
  SELECT @TypeValue9=typeValue FROM #templist9 WHERE RowID=@intMinId9
  select @optionMaxSeq9=MAX(Seq) from [YHSmartDataRetail].[dbo].[NWGoods_SXOption];
  if @optionMaxSeq9 is null
  begin
	set @optionMaxSeq9=1
  end
  else
  begin
	set @optionMaxSeq9=@optionMaxSeq9+1
  end

  set @CodeValue9=CAST(@intMinId9 as varchar(10))
  if len(@CodeValue9)=1
  begin
    set @CodeValue9 = '00'+@CodeValue9
  end
  if len(@CodeValue9)=2
  begin
    set @CodeValue9 = '0'+@CodeValue9
  end

  insert into [YHSmartDataRetail].[dbo].[NWGoods_SXOption] ([Seq],[SXSeq],[Code],[Value],[InputTime],[Del]) VALUES  (@optionMaxSeq9,9,@CodeValue9,@TypeValue9,GETDATE(),0)
  set @intMinId9 = @intMinId9+1
end
DROP TABLE #templist9


#查询有效的鞋子
declare @intMinId int,@intMaxId int,@optionMaxSeq int,@PeriodSeq int,@Name varchar(50),@ShoeID varchar(50),@CategorySeq int,@CodeName3 varchar(10),@CodeName4 varchar(10),@CodeName5 varchar(10),@CodeName6 varchar(10),@CodeName7 varchar(10),@CodeName8 varchar(10),@CodeName9 varchar(10),@InputTime date
declare @CodeValue3 varchar(10),@CodeValue4 varchar(10),@CodeValue5 varchar(10),@CodeValue6 varchar(10),@CodeValue7 varchar(10),@CodeValue8 varchar(10),@CodeValue9 varchar(10)
declare @checkSeq int
SELECT RowID=IDENTITY(INT,1,1)
      ,[SHOESPERIOD_SEQ] as PeriodSeq
      ,[GOODNAME] as Name
      ,[GOODID] as ShoeID
      ,[COLOR]
      ,[SURFACEMATERIAL]
      ,[INNERMATERIAL]
      ,[SOLEMATERIAL]
      ,[CLOSEFORM]
      ,[HEELSHAPE]
      ,[TOESTYLE]
      ,[HEELHEIGHT]
      ,[INPUTDATE] as InputTime into #tempshoelist
  FROM [tryshoes].[dbo].[NWBASE_SHOESBASEINFO] where DEL=0 and ([SHOESPERIOD_SEQ] in (select Seq from [YHSmartDataRetail].[dbo].[NWGoods_Period])) and (substring([GOODID],9,1) in ('A','B','C','X'))
SELECT @intMinId =MIN(RowID),@intMaxId=MAX(RowID) FROM #tempshoelist
while @intMinId<=@intMaxId
begin
  SELECT @PeriodSeq=PeriodSeq,@Name=Name,@ShoeID=ShoeID,@CodeName3=SURFACEMATERIAL,@CodeName4=INNERMATERIAL,@CodeName5=SOLEMATERIAL,@CodeName6=CLOSEFORM,@CodeName7=HEELSHAPE,@CodeName8=TOESTYLE,@CodeName9=HEELHEIGHT,@InputTime=InputTime FROM #tempshoelist WHERE RowID=@intMinId
  /**判断货号是否存在，存在不处理*/
  set @checkSeq=NULL
  select @checkSeq=Seq from [YHSmartDataRetail].[dbo].[NWGoods_Shoe] where ShoeID=@ShoeID
  if @checkSeq is null
  begin
    /**获取下一个Seq*/
    select @optionMaxSeq=MAX(Seq) from [YHSmartDataRetail].[dbo].[NWGoods_Shoe]
    if @optionMaxSeq is null
    begin
      set @optionMaxSeq=1
    end
    else
    begin
      set @optionMaxSeq=@optionMaxSeq+1
    end

    /**计算分类Seq*/
    if substring(@ShoeID,9,1)='X'
    begin
      set @CategorySeq=2
    end
    else
    begin
      set @CategorySeq=1
    end
    /**获取属性3的code*/
    SELECT TOP (1) @CodeValue3=[Code] FROM [YHSmartDataRetail].[dbo].[NWGoods_SXOption] where [SXSeq]=3 and [Value]=@CodeName3
    /**获取属性4的code*/
    SELECT TOP (1) @CodeValue4=[Code] FROM [YHSmartDataRetail].[dbo].[NWGoods_SXOption] where [SXSeq]=4 and [Value]=@CodeName4
    /**获取属性5的code*/
    SELECT TOP (1) @CodeValue5=[Code] FROM [YHSmartDataRetail].[dbo].[NWGoods_SXOption] where [SXSeq]=5 and [Value]=@CodeName5
    /**获取属性6的code*/
    SELECT TOP (1) @CodeValue6=[Code] FROM [YHSmartDataRetail].[dbo].[NWGoods_SXOption] where [SXSeq]=6 and [Value]=@CodeName6
    /**获取属性7的code*/
    SELECT TOP (1) @CodeValue7=[Code] FROM [YHSmartDataRetail].[dbo].[NWGoods_SXOption] where [SXSeq]=7 and [Value]=@CodeName7
    /**获取属性8的code*/
    SELECT TOP (1) @CodeValue8=[Code] FROM [YHSmartDataRetail].[dbo].[NWGoods_SXOption] where [SXSeq]=8 and [Value]=@CodeName8
    /**获取属性9的code*/
    SELECT TOP (1) @CodeValue9=[Code] FROM [YHSmartDataRetail].[dbo].[NWGoods_SXOption] where [SXSeq]=9 and [Value]=@CodeName9

    INSERT into [YHSmartDataRetail].[dbo].[NWGoods_Shoe]([Seq],[PeriodSeq],[Name],[ShoeID],[CategorySeq],[SX3],[SX4],[SX5],[SX6],[SX7],[SX8],[SX9],[InputTime],[Del]) VALUES (@optionMaxSeq,@PeriodSeq,@Name,@ShoeID,@CategorySeq,@CodeValue3,@CodeValue4,@CodeValue5,@CodeValue6,@CodeValue7,@CodeValue8,@CodeValue9,@InputTime,0)
  end
  set @intMinId = @intMinId+1
end
DROP TABLE #tempshoelist