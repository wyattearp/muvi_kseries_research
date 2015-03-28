// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.arcsoft.util.tool;

import com.arcsoft.util.debug.DebugUtils;
import com.arcsoft.util.debug.Log;

public class DynamicDataStateMachine
{
    public static interface OnStateChangeActions
    {

        public abstract void onDisable();

        public abstract void onEnable();

        public abstract void onInit();

        public abstract void onPause();

        public abstract void onResume();

        public abstract void onUninit();
    }

    public static interface OnStateChangeFinishActions
    {

        public abstract void onAfterDisable();

        public abstract void onAfterEnable();

        public abstract void onAfterInit();

        public abstract void onAfterPause();

        public abstract void onAfterResume();

        public abstract void onAfterUninit();
    }


    private static final int FLAG_ENABLE = 2;
    private static final int FLAG_INIT = 1;
    private static final int FLAG_RESUME = 4;
    public static final int STATUS_ENABLE = 3;
    public static final int STATUS_INIT = 1;
    public static final int STATUS_RESUME = 7;
    public static final int STATUS_UNINIT;
    private OnStateChangeActions mActions;
    private OnStateChangeFinishActions mFinishActions;
    private final String mName;
    private int mStatus;

    public DynamicDataStateMachine(String s)
    {
        mStatus = 0;
        mName = s;
    }

    private static String statusToString(int i)
    {
        switch (i)
        {
        case 2: // '\002'
        case 4: // '\004'
        case 5: // '\005'
        case 6: // '\006'
        default:
            return null;

        case 0: // '\0'
            return "STATUS_UNINIT";

        case 1: // '\001'
            return "STATUS_INIT";

        case 3: // '\003'
            return "STATUS_ENABLE";

        case 7: // '\007'
            return "STATUS_RESUME";
        }
    }

    public final void disable()
    {
        if (mStatus == 1)
        {
            Log.w(mName, (new StringBuilder()).append(DebugUtils.currentTraceInfo()).append("Has already disabled").toString());
            return;
        }
        if (mStatus != 3)
        {
            String s = (new StringBuilder()).append("Bad status: ").append(statusToString(mStatus)).toString();
            Log.e(mName, (new StringBuilder()).append(DebugUtils.currentTraceInfo()).append(s).toString());
            Log.i(mName, (new StringBuilder()).append("Should in status: ").append(statusToString(3)).toString());
            return;
        } else
        {
            onDisable();
            mStatus = -3 & mStatus;
            onAfterDisable();
            return;
        }
    }

    public final void enable()
    {
        if (mStatus == 3)
        {
            Log.w(mName, (new StringBuilder()).append(DebugUtils.currentTraceInfo()).append("Has already enabled").toString());
            return;
        }
        if (mStatus != 1)
        {
            String s = (new StringBuilder()).append("Bad status: ").append(statusToString(mStatus)).toString();
            Log.e(mName, (new StringBuilder()).append(DebugUtils.currentTraceInfo()).append(s).toString());
            Log.i(mName, (new StringBuilder()).append("Should in status: ").append(statusToString(1)).toString());
            return;
        } else
        {
            onEnable();
            mStatus = 2 | mStatus;
            onAfterEnable();
            return;
        }
    }

    public final void init()
    {
        if (mStatus == 1)
        {
            Log.w(mName, (new StringBuilder()).append(DebugUtils.currentTraceInfo()).append("Has already inited").toString());
            return;
        }
        if (mStatus != 0)
        {
            String s = (new StringBuilder()).append("Bad status: ").append(statusToString(mStatus)).toString();
            Log.e(mName, (new StringBuilder()).append(DebugUtils.currentTraceInfo()).append(s).toString());
            Log.i(mName, (new StringBuilder()).append("Should in status: ").append(statusToString(0)).toString());
            throw new IllegalStateException(s);
        } else
        {
            onInit();
            mStatus = 1;
            onAfterInit();
            return;
        }
    }

    public final boolean isEnable()
    {
        return (2 & mStatus) > 0;
    }

    public final boolean isInit()
    {
        return (1 & mStatus) > 0;
    }

    public final boolean isResume()
    {
        return (4 & mStatus) > 0;
    }

    protected void onAfterDisable()
    {
        if (mFinishActions != null)
        {
            mFinishActions.onAfterDisable();
        }
    }

    protected void onAfterEnable()
    {
        if (mFinishActions != null)
        {
            mFinishActions.onAfterEnable();
        }
    }

    protected void onAfterInit()
    {
        if (mFinishActions != null)
        {
            mFinishActions.onAfterInit();
        }
    }

    protected void onAfterPause()
    {
        if (mFinishActions != null)
        {
            mFinishActions.onAfterPause();
        }
    }

    protected void onAfterResume()
    {
        if (mFinishActions != null)
        {
            mFinishActions.onAfterResume();
        }
    }

    protected void onAfterUninit()
    {
        if (mFinishActions != null)
        {
            mFinishActions.onAfterUninit();
        }
    }

    protected void onDisable()
    {
        if (mActions != null)
        {
            mActions.onDisable();
        }
    }

    protected void onEnable()
    {
        if (mActions != null)
        {
            mActions.onEnable();
        }
    }

    protected void onInit()
    {
        if (mActions != null)
        {
            mActions.onInit();
        }
    }

    protected void onPause()
    {
        if (mActions != null)
        {
            mActions.onPause();
        }
    }

    protected void onResume()
    {
        if (mActions != null)
        {
            mActions.onResume();
        }
    }

    protected void onUninit()
    {
        if (mActions != null)
        {
            mActions.onUninit();
        }
    }

    public final void pause()
    {
        if (mStatus == 3)
        {
            Log.w(mName, (new StringBuilder()).append(DebugUtils.currentTraceInfo()).append("Has already paused").toString());
            return;
        }
        if (mStatus != 7)
        {
            String s = (new StringBuilder()).append("Bad status: ").append(statusToString(mStatus)).toString();
            Log.e(mName, (new StringBuilder()).append(DebugUtils.currentTraceInfo()).append(s).toString());
            Log.i(mName, (new StringBuilder()).append("Should in status: ").append(statusToString(7)).toString());
            return;
        } else
        {
            onPause();
            mStatus = -5 & mStatus;
            onAfterPause();
            return;
        }
    }

    public final void resume()
    {
        if (mStatus == 7)
        {
            Log.w(mName, (new StringBuilder()).append(DebugUtils.currentTraceInfo()).append("Has already resumed").toString());
            return;
        }
        if (mStatus != 3)
        {
            String s = (new StringBuilder()).append("Bad status: ").append(statusToString(mStatus)).toString();
            Log.e(mName, (new StringBuilder()).append(DebugUtils.currentTraceInfo()).append(s).toString());
            Log.i(mName, (new StringBuilder()).append("Should in status: ").append(statusToString(3)).toString());
            return;
        } else
        {
            onResume();
            mStatus = 4 | mStatus;
            onAfterResume();
            return;
        }
    }

    public void setOnStateChangeActions(OnStateChangeActions onstatechangeactions)
    {
        mActions = onstatechangeactions;
    }

    public void setOnStateChangeFinishActions(OnStateChangeFinishActions onstatechangefinishactions)
    {
        mFinishActions = onstatechangefinishactions;
    }

    public final void unInit()
    {
        if ((4 & mStatus) != 0)
        {
            onPause();
            mStatus = -5 & mStatus;
            onAfterPause();
        }
        if ((2 & mStatus) != 0)
        {
            onDisable();
            mStatus = -3 & mStatus;
            onAfterDisable();
        }
        if ((1 & mStatus) != 0)
        {
            onUninit();
            mStatus = -2 & mStatus;
            onAfterUninit();
        }
    }
}
