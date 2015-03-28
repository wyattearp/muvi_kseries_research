// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.arcsoft.mediaplus.datasource;

import android.net.Uri;

// Referenced classes of package com.arcsoft.mediaplus.datasource:
//            Property, IDataSource

public final class VideoDataSourceHelper
{

    private VideoDataSourceHelper()
    {
    }

    public static long getAddedTime(int i, IDataSource idatasource)
    {
        long l = 0L;
        if (idatasource != null)
        {
            l = idatasource.getLongProp(i, Property.PROP_ADDED_TIME, l);
        }
        return l;
    }

    public static long getDuration(int i, IDataSource idatasource)
    {
        long l = 0L;
        if (idatasource != null)
        {
            l = idatasource.getLongProp(i, Property.PROP_DURATION, l);
        }
        return l;
    }

    public static int getHeight(int i, IDataSource idatasource)
    {
        int j = 0;
        if (idatasource != null)
        {
            j = idatasource.getIntProp(i, Property.PROP_HEIGHT, 0);
        }
        return j;
    }

    public static String getMimeType(int i, IDataSource idatasource)
    {
        return null;
    }

    public static long getModifiedTime(int i, IDataSource idatasource)
    {
        long l = 0L;
        if (idatasource != null)
        {
            l = idatasource.getLongProp(i, Property.PROP_MODIFIED_TIME, l);
        }
        return l;
    }

    public static long getSize(int i, IDataSource idatasource)
    {
        long l = 0L;
        if (idatasource != null)
        {
            l = idatasource.getLongProp(i, Property.PROP_SIZE, l);
        }
        return l;
    }

    public static long getTakenTime(int i, IDataSource idatasource)
    {
        long l = 0L;
        if (idatasource != null)
        {
            l = idatasource.getLongProp(i, Property.PROP_TAKEN_TIME, l);
        }
        return l;
    }

    public static String getTitle(int i, IDataSource idatasource)
    {
        String s = "";
        if (idatasource != null)
        {
            s = idatasource.getStringProp(i, Property.PROP_TITLE, s);
        }
        return s;
    }

    public static Uri getUri(int i, IDataSource idatasource)
    {
        Uri uri = null;
        if (idatasource != null)
        {
            uri = (Uri)idatasource.getObjectProp(i, Property.PROP_URI, null);
        }
        return uri;
    }

    public static int getWidth(int i, IDataSource idatasource)
    {
        int j = 0;
        if (idatasource != null)
        {
            j = idatasource.getIntProp(i, Property.PROP_WIDTH, 0);
        }
        return j;
    }

    public static boolean is3DFile(int i, IDataSource idatasource)
    {
        boolean flag = false;
        if (idatasource != null)
        {
            flag = idatasource.getBooleanProp(i, Property.PROP_3D, false);
        }
        return flag;
    }

    public static boolean isDirectory(int i, IDataSource idatasource)
    {
        boolean flag = false;
        if (idatasource != null)
        {
            flag = idatasource.getBooleanProp(i, Property.PROP_ISDIR, false);
        }
        return flag;
    }
}
