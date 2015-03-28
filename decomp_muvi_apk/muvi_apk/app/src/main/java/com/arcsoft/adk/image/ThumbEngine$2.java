// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.arcsoft.adk.image;

import com.arcsoft.util.debug.Log;
import com.arcsoft.util.tool.DynamicDataStateMachine;

// Referenced classes of package com.arcsoft.adk.image:
//            ThumbEngine

class this._cls0
    implements com.arcsoft.util.tool.er.IOnCacheClearCacheListener
{

    final ThumbEngine this$0;

    public void onClearCacheFinished(String s)
    {
        if (!s.equals(ThumbEngine.access$700()))
        {
            return;
        }
        Log.w("ThumbEngine", "TE: onClearCacheFinished");
        ThumbEngine.access$1000(ThumbEngine.this, false);
        ThumbEngine.access$1100(ThumbEngine.this);
        if (ThumbEngine.access$1200(ThumbEngine.this) != null)
        {
            setOutputFormat(ThumbEngine.access$1200(ThumbEngine.this));
        }
        if (ThumbEngine.access$1300(ThumbEngine.this).isEnable())
        {
            prefetch(ThumbEngine.access$1400(ThumbEngine.this), ThumbEngine.access$1500(ThumbEngine.this));
            return;
        } else
        {
            ThumbEngine.access$1700(ThumbEngine.this, ThumbEngine.access$1600(ThumbEngine.this), false);
            return;
        }
    }

    public void onPrepareToClearCache(String s)
    {
        if (!s.equals(ThumbEngine.access$700()))
        {
            return;
        } else
        {
            Log.w("ThumbEngine", "TE: onPrepareToClearCache");
            ThumbEngine.access$800(ThumbEngine.this);
            ThumbEngine.access$900(ThumbEngine.this, false);
            return;
        }
    }

    teMachine()
    {
        this$0 = ThumbEngine.this;
        super();
    }
}
