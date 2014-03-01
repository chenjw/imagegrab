package com.chenjw.imagegrab.ui;

public interface DataHandler {

    public String getSearchWord();

    public String getSource();

    public String getGrayThreshold();

    public void appendResult(String text);

    public void clearResult();
}