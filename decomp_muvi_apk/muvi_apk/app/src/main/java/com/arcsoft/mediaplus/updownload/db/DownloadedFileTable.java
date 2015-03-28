// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.arcsoft.mediaplus.updownload.db;


public class DownloadedFileTable
{
    public static interface Columns
    {

        public static final String DMS_UUID = "dms_uuid";
        public static final String FILE_PATH = "file_path";
        public static final String FILE_SIZE = "file_size";
        public static final String FILE_TITLE = "file_title";
        public static final String ITEM_UUID = "item_uuid";
        public static final String URI = "uri";
        public static final String _ID = "_id";
    }


    public static final String PROJECTION_ARRAY[] = {
        "_id", "dms_uuid", "item_uuid", "file_size", "file_path", "file_title", "uri"
    };
    public static final String TABLE_NAME = "DownloadedFileTable";
    public static final String TABLE_NAME_SUFFIX = ".db";

    public DownloadedFileTable()
    {
    }

}
