// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package powermobia.videoeditor.base;


public class MSurfaceLayerViewList
{

    private final int maxSurfaceLayerViewCount = 5;
    private Object surfaceLayerViewList[];

    public MSurfaceLayerViewList()
    {
        surfaceLayerViewList = null;
    }

    int GetMaxSurfaceLayerViewCount()
    {
        return 5;
    }

    Object[] GetSurfaceLayerView()
    {
        return surfaceLayerViewList;
    }

    public int SetSurfaceLayerView(Object aobj[])
    {
        if (aobj.length == 0 || aobj.length > 5)
        {
            return 0x80003;
        }
        surfaceLayerViewList = new Object[aobj.length];
        int i = 0;
        do
        {
            if (i >= aobj.length)
            {
                return 0;
            }
            surfaceLayerViewList[i] = aobj[i];
            i++;
        } while (true);
    }
}
