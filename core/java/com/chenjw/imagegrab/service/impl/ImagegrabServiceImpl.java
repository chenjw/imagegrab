package com.chenjw.imagegrab.service.impl;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;

import com.alibaba.fastjson.JSON;
import com.chenjw.dynacomponent.util.ComponentUtils;
import com.chenjw.imagegrab.container.GrabberContainer;
import com.chenjw.imagegrab.service.ImagegrabService;
import com.chenjw.imagegrab.spi.Grabber;
import com.chenjw.imagegrab.ui.DataHandler;

public class ImagegrabServiceImpl implements ImagegrabService{
    private DataHandler dataHandler;
    
    /**
     * 批量下载图片
     */
    public void grab(){

        String sourceId=dataHandler.getSource();
        GrabberContainer container=ComponentUtils.getContainer("grabber");
        Grabber grabber=container.getById(sourceId);
        if(grabber!=null){
            grabber.grabImage(dataHandler);;
        }
        else{
            System.out.println("Grabber "+sourceId+" not found!");
        }

    }

    @Override
    public void setDataHandler(DataHandler dataHandler) {
        this.dataHandler=dataHandler;
    }
    
    public static void main(String[] args) throws IOException{
        String text=FileUtils.readFileToString(new File("/home/chenjw/test/imagegrab/1.txt"));
        JSON.parse(text);
    }
}
