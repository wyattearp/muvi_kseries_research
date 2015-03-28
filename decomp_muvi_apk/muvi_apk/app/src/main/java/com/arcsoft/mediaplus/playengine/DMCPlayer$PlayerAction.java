// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.arcsoft.mediaplus.playengine;

import com.arcsoft.util.debug.Log;
import java.util.ArrayList;
import java.util.ListIterator;

// Referenced classes of package com.arcsoft.mediaplus.playengine:
//            DMCPlayer

public abstract class mActionDid
{

    private final ArrayList mActionConditions = new ArrayList();
    private boolean mActionDid;
    private boolean mFinish;
    private on mFinishConditions;
    private final ArrayList mPreActions = new ArrayList();
    final DMCPlayer this$0;

    public final void buildAction()
    {
        mActionDid amactiondid[] = getPreActions();
        if (amactiondid != null)
        {
            int k = amactiondid.length;
            for (int l = 0; l < k; l++)
            {
                mActionDid mactiondid = amactiondid[l];
                mactiondid.buildAction();
                mPreActions.add(mactiondid);
            }

        }
        on aon[] = getActionConditions();
        if (aon != null)
        {
            int i = aon.length;
            for (int j = 0; j < i; j++)
            {
                on on = aon[j];
                mActionConditions.add(on);
            }

        }
        mFinishConditions = getFinishConditions();
    }

    protected abstract void doAction();

    protected boolean finishAfterDoAction()
    {
        return true;
    }

    protected abstract on[] getActionConditions();

    protected abstract on getFinishConditions();

    protected abstract on[] getPreActions();

    public boolean isFinish()
    {
        return mFinish;
    }

    protected boolean onConditionArrival(on on, Object obj)
        throws on
    {
        return true;
    }

    public void setConditionOK(on on, Object obj)
        throws on
    {
        boolean flag = true;
        this;
        JVM INSTR monitorenter ;
        boolean flag1;
        Log.d(DMCPlayer.TAG, (new StringBuilder()).append(getClass().getSimpleName()).append(": ").append("setConditionOK (").append(on).append(")").toString());
        flag1 = isFinish();
        if (!flag1) goto _L2; else goto _L1
_L1:
        this;
        JVM INSTR monitorexit ;
        return;
_L2:
        ListIterator listiterator = mPreActions.listIterator();
        do
        {
            if (!listiterator.hasNext())
            {
                break;
            }
            on on1 = (mPreActions)listiterator.next();
            Log.d(DMCPlayer.TAG, (new StringBuilder()).append(getClass().getSimpleName()).append(": ").append("PreAction setConditionOK (").append(on).append(")").toString());
            on1.setConditionOK(on, obj);
            if (on1.isFinish())
            {
                listiterator.remove();
            }
        } while (true);
        break MISSING_BLOCK_LABEL_173;
        Exception exception;
        exception;
        throw exception;
        ListIterator listiterator1 = mActionConditions.listIterator();
        do
        {
            if (!listiterator1.hasNext())
            {
                break;
            }
            if ((on)listiterator1.next() == on && onConditionArrival(on, obj))
            {
                Log.d(DMCPlayer.TAG, (new StringBuilder()).append(getClass().getSimpleName()).append(": ").append("Pre-Condition OK (").append(on).append(")").toString());
                listiterator1.remove();
            }
        } while (true);
        if (mPreActions.size() <= 0 && mActionConditions.size() <= 0 && !mActionDid)
        {
            Log.d(DMCPlayer.TAG, (new StringBuilder()).append(getClass().getSimpleName()).append(": ").append("doAction").toString());
            mActionDid = true;
            doAction();
        }
        if (finishAfterDoAction() && !mActionDid)
        {
            flag = false;
        }
        if (mFinishConditions == null || !flag)
        {
            break MISSING_BLOCK_LABEL_437;
        }
        if (on == mFinishConditions && onConditionArrival(on, obj))
        {
            Log.d(DMCPlayer.TAG, (new StringBuilder()).append(getClass().getSimpleName()).append(": ").append("Finish-Condition OK (").append(on).append(")").toString());
            mFinishConditions = null;
        }
        if (mFinishConditions == null && mPreActions.size() <= 0 && mActionConditions.size() <= 0)
        {
            Log.d(DMCPlayer.TAG, (new StringBuilder()).append(getClass().getSimpleName()).append(": ").append("Finished !!!!!!!!!!").toString());
            mFinish = true;
        }
          goto _L1
    }

    public on()
    {
        this$0 = DMCPlayer.this;
        super();
        mFinishConditions = null;
        mFinish = false;
        mActionDid = false;
    }
}
