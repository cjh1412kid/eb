USE [master]
GO

/****** Object:  Database [YHSmartDataRetail]    Script Date: 2018/12/5 10:07:46 ******/
CREATE DATABASE [YHSmartDataRetail] ON  PRIMARY 
( NAME = N'YHSmartDataRetail', FILENAME = N'C:\Program Files\Microsoft SQL Server\MSSQL10.MSSQLSERVER\MSSQL\DATA\YHSmartDataRetail.mdf' , SIZE = 506880KB , MAXSIZE = UNLIMITED, FILEGROWTH = 1024KB )
 LOG ON 
( NAME = N'YHSmartDataRetail_log', FILENAME = N'C:\Program Files\Microsoft SQL Server\MSSQL10.MSSQLSERVER\MSSQL\DATA\YHSmartDataRetail_log.ldf' , SIZE = 1964480KB , MAXSIZE = 2048GB , FILEGROWTH = 10%)
GO

ALTER DATABASE [YHSmartDataRetail] SET COMPATIBILITY_LEVEL = 100
GO

IF (1 = FULLTEXTSERVICEPROPERTY('IsFullTextInstalled'))
begin
EXEC [YHSmartDataRetail].[dbo].[sp_fulltext_database] @action = 'enable'
end
GO

ALTER DATABASE [YHSmartDataRetail] SET ANSI_NULL_DEFAULT OFF 
GO

ALTER DATABASE [YHSmartDataRetail] SET ANSI_NULLS OFF 
GO

ALTER DATABASE [YHSmartDataRetail] SET ANSI_PADDING OFF 
GO

ALTER DATABASE [YHSmartDataRetail] SET ANSI_WARNINGS OFF 
GO

ALTER DATABASE [YHSmartDataRetail] SET ARITHABORT OFF 
GO

ALTER DATABASE [YHSmartDataRetail] SET AUTO_CLOSE OFF 
GO

ALTER DATABASE [YHSmartDataRetail] SET AUTO_SHRINK OFF 
GO

ALTER DATABASE [YHSmartDataRetail] SET AUTO_UPDATE_STATISTICS ON 
GO

ALTER DATABASE [YHSmartDataRetail] SET CURSOR_CLOSE_ON_COMMIT OFF 
GO

ALTER DATABASE [YHSmartDataRetail] SET CURSOR_DEFAULT  GLOBAL 
GO

ALTER DATABASE [YHSmartDataRetail] SET CONCAT_NULL_YIELDS_NULL OFF 
GO

ALTER DATABASE [YHSmartDataRetail] SET NUMERIC_ROUNDABORT OFF 
GO

ALTER DATABASE [YHSmartDataRetail] SET QUOTED_IDENTIFIER OFF 
GO

ALTER DATABASE [YHSmartDataRetail] SET RECURSIVE_TRIGGERS OFF 
GO

ALTER DATABASE [YHSmartDataRetail] SET  DISABLE_BROKER 
GO

ALTER DATABASE [YHSmartDataRetail] SET AUTO_UPDATE_STATISTICS_ASYNC OFF 
GO

ALTER DATABASE [YHSmartDataRetail] SET DATE_CORRELATION_OPTIMIZATION OFF 
GO

ALTER DATABASE [YHSmartDataRetail] SET TRUSTWORTHY OFF 
GO

ALTER DATABASE [YHSmartDataRetail] SET ALLOW_SNAPSHOT_ISOLATION OFF 
GO

ALTER DATABASE [YHSmartDataRetail] SET PARAMETERIZATION SIMPLE 
GO

ALTER DATABASE [YHSmartDataRetail] SET READ_COMMITTED_SNAPSHOT OFF 
GO

ALTER DATABASE [YHSmartDataRetail] SET HONOR_BROKER_PRIORITY OFF 
GO

ALTER DATABASE [YHSmartDataRetail] SET RECOVERY FULL 
GO

ALTER DATABASE [YHSmartDataRetail] SET  MULTI_USER 
GO

ALTER DATABASE [YHSmartDataRetail] SET PAGE_VERIFY CHECKSUM  
GO

ALTER DATABASE [YHSmartDataRetail] SET DB_CHAINING OFF 
GO

ALTER DATABASE [YHSmartDataRetail] SET  READ_WRITE 
GO


USE [YHSmartDataRetail]
GO
/****** Object:  Table [dbo].[NWBase_Animation]    Script Date: 2018/12/5 10:12:31 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[NWBase_Animation](
	[Seq] [int] IDENTITY(1,1) NOT NULL,
	[Name] [nchar](10) NULL,
	[InputTime] [datetime] NULL,
	[Del] [int] NOT NULL,
 CONSTRAINT [PK_NWBase_Animation] PRIMARY KEY CLUSTERED 
(
	[Seq] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[NWBase_Area]    Script Date: 2018/12/5 10:12:31 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[NWBase_Area](
	[Seq] [int] NOT NULL,
	[ParentSeq] [int] NULL,
	[BrandSeq] [int] NULL,
	[AreaName] [varchar](50) NULL,
	[Bound] [nvarchar](max) NULL,
	[InputTime] [datetime] NULL,
	[Del] [int] NOT NULL,
 CONSTRAINT [PK_NWBase_Area] PRIMARY KEY CLUSTERED 
(
	[Seq] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY] TEXTIMAGE_ON [PRIMARY]
GO
/****** Object:  Table [dbo].[NWBase_Brand]    Script Date: 2018/12/5 10:12:31 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[NWBase_Brand](
	[Seq] [int] NOT NULL,
	[IdentifyCode] [varchar](50) NULL,
	[InnerKey] [varchar](50) NULL,
	[Prefix] [varchar](20) NULL,
	[BrandName] [varchar](50) NULL,
	[CompanySeq] [int] NULL,
	[QRCodeName] [varchar](50) NULL,
	[InputTime] [datetime] NULL,
	[Del] [int] NOT NULL,
 CONSTRAINT [PK_NWBase_Brand] PRIMARY KEY CLUSTERED 
(
	[Seq] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[NWBase_Company]    Script Date: 2018/12/5 10:12:31 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[NWBase_Company](
	[Seq] [int] NOT NULL,
	[InnerKey] [varchar](50) NULL,
	[Name] [varchar](255) NULL,
	[InputTime] [datetime] NULL,
	[Del] [int] NOT NULL,
 CONSTRAINT [PK_NWBase_Company] PRIMARY KEY CLUSTERED 
(
	[Seq] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[NWBase_DefaultPlayList]    Script Date: 2018/12/5 10:12:31 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[NWBase_DefaultPlayList](
	[Seq] [int] NOT NULL,
	[BrandSeq] [int] NULL,
	[Name] [varchar](50) NULL,
	[FileName] [varchar](50) NULL,
	[IsSelect] [int] NULL,
	[MD5Code] [varchar](50) NULL,
	[RelativeURL] [varchar](255) NULL,
	[Flag] [int] NULL,
	[InputTime] [datetime] NULL,
	[Del] [int] NOT NULL,
 CONSTRAINT [PK_NWBase_DefaultPlayList] PRIMARY KEY CLUSTERED 
(
	[Seq] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[NWBase_HardwareParameterSet]    Script Date: 2018/12/5 10:12:31 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[NWBase_HardwareParameterSet](
	[Seq] [int] NOT NULL,
	[ShopSeq] [int] NULL,
	[HardwareID] [varchar](50) NULL,
	[Name] [varchar](50) NULL,
	[UseAntennaPort] [varchar](255) NULL,
	[ReaderType] [int] NULL,
	[IP] [varchar](15) NULL,
	[Port] [varchar](15) NULL,
	[OutPutPower] [int] NULL,
	[RFIDEffectTime] [int] NULL,
	[Minimumfrequency] [float] NULL,
	[Maxmumfrequency] [float] NULL,
	[FrequencySpace] [float] NULL,
	[BleeperMode] [int] NULL,
	[ProFileMode] [int] NULL,
	[ReturnLoss] [int] NULL,
	[InputTime] [datetime] NULL,
	[Del] [int] NOT NULL,
 CONSTRAINT [PK_NWBase_HardwareParameterSet] PRIMARY KEY CLUSTERED 
(
	[Seq] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[NWBase_Shop]    Script Date: 2018/12/5 10:12:31 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[NWBase_Shop](
	[Seq] [int] NOT NULL,
	[AreaSeq] [int] NULL,
	[ShopID] [varchar](255) NULL,
	[Name] [varchar](50) NULL,
	[Address] [varchar](255) NULL,
	[Lat] [decimal](10, 6) NULL,
	[Lng] [decimal](10, 6) NULL,
	[InstallDate] [datetime] NULL,
	[InputTime] [datetime] NULL,
	[Del] [int] NOT NULL,
 CONSTRAINT [PK_NWBase_Shop] PRIMARY KEY CLUSTERED 
(
	[Seq] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[NWBase_ShopAnimationControl]    Script Date: 2018/12/5 10:12:31 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[NWBase_ShopAnimationControl](
	[Seq] [int] NOT NULL,
	[AnimationSeq] [int] NULL,
	[UserSeq] [int] NULL,
	[ShopSeqs] [int] NULL,
	[SValidDate] [datetime] NULL,
	[EValidDate] [datetime] NULL,
	[InputTime] [datetime] NULL,
	[Del] [int] NOT NULL,
 CONSTRAINT [PK_NWBase_ShopAnimationControl] PRIMARY KEY CLUSTERED 
(
	[Seq] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[NWBase_ShopShowControl]    Script Date: 2018/12/5 10:12:31 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[NWBase_ShopShowControl](
	[Seq] [int] NOT NULL,
	[UserSeq] [int] NULL,
	[ShopSeqs] [varchar](max) NULL,
	[DefaultTry] [int] NULL,
	[DefaultSale] [int] NULL,
	[RecommendShoes] [varchar](max) NULL,
	[SValidDate] [datetime] NULL,
	[EValidDate] [datetime] NULL,
	[InputTime] [datetime] NULL,
	[Del] [int] NOT NULL,
 CONSTRAINT [PK_NWBase_ShopShowControl] PRIMARY KEY CLUSTERED 
(
	[Seq] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY] TEXTIMAGE_ON [PRIMARY]
GO
/****** Object:  Table [dbo].[NWBase_UserBrand]    Script Date: 2018/12/5 10:12:31 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[NWBase_UserBrand](
	[Seq] [int] NOT NULL,
	[UserSeq] [int] NULL,
	[BrandSeq] [int] NULL,
	[InputTime] [datetime] NULL,
	[Del] [int] NOT NULL,
 CONSTRAINT [PK_NWBase_UserBrand] PRIMARY KEY CLUSTERED 
(
	[Seq] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[NWGoods_Category]    Script Date: 2018/12/5 10:12:31 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[NWGoods_Category](
	[Seq] [int] NOT NULL,
	[ParentSeq] [int] NULL,
	[BrandSeq] [int] NULL,
	[Name] [varchar](50) NULL,
	[CategoryCode] [varchar](10) NULL,
	[Visible] [int] NULL,
	[InputTime] [datetime] NULL,
	[Del] [int] NOT NULL,
 CONSTRAINT [PK_NWGoods_Category] PRIMARY KEY CLUSTERED 
(
	[Seq] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[NWGoods_Color]    Script Date: 2018/12/5 10:12:31 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[NWGoods_Color](
	[Seq] [int] NOT NULL,
	[BrandSeq] [int] NULL,
	[Code] [varchar](50) NULL,
	[Name] [varchar](50) NULL,
	[InputTime] [datetime] NULL,
	[Del] [int] NOT NULL,
 CONSTRAINT [PK_NWGoods_Color] PRIMARY KEY CLUSTERED 
(
	[Seq] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[NWGoods_Material]    Script Date: 2018/12/5 10:12:31 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[NWGoods_Material](
	[Seq] [int] NOT NULL,
	[PeriodSeq] [int] NULL,
	[MaterialName] [varchar](50) NULL,
	[MaterialFileName] [varchar](50) NULL,
	[MaterialType] [int] NULL,
	[UsefulDate] [datetime] NULL,
	[ShoeSeq] [int] NULL,
	[ShoeID] [varchar](50) NULL,
	[MD5Code] [varchar](50) NULL,
	[RelativeURL] [varchar](255) NULL,
	[InputTime] [datetime] NULL,
	[Del] [int] NOT NULL,
 CONSTRAINT [PK_NWGoods_Material] PRIMARY KEY CLUSTERED 
(
	[Seq] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[NWGoods_Period]    Script Date: 2018/12/5 10:12:31 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[NWGoods_Period](
	[Seq] [int] NOT NULL,
	[BrandSeq] [int] NULL,
	[InnerKey] [varchar](50) NULL,
	[Name] [varchar](255) NULL,
	[FileDownLoadDate] [datetime] NULL,
	[SaleDate] [date] NULL,
	[isValid] [int] NULL,
	[InputTime] [datetime] NULL,
	[Del] [int] NOT NULL,
 CONSTRAINT [PK_NWGoods_Period] PRIMARY KEY CLUSTERED 
(
	[Seq] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[NWGoods_SaleQuota]    Script Date: 2018/12/5 10:12:31 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[NWGoods_SaleQuota](
	[Seq] [int] NOT NULL,
	[BrandSeq] [int] NULL,
	[Year] [int] NULL,
	[Month] [int] NULL,
	[AreaSeq] [int] NULL,
	[Quota] [decimal](12, 2) NULL,
	[AreaType] [int] NULL,
	[UserSeq] [int] NULL,
	[InputTime] [datetime] NULL,
	[Del] [int] NOT NULL,
 CONSTRAINT [PK_NWGoods_SaleQuota] PRIMARY KEY CLUSTERED 
(
	[Seq] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[NWGoods_SaleShoesDetail]    Script Date: 2018/12/5 10:12:31 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[NWGoods_SaleShoesDetail](
	[Seq] [int] NOT NULL,
	[PeriodSeq] [int] NULL,
	[AreaSeq] [int] NULL,
	[BranchOfficeSeq] [int] NULL,
	[ShopSeq] [int] NULL,
	[SaleDate] [datetime] NULL,
	[ShoeSeq] [int] NULL,
	[ShoeID] [varchar](50) NULL,
	[OrderCount] [int] NULL,
	[SaleCount] [int] NULL,
	[Cost] [decimal](10, 2) NULL,
	[TagPrice] [decimal](10, 2) NULL,
	[RealPrice] [decimal](10, 2) NULL,
	[InputTime] [datetime] NULL,
	[Del] [int] NOT NULL,
 CONSTRAINT [PK_NWGoods_SaleShoesDetail] PRIMARY KEY CLUSTERED 
(
	[Seq] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[NWGoods_Series]    Script Date: 2018/12/5 10:12:31 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[NWGoods_Series](
	[Seq] [int] NOT NULL,
	[BrandSeq] [int] NULL,
	[Name] [varchar](50) NULL,
	[Remark] [varchar](50) NULL,
	[BgImg] [varchar](50) NULL,
	[InputTime] [datetime] NULL,
	[Del] [int] NOT NULL,
 CONSTRAINT [PK_NWGoods_Series] PRIMARY KEY CLUSTERED 
(
	[Seq] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[NWGoods_Shoe]    Script Date: 2018/12/5 10:12:31 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[NWGoods_Shoe](
	[Seq] [int] NOT NULL,
	[PeriodSeq] [int] NULL,
	[QRCode] [varchar](255) NULL,
	[Name] [varchar](50) NULL,
	[ShoeID] [varchar](50) NULL,
	[CategorySeq] [int] NULL,
	[SX1] [varchar](6) NULL,
	[SX2] [varchar](6) NULL,
	[SX3] [varchar](6) NULL,
	[SX4] [varchar](6) NULL,
	[SX5] [varchar](6) NULL,
	[SX6] [varchar](6) NULL,
	[SX7] [varchar](6) NULL,
	[SX8] [varchar](6) NULL,
	[SX9] [varchar](6) NULL,
	[SX10] [varchar](6) NULL,
	[SX11] [varchar](6) NULL,
	[SX12] [varchar](6) NULL,
	[SX13] [varchar](6) NULL,
	[SX14] [varchar](6) NULL,
	[SX15] [varchar](6) NULL,
	[SX16] [varchar](6) NULL,
	[SX17] [varchar](6) NULL,
	[SX18] [varchar](6) NULL,
	[SX19] [varchar](6) NULL,
	[SX20] [varchar](6) NULL,
	[SeriesSeq] [int] NULL,
	[Thumbnail] [varchar](50) NULL,
	[InputTime] [datetime] NULL,
	[Del] [int] NOT NULL,
 CONSTRAINT [PK_NWGoods_Shoe] PRIMARY KEY CLUSTERED 
(
	[Seq] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[NWGoods_ShoeRFID]    Script Date: 2018/12/5 10:12:31 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[NWGoods_ShoeRFID](
	[Seq] [int] NOT NULL,
	[ShoeSeq] [varchar](255) NULL,
	[ShoeID] [varchar](50) NULL,
	[RFIDCode] [varchar](255) NULL,
	[InputTime] [datetime] NULL,
	[Del] [int] NOT NULL,
 CONSTRAINT [PK_NWGoods_ShoeRFID] PRIMARY KEY CLUSTERED 
(
	[Seq] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[NWGoods_ShoesData]    Script Date: 2018/12/5 10:12:31 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[NWGoods_ShoesData](
	[Seq] [int] NOT NULL,
	[ShoeSeq] [int] NULL,
	[ColorSeq] [int] NULL,
	[SizeSeq] [int] NULL,
	[Num] [int] NULL,
	[StockDate] [datetime] NULL,
	[Stock] [int] NULL,
	[InputTime] [datetime] NULL,
	[Del] [int] NOT NULL,
 CONSTRAINT [PK_NWGoods_ShoesData] PRIMARY KEY CLUSTERED 
(
	[Seq] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[NWGoods_ShoesOrder]    Script Date: 2018/12/5 10:12:31 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[NWGoods_ShoesOrder](
	[Seq] [int] NOT NULL,
	[ShoeSeq] [int] NULL,
	[PeriodSeq] [int] NULL,
	[AreaSeq] [int] NULL,
	[shopSeq] [int] NULL,
	[OrderCount] [int] NULL,
	[InputTime] [datetime] NULL,
	[Del] [int] NOT NULL,
 CONSTRAINT [PK_NWGoods_ShoesOrder] PRIMARY KEY CLUSTERED 
(
	[Seq] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[NWGoods_Size]    Script Date: 2018/12/5 10:12:31 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[NWGoods_Size](
	[Seq] [int] NOT NULL,
	[BrandSeq] [int] NULL,
	[Code] [varchar](50) NULL,
	[Name] [varchar](50) NULL,
	[InputTime] [datetime] NULL,
	[Del] [int] NOT NULL,
 CONSTRAINT [PK_NWGoods_Size] PRIMARY KEY CLUSTERED 
(
	[Seq] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[NWGoods_SX]    Script Date: 2018/12/5 10:12:31 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[NWGoods_SX](
	[Seq] [int] NOT NULL,
	[BrandSeq] [int] NULL,
	[SXID] [varchar](50) NULL,
	[SXName] [varchar](50) NULL,
	[Visible] [int] NULL,
	[InputTime] [datetime] NULL,
	[Del] [int] NOT NULL,
 CONSTRAINT [PK_NWGoods_SX] PRIMARY KEY CLUSTERED 
(
	[Seq] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[NWGoods_SXOption]    Script Date: 2018/12/5 10:12:31 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[NWGoods_SXOption](
	[Seq] [int] NOT NULL,
	[SXSeq] [int] NULL,
	[Code] [varchar](50) NULL,
	[Value] [varchar](50) NULL,
	[InputTime] [datetime] NULL,
	[Del] [int] NOT NULL,
 CONSTRAINT [PK_NWGoods_SXOption] PRIMARY KEY CLUSTERED 
(
	[Seq] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[NWGoods_TryShoesDetail]    Script Date: 2018/12/5 10:12:31 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[NWGoods_TryShoesDetail](
	[Seq] [int] NOT NULL,
	[ShoeSeq] [int] NULL,
	[ShoeID] [varchar](50) NULL,
	[TryCount] [int] NULL,
	[TryTimes] [int] NULL,
	[ShopSeq] [int] NULL,
	[DataTime] [datetime] NULL,
	[InputTime] [datetime] NULL,
	[Del] [int] NOT NULL,
 CONSTRAINT [PK_NWGoods_TryShoesDetail] PRIMARY KEY CLUSTERED 
(
	[Seq] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[NWOther_UpSystem]    Script Date: 2018/12/5 10:12:31 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[NWOther_UpSystem](
	[Seq] [int] NOT NULL,
	[US_FileName] [varchar](255) NULL,
	[US_SystemFile] [int] NULL,
	[US_RegFile] [int] NULL,
	[US_Version] [int] NULL,
	[CompanySeq] [int] NULL,
	[US_FilePath] [varchar](255) NULL,
 CONSTRAINT [PK_NWOther_UpSystem] PRIMARY KEY CLUSTERED 
(
	[Seq] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[NWUser_FollowsArea]    Script Date: 2018/12/5 10:12:31 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[NWUser_FollowsArea](
	[Seq] [int] NOT NULL,
	[UserSeq] [int] NULL,
	[FollowDQSeqs] [varchar](50) NULL,
	[FollowBSCSeqs] [varchar](50) NULL,
	[FollowMDSeqs] [varchar](50) NULL,
	[InputTime] [datetime] NULL,
	[Del] [int] NOT NULL,
 CONSTRAINT [PK_NWUser_FollowsArea] PRIMARY KEY CLUSTERED 
(
	[Seq] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[NWUser_Permission]    Script Date: 2018/12/5 10:12:31 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[NWUser_Permission](
	[Seq] [int] NOT NULL,
	[PerName] [varchar](50) NULL,
	[Path] [varchar](50) NULL,
	[InputTime] [datetime] NULL,
	[Del] [int] NOT NULL,
 CONSTRAINT [PK_NWUser_Permission] PRIMARY KEY CLUSTERED 
(
	[Seq] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[NWUser_Role]    Script Date: 2018/12/5 10:12:31 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[NWUser_Role](
	[Seq] [int] NOT NULL,
	[RoleName] [varchar](50) NULL,
	[InputTime] [datetime] NULL,
	[Del] [int] NOT NULL,
 CONSTRAINT [PK_NWUser_Role] PRIMARY KEY CLUSTERED 
(
	[Seq] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[NWUser_RolePermission]    Script Date: 2018/12/5 10:12:31 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[NWUser_RolePermission](
	[Seq] [int] NOT NULL,
	[RoleSeq] [int] NULL,
	[PermissionSeq] [int] NULL,
	[InputTime] [datetime] NULL,
	[Del] [int] NOT NULL,
 CONSTRAINT [PK_NWUser_RolePermission] PRIMARY KEY CLUSTERED 
(
	[Seq] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[NWUser_User]    Script Date: 2018/12/5 10:12:31 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[NWUser_User](
	[Seq] [int] NOT NULL,
	[UserName] [varchar](50) NOT NULL,
	[Password] [varchar](100) NULL,
	[RoleSeq] [int] NULL,
	[UserAreaSeq] [int] NULL,
	[CreateUserSeq] [int] NULL,
	[RealName] [varchar](50) NULL,
	[Telephone] [varchar](20) NULL,
	[HeadImg] [varchar](100) NULL,
	[IsUseful] [int] NOT NULL,
	[InputTime] [datetime] NULL,
	[Del] [int] NOT NULL,
 CONSTRAINT [PK_NWUser_User] PRIMARY KEY CLUSTERED 
(
	[Seq] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[QRTZ_BLOB_TRIGGERS]    Script Date: 2018/12/5 10:12:31 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[QRTZ_BLOB_TRIGGERS](
	[SCHED_NAME] [varchar](120) NOT NULL,
	[TRIGGER_NAME] [varchar](200) NOT NULL,
	[TRIGGER_GROUP] [varchar](200) NOT NULL,
	[BLOB_DATA] [image] NULL
) ON [PRIMARY] TEXTIMAGE_ON [PRIMARY]
GO
/****** Object:  Table [dbo].[QRTZ_CALENDARS]    Script Date: 2018/12/5 10:12:31 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[QRTZ_CALENDARS](
	[SCHED_NAME] [varchar](120) NOT NULL,
	[CALENDAR_NAME] [varchar](200) NOT NULL,
	[CALENDAR] [image] NOT NULL,
 CONSTRAINT [PK_QRTZ_CALENDARS] PRIMARY KEY CLUSTERED 
(
	[SCHED_NAME] ASC,
	[CALENDAR_NAME] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY] TEXTIMAGE_ON [PRIMARY]
GO
/****** Object:  Table [dbo].[QRTZ_CRON_TRIGGERS]    Script Date: 2018/12/5 10:12:31 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[QRTZ_CRON_TRIGGERS](
	[SCHED_NAME] [varchar](120) NOT NULL,
	[TRIGGER_NAME] [varchar](200) NOT NULL,
	[TRIGGER_GROUP] [varchar](200) NOT NULL,
	[CRON_EXPRESSION] [varchar](120) NOT NULL,
	[TIME_ZONE_ID] [varchar](80) NULL,
 CONSTRAINT [PK_QRTZ_CRON_TRIGGERS] PRIMARY KEY CLUSTERED 
(
	[SCHED_NAME] ASC,
	[TRIGGER_NAME] ASC,
	[TRIGGER_GROUP] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[QRTZ_FIRED_TRIGGERS]    Script Date: 2018/12/5 10:12:31 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[QRTZ_FIRED_TRIGGERS](
	[SCHED_NAME] [varchar](120) NOT NULL,
	[ENTRY_ID] [varchar](95) NOT NULL,
	[TRIGGER_NAME] [varchar](200) NOT NULL,
	[TRIGGER_GROUP] [varchar](200) NOT NULL,
	[INSTANCE_NAME] [varchar](200) NOT NULL,
	[FIRED_TIME] [bigint] NOT NULL,
	[SCHED_TIME] [bigint] NOT NULL,
	[PRIORITY] [int] NOT NULL,
	[STATE] [varchar](16) NOT NULL,
	[JOB_NAME] [varchar](200) NULL,
	[JOB_GROUP] [varchar](200) NULL,
	[IS_NONCONCURRENT] [varchar](1) NULL,
	[REQUESTS_RECOVERY] [varchar](1) NULL,
 CONSTRAINT [PK_QRTZ_FIRED_TRIGGERS] PRIMARY KEY CLUSTERED 
(
	[SCHED_NAME] ASC,
	[ENTRY_ID] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[QRTZ_JOB_DETAILS]    Script Date: 2018/12/5 10:12:31 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[QRTZ_JOB_DETAILS](
	[SCHED_NAME] [varchar](120) NOT NULL,
	[JOB_NAME] [varchar](200) NOT NULL,
	[JOB_GROUP] [varchar](200) NOT NULL,
	[DESCRIPTION] [varchar](250) NULL,
	[JOB_CLASS_NAME] [varchar](250) NOT NULL,
	[IS_DURABLE] [varchar](1) NOT NULL,
	[IS_NONCONCURRENT] [varchar](1) NOT NULL,
	[IS_UPDATE_DATA] [varchar](1) NOT NULL,
	[REQUESTS_RECOVERY] [varchar](1) NOT NULL,
	[JOB_DATA] [image] NULL,
 CONSTRAINT [PK_QRTZ_JOB_DETAILS] PRIMARY KEY CLUSTERED 
(
	[SCHED_NAME] ASC,
	[JOB_NAME] ASC,
	[JOB_GROUP] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY] TEXTIMAGE_ON [PRIMARY]
GO
/****** Object:  Table [dbo].[QRTZ_LOCKS]    Script Date: 2018/12/5 10:12:31 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[QRTZ_LOCKS](
	[SCHED_NAME] [varchar](120) NOT NULL,
	[LOCK_NAME] [varchar](40) NOT NULL,
 CONSTRAINT [PK_QRTZ_LOCKS] PRIMARY KEY CLUSTERED 
(
	[SCHED_NAME] ASC,
	[LOCK_NAME] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[QRTZ_PAUSED_TRIGGER_GRPS]    Script Date: 2018/12/5 10:12:31 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[QRTZ_PAUSED_TRIGGER_GRPS](
	[SCHED_NAME] [varchar](120) NOT NULL,
	[TRIGGER_GROUP] [varchar](200) NOT NULL,
 CONSTRAINT [PK_QRTZ_PAUSED_TRIGGER_GRPS] PRIMARY KEY CLUSTERED 
(
	[SCHED_NAME] ASC,
	[TRIGGER_GROUP] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[QRTZ_SCHEDULER_STATE]    Script Date: 2018/12/5 10:12:31 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[QRTZ_SCHEDULER_STATE](
	[SCHED_NAME] [varchar](120) NOT NULL,
	[INSTANCE_NAME] [varchar](200) NOT NULL,
	[LAST_CHECKIN_TIME] [bigint] NOT NULL,
	[CHECKIN_INTERVAL] [bigint] NOT NULL,
 CONSTRAINT [PK_QRTZ_SCHEDULER_STATE] PRIMARY KEY CLUSTERED 
(
	[SCHED_NAME] ASC,
	[INSTANCE_NAME] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[QRTZ_SIMPLE_TRIGGERS]    Script Date: 2018/12/5 10:12:31 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[QRTZ_SIMPLE_TRIGGERS](
	[SCHED_NAME] [varchar](120) NOT NULL,
	[TRIGGER_NAME] [varchar](200) NOT NULL,
	[TRIGGER_GROUP] [varchar](200) NOT NULL,
	[REPEAT_COUNT] [bigint] NOT NULL,
	[REPEAT_INTERVAL] [bigint] NOT NULL,
	[TIMES_TRIGGERED] [bigint] NOT NULL,
 CONSTRAINT [PK_QRTZ_SIMPLE_TRIGGERS] PRIMARY KEY CLUSTERED 
(
	[SCHED_NAME] ASC,
	[TRIGGER_NAME] ASC,
	[TRIGGER_GROUP] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[QRTZ_SIMPROP_TRIGGERS]    Script Date: 2018/12/5 10:12:31 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[QRTZ_SIMPROP_TRIGGERS](
	[SCHED_NAME] [varchar](120) NOT NULL,
	[TRIGGER_NAME] [varchar](200) NOT NULL,
	[TRIGGER_GROUP] [varchar](200) NOT NULL,
	[STR_PROP_1] [varchar](512) NULL,
	[STR_PROP_2] [varchar](512) NULL,
	[STR_PROP_3] [varchar](512) NULL,
	[INT_PROP_1] [int] NULL,
	[INT_PROP_2] [int] NULL,
	[LONG_PROP_1] [bigint] NULL,
	[LONG_PROP_2] [bigint] NULL,
	[DEC_PROP_1] [numeric](13, 4) NULL,
	[DEC_PROP_2] [numeric](13, 4) NULL,
	[BOOL_PROP_1] [varchar](1) NULL,
	[BOOL_PROP_2] [varchar](1) NULL,
 CONSTRAINT [PK_QRTZ_SIMPROP_TRIGGERS] PRIMARY KEY CLUSTERED 
(
	[SCHED_NAME] ASC,
	[TRIGGER_NAME] ASC,
	[TRIGGER_GROUP] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[QRTZ_TRIGGERS]    Script Date: 2018/12/5 10:12:31 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[QRTZ_TRIGGERS](
	[SCHED_NAME] [varchar](120) NOT NULL,
	[TRIGGER_NAME] [varchar](200) NOT NULL,
	[TRIGGER_GROUP] [varchar](200) NOT NULL,
	[JOB_NAME] [varchar](200) NOT NULL,
	[JOB_GROUP] [varchar](200) NOT NULL,
	[DESCRIPTION] [varchar](250) NULL,
	[NEXT_FIRE_TIME] [bigint] NULL,
	[PREV_FIRE_TIME] [bigint] NULL,
	[PRIORITY] [int] NULL,
	[TRIGGER_STATE] [varchar](16) NOT NULL,
	[TRIGGER_TYPE] [varchar](8) NOT NULL,
	[START_TIME] [bigint] NOT NULL,
	[END_TIME] [bigint] NULL,
	[CALENDAR_NAME] [varchar](200) NULL,
	[MISFIRE_INSTR] [smallint] NULL,
	[JOB_DATA] [image] NULL,
 CONSTRAINT [PK_QRTZ_TRIGGERS] PRIMARY KEY CLUSTERED 
(
	[SCHED_NAME] ASC,
	[TRIGGER_NAME] ASC,
	[TRIGGER_GROUP] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY] TEXTIMAGE_ON [PRIMARY]
GO
/****** Object:  Table [dbo].[schedule_job]    Script Date: 2018/12/5 10:12:31 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[schedule_job](
	[job_id] [bigint] IDENTITY(1,1) NOT NULL,
	[bean_name] [varchar](200) NULL,
	[method_name] [varchar](100) NULL,
	[params] [varchar](2000) NULL,
	[cron_expression] [varchar](100) NULL,
	[status] [tinyint] NULL,
	[remark] [varchar](255) NULL,
	[create_time] [datetime] NULL,
PRIMARY KEY CLUSTERED 
(
	[job_id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[schedule_job_log]    Script Date: 2018/12/5 10:12:31 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[schedule_job_log](
	[log_id] [bigint] IDENTITY(1,1) NOT NULL,
	[job_id] [bigint] NOT NULL,
	[bean_name] [varchar](200) NULL,
	[method_name] [varchar](100) NULL,
	[params] [varchar](2000) NULL,
	[status] [tinyint] NOT NULL,
	[error] [varchar](2000) NULL,
	[times] [int] NOT NULL,
	[create_time] [datetime] NULL,
PRIMARY KEY CLUSTERED 
(
	[log_id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[sys_log]    Script Date: 2018/12/5 10:12:31 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[sys_log](
	[id] [bigint] IDENTITY(1,1) NOT NULL,
	[username] [varchar](50) NULL,
	[operation] [varchar](50) NULL,
	[method] [varchar](200) NULL,
	[params] [varchar](5000) NULL,
	[time] [bigint] NOT NULL,
	[ip] [varchar](64) NULL,
	[create_date] [datetime] NULL,
PRIMARY KEY CLUSTERED 
(
	[id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[tb_token]    Script Date: 2018/12/5 10:12:31 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[tb_token](
	[userSeq] [int] NOT NULL,
	[token] [varchar](255) NOT NULL,
	[expireTime] [datetime] NOT NULL,
	[updateTime] [datetime] NULL,
PRIMARY KEY CLUSTERED 
(
	[userSeq] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  View [dbo].[NWGoods_ShoeView]    Script Date: 2018/12/5 10:12:31 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE VIEW [dbo].[NWGoods_ShoeView] AS 
SELECT 
	G.*, P.BrandSeq, 
	SX1.SXName AS SX1Name, SX1.Value AS SX1Value,
	SX2.SXName AS SX2Name, SX2.Value AS SX2Value,
	SX3.SXName AS SX3Name, SX3.Value AS SX3Value,
	SX4.SXName AS SX4Name, SX4.Value AS SX4Value,
	SX5.SXName AS SX5Name, SX5.Value AS SX5Value,
	SX6.SXName AS SX6Name, SX6.Value AS SX6Value,
	SX7.SXName AS SX7Name, SX7.Value AS SX7Value,
	SX8.SXName AS SX8Name, SX8.Value AS SX8Value,
	SX9.SXName AS SX9Name, SX9.Value AS SX9Value,
	SX10.SXName AS SX10Name, SX10.Value AS SX10Value,
	SX11.SXName AS SX11Name, SX11.Value AS SX11Value,
	SX12.SXName AS SX12Name, SX12.Value AS SX12Value,
	SX13.SXName AS SX13Name, SX13.Value AS SX13Value,
	SX14.SXName AS SX14Name, SX14.Value AS SX14Value,
	SX15.SXName AS SX15Name, SX15.Value AS SX15Value,
	SX16.SXName AS SX16Name, SX16.Value AS SX16Value,
	SX17.SXName AS SX17Name, SX17.Value AS SX17Value,
	SX18.SXName AS SX18Name, SX18.Value AS SX18Value,
	SX19.SXName AS SX19Name, SX19.Value AS SX19Value,
	SX20.SXName AS SX20Name, SX20.Value AS SX20Value
FROM NWGoods_Shoe G 
LEFT JOIN NWGoods_Period P ON G.PeriodSeq = P.Seq 

LEFT JOIN (
		SELECT 
			A.BrandSeq, A.SXID, A.SXName, B.Code, B.Value
		FROM 
			[dbo].[NWGoods_SX] A 
		LEFT JOIN [dbo].[NWGoods_SXOption] B ON A.Seq = B.SXSeq AND B.Del = 0 
		WHERE 
			A.Del = 0 AND A.SXID = 'SX1'
) SX1 ON P.BrandSeq = SX1.BrandSeq AND G.SX1 = SX1.Code

LEFT JOIN (
		SELECT 
			A.BrandSeq, A.SXID, A.SXName, B.Code, B.Value
		FROM 
			[dbo].[NWGoods_SX] A 
		LEFT JOIN [dbo].[NWGoods_SXOption] B ON A.Seq = B.SXSeq AND B.Del = 0 
		WHERE 
			A.Del = 0 AND A.SXID = 'SX2'
) SX2 ON P.BrandSeq = SX2.BrandSeq AND G.SX2 = SX2.Code

LEFT JOIN (
		SELECT 
			A.BrandSeq, A.SXID, A.SXName, B.Code, B.Value
		FROM 
			[dbo].[NWGoods_SX] A 
		LEFT JOIN [dbo].[NWGoods_SXOption] B ON A.Seq = B.SXSeq AND B.Del = 0 
		WHERE 
			A.Del = 0 AND A.SXID = 'SX3'
) SX3 ON P.BrandSeq = SX3.BrandSeq AND G.SX3 = SX3.Code

LEFT JOIN (
		SELECT 
			A.BrandSeq, A.SXID, A.SXName, B.Code, B.Value
		FROM 
			[dbo].[NWGoods_SX] A 
		LEFT JOIN [dbo].[NWGoods_SXOption] B ON A.Seq = B.SXSeq AND B.Del = 0 
		WHERE 
			A.Del = 0 AND A.SXID = 'SX4'
) SX4 ON P.BrandSeq = SX4.BrandSeq AND G.SX4 = SX4.Code

LEFT JOIN (
		SELECT 
			A.BrandSeq, A.SXID, A.SXName, B.Code, B.Value
		FROM 
			[dbo].[NWGoods_SX] A 
		LEFT JOIN [dbo].[NWGoods_SXOption] B ON A.Seq = B.SXSeq AND B.Del = 0 
		WHERE 
			A.Del = 0 AND A.SXID = 'SX5'
) SX5 ON P.BrandSeq = SX5.BrandSeq AND G.SX5 = SX5.Code

LEFT JOIN (
		SELECT 
			A.BrandSeq, A.SXID, A.SXName, B.Code, B.Value
		FROM 
			[dbo].[NWGoods_SX] A 
		LEFT JOIN [dbo].[NWGoods_SXOption] B ON A.Seq = B.SXSeq AND B.Del = 0 
		WHERE 
			A.Del = 0 AND A.SXID = 'SX6'
) SX6 ON P.BrandSeq = SX6.BrandSeq AND G.SX6 = SX6.Code

LEFT JOIN (
		SELECT 
			A.BrandSeq, A.SXID, A.SXName, B.Code, B.Value
		FROM 
			[dbo].[NWGoods_SX] A 
		LEFT JOIN [dbo].[NWGoods_SXOption] B ON A.Seq = B.SXSeq AND B.Del = 0 
		WHERE 
			A.Del = 0 AND A.SXID = 'SX7'
) SX7 ON P.BrandSeq = SX7.BrandSeq AND G.SX7 = SX7.Code

LEFT JOIN (
		SELECT 
			A.BrandSeq, A.SXID, A.SXName, B.Code, B.Value
		FROM 
			[dbo].[NWGoods_SX] A 
		LEFT JOIN [dbo].[NWGoods_SXOption] B ON A.Seq = B.SXSeq AND B.Del = 0 
		WHERE 
			A.Del = 0 AND A.SXID = 'SX8'
) SX8 ON P.BrandSeq = SX8.BrandSeq AND G.SX8 = SX8.Code

LEFT JOIN (
		SELECT 
			A.BrandSeq, A.SXID, A.SXName, B.Code, B.Value
		FROM 
			[dbo].[NWGoods_SX] A 
		LEFT JOIN [dbo].[NWGoods_SXOption] B ON A.Seq = B.SXSeq AND B.Del = 0 
		WHERE 
			A.Del = 0 AND A.SXID = 'SX9'
) SX9 ON P.BrandSeq = SX9.BrandSeq AND G.SX9 = SX9.Code

LEFT JOIN (
		SELECT 
			A.BrandSeq, A.SXID, A.SXName, B.Code, B.Value
		FROM 
			[dbo].[NWGoods_SX] A 
		LEFT JOIN [dbo].[NWGoods_SXOption] B ON A.Seq = B.SXSeq AND B.Del = 0 
		WHERE 
			A.Del = 0 AND A.SXID = 'SX10'
) SX10 ON P.BrandSeq = SX10.BrandSeq AND G.SX10 = SX10.Code

LEFT JOIN (
		SELECT 
			A.BrandSeq, A.SXID, A.SXName, B.Code, B.Value
		FROM 
			[dbo].[NWGoods_SX] A 
		LEFT JOIN [dbo].[NWGoods_SXOption] B ON A.Seq = B.SXSeq AND B.Del = 0 
		WHERE 
			A.Del = 0 AND A.SXID = 'SX11'
) SX11 ON P.BrandSeq = SX11.BrandSeq AND G.SX11 = SX11.Code

LEFT JOIN (
		SELECT 
			A.BrandSeq, A.SXID, A.SXName, B.Code, B.Value
		FROM 
			[dbo].[NWGoods_SX] A 
		LEFT JOIN [dbo].[NWGoods_SXOption] B ON A.Seq = B.SXSeq AND B.Del = 0 
		WHERE 
			A.Del = 0 AND A.SXID = 'SX12'
) SX12 ON P.BrandSeq = SX12.BrandSeq AND G.SX12 = SX12.Code

LEFT JOIN (
		SELECT 
			A.BrandSeq, A.SXID, A.SXName, B.Code, B.Value
		FROM 
			[dbo].[NWGoods_SX] A 
		LEFT JOIN [dbo].[NWGoods_SXOption] B ON A.Seq = B.SXSeq AND B.Del = 0 
		WHERE 
			A.Del = 0 AND A.SXID = 'SX13'
) SX13 ON P.BrandSeq = SX13.BrandSeq AND G.SX13 = SX13.Code

LEFT JOIN (
		SELECT 
			A.BrandSeq, A.SXID, A.SXName, B.Code, B.Value
		FROM 
			[dbo].[NWGoods_SX] A 
		LEFT JOIN [dbo].[NWGoods_SXOption] B ON A.Seq = B.SXSeq AND B.Del = 0 
		WHERE 
			A.Del = 0 AND A.SXID = 'SX14'
) SX14 ON P.BrandSeq = SX14.BrandSeq AND G.SX14 = SX14.Code

LEFT JOIN (
		SELECT 
			A.BrandSeq, A.SXID, A.SXName, B.Code, B.Value
		FROM 
			[dbo].[NWGoods_SX] A 
		LEFT JOIN [dbo].[NWGoods_SXOption] B ON A.Seq = B.SXSeq AND B.Del = 0 
		WHERE 
			A.Del = 0 AND A.SXID = 'SX15'
) SX15 ON P.BrandSeq = SX15.BrandSeq AND G.SX15 = SX15.Code

LEFT JOIN (
		SELECT 
			A.BrandSeq, A.SXID, A.SXName, B.Code, B.Value
		FROM 
			[dbo].[NWGoods_SX] A 
		LEFT JOIN [dbo].[NWGoods_SXOption] B ON A.Seq = B.SXSeq AND B.Del = 0 
		WHERE 
			A.Del = 0 AND A.SXID = 'SX16'
) SX16 ON P.BrandSeq = SX16.BrandSeq AND G.SX16 = SX16.Code

LEFT JOIN (
		SELECT 
			A.BrandSeq, A.SXID, A.SXName, B.Code, B.Value
		FROM 
			[dbo].[NWGoods_SX] A 
		LEFT JOIN [dbo].[NWGoods_SXOption] B ON A.Seq = B.SXSeq AND B.Del = 0 
		WHERE 
			A.Del = 0 AND A.SXID = 'SX17'
) SX17 ON P.BrandSeq = SX17.BrandSeq AND G.SX17 = SX17.Code

LEFT JOIN (
		SELECT 
			A.BrandSeq, A.SXID, A.SXName, B.Code, B.Value
		FROM 
			[dbo].[NWGoods_SX] A 
		LEFT JOIN [dbo].[NWGoods_SXOption] B ON A.Seq = B.SXSeq AND B.Del = 0 
		WHERE 
			A.Del = 0 AND A.SXID = 'SX18'
) SX18 ON P.BrandSeq = SX18.BrandSeq AND G.SX18 = SX18.Code

LEFT JOIN (
		SELECT 
			A.BrandSeq, A.SXID, A.SXName, B.Code, B.Value
		FROM 
			[dbo].[NWGoods_SX] A 
		LEFT JOIN [dbo].[NWGoods_SXOption] B ON A.Seq = B.SXSeq AND B.Del = 0 
		WHERE 
			A.Del = 0 AND A.SXID = 'SX19'
) SX19 ON P.BrandSeq = SX19.BrandSeq AND G.SX19 = SX19.Code

LEFT JOIN (
		SELECT 
			A.BrandSeq, A.SXID, A.SXName, B.Code, B.Value
		FROM 
			[dbo].[NWGoods_SX] A 
		LEFT JOIN [dbo].[NWGoods_SXOption] B ON A.Seq = B.SXSeq AND B.Del = 0 
		WHERE 
			A.Del = 0 AND A.SXID = 'SX20'
) SX20 ON P.BrandSeq = SX20.BrandSeq AND G.SX20 = SX20.Code
GO
INSERT [dbo].[NWBase_Brand] ([Seq], [IdentifyCode], [InnerKey], [Prefix], [BrandName], [CompanySeq], [QRCodeName], [InputTime], [Del]) VALUES (1, N'YB001', N'560B2A5A-2F8E-4DE5-B2A3-90DFAAB8BC5E', N'yb', N'伊伴', 1, NULL, CAST(N'2018-11-26T11:11:19.670' AS DateTime), 0)
INSERT [dbo].[NWBase_UserBrand] ([Seq], [UserSeq], [BrandSeq], [InputTime], [Del]) VALUES (1, 1, 1, CAST(N'2018-11-26T11:11:19.670' AS DateTime), 0)
INSERT [dbo].[NWBase_UserBrand] ([Seq], [UserSeq], [BrandSeq], [InputTime], [Del]) VALUES (2, 2, 1, CAST(N'2018-11-26T11:11:19.670' AS DateTime), 0)
INSERT [dbo].[NWUser_Role] ([Seq], [RoleName], [InputTime], [Del]) VALUES (-1, N'超级管理员', CAST(N'2018-11-26T11:11:19.670' AS DateTime), 0)
INSERT [dbo].[NWUser_Role] ([Seq], [RoleName], [InputTime], [Del]) VALUES (0, N'后台管理员', CAST(N'2018-11-26T11:11:19.670' AS DateTime), 0)
INSERT [dbo].[NWUser_Role] ([Seq], [RoleName], [InputTime], [Del]) VALUES (1, N'全国用户角色', CAST(N'2018-11-26T11:11:19.670' AS DateTime), 0)
INSERT [dbo].[NWUser_Role] ([Seq], [RoleName], [InputTime], [Del]) VALUES (2, N'大区角色', CAST(N'2018-11-26T11:11:19.670' AS DateTime), 0)
INSERT [dbo].[NWUser_Role] ([Seq], [RoleName], [InputTime], [Del]) VALUES (3, N'分公司角色', CAST(N'2018-11-26T11:11:19.670' AS DateTime), 0)
INSERT [dbo].[NWUser_Role] ([Seq], [RoleName], [InputTime], [Del]) VALUES (4, N'门店角色', CAST(N'2018-11-26T11:11:19.670' AS DateTime), 0)
INSERT [dbo].[NWUser_User] ([Seq], [UserName], [Password], [RoleSeq], [UserAreaSeq], [CreateUserSeq], [RealName], [Telephone], [HeadImg], [IsUseful], [InputTime], [Del]) VALUES (-1, N'admin', N'a665a45920422f9d417e4867efdc4fb8a04a1f3fff1fa07e998e86f7f7a27ae3', -1, NULL, NULL, N'超级管理员', NULL, NULL, 1, CAST(N'2018-11-26T11:11:19.670' AS DateTime), 0)
INSERT [dbo].[NWUser_User] ([Seq], [UserName], [Password], [RoleSeq], [UserAreaSeq], [CreateUserSeq], [RealName], [Telephone], [HeadImg], [IsUseful], [InputTime], [Del]) VALUES (1, N'ybadmin', N'a665a45920422f9d417e4867efdc4fb8a04a1f3fff1fa07e998e86f7f7a27ae3', 0, NULL, -1, N'伊伴后台管理员', N'1234', N'1_1543110627052_eg_tulip.jpg', 1, CAST(N'2018-11-26T11:11:19.670' AS DateTime), 0)
INSERT [dbo].[NWUser_User] ([Seq], [UserName], [Password], [RoleSeq], [UserAreaSeq], [CreateUserSeq], [RealName], [Telephone], [HeadImg], [IsUseful], [InputTime], [Del]) VALUES (2, N'ybquanguo', N'a665a45920422f9d417e4867efdc4fb8a04a1f3fff1fa07e998e86f7f7a27ae3', 1, NULL, NULL, N'伊伴全国', NULL, NULL, 1, CAST(N'2018-11-26T11:11:19.670' AS DateTime), 0)
GO
ALTER TABLE [dbo].[NWBase_Brand] ADD  DEFAULT (getdate()) FOR [InputTime]
GO
ALTER TABLE [dbo].[NWBase_Company] ADD  DEFAULT (getdate()) FOR [InputTime]
GO
ALTER TABLE [dbo].[NWBase_HardwareParameterSet] ADD  CONSTRAINT [DF_NWBase_HardwareParameterSet_UseAntennaPort]  DEFAULT ((1)) FOR [UseAntennaPort]
GO
ALTER TABLE [dbo].[NWBase_HardwareParameterSet] ADD  CONSTRAINT [DF_NWBase_HardwareParameterSet_IP]  DEFAULT ('192.168.0.178') FOR [IP]
GO
ALTER TABLE [dbo].[NWBase_HardwareParameterSet] ADD  CONSTRAINT [DF_NWBase_HardwareParameterSet_Port]  DEFAULT ((4001)) FOR [Port]
GO
ALTER TABLE [dbo].[NWBase_HardwareParameterSet] ADD  CONSTRAINT [DF_NWBase_HardwareParameterSet_OutPutPower]  DEFAULT ((30)) FOR [OutPutPower]
GO
ALTER TABLE [dbo].[NWBase_HardwareParameterSet] ADD  CONSTRAINT [DF_NWBase_HardwareParameterSet_RFIDEffectTime]  DEFAULT ((3)) FOR [RFIDEffectTime]
GO
ALTER TABLE [dbo].[NWBase_HardwareParameterSet] ADD  CONSTRAINT [DF_NWBase_HardwareParameterSet_BleeperMode]  DEFAULT ((1)) FOR [BleeperMode]
GO
ALTER TABLE [dbo].[NWBase_HardwareParameterSet] ADD  CONSTRAINT [DF_NWBase_HardwareParameterSet_ProFileMode]  DEFAULT ((2)) FOR [ProFileMode]
GO
ALTER TABLE [dbo].[NWBase_HardwareParameterSet] ADD  CONSTRAINT [DF_NWBase_HardwareParameterSet_ReturnLoss]  DEFAULT ((10)) FOR [ReturnLoss]
GO
ALTER TABLE [dbo].[NWBase_UserBrand] ADD  DEFAULT (getdate()) FOR [InputTime]
GO
ALTER TABLE [dbo].[NWGoods_Category] ADD  CONSTRAINT [DF_NWGoods_Category_Visible]  DEFAULT ((0)) FOR [Visible]
GO
ALTER TABLE [dbo].[NWGoods_Size] ADD  DEFAULT (getdate()) FOR [InputTime]
GO
ALTER TABLE [dbo].[NWUser_User] ADD  CONSTRAINT [DF__NWUser_Us__Input__4A8310C6]  DEFAULT (getdate()) FOR [InputTime]
GO
ALTER TABLE [dbo].[QRTZ_CRON_TRIGGERS]  WITH CHECK ADD  CONSTRAINT [FK_QRTZ_CRON_TRIGGERS_QRTZ_TRIGGERS] FOREIGN KEY([SCHED_NAME], [TRIGGER_NAME], [TRIGGER_GROUP])
REFERENCES [dbo].[QRTZ_TRIGGERS] ([SCHED_NAME], [TRIGGER_NAME], [TRIGGER_GROUP])
ON DELETE CASCADE
GO
ALTER TABLE [dbo].[QRTZ_CRON_TRIGGERS] CHECK CONSTRAINT [FK_QRTZ_CRON_TRIGGERS_QRTZ_TRIGGERS]
GO
ALTER TABLE [dbo].[QRTZ_SIMPLE_TRIGGERS]  WITH CHECK ADD  CONSTRAINT [FK_QRTZ_SIMPLE_TRIGGERS_QRTZ_TRIGGERS] FOREIGN KEY([SCHED_NAME], [TRIGGER_NAME], [TRIGGER_GROUP])
REFERENCES [dbo].[QRTZ_TRIGGERS] ([SCHED_NAME], [TRIGGER_NAME], [TRIGGER_GROUP])
ON DELETE CASCADE
GO
ALTER TABLE [dbo].[QRTZ_SIMPLE_TRIGGERS] CHECK CONSTRAINT [FK_QRTZ_SIMPLE_TRIGGERS_QRTZ_TRIGGERS]
GO
ALTER TABLE [dbo].[QRTZ_SIMPROP_TRIGGERS]  WITH CHECK ADD  CONSTRAINT [FK_QRTZ_SIMPROP_TRIGGERS_QRTZ_TRIGGERS] FOREIGN KEY([SCHED_NAME], [TRIGGER_NAME], [TRIGGER_GROUP])
REFERENCES [dbo].[QRTZ_TRIGGERS] ([SCHED_NAME], [TRIGGER_NAME], [TRIGGER_GROUP])
ON DELETE CASCADE
GO
ALTER TABLE [dbo].[QRTZ_SIMPROP_TRIGGERS] CHECK CONSTRAINT [FK_QRTZ_SIMPROP_TRIGGERS_QRTZ_TRIGGERS]
GO
ALTER TABLE [dbo].[QRTZ_TRIGGERS]  WITH CHECK ADD  CONSTRAINT [FK_QRTZ_TRIGGERS_QRTZ_JOB_DETAILS] FOREIGN KEY([SCHED_NAME], [JOB_NAME], [JOB_GROUP])
REFERENCES [dbo].[QRTZ_JOB_DETAILS] ([SCHED_NAME], [JOB_NAME], [JOB_GROUP])
GO
ALTER TABLE [dbo].[QRTZ_TRIGGERS] CHECK CONSTRAINT [FK_QRTZ_TRIGGERS_QRTZ_JOB_DETAILS]
GO
/****** Object:  StoredProcedure [dbo].[insertGoodsMaterial]    Script Date: 2018/12/5 10:12:31 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE PROCEDURE [dbo].[insertGoodsMaterial]
AS
BEGIN
	TRUNCATE TABLE YHSmartDataRetail.dbo.NWGoods_Material;

	INSERT INTO YHSmartDataRetail.dbo.NWGoods_Material
	(
		Seq,
		PeriodSeq,
		MaterialName,
		MaterialFileName,
		MaterialType,
		UsefulDate,
		ShoeID,
		MD5Code,
		RelativeURL,
		InputTime,
		Del
	)
	(SELECT
		A.SEQ,
		A.SHOESPERIOD_SEQ,
		A.MATERIALNAME,
		A.MATERIALFILENAME,
		A.MATERIALTYPE,
		A.USEFULDATE,
		B.GOODID,
		A.MD5Code,
		A.RelativeURL,
		A.INPUTDATE,
		A.DEL
		 FROM [TRYSHOES_OLD].tryshoes.dbo.NWBASE_MATERIALBASEINFO A
			LEFT JOIN [TRYSHOES_OLD].tryshoes.dbo.NWBASE_SHOESBASEINFO B ON A.ShoesSEQ = B.SEQ
	);

END
GO
/****** Object:  StoredProcedure [dbo].[insertSaleShoesDetail]    Script Date: 2018/12/5 10:12:31 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE PROCEDURE [dbo].[insertSaleShoesDetail]
AS
BEGIN
	TRUNCATE TABLE YHSmartDataRetail.dbo.NWGoods_SaleShoesDetail;

	INSERT INTO YHSmartDataRetail.dbo.NWGoods_SaleShoesDetail
	(
		Seq,
		PeriodSeq,
		AreaSeq,
		BranchOfficeSeq,
		ShopSeq,
		SaleDate,
		ShoeID,
		OrderCount,
		SaleCount,
		Cost,
		TagPrice,
		RealPrice,
		InputTime,
		Del
	)
	(SELECT
		A.SEQ,
		A.periodSeq,
		A.areaSeq,
		A.branchOfficeSeq,
		A.shopSeq,
		A.saleDate,
		B.GOODID,
		A.orderCount,
		A.saleCount,
		A.cost,
		A.tagPrice,
		A.realPrice,
		A.inputDate,
		A.del
		 FROM [TRYSHOES_OLD].tryshoesAnalysis.dbo.NWPRODUCT_SaleDetail A
			LEFT JOIN [TRYSHOES_OLD].tryshoes.dbo.NWBASE_SHOESBASEINFO B ON A.goodSeq = B.SEQ
		    WHERE A.periodSeq IN (SELECT Seq FROM YHSmartDataRetail.dbo.NWGoods_Period)
	);

END
GO
/****** Object:  StoredProcedure [dbo].[insertShoeRFID]    Script Date: 2018/12/5 10:12:31 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE PROCEDURE [dbo].[insertShoeRFID]
AS
BEGIN
	TRUNCATE TABLE YHSmartDataRetail.dbo.NWGoods_ShoeRFID;

	INSERT INTO YHSmartDataRetail.dbo.NWGoods_ShoeRFID
	(
		Seq,
		ShoeID,
		RFIDCode,
		InputTime,
		Del
	)
	(SELECT
		A.SEQ,
		B.GOODID,
		A.RFIDCODE,
		A.INPUTDATE,
		A.DEL
		 FROM [TRYSHOES_OLD].tryshoes.dbo.NWBASE_SHOESRFIDCODERELATION A
			LEFT JOIN [TRYSHOES_OLD].tryshoes.dbo.NWBASE_SHOESBASEINFO B ON A.SHOESSEQ = B.SEQ
	);

END
GO
/****** Object:  StoredProcedure [dbo].[insertTryShoesDetail]    Script Date: 2018/12/5 10:12:31 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE PROCEDURE [dbo].[insertTryShoesDetail]
AS
BEGIN
	TRUNCATE TABLE YHSmartDataRetail.dbo.NWGoods_TryShoesDetail;

	INSERT INTO YHSmartDataRetail.dbo.NWGoods_TryShoesDetail
	(
		Seq,
		ShoeID,
		TryCount,
		TryTimes,
		ShopSeq,
		DataTime,
		InputTime,
		Del
	)
	(SELECT
		A.SEQ,
		B.GOODID,
		A.TryCount,
		A.TryTimes,
		A.FK_SHOPBASEINFO_SEQ,
		A.DataTime,
		A.InputTime,
		A.DEL
		 FROM [TRYSHOES_OLD].tryshoesAnalysis.dbo.NWPRODUCT_PRODUCTSTATISTICS A
			LEFT JOIN [TRYSHOES_OLD].tryshoes.dbo.NWBASE_SHOESBASEINFO B ON A.FK_ShoesBaseInfo_seq = B.SEQ
		 --WHERE A.DATATIME >= '2018-11-15 00:00:00'
	);

END
GO
/****** Object:  StoredProcedure [dbo].[updateGoodsMaterial]    Script Date: 2018/12/5 10:12:31 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE PROCEDURE [dbo].[updateGoodsMaterial]
@lastUpdateTime datetime
AS

DECLARE
@seq INT, @InputTime datetime;

BEGIN

-- 定义游标. （数据为旧表中所有更新时间大于新表最大更新时间的数据的List）
DECLARE test_cursor CURSOR FAST_FORWARD FOR 
	SELECT SEQ,INPUTDATE FROM [TRYSHOES_OLD].tryshoes.dbo.NWBASE_MATERIALBASEINFO WHERE INPUTDATE > @lastUpdateTime;

-- 打开游标.
OPEN test_cursor;

	WHILE 1=1
	BEGIN
		-- 填充数据.
		FETCH NEXT FROM test_cursor INTO @seq, @InputTime;
		-- 假如未检索到数据，退出循环.
		IF @@fetch_status != 0 BREAK;
			PRINT @seq;

				--删除可能已存在的数据
				DELETE FROM YHSmartDataRetail.[dbo].[NWGoods_Material] WHERE Seq = @seq;

				--插入数据
				INSERT INTO YHSmartDataRetail.dbo.NWGoods_Material
				(
					Seq,
					PeriodSeq,
					MaterialName,
					MaterialFileName,
					MaterialType,
					UsefulDate,
					ShoeID,
					MD5Code,
					RelativeURL,
					InputTime,
					Del
				)
				(SELECT
					A.SEQ,
					A.SHOESPERIOD_SEQ,
					A.MATERIALNAME,
					A.MATERIALFILENAME,
					A.MATERIALTYPE,
					A.USEFULDATE,
					B.GOODID,
					A.MD5Code,
					A.RelativeURL,
					A.INPUTDATE,
					A.DEL
					 FROM [TRYSHOES_OLD].tryshoes.dbo.NWBASE_MATERIALBASEINFO A
						LEFT JOIN [TRYSHOES_OLD].tryshoes.dbo.NWBASE_SHOESBASEINFO B ON A.ShoesSEQ = B.SEQ
					 WHERE A.SEQ = @seq
				);


	END;

-- 关闭游标
CLOSE test_cursor;

-- 释放游标.
DEALLOCATE test_cursor;

END
GO
/****** Object:  StoredProcedure [dbo].[updateShoeRFID]    Script Date: 2018/12/5 10:12:31 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE PROCEDURE [dbo].[updateShoeRFID]
@lastUpdateTime datetime
AS

DECLARE
@seq INT, @InputTime datetime;

BEGIN

-- 定义游标. （数据为旧表中所有更新时间大于新表最大更新时间的数据的List）
DECLARE test_cursor CURSOR FAST_FORWARD FOR 
	SELECT SEQ,INPUTDATE FROM [TRYSHOES_OLD].tryshoes.dbo.NWBASE_SHOESRFIDCODERELATION WHERE INPUTDATE > @lastUpdateTime;

-- 打开游标.
OPEN test_cursor;

	WHILE 1=1
	BEGIN
		-- 填充数据.
		FETCH NEXT FROM test_cursor INTO @seq, @InputTime;
		-- 假如未检索到数据，退出循环.
		IF @@fetch_status != 0 BREAK;
			PRINT @seq;

				--删除可能已存在的数据
				DELETE FROM YHSmartDataRetail.[dbo].[NWGoods_ShoeRFID] WHERE Seq = @seq;

				--插入数据
				INSERT INTO YHSmartDataRetail.dbo.NWGoods_ShoeRFID
				(
					Seq,
					ShoeID,
					RFIDCode,
					InputTime,
					Del
				)
				(SELECT
					A.SEQ,
					B.GOODID,
					A.RFIDCODE,
					A.INPUTDATE,
					A.DEL
					 FROM [TRYSHOES_OLD].tryshoes.dbo.NWBASE_SHOESRFIDCODERELATION A
						LEFT JOIN [TRYSHOES_OLD].tryshoes.dbo.NWBASE_SHOESBASEINFO B ON A.SHOESSEQ = B.SEQ
					 WHERE A.SEQ = @seq
				);



	END;

-- 关闭游标
CLOSE test_cursor;

-- 释放游标.
DEALLOCATE test_cursor;

END
GO
/****** Object:  StoredProcedure [dbo].[updateTryShoesDetail]    Script Date: 2018/12/5 10:12:31 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE PROCEDURE [dbo].[updateTryShoesDetail]
@lastUpdateTime datetime
AS

DECLARE
@seq INT, @InputTime datetime;

BEGIN

-- 定义游标. （数据为旧表中所有更新时间大于新表最大更新时间的数据的List）
DECLARE updatetryshoes_cursor CURSOR FAST_FORWARD FOR
	SELECT A.SEQ, A.InputTime FROM [TRYSHOES_OLD].tryshoesAnalysis.dbo.NWPRODUCT_PRODUCTSTATISTICS A
						LEFT JOIN [TRYSHOES_OLD].tryshoes.dbo.NWBASE_SHOESBASEINFO B ON A.FK_ShoesBaseInfo_seq = B.SEQ
					WHERE A.InputTime > @lastUpdateTime
					  AND B.SHOESPERIOD_SEQ IN (SELECT Seq FROM YHSmartDataRetail.dbo.NWGoods_Period)

-- 打开游标.
OPEN updatetryshoes_cursor;

	WHILE 1=1
	BEGIN
		-- 填充数据.
		FETCH NEXT FROM updatetryshoes_cursor INTO @seq, @InputTime;
		-- 假如未检索到数据，退出循环.
		IF @@fetch_status != 0 BREAK;
			PRINT @seq;
			/**--主要业务逻辑：查询判断数据在新表是否已存在，如不存在则插入，存在则更新
			declare @newSeq INT = NULL  --新表中的seq
			SELECT @newSeq = Seq FROM YHSmartDataRetail.dbo.NWGoods_TryShoesDetail WHERE Seq = @seq;
			PRINT @newSeq;
			-- 不存在，插入
			IF @newSeq IS NULL
			begin
				PRINT '插入：' + convert(VARCHAR, @seq);
			end
			-- 已存在，更新
			else
			begin
				PRINT '更新：' + convert(VARCHAR, @seq);
			end**/

				--删除可能已存在的数据
				DELETE FROM YHSmartDataRetail.[dbo].[NWGoods_TryShoesDetail] WHERE Seq = @seq;

				--插入数据
				INSERT INTO YHSmartDataRetail.dbo.NWGoods_TryShoesDetail
				(
					Seq,
					ShoeID,
					TryCount,
					TryTimes,
					ShopSeq,
					DataTime,
					InputTime,
					Del
				)
				(SELECT
					A.SEQ,
					B.GOODID,
					A.TryCount,
					A.TryTimes,
					A.FK_SHOPBASEINFO_SEQ,
					A.DataTime,
					A.InputTime,
					A.DEL
					 FROM [TRYSHOES_OLD].tryshoesAnalysis.dbo.NWPRODUCT_PRODUCTSTATISTICS A
						LEFT JOIN [TRYSHOES_OLD].tryshoes.dbo.NWBASE_SHOESBASEINFO B ON A.FK_ShoesBaseInfo_seq = B.SEQ
					 WHERE A.SEQ = @seq
				);


	END;

-- 关闭游标
CLOSE updatetryshoes_cursor;

-- 释放游标.
DEALLOCATE updatetryshoes_cursor;

END;
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'序号(主键)' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'NWBase_Animation', @level2type=N'COLUMN',@level2name=N'Seq'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'动画名称' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'NWBase_Animation', @level2type=N'COLUMN',@level2name=N'Name'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'入库时间' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'NWBase_Animation', @level2type=N'COLUMN',@level2name=N'InputTime'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'
删除标识( 0 : 未删除、  1 : 删除 )' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'NWBase_Animation', @level2type=N'COLUMN',@level2name=N'Del'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'序号(主键)' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'NWBase_Area', @level2type=N'COLUMN',@level2name=N'Seq'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'区域父节点编号( 父节点为 0 的是大区 )' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'NWBase_Area', @level2type=N'COLUMN',@level2name=N'ParentSeq'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'品牌' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'NWBase_Area', @level2type=N'COLUMN',@level2name=N'BrandSeq'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'区域名称' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'NWBase_Area', @level2type=N'COLUMN',@level2name=N'AreaName'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'区域的地理范围' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'NWBase_Area', @level2type=N'COLUMN',@level2name=N'Bound'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'插入时间' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'NWBase_Area', @level2type=N'COLUMN',@level2name=N'InputTime'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'删除标识( 0 : 未删除、  1 : 删除 )' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'NWBase_Area', @level2type=N'COLUMN',@level2name=N'Del'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'序号(主键)' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'NWBase_Brand', @level2type=N'COLUMN',@level2name=N'Seq'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'前台系统识别码' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'NWBase_Brand', @level2type=N'COLUMN',@level2name=N'IdentifyCode'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'内部编码' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'NWBase_Brand', @level2type=N'COLUMN',@level2name=N'InnerKey'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'用户名前缀' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'NWBase_Brand', @level2type=N'COLUMN',@level2name=N'Prefix'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'品牌名' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'NWBase_Brand', @level2type=N'COLUMN',@level2name=N'BrandName'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'所属公司序号' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'NWBase_Brand', @level2type=N'COLUMN',@level2name=N'CompanySeq'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'二维码（吸粉）' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'NWBase_Brand', @level2type=N'COLUMN',@level2name=N'QRCodeName'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'插入时间' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'NWBase_Brand', @level2type=N'COLUMN',@level2name=N'InputTime'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'删除标识( 0 : 未删除、  1 : 删除 )' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'NWBase_Brand', @level2type=N'COLUMN',@level2name=N'Del'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'序号(主键)' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'NWBase_Company', @level2type=N'COLUMN',@level2name=N'Seq'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'内部编码' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'NWBase_Company', @level2type=N'COLUMN',@level2name=N'InnerKey'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'公司集团名字' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'NWBase_Company', @level2type=N'COLUMN',@level2name=N'Name'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'插入时间' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'NWBase_Company', @level2type=N'COLUMN',@level2name=N'InputTime'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'删除标识( 0 : 未删除、  1 : 删除 )' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'NWBase_Company', @level2type=N'COLUMN',@level2name=N'Del'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'序号(主键)' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'NWBase_DefaultPlayList', @level2type=N'COLUMN',@level2name=N'Seq'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'品牌序号' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'NWBase_DefaultPlayList', @level2type=N'COLUMN',@level2name=N'BrandSeq'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'名称' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'NWBase_DefaultPlayList', @level2type=N'COLUMN',@level2name=N'Name'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'文件名称(带文件后缀) 下载时前缀发布地址+RelativeURL+本字段合并' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'NWBase_DefaultPlayList', @level2type=N'COLUMN',@level2name=N'FileName'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'选中标识（0 : 未选中， 1 : 选中）' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'NWBase_DefaultPlayList', @level2type=N'COLUMN',@level2name=N'IsSelect'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'文件的MD5  检查文件的一致性
' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'NWBase_DefaultPlayList', @level2type=N'COLUMN',@level2name=N'MD5Code'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'相对的URL地址用语下载文件' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'NWBase_DefaultPlayList', @level2type=N'COLUMN',@level2name=N'RelativeURL'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'0:音频 1：视频' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'NWBase_DefaultPlayList', @level2type=N'COLUMN',@level2name=N'Flag'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'创建时间' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'NWBase_DefaultPlayList', @level2type=N'COLUMN',@level2name=N'InputTime'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'删除标识( 0 : 未删除、  1 : 删除 )' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'NWBase_DefaultPlayList', @level2type=N'COLUMN',@level2name=N'Del'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'序号(主键)' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'NWBase_HardwareParameterSet', @level2type=N'COLUMN',@level2name=N'Seq'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'门店序号' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'NWBase_HardwareParameterSet', @level2type=N'COLUMN',@level2name=N'ShopSeq'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'硬件编号' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'NWBase_HardwareParameterSet', @level2type=N'COLUMN',@level2name=N'HardwareID'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'硬件名称' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'NWBase_HardwareParameterSet', @level2type=N'COLUMN',@level2name=N'Name'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'使用天线口标识，用逗号做标识，如果1和3天线口可用，则值为: "1,3"' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'NWBase_HardwareParameterSet', @level2type=N'COLUMN',@level2name=N'UseAntennaPort'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'读取方式, 默认为0 ( 0 : 服务器端、1 : 客户端 )' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'NWBase_HardwareParameterSet', @level2type=N'COLUMN',@level2name=N'ReaderType'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'IP地址' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'NWBase_HardwareParameterSet', @level2type=N'COLUMN',@level2name=N'IP'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'端口号' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'NWBase_HardwareParameterSet', @level2type=N'COLUMN',@level2name=N'Port'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'射频输出功率功率2-255（默认30）' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'NWBase_HardwareParameterSet', @level2type=N'COLUMN',@level2name=N'OutPutPower'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'RFID有效时长（秒）' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'NWBase_HardwareParameterSet', @level2type=N'COLUMN',@level2name=N'RFIDEffectTime'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'频率最小值' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'NWBase_HardwareParameterSet', @level2type=N'COLUMN',@level2name=N'Minimumfrequency'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'频率最大值' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'NWBase_HardwareParameterSet', @level2type=N'COLUMN',@level2name=N'Maxmumfrequency'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'频率间隔' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'NWBase_HardwareParameterSet', @level2type=N'COLUMN',@level2name=N'FrequencySpace'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'蜂鸣器默认设置
0 : 静音 0xff、   1 : 默认静音 0x00、    2 : 存盘一次就嗡鸣0x01、   3 : 每读到一张标签鸣响(影响多标签识别，仅供测试)
' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'NWBase_HardwareParameterSet', @level2type=N'COLUMN',@level2name=N'BleeperMode'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'射频通讯链路
0, //默认无 0xff            1, //配置0  Tari 25uS; FM0 40KHz0xD0  2,//配置1(推荐且为默认)   Tari 25uS; Miller 4 250KHz0xD1    3,//配置2  Tari 25uS; Miller 4 300KHz;0xD2
4,//配置3                 Tari 6.25uS; FM0 400KHz;0xD3

' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'NWBase_HardwareParameterSet', @level2type=N'COLUMN',@level2name=N'ProFileMode'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'10, 为保护设备，检测到回波损耗大于此阈值将报错并停止读写标签操作。值为0:关闭此功能' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'NWBase_HardwareParameterSet', @level2type=N'COLUMN',@level2name=N'ReturnLoss'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'插入时间' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'NWBase_HardwareParameterSet', @level2type=N'COLUMN',@level2name=N'InputTime'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'删除标识( 0 : 未删除、  1 : 删除 )' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'NWBase_HardwareParameterSet', @level2type=N'COLUMN',@level2name=N'Del'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'序号(主键)' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'NWBase_Shop', @level2type=N'COLUMN',@level2name=N'Seq'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'关联区域表序号(分公司)' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'NWBase_Shop', @level2type=N'COLUMN',@level2name=N'AreaSeq'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'门店编号' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'NWBase_Shop', @level2type=N'COLUMN',@level2name=N'ShopID'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'店名' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'NWBase_Shop', @level2type=N'COLUMN',@level2name=N'Name'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'地址' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'NWBase_Shop', @level2type=N'COLUMN',@level2name=N'Address'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'纬度' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'NWBase_Shop', @level2type=N'COLUMN',@level2name=N'Lat'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'经度' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'NWBase_Shop', @level2type=N'COLUMN',@level2name=N'Lng'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'安装时间' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'NWBase_Shop', @level2type=N'COLUMN',@level2name=N'InstallDate'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'插入时间' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'NWBase_Shop', @level2type=N'COLUMN',@level2name=N'InputTime'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'删除标识( 0 : 未删除、  1 : 删除 )' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'NWBase_Shop', @level2type=N'COLUMN',@level2name=N'Del'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'序号(主键)' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'NWBase_ShopAnimationControl', @level2type=N'COLUMN',@level2name=N'Seq'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'动画序号' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'NWBase_ShopAnimationControl', @level2type=N'COLUMN',@level2name=N'AnimationSeq'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'控制的用户序号' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'NWBase_ShopAnimationControl', @level2type=N'COLUMN',@level2name=N'UserSeq'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'设置的门店范围' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'NWBase_ShopAnimationControl', @level2type=N'COLUMN',@level2name=N'ShopSeqs'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'起始时间' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'NWBase_ShopAnimationControl', @level2type=N'COLUMN',@level2name=N'SValidDate'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'结束时间' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'NWBase_ShopAnimationControl', @level2type=N'COLUMN',@level2name=N'EValidDate'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'入库时间' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'NWBase_ShopAnimationControl', @level2type=N'COLUMN',@level2name=N'InputTime'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'删除标识( 0 : 未删除、  1 : 删除 )' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'NWBase_ShopAnimationControl', @level2type=N'COLUMN',@level2name=N'Del'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'序号(主键)' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'NWBase_ShopShowControl', @level2type=N'COLUMN',@level2name=N'Seq'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'设置人序号' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'NWBase_ShopShowControl', @level2type=N'COLUMN',@level2name=N'UserSeq'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'设置的门店范围' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'NWBase_ShopShowControl', @level2type=N'COLUMN',@level2name=N'ShopSeqs'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'前十大试穿（0不展示、1展示）' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'NWBase_ShopShowControl', @level2type=N'COLUMN',@level2name=N'DefaultTry'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'前十大销售（0不展示、1展示）' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'NWBase_ShopShowControl', @level2type=N'COLUMN',@level2name=N'DefaultSale'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'推荐展示的鞋子( 如: "1，2......" )' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'NWBase_ShopShowControl', @level2type=N'COLUMN',@level2name=N'RecommendShoes'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'起始时间' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'NWBase_ShopShowControl', @level2type=N'COLUMN',@level2name=N'SValidDate'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'结束时间' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'NWBase_ShopShowControl', @level2type=N'COLUMN',@level2name=N'EValidDate'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'入库时间' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'NWBase_ShopShowControl', @level2type=N'COLUMN',@level2name=N'InputTime'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'删除标识( 0 : 未删除、  1 : 删除 )' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'NWBase_ShopShowControl', @level2type=N'COLUMN',@level2name=N'Del'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'序号(主键)' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'NWBase_UserBrand', @level2type=N'COLUMN',@level2name=N'Seq'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'用户序号' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'NWBase_UserBrand', @level2type=N'COLUMN',@level2name=N'UserSeq'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'品牌序号' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'NWBase_UserBrand', @level2type=N'COLUMN',@level2name=N'BrandSeq'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'插入时间' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'NWBase_UserBrand', @level2type=N'COLUMN',@level2name=N'InputTime'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'删除标识( 0 : 未删除、  1 : 删除 )' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'NWBase_UserBrand', @level2type=N'COLUMN',@level2name=N'Del'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'序号(主键)' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'NWGoods_Category', @level2type=N'COLUMN',@level2name=N'Seq'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'分类父节点序号' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'NWGoods_Category', @level2type=N'COLUMN',@level2name=N'ParentSeq'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'品牌序号' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'NWGoods_Category', @level2type=N'COLUMN',@level2name=N'BrandSeq'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'分类名称' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'NWGoods_Category', @level2type=N'COLUMN',@level2name=N'Name'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'分类代码' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'NWGoods_Category', @level2type=N'COLUMN',@level2name=N'CategoryCode'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'是否可见（ 0 : 可见,   1 : 不可见 ）' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'NWGoods_Category', @level2type=N'COLUMN',@level2name=N'Visible'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'创建时间' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'NWGoods_Category', @level2type=N'COLUMN',@level2name=N'InputTime'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'删除标识( 0 : 未删除、  1 : 删除 )' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'NWGoods_Category', @level2type=N'COLUMN',@level2name=N'Del'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'序号(主键)' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'NWGoods_Color', @level2type=N'COLUMN',@level2name=N'Seq'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'品牌序号' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'NWGoods_Color', @level2type=N'COLUMN',@level2name=N'BrandSeq'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'颜色编码' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'NWGoods_Color', @level2type=N'COLUMN',@level2name=N'Code'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'颜色名称' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'NWGoods_Color', @level2type=N'COLUMN',@level2name=N'Name'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'创建时间' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'NWGoods_Color', @level2type=N'COLUMN',@level2name=N'InputTime'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'删除标识( 0 : 未删除、  1 : 删除 )' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'NWGoods_Color', @level2type=N'COLUMN',@level2name=N'Del'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'序号(主键)' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'NWGoods_Material', @level2type=N'COLUMN',@level2name=N'Seq'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'波次序号' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'NWGoods_Material', @level2type=N'COLUMN',@level2name=N'PeriodSeq'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'素材名称' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'NWGoods_Material', @level2type=N'COLUMN',@level2name=N'MaterialName'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'素材文件名称，下载时前缀发布地址+RelativeURL+本字段合并' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'NWGoods_Material', @level2type=N'COLUMN',@level2name=N'MaterialFileName'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'素材类型( 0：图片    1：视频     2：音频 )' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'NWGoods_Material', @level2type=N'COLUMN',@level2name=N'MaterialType'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'使用时间' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'NWGoods_Material', @level2type=N'COLUMN',@level2name=N'UsefulDate'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'关联的鞋子序号' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'NWGoods_Material', @level2type=N'COLUMN',@level2name=N'ShoeSeq'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'货号' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'NWGoods_Material', @level2type=N'COLUMN',@level2name=N'ShoeID'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'文件的MD5,  检查文件的一致性
' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'NWGoods_Material', @level2type=N'COLUMN',@level2name=N'MD5Code'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'相对的URL地址用语下载文件,  没有文件名' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'NWGoods_Material', @level2type=N'COLUMN',@level2name=N'RelativeURL'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'入库时间' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'NWGoods_Material', @level2type=N'COLUMN',@level2name=N'InputTime'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'删除标识( 0 : 未删除、  1 : 删除 )' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'NWGoods_Material', @level2type=N'COLUMN',@level2name=N'Del'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'序号(主键)' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'NWGoods_Period', @level2type=N'COLUMN',@level2name=N'Seq'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'品牌序号' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'NWGoods_Period', @level2type=N'COLUMN',@level2name=N'BrandSeq'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'内部编码' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'NWGoods_Period', @level2type=N'COLUMN',@level2name=N'InnerKey'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'波次名字' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'NWGoods_Period', @level2type=N'COLUMN',@level2name=N'Name'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'对应波次下鞋子素材文件的起始下载时间' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'NWGoods_Period', @level2type=N'COLUMN',@level2name=N'FileDownLoadDate'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'上架销售时间' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'NWGoods_Period', @level2type=N'COLUMN',@level2name=N'SaleDate'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'0：无效 1：有效' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'NWGoods_Period', @level2type=N'COLUMN',@level2name=N'isValid'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'入库时间' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'NWGoods_Period', @level2type=N'COLUMN',@level2name=N'InputTime'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'删除标识( 0 : 未删除、  1 : 删除 )' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'NWGoods_Period', @level2type=N'COLUMN',@level2name=N'Del'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'序号(主键)' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'NWGoods_SaleQuota', @level2type=N'COLUMN',@level2name=N'Seq'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'品牌序号' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'NWGoods_SaleQuota', @level2type=N'COLUMN',@level2name=N'BrandSeq'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'年份' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'NWGoods_SaleQuota', @level2type=N'COLUMN',@level2name=N'Year'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'月份' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'NWGoods_SaleQuota', @level2type=N'COLUMN',@level2name=N'Month'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'区域序号（大区、分公司、门店）' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'NWGoods_SaleQuota', @level2type=N'COLUMN',@level2name=N'AreaSeq'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'区域销售指标' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'NWGoods_SaleQuota', @level2type=N'COLUMN',@level2name=N'Quota'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'区域类型（1大区、2分公司、3门店）' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'NWGoods_SaleQuota', @level2type=N'COLUMN',@level2name=N'AreaType'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'下发人序号' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'NWGoods_SaleQuota', @level2type=N'COLUMN',@level2name=N'UserSeq'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'插入时间' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'NWGoods_SaleQuota', @level2type=N'COLUMN',@level2name=N'InputTime'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'删除标识( 0 : 未删除、  1 : 删除 )' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'NWGoods_SaleQuota', @level2type=N'COLUMN',@level2name=N'Del'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'序号(主键)' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'NWGoods_SaleShoesDetail', @level2type=N'COLUMN',@level2name=N'Seq'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'波次序号' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'NWGoods_SaleShoesDetail', @level2type=N'COLUMN',@level2name=N'PeriodSeq'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'大区序号' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'NWGoods_SaleShoesDetail', @level2type=N'COLUMN',@level2name=N'AreaSeq'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'分公司序号' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'NWGoods_SaleShoesDetail', @level2type=N'COLUMN',@level2name=N'BranchOfficeSeq'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'门店序号' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'NWGoods_SaleShoesDetail', @level2type=N'COLUMN',@level2name=N'ShopSeq'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'销售时间' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'NWGoods_SaleShoesDetail', @level2type=N'COLUMN',@level2name=N'SaleDate'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'鞋子序号' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'NWGoods_SaleShoesDetail', @level2type=N'COLUMN',@level2name=N'ShoeSeq'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'货号' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'NWGoods_SaleShoesDetail', @level2type=N'COLUMN',@level2name=N'ShoeID'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'订单数量' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'NWGoods_SaleShoesDetail', @level2type=N'COLUMN',@level2name=N'OrderCount'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'销售双数' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'NWGoods_SaleShoesDetail', @level2type=N'COLUMN',@level2name=N'SaleCount'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'成本' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'NWGoods_SaleShoesDetail', @level2type=N'COLUMN',@level2name=N'Cost'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'吊牌价' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'NWGoods_SaleShoesDetail', @level2type=N'COLUMN',@level2name=N'TagPrice'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'实际销售价格' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'NWGoods_SaleShoesDetail', @level2type=N'COLUMN',@level2name=N'RealPrice'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'插入时间' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'NWGoods_SaleShoesDetail', @level2type=N'COLUMN',@level2name=N'InputTime'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'删除标识( 0 : 未删除、  1 : 删除 )' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'NWGoods_SaleShoesDetail', @level2type=N'COLUMN',@level2name=N'Del'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'序号(主键)' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'NWGoods_Series', @level2type=N'COLUMN',@level2name=N'Seq'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'品牌序号' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'NWGoods_Series', @level2type=N'COLUMN',@level2name=N'BrandSeq'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'系列名称' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'NWGoods_Series', @level2type=N'COLUMN',@level2name=N'Name'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'系列描述备注' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'NWGoods_Series', @level2type=N'COLUMN',@level2name=N'Remark'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'用于展示系统背景虚化的图片' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'NWGoods_Series', @level2type=N'COLUMN',@level2name=N'BgImg'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'创建时间' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'NWGoods_Series', @level2type=N'COLUMN',@level2name=N'InputTime'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'删除标识( 0 : 未删除、  1 : 删除 )' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'NWGoods_Series', @level2type=N'COLUMN',@level2name=N'Del'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'序号(主键)' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'NWGoods_Shoe', @level2type=N'COLUMN',@level2name=N'Seq'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'波次序号' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'NWGoods_Shoe', @level2type=N'COLUMN',@level2name=N'PeriodSeq'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'二维码标号（鞋子、标签关联表内二维码一致）' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'NWGoods_Shoe', @level2type=N'COLUMN',@level2name=N'QRCode'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'货品名称' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'NWGoods_Shoe', @level2type=N'COLUMN',@level2name=N'Name'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'货号' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'NWGoods_Shoe', @level2type=N'COLUMN',@level2name=N'ShoeID'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'鞋子类别序号' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'NWGoods_Shoe', @level2type=N'COLUMN',@level2name=N'CategorySeq'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'属性Code(1)' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'NWGoods_Shoe', @level2type=N'COLUMN',@level2name=N'SX1'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'属性Code(20)' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'NWGoods_Shoe', @level2type=N'COLUMN',@level2name=N'SX20'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'系列序号' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'NWGoods_Shoe', @level2type=N'COLUMN',@level2name=N'SeriesSeq'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'缩略图名字（展示前十大动画图片）' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'NWGoods_Shoe', @level2type=N'COLUMN',@level2name=N'Thumbnail'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'创建时间' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'NWGoods_Shoe', @level2type=N'COLUMN',@level2name=N'InputTime'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'删除标识( 0 : 未删除、  1 : 删除 )' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'NWGoods_Shoe', @level2type=N'COLUMN',@level2name=N'Del'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'序号(主键)' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'NWGoods_ShoeRFID', @level2type=N'COLUMN',@level2name=N'Seq'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'鞋子货号' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'NWGoods_ShoeRFID', @level2type=N'COLUMN',@level2name=N'ShoeSeq'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'货号' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'NWGoods_ShoeRFID', @level2type=N'COLUMN',@level2name=N'ShoeID'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'RFID标签编号' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'NWGoods_ShoeRFID', @level2type=N'COLUMN',@level2name=N'RFIDCode'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'入库时间' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'NWGoods_ShoeRFID', @level2type=N'COLUMN',@level2name=N'InputTime'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'删除标识( 0 : 未删除、  1 : 删除 )' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'NWGoods_ShoeRFID', @level2type=N'COLUMN',@level2name=N'Del'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'序号(主键)' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'NWGoods_ShoesData', @level2type=N'COLUMN',@level2name=N'Seq'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'鞋子序号' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'NWGoods_ShoesData', @level2type=N'COLUMN',@level2name=N'ShoeSeq'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'颜色seq' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'NWGoods_ShoesData', @level2type=N'COLUMN',@level2name=N'ColorSeq'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'尺码seq' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'NWGoods_ShoesData', @level2type=N'COLUMN',@level2name=N'SizeSeq'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'总数量' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'NWGoods_ShoesData', @level2type=N'COLUMN',@level2name=N'Num'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'库存修改时间' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'NWGoods_ShoesData', @level2type=N'COLUMN',@level2name=N'StockDate'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'库存' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'NWGoods_ShoesData', @level2type=N'COLUMN',@level2name=N'Stock'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'入库时间' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'NWGoods_ShoesData', @level2type=N'COLUMN',@level2name=N'InputTime'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'删除标识( 0 : 未删除、  1 : 删除 )' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'NWGoods_ShoesData', @level2type=N'COLUMN',@level2name=N'Del'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'序号(主键)' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'NWGoods_ShoesOrder', @level2type=N'COLUMN',@level2name=N'Seq'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'鞋子序号' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'NWGoods_ShoesOrder', @level2type=N'COLUMN',@level2name=N'ShoeSeq'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'波次序号' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'NWGoods_ShoesOrder', @level2type=N'COLUMN',@level2name=N'PeriodSeq'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'分公司序号' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'NWGoods_ShoesOrder', @level2type=N'COLUMN',@level2name=N'AreaSeq'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'门店序号' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'NWGoods_ShoesOrder', @level2type=N'COLUMN',@level2name=N'shopSeq'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'所订鞋数量' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'NWGoods_ShoesOrder', @level2type=N'COLUMN',@level2name=N'OrderCount'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'入库时间' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'NWGoods_ShoesOrder', @level2type=N'COLUMN',@level2name=N'InputTime'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'删除标识( 0 : 未删除、  1 : 删除 )' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'NWGoods_ShoesOrder', @level2type=N'COLUMN',@level2name=N'Del'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'序号(主键)' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'NWGoods_Size', @level2type=N'COLUMN',@level2name=N'Seq'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'品牌序号' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'NWGoods_Size', @level2type=N'COLUMN',@level2name=N'BrandSeq'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'尺码编码' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'NWGoods_Size', @level2type=N'COLUMN',@level2name=N'Code'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'尺码名称' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'NWGoods_Size', @level2type=N'COLUMN',@level2name=N'Name'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'创建时间' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'NWGoods_Size', @level2type=N'COLUMN',@level2name=N'InputTime'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'删除标识( 0 : 未删除、  1 : 删除 )' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'NWGoods_Size', @level2type=N'COLUMN',@level2name=N'Del'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'序号(主键)' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'NWGoods_SX', @level2type=N'COLUMN',@level2name=N'Seq'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'品牌序号' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'NWGoods_SX', @level2type=N'COLUMN',@level2name=N'BrandSeq'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'属性对应Goods表字段( 如 SX1,SX2...)' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'NWGoods_SX', @level2type=N'COLUMN',@level2name=N'SXID'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'属性中文含义' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'NWGoods_SX', @level2type=N'COLUMN',@level2name=N'SXName'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'是否可见（0 : 可见,    1 : 不可见）' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'NWGoods_SX', @level2type=N'COLUMN',@level2name=N'Visible'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'创建时间' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'NWGoods_SX', @level2type=N'COLUMN',@level2name=N'InputTime'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'删除标识( 0 : 未删除、  1 : 删除 )' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'NWGoods_SX', @level2type=N'COLUMN',@level2name=N'Del'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'序号(主键)' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'NWGoods_SXOption', @level2type=N'COLUMN',@level2name=N'Seq'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'属性序号( 外键:YHSR_Goods_SX表 )' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'NWGoods_SXOption', @level2type=N'COLUMN',@level2name=N'SXSeq'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'属性编码' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'NWGoods_SXOption', @level2type=N'COLUMN',@level2name=N'Code'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'属性值' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'NWGoods_SXOption', @level2type=N'COLUMN',@level2name=N'Value'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'创建时间' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'NWGoods_SXOption', @level2type=N'COLUMN',@level2name=N'InputTime'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'删除标识( 0 : 未删除、  1 : 删除 )' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'NWGoods_SXOption', @level2type=N'COLUMN',@level2name=N'Del'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'序号(主键)' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'NWGoods_TryShoesDetail', @level2type=N'COLUMN',@level2name=N'Seq'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'鞋子序号' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'NWGoods_TryShoesDetail', @level2type=N'COLUMN',@level2name=N'ShoeSeq'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'货号' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'NWGoods_TryShoesDetail', @level2type=N'COLUMN',@level2name=N'ShoeID'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'试穿次数' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'NWGoods_TryShoesDetail', @level2type=N'COLUMN',@level2name=N'TryCount'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'试穿时间长(单位：秒钟)' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'NWGoods_TryShoesDetail', @level2type=N'COLUMN',@level2name=N'TryTimes'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'门店序号' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'NWGoods_TryShoesDetail', @level2type=N'COLUMN',@level2name=N'ShopSeq'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'数据时间' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'NWGoods_TryShoesDetail', @level2type=N'COLUMN',@level2name=N'DataTime'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'插入时间' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'NWGoods_TryShoesDetail', @level2type=N'COLUMN',@level2name=N'InputTime'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'删除标识( 0 : 未删除、  1 : 删除 )' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'NWGoods_TryShoesDetail', @level2type=N'COLUMN',@level2name=N'Del'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'序号(主键)' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'NWOther_UpSystem', @level2type=N'COLUMN',@level2name=N'Seq'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'文件名称（由文件相对路径+文件全名+后缀名）' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'NWOther_UpSystem', @level2type=N'COLUMN',@level2name=N'US_FileName'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'是否为系统文件，1是0否' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'NWOther_UpSystem', @level2type=N'COLUMN',@level2name=N'US_SystemFile'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'是否为注册文件，1是0否，给前台调用regsvr32注册组件使用' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'NWOther_UpSystem', @level2type=N'COLUMN',@level2name=N'US_RegFile'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'系统版本号' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'NWOther_UpSystem', @level2type=N'COLUMN',@level2name=N'US_Version'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'公司序号' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'NWOther_UpSystem', @level2type=N'COLUMN',@level2name=N'CompanySeq'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'序号(主键)' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'NWUser_FollowsArea', @level2type=N'COLUMN',@level2name=N'Seq'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'用户序号' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'NWUser_FollowsArea', @level2type=N'COLUMN',@level2name=N'UserSeq'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'关注的大区序号  如 : "1,2,3"' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'NWUser_FollowsArea', @level2type=N'COLUMN',@level2name=N'FollowDQSeqs'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'关注的分公司序号  如 : "1,2,3"' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'NWUser_FollowsArea', @level2type=N'COLUMN',@level2name=N'FollowBSCSeqs'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'关注的门店序号  如 : "1,2,3"' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'NWUser_FollowsArea', @level2type=N'COLUMN',@level2name=N'FollowMDSeqs'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'插入时间' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'NWUser_FollowsArea', @level2type=N'COLUMN',@level2name=N'InputTime'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'删除标识( 0 : 未删除、  1 : 删除 )' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'NWUser_FollowsArea', @level2type=N'COLUMN',@level2name=N'Del'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'序号(主键)' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'NWUser_Permission', @level2type=N'COLUMN',@level2name=N'Seq'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'权限名称( 模块名称 )' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'NWUser_Permission', @level2type=N'COLUMN',@level2name=N'PerName'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'功能模块路径' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'NWUser_Permission', @level2type=N'COLUMN',@level2name=N'Path'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'插入时间' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'NWUser_Permission', @level2type=N'COLUMN',@level2name=N'InputTime'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'删除标识( 0 : 未删除,   1 : 删除 )' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'NWUser_Permission', @level2type=N'COLUMN',@level2name=N'Del'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'序号(主键)' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'NWUser_Role', @level2type=N'COLUMN',@level2name=N'Seq'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'-1 : 超级管理员,   0 : 后台管理员,   1 : 全国用户角色,   2 : 大区角色,    3 : 分公司角色,    4 : 门店角色' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'NWUser_Role', @level2type=N'COLUMN',@level2name=N'RoleName'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'插入时间' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'NWUser_Role', @level2type=N'COLUMN',@level2name=N'InputTime'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'删除标识( 0 : 未删除,   1 : 删除 )' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'NWUser_Role', @level2type=N'COLUMN',@level2name=N'Del'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'序号(主键)' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'NWUser_RolePermission', @level2type=N'COLUMN',@level2name=N'Seq'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'角色序号' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'NWUser_RolePermission', @level2type=N'COLUMN',@level2name=N'RoleSeq'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'权限序号' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'NWUser_RolePermission', @level2type=N'COLUMN',@level2name=N'PermissionSeq'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'插入时间' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'NWUser_RolePermission', @level2type=N'COLUMN',@level2name=N'InputTime'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'
删除标识( 0 : 未删除,   1 : 删除 )' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'NWUser_RolePermission', @level2type=N'COLUMN',@level2name=N'Del'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'序号(主键)' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'NWUser_User', @level2type=N'COLUMN',@level2name=N'Seq'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'账号' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'NWUser_User', @level2type=N'COLUMN',@level2name=N'UserName'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'密码' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'NWUser_User', @level2type=N'COLUMN',@level2name=N'Password'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'角色序号' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'NWUser_User', @level2type=N'COLUMN',@level2name=N'RoleSeq'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'自己的序号  门店用户则是门店序号 大区则是大区序号 分公司是分公司序号 全国则默认为0' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'NWUser_User', @level2type=N'COLUMN',@level2name=N'UserAreaSeq'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'创建人序号' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'NWUser_User', @level2type=N'COLUMN',@level2name=N'CreateUserSeq'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'真实姓名' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'NWUser_User', @level2type=N'COLUMN',@level2name=N'RealName'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'电话' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'NWUser_User', @level2type=N'COLUMN',@level2name=N'Telephone'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'头像' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'NWUser_User', @level2type=N'COLUMN',@level2name=N'HeadImg'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'是否有效( 0 : 无效、 1 : 有效 )' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'NWUser_User', @level2type=N'COLUMN',@level2name=N'IsUseful'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'插入时间' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'NWUser_User', @level2type=N'COLUMN',@level2name=N'InputTime'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'删除标识( 0 : 未删除、  1 : 删除 )' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'NWUser_User', @level2type=N'COLUMN',@level2name=N'Del'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'用户Seq' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'tb_token', @level2type=N'COLUMN',@level2name=N'userSeq'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'token值' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'tb_token', @level2type=N'COLUMN',@level2name=N'token'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'过期时间' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'tb_token', @level2type=N'COLUMN',@level2name=N'expireTime'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'更新时间' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'tb_token', @level2type=N'COLUMN',@level2name=N'updateTime'
GO
