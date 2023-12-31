USE [master]
GO
/****** Object:  Database [SampleProject1]    Script Date: 02/12/2021 4:36:00 CH ******/
CREATE DATABASE [SampleProject1]
 CONTAINMENT = NONE
 ON  PRIMARY 
( NAME = N'SampleProject1', FILENAME = N'C:\Program Files\Microsoft SQL Server\MSSQL15.SQLEXPRESS\MSSQL\DATA\SampleProject1.mdf' , SIZE = 8192KB , MAXSIZE = UNLIMITED, FILEGROWTH = 1024KB )
 LOG ON 
( NAME = N'SampleProject1_log', FILENAME = N'C:\Program Files\Microsoft SQL Server\MSSQL15.SQLEXPRESS\MSSQL\DATA\SampleProject1_log.ldf' , SIZE = 1024KB , MAXSIZE = 2048GB , FILEGROWTH = 10%)
 WITH CATALOG_COLLATION = DATABASE_DEFAULT
GO
ALTER DATABASE [SampleProject1] SET COMPATIBILITY_LEVEL = 120
GO
IF (1 = FULLTEXTSERVICEPROPERTY('IsFullTextInstalled'))
begin
EXEC [SampleProject1].[dbo].[sp_fulltext_database] @action = 'enable'
end
GO
ALTER DATABASE [SampleProject1] SET ANSI_NULL_DEFAULT OFF 
GO
ALTER DATABASE [SampleProject1] SET ANSI_NULLS OFF 
GO
ALTER DATABASE [SampleProject1] SET ANSI_PADDING OFF 
GO
ALTER DATABASE [SampleProject1] SET ANSI_WARNINGS OFF 
GO
ALTER DATABASE [SampleProject1] SET ARITHABORT OFF 
GO
ALTER DATABASE [SampleProject1] SET AUTO_CLOSE OFF 
GO
ALTER DATABASE [SampleProject1] SET AUTO_SHRINK OFF 
GO
ALTER DATABASE [SampleProject1] SET AUTO_UPDATE_STATISTICS ON 
GO
ALTER DATABASE [SampleProject1] SET CURSOR_CLOSE_ON_COMMIT OFF 
GO
ALTER DATABASE [SampleProject1] SET CURSOR_DEFAULT  GLOBAL 
GO
ALTER DATABASE [SampleProject1] SET CONCAT_NULL_YIELDS_NULL OFF 
GO
ALTER DATABASE [SampleProject1] SET NUMERIC_ROUNDABORT OFF 
GO
ALTER DATABASE [SampleProject1] SET QUOTED_IDENTIFIER OFF 
GO
ALTER DATABASE [SampleProject1] SET RECURSIVE_TRIGGERS OFF 
GO
ALTER DATABASE [SampleProject1] SET  DISABLE_BROKER 
GO
ALTER DATABASE [SampleProject1] SET AUTO_UPDATE_STATISTICS_ASYNC OFF 
GO
ALTER DATABASE [SampleProject1] SET DATE_CORRELATION_OPTIMIZATION OFF 
GO
ALTER DATABASE [SampleProject1] SET TRUSTWORTHY OFF 
GO
ALTER DATABASE [SampleProject1] SET ALLOW_SNAPSHOT_ISOLATION OFF 
GO
ALTER DATABASE [SampleProject1] SET PARAMETERIZATION SIMPLE 
GO
ALTER DATABASE [SampleProject1] SET READ_COMMITTED_SNAPSHOT OFF 
GO
ALTER DATABASE [SampleProject1] SET HONOR_BROKER_PRIORITY OFF 
GO
ALTER DATABASE [SampleProject1] SET RECOVERY SIMPLE 
GO
ALTER DATABASE [SampleProject1] SET  MULTI_USER 
GO
ALTER DATABASE [SampleProject1] SET PAGE_VERIFY CHECKSUM  
GO
ALTER DATABASE [SampleProject1] SET DB_CHAINING OFF 
GO
ALTER DATABASE [SampleProject1] SET FILESTREAM( NON_TRANSACTED_ACCESS = OFF ) 
GO
ALTER DATABASE [SampleProject1] SET TARGET_RECOVERY_TIME = 0 SECONDS 
GO
ALTER DATABASE [SampleProject1] SET DELAYED_DURABILITY = DISABLED 
GO
ALTER DATABASE [SampleProject1] SET ACCELERATED_DATABASE_RECOVERY = OFF  
GO
ALTER DATABASE [SampleProject1] SET QUERY_STORE = OFF
GO
USE [SampleProject1]
GO
/****** Object:  Table [dbo].[CTPhieuMuon]    Script Date: 02/12/2021 4:36:00 CH ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[CTPhieuMuon](
	[MaPM] [nvarchar](8) NOT NULL,
	[MaSach] [nvarchar](8) NOT NULL,
	[NgayTra] [date] NOT NULL
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[DocGia]    Script Date: 02/12/2021 4:36:00 CH ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[DocGia](
	[MaDG] [nvarchar](8) NOT NULL,
	[TenDG] [nvarchar](64) NOT NULL,
	[DiaChi] [nvarchar](255) NOT NULL,
	[GioiTinh] [bit] NOT NULL,
	[DienThoai] [nvarchar](16) NULL,
 CONSTRAINT [PK_DocGia] PRIMARY KEY CLUSTERED 
(
	[MaDG] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[NguoiDung]    Script Date: 02/12/2021 4:36:00 CH ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[NguoiDung](
	[MaND] [nvarchar](8) NOT NULL,
	[TenND] [nvarchar](32) NOT NULL,
	[MatKhau] [nvarchar](32) NOT NULL,
	[VaiTro] [bit] NULL,
 CONSTRAINT [PK_NguoiDung] PRIMARY KEY CLUSTERED 
(
	[MaND] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[PhieuMuon]    Script Date: 02/12/2021 4:36:00 CH ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[PhieuMuon](
	[MaPM] [nvarchar](8) NOT NULL,
	[MaDG] [nvarchar](8) NOT NULL,
	[SoLuongMuon] [int] NOT NULL,
	[TienCoc] [float] NOT NULL,
	[NgayMuon] [date] NOT NULL,
	[NgayHenTra] [date] NOT NULL,
 CONSTRAINT [PK_PhieuMuon] PRIMARY KEY CLUSTERED 
(
	[MaPM] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[Sach]    Script Date: 02/12/2021 4:36:00 CH ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Sach](
	[MaSach] [nvarchar](8) NOT NULL,
	[TenSach] [nvarchar](125) NOT NULL,
	[TacGia] [nvarchar](64) NOT NULL,
	[NhaXB] [nvarchar](125) NOT NULL,
	[SoLuong] [int] NOT NULL,
	[SoLuongCon] [int] NOT NULL,
	[NgayNhap] [datetime] NOT NULL,
	[GiaBia] [int] NOT NULL,
 CONSTRAINT [PK_Sach] PRIMARY KEY CLUSTERED 
(
	[MaSach] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
INSERT [dbo].[CTPhieuMuon] ([MaPM], [MaSach], [NgayTra]) VALUES (N'P02', N'S02', CAST(N'2022-01-02' AS Date))
INSERT [dbo].[CTPhieuMuon] ([MaPM], [MaSach], [NgayTra]) VALUES (N'P01', N'S04', CAST(N'2022-01-02' AS Date))
INSERT [dbo].[CTPhieuMuon] ([MaPM], [MaSach], [NgayTra]) VALUES (N'P02', N'S01', CAST(N'2022-02-01' AS Date))
INSERT [dbo].[CTPhieuMuon] ([MaPM], [MaSach], [NgayTra]) VALUES (N'P03', N'S01', CAST(N'2022-01-02' AS Date))
INSERT [dbo].[CTPhieuMuon] ([MaPM], [MaSach], [NgayTra]) VALUES (N'P05', N'S05', CAST(N'2022-01-22' AS Date))
GO
INSERT [dbo].[DocGia] ([MaDG], [TenDG], [DiaChi], [GioiTinh], [DienThoai]) VALUES (N'DG00', N'Ngoan', N'Cà Mau', 1, N'0913139584')
INSERT [dbo].[DocGia] ([MaDG], [TenDG], [DiaChi], [GioiTinh], [DienThoai]) VALUES (N'DG01', N'Hien', N'Cần Thơ', 1, N'0913242856')
INSERT [dbo].[DocGia] ([MaDG], [TenDG], [DiaChi], [GioiTinh], [DienThoai]) VALUES (N'DG02', N'Tuấn', N'Sóc Trăng', 1, N'0976323549')
INSERT [dbo].[DocGia] ([MaDG], [TenDG], [DiaChi], [GioiTinh], [DienThoai]) VALUES (N'DG03', N'Hào', N'Đồng Tháp', 1, N'0942564282')
INSERT [dbo].[DocGia] ([MaDG], [TenDG], [DiaChi], [GioiTinh], [DienThoai]) VALUES (N'DG04', N'Kiệt', N'Cà Mau', 1, N'0919678369')
INSERT [dbo].[DocGia] ([MaDG], [TenDG], [DiaChi], [GioiTinh], [DienThoai]) VALUES (N'DG05', N'Nguyen anh thu', N'Bac Lieu', 0, N'0912124563')
INSERT [dbo].[DocGia] ([MaDG], [TenDG], [DiaChi], [GioiTinh], [DienThoai]) VALUES (N'DG06', N'Phan Anh Tuấn', N'Sóc Trăng', 1, N'0924224465')
INSERT [dbo].[DocGia] ([MaDG], [TenDG], [DiaChi], [GioiTinh], [DienThoai]) VALUES (N'DG07', N'Lê Thị Chăm', N'Cà Mau', 0, N'0924215879')
INSERT [dbo].[DocGia] ([MaDG], [TenDG], [DiaChi], [GioiTinh], [DienThoai]) VALUES (N'DG08', N'Nguyễn Phương Linh', N'Cần Thơ', 0, N'092873586')
GO
INSERT [dbo].[NguoiDung] ([MaND], [TenND], [MatKhau], [VaiTro]) VALUES (N'Admin', N'Admin', N'123456', 1)
INSERT [dbo].[NguoiDung] ([MaND], [TenND], [MatKhau], [VaiTro]) VALUES (N'Hien', N'Hien', N'123456', 0)
INSERT [dbo].[NguoiDung] ([MaND], [TenND], [MatKhau], [VaiTro]) VALUES (N'ND01', N'Tri', N'123456', 0)
INSERT [dbo].[NguoiDung] ([MaND], [TenND], [MatKhau], [VaiTro]) VALUES (N'Ngoantt', N'Ngoan', N'123456', 1)
GO
INSERT [dbo].[PhieuMuon] ([MaPM], [MaDG], [SoLuongMuon], [TienCoc], [NgayMuon], [NgayHenTra]) VALUES (N'P00', N'DG00', 1, 20000, CAST(N'2021-12-12' AS Date), CAST(N'2022-01-01' AS Date))
INSERT [dbo].[PhieuMuon] ([MaPM], [MaDG], [SoLuongMuon], [TienCoc], [NgayMuon], [NgayHenTra]) VALUES (N'P01', N'DG02', 1, 20000, CAST(N'2021-11-12' AS Date), CAST(N'2022-01-02' AS Date))
INSERT [dbo].[PhieuMuon] ([MaPM], [MaDG], [SoLuongMuon], [TienCoc], [NgayMuon], [NgayHenTra]) VALUES (N'P02', N'DG01', 2, 50000, CAST(N'2021-10-23' AS Date), CAST(N'2022-02-01' AS Date))
INSERT [dbo].[PhieuMuon] ([MaPM], [MaDG], [SoLuongMuon], [TienCoc], [NgayMuon], [NgayHenTra]) VALUES (N'P03', N'DG03', 1, 2222, CAST(N'2021-11-22' AS Date), CAST(N'2021-12-22' AS Date))
INSERT [dbo].[PhieuMuon] ([MaPM], [MaDG], [SoLuongMuon], [TienCoc], [NgayMuon], [NgayHenTra]) VALUES (N'P04', N'DG03', 1, 120000, CAST(N'2021-12-23' AS Date), CAST(N'2022-01-23' AS Date))
INSERT [dbo].[PhieuMuon] ([MaPM], [MaDG], [SoLuongMuon], [TienCoc], [NgayMuon], [NgayHenTra]) VALUES (N'P05', N'DG04', 1, 12000, CAST(N'2021-12-02' AS Date), CAST(N'2022-01-22' AS Date))
GO
INSERT [dbo].[Sach] ([MaSach], [TenSach], [TacGia], [NhaXB], [SoLuong], [SoLuongCon], [NgayNhap], [GiaBia]) VALUES (N'S00', N'Java1', N'Vũ Công Tấn Tài', N'NXB Thanh Niên', 2, 1, CAST(N'2021-10-21T00:00:00.000' AS DateTime), 120000)
INSERT [dbo].[Sach] ([MaSach], [TenSach], [TacGia], [NhaXB], [SoLuong], [SoLuongCon], [NgayNhap], [GiaBia]) VALUES (N'S01', N'Java2', N'Vũ Công Tấn Tài', N'NXB Thanh Niên', 2, 0, CAST(N'2021-10-12T00:00:00.000' AS DateTime), 130000)
INSERT [dbo].[Sach] ([MaSach], [TenSach], [TacGia], [NhaXB], [SoLuong], [SoLuongCon], [NgayNhap], [GiaBia]) VALUES (N'S02', N'Java3', N'Vũ Công Tấn Tài', N'NXB Thanh Niên', 2, 1, CAST(N'2021-10-12T00:00:00.000' AS DateTime), 140000)
INSERT [dbo].[Sach] ([MaSach], [TenSach], [TacGia], [NhaXB], [SoLuong], [SoLuongCon], [NgayNhap], [GiaBia]) VALUES (N'S03', N'DORAEMON TRUYỆN NGẮN TẬP 1', N'Fujiko F Fujio', N'NXB Kim Đồng', 8, 8, CAST(N'2021-11-11T00:00:00.000' AS DateTime), 16200)
INSERT [dbo].[Sach] ([MaSach], [TenSach], [TacGia], [NhaXB], [SoLuong], [SoLuongCon], [NgayNhap], [GiaBia]) VALUES (N'S04', N'DORAEMON TRUYỆN NGẮN TẬP 2', N'Fujiko F Fujio', N'NXB Kim Đồng', 8, 8, CAST(N'2021-11-11T00:00:00.000' AS DateTime), 16200)
INSERT [dbo].[Sach] ([MaSach], [TenSach], [TacGia], [NhaXB], [SoLuong], [SoLuongCon], [NgayNhap], [GiaBia]) VALUES (N'S05', N'DORAEMON TRUYỆN NGẮN TẬP 3', N'Fujiko F Fujio', N'NXB Kim Đồng', 8, 7, CAST(N'2021-11-26T00:00:00.000' AS DateTime), 17200)
INSERT [dbo].[Sach] ([MaSach], [TenSach], [TacGia], [NhaXB], [SoLuong], [SoLuongCon], [NgayNhap], [GiaBia]) VALUES (N'S06', N'DORAEMON TRUYỆN NGẮN TẬP 4', N'Fujiko F Fujio', N'NXB Kim Đồng', 8, 8, CAST(N'2021-11-26T00:00:00.000' AS DateTime), 17200)
INSERT [dbo].[Sach] ([MaSach], [TenSach], [TacGia], [NhaXB], [SoLuong], [SoLuongCon], [NgayNhap], [GiaBia]) VALUES (N'S07', N'DORAEMON TRUYỆN NGẮN TẬP 5', N'Fujiko F Fujio', N'NXB Kim Đồng', 8, 8, CAST(N'2021-11-26T00:00:00.000' AS DateTime), 17500)
INSERT [dbo].[Sach] ([MaSach], [TenSach], [TacGia], [NhaXB], [SoLuong], [SoLuongCon], [NgayNhap], [GiaBia]) VALUES (N'S08', N'Đắc Nhân Tâm', N'Dale Carnegie', N'	Simon and Schuster (1936)', 2, 2, CAST(N'2021-11-27T00:00:00.000' AS DateTime), 75800)
INSERT [dbo].[Sach] ([MaSach], [TenSach], [TacGia], [NhaXB], [SoLuong], [SoLuongCon], [NgayNhap], [GiaBia]) VALUES (N'S09', N'Nếu Một Ngày Cậu Bất Chợt Rời Xa', N'Ha Woon Lim', N'NXB Dân Trí', 2, 2, CAST(N'2021-11-27T00:00:00.000' AS DateTime), 78400)
INSERT [dbo].[Sach] ([MaSach], [TenSach], [TacGia], [NhaXB], [SoLuong], [SoLuongCon], [NgayNhap], [GiaBia]) VALUES (N'S10', N'TÔI ƠI ĐỪNG TUYỆT VỌNG!', N'Hiroki Kashiragi', N'NXB Hà Nội', 2, 2, CAST(N'2021-11-27T00:00:00.000' AS DateTime), 58500)
GO
ALTER TABLE [dbo].[DocGia] ADD  DEFAULT ((0)) FOR [GioiTinh]
GO
ALTER TABLE [dbo].[NguoiDung] ADD  DEFAULT ((0)) FOR [VaiTro]
GO
ALTER TABLE [dbo].[PhieuMuon] ADD  CONSTRAINT [DF__PhieuMuon__SoLuo__2B3F6F97]  DEFAULT ((0)) FOR [SoLuongMuon]
GO
ALTER TABLE [dbo].[PhieuMuon] ADD  CONSTRAINT [DF__PhieuMuon__TienC__2C3393D0]  DEFAULT ((0)) FOR [TienCoc]
GO
ALTER TABLE [dbo].[Sach] ADD  CONSTRAINT [DF__Sach__SoLuong__2F10007B]  DEFAULT ((0)) FOR [SoLuong]
GO
ALTER TABLE [dbo].[Sach] ADD  CONSTRAINT [DF__Sach__SoLuongCon__300424B4]  DEFAULT ((0)) FOR [SoLuongCon]
GO
ALTER TABLE [dbo].[Sach] ADD  CONSTRAINT [DF__Sach__GiaBia__30F848ED]  DEFAULT ((0)) FOR [GiaBia]
GO
ALTER TABLE [dbo].[CTPhieuMuon]  WITH CHECK ADD  CONSTRAINT [FK_CTPhieuMuon_PhieuMuon] FOREIGN KEY([MaPM])
REFERENCES [dbo].[PhieuMuon] ([MaPM])
ON UPDATE CASCADE
ON DELETE CASCADE
GO
ALTER TABLE [dbo].[CTPhieuMuon] CHECK CONSTRAINT [FK_CTPhieuMuon_PhieuMuon]
GO
ALTER TABLE [dbo].[CTPhieuMuon]  WITH CHECK ADD  CONSTRAINT [FK_CTPhieuMuon_Sach] FOREIGN KEY([MaSach])
REFERENCES [dbo].[Sach] ([MaSach])
ON UPDATE CASCADE
ON DELETE CASCADE
GO
ALTER TABLE [dbo].[CTPhieuMuon] CHECK CONSTRAINT [FK_CTPhieuMuon_Sach]
GO
ALTER TABLE [dbo].[PhieuMuon]  WITH CHECK ADD  CONSTRAINT [FK_PhieuMuon_DocGia] FOREIGN KEY([MaDG])
REFERENCES [dbo].[DocGia] ([MaDG])
ON UPDATE CASCADE
ON DELETE CASCADE
GO
ALTER TABLE [dbo].[PhieuMuon] CHECK CONSTRAINT [FK_PhieuMuon_DocGia]
GO
/****** Object:  StoredProcedure [dbo].[sp_ThongKeDocGia]    Script Date: 02/12/2021 4:36:00 CH ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
-- =============================================
-- Author:		<Author,,Name>
-- Create date: <Create Date,,>
-- Description:	<Description,,>
-- =============================================
CREATE PROCEDURE [dbo].[sp_ThongKeDocGia]
	-- Add the parameters for the stored procedure here
AS
BEGIN
	-- SET NOCOUNT ON added to prevent extra result sets from
	-- interfering with SELECT statements.
	SET NOCOUNT ON;

    -- Insert statements for procedure here
	SELECT TenDG TenDocGia,
	      COUNT(DISTINCT pm.MaPM) SoPM,
		  MONTH(pm.NgayMuon) ThangMuon,
          COUNT(ct.MaSach) SoSachMuon,
          MIN(ct.MaSach) ThapNhat,
		  MAX(ct.MaSach) CaoNhat
    FROM CTPhieuMuon ct
         JOIN PhieuMuon pm ON ct.MaPM=pm.MaPM
         JOIN DocGia dg ON dg.MaDG=pm.MaDG
    GROUP BY TenDG,MONTH(pm.NgayMuon) 
END

GO
/****** Object:  StoredProcedure [dbo].[sp_ThongKeSach]    Script Date: 02/12/2021 4:36:00 CH ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
-- =============================================
-- Author:		<Author,,Name>
-- Create date: <Create Date,,>
-- Description:	<Description,,>
-- =============================================
CREATE PROCEDURE [dbo].[sp_ThongKeSach] 
	-- Add the parameters for the stored procedure here
AS
BEGIN
	-- SET NOCOUNT ON added to prevent extra result sets from
	-- interfering with SELECT statements.
	SET NOCOUNT ON;

    -- Insert statements for procedure here
	SELECT MONTH(NgayNhap) Thang, Count(*) SoLuong,MIN(NgayNhap) DauTien,MAX(NgayNhap) CuoiCung
	FROM Sach
	GROUP BY MONTH(NgayNhap)
END

GO
/****** Object:  StoredProcedure [dbo].[sp_ThongKeSachMuon]    Script Date: 02/12/2021 4:36:00 CH ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
-- =============================================
-- Author:		<Author,,Name>
-- Create date: <Create Date,,>
-- Description:	<Description,,>
-- =============================================
CREATE PROCEDURE [dbo].[sp_ThongKeSachMuon]
	-- Add the parameters for the stored procedure here
AS
BEGIN
	-- SET NOCOUNT ON added to prevent extra result sets from
	-- interfering with SELECT statements.
	SET NOCOUNT ON;

    -- Insert statements for procedure here
	SELECT  MONTH(NgayTra) Thang, COUNT(MaSach) SoLuong, MIN(MaSach) ItNhat,MAX(MaSach) NhieuNhat
	FROM CTPhieuMuon
	GROUP BY MONTH(NgayTra)
END

GO
USE [master]
GO
ALTER DATABASE [SampleProject1] SET  READ_WRITE 
GO
