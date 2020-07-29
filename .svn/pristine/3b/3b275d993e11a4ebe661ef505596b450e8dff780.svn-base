USE [YHSmartDataRetail]
GO

/****** Object:  Table [dbo].[NWFestival_Template]    Script Date: 01/28/2019 15:16:34 ******/
SET ANSI_NULLS ON
GO

SET QUOTED_IDENTIFIER ON
GO

SET ANSI_PADDING ON
GO

CREATE TABLE [dbo].[NWFestival_Template](
	[Seq] [int] IDENTITY(1,1) NOT NULL,
	[Name] [varchar](50) NULL,
	[ParentSeq] [int] NULL,
	[NodeType] [int] NULL,
	[UpdateTime] [datetime] NULL,
	[InputTime] [datetime] NULL,
	[Del] [int] NULL,
	[BelongTo] [int] NULL,
	[CreatorSeq] [int] NULL,
 CONSTRAINT [PK_Template_Seq] PRIMARY KEY CLUSTERED
(
	[Seq] ASC
)WITH (PAD_INDEX  = OFF, STATISTICS_NORECOMPUTE  = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS  = ON, ALLOW_PAGE_LOCKS  = ON) ON [PRIMARY]
) ON [PRIMARY]

GO

SET ANSI_PADDING OFF
GO

ALTER TABLE [dbo].[NWFestival_Template] ADD  DEFAULT (getdate()) FOR [UpdateTime]
GO

ALTER TABLE [dbo].[NWFestival_Template] ADD  DEFAULT (getdate()) FOR [InputTime]
GO

ALTER TABLE [dbo].[NWFestival_Template] ADD  DEFAULT ((0)) FOR [Del]
GO

ALTER TABLE [dbo].[NWFestival_Template] ADD  DEFAULT ((0)) FOR [BelongTo]
GO



USE [YHSmartDataRetail]
GO

/****** Object:  Table [dbo].[NWFestival_Material]    Script Date: 01/28/2019 15:15:51 ******/
SET ANSI_NULLS ON
GO

SET QUOTED_IDENTIFIER ON
GO

SET ANSI_PADDING ON
GO

CREATE TABLE [dbo].[NWFestival_Material](
	[Seq] [int] IDENTITY(1,1) NOT NULL,
	[Path] [varchar](max) NULL,
	[InputTime] [datetime] NULL,
	[BelongTo] [int] NULL,
	[CreatorSeq] [int] NULL,
 CONSTRAINT [PK_Material_Seq] PRIMARY KEY CLUSTERED
(
	[Seq] ASC
)WITH (PAD_INDEX  = OFF, STATISTICS_NORECOMPUTE  = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS  = ON, ALLOW_PAGE_LOCKS  = ON) ON [PRIMARY]
) ON [PRIMARY]

GO

SET ANSI_PADDING OFF
GO

ALTER TABLE [dbo].[NWFestival_Material] ADD  DEFAULT (getdate()) FOR [InputTime]
GO


USE [YHSmartDataRetail]
GO

/****** Object:  Table [dbo].[NWFestival_StaticContent]    Script Date: 01/28/2019 15:16:07 ******/
SET ANSI_NULLS ON
GO

SET QUOTED_IDENTIFIER ON
GO

CREATE TABLE [dbo].[NWFestival_StaticContent](
	[Seq] [int] IDENTITY(1,1) NOT NULL,
	[Position] [int] NULL,
	[Wper] [int] NULL,
	[Hper] [int] NULL,
	[Opacity] [int] NULL,
	[TemplateSeq] [int] NULL,
	[MaterialSeq] [int] NULL,
	[InputTime] [datetime] NULL,
 CONSTRAINT [PK_StaticContent_Seq] PRIMARY KEY CLUSTERED
(
	[Seq] ASC
)WITH (PAD_INDEX  = OFF, STATISTICS_NORECOMPUTE  = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS  = ON, ALLOW_PAGE_LOCKS  = ON) ON [PRIMARY]
) ON [PRIMARY]

GO

ALTER TABLE [dbo].[NWFestival_StaticContent]  WITH CHECK ADD  CONSTRAINT [FK_StaticContent_MaterialSeq] FOREIGN KEY([MaterialSeq])
REFERENCES [dbo].[NWFestival_Material] ([Seq])
GO

ALTER TABLE [dbo].[NWFestival_StaticContent] CHECK CONSTRAINT [FK_StaticContent_MaterialSeq]
GO

ALTER TABLE [dbo].[NWFestival_StaticContent]  WITH CHECK ADD  CONSTRAINT [FK_StaticContent_TemplateSeq] FOREIGN KEY([TemplateSeq])
REFERENCES [dbo].[NWFestival_Template] ([Seq])
GO

ALTER TABLE [dbo].[NWFestival_StaticContent] CHECK CONSTRAINT [FK_StaticContent_TemplateSeq]
GO

ALTER TABLE [dbo].[NWFestival_StaticContent] ADD  DEFAULT ((0)) FOR [Opacity]
GO

ALTER TABLE [dbo].[NWFestival_StaticContent] ADD  DEFAULT (getdate()) FOR [InputTime]
GO


USE [YHSmartDataRetail]
GO

/****** Object:  Table [dbo].[NWFestival_AnimateContent]    Script Date: 01/28/2019 15:15:11 ******/
SET ANSI_NULLS ON
GO

SET QUOTED_IDENTIFIER ON
GO

CREATE TABLE [dbo].[NWFestival_AnimateContent](
	[Seq] [int] IDENTITY(1,1) NOT NULL,
	[WindPower] [int] NULL,
	[Speed] [int] NULL,
	[Count] [int] NULL,
	[Size] [int] NULL,
	[Opacity] [int] NULL,
	[InputTime] [datetime] NULL,
	[TemplateSeq] [int] NULL,
	[MaterialSeq] [int] NULL,
 CONSTRAINT [PK_AnimateContent_Seq] PRIMARY KEY CLUSTERED
(
	[Seq] ASC
)WITH (PAD_INDEX  = OFF, STATISTICS_NORECOMPUTE  = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS  = ON, ALLOW_PAGE_LOCKS  = ON) ON [PRIMARY]
) ON [PRIMARY]

GO

ALTER TABLE [dbo].[NWFestival_AnimateContent]  WITH CHECK ADD  CONSTRAINT [FK_AnimateContent_MaterialSeq] FOREIGN KEY([MaterialSeq])
REFERENCES [dbo].[NWFestival_Material] ([Seq])
GO

ALTER TABLE [dbo].[NWFestival_AnimateContent] CHECK CONSTRAINT [FK_AnimateContent_MaterialSeq]
GO

ALTER TABLE [dbo].[NWFestival_AnimateContent]  WITH CHECK ADD  CONSTRAINT [FK_AnimateContent_TemplateSeq] FOREIGN KEY([TemplateSeq])
REFERENCES [dbo].[NWFestival_Template] ([Seq])
GO

ALTER TABLE [dbo].[NWFestival_AnimateContent] CHECK CONSTRAINT [FK_AnimateContent_TemplateSeq]
GO

ALTER TABLE [dbo].[NWFestival_AnimateContent] ADD  DEFAULT ((0)) FOR [WindPower]
GO

ALTER TABLE [dbo].[NWFestival_AnimateContent] ADD  DEFAULT ((3)) FOR [Speed]
GO

ALTER TABLE [dbo].[NWFestival_AnimateContent] ADD  DEFAULT ((1)) FOR [Count]
GO

ALTER TABLE [dbo].[NWFestival_AnimateContent] ADD  DEFAULT ((10)) FOR [Size]
GO

ALTER TABLE [dbo].[NWFestival_AnimateContent] ADD  DEFAULT ((0)) FOR [Opacity]
GO

ALTER TABLE [dbo].[NWFestival_AnimateContent] ADD  DEFAULT (getdate()) FOR [InputTime]
GO

USE [YHSmartDataRetail]
GO

/****** Object:  Table [dbo].[NWFestival_TemplateBackground]    Script Date: 02/19/2019 11:17:10 ******/
SET ANSI_NULLS ON
GO

SET QUOTED_IDENTIFIER ON
GO

CREATE TABLE [dbo].[NWFestival_TemplateBackground](
	[Seq] [int] IDENTITY(1,1) NOT NULL,
	[TemplateSeq] [int] NULL,
	[MaterialSeq] [int] NULL,
	[CreatorSeq] [int] NULL,
	[InputTime] [datetime] NULL,
 CONSTRAINT [PK_TemplateBackground_Seq] PRIMARY KEY CLUSTERED
(
	[Seq] ASC
)WITH (PAD_INDEX  = OFF, STATISTICS_NORECOMPUTE  = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS  = ON, ALLOW_PAGE_LOCKS  = ON) ON [PRIMARY]
) ON [PRIMARY]

GO

ALTER TABLE [dbo].[NWFestival_TemplateBackground]  WITH CHECK ADD  CONSTRAINT [FK_TemplateBackground_MaterialSeq] FOREIGN KEY([MaterialSeq])
REFERENCES [dbo].[NWFestival_Material] ([Seq])
GO

ALTER TABLE [dbo].[NWFestival_TemplateBackground] CHECK CONSTRAINT [FK_TemplateBackground_MaterialSeq]
GO

ALTER TABLE [dbo].[NWFestival_TemplateBackground]  WITH CHECK ADD  CONSTRAINT [FK_TemplateBackground_TemplateSeq] FOREIGN KEY([TemplateSeq])
REFERENCES [dbo].[NWFestival_Template] ([Seq])
GO

ALTER TABLE [dbo].[NWFestival_TemplateBackground] CHECK CONSTRAINT [FK_TemplateBackground_TemplateSeq]
GO

ALTER TABLE [dbo].[NWFestival_TemplateBackground] ADD  DEFAULT (getdate()) FOR [InputTime]
GO



