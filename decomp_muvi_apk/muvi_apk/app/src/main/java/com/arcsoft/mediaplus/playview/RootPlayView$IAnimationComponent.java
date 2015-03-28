// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.arcsoft.mediaplus.playview;

import android.graphics.Bitmap;
import android.graphics.Point;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.view.View;

// Referenced classes of package com.arcsoft.mediaplus.playview:
//            RootPlayView

public static interface 
{

    public abstract View getControlView();

    public abstract Drawable getDefaultDrawable();

    public abstract Bitmap getDisplayBitmap(Point point);

    public abstract Rect getDisplayLayout();

    public abstract boolean isActive();
}
