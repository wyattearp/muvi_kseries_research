// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package android.media;

import android.content.ContentResolver;
import android.content.Context;
import android.content.res.AssetFileDescriptor;
import android.graphics.Bitmap;
import android.net.Uri;
import java.io.FileDescriptor;
import java.io.FileNotFoundException;
import java.io.IOException;

public class MediaMetadataRetriever
{

    private static final int EMBEDDED_PICTURE_TYPE_ANY = 65535;
    public static final int METADATA_KEY_ALBUM = 1;
    public static final int METADATA_KEY_ARTIST = 2;
    public static final int METADATA_KEY_AUTHOR = 3;
    public static final int METADATA_KEY_BIT_RATE = 16;
    public static final int METADATA_KEY_CD_TRACK_NUMBER = 0;
    public static final int METADATA_KEY_CODEC = 12;
    public static final int METADATA_KEY_COMMENT = 14;
    public static final int METADATA_KEY_COMPOSER = 4;
    public static final int METADATA_KEY_COPYRIGHT = 15;
    public static final int METADATA_KEY_DATE = 5;
    public static final int METADATA_KEY_DURATION = 9;
    public static final int METADATA_KEY_FRAME_RATE = 17;
    public static final int METADATA_KEY_GENRE = 6;
    public static final int METADATA_KEY_IS_DRM_CRIPPLED = 11;
    public static final int METADATA_KEY_NUM_TRACKS = 10;
    public static final int METADATA_KEY_RATING = 13;
    public static final int METADATA_KEY_TITLE = 7;
    public static final int METADATA_KEY_VIDEO_FORMAT = 18;
    public static final int METADATA_KEY_VIDEO_HEIGHT = 19;
    public static final int METADATA_KEY_VIDEO_WIDTH = 20;
    public static final int METADATA_KEY_WRITER = 21;
    public static final int METADATA_KEY_YEAR = 8;
    public static final int MODE_CAPTURE_FRAME_ONLY = 2;
    public static final int MODE_GET_METADATA_ONLY = 1;
    public static final int OPTION_CLOSEST = 3;
    public static final int OPTION_CLOSEST_SYNC = 2;
    public static final int OPTION_NEXT_SYNC = 1;
    public static final int OPTION_PREVIOUS_SYNC;
    private int mNativeContext;

    public MediaMetadataRetriever()
    {
        native_setup();
    }

    private native Bitmap _getFrameAtTime(long l, int i);

    private native byte[] getEmbeddedPicture(int i);

    private final native void native_finalize();

    private static native void native_init();

    private native void native_setup();

    public native Bitmap captureFrame();

    public native byte[] extractAlbumArt();

    public native String extractMetadata(int i);

    protected void finalize()
        throws Throwable
    {
        native_finalize();
        super.finalize();
        return;
        Exception exception;
        exception;
        super.finalize();
        throw exception;
    }

    public byte[] getEmbeddedPicture()
    {
        return getEmbeddedPicture(65535);
    }

    public Bitmap getFrameAtTime()
    {
        return getFrameAtTime(-1L, 2);
    }

    public Bitmap getFrameAtTime(long l)
    {
        return getFrameAtTime(l, 2);
    }

    public Bitmap getFrameAtTime(long l, int i)
    {
        if (i < 0 || i > 3)
        {
            throw new IllegalArgumentException((new StringBuilder()).append("Unsupported option: ").append(i).toString());
        } else
        {
            return _getFrameAtTime(l, i);
        }
    }

    public native int getMode();

    public native void release();

    public void setDataSource(Context context, Uri uri)
        throws IllegalArgumentException, SecurityException
    {
        String s;
        if (uri == null)
        {
            throw new IllegalArgumentException();
        }
        s = uri.getScheme();
        if (s != null && !s.equals("file")) goto _L2; else goto _L1
_L1:
        setDataSource(uri.getPath());
_L5:
        return;
_L2:
        AssetFileDescriptor assetfiledescriptor = null;
        ContentResolver contentresolver = context.getContentResolver();
        AssetFileDescriptor assetfiledescriptor1 = contentresolver.openAssetFileDescriptor(uri, "r");
        assetfiledescriptor = assetfiledescriptor1;
        if (assetfiledescriptor == null)
        {
            Exception exception;
            FileNotFoundException filenotfoundexception;
            try
            {
                throw new IllegalArgumentException();
            }
            catch (SecurityException securityexception) { }
            finally
            {
                if (assetfiledescriptor == null) goto _L0; else goto _L0
            }
            FileDescriptor filedescriptor;
            IOException ioexception2;
            if (assetfiledescriptor != null)
            {
                try
                {
                    assetfiledescriptor.close();
                }
                catch (IOException ioexception1)
                {
                    ioexception1.printStackTrace();
                }
            }
            setDataSource(uri.toString());
            return;
        }
        break MISSING_BLOCK_LABEL_121;
        filenotfoundexception;
        throw new IllegalArgumentException();
        try
        {
            assetfiledescriptor.close();
        }
        catch (IOException ioexception)
        {
            ioexception.printStackTrace();
        }
        throw exception;
        filedescriptor = assetfiledescriptor.getFileDescriptor();
        if (!filedescriptor.valid())
        {
            throw new IllegalArgumentException();
        }
        if (assetfiledescriptor.getDeclaredLength() >= 0L)
        {
            break MISSING_BLOCK_LABEL_179;
        }
        setDataSource(filedescriptor);
_L3:
        if (assetfiledescriptor != null)
        {
            try
            {
                assetfiledescriptor.close();
                return;
            }
            // Misplaced declaration of an exception variable
            catch (IOException ioexception2)
            {
                ioexception2.printStackTrace();
            }
            return;
        }
        continue; /* Loop/switch isn't completed */
        setDataSource(filedescriptor, assetfiledescriptor.getStartOffset(), assetfiledescriptor.getDeclaredLength());
          goto _L3
        if (true) goto _L5; else goto _L4
_L4:
    }

    public void setDataSource(FileDescriptor filedescriptor)
        throws IllegalArgumentException
    {
        setDataSource(filedescriptor, 0L, 0x7ffffffffffffffL);
    }

    public native void setDataSource(FileDescriptor filedescriptor, long l, long l1)
        throws IllegalArgumentException;

    public native void setDataSource(String s)
        throws IllegalArgumentException;

    public native void setMode(int i);

    static 
    {
        System.loadLibrary("media_jni");
        native_init();
    }
}
