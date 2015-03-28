// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.arcsoft.adk.atv;


// Referenced classes of package com.arcsoft.adk.atv:
//            UPnPFlagsParameterUtils

public static final class  extends Enum
{

    private static final .VALUES $VALUES[];
    public static final .VALUES bit12_dtcp_move;
    public static final .VALUES bit13_dtcp_copy;
    public static final .VALUES bit14_lopcleartextbytes;
    public static final .VALUES bit15_cleartextbyteseekfull;
    public static final .VALUES bit16_lpflag;
    public static final .VALUES bit20_dlna_v15falg;
    public static final .VALUES bit21_httpstalling;
    public static final .VALUES bit22_tmb;
    public static final .VALUES bit23_tmi;
    public static final .VALUES bit24_tms;
    public static final .VALUES bit25_rtsppause;
    public static final .VALUES bit26_snincreasing;
    public static final .VALUES bit27_s0increasing;
    public static final .VALUES bit28_playcontainerparam;
    public static final .VALUES bit29_lopbytes;
    public static final .VALUES bit30_lopnpt;
    public static final .VALUES bit31_spflag;

    public static  valueOf(String s)
    {
        return ()Enum.valueOf(com/arcsoft/adk/atv/UPnPFlagsParameterUtils$Flags, s);
    }

    public static [] values()
    {
        return ([])$VALUES.clone();
    }

    static 
    {
        bit31_spflag = new <init>("bit31_spflag", 0);
        bit30_lopnpt = new <init>("bit30_lopnpt", 1);
        bit29_lopbytes = new <init>("bit29_lopbytes", 2);
        bit28_playcontainerparam = new <init>("bit28_playcontainerparam", 3);
        bit27_s0increasing = new <init>("bit27_s0increasing", 4);
        bit26_snincreasing = new <init>("bit26_snincreasing", 5);
        bit25_rtsppause = new <init>("bit25_rtsppause", 6);
        bit24_tms = new <init>("bit24_tms", 7);
        bit23_tmi = new <init>("bit23_tmi", 8);
        bit22_tmb = new <init>("bit22_tmb", 9);
        bit21_httpstalling = new <init>("bit21_httpstalling", 10);
        bit20_dlna_v15falg = new <init>("bit20_dlna_v15falg", 11);
        bit16_lpflag = new <init>("bit16_lpflag", 12);
        bit15_cleartextbyteseekfull = new <init>("bit15_cleartextbyteseekfull", 13);
        bit14_lopcleartextbytes = new <init>("bit14_lopcleartextbytes", 14);
        bit13_dtcp_copy = new <init>("bit13_dtcp_copy", 15);
        bit12_dtcp_move = new <init>("bit12_dtcp_move", 16);
        s_3B_.clone aclone[] = new <init>[17];
        aclone[0] = bit31_spflag;
        aclone[1] = bit30_lopnpt;
        aclone[2] = bit29_lopbytes;
        aclone[3] = bit28_playcontainerparam;
        aclone[4] = bit27_s0increasing;
        aclone[5] = bit26_snincreasing;
        aclone[6] = bit25_rtsppause;
        aclone[7] = bit24_tms;
        aclone[8] = bit23_tmi;
        aclone[9] = bit22_tmb;
        aclone[10] = bit21_httpstalling;
        aclone[11] = bit20_dlna_v15falg;
        aclone[12] = bit16_lpflag;
        aclone[13] = bit15_cleartextbyteseekfull;
        aclone[14] = bit14_lopcleartextbytes;
        aclone[15] = bit13_dtcp_copy;
        aclone[16] = bit12_dtcp_move;
        $VALUES = aclone;
    }

    private (String s, int i)
    {
        super(s, i);
    }
}
