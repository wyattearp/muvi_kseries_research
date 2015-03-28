// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.arcsoft.mediaplus.picture.opengl;

import android.graphics.RectF;
import javax.microedition.khronos.opengles.GL11;

// Referenced classes of package com.arcsoft.mediaplus.picture.opengl:
//            BasicTexture, GLPaint

public interface GLCanvas
{

    public static final int SAVE_FLAG_ALL = -1;
    public static final int SAVE_FLAG_ALPHA = 2;
    public static final int SAVE_FLAG_CLIP = 1;
    public static final int SAVE_FLAG_MATRIX = 4;

    public abstract void clearBuffer();

    public abstract boolean clipRect(int i, int j, int k, int l);

    public abstract BasicTexture copyTexture(int i, int j, int k, int l);

    public abstract long currentAnimationTimeMillis();

    public abstract void deleteBuffer(int i);

    public abstract void deleteRecycledResources();

    public abstract void drawLine(float f, float f1, float f2, float f3, GLPaint glpaint);

    public abstract void drawMesh(BasicTexture basictexture, int i, int j, int k, int l, int i1, int j1);

    public abstract void drawMixed(BasicTexture basictexture, int i, float f, int j, int k, int l, int i1);

    public abstract void drawMixed(BasicTexture basictexture, BasicTexture basictexture1, float f, int i, int j, int k, int l);

    public abstract void drawRect(float f, float f1, float f2, float f3, GLPaint glpaint);

    public abstract void drawTexture(BasicTexture basictexture, int i, int j, int k, int l);

    public abstract void drawTexture(BasicTexture basictexture, int i, int j, int k, int l, float f);

    public abstract void drawTexture(BasicTexture basictexture, RectF rectf, RectF rectf1);

    public abstract void fillRect(float f, float f1, float f2, float f3, int i);

    public abstract float getAlpha();

    public abstract GL11 getGLInstance();

    public abstract void multiplyAlpha(float f);

    public abstract void multiplyMatrix(float af[], int i);

    public abstract void restore();

    public abstract void rotate(float f, float f1, float f2, float f3);

    public abstract int save();

    public abstract int save(int i);

    public abstract void scale(float f, float f1, float f2);

    public abstract void setAlpha(float f);

    public abstract void setBlendEnabled(boolean flag);

    public abstract void setCurrentAnimationTimeMillis(long l);

    public abstract void setSize(int i, int j);

    public abstract void translate(float f, float f1, float f2);

    public abstract boolean unloadTexture(BasicTexture basictexture);
}
