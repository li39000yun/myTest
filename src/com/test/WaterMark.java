package com.test;

import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.FileOutputStream;

import javax.swing.ImageIcon;

import com.sun.image.codec.jpeg.JPEGCodec;
import com.sun.image.codec.jpeg.JPEGEncodeParam;
import com.sun.image.codec.jpeg.JPEGImageEncoder;

public class WaterMark {

	/**
	 * <style type="text/css">body{background:#C7EDCC;}</style> 在图片上添加水印图片或水印文字
	 * 
	 * @param filePath
	 *            原始图片路径
	 * @param watermarkPath
	 *            水印图片路径
	 * @param watermarkX
	 *            水印位置:x
	 * @param watermarkY
	 *            水印位置:y
	 * @param watermarkAlpha
	 *            透明度
	 * @return
	 */
	public static boolean createMark(String filePath, String watermarkPath, int watermarkX, int watermarkY, float watermarkAlpha) {
		// 读取原图片
		ImageIcon imgIcon = new ImageIcon(filePath);
		Image theImg = imgIcon.getImage();
		// 读取标签图片
		ImageIcon waterIcon = new ImageIcon(watermarkPath);
		Image waterImg = waterIcon.getImage();

		int width = theImg.getWidth(null);
		int height = theImg.getHeight(null);

		// 创建一个和原图片同大小的新空白图片
		BufferedImage bimage = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
		Graphics2D g = bimage.createGraphics();

		// bimage = g.getDeviceConfiguration().createCompatibleImage(width,
		// height, Transparency.TRANSLUCENT);
		// g.dispose();
		// g = bimage.createGraphics();

		// 设置字体
		// Font font = new Font("SansSerif", Font.BOLD, 30);
		// g.setFont(font);
		// 设置前景色
		// g.setColor(Color.red);
		// 设置背景色
		g.setBackground(Color.white);
		// 画原图
		g.drawImage(theImg, 0, 0, null);

		// 值从0f-1.0f
		g.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_ATOP, watermarkAlpha));
		// 画出水印图
		g.drawImage(waterImg, watermarkX, watermarkY, waterImg.getWidth(null), waterImg.getHeight(null), null);

		// 画字
		 g.drawString("制作水印", 50, 50);

		// 透明度设置 结束
		g.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER));

		g.dispose();
		FileOutputStream out = null;
		try {
			String newWaterFile = filePath.substring(0, filePath.lastIndexOf(".")) + "_water." + filePath.substring(filePath.lastIndexOf(".") + 1);
			out = new FileOutputStream(newWaterFile);
			JPEGImageEncoder encoder = JPEGCodec.createJPEGEncoder(out);
			JPEGEncodeParam param = encoder.getDefaultJPEGEncodeParam(bimage);
			param.setQuality(50f, true);
			encoder.encode(bimage, param);
		} catch (Exception e) {
			System.out.println("--- 生成失败---");
			return false;
		} finally {
			if (out != null) {
				try {
					out.close();
					out = null;
				} catch (Exception e) {
				}
			}
		}
		System.out.println("===生成成功===");
		return true;
	}

	public static void main(String[] args) {
		createMark("D:\\test.jpg", "D:\\test1.png", 500, 300, 1f);
	}
}
