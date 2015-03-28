// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.arcsoft.mediaplus.playengine;

import android.net.Uri;
import android.os.Handler;
import android.os.Message;
import com.arcsoft.adk.atv.DLNA;
import com.arcsoft.adk.atv.RenderManager;
import com.arcsoft.util.debug.Log;
import java.util.ArrayList;
import java.util.ListIterator;

// Referenced classes of package com.arcsoft.mediaplus.playengine:
//            IPlayer, DMCPlayEngine, AbsDMCPlayList

public class DMCPlayer
    implements IPlayer
{
    private static final class ActionCondition extends Enum
    {

        private static final ActionCondition $VALUES[];
        public static final ActionCondition ENGINESTABEL;
        public static final ActionCondition OPENED;
        public static final ActionCondition PAUSED;
        public static final ActionCondition SEEKED;
        public static final ActionCondition STARTEDPLAY;
        public static final ActionCondition STOPPED;
        public static final ActionCondition URLREADY;

        public static ActionCondition valueOf(String s)
        {
            return (ActionCondition)Enum.valueOf(com/arcsoft/mediaplus/playengine/DMCPlayer$ActionCondition, s);
        }

        public static ActionCondition[] values()
        {
            return (ActionCondition[])$VALUES.clone();
        }

        static 
        {
            URLREADY = new ActionCondition("URLREADY", 0);
            ENGINESTABEL = new ActionCondition("ENGINESTABEL", 1);
            STOPPED = new ActionCondition("STOPPED", 2);
            OPENED = new ActionCondition("OPENED", 3);
            STARTEDPLAY = new ActionCondition("STARTEDPLAY", 4);
            SEEKED = new ActionCondition("SEEKED", 5);
            PAUSED = new ActionCondition("PAUSED", 6);
            ActionCondition aactioncondition[] = new ActionCondition[7];
            aactioncondition[0] = URLREADY;
            aactioncondition[1] = ENGINESTABEL;
            aactioncondition[2] = STOPPED;
            aactioncondition[3] = OPENED;
            aactioncondition[4] = STARTEDPLAY;
            aactioncondition[5] = SEEKED;
            aactioncondition[6] = PAUSED;
            $VALUES = aactioncondition;
        }

        private ActionCondition(String s, int i)
        {
            super(s, i);
        }
    }

    private class OpenAction extends PlayerAction
    {

        StopAction mStopAction;
        private URLInfo mUrlInfo;
        final DMCPlayer this$0;

        protected void doAction()
        {
            mEngine.open(Uri.parse(mUrlInfo.resurl), mUrlInfo.resmeta);
        }

        protected ActionCondition[] getActionConditions()
        {
            ActionCondition aactioncondition[] = new ActionCondition[2];
            aactioncondition[0] = ActionCondition.STOPPED;
            aactioncondition[1] = ActionCondition.URLREADY;
            return aactioncondition;
        }

        protected ActionCondition getFinishConditions()
        {
            return ActionCondition.OPENED;
        }

        protected PlayerAction[] getPreActions()
        {
            PlayerAction aplayeraction[] = new PlayerAction[1];
            aplayeraction[0] = mStopAction;
            return aplayeraction;
        }

        protected boolean onConditionArrival(ActionCondition actioncondition, Object obj)
            throws PlayerException
        {
            if (actioncondition != ActionCondition.URLREADY)
            {
                return super.onConditionArrival(actioncondition, obj);
            }
            URLInfo urlinfo = (URLInfo)obj;
            if (urlinfo.itemid == mUrlInfo.itemid)
            {
                if (urlinfo.resurl != null)
                {
                    mUrlInfo = urlinfo;
                    return true;
                } else
                {
                    throw new PlayerException(IPlayer.PlayerError.ERROR_UNSUPPORT);
                }
            } else
            {
                return false;
            }
        }

        OpenAction(long l, IDMCPlayEffect.EFFECT effect)
        {
            this$0 = DMCPlayer.this;
            super();
            mUrlInfo = null;
            mStopAction = null;
            mUrlInfo = new URLInfo();
            mUrlInfo.itemid = l;
            mStopAction = new StopAction(effect);
        }
    }

    private class PlayAction extends PlayerAction
    {

        OpenAction mOpenAction;
        final DMCPlayer this$0;

        protected void doAction()
        {
            mEngine.play();
        }

        protected ActionCondition[] getActionConditions()
        {
            ActionCondition aactioncondition[] = new ActionCondition[1];
            aactioncondition[0] = ActionCondition.OPENED;
            return aactioncondition;
        }

        protected ActionCondition getFinishConditions()
        {
            return ActionCondition.STARTEDPLAY;
        }

        protected PlayerAction[] getPreActions()
        {
            PlayerAction aplayeraction[] = new PlayerAction[1];
            aplayeraction[0] = mOpenAction;
            return aplayeraction;
        }

        PlayAction(long l, IDMCPlayEffect.EFFECT effect)
        {
            this$0 = DMCPlayer.this;
            super();
            mOpenAction = null;
            mOpenAction = new OpenAction(l, effect);
        }
    }

    public abstract class PlayerAction
    {

        private final ArrayList mActionConditions = new ArrayList();
        private boolean mActionDid;
        private boolean mFinish;
        private ActionCondition mFinishConditions;
        private final ArrayList mPreActions = new ArrayList();
        final DMCPlayer this$0;

        public final void buildAction()
        {
            PlayerAction aplayeraction[] = getPreActions();
            if (aplayeraction != null)
            {
                int k = aplayeraction.length;
                for (int l = 0; l < k; l++)
                {
                    PlayerAction playeraction = aplayeraction[l];
                    playeraction.buildAction();
                    mPreActions.add(playeraction);
                }

            }
            ActionCondition aactioncondition[] = getActionConditions();
            if (aactioncondition != null)
            {
                int i = aactioncondition.length;
                for (int j = 0; j < i; j++)
                {
                    ActionCondition actioncondition = aactioncondition[j];
                    mActionConditions.add(actioncondition);
                }

            }
            mFinishConditions = getFinishConditions();
        }

        protected abstract void doAction();

        protected boolean finishAfterDoAction()
        {
            return true;
        }

        protected abstract ActionCondition[] getActionConditions();

        protected abstract ActionCondition getFinishConditions();

        protected abstract PlayerAction[] getPreActions();

        public boolean isFinish()
        {
            return mFinish;
        }

        protected boolean onConditionArrival(ActionCondition actioncondition, Object obj)
            throws PlayerException
        {
            return true;
        }

        public void setConditionOK(ActionCondition actioncondition, Object obj)
            throws PlayerException
        {
            boolean flag = true;
            this;
            JVM INSTR monitorenter ;
            boolean flag1;
            Log.d(DMCPlayer.TAG, (new StringBuilder()).append(getClass().getSimpleName()).append(": ").append("setConditionOK (").append(actioncondition).append(")").toString());
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
                PlayerAction playeraction = (PlayerAction)listiterator.next();
                Log.d(DMCPlayer.TAG, (new StringBuilder()).append(getClass().getSimpleName()).append(": ").append("PreAction setConditionOK (").append(actioncondition).append(")").toString());
                playeraction.setConditionOK(actioncondition, obj);
                if (playeraction.isFinish())
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
                if ((ActionCondition)listiterator1.next() == actioncondition && onConditionArrival(actioncondition, obj))
                {
                    Log.d(DMCPlayer.TAG, (new StringBuilder()).append(getClass().getSimpleName()).append(": ").append("Pre-Condition OK (").append(actioncondition).append(")").toString());
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
            if (actioncondition == mFinishConditions && onConditionArrival(actioncondition, obj))
            {
                Log.d(DMCPlayer.TAG, (new StringBuilder()).append(getClass().getSimpleName()).append(": ").append("Finish-Condition OK (").append(actioncondition).append(")").toString());
                mFinishConditions = null;
            }
            if (mFinishConditions == null && mPreActions.size() <= 0 && mActionConditions.size() <= 0)
            {
                Log.d(DMCPlayer.TAG, (new StringBuilder()).append(getClass().getSimpleName()).append(": ").append("Finished !!!!!!!!!!").toString());
                mFinish = true;
            }
              goto _L1
        }

        public PlayerAction()
        {
            this$0 = DMCPlayer.this;
            super();
            mFinishConditions = null;
            mFinish = false;
            mActionDid = false;
        }
    }

    private class PlayerException extends Exception
    {

        public IPlayer.PlayerError mError;
        final DMCPlayer this$0;

        public IPlayer.PlayerError getError()
        {
            return mError;
        }

        public PlayerException(IPlayer.PlayerError playererror)
        {
            this$0 = DMCPlayer.this;
            super();
            mError = IPlayer.PlayerError.ERROR_UNKNOWN;
            mError = playererror;
        }
    }

    public class SeekAction extends PlayerAction
    {

        private PlayAction mPlayAction;
        private long mSeekPos;
        final DMCPlayer this$0;

        protected void doAction()
        {
            if (mSeekPos > 0L)
            {
                mEngine.seekTo(mSeekPos);
            }
        }

        protected ActionCondition[] getActionConditions()
        {
            ActionCondition aactioncondition[] = new ActionCondition[1];
            aactioncondition[0] = ActionCondition.STARTEDPLAY;
            return aactioncondition;
        }

        protected ActionCondition getFinishConditions()
        {
            if (mSeekPos > 0L)
            {
                return ActionCondition.SEEKED;
            } else
            {
                return null;
            }
        }

        protected PlayerAction[] getPreActions()
        {
            PlayerAction aplayeraction[] = new PlayerAction[1];
            aplayeraction[0] = mPlayAction;
            return aplayeraction;
        }

        public SeekAction(long l, long l1, IDMCPlayEffect.EFFECT effect)
        {
            this$0 = DMCPlayer.this;
            super();
            mSeekPos = 0L;
            mPlayAction = null;
            mSeekPos = l1;
            mPlayAction = new PlayAction(l, effect);
        }
    }

    private class SeekSession
    {

        long pos;
        boolean seeking;
        final DMCPlayer this$0;

        private SeekSession()
        {
            this$0 = DMCPlayer.this;
            super();
            seeking = false;
            pos = 0L;
        }

    }

    public class SharpPlayAction extends PlayerAction
    {

        private OpenAction mOpenAction;
        private SharpSeekAction mSeekAction;
        final DMCPlayer this$0;

        protected void doAction()
        {
            mEngine.play();
        }

        protected ActionCondition[] getActionConditions()
        {
            if (mSeekAction != null)
            {
                ActionCondition aactioncondition1[] = new ActionCondition[1];
                aactioncondition1[0] = ActionCondition.SEEKED;
                return aactioncondition1;
            } else
            {
                ActionCondition aactioncondition[] = new ActionCondition[1];
                aactioncondition[0] = ActionCondition.OPENED;
                return aactioncondition;
            }
        }

        protected ActionCondition getFinishConditions()
        {
            return ActionCondition.STARTEDPLAY;
        }

        protected PlayerAction[] getPreActions()
        {
            if (mSeekAction != null)
            {
                PlayerAction aplayeraction1[] = new PlayerAction[1];
                aplayeraction1[0] = mSeekAction;
                return aplayeraction1;
            } else
            {
                PlayerAction aplayeraction[] = new PlayerAction[1];
                aplayeraction[0] = mOpenAction;
                return aplayeraction;
            }
        }

        public SharpPlayAction(long l, long l1, IDMCPlayEffect.EFFECT effect)
        {
            this$0 = DMCPlayer.this;
            super();
            mSeekAction = null;
            mOpenAction = null;
            if (l1 > 0L)
            {
                mSeekAction = new SharpSeekAction(l, l1, effect);
                return;
            } else
            {
                mOpenAction = new OpenAction(l, effect);
                return;
            }
        }
    }

    private class SharpSeekAction extends PlayerAction
    {

        private OpenAction mOpenAction;
        private long mSeekPos;
        final DMCPlayer this$0;

        protected void doAction()
        {
            Log.v(DMCPlayer.TAG, (new StringBuilder()).append("SharpSeekAction pos=").append(mSeekPos).toString());
            if (mSeekPos > 0L)
            {
                mEngine.seekTo(mSeekPos);
            }
        }

        protected ActionCondition[] getActionConditions()
        {
            ActionCondition aactioncondition[] = new ActionCondition[1];
            aactioncondition[0] = ActionCondition.OPENED;
            return aactioncondition;
        }

        protected ActionCondition getFinishConditions()
        {
            if (mSeekPos > 0L)
            {
                return ActionCondition.SEEKED;
            } else
            {
                return null;
            }
        }

        protected PlayerAction[] getPreActions()
        {
            PlayerAction aplayeraction[] = new PlayerAction[1];
            aplayeraction[0] = mOpenAction;
            return aplayeraction;
        }

        SharpSeekAction(long l, long l1, IDMCPlayEffect.EFFECT effect)
        {
            this$0 = DMCPlayer.this;
            super();
            mSeekPos = 0L;
            mOpenAction = null;
            mSeekPos = l1;
            mOpenAction = new OpenAction(l, effect);
        }
    }

    private class StopAction extends PlayerAction
    {

        IDMCPlayEffect.EFFECT mPlayEffect;
        final DMCPlayer this$0;

        protected void doAction()
        {
            mEngine.stop(mPlayEffect);
        }

        protected boolean finishAfterDoAction()
        {
            return false;
        }

        protected ActionCondition[] getActionConditions()
        {
            ActionCondition aactioncondition[] = new ActionCondition[1];
            aactioncondition[0] = ActionCondition.ENGINESTABEL;
            return aactioncondition;
        }

        protected ActionCondition getFinishConditions()
        {
            return ActionCondition.STOPPED;
        }

        protected PlayerAction[] getPreActions()
        {
            return null;
        }

        StopAction(IDMCPlayEffect.EFFECT effect)
        {
            this$0 = DMCPlayer.this;
            super();
            mPlayEffect = IDMCPlayEffect.EFFECT.EFFECT_NONE;
            mPlayEffect = effect;
        }
    }

    private class URLInfo
    {

        long itemid;
        String resmeta;
        String resurl;
        final DMCPlayer this$0;

        private URLInfo()
        {
            this$0 = DMCPlayer.this;
            super();
        }

    }


    protected static final int MSG_RUNNABLE;
    protected static String TAG = "DMCPlay";
    protected PlayerAction mCurAction;
    protected Handler mDelayPlayHandler;
    protected DMCPlayEngine mEngine;
    protected DMCPlayEngine.IDMCPlayEngineListener mEngineListener;
    protected boolean mIsEngineLose;
    protected Handler mMsgHandler;
    protected com.arcsoft.mediaplus.datasource.DMCDataSource.IOnGetPlayURLListener mOnGetPlayURLListener;
    private final IDMCPlayEffect.EFFECT mPlayEffect;
    private AbsDMCPlayList mPlayList;
    protected IPlayer.IPlayerListener mPlayerListener;
    protected AbsPlayList.IOnPlaylistChangeListener mPlaylistChangeListener;
    private final SeekSession mSeekSession = new SeekSession();

    public DMCPlayer()
    {
        mEngineListener = new DMCPlayEngine.IDMCPlayEngineListener() {

            final DMCPlayer this$0;

            public void OnBuffering(int i)
            {
                if (mPlayerListener != null)
                {
                    mPlayerListener.onBuffering();
                }
            }

            public void OnComplete()
            {
                if (mPlayerListener != null)
                {
                    mPlayerListener.onCompleted();
                }
            }

            public void OnError(IPlayEngine.PlayEngineError playengineerror)
            {
                IPlayer.PlayerError playererror = tanslateEngineErrorToError(playengineerror);
                if (mPlayerListener != null)
                {
                    mPlayerListener.onError(playererror);
                }
            }

            public void OnMute(boolean flag)
            {
                if (mPlayerListener != null)
                {
                    mPlayerListener.onMute(flag);
                }
            }

            public void OnOpened()
            {
                onActionConditionArrival(ActionCondition.OPENED, null);
                onActionConditionArrival(ActionCondition.ENGINESTABEL, null);
                if (mPlayerListener != null)
                {
                    mPlayerListener.onBuffering();
                }
            }

            public void OnOpening()
            {
                if (mPlayerListener != null)
                {
                    mPlayerListener.onBuffering();
                }
            }

            public void OnPaused()
            {
                onActionConditionArrival(ActionCondition.PAUSED, null);
                onActionConditionArrival(ActionCondition.ENGINESTABEL, null);
                if (mPlayerListener != null)
                {
                    mPlayerListener.onPaused();
                }
            }

            public void OnPausing()
            {
            }

            public void OnProgressChanged(long l, long l1)
            {
                while (mSeekSession.seeking || mPlayerListener == null) 
                {
                    return;
                }
                mPlayerListener.onProgressChanged(l1, l);
            }

            public void OnSeeked(long l)
            {
                onActionConditionArrival(ActionCondition.SEEKED, null);
                if (mPlayerListener != null)
                {
                    mPlayerListener.onSeeked();
                }
            }

            public void OnSeeking()
            {
                if (mPlayerListener != null)
                {
                    mPlayerListener.onBuffering();
                }
            }

            public void OnStartedPlay()
            {
                onActionConditionArrival(ActionCondition.STARTEDPLAY, null);
                onActionConditionArrival(ActionCondition.ENGINESTABEL, null);
                if (mPlayerListener != null)
                {
                    mPlayerListener.onPlayStarted();
                }
                if (mSeekSession.seeking)
                {
                    Log.v(DMCPlayer.TAG, (new StringBuilder()).append("OnStartedPlay pos=").append(mSeekSession.pos).toString());
                    mEngine.seekTo(mSeekSession.pos);
                    mSeekSession.seeking = false;
                }
            }

            public void OnStartingPlay()
            {
                if (mPlayerListener != null)
                {
                    mPlayerListener.onBuffering();
                }
            }

            public void OnStopped()
            {
                onActionConditionArrival(ActionCondition.STOPPED, null);
                onActionConditionArrival(ActionCondition.ENGINESTABEL, null);
                if (mPlayerListener != null)
                {
                    mPlayerListener.onStopped();
                }
            }

            public void OnStopping()
            {
            }

            public void OnVolumeChanged(int i)
            {
                if (mPlayerListener != null)
                {
                    mPlayerListener.onVolumeChanged(i);
                }
            }

            public void onFault()
            {
                mCurAction = null;
            }

            public void onListenerLosed(DMCPlayEngine.IDMCPlayEngineListener idmcplayenginelistener)
            {
                if (idmcplayenginelistener != mEngineListener)
                {
                    mIsEngineLose = true;
                }
            }

            
            {
                this$0 = DMCPlayer.this;
                super();
            }
        };
        mPlaylistChangeListener = new AbsPlayList.IOnPlaylistChangeListener() {

            final DMCPlayer this$0;

            public void onPlayIndexChanged(int i)
            {
                if (mPlayerListener != null)
                {
                    mPlayerListener.onPlayIndexChanged(i);
                }
            }

            public void onPlayIndexReady(int i)
            {
                if (i >= 0)
                {
                    play(i, 0L, IDMCPlayEffect.EFFECT.EFFECT_START_PLAY_SLIDE);
                }
            }

            public void onPlaylistUpdated(int i, int j, boolean flag)
            {
            }

            
            {
                this$0 = DMCPlayer.this;
                super();
            }
        };
        mOnGetPlayURLListener = new com.arcsoft.mediaplus.datasource.DMCDataSource.IOnGetPlayURLListener() {

            final DMCPlayer this$0;

            public void onGetPlayURL(String s, String s1, Object obj)
            {
                URLInfo urlinfo = new URLInfo();
                urlinfo.itemid = ((Long)obj).longValue();
                urlinfo.resmeta = s1;
                urlinfo.resurl = s;
                postActionConditionMsg(ActionCondition.URLREADY, urlinfo);
            }

            public void onGetPlayURLError(Object obj)
            {
                URLInfo urlinfo = new URLInfo();
                urlinfo.itemid = ((Long)obj).longValue();
                urlinfo.resmeta = null;
                urlinfo.resurl = null;
                postActionConditionMsg(ActionCondition.URLREADY, urlinfo);
            }

            
            {
                this$0 = DMCPlayer.this;
                super();
            }
        };
        mPlayEffect = IDMCPlayEffect.EFFECT.EFFECT_START_PLAY_SLIDE;
        mIsEngineLose = true;
        mPlayerListener = null;
        mCurAction = null;
        mEngine = null;
        mPlayList = null;
        mMsgHandler = new Handler() {

            final DMCPlayer this$0;

            public void handleMessage(Message message)
            {
                if (message.what == 0)
                {
                    ((Runnable)message.obj).run();
                }
            }

            
            {
                this$0 = DMCPlayer.this;
                super();
            }
        };
        mDelayPlayHandler = new Handler() {

            final DMCPlayer this$0;

            public void handleMessage(Message message)
            {
                ((Runnable)message.obj).run();
            }

            
            {
                this$0 = DMCPlayer.this;
                super();
            }
        };
    }

    private IPlayer.Status convertEngineStatus(IPlayEngine.PLAYSTATUS playstatus)
    {
        IPlayer.Status status = IPlayer.Status.STATUS_IDLE;
        static class _cls8
        {

            static final int $SwitchMap$com$arcsoft$mediaplus$playengine$IPlayEngine$PLAYSTATUS[];
            static final int $SwitchMap$com$arcsoft$mediaplus$playengine$IPlayEngine$PlayEngineError[];

            static 
            {
                $SwitchMap$com$arcsoft$mediaplus$playengine$IPlayEngine$PlayEngineError = new int[IPlayEngine.PlayEngineError.values().length];
                try
                {
                    $SwitchMap$com$arcsoft$mediaplus$playengine$IPlayEngine$PlayEngineError[IPlayEngine.PlayEngineError.ERROR_RENDER_DISCONNECTED.ordinal()] = 1;
                }
                catch (NoSuchFieldError nosuchfielderror) { }
                try
                {
                    $SwitchMap$com$arcsoft$mediaplus$playengine$IPlayEngine$PlayEngineError[IPlayEngine.PlayEngineError.ERROR_SERVER_DISCONNECTED.ordinal()] = 2;
                }
                catch (NoSuchFieldError nosuchfielderror1) { }
                try
                {
                    $SwitchMap$com$arcsoft$mediaplus$playengine$IPlayEngine$PlayEngineError[IPlayEngine.PlayEngineError.ERROR_UNKNOWN.ordinal()] = 3;
                }
                catch (NoSuchFieldError nosuchfielderror2) { }
                try
                {
                    $SwitchMap$com$arcsoft$mediaplus$playengine$IPlayEngine$PlayEngineError[IPlayEngine.PlayEngineError.ERROR_OPENFILE.ordinal()] = 4;
                }
                catch (NoSuchFieldError nosuchfielderror3) { }
                try
                {
                    $SwitchMap$com$arcsoft$mediaplus$playengine$IPlayEngine$PlayEngineError[IPlayEngine.PlayEngineError.ERROR_PLAY.ordinal()] = 5;
                }
                catch (NoSuchFieldError nosuchfielderror4) { }
                try
                {
                    $SwitchMap$com$arcsoft$mediaplus$playengine$IPlayEngine$PlayEngineError[IPlayEngine.PlayEngineError.ERROR_PAUSE.ordinal()] = 6;
                }
                catch (NoSuchFieldError nosuchfielderror5) { }
                try
                {
                    $SwitchMap$com$arcsoft$mediaplus$playengine$IPlayEngine$PlayEngineError[IPlayEngine.PlayEngineError.ERROR_SEEK.ordinal()] = 7;
                }
                catch (NoSuchFieldError nosuchfielderror6) { }
                try
                {
                    $SwitchMap$com$arcsoft$mediaplus$playengine$IPlayEngine$PlayEngineError[IPlayEngine.PlayEngineError.ERROR_STOP.ordinal()] = 8;
                }
                catch (NoSuchFieldError nosuchfielderror7) { }
                try
                {
                    $SwitchMap$com$arcsoft$mediaplus$playengine$IPlayEngine$PlayEngineError[IPlayEngine.PlayEngineError.ERROR_GETVOLUME.ordinal()] = 9;
                }
                catch (NoSuchFieldError nosuchfielderror8) { }
                try
                {
                    $SwitchMap$com$arcsoft$mediaplus$playengine$IPlayEngine$PlayEngineError[IPlayEngine.PlayEngineError.ERROR_GETMUTE.ordinal()] = 10;
                }
                catch (NoSuchFieldError nosuchfielderror9) { }
                try
                {
                    $SwitchMap$com$arcsoft$mediaplus$playengine$IPlayEngine$PlayEngineError[IPlayEngine.PlayEngineError.ERROR_UNSUPPORT.ordinal()] = 11;
                }
                catch (NoSuchFieldError nosuchfielderror10) { }
                $SwitchMap$com$arcsoft$mediaplus$playengine$IPlayEngine$PLAYSTATUS = new int[IPlayEngine.PLAYSTATUS.values().length];
                try
                {
                    $SwitchMap$com$arcsoft$mediaplus$playengine$IPlayEngine$PLAYSTATUS[IPlayEngine.PLAYSTATUS.STATUS_IDLE.ordinal()] = 1;
                }
                catch (NoSuchFieldError nosuchfielderror11) { }
                try
                {
                    $SwitchMap$com$arcsoft$mediaplus$playengine$IPlayEngine$PLAYSTATUS[IPlayEngine.PLAYSTATUS.STATUS_OPENING.ordinal()] = 2;
                }
                catch (NoSuchFieldError nosuchfielderror12) { }
                try
                {
                    $SwitchMap$com$arcsoft$mediaplus$playengine$IPlayEngine$PLAYSTATUS[IPlayEngine.PLAYSTATUS.STATUS_OPENED.ordinal()] = 3;
                }
                catch (NoSuchFieldError nosuchfielderror13) { }
                try
                {
                    $SwitchMap$com$arcsoft$mediaplus$playengine$IPlayEngine$PLAYSTATUS[IPlayEngine.PLAYSTATUS.STATUS_STARTINGPLAY.ordinal()] = 4;
                }
                catch (NoSuchFieldError nosuchfielderror14) { }
                try
                {
                    $SwitchMap$com$arcsoft$mediaplus$playengine$IPlayEngine$PLAYSTATUS[IPlayEngine.PLAYSTATUS.STATUS_PLAYING.ordinal()] = 5;
                }
                catch (NoSuchFieldError nosuchfielderror15) { }
                try
                {
                    $SwitchMap$com$arcsoft$mediaplus$playengine$IPlayEngine$PLAYSTATUS[IPlayEngine.PLAYSTATUS.STATUS_BUFFERING.ordinal()] = 6;
                }
                catch (NoSuchFieldError nosuchfielderror16) { }
                try
                {
                    $SwitchMap$com$arcsoft$mediaplus$playengine$IPlayEngine$PLAYSTATUS[IPlayEngine.PLAYSTATUS.STATUS_PAUSING.ordinal()] = 7;
                }
                catch (NoSuchFieldError nosuchfielderror17) { }
                try
                {
                    $SwitchMap$com$arcsoft$mediaplus$playengine$IPlayEngine$PLAYSTATUS[IPlayEngine.PLAYSTATUS.STATUS_PAUSED.ordinal()] = 8;
                }
                catch (NoSuchFieldError nosuchfielderror18) { }
                try
                {
                    $SwitchMap$com$arcsoft$mediaplus$playengine$IPlayEngine$PLAYSTATUS[IPlayEngine.PLAYSTATUS.STATUS_STOPPING.ordinal()] = 9;
                }
                catch (NoSuchFieldError nosuchfielderror19) { }
                try
                {
                    $SwitchMap$com$arcsoft$mediaplus$playengine$IPlayEngine$PLAYSTATUS[IPlayEngine.PLAYSTATUS.STATUS_STOPPED.ordinal()] = 10;
                }
                catch (NoSuchFieldError nosuchfielderror20) { }
                try
                {
                    $SwitchMap$com$arcsoft$mediaplus$playengine$IPlayEngine$PLAYSTATUS[IPlayEngine.PLAYSTATUS.STATUS_SEEKING.ordinal()] = 11;
                }
                catch (NoSuchFieldError nosuchfielderror21)
                {
                    return;
                }
            }
        }

        switch (_cls8..SwitchMap.com.arcsoft.mediaplus.playengine.IPlayEngine.PLAYSTATUS[playstatus.ordinal()])
        {
        default:
            return status;

        case 1: // '\001'
            return IPlayer.Status.STATUS_IDLE;

        case 2: // '\002'
            return IPlayer.Status.STATUS_OPENING;

        case 3: // '\003'
            return IPlayer.Status.STATUS_OPENING;

        case 4: // '\004'
            return IPlayer.Status.STATUS_OPENING;

        case 5: // '\005'
            return IPlayer.Status.STATUS_PLAYING;

        case 6: // '\006'
            return IPlayer.Status.STATUS_BUFFERING;

        case 7: // '\007'
            return IPlayer.Status.STATUS_PAUSING;

        case 8: // '\b'
            return IPlayer.Status.STATUS_PAUSED;

        case 9: // '\t'
            return IPlayer.Status.STATUS_STOPPING;

        case 10: // '\n'
            return IPlayer.Status.STATUS_STOPPED;

        case 11: // '\013'
            return IPlayer.Status.STATUS_SEEKING;
        }
    }

    private void onActionConditionArrival(ActionCondition actioncondition, Object obj)
    {
        if (mCurAction != null) goto _L2; else goto _L1
_L1:
        return;
_L2:
        Log.e(TAG, (new StringBuilder()).append("Action Condition arrival : ").append(actioncondition).toString());
        mCurAction.setConditionOK(actioncondition, obj);
        if (mCurAction != null && mCurAction.isFinish())
        {
            mCurAction = null;
            return;
        }
        continue; /* Loop/switch isn't completed */
        PlayerException playerexception;
        playerexception;
        checkAndStop(IDMCPlayEffect.EFFECT.EFFECT_NONE);
        mCurAction = null;
        if (mPlayerListener != null)
        {
            mPlayerListener.onError(playerexception.getError());
            return;
        }
        if (true) goto _L1; else goto _L3
_L3:
    }

    private void postActionConditionMsg(final ActionCondition condition, final Object conditiondata)
    {
        Message message = Message.obtain();
        message.what = 0;
        message.obj = new Runnable() {

            final DMCPlayer this$0;
            final ActionCondition val$condition;
            final Object val$conditiondata;

            public void run()
            {
                onActionConditionArrival(condition, conditiondata);
            }

            
            {
                this$0 = DMCPlayer.this;
                condition = actioncondition;
                conditiondata = obj;
                super();
            }
        };
        mMsgHandler.sendMessage(message);
    }

    private IPlayer.PlayerError tanslateEngineErrorToError(IPlayEngine.PlayEngineError playengineerror)
    {
        switch (_cls8..SwitchMap.com.arcsoft.mediaplus.playengine.IPlayEngine.PlayEngineError[playengineerror.ordinal()])
        {
        default:
            return IPlayer.PlayerError.ERROR_UNKNOWN;

        case 1: // '\001'
            return IPlayer.PlayerError.ERROR_RENDER_DISCONNECTED;

        case 2: // '\002'
            return IPlayer.PlayerError.ERROR_SERVER_DISCONNECTED;

        case 3: // '\003'
            return IPlayer.PlayerError.ERROR_UNKNOWN;

        case 4: // '\004'
            return IPlayer.PlayerError.ERROR_OPENFILE;

        case 5: // '\005'
            return IPlayer.PlayerError.ERROR_PLAY;

        case 6: // '\006'
            return IPlayer.PlayerError.ERROR_UNKNOWN;

        case 7: // '\007'
            return IPlayer.PlayerError.ERROR_SEEK;

        case 8: // '\b'
            return IPlayer.PlayerError.ERROR_UNKNOWN;

        case 9: // '\t'
            return IPlayer.PlayerError.ERROR_UNKNOWN;

        case 10: // '\n'
            return IPlayer.PlayerError.ERROR_UNKNOWN;

        case 11: // '\013'
            return IPlayer.PlayerError.ERROR_UNSUPPORT;
        }
    }

    public boolean canPause()
    {
        if (mCurAction != null)
        {
            return false;
        } else
        {
            return mEngine.canPause();
        }
    }

    public boolean canResume()
    {
        if (mCurAction != null)
        {
            return false;
        } else
        {
            return mEngine.canPlay();
        }
    }

    public boolean canSeek()
    {
        while (mCurAction != null || !mEngine.canSeek()) 
        {
            return false;
        }
        return true;
    }

    public boolean canStop()
    {
        return true;
    }

    protected void checkAndStop(IDMCPlayEffect.EFFECT effect)
    {
        if (mEngine.getStatus() == IPlayEngine.PLAYSTATUS.STATUS_STOPPED)
        {
            onActionConditionArrival(ActionCondition.ENGINESTABEL, null);
            onActionConditionArrival(ActionCondition.STOPPED, null);
            return;
        }
        if (mEngine.getStatus() == IPlayEngine.PLAYSTATUS.STATUS_IDLE)
        {
            mEngine.reset();
            return;
        } else
        {
            mEngine.stop(effect);
            return;
        }
    }

    public void endSeek()
    {
        while (mCurAction != null || !mSeekSession.seeking || IPlayer.Status.STATUS_PLAYING != getStatus()) 
        {
            return;
        }
        mEngine.seekTo(mSeekSession.pos);
        mSeekSession.seeking = false;
    }

    public boolean fastBackward()
    {
        long l = 0L;
        if (!canSeek() || mCurAction != null)
        {
            return false;
        }
        long l1 = getPosition();
        if (mSeekSession.seeking)
        {
            l1 = mSeekSession.pos;
        }
        SeekSession seeksession = mSeekSession;
        if (l1 - 10000L >= l)
        {
            l = l1 - 10000L;
        }
        seeksession.pos = l;
        mSeekSession.seeking = true;
        mPlayerListener.onProgressChanged(getDuration(), mSeekSession.pos);
        if (mSeekSession.seeking && IPlayer.Status.STATUS_PLAYING == getStatus())
        {
            mEngine.seekTo(mSeekSession.pos);
            mSeekSession.seeking = false;
            return true;
        } else
        {
            return false;
        }
    }

    public boolean fastForward()
    {
        if (!canSeek() || mCurAction != null)
        {
            return false;
        }
        long l = getPosition();
        if (mSeekSession.seeking)
        {
            l = mSeekSession.pos;
        }
        SeekSession seeksession = mSeekSession;
        long l1;
        if (l + 30000L > getDuration())
        {
            l1 = getDuration();
        } else
        {
            l1 = l + 30000L;
        }
        seeksession.pos = l1;
        mSeekSession.seeking = true;
        mPlayerListener.onProgressChanged(getDuration(), mSeekSession.pos);
        if (mSeekSession.pos == getDuration())
        {
            mSeekSession.seeking = false;
            mPlayerListener.onCompleted();
            return false;
        }
        if (mSeekSession.seeking && IPlayer.Status.STATUS_PLAYING == getStatus())
        {
            mEngine.seekTo(mSeekSession.pos);
            mSeekSession.seeking = false;
            return true;
        } else
        {
            return false;
        }
    }

    public long getDuration()
    {
        return mEngine.getDuration();
    }

    public long getPosition()
    {
        return mEngine.getPosition();
    }

    public IPlayer.Status getStatus()
    {
        return convertEngineStatus(mEngine.getStatus());
    }

    public int getVolume()
    {
        return mEngine.getVolume();
    }

    public void init()
    {
        if (mEngine == null);
    }

    public boolean isMute()
    {
        return mEngine.isMute();
    }

    public boolean pause()
    {
        if (mCurAction != null)
        {
            return false;
        } else
        {
            return mEngine.pause();
        }
    }

    public boolean play(int i, long l)
    {
        return play(i, l, IDMCPlayEffect.EFFECT.EFFECT_NONE);
    }

    public boolean play(final int _index, final long _startpos, final IDMCPlayEffect.EFFECT _effect)
    {
        final com.arcsoft.adk.atv.UPnP.MediaRenderDesc renderdesc;
        if (mPlayList != null && _index >= 0)
        {
            if ((renderdesc = DLNA.instance().getRenderManager().getRenderDesc(mEngine.getRenderUDN())) != null)
            {
                mCurAction = null;
                mMsgHandler.removeMessages(0);
                mDelayPlayHandler.removeMessages(0);
                Message message = mDelayPlayHandler.obtainMessage();
                message.what = 0;
                message.obj = new Runnable() {

                    final DMCPlayer this$0;
                    final IDMCPlayEffect.EFFECT val$_effect;
                    final int val$_index;
                    final long val$_startpos;
                    final com.arcsoft.adk.atv.UPnP.MediaRenderDesc val$renderdesc;

                    public void run()
                    {
                        Log.d(DMCPlayer.TAG, (new StringBuilder()).append("Play index ").append(_index).toString());
                        long l = mPlayList.getId(_index);
                        if (mPlayList.getUri(_index) == null)
                        {
                            Log.e(DMCPlayer.TAG, "Uri is null!");
                            if (mPlayerListener != null)
                            {
                                mPlayerListener.onError(IPlayer.PlayerError.ERROR_PLAYLIST_URI_NULL);
                            }
                            return;
                        }
                        if (DLNA.instance().getRenderManager().isSharpDMR(mEngine.getRenderUDN()))
                        {
                            mCurAction = new SharpPlayAction(l, _startpos, _effect);
                        } else
                        {
                            mCurAction = new SeekAction(l, _startpos, _effect);
                        }
                        if (mPlayList != null)
                        {
                            mPlayList.setCurrentIndex(_index);
                        }
                        mCurAction.buildAction();
                        mPlayList.getPlayURLAsync(_index, renderdesc, new Long(l), mOnGetPlayURLListener);
                        checkAndStop(_effect);
                    }

            
            {
                this$0 = DMCPlayer.this;
                _index = i;
                _startpos = l;
                _effect = effect;
                renderdesc = mediarenderdesc;
                super();
            }
                };
                checkAndStop(_effect);
                mDelayPlayHandler.sendMessageDelayed(message, 500L);
                return false;
            }
        }
        return false;
    }

    public boolean playNext(boolean flag)
    {
        int i = mPlayList.next(flag);
        Log.d(TAG, (new StringBuilder()).append("play next index = ").append(i).toString());
        if (i == -1)
        {
            int j = mPlayList.getCount();
            boolean flag1 = false;
            if (j > 0)
            {
                AbsPlayList.RepeatMode repeatmode = mPlayList.getRepeatMode();
                AbsPlayList.RepeatMode repeatmode1 = AbsPlayList.RepeatMode.RepeatOne;
                flag1 = false;
                if (repeatmode == repeatmode1)
                {
                    flag1 = play(0, 0L, IDMCPlayEffect.EFFECT.EFFECT_TURNTO_PLAY);
                }
            }
            return flag1;
        } else
        {
            return play(i, 0L, IDMCPlayEffect.EFFECT.EFFECT_TURNTO_PLAY);
        }
    }

    public boolean playnext()
    {
        return playNext(true);
    }

    public boolean playprev()
    {
        int i = mPlayList.prev(true);
        if (i == -1)
        {
            if (mPlayList.getCount() > 0 && mPlayList.getRepeatMode() == AbsPlayList.RepeatMode.RepeatOne)
            {
                return play(-1 + mPlayList.getCount(), 0L, IDMCPlayEffect.EFFECT.EFFECT_TURNTO_PLAY);
            } else
            {
                return false;
            }
        } else
        {
            return play(i, 0L, IDMCPlayEffect.EFFECT.EFFECT_TURNTO_PLAY);
        }
    }

    protected void registPlaylistChangeListener()
    {
        if (mPlayList != null)
        {
            mPlayList.setOnPlaylistChangeListener(mPlaylistChangeListener);
        }
    }

    public boolean resume()
    {
        if (!canResume())
        {
            return false;
        } else
        {
            return mEngine.play();
        }
    }

    public void seekTo(long l)
    {
        while (!mSeekSession.seeking || mCurAction != null) 
        {
            return;
        }
        mSeekSession.pos = l;
    }

    public void setActivate(boolean flag)
    {
label0:
        {
            if (flag && mIsEngineLose)
            {
                mIsEngineLose = false;
                mEngine.gainListening(mEngineListener);
                if (DLNA.instance().getRenderManager().isRenderOnline(mEngine.getRenderUDN()))
                {
                    break label0;
                }
                mEngineListener.OnError(IPlayEngine.PlayEngineError.ERROR_RENDER_DISCONNECTED);
            }
            return;
        }
        play(mPlayList.getCurrentIndex(), 0L, IDMCPlayEffect.EFFECT.EFFECT_START_PLAY_FADE);
    }

    public boolean setMute(boolean flag)
    {
        return mEngine.setMute(flag);
    }

    public void setPlayerListener(IPlayer.IPlayerListener iplayerlistener)
    {
        mPlayerListener = iplayerlistener;
    }

    public void setPlaylist(AbsDMCPlayList absdmcplaylist)
    {
        if (mPlayList == absdmcplaylist)
        {
            return;
        }
        if (mPlayList != null)
        {
            mPlayList.setOnPlaylistChangeListener(null);
        }
        stop(IDMCPlayEffect.EFFECT.EFFECT_NONE);
        mPlayList = absdmcplaylist;
        registPlaylistChangeListener();
    }

    public void setRender(String s)
    {
        if (mEngine == null)
        {
            mEngine = DMCPlayEngine.getDMCPlayEngine(s, mEngineListener);
            mIsEngineLose = false;
            return;
        } else
        {
            mEngine.setRender(s);
            return;
        }
    }

    public boolean setVolume(int i)
    {
        return mEngine.setVolume(i);
    }

    public boolean startSeek()
    {
        if (!canSeek())
        {
            return false;
        } else
        {
            mSeekSession.seeking = true;
            mSeekSession.pos = getDuration();
            return true;
        }
    }

    public void stop()
    {
        throw new UnsupportedOperationException("Use stop(boolean bSwitchOrExit) instead");
    }

    public void stop(IDMCPlayEffect.EFFECT effect)
    {
        if (!canStop())
        {
            return;
        } else
        {
            mDelayPlayHandler.removeMessages(0);
            mCurAction = new StopAction(effect);
            mCurAction.buildAction();
            checkAndStop(effect);
            return;
        }
    }

    protected void unRegistPlaylistChangeListener()
    {
        if (mPlayList != null)
        {
            mPlayList.setOnPlaylistChangeListener(null);
        }
    }

    public void uninit()
    {
        mMsgHandler.removeMessages(0);
        mDelayPlayHandler.removeMessages(0);
        unRegistPlaylistChangeListener();
        if (mEngine != null)
        {
            mEngine.stop();
            DMCPlayEngine.releaseDMCPlayEngine(mEngine, mEngineListener);
            mEngine = null;
        }
    }






}
