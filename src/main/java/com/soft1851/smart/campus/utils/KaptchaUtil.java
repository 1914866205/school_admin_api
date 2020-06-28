package com.soft1851.smart.campus.utils;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

/**
 * @Description TODO
 * @Author wf
 * @Date 2020/6/10
 * @Version 1.0
 */
public class KaptchaUtil {

    static StringBuilder result = new StringBuilder();  // 运算验证码结果
    /**
     * 生成运算验证码
     * @return  运算验证码
     */
    public static Map<String, Object> generatorOperationVerificationCode() {
        //  验证码图片边框宽度
        final int WIDTH = 200;
        //  验证码图片边框高度
        final int HEIGHT = 40;
        //  验证码字体高度
        int FONT_HEIGHT = HEIGHT - 12;
        //  验证码干扰线条数
        int INTERFERENCE_LINE = 4;

        Map<String, Object> verificationCodeMap = null;

        //  生成透明rgb图片
        BufferedImage bufferedImage = new BufferedImage(WIDTH, HEIGHT, BufferedImage.TYPE_INT_ARGB);
        Graphics graphics = bufferedImage.getGraphics();
        //  备份原始画笔颜色
        Color color = graphics.getColor();
        graphics.setColor(Color.WHITE);
        //  图片填充黑色
        graphics.fillRect(0, 0, WIDTH, HEIGHT);

        graphics.setColor(Color.WHITE);
        //  图片填充白色；组成黑色边框的白色图片
        graphics.fillRect(1, 1, WIDTH - 2, HEIGHT - 2);

        //  验证码字符串
        String text = getText();
        System.out.println("验证码" + text);
        //  运算表达式
        //String operationExpression = text.substring(0, text.lastIndexOf("@") - 1);
        String x = text.substring(text.indexOf("x") + 1, text.indexOf("&"));
        String y = text.substring(text.indexOf("y") + 1, text.indexOf("="));
        String operator = text.substring(text.indexOf("&") + 1, text.indexOf("y"));
        String equal = text.substring(text.indexOf("="), text.indexOf("@"));
        System.out.println();
        //  计算结果
        String result = text.substring(text.lastIndexOf("@") + 1, text.length());

        //int newFontHeight = operationExpression.length() > 4 ? FONT_HEIGHT * 4 / operationExpression.length() : FONT_HEIGHT;

        //  设置画笔字体
        Font font = new Font("微软雅黑", Font.PLAIN, FONT_HEIGHT);
        graphics.setFont(font);
        //  根据系统时间创建随机数对象
        Random random = new Random(System.currentTimeMillis());
        int r = 0;
        int g = 0;
        int b = 0;

        //   随机生成rgb颜色值，并设置画笔颜色
        r = random.nextInt(255);
        g = random.nextInt(255);
        b = random.nextInt(255);
        graphics.setColor(new Color(r));
        //  根据画笔颜色绘制字符
        //graphics.drawString(operationExpression, 5, FONT_HEIGHT);
        graphics.drawString(x, 5, FONT_HEIGHT);
        r = random.nextInt(255);
        g = random.nextInt(255);
        b = random.nextInt(255);
        graphics.setColor(new Color(r, g, b));
        graphics.drawString(operator, 50, FONT_HEIGHT);
        r = random.nextInt(255);
        g = random.nextInt(255);
        b = random.nextInt(255);
        graphics.setColor(new Color(r, g, b));
        graphics.drawString(y, 85, FONT_HEIGHT);
        r = random.nextInt(255);
        g = random.nextInt(255);
        b = random.nextInt(255);
        graphics.setColor(new Color(r, g, b));
        graphics.drawString(equal, 130, FONT_HEIGHT);

        //  绘制干扰线
        int x1, y1, x2, y2;
        for (int i = 0; i < INTERFERENCE_LINE; i++) {
            //   随机生成rgb颜色值，并设置画笔颜色
            r = random.nextInt(255);
            g = random.nextInt(255);
            b = random.nextInt(255);
            graphics.setColor(new Color(r, g, b));
            x1 = random.nextInt(WIDTH);
            y1 = random.nextInt(HEIGHT);
            x2 = random.nextInt(WIDTH);
            y2 = random.nextInt(HEIGHT);
            //  绘制线条
            graphics.drawLine(x1, y1, x2, y2);
        }

        //  恢复画笔颜色
        graphics.setColor(color);

        verificationCodeMap = new HashMap<String, Object>();
        verificationCodeMap.put("verificationCodeImage", bufferedImage);
        verificationCodeMap.put("verificationCode", result);
        return verificationCodeMap;

    }

    /**
     * 获取运算验证码
     * @return 运算验证码
     */
    public static String getText() {
        Random random = new Random(System.currentTimeMillis());
        int x = random.nextInt(99);
        int y = random.nextInt(99);
        int operationalRules = random.nextInt(4);
        System.out.println("x的值: " + x);
        System.out.println("y的值: " + y);
        System.out.println(result);
        if(result.length() > 0){
            result.delete(0, result.length() - 1);
        }
        switch (operationalRules) {
            case 0:
                add(x, y);
                break;
            case 1:
                subtract(x, y);
                break;
            case 2:
                multiply(x, y);
                break;
            case 3:
                divide(x, y);
                break;
        }
        return result.toString();
    }

    /**
     * 加法运算
     * @param x 变量x
     * @param y 变量y
     */
    private static void add(int x, int y) {
//        result = null;
        result.append("x");
        result.append(x);
        result.append("&");
        result.append(" + ");
        result.append("y");
        result.append(y);
        result.append(" = ?@");
        result.append(x + y);
    }

    /**
     * 减法运算
     * @param x 变量x
     * @param y 变量y
     */
    private static void subtract(int x, int y) {
        int max = Math.max(x, y);
        int min = Math.min(x, y);
        result.append("x");
        result.append(max);
        result.append("&");
        result.append(" - ");
        result.append("y");
        result.append(min);
        result.append(" = ?@");
        result.append(max - min);
    }

    /**
     * 乘法运算
     * @param x 变量x
     * @param y 变量y
     */
    private static void multiply(int x, int y) {
        int value = x * y;
        result.append("x");
        result.append(x);
        result.append("&");
        result.append(value > 100 ? " + " : " * ");
        result.append("y");
        result.append(y);
        result.append(" = ?@");
        result.append(value > 100 ? x + y : x * y);
    }

    /**
     * 出发运算
     * @param x 变量x
     * @param y 变量y
     */
    private static void divide(int x, int y) {
        int max = Math.max(x, y);
        int min = Math.min(x, y);
        if (min == 0) {
            multiply(max, min);
        } else if (max % min == 0) {
            result.append("x");
            result.append(max);
            result.append("&");
            result.append(" / ");
            result.append("y");
            result.append(min);
            result.append(" = ?@");
            result.append(max / min);
        } else {
            result.append("x");
            result.append(max);
            result.append("&");
            result.append(" % ");
            result.append("y");
            result.append(min);
            result.append(" = ?@");
            result.append(max % min);
        }
    }


    public static void main(String[] args) throws IOException {
        Map<String, Object> operationMap = generatorOperationVerificationCode();
        BufferedImage bufferedImage2 = (BufferedImage) operationMap.get("verificationCodeImage");

        OutputStream outputStream2 = new FileOutputStream("D://code.png");
        ImageIO.write(bufferedImage2, "png", outputStream2);
        System.out.println("验证码： " + operationMap.get("verificationCode"));
        outputStream2.flush();
        outputStream2.close();
    }

}
