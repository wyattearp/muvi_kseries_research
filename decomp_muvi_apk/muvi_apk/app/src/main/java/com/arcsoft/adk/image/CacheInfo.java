// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.arcsoft.adk.image;


public final class CacheInfo
{

    public int FolderNumUsed;
    public String cacheFolders[];
    public int cacheLimiteds[];
    public int height;
    public boolean isUseExifThumb;
    public String srcFolders[];
    public int type;
    public int width;

    public CacheInfo()
    {
        width = 0;
        height = 0;
        type = 0;
        isUseExifThumb = false;
        srcFolders = null;
        cacheFolders = null;
        cacheLimiteds = null;
    }

    public CacheInfo(CacheInfo cacheinfo)
    {
        width = cacheinfo.width;
        height = cacheinfo.height;
        type = cacheinfo.type;
        FolderNumUsed = cacheinfo.FolderNumUsed;
        isUseExifThumb = cacheinfo.isUseExifThumb;
        srcFolders = cacheinfo.srcFolders;
        cacheFolders = cacheinfo.cacheFolders;
        cacheLimiteds = cacheinfo.cacheLimiteds;
    }
}
