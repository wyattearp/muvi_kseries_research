// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package powermobia.videoeditor.clip;


// Referenced classes of package powermobia.videoeditor.clip:
//            MMediaSource

public class MTextSource
{

    private int index;
    MMediaSource source;

    public MTextSource()
    {
        index = 0;
        source = null;
        index = 0;
        source = null;
    }

    public MTextSource(int i, MMediaSource mmediasource)
    {
        index = 0;
        source = null;
        index = i;
        source = mmediasource;
    }

    public int getIndex()
    {
        return index;
    }

    public MMediaSource getSource()
    {
        return source;
    }

    public void setIndex(int i)
    {
        index = i;
    }

    public void setSource(MMediaSource mmediasource)
    {
        source = mmediasource;
    }
}
