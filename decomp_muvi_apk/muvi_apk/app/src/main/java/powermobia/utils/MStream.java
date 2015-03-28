// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package powermobia.utils;


public class MStream
{

    private static final int INVALID_HMSTREAM = 0;
    public static final int STREAM_APPEND = 3;
    public static final int STREAM_ASYNC_WRITE = 7;
    public static final int STREAM_A_PLUS = 6;
    public static final int STREAM_BEGIN = 0;
    public static final int STREAM_CONSUME_NORMAL = 1;
    public static final int STREAM_CONSUME_PREVIEW = 2;
    public static final int STREAM_CUR = 2;
    public static final int STREAM_DRM_READ = 257;
    public static final int STREAM_END = 1;
    public static final int STREAM_READ = 1;
    public static final int STREAM_R_PLUS = 4;
    public static final int STREAM_WRITE = 2;
    public static final int STREAM_W_PLUS = 5;
    private int mNativeStream;

    public MStream()
    {
        mNativeStream = 0;
        mNativeStream = 0;
    }

    public static boolean fileCopy(String s, String s1, boolean flag)
    {
        return native_StreamFileCopy(s, s1, flag);
    }

    public static boolean fileDelete(String s)
    {
        return native_StreamFileDelete(s);
    }

    public static boolean fileExists(String s)
    {
        return native_StreamFileExists(s);
    }

    public static int fileGetSize(String s)
    {
        return native_StreamFileGetSize(s);
    }

    public static boolean fileMove(String s, String s1)
    {
        return native_StreamFileMove(s, s1);
    }

    public static boolean fileRename(String s, String s1)
    {
        return native_StreamFileRename(s, s1);
    }

    private native boolean native_StreamClose(int i);

    private native int native_StreamCreate(String s);

    private static native long native_StreamDelete(String s);

    private static native boolean native_StreamFileCopy(String s, String s1, boolean flag);

    private static native boolean native_StreamFileDelete(String s);

    private static native boolean native_StreamFileExists(String s);

    private static native int native_StreamFileGetSize(String s);

    private static native boolean native_StreamFileMove(String s, String s1);

    private static native boolean native_StreamFileRename(String s, String s1);

    private native boolean native_StreamFlush(int i);

    private native int native_StreamGetSize(int i);

    private native int native_StreamOpen(String s, int i);

    private native int native_StreamOpenFromMemoryBlock(byte abyte0[], int i);

    private native int native_StreamRead(int i, byte abyte0[], int j);

    private native int native_StreamSeek(int i, int j, int k);

    private native int native_StreamSetSize(int i, int j);

    private native int native_StreamTell(int i);

    private native int native_StreamWrite(int i, byte abyte0[], int j);

    public boolean close()
    {
        return native_StreamClose(mNativeStream);
    }

    public boolean flush()
    {
        return native_StreamFlush(mNativeStream);
    }

    public int getNativeHandle()
    {
        return mNativeStream;
    }

    public int getSize()
    {
        return native_StreamGetSize(mNativeStream);
    }

    public int getStreamHandle()
    {
        return mNativeStream;
    }

    public boolean isValidStream()
    {
        return mNativeStream != 0;
    }

    public boolean open(String s, int i)
    {
        mNativeStream = native_StreamOpen(s, i);
        return mNativeStream != 0;
    }

    public boolean open(byte abyte0[])
    {
        int i;
        if (abyte0 == null)
        {
            i = 0;
        } else
        {
            i = abyte0.length;
        }
        mNativeStream = native_StreamOpenFromMemoryBlock(abyte0, i);
        return mNativeStream != 0;
    }

    public int read(byte abyte0[], int i)
    {
        return native_StreamRead(mNativeStream, abyte0, i);
    }

    public int seek(int i, int j)
    {
        return native_StreamSeek(mNativeStream, i, j);
    }

    public int setSize(int i)
    {
        return native_StreamSetSize(mNativeStream, i);
    }

    public int tell()
    {
        return native_StreamTell(mNativeStream);
    }

    public int write(byte abyte0[], int i)
    {
        return native_StreamWrite(mNativeStream, abyte0, i);
    }
}
