// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.arcsoft.mediaplus.picture.data;

import android.content.ContentResolver;
import android.content.Context;
import android.database.ContentObserver;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteStatement;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.opengl.ETC1;
import android.os.Environment;
import android.os.Handler;
import com.arcsoft.mediaplus.datasource.MediaItem;
import com.arcsoft.mediaplus.oem.OEMConfig;
import com.arcsoft.util.FileUtils;
import com.arcsoft.util.debug.Log;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;

// Referenced classes of package com.arcsoft.mediaplus.picture.data:
//            CacheProvider

public class LocalCacheProvider
    implements CacheProvider
{
    public static interface DecodeTaskStatus
    {

        public abstract void onTaskBusy();

        public abstract void onTaskFree();
    }

    public static interface IThumbnailDecoder
    {

        public abstract Bitmap decodeImage(MediaItem mediaitem, Context context);

        public abstract void registerDecodeOkListener();

        public abstract void unRegisterDecodeOkListener();
    }


    public static final int ALBUM_THUMB_HEIGHT = 180;
    public static final int ALBUM_THUMB_WIDTH = 180;
    public static final String DBName = "thumb_cache";
    public static final String DBSuffix = ".db";
    private static final String MEDIAPLUS_DIR;
    private static final String ORG_DIR;
    private static final String TAG = "LocalCacheProvider";
    public static final int THUMB_DATA_SIZE = 8192;
    public static final int THUMB_HEIGHT = 128;
    public static final int THUMB_WIDTH = 128;
    final String BUCKET_PROJECTION_IMAGES[] = {
        "bucket_id", "bucket_display_name"
    };
    final String PROJECTION_IMAGES_MEDIA_STORE[] = {
        "_id", "_data", "date_modified"
    };
    private final byte mBuf[] = new byte[8192];
    ContentResolver mCR;
    private final Context mContext;
    SQLiteStatement mInserAlbumtStatemen;
    SQLiteStatement mInserThumbtStatemen;
    boolean mIsDBReady;
    private final ContentObserver mMediaObserver = new ContentObserver(new Handler()) {

        final LocalCacheProvider this$0;

        public void onChange(boolean flag)
        {
            super.onChange(flag);
            Log.e("media observer", "media changed");
        }

            
            {
                this$0 = LocalCacheProvider.this;
                super(handler);
            }
    };
    private volatile boolean mRomoteFlag;
    private SQLiteDatabase mSqlite;
    private final DecodeTaskStatus mStatusListenner;
    IThumbnailDecoder mThumbnailDecoder;
    final Uri uri;

    public LocalCacheProvider(Context context, DecodeTaskStatus decodetaskstatus)
    {
        mInserThumbtStatemen = null;
        mInserAlbumtStatemen = null;
        mThumbnailDecoder = null;
        mIsDBReady = false;
        mRomoteFlag = false;
        mCR = null;
        uri = android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI.buildUpon().appendQueryParameter("distinct", "true").build();
        mContext = context;
        mStatusListenner = decodetaskstatus;
        mCR = mContext.getContentResolver();
        mCR.registerContentObserver(android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI, true, mMediaObserver);
        mCR.registerContentObserver(android.provider.MediaStore.Video.Media.EXTERNAL_CONTENT_URI, true, mMediaObserver);
    }

    private void close()
    {
        closeDB();
        mCR.unregisterContentObserver(mMediaObserver);
    }

    public static LocalCacheProvider create(Context context, DecodeTaskStatus decodetaskstatus)
    {
        LocalCacheProvider localcacheprovider = new LocalCacheProvider(context, decodetaskstatus);
        localcacheprovider.createCache();
        return localcacheprovider;
    }

    private void deleteBitmapFromDatabase(int i)
    {
        this;
        JVM INSTR monitorenter ;
        SQLiteDatabase sqlitedatabase = mSqlite;
        String as[] = new String[1];
        as[0] = String.valueOf(i);
        sqlitedatabase.delete("albums", "_id = ?", as);
_L2:
        this;
        JVM INSTR monitorexit ;
        return;
        SQLiteException sqliteexception;
        sqliteexception;
        sqliteexception.printStackTrace();
        if (true) goto _L2; else goto _L1
_L1:
        Exception exception;
        exception;
        throw exception;
    }

    private void deleteTextureFromDatabase(int i)
    {
        this;
        JVM INSTR monitorenter ;
        SQLiteDatabase sqlitedatabase = mSqlite;
        String as[] = new String[1];
        as[0] = String.valueOf(i);
        sqlitedatabase.delete("files", "_id = ?", as);
_L2:
        this;
        JVM INSTR monitorexit ;
        return;
        SQLiteException sqliteexception;
        sqliteexception;
        sqliteexception.printStackTrace();
        if (true) goto _L2; else goto _L1
_L1:
        Exception exception;
        exception;
        throw exception;
    }

    public static void destroy(LocalCacheProvider localcacheprovider)
    {
        localcacheprovider.close();
    }

    public static String getDBName(Context context)
    {
        String s;
        s = "thumb_cache.db";
        if (context == null)
        {
            return s;
        }
        String s1 = (new StringBuilder()).append("thumb_cache").append(FileUtils.getVersionName(context)).append(".db").toString();
        s = s1;
_L2:
        return s;
        android.content.pm.PackageManager.NameNotFoundException namenotfoundexception;
        namenotfoundexception;
        namenotfoundexception.printStackTrace();
        if (true) goto _L2; else goto _L1
_L1:
    }

    private long getItemSize(Uri uri1, long l)
    {
        long l1 = 0L;
        String s = uri1.toString();
        if (s.startsWith("file://"))
        {
            File file = new File(s.substring(7));
            if (file.exists())
            {
                l1 = file.length();
            }
            return l1;
        } else
        {
            return l;
        }
    }

    private boolean isAlbumCached(MediaItem mediaitem)
    {
        Cursor cursor;
        boolean flag1;
        while (!isReady() || mediaitem == null || mediaitem.uri == null) 
        {
            return false;
        }
        SQLiteDatabase sqlitedatabase = mSqlite;
        String as[] = new String[3];
        as[0] = mediaitem.title;
        boolean flag = mediaitem.videoOrImage;
        int i = 0;
        if (flag)
        {
            i = 1;
        }
        as[1] = String.valueOf(i);
        as[2] = mediaitem.uri.toString();
        cursor = sqlitedatabase.rawQuery("select thumb from albums where title=? And mediaType=? And url=?", as);
        flag1 = false;
        if (cursor == null)
        {
            break MISSING_BLOCK_LABEL_110;
        }
        boolean flag2 = cursor.moveToFirst();
        flag1 = false;
        if (flag2)
        {
            flag1 = true;
        }
        if (cursor != null)
        {
            cursor.close();
        }
        return flag1;
        Exception exception;
        exception;
        if (cursor != null)
        {
            cursor.close();
        }
        throw exception;
    }

    public static boolean isSDCardAvailable()
    {
        for (String s = Environment.getExternalStorageState(); !"mounted".equals(s) && !"mounted_ro".equals(s) || FileUtils.getAndroidDataAvailableSize() < 10240L;)
        {
            return false;
        }

        return true;
    }

    private void saveBitmapToDatabase(MediaItem mediaitem, Bitmap bitmap)
    {
        this;
        JVM INSTR monitorenter ;
          goto _L1
_L3:
        this;
        JVM INSTR monitorexit ;
        return;
_L1:
        if (bitmap == null || mediaitem == null) goto _L3; else goto _L2
_L2:
        byte abyte0[];
        boolean flag;
        if (mediaitem.isDownloadingFile || mediaitem.uri == null)
        {
            continue; /* Loop/switch isn't completed */
        }
        ByteArrayOutputStream bytearrayoutputstream = new ByteArrayOutputStream(8192);
        bitmap.compress(android.graphics.Bitmap.CompressFormat.JPEG, 75, bytearrayoutputstream);
        abyte0 = bytearrayoutputstream.toByteArray();
        flag = isAlbumCached(mediaitem);
        if (flag)
        {
            continue; /* Loop/switch isn't completed */
        }
        SQLiteStatement sqlitestatement;
        mInserAlbumtStatemen.bindString(2, mediaitem.title);
        sqlitestatement = mInserAlbumtStatemen;
        Exception exception;
        SQLiteException sqliteexception;
        long l;
        if (mediaitem.videoOrImage)
        {
            l = 1L;
        } else
        {
            l = 0L;
        }
        sqlitestatement.bindLong(3, l);
        mInserAlbumtStatemen.bindString(4, mediaitem.uri.toString());
        mInserAlbumtStatemen.bindBlob(5, abyte0);
        mInserAlbumtStatemen.executeInsert();
        continue; /* Loop/switch isn't completed */
        sqliteexception;
        sqliteexception.printStackTrace();
        if (true) goto _L3; else goto _L4
_L4:
        exception;
        throw exception;
    }

    public void closeDB()
    {
        this;
        JVM INSTR monitorenter ;
        if (mInserThumbtStatemen != null)
        {
            mInserThumbtStatemen.close();
            mInserThumbtStatemen = null;
        }
        if (mInserAlbumtStatemen != null)
        {
            mInserAlbumtStatemen.close();
            mInserAlbumtStatemen = null;
        }
        if (mSqlite != null)
        {
            mSqlite.close();
            mSqlite = null;
        }
        this;
        JVM INSTR monitorexit ;
        return;
        Exception exception;
        exception;
        throw exception;
    }

    public void createCache()
    {
        setDBReady(isSDCardAvailable());
        if (!isReady())
        {
            return;
        }
        String s = OEMConfig.CACHE_BASE_PATH;
        File file = new File(s);
        if (!file.exists())
        {
            file.mkdirs();
        }
        try
        {
            closeDB();
            mSqlite = SQLiteDatabase.openOrCreateDatabase((new StringBuilder()).append(s).append(getDBName(mContext)).toString(), null);
            mSqlite.execSQL("create table if not exists files(_id integer primary key not null, title TEXT, mediaType integer, size integer, url TEXT, thumb BLOB)");
            mSqlite.execSQL("create table if not exists albums(_id integer primary key not null, title TEXT, mediaType integer, url TEXT, thumb BLOB)");
            mInserThumbtStatemen = mSqlite.compileStatement("insert into files(_id,title,mediaType,size,url,thumb) VALUES(?,?,?,?,?,?)");
            mInserAlbumtStatemen = mSqlite.compileStatement("insert into albums(_id,title,mediaType,url,thumb) VALUES(?,?,?,?,?)");
            return;
        }
        catch (Exception exception)
        {
            exception.printStackTrace();
        }
    }

    public Bitmap createThumbnail(MediaItem mediaitem, int i, int j)
    {
        return mThumbnailDecoder.decodeImage(mediaitem, mContext);
    }

    public Bitmap getAlbum(MediaItem mediaitem)
    {
        boolean flag;
        Bitmap bitmap;
        flag = isReady();
        bitmap = null;
        if (flag) goto _L2; else goto _L1
_L1:
        return bitmap;
_L2:
        Cursor cursor;
        bitmap = null;
        if (mediaitem == null)
        {
            continue; /* Loop/switch isn't completed */
        }
        boolean flag1 = mediaitem.isDownloadingFile;
        bitmap = null;
        if (flag1)
        {
            continue; /* Loop/switch isn't completed */
        }
        Uri uri1 = mediaitem.uri;
        bitmap = null;
        if (uri1 == null)
        {
            continue; /* Loop/switch isn't completed */
        }
        SQLiteDatabase sqlitedatabase = mSqlite;
        String as[] = new String[3];
        as[0] = mediaitem.title;
        boolean flag2 = mediaitem.videoOrImage;
        int i = 0;
        if (flag2)
        {
            i = 1;
        }
        as[1] = String.valueOf(i);
        as[2] = mediaitem.uri.toString();
        cursor = sqlitedatabase.rawQuery("select thumb from albums where title=? And mediaType=? And url=?", as);
        if (cursor == null) goto _L4; else goto _L3
_L3:
        if (!cursor.moveToFirst()) goto _L4; else goto _L5
_L5:
        Bitmap bitmap1;
        android.graphics.BitmapFactory.Options options = new android.graphics.BitmapFactory.Options();
        byte abyte0[] = cursor.getBlob(0);
        options.inPreferredConfig = android.graphics.Bitmap.Config.RGB_565;
        bitmap1 = BitmapFactory.decodeByteArray(abyte0, 0, abyte0.length, options);
        bitmap = bitmap1;
_L7:
        if (cursor != null)
        {
            cursor.close();
            return bitmap;
        }
        break; /* Loop/switch isn't completed */
_L4:
        bitmap = createThumbnail(mediaitem, 180, 180);
        if (bitmap == null)
        {
            continue; /* Loop/switch isn't completed */
        }
        saveBitmapToDatabase(mediaitem, bitmap);
        if (true) goto _L7; else goto _L6
_L6:
        if (true) goto _L1; else goto _L8
_L8:
        Exception exception;
        exception;
        if (cursor != null)
        {
            cursor.close();
        }
        throw exception;
    }

    public android.opengl.ETC1Util.ETC1Texture getETC1Texture(Bitmap bitmap)
    {
        ByteBuffer bytebuffer;
        if (bitmap != null)
        {
            if ((bytebuffer = ByteBuffer.allocateDirect(bitmap.getRowBytes() * bitmap.getHeight())) != null)
            {
                bytebuffer.order(ByteOrder.nativeOrder());
                bitmap.copyPixelsToBuffer(bytebuffer);
                bytebuffer.position(0);
                ByteBuffer bytebuffer1 = ByteBuffer.allocateDirect(ETC1.getEncodedDataSize(bitmap.getWidth(), bitmap.getHeight())).order(ByteOrder.nativeOrder());
                ETC1.encodeImage(bytebuffer, bitmap.getWidth(), bitmap.getHeight(), 2, 2 * bitmap.getWidth(), bytebuffer1);
                return new android.opengl.ETC1Util.ETC1Texture(bitmap.getWidth(), bitmap.getHeight(), bytebuffer1);
            }
        }
        return null;
    }

    public android.opengl.ETC1Util.ETC1Texture getETC1Texture(MediaItem mediaitem)
    {
        if (mediaitem != null && mSqlite != null && mediaitem.title != null && mediaitem.uri != null) goto _L2; else goto _L1
_L1:
        android.opengl.ETC1Util.ETC1Texture etc1texture = null;
_L7:
        return etc1texture;
_L2:
        Cursor cursor;
        etc1texture = null;
        if (!isReady())
        {
            return null;
        }
        String s = String.valueOf(getItemSize(mediaitem.uri, mediaitem.size));
        SQLiteDatabase sqlitedatabase = mSqlite;
        String as[] = new String[4];
        as[0] = mediaitem.title;
        int i;
        byte abyte0[];
        ByteBuffer bytebuffer1;
        ByteBuffer bytebuffer2;
        android.opengl.ETC1Util.ETC1Texture etc1texture1;
        if (mediaitem.videoOrImage)
        {
            i = 1;
        } else
        {
            i = 0;
        }
        as[1] = String.valueOf(i);
        as[2] = mediaitem.uri.toString();
        as[3] = s;
        cursor = sqlitedatabase.rawQuery("select thumb from files where title=? And mediaType=? And url=? And size=?", as);
        if (cursor == null) goto _L4; else goto _L3
_L3:
        if (!cursor.moveToFirst()) goto _L4; else goto _L5
_L5:
        abyte0 = cursor.getBlob(0);
        bytebuffer1 = ByteBuffer.allocateDirect(abyte0.length);
        bytebuffer1.order(ByteOrder.nativeOrder());
        bytebuffer2 = bytebuffer1.put(abyte0);
        bytebuffer2.position(0);
        etc1texture1 = new android.opengl.ETC1Util.ETC1Texture(128, 128, bytebuffer2);
        etc1texture = etc1texture1;
_L9:
        if (cursor == null) goto _L7; else goto _L6
_L6:
        cursor.close();
        return etc1texture;
_L4:
        Bitmap bitmap = createThumbnail(mediaitem, 128, 128);
        etc1texture = null;
        if (bitmap == null) goto _L9; else goto _L8
_L8:
        etc1texture = getETC1Texture(bitmap);
        ByteBuffer bytebuffer = etc1texture.getData();
        saveETCToDatabase(mediaitem, etc1texture);
        bitmap.recycle();
        bytebuffer.position(0);
          goto _L9
        Exception exception1;
        exception1;
        exception1.printStackTrace();
        if (cursor == null) goto _L7; else goto _L10
_L10:
        cursor.close();
        return etc1texture;
        Exception exception;
        exception;
        if (cursor != null)
        {
            cursor.close();
        }
        throw exception;
    }

    public boolean isReady()
    {
        return mIsDBReady;
    }

    public boolean isThumbCached(MediaItem mediaitem)
    {
        Cursor cursor;
        boolean flag1;
        while (!isReady() || mediaitem == null || mediaitem.uri == null) 
        {
            return false;
        }
        String s = String.valueOf(getItemSize(mediaitem.uri, mediaitem.size));
        SQLiteDatabase sqlitedatabase = mSqlite;
        String as[] = new String[4];
        as[0] = mediaitem.title;
        boolean flag = mediaitem.videoOrImage;
        int i = 0;
        if (flag)
        {
            i = 1;
        }
        as[1] = String.valueOf(i);
        as[2] = mediaitem.uri.toString();
        as[3] = s;
        cursor = sqlitedatabase.rawQuery("select thumb from files where title=? And mediaType=? And url=? And size=?", as);
        flag1 = false;
        if (cursor == null)
        {
            break MISSING_BLOCK_LABEL_136;
        }
        boolean flag2 = cursor.moveToFirst();
        flag1 = false;
        if (flag2)
        {
            flag1 = true;
        }
        if (cursor != null)
        {
            cursor.close();
        }
        return flag1;
        Exception exception;
        exception;
        if (cursor != null)
        {
            cursor.close();
        }
        throw exception;
    }

    public void onTaskBusy()
    {
        mStatusListenner.onTaskBusy();
    }

    public void onTaskFree()
    {
        mStatusListenner.onTaskFree();
    }

    public void removeInvalidFiles()
    {
    }

    public void saveETCToDatabase(MediaItem mediaitem, android.opengl.ETC1Util.ETC1Texture etc1texture)
    {
        this;
        JVM INSTR monitorenter ;
          goto _L1
_L3:
        this;
        JVM INSTR monitorexit ;
        return;
_L1:
        if (etc1texture == null || mediaitem == null) goto _L3; else goto _L2
_L2:
        boolean flag;
        if (mediaitem.isDownloadingFile || mediaitem.uri == null)
        {
            continue; /* Loop/switch isn't completed */
        }
        ByteBuffer bytebuffer = etc1texture.getData();
        bytebuffer.position(0);
        bytebuffer.get(mBuf);
        flag = isThumbCached(mediaitem);
        if (flag)
        {
            continue; /* Loop/switch isn't completed */
        }
        long l;
        SQLiteStatement sqlitestatement;
        l = getItemSize(mediaitem.uri, mediaitem.size);
        mInserThumbtStatemen.bindString(2, mediaitem.title);
        sqlitestatement = mInserThumbtStatemen;
        Exception exception;
        SQLiteException sqliteexception;
        long l1;
        if (mediaitem.videoOrImage)
        {
            l1 = 1L;
        } else
        {
            l1 = 0L;
        }
        sqlitestatement.bindLong(3, l1);
        mInserThumbtStatemen.bindLong(4, l);
        mInserThumbtStatemen.bindString(5, mediaitem.uri.toString());
        mInserThumbtStatemen.bindBlob(6, mBuf);
        mInserThumbtStatemen.executeInsert();
        continue; /* Loop/switch isn't completed */
        sqliteexception;
        sqliteexception.printStackTrace();
        if (true) goto _L3; else goto _L4
_L4:
        exception;
        throw exception;
    }

    protected void setDBReady(boolean flag)
    {
        mIsDBReady = flag;
    }

    public void setRomoteFlag(boolean flag)
    {
        mRomoteFlag = flag;
    }

    public void setThumbnailDecoder(IThumbnailDecoder ithumbnaildecoder)
    {
        mThumbnailDecoder = ithumbnaildecoder;
    }

    public void updateListForDelete(int ai[])
    {
    }

    public void updateListForDeleteDownload(String s)
    {
    }

    public void updateListForDownloadOrEdit(String s, boolean flag)
    {
    }

    static 
    {
        MEDIAPLUS_DIR = (new StringBuilder()).append(Environment.getExternalStorageDirectory().toString()).append(File.separator).append("MediaPlus").toString();
        ORG_DIR = (new StringBuilder()).append(MEDIAPLUS_DIR).append(File.separator).append("From_WiFi_Camera").toString();
    }
}
