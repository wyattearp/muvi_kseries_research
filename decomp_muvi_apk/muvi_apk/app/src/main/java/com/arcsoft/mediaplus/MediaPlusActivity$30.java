// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.arcsoft.mediaplus;

import com.arcsoft.util.debug.Log;

// Referenced classes of package com.arcsoft.mediaplus:
//            MediaPlusActivity

class this._cls0
    implements com.arcsoft.videostream.aee.uestRespondsListener
{

    final MediaPlusActivity this$0;

    public void onRequestRespondsFinished(int i, String s, int j, String s1, int k, String s2)
    {
        int l;
        l = 1;
        Log.e("MediaPlusActivity", (new StringBuilder()).append("testP onRequestRespondsFinished respond = ").append(s).append(" num = ").append(j).append(" param = ").append(s1).append(" curCmdType = ").append(i).append(" paramSize = ").append(k).toString());
        i;
        JVM INSTR lookupswitch 2: default 92
    //                   -1: 178
    //                   268435475: 133;
           goto _L1 _L2 _L3
_L1:
        if (j == 0) goto _L5; else goto _L4
_L4:
        Log.e("testP", (new StringBuilder()).append("sendCommandFailed curCmdType = ").append(i).toString());
        MediaPlusActivity.access$4500(MediaPlusActivity.this, j, i, s1);
_L7:
        return;
_L3:
        MediaPlusActivity.access$1102(MediaPlusActivity.this, false);
        MediaPlusActivity mediaplusactivity = MediaPlusActivity.this;
        if (!MediaPlusActivity.access$1100(MediaPlusActivity.this))
        {
            l = 0;
        }
        MediaPlusActivity.access$1200(mediaplusactivity, 551, l, -1, null, 0);
        return;
_L2:
        if (j != l || s1 == null) goto _L7; else goto _L6
_L6:
        MediaPlusActivity.access$4500(MediaPlusActivity.this, j, i, s1);
        return;
_L5:
        MediaPlusActivity.access$1102(MediaPlusActivity.this, false);
        MediaPlusActivity mediaplusactivity1 = MediaPlusActivity.this;
        if (!MediaPlusActivity.access$1100(MediaPlusActivity.this))
        {
            l = 0;
        }
        MediaPlusActivity.access$1200(mediaplusactivity1, 551, l, -1, null, 0);
        return;
    }

    .OnRequestRespondsListener()
    {
        this$0 = MediaPlusActivity.this;
        super();
    }
}
