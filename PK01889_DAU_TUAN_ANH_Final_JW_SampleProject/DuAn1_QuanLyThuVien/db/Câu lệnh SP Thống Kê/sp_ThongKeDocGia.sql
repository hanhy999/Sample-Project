-- ================================================
-- Template generated from Template Explorer using:
-- Create Procedure (New Menu).SQL
--
-- Use the Specify Values for Template Parameters 
-- command (Ctrl-Shift-M) to fill in the parameter 
-- values below.
--
-- This block of comments will not be included in
-- the definition of the procedure.
-- ================================================
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
-- =============================================
-- Author:		<Author,,Name>
-- Create date: <Create Date,,>
-- Description:	<Description,,>
-- =============================================
CREATE PROCEDURE sp_ThongKeDocGia
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
