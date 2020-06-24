package com.huijian.rac.bean;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.File;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class QRCode {
    //正文
    private String contents;
    //二维码宽度
    private int width;
    //二维码高度
    private int height;
   //图片格式
    private String format;
    //编码方式
    private String character_set;
    //字体大小
    private int fontSize;
    //logo
    private File logoFile;
    //logo所占二维码比例
    private float logoRatio;
    //二维码下文字
    private String desc;
    //下方日期
    private String date;
    //白边的宽度
    private int whiteWidth;
    //二维码最下边的开始坐标
    private int[] bottomStart;
    //二维码最下边的结束坐标
    private int[] bottomEnd;

}
