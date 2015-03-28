// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.arcsoft.mediaplus.playview;


// Referenced classes of package com.arcsoft.mediaplus.playview:
//            PlayView

public class position
{

    int index;
    boolean playing;
    long position;
    final PlayView this$0;

    _cls9(int i, long l)
    {
        this$0 = PlayView.this;
        super();
        playing = true;
        index = i;
        position = l;
    }
}
