// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.arcsoft.adk.atv;

import android.media.MediaMetadataRetriever;
import com.arcsoft.util.debug.Log;

// Referenced classes of package com.arcsoft.adk.atv:
//            Recycleble, MetadataVideo

public static class mRetriever
    implements Recycleble
{

    private MediaMetadataRetriever mRetriever;

    public void Recycle()
    {
        if (mRetriever != null)
        {
            mRetriever.release();
        }
    }

    public long getBitRate()
    {
        long l;
        try
        {
            l = Long.parseLong(mRetriever.extractMetadata(16));
        }
        catch (NumberFormatException numberformatexception)
        {
            return 0L;
        }
        return l;
    }

    public int getDimensionHeight()
    {
        int i;
        try
        {
            i = Integer.parseInt(mRetriever.extractMetadata(19));
        }
        catch (NumberFormatException numberformatexception)
        {
            return 0;
        }
        return i;
    }

    public int getDimensionWidth()
    {
        int i;
        try
        {
            i = Integer.parseInt(mRetriever.extractMetadata(20));
            Log.e("LocalVideoInfoManager", (new StringBuilder()).append("get width:").append(i).toString());
        }
        catch (NumberFormatException numberformatexception)
        {
            Log.e("LocalVideoInfoManager", "get width exception");
            return 0;
        }
        return i;
    }

    public int getFrameRate()
    {
        int i;
        try
        {
            i = Integer.parseInt(mRetriever.extractMetadata(17));
        }
        catch (NumberFormatException numberformatexception)
        {
            return 0;
        }
        return i;
    }

    public String getVideoFormat()
    {
        String s;
        try
        {
            s = mRetriever.extractMetadata(18);
        }
        catch (Exception exception)
        {
            return null;
        }
        return s;
    }

    public (String s)
    {
        mRetriever = null;
        mRetriever = new MediaMetadataRetriever();
        mRetriever.setMode(1);
        try
        {
            mRetriever.setDataSource(s);
            return;
        }
        catch (IllegalArgumentException illegalargumentexception)
        {
            illegalargumentexception.printStackTrace();
            return;
        }
        catch (RuntimeException runtimeexception)
        {
            runtimeexception.printStackTrace();
        }
    }
}
