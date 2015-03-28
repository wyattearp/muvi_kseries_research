// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.arcsoft.adk.atv;


public class DLNAUtil
{

    public static final String UPNP_PN = "DLNA.ORG_PN";

    public DLNAUtil()
    {
    }

    public static boolean IsSupportTimeSeek(String s)
    {
        if (s != null) goto _L2; else goto _L1
_L1:
        String s1;
        return false;
_L2:
        if (!(s1 = s.toUpperCase()).contains("DLNA.ORG_OP") || !s1.contains("HTTP-GET")) goto _L1; else goto _L3
_L3:
        int i;
        int j;
        i = s1.indexOf("DLNA.ORG_OP");
        j = s1.indexOf('=', i);
        if (j <= i || j >= s1.length()) goto _L1; else goto _L4
_L4:
        int k = j + 1;
_L6:
        if (k >= s1.length())
        {
            continue; /* Loop/switch isn't completed */
        }
        if (s1.charAt(k) == ' ')
        {
            break MISSING_BLOCK_LABEL_121;
        }
        j = k;
        if (j >= s1.length()) goto _L1; else goto _L5
_L5:
        String s2 = s1.substring(j, j + 2);
        if (s2 != null && s2.charAt(0) == '1')
        {
            return true;
        }
          goto _L1
        k++;
          goto _L6
    }
}
