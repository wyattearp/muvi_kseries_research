// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package powermobia.videoeditor.clip;


public class MTransition
{

    private boolean animated;
    private boolean automatizm;
    private int duration;
    private String template;

    public MTransition()
    {
        template = null;
        duration = 0;
        animated = false;
        automatizm = false;
    }

    public MTransition(String s, int i, boolean flag)
    {
        template = null;
        duration = 0;
        animated = false;
        automatizm = false;
        template = s;
        duration = i;
        animated = flag;
        automatizm = false;
    }

    public MTransition(MTransition mtransition)
    {
        template = null;
        duration = 0;
        animated = false;
        automatizm = false;
        template = mtransition.template;
        duration = mtransition.duration;
        animated = mtransition.animated;
        automatizm = mtransition.automatizm;
    }

    public int getDuration()
    {
        return duration;
    }

    public String getTemplate()
    {
        return template;
    }

    public boolean isAnimated()
    {
        return animated;
    }

    public boolean isAutomatizm()
    {
        return automatizm;
    }

    public void setAnimated(boolean flag)
    {
        animated = flag;
    }

    public void setAutomatizm(boolean flag)
    {
        automatizm = flag;
    }

    public void setDuration(int i)
    {
        duration = i;
    }

    public void setTemplate(String s)
    {
        template = s;
    }
}
