// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.arcsoft.mediaplus.datasource;


public final class Property
{

    public static final Property PROP_3D = new Property("PROP_3D");
    public static final Property PROP_ADDED_TIME = new Property("PROP_ADD_TIME");
    public static final Property PROP_ALBUM = new Property("PROP_ALBUM");
    public static final Property PROP_ARTIST = new Property("PROP_ARTIST");
    public static final Property PROP_CHANNELID = new Property("PROP_CHANNELID");
    public static final Property PROP_CHANNELNAME = new Property("PROP_CHANNELNAME");
    public static final Property PROP_CHANNELNR = new Property("PROP_CHANNELNR");
    public static final Property PROP_COMPOSER = new Property("PROP_COMPOSER");
    public static final Property PROP_COPYCOUNT = new Property("PROP_COPYCOUNT");
    public static final Property PROP_DOWNLOAD_URL = new Property("DOWNLOAD_URL");
    public static final Property PROP_DURATION = new Property("PROP_DURATION");
    public static final Property PROP_GENRE = new Property("PROP_GENRE");
    public static final Property PROP_HEIGHT = new Property("PROP_HEIGHT");
    public static final Property PROP_ID = new Property("PROP_ID");
    public static final Property PROP_IMAGE_FILEPATH = new Property("PROP_IMAGE_FILEPATH");
    public static final Property PROP_ISDIR = new Property("PROP_ISDIR");
    public static final Property PROP_MIMETYPE = new Property("PROP_MIMETYPE");
    public static final Property PROP_MODIFIED_TIME = new Property("PROP_MODIFY_TIME");
    public static final Property PROP_RESUMEPOINT = new Property("PROP_RESUMEPOINT");
    public static final Property PROP_SIZE = new Property("PROP_SIZE");
    public static final Property PROP_STORAGE_FULL = new Property("STORAGE_FULL");
    public static final Property PROP_TAKEN_TIME = new Property("PROP_TAKEN_TIME");
    public static final Property PROP_THUMBNAIL_FILEPATH = new Property("PROP_THUMBNAIL_FILEPATH");
    public static final Property PROP_TITLE = new Property("PROP_TITLE");
    public static final Property PROP_URI = new Property("PROP_URI");
    public static final Property PROP_VGAURI = new Property("PROP_VGAURI");
    public static final Property PROP_VIDEO_OR_IMAGE = new Property("VIDEO_OR_IMAGE");
    public static final Property PROP_WIDTH = new Property("PROP_WIDTH");
    private final String mName;

    protected Property(String s)
    {
        mName = s;
    }

    public boolean equals(Object obj)
    {
        return this == obj;
    }

    public boolean isBelongsTo(Property aproperty[])
    {
        int i = aproperty.length;
        for (int j = 0; j < i; j++)
        {
            if (aproperty[j].equals(this))
            {
                return true;
            }
        }

        return false;
    }

    public String toString()
    {
        return mName;
    }

}
