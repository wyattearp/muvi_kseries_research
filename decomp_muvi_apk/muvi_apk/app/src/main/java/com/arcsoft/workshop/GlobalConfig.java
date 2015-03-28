// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.arcsoft.workshop;

import com.arcsoft.mediaplus.oem.OEMConfig;
import java.io.File;

public class GlobalConfig
{

    public static final String ASSETS_DIRS_TO_COPY[] = {
        "data/contents/frames/frames", "data/contents/background"
    };
    public static final String EDITOR_SAVE_DIR;
    public static final String EDITOR_SAVE_TMPDIR;
    public static final int IO_BUFFER_SIZE = 0x10000;
    public static final String MEDIAPLUS_DIR;
    public static final String WORKSHOP_ASSETS_FILE = "workshop_assets";
    public static final String WORKSHOP_DATA_DIR = "/data/data/com.arcsoft.mediaplus/data/";
    public static final String WORKSHOP_DIR = "/data/data/com.arcsoft.mediaplus/";

    public GlobalConfig()
    {
    }

    static 
    {
        MEDIAPLUS_DIR = OEMConfig.DOWNLOAD_PATH;
        EDITOR_SAVE_DIR = MEDIAPLUS_DIR;
        EDITOR_SAVE_TMPDIR = (new StringBuilder()).append(MEDIAPLUS_DIR).append(File.separator).append(".Temp").toString();
    }
}
