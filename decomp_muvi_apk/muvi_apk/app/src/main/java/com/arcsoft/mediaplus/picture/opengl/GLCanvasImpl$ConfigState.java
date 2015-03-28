// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.arcsoft.mediaplus.picture.opengl;

import android.graphics.Rect;
import android.opengl.GLES20;

// Referenced classes of package com.arcsoft.mediaplus.picture.opengl:
//            GLCanvasImpl

private static class <init>
{

    float mAlpha;
    float mMatrix[];
    mMatrix mNextFree;
    Rect mRect;

    public void restore(GLCanvasImpl glcanvasimpl)
    {
        if (mAlpha >= 0.0F)
        {
            glcanvasimpl.setAlpha(mAlpha);
        }
        if (mRect.left != 0x7fffffff)
        {
            Rect rect = mRect;
            GLCanvasImpl.access$100(glcanvasimpl).set(rect);
            GLES20.glScissor(rect.left, rect.top, rect.width(), rect.height());
        }
        if (mMatrix[0] != (-1.0F / 0.0F))
        {
            System.arraycopy(mMatrix, 0, GLCanvasImpl.access$200(glcanvasimpl), 0, 16);
        }
    }

    private ()
    {
        mRect = new Rect();
        mMatrix = new float[16];
    }

    mMatrix(mMatrix mmatrix)
    {
        this();
    }
}
