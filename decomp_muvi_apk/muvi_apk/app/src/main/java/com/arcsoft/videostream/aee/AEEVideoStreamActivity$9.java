// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.arcsoft.videostream.aee;

import com.arcsoft.MediaPlayer.SecureMediaPlayer;
import com.arcsoft.util.debug.Log;
import java.io.IOException;

// Referenced classes of package com.arcsoft.videostream.aee:
//            AEEVideoStreamActivity, AEESocketClient, AEEUtilDef

class this._cls0
    implements RespondsListener
{

    final AEEVideoStreamActivity this$0;

    public void onRequestRespondsFinished(int i, String s, int j, String s1, int k, String s2)
    {
        Log.e("AEEVideoStreamActivity", (new StringBuilder()).append("onRequestRespondsFinished respond = ").append(s).append(" num = ").append(j).append(" param = ").append(s1).append(" curCmdType = ").append(i).append(" paramSize = ").append(k).toString());
        if (j != 1 || s1 == null) goto _L2; else goto _L1
_L1:
        if (!s1.contains("16777217")) goto _L4; else goto _L3
_L3:
        if (AEEVideoStreamActivity.access$1400(AEEVideoStreamActivity.this) != 6) goto _L6; else goto _L5
_L5:
        AEEVideoStreamActivity.access$2900(AEEVideoStreamActivity.this, true, true);
_L64:
        return;
_L6:
        if (AEEVideoStreamActivity.access$1400(AEEVideoStreamActivity.this) == 3)
        {
            AEEVideoStreamActivity.access$2800(AEEVideoStreamActivity.this);
            AEEVideoStreamActivity.access$2900(AEEVideoStreamActivity.this, false, true);
            if (AEEVideoStreamActivity.access$1000(AEEVideoStreamActivity.this) == 18)
            {
                switchTo(2, -1);
                showSurfaceView(0);
            }
            switchTo(1, 19);
            return;
        }
        continue; /* Loop/switch isn't completed */
_L4:
        AEEVideoStreamActivity.access$3900(AEEVideoStreamActivity.this, j, i, s1);
        return;
_L2:
        int l = -1;
        int l4;
        mSocketClient = AEESocketClient.getInstanceClient();
        l4 = mSocketClient.getNextCMD();
        l = l4;
_L51:
        i;
        JVM INSTR lookupswitch 45: default 584
    //                   -1: 104
    //                   268435457: 2106
    //                   268435458: 2288
    //                   268435459: 2817
    //                   268435460: 2984
    //                   268435461: 3176
    //                   268435462: 3237
    //                   268435463: 3237
    //                   268435464: 3794
    //                   268435465: 3274
    //                   268435466: 3274
    //                   268435467: 4341
    //                   268435468: 4442
    //                   268435469: 3533
    //                   268435470: 3609
    //                   268435473: 3856
    //                   268435474: 643
    //                   268435475: 2042
    //                   268435476: 3685
    //                   268435477: 4534
    //                   268435478: 3867
    //                   268435487: 942
    //                   268435488: 4873
    //                   268435489: 1193
    //                   268435490: 1109
    //                   268435491: 1605
    //                   268435492: 1012
    //                   268435493: 2364
    //                   268435494: 4801
    //                   268435495: 4641
    //                   268435496: 4719
    //                   268435498: 4760
    //                   268435499: 3886
    //                   268435500: 2551
    //                   268435501: 4899
    //                   268435502: 4939
    //                   268435503: 4979
    //                   268435504: 5019
    //                   268435505: 5081
    //                   268435506: 5144
    //                   268435507: 3299
    //                   268435509: 1872
    //                   268435510: 1852
    //                   268435511: 1892
    //                   268435512: 1810;
           goto _L7 _L8 _L9 _L10 _L11 _L12 _L13 _L14 _L14 _L15 _L16 _L16 _L17 _L18 _L19 _L20 _L21 _L22 _L23 _L24 _L25 _L26 _L27 _L28 _L29 _L30 _L31 _L32 _L33 _L34 _L35 _L36 _L37 _L38 _L39 _L40 _L41 _L42 _L43 _L44 _L45 _L46 _L47 _L48 _L49 _L50
_L8:
        continue; /* Loop/switch isn't completed */
_L7:
        Log.e("AEEVideoStreamActivity", "onRequestRespondsFinished default");
        if (j != 0)
        {
            Log.e("AEEVideoStreamActivity", (new StringBuilder()).append("sendCommandFailed curCmdType = ").append(i).toString());
            AEEVideoStreamActivity.access$3900(AEEVideoStreamActivity.this, j, i, s1);
            return;
        }
        continue; /* Loop/switch isn't completed */
        IOException ioexception;
        ioexception;
        ioexception.printStackTrace();
          goto _L51
_L22:
        Log.e("AEEVideoStreamActivity", (new StringBuilder()).append("onRequestRespondsFinished PARAM_AEE_CMD_START_ENCODING nextCmd = ").append(l).toString());
        if (AEEVideoStreamActivity.access$4000(AEEVideoStreamActivity.this) != 8 && (!AEEVideoStreamActivity.access$900(AEEVideoStreamActivity.this) || AEEVideoStreamActivity.access$1000(AEEVideoStreamActivity.this) != 18))
        {
            if (AEEVideoStreamActivity.access$1200(AEEVideoStreamActivity.this))
            {
                switchTo(1, 20);
            } else
            {
                AEEVideoStreamActivity aeevideostreamactivity16 = AEEVideoStreamActivity.this;
                int k4;
                if (AEEVideoStreamActivity.access$1000(AEEVideoStreamActivity.this) == 19)
                {
                    k4 = AEEVideoStreamActivity.access$1000(AEEVideoStreamActivity.this);
                } else
                {
                    k4 = -1;
                }
                aeevideostreamactivity16.switchTo(2, k4);
                if (AEEVideoStreamActivity.access$000(AEEVideoStreamActivity.this) != null)
                {
                    AEEVideoStreamActivity.access$000(AEEVideoStreamActivity.this).reset();
                    mSocketClient.setIsFirstStartPreview(true);
                    AEEVideoStreamActivity.access$502(AEEVideoStreamActivity.this, false);
                    AEEVideoStreamActivity.access$202(AEEVideoStreamActivity.this, false);
                }
                if (!AEEVideoStreamActivity.access$2600(AEEVideoStreamActivity.this))
                {
                    AEEVideoStreamActivity aeevideostreamactivity17 = AEEVideoStreamActivity.this;
                    char c2;
                    if (AEEVideoStreamActivity.access$4100(AEEVideoStreamActivity.this))
                    {
                        c2 = '\u05DC';
                    } else
                    {
                        c2 = '\0';
                    }
                    aeevideostreamactivity17.showSurfaceView(c2);
                } else
                {
                    AEEVideoStreamActivity.access$2700(AEEVideoStreamActivity.this, true);
                }
                AEEVideoStreamActivity.access$4102(AEEVideoStreamActivity.this, false);
            }
        }
        AEEVideoStreamActivity.access$1502(AEEVideoStreamActivity.this, false);
        switch (l)
        {
        default:
            AEEVideoStreamActivity.access$1700(AEEVideoStreamActivity.this, l, -1, 0L);
            return;

        case 268435487: 
            AEEVideoStreamActivity.access$1700(AEEVideoStreamActivity.this, 0x1000001f, 0x10000027, 0L);
            return;

        case -1: 
            break;
        }
        continue; /* Loop/switch isn't completed */
_L27:
        if (j == -12)
        {
            mSocketClient.setHasConfig(false);
        } else
        {
            mSocketClient.setHasConfig(true);
        }
        if (l == 0x10000027 || -1 == l)
        {
            AEEVideoStreamActivity.access$4200(AEEVideoStreamActivity.this, 11, 0x10000027, -1, null);
            return;
        } else
        {
            AEEVideoStreamActivity.access$1700(AEEVideoStreamActivity.this, l, -1, 0L);
            return;
        }
_L32:
        Log.e("AEEVideoStreamActivity", "onRequestRespondsFinished PARAM_AEE_CMD_GET_DV_SETTINGS_VIDEO_STAMP");
        if (s1 == null || s1.contains("off"))
        {
            if (AEEVideoStreamActivity.access$900(AEEVideoStreamActivity.this))
            {
                AEEVideoStreamActivity.access$1700(AEEVideoStreamActivity.this, 0x10000022, -1, 0L);
                return;
            } else
            {
                AEEVideoStreamActivity.access$1700(AEEVideoStreamActivity.this, 0x10000021, -1, 0L);
                return;
            }
        }
        AEEVideoStreamActivity aeevideostreamactivity15 = AEEVideoStreamActivity.this;
        int j4;
        if (!mSocketClient.getIsNeedEncoding())
        {
            j4 = 0x10000013;
        } else
        {
            j4 = -1;
        }
        AEEVideoStreamActivity.access$1700(aeevideostreamactivity15, j4, 0x10000038, 0L);
        return;
_L30:
        Log.e("AEEVideoStreamActivity", "onRequestRespondsFinished PARAM_AEE_CMD_GET_DV_SETTINGS_STREAM_TYPE");
        if (s1 == null || s1.contains("rtsp"))
        {
            AEEVideoStreamActivity.access$1700(AEEVideoStreamActivity.this, 0x10000023, -1, 0L);
            return;
        }
        AEEVideoStreamActivity.access$4102(AEEVideoStreamActivity.this, true);
        AEEVideoStreamActivity aeevideostreamactivity14 = AEEVideoStreamActivity.this;
        int i4;
        if (!mSocketClient.getIsNeedEncoding())
        {
            i4 = 0x10000013;
        } else
        {
            i4 = -1;
        }
        AEEVideoStreamActivity.access$1700(aeevideostreamactivity14, i4, 0x10000036, 0L);
        return;
_L29:
        Log.e("AEEVideoStreamActivity", (new StringBuilder()).append("PARAM_AEE_CMD_GET_DV_SETTINGS_DUAL_STREAM mSocketClient.getIsFirstStartPreview() = ").append(mSocketClient.getIsFirstStartPreview()).toString());
        Log.e("AEEVideoStreamActivity", (new StringBuilder()).append("PARAM_AEE_CMD_GET_DV_SETTINGS_DUAL_STREAM isRecting = ").append(AEEVideoStreamActivity.access$900(AEEVideoStreamActivity.this)).toString());
        if (s1 == null || s1.contains("on"))
        {
            if (!AEEVideoStreamActivity.access$900(AEEVideoStreamActivity.this))
            {
                AEEVideoStreamActivity.access$1700(AEEVideoStreamActivity.this, 0x10000022, -1, 0L);
                return;
            }
            AEEVideoStreamActivity.access$1302(AEEVideoStreamActivity.this, false);
            if (!AEEVideoStreamActivity.access$1200(AEEVideoStreamActivity.this))
            {
                if (mSocketClient.getIsFirstStartPreview())
                {
                    AEEVideoStreamActivity.access$1700(AEEVideoStreamActivity.this, 0x10000024, -1, 0L);
                    return;
                }
                AEEVideoStreamActivity aeevideostreamactivity10 = AEEVideoStreamActivity.this;
                AEEVideoStreamActivity aeevideostreamactivity9;
                int j3;
                if (AEEVideoStreamActivity.access$1000(AEEVideoStreamActivity.this) == 19)
                {
                    j3 = AEEVideoStreamActivity.access$1000(AEEVideoStreamActivity.this);
                } else
                {
                    j3 = -1;
                }
                aeevideostreamactivity10.switchTo(2, j3);
                if (!AEEVideoStreamActivity.access$2600(AEEVideoStreamActivity.this))
                {
                    showSurfaceView(0);
                    AEEVideoStreamActivity.access$4102(AEEVideoStreamActivity.this, false);
                } else
                {
                    AEEVideoStreamActivity.access$2700(AEEVideoStreamActivity.this, true);
                }
            } else
            {
                switchTo(1, 20);
            }
            if (l != -1)
            {
                aeevideostreamactivity9 = AEEVideoStreamActivity.this;
                int i3;
                if (l == 0x1000001f)
                {
                    i3 = 0x10000027;
                } else
                {
                    i3 = -1;
                }
                AEEVideoStreamActivity.access$1700(aeevideostreamactivity9, l, i3, 0L);
                return;
            }
        } else
        if (AEEVideoStreamActivity.access$900(AEEVideoStreamActivity.this))
        {
            AEEVideoStreamActivity.access$1302(AEEVideoStreamActivity.this, true);
            AEEVideoStreamActivity aeevideostreamactivity12 = AEEVideoStreamActivity.this;
            byte byte2;
            if (AEEVideoStreamActivity.access$1200(AEEVideoStreamActivity.this))
            {
                byte2 = 20;
            } else
            {
                byte2 = 18;
            }
            aeevideostreamactivity12.switchTo(1, byte2);
            if (l != -1)
            {
                AEEVideoStreamActivity aeevideostreamactivity13 = AEEVideoStreamActivity.this;
                int l3;
                if (l == 0x1000001f)
                {
                    l3 = 0x10000027;
                } else
                {
                    l3 = -1;
                }
                AEEVideoStreamActivity.access$1700(aeevideostreamactivity13, l, l3, 0L);
                return;
            }
        } else
        {
            AEEVideoStreamActivity aeevideostreamactivity11 = AEEVideoStreamActivity.this;
            int k3;
            if (!mSocketClient.getIsNeedEncoding())
            {
                k3 = 0x10000013;
            } else
            {
                k3 = -1;
            }
            AEEVideoStreamActivity.access$1700(aeevideostreamactivity11, k3, 0x10000035, 0L);
            return;
        }
        continue; /* Loop/switch isn't completed */
_L31:
        Log.e("AEEVideoStreamActivity", "onRequestRespondsFinished PARAM_AEE_CMD_GET_DV_SETTINGS_STREAMING");
        if (s1 == null || s1.contains("on"))
        {
            if (mSocketClient.getIsNeedEncoding())
            {
                AEEVideoStreamActivity.access$1700(AEEVideoStreamActivity.this, 0x10000012, 0x1000001f, 0L);
                return;
            }
            AEEVideoStreamActivity aeevideostreamactivity6 = AEEVideoStreamActivity.this;
            int j2;
            if (AEEVideoStreamActivity.access$1000(AEEVideoStreamActivity.this) == 19)
            {
                j2 = AEEVideoStreamActivity.access$1000(AEEVideoStreamActivity.this);
            } else
            {
                j2 = -1;
            }
            aeevideostreamactivity6.switchTo(2, j2);
            AEEVideoStreamActivity.access$1700(AEEVideoStreamActivity.this, 0x1000001f, 0x10000027, 0L);
            if (!AEEVideoStreamActivity.access$2600(AEEVideoStreamActivity.this))
            {
                AEEVideoStreamActivity aeevideostreamactivity7 = AEEVideoStreamActivity.this;
                char c1;
                if (AEEVideoStreamActivity.access$4100(AEEVideoStreamActivity.this))
                {
                    c1 = '\u05DC';
                } else
                {
                    c1 = '\0';
                }
                aeevideostreamactivity7.showSurfaceView(c1);
                AEEVideoStreamActivity.access$4102(AEEVideoStreamActivity.this, false);
                return;
            } else
            {
                AEEVideoStreamActivity.access$2700(AEEVideoStreamActivity.this, true);
                return;
            }
        }
        AEEVideoStreamActivity aeevideostreamactivity8 = AEEVideoStreamActivity.this;
        int k2;
        if (!mSocketClient.getIsNeedEncoding())
        {
            k2 = 0x10000013;
        } else
        {
            k2 = -1;
        }
        AEEVideoStreamActivity.access$1700(aeevideostreamactivity8, k2, 0x10000037, 0L);
        return;
_L50:
        Log.e("AEEVideoStreamActivity", "onRequestRespondsFinished PARAM_AEE_CMD_SET_DV_SETTINGS_VIDEO_STAMP");
        if (AEEVideoStreamActivity.access$900(AEEVideoStreamActivity.this))
        {
            AEEVideoStreamActivity.access$1700(AEEVideoStreamActivity.this, 0x10000022, -1, 0L);
            return;
        } else
        {
            AEEVideoStreamActivity.access$1700(AEEVideoStreamActivity.this, 0x10000021, -1, 0L);
            return;
        }
_L48:
        Log.e("AEEVideoStreamActivity", "onRequestRespondsFinished PARAM_AEE_CMD_SET_DV_SETTINGS_STREAM_TYPE");
        AEEVideoStreamActivity.access$1700(AEEVideoStreamActivity.this, 0x10000023, -1, 0L);
        return;
_L47:
        Log.e("AEEVideoStreamActivity", "onRequestRespondsFinished PARAM_AEE_CMD_SET_DV_SETTINGS_DUAL_STREAM");
        AEEVideoStreamActivity.access$1700(AEEVideoStreamActivity.this, 0x10000022, -1, 0L);
        return;
_L49:
        Log.e("AEEVideoStreamActivity", "onRequestRespondsFinished PARAM_AEE_CMD_SET_DV_SETTINGS_STREAMING");
        if (mSocketClient.getIsNeedEncoding())
        {
            AEEVideoStreamActivity.access$1700(AEEVideoStreamActivity.this, 0x10000012, 0x1000001f, 0L);
            return;
        }
        AEEVideoStreamActivity aeevideostreamactivity4 = AEEVideoStreamActivity.this;
        int i2;
        if (AEEVideoStreamActivity.access$1000(AEEVideoStreamActivity.this) == 19)
        {
            i2 = AEEVideoStreamActivity.access$1000(AEEVideoStreamActivity.this);
        } else
        {
            i2 = -1;
        }
        aeevideostreamactivity4.switchTo(2, i2);
        AEEVideoStreamActivity.access$1700(AEEVideoStreamActivity.this, 0x1000001f, 0x10000027, 0L);
        if (!AEEVideoStreamActivity.access$2600(AEEVideoStreamActivity.this))
        {
            AEEVideoStreamActivity aeevideostreamactivity5 = AEEVideoStreamActivity.this;
            char c;
            if (AEEVideoStreamActivity.access$4100(AEEVideoStreamActivity.this))
            {
                c = '\u05DC';
            } else
            {
                c = '\0';
            }
            aeevideostreamactivity5.showSurfaceView(c);
            AEEVideoStreamActivity.access$4102(AEEVideoStreamActivity.this, false);
            return;
        } else
        {
            AEEVideoStreamActivity.access$2700(AEEVideoStreamActivity.this, true);
            return;
        }
_L23:
        Log.e("AEEVideoStreamActivity", "onRequestRespondsFinished PARAM_AEE_CMD_STOP_ENCODING");
        if (j != 0)
        {
            AEEVideoStreamActivity.access$3900(AEEVideoStreamActivity.this, j, i, s1);
            return;
        }
        if (l == 0x1000000d || l == 0x1000000e)
        {
            AEEVideoStreamActivity.access$1700(AEEVideoStreamActivity.this, l, -1, 500L);
            return;
        } else
        {
            AEEVideoStreamActivity.access$1700(AEEVideoStreamActivity.this, l, -1, 0L);
            return;
        }
_L9:
        if (j != 0)
        {
            if (mSocketClient.getCurShareId(AEEVideoStreamActivity.this) > 0)
            {
                AEEVideoStreamActivity aeevideostreamactivity3 = AEEVideoStreamActivity.this;
                int k1;
                if (l == 0x1000002b)
                {
                    k1 = 0x10000033;
                } else
                {
                    k1 = -1;
                }
                AEEVideoStreamActivity.access$1700(aeevideostreamactivity3, l, k1, 0L);
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
                int j1 = Integer.parseInt(s1);
                AEEUtilDef.setSharedTokenId(AEEVideoStreamActivity.this, j1);
            }
            catch (Exception exception1)
            {
                exception1.printStackTrace();
            }
        }
        if (l != -1)
        {
            switch (l)
            {
            default:
                AEEVideoStreamActivity.access$1700(AEEVideoStreamActivity.this, l, -1, 0L);
                return;

            case 268435499: 
                AEEVideoStreamActivity.access$1700(AEEVideoStreamActivity.this, l, 0x10000033, 0L);
                return;

            case 268435459: 
                AEEVideoStreamActivity.access$1700(AEEVideoStreamActivity.this, l, 0x10000021, 0L);
                return;
            }
        }
        continue; /* Loop/switch isn't completed */
_L10:
        Log.e("AEEVideoStreamActivity", "PARAM_AEE_CMD_SESSION_STOP");
        try
        {
            mSocketClient.stopRespondParams();
            mSocketClient.setOnRequestRespondsListener(null);
            mSocketClient.releaseCurTokenId();
            mSocketClient.close();
            mSocketClient.realseClient();
            mSocketClient = null;
            return;
        }
        catch (IOException ioexception1)
        {
            ioexception1.printStackTrace();
        }
        return;
_L33:
        if (s1 != null && s1.contains("record"))
        {
            if (AEEVideoStreamActivity.access$4000(AEEVideoStreamActivity.this) != 3)
            {
                AEEVideoStreamActivity.access$4002(AEEVideoStreamActivity.this, 3);
                AEEVideoStreamActivity.access$4200(AEEVideoStreamActivity.this, 10, 3, -1, null);
            }
            mSocketClient.setIsNeedEncoding(false);
            AEEVideoStreamActivity.access$1700(AEEVideoStreamActivity.this, 0x1000002c, 0x10000021, 0L);
            return;
        }
        if (s1 != null && s1.contains("idle"))
        {
            mSocketClient.setIsNeedEncoding(true);
            AEEVideoStreamActivity aeevideostreamactivity2 = AEEVideoStreamActivity.this;
            long l2;
            if (AEEVideoStreamActivity.access$4100(AEEVideoStreamActivity.this))
            {
                l2 = 1500L;
            } else
            {
                l2 = 0L;
            }
            AEEVideoStreamActivity.access$1700(aeevideostreamactivity2, 0x10000024, -1, l2);
            return;
        }
        mSocketClient.setIsNeedEncoding(false);
        AEEVideoStreamActivity aeevideostreamactivity1 = AEEVideoStreamActivity.this;
        long l1;
        if (AEEVideoStreamActivity.access$4100(AEEVideoStreamActivity.this))
        {
            l1 = 1500L;
        } else
        {
            l1 = 0L;
        }
        AEEVideoStreamActivity.access$1700(aeevideostreamactivity1, 0x10000024, -1, l1);
        return;
_L39:
        int i1;
        String as[];
        if (AEEVideoStreamActivity.access$900(AEEVideoStreamActivity.this))
        {
            i1 = -1;
        } else
        {
            i1 = 0x1000001f;
        }
        if (s1 == null || j != 0) goto _L53; else goto _L52
_L52:
        Log.e("AEEVideoStreamActivity", (new StringBuilder()).append("PARAM_AEE_CMD_GET_DV_RECORD_TIME param = ").append(s1).toString());
        as = s1.split(":");
        if (as.length != 1) goto _L55; else goto _L54
_L54:
        AEEVideoStreamActivity.access$2102(AEEVideoStreamActivity.this, Integer.parseInt(as[0]));
_L58:
        Log.e("AEEVideoStreamActivity", (new StringBuilder()).append("PARAM_AEE_CMD_GET_DV_RECORD_TIME mTimeCountor = ").append(AEEVideoStreamActivity.access$2100(AEEVideoStreamActivity.this)).toString());
_L53:
        if (!AEEVideoStreamActivity.access$900(AEEVideoStreamActivity.this))
        {
            AEEVideoStreamActivity.access$4300(AEEVideoStreamActivity.this);
            AEEVideoStreamActivity.access$400(AEEVideoStreamActivity.this, 16, 1, -1, null, 0L);
        }
        AEEVideoStreamActivity.access$902(AEEVideoStreamActivity.this, true);
        AEEVideoStreamActivity.access$1700(AEEVideoStreamActivity.this, l, i1, 0L);
        return;
_L55:
        if (as.length != 2) goto _L57; else goto _L56
_L56:
        AEEVideoStreamActivity.access$2102(AEEVideoStreamActivity.this, 60 * Integer.parseInt(as[0]) + Integer.parseInt(as[1]));
          goto _L58
        Exception exception;
        exception;
        exception.printStackTrace();
          goto _L53
_L57:
        if (as.length != 3) goto _L58; else goto _L59
_L59:
        AEEVideoStreamActivity.access$2102(AEEVideoStreamActivity.this, 3600 * Integer.parseInt(as[0]) + 60 * Integer.parseInt(as[1]) + Integer.parseInt(as[2]));
          goto _L58
_L11:
        Log.e("AEEVideoStreamActivity", "onRequestRespondsFinished PARAM_AEE_CMD_RECORD_START");
        AEEVideoStreamActivity.access$1502(AEEVideoStreamActivity.this, false);
        switch (j)
        {
        default:
            AEEVideoStreamActivity.access$2900(AEEVideoStreamActivity.this, false, true);
            return;

        case 0: // '\0'
            AEEVideoStreamActivity.access$4300(AEEVideoStreamActivity.this);
            AEEVideoStreamActivity.access$2900(AEEVideoStreamActivity.this, true, true);
            if (l > 0)
            {
                AEEVideoStreamActivity.access$1700(AEEVideoStreamActivity.this, l, -1, 0L);
                return;
            }
            break;

        case -19: 
        case -17: 
        case 1: // '\001'
            if (AEEVideoStreamActivity.access$900(AEEVideoStreamActivity.this))
            {
                AEEVideoStreamActivity.access$2800(AEEVideoStreamActivity.this);
                AEEVideoStreamActivity.access$2900(AEEVideoStreamActivity.this, false, true);
            }
            AEEVideoStreamActivity.access$3900(AEEVideoStreamActivity.this, j, i, s1);
            return;

        case -4: 
            AEEVideoStreamActivity.access$1700(AEEVideoStreamActivity.this, 0x10000001, 0x10000003, 0L);
            return;
        }
        continue; /* Loop/switch isn't completed */
_L12:
        Log.e("AEEVideoStreamActivity", "onRequestRespondsFinished PARAM_AEE_CMD_RECORD_STOP");
        AEEVideoStreamActivity.access$1302(AEEVideoStreamActivity.this, false);
        if (j == 0 || AEEVideoStreamActivity.access$2600(AEEVideoStreamActivity.this))
        {
            AEEVideoStreamActivity.access$2800(AEEVideoStreamActivity.this);
            AEEVideoStreamActivity.access$2900(AEEVideoStreamActivity.this, false, true);
            if (AEEVideoStreamActivity.access$1400(AEEVideoStreamActivity.this) == 8)
            {
                switchTo(8, 0);
            } else
            if (AEEVideoStreamActivity.access$1200(AEEVideoStreamActivity.this))
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
            AEEVideoStreamActivity.access$1700(AEEVideoStreamActivity.this, 0x10000001, 0x10000004, 0L);
        } else
        {
            AEEVideoStreamActivity.access$902(AEEVideoStreamActivity.this, true);
            AEEVideoStreamActivity.access$3900(AEEVideoStreamActivity.this, j, i, s1);
        }
        if (l != -1)
        {
            AEEVideoStreamActivity.access$1700(AEEVideoStreamActivity.this, 0x10000026, l, 0L);
            return;
        } else
        {
            AEEVideoStreamActivity.access$1700(AEEVideoStreamActivity.this, 0x10000026, -1, 0L);
            return;
        }
_L13:
        AEEVideoStreamActivity.access$1502(AEEVideoStreamActivity.this, false);
        if (j == 0)
        {
            AEEVideoStreamActivity.access$4300(AEEVideoStreamActivity.this);
            switchTo(8, 1);
            AEEVideoStreamActivity.access$2900(AEEVideoStreamActivity.this, true, true);
            return;
        } else
        {
            AEEVideoStreamActivity.access$902(AEEVideoStreamActivity.this, false);
            AEEVideoStreamActivity.access$3900(AEEVideoStreamActivity.this, j, i, s1);
            return;
        }
_L14:
        AEEVideoStreamActivity.access$2900(AEEVideoStreamActivity.this, false, true);
        if (j != 0)
        {
            AEEVideoStreamActivity.access$3900(AEEVideoStreamActivity.this, j, i, s1);
        }
        AEEVideoStreamActivity.access$1700(AEEVideoStreamActivity.this, 0x10000026, -1, 0L);
        return;
_L16:
        AEEVideoStreamActivity.access$1502(AEEVideoStreamActivity.this, false);
        if (j != 0)
        {
            AEEVideoStreamActivity.access$3900(AEEVideoStreamActivity.this, j, i, s1);
            return;
        }
        continue; /* Loop/switch isn't completed */
_L46:
        if (s1 != null && s1.contains("video_flip_rotate_on_"))
        {
            if (AEEVideoStreamActivity.access$1500(AEEVideoStreamActivity.this))
            {
                AEEVideoStreamActivity.access$1700(AEEVideoStreamActivity.this, 0x10000013, 0x1000000e, 0L);
            } else
            {
                if (!mSocketClient.getIsRolloverOn())
                {
                    mSocketClient.setIsRolloverOn(true);
                }
                AEEVideoStreamActivity.access$4200(AEEVideoStreamActivity.this, 14, 1, 7, null);
            }
        } else
        if (s1 != null && s1.contains("video_flip_rotate_off"))
        {
            if (AEEVideoStreamActivity.access$1500(AEEVideoStreamActivity.this))
            {
                AEEVideoStreamActivity.access$1700(AEEVideoStreamActivity.this, 0x10000013, 0x1000000d, 0L);
            } else
            {
                if (mSocketClient.getIsRolloverOn())
                {
                    mSocketClient.setIsRolloverOn(false);
                }
                AEEVideoStreamActivity.access$4200(AEEVideoStreamActivity.this, 14, 0, 7, null);
            }
        } else
        {
            AEEVideoStreamActivity.access$3900(AEEVideoStreamActivity.this, j, i, s1);
            AEEVideoStreamActivity.access$1502(AEEVideoStreamActivity.this, false);
        }
        switch (l)
        {
        default:
            AEEVideoStreamActivity.access$1700(AEEVideoStreamActivity.this, l, -1, 0L);
            return;

        case 268435493: 
            AEEVideoStreamActivity.access$1700(AEEVideoStreamActivity.this, l, 0x10000024, 0L);
            return;

        case -1: 
            break;
        }
        continue; /* Loop/switch isn't completed */
_L19:
        if (j == 0)
        {
            mSocketClient.setIsRolloverOn(true);
            AEEVideoStreamActivity.access$4200(AEEVideoStreamActivity.this, 14, 1, 7, null);
        } else
        {
            AEEVideoStreamActivity.access$3900(AEEVideoStreamActivity.this, j, i, s1);
        }
        if (mSocketClient.getIsNeedEncoding())
        {
            AEEVideoStreamActivity.access$1700(AEEVideoStreamActivity.this, 0x10000012, -1, 0L);
        }
        AEEVideoStreamActivity.access$1502(AEEVideoStreamActivity.this, false);
        return;
_L20:
        if (j == 0)
        {
            mSocketClient.setIsRolloverOn(false);
            AEEVideoStreamActivity.access$4200(AEEVideoStreamActivity.this, 14, 0, 7, null);
        } else
        {
            AEEVideoStreamActivity.access$3900(AEEVideoStreamActivity.this, j, i, s1);
        }
        if (mSocketClient.getIsNeedEncoding())
        {
            AEEVideoStreamActivity.access$1700(AEEVideoStreamActivity.this, 0x10000012, -1, 0L);
        }
        AEEVideoStreamActivity.access$1502(AEEVideoStreamActivity.this, false);
        return;
_L24:
        AEEVideoStreamActivity.access$1502(AEEVideoStreamActivity.this, false);
        switch (j)
        {
        default:
            AEEVideoStreamActivity.access$902(AEEVideoStreamActivity.this, false);
            AEEVideoStreamActivity.access$3900(AEEVideoStreamActivity.this, j, i, s1);
            return;

        case 0: // '\0'
            AEEVideoStreamActivity.access$2900(AEEVideoStreamActivity.this, true, true);
            return;

        case 1: // '\001'
            break;
        }
        if (s1 != null && s1.contains("16777220"))
        {
            AEEVideoStreamActivity.access$902(AEEVideoStreamActivity.this, false);
            AEEVideoStreamActivity.access$3900(AEEVideoStreamActivity.this, j, i, s1);
            return;
        } else
        {
            AEEVideoStreamActivity.access$2900(AEEVideoStreamActivity.this, true, true);
            return;
        }
_L15:
        if (j == 0)
        {
            AEEVideoStreamActivity.access$2900(AEEVideoStreamActivity.this, false, true);
            updateSurfaceView(0);
        } else
        if (j != 1)
        {
            AEEVideoStreamActivity.access$902(AEEVideoStreamActivity.this, true);
            AEEVideoStreamActivity.access$3900(AEEVideoStreamActivity.this, j, i, s1);
        }
        AEEVideoStreamActivity.access$1700(AEEVideoStreamActivity.this, 0x10000026, -1, 0L);
        return;
_L21:
        switchTo(1, 16);
        return;
_L26:
        showSurfaceView(0);
        switchTo(2, 17);
        return;
_L38:
        byte byte0 = -1;
        j;
        JVM INSTR lookupswitch 2: default 3916
    //                   -4: 4157
    //                   0: 4100;
           goto _L60 _L61 _L62
_L60:
        if (AEEVideoStreamActivity.access$4000(AEEVideoStreamActivity.this) == -1)
        {
            AEEVideoStreamActivity.access$4002(AEEVideoStreamActivity.this, byte0);
            AEEVideoStreamActivity aeevideostreamactivity = AEEVideoStreamActivity.this;
            byte byte1;
            if (byte0 == -1)
            {
                byte1 = 3;
            } else
            {
                byte1 = byte0;
            }
            AEEVideoStreamActivity.access$4200(aeevideostreamactivity, 10, byte1, -1, null);
        }
        Log.e("AEEVideoStreamActivity", (new StringBuilder()).append("PARAM_AEE_CMD_GET_DV_SETTINGS_DV_MODE curState = ").append(byte0).append(" mCurDVMode = ").append(AEEVideoStreamActivity.access$4000(AEEVideoStreamActivity.this)).toString());
        Log.e("AEEVideoStreamActivity", (new StringBuilder()).append("PARAM_AEE_CMD_GET_DV_SETTINGS_DV_MODE nextCMD = ").append(l).toString());
        switch (l)
        {
        default:
            return;

        case 268435467: 
            if (AEEVideoStreamActivity.access$4000(AEEVideoStreamActivity.this) != 3)
            {
                AEEVideoStreamActivity.access$1700(AEEVideoStreamActivity.this, l, -1, 0L);
                return;
            } else
            {
                AEEVideoStreamActivity.access$4002(AEEVideoStreamActivity.this, 3);
                AEEVideoStreamActivity.access$4200(AEEVideoStreamActivity.this, 10, 3, -1, null);
                return;
            }

        case 268435468: 
            if (byte0 != 8 && AEEVideoStreamActivity.access$4000(AEEVideoStreamActivity.this) != 8)
            {
                AEEVideoStreamActivity.access$1700(AEEVideoStreamActivity.this, 0x10000013, l, 0L);
                return;
            } else
            {
                AEEVideoStreamActivity.access$4002(AEEVideoStreamActivity.this, 8);
                AEEVideoStreamActivity.access$4200(AEEVideoStreamActivity.this, 10, 8, -1, null);
                return;
            }

        case 268435477: 
            if (byte0 != 4 && AEEVideoStreamActivity.access$4000(AEEVideoStreamActivity.this) != 4)
            {
                AEEVideoStreamActivity.access$1700(AEEVideoStreamActivity.this, l, -1, 0L);
                return;
            } else
            {
                AEEVideoStreamActivity.access$4002(AEEVideoStreamActivity.this, 4);
                AEEVideoStreamActivity.access$4200(AEEVideoStreamActivity.this, 10, AEEVideoStreamActivity.access$4400(AEEVideoStreamActivity.this), -1, null);
                return;
            }

        case 268435507: 
            AEEVideoStreamActivity.access$1700(AEEVideoStreamActivity.this, l, 0x10000025, 0L);
            return;
        }
_L62:
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
          goto _L60
_L61:
        mSocketClient.releaseCurShareId(AEEVideoStreamActivity.this);
        AEEVideoStreamActivity.access$1700(AEEVideoStreamActivity.this, 0x10000001, 0x1000002b, 0L);
        return;
_L17:
        Log.e("AEEVideoStreamActivity", (new StringBuilder()).append("PARAM_AEE_CMD_DV_MODE mSwitchDVMode = ").append(AEEVideoStreamActivity.access$4400(AEEVideoStreamActivity.this)).toString());
        if (j == 0)
        {
            AEEVideoStreamActivity.access$4002(AEEVideoStreamActivity.this, 3);
            AEEVideoStreamActivity.access$4200(AEEVideoStreamActivity.this, 10, 3, -1, null);
            return;
        }
        if (AEEVideoStreamActivity.access$4000(AEEVideoStreamActivity.this) == 3)
        {
            AEEVideoStreamActivity.access$1700(AEEVideoStreamActivity.this, 0x10000013, -1, 0L);
        }
        AEEVideoStreamActivity.access$3900(AEEVideoStreamActivity.this, j, i, s1);
        AEEVideoStreamActivity.access$4402(AEEVideoStreamActivity.this, -1);
        return;
_L18:
        Log.e("AEEVideoStreamActivity", (new StringBuilder()).append("PARAM_AEE_CMD_VOICE_MODE mSwitchDVMode = ").append(AEEVideoStreamActivity.access$4400(AEEVideoStreamActivity.this)).toString());
        if (j == 0)
        {
            AEEVideoStreamActivity.access$4002(AEEVideoStreamActivity.this, 8);
            AEEVideoStreamActivity.access$4200(AEEVideoStreamActivity.this, 10, 8, -1, null);
            return;
        } else
        {
            AEEVideoStreamActivity.access$1700(AEEVideoStreamActivity.this, 0x10000012, -1, 0L);
            AEEVideoStreamActivity.access$3900(AEEVideoStreamActivity.this, j, i, s1);
            AEEVideoStreamActivity.access$4402(AEEVideoStreamActivity.this, -1);
            return;
        }
_L25:
        Log.e("AEEVideoStreamActivity", (new StringBuilder()).append("PARAM_AEE_CMD_PHOTO_MODE mSwitchDVMode = ").append(AEEVideoStreamActivity.access$4400(AEEVideoStreamActivity.this)).toString());
        if (j == 0)
        {
            AEEVideoStreamActivity.access$4002(AEEVideoStreamActivity.this, 4);
            AEEVideoStreamActivity.access$4200(AEEVideoStreamActivity.this, 10, AEEVideoStreamActivity.access$4400(AEEVideoStreamActivity.this), -1, null);
            return;
        }
        if (AEEVideoStreamActivity.access$4000(AEEVideoStreamActivity.this) == 3)
        {
            AEEVideoStreamActivity.access$1700(AEEVideoStreamActivity.this, 0x10000013, -1, 0L);
        }
        AEEVideoStreamActivity.access$3900(AEEVideoStreamActivity.this, j, i, s1);
        AEEVideoStreamActivity.access$4402(AEEVideoStreamActivity.this, -1);
        return;
_L35:
        Log.e("AEEVideoStreamActivity", (new StringBuilder()).append("PARAM_AEE_CMD_GET_DV_SETTINGS_DV_INFO param = ").append(s1).append(" nextCmd = ").append(l).toString());
        if (j == 0)
        {
            AEEVideoStreamActivity.access$4200(AEEVideoStreamActivity.this, 12, 0x10000027, -1, s1);
        }
        if (l >= 0)
        {
            AEEVideoStreamActivity.access$4200(AEEVideoStreamActivity.this, 11, l, -1, Integer.valueOf(0));
            return;
        }
        continue; /* Loop/switch isn't completed */
_L36:
        if (j == 0)
        {
            AEEVideoStreamActivity.access$4200(AEEVideoStreamActivity.this, 12, 0x10000028, -1, s1);
        }
        if (l >= 0)
        {
            AEEVideoStreamActivity.access$4200(AEEVideoStreamActivity.this, 11, l, -1, Integer.valueOf(0));
            return;
        }
        continue; /* Loop/switch isn't completed */
_L37:
        if (j == 0)
        {
            AEEVideoStreamActivity.access$4200(AEEVideoStreamActivity.this, 12, 0x1000002a, -1, s1);
        }
        if (l >= 0)
        {
            AEEVideoStreamActivity.access$4200(AEEVideoStreamActivity.this, 11, l, -1, Integer.valueOf(0));
            return;
        }
        continue; /* Loop/switch isn't completed */
_L34:
        if (j == 0)
        {
            AEEVideoStreamActivity.access$4200(AEEVideoStreamActivity.this, 12, 0x10000026, -1, s1);
        }
        if (l == 0x1000002d)
        {
            AEEVideoStreamActivity.access$4200(AEEVideoStreamActivity.this, 11, l, -1, Integer.valueOf(0));
        } else
        if (l > 0)
        {
            AEEVideoStreamActivity.access$1700(AEEVideoStreamActivity.this, l, -1, 0L);
        }
        AEEVideoStreamActivity.access$1502(AEEVideoStreamActivity.this, false);
        return;
_L28:
        if (j != 0);
        if (l >= 0)
        {
            AEEVideoStreamActivity.access$4200(AEEVideoStreamActivity.this, 11, l, -1, Integer.valueOf(0));
            return;
        }
        continue; /* Loop/switch isn't completed */
_L40:
        if (j == 0)
        {
            mSocketClient.setDVInfo(12, s1);
        }
        if (l >= 0)
        {
            AEEVideoStreamActivity.access$4200(AEEVideoStreamActivity.this, 11, l, -1, Integer.valueOf(0));
            return;
        }
        continue; /* Loop/switch isn't completed */
_L41:
        if (j == 0)
        {
            mSocketClient.setDVInfo(14, s1);
        }
        if (l >= 0)
        {
            AEEVideoStreamActivity.access$4200(AEEVideoStreamActivity.this, 11, l, -1, Integer.valueOf(0));
            return;
        }
        continue; /* Loop/switch isn't completed */
_L42:
        if (j == 0)
        {
            mSocketClient.setDVInfo(15, s1);
        }
        if (l >= 0)
        {
            AEEVideoStreamActivity.access$4200(AEEVideoStreamActivity.this, 11, l, -1, Integer.valueOf(0));
            return;
        }
        continue; /* Loop/switch isn't completed */
_L43:
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
            AEEVideoStreamActivity.access$4200(AEEVideoStreamActivity.this, 11, l, -1, Integer.valueOf(0));
            return;
        }
        continue; /* Loop/switch isn't completed */
_L44:
        if (j == 0)
        {
            mSocketClient.setDVInfo(17, s1);
        }
        if (AEEVideoStreamActivity.access$1000(AEEVideoStreamActivity.this) == 19)
        {
            AEEVideoStreamActivity.access$1002(AEEVideoStreamActivity.this, -1);
            AEEVideoStreamActivity.access$4200(AEEVideoStreamActivity.this, 17, 0x7f0b015d, -1, null);
        }
        AEEVideoStreamActivity.access$1602(AEEVideoStreamActivity.this, false);
        return;
_L45:
        if (j == 0)
        {
            mSocketClient.setDVInfo(13, s1);
        }
        if (l >= 0)
        {
            AEEVideoStreamActivity.access$4200(AEEVideoStreamActivity.this, 11, l, -1, Integer.valueOf(0));
            return;
        }
        if (true) goto _L64; else goto _L63
_L63:
    }

    RespondsListener()
    {
        this$0 = AEEVideoStreamActivity.this;
        super();
    }
}
