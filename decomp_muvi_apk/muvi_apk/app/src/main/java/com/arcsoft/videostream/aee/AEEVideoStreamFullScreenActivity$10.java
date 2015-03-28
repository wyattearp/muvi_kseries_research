// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.arcsoft.videostream.aee;

import com.arcsoft.MediaPlayer.SecureMediaPlayer;
import com.arcsoft.util.debug.Log;
import java.io.IOException;

// Referenced classes of package com.arcsoft.videostream.aee:
//            AEEVideoStreamFullScreenActivity, AEESocketClient, AEEUtilDef

class this._cls0
    implements tener
{

    final AEEVideoStreamFullScreenActivity this$0;

    public void onRequestRespondsFinished(int i, String s, int j, String s1, int k, String s2)
    {
        Log.e("AEEVideoStreamFullScreenActivity", (new StringBuilder()).append("onRequestRespondsFinished respond = ").append(s).append(" num = ").append(j).append(" param = ").append(s1).append(" curCmdType = ").append(i).append(" paramSize = ").append(k).toString());
        if (j != 1 || s1 == null) goto _L2; else goto _L1
_L1:
        if (!s1.contains("16777217")) goto _L4; else goto _L3
_L3:
        Log.e("AEEVideoStreamFullScreenActivity", (new StringBuilder()).append(" mCurExecuteStatus = ").append(AEEVideoStreamFullScreenActivity.access$1800(AEEVideoStreamFullScreenActivity.this)).toString());
        if (AEEVideoStreamFullScreenActivity.access$1800(AEEVideoStreamFullScreenActivity.this) != 6) goto _L6; else goto _L5
_L5:
        AEEVideoStreamFullScreenActivity.access$2000(AEEVideoStreamFullScreenActivity.this, true, 6);
_L57:
        return;
_L6:
        if (AEEVideoStreamFullScreenActivity.access$1800(AEEVideoStreamFullScreenActivity.this) == 3)
        {
            if (AEEVideoStreamFullScreenActivity.access$1100(AEEVideoStreamFullScreenActivity.this))
            {
                AEEVideoStreamFullScreenActivity.access$2700(AEEVideoStreamFullScreenActivity.this);
                AEEVideoStreamFullScreenActivity.access$2000(AEEVideoStreamFullScreenActivity.this, false, 3);
                if (AEEVideoStreamFullScreenActivity.access$1200(AEEVideoStreamFullScreenActivity.this) == 18)
                {
                    switchTo(2, -1);
                    showSurfaceView(0);
                }
            }
            switchTo(1, 19);
            return;
        }
        continue; /* Loop/switch isn't completed */
_L4:
        AEEVideoStreamFullScreenActivity.access$3800(AEEVideoStreamFullScreenActivity.this, j, i, s1);
        return;
_L2:
        int l = -1;
        int i5;
        mSocketClient = AEESocketClient.getInstanceClient();
        i5 = mSocketClient.getNextCMD();
        l = i5;
_L44:
        i;
        JVM INSTR lookupswitch 38: default 572
    //                   -1: 136
    //                   268435457: 1924
    //                   268435458: 2455
    //                   268435459: 2976
    //                   268435460: 3139
    //                   268435461: 3335
    //                   268435462: 3397
    //                   268435463: 3397
    //                   268435464: 3607
    //                   268435465: 3486
    //                   268435466: 3486
    //                   268435474: 631
    //                   268435475: 1888
    //                   268435476: 3546
    //                   268435487: 836
    //                   268435488: 3885
    //                   268435489: 1088
    //                   268435490: 1004
    //                   268435491: 1451
    //                   268435492: 907
    //                   268435493: 2523
    //                   268435494: 3825
    //                   268435495: 3679
    //                   268435497: 3745
    //                   268435498: 3785
    //                   268435499: 2108
    //                   268435500: 2735
    //                   268435501: 3909
    //                   268435502: 3947
    //                   268435503: 3985
    //                   268435504: 4023
    //                   268435505: 4083
    //                   268435506: 4147
    //                   268435507: 2320
    //                   268435509: 1718
    //                   268435510: 1698
    //                   268435511: 1738
    //                   268435512: 1656;
           goto _L7 _L8 _L9 _L10 _L11 _L12 _L13 _L14 _L14 _L15 _L16 _L16 _L17 _L18 _L19 _L20 _L21 _L22 _L23 _L24 _L25 _L26 _L27 _L28 _L29 _L30 _L31 _L32 _L33 _L34 _L35 _L36 _L37 _L38 _L39 _L40 _L41 _L42 _L43
_L8:
        continue; /* Loop/switch isn't completed */
_L7:
        Log.e("AEEVideoStreamFullScreenActivity", "onRequestRespondsFinished default");
        if (j != 0)
        {
            Log.e("AEEVideoStreamFullScreenActivity", (new StringBuilder()).append("sendCommandFailed curCmdType = ").append(i).toString());
            AEEVideoStreamFullScreenActivity.access$3800(AEEVideoStreamFullScreenActivity.this, j, i, s1);
            return;
        }
        continue; /* Loop/switch isn't completed */
        IOException ioexception;
        ioexception;
        ioexception.printStackTrace();
          goto _L44
_L17:
        Log.e("AEEVideoStreamFullScreenActivity", "onRequestRespondsFinished PARAM_AEE_CMD_START_ENCODING");
        if (AEEVideoStreamFullScreenActivity.access$1800(AEEVideoStreamFullScreenActivity.this) != 8 && (!AEEVideoStreamFullScreenActivity.access$1100(AEEVideoStreamFullScreenActivity.this) || AEEVideoStreamFullScreenActivity.access$1200(AEEVideoStreamFullScreenActivity.this) != 18))
        {
            switchTo(2, -1);
            if (AEEVideoStreamFullScreenActivity.access$800(AEEVideoStreamFullScreenActivity.this) != null)
            {
                AEEVideoStreamFullScreenActivity.access$800(AEEVideoStreamFullScreenActivity.this).reset();
                mSocketClient.setIsFirstStartPreview(true);
                AEEVideoStreamFullScreenActivity.access$002(AEEVideoStreamFullScreenActivity.this, false);
            }
            if (!AEEVideoStreamFullScreenActivity.access$2500(AEEVideoStreamFullScreenActivity.this))
            {
                showSurfaceView(0);
            } else
            {
                AEEVideoStreamFullScreenActivity.access$2600(AEEVideoStreamFullScreenActivity.this, true);
            }
            AEEVideoStreamFullScreenActivity.access$3902(AEEVideoStreamFullScreenActivity.this, false);
        }
        if (AEEVideoStreamFullScreenActivity.access$1800(AEEVideoStreamFullScreenActivity.this) == 8)
        {
            switchTo(8, -1);
        }
        switch (l)
        {
        default:
            AEEVideoStreamFullScreenActivity.access$1900(AEEVideoStreamFullScreenActivity.this, l, -1, 0L);
            return;

        case 268435487: 
            AEEVideoStreamFullScreenActivity.access$1900(AEEVideoStreamFullScreenActivity.this, 0x1000001f, 0x10000027, 0L);
            return;

        case -1: 
            break;
        }
        continue; /* Loop/switch isn't completed */
_L20:
        if (j == -12)
        {
            mSocketClient.setHasConfig(false);
        } else
        {
            mSocketClient.setHasConfig(true);
        }
        if (l == 0x10000027 || -1 == l)
        {
            AEEVideoStreamFullScreenActivity.access$3400(AEEVideoStreamFullScreenActivity.this, 6, 0x10000027, -1, null, 0L);
            return;
        } else
        {
            AEEVideoStreamFullScreenActivity.access$1900(AEEVideoStreamFullScreenActivity.this, l, -1, 0L);
            return;
        }
_L25:
        Log.e("AEEVideoStreamFullScreenActivity", "onRequestRespondsFinished PARAM_AEE_CMD_GET_DV_SETTINGS_VIDEO_STAMP");
        if (s1 == null || s1.contains("off"))
        {
            if (AEEVideoStreamFullScreenActivity.access$1100(AEEVideoStreamFullScreenActivity.this))
            {
                AEEVideoStreamFullScreenActivity.access$1900(AEEVideoStreamFullScreenActivity.this, 0x10000022, -1, 0L);
                return;
            } else
            {
                AEEVideoStreamFullScreenActivity.access$1900(AEEVideoStreamFullScreenActivity.this, 0x10000021, -1, 0L);
                return;
            }
        }
        AEEVideoStreamFullScreenActivity aeevideostreamfullscreenactivity16 = AEEVideoStreamFullScreenActivity.this;
        int l4;
        if (!mSocketClient.getIsNeedEncoding())
        {
            l4 = 0x10000013;
        } else
        {
            l4 = -1;
        }
        AEEVideoStreamFullScreenActivity.access$1900(aeevideostreamfullscreenactivity16, l4, 0x10000038, 0L);
        return;
_L23:
        Log.e("AEEVideoStreamFullScreenActivity", "onRequestRespondsFinished PARAM_AEE_CMD_GET_DV_SETTINGS_STREAM_TYPE");
        if (s1 == null || s1.contains("rtsp"))
        {
            AEEVideoStreamFullScreenActivity.access$1900(AEEVideoStreamFullScreenActivity.this, 0x10000023, -1, 0L);
            return;
        }
        AEEVideoStreamFullScreenActivity.access$3902(AEEVideoStreamFullScreenActivity.this, true);
        AEEVideoStreamFullScreenActivity aeevideostreamfullscreenactivity15 = AEEVideoStreamFullScreenActivity.this;
        int k4;
        if (!mSocketClient.getIsNeedEncoding())
        {
            k4 = 0x10000013;
        } else
        {
            k4 = -1;
        }
        AEEVideoStreamFullScreenActivity.access$1900(aeevideostreamfullscreenactivity15, k4, 0x10000036, 0L);
        return;
_L22:
        Log.e("AEEVideoStreamFullScreenActivity", "onRequestRespondsFinished PARAM_AEE_CMD_GET_DV_SETTINGS_DUAL_STREAM");
        if (s1 == null || s1.contains("on"))
        {
            if (!AEEVideoStreamFullScreenActivity.access$1100(AEEVideoStreamFullScreenActivity.this))
            {
                AEEVideoStreamFullScreenActivity.access$1900(AEEVideoStreamFullScreenActivity.this, 0x10000022, -1, 0L);
                return;
            }
            AEEVideoStreamFullScreenActivity.access$1502(AEEVideoStreamFullScreenActivity.this, false);
            if (!AEEVideoStreamFullScreenActivity.access$1400(AEEVideoStreamFullScreenActivity.this))
            {
                if (mSocketClient.getIsFirstStartPreview())
                {
                    if (l != -1)
                    {
                        AEEVideoStreamFullScreenActivity.access$1900(AEEVideoStreamFullScreenActivity.this, 0x10000024, -1, 0L);
                    }
                } else
                {
                    AEEVideoStreamFullScreenActivity aeevideostreamfullscreenactivity11 = AEEVideoStreamFullScreenActivity.this;
                    int l3;
                    if (AEEVideoStreamFullScreenActivity.access$1200(AEEVideoStreamFullScreenActivity.this) == 19)
                    {
                        l3 = AEEVideoStreamFullScreenActivity.access$1200(AEEVideoStreamFullScreenActivity.this);
                    } else
                    {
                        l3 = -1;
                    }
                    aeevideostreamfullscreenactivity11.switchTo(2, l3);
                    if (!AEEVideoStreamFullScreenActivity.access$2500(AEEVideoStreamFullScreenActivity.this))
                    {
                        showSurfaceView(0);
                        AEEVideoStreamFullScreenActivity.access$3902(AEEVideoStreamFullScreenActivity.this, false);
                    } else
                    {
                        AEEVideoStreamFullScreenActivity.access$2600(AEEVideoStreamFullScreenActivity.this, true);
                    }
                }
            } else
            {
                switchTo(1, 20);
            }
            if (l != -1)
            {
                AEEVideoStreamFullScreenActivity aeevideostreamfullscreenactivity10 = AEEVideoStreamFullScreenActivity.this;
                int k3;
                if (l == 0x1000001f)
                {
                    k3 = 0x10000027;
                } else
                {
                    k3 = -1;
                }
                AEEVideoStreamFullScreenActivity.access$1900(aeevideostreamfullscreenactivity10, l, k3, 0L);
                return;
            }
        } else
        if (AEEVideoStreamFullScreenActivity.access$1100(AEEVideoStreamFullScreenActivity.this))
        {
            AEEVideoStreamFullScreenActivity.access$1502(AEEVideoStreamFullScreenActivity.this, true);
            AEEVideoStreamFullScreenActivity aeevideostreamfullscreenactivity13 = AEEVideoStreamFullScreenActivity.this;
            byte byte2;
            if (AEEVideoStreamFullScreenActivity.access$1400(AEEVideoStreamFullScreenActivity.this))
            {
                byte2 = 20;
            } else
            {
                byte2 = 18;
            }
            aeevideostreamfullscreenactivity13.switchTo(1, byte2);
            if (l != -1)
            {
                AEEVideoStreamFullScreenActivity aeevideostreamfullscreenactivity14 = AEEVideoStreamFullScreenActivity.this;
                int j4;
                if (l == 0x1000001f)
                {
                    j4 = 0x10000027;
                } else
                {
                    j4 = -1;
                }
                AEEVideoStreamFullScreenActivity.access$1900(aeevideostreamfullscreenactivity14, l, j4, 0L);
                return;
            }
        } else
        {
            AEEVideoStreamFullScreenActivity aeevideostreamfullscreenactivity12 = AEEVideoStreamFullScreenActivity.this;
            int i4;
            if (!mSocketClient.getIsNeedEncoding())
            {
                i4 = 0x10000013;
            } else
            {
                i4 = -1;
            }
            AEEVideoStreamFullScreenActivity.access$1900(aeevideostreamfullscreenactivity12, i4, 0x10000035, 0L);
            return;
        }
        continue; /* Loop/switch isn't completed */
_L24:
        Log.e("AEEVideoStreamFullScreenActivity", "onRequestRespondsFinished PARAM_AEE_CMD_GET_DV_SETTINGS_STREAMING");
        if (s1 == null || s1.contains("on"))
        {
            if (mSocketClient.getIsNeedEncoding())
            {
                AEEVideoStreamFullScreenActivity.access$1900(AEEVideoStreamFullScreenActivity.this, 0x10000012, 0x1000001f, 0L);
                return;
            }
            AEEVideoStreamFullScreenActivity aeevideostreamfullscreenactivity7 = AEEVideoStreamFullScreenActivity.this;
            int i3;
            if (AEEVideoStreamFullScreenActivity.access$1200(AEEVideoStreamFullScreenActivity.this) == 19)
            {
                i3 = AEEVideoStreamFullScreenActivity.access$1200(AEEVideoStreamFullScreenActivity.this);
            } else
            {
                i3 = -1;
            }
            aeevideostreamfullscreenactivity7.switchTo(2, i3);
            AEEVideoStreamFullScreenActivity.access$1900(AEEVideoStreamFullScreenActivity.this, 0x1000001f, 0x10000027, 0L);
            if (!AEEVideoStreamFullScreenActivity.access$2500(AEEVideoStreamFullScreenActivity.this))
            {
                AEEVideoStreamFullScreenActivity aeevideostreamfullscreenactivity8 = AEEVideoStreamFullScreenActivity.this;
                char c1;
                if (AEEVideoStreamFullScreenActivity.access$3900(AEEVideoStreamFullScreenActivity.this))
                {
                    c1 = '\u05DC';
                } else
                {
                    c1 = '\0';
                }
                aeevideostreamfullscreenactivity8.showSurfaceView(c1);
                AEEVideoStreamFullScreenActivity.access$3902(AEEVideoStreamFullScreenActivity.this, false);
                return;
            } else
            {
                AEEVideoStreamFullScreenActivity.access$2600(AEEVideoStreamFullScreenActivity.this, true);
                return;
            }
        }
        AEEVideoStreamFullScreenActivity aeevideostreamfullscreenactivity9 = AEEVideoStreamFullScreenActivity.this;
        int j3;
        if (!mSocketClient.getIsNeedEncoding())
        {
            j3 = 0x10000013;
        } else
        {
            j3 = -1;
        }
        AEEVideoStreamFullScreenActivity.access$1900(aeevideostreamfullscreenactivity9, j3, 0x10000037, 0L);
        return;
_L43:
        Log.e("AEEVideoStreamFullScreenActivity", "onRequestRespondsFinished PARAM_AEE_CMD_SET_DV_SETTINGS_VIDEO_STAMP");
        if (AEEVideoStreamFullScreenActivity.access$1100(AEEVideoStreamFullScreenActivity.this))
        {
            AEEVideoStreamFullScreenActivity.access$1900(AEEVideoStreamFullScreenActivity.this, 0x10000022, -1, 0L);
            return;
        } else
        {
            AEEVideoStreamFullScreenActivity.access$1900(AEEVideoStreamFullScreenActivity.this, 0x10000021, -1, 0L);
            return;
        }
_L41:
        Log.e("AEEVideoStreamFullScreenActivity", "onRequestRespondsFinished PARAM_AEE_CMD_SET_DV_SETTINGS_STREAM_TYPE");
        AEEVideoStreamFullScreenActivity.access$1900(AEEVideoStreamFullScreenActivity.this, 0x10000037, -1, 0L);
        return;
_L40:
        Log.e("AEEVideoStreamFullScreenActivity", "onRequestRespondsFinished PARAM_AEE_CMD_SET_DV_SETTINGS_DUAL_STREAM");
        AEEVideoStreamFullScreenActivity.access$1900(AEEVideoStreamFullScreenActivity.this, 0x10000022, -1, 0L);
        return;
_L42:
        Log.e("AEEVideoStreamFullScreenActivity", "onRequestRespondsFinished PARAM_AEE_CMD_SET_DV_SETTINGS_STREAMING");
        if (mSocketClient.getIsNeedEncoding())
        {
            AEEVideoStreamFullScreenActivity.access$1900(AEEVideoStreamFullScreenActivity.this, 0x10000012, 0x1000001f, 0L);
            return;
        }
        AEEVideoStreamFullScreenActivity aeevideostreamfullscreenactivity5 = AEEVideoStreamFullScreenActivity.this;
        int k2;
        if (AEEVideoStreamFullScreenActivity.access$1200(AEEVideoStreamFullScreenActivity.this) == 19)
        {
            k2 = AEEVideoStreamFullScreenActivity.access$1200(AEEVideoStreamFullScreenActivity.this);
        } else
        {
            k2 = -1;
        }
        aeevideostreamfullscreenactivity5.switchTo(2, k2);
        AEEVideoStreamFullScreenActivity.access$1900(AEEVideoStreamFullScreenActivity.this, 0x1000001f, 0x10000027, 0L);
        if (!AEEVideoStreamFullScreenActivity.access$2500(AEEVideoStreamFullScreenActivity.this))
        {
            AEEVideoStreamFullScreenActivity aeevideostreamfullscreenactivity6 = AEEVideoStreamFullScreenActivity.this;
            char c;
            if (AEEVideoStreamFullScreenActivity.access$3900(AEEVideoStreamFullScreenActivity.this))
            {
                c = '\u05DC';
            } else
            {
                c = '\0';
            }
            aeevideostreamfullscreenactivity6.showSurfaceView(c);
            AEEVideoStreamFullScreenActivity.access$3902(AEEVideoStreamFullScreenActivity.this, false);
            return;
        } else
        {
            AEEVideoStreamFullScreenActivity.access$2600(AEEVideoStreamFullScreenActivity.this, true);
            return;
        }
_L18:
        Log.e("AEEVideoStreamFullScreenActivity", "onRequestRespondsFinished PARAM_AEE_CMD_STOP_ENCODING");
        if (j != 0)
        {
            AEEVideoStreamFullScreenActivity.access$3800(AEEVideoStreamFullScreenActivity.this, j, i, s1);
            return;
        } else
        {
            AEEVideoStreamFullScreenActivity.access$1900(AEEVideoStreamFullScreenActivity.this, l, -1, 0L);
            return;
        }
_L9:
        Log.e("AEEVideoStreamFullScreenActivity", "onRequestRespondsFinished PARAM_AEE_CMD_SESSION_START");
        if (j != 0)
        {
            if (mSocketClient.getCurShareId(AEEVideoStreamFullScreenActivity.this) > 0)
            {
                AEEVideoStreamFullScreenActivity aeevideostreamfullscreenactivity4 = AEEVideoStreamFullScreenActivity.this;
                int j2;
                if (l == 0x1000002b)
                {
                    j2 = 0x10000033;
                } else
                {
                    j2 = -1;
                }
                AEEVideoStreamFullScreenActivity.access$1900(aeevideostreamfullscreenactivity4, l, j2, 0L);
                return;
            } else
            {
                switchTo(1, 13);
                return;
            }
        }
        if (j == 0 && s1 != null && s1.length() > 0)
        {
            try
            {
                int i2 = Integer.parseInt(s1);
                AEEUtilDef.setSharedTokenId(AEEVideoStreamFullScreenActivity.this, i2);
            }
            catch (Exception exception1)
            {
                exception1.printStackTrace();
            }
        }
        switch (l)
        {
        default:
            AEEVideoStreamFullScreenActivity.access$1900(AEEVideoStreamFullScreenActivity.this, l, -1, 0L);
            return;

        case 268435499: 
            AEEVideoStreamFullScreenActivity.access$1900(AEEVideoStreamFullScreenActivity.this, l, 0x10000033, 0L);
            return;

        case 268435459: 
            AEEVideoStreamFullScreenActivity.access$1900(AEEVideoStreamFullScreenActivity.this, l, 0x10000021, 0L);
            return;
        }
_L31:
        byte byte0 = -1;
        j;
        JVM INSTR lookupswitch 2: default 2140
    //                   -4: 2280
    //                   0: 2226;
           goto _L45 _L46 _L47
_L45:
        if (AEEVideoStreamFullScreenActivity.access$1800(AEEVideoStreamFullScreenActivity.this) == -1)
        {
            AEEVideoStreamFullScreenActivity aeevideostreamfullscreenactivity3 = AEEVideoStreamFullScreenActivity.this;
            byte byte1;
            int k1;
            if (byte0 == -1)
            {
                byte1 = 3;
            } else
            {
                byte1 = byte0;
            }
            if (AEEVideoStreamFullScreenActivity.access$1100(AEEVideoStreamFullScreenActivity.this))
            {
                k1 = 1;
            } else
            {
                k1 = 0;
            }
            AEEVideoStreamFullScreenActivity.access$3400(aeevideostreamfullscreenactivity3, 14, byte1, k1, null, 0L);
        }
        switch (l)
        {
        default:
            return;

        case 268435507: 
            AEEVideoStreamFullScreenActivity.access$1900(AEEVideoStreamFullScreenActivity.this, l, 0x10000025, 0L);
            break;
        }
        return;
_L47:
        if (k > 0)
        {
            if (s1.contains("VIDEO"))
            {
                byte0 = 3;
            } else
            if (s1.contains("PHOTO"))
            {
                byte0 = 4;
            } else
            if (s1.contains("VOICE"))
            {
                byte0 = 8;
            }
        }
          goto _L45
_L46:
        mSocketClient.releaseCurShareId(AEEVideoStreamFullScreenActivity.this);
        AEEVideoStreamFullScreenActivity.access$1900(AEEVideoStreamFullScreenActivity.this, 0x10000001, 0x1000002b, 0L);
        return;
_L39:
        if (s1 != null && s1.contains("video_flip_rotate_on_"))
        {
            if (!mSocketClient.getIsRolloverOn())
            {
                mSocketClient.setIsRolloverOn(true);
            }
        } else
        if (s1 != null && s1.contains("video_flip_rotate_off") && mSocketClient.getIsRolloverOn())
        {
            mSocketClient.setIsRolloverOn(false);
        }
        switch (l)
        {
        default:
            AEEVideoStreamFullScreenActivity.access$1900(AEEVideoStreamFullScreenActivity.this, l, -1, 0L);
            return;

        case 268435493: 
            AEEVideoStreamFullScreenActivity.access$1900(AEEVideoStreamFullScreenActivity.this, l, 0x10000024, 0L);
            return;

        case -1: 
            break;
        }
        continue; /* Loop/switch isn't completed */
_L10:
        try
        {
            mSocketClient = AEESocketClient.getInstanceClient();
            mSocketClient.stopRespondParams();
            mSocketClient.setOnRequestRespondsListener(null);
            mSocketClient.releaseCurTokenId();
            mSocketClient.close();
            mSocketClient = null;
            return;
        }
        catch (IOException ioexception1)
        {
            ioexception1.printStackTrace();
        }
        return;
_L26:
        Log.e("AEEVideoStreamFullScreenActivity", "onRequestRespondsFinished PARAM_AEE_CMD_GET_DV_SETTINGS_APP_STATUS");
        if (s1 != null && s1.contains("record"))
        {
            if (AEEVideoStreamFullScreenActivity.access$1800(AEEVideoStreamFullScreenActivity.this) != 3)
            {
                AEEVideoStreamFullScreenActivity aeevideostreamfullscreenactivity2 = AEEVideoStreamFullScreenActivity.this;
                int j1;
                if (AEEVideoStreamFullScreenActivity.access$1100(AEEVideoStreamFullScreenActivity.this))
                {
                    j1 = 1;
                } else
                {
                    j1 = 0;
                }
                AEEVideoStreamFullScreenActivity.access$3400(aeevideostreamfullscreenactivity2, 14, 3, j1, null, 0L);
            }
            mSocketClient.setIsNeedEncoding(false);
            AEEVideoStreamFullScreenActivity.access$1900(AEEVideoStreamFullScreenActivity.this, 0x1000002c, 0x10000021, 0L);
            return;
        }
        if (s1 != null && s1.contains("idle"))
        {
            mSocketClient.setIsNeedEncoding(true);
            AEEVideoStreamFullScreenActivity aeevideostreamfullscreenactivity1 = AEEVideoStreamFullScreenActivity.this;
            long l2;
            if (AEEVideoStreamFullScreenActivity.access$3900(AEEVideoStreamFullScreenActivity.this))
            {
                l2 = 1500L;
            } else
            {
                l2 = 0L;
            }
            AEEVideoStreamFullScreenActivity.access$1900(aeevideostreamfullscreenactivity1, 0x10000024, -1, l2);
            return;
        }
        mSocketClient.setIsNeedEncoding(false);
        AEEVideoStreamFullScreenActivity aeevideostreamfullscreenactivity = AEEVideoStreamFullScreenActivity.this;
        long l1;
        if (AEEVideoStreamFullScreenActivity.access$3900(AEEVideoStreamFullScreenActivity.this))
        {
            l1 = 1500L;
        } else
        {
            l1 = 0L;
        }
        AEEVideoStreamFullScreenActivity.access$1900(aeevideostreamfullscreenactivity, 0x10000024, -1, l1);
        return;
_L32:
        int i1;
        String as[];
        if (AEEVideoStreamFullScreenActivity.access$1100(AEEVideoStreamFullScreenActivity.this))
        {
            i1 = -1;
        } else
        {
            i1 = 0x1000001f;
        }
        if (s1 == null || j != 0) goto _L49; else goto _L48
_L48:
        as = s1.split(":");
        if (as.length != 1) goto _L51; else goto _L50
_L50:
        AEEVideoStreamFullScreenActivity.access$2902(AEEVideoStreamFullScreenActivity.this, Integer.parseInt(as[0]));
_L54:
        if (!AEEVideoStreamFullScreenActivity.access$1100(AEEVideoStreamFullScreenActivity.this))
        {
            AEEVideoStreamFullScreenActivity.access$4000(AEEVideoStreamFullScreenActivity.this);
            AEEVideoStreamFullScreenActivity.access$2000(AEEVideoStreamFullScreenActivity.this, true, AEEVideoStreamFullScreenActivity.access$1800(AEEVideoStreamFullScreenActivity.this));
        }
        Log.e("AEEVideoStreamFullScreenActivity", (new StringBuilder()).append("PARAM_AEE_CMD_GET_DV_RECORD_TIME mTimeCountor = ").append(AEEVideoStreamFullScreenActivity.access$2900(AEEVideoStreamFullScreenActivity.this)).toString());
_L49:
        AEEVideoStreamFullScreenActivity.access$1102(AEEVideoStreamFullScreenActivity.this, true);
        AEEVideoStreamFullScreenActivity.access$1900(AEEVideoStreamFullScreenActivity.this, l, i1, 0L);
        return;
_L51:
        if (as.length != 2) goto _L53; else goto _L52
_L52:
        AEEVideoStreamFullScreenActivity.access$2902(AEEVideoStreamFullScreenActivity.this, 60 * Integer.parseInt(as[0]) + Integer.parseInt(as[1]));
          goto _L54
        Exception exception;
        exception;
        exception.printStackTrace();
          goto _L54
_L53:
        if (as.length != 3) goto _L54; else goto _L55
_L55:
        AEEVideoStreamFullScreenActivity.access$2902(AEEVideoStreamFullScreenActivity.this, 3600 * Integer.parseInt(as[0]) + 60 * Integer.parseInt(as[1]) + Integer.parseInt(as[2]));
          goto _L54
_L11:
        Log.e("AEEVideoStreamFullScreenActivity", "onRequestRespondsFinished PARAM_AEE_CMD_RECORD_START");
        AEEVideoStreamFullScreenActivity.access$1602(AEEVideoStreamFullScreenActivity.this, false);
        switch (j)
        {
        default:
            AEEVideoStreamFullScreenActivity.access$2000(AEEVideoStreamFullScreenActivity.this, false, 3);
            return;

        case 0: // '\0'
            AEEVideoStreamFullScreenActivity.access$4000(AEEVideoStreamFullScreenActivity.this);
            AEEVideoStreamFullScreenActivity.access$2000(AEEVideoStreamFullScreenActivity.this, true, 3);
            if (l > 0)
            {
                AEEVideoStreamFullScreenActivity.access$1900(AEEVideoStreamFullScreenActivity.this, l, -1, 0L);
                return;
            }
            break;

        case -19: 
        case -17: 
        case 1: // '\001'
            if (AEEVideoStreamFullScreenActivity.access$1100(AEEVideoStreamFullScreenActivity.this))
            {
                AEEVideoStreamFullScreenActivity.access$2700(AEEVideoStreamFullScreenActivity.this);
                AEEVideoStreamFullScreenActivity.access$2000(AEEVideoStreamFullScreenActivity.this, false, 3);
            }
            AEEVideoStreamFullScreenActivity.access$3800(AEEVideoStreamFullScreenActivity.this, j, i, s1);
            return;

        case -4: 
            AEEVideoStreamFullScreenActivity.access$1900(AEEVideoStreamFullScreenActivity.this, 0x10000001, 0x10000003, 0L);
            return;
        }
        continue; /* Loop/switch isn't completed */
_L12:
        Log.e("AEEVideoStreamFullScreenActivity", "onRequestRespondsFinished PARAM_AEE_CMD_RECORD_STOP");
        AEEVideoStreamFullScreenActivity.access$1602(AEEVideoStreamFullScreenActivity.this, false);
        AEEVideoStreamFullScreenActivity.access$1502(AEEVideoStreamFullScreenActivity.this, false);
        if (j == 0)
        {
            AEEVideoStreamFullScreenActivity.access$2700(AEEVideoStreamFullScreenActivity.this);
            AEEVideoStreamFullScreenActivity.access$2000(AEEVideoStreamFullScreenActivity.this, false, AEEVideoStreamFullScreenActivity.access$1800(AEEVideoStreamFullScreenActivity.this));
            if (AEEVideoStreamFullScreenActivity.access$1800(AEEVideoStreamFullScreenActivity.this) == 8)
            {
                switchTo(8, -1);
            } else
            if (AEEVideoStreamFullScreenActivity.access$1400(AEEVideoStreamFullScreenActivity.this))
            {
                switchTo(1, 20);
            } else
            {
                showSurfaceView(0);
                switchTo(2, -1);
            }
        } else
        if (j == -4)
        {
            AEEVideoStreamFullScreenActivity.access$1900(AEEVideoStreamFullScreenActivity.this, 0x10000001, 0x10000004, 0L);
        } else
        {
            AEEVideoStreamFullScreenActivity.access$1102(AEEVideoStreamFullScreenActivity.this, true);
            AEEVideoStreamFullScreenActivity.access$3800(AEEVideoStreamFullScreenActivity.this, j, i, s1);
        }
        if (l != -1)
        {
            AEEVideoStreamFullScreenActivity.access$1900(AEEVideoStreamFullScreenActivity.this, 0x10000026, l, 0L);
            return;
        } else
        {
            AEEVideoStreamFullScreenActivity.access$1900(AEEVideoStreamFullScreenActivity.this, 0x10000026, -1, 0L);
            return;
        }
_L13:
        AEEVideoStreamFullScreenActivity.access$1602(AEEVideoStreamFullScreenActivity.this, false);
        if (j == 0)
        {
            AEEVideoStreamFullScreenActivity.access$4000(AEEVideoStreamFullScreenActivity.this);
            switchTo(8, -1);
            AEEVideoStreamFullScreenActivity.access$2000(AEEVideoStreamFullScreenActivity.this, false, 8);
            return;
        } else
        {
            AEEVideoStreamFullScreenActivity.access$1102(AEEVideoStreamFullScreenActivity.this, false);
            AEEVideoStreamFullScreenActivity.access$3800(AEEVideoStreamFullScreenActivity.this, j, i, s1);
            return;
        }
_L14:
        AEEVideoStreamFullScreenActivity.access$1602(AEEVideoStreamFullScreenActivity.this, false);
        AEEVideoStreamFullScreenActivity.access$2000(AEEVideoStreamFullScreenActivity.this, false, AEEVideoStreamFullScreenActivity.access$1800(AEEVideoStreamFullScreenActivity.this));
        if (AEEVideoStreamFullScreenActivity.access$4100(AEEVideoStreamFullScreenActivity.this) && mSocketClient != null)
        {
            mSocketClient.setIsExcuting(AEEVideoStreamFullScreenActivity.access$1600(AEEVideoStreamFullScreenActivity.this));
        }
        if (j != 0)
        {
            AEEVideoStreamFullScreenActivity.access$3800(AEEVideoStreamFullScreenActivity.this, j, i, s1);
        }
        AEEVideoStreamFullScreenActivity.access$1900(AEEVideoStreamFullScreenActivity.this, 0x10000026, -1, 0L);
        return;
_L16:
        AEEVideoStreamFullScreenActivity.access$1602(AEEVideoStreamFullScreenActivity.this, false);
        AEEVideoStreamFullScreenActivity.access$2000(AEEVideoStreamFullScreenActivity.this, false, AEEVideoStreamFullScreenActivity.access$1800(AEEVideoStreamFullScreenActivity.this));
        if (AEEVideoStreamFullScreenActivity.access$1800(AEEVideoStreamFullScreenActivity.this) != 8)
        {
            updateSurfaceView(0);
        }
        if (j != 0)
        {
            AEEVideoStreamFullScreenActivity.access$3800(AEEVideoStreamFullScreenActivity.this, j, i, s1);
            return;
        }
        continue; /* Loop/switch isn't completed */
_L19:
        AEEVideoStreamFullScreenActivity.access$1602(AEEVideoStreamFullScreenActivity.this, false);
        if (j == 0)
        {
            AEEVideoStreamFullScreenActivity.access$2000(AEEVideoStreamFullScreenActivity.this, true, 6);
            return;
        }
        if (j == 1)
        {
            AEEVideoStreamFullScreenActivity.access$2000(AEEVideoStreamFullScreenActivity.this, true, 6);
            return;
        } else
        {
            AEEVideoStreamFullScreenActivity.access$1102(AEEVideoStreamFullScreenActivity.this, false);
            AEEVideoStreamFullScreenActivity.access$3800(AEEVideoStreamFullScreenActivity.this, j, i, s1);
            return;
        }
_L15:
        AEEVideoStreamFullScreenActivity.access$1602(AEEVideoStreamFullScreenActivity.this, false);
        if (j == 0)
        {
            AEEVideoStreamFullScreenActivity.access$2000(AEEVideoStreamFullScreenActivity.this, false, 6);
            updateSurfaceView(0);
        } else
        if (j != 1)
        {
            AEEVideoStreamFullScreenActivity.access$1102(AEEVideoStreamFullScreenActivity.this, true);
            AEEVideoStreamFullScreenActivity.access$3800(AEEVideoStreamFullScreenActivity.this, j, i, s1);
        }
        AEEVideoStreamFullScreenActivity.access$1900(AEEVideoStreamFullScreenActivity.this, 0x10000026, -1, 0L);
        return;
_L28:
        Log.e("AEEVideoStreamFullScreenActivity", (new StringBuilder()).append("PARAM_AEE_CMD_GET_DV_SETTINGS_DV_INFO param = ").append(s1).toString());
        if (j == 0)
        {
            AEEVideoStreamFullScreenActivity.access$3400(AEEVideoStreamFullScreenActivity.this, 11, 0x10000027, -1, s1, 0L);
        }
        if (l >= 0)
        {
            AEEVideoStreamFullScreenActivity.access$3400(AEEVideoStreamFullScreenActivity.this, 6, l, -1, null, 0L);
            return;
        }
        continue; /* Loop/switch isn't completed */
_L29:
        if (j == 0)
        {
            AEEVideoStreamFullScreenActivity.access$3400(AEEVideoStreamFullScreenActivity.this, 11, 0x10000029, -1, s1, 0L);
        }
        if (l >= 0)
        {
            AEEVideoStreamFullScreenActivity.access$3400(AEEVideoStreamFullScreenActivity.this, 6, l, -1, null, 0L);
            return;
        }
        continue; /* Loop/switch isn't completed */
_L30:
        if (j == 0)
        {
            AEEVideoStreamFullScreenActivity.access$3400(AEEVideoStreamFullScreenActivity.this, 11, 0x1000002a, -1, s1, 0L);
        }
        if (l >= 0)
        {
            AEEVideoStreamFullScreenActivity.access$3400(AEEVideoStreamFullScreenActivity.this, 6, l, -1, null, 0L);
            return;
        }
        continue; /* Loop/switch isn't completed */
_L27:
        if (j == 0)
        {
            AEEVideoStreamFullScreenActivity.access$3400(AEEVideoStreamFullScreenActivity.this, 11, 0x10000026, -1, s1, 0L);
        }
        if (l == 0x1000002d)
        {
            AEEVideoStreamFullScreenActivity.access$3400(AEEVideoStreamFullScreenActivity.this, 6, l, -1, null, 0L);
            return;
        }
        if (l > 0)
        {
            AEEVideoStreamFullScreenActivity.access$1900(AEEVideoStreamFullScreenActivity.this, l, -1, 0L);
            return;
        }
        continue; /* Loop/switch isn't completed */
_L21:
        if (j != 0);
        if (l >= 0)
        {
            AEEVideoStreamFullScreenActivity.access$3400(AEEVideoStreamFullScreenActivity.this, 6, l, -1, null, 0L);
            return;
        }
        continue; /* Loop/switch isn't completed */
_L33:
        if (j == 0)
        {
            mSocketClient.setDVInfo(12, s1);
        }
        if (l >= 0)
        {
            AEEVideoStreamFullScreenActivity.access$3400(AEEVideoStreamFullScreenActivity.this, 6, l, -1, null, 0L);
            return;
        }
        continue; /* Loop/switch isn't completed */
_L34:
        if (j == 0)
        {
            mSocketClient.setDVInfo(14, s1);
        }
        if (l >= 0)
        {
            AEEVideoStreamFullScreenActivity.access$3400(AEEVideoStreamFullScreenActivity.this, 6, l, -1, null, 0L);
            return;
        }
        continue; /* Loop/switch isn't completed */
_L35:
        if (j == 0)
        {
            mSocketClient.setDVInfo(15, s1);
        }
        if (l >= 0)
        {
            AEEVideoStreamFullScreenActivity.access$3400(AEEVideoStreamFullScreenActivity.this, 6, l, -1, null, 0L);
            return;
        }
        continue; /* Loop/switch isn't completed */
_L36:
        if (j == 0)
        {
            mSocketClient.setDVInfo(16, s1);
        } else
        if (j == -15)
        {
            mSocketClient.setDVInfo(16, null);
        }
        if (l >= 0)
        {
            AEEVideoStreamFullScreenActivity.access$3400(AEEVideoStreamFullScreenActivity.this, 6, l, -1, null, 0L);
            return;
        }
        continue; /* Loop/switch isn't completed */
_L37:
        if (j == 0)
        {
            mSocketClient.setDVInfo(17, s1);
        }
        if (AEEVideoStreamFullScreenActivity.access$1200(AEEVideoStreamFullScreenActivity.this) == 19)
        {
            AEEVideoStreamFullScreenActivity.access$1202(AEEVideoStreamFullScreenActivity.this, -1);
            AEEVideoStreamFullScreenActivity.access$3400(AEEVideoStreamFullScreenActivity.this, 13, 0x7f0b015d, -1, null, 0L);
        }
        AEEVideoStreamFullScreenActivity.access$1702(AEEVideoStreamFullScreenActivity.this, false);
        return;
_L38:
        if (j == 0)
        {
            mSocketClient.setDVInfo(13, s1);
        }
        if (l >= 0)
        {
            AEEVideoStreamFullScreenActivity.access$3400(AEEVideoStreamFullScreenActivity.this, 6, l, -1, null, 0L);
            return;
        }
        if (true) goto _L57; else goto _L56
_L56:
    }

    tener()
    {
        this$0 = AEEVideoStreamFullScreenActivity.this;
        super();
    }
}
