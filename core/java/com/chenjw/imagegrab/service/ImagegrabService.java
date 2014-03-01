package com.chenjw.imagegrab.service;

import com.chenjw.imagegrab.ui.DataHandler;

public interface ImagegrabService {
    /**
     * 批量下载图片
     */
    public void grab();
    
    public void setDataHandler(DataHandler dataHandler);
}
