/*
package com.huijian.rac.bean;

import org.springframework.stereotype.Component;

import javax.servlet.ServletRequestEvent;
import javax.servlet.ServletRequestListener;
import javax.servlet.annotation.WebListener;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@WebListener
@Component
public class RequestListener implements ServletRequestListener {
    @Override
    public void requestInitialized(ServletRequestEvent sre)  {
        //将所有request请求都携带上httpSession
        ((HttpServletRequest) sre.getServletRequest()).getSession();
    }

    public RequestListener() {}

    @Override
    public void requestDestroyed(ServletRequestEvent sre)  {
        ServletRequestListener.super.requestDestroyed(sre);
    }
}
*/
