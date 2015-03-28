// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.arcsoft.mediaplus.datasource;

import android.net.Uri;
import java.util.Date;

// Referenced classes of package com.arcsoft.mediaplus.datasource:
//            Property, IDataSource

public final class AudioDataSourceHelper
{

    private AudioDataSourceHelper()
    {
    }

    public static String getAlbumName(int i, IDataSource idatasource)
    {
        String s = "";
        if (idatasource != null)
        {
            s = idatasource.getStringProp(i, Property.PROP_ALBUM, s);
        }
        return s;
    }

    public static String getAristName(int i, IDataSource idatasource)
    {
        String s = "";
        if (idatasource != null)
        {
            s = idatasource.getStringProp(i, Property.PROP_ARTIST, s);
        }
        return s;
    }

    public static String getComposer(int i, IDataSource idatasource)
    {
        String s = null;
        if (idatasource != null)
        {
            s = idatasource.getStringProp(i, Property.PROP_COMPOSER, null);
        }
        return s;
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

    public static String getGenreName(int i, IDataSource idatasource)
    {
        String s = "";
        if (idatasource != null)
        {
            s = idatasource.getStringProp(i, Property.PROP_GENRE, s);
        }
        return s;
    }

    public static String getMimeType(int i, IDataSource idatasource)
    {
        return null;
    }

    public static String getModifiedTime(int i, IDataSource idatasource)
    {
        long l = idatasource.getLongProp(i, Property.PROP_MODIFIED_TIME, 0L);
        int j = l != 0L;
        String s = null;
        if (j > 0)
        {
            s = (new Date(1000L * l)).toLocaleString();
        }
        return s;
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
