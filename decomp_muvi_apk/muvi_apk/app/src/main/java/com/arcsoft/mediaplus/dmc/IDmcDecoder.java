// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.arcsoft.mediaplus.dmc;

import android.graphics.Bitmap;

public interface IDmcDecoder
{

    public abstract void decodeFinish(int i, Bitmap bitmap, DmcUtils.COVER_TYPE cover_type);
}
