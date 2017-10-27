package com.rongcheng.erp.utils;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public final class ImgCodeUtil {

	// 验证码字符集
	private static final int[] numbers = { 0, 1, 2, 3, 4, 5, 6, 7, 8, 9 };
	// 字符数量
	private static final int SIZE = 3;
	// 干扰线数量
	private static final int LINES = 10;
	// 宽度
	private static final int WIDTH = 90;
	// 高度
	private static final int HEIGHT = 43;
	// 字体大小
	private static final int FONT_SIZE = 40;

	/**
	 * 生成随机验证码及图片
	 * 
	 */
	public static Object[] createImage() {
		List<Integer> number = new ArrayList<Integer>();
		int symbol = 0;
		// 1.创建空白图片
		BufferedImage image = new BufferedImage(WIDTH, HEIGHT, BufferedImage.TYPE_INT_RGB);
		// 2.获取图片画笔
		Graphics graphic = image.getGraphics();
		// 3.设置画笔颜色
		graphic.setColor(Color.WHITE);
		// 4.绘制矩形背景
		graphic.fillRect(0, 0, WIDTH, HEIGHT);
		// 5.画随机字符
		Random ran = new Random();
		// 6.设置统一字符和干扰线颜色
		Color color = getRandomColor();
		for (int i = 0; i < SIZE; i++) {
			// 取随机字符索引
			int n = ran.nextInt(numbers.length);
			// 设置随机颜色
			graphic.setColor(color);
			// 设置字体大小
			graphic.setFont(new Font("SansSerift", 2, FONT_SIZE + new Random().nextInt(8)));

			int offset = new Random().nextInt(20);
			int charX = i * (WIDTH - 5) / SIZE + offset;
			int charY = HEIGHT / 2 + 13 + offset / 2;

			if (i % 2 == 0) {
				// 画字符
				graphic.drawString(numbers[n] + "", charX, charY);
				// 记录字符
				number.add(numbers[n]);
			} else {
				// 设置字体大小
				graphic.setFont(new Font("SansSerift", 2, FONT_SIZE + new Random().nextInt(10)));
				charX -= 6;
				charY -= 7;
				int r = new Random().nextInt(6);
				if (r / 2 == 0) {
					graphic.drawString("加", charX, charY);
					symbol = 1;
				} else if (r / 2 == 1) {
					graphic.drawString("减", charX, charY);
					symbol = 2;
				} else if (r / 2 == 2) {
					graphic.drawString("成", charX, charY);
					symbol = 3;
				}
			}
		}
		// 6.画干扰线
		for (int i = 0; i < LINES; i++) {
			// 设置随机颜色
			graphic.setColor(color);
			// 设置字体大小
			graphic.setFont(new Font("SansSerift", 4, FONT_SIZE - new Random().nextInt(12)));
			// 随机画线
			graphic.drawLine(ran.nextInt(WIDTH), ran.nextInt(HEIGHT), ran.nextInt(WIDTH), ran.nextInt(HEIGHT));
		}
		String result = "";
		try {
			switch (symbol) {
			case 1:
				result = number.get(0) + number.get(1) + "";
				break;
			case 2:
				result = number.get(0) - number.get(1) + "";
				break;
			case 3:
				result = number.get(0) * number.get(1) + "";
				break;
			}
		} catch (Exception e) {
		}
		// 7.返回验证码和图片
		return new Object[] { result, image };
	}

	/**
	 * 随机取色
	 */
	public static Color getRandomColor() {
		Random ran = new Random();
		Color color = new Color(ran.nextInt(156), ran.nextInt(156), ran.nextInt(156));
		return color;
	}
}
