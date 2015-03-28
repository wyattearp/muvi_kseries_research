// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.arcsoft.util.tool;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public final class DateConvert
{

    public DateConvert()
    {
    }

    public static String dateToString(Date date)
    {
        return (new SimpleDateFormat("yyyy-MM-dd HH:mm:ss")).format(date);
    }

    public static String dateToString(Date date, int i)
    {
        return (new SimpleDateFormat("yyyy-MM-dd KK:mm:ss a")).format(date);
    }

    public static Date stringToDate(String s)
        throws ParseException
    {
        String s1;
        SimpleDateFormat simpledateformat;
        int j;
        s1 = s.trim();
        int i = s1.indexOf("AD");
        simpledateformat = new SimpleDateFormat("yyyy.MM.dd G 'at' hh:mm:ss z");
        if (i > -1)
        {
            s1 = (new StringBuilder()).append(s1.substring(0, i)).append("\u516C\u5143").append(s1.substring(i + "AD".length())).toString();
            simpledateformat = new SimpleDateFormat("yyyy.MM.dd G 'at' hh:mm:ss z");
        }
        j = s1.indexOf("-");
        if (s1.indexOf("-") <= -1 || s1.indexOf(" ") >= 0 || s1.length() != 10) goto _L2; else goto _L1
_L1:
        simpledateformat = new SimpleDateFormat("yyyy-MM-dd");
_L4:
        return simpledateformat.parse(s1);
_L2:
        if (j > -1 && s1.indexOf(" ") < 0)
        {
            simpledateformat = new SimpleDateFormat("yyyyMMddHHmmssZ");
        } else
        if (s1.indexOf("/") > -1 && s1.indexOf(" ") > -1)
        {
            simpledateformat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
        } else
        if (s1.indexOf("-") > -1 && s1.indexOf(" ") > -1)
        {
            simpledateformat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        } else
        if (s1.indexOf("-") > -1 && s1.indexOf("T") > -1)
        {
            simpledateformat = new SimpleDateFormat("yyyy-MM-ddTHH:mm:ss");
        } else
        if (s1.indexOf("/") > -1 && s1.indexOf("am") > -1 || s1.indexOf("pm") > -1)
        {
            simpledateformat = new SimpleDateFormat("yyyy-MM-dd KK:mm:ss a");
        } else
        if (s1.indexOf("-") > -1 && s1.indexOf("am") > -1 || s1.indexOf("pm") > -1)
        {
            simpledateformat = new SimpleDateFormat("yyyy-MM-dd KK:mm:ss a");
        }
        if (true) goto _L4; else goto _L3
_L3:
    }
}
