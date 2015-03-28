// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.arcsoft.mediaplus.updownload.easytransfer;

import com.arcsoft.util.debug.Log;

class EasyTransferStateMachine
{
    public static final class Action extends Enum
    {

        private static final Action $VALUES[];
        public static final Action BROWSE;
        public static final Action BUILD;
        public static final Action CANCEL;
        public static final Action COMPLETE;
        public static final Action DELETE;

        public static Action valueOf(String s)
        {
            return (Action)Enum.valueOf(com/arcsoft/mediaplus/updownload/easytransfer/EasyTransferStateMachine$Action, s);
        }

        public static Action[] values()
        {
            return (Action[])$VALUES.clone();
        }

        static 
        {
            BROWSE = new Action("BROWSE", 0);
            BUILD = new Action("BUILD", 1);
            COMPLETE = new Action("COMPLETE", 2);
            CANCEL = new Action("CANCEL", 3);
            DELETE = new Action("DELETE", 4);
            Action aaction[] = new Action[5];
            aaction[0] = BROWSE;
            aaction[1] = BUILD;
            aaction[2] = COMPLETE;
            aaction[3] = CANCEL;
            aaction[4] = DELETE;
            $VALUES = aaction;
        }

        private Action(String s, int i)
        {
            super(s, i);
        }
    }

    public static interface IOnActionListener
    {

        public abstract boolean onDoAction(Action action, Object obj);

        public abstract void onDoActionResult(Action action, State state, State state1, Object obj);

        public abstract void onFinishActionResult(Action action, State state, State state1, Object obj);
    }

    public static final class State extends Enum
    {

        private static final State $VALUES[];
        public static final State BROWSED;
        public static final State BROWSING;
        public static final State BUILDING;
        public static final State BUILT;
        public static final State CANCELLED;
        public static final State CANCELLING;
        public static final State COMPLETED;
        public static final State COMPLETING;
        public static final State DELETED;
        public static final State DELETING;
        public static final State FAULT;

        public static State valueOf(String s)
        {
            return (State)Enum.valueOf(com/arcsoft/mediaplus/updownload/easytransfer/EasyTransferStateMachine$State, s);
        }

        public static State[] values()
        {
            return (State[])$VALUES.clone();
        }

        static 
        {
            FAULT = new State("FAULT", 0);
            BROWSING = new State("BROWSING", 1);
            BROWSED = new State("BROWSED", 2);
            BUILDING = new State("BUILDING", 3);
            BUILT = new State("BUILT", 4);
            COMPLETING = new State("COMPLETING", 5);
            COMPLETED = new State("COMPLETED", 6);
            CANCELLED = new State("CANCELLED", 7);
            CANCELLING = new State("CANCELLING", 8);
            DELETED = new State("DELETED", 9);
            DELETING = new State("DELETING", 10);
            State astate[] = new State[11];
            astate[0] = FAULT;
            astate[1] = BROWSING;
            astate[2] = BROWSED;
            astate[3] = BUILDING;
            astate[4] = BUILT;
            astate[5] = COMPLETING;
            astate[6] = COMPLETED;
            astate[7] = CANCELLED;
            astate[8] = CANCELLING;
            astate[9] = DELETED;
            astate[10] = DELETING;
            $VALUES = astate;
        }

        private State(String s, int i)
        {
            super(s, i);
        }
    }


    private final String TAG = "EasyTransferStateMachine";
    private IOnActionListener mActionListener;
    private State mState;

    public EasyTransferStateMachine(IOnActionListener ionactionlistener)
    {
        mActionListener = null;
        mState = State.COMPLETED;
        Log.i("EasyTransferStateMachine", (new StringBuilder()).append("state =").append(mState).toString());
        mActionListener = ionactionlistener;
    }

    private boolean canDoAction(Action action)
    {
        Action aaction[] = supportDoAction(mState);
        if (aaction != null)
        {
            int i = 0;
            while (i < aaction.length) 
            {
                if (action == aaction[i])
                {
                    return true;
                }
                i++;
            }
        }
        return false;
    }

    private boolean changeState(Action action, State state, State state1)
    {
        Log.i("EasyTransferStateMachine", (new StringBuilder()).append("change to state =").append(state1).toString());
        if (state1 == state)
        {
            Log.w("EasyTransferStateMachine", (new StringBuilder()).append("changeState() same state =").append(state).toString());
        }
        mState = state1;
        return true;
    }

    private State getStateAfterDoAction(Action action, boolean flag)
    {
        State state;
        state = State.FAULT;
        if (!flag)
        {
            return state;
        }
        static class _cls1
        {

            static final int $SwitchMap$com$arcsoft$mediaplus$updownload$easytransfer$EasyTransferStateMachine$Action[];
            static final int $SwitchMap$com$arcsoft$mediaplus$updownload$easytransfer$EasyTransferStateMachine$State[];

            static 
            {
                $SwitchMap$com$arcsoft$mediaplus$updownload$easytransfer$EasyTransferStateMachine$Action = new int[Action.values().length];
                try
                {
                    $SwitchMap$com$arcsoft$mediaplus$updownload$easytransfer$EasyTransferStateMachine$Action[Action.BROWSE.ordinal()] = 1;
                }
                catch (NoSuchFieldError nosuchfielderror) { }
                try
                {
                    $SwitchMap$com$arcsoft$mediaplus$updownload$easytransfer$EasyTransferStateMachine$Action[Action.BUILD.ordinal()] = 2;
                }
                catch (NoSuchFieldError nosuchfielderror1) { }
                try
                {
                    $SwitchMap$com$arcsoft$mediaplus$updownload$easytransfer$EasyTransferStateMachine$Action[Action.COMPLETE.ordinal()] = 3;
                }
                catch (NoSuchFieldError nosuchfielderror2) { }
                try
                {
                    $SwitchMap$com$arcsoft$mediaplus$updownload$easytransfer$EasyTransferStateMachine$Action[Action.CANCEL.ordinal()] = 4;
                }
                catch (NoSuchFieldError nosuchfielderror3) { }
                try
                {
                    $SwitchMap$com$arcsoft$mediaplus$updownload$easytransfer$EasyTransferStateMachine$Action[Action.DELETE.ordinal()] = 5;
                }
                catch (NoSuchFieldError nosuchfielderror4) { }
                $SwitchMap$com$arcsoft$mediaplus$updownload$easytransfer$EasyTransferStateMachine$State = new int[State.values().length];
                try
                {
                    $SwitchMap$com$arcsoft$mediaplus$updownload$easytransfer$EasyTransferStateMachine$State[State.BROWSING.ordinal()] = 1;
                }
                catch (NoSuchFieldError nosuchfielderror5) { }
                try
                {
                    $SwitchMap$com$arcsoft$mediaplus$updownload$easytransfer$EasyTransferStateMachine$State[State.BROWSED.ordinal()] = 2;
                }
                catch (NoSuchFieldError nosuchfielderror6) { }
                try
                {
                    $SwitchMap$com$arcsoft$mediaplus$updownload$easytransfer$EasyTransferStateMachine$State[State.BUILDING.ordinal()] = 3;
                }
                catch (NoSuchFieldError nosuchfielderror7) { }
                try
                {
                    $SwitchMap$com$arcsoft$mediaplus$updownload$easytransfer$EasyTransferStateMachine$State[State.BUILT.ordinal()] = 4;
                }
                catch (NoSuchFieldError nosuchfielderror8) { }
                try
                {
                    $SwitchMap$com$arcsoft$mediaplus$updownload$easytransfer$EasyTransferStateMachine$State[State.COMPLETING.ordinal()] = 5;
                }
                catch (NoSuchFieldError nosuchfielderror9) { }
                try
                {
                    $SwitchMap$com$arcsoft$mediaplus$updownload$easytransfer$EasyTransferStateMachine$State[State.COMPLETED.ordinal()] = 6;
                }
                catch (NoSuchFieldError nosuchfielderror10) { }
                try
                {
                    $SwitchMap$com$arcsoft$mediaplus$updownload$easytransfer$EasyTransferStateMachine$State[State.CANCELLED.ordinal()] = 7;
                }
                catch (NoSuchFieldError nosuchfielderror11) { }
                try
                {
                    $SwitchMap$com$arcsoft$mediaplus$updownload$easytransfer$EasyTransferStateMachine$State[State.DELETED.ordinal()] = 8;
                }
                catch (NoSuchFieldError nosuchfielderror12) { }
                try
                {
                    $SwitchMap$com$arcsoft$mediaplus$updownload$easytransfer$EasyTransferStateMachine$State[State.FAULT.ordinal()] = 9;
                }
                catch (NoSuchFieldError nosuchfielderror13)
                {
                    return;
                }
            }
        }

        _cls1..SwitchMap.com.arcsoft.mediaplus.updownload.easytransfer.EasyTransferStateMachine.Action[action.ordinal()];
        JVM INSTR tableswitch 1 5: default 52
    //                   1 54
    //                   2 61
    //                   3 68
    //                   4 75
    //                   5 82;
           goto _L1 _L2 _L3 _L4 _L5 _L6
_L1:
        return state;
_L2:
        state = State.BROWSING;
        continue; /* Loop/switch isn't completed */
_L3:
        state = State.BUILDING;
        continue; /* Loop/switch isn't completed */
_L4:
        state = State.COMPLETING;
        continue; /* Loop/switch isn't completed */
_L5:
        state = State.CANCELLING;
        continue; /* Loop/switch isn't completed */
_L6:
        state = State.DELETING;
        if (true) goto _L1; else goto _L7
_L7:
    }

    private State getStateAfterFinishAction(Action action, boolean flag)
    {
        State state;
        state = State.FAULT;
        if (!flag)
        {
            return state;
        }
        _cls1..SwitchMap.com.arcsoft.mediaplus.updownload.easytransfer.EasyTransferStateMachine.Action[action.ordinal()];
        JVM INSTR tableswitch 1 5: default 52
    //                   1 54
    //                   2 61
    //                   3 68
    //                   4 75
    //                   5 82;
           goto _L1 _L2 _L3 _L4 _L5 _L6
_L1:
        return state;
_L2:
        state = State.BROWSED;
        continue; /* Loop/switch isn't completed */
_L3:
        state = State.BUILT;
        continue; /* Loop/switch isn't completed */
_L4:
        state = State.COMPLETED;
        continue; /* Loop/switch isn't completed */
_L5:
        state = State.CANCELLED;
        continue; /* Loop/switch isn't completed */
_L6:
        state = State.DELETED;
        if (true) goto _L1; else goto _L7
_L7:
    }

    private Action[] supportDoAction(State state)
    {
        Action aaction[];
        switch (_cls1..SwitchMap.com.arcsoft.mediaplus.updownload.easytransfer.EasyTransferStateMachine.State[state.ordinal()])
        {
        default:
            return null;

        case 1: // '\001'
            Action aaction7[] = new Action[2];
            aaction7[0] = Action.CANCEL;
            aaction7[1] = Action.DELETE;
            return aaction7;

        case 2: // '\002'
            Action aaction6[] = new Action[3];
            aaction6[0] = Action.BUILD;
            aaction6[1] = Action.CANCEL;
            aaction6[2] = Action.DELETE;
            return aaction6;

        case 3: // '\003'
            Action aaction5[] = new Action[2];
            aaction5[0] = Action.CANCEL;
            aaction5[1] = Action.DELETE;
            return aaction5;

        case 4: // '\004'
            Action aaction4[] = new Action[3];
            aaction4[0] = Action.COMPLETE;
            aaction4[1] = Action.CANCEL;
            aaction4[2] = Action.DELETE;
            return aaction4;

        case 5: // '\005'
            Action aaction3[] = new Action[2];
            aaction3[0] = Action.CANCEL;
            aaction3[1] = Action.DELETE;
            return aaction3;

        case 6: // '\006'
            Action aaction2[] = new Action[3];
            aaction2[0] = Action.BROWSE;
            aaction2[1] = Action.CANCEL;
            aaction2[2] = Action.DELETE;
            return aaction2;

        case 7: // '\007'
        case 8: // '\b'
            Action aaction1[] = new Action[2];
            aaction1[0] = Action.BROWSE;
            aaction1[1] = Action.COMPLETE;
            return aaction1;

        case 9: // '\t'
            aaction = new Action[3];
            break;
        }
        aaction[0] = Action.BROWSE;
        aaction[1] = Action.CANCEL;
        aaction[2] = Action.DELETE;
        return aaction;
    }

    public void doAction(Action action, Object obj)
    {
        this;
        JVM INSTR monitorenter ;
        Log.i("EasyTransferStateMachine", (new StringBuilder()).append("doAction() action =").append(action).toString());
        if (canDoAction(action)) goto _L2; else goto _L1
_L1:
        Log.w("EasyTransferStateMachine", (new StringBuilder()).append("doAction [").append(action).append("] don't support. current state =").append(mState).toString());
_L4:
        this;
        JVM INSTR monitorexit ;
        return;
_L2:
        boolean flag = mActionListener.onDoAction(action, obj);
        State state = mState;
        State state1 = getStateAfterDoAction(action, flag);
        changeState(action, state, state1);
        mActionListener.onDoActionResult(action, state, state1, obj);
        if (true) goto _L4; else goto _L3
_L3:
        Exception exception;
        exception;
        throw exception;
    }

    public void finishAction(Action action, boolean flag, Object obj)
    {
        this;
        JVM INSTR monitorenter ;
        Log.i("EasyTransferStateMachine", (new StringBuilder()).append("finishAction() action =").append(action).append(", result =").append(flag).toString());
        State state = mState;
        State state1 = getStateAfterFinishAction(action, flag);
        changeState(action, state, state1);
        mActionListener.onFinishActionResult(action, state, state1, obj);
        this;
        JVM INSTR monitorexit ;
        return;
        Exception exception;
        exception;
        throw exception;
    }

    public State getState()
    {
        this;
        JVM INSTR monitorenter ;
        State state;
        Log.i("EasyTransferStateMachine", (new StringBuilder()).append("getState() =").append(mState).toString());
        state = mState;
        this;
        JVM INSTR monitorexit ;
        return state;
        Exception exception;
        exception;
        throw exception;
    }

    public void reset()
    {
        this;
        JVM INSTR monitorenter ;
        Log.i("EasyTransferStateMachine", "reset()");
        changeState(null, mState, State.COMPLETED);
        this;
        JVM INSTR monitorexit ;
        return;
        Exception exception;
        exception;
        throw exception;
    }
}
