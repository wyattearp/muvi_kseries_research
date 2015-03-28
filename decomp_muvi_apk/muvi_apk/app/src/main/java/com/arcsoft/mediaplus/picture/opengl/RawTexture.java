// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.arcsoft.mediaplus.picture.opengl;

import java.lang.ref.WeakReference;
import javax.microedition.khronos.opengles.GL11;

// Referenced classes of package com.arcsoft.mediaplus.picture.opengl:
//            BasicTexture, GLCanvas

class RawTexture extends BasicTexture
{

    private RawTexture(GLCanvas glcanvas, int i)
    {
        super(glcanvas, i, 1);
    }

    public static RawTexture newInstance(GLCanvas glcanvas)
    {
        int ai[] = new int[1];
        glcanvas.getGLInstance().glGenTextures(1, ai, 0);
        return new RawTexture(glcanvas, ai[0]);
    }

    public boolean isOpaque()
    {
        return true;
    }

    protected boolean onBind(GLCanvas glcanvas)
    {
        if (mCanvasRef.get() != glcanvas)
        {
            throw new RuntimeException("cannot bind to different canvas");
        } else
        {
            return true;
        }
    }

    public void yield()
    {
    }
}
