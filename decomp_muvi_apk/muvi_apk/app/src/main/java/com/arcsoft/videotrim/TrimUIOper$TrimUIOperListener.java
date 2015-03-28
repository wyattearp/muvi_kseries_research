// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.arcsoft.videotrim;

import com.arcsoft.videotrim.Utils.ThumbManagerList;

// Referenced classes of package com.arcsoft.videotrim:
//            TrimUIOper

public static interface _cls9
{

    public abstract void notifyCurScaleLevelChanged();

    public abstract void notifyScaleLevelChanged(int i, int j);

    public abstract void notifySeekToValue(int i);

    public abstract void notifyValueChanged(boolean flag, int i);

    public abstract void onTrimThumbManagerList(ThumbManagerList thumbmanagerlist);
}
