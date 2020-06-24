package com.huijian.rac.utils;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel;
import com.huijian.rac.bean.QRCode;
import java.awt.image.BufferedImage;
import java.util.HashMap;
import java.util.Map;

public class QRCodeGenerator {

    private static int BLACK = 0x000000;
    private static int WHITE = 0xFFFFFF;

    /**
     * 1.创建最原始的二维码图片
     * @param info
     * @return
     */
    public BufferedImage createCodeImage(QRCode info){
        String contents = info.getContents();
        int width = info.getWidth();
        int height = info.getHeight();
        Map<EncodeHintType, Object> hint = new HashMap<>();
        //设置二维码的纠错级别 级别分别为M L H Q ，H纠错能力级别最高，约可纠错30%的数据码字
        hint.put(EncodeHintType.ERROR_CORRECTION, ErrorCorrectionLevel.H);
        //设置二维码编码方式 UTF-8
        hint.put(EncodeHintType.CHARACTER_SET, info.getCharacter_set());
        hint.put(EncodeHintType.MARGIN, 0);
        MultiFormatWriter writer = new MultiFormatWriter();
        BufferedImage img = null;
        try{
            //构建二维码图片
            //QR_CODE 一种矩阵二维码
            BitMatrix bm = writer.encode(contents, BarcodeFormat.QR_CODE, width, height, hint);
            int[] locationTopLeft = bm.getTopLeftOnBit();
            int[] locationBottomRight = bm.getBottomRightOnBit();
            info.setBottomStart(new int[]{locationTopLeft[0], locationBottomRight[1]});
            info.setBottomEnd(locationBottomRight);
            int w = bm.getWidth();
            int h = bm.getHeight();
            img = new BufferedImage(w, h, BufferedImage.TYPE_INT_RGB);
            for (int x = 0; x < w; x++) {
                for (int y = 0; y < h; y++) {
                    img.setRGB(x, y, bm.get(x, y) ? BLACK : WHITE);
                }
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return img;
    }
}
