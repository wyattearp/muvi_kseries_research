// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package powermobia.utils;


// Referenced classes of package powermobia.utils:
//            MStream

public final class MImgFileInfo
{

    public static final int AM_BMP = 1;
    public static final int AM_FILE_SUPPORTED = -1;
    public static final int AM_GIF = 4;
    public static final int AM_IMAGE_FF_MASK = 65535;
    public static final int AM_JPEG = 2;
    public static final int AM_PNG = 8;
    public static final int AM_SUBFMT_GIF_ANIMATED = 3;
    public static final int AM_SUBFMT_GIF_STILL = 2;
    public static final int AM_SUBFMT_JPEG_BASELINE = 4;
    public static final int AM_SUBFMT_JPEG_PROGRESSIVE = 5;
    public static final int AM_SUBFMT_SVG_COMPRESSED = 7;
    public static final int AM_SUBFMT_SVG_UNCOMPRESSED = 6;
    public static final int AM_SUBFMT_UNKNOWN = 1;
    public static final int AM_SUBFMT_UNUSED = 0;
    public static final int AM_SVG = 256;
    public static final int AM_SWF = 128;
    public static final int AM_TIF = 1024;
    public static final int AM_TTF = 512;
    public static final int AM_UNKNOWN = 0;
    public static final int AM_WBMP = 32;
    private int mBitCounts;
    private int mColorSpace;
    private int mFileFormat;
    private int mFileSubformat;
    private int mHeight;
    private boolean mReadFromStream;
    private int mStream;
    private int mWidth;
    private String mstrFileName;

    public MImgFileInfo(MStream mstream)
    {
        mStream = mstream.getStreamHandle();
        mReadFromStream = false;
    }

    private native int native_GetImgFileInfo(int i);

    private int readFromStream()
    {
        int i = native_GetImgFileInfo(mStream);
        mReadFromStream = true;
        return i;
    }

    public int getBitCounts()
    {
        if (!mReadFromStream)
        {
            readFromStream();
        }
        return mBitCounts;
    }

    public int getColorSpace()
    {
        if (!mReadFromStream)
        {
            readFromStream();
        }
        return mColorSpace;
    }

    public int getFileFormat()
    {
        if (!mReadFromStream)
        {
            readFromStream();
        }
        return mFileFormat;
    }

    public int getFileSubformat()
    {
        if (!mReadFromStream)
        {
            readFromStream();
        }
        return mFileSubformat;
    }

    public int getHeight()
    {
        if (!mReadFromStream)
        {
            readFromStream();
        }
        return mHeight;
    }

    public int getWidth()
    {
        if (!mReadFromStream)
        {
            readFromStream();
        }
        return mWidth;
    }
}
