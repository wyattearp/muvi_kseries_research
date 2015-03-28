// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package powermobia.videoeditor.storyboard;

import powermobia.videoeditor.clip.MMediaSource;

// Referenced classes of package powermobia.videoeditor.storyboard:
//            MThemeAddText

public class MThemeAddCover
{

    private boolean cover;
    private int coverHeight;
    private int coverWidth;
    private MMediaSource source[];
    private int sourceCount;
    private String templateFile;
    private MThemeAddText text;

    private MThemeAddCover()
    {
        cover = true;
        templateFile = null;
        coverWidth = 1920;
        coverHeight = 1080;
        sourceCount = 0;
        source = null;
        text = null;
    }

    public int getCoverHeight()
    {
        return coverHeight;
    }

    public int getCoverWidth()
    {
        return coverWidth;
    }

    public MMediaSource[] getSource()
    {
        return source;
    }

    public int getSourceCount()
    {
        return sourceCount;
    }

    public String getTemplateFile()
    {
        return templateFile;
    }

    public MThemeAddText getTextInfo()
    {
        return text;
    }

    public boolean isCover()
    {
        return cover;
    }

    public void setCoverHeight(int i)
    {
        coverHeight = i;
    }

    public void setCoverWidth(int i)
    {
        coverWidth = i;
    }

    public int setSource(MMediaSource ammediasource[])
    {
        if (ammediasource.length < sourceCount)
        {
            return 0x80003;
        } else
        {
            source = ammediasource;
            return 0;
        }
    }
}
