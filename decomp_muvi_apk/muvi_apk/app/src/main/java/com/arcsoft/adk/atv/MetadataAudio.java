// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.arcsoft.adk.atv;

import android.app.Application;
import android.content.ContentResolver;
import android.database.ContentObserver;
import android.database.Cursor;
import android.database.CursorIndexOutOfBoundsException;
import android.os.Handler;
import com.arcsoft.util.debug.Log;
import java.util.ArrayList;
import java.util.Collections;

// Referenced classes of package com.arcsoft.adk.atv:
//            Recycleble, DLNA

public final class MetadataAudio
    implements Recycleble, Metadata.MetadataSetInterface
{
    static final class AudioContentObserver extends ContentObserver
    {

        public void onChange(boolean flag)
        {
            super.onChange(flag);
            Log.d("MetadataAudio", "AudioContentObserver.onChange");
        }

        public AudioContentObserver(Handler handler)
        {
            super(handler);
        }
    }

    public static class AudioInfo extends Metadata.MetadataInfo
    {

        public long lDuration;
        public String strAlbum;
        public String strArtist;

        public int getType()
        {
            return 2;
        }

        public AudioInfo()
        {
        }
    }


    public static final String TAG = "MetadataAudio";
    static ContentObserver mObserver = null;
    public int mCategory;
    private Cursor mCursor;
    ArrayList mList;
    long mListCursor;
    String mstrCateName;

    public MetadataAudio()
    {
        mCursor = null;
        mCategory = -1;
        mstrCateName = null;
        mList = new ArrayList();
        mListCursor = 0L;
    }

    public static Metadata.MetadataInfo GetMetaData(long l)
    {
        ContentResolver contentresolver = DLNA.instance().mApplication.getContentResolver();
        android.net.Uri uri = android.provider.MediaStore.Audio.Media.EXTERNAL_CONTENT_URI;
        String as[] = new String[1];
        as[0] = (new StringBuilder()).append("").append(l).toString();
        Cursor cursor = contentresolver.query(uri, null, "_id =? ", as, null);
        AudioInfo audioinfo = null;
        if (cursor != null)
        {
            boolean flag = cursor.moveToFirst();
            audioinfo = null;
            if (flag)
            {
                int i = cursor.getColumnIndex("_id");
                int j = cursor.getColumnIndex("_data");
                int k = cursor.getColumnIndex("_size");
                int i1 = cursor.getColumnIndex("mime_type");
                int j1 = cursor.getColumnIndex("title");
                int k1 = cursor.getColumnIndex("duration");
                AudioInfo audioinfo1 = new AudioInfo();
                audioinfo1.lIndex = cursor.getLong(i);
                audioinfo1.strPath = cursor.getString(j);
                audioinfo1.lSize = cursor.getLong(k);
                audioinfo1.strMimeType = cursor.getString(i1);
                audioinfo1.strTitle = cursor.getString(j1);
                audioinfo1.lDuration = cursor.getLong(k1);
                Log.d("MetadataAudio", (new StringBuilder()).append(audioinfo1.lIndex).append(":").append(audioinfo1.strPath).append(":").append(audioinfo1.lDuration).toString());
                audioinfo1.bPresentItem = true;
                audioinfo = audioinfo1;
            }
            cursor.close();
        }
        return audioinfo;
    }

    public static Metadata.MetadataInfo GetMetaData(String s)
    {
        Cursor cursor = DLNA.instance().mApplication.getContentResolver().query(android.provider.MediaStore.Audio.Media.EXTERNAL_CONTENT_URI, null, "_data =? ", new String[] {
            s
        }, null);
        AudioInfo audioinfo = null;
        if (cursor != null)
        {
            boolean flag = cursor.moveToFirst();
            audioinfo = null;
            if (flag)
            {
                int i = cursor.getColumnIndex("_id");
                int j = cursor.getColumnIndex("_data");
                int k = cursor.getColumnIndex("_size");
                int l = cursor.getColumnIndex("mime_type");
                int i1 = cursor.getColumnIndex("title");
                int j1 = cursor.getColumnIndex("duration");
                int k1 = cursor.getColumnIndex("artist");
                int l1 = cursor.getColumnIndex("album");
                AudioInfo audioinfo1 = new AudioInfo();
                audioinfo1.lIndex = cursor.getLong(i);
                audioinfo1.strPath = cursor.getString(j);
                audioinfo1.lSize = cursor.getLong(k);
                audioinfo1.strMimeType = cursor.getString(l);
                audioinfo1.strTitle = cursor.getString(i1);
                audioinfo1.lDuration = cursor.getLong(j1);
                audioinfo1.strArtist = cursor.getString(k1);
                audioinfo1.strAlbum = cursor.getString(l1);
                Log.d("MetadataAudio", (new StringBuilder()).append(audioinfo1.lIndex).append(":").append(audioinfo1.strPath).append(":").append(audioinfo1.lDuration).toString());
                audioinfo1.bPresentItem = true;
                audioinfo = audioinfo1;
            }
            cursor.close();
        }
        return audioinfo;
    }

    public static void RegisterContentObserver()
    {
        mObserver = new AudioContentObserver(new Handler());
        DLNA.instance().mApplication.getContentResolver().registerContentObserver(android.provider.MediaStore.Audio.Media.EXTERNAL_CONTENT_URI, true, mObserver);
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
        mListCursor = 0L;
        mList.clear();
    }

    public long GetCount()
    {
        if (mCategory != 0 && mstrCateName == null)
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
        if (mCategory != 0 && mstrCateName == null) goto _L4; else goto _L3
_L3:
        int i = mCursor.getColumnIndex("_id");
        int j = mCursor.getColumnIndex("_data");
        int k = mCursor.getColumnIndex("_size");
        int l = mCursor.getColumnIndex("mime_type");
        int i1 = mCursor.getColumnIndex("title");
        int j1 = mCursor.getColumnIndex("duration");
        AudioInfo audioinfo = new AudioInfo();
        audioinfo.lIndex = mCursor.getLong(i);
        audioinfo.strPath = mCursor.getString(j);
        audioinfo.lSize = mCursor.getLong(k);
        audioinfo.strMimeType = mCursor.getString(l);
        audioinfo.strTitle = mCursor.getString(i1);
        audioinfo.lDuration = mCursor.getLong(j1);
        Log.d("MetadataAudio", (new StringBuilder()).append(audioinfo.lIndex).append(":").append(audioinfo.strPath).append(":").append(audioinfo.lDuration).toString());
        audioinfo.bPresentItem = true;
        obj = audioinfo;
_L2:
        if (obj != null)
        {
            obj.iCategoryType = mCategory;
        }
        return ((Metadata.MetadataInfo) (obj));
_L4:
        switch (mCategory)
        {
        case 2: // '\002'
        default:
            obj = null;
            break;

        case 1: // '\001'
            obj = new Metadata.MetadataInfo();
            obj.bPresentItem = false;
            obj.strCategory = (String)mList.get((int)mListCursor);
            break;

        case 3: // '\003'
            obj = new Metadata.MetadataInfo();
            obj.bPresentItem = false;
            obj.strCategory = (String)mList.get((int)mListCursor);
            break;
        }
        if (true) goto _L2; else goto _L5
_L5:
    }

    public boolean MoveNext()
    {
        boolean flag;
        if (mCategory != 0 && mstrCateName == null)
        {
            mListCursor = 1L + mListCursor;
            if (mListCursor < (long)mList.size())
            {
                flag = true;
            } else
            {
                flag = false;
            }
        } else
        {
            Cursor cursor = mCursor;
            flag = false;
            if (cursor != null)
            {
                return mCursor.moveToNext();
            }
        }
        return flag;
    }

    public long MoveTo(long l)
    {
        long l1 = -1L;
        if (mCategory != 0 && mstrCateName == null && l >= 0L && l < (long)mList.size())
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
        mCursor = DLNA.instance().mApplication.getContentResolver().query(android.provider.MediaStore.Audio.Media.EXTERNAL_CONTENT_URI, null, null, null, "title asc ");
_L4:
        if (mList != null && mList.size() > 0)
        {
            Collections.sort(mList);
        }
        long l1 = MoveTo(l);
        Log.v("MetadataAudio", (new StringBuilder()).append(" ret val :").append(l1).toString());
        return l1;
_L2:
        if (s != null)
        {
            break; /* Loop/switch isn't completed */
        }
        switch (mCategory)
        {
        case 1: // '\001'
            mCursor = DLNA.instance().mApplication.getContentResolver().query(android.provider.MediaStore.Audio.Media.EXTERNAL_CONTENT_URI, null, null, null, "album asc ");
            if (mCursor != null)
            {
                mCursor.moveToFirst();
                do
                {
                    try
                    {
                        int k = mCursor.getColumnIndex("album");
                        String s2 = mCursor.getString(k);
                        if (!mList.contains(s2))
                        {
                            mList.add(s2);
                            Log.d("MetadataAudio", s2);
                        }
                    }
                    catch (CursorIndexOutOfBoundsException cursorindexoutofboundsexception1)
                    {
                        cursorindexoutofboundsexception1.printStackTrace();
                    }
                } while (mCursor.moveToNext());
            }
            break;

        case 3: // '\003'
            mCursor = DLNA.instance().mApplication.getContentResolver().query(android.provider.MediaStore.Audio.Media.EXTERNAL_CONTENT_URI, null, null, null, "artist asc ");
            if (mCursor != null)
            {
                mCursor.moveToFirst();
                do
                {
                    try
                    {
                        int j = mCursor.getColumnIndex("artist");
                        String s1 = mCursor.getString(j);
                        if (!mList.contains(s1))
                        {
                            mList.add(s1);
                            Log.d("MetadataAudio", s1);
                        }
                    }
                    catch (CursorIndexOutOfBoundsException cursorindexoutofboundsexception)
                    {
                        cursorindexoutofboundsexception.printStackTrace();
                    }
                } while (mCursor.moveToNext());
            }
            break;
        }
        if (true) goto _L4; else goto _L3
_L3:
        switch (mCategory)
        {
        case 1: // '\001'
            mCursor = DLNA.instance().mApplication.getContentResolver().query(android.provider.MediaStore.Audio.Media.EXTERNAL_CONTENT_URI, null, "album =? ", new String[] {
                s
            }, "title asc ");
            break;

        case 3: // '\003'
            mCursor = DLNA.instance().mApplication.getContentResolver().query(android.provider.MediaStore.Audio.Media.EXTERNAL_CONTENT_URI, null, "artist =? ", new String[] {
                s
            }, "title asc ");
            break;
        }
        if (true) goto _L4; else goto _L5
_L5:
    }

    public void Recycle()
    {
        CloseRecordSet();
    }

}
