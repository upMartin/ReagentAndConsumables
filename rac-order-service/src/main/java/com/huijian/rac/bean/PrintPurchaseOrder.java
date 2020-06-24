package com.huijian.rac.bean;

import org.springframework.stereotype.Component;

import java.awt.*;
import java.awt.print.PageFormat;
import java.awt.print.Printable;
import java.awt.print.PrinterException;

@Component
public class PrintPurchaseOrder implements Printable {
    //字体
    private Font font;

    @Override
    public int print(Graphics graphics, PageFormat pageFormat, int pageIndex) throws PrinterException {
        return 0;
    }
}
