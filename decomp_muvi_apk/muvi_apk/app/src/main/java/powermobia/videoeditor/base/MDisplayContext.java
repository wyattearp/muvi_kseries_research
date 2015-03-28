// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package powermobia.videoeditor.base;

import powermobia.ve.utils.MRect;

public class MDisplayContext
{

    public static final int DISPLAY_ORIENTATION_LEFT_HANDED = 1;
    public static final int DISPLAY_ORIENTATION_NORMAL = 0;
    public static final int DISPLAY_ORIENTATION_REVERSAL = 3;
    public static final int DISPLAY_ORIENTATION_RIGHT_HANDED = 2;
    private static final int DISPLAY_ORIENTATION_TOTAL = 4;
    public static final int DISPLAY_ROTATION_180 = 180;
    public static final int DISPLAY_ROTATION_270 = 270;
    public static final int DISPLAY_ROTATION_90 = 90;
    public static final int DISPLAY_ROTATION_NONE = 0;
    public static final int RESAMPLE_MODE_FILL = 3;
    public static final int RESAMPLE_MODE_FITIN = 1;
    public static final int RESAMPLE_MODE_FITOUT = 2;
    public static final int RESAMPLE_MODE_UPSCALE_FITIN = 0x10001;
    public static final int RESAMPLE_MODE_UPSCALE_FITOUT = 0x10002;
    private int backgroundColor;
    private MRect clipRect;
    private int orientation;
    private int resampleMode;
    private int rotation;
    private MRect screenRect;

    public MDisplayContext()
    {
        screenRect = null;
        clipRect = null;
        backgroundColor = 0;
        rotation = 0;
        orientation = 0;
        resampleMode = 0x10001;
    }

    public MDisplayContext(MRect mrect, MRect mrect1, int i, int j, int k, int l)
    {
        screenRect = null;
        clipRect = null;
        backgroundColor = 0;
        rotation = 0;
        orientation = 0;
        resampleMode = 0x10001;
        screenRect = mrect;
        clipRect = mrect1;
        backgroundColor = i;
        if (k < 0 || k >= 4)
        {
            orientation = 0;
        } else
        {
            orientation = k;
        }
        if (j != 0 && j != 90 && j != 180 && j != 270)
        {
            rotation = 0;
        } else
        {
            rotation = j;
        }
        if (l != 1 && l != 2 && l != 3 && l != 0x10001 && l != 0x10002)
        {
            resampleMode = 0x10001;
            return;
        } else
        {
            resampleMode = l;
            return;
        }
    }

    public MDisplayContext(MDisplayContext mdisplaycontext)
    {
        screenRect = null;
        clipRect = null;
        backgroundColor = 0;
        rotation = 0;
        orientation = 0;
        resampleMode = 0x10001;
        screenRect = mdisplaycontext.screenRect;
        clipRect = mdisplaycontext.clipRect;
        backgroundColor = mdisplaycontext.backgroundColor;
        orientation = mdisplaycontext.orientation;
        rotation = mdisplaycontext.rotation;
        resampleMode = mdisplaycontext.resampleMode;
    }

    public int getBackgroundColor()
    {
        return backgroundColor;
    }

    public MRect getClipRect()
    {
        return clipRect;
    }

    public int getOrientation()
    {
        return orientation;
    }

    public int getResampleMode()
    {
        return resampleMode;
    }

    public int getRotation()
    {
        return rotation;
    }

    public MRect getScreenRect()
    {
        return screenRect;
    }

    public void setBackgroundColor(int i)
    {
        backgroundColor = i;
    }

    public void setClipRect(MRect mrect)
    {
        if (mrect == null)
        {
            return;
        }
        if (clipRect == null)
        {
            clipRect = new MRect(mrect);
            return;
        } else
        {
            clipRect.set(mrect.left, mrect.top, mrect.right, mrect.bottom);
            return;
        }
    }

    public void setOrientation(int i)
    {
        if (i < 0 || i >= 4)
        {
            return;
        } else
        {
            orientation = i;
            return;
        }
    }

    public void setResampleMode(int i)
    {
        if (i != 1 && i != 2 && i != 3 && i != 0x10001 && i != 0x10002)
        {
            return;
        } else
        {
            resampleMode = i;
            return;
        }
    }

    public void setRotation(int i)
    {
        if (i != 0 && i != 90 && i != 180 && i != 270)
        {
            return;
        } else
        {
            rotation = i;
            return;
        }
    }

    public void setScreenRect(MRect mrect)
    {
        if (mrect == null)
        {
            return;
        }
        if (screenRect == null)
        {
            screenRect = new MRect(mrect);
            return;
        } else
        {
            screenRect.set(mrect.left, mrect.top, mrect.right, mrect.bottom);
            return;
        }
    }
}
