// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.arcsoft.mediaplus.playengine;

import com.arcsoft.util.debug.Log;
import java.util.ArrayList;

public class DMCPlayEngineStatusMachine
{
    private class CanceledStatus extends DoneStatusInfo
    {

        final DMCPlayEngineStatusMachine this$0;

        public Status getAfterDoActionStatus(TransformAction transformaction, boolean flag)
        {
            Status status = Status.FAULT;
            if (transformaction != TransformAction.OPEN) goto _L2; else goto _L1
_L1:
            if (!flag) goto _L4; else goto _L3
_L3:
            status = Status.OPENING;
_L6:
            return status;
_L4:
            return Status.FAULT;
_L2:
            if (transformaction == TransformAction.STOP)
            {
                if (flag)
                {
                    return Status.STOPPING;
                } else
                {
                    return Status.FAULT;
                }
            }
            if (true) goto _L6; else goto _L5
_L5:
        }

        public Status getStatus()
        {
            return Status.CANCELED;
        }

        public boolean isSupportedAction(TransformAction transformaction)
        {
            return transformaction == TransformAction.OPEN || transformaction == TransformAction.STOP;
        }

        private CanceledStatus()
        {
            this$0 = DMCPlayEngineStatusMachine.this;
            super();
        }

    }

    private class CancelingStatus extends DoingStatusInfo
    {

        final DMCPlayEngineStatusMachine this$0;

        public TransformAction getCauseAction()
        {
            return TransformAction.STOP;
        }

        public Status getDestinationStatus(TransformAction transformaction, boolean flag)
        {
            return Status.CANCELED;
        }

        public Status getStatus()
        {
            return Status.CANCELING;
        }

        private CancelingStatus()
        {
            this$0 = DMCPlayEngineStatusMachine.this;
            super();
        }

    }

    private abstract class DoingStatusInfo
        implements IStatusInfo
    {

        final DMCPlayEngineStatusMachine this$0;

        public Status getAfterDoActionStatus(TransformAction transformaction, boolean flag)
        {
            return Status.FAULT;
        }

        abstract TransformAction getCauseAction();

        abstract Status getDestinationStatus(TransformAction transformaction, boolean flag);

        public TransformAction getDoActionByRequestAction(TransformAction transformaction)
        {
            return transformaction;
        }

        public boolean isSupportedAction(TransformAction transformaction)
        {
            return false;
        }

        private DoingStatusInfo()
        {
            this$0 = DMCPlayEngineStatusMachine.this;
            super();
        }

    }

    private abstract class DoneStatusInfo
        implements IStatusInfo
    {

        final DMCPlayEngineStatusMachine this$0;

        public TransformAction getDoActionByRequestAction(TransformAction transformaction)
        {
            return transformaction;
        }

        private DoneStatusInfo()
        {
            this$0 = DMCPlayEngineStatusMachine.this;
            super();
        }

    }

    private class FaultStatus extends DoneStatusInfo
    {

        final DMCPlayEngineStatusMachine this$0;

        public Status getAfterDoActionStatus(TransformAction transformaction, boolean flag)
        {
            return Status.FAULT;
        }

        public Status getStatus()
        {
            return Status.FAULT;
        }

        public boolean isSupportedAction(TransformAction transformaction)
        {
            return false;
        }

        private FaultStatus()
        {
            this$0 = DMCPlayEngineStatusMachine.this;
            super();
        }

    }

    public static interface IOnStatusActionListener
    {

        public abstract boolean doAction(TransformAction transformaction, Object obj);

        public abstract void onDoActionError(TransformAction transformaction, Object obj);

        public abstract void onDoActionResultError(TransformAction transformaction, Object obj);
    }

    public static interface IOnStatusChangedListener
    {

        public abstract void onStatusChanged(Status status, Status status1, TransformAction transformaction);
    }

    private static interface IStatusInfo
    {

        public abstract Status getAfterDoActionStatus(TransformAction transformaction, boolean flag);

        public abstract TransformAction getDoActionByRequestAction(TransformAction transformaction);

        public abstract Status getStatus();

        public abstract boolean isSupportedAction(TransformAction transformaction);
    }

    private class OpendStatus extends DoneStatusInfo
    {

        final DMCPlayEngineStatusMachine this$0;

        public Status getAfterDoActionStatus(TransformAction transformaction, boolean flag)
        {
            Status status = Status.FAULT;
            if (transformaction != TransformAction.STOP) goto _L2; else goto _L1
_L1:
            if (!flag) goto _L4; else goto _L3
_L3:
            status = Status.STOPPING;
_L6:
            return status;
_L4:
            return Status.FAULT;
_L2:
            if (transformaction == TransformAction.PLAY)
            {
                if (flag)
                {
                    return Status.STARTINGPLAY;
                } else
                {
                    return Status.FAULT;
                }
            }
            if (transformaction == TransformAction.SEEK)
            {
                if (flag)
                {
                    return Status.SEEKING;
                } else
                {
                    return Status.OPENED;
                }
            }
            if (true) goto _L6; else goto _L5
_L5:
        }

        public Status getStatus()
        {
            return Status.OPENED;
        }

        public boolean isSupportedAction(TransformAction transformaction)
        {
            return transformaction == TransformAction.STOP || transformaction == TransformAction.PLAY || transformaction == TransformAction.SEEK;
        }

        private OpendStatus()
        {
            this$0 = DMCPlayEngineStatusMachine.this;
            super();
        }

    }

    private class OpeningStatus extends StopableDoingStatusInfo
    {

        final DMCPlayEngineStatusMachine this$0;

        public TransformAction getCauseAction()
        {
            return TransformAction.OPEN;
        }

        public Status getDestinationStatus(TransformAction transformaction, boolean flag)
        {
            if (flag)
            {
                return Status.OPENED;
            } else
            {
                return Status.FAULT;
            }
        }

        public Status getStatus()
        {
            return Status.OPENING;
        }

        private OpeningStatus()
        {
            this$0 = DMCPlayEngineStatusMachine.this;
            super();
        }

    }

    private class PausedStatus extends DoneStatusInfo
    {

        final DMCPlayEngineStatusMachine this$0;

        public Status getAfterDoActionStatus(TransformAction transformaction, boolean flag)
        {
            Status status = Status.FAULT;
            if (transformaction != TransformAction.STOP) goto _L2; else goto _L1
_L1:
            if (!flag) goto _L4; else goto _L3
_L3:
            status = Status.STOPPING;
_L6:
            return status;
_L4:
            return Status.FAULT;
_L2:
            if (transformaction == TransformAction.PLAY)
            {
                if (flag)
                {
                    return Status.STARTINGPLAY;
                } else
                {
                    return Status.PAUSED;
                }
            }
            if (transformaction == TransformAction.SEEK)
            {
                if (flag)
                {
                    return Status.SEEKING;
                } else
                {
                    return Status.PAUSED;
                }
            }
            if (true) goto _L6; else goto _L5
_L5:
        }

        public Status getStatus()
        {
            return Status.PAUSED;
        }

        public boolean isSupportedAction(TransformAction transformaction)
        {
            return transformaction == TransformAction.STOP || transformaction == TransformAction.PLAY || transformaction == TransformAction.SEEK;
        }

        private PausedStatus()
        {
            this$0 = DMCPlayEngineStatusMachine.this;
            super();
        }

    }

    private class PausingStatus extends StopableDoingStatusInfo
    {

        final DMCPlayEngineStatusMachine this$0;

        public TransformAction getCauseAction()
        {
            return TransformAction.PAUSE;
        }

        public Status getDestinationStatus(TransformAction transformaction, boolean flag)
        {
            if (flag)
            {
                return Status.PAUSED;
            } else
            {
                return Status.PLAYING;
            }
        }

        public Status getStatus()
        {
            return Status.PAUSING;
        }

        private PausingStatus()
        {
            this$0 = DMCPlayEngineStatusMachine.this;
            super();
        }

    }

    private class PlayingStatus extends DoneStatusInfo
    {

        final DMCPlayEngineStatusMachine this$0;

        public Status getAfterDoActionStatus(TransformAction transformaction, boolean flag)
        {
            Status status = Status.FAULT;
            if (transformaction == TransformAction.STOP)
            {
                if (flag)
                {
                    status = Status.STOPPING;
                } else
                {
                    status = Status.FAULT;
                }
            }
            if (transformaction != TransformAction.COMPLETE) goto _L2; else goto _L1
_L1:
            if (!flag) goto _L4; else goto _L3
_L3:
            status = Status.STOPED;
_L6:
            return status;
_L4:
            return Status.FAULT;
_L2:
            if (transformaction == TransformAction.PAUSE)
            {
                if (flag)
                {
                    return Status.PAUSING;
                } else
                {
                    return Status.PLAYING;
                }
            }
            if (transformaction == TransformAction.SEEK)
            {
                if (flag)
                {
                    return Status.SEEKING;
                } else
                {
                    return Status.PLAYING;
                }
            }
            if (true) goto _L6; else goto _L5
_L5:
        }

        public Status getStatus()
        {
            return Status.PLAYING;
        }

        public boolean isSupportedAction(TransformAction transformaction)
        {
            return transformaction == TransformAction.STOP || transformaction == TransformAction.PAUSE || transformaction == TransformAction.SEEK || transformaction == TransformAction.COMPLETE;
        }

        private PlayingStatus()
        {
            this$0 = DMCPlayEngineStatusMachine.this;
            super();
        }

    }

    private class SeekingStatus extends StopableDoingStatusInfo
    {

        final DMCPlayEngineStatusMachine this$0;

        public TransformAction getCauseAction()
        {
            return TransformAction.SEEK;
        }

        public Status getDestinationStatus(TransformAction transformaction, boolean flag)
        {
            if (mPreviousStatus == Status.PLAYING)
            {
                return Status.PLAYING;
            }
            if (mPreviousStatus == Status.PAUSED)
            {
                return Status.PAUSED;
            }
            if (mPreviousStatus == Status.OPENED)
            {
                return Status.OPENED;
            } else
            {
                throw new IllegalStateException((new StringBuilder()).append("Imposible Previous Status when seek end : ").append(mPreviousStatus).toString());
            }
        }

        public Status getStatus()
        {
            return Status.SEEKING;
        }

        private SeekingStatus()
        {
            this$0 = DMCPlayEngineStatusMachine.this;
            super();
        }

    }

    private class StartingPlayStatus extends StopableDoingStatusInfo
    {

        final DMCPlayEngineStatusMachine this$0;

        public TransformAction getCauseAction()
        {
            return TransformAction.PLAY;
        }

        public Status getDestinationStatus(TransformAction transformaction, boolean flag)
        {
            if (flag)
            {
                return Status.PLAYING;
            }
            if (mPreviousStatus == Status.OPENED)
            {
                return Status.FAULT;
            }
            if (mPreviousStatus == Status.PAUSED)
            {
                return Status.PAUSED;
            } else
            {
                throw new IllegalStateException((new StringBuilder()).append("Imposible Previous Status when startplay : ").append(mPreviousStatus).toString());
            }
        }

        public Status getStatus()
        {
            return Status.STARTINGPLAY;
        }

        private StartingPlayStatus()
        {
            this$0 = DMCPlayEngineStatusMachine.this;
            super();
        }

    }

    public static final class Status extends Enum
    {

        private static final Status $VALUES[];
        public static final Status CANCELED;
        public static final Status CANCELING;
        public static final Status FAULT;
        public static final Status OPENED;
        public static final Status OPENING;
        public static final Status PAUSED;
        public static final Status PAUSING;
        public static final Status PLAYING;
        public static final Status SEEKING;
        public static final Status STARTINGPLAY;
        public static final Status STOPED;
        public static final Status STOPPING;

        public static Status valueOf(String s)
        {
            return (Status)Enum.valueOf(com/arcsoft/mediaplus/playengine/DMCPlayEngineStatusMachine$Status, s);
        }

        public static Status[] values()
        {
            return (Status[])$VALUES.clone();
        }

        static 
        {
            FAULT = new Status("FAULT", 0);
            STOPPING = new Status("STOPPING", 1);
            STOPED = new Status("STOPED", 2);
            CANCELING = new Status("CANCELING", 3);
            CANCELED = new Status("CANCELED", 4);
            OPENING = new Status("OPENING", 5);
            OPENED = new Status("OPENED", 6);
            STARTINGPLAY = new Status("STARTINGPLAY", 7);
            PLAYING = new Status("PLAYING", 8);
            PAUSING = new Status("PAUSING", 9);
            PAUSED = new Status("PAUSED", 10);
            SEEKING = new Status("SEEKING", 11);
            Status astatus[] = new Status[12];
            astatus[0] = FAULT;
            astatus[1] = STOPPING;
            astatus[2] = STOPED;
            astatus[3] = CANCELING;
            astatus[4] = CANCELED;
            astatus[5] = OPENING;
            astatus[6] = OPENED;
            astatus[7] = STARTINGPLAY;
            astatus[8] = PLAYING;
            astatus[9] = PAUSING;
            astatus[10] = PAUSED;
            astatus[11] = SEEKING;
            $VALUES = astatus;
        }

        private Status(String s, int i)
        {
            super(s, i);
        }
    }

    private abstract class StopableDoingStatusInfo extends DoingStatusInfo
    {

        final DMCPlayEngineStatusMachine this$0;

        public Status getAfterDoActionStatus(TransformAction transformaction, boolean flag)
        {
label0:
            {
                Status status = Status.FAULT;
                if (transformaction == TransformAction.STOP)
                {
                    if (!flag)
                    {
                        break label0;
                    }
                    status = Status.STOPPING;
                }
                return status;
            }
            return Status.FAULT;
        }

        public TransformAction getDoActionByRequestAction(TransformAction transformaction)
        {
            return transformaction;
        }

        public boolean isSupportedAction(TransformAction transformaction)
        {
            return transformaction == TransformAction.STOP;
        }

        private StopableDoingStatusInfo()
        {
            this$0 = DMCPlayEngineStatusMachine.this;
            super();
        }

    }

    private class StopedStatus extends DoneStatusInfo
    {

        final DMCPlayEngineStatusMachine this$0;

        public Status getAfterDoActionStatus(TransformAction transformaction, boolean flag)
        {
label0:
            {
                Status status = Status.CANCELING;
                if (transformaction == TransformAction.STOP)
                {
                    if (!flag)
                    {
                        break label0;
                    }
                    status = Status.CANCELING;
                }
                return status;
            }
            return Status.CANCELED;
        }

        public TransformAction getDoActionByRequestAction(TransformAction transformaction)
        {
            if (transformaction == TransformAction.OPEN)
            {
                return TransformAction.STOP;
            } else
            {
                return null;
            }
        }

        public Status getStatus()
        {
            return Status.STOPED;
        }

        public boolean isSupportedAction(TransformAction transformaction)
        {
            return transformaction == TransformAction.OPEN;
        }

        private StopedStatus()
        {
            this$0 = DMCPlayEngineStatusMachine.this;
            super();
        }

    }

    private class StoppingStatus extends StopableDoingStatusInfo
    {

        final DMCPlayEngineStatusMachine this$0;

        public TransformAction getCauseAction()
        {
            return TransformAction.STOP;
        }

        public Status getDestinationStatus(TransformAction transformaction, boolean flag)
        {
            if (flag)
            {
                return Status.STOPED;
            } else
            {
                return Status.FAULT;
            }
        }

        public Status getStatus()
        {
            return Status.STOPPING;
        }

        private StoppingStatus()
        {
            this$0 = DMCPlayEngineStatusMachine.this;
            super();
        }

    }

    public static final class TransformAction extends Enum
    {

        private static final TransformAction $VALUES[];
        public static final TransformAction COMPLETE;
        public static final TransformAction OPEN;
        public static final TransformAction PAUSE;
        public static final TransformAction PLAY;
        public static final TransformAction SEEK;
        public static final TransformAction STOP;

        public static TransformAction valueOf(String s)
        {
            return (TransformAction)Enum.valueOf(com/arcsoft/mediaplus/playengine/DMCPlayEngineStatusMachine$TransformAction, s);
        }

        public static TransformAction[] values()
        {
            return (TransformAction[])$VALUES.clone();
        }

        static 
        {
            OPEN = new TransformAction("OPEN", 0);
            PLAY = new TransformAction("PLAY", 1);
            STOP = new TransformAction("STOP", 2);
            COMPLETE = new TransformAction("COMPLETE", 3);
            PAUSE = new TransformAction("PAUSE", 4);
            SEEK = new TransformAction("SEEK", 5);
            TransformAction atransformaction[] = new TransformAction[6];
            atransformaction[0] = OPEN;
            atransformaction[1] = PLAY;
            atransformaction[2] = STOP;
            atransformaction[3] = COMPLETE;
            atransformaction[4] = PAUSE;
            atransformaction[5] = SEEK;
            $VALUES = atransformaction;
        }

        private TransformAction(String s, int i)
        {
            super(s, i);
        }
    }


    public static final String TAG = "DMCPlay";
    private IOnStatusActionListener mActionListener;
    private IStatusInfo mCurStatusInfo;
    private ArrayList mForbidenAction;
    private Status mPreviousStatus;
    private IOnStatusChangedListener mStatusListener;

    public DMCPlayEngineStatusMachine()
    {
        mForbidenAction = new ArrayList();
        mCurStatusInfo = createStatusInfo(Status.STOPED);
        mPreviousStatus = null;
        mActionListener = null;
        mStatusListener = null;
    }

    private void changeStatus(Status status, TransformAction transformaction, boolean flag)
    {
        Log.v("DMCPlay", (new StringBuilder()).append("Change to Status : ").append(status).toString());
        break MISSING_BLOCK_LABEL_25;
        if ((mCurStatusInfo.getStatus() != Status.FAULT || flag) && status != mCurStatusInfo.getStatus())
        {
            mPreviousStatus = mCurStatusInfo.getStatus();
            mCurStatusInfo = createStatusInfo(status);
            if (mStatusListener != null)
            {
                mStatusListener.onStatusChanged(mPreviousStatus, status, transformaction);
                return;
            }
        }
        return;
    }

    private IStatusInfo createStatusInfo(Status status)
    {
        static class _cls1
        {

            static final int $SwitchMap$com$arcsoft$mediaplus$playengine$DMCPlayEngineStatusMachine$Status[];

            static 
            {
                $SwitchMap$com$arcsoft$mediaplus$playengine$DMCPlayEngineStatusMachine$Status = new int[Status.values().length];
                try
                {
                    $SwitchMap$com$arcsoft$mediaplus$playengine$DMCPlayEngineStatusMachine$Status[Status.FAULT.ordinal()] = 1;
                }
                catch (NoSuchFieldError nosuchfielderror) { }
                try
                {
                    $SwitchMap$com$arcsoft$mediaplus$playengine$DMCPlayEngineStatusMachine$Status[Status.STOPPING.ordinal()] = 2;
                }
                catch (NoSuchFieldError nosuchfielderror1) { }
                try
                {
                    $SwitchMap$com$arcsoft$mediaplus$playengine$DMCPlayEngineStatusMachine$Status[Status.STOPED.ordinal()] = 3;
                }
                catch (NoSuchFieldError nosuchfielderror2) { }
                try
                {
                    $SwitchMap$com$arcsoft$mediaplus$playengine$DMCPlayEngineStatusMachine$Status[Status.CANCELING.ordinal()] = 4;
                }
                catch (NoSuchFieldError nosuchfielderror3) { }
                try
                {
                    $SwitchMap$com$arcsoft$mediaplus$playengine$DMCPlayEngineStatusMachine$Status[Status.CANCELED.ordinal()] = 5;
                }
                catch (NoSuchFieldError nosuchfielderror4) { }
                try
                {
                    $SwitchMap$com$arcsoft$mediaplus$playengine$DMCPlayEngineStatusMachine$Status[Status.OPENING.ordinal()] = 6;
                }
                catch (NoSuchFieldError nosuchfielderror5) { }
                try
                {
                    $SwitchMap$com$arcsoft$mediaplus$playengine$DMCPlayEngineStatusMachine$Status[Status.OPENED.ordinal()] = 7;
                }
                catch (NoSuchFieldError nosuchfielderror6) { }
                try
                {
                    $SwitchMap$com$arcsoft$mediaplus$playengine$DMCPlayEngineStatusMachine$Status[Status.STARTINGPLAY.ordinal()] = 8;
                }
                catch (NoSuchFieldError nosuchfielderror7) { }
                try
                {
                    $SwitchMap$com$arcsoft$mediaplus$playengine$DMCPlayEngineStatusMachine$Status[Status.PLAYING.ordinal()] = 9;
                }
                catch (NoSuchFieldError nosuchfielderror8) { }
                try
                {
                    $SwitchMap$com$arcsoft$mediaplus$playengine$DMCPlayEngineStatusMachine$Status[Status.PAUSING.ordinal()] = 10;
                }
                catch (NoSuchFieldError nosuchfielderror9) { }
                try
                {
                    $SwitchMap$com$arcsoft$mediaplus$playengine$DMCPlayEngineStatusMachine$Status[Status.PAUSED.ordinal()] = 11;
                }
                catch (NoSuchFieldError nosuchfielderror10) { }
                try
                {
                    $SwitchMap$com$arcsoft$mediaplus$playengine$DMCPlayEngineStatusMachine$Status[Status.SEEKING.ordinal()] = 12;
                }
                catch (NoSuchFieldError nosuchfielderror11)
                {
                    return;
                }
            }
        }

        switch (_cls1..SwitchMap.com.arcsoft.mediaplus.playengine.DMCPlayEngineStatusMachine.Status[status.ordinal()])
        {
        default:
            throw new UnsupportedOperationException((new StringBuilder()).append("Can not craete StatusInfo : ").append(status).toString());

        case 1: // '\001'
            return new FaultStatus(null);

        case 2: // '\002'
            return new StoppingStatus(null);

        case 3: // '\003'
            return new StopedStatus(null);

        case 4: // '\004'
            return new CancelingStatus(null);

        case 5: // '\005'
            return new CanceledStatus(null);

        case 6: // '\006'
            return new OpeningStatus(null);

        case 7: // '\007'
            return new OpendStatus(null);

        case 8: // '\b'
            return new StartingPlayStatus(null);

        case 9: // '\t'
            return new PlayingStatus(null);

        case 10: // '\n'
            return new PausingStatus(null);

        case 11: // '\013'
            return new PausedStatus(null);

        case 12: // '\f'
            return new SeekingStatus(null);
        }
    }

    public boolean canDoAction(TransformAction transformaction)
    {
        boolean flag = false;
        this;
        JVM INSTR monitorenter ;
        if (!mForbidenAction.contains(transformaction)) goto _L2; else goto _L1
_L1:
        Log.e("DMCPlay", (new StringBuilder()).append("Forbiden Action : ").append(transformaction).toString());
_L4:
        this;
        JVM INSTR monitorexit ;
        return flag;
_L2:
        if (mCurStatusInfo != null)
        {
            break MISSING_BLOCK_LABEL_69;
        }
        Log.e("DMCPlay", "State invalid is null");
        flag = false;
        continue; /* Loop/switch isn't completed */
        Exception exception;
        exception;
        throw exception;
        if (mCurStatusInfo.getStatus() != Status.FAULT)
        {
            break MISSING_BLOCK_LABEL_97;
        }
        Log.e("DMCPlay", "doTransformAction failed : Fault status");
        flag = false;
        continue; /* Loop/switch isn't completed */
        boolean flag1 = mCurStatusInfo.isSupportedAction(transformaction);
        flag = false;
        if (flag1)
        {
            flag = true;
        }
        if (true) goto _L4; else goto _L3
_L3:
    }

    public boolean doTransformAction(TransformAction transformaction, Object obj)
    {
        this;
        JVM INSTR monitorenter ;
        boolean flag = canDoAction(transformaction);
        boolean flag1 = false;
        if (flag) goto _L2; else goto _L1
_L1:
        this;
        JVM INSTR monitorexit ;
        return flag1;
_L2:
        flag1 = true;
        TransformAction transformaction1;
        transformaction1 = mCurStatusInfo.getDoActionByRequestAction(transformaction);
        if (mActionListener != null)
        {
            flag1 = mActionListener.doAction(transformaction1, obj);
        }
        changeStatus(mCurStatusInfo.getAfterDoActionStatus(transformaction1, flag1), transformaction, false);
        if (flag1)
        {
            continue; /* Loop/switch isn't completed */
        }
        if (mActionListener != null)
        {
            mActionListener.onDoActionError(transformaction1, obj);
        }
        if (true) goto _L1; else goto _L3
_L3:
        Exception exception;
        exception;
        throw exception;
    }

    public Status getStatus()
    {
        this;
        JVM INSTR monitorenter ;
        Status status = mCurStatusInfo.getStatus();
        this;
        JVM INSTR monitorexit ;
        return status;
        Exception exception;
        exception;
        throw exception;
    }

    public void release()
    {
        this;
        JVM INSTR monitorenter ;
    }

    public boolean reset()
    {
        this;
        JVM INSTR monitorenter ;
        Log.w("DMCPlay", "Reset DMC Engine state machine");
        changeStatus(Status.STOPED, null, true);
        setAllActionAllowed();
        this;
        JVM INSTR monitorexit ;
        return true;
        Exception exception;
        exception;
        throw exception;
    }

    public void setAllActionAllowed()
    {
        this;
        JVM INSTR monitorenter ;
        setForbidenActions(null);
        this;
        JVM INSTR monitorexit ;
        return;
        Exception exception;
        exception;
        throw exception;
    }

    public void setAllActionForbiden()
    {
        this;
        JVM INSTR monitorenter ;
        TransformAction atransformaction[] = new TransformAction[6];
        atransformaction[0] = TransformAction.OPEN;
        atransformaction[1] = TransformAction.PLAY;
        atransformaction[2] = TransformAction.STOP;
        atransformaction[3] = TransformAction.COMPLETE;
        atransformaction[4] = TransformAction.PAUSE;
        atransformaction[5] = TransformAction.SEEK;
        setForbidenActions(atransformaction);
        this;
        JVM INSTR monitorexit ;
        return;
        Exception exception;
        exception;
        throw exception;
    }

    public void setForbidenActions(TransformAction atransformaction[])
    {
        this;
        JVM INSTR monitorenter ;
        mForbidenAction.clear();
        if (atransformaction == null) goto _L2; else goto _L1
_L1:
        int i = atransformaction.length;
        int j = 0;
_L3:
        if (j >= i)
        {
            break; /* Loop/switch isn't completed */
        }
        TransformAction transformaction = atransformaction[j];
        mForbidenAction.add(transformaction);
        j++;
        if (true) goto _L3; else goto _L2
_L2:
        this;
        JVM INSTR monitorexit ;
        return;
        Exception exception;
        exception;
        throw exception;
    }

    public void setOnStatusActionListener(IOnStatusActionListener ionstatusactionlistener)
    {
        this;
        JVM INSTR monitorenter ;
        mActionListener = ionstatusactionlistener;
        this;
        JVM INSTR monitorexit ;
        return;
        Exception exception;
        exception;
        throw exception;
    }

    public void setOnStatusChangedListener(IOnStatusChangedListener ionstatuschangedlistener)
    {
        this;
        JVM INSTR monitorenter ;
        mStatusListener = ionstatuschangedlistener;
        this;
        JVM INSTR monitorexit ;
        return;
        Exception exception;
        exception;
        throw exception;
    }

    public boolean setStatus(Status status)
    {
        this;
        JVM INSTR monitorenter ;
        Status status1 = mCurStatusInfo.getStatus();
        if (status1 != status) goto _L2; else goto _L1
_L1:
        this;
        JVM INSTR monitorexit ;
        return true;
_L2:
        Log.w("DMCPlay", (new StringBuilder()).append("SetStatus : ").append(status).append(" Current Status = ").append(mCurStatusInfo.getStatus()).toString());
        changeStatus(status, null, false);
        if (true) goto _L1; else goto _L3
_L3:
        Exception exception;
        exception;
        throw exception;
    }

    public boolean setTransformActionResult(TransformAction transformaction, boolean flag, Object obj)
    {
        boolean flag1 = false;
        this;
        JVM INSTR monitorenter ;
        if (mCurStatusInfo.getStatus() != Status.FAULT) goto _L2; else goto _L1
_L1:
        Log.e("DMCPlay", "setTransformActionResult failed : Fault status");
_L4:
        this;
        JVM INSTR monitorexit ;
        return flag1;
_L2:
        if (mCurStatusInfo != null && (mCurStatusInfo instanceof DoingStatusInfo))
        {
            break MISSING_BLOCK_LABEL_71;
        }
        Log.e("DMCPlay", "setTransformActionResult failed : current not doing-status");
        flag1 = false;
        continue; /* Loop/switch isn't completed */
        Exception exception;
        exception;
        throw exception;
        DoingStatusInfo doingstatusinfo;
        TransformAction transformaction1;
        doingstatusinfo = (DoingStatusInfo)mCurStatusInfo;
        transformaction1 = doingstatusinfo.getCauseAction();
        Log.v("DMCPlay", (new StringBuilder()).append("Current status cause action = ").append(transformaction1).toString());
        if (transformaction1 == transformaction)
        {
            break MISSING_BLOCK_LABEL_133;
        }
        Log.e("DMCPlay", "setTransformActionResult failed : not the same cause action");
        flag1 = false;
        continue; /* Loop/switch isn't completed */
        changeStatus(doingstatusinfo.getDestinationStatus(transformaction, flag), transformaction, false);
        if (flag)
        {
            break MISSING_BLOCK_LABEL_168;
        }
        if (mActionListener != null)
        {
            mActionListener.onDoActionResultError(transformaction, obj);
        }
        flag1 = true;
        if (true) goto _L4; else goto _L3
_L3:
    }

}
