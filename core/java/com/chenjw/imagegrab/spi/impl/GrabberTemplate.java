package com.chenjw.imagegrab.spi.impl;

import com.chenjw.imagegrab.spi.Grabber;


public abstract class GrabberTemplate  implements Grabber{



    @Override
    public void grabImage() {
        System.out.println("1111");
    }

}
