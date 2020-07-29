USE [master]
GO

/****** Object:  LinkedServer [TRYSHOES_OLD]    Script Date: 2018/12/5 10:40:02 ******/
EXEC master.dbo.sp_addlinkedserver @server = N'TRYSHOES_OLD', @srvproduct=N'', @provider=N'SQLNCLI', @datasrc=N'127.0.0.1'
 /* For security reasons the linked server remote logins password is changed with ######## */
EXEC master.dbo.sp_addlinkedsrvlogin @rmtsrvname=N'TRYSHOES_OLD',@useself=N'False',@locallogin=NULL,@rmtuser=N'sa',@rmtpassword='########'
EXEC master.dbo.sp_addlinkedsrvlogin @rmtsrvname=N'TRYSHOES_OLD',@useself=N'False',@locallogin=N'sa',@rmtuser=N'sa',@rmtpassword='########'
GO

EXEC master.dbo.sp_serveroption @server=N'TRYSHOES_OLD', @optname=N'collation compatible', @optvalue=N'false'
GO

EXEC master.dbo.sp_serveroption @server=N'TRYSHOES_OLD', @optname=N'data access', @optvalue=N'true'
GO

EXEC master.dbo.sp_serveroption @server=N'TRYSHOES_OLD', @optname=N'dist', @optvalue=N'false'
GO

EXEC master.dbo.sp_serveroption @server=N'TRYSHOES_OLD', @optname=N'pub', @optvalue=N'false'
GO

EXEC master.dbo.sp_serveroption @server=N'TRYSHOES_OLD', @optname=N'rpc', @optvalue=N'false'
GO

EXEC master.dbo.sp_serveroption @server=N'TRYSHOES_OLD', @optname=N'rpc out', @optvalue=N'false'
GO

EXEC master.dbo.sp_serveroption @server=N'TRYSHOES_OLD', @optname=N'sub', @optvalue=N'false'
GO

EXEC master.dbo.sp_serveroption @server=N'TRYSHOES_OLD', @optname=N'connect timeout', @optvalue=N'0'
GO

EXEC master.dbo.sp_serveroption @server=N'TRYSHOES_OLD', @optname=N'collation name', @optvalue=null
GO

EXEC master.dbo.sp_serveroption @server=N'TRYSHOES_OLD', @optname=N'lazy schema validation', @optvalue=N'false'
GO

EXEC master.dbo.sp_serveroption @server=N'TRYSHOES_OLD', @optname=N'query timeout', @optvalue=N'0'
GO

EXEC master.dbo.sp_serveroption @server=N'TRYSHOES_OLD', @optname=N'use remote collation', @optvalue=N'true'
GO

EXEC master.dbo.sp_serveroption @server=N'TRYSHOES_OLD', @optname=N'remote proc transaction promotion', @optvalue=N'true'
GO

