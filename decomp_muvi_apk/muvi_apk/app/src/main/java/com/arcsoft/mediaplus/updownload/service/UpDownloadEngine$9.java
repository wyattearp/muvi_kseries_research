// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.arcsoft.mediaplus.updownload.service;

import android.content.ContentResolver;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import com.arcsoft.util.debug.Log;

// Referenced classes of package com.arcsoft.mediaplus.updownload.service:
//            UpDownloadEngine

class this._cls0
    implements android.media.ion.OnScanCompletedListener
{

    final UpDownloadEngine this$0;

    public void onScanCompleted(String s, Uri uri)
    {
        Log.i("test", (new StringBuilder()).append("Scanned ").append(s).append(":").toString());
        Log.i("test", (new StringBuilder()).append("-> uri=").append(uri).toString());
        ContentResolver contentresolver = UpDownloadEngine.access$800(UpDownloadEngine.this).getContentResolver();
        long l = 0L;
        long l1 = 0L;
        Cursor cursor = contentresolver.query(uri, null, null, null, null);
        if (cursor != null && cursor.moveToFirst())
        {
            l = cursor.getLong(cursor.getColumnIndex("date_modified"));
            l1 = cursor.getLong(cursor.getColumnIndex("date_added"));
            cursor.close();
        }
        ContentValues contentvalues = new ContentValues();
        if (l > 0L && String.valueOf(l).length() > 10)
        {
            contentvalues.put("date_modified", Long.valueOf(l / 1000L));
        }
        if (l1 > 0L && String.valueOf(l1).length() > 13)
        {
            contentvalues.put("date_added", Long.valueOf(l1 / 1000L));
        }
        if (contentvalues.size() > 0)
        {
            contentresolver.update(uri, contentvalues, null, null);
        }
    }

    ()
    {
        this$0 = UpDownloadEngine.this;
        super();
    }
}
