// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.arcsoft.util.oem;

import java.io.File;
import java.io.FilenameFilter;

public class SharpUtils
{

    public SharpUtils()
    {
    }

    public static String getMPOFilePath(String s)
    {
        File file = new File(s);
        String s1 = file.getName();
        int i = s1.lastIndexOf('.');
        if (i > 0)
        {
            s1 = s1.substring(0, i);
        }
        FilenameFilter filenamefilter = new FilenameFilter(s1) {

            final String val$cmpname;

            public boolean accept(File file2, String s2)
            {
                int j = s2.lastIndexOf('.');
                if (j >= 0)
                {
                    String s3 = s2.substring(j).toLowerCase();
                    if (s2.substring(0, j).equals(cmpname) && s3.equals(".mpo"))
                    {
                        return true;
                    }
                }
                return false;
            }

            
            {
                cmpname = s;
                super();
            }
        };
        File file1 = file.getParentFile();
        String as[] = file.getParentFile().list(filenamefilter);
        if (as != null && as.length > 0)
        {
            return (new StringBuilder()).append(file1.getAbsolutePath()).append("/").append(as[0]).toString();
        } else
        {
            return null;
        }
    }

    public static String getValue(String s, String s1, String s2, String s3)
    {
        if (s != null && s3 != null) goto _L2; else goto _L1
_L1:
        return null;
_L2:
        int k;
        if (s1 == null)
        {
            continue; /* Loop/switch isn't completed */
        }
        if ((k = s.indexOf(s1)) > -1)
        {
            int l = s.indexOf(s3, k + s1.length());
            if (l > -1)
            {
                return s.substring(k + s1.length(), l);
            }
        }
        continue; /* Loop/switch isn't completed */
        if (s2 == null) goto _L1; else goto _L3
_L3:
        int i = s.indexOf(s2);
        if (i > 0)
        {
            int j = s.indexOf(s3, i + s2.length());
            if (j > -1)
            {
                return s.substring(i, j);
            }
        }
        if (true) goto _L1; else goto _L4
_L4:
    }
}
