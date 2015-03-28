// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.arcsoft.videostream;

import android.os.AsyncTask;
import com.arcsoft.util.debug.Log;
import com.arcsoft.videostream.aee.AEEVideoStreamActivity;

// Referenced classes of package com.arcsoft.videostream:
//            VideoView

public class this._cls0 extends AsyncTask
{

    final VideoView this$0;

    protected volatile Object doInBackground(Object aobj[])
    {
        return doInBackground((String[])aobj);
    }

    protected transient Void doInBackground(String as[])
    {
        int i = VideoView.access$1800(VideoView.this);
        Log.e("RecvMjpgTask", (new StringBuilder()).append("RecvMjpgTask  doInBackground() nRes: ").append(i).toString());
        if (i < 0)
        {
            VideoView.access$702(VideoView.this, false);
            ((AEEVideoStreamActivity)VideoView.access$600(VideoView.this)).switchTo(1, 14);
        }
        return null;
    }

    public ity()
    {
        this$0 = VideoView.this;
        super();
    }
}
