// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.arcsoft.adk.atv;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class UPnPFlagsParameterUtils
{
    public static final class Flags extends Enum
    {

        private static final Flags $VALUES[];
        public static final Flags bit12_dtcp_move;
        public static final Flags bit13_dtcp_copy;
        public static final Flags bit14_lopcleartextbytes;
        public static final Flags bit15_cleartextbyteseekfull;
        public static final Flags bit16_lpflag;
        public static final Flags bit20_dlna_v15falg;
        public static final Flags bit21_httpstalling;
        public static final Flags bit22_tmb;
        public static final Flags bit23_tmi;
        public static final Flags bit24_tms;
        public static final Flags bit25_rtsppause;
        public static final Flags bit26_snincreasing;
        public static final Flags bit27_s0increasing;
        public static final Flags bit28_playcontainerparam;
        public static final Flags bit29_lopbytes;
        public static final Flags bit30_lopnpt;
        public static final Flags bit31_spflag;

        public static Flags valueOf(String s)
        {
            return (Flags)Enum.valueOf(com/arcsoft/adk/atv/UPnPFlagsParameterUtils$Flags, s);
        }

        public static Flags[] values()
        {
            return (Flags[])$VALUES.clone();
        }

        static 
        {
            bit31_spflag = new Flags("bit31_spflag", 0);
            bit30_lopnpt = new Flags("bit30_lopnpt", 1);
            bit29_lopbytes = new Flags("bit29_lopbytes", 2);
            bit28_playcontainerparam = new Flags("bit28_playcontainerparam", 3);
            bit27_s0increasing = new Flags("bit27_s0increasing", 4);
            bit26_snincreasing = new Flags("bit26_snincreasing", 5);
            bit25_rtsppause = new Flags("bit25_rtsppause", 6);
            bit24_tms = new Flags("bit24_tms", 7);
            bit23_tmi = new Flags("bit23_tmi", 8);
            bit22_tmb = new Flags("bit22_tmb", 9);
            bit21_httpstalling = new Flags("bit21_httpstalling", 10);
            bit20_dlna_v15falg = new Flags("bit20_dlna_v15falg", 11);
            bit16_lpflag = new Flags("bit16_lpflag", 12);
            bit15_cleartextbyteseekfull = new Flags("bit15_cleartextbyteseekfull", 13);
            bit14_lopcleartextbytes = new Flags("bit14_lopcleartextbytes", 14);
            bit13_dtcp_copy = new Flags("bit13_dtcp_copy", 15);
            bit12_dtcp_move = new Flags("bit12_dtcp_move", 16);
            Flags aflags[] = new Flags[17];
            aflags[0] = bit31_spflag;
            aflags[1] = bit30_lopnpt;
            aflags[2] = bit29_lopbytes;
            aflags[3] = bit28_playcontainerparam;
            aflags[4] = bit27_s0increasing;
            aflags[5] = bit26_snincreasing;
            aflags[6] = bit25_rtsppause;
            aflags[7] = bit24_tms;
            aflags[8] = bit23_tmi;
            aflags[9] = bit22_tmb;
            aflags[10] = bit21_httpstalling;
            aflags[11] = bit20_dlna_v15falg;
            aflags[12] = bit16_lpflag;
            aflags[13] = bit15_cleartextbyteseekfull;
            aflags[14] = bit14_lopcleartextbytes;
            aflags[15] = bit13_dtcp_copy;
            aflags[16] = bit12_dtcp_move;
            $VALUES = aflags;
        }

        private Flags(String s, int i)
        {
            super(s, i);
        }
    }


    public static final int INVALIDATE_FLAG = 0;
    public static final int UNDEFIND_FLAG = 15;
    public static final int VALIDATE_FLAG = 1;

    public UPnPFlagsParameterUtils()
    {
    }

    protected static String getProtocol_DLNA_ORG_FLAGS_KEY(String s)
    {
        Matcher matcher = Pattern.compile("(DLNA.ORG_FLAGS=)[0-9a-zA-Z_]+").matcher(s);
        String s1;
        for (s1 = null; matcher.find(); s1 = matcher.group()) { }
        if (s1 != null)
        {
            s1 = s1.replace("DLNA.ORG_FLAGS=", "");
        }
        return s1;
    }

    protected static int getflag(String s, Flags flags)
    {
        char ac[];
        byte byte0;
        ac = s.toCharArray();
        byte0 = -1;
        static class _cls1
        {

            static final int $SwitchMap$com$arcsoft$adk$atv$UPnPFlagsParameterUtils$Flags[];

            static 
            {
                $SwitchMap$com$arcsoft$adk$atv$UPnPFlagsParameterUtils$Flags = new int[Flags.values().length];
                try
                {
                    $SwitchMap$com$arcsoft$adk$atv$UPnPFlagsParameterUtils$Flags[Flags.bit31_spflag.ordinal()] = 1;
                }
                catch (NoSuchFieldError nosuchfielderror) { }
                try
                {
                    $SwitchMap$com$arcsoft$adk$atv$UPnPFlagsParameterUtils$Flags[Flags.bit30_lopnpt.ordinal()] = 2;
                }
                catch (NoSuchFieldError nosuchfielderror1) { }
                try
                {
                    $SwitchMap$com$arcsoft$adk$atv$UPnPFlagsParameterUtils$Flags[Flags.bit29_lopbytes.ordinal()] = 3;
                }
                catch (NoSuchFieldError nosuchfielderror2) { }
                try
                {
                    $SwitchMap$com$arcsoft$adk$atv$UPnPFlagsParameterUtils$Flags[Flags.bit28_playcontainerparam.ordinal()] = 4;
                }
                catch (NoSuchFieldError nosuchfielderror3) { }
                try
                {
                    $SwitchMap$com$arcsoft$adk$atv$UPnPFlagsParameterUtils$Flags[Flags.bit27_s0increasing.ordinal()] = 5;
                }
                catch (NoSuchFieldError nosuchfielderror4) { }
                try
                {
                    $SwitchMap$com$arcsoft$adk$atv$UPnPFlagsParameterUtils$Flags[Flags.bit26_snincreasing.ordinal()] = 6;
                }
                catch (NoSuchFieldError nosuchfielderror5) { }
                try
                {
                    $SwitchMap$com$arcsoft$adk$atv$UPnPFlagsParameterUtils$Flags[Flags.bit25_rtsppause.ordinal()] = 7;
                }
                catch (NoSuchFieldError nosuchfielderror6) { }
                try
                {
                    $SwitchMap$com$arcsoft$adk$atv$UPnPFlagsParameterUtils$Flags[Flags.bit24_tms.ordinal()] = 8;
                }
                catch (NoSuchFieldError nosuchfielderror7) { }
                try
                {
                    $SwitchMap$com$arcsoft$adk$atv$UPnPFlagsParameterUtils$Flags[Flags.bit23_tmi.ordinal()] = 9;
                }
                catch (NoSuchFieldError nosuchfielderror8) { }
                try
                {
                    $SwitchMap$com$arcsoft$adk$atv$UPnPFlagsParameterUtils$Flags[Flags.bit22_tmb.ordinal()] = 10;
                }
                catch (NoSuchFieldError nosuchfielderror9) { }
                try
                {
                    $SwitchMap$com$arcsoft$adk$atv$UPnPFlagsParameterUtils$Flags[Flags.bit21_httpstalling.ordinal()] = 11;
                }
                catch (NoSuchFieldError nosuchfielderror10) { }
                try
                {
                    $SwitchMap$com$arcsoft$adk$atv$UPnPFlagsParameterUtils$Flags[Flags.bit20_dlna_v15falg.ordinal()] = 12;
                }
                catch (NoSuchFieldError nosuchfielderror11) { }
                try
                {
                    $SwitchMap$com$arcsoft$adk$atv$UPnPFlagsParameterUtils$Flags[Flags.bit16_lpflag.ordinal()] = 13;
                }
                catch (NoSuchFieldError nosuchfielderror12) { }
                try
                {
                    $SwitchMap$com$arcsoft$adk$atv$UPnPFlagsParameterUtils$Flags[Flags.bit15_cleartextbyteseekfull.ordinal()] = 14;
                }
                catch (NoSuchFieldError nosuchfielderror13) { }
                try
                {
                    $SwitchMap$com$arcsoft$adk$atv$UPnPFlagsParameterUtils$Flags[Flags.bit14_lopcleartextbytes.ordinal()] = 15;
                }
                catch (NoSuchFieldError nosuchfielderror14) { }
                try
                {
                    $SwitchMap$com$arcsoft$adk$atv$UPnPFlagsParameterUtils$Flags[Flags.bit13_dtcp_copy.ordinal()] = 16;
                }
                catch (NoSuchFieldError nosuchfielderror15) { }
                try
                {
                    $SwitchMap$com$arcsoft$adk$atv$UPnPFlagsParameterUtils$Flags[Flags.bit12_dtcp_move.ordinal()] = 17;
                }
                catch (NoSuchFieldError nosuchfielderror16)
                {
                    return;
                }
            }
        }

        _cls1..SwitchMap.com.arcsoft.adk.atv.UPnPFlagsParameterUtils.Flags[flags.ordinal()];
        JVM INSTR tableswitch 1 17: default 96
    //                   1 135
    //                   2 140
    //                   3 145
    //                   4 150
    //                   5 155
    //                   6 160
    //                   7 165
    //                   8 171
    //                   9 177
    //                   10 183
    //                   11 189
    //                   12 195
    //                   13 201
    //                   14 207
    //                   15 213
    //                   16 219
    //                   17 225;
           goto _L1 _L2 _L3 _L4 _L5 _L6 _L7 _L8 _L9 _L10 _L11 _L12 _L13 _L14 _L15 _L16 _L17 _L18
_L1:
        break; /* Loop/switch isn't completed */
_L18:
        break MISSING_BLOCK_LABEL_225;
_L19:
        int i = byte0 - (32 - ac.length);
        if (i >= 0 && i <= 31)
        {
            if (ac.length > i)
            {
                return Integer.parseInt(String.valueOf(ac[i]));
            } else
            {
                return 0;
            }
        } else
        {
            return 15;
        }
_L2:
        byte0 = 0;
          goto _L19
_L3:
        byte0 = 1;
          goto _L19
_L4:
        byte0 = 2;
          goto _L19
_L5:
        byte0 = 3;
          goto _L19
_L6:
        byte0 = 4;
          goto _L19
_L7:
        byte0 = 5;
          goto _L19
_L8:
        byte0 = 6;
          goto _L19
_L9:
        byte0 = 7;
          goto _L19
_L10:
        byte0 = 8;
          goto _L19
_L11:
        byte0 = 9;
          goto _L19
_L12:
        byte0 = 10;
          goto _L19
_L13:
        byte0 = 11;
          goto _L19
_L14:
        byte0 = 15;
          goto _L19
_L15:
        byte0 = 16;
          goto _L19
_L16:
        byte0 = 17;
          goto _L19
_L17:
        byte0 = 18;
          goto _L19
        byte0 = 19;
          goto _L19
    }

    public static int getflagbyBitFilter(String s, Flags flags)
    {
        String s1 = getProtocol_DLNA_ORG_FLAGS_KEY(s);
        if (s1 == null || s1.length() < 8)
        {
            return 15;
        } else
        {
            return getflag(Long.toBinaryString(Long.parseLong(new String(s1.toCharArray(), 0, 8), 16)), flags);
        }
    }
}
