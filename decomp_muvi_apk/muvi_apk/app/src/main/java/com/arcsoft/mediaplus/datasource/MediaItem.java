// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.arcsoft.mediaplus.datasource;

import android.net.Uri;

public class MediaItem
{

    public long _id;
    public long addedTime;
    public boolean isDir;
    public boolean isDownloadingFile;
    public String mime_type;
    public long size;
    public long status;
    public String strDecodePath;
    public String title;
    public Uri uri;
    public boolean videoOrImage;

    public MediaItem()
    {
        isDownloadingFile = false;
        status = 0L;
    }
}
