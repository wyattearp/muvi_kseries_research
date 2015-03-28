// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.arcsoft.adk.atv;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class UPnPOperationsParameterUtils
{

    public UPnPOperationsParameterUtils()
    {
    }

    protected static String getProtocol_DLNA_ORG_OP_KEY(String s)
    {
        Matcher matcher = Pattern.compile("(DLNA.ORG_OP=)[0-9a-zA-Z_]+").matcher(s);
        if (matcher.find())
        {
            return matcher.group().replace("DLNA.ORG_OP=", "");
        } else
        {
            return null;
        }
    }

    protected static boolean[] getSeekMode(String s)
    {
        boolean aflag[] = {
            0, 0
        };
        if (s != null)
        {
            String s1 = getProtocol_DLNA_ORG_OP_KEY(s);
            if (s1 != null && s1.length() == 2)
            {
                if (Integer.parseInt(new String(s1.toCharArray(), 0, 1)) == 1)
                {
                    aflag[0] = true;
                }
                if (Integer.parseInt(new String(s1.toCharArray(), 1, 1)) == 1)
                {
                    aflag[1] = true;
                }
            }
        }
        return aflag;
    }

    public static boolean isSupportByteBasedSeek(String s)
    {
        boolean aflag[] = getSeekMode(s);
        if (aflag != null && aflag.length == 2)
        {
            return aflag[1];
        } else
        {
            return false;
        }
    }

    public static boolean isSupportTimeBasedSeek(String s)
    {
        boolean aflag[] = getSeekMode(s);
        boolean flag = false;
        if (aflag != null)
        {
            int i = aflag.length;
            flag = false;
            if (i == 2)
            {
                flag = aflag[0];
            }
        }
        return flag;
    }
}
