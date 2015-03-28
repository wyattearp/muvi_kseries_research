// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.arcsoft.MediaPlayer;

import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.util.Log;

// Referenced classes of package com.arcsoft.MediaPlayer:
//            ArcMediaPlayer

private class mArcMediaPlayer extends Handler
{

    private ArcMediaPlayer mArcMediaPlayer;
    final ArcMediaPlayer this$0;

    public void handleMessage(Message message)
    {
        if (ArcMediaPlayer.access$3(mArcMediaPlayer) != 0) goto _L2; else goto _L1
_L1:
        Log.w("ArcMediaPlayer", "ArcMediaPlayer went away with unhandled events");
_L4:
        return;
_L2:
        switch (message.what)
        {
        default:
            Log.e("ArcMediaPlayer", (new StringBuilder("Unknown message type ")).append(message.what).toString());
            return;

        case 1000: 
            continue; /* Loop/switch isn't completed */

        case 1: // '\001'
            if (ArcMediaPlayer.access$4(ArcMediaPlayer.this) != null)
            {
                ArcMediaPlayer.access$4(ArcMediaPlayer.this).onPrepared(mArcMediaPlayer);
                return;
            }
            break;

        case 2: // '\002'
            if (ArcMediaPlayer.access$5(ArcMediaPlayer.this) != null)
            {
                ArcMediaPlayer.access$5(ArcMediaPlayer.this).onCompletion(mArcMediaPlayer);
            }
            ArcMediaPlayer.access$6(ArcMediaPlayer.this, false);
            return;

        case 3: // '\003'
            if (ArcMediaPlayer.access$7(ArcMediaPlayer.this) != null)
            {
                ArcMediaPlayer.access$7(ArcMediaPlayer.this).onBufferingUpdate(mArcMediaPlayer, message.arg1);
                return;
            }
            break;

        case 4: // '\004'
            if (ArcMediaPlayer.access$8(ArcMediaPlayer.this) != null)
            {
                ArcMediaPlayer.access$8(ArcMediaPlayer.this).onSeekComplete(mArcMediaPlayer);
                return;
            }
            break;

        case 5: // '\005'
            if (ArcMediaPlayer.access$9(ArcMediaPlayer.this) != null)
            {
                ArcMediaPlayer.access$9(ArcMediaPlayer.this).onVideoSizeChanged(mArcMediaPlayer, message.arg1, message.arg2);
                return;
            }
            break;

        case 100: // 'd'
            Log.e("ArcMediaPlayer", (new StringBuilder("Error (")).append(message.arg1).append(",").append(message.arg2).append(")").toString());
            android.media.ntHandler nthandler = ArcMediaPlayer.access$10(ArcMediaPlayer.this);
            boolean flag = false;
            if (nthandler != null)
            {
                flag = ArcMediaPlayer.access$10(ArcMediaPlayer.this).onError(mArcMediaPlayer, message.arg1, message.arg2);
            }
            if (ArcMediaPlayer.access$5(ArcMediaPlayer.this) != null && !flag)
            {
                ArcMediaPlayer.access$5(ArcMediaPlayer.this).onCompletion(mArcMediaPlayer);
            }
            ArcMediaPlayer.access$6(ArcMediaPlayer.this, false);
            return;

        case 200: 
            Log.i("ArcMediaPlayer", (new StringBuilder("Info (")).append(message.arg1).append(",").append(message.arg2).append(")").toString());
            if (ArcMediaPlayer.access$11(ArcMediaPlayer.this) != null)
            {
                ArcMediaPlayer.access$11(ArcMediaPlayer.this).nInfo(mArcMediaPlayer, message.arg1, message.arg2);
                return;
            }
            break;

        case 0: // '\0'
            break;
        }
        continue; /* Loop/switch isn't completed */
        if (mOnMessageListener == null) goto _L4; else goto _L3
_L3:
        mOnMessageListener.onMessage(mArcMediaPlayer, message.arg1, message.arg2);
        return;
        if (true) goto _L4; else goto _L5
_L5:
    }

    public ener(ArcMediaPlayer arcmediaplayer1, Looper looper)
    {
        this$0 = ArcMediaPlayer.this;
        super(looper);
        mArcMediaPlayer = arcmediaplayer1;
    }
}
