package com.chenjw.imagegrab.service.impl;

import com.chenjw.imagegrab.service.ImagegrabService;
import com.chenjw.imagegrab.ui.DataHandler;

public class ImagegrabServiceImpl implements ImagegrabService{
    private DataHandler dataHandler;
    
    /**
     * 批量下载图片
     */
    public void grab(){
        
    }

    @Override
    public void setDataHandler(DataHandler dataHandler) {
        this.dataHandler=dataHandler;
    }
}
