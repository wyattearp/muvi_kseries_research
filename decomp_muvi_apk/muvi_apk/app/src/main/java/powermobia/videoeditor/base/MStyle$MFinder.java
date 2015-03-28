// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package powermobia.videoeditor.base;


// Referenced classes of package powermobia.videoeditor.base:
//            MStyle

public static class handle
{

    private long handle;

    private native int nativeCreate(String s, int i, String s1);

    private native int nativeDestroy();

    private native int nativeGetCount(long l);

    private native String nativeGetFileName(long l, int i);

    private native int nativeUpdate(long l);

    public int create(String s, int i, String s1)
    {
        if (s == null)
        {
            return 0x80003;
        }
        if (handle != 0L)
        {
            destroy();
        }
        return nativeCreate(s, i, s1);
    }

    public int destroy()
    {
        if (handle == 0L)
        {
            return 0x80006;
        } else
        {
            return nativeDestroy();
        }
    }

    public int getCount()
    {
        if (handle == 0L)
        {
            return 0x80006;
        } else
        {
            return nativeGetCount(handle);
        }
    }

    public String getFileName(int i)
    {
        if (handle == 0L)
        {
            return null;
        } else
        {
            return nativeGetFileName(handle, i);
        }
    }

    public int update()
    {
        if (handle == 0L)
        {
            return 0x80006;
        } else
        {
            return nativeUpdate(handle);
        }
    }

    public ()
    {
        handle = 0L;
    }
}
