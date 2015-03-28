// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.arcsoft.mediaplus.updownload.service;

import com.arcsoft.util.debug.Log;
import java.io.UnsupportedEncodingException;

// Referenced classes of package com.arcsoft.mediaplus.updownload.service:
//            UpDownloadEngine

private static class 
{

    static String truncateUTF8(String s, int i)
    {
        byte abyte0[];
        String s1;
        try
        {
            abyte0 = s.getBytes("UTF-8");
        }
        catch (UnsupportedEncodingException unsupportedencodingexception)
        {
            unsupportedencodingexception.printStackTrace();
            Log.e("UpDownloadEngine", "truncate title not support utf-8");
            return null;
        }
        if (i < abyte0.length) goto _L2; else goto _L1
_L1:
        Log.i("UpDownloadEngine", "profile title not need truncate");
        s1 = new String(abyte0);
_L4:
        return s1;
_L2:
        Log.i("UpDownloadEngine", "profile title too long, need truncate");
        int j = -1 + abyte0.length;
        do
        {
            s1 = null;
            if (j < 0)
            {
                continue;
            }
            if (abyte0[j] >= -64 && abyte0[j] <= -4 && j <= i)
            {
                String s2 = new String(abyte0, 0, j);
                Log.i("UpDownloadEngine", (new StringBuilder()).append("profile title truncate string =").append(s2).toString());
                return s2;
            }
            j--;
        } while (true);
        if (true) goto _L4; else goto _L3
_L3:
    }

    private ()
    {
    }
}
