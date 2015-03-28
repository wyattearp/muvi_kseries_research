// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.arcsoft.adk.atv;

import android.app.Application;
import android.content.ContentResolver;
import android.database.ContentObserver;
import android.database.Cursor;
import android.database.CursorIndexOutOfBoundsException;
import android.media.MediaMetadataRetriever;
import android.os.Handler;
import com.arcsoft.util.debug.Log;
import java.util.ArrayList;
import java.util.Collections;

// Referenced classes of package com.arcsoft.adk.atv:
//            Recycleble, DLNA

public final class MetadataVideo
    implements Recycleble, Metadata.MetadataSetInterface
{
    public static class LocalVideoInfoManager
        implements Recycleble
    {

        private MediaMetadataRetriever mRetriever;

        public void Recycle()
        {
            if (mRetriever != null)
            {
                mRetriever.release();
            }
        }

        public long getBitRate()
        {
            long l;
            try
            {
                l = Long.parseLong(mRetriever.extractMetadata(16));
            }
            catch (NumberFormatException numberformatexception)
            {
                return 0L;
            }
            return l;
        }

        public int getDimensionHeight()
        {
            int i;
            try
            {
                i = Integer.parseInt(mRetriever.extractMetadata(19));
            }
            catch (NumberFormatException numberformatexception)
            {
                return 0;
            }
            return i;
        }

        public int getDimensionWidth()
        {
            int i;
            try
            {
                i = Integer.parseInt(mRetriever.extractMetadata(20));
                Log.e("LocalVideoInfoManager", (new StringBuilder()).append("get width:").append(i).toString());
            }
            catch (NumberFormatException numberformatexception)
            {
                Log.e("LocalVideoInfoManager", "get width exception");
                return 0;
            }
            return i;
        }

        public int getFrameRate()
        {
            int i;
            try
            {
                i = Integer.parseInt(mRetriever.extractMetadata(17));
            }
            catch (NumberFormatException numberformatexception)
            {
                return 0;
            }
            return i;
        }

        public String getVideoFormat()
        {
            String s;
            try
            {
                s = mRetriever.extractMetadata(18);
            }
            catch (Exception exception)
            {
                return null;
            }
            return s;
        }

        public LocalVideoInfoManager(String s)
        {
            mRetriever = null;
            mRetriever = new MediaMetadataRetriever();
            mRetriever.setMode(1);
            try
            {
                mRetriever.setDataSource(s);
                return;
            }
            catch (IllegalArgumentException illegalargumentexception)
            {
                illegalargumentexception.printStackTrace();
                return;
            }
            catch (RuntimeException runtimeexception)
            {
                runtimeexception.printStackTrace();
            }
        }
    }

    static final class VideoContentObserver extends ContentObserver
    {

        public void onChange(boolean flag)
        {
            super.onChange(flag);
            Log.d("MetadataVideo", "VideoContentObserver.onChange");
        }

        public VideoContentObserver(Handler handler)
        {
            super(handler);
        }
    }

    public static class VideoInfo extends Metadata.MetadataInfo
    {

        public long lDuration;
        public long lResolutionX;
        public long lResolutionY;
        public String strAlbum;
        public String strArtist;

        public int getType()
        {
            return 1;
        }

        public VideoInfo()
        {
            lResolutionX = -1L;
            lResolutionY = -1L;
        }
    }


    public static final String TAG = "MetadataVideo";
    static ContentObserver mObserver = null;
    public int mCategory;
    private Cursor mCursor;
    ArrayList mList;
    long mListCursor;
    String mstrCateName;

    public MetadataVideo()
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
        android.net.Uri uri = android.provider.MediaStore.Video.Media.EXTERNAL_CONTENT_URI;
        String as[] = new String[1];
        as[0] = (new StringBuilder()).append("").append(l).toString();
        Cursor cursor = contentresolver.query(uri, null, "_id =? ", as, null);
        VideoInfo videoinfo = null;
        if (cursor != null)
        {
            boolean flag = cursor.moveToFirst();
            videoinfo = null;
            if (flag)
            {
                int i = cursor.getColumnIndex("_id");
                int j = cursor.getColumnIndex("_data");
                int k = cursor.getColumnIndex("_size");
                int i1 = cursor.getColumnIndex("mime_type");
                int j1 = cursor.getColumnIndex("title");
                int k1 = cursor.getColumnIndex("duration");
                VideoInfo videoinfo1 = new VideoInfo();
                videoinfo1.lIndex = cursor.getLong(i);
                videoinfo1.strPath = cursor.getString(j);
                videoinfo1.lSize = cursor.getLong(k);
                videoinfo1.strMimeType = cursor.getString(i1);
                videoinfo1.strTitle = cursor.getString(j1);
                videoinfo1.lDuration = cursor.getLong(k1);
                Log.d("MetadataVideo", (new StringBuilder()).append("name:").append(videoinfo1.strTitle).append("dur:").append(videoinfo1.lDuration).append("x=").append(videoinfo1.lResolutionX).append("y=").append(videoinfo1.lResolutionY).toString());
                videoinfo1.bPresentItem = true;
                videoinfo = videoinfo1;
            }
            cursor.close();
        }
        return videoinfo;
    }

    public static Metadata.MetadataInfo GetMetaData(String s)
    {
        Cursor cursor = DLNA.instance().mApplication.getContentResolver().query(android.provider.MediaStore.Video.Media.EXTERNAL_CONTENT_URI, null, "_data =? ", new String[] {
            s
        }, null);
        VideoInfo videoinfo = null;
        if (cursor != null)
        {
            boolean flag = cursor.moveToFirst();
            videoinfo = null;
            if (flag)
            {
                int i = cursor.getColumnIndex("_id");
                int j = cursor.getColumnIndex("_data");
                int k = cursor.getColumnIndex("_size");
                int l = cursor.getColumnIndex("mime_type");
                int i1 = cursor.getColumnIndex("title");
                int j1 = cursor.getColumnIndex("duration");
                VideoInfo videoinfo1 = new VideoInfo();
                videoinfo1.lIndex = cursor.getLong(i);
                videoinfo1.strPath = cursor.getString(j);
                videoinfo1.lSize = cursor.getLong(k);
                videoinfo1.strMimeType = cursor.getString(l);
                videoinfo1.strTitle = cursor.getString(i1);
                videoinfo1.lDuration = cursor.getLong(j1);
                Log.d("MetadataVideo", (new StringBuilder()).append("name:").append(videoinfo1.strTitle).append("dur:").append(videoinfo1.lDuration).append("x=").append(videoinfo1.lResolutionX).append("y=").append(videoinfo1.lResolutionY).toString());
                videoinfo1.bPresentItem = true;
                videoinfo = videoinfo1;
            }
            cursor.close();
        }
        return videoinfo;
    }

    public static void RegisterContentObserver()
    {
        mObserver = new VideoContentObserver(new Handler());
        DLNA.instance().mApplication.getContentResolver().registerContentObserver(android.provider.MediaStore.Video.Media.EXTERNAL_CONTENT_URI, true, mObserver);
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
        VideoInfo videoinfo = new VideoInfo();
        videoinfo.lIndex = mCursor.getLong(i);
        videoinfo.strPath = mCursor.getString(j);
        videoinfo.lSize = mCursor.getLong(k);
        videoinfo.strMimeType = mCursor.getString(l);
        videoinfo.strTitle = mCursor.getString(i1);
        videoinfo.lDuration = mCursor.getLong(j1);
        Log.d("MetadataVideo", (new StringBuilder()).append("name:").append(videoinfo.strTitle).append("dur:").append(videoinfo.lDuration).append("x=").append(videoinfo.lResolutionX).append("y=").append(videoinfo.lResolutionY).toString());
        videoinfo.bPresentItem = true;
        obj = videoinfo;
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
        mCursor = DLNA.instance().mApplication.getContentResolver().query(android.provider.MediaStore.Video.Media.EXTERNAL_CONTENT_URI, null, null, null, "title asc ");
_L4:
        if (mList != null && mList.size() > 0)
        {
            Collections.sort(mList);
        }
        long l1 = MoveTo(l);
        Log.v("MetadataVideo", (new StringBuilder()).append(" ret val :").append(l1).toString());
        return l1;
_L2:
        if (s != null)
        {
            break; /* Loop/switch isn't completed */
        }
        switch (mCategory)
        {
        case 1: // '\001'
            mCursor = DLNA.instance().mApplication.getContentResolver().query(android.provider.MediaStore.Video.Media.EXTERNAL_CONTENT_URI, null, null, null, "album asc ");
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
                            Log.d("MetadataVideo", s2);
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
            mCursor = DLNA.instance().mApplication.getContentResolver().query(android.provider.MediaStore.Video.Media.EXTERNAL_CONTENT_URI, null, null, null, "artist asc ");
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
                            Log.d("MetadataVideo", s1);
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
            mCursor = DLNA.instance().mApplication.getContentResolver().query(android.provider.MediaStore.Video.Media.EXTERNAL_CONTENT_URI, null, "album =? ", new String[] {
                s
            }, "title asc ");
            break;

        case 3: // '\003'
            mCursor = DLNA.instance().mApplication.getContentResolver().query(android.provider.MediaStore.Video.Media.EXTERNAL_CONTENT_URI, null, "artist =? ", new String[] {
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
