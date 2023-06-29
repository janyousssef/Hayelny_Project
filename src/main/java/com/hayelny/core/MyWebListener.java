package com.hayelny.core;

import jakarta.servlet.ServletRequestEvent;
import jakarta.servlet.ServletRequestListener;
import jakarta.servlet.annotation.WebListener;
import lombok.extern.slf4j.Slf4j;

@WebListener
@Slf4j
public class MyWebListener implements ServletRequestListener {
    @Override
    public void requestInitialized(ServletRequestEvent sre) {
      log.info("Request made to "+ sre.getServletRequest().getLocalAddr()+":"+sre.getServletRequest().getLocalPort());
    }
}
