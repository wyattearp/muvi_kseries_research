// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package powermobia.videoeditor.clip;


public class MMediaSource
{

    public static final int TYPE_BITMAP = 1;
    public static final int TYPE_BUBBLETEXT = 2;
    public static final int TYPE_FILE;
    private boolean isTempSource;
    private Object source;
    private int type;

    public MMediaSource()
    {
        type = 0;
        isTempSource = false;
        source = null;
    }

    public MMediaSource(int i, boolean flag, Object obj)
    {
        type = 0;
        isTempSource = false;
        source = null;
        type = i;
        isTempSource = flag;
        source = obj;
    }

    public Object getSource()
    {
        return source;
    }

    public int getSourceType()
    {
        return type;
    }

    public boolean isTempSource()
    {
        return isTempSource;
    }
}
