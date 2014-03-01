/**
 * Alipay.com Inc.
 * Copyright (c) 2004-2013 All Rights Reserved.
 */
package com.chenjw.imagegrab.ui;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang.StringUtils;

public class SetupConfig {
    public static final String      KEY_TEMPLATE_PATH = "templatePath";

    public static final String      KEY_TEST_PATH     = "testPath";

    public static final String      KEY_CATEGORY_PATH = "categoryPath";

    public static final SetupConfig INSTANCE          = new SetupConfig();
    static {
        INSTANCE.init();
    }
    public Map<String, String>      configs           = new HashMap<String, String>();

    @SuppressWarnings("unchecked")
    public void init() {
        try {
            String setup = null;
            if (EnvUtils.getEnvMode() == EnvUtils.ENV_ECLIPSE) {
                setup = "src/test/resources/setup.ini";
            } else {
                setup = "setup.ini";
            }
            Iterator<String> iterator = FileUtils.lineIterator(new File(setup), "GBK");
            while (iterator.hasNext()) {
                String line = iterator.next();
                if (line.startsWith("#")) {
                    continue;
                }
                String key = StringUtils.substringBefore(line, "=").trim();
                String value = StringUtils.substringAfter(line, "=").trim();
                configs.put(key, value);
            }
        } catch (IOException e) {
           e.printStackTrace();
        }
    }

    public String get(String key) {
        return configs.get(key);
    }
}
