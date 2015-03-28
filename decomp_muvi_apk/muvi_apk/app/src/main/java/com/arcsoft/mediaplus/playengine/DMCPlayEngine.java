// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.arcsoft.mediaplus.playengine;

import android.net.Uri;
import android.os.Handler;
import android.os.Message;
import com.arcsoft.adk.atv.DLNA;
import com.arcsoft.adk.atv.RenderManager;
import com.arcsoft.util.TimeUtils;
import com.arcsoft.util.debug.Log;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.ListIterator;
import java.util.Map;

// Referenced classes of package com.arcsoft.mediaplus.playengine:
//            IPlayEngine, DMCPlayEngineStatusMachine

public class DMCPlayEngine
    implements IPlayEngine
{
    static final class ASyncMsgType extends Enum
    {

        private static final ASyncMsgType $VALUES[];
        public static final ASyncMsgType MSG_GETACTION;
        public static final ASyncMsgType MSG_GETALLOWACTIONS;
        public static final ASyncMsgType MSG_GETMETADATA;
        public static final ASyncMsgType MSG_GETMUTE;
        public static final ASyncMsgType MSG_GETPOSITION;
        public static final ASyncMsgType MSG_GETVOLUME;
        public static final ASyncMsgType MSG_OPEN;
        public static final ASyncMsgType MSG_PAUSE;
        public static final ASyncMsgType MSG_PLAY;
        public static final ASyncMsgType MSG_SEEK;
        public static final ASyncMsgType MSG_STOP;

        public static ASyncMsgType valueOf(String s)
        {
            return (ASyncMsgType)Enum.valueOf(com/arcsoft/mediaplus/playengine/DMCPlayEngine$ASyncMsgType, s);
        }

        public static ASyncMsgType[] values()
        {
            return (ASyncMsgType[])$VALUES.clone();
        }

        static 
        {
            MSG_OPEN = new ASyncMsgType("MSG_OPEN", 0);
            MSG_PLAY = new ASyncMsgType("MSG_PLAY", 1);
            MSG_STOP = new ASyncMsgType("MSG_STOP", 2);
            MSG_PAUSE = new ASyncMsgType("MSG_PAUSE", 3);
            MSG_SEEK = new ASyncMsgType("MSG_SEEK", 4);
            MSG_GETVOLUME = new ASyncMsgType("MSG_GETVOLUME", 5);
            MSG_GETMUTE = new ASyncMsgType("MSG_GETMUTE", 6);
            MSG_GETACTION = new ASyncMsgType("MSG_GETACTION", 7);
            MSG_GETPOSITION = new ASyncMsgType("MSG_GETPOSITION", 8);
            MSG_GETMETADATA = new ASyncMsgType("MSG_GETMETADATA", 9);
            MSG_GETALLOWACTIONS = new ASyncMsgType("MSG_GETALLOWACTIONS", 10);
            ASyncMsgType aasyncmsgtype[] = new ASyncMsgType[11];
            aasyncmsgtype[0] = MSG_OPEN;
            aasyncmsgtype[1] = MSG_PLAY;
            aasyncmsgtype[2] = MSG_STOP;
            aasyncmsgtype[3] = MSG_PAUSE;
            aasyncmsgtype[4] = MSG_SEEK;
            aasyncmsgtype[5] = MSG_GETVOLUME;
            aasyncmsgtype[6] = MSG_GETMUTE;
            aasyncmsgtype[7] = MSG_GETACTION;
            aasyncmsgtype[8] = MSG_GETPOSITION;
            aasyncmsgtype[9] = MSG_GETMETADATA;
            aasyncmsgtype[10] = MSG_GETALLOWACTIONS;
            $VALUES = aasyncmsgtype;
        }

        private ASyncMsgType(String s, int i)
        {
            super(s, i);
        }
    }

    private class AsyncMsgInfo
    {

        long gettime;
        final DMCPlayEngine this$0;
        boolean valid;

        private AsyncMsgInfo()
        {
            this$0 = DMCPlayEngine.this;
            super();
        }

    }

    public static interface IDMCPlayEngineListener
        extends IPlayEngine.IPlayEngineListener
    {

        public abstract void onFault();

        public abstract void onListenerLosed(IDMCPlayEngineListener idmcplayenginelistener);
    }

    private class PlayEffect
    {

        IDMCPlayEffect.EFFECT effect;
        final DMCPlayEngine this$0;

        private PlayEffect()
        {
            this$0 = DMCPlayEngine.this;
            super();
            effect = IDMCPlayEffect.EFFECT.EFFECT_START_PLAY_SLIDE;
        }

    }

    private class PlayItemInfo
    {

        long duration;
        String metadata;
        long position;
        final DMCPlayEngine this$0;
        String url;

        private PlayItemInfo()
        {
            this$0 = DMCPlayEngine.this;
            super();
        }

    }


    private static final int INTERVAL_SEEKTIMEOUT = 3000;
    private static final int MSG_RENDER = -1;
    private static final int MSG_SEEKTIMEOUT = -8191;
    private static final int SYNC_ALL = 15;
    private static final int SYNC_ALLOWEDACTION = 16;
    private static final int SYNC_CURACTION = 1;
    private static final int SYNC_METADATA = 2;
    private static final int SYNC_POSITION = 4;
    private static final int SYNC_VOLUME = 8;
    public static final String TAG = "DMCPlay";
    private static ArrayList mDMCEnginePool = new ArrayList();
    private int mCurVolume;
    private IDMCPlayEngineListener mEngineListener;
    private Map mGetMsgValidMap;
    private boolean mMute;
    private Map mOpMsgWaitQueue;
    private PlayEffect mPlayEffect;
    private PlayItemInfo mPlayItemInfo;
    private int mRefCount;
    private com.arcsoft.adk.atv.RenderManager.IRenderPlayListener mRenderPlayListener;
    private com.arcsoft.adk.atv.RenderManager.IRenderStatusListener mRenderStatusListener;
    private String mRenderUDN;
    private DMCPlayEngineStatusMachine.IOnStatusActionListener mStatusActionListener;
    private DMCPlayEngineStatusMachine.IOnStatusChangedListener mStatusChangeListener;
    private DMCPlayEngineStatusMachine mStatusMachine;
    private Handler mSyncHandler;

    private DMCPlayEngine()
    {
        mRenderStatusListener = new com.arcsoft.adk.atv.RenderManager.IRenderStatusListener() {

            final DMCPlayEngine this$0;

            public void onGetProtocolInfo(String s, com.arcsoft.adk.atv.MRCPCallback.DataOnGetProtocolInfo dataongetprotocolinfo, int i)
            {
            }

            public void onRenderAdded(com.arcsoft.adk.atv.UPnP.MediaRenderDesc mediarenderdesc)
            {
                bridgeRenderMsg(mediarenderdesc. new Runnable() {

                    final _cls2 this$1;
                    final com.arcsoft.adk.atv.UPnP.MediaRenderDesc val$data;

                    public void run()
                    {
                        if (mRenderUDN != null && data.m_strUuid.equals(mRenderUDN))
                        {
                            reset();
                        }
                    }

            
            {
                this$1 = final__pcls2;
                data = com.arcsoft.adk.atv.UPnP.MediaRenderDesc.this;
                super();
            }
                });
            }

            public void onRenderInstalled(com.arcsoft.adk.atv.UPnP.MediaRenderDesc mediarenderdesc, boolean flag, boolean flag1, boolean flag2)
            {
            }

            public void onRenderRemoved(com.arcsoft.adk.atv.UPnP.MediaRenderDesc mediarenderdesc)
            {
                bridgeRenderMsg(mediarenderdesc. new Runnable() {

                    final _cls2 this$1;
                    final com.arcsoft.adk.atv.UPnP.MediaRenderDesc val$data;

                    public void run()
                    {
                        if (mRenderUDN != null && data.m_strUuid.equals(mRenderUDN))
                        {
                            reset();
                            mStatusMachine.setStatus(DMCPlayEngineStatusMachine.Status.FAULT);
                            if (mEngineListener != null)
                            {
                                mEngineListener.OnError(IPlayEngine.PlayEngineError.ERROR_RENDER_DISCONNECTED);
                            }
                        }
                    }

            
            {
                this$1 = final__pcls2;
                data = com.arcsoft.adk.atv.UPnP.MediaRenderDesc.this;
                super();
            }
                });
            }

            
            {
                this$0 = DMCPlayEngine.this;
                super();
            }
        };
        mRenderPlayListener = new com.arcsoft.adk.atv.RenderManager.IRenderPlayListener() {

            int mStopRetryCnt;
            final DMCPlayEngine this$0;

            private void onRenderNoMediaStatus(int i, com.arcsoft.adk.atv.MRCPCallback.DataOnGetTransportInfo dataongettransportinfo, String s)
            {
                mStatusMachine.doTransformAction(DMCPlayEngineStatusMachine.TransformAction.COMPLETE, null);
            }

            private void onRenderPausedStatus(int i, com.arcsoft.adk.atv.MRCPCallback.DataOnGetTransportInfo dataongettransportinfo, String s)
            {
                if (mStatusMachine.getStatus() != DMCPlayEngineStatusMachine.Status.SEEKING)
                {
                    mStatusMachine.setStatus(DMCPlayEngineStatusMachine.Status.PAUSED);
                }
            }

            private void onRenderPlayingStatus(int i, com.arcsoft.adk.atv.MRCPCallback.DataOnGetTransportInfo dataongettransportinfo, final String renderudn)
            {
                if (getStatus() == IPlayEngine.PLAYSTATUS.STATUS_STARTINGPLAY)
                {
                    bridgeRenderMsgDelay(i. new Runnable() {

                        final _cls3 this$1;
                        final int val$errorcode;
                        final String val$renderudn;

                        public void run()
                        {
                            if (!removeAsyncOpMsg(ASyncMsgType.MSG_PLAY, renderudn, null))
                            {
                                return;
                            }
                            Log.w("DMCPlay", (new StringBuilder()).append("onOpenMedia (get playing status) = ").append(errorcode).toString());
                            DMCPlayEngineStatusMachine dmcplayenginestatusmachine = mStatusMachine;
                            DMCPlayEngineStatusMachine.TransformAction transformaction = DMCPlayEngineStatusMachine.TransformAction.PLAY;
                            boolean flag;
                            if (errorcode == 0)
                            {
                                flag = true;
                            } else
                            {
                                flag = false;
                            }
                            dmcplayenginestatusmachine.setTransformActionResult(transformaction, flag, new Integer(errorcode));
                        }

            
            {
                this$1 = final__pcls3;
                renderudn = s;
                errorcode = I.this;
                super();
            }
                    }, 500L);
                } else
                if (mStatusMachine.getStatus() != DMCPlayEngineStatusMachine.Status.SEEKING)
                {
                    mStatusMachine.setStatus(DMCPlayEngineStatusMachine.Status.PLAYING);
                    return;
                }
            }

            private void onRenderStopedStatus(int i, com.arcsoft.adk.atv.MRCPCallback.DataOnGetTransportInfo dataongettransportinfo, String s)
            {
                if (mStatusMachine.getStatus() == DMCPlayEngineStatusMachine.Status.STARTINGPLAY)
                {
                    return;
                }
                if (mStatusMachine.getStatus() == DMCPlayEngineStatusMachine.Status.PLAYING && mPlayItemInfo != null && mPlayItemInfo.position <= mPlayItemInfo.duration && (mPlayItemInfo.duration - mPlayItemInfo.position <= 2000L || mPlayItemInfo.position < 500L))
                {
                    Log.e("DMCPlay", "Complete!!!!!!!!!!!!!!!!!!!!!");
                    mStatusMachine.doTransformAction(DMCPlayEngineStatusMachine.TransformAction.COMPLETE, null);
                    return;
                }
                if (mStatusMachine.getStatus() == DMCPlayEngineStatusMachine.Status.STOPPING || mStatusMachine.getStatus() == DMCPlayEngineStatusMachine.Status.CANCELING)
                {
                    removeAsyncOpMsg(ASyncMsgType.MSG_STOP, s, null);
                    stopSyncRenderInfo(7);
                    DMCPlayEngineStatusMachine dmcplayenginestatusmachine = mStatusMachine;
                    DMCPlayEngineStatusMachine.TransformAction transformaction = DMCPlayEngineStatusMachine.TransformAction.STOP;
                    boolean flag;
                    if (i == 0)
                    {
                        flag = true;
                    } else
                    {
                        flag = false;
                    }
                    dmcplayenginestatusmachine.setTransformActionResult(transformaction, flag, new Integer(i));
                    return;
                } else
                {
                    mStatusMachine.setStatus(DMCPlayEngineStatusMachine.Status.STOPED);
                    return;
                }
            }

            public void onGetCurrentTransportActions(final int errorcode, String s, final String renderudn)
            {
                if (errorcode != 0)
                {
                    return;
                } else
                {
                    bridgeRenderMsg(s. new Runnable() {

                        final _cls3 this$1;
                        final String val$allowedactions;
                        final int val$errorcode;
                        final String val$renderudn;

                        public void run()
                        {
                            if (!isGetMsgValid(ASyncMsgType.MSG_GETALLOWACTIONS, renderudn) || errorcode != 0)
                            {
                                return;
                            } else
                            {
                                Log.w("DMCPlay", (new StringBuilder()).append(" Allowed actions: ").append(allowedactions).toString());
                                return;
                            }
                        }

            
            {
                this$1 = final__pcls3;
                renderudn = s;
                errorcode = i;
                allowedactions = String.this;
                super();
            }
                    });
                    return;
                }
            }

            public void onGetMeidaInfo(final int errorcode, com.arcsoft.adk.atv.MRCPCallback.DataOnGetMediaInfo dataongetmediainfo, final String renderudn)
            {
                Log.v("DMCPlay", (new StringBuilder()).append("onGetMeidaInfo Dur = ").append(dataongetmediainfo.m_strMediaDuration).toString());
                if (errorcode != 0)
                {
                    return;
                } else
                {
                    bridgeRenderMsg(dataongetmediainfo. new Runnable() {

                        final _cls3 this$1;
                        final int val$errorcode;
                        final com.arcsoft.adk.atv.MRCPCallback.DataOnGetMediaInfo val$info;
                        final String val$renderudn;

                        public void run()
                        {
                            while (!isGetMsgValid(ASyncMsgType.MSG_GETMETADATA, renderudn) || errorcode != 0 || !checkRenerPlayingUri(info.m_strCurUri)) 
                            {
                                return;
                            }
                            updateProgress(mPlayItemInfo.position, 1000L * TimeUtils.convertTimeStringToSec(info.m_strMediaDuration));
                        }

            
            {
                this$1 = final__pcls3;
                renderudn = s;
                errorcode = i;
                info = com.arcsoft.adk.atv.MRCPCallback.DataOnGetMediaInfo.this;
                super();
            }
                    });
                    return;
                }
            }

            public void onGetMute(final int errorcode, boolean flag, final String renderudn)
            {
                bridgeRenderMsg(flag. new Runnable() {

                    final _cls3 this$1;
                    final int val$errorcode;
                    final boolean val$mute;
                    final String val$renderudn;

                    public void run()
                    {
                        if (isGetMsgValid(ASyncMsgType.MSG_GETMUTE, renderudn) && errorcode == 0 && mMute != mute)
                        {
                            mMute = mute;
                            if (mEngineListener != null)
                            {
                                mEngineListener.OnMute(mute);
                                return;
                            }
                        }
                    }

            
            {
                this$1 = final__pcls3;
                renderudn = s;
                errorcode = i;
                mute = Z.this;
                super();
            }
                });
            }

            public void onGetPositionInfo(final int errorcode, com.arcsoft.adk.atv.MRCPCallback.DataOnGetPositionInfo dataongetpositioninfo, final String renderudn)
            {
                Log.v("DMCPlay", (new StringBuilder()).append("onGetPositionInfo position = ").append(dataongetpositioninfo.m_strRelTime).toString());
                if (errorcode != 0)
                {
                    return;
                } else
                {
                    bridgeRenderMsg(dataongetpositioninfo. new Runnable() {

                        final _cls3 this$1;
                        final com.arcsoft.adk.atv.MRCPCallback.DataOnGetPositionInfo val$data;
                        final int val$errorcode;
                        final String val$renderudn;

                        public void run()
                        {
                            while (!isGetMsgValid(ASyncMsgType.MSG_GETPOSITION, renderudn) || errorcode != 0 || !checkRenerPlayingUri(data.m_strTrackUri)) 
                            {
                                return;
                            }
                            long l = 1000L * TimeUtils.convertTimeStringToSec(data.m_strRelTime);
                            long l1 = 1000L * TimeUtils.convertTimeStringToSec(data.m_strTrackDuration);
                            updateProgress(l, l1);
                        }

            
            {
                this$1 = final__pcls3;
                renderudn = s;
                errorcode = i;
                data = com.arcsoft.adk.atv.MRCPCallback.DataOnGetPositionInfo.this;
                super();
            }
                    });
                    return;
                }
            }

            public void onGetTransportInfo(int i, final com.arcsoft.adk.atv.MRCPCallback.DataOnGetTransportInfo info, final String renderudn)
            {
                Log.w("DMCPlay", (new StringBuilder()).append("onGetTransportInfo ").append(info.strCurrentTransportState).toString());
                bridgeRenderMsg(i. new Runnable() {

                    final _cls3 this$1;
                    final int val$errorcode;
                    final com.arcsoft.adk.atv.MRCPCallback.DataOnGetTransportInfo val$info;
                    final String val$renderudn;

                    public void run()
                    {
                        Log.v("DMCPlay", (new StringBuilder()).append("GetTransInfo Status = ").append(info.strCurrentTransportState).append(" CurStatus = ").append(mStatusMachine.getStatus()).toString());
                        if (isGetMsgValid(ASyncMsgType.MSG_GETACTION, renderudn))
                        {
                            if (errorcode != 0)
                            {
                                Log.v("DMCPlay", (new StringBuilder()).append("GetTransInfo errorcode=").append(errorcode).toString());
                                return;
                            }
                            if (info.strCurrentTransportState == null)
                            {
                                Log.v("DMCPlay", "GetTransInfo strCurrentTransportState is null");
                                return;
                            }
                            if (info.strCurrentTransportState.equalsIgnoreCase("PLAYING"))
                            {
                                onRenderPlayingStatus(errorcode, info, renderudn);
                                return;
                            }
                            if (info.strCurrentTransportState.equalsIgnoreCase("PAUSED_PLAYBACK"))
                            {
                                onRenderPausedStatus(errorcode, info, renderudn);
                                return;
                            }
                            if (info.strCurrentTransportState.equalsIgnoreCase("STOPPED"))
                            {
                                onRenderStopedStatus(errorcode, info, renderudn);
                                return;
                            }
                            if (info.strCurrentTransportState.equalsIgnoreCase("NO_MEDIA_PRESENT"))
                            {
                                onRenderNoMediaStatus(errorcode, info, renderudn);
                                return;
                            }
                        }
                    }

            
            {
                this$1 = final__pcls3;
                info = dataongettransportinfo;
                renderudn = s;
                errorcode = I.this;
                super();
            }
                });
            }

            public void onGetTransportSettings(int i, com.arcsoft.adk.atv.MRCPCallback.DataOnGetTransportSettings dataongettransportsettings, String s)
            {
            }

            public void onGetVolume(final int errorcode, int i, final String renderudn)
            {
                bridgeRenderMsg(i. new Runnable() {

                    final _cls3 this$1;
                    final int val$errorcode;
                    final String val$renderudn;
                    final int val$volume;

                    public void run()
                    {
                        if (isGetMsgValid(ASyncMsgType.MSG_GETVOLUME, renderudn) && errorcode == 0 && mCurVolume != volume)
                        {
                            mCurVolume = volume;
                            if (mEngineListener != null)
                            {
                                mEngineListener.OnVolumeChanged(volume);
                                return;
                            }
                        }
                    }

            
            {
                this$1 = final__pcls3;
                renderudn = s;
                errorcode = i;
                volume = I.this;
                super();
            }
                });
            }

            public void onMediaPause(int i, final String renderudn)
            {
                Log.w("DMCPlay", (new StringBuilder()).append("onMediaPause = ").append(i).toString());
                bridgeRenderMsg(i. new Runnable() {

                    final _cls3 this$1;
                    final int val$errorcode;
                    final String val$renderudn;

                    public void run()
                    {
                        if (!removeAsyncOpMsg(ASyncMsgType.MSG_PAUSE, renderudn, null))
                        {
                            return;
                        }
                        DMCPlayEngineStatusMachine dmcplayenginestatusmachine = mStatusMachine;
                        DMCPlayEngineStatusMachine.TransformAction transformaction = DMCPlayEngineStatusMachine.TransformAction.PAUSE;
                        boolean flag;
                        if (errorcode == 0)
                        {
                            flag = true;
                        } else
                        {
                            flag = false;
                        }
                        dmcplayenginestatusmachine.setTransformActionResult(transformaction, flag, new Integer(errorcode));
                    }

            
            {
                this$1 = final__pcls3;
                renderudn = s;
                errorcode = I.this;
                super();
            }
                });
            }

            public void onMediaPlay(int i, final String renderudn)
            {
                Log.w("DMCPlay", (new StringBuilder()).append("onMediaPlay = ").append(i).toString());
                if (i != 0)
                {
                    bridgeRenderMsgDelay(i. new Runnable() {

                        final _cls3 this$1;
                        final int val$errorcode;
                        final String val$renderudn;

                        public void run()
                        {
                            if (!removeAsyncOpMsg(ASyncMsgType.MSG_PLAY, renderudn, null))
                            {
                                return;
                            }
                            DMCPlayEngineStatusMachine dmcplayenginestatusmachine = mStatusMachine;
                            DMCPlayEngineStatusMachine.TransformAction transformaction = DMCPlayEngineStatusMachine.TransformAction.PLAY;
                            boolean flag;
                            if (errorcode == 0)
                            {
                                flag = true;
                            } else
                            {
                                flag = false;
                            }
                            dmcplayenginestatusmachine.setTransformActionResult(transformaction, flag, new Integer(errorcode));
                        }

            
            {
                this$1 = final__pcls3;
                renderudn = s;
                errorcode = I.this;
                super();
            }
                    }, 500L);
                }
            }

            public void onMediaSeek(final int errorcode, String s)
            {
                mSyncHandler.removeMessages(-8191);
                Log.v("DMCPlay", "onMediaSeek()");
                bridgeRenderMsg(s. new Runnable() {

                    final _cls3 this$1;
                    final int val$errorcode;
                    final String val$renderudn;

                    public void run()
                    {
                        Log.v("DMCPlay", (new StringBuilder()).append("onMediaSeek() start, errorcode=").append(errorcode).append(",renderudn=").append(renderudn).toString());
                        if (!removeAsyncOpMsg(ASyncMsgType.MSG_SEEK, renderudn, null))
                        {
                            Log.v("DMCPlay", "onMediaSeek() fail");
                            return;
                        }
                        DMCPlayEngineStatusMachine dmcplayenginestatusmachine = mStatusMachine;
                        DMCPlayEngineStatusMachine.TransformAction transformaction = DMCPlayEngineStatusMachine.TransformAction.SEEK;
                        boolean flag;
                        if (errorcode == 0)
                        {
                            flag = true;
                        } else
                        {
                            flag = false;
                        }
                        dmcplayenginestatusmachine.setTransformActionResult(transformaction, flag, new Integer(errorcode));
                    }

            
            {
                this$1 = final__pcls3;
                errorcode = i;
                renderudn = String.this;
                super();
            }
                });
            }

            public void onMediaStop(int i, final String renderudn)
            {
                Log.w("DMCPlay", (new StringBuilder()).append("onMediaStop = ").append(i).toString());
                bridgeRenderMsg(i. new Runnable() {

                    final _cls3 this$1;
                    final int val$err;
                    final String val$renderudn;

                    public void run()
                    {
                        if (!removeAsyncOpMsg(ASyncMsgType.MSG_STOP, renderudn, null))
                        {
                            return;
                        }
                        if (err != 0 && mStopRetryCnt <= 2)
                        {
                            _cls3 _lcls3 = _cls3.this;
                            _lcls3.mStopRetryCnt = 1 + _lcls3.mStopRetryCnt;
                            PlayEffect playeffect = new PlayEffect();
                            playeffect.effect = IDMCPlayEffect.EFFECT.EFFECT_NONE;
                            doStopAction(playeffect);
                            Log.e("DMCPlay", (new StringBuilder()).append("Faild to Stop, retry stop with no effect , Current Stop retry count = ").append(mStopRetryCnt).toString());
                            return;
                        }
                        if (err == 0)
                        {
                            Log.e("DMCPlay", "Success to Stop");
                        } else
                        {
                            Log.e("DMCPlay", "Faild to Stop anyway, set stop success force!!!!!!!");
                        }
                        mStopRetryCnt = 0;
                        mStatusMachine.setTransformActionResult(DMCPlayEngineStatusMachine.TransformAction.STOP, true, new Integer(0));
                    }

            
            {
                this$1 = final__pcls3;
                renderudn = s;
                err = I.this;
                super();
            }
                });
            }

            public void onOpenMedia(int i, final String renderudn)
            {
                Log.w("DMCPlay", (new StringBuilder()).append("onOpenMedia = ").append(i).toString());
                bridgeRenderMsg(i. new Runnable() {

                    final _cls3 this$1;
                    final int val$errorcode;
                    final String val$renderudn;

                    public void run()
                    {
                        if (!removeAsyncOpMsg(ASyncMsgType.MSG_OPEN, renderudn, null))
                        {
                            return;
                        }
                        DMCPlayEngineStatusMachine dmcplayenginestatusmachine = mStatusMachine;
                        DMCPlayEngineStatusMachine.TransformAction transformaction = DMCPlayEngineStatusMachine.TransformAction.OPEN;
                        boolean flag;
                        if (errorcode == 0)
                        {
                            flag = true;
                        } else
                        {
                            flag = false;
                        }
                        dmcplayenginestatusmachine.setTransformActionResult(transformaction, flag, new Integer(errorcode));
                    }

            
            {
                this$1 = final__pcls3;
                renderudn = s;
                errorcode = I.this;
                super();
            }
                });
            }

            public void onSetMute(int i, String s)
            {
            }

            public void onSetVolume(int i, String s)
            {
            }





            
            {
                this$0 = DMCPlayEngine.this;
                super();
                mStopRetryCnt = 0;
            }
        };
        mStatusActionListener = new DMCPlayEngineStatusMachine.IOnStatusActionListener() {

            final DMCPlayEngine this$0;

            public boolean doAction(DMCPlayEngineStatusMachine.TransformAction transformaction, Object obj)
            {
                Log.v("DMCPlay", "doAction()");
                static class _cls7
                {

                    static final int $SwitchMap$com$arcsoft$mediaplus$playengine$DMCPlayEngineStatusMachine$Status[];
                    static final int $SwitchMap$com$arcsoft$mediaplus$playengine$DMCPlayEngineStatusMachine$TransformAction[];
                    static final int $SwitchMap$com$arcsoft$mediaplus$playengine$IDMCPlayEffect$EFFECT[];

                    static 
                    {
                        $SwitchMap$com$arcsoft$mediaplus$playengine$IDMCPlayEffect$EFFECT = new int[IDMCPlayEffect.EFFECT.values().length];
                        try
                        {
                            $SwitchMap$com$arcsoft$mediaplus$playengine$IDMCPlayEffect$EFFECT[IDMCPlayEffect.EFFECT.EFFECT_START_PLAY_SLIDE.ordinal()] = 1;
                        }
                        catch (NoSuchFieldError nosuchfielderror) { }
                        try
                        {
                            $SwitchMap$com$arcsoft$mediaplus$playengine$IDMCPlayEffect$EFFECT[IDMCPlayEffect.EFFECT.EFFECT_START_PLAY_FADE.ordinal()] = 2;
                        }
                        catch (NoSuchFieldError nosuchfielderror1) { }
                        try
                        {
                            $SwitchMap$com$arcsoft$mediaplus$playengine$IDMCPlayEffect$EFFECT[IDMCPlayEffect.EFFECT.EFFECT_TURNTO_PLAY.ordinal()] = 3;
                        }
                        catch (NoSuchFieldError nosuchfielderror2) { }
                        try
                        {
                            $SwitchMap$com$arcsoft$mediaplus$playengine$IDMCPlayEffect$EFFECT[IDMCPlayEffect.EFFECT.EFFECT_STOP_PLAY_SLIDE.ordinal()] = 4;
                        }
                        catch (NoSuchFieldError nosuchfielderror3) { }
                        try
                        {
                            $SwitchMap$com$arcsoft$mediaplus$playengine$IDMCPlayEffect$EFFECT[IDMCPlayEffect.EFFECT.EFFECT_STOP_PLAY_FADE.ordinal()] = 5;
                        }
                        catch (NoSuchFieldError nosuchfielderror4) { }
                        try
                        {
                            $SwitchMap$com$arcsoft$mediaplus$playengine$IDMCPlayEffect$EFFECT[IDMCPlayEffect.EFFECT.EFFECT_NONE.ordinal()] = 6;
                        }
                        catch (NoSuchFieldError nosuchfielderror5) { }
                        $SwitchMap$com$arcsoft$mediaplus$playengine$DMCPlayEngineStatusMachine$Status = new int[DMCPlayEngineStatusMachine.Status.values().length];
                        try
                        {
                            $SwitchMap$com$arcsoft$mediaplus$playengine$DMCPlayEngineStatusMachine$Status[DMCPlayEngineStatusMachine.Status.FAULT.ordinal()] = 1;
                        }
                        catch (NoSuchFieldError nosuchfielderror6) { }
                        try
                        {
                            $SwitchMap$com$arcsoft$mediaplus$playengine$DMCPlayEngineStatusMachine$Status[DMCPlayEngineStatusMachine.Status.STOPED.ordinal()] = 2;
                        }
                        catch (NoSuchFieldError nosuchfielderror7) { }
                        try
                        {
                            $SwitchMap$com$arcsoft$mediaplus$playengine$DMCPlayEngineStatusMachine$Status[DMCPlayEngineStatusMachine.Status.STOPPING.ordinal()] = 3;
                        }
                        catch (NoSuchFieldError nosuchfielderror8) { }
                        try
                        {
                            $SwitchMap$com$arcsoft$mediaplus$playengine$DMCPlayEngineStatusMachine$Status[DMCPlayEngineStatusMachine.Status.CANCELING.ordinal()] = 4;
                        }
                        catch (NoSuchFieldError nosuchfielderror9) { }
                        try
                        {
                            $SwitchMap$com$arcsoft$mediaplus$playengine$DMCPlayEngineStatusMachine$Status[DMCPlayEngineStatusMachine.Status.CANCELED.ordinal()] = 5;
                        }
                        catch (NoSuchFieldError nosuchfielderror10) { }
                        try
                        {
                            $SwitchMap$com$arcsoft$mediaplus$playengine$DMCPlayEngineStatusMachine$Status[DMCPlayEngineStatusMachine.Status.OPENING.ordinal()] = 6;
                        }
                        catch (NoSuchFieldError nosuchfielderror11) { }
                        try
                        {
                            $SwitchMap$com$arcsoft$mediaplus$playengine$DMCPlayEngineStatusMachine$Status[DMCPlayEngineStatusMachine.Status.OPENED.ordinal()] = 7;
                        }
                        catch (NoSuchFieldError nosuchfielderror12) { }
                        try
                        {
                            $SwitchMap$com$arcsoft$mediaplus$playengine$DMCPlayEngineStatusMachine$Status[DMCPlayEngineStatusMachine.Status.STARTINGPLAY.ordinal()] = 8;
                        }
                        catch (NoSuchFieldError nosuchfielderror13) { }
                        try
                        {
                            $SwitchMap$com$arcsoft$mediaplus$playengine$DMCPlayEngineStatusMachine$Status[DMCPlayEngineStatusMachine.Status.PLAYING.ordinal()] = 9;
                        }
                        catch (NoSuchFieldError nosuchfielderror14) { }
                        try
                        {
                            $SwitchMap$com$arcsoft$mediaplus$playengine$DMCPlayEngineStatusMachine$Status[DMCPlayEngineStatusMachine.Status.PAUSING.ordinal()] = 10;
                        }
                        catch (NoSuchFieldError nosuchfielderror15) { }
                        try
                        {
                            $SwitchMap$com$arcsoft$mediaplus$playengine$DMCPlayEngineStatusMachine$Status[DMCPlayEngineStatusMachine.Status.PAUSED.ordinal()] = 11;
                        }
                        catch (NoSuchFieldError nosuchfielderror16) { }
                        try
                        {
                            $SwitchMap$com$arcsoft$mediaplus$playengine$DMCPlayEngineStatusMachine$Status[DMCPlayEngineStatusMachine.Status.SEEKING.ordinal()] = 12;
                        }
                        catch (NoSuchFieldError nosuchfielderror17) { }
                        $SwitchMap$com$arcsoft$mediaplus$playengine$DMCPlayEngineStatusMachine$TransformAction = new int[DMCPlayEngineStatusMachine.TransformAction.values().length];
                        try
                        {
                            $SwitchMap$com$arcsoft$mediaplus$playengine$DMCPlayEngineStatusMachine$TransformAction[DMCPlayEngineStatusMachine.TransformAction.OPEN.ordinal()] = 1;
                        }
                        catch (NoSuchFieldError nosuchfielderror18) { }
                        try
                        {
                            $SwitchMap$com$arcsoft$mediaplus$playengine$DMCPlayEngineStatusMachine$TransformAction[DMCPlayEngineStatusMachine.TransformAction.STOP.ordinal()] = 2;
                        }
                        catch (NoSuchFieldError nosuchfielderror19) { }
                        try
                        {
                            $SwitchMap$com$arcsoft$mediaplus$playengine$DMCPlayEngineStatusMachine$TransformAction[DMCPlayEngineStatusMachine.TransformAction.COMPLETE.ordinal()] = 3;
                        }
                        catch (NoSuchFieldError nosuchfielderror20) { }
                        try
                        {
                            $SwitchMap$com$arcsoft$mediaplus$playengine$DMCPlayEngineStatusMachine$TransformAction[DMCPlayEngineStatusMachine.TransformAction.PLAY.ordinal()] = 4;
                        }
                        catch (NoSuchFieldError nosuchfielderror21) { }
                        try
                        {
                            $SwitchMap$com$arcsoft$mediaplus$playengine$DMCPlayEngineStatusMachine$TransformAction[DMCPlayEngineStatusMachine.TransformAction.PAUSE.ordinal()] = 5;
                        }
                        catch (NoSuchFieldError nosuchfielderror22) { }
                        try
                        {
                            $SwitchMap$com$arcsoft$mediaplus$playengine$DMCPlayEngineStatusMachine$TransformAction[DMCPlayEngineStatusMachine.TransformAction.SEEK.ordinal()] = 6;
                        }
                        catch (NoSuchFieldError nosuchfielderror23)
                        {
                            return;
                        }
                    }
                }

                switch (_cls7..SwitchMap.com.arcsoft.mediaplus.playengine.DMCPlayEngineStatusMachine.TransformAction[transformaction.ordinal()])
                {
                default:
                    return false;

                case 1: // '\001'
                    return doOpenAction(obj);

                case 2: // '\002'
                    return doStopAction(obj);

                case 3: // '\003'
                    return doCompleteAction(obj);

                case 4: // '\004'
                    return doPlayAction(obj);

                case 5: // '\005'
                    return doPauseAction(obj);

                case 6: // '\006'
                    return doSeekAction(obj);
                }
            }

            public void onDoActionError(DMCPlayEngineStatusMachine.TransformAction transformaction, Object obj)
            {
                Log.v("DMCPlay", (new StringBuilder()).append("onDoActionError(), action=").append(transformaction).toString());
                if (mEngineListener != null)
                {
                    IPlayEngine.PlayEngineError playengineerror = convertErrorCode(transformaction);
                    mEngineListener.OnError(playengineerror);
                }
            }

            public void onDoActionResultError(DMCPlayEngineStatusMachine.TransformAction transformaction, Object obj)
            {
                Log.v("DMCPlay", (new StringBuilder()).append("onDoActionResultError(), action=").append(transformaction).toString());
                if (mEngineListener != null)
                {
                    IPlayEngine.PlayEngineError playengineerror = convertErrorCode(transformaction);
                    mEngineListener.OnError(playengineerror);
                }
            }

            
            {
                this$0 = DMCPlayEngine.this;
                super();
            }
        };
        mStatusChangeListener = new DMCPlayEngineStatusMachine.IOnStatusChangedListener() {

            final DMCPlayEngine this$0;

            public void onStatusChanged(DMCPlayEngineStatusMachine.Status status, DMCPlayEngineStatusMachine.Status status1, DMCPlayEngineStatusMachine.TransformAction transformaction)
            {
                Log.w("DMCPlay", (new StringBuilder()).append("onStatusChanged = ").append(status1).toString());
                _cls7..SwitchMap.com.arcsoft.mediaplus.playengine.DMCPlayEngineStatusMachine.Status[status1.ordinal()];
                JVM INSTR tableswitch 1 12: default 96
            //                           1 97
            //                           2 138
            //                           3 198
            //                           4 529
            //                           5 539
            //                           6 229
            //                           7 252
            //                           8 315
            //                           9 347
            //                           10 427
            //                           11 458
            //                           12 574;
                   goto _L1 _L2 _L3 _L4 _L5 _L6 _L7 _L8 _L9 _L10 _L11 _L12 _L13
_L1:
                return;
_L2:
                stopSyncRenderInfo(7);
                updateProgress(0L, 0L);
                if (mEngineListener != null)
                {
                    mEngineListener.onFault();
                    return;
                }
                continue; /* Loop/switch isn't completed */
_L3:
                stopSyncRenderInfo(7);
                updateProgress(0L, 0L);
                if (mEngineListener != null)
                {
                    mEngineListener.OnStopped();
                    if (transformaction == DMCPlayEngineStatusMachine.TransformAction.COMPLETE)
                    {
                        mEngineListener.OnComplete();
                        return;
                    }
                }
                if (true) goto _L1; else goto _L4
_L4:
                stopSyncRenderInfo(3);
                if (mEngineListener != null)
                {
                    mEngineListener.OnStopping();
                    return;
                }
                continue; /* Loop/switch isn't completed */
_L7:
                if (mEngineListener != null)
                {
                    mEngineListener.OnOpening();
                    return;
                }
                if (true) goto _L1; else goto _L8
_L8:
                if (transformaction != DMCPlayEngineStatusMachine.TransformAction.SEEK)
                {
                    continue; /* Loop/switch isn't completed */
                }
                if (mEngineListener != null)
                {
                    mEngineListener.OnSeeked(mPlayItemInfo.position);
                    return;
                }
                continue; /* Loop/switch isn't completed */
                if (mEngineListener == null) goto _L1; else goto _L14
_L14:
                mEngineListener.OnOpened();
                return;
_L9:
                startSyncRenderInfo(true, 1);
                if (mEngineListener != null)
                {
                    mEngineListener.OnStartingPlay();
                    return;
                }
                if (true) goto _L1; else goto _L10
_L10:
                if (transformaction == DMCPlayEngineStatusMachine.TransformAction.SEEK && mEngineListener != null)
                {
                    mEngineListener.OnSeeked(mPlayItemInfo.position);
                }
                startSyncRenderInfo(false, 4);
                if (mEngineListener != null)
                {
                    mEngineListener.OnStartedPlay();
                    startSyncRenderInfo(true, 3);
                    return;
                }
                if (true)
                {
                    continue; /* Loop/switch isn't completed */
                }
_L11:
                stopSyncRenderInfo(4);
                if (mEngineListener != null)
                {
                    mEngineListener.OnPausing();
                    return;
                }
                if (true) goto _L1; else goto _L12
_L12:
                if (transformaction == DMCPlayEngineStatusMachine.TransformAction.SEEK)
                {
                    if (mEngineListener != null)
                    {
                        mEngineListener.OnSeeked(mPlayItemInfo.position);
                    }
                    startSyncRenderInfo(true, 4);
                }
                if (mEngineListener != null)
                {
                    mEngineListener.OnPaused();
                    return;
                }
                if (true)
                {
                    continue; /* Loop/switch isn't completed */
                }
_L5:
                startSyncRenderInfo(false, 1);
                return;
_L6:
                stopSyncRenderInfo(7);
                if (mEngineListener != null)
                {
                    mStatusMachine.doTransformAction(DMCPlayEngineStatusMachine.TransformAction.OPEN, null);
                    return;
                }
                if (true) goto _L1; else goto _L13
_L13:
                stopSyncRenderInfo(4);
                if (mEngineListener != null)
                {
                    mEngineListener.OnSeeking();
                    return;
                }
                if (true) goto _L1; else goto _L15
_L15:
            }

            
            {
                this$0 = DMCPlayEngine.this;
                super();
            }
        };
        mSyncHandler = new Handler() {

            final DMCPlayEngine this$0;

            public void handleMessage(Message message)
            {
                boolean flag;
                long l;
                RenderManager rendermanager;
                flag = true;
                l = 0L;
                rendermanager = DLNA.instance().getRenderManager();
                if (message.what != 8) goto _L2; else goto _L1
_L1:
                setGetMsgValid(ASyncMsgType.MSG_GETVOLUME, true);
                rendermanager.getVolumeAsync(mRenderUDN, com.arcsoft.adk.atv.RenderManager.VolumeChannel.MASTER);
                l = 3000L;
_L4:
                if (flag)
                {
                    sendEmptyMessageDelayed(message.what, l);
                }
                return;
_L2:
                if (message.what == 1)
                {
                    setGetMsgValid(ASyncMsgType.MSG_GETACTION, true);
                    rendermanager.getTransportInfoAsync(mRenderUDN);
                    l = 1000L;
                } else
                if (message.what == 16)
                {
                    setGetMsgValid(ASyncMsgType.MSG_GETALLOWACTIONS, true);
                    rendermanager.getCurrentTransportActionsAsync(mRenderUDN);
                    l = 300L;
                } else
                if (message.what != 2)
                {
                    if (message.what == 4)
                    {
                        if (getStatus() == IPlayEngine.PLAYSTATUS.STATUS_PLAYING)
                        {
                            setGetMsgValid(ASyncMsgType.MSG_GETPOSITION, true);
                            rendermanager.getPositionInfoAsync(mRenderUDN);
                        }
                        l = 1000L;
                    } else
                    if (message.what == -1 || message.what == -8191)
                    {
                        ((Runnable)message.obj).run();
                        flag = false;
                    }
                }
                if (true) goto _L4; else goto _L3
_L3:
            }

            
            {
                this$0 = DMCPlayEngine.this;
                super();
            }
        };
        mOpMsgWaitQueue = new HashMap();
        mGetMsgValidMap = new HashMap();
        mRefCount = 0;
        mCurVolume = 0;
        mMute = false;
        mPlayItemInfo = new PlayItemInfo();
        mPlayEffect = new PlayEffect();
        mRenderUDN = null;
        mEngineListener = null;
        mStatusMachine = new DMCPlayEngineStatusMachine();
    }

    private void addAsyncOpMsg(ASyncMsgType asyncmsgtype)
    {
        Map map = mOpMsgWaitQueue;
        map;
        JVM INSTR monitorenter ;
        long l;
        ArrayList arraylist;
        l = System.currentTimeMillis();
        arraylist = (ArrayList)mOpMsgWaitQueue.get(asyncmsgtype);
        if (arraylist != null)
        {
            break MISSING_BLOCK_LABEL_54;
        }
        arraylist = new ArrayList();
        mOpMsgWaitQueue.put(asyncmsgtype, arraylist);
        AsyncMsgInfo asyncmsginfo = new AsyncMsgInfo();
        asyncmsginfo.gettime = l;
        asyncmsginfo.valid = true;
        arraylist.add(asyncmsginfo);
        map;
        JVM INSTR monitorexit ;
        return;
        Exception exception;
        exception;
        map;
        JVM INSTR monitorexit ;
        throw exception;
    }

    private void addRef()
    {
        mRefCount = 1 + mRefCount;
    }

    private void bridgeRenderMsg(Runnable runnable)
    {
        Message message = Message.obtain();
        message.what = -1;
        message.obj = runnable;
        mSyncHandler.sendMessage(message);
    }

    private void bridgeRenderMsgDelay(Runnable runnable, long l)
    {
        Message message = Message.obtain();
        message.what = -1;
        message.obj = runnable;
        mSyncHandler.sendMessageDelayed(message, l);
    }

    private boolean checkRenerPlayingUri(String s)
    {
        boolean flag = true;
        if (s == null || mPlayItemInfo == null || !s.equals(mPlayItemInfo.url))
        {
            boolean flag1;
            boolean flag2;
            boolean flag3;
            if (s == null)
            {
                flag1 = flag;
            } else
            {
                flag1 = false;
            }
            if (mPlayItemInfo == null)
            {
                flag2 = flag;
            } else
            {
                flag2 = false;
            }
            if (!s.equals(mPlayItemInfo.url))
            {
                flag3 = flag;
            } else
            {
                flag3 = false;
            }
            Log.w("DMCPlay", (new StringBuilder()).append("Receive Diff URI cururi C1 = ").append(flag1).append(" C2 = ").append(flag2).append(" C3 = ").append(flag3).toString());
            Log.w("DMCPlay", (new StringBuilder()).append("Receive Diff URI cururi cururi = ").append(s).toString());
            Log.w("DMCPlay", (new StringBuilder()).append("Receive Diff URI cururi mPlayItemInfo.url = ").append(mPlayItemInfo.url).toString());
            stopSyncRenderInfo(7);
            mStatusMachine.setStatus(DMCPlayEngineStatusMachine.Status.STOPED);
            flag = false;
        }
        return flag;
    }

    private void clearAsyncMsg(ASyncMsgType asyncmsgtype)
    {
        Map map = mOpMsgWaitQueue;
        map;
        JVM INSTR monitorenter ;
        ArrayList arraylist = (ArrayList)mOpMsgWaitQueue.get(asyncmsgtype);
        if (arraylist == null)
        {
            break MISSING_BLOCK_LABEL_35;
        }
        if (arraylist.size() > 0)
        {
            break MISSING_BLOCK_LABEL_38;
        }
        map;
        JVM INSTR monitorexit ;
        return;
        for (Iterator iterator = arraylist.iterator(); iterator.hasNext();)
        {
            ((AsyncMsgInfo)iterator.next()).valid = false;
        }

        break MISSING_BLOCK_LABEL_77;
        Exception exception;
        exception;
        map;
        JVM INSTR monitorexit ;
        throw exception;
        map;
        JVM INSTR monitorexit ;
    }

    private IPlayEngine.PlayEngineError convertErrorCode(DMCPlayEngineStatusMachine.TransformAction transformaction)
    {
        IPlayEngine.PlayEngineError playengineerror = IPlayEngine.PlayEngineError.ERROR_UNKNOWN;
        switch (_cls7..SwitchMap.com.arcsoft.mediaplus.playengine.DMCPlayEngineStatusMachine.TransformAction[transformaction.ordinal()])
        {
        default:
            return playengineerror;

        case 1: // '\001'
            return IPlayEngine.PlayEngineError.ERROR_OPENFILE;

        case 2: // '\002'
            return IPlayEngine.PlayEngineError.ERROR_STOP;

        case 3: // '\003'
            return IPlayEngine.PlayEngineError.ERROR_STOP;

        case 4: // '\004'
            return IPlayEngine.PlayEngineError.ERROR_PLAY;

        case 5: // '\005'
            return IPlayEngine.PlayEngineError.ERROR_PAUSE;

        case 6: // '\006'
            return IPlayEngine.PlayEngineError.ERROR_SEEK;
        }
    }

    private boolean doCompleteAction(Object obj)
    {
        return true;
    }

    private boolean doOpenAction(Object obj)
    {
        boolean flag = DLNA.instance().getRenderManager().openMediaAsync(mRenderUDN, mPlayItemInfo.url, mPlayItemInfo.metadata);
        if (flag)
        {
            addAsyncOpMsg(ASyncMsgType.MSG_OPEN);
        }
        Log.w("DMCPlay", (new StringBuilder()).append("doOpenAction = ").append(flag).toString());
        return flag;
    }

    private boolean doPauseAction(Object obj)
    {
        boolean flag = DLNA.instance().getRenderManager().pauseMediaAsync(mRenderUDN);
        if (flag)
        {
            addAsyncOpMsg(ASyncMsgType.MSG_PAUSE);
        }
        return flag;
    }

    private boolean doPlayAction(Object obj)
    {
        boolean flag = doPlayEffect(true, (PlayEffect)obj);
        if (flag)
        {
            addAsyncOpMsg(ASyncMsgType.MSG_PLAY);
        }
        Log.w("DMCPlay", (new StringBuilder()).append("doPlayAction = ").append(flag).toString());
        return flag;
    }

    private boolean doPlayEffect(boolean flag, PlayEffect playeffect)
    {
        IDMCPlayEffect.EFFECT effect;
        if (playeffect == null)
        {
            effect = IDMCPlayEffect.EFFECT.EFFECT_NONE;
        } else
        {
            effect = playeffect.effect;
        }
        if (flag)
        {
            switch (_cls7..SwitchMap.com.arcsoft.mediaplus.playengine.IDMCPlayEffect.EFFECT[effect.ordinal()])
            {
            case 4: // '\004'
            case 5: // '\005'
            default:
                return true;

            case 1: // '\001'
                return DLNA.instance().getRenderManager().playMediaAsync(mRenderUDN, com.arcsoft.adk.atv.RenderManager.PlaySpeed.NORMAL, RenderManager.SHARPTV_SLIDEUPEFFECT);

            case 2: // '\002'
                return DLNA.instance().getRenderManager().playMediaAsync(mRenderUDN, com.arcsoft.adk.atv.RenderManager.PlaySpeed.NORMAL, RenderManager.SHARPTV_FADEINEFFECT);

            case 3: // '\003'
                return DLNA.instance().getRenderManager().playMediaAsync(mRenderUDN, com.arcsoft.adk.atv.RenderManager.PlaySpeed.NORMAL, RenderManager.SHARPTV_FADEINEFFECT);

            case 6: // '\006'
                return DLNA.instance().getRenderManager().playMediaAsync(mRenderUDN, com.arcsoft.adk.atv.RenderManager.PlaySpeed.NORMAL, null);
            }
        }
        switch (_cls7..SwitchMap.com.arcsoft.mediaplus.playengine.IDMCPlayEffect.EFFECT[mPlayEffect.effect.ordinal()])
        {
        default:
            return true;

        case 1: // '\001'
        case 2: // '\002'
            return DLNA.instance().getRenderManager().stopMediaAsync(mRenderUDN, null, null);

        case 3: // '\003'
            return DLNA.instance().getRenderManager().stopMediaAsync(mRenderUDN, RenderManager.SHARPTV_FADEOUTEFFECT, "FALSE");

        case 4: // '\004'
            return DLNA.instance().getRenderManager().stopMediaAsync(mRenderUDN, RenderManager.SHARPTV_SLIDEDOWNEFFECT, "TRUE");

        case 5: // '\005'
            return DLNA.instance().getRenderManager().stopMediaAsync(mRenderUDN, RenderManager.SHARPTV_FADEOUTEFFECT, "TRUE");

        case 6: // '\006'
            return DLNA.instance().getRenderManager().stopMediaAsync(mRenderUDN, null, null);
        }
    }

    private boolean doSeekAction(Object obj)
    {
        mSyncHandler.removeMessages(-8191);
        stopSyncRenderInfo(1);
        long l = ((Long)obj).longValue();
        boolean flag = DLNA.instance().getRenderManager().seekMediaAsync(mRenderUDN, l);
        if (flag)
        {
            Log.v("DMCPlay", (new StringBuilder()).append("doSeekAction() success, position=").append(l).toString());
            mPlayItemInfo.position = l;
            addAsyncOpMsg(ASyncMsgType.MSG_SEEK);
            final String udn = mRenderUDN;
            Message message = mSyncHandler.obtainMessage(-8191);
            message.obj = new Runnable() {

                final DMCPlayEngine this$0;
                final String val$udn;

                public void run()
                {
                    if (mRenderPlayListener != null)
                    {
                        mRenderPlayListener.onMediaSeek(0, udn);
                    }
                }

            
            {
                this$0 = DMCPlayEngine.this;
                udn = s;
                super();
            }
            };
            mSyncHandler.sendMessageDelayed(message, 3000L);
            return flag;
        } else
        {
            Log.v("DMCPlay", "doSeekAction()fail");
            startSyncRenderInfo(true, 1);
            return flag;
        }
    }

    private boolean doStopAction(Object obj)
    {
        stopSyncRenderInfo(6);
        boolean flag = doPlayEffect(false, (PlayEffect)obj);
        startSyncRenderInfo(false, 1);
        if (flag)
        {
            addAsyncOpMsg(ASyncMsgType.MSG_STOP);
        }
        Log.w("DMCPlay", (new StringBuilder()).append("doStopAction = ").append(flag).toString());
        return flag;
    }

    public static DMCPlayEngine getDMCPlayEngine(String s, IDMCPlayEngineListener idmcplayenginelistener)
    {
        if (idmcplayenginelistener == null)
        {
            throw new IllegalArgumentException("Must proivde a engine listener");
        }
        ListIterator listiterator = mDMCEnginePool.listIterator();
        DMCPlayEngine dmcplayengine;
        do
        {
            boolean flag = listiterator.hasNext();
            dmcplayengine = null;
            if (!flag)
            {
                break;
            }
            DMCPlayEngine dmcplayengine1 = (DMCPlayEngine)listiterator.next();
            if (!dmcplayengine1.getRenderUDN().equals(s))
            {
                continue;
            }
            dmcplayengine1.reset();
            dmcplayengine = dmcplayengine1;
            break;
        } while (true);
        if (dmcplayengine == null)
        {
            dmcplayengine = new DMCPlayEngine();
            mDMCEnginePool.add(dmcplayengine);
            dmcplayengine.init();
            dmcplayengine.setRender(s);
        }
        if (dmcplayengine.mEngineListener != idmcplayenginelistener)
        {
            dmcplayengine.addRef();
            dmcplayengine.setEngineListener(idmcplayenginelistener, true);
        }
        return dmcplayengine;
    }

    private void init()
    {
        mStatusMachine.setOnStatusActionListener(mStatusActionListener);
        mStatusMachine.setOnStatusChangedListener(mStatusChangeListener);
        DLNA.instance().getRenderManager().registerRenderPlayListener(mRenderPlayListener);
        DLNA.instance().getRenderManager().registerRenderStatusListener(mRenderStatusListener);
    }

    private boolean isGetMsgValid(ASyncMsgType asyncmsgtype, String s)
    {
        if (!mRenderUDN.equals(s))
        {
            return false;
        }
        Map map = mGetMsgValidMap;
        map;
        JVM INSTR monitorenter ;
        Boolean boolean1 = (Boolean)mGetMsgValidMap.get(asyncmsgtype);
        boolean flag;
        flag = false;
        if (boolean1 == null)
        {
            break MISSING_BLOCK_LABEL_61;
        }
        boolean flag1 = boolean1.booleanValue();
        flag = false;
        if (flag1)
        {
            flag = true;
        }
        map;
        JVM INSTR monitorexit ;
        return flag;
        Exception exception;
        exception;
        map;
        JVM INSTR monitorexit ;
        throw exception;
    }

    private IPlayEngine.PLAYSTATUS machineStatusToEngineStatus(DMCPlayEngineStatusMachine.Status status)
    {
        switch (_cls7..SwitchMap.com.arcsoft.mediaplus.playengine.DMCPlayEngineStatusMachine.Status[status.ordinal()])
        {
        default:
            return null;

        case 1: // '\001'
            return IPlayEngine.PLAYSTATUS.STATUS_IDLE;

        case 2: // '\002'
            return IPlayEngine.PLAYSTATUS.STATUS_STOPPED;

        case 3: // '\003'
            return IPlayEngine.PLAYSTATUS.STATUS_STOPPING;

        case 4: // '\004'
            return IPlayEngine.PLAYSTATUS.STATUS_OPENING;

        case 5: // '\005'
            return IPlayEngine.PLAYSTATUS.STATUS_OPENING;

        case 6: // '\006'
            return IPlayEngine.PLAYSTATUS.STATUS_OPENING;

        case 7: // '\007'
            return IPlayEngine.PLAYSTATUS.STATUS_OPENED;

        case 8: // '\b'
            return IPlayEngine.PLAYSTATUS.STATUS_STARTINGPLAY;

        case 9: // '\t'
            return IPlayEngine.PLAYSTATUS.STATUS_PLAYING;

        case 10: // '\n'
            return IPlayEngine.PLAYSTATUS.STATUS_PAUSING;

        case 11: // '\013'
            return IPlayEngine.PLAYSTATUS.STATUS_PAUSED;

        case 12: // '\f'
            return IPlayEngine.PLAYSTATUS.STATUS_SEEKING;
        }
    }

    public static void releaseDMCPlayEngine(DMCPlayEngine dmcplayengine, IDMCPlayEngineListener idmcplayenginelistener)
    {
        ListIterator listiterator = mDMCEnginePool.listIterator();
        do
        {
            if (!listiterator.hasNext())
            {
                break;
            }
            if (dmcplayengine == (DMCPlayEngine)listiterator.next())
            {
                if (dmcplayengine.mEngineListener == idmcplayenginelistener)
                {
                    dmcplayengine.setEngineListener(null, false);
                }
                if (dmcplayengine.releaseRef() <= 0)
                {
                    dmcplayengine.uninit();
                    listiterator.remove();
                }
            }
        } while (true);
    }

    private int releaseRef()
    {
        mRefCount = -1 + mRefCount;
        return mRefCount;
    }

    private boolean removeAsyncOpMsg(ASyncMsgType asyncmsgtype, String s, AsyncMsgInfo aasyncmsginfo[])
    {
        if (!mRenderUDN.equals(s))
        {
            return false;
        }
        Map map = mOpMsgWaitQueue;
        map;
        JVM INSTR monitorenter ;
        ArrayList arraylist = (ArrayList)mOpMsgWaitQueue.get(asyncmsgtype);
        if (arraylist == null)
        {
            break MISSING_BLOCK_LABEL_50;
        }
        if (arraylist.size() > 0)
        {
            break MISSING_BLOCK_LABEL_63;
        }
        map;
        JVM INSTR monitorexit ;
        return false;
        Exception exception;
        exception;
        map;
        JVM INSTR monitorexit ;
        throw exception;
        AsyncMsgInfo asyncmsginfo = (AsyncMsgInfo)arraylist.remove(0);
        if (aasyncmsginfo == null)
        {
            break MISSING_BLOCK_LABEL_83;
        }
        aasyncmsginfo[0] = asyncmsginfo;
        boolean flag = asyncmsginfo.valid;
        map;
        JVM INSTR monitorexit ;
        return flag;
    }

    private void setEngineListener(IDMCPlayEngineListener idmcplayenginelistener, boolean flag)
    {
        if (idmcplayenginelistener == mEngineListener)
        {
            return;
        }
        if (mEngineListener != null && flag)
        {
            mEngineListener.onListenerLosed(idmcplayenginelistener);
        }
        mEngineListener = idmcplayenginelistener;
    }

    private void setGetMsgValid(ASyncMsgType asyncmsgtype, boolean flag)
    {
        synchronized (mGetMsgValidMap)
        {
            mGetMsgValidMap.put(asyncmsgtype, Boolean.valueOf(flag));
        }
        return;
        exception;
        map;
        JVM INSTR monitorexit ;
        throw exception;
    }

    private void startSyncRenderInfo(boolean flag, int i)
    {
        stopSyncRenderInfo(i);
        int j;
        if (flag)
        {
            j = 300;
        } else
        {
            j = 1000;
        }
        if ((i & 8) != 0)
        {
            mSyncHandler.sendEmptyMessageDelayed(8, j);
        }
        if ((i & 4) != 0)
        {
            Log.w("DMCPlay", "start SyncRenderInfo SYNC_POSITION");
            mSyncHandler.sendEmptyMessageDelayed(4, j);
        }
        if ((i & 2) != 0)
        {
            Log.w("DMCPlay", "start SyncRenderInfo SYNC_METADATA");
            mSyncHandler.sendEmptyMessageDelayed(2, j);
        }
        if ((i & 1) != 0)
        {
            Log.w("DMCPlay", "start  SyncRenderInfo SYNC_CURACTION");
            mSyncHandler.sendEmptyMessageDelayed(1, j);
        }
        if ((i & 0x10) != 0)
        {
            Log.w("DMCPlay", "start  SyncRenderInfo SYNC_ALLOWEDACTION");
            mSyncHandler.sendEmptyMessageDelayed(16, j);
        }
    }

    private void stopSyncRenderInfo(int i)
    {
        if ((i & 8) != 0)
        {
            mSyncHandler.removeMessages(8);
            setGetMsgValid(ASyncMsgType.MSG_GETMUTE, false);
            setGetMsgValid(ASyncMsgType.MSG_GETVOLUME, false);
        }
        if ((i & 4) != 0)
        {
            Log.w("DMCPlay", "stop SyncRenderInfo SYNC_POSITION");
            mSyncHandler.removeMessages(4);
            setGetMsgValid(ASyncMsgType.MSG_GETPOSITION, false);
        }
        if ((i & 1) != 0)
        {
            Log.w("DMCPlay", "stop SyncRenderInfo SYNC_CURACTION");
            mSyncHandler.removeMessages(1);
            setGetMsgValid(ASyncMsgType.MSG_GETACTION, false);
        }
        if ((i & 2) != 0)
        {
            Log.w("DMCPlay", "stop SyncRenderInfo SYNC_METADATA");
            mSyncHandler.removeMessages(2);
            setGetMsgValid(ASyncMsgType.MSG_GETMETADATA, false);
        }
        if ((i & 0x10) != 0)
        {
            Log.w("DMCPlay", "stop SyncRenderInfo SYNC_ALLOWEDACTION");
            mSyncHandler.removeMessages(16);
            setGetMsgValid(ASyncMsgType.MSG_GETALLOWACTIONS, false);
        }
    }

    private void uninit()
    {
        mEngineListener = null;
        stopSyncRenderInfo(15);
        mSyncHandler.removeMessages(-1);
        mStatusMachine.setOnStatusActionListener(null);
        mStatusMachine.setOnStatusChangedListener(null);
        mSyncHandler.removeMessages(-8191);
        DLNA.instance().getRenderManager().unregisterRenderPlayListener(mRenderPlayListener);
    }

    private void updateProgress(long l, long l1)
    {
        if (l1 != mPlayItemInfo.duration || l != mPlayItemInfo.position)
        {
            if (l > l1)
            {
                l = l1;
            }
            mPlayItemInfo.duration = l1;
            mPlayItemInfo.position = l;
            if (mEngineListener != null)
            {
                mEngineListener.OnProgressChanged(l, l1);
            }
        }
    }

    public boolean canOpen()
    {
        return mStatusMachine.canDoAction(DMCPlayEngineStatusMachine.TransformAction.OPEN);
    }

    public boolean canPause()
    {
        return mStatusMachine.canDoAction(DMCPlayEngineStatusMachine.TransformAction.PAUSE);
    }

    public boolean canPlay()
    {
        return mStatusMachine.canDoAction(DMCPlayEngineStatusMachine.TransformAction.PLAY);
    }

    public boolean canSeek()
    {
        return mStatusMachine.canDoAction(DMCPlayEngineStatusMachine.TransformAction.SEEK);
    }

    public boolean canStop()
    {
        return mStatusMachine.canDoAction(DMCPlayEngineStatusMachine.TransformAction.STOP);
    }

    public void gainListening(IDMCPlayEngineListener idmcplayenginelistener)
    {
        setEngineListener(idmcplayenginelistener, true);
    }

    public long getDuration()
    {
        if (mPlayItemInfo == null)
        {
            return 0L;
        } else
        {
            return mPlayItemInfo.duration;
        }
    }

    public IDMCPlayEngineListener getFocusedListener()
    {
        return mEngineListener;
    }

    public long getPosition()
    {
        if (mPlayItemInfo == null)
        {
            return 0L;
        } else
        {
            return mPlayItemInfo.position;
        }
    }

    public String getRenderUDN()
    {
        return mRenderUDN;
    }

    public IPlayEngine.PLAYSTATUS getStatus()
    {
        return machineStatusToEngineStatus(mStatusMachine.getStatus());
    }

    public int getVolume()
    {
        return mCurVolume;
    }

    public boolean isMute()
    {
        return mMute;
    }

    public boolean open(Uri uri)
    {
        throw new UnsupportedOperationException("Use open(String uri, String metadata) instead");
    }

    public boolean open(Uri uri, String s)
    {
        String s1 = uri.toString();
        if (mPlayItemInfo.url != null && !mPlayItemInfo.url.equals(s1))
        {
            mPlayItemInfo.duration = 0L;
        }
        mPlayItemInfo.position = 0L;
        mPlayItemInfo.metadata = s;
        mPlayItemInfo.url = s1;
        return mStatusMachine.doTransformAction(DMCPlayEngineStatusMachine.TransformAction.OPEN, null);
    }

    public boolean pause()
    {
        return mStatusMachine.doTransformAction(DMCPlayEngineStatusMachine.TransformAction.PAUSE, null);
    }

    public boolean play()
    {
        return mStatusMachine.doTransformAction(DMCPlayEngineStatusMachine.TransformAction.PLAY, mPlayEffect);
    }

    public void reset()
    {
        stopSyncRenderInfo(15);
        mStatusMachine.reset();
        mStatusMachine.setAllActionAllowed();
        updateProgress(0L, 0L);
        if (mRenderUDN != null && DLNA.instance().getRenderManager().isRenderOnline(mRenderUDN))
        {
            startSyncRenderInfo(true, 8);
            return;
        }
        mCurVolume = 0;
        mMute = false;
        if (mEngineListener != null)
        {
            mEngineListener.OnVolumeChanged(mCurVolume);
            mEngineListener.OnMute(mMute);
        }
        mStatusMachine.setAllActionForbiden();
    }

    public boolean seekTo(long l)
    {
        return mStatusMachine.doTransformAction(DMCPlayEngineStatusMachine.TransformAction.SEEK, new Long(l));
    }

    public boolean setMute(boolean flag)
    {
        if (flag == mMute)
        {
            return true;
        }
        stopSyncRenderInfo(8);
        boolean flag1 = DLNA.instance().getRenderManager().setMuteAsync(mRenderUDN, com.arcsoft.adk.atv.RenderManager.VolumeChannel.MASTER, flag);
        if (flag1)
        {
            mMute = flag;
        }
        startSyncRenderInfo(false, 8);
        return flag1;
    }

    public void setRender(String s)
    {
        if (mRenderUDN != null && mRenderUDN.equals(s))
        {
            return;
        }
        mRenderUDN = s;
        reset();
        synchronized (mOpMsgWaitQueue)
        {
            mOpMsgWaitQueue.clear();
        }
        return;
        exception;
        map;
        JVM INSTR monitorexit ;
        throw exception;
    }

    public boolean setVolume(int i)
    {
        if (mRenderUDN == null || !DLNA.instance().getRenderManager().isRenderOnline(mRenderUDN))
        {
            return false;
        }
        if (i < 0)
        {
            i = 0;
        }
        if (i > 100)
        {
            i = 100;
        }
        stopSyncRenderInfo(8);
        boolean flag = DLNA.instance().getRenderManager().setVolumeAsync(mRenderUDN, com.arcsoft.adk.atv.RenderManager.VolumeChannel.MASTER, i);
        if (flag)
        {
            mCurVolume = i;
        }
        startSyncRenderInfo(false, 8);
        return flag;
    }

    public boolean stop()
    {
        return mStatusMachine.doTransformAction(DMCPlayEngineStatusMachine.TransformAction.STOP, mPlayEffect);
    }

    public boolean stop(IDMCPlayEffect.EFFECT effect)
    {
        mPlayEffect.effect = effect;
        return mStatusMachine.doTransformAction(DMCPlayEngineStatusMachine.TransformAction.STOP, mPlayEffect);
    }





/*
    static boolean access$1002(DMCPlayEngine dmcplayengine, boolean flag)
    {
        dmcplayengine.mMute = flag;
        return flag;
    }

*/



/*
    static int access$1102(DMCPlayEngine dmcplayengine, int i)
    {
        dmcplayengine.mCurVolume = i;
        return i;
    }

*/





















}
