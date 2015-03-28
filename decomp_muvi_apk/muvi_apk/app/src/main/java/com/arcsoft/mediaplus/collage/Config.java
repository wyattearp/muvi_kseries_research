// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.arcsoft.mediaplus.collage;

import android.media.ExifInterface;
import android.os.Environment;
import com.arcsoft.mediaplus.oem.OEMConfig;
import java.io.File;
import java.io.IOException;

public class Config
{

    public static final String COLLAGE_DIR;
    public static final String COLLAGE_FILE_NAME_BASE = "Collage_";
    public static final String COLLAGE_FILE_TYPE = ".jpg";

    public Config()
    {
    }

    public static boolean checkSaveDir()
    {
        File file;
        if (Environment.getExternalStorageState().equals("mounted"))
        {
            if ((file = new File(COLLAGE_DIR)).exists() || file.mkdir())
            {
                return true;
            }
        }
        return false;
    }

    public static int getThumbFromExif(String s)
    {
        ExifInterface exifinterface;
        int i;
        try
        {
            exifinterface = new ExifInterface(s);
        }
        catch (IOException ioexception)
        {
            return 0;
        }
        if (exifinterface == null) goto _L2; else goto _L1
_L1:
        i = exifinterface.getAttributeInt("Orientation", -1);
        if (i == -1) goto _L2; else goto _L3
_L3:
        i;
        JVM INSTR tableswitch 3 8: default 64
    //                   3 69
    //                   4 64
    //                   5 64
    //                   6 66
    //                   7 64
    //                   8 73;
           goto _L2 _L4 _L2 _L2 _L5 _L2 _L6
_L2:
        return 0;
_L5:
        return 90;
_L4:
        return 180;
_L6:
        return 270;
    }

    static 
    {
        COLLAGE_DIR = OEMConfig.DOWNLOAD_PATH;
    }
}
