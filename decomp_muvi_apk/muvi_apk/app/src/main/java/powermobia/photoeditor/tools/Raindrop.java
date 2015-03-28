// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package powermobia.photoeditor.tools;


// Referenced classes of package powermobia.photoeditor.tools:
//            ToolBase

public class Raindrop extends ToolBase
{
    public static class RaindropInfo
    {

        public boolean bAdded;
        public int iAngle;
        public int iDensity;
        public int iScale;

        public RaindropInfo()
        {
        }
    }


    private static final int TOOLID_RAINDROP = 0x9084033e;

    public Raindrop()
    {
        mToolId = 0x9084033e;
    }

    private native int native_RD_GetRaindropInfo(int i, RaindropInfo raindropinfo);

    private native int native_RD_InitRaindrop(int i, int j, int k);

    private native int native_RD_SetAngle(int i, int j);

    private native int native_RD_SetPath(int i, String s);

    private native int native_RD_SetScale(int i, int j);

    public int getRaindropInfo(RaindropInfo raindropinfo)
    {
        return native_RD_GetRaindropInfo(getNativeEngine(), raindropinfo);
    }

    public int initRaindrop(int i, int j)
    {
        return native_RD_InitRaindrop(getNativeEngine(), i, j);
    }

    public int setAngle(int i)
    {
        return native_RD_SetAngle(getNativeEngine(), i);
    }

    public int setPath(String s)
    {
        return native_RD_SetPath(getNativeEngine(), s);
    }

    public int setScale(int i)
    {
        return native_RD_SetScale(getNativeEngine(), i);
    }
}
