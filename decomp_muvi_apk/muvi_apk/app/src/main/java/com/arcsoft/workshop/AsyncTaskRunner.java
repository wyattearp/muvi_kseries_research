// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.arcsoft.workshop;

import android.content.Context;
import android.os.Handler;
import android.os.Message;
import com.arcsoft.util.debug.Log;

// Referenced classes of package com.arcsoft.workshop:
//            WorkShop, EditorEngineWrapper

public class AsyncTaskRunner
{

    private static final int FIRST_LOOPTIME = 50;
    private static final int LOOPTIME = 10;
    private static final int MSGTYPELOOP = 1;
    private static final String TAG = "EditorTools";
    private final EditorEngineWrapper mEditorEngineWrapper;
    Handler mHandlerAysnTask;
    private boolean mHasAysnTaskToDo;
    private final WorkShop mWorkShop;

    public AsyncTaskRunner(Context context, EditorEngineWrapper editorenginewrapper)
    {
        mHasAysnTaskToDo = false;
        mHandlerAysnTask = new Handler(new android.os.Handler.Callback() {

            final AsyncTaskRunner this$0;

            public boolean handleMessage(Message message)
            {
                message.what;
                JVM INSTR tableswitch 1 1: default 24
            //                           1 26;
                   goto _L1 _L2
_L1:
                return false;
_L2:
                Log.v("EditorTools", "In mHandlerAysnTask");
                if (mWorkShop.isFinishing())
                {
                    Log.v("WorkShop", "dostep when isFinishing");
                    return false;
                }
                if (!mWorkShop.getSaveStart() && mWorkShop.isNeedDoAsyncTask())
                {
                    int i = doStepWrapper();
                    if (i == 0x80002)
                    {
                        Log.v("EditorTools", "In mHandlerAysnTask MERR_NO_MORE");
                    }
                    if (i == 0)
                    {
                        mHandlerAysnTask.sendEmptyMessageDelayed(1, 10L);
                    }
                    if (i != 0 && i != 0x80002)
                    {
                        mHandlerAysnTask.removeMessages(1);
                        return false;
                    }
                }
                if (true) goto _L1; else goto _L3
_L3:
            }

            
            {
                this$0 = AsyncTaskRunner.this;
                super();
            }
        });
        mWorkShop = (WorkShop)context;
        mEditorEngineWrapper = editorenginewrapper;
    }

    private int doStepWrapper()
    {
        long l = System.currentTimeMillis();
        int i;
        do
        {
            i = mEditorEngineWrapper.doStep();
            if (i != 0)
            {
                return i;
            }
        } while (System.currentTimeMillis() - l <= 25L);
        return i;
    }

    public void forceAsyncTask()
    {
        mHandlerAysnTask.sendEmptyMessageDelayed(1, 10L);
    }

    public void notifyAsyncTask()
    {
        mHandlerAysnTask.removeMessages(1);
        if (mWorkShop.isZoomPaning())
        {
            mHasAysnTaskToDo = true;
            return;
        } else
        {
            mHasAysnTaskToDo = false;
            mHandlerAysnTask.sendEmptyMessageDelayed(1, 50L);
            return;
        }
    }

    public void resumeAysnTask()
    {
        mHandlerAysnTask.removeMessages(1);
        if (mWorkShop.isZoomPaning())
        {
            mHasAysnTaskToDo = true;
            return;
        } else
        {
            mHandlerAysnTask.sendEmptyMessageDelayed(1, 50L);
            mHasAysnTaskToDo = false;
            return;
        }
    }

    public void startAysnTask()
    {
        if (mHasAysnTaskToDo)
        {
            mHandlerAysnTask.removeMessages(1);
            mHandlerAysnTask.sendEmptyMessageDelayed(1, 50L);
            mHasAysnTaskToDo = false;
        }
    }

    public void stopAysnTask()
    {
        mHasAysnTaskToDo = false;
        mHandlerAysnTask.removeMessages(1);
    }


}
