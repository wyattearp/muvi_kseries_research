// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package powermobia.photoeditor;


public abstract class IPAdaptor
{

    private int parameter[];

    public IPAdaptor()
    {
        parameter = null;
    }

    public abstract int getBeginMethod();

    public abstract int getCalcMethod();

    public abstract int getCreateMethod();

    public abstract int getDestroyMethod();

    public abstract int getEndMethod();

    public abstract int getGetPropMethod();

    public abstract int getIsSupportedMethod();

    public abstract int getProcessMethod();

    public abstract int getSetPropMethod();

    public void setParameter(int ai[])
    {
        parameter = ai;
    }
}
