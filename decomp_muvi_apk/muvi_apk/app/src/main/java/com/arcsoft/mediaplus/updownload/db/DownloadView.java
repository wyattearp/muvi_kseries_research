// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.arcsoft.mediaplus.updownload.db;


public class DownloadView
{
    public static interface Columns
    {

        public static final String CURRENT_SIZE = "current_size";
        public static final String DATE_ADDED = "date_added";
        public static final String DMS_UUID = "dms_uuid";
        public static final String FILE_PATH = "file_path";
        public static final String FILE_SIZE = "file_size";
        public static final String ITEM_UUID = "item_uuid";
        public static final String MEDIATYPE = "mediaType";
        public static final String MEDIA_ID = "MEDIA_ID";
        public static final String STATE = "state";
        public static final String TITLE = "title";
        public static final String UPNP_CLASS = "UPNP_CLASS";
        public static final String URI = "uri";
        public static final String _ID = "_id";
    }


    public static final String PROJECTION_ARRAY[] = {
        "_id", "file_path", "title", "uri", "file_size", "current_size", "date_added", "state", "dms_uuid", "item_uuid", 
        "UPNP_CLASS", "MEDIA_ID"
    };
    public static final String SORT_ORDER_BY_TIME_ADDED = "date_added ASC";
    public static final String TABLE_NAME = "DownloadView";

    public DownloadView()
    {
    }

}
