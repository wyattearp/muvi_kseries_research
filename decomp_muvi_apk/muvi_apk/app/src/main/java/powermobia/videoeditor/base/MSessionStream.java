// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package powermobia.videoeditor.base;

import powermobia.videoeditor.clip.MClip;

// Referenced classes of package powermobia.videoeditor.base:
//            MSession, MRange, MDisplayContext

public class MSessionStream
{

    public static final int DECODER_USAGE_TYPE_AUTO = 4;
    public static final int DECODER_USAGE_TYPE_HW = 1;
    public static final int DECODER_USAGE_TYPE_PLAYBACK_THUMBNAIL = 16;
    public static final int DECODER_USAGE_TYPE_SW = 2;
    public static final int DECODER_USAGE_TYPE_THUMBNAIL = 8;
    private static final int SOURCE_TYPE_AUTOCUT = 5;
    public static final int SOURCE_TYPE_CLIP = 2;
    private static final int SOURCE_TYPE_MSS = 4;
    public static final int SOURCE_TYPE_STORYBOARD = 1;
    public static final int SOURCE_TYPE_TRANSITION = 3;
    private long handle;

    public MSessionStream()
    {
        handle = 0L;
    }

    private native int nativeClose(MSessionStream msessionstream);

    private native int nativeOpen(int i, MSession msession, int j, int k, MRange mrange, MClip mclip, MDisplayContext mdisplaycontext, 
            MSessionStream msessionstream, int l);

    public int close()
    {
        return nativeClose(this);
    }

    public int open(int i, MSession msession, int j, int k, MRange mrange, MClip mclip, MDisplayContext mdisplaycontext, 
            int l)
    {
        return nativeOpen(i, msession, j, k, mrange, mclip, mdisplaycontext, this, l);
    }
}
