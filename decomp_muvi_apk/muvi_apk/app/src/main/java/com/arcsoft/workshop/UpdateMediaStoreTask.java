// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.arcsoft.workshop;

import android.content.ContentResolver;
import android.content.ContentValues;
import android.content.Context;
import android.os.AsyncTask;
import java.io.File;

public class UpdateMediaStoreTask extends AsyncTask
{

    private Context mContext;

    public UpdateMediaStoreTask(Context context)
    {
        mContext = context;
    }

    public static void updateMediaStore(Context context, String s)
        throws Exception
    {
        File file;
        ContentResolver contentresolver;
        String s1;
        int i;
label0:
        {
            if (s == null)
            {
                throw new NullPointerException("strFileName is null!");
            }
            file = new File(s);
            if (!file.exists())
            {
                throw new IllegalArgumentException("strFileName is not exist!");
            }
            contentresolver = context.getContentResolver();
            if (file != null)
            {
                s1 = file.getName();
                i = s1.lastIndexOf(".");
                if (i >= 0)
                {
                    break label0;
                }
            }
            return;
        }
        ContentValues contentvalues = new ContentValues(6);
        contentvalues.put("title", s1.substring(0, i));
        contentvalues.put("_display_name", file.getName());
        contentvalues.put("_data", file.getPath());
        contentvalues.put("date_modified", Long.valueOf(System.currentTimeMillis() / 1000L));
        contentvalues.put("_size", Long.valueOf(file.length()));
        contentvalues.put("mime_type", "image/jpeg");
        contentresolver.insert(android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI, contentvalues);
    }

    protected volatile Object doInBackground(Object aobj[])
    {
        return doInBackground((String[])aobj);
    }

    protected transient Void doInBackground(String as[])
    {
        try
        {
            updateMediaStore(mContext, as[0]);
        }
        catch (Exception exception)
        {
            exception.printStackTrace();
        }
        return null;
    }
}
