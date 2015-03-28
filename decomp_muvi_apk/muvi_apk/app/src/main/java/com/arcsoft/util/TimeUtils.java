// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.arcsoft.util;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

public class TimeUtils
{

    public static final String PatternHM = "HH:mm";
    public static final String PatternHMS = "HH:mm:ss";
    public static final String PatternMDY = "MMM d,yyyy";
    public static final String PatternYMDHM = "yyyy-MM-dd HH:mm";
    private static Date m_Date = new Date();
    private static SimpleDateFormat m_DateFormat = new SimpleDateFormat();

    public TimeUtils()
    {
    }

    public static String convertSecToTimeString(long l, boolean flag)
    {
        long l1 = l / 3600L;
        long l2 = l % 3600L;
        long l3 = l2 % 60L;
        long l4 = l2 / 60L;
        if (l1 == 0L && !flag)
        {
            Object aobj1[] = new Object[2];
            aobj1[0] = Long.valueOf(l4);
            aobj1[1] = Long.valueOf(l3);
            return String.format("%02d:%02d", aobj1);
        } else
        {
            Object aobj[] = new Object[3];
            aobj[0] = Long.valueOf(l1);
            aobj[1] = Long.valueOf(l4);
            aobj[2] = Long.valueOf(l3);
            return String.format("%02d:%02d:%02d", aobj);
        }
    }

    public static String convertSecToTimeStringWithoutHour(long l)
    {
        long l1 = l / 60L;
        long l2 = l % 60L;
        Object aobj[] = new Object[2];
        aobj[0] = Long.valueOf(l1);
        aobj[1] = Long.valueOf(l2);
        return String.format("%02d:%02d", aobj);
    }

    public static long convertTimeStringToSec(String s)
    {
        if (s != null) goto _L2; else goto _L1
_L1:
        long l = 0L;
_L4:
        return l;
_L2:
        int j;
        l = 0L;
        int i = s.lastIndexOf('.');
        if (i != -1)
        {
            s = s.substring(0, i);
        }
        j = 0;
_L5:
        int k = s.lastIndexOf(':');
        String s1;
        double d;
        int j1;
        double d1;
        int k1;
        double d2;
        double d3;
        if (k == -1)
        {
            s1 = s;
            s = null;
        } else
        {
            int i1 = k + 1;
            s1 = s.substring(i1);
            s = s.substring(0, k);
        }
        d = l;
        j1 = Integer.valueOf(s1).intValue();
        d1 = j1;
        k1 = j + 1;
        d2 = j;
        d3 = Math.pow(60D, d2);
        l = (long)(d + d1 * d3);
        if (s == null) goto _L4; else goto _L3
_L3:
        j = k1;
          goto _L5
        NumberFormatException numberformatexception;
        numberformatexception;
_L7:
        return 0L;
        NumberFormatException numberformatexception1;
        numberformatexception1;
        if (true) goto _L7; else goto _L6
_L6:
    }

    public static String formatDuration(String s, long l)
    {
        com/arcsoft/util/TimeUtils;
        JVM INSTR monitorenter ;
        String s1;
        TimeZone timezone = m_DateFormat.getTimeZone();
        m_DateFormat.setTimeZone(TimeZone.getTimeZone("GMT"));
        m_DateFormat.applyPattern(s);
        m_Date.setTime(999L + l);
        s1 = m_DateFormat.format(m_Date);
        m_DateFormat.setTimeZone(timezone);
        com/arcsoft/util/TimeUtils;
        JVM INSTR monitorexit ;
        return s1;
        Exception exception;
        exception;
        throw exception;
    }

    public static TimeZone getTimeZome()
    {
        com/arcsoft/util/TimeUtils;
        JVM INSTR monitorenter ;
        TimeZone timezone = m_DateFormat.getTimeZone();
        com/arcsoft/util/TimeUtils;
        JVM INSTR monitorexit ;
        return timezone;
        Exception exception;
        exception;
        throw exception;
    }

    public static String millisecond2String(String s, long l)
    {
        com/arcsoft/util/TimeUtils;
        JVM INSTR monitorenter ;
        String s1;
        synchronized (m_DateFormat)
        {
            m_DateFormat.applyPattern(s);
            m_Date.setTime(l);
            s1 = m_DateFormat.format(m_Date);
        }
        com/arcsoft/util/TimeUtils;
        JVM INSTR monitorexit ;
        return s1;
        exception1;
        simpledateformat;
        JVM INSTR monitorexit ;
        throw exception1;
        Exception exception;
        exception;
        com/arcsoft/util/TimeUtils;
        JVM INSTR monitorexit ;
        throw exception;
    }

    public static String second2String(String s, long l)
    {
        com/arcsoft/util/TimeUtils;
        JVM INSTR monitorenter ;
        long l1 = 1000L * l;
        String s1 = millisecond2String(s, l1);
        com/arcsoft/util/TimeUtils;
        JVM INSTR monitorexit ;
        return s1;
        Exception exception;
        exception;
        throw exception;
    }

    public static void setTimeZome(TimeZone timezone)
    {
        com/arcsoft/util/TimeUtils;
        JVM INSTR monitorenter ;
        m_DateFormat.setTimeZone(timezone);
        com/arcsoft/util/TimeUtils;
        JVM INSTR monitorexit ;
        return;
        Exception exception;
        exception;
        throw exception;
    }

    public static String[] splitDate(String s)
    {
        String as[];
        if (s != null)
        {
            if ((as = s.split("T")) != null && as.length <= 2 && as[0] != null)
            {
                String as1[] = as[0].split("-");
                if (as1 == null || as1.length != 3)
                {
                    as1 = null;
                }
                return as1;
            }
        }
        return null;
    }

}
