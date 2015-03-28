// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.arcsoft.mediaplus.playengine;

import android.os.Handler;
import com.arcsoft.util.TimeUtils;
import com.arcsoft.util.debug.Log;

// Referenced classes of package com.arcsoft.mediaplus.playengine:
//            DMCPlayEngine, DMCPlayEngineStatusMachine

class val.mute
    implements Runnable
{

    final val.mute this$1;
    final int val$errorcode;
    final boolean val$mute;
    final String val$renderudn;

    public void run()
    {
        if (DMCPlayEngine.access$600(_fld0, cMsgType.MSG_GETMUTE, val$renderudn) && val$errorcode == 0 && DMCPlayEngine.access$1000(_fld0) != val$mute)
        {
            DMCPlayEngine.access$1002(_fld0, val$mute);
            if (DMCPlayEngine.access$500(_fld0) != null)
            {
                DMCPlayEngine.access$500(_fld0).OnMute(val$mute);
                return;
            }
        }
    }

    topRetryCnt()
    {
        this$1 = final_topretrycnt;
        val$renderudn = s;
        val$errorcode = i;
        val$mute = Z.this;
        super();
    }

    // Unreferenced inner class com/arcsoft/mediaplus/playengine/DMCPlayEngine$3

/* anonymous class */
    class DMCPlayEngine._cls3
        implements com.arcsoft.adk.atv.RenderManager.IRenderPlayListener
    {

        int mStopRetryCnt;
        final DMCPlayEngine this$0;

        private void onRenderNoMediaStatus(int i, com.arcsoft.adk.atv.MRCPCallback.DataOnGetTransportInfo dataongettransportinfo, String s)
        {
            DMCPlayEngine.access$400(DMCPlayEngine.this).doTransformAction(DMCPlayEngineStatusMachine.TransformAction.COMPLETE, null);
        }

        private void onRenderPausedStatus(int i, com.arcsoft.adk.atv.MRCPCallback.DataOnGetTransportInfo dataongettransportinfo, String s)
        {
            if (DMCPlayEngine.access$400(DMCPlayEngine.this).getStatus() != DMCPlayEngineStatusMachine.Status.SEEKING)
            {
                DMCPlayEngine.access$400(DMCPlayEngine.this).setStatus(DMCPlayEngineStatusMachine.Status.PAUSED);
            }
        }

        private void onRenderPlayingStatus(final int errorcode, com.arcsoft.adk.atv.MRCPCallback.DataOnGetTransportInfo dataongettransportinfo, final String renderudn)
        {
            if (getStatus() == IPlayEngine.PLAYSTATUS.STATUS_STARTINGPLAY)
            {
                DMCPlayEngine.access$1800(DMCPlayEngine.this, new DMCPlayEngine._cls3._cls7(), 500L);
            } else
            if (DMCPlayEngine.access$400(DMCPlayEngine.this).getStatus() != DMCPlayEngineStatusMachine.Status.SEEKING)
            {
                DMCPlayEngine.access$400(DMCPlayEngine.this).setStatus(DMCPlayEngineStatusMachine.Status.PLAYING);
                return;
            }
        }

        private void onRenderStopedStatus(int i, com.arcsoft.adk.atv.MRCPCallback.DataOnGetTransportInfo dataongettransportinfo, String s)
        {
            if (DMCPlayEngine.access$400(DMCPlayEngine.this).getStatus() == DMCPlayEngineStatusMachine.Status.STARTINGPLAY)
            {
                return;
            }
            if (DMCPlayEngine.access$400(DMCPlayEngine.this).getStatus() == DMCPlayEngineStatusMachine.Status.PLAYING && DMCPlayEngine.access$800(DMCPlayEngine.this) != null && DMCPlayEngine.access$800(DMCPlayEngine.this).position <= DMCPlayEngine.access$800(DMCPlayEngine.this).duration && (DMCPlayEngine.access$800(DMCPlayEngine.this).duration - DMCPlayEngine.access$800(DMCPlayEngine.this).position <= 2000L || DMCPlayEngine.access$800(DMCPlayEngine.this).position < 500L))
            {
                Log.e("DMCPlay", "Complete!!!!!!!!!!!!!!!!!!!!!");
                DMCPlayEngine.access$400(DMCPlayEngine.this).doTransformAction(DMCPlayEngineStatusMachine.TransformAction.COMPLETE, null);
                return;
            }
            if (DMCPlayEngine.access$400(DMCPlayEngine.this).getStatus() == DMCPlayEngineStatusMachine.Status.STOPPING || DMCPlayEngine.access$400(DMCPlayEngine.this).getStatus() == DMCPlayEngineStatusMachine.Status.CANCELING)
            {
                DMCPlayEngine.access$1600(DMCPlayEngine.this, DMCPlayEngine.ASyncMsgType.MSG_STOP, s, null);
                DMCPlayEngine.access$1700(DMCPlayEngine.this, 7);
                DMCPlayEngineStatusMachine dmcplayenginestatusmachine = DMCPlayEngine.access$400(DMCPlayEngine.this);
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
                DMCPlayEngine.access$400(DMCPlayEngine.this).setStatus(DMCPlayEngineStatusMachine.Status.STOPED);
                return;
            }
        }

        public void onGetCurrentTransportActions(final int errorcode, final String allowedactions, final String renderudn)
        {
            if (errorcode != 0)
            {
                return;
            } else
            {
                DMCPlayEngine.access$300(DMCPlayEngine.this, new DMCPlayEngine._cls3._cls1());
                return;
            }
        }

        public void onGetMeidaInfo(final int errorcode, final com.arcsoft.adk.atv.MRCPCallback.DataOnGetMediaInfo info, final String renderudn)
        {
            Log.v("DMCPlay", (new StringBuilder()).append("onGetMeidaInfo Dur = ").append(info.m_strMediaDuration).toString());
            if (errorcode != 0)
            {
                return;
            } else
            {
                DMCPlayEngine.access$300(DMCPlayEngine.this, new DMCPlayEngine._cls3._cls2());
                return;
            }
        }

        public void onGetMute(final int errorcode, boolean flag, final String renderudn)
        {
            DMCPlayEngine.access$300(DMCPlayEngine.this, flag. new DMCPlayEngine._cls3._cls3());
        }

        public void onGetPositionInfo(final int errorcode, final com.arcsoft.adk.atv.MRCPCallback.DataOnGetPositionInfo data, final String renderudn)
        {
            Log.v("DMCPlay", (new StringBuilder()).append("onGetPositionInfo position = ").append(data.m_strRelTime).toString());
            if (errorcode != 0)
            {
                return;
            } else
            {
                DMCPlayEngine.access$300(DMCPlayEngine.this, new DMCPlayEngine._cls3._cls5());
                return;
            }
        }

        public void onGetTransportInfo(final int errorcode, final com.arcsoft.adk.atv.MRCPCallback.DataOnGetTransportInfo info, final String renderudn)
        {
            Log.w("DMCPlay", (new StringBuilder()).append("onGetTransportInfo ").append(info.strCurrentTransportState).toString());
            DMCPlayEngine.access$300(DMCPlayEngine.this, new DMCPlayEngine._cls3._cls6());
        }

        public void onGetTransportSettings(int i, com.arcsoft.adk.atv.MRCPCallback.DataOnGetTransportSettings dataongettransportsettings, String s)
        {
        }

        public void onGetVolume(final int errorcode, final int volume, final String renderudn)
        {
            DMCPlayEngine.access$300(DMCPlayEngine.this, new DMCPlayEngine._cls3._cls4());
        }

        public void onMediaPause(final int errorcode, final String renderudn)
        {
            Log.w("DMCPlay", (new StringBuilder()).append("onMediaPause = ").append(errorcode).toString());
            DMCPlayEngine.access$300(DMCPlayEngine.this, new DMCPlayEngine._cls3._cls8());
        }

        public void onMediaPlay(final int errorcode, final String renderudn)
        {
            Log.w("DMCPlay", (new StringBuilder()).append("onMediaPlay = ").append(errorcode).toString());
            if (errorcode != 0)
            {
                DMCPlayEngine.access$1800(DMCPlayEngine.this, new DMCPlayEngine._cls3._cls9(), 500L);
            }
        }

        public void onMediaSeek(final int errorcode, final String renderudn)
        {
            DMCPlayEngine.access$1900(DMCPlayEngine.this).removeMessages(-8191);
            Log.v("DMCPlay", "onMediaSeek()");
            DMCPlayEngine.access$300(DMCPlayEngine.this, new DMCPlayEngine._cls3._cls10());
        }

        public void onMediaStop(final int err, final String renderudn)
        {
            Log.w("DMCPlay", (new StringBuilder()).append("onMediaStop = ").append(err).toString());
            DMCPlayEngine.access$300(DMCPlayEngine.this, new DMCPlayEngine._cls3._cls11());
        }

        public void onOpenMedia(final int errorcode, final String renderudn)
        {
            Log.w("DMCPlay", (new StringBuilder()).append("onOpenMedia = ").append(errorcode).toString());
            DMCPlayEngine.access$300(DMCPlayEngine.this, new DMCPlayEngine._cls3._cls12());
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

        // Unreferenced inner class com/arcsoft/mediaplus/playengine/DMCPlayEngine$3$1

/* anonymous class */
        class DMCPlayEngine._cls3._cls1
            implements Runnable
        {

            final DMCPlayEngine._cls3 this$1;
            final String val$allowedactions;
            final int val$errorcode;
            final String val$renderudn;

            public void run()
            {
                if (!DMCPlayEngine.access$600(this$0, DMCPlayEngine.ASyncMsgType.MSG_GETALLOWACTIONS, renderudn) || errorcode != 0)
                {
                    return;
                } else
                {
                    Log.w("DMCPlay", (new StringBuilder()).append(" Allowed actions: ").append(allowedactions).toString());
                    return;
                }
            }

                    
                    {
                        this$1 = DMCPlayEngine._cls3.this;
                        renderudn = s;
                        errorcode = i;
                        allowedactions = s1;
                        super();
                    }
        }


        // Unreferenced inner class com/arcsoft/mediaplus/playengine/DMCPlayEngine$3$10

/* anonymous class */
        class DMCPlayEngine._cls3._cls10
            implements Runnable
        {

            final DMCPlayEngine._cls3 this$1;
            final int val$errorcode;
            final String val$renderudn;

            public void run()
            {
                Log.v("DMCPlay", (new StringBuilder()).append("onMediaSeek() start, errorcode=").append(errorcode).append(",renderudn=").append(renderudn).toString());
                if (!DMCPlayEngine.access$1600(this$0, DMCPlayEngine.ASyncMsgType.MSG_SEEK, renderudn, null))
                {
                    Log.v("DMCPlay", "onMediaSeek() fail");
                    return;
                }
                DMCPlayEngineStatusMachine dmcplayenginestatusmachine = DMCPlayEngine.access$400(this$0);
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
                        this$1 = DMCPlayEngine._cls3.this;
                        errorcode = i;
                        renderudn = s;
                        super();
                    }
        }


        // Unreferenced inner class com/arcsoft/mediaplus/playengine/DMCPlayEngine$3$11

/* anonymous class */
        class DMCPlayEngine._cls3._cls11
            implements Runnable
        {

            final DMCPlayEngine._cls3 this$1;
            final int val$err;
            final String val$renderudn;

            public void run()
            {
                if (!DMCPlayEngine.access$1600(this$0, DMCPlayEngine.ASyncMsgType.MSG_STOP, renderudn, null))
                {
                    return;
                }
                if (err != 0 && mStopRetryCnt <= 2)
                {
                    DMCPlayEngine._cls3 _lcls3 = DMCPlayEngine._cls3.this;
                    _lcls3.mStopRetryCnt = 1 + _lcls3.mStopRetryCnt;
                    DMCPlayEngine.PlayEffect playeffect = new DMCPlayEngine.PlayEffect(this$0, null);
                    playeffect.effect = IDMCPlayEffect.EFFECT.EFFECT_NONE;
                    DMCPlayEngine.access$2100(this$0, playeffect);
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
                DMCPlayEngine.access$400(this$0).setTransformActionResult(DMCPlayEngineStatusMachine.TransformAction.STOP, true, new Integer(0));
            }

                    
                    {
                        this$1 = DMCPlayEngine._cls3.this;
                        renderudn = s;
                        err = i;
                        super();
                    }
        }


        // Unreferenced inner class com/arcsoft/mediaplus/playengine/DMCPlayEngine$3$12

/* anonymous class */
        class DMCPlayEngine._cls3._cls12
            implements Runnable
        {

            final DMCPlayEngine._cls3 this$1;
            final int val$errorcode;
            final String val$renderudn;

            public void run()
            {
                if (!DMCPlayEngine.access$1600(this$0, DMCPlayEngine.ASyncMsgType.MSG_OPEN, renderudn, null))
                {
                    return;
                }
                DMCPlayEngineStatusMachine dmcplayenginestatusmachine = DMCPlayEngine.access$400(this$0);
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
                        this$1 = DMCPlayEngine._cls3.this;
                        renderudn = s;
                        errorcode = i;
                        super();
                    }
        }


        // Unreferenced inner class com/arcsoft/mediaplus/playengine/DMCPlayEngine$3$2

/* anonymous class */
        class DMCPlayEngine._cls3._cls2
            implements Runnable
        {

            final DMCPlayEngine._cls3 this$1;
            final int val$errorcode;
            final com.arcsoft.adk.atv.MRCPCallback.DataOnGetMediaInfo val$info;
            final String val$renderudn;

            public void run()
            {
                while (!DMCPlayEngine.access$600(this$0, DMCPlayEngine.ASyncMsgType.MSG_GETMETADATA, renderudn) || errorcode != 0 || !DMCPlayEngine.access$700(this$0, info.m_strCurUri)) 
                {
                    return;
                }
                DMCPlayEngine.access$900(this$0, DMCPlayEngine.access$800(this$0).position, 1000L * TimeUtils.convertTimeStringToSec(info.m_strMediaDuration));
            }

                    
                    {
                        this$1 = DMCPlayEngine._cls3.this;
                        renderudn = s;
                        errorcode = i;
                        info = dataongetmediainfo;
                        super();
                    }
        }


        // Unreferenced inner class com/arcsoft/mediaplus/playengine/DMCPlayEngine$3$4

/* anonymous class */
        class DMCPlayEngine._cls3._cls4
            implements Runnable
        {

            final DMCPlayEngine._cls3 this$1;
            final int val$errorcode;
            final String val$renderudn;
            final int val$volume;

            public void run()
            {
                if (DMCPlayEngine.access$600(this$0, DMCPlayEngine.ASyncMsgType.MSG_GETVOLUME, renderudn) && errorcode == 0 && DMCPlayEngine.access$1100(this$0) != volume)
                {
                    DMCPlayEngine.access$1102(this$0, volume);
                    if (DMCPlayEngine.access$500(this$0) != null)
                    {
                        DMCPlayEngine.access$500(this$0).OnVolumeChanged(volume);
                        return;
                    }
                }
            }

                    
                    {
                        this$1 = DMCPlayEngine._cls3.this;
                        renderudn = s;
                        errorcode = i;
                        volume = j;
                        super();
                    }
        }


        // Unreferenced inner class com/arcsoft/mediaplus/playengine/DMCPlayEngine$3$5

/* anonymous class */
        class DMCPlayEngine._cls3._cls5
            implements Runnable
        {

            final DMCPlayEngine._cls3 this$1;
            final com.arcsoft.adk.atv.MRCPCallback.DataOnGetPositionInfo val$data;
            final int val$errorcode;
            final String val$renderudn;

            public void run()
            {
                while (!DMCPlayEngine.access$600(this$0, DMCPlayEngine.ASyncMsgType.MSG_GETPOSITION, renderudn) || errorcode != 0 || !DMCPlayEngine.access$700(this$0, data.m_strTrackUri)) 
                {
                    return;
                }
                long l = 1000L * TimeUtils.convertTimeStringToSec(data.m_strRelTime);
                long l1 = 1000L * TimeUtils.convertTimeStringToSec(data.m_strTrackDuration);
                DMCPlayEngine.access$900(this$0, l, l1);
            }

                    
                    {
                        this$1 = DMCPlayEngine._cls3.this;
                        renderudn = s;
                        errorcode = i;
                        data = dataongetpositioninfo;
                        super();
                    }
        }


        // Unreferenced inner class com/arcsoft/mediaplus/playengine/DMCPlayEngine$3$6

/* anonymous class */
        class DMCPlayEngine._cls3._cls6
            implements Runnable
        {

            final DMCPlayEngine._cls3 this$1;
            final int val$errorcode;
            final com.arcsoft.adk.atv.MRCPCallback.DataOnGetTransportInfo val$info;
            final String val$renderudn;

            public void run()
            {
                Log.v("DMCPlay", (new StringBuilder()).append("GetTransInfo Status = ").append(info.strCurrentTransportState).append(" CurStatus = ").append(DMCPlayEngine.access$400(this$0).getStatus()).toString());
                if (DMCPlayEngine.access$600(this$0, DMCPlayEngine.ASyncMsgType.MSG_GETACTION, renderudn))
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
                        this$1 = DMCPlayEngine._cls3.this;
                        info = dataongettransportinfo;
                        renderudn = s;
                        errorcode = i;
                        super();
                    }
        }


        // Unreferenced inner class com/arcsoft/mediaplus/playengine/DMCPlayEngine$3$7

/* anonymous class */
        class DMCPlayEngine._cls3._cls7
            implements Runnable
        {

            final DMCPlayEngine._cls3 this$1;
            final int val$errorcode;
            final String val$renderudn;

            public void run()
            {
                if (!DMCPlayEngine.access$1600(this$0, DMCPlayEngine.ASyncMsgType.MSG_PLAY, renderudn, null))
                {
                    return;
                }
                Log.w("DMCPlay", (new StringBuilder()).append("onOpenMedia (get playing status) = ").append(errorcode).toString());
                DMCPlayEngineStatusMachine dmcplayenginestatusmachine = DMCPlayEngine.access$400(this$0);
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
                        this$1 = DMCPlayEngine._cls3.this;
                        renderudn = s;
                        errorcode = i;
                        super();
                    }
        }


        // Unreferenced inner class com/arcsoft/mediaplus/playengine/DMCPlayEngine$3$8

/* anonymous class */
        class DMCPlayEngine._cls3._cls8
            implements Runnable
        {

            final DMCPlayEngine._cls3 this$1;
            final int val$errorcode;
            final String val$renderudn;

            public void run()
            {
                if (!DMCPlayEngine.access$1600(this$0, DMCPlayEngine.ASyncMsgType.MSG_PAUSE, renderudn, null))
                {
                    return;
                }
                DMCPlayEngineStatusMachine dmcplayenginestatusmachine = DMCPlayEngine.access$400(this$0);
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
                        this$1 = DMCPlayEngine._cls3.this;
                        renderudn = s;
                        errorcode = i;
                        super();
                    }
        }


        // Unreferenced inner class com/arcsoft/mediaplus/playengine/DMCPlayEngine$3$9

/* anonymous class */
        class DMCPlayEngine._cls3._cls9
            implements Runnable
        {

            final DMCPlayEngine._cls3 this$1;
            final int val$errorcode;
            final String val$renderudn;

            public void run()
            {
                if (!DMCPlayEngine.access$1600(this$0, DMCPlayEngine.ASyncMsgType.MSG_PLAY, renderudn, null))
                {
                    return;
                }
                DMCPlayEngineStatusMachine dmcplayenginestatusmachine = DMCPlayEngine.access$400(this$0);
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
                        this$1 = DMCPlayEngine._cls3.this;
                        renderudn = s;
                        errorcode = i;
                        super();
                    }
        }

    }

}
