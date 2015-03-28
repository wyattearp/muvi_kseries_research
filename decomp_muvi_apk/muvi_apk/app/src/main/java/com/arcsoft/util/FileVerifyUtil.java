// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.arcsoft.util;

import com.arcsoft.util.debug.Log;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.lang.reflect.Array;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;

public class FileVerifyUtil
{

    private static final int BUFF_SIZE = 512;
    private static final String FILE_DIR = "/system/lib/";
    private static final String FILE_LIST[] = {
        "/system/lib/libmedia.so", "/system/lib/libmediaplayerservice.so", "/system/lib/libstagefright.so"
    };
    private static final String MD5_FILE = "/system/arcsoft/mediaplus/lib/libprivate.so";
    private static final int MD5_LENGTH = 16;
    private static final byte MD5_LIST[][];
    private static final byte MD5_key[] = {
        97, 114, 98, 111
    };
    private static final byte MD5_start[] = new byte[400];
    private static final int MD5_startLength = 400;

    public FileVerifyUtil()
    {
    }

    public static void generateMD5File()
    {
        FileOutputStream fileoutputstream = null;
        FileOutputStream fileoutputstream1 = new FileOutputStream("/system/arcsoft/mediaplus/lib/libprivate.so", false);
        int i = FILE_LIST.length;
        int j = 0;
_L2:
        if (j >= i)
        {
            break; /* Loop/switch isn't completed */
        }
        fileoutputstream1.write(getMD5FromFile(FILE_LIST[j]));
        j++;
        if (true) goto _L2; else goto _L1
_L1:
        if (fileoutputstream1 == null)
        {
            break MISSING_BLOCK_LABEL_151;
        }
        fileoutputstream1.close();
_L4:
        return;
        IOException ioexception4;
        ioexception4;
        ioexception4.printStackTrace();
        return;
        FileNotFoundException filenotfoundexception;
        filenotfoundexception;
_L9:
        filenotfoundexception.printStackTrace();
        if (fileoutputstream == null) goto _L4; else goto _L3
_L3:
        try
        {
            fileoutputstream.close();
            return;
        }
        catch (IOException ioexception1)
        {
            ioexception1.printStackTrace();
        }
        return;
        IOException ioexception2;
        ioexception2;
_L8:
        ioexception2.printStackTrace();
        if (fileoutputstream == null) goto _L4; else goto _L5
_L5:
        try
        {
            fileoutputstream.close();
            return;
        }
        catch (IOException ioexception3)
        {
            ioexception3.printStackTrace();
        }
        return;
        Exception exception;
        exception;
_L7:
        if (fileoutputstream != null)
        {
            try
            {
                fileoutputstream.close();
            }
            catch (IOException ioexception)
            {
                ioexception.printStackTrace();
            }
        }
        throw exception;
        exception;
        fileoutputstream = fileoutputstream1;
        if (true) goto _L7; else goto _L6
_L6:
        ioexception2;
        fileoutputstream = fileoutputstream1;
          goto _L8
        filenotfoundexception;
        fileoutputstream = fileoutputstream1;
          goto _L9
    }

    private static byte[] getMD5FromFile(String s)
    {
        FileInputStream fileinputstream = null;
        FileInputStream fileinputstream1 = new FileInputStream(s);
        MessageDigest messagedigest;
        byte abyte0[];
        messagedigest = MessageDigest.getInstance("MD5");
        abyte0 = new byte[512];
_L3:
        int i = fileinputstream1.read(abyte0);
        if (i <= 0) goto _L2; else goto _L1
_L1:
        messagedigest.update(abyte0, 0, i);
          goto _L3
        NoSuchAlgorithmException nosuchalgorithmexception;
        nosuchalgorithmexception;
        fileinputstream = fileinputstream1;
_L12:
        nosuchalgorithmexception.printStackTrace();
        byte abyte1[];
        if (fileinputstream != null)
        {
            try
            {
                fileinputstream.close();
            }
            catch (IOException ioexception4)
            {
                ioexception4.printStackTrace();
                return null;
            }
        }
_L5:
        return null;
_L2:
        abyte1 = messagedigest.digest();
        FileNotFoundException filenotfoundexception;
        Exception exception;
        IOException ioexception2;
        if (fileinputstream1 != null)
        {
            try
            {
                fileinputstream1.close();
            }
            catch (IOException ioexception5)
            {
                ioexception5.printStackTrace();
                return abyte1;
            }
            return abyte1;
        } else
        {
            return abyte1;
        }
        filenotfoundexception;
_L10:
        filenotfoundexception.printStackTrace();
        if (fileinputstream == null) goto _L5; else goto _L4
_L4:
        try
        {
            fileinputstream.close();
        }
        catch (IOException ioexception1)
        {
            ioexception1.printStackTrace();
            return null;
        }
        return null;
        ioexception2;
_L9:
        ioexception2.printStackTrace();
        if (fileinputstream == null) goto _L5; else goto _L6
_L6:
        try
        {
            fileinputstream.close();
        }
        catch (IOException ioexception3)
        {
            ioexception3.printStackTrace();
            return null;
        }
        return null;
        exception;
_L8:
        if (fileinputstream != null)
        {
            try
            {
                fileinputstream.close();
            }
            catch (IOException ioexception)
            {
                ioexception.printStackTrace();
            }
        }
        throw exception;
        exception;
        fileinputstream = fileinputstream1;
        if (true) goto _L8; else goto _L7
_L7:
        ioexception2;
        fileinputstream = fileinputstream1;
          goto _L9
        filenotfoundexception;
        fileinputstream = fileinputstream1;
          goto _L10
        nosuchalgorithmexception;
        fileinputstream = null;
        if (true) goto _L12; else goto _L11
_L11:
    }

    private static boolean initMD5List()
    {
        FileInputStream fileinputstream = null;
        FileInputStream fileinputstream1 = new FileInputStream("/system/arcsoft/mediaplus/lib/libprivate.so");
        fileinputstream1.read(MD5_start);
        int i = 0;
_L15:
        if (i >= MD5_LIST.length) goto _L2; else goto _L1
_L1:
        int j = fileinputstream1.read(MD5_LIST[i]);
        if (j >= 16) goto _L4; else goto _L3
_L3:
        if (fileinputstream1 != null)
        {
            try
            {
                fileinputstream1.close();
            }
            catch (IOException ioexception5)
            {
                ioexception5.printStackTrace();
            }
        }
_L8:
        return false;
_L4:
        int k = 0;
_L6:
        if (k >= 16)
        {
            break; /* Loop/switch isn't completed */
        }
        MD5_LIST[i][k] = (byte)(MD5_LIST[i][k] ^ MD5_key[k % 4]);
        k++;
        if (true) goto _L6; else goto _L5
_L5:
        i++;
        continue; /* Loop/switch isn't completed */
_L2:
        if (fileinputstream1 != null)
        {
            try
            {
                fileinputstream1.close();
            }
            catch (IOException ioexception4)
            {
                ioexception4.printStackTrace();
                return true;
            }
            return true;
        }
        break MISSING_BLOCK_LABEL_230;
        FileNotFoundException filenotfoundexception;
        filenotfoundexception;
_L13:
        filenotfoundexception.printStackTrace();
        if (fileinputstream == null) goto _L8; else goto _L7
_L7:
        try
        {
            fileinputstream.close();
        }
        catch (IOException ioexception1)
        {
            ioexception1.printStackTrace();
            return false;
        }
        return false;
        IOException ioexception2;
        ioexception2;
_L12:
        ioexception2.printStackTrace();
        if (fileinputstream == null) goto _L8; else goto _L9
_L9:
        try
        {
            fileinputstream.close();
        }
        catch (IOException ioexception3)
        {
            ioexception3.printStackTrace();
            return false;
        }
        return false;
        Exception exception;
        exception;
_L11:
        if (fileinputstream != null)
        {
            try
            {
                fileinputstream.close();
            }
            catch (IOException ioexception)
            {
                ioexception.printStackTrace();
            }
        }
        throw exception;
        exception;
        fileinputstream = fileinputstream1;
        if (true) goto _L11; else goto _L10
_L10:
        ioexception2;
        fileinputstream = fileinputstream1;
          goto _L12
        filenotfoundexception;
        fileinputstream = fileinputstream1;
          goto _L13
        return true;
        if (true) goto _L15; else goto _L14
_L14:
    }

    public static void print()
    {
        int i = FILE_LIST.length;
        for (int j = 0; j < i; j++)
        {
            byte abyte0[] = getMD5FromFile(FILE_LIST[j]);
            System.out.println((new StringBuilder()).append("MD5: ").append(FILE_LIST[j]).toString());
            System.out.print("MD5: ");
            int k = abyte0.length;
            for (int l = 0; l < k; l++)
            {
                byte byte0 = abyte0[l];
                PrintStream printstream = System.out;
                Object aobj[] = new Object[1];
                aobj[0] = Byte.valueOf(byte0);
                printstream.print(String.format("%X, ", aobj));
            }

            System.out.println();
        }

    }

    public static boolean verify()
    {
        if (!initMD5List())
        {
            return false;
        }
        boolean flag = true;
        int i = FILE_LIST.length;
        int j = 0;
        do
        {
label0:
            {
                if (j < i)
                {
                    byte abyte0[] = getMD5FromFile(FILE_LIST[j]);
                    if (abyte0 != null && Arrays.equals(abyte0, MD5_LIST[j]))
                    {
                        break label0;
                    }
                    flag = false;
                }
                Log.d("FileVerifyUtil", (new StringBuilder()).append("CHECK = ").append(flag).toString());
                return flag;
            }
            j++;
        } while (true);
    }

    static 
    {
        int ai[] = {
            FILE_LIST.length, 16
        };
        MD5_LIST = (byte[][])Array.newInstance(Byte.TYPE, ai);
    }
}
