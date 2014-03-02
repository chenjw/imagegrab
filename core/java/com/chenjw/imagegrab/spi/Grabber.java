package com.chenjw.imagegrab.spi;

import com.chenjw.imagegrab.ui.DataHandler;


public interface Grabber {
    public String id();
    
    public String name();
    
    public void grabImage(DataHandler dataHandler);
}
