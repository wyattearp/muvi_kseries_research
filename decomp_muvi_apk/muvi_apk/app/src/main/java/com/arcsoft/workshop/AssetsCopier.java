// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.arcsoft.workshop;

import android.content.res.AssetManager;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

// Referenced classes of package com.arcsoft.workshop:
//            GlobalConfig

public class AssetsCopier
{

    private AssetManager mAssetManager;

    AssetsCopier(AssetManager assetmanager)
    {
        mAssetManager = null;
        mAssetManager = assetmanager;
    }

    public boolean copyAssetDataFile(AssetManager assetmanager, String s, String s1)
    {
        boolean flag;
        InputStream inputstream;
        FileOutputStream fileoutputstream;
        flag = false;
        inputstream = null;
        fileoutputstream = null;
        if (assetmanager == null) goto _L2; else goto _L1
_L1:
        String s3;
        File file;
        boolean flag2;
        String s2 = s1.substring(0, 1 + s1.lastIndexOf("/")).concat(s);
        s3 = "/data/data/com.arcsoft.mediaplus/".concat(s1);
        inputstream = assetmanager.open(s2);
        file = new File(s3);
        flag2 = file.exists();
        fileoutputstream = null;
        if (flag2)
        {
            break MISSING_BLOCK_LABEL_108;
        }
        File file1;
        boolean flag3;
        file1 = file.getParentFile();
        flag3 = file1.exists();
        fileoutputstream = null;
        if (flag3)
        {
            break MISSING_BLOCK_LABEL_102;
        }
        file1.mkdirs();
        file.createNewFile();
        FileOutputStream fileoutputstream1 = new FileOutputStream(s3, true);
        int i = inputstream.available();
        int j;
        j = 0x10000;
        if (j > i)
        {
            j = i;
        }
        byte abyte0[] = new byte[j];
        int k = 0;
          goto _L3
_L15:
        int l;
        inputstream.read(abyte0, 0, l);
        fileoutputstream1.write(abyte0, 0, l);
        k += l;
          goto _L3
_L13:
        fileoutputstream1.flush();
        flag = true;
        fileoutputstream = fileoutputstream1;
_L2:
        if (inputstream == null)
        {
            break MISSING_BLOCK_LABEL_207;
        }
        inputstream.close();
        if (fileoutputstream == null)
        {
            break MISSING_BLOCK_LABEL_217;
        }
        fileoutputstream.close();
        boolean flag1 = true;
_L4:
        IOException ioexception;
        Exception exception;
        SecurityException securityexception;
        IOException ioexception2;
        NullPointerException nullpointerexception;
        IOException ioexception3;
        IndexOutOfBoundsException indexoutofboundsexception;
        IOException ioexception4;
        IOException ioexception5;
        IOException ioexception6;
        FileNotFoundException filenotfoundexception;
        IOException ioexception7;
        return flag && flag1;
        ioexception;
        ioexception.printStackTrace();
        flag1 = false;
          goto _L4
        filenotfoundexception;
_L11:
        filenotfoundexception.printStackTrace();
        if (inputstream == null)
        {
            break MISSING_BLOCK_LABEL_262;
        }
        inputstream.close();
        if (fileoutputstream == null)
        {
            break MISSING_BLOCK_LABEL_272;
        }
        fileoutputstream.close();
        flag1 = true;
        flag = false;
          goto _L4
        ioexception7;
        ioexception7.printStackTrace();
        flag1 = false;
        flag = false;
          goto _L4
        ioexception5;
_L10:
        ioexception5.printStackTrace();
        if (inputstream == null)
        {
            break MISSING_BLOCK_LABEL_314;
        }
        inputstream.close();
        if (fileoutputstream == null)
        {
            break MISSING_BLOCK_LABEL_324;
        }
        fileoutputstream.close();
        flag1 = true;
        flag = false;
          goto _L4
        ioexception6;
        ioexception6.printStackTrace();
        flag1 = false;
        flag = false;
          goto _L4
        indexoutofboundsexception;
_L9:
        indexoutofboundsexception.printStackTrace();
        if (inputstream == null)
        {
            break MISSING_BLOCK_LABEL_366;
        }
        inputstream.close();
        if (fileoutputstream == null)
        {
            break MISSING_BLOCK_LABEL_376;
        }
        fileoutputstream.close();
        flag1 = true;
        flag = false;
          goto _L4
        ioexception4;
        ioexception4.printStackTrace();
        flag1 = false;
        flag = false;
          goto _L4
        nullpointerexception;
_L8:
        nullpointerexception.printStackTrace();
        if (inputstream == null)
        {
            break MISSING_BLOCK_LABEL_418;
        }
        inputstream.close();
        if (fileoutputstream == null)
        {
            break MISSING_BLOCK_LABEL_428;
        }
        fileoutputstream.close();
        flag1 = true;
        flag = false;
          goto _L4
        ioexception3;
        ioexception3.printStackTrace();
        flag1 = false;
        flag = false;
          goto _L4
        securityexception;
_L7:
        securityexception.printStackTrace();
        if (inputstream == null)
        {
            break MISSING_BLOCK_LABEL_470;
        }
        inputstream.close();
        if (fileoutputstream == null)
        {
            break MISSING_BLOCK_LABEL_480;
        }
        fileoutputstream.close();
        flag1 = true;
        flag = false;
          goto _L4
        ioexception2;
        ioexception2.printStackTrace();
        flag1 = false;
        flag = false;
          goto _L4
        exception;
_L6:
        if (inputstream == null)
        {
            break MISSING_BLOCK_LABEL_517;
        }
        inputstream.close();
        if (fileoutputstream != null)
        {
            try
            {
                fileoutputstream.close();
            }
            catch (IOException ioexception1)
            {
                ioexception1.printStackTrace();
            }
        }
        throw exception;
        exception;
        fileoutputstream = fileoutputstream1;
        if (true) goto _L6; else goto _L5
_L5:
        securityexception;
        fileoutputstream = fileoutputstream1;
          goto _L7
        nullpointerexception;
        fileoutputstream = fileoutputstream1;
          goto _L8
        indexoutofboundsexception;
        fileoutputstream = fileoutputstream1;
          goto _L9
        ioexception5;
        fileoutputstream = fileoutputstream1;
          goto _L10
        filenotfoundexception;
        fileoutputstream = fileoutputstream1;
          goto _L11
_L3:
        if (k >= i) goto _L13; else goto _L12
_L12:
        l = j;
        if (l + k > i)
        {
            l = i - k;
        }
        if (true) goto _L15; else goto _L14
_L14:
    }

    public boolean copyAssetFile()
    {
        if (mAssetManager != null) goto _L2; else goto _L1
_L1:
        return false;
_L2:
        int i;
        int j;
        i = GlobalConfig.ASSETS_DIRS_TO_COPY.length;
        j = 0;
_L5:
        if (j >= i) goto _L4; else goto _L3
_L3:
        String as[];
        int k;
        boolean flag;
        try
        {
            as = mAssetManager.list(GlobalConfig.ASSETS_DIRS_TO_COPY[j]);
        }
        catch (IOException ioexception)
        {
            ioexception.printStackTrace();
            return false;
        }
        if (as == null)
        {
            continue; /* Loop/switch isn't completed */
        }
        k = 0;
        if (k >= as.length)
        {
            continue; /* Loop/switch isn't completed */
        }
        flag = copyAssetDataFile(mAssetManager, as[k], (new StringBuilder()).append(GlobalConfig.ASSETS_DIRS_TO_COPY[j]).append("/").append(as[k]).toString());
        if (flag)
        {
            k++;
            break MISSING_BLOCK_LABEL_43;
        }
        continue; /* Loop/switch isn't completed */
        continue; /* Loop/switch isn't completed */
        j++;
          goto _L5
_L4:
        return true;
        if (true) goto _L1; else goto _L6
_L6:
    }
}
