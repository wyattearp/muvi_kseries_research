// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.arcsoft.adk.image;

import android.graphics.Rect;

// Referenced classes of package com.arcsoft.adk.image:
//            PhotoViewer

public class mImgDisplayRect
{

    public int mImageOrientationToScreen;
    public Rect mImgDisplayRect;
    public int mImgHeight;
    public int mImgIndex;
    public int mImgWidth;
    public int mZoom;
    public boolean mbIsFitIn;
    public boolean mbTransColor;
    final PhotoViewer this$0;

    public ()
    {
        this$0 = PhotoViewer.this;
        super();
        mImgDisplayRect = new Rect();
    }
}
