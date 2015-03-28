// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package powermobia.videoeditor.base;

import powermobia.ve.utils.MPoint;
import powermobia.ve.utils.MRect;

public class MBubbleTextSource
{

    private int backgroundColor;
    private boolean horizontalReversal;
    private MRect regionRatio;
    private float rotateAngle;
    private MPoint rotateCenter;
    private String svgFile;
    private String text;
    private int textColor;
    private int transparency;
    private boolean verticalReversal;

    public MBubbleTextSource()
    {
        svgFile = null;
        backgroundColor = 0;
        verticalReversal = false;
        horizontalReversal = false;
        rotateAngle = 0.0F;
        rotateCenter = null;
        regionRatio = null;
        transparency = 0;
        textColor = -1;
        text = null;
    }

    public MBubbleTextSource(String s, int i, boolean flag, boolean flag1, float f, MPoint mpoint, MRect mrect, 
            int j, int k, String s1)
    {
        svgFile = null;
        backgroundColor = 0;
        verticalReversal = false;
        horizontalReversal = false;
        rotateAngle = 0.0F;
        rotateCenter = null;
        regionRatio = null;
        transparency = 0;
        textColor = -1;
        text = null;
        svgFile = s;
        backgroundColor = i;
        verticalReversal = flag;
        horizontalReversal = flag1;
        rotateAngle = f;
        rotateCenter = mpoint;
        regionRatio = mrect;
        transparency = j;
        textColor = k;
        text = s1;
    }

    public MBubbleTextSource(MBubbleTextSource mbubbletextsource)
    {
        svgFile = null;
        backgroundColor = 0;
        verticalReversal = false;
        horizontalReversal = false;
        rotateAngle = 0.0F;
        rotateCenter = null;
        regionRatio = null;
        transparency = 0;
        textColor = -1;
        text = null;
        svgFile = mbubbletextsource.svgFile;
        backgroundColor = mbubbletextsource.backgroundColor;
        verticalReversal = mbubbletextsource.verticalReversal;
        horizontalReversal = mbubbletextsource.horizontalReversal;
        rotateAngle = mbubbletextsource.rotateAngle;
        rotateCenter = mbubbletextsource.rotateCenter;
        regionRatio = mbubbletextsource.regionRatio;
        transparency = mbubbletextsource.transparency;
        textColor = mbubbletextsource.textColor;
        text = mbubbletextsource.text;
    }

    public int getBackgroundColor()
    {
        return backgroundColor;
    }

    public boolean getHorizontalReversal()
    {
        return horizontalReversal;
    }

    public MRect getRegionRatio()
    {
        return regionRatio;
    }

    public float getRotateAngle()
    {
        return rotateAngle;
    }

    public MPoint getRotateCenter()
    {
        return rotateCenter;
    }

    public String getSVGFile()
    {
        return svgFile;
    }

    public String getText()
    {
        return text;
    }

    public int getTextColor()
    {
        return textColor;
    }

    public int getTransparency()
    {
        return transparency;
    }

    public boolean getVerticalReversal()
    {
        return verticalReversal;
    }
}
