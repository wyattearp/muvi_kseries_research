// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.arcsoft.adk.atv;

import android.app.Application;
import android.content.ContentResolver;
import android.database.ContentObserver;
import android.database.Cursor;
import android.database.CursorIndexOutOfBoundsException;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Handler;
import com.arcsoft.util.debug.Log;
import java.sql.Date;
import java.util.ArrayList;
import java.util.Collections;

// Referenced classes of package com.arcsoft.adk.atv:
//            Recycleble, DLNA, Metadatahelper

public final class MetadataImage
    implements Recycleble, Metadata.MetadataSetInterface
{
    static final class ImageContentObserver extends ContentObserver
    {

        public void onChange(boolean flag)
        {
            super.onChange(flag);
            Log.d("MetadataImage", "ImageContentObserver.onChange");
        }

        public ImageContentObserver(Handler handler)
        {
            super(handler);
        }
    }

    public static class ImageInfo extends Metadata.MetadataInfo
    {

        public long lResolutionX;
        public long lResolutionY;

        public int getType()
        {
            return 3;
        }

        public ImageInfo()
        {
        }
    }

    public static class LocalImageInfoManager
        implements Recycleble
    {

        Bitmap mBitmap;
        android.graphics.BitmapFactory.Options mOpts;

        public void Recycle()
        {
            if (mOpts != null)
            {
                mOpts = null;
            }
            if (mBitmap != null)
            {
                mBitmap.recycle();
                mBitmap = null;
            }
        }

        public int getDimensionHeight()
        {
            android.graphics.BitmapFactory.Options options = mOpts;
            int i = 0;
            if (options != null)
            {
                i = mOpts.outHeight;
            }
            return i;
        }

        public int getDimensionWidth()
        {
            android.graphics.BitmapFactory.Options options = mOpts;
            int i = 0;
            if (options != null)
            {
                i = mOpts.outWidth;
            }
            return i;
        }

        public LocalImageInfoManager(String s)
        {
            mOpts = null;
            mBitmap = null;
            mOpts = new android.graphics.BitmapFactory.Options();
            mOpts.inJustDecodeBounds = true;
            mBitmap = BitmapFactory.decodeFile(s, mOpts);
        }
    }


    public static final String TAG = "MetadataImage";
    static ContentObserver mObserver = null;
    public int mCategory;
    private Cursor mCursor;
    Metadatahelper.DateTaken mDateTaken;
    ArrayList mList;
    long mListCursor;
    String mstrCateName;
    String mstrTaken[];

    public MetadataImage()
    {
        mCursor = null;
        mCategory = -1;
        mstrCateName = null;
        mstrTaken = new String[3];
        mList = new ArrayList();
        mListCursor = 0L;
    }

    private void ClearDateTakenProfile()
    {
        mListCursor = 0L;
        mDateTaken = null;
        mstrTaken[0] = null;
        mstrTaken[1] = null;
        mstrTaken[2] = null;
        mList.clear();
    }

    public static Metadata.MetadataInfo GetMetaData(long l)
    {
        ContentResolver contentresolver = DLNA.instance().mApplication.getContentResolver();
        android.net.Uri uri = android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI;
        String as[] = new String[1];
        as[0] = (new StringBuilder()).append("").append(l).toString();
        Cursor cursor = contentresolver.query(uri, null, "_id =? ", as, null);
        ImageInfo imageinfo = null;
        if (cursor != null)
        {
            boolean flag = cursor.moveToFirst();
            imageinfo = null;
            if (flag)
            {
                int i = cursor.getColumnIndex("_id");
                int j = cursor.getColumnIndex("_data");
                int k = cursor.getColumnIndex("_size");
                int i1 = cursor.getColumnIndex("mime_type");
                int j1 = cursor.getColumnIndex("title");
                ImageInfo imageinfo1 = new ImageInfo();
                imageinfo1.lIndex = cursor.getLong(i);
                imageinfo1.strPath = cursor.getString(j);
                imageinfo1.lSize = cursor.getLong(k);
                imageinfo1.strMimeType = cursor.getString(i1);
                imageinfo1.strTitle = cursor.getString(j1);
                Log.d("MetadataImage", (new StringBuilder()).append("name:").append(imageinfo1.strTitle).append("x=").append(imageinfo1.lResolutionX).append("y=").append(imageinfo1.lResolutionY).toString());
                imageinfo1.bPresentItem = true;
                imageinfo = imageinfo1;
            }
            cursor.close();
        }
        return imageinfo;
    }

    public static Metadata.MetadataInfo GetMetaData(String s)
    {
        Cursor cursor = DLNA.instance().mApplication.getContentResolver().query(android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI, null, "_data =? ", new String[] {
            s
        }, null);
        ImageInfo imageinfo = null;
        if (cursor != null)
        {
            boolean flag = cursor.moveToFirst();
            imageinfo = null;
            if (flag)
            {
                int i = cursor.getColumnIndex("_id");
                int j = cursor.getColumnIndex("_data");
                int k = cursor.getColumnIndex("_size");
                int l = cursor.getColumnIndex("mime_type");
                int i1 = cursor.getColumnIndex("title");
                ImageInfo imageinfo1 = new ImageInfo();
                imageinfo1.lIndex = cursor.getLong(i);
                imageinfo1.strPath = cursor.getString(j);
                imageinfo1.lSize = cursor.getLong(k);
                imageinfo1.strMimeType = cursor.getString(l);
                imageinfo1.strTitle = cursor.getString(i1);
                Log.d("MetadataImage", (new StringBuilder()).append("name:").append(imageinfo1.strTitle).append("x=").append(imageinfo1.lResolutionX).append("y=").append(imageinfo1.lResolutionY).toString());
                imageinfo1.bPresentItem = true;
                imageinfo = imageinfo1;
            }
            cursor.close();
        }
        return imageinfo;
    }

    public static void RegisterContentObserver()
    {
        mObserver = new ImageContentObserver(new Handler());
        DLNA.instance().mApplication.getContentResolver().registerContentObserver(android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI, true, mObserver);
    }

    public static void UnregisterContentObserver()
    {
        if (mObserver != null)
        {
            DLNA.instance().mApplication.getContentResolver().unregisterContentObserver(mObserver);
            mObserver = null;
        }
    }

    public void CloseRecordSet()
    {
        if (mCursor != null)
        {
            mCursor.close();
            mCursor = null;
        }
        mCategory = -1;
        mstrCateName = null;
        ClearDateTakenProfile();
    }

    public long GetCount()
    {
        if (mDateTaken != null && mDateTaken != Metadatahelper.DateTaken.Item)
        {
            return (long)mList.size();
        }
        if (mCursor != null)
        {
            return (long)mCursor.getCount();
        } else
        {
            return -1L;
        }
    }

    public Metadata.MetadataInfo GetCurrent()
    {
        Cursor cursor;
        Object obj;
        cursor = mCursor;
        obj = null;
        if (cursor == null) goto _L2; else goto _L1
_L1:
        if (mCategory != 0 && mDateTaken != Metadatahelper.DateTaken.Item) goto _L4; else goto _L3
_L3:
        int i = mCursor.getColumnIndex("_id");
        int j = mCursor.getColumnIndex("_data");
        int k = mCursor.getColumnIndex("_size");
        int l = mCursor.getColumnIndex("mime_type");
        int i1 = mCursor.getColumnIndex("title");
        ImageInfo imageinfo = new ImageInfo();
        imageinfo.lIndex = mCursor.getLong(i);
        imageinfo.strPath = mCursor.getString(j);
        imageinfo.lSize = mCursor.getLong(k);
        imageinfo.strMimeType = mCursor.getString(l);
        imageinfo.strTitle = mCursor.getString(i1);
        Log.d("MetadataImage", (new StringBuilder()).append("name:").append(imageinfo.strTitle).append("x=").append(imageinfo.lResolutionX).append("y=").append(imageinfo.lResolutionY).toString());
        imageinfo.bPresentItem = true;
        obj = imageinfo;
_L2:
        if (obj != null)
        {
            obj.iCategoryType = mCategory;
        }
        return ((Metadata.MetadataInfo) (obj));
_L4:
        switch (mCategory)
        {
        default:
            obj = null;
            continue; /* Loop/switch isn't completed */

        case 5: // '\005'
            break;
        }
        if (mDateTaken != Metadatahelper.DateTaken.Year_item && mDateTaken != Metadatahelper.DateTaken.Month_item)
        {
            Metadatahelper.DateTaken datetaken = mDateTaken;
            Metadatahelper.DateTaken datetaken1 = Metadatahelper.DateTaken.Day_item;
            obj = null;
            if (datetaken != datetaken1)
            {
                continue; /* Loop/switch isn't completed */
            }
        }
        obj = new Metadata.MetadataInfo();
        obj.bPresentItem = false;
        obj.strCategory = (String)mList.get((int)mListCursor);
        if (true) goto _L2; else goto _L5
_L5:
    }

    public boolean MoveNext()
    {
        if (mDateTaken == null || mDateTaken == Metadatahelper.DateTaken.Item) goto _L2; else goto _L1
_L1:
        mListCursor = 1L + mListCursor;
        if (mListCursor >= (long)mList.size()) goto _L4; else goto _L3
_L3:
        boolean flag = true;
_L6:
        return flag;
_L4:
        return false;
_L2:
        Cursor cursor = mCursor;
        flag = false;
        if (cursor != null)
        {
            return mCursor.moveToNext();
        }
        if (true) goto _L6; else goto _L5
_L5:
    }

    public long MoveTo(long l)
    {
        long l1 = -1L;
        if (mDateTaken != null && mDateTaken != Metadatahelper.DateTaken.Item && mCursor != null && l >= 0L && l < (long)mCursor.getCount())
        {
            mListCursor = l;
            l1 = 1L;
        } else
        if (mCursor != null && l >= 0L && l < (long)mCursor.getCount())
        {
            mCursor.moveToPosition((int)l);
            return 1L;
        }
        return l1;
    }

    public long OpenRecordSet(int i, String s, Metadatahelper.Filter afilter[], Metadatahelper.Sort sort, long l)
    {
        mCategory = i;
        mstrCateName = s;
        if (mCategory != 0) goto _L2; else goto _L1
_L1:
        mCursor = DLNA.instance().mApplication.getContentResolver().query(android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI, null, null, null, "title asc ");
_L4:
        if (mList != null && mList.size() > 0)
        {
            Collections.sort(mList);
        }
        long l1 = MoveTo(l);
        Log.v("MetadataImage", (new StringBuilder()).append(" ret val :").append(l1).toString());
        return l1;
_L2:
        if (mCategory == 5)
        {
            Log.v("MetadataImage", "000000");
            ClearDateTakenProfile();
            mDateTaken = Metadatahelper.GetDateTaken(s, mstrTaken);
        }
        Log.v("MetadataImage", "11111111111");
        if (mDateTaken != null)
        {
            switch (mCategory)
            {
            case 5: // '\005'
                if (mDateTaken == Metadatahelper.DateTaken.Year_item)
                {
                    mCursor = DLNA.instance().mApplication.getContentResolver().query(android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI, null, null, null, "title asc ");
                    if (mCursor != null)
                    {
                        mCursor.moveToFirst();
                        do
                        {
                            try
                            {
                                int i1 = mCursor.getColumnIndex("date_modified");
                                long l7 = 1000L * mCursor.getLong(i1);
                                Date date2 = new Date(l7);
                                String as5[] = date2.toString().split("-");
                                if (as5.length >= 3 && !mList.contains(as5[0]))
                                {
                                    mList.add(as5[0]);
                                    Log.d("MetadataImage", as5[0]);
                                }
                            }
                            catch (CursorIndexOutOfBoundsException cursorindexoutofboundsexception2)
                            {
                                cursorindexoutofboundsexception2.printStackTrace();
                            }
                        } while (mCursor.moveToNext());
                    }
                } else
                if (mDateTaken == Metadatahelper.DateTaken.Month_item)
                {
                    long al2[] = new long[2];
                    long l5 = 1 + Integer.parseInt(mstrTaken[0]);
                    al2[0] = Date.valueOf((new StringBuilder()).append(mstrTaken[0]).append("-01").append("-01").toString()).getTime() / 1000L;
                    al2[1] = Date.valueOf((new StringBuilder()).append(l5).append("-01").append("-01").toString()).getTime() / 1000L;
                    ContentResolver contentresolver2 = DLNA.instance().mApplication.getContentResolver();
                    android.net.Uri uri2 = android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI;
                    String as3[] = new String[2];
                    as3[0] = (new StringBuilder()).append("").append(al2[0]).toString();
                    as3[1] = (new StringBuilder()).append("").append(al2[1]).toString();
                    mCursor = contentresolver2.query(uri2, null, "date_modified >=?  and date_modified <? ", as3, "title asc ");
                    if (mCursor != null)
                    {
                        mCursor.moveToFirst();
                        do
                        {
                            try
                            {
                                int k = mCursor.getColumnIndex("date_modified");
                                long l6 = 1000L * mCursor.getLong(k);
                                Date date1 = new Date(l6);
                                String as4[] = date1.toString().split("-");
                                Log.d("MetadataImage", (new StringBuilder()).append(as4[0]).append(" [] ").append(mstrTaken[0]).toString());
                                if (as4.length >= 3 && !mList.contains(as4[1]) && as4[0].compareTo(mstrTaken[0]) == 0)
                                {
                                    mList.add(as4[1]);
                                    Log.d("MetadataImage", as4[1]);
                                }
                            }
                            catch (CursorIndexOutOfBoundsException cursorindexoutofboundsexception1)
                            {
                                cursorindexoutofboundsexception1.printStackTrace();
                            }
                        } while (mCursor.moveToNext());
                    }
                } else
                if (mDateTaken == Metadatahelper.DateTaken.Day_item)
                {
                    long al1[] = new long[2];
                    long l2 = Integer.parseInt(mstrTaken[0]);
                    long l3 = 1 + Integer.parseInt(mstrTaken[1]);
                    if (l3 > 12L)
                    {
                        l3 = 1L;
                        l2++;
                    }
                    String s1;
                    ContentResolver contentresolver1;
                    android.net.Uri uri1;
                    String as1[];
                    if (l3 < 10L)
                    {
                        s1 = (new StringBuilder()).append("0").append(l3).toString();
                    } else
                    {
                        s1 = (new StringBuilder()).append("").append(l3).toString();
                    }
                    al1[0] = Date.valueOf((new StringBuilder()).append(mstrTaken[0]).append("-").append(mstrTaken[1]).append("-01").toString()).getTime() / 1000L;
                    al1[1] = Date.valueOf((new StringBuilder()).append(l2).append("-").append(s1).append("-01").toString()).getTime() / 1000L;
                    contentresolver1 = DLNA.instance().mApplication.getContentResolver();
                    uri1 = android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI;
                    as1 = new String[2];
                    as1[0] = (new StringBuilder()).append("").append(al1[0]).toString();
                    as1[1] = (new StringBuilder()).append("").append(al1[1]).toString();
                    mCursor = contentresolver1.query(uri1, null, "date_modified >=?  and date_modified <? ", as1, "title asc ");
                    if (mCursor != null)
                    {
                        mCursor.moveToFirst();
                        do
                        {
                            try
                            {
                                int j = mCursor.getColumnIndex("date_modified");
                                long l4 = 1000L * mCursor.getLong(j);
                                Date date = new Date(l4);
                                String as2[] = date.toString().split("-");
                                Log.d("MetadataImage", (new StringBuilder()).append(as2[0]).append(" [] ").append(mstrTaken[0]).append(" [] ").append(as2[1]).append(" [] ").append(mstrTaken[1]).toString());
                                if (as2.length >= 3 && !mList.contains(as2[2]) && as2[0].compareTo(mstrTaken[0]) == 0 && as2[1].compareTo(mstrTaken[1]) == 0)
                                {
                                    mList.add(as2[2]);
                                    Log.d("MetadataImage", as2[2]);
                                }
                            }
                            catch (CursorIndexOutOfBoundsException cursorindexoutofboundsexception)
                            {
                                cursorindexoutofboundsexception.printStackTrace();
                            }
                        } while (mCursor.moveToNext());
                    }
                } else
                if (mDateTaken == Metadatahelper.DateTaken.Item)
                {
                    long al[] = new long[2];
                    al[0] = Date.valueOf((new StringBuilder()).append(mstrTaken[0]).append("-").append(mstrTaken[1]).append("-").append(mstrTaken[2]).toString()).getTime() / 1000L;
                    al[1] = 0x15180L + al[0];
                    ContentResolver contentresolver = DLNA.instance().mApplication.getContentResolver();
                    android.net.Uri uri = android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI;
                    String as[] = new String[2];
                    as[0] = (new StringBuilder()).append("").append(al[0]).toString();
                    as[1] = (new StringBuilder()).append("").append(al[1]).toString();
                    mCursor = contentresolver.query(uri, null, "date_modified >=?  and date_modified <? ", as, "title asc ");
                }
                break;
            }
        }
        if (true) goto _L4; else goto _L3
_L3:
    }

    public void Recycle()
    {
        CloseRecordSet();
    }

}
