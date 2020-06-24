package com.huijian.rac.controller;

import com.huijian.rac.bean.GoodsRecord;
import com.huijian.rac.bean.QRCode;
import com.huijian.rac.bean.WarehouseRecord;
import com.huijian.rac.utils.QRCodeGenerator;
import com.sun.org.apache.xerces.internal.impl.dv.util.Base64;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.imageio.ImageIO;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/stock")
public class QRCodeController {

    @RequestMapping("/QRCode")
    @ResponseBody
    public void getQRCode(HttpServletResponse response, @RequestBody GoodsRecord goodsRecord) throws IOException {
        /*List<String> strings = new ArrayList<>();*/
        OutputStream os = response.getOutputStream();
        QRCode info = new QRCode();
        info.setContents(goodsRecord.getName());
        info.setWidth(120);
        info.setHeight(120);
        info.setFontSize(12);
        info.setCharacter_set("UTF-8");
        QRCodeGenerator qrCodeGenerator = new QRCodeGenerator();
        BufferedImage image = qrCodeGenerator.createCodeImage(info);
        ImageIO.write(image, "PNG", os);
        os.flush();
        os.close();
    }

}
