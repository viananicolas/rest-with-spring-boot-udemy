USE [springbootdb]
GO
/****** Object:  Table [dbo].[person]    Script Date: 19/05/2020 13:03:08 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[person](
                               [id] [bigint] IDENTITY(1,1) NOT NULL,
                               [address] [varchar](255) NOT NULL,
                               [first_name] [varchar](255) NOT NULL,
                               [gender] [varchar](255) NOT NULL,
                               [last_name] [varchar](255) NOT NULL,
                               PRIMARY KEY CLUSTERED
                                   (
                                    [id] ASC
                                       )WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
