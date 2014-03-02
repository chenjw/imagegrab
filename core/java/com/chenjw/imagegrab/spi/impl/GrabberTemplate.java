package com.chenjw.imagegrab.spi.impl;

import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import javax.imageio.ImageIO;

import org.apache.commons.io.FileUtils;

import com.chenjw.imagegrab.spi.Grabber;
import com.chenjw.imagegrab.ui.DataHandler;
import com.chenjw.imagegrab.ui.SetupConfig;

public abstract class GrabberTemplate implements Grabber {
    // 用于异步执行任务
    private ExecutorService executeService = Executors.newFixedThreadPool(40);
    private File downloadFolder=new File(SetupConfig.INSTANCE.get(SetupConfig.KEY_DOWNLOAD_PATH));
    
    protected File getFileExist(String fileName,String folder){
        File f=new File(new File(downloadFolder,folder),fileName);
        if(f.exists()){
            return f;
        }
        else{
            return null;
        }
    }
    
    protected File saveFile(String fileName,String folder,byte[] bytes) throws IOException{
        File f=new File(new File(downloadFolder,folder),fileName);
        FileUtils.writeByteArrayToFile(f, bytes);
        return f;
    }
    
    protected void execute(Runnable command) {
        executeService.execute(command);
    }

    abstract protected void doGrab(DataHandler dataHandler);

    @Override
    public void grabImage(DataHandler dataHandler) {

        doGrab(dataHandler);
    }

    protected int getAvgGray(byte[] bytes) {
        long num = 0;
        long grayValue = 0;
        BufferedImage img;
        try {
            img = ImageIO.read(new ByteArrayInputStream(bytes));
            for (int i = 0; i < img.getWidth(); i++) {
                for (int j = 0; j < img.getHeight(); j++) {
                    int gray = getGray(img, i, j);
                    grayValue += gray;
                    num++;
                }
            }
            return (int)(grayValue / num);
        } catch (IOException e) {
            e.printStackTrace();
            return 0;
        }

    }

    private int getGray(BufferedImage img, int x, int y) {
        Object data = img.getRaster().getDataElements(x, y, null);//获取该点像素，并以object类型表示  
        int red = img.getColorModel().getRed(data);
        int blue = img.getColorModel().getBlue(data);
        int green = img.getColorModel().getGreen(data);
        int pixelGray = (int) (0.3 * red + 0.59 * green + 0.11 * blue);//计算每个坐标点的灰度
        int rgb = (pixelGray << 16) + (pixelGray << 8) + (pixelGray);
        return rgb;
    }

}
