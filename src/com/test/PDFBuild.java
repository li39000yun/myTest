package com.test;

import java.io.*;
import com.itextpdf.text.*;
import com.itextpdf.text.pdf.*;

/**
 * PDF生成
 */
public class PDFBuild {

	public static void buidPDF(String pdfFile, String imageFile, String waterMarkName, int permission) {
		try {
			File file = File.createTempFile("tempFile", ".pdf"); // 创建临时文件

			// 生成PDF
			if (createPDFFile(file)) {
				waterMark(file.getPath(), imageFile, pdfFile, waterMarkName, permission); // 添加水印
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 创建PDF文件
	 * 
	 * @param file
	 *            临时文件
	 * @return 成功/失败
	 */
	public static boolean createPDFFile(File file) {
		Rectangle rect = new Rectangle(PageSize.A4);
		Document doc = new Document(rect, 50.0F, 50.0F, 50.0F, 50.0F);
		try {
			PdfWriter.getInstance(doc, new FileOutputStream(file));
			doc.open();

			BaseFont bfChinese = BaseFont.createFont("STSong-Light", "UniGB-UCS2-H", BaseFont.NOT_EMBEDDED); // 设置中文字体
			Font font10 = new Font(bfChinese, 10, Font.NORMAL);// 设置字体大小
			Font font8 = new Font(bfChinese, 8, Font.NORMAL);// 设置字体大小
			Font font6 = new Font(bfChinese, 6, Font.NORMAL);// 设置字体大小

			PdfPTable table;
			PdfPCell cell;
			float baseHeight = 15f;// 基础高度
			table = new PdfPTable(2);
			table.setTotalWidth(500f);
			table.setLockedWidth(true);
			table.setHorizontalAlignment(Element.ALIGN_CENTER);
			table.getDefaultCell().setBorder(0);

			// 绘制表格
			// 设置宽度
			table = new PdfPTable(new float[] { 95f, 75f, 60f, 60f, 55f, 60f, 95f });
			table.setTotalWidth(500f);
			table.setLockedWidth(true);
			table.setHorizontalAlignment(Element.ALIGN_CENTER);

			cell = new PdfPCell(new Phrase("Shipper's Name and Address", font8));
			cell.setColspan(3);// 合并3列
			cell.setFixedHeight(baseHeight);// 设置固定高度
			table.addCell(cell);

			cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
			setHideLine(cell, true, true, true, false);// 隐藏右边边线
			cell.setRowspan(2);// 合并2行
			cell.setFixedHeight(4 * baseHeight);// 设置固定高度
			table.addCell(cell);

			cell = new PdfPCell(new Phrase("OCEAN \n BILL OF LADING", font10));
			setHideLine(cell, true, true, false, true);// 隐藏左边边线
			cell.setColspan(3);// 合并3列
			cell.setRowspan(2);// 合并2行
			cell.setHorizontalAlignment(Element.ALIGN_CENTER);// 水平居中
			cell.setVerticalAlignment(Element.ALIGN_CENTER);// 垂直居中
			table.addCell(cell);

			// 换行
			/** 发货人 */
			cell = new PdfPCell(new Phrase("发货", font8));
			cell.setColspan(3);// 合并3列
			cell.setRowspan(3);// 合并3行
			cell.setFixedHeight(5 * baseHeight);// 设置固定高度
			table.addCell(cell);

			cell = new PdfPCell(new Phrase("Export Reference", font8));
			cell.setColspan(2);// 合并2列
			cell.setFixedHeight(baseHeight);// 设置固定高度
			table.addCell(cell);

			cell = new PdfPCell(new Phrase("B/L Number", font8));
			setHideLine(cell, true, false, true, false);// 隐藏右下边线
			table.addCell(cell);

			/** 提单号 */
			cell = new PdfPCell(new Phrase("-----", font8));
			setHideLine(cell, true, true, false, true);// 隐藏左边线
			cell.setRowspan(2);// 合并2行
			cell.setVerticalAlignment(Element.ALIGN_CENTER);// 垂直居中
			table.addCell(cell);

			cell = new PdfPCell(new Phrase("-----", font8));
			cell.setColspan(2);// 合并2列
			cell.setFixedHeight(baseHeight);// 设置固定高度
			table.addCell(cell);

			cell = new PdfPCell(new Phrase("", font8));
			setHideLine(cell, false, true, true, false);// 隐藏右上边线
			table.addCell(cell);

			// 换行
			cell = new PdfPCell(new Phrase("Consignee's Name and Address", font8));
			setHideLine(cell, true, false, true, true);// 隐藏下边线
			cell.setColspan(3);// 合并3列
			cell.setFixedHeight(baseHeight);// 设置固定高度
			table.addCell(cell);

			cell = new PdfPCell(new Phrase("For Cargo Delivery,Please Contact:", font8));
			cell.setColspan(4);// 合并4列
			table.addCell(cell);

			// 换行
			/** 收货人 */
			cell = new PdfPCell(new Phrase("-----", font8));
			setHideLine(cell, false, true, true, true);// 隐藏上边线
			cell.setColspan(3);// 合并3列
			cell.setFixedHeight(4 * baseHeight);// 设置固定高度
			table.addCell(cell);

			/** 目的港代理 */
			cell = new PdfPCell(new Phrase("-----", font8));
			cell.setColspan(4);// 合并4列
			table.addCell(cell);

			// 换行
			cell = new PdfPCell(new Phrase("Notify Party's Name Address", font8));
			setHideLine(cell, true, false, true, true);// 隐藏下边线
			cell.setColspan(3);// 合并3列
			cell.setFixedHeight(baseHeight);// 设置固定高度
			table.addCell(cell);

			cell = new PdfPCell(new Phrase("Also Notify", font8));
			setHideLine(cell, true, false, true, true);// 隐藏下边线
			cell.setColspan(4);// 合并4列
			table.addCell(cell);

			// 换行
			/** 通知人 */
			cell = new PdfPCell(new Phrase("-----", font8));
			setHideLine(cell, false, true, true, true);// 隐藏上边线
			cell.setColspan(3);// 合并3列
			cell.setFixedHeight(4 * baseHeight);// 设置固定高度
			table.addCell(cell);

			cell = new PdfPCell(new Phrase("", font8));
			setHideLine(cell, false, true, true, true);// 隐藏上边线
			cell.setColspan(4);// 合并4列
			table.addCell(cell);

			// 换行
			cell = new PdfPCell(new Phrase("*Pre-carriage by", font8));
			setHideLine(cell, true, false, true, true);// 隐藏下边线
			cell.setColspan(2);// 合并2列
			cell.setFixedHeight(baseHeight);// 设置固定高度
			table.addCell(cell);

			cell = new PdfPCell(new Phrase("*Place of Receipt", font8));
			setHideLine(cell, true, false, true, true);// 隐藏下边线
			cell.setColspan(2);// 合并2列
			table.addCell(cell);

			cell = new PdfPCell(new Phrase("Point and Country of Origin of Goods", font8));
			setHideLine(cell, true, false, true, true);// 隐藏下边线
			cell.setColspan(3);// 合并3列
			table.addCell(cell);

			// 换行
			/** 前段运输 */
			cell = new PdfPCell(new Phrase("-----", font8));
			setHideLine(cell, false, true, true, true);// 隐藏上边线
			cell.setColspan(2);// 合并2列
			cell.setFixedHeight(baseHeight);// 设置固定高度
			table.addCell(cell);

			/** 收货地址 */
			cell = new PdfPCell(new Phrase("-----", font8));
			setHideLine(cell, false, true, true, true);// 隐藏上边线
			cell.setColspan(2);// 合并2列
			table.addCell(cell);

			cell = new PdfPCell(new Phrase("", font8));
			setHideLine(cell, false, true, true, true);// 隐藏上边线
			cell.setColspan(3);// 合并3列
			table.addCell(cell);

			// 换行
			cell = new PdfPCell(new Phrase("Ocean Vessel/Voyage", font8));
			setHideLine(cell, true, false, true, true);// 隐藏下边线
			cell.setColspan(2);// 合并2列
			cell.setFixedHeight(baseHeight);// 设置固定高度
			table.addCell(cell);

			cell = new PdfPCell(new Phrase("Port of Loading", font8));
			setHideLine(cell, true, false, true, true);// 隐藏下边线
			cell.setColspan(2);// 合并2列
			table.addCell(cell);

			cell = new PdfPCell(new Phrase("No. of Original B/l", font8));
			setHideLine(cell, true, false, true, true);// 隐藏下边线
			cell.setColspan(3);// 合并3列
			table.addCell(cell);

			// 换行
			/** 船名+航次 */
			cell = new PdfPCell(new Phrase("-----" + " / " + "-----", font8));
			setHideLine(cell, false, true, true, true);// 隐藏上边线
			cell.setColspan(2);// 合并2列
			cell.setFixedHeight(baseHeight);// 设置固定高度
			table.addCell(cell);

			/** 装货港 */
			cell = new PdfPCell(new Phrase("-----", font8));
			setHideLine(cell, false, true, true, true);// 隐藏上边线
			cell.setColspan(2);// 合并2列
			table.addCell(cell);

			/** 正本张数 */
			cell = new PdfPCell(new Phrase("-----", font8));
			setHideLine(cell, false, true, true, true);// 隐藏上边线
			cell.setColspan(3);// 合并3列
			table.addCell(cell);

			// 换行
			cell = new PdfPCell(new Phrase("Port of Discharge", font8));
			setHideLine(cell, true, false, true, true);// 隐藏下边线
			cell.setColspan(2);// 合并2列
			cell.setFixedHeight(baseHeight);// 设置固定高度
			table.addCell(cell);

			cell = new PdfPCell(new Phrase("*Place of Delivery", font8));
			setHideLine(cell, true, false, true, true);// 隐藏下边线
			cell.setColspan(2);// 合并2列
			table.addCell(cell);

			cell = new PdfPCell(new Phrase("Final Destination(For the Merchant's Reference Only)", font8));
			setHideLine(cell, true, false, true, true);// 隐藏下边线
			cell.setColspan(3);// 合并3列
			table.addCell(cell);

			// 换行
			/** 目的港 */
			cell = new PdfPCell(new Phrase("-----", font8));
			setHideLine(cell, false, true, true, true);// 隐藏上边线
			cell.setColspan(2);// 合并2列
			cell.setFixedHeight(baseHeight);// 设置固定高度
			table.addCell(cell);

			/** 目的地 */
			cell = new PdfPCell(new Phrase("-----", font8));
			setHideLine(cell, false, true, true, true);// 隐藏上边线
			cell.setColspan(2);// 合并2列
			table.addCell(cell);

			cell = new PdfPCell(new Phrase("", font8));
			setHideLine(cell, false, true, true, true);// 隐藏上边线
			cell.setColspan(3);// 合并3列
			table.addCell(cell);

			// 换一行
			cell = new PdfPCell(new Phrase("", font8));
			cell.setColspan(7);// 合并7列
			cell.setFixedHeight(5f);// 设置固定高度
			table.addCell(cell);

			// 换一行
			cell = new PdfPCell(new Phrase("Particulars Fumished by Shipper", font8));
			cell.setColspan(7);// 合并7列
			cell.setFixedHeight(baseHeight);// 设置固定高度
			cell.setHorizontalAlignment(Element.ALIGN_CENTER);
			table.addCell(cell);

			// 换行
			cell = new PdfPCell(new Phrase("Marks and Numbers", font8));
			setHideLine(cell, true, false, true, false);// 隐藏右下边线
			cell.setFixedHeight(baseHeight);// 设置固定高度
			table.addCell(cell);

			cell = new PdfPCell(new Phrase("No of Packages", font8));
			setHideLine(cell, true, false, false, false);// 隐藏左右下边线
			table.addCell(cell);

			cell = new PdfPCell(new Phrase("Description of Packages and Goods", font8));
			setHideLine(cell, true, false, false, false);// 隐藏左右下边线
			cell.setColspan(3);// 合并3列
			table.addCell(cell);

			cell = new PdfPCell(new Phrase("Gross Weight", font8));
			setHideLine(cell, true, false, false, false);// 隐藏左右下边线
			table.addCell(cell);

			cell = new PdfPCell(new Phrase("Measurement", font8));
			setHideLine(cell, true, false, false, true);// 隐藏左下边线
			table.addCell(cell);

			// 换行
			/** 标记唛头 */
			cell = new PdfPCell(new Phrase("-----", font8));
			setHideLine(cell, false, false, true, false);// 隐藏右下边线
			cell.setFixedHeight(6 * baseHeight);// 设置固定高度
			table.addCell(cell);

			/** 件数 */
			cell = new PdfPCell(new Phrase("-----", font8));
			setHideLine(cell, false, false, false, false);// 隐藏左右下边线
			table.addCell(cell);

			/** 货物英文名称 */
			cell = new PdfPCell(new Phrase("-----", font8));
			setHideLine(cell, false, false, false, false);// 隐藏左右下边线
			cell.setColspan(3);// 合并3列
			table.addCell(cell);

			/** 毛重 */
			cell = new PdfPCell(new Phrase("-----", font8));
			setHideLine(cell, false, false, false, false);// 隐藏左右下边线
			table.addCell(cell);

			/** 体积 */
			cell = new PdfPCell(new Phrase("-----", font8));
			setHideLine(cell, false, false, false, true);// 隐藏左下边线
			table.addCell(cell);

			// 换行
			cell = new PdfPCell(new Phrase("ContainerNo:", font8));
			setHideLine(cell, false, false, true, false);// 隐藏右上下边线
			cell.setFixedHeight(baseHeight);// 设置固定高度
			table.addCell(cell);

			cell = new PdfPCell(new Phrase("Container Type:", font8));
			setHideLine(cell, false, false, false, false);// 隐藏边线
			table.addCell(cell);

			cell = new PdfPCell(new Phrase("Seal No:", font8));
			setHideLine(cell, false, false, false, false);// 隐藏边线
			cell.setColspan(3);// 合并3列
			table.addCell(cell);

			cell = new PdfPCell(new Phrase("", font8));
			setHideLine(cell, false, false, false, false);// 隐藏边线
			table.addCell(cell);

			cell = new PdfPCell(new Phrase("", font8));
			setHideLine(cell, false, false, false, true);// 隐藏左上下边线
			table.addCell(cell);

			// 换行
			/** 箱号 */
			cell = new PdfPCell(new Phrase("-----", font8));
			setHideLine(cell, false, true, true, false);// 隐藏右上边线
			cell.setFixedHeight(5 * baseHeight);// 设置固定高度
			table.addCell(cell);

			/** 箱型/箱量 */
			cell = new PdfPCell(new Phrase("-----", font8));
			setHideLine(cell, false, true, false, false);// 隐藏左右上边线
			cell.setFixedHeight(5 * baseHeight);// 设置固定高度
			table.addCell(cell);

			/** 封条号 */
			cell = new PdfPCell(new Phrase("-----", font8));
			setHideLine(cell, false, true, false, false);// 隐藏左右上边线
			cell.setFixedHeight(5 * baseHeight);// 设置固定高度
			table.addCell(cell);

			String piece = "";// 件数
			String packing = "";// 规格
			String weight = "";// 重量
			String cubage = "";// 体积

			/** 件数 **/
			cell = new PdfPCell(new Phrase(piece, font8));
			setHideLine(cell, false, true, false, false);// 隐藏左右上边线
			table.addCell(cell);

			/** 规格 **/
			cell = new PdfPCell(new Phrase(packing, font8));
			setHideLine(cell, false, true, false, false);// 隐藏左右上边线
			table.addCell(cell);

			/** 重量 **/
			cell = new PdfPCell(new Phrase(weight, font8));
			setHideLine(cell, false, true, false, false);// 隐藏左右上边线
			table.addCell(cell);

			/** 体积 **/
			cell = new PdfPCell(new Phrase(cubage, font8));
			setHideLine(cell, false, true, false, true);// 隐藏左右上边线
			table.addCell(cell);

			// 换一行
			cell = new PdfPCell(new Phrase("Total No.Container or Packages or units received by the Carrier(in Words)", font8));
			cell.setColspan(7);// 合并7列
			setHideLine(cell, true, false, true, true);// 隐藏下边线
			cell.setFixedHeight(baseHeight);// 设置固定高度
			table.addCell(cell);

			// 换一行
			/** 箱量描述 */
			cell = new PdfPCell(new Phrase("-----", font8));
			cell.setColspan(7);// 合并7列
			setHideLine(cell, false, true, true, true);// 隐藏下边线
			cell.setFixedHeight(baseHeight);// 设置固定高度
			table.addCell(cell);

			// 换行
			cell = new PdfPCell(new Phrase("Freight and Charges", font8));
			cell.setHorizontalAlignment(Element.ALIGN_CENTER);// 居中
			cell.setFixedHeight(baseHeight);// 设置固定高度
			table.addCell(cell);

			cell = new PdfPCell(new Phrase("Prepaid", font8));
			cell.setHorizontalAlignment(Element.ALIGN_CENTER);// 居中
			table.addCell(cell);

			cell = new PdfPCell(new Phrase("Collect", font8));
			cell.setHorizontalAlignment(Element.ALIGN_CENTER);// 居中
			table.addCell(cell);

			cell = new PdfPCell(new Phrase("Date and Place of issue", font8));
			cell.setColspan(4);// 合并4列
			setHideLine(cell, true, false, true, true);// 隐藏下边线
			table.addCell(cell);

			// 换行
			cell = new PdfPCell(new Phrase("AS ARRANGED", font8));
			cell.setHorizontalAlignment(Element.ALIGN_CENTER);// 居中
			cell.setFixedHeight(baseHeight);// 设置固定高度
			table.addCell(cell);

			/** 付款地 */
			String data = "";
			cell = new PdfPCell(new Phrase(data, font8));
			cell.setHorizontalAlignment(Element.ALIGN_CENTER);// 居中
			table.addCell(cell);

			/** 付款地 */
			data = "";
			cell = new PdfPCell(new Phrase(data, font8));
			cell.setHorizontalAlignment(Element.ALIGN_CENTER);// 居中
			table.addCell(cell);

			/** 签单地点+签单日期 */
			cell = new PdfPCell(new Phrase("-----", font8));
			cell.setColspan(4);// 合并4列
			setHideLine(cell, false, true, true, true);// 隐藏上边线
			table.addCell(cell);

			// 换行
			cell = new PdfPCell(new Phrase("", font8));
			setHideLine(cell, true, false, true, true);// 隐藏下边线
			cell.setFixedHeight(baseHeight);// 设置固定高度
			table.addCell(cell);

			cell = new PdfPCell(new Phrase("", font8));
			setHideLine(cell, true, false, true, true);// 隐藏下边线
			table.addCell(cell);

			cell = new PdfPCell(new Phrase("", font8));
			setHideLine(cell, true, false, true, true);// 隐藏下边线
			table.addCell(cell);

			cell = new PdfPCell(new Phrase("Laden on Board the Vessel and date", font8));
			cell.setColspan(4);// 合并4列
			setHideLine(cell, true, false, true, true);// 隐藏下边线
			table.addCell(cell);

			// 换行
			cell = new PdfPCell(new Phrase("", font8));
			setHideLine(cell, false, true, true, true);// 隐藏上边线
			cell.setFixedHeight(baseHeight);// 设置固定高度
			table.addCell(cell);

			cell = new PdfPCell(new Phrase("", font8));
			setHideLine(cell, false, true, true, true);// 隐藏上边线
			table.addCell(cell);

			cell = new PdfPCell(new Phrase("", font8));
			setHideLine(cell, false, true, true, true);// 隐藏上边线
			table.addCell(cell);

			/** 船名+航次 */
			cell = new PdfPCell(new Phrase("-----" + " / " + "-----" + "    " + "-----", font8));
			cell.setColspan(4);// 合并4列
			setHideLine(cell, false, true, true, true);// 隐藏上边线
			table.addCell(cell);

			// 换行
			data = "RECEIVED in apparent good order and condition except as otherwise noted the total number of Containers or other packages and units" + " enumerated below for transportation from the place of receipt to the place of delivery subject to the terms and conditions here of. One of the signed original Bills of Lading" + " must be surrendered duly endorsed in exchange for the Goods or Delivery Order. On presentation of this document duly endorsed to the Carrier by or on behalf of the Holder of" + " the Bill of Lading, the rights and liabilities arising in accordance with the terms and conditions here of shall(without prejudice to any rule of common law or statute rendering" + " them binding on the Merchant) become binding in all respects between the Carrier and the Holder as though the contract evidenced hereby had been made between them. " + " \nIN WITNESS whereof the number of original Bills of Lading stated under have been" + "signed, all of this tenor and date, one of which being accomplished, the other(s) to be void.";
			cell = new PdfPCell(new Phrase(data, font6));
			cell.setFixedHeight(5 * baseHeight);// 设置固定高度
			cell.setColspan(3);// 合并3列
			table.addCell(cell);

			cell = new PdfPCell(new Phrase("", font8));
			cell.setColspan(4);// 合并4列
			table.addCell(cell);
			// 添加表格
			doc.add(table);
			doc.close();
			return true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

	/**
	 * 设置边线是否显示
	 * 
	 * @param cell
	 *            单元格
	 * @param top
	 *            顶部是否显示
	 * @param buttom
	 *            顶部是否显示
	 * @param left
	 *            左边是否显示
	 * @param right
	 *            右边是否显示
	 */
	private static void setHideLine(PdfPCell cell, boolean top, boolean buttom, boolean left, boolean right) {
		if (!top) {
			cell.setBorderWidthTop(0f);// 顶部去掉线条
		}
		if (!buttom) {
			cell.setBorderWidthBottom(0f);// 底部去掉线条
		}
		if (!left) {
			cell.setBorderWidthLeft(0f);// 左边去掉线条
		}
		if (!right) {
			cell.setBorderWidthRight(0f);// 右边去掉线条
		}
	}

	public static void waterMark(String inputFile, String imageFile, String outputFile, String waterMarkName, int permission) {
		try {
			PdfReader reader = new PdfReader(inputFile);
			PdfStamper stamper = new PdfStamper(reader, new FileOutputStream(outputFile));

			BaseFont base = BaseFont.createFont("C:/WINDOWS/Fonts/SIMSUN.TTC,1", "Identity-H", true);// 使用系统字体

			int total = reader.getNumberOfPages() + 1;
			Image image = Image.getInstance(imageFile);

			// 图片位置
			image.setAbsolutePosition(200, 480);
			PdfContentByte under;
			int j = waterMarkName.length();
			char c = 0;
			int rise = 0;
			for (int i = 1; i < total; i++) {
				rise = 400;
				under = stamper.getUnderContent(i);
				under.beginText();
				under.setFontAndSize(base, 30);

				if (j >= 15) {
					under.setTextMatrix(200, 120);
					for (int k = 0; k < j; k++) {
						under.setTextRise(rise);
						c = waterMarkName.charAt(k);
						under.showText(c + "");
					}
				} else {
					under.setTextMatrix(240, 100);
					for (int k = 0; k < j; k++) {
						under.setTextRise(rise);
						c = waterMarkName.charAt(k);
						under.showText(c + "");
						rise -= 18;

					}
				}

				// 添加水印文字
				under.endText();

				// 添加水印图片
				under.addImage(image);

				// 画个圈
				under.ellipse(250, 450, 350, 550);
				under.setLineWidth(1f);
				under.stroke();
			}
			stamper.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		String imageFilePath = "D:/itext2.png"; // 水印图片路径
		String pdfFilePath = "D:/itext.pdf"; // 文件生成路径
		buidPDF(pdfFilePath, imageFilePath, "正版授权", 16);
	}
}