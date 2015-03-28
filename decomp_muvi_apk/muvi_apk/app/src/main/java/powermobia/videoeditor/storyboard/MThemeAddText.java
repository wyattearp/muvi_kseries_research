// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package powermobia.videoeditor.storyboard;

import powermobia.videoeditor.clip.MMediaSource;

public class MThemeAddText
{

    private String templateFile;
    private int textCount;
    private MMediaSource textSource[];

    private MThemeAddText()
    {
        templateFile = null;
        textCount = 0;
        textSource = null;
    }

    public String getTemplateFile()
    {
        return templateFile;
    }

    public int getTextCount()
    {
        return textCount;
    }

    public MMediaSource[] getTextSource()
    {
        return textSource;
    }

    public int setTextSource(MMediaSource ammediasource[])
    {
        if (ammediasource.length < textCount)
        {
            return 0x80003;
        } else
        {
            textSource = ammediasource;
            return 0;
        }
    }
}
