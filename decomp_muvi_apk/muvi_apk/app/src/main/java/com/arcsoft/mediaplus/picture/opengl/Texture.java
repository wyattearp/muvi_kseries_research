// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.arcsoft.mediaplus.picture.opengl;


// Referenced classes of package com.arcsoft.mediaplus.picture.opengl:
//            GLCanvas

public interface Texture
{

    public abstract void draw(GLCanvas glcanvas, int i, int j);

    public abstract void draw(GLCanvas glcanvas, int i, int j, int k, int l);

    public abstract int getHeight();

    public abstract int getWidth();

    public abstract boolean isOpaque();
}
