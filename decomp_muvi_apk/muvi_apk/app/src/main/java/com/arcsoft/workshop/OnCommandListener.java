// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.arcsoft.workshop;


public interface OnCommandListener
{

    public static final int CMD_ADDMINIATURE = 20;
    public static final int CMD_AUTOFIX = 1;
    public static final int CMD_CANCEL = 4;
    public static final int CMD_CROP = 17;
    public static final int CMD_DOTHUMNAIL = 8;
    public static final int CMD_DO_EFFECT = 9;
    public static final int CMD_EFFECT_CHANGEPARAMS = 16;
    public static final int CMD_EFFECT_COLDCOLOR = 37;
    public static final int CMD_EFFECT_DEEPQUITE = 25;
    public static final int CMD_EFFECT_FISHEYE = 32;
    public static final int CMD_EFFECT_GOTHIC = 24;
    public static final int CMD_EFFECT_NEWSKETCH = 34;
    public static final int CMD_EFFECT_NOSTALGIC = 35;
    public static final int CMD_EFFECT_PLUGIN = 22;
    public static final int CMD_EFFECT_POLAROID = 23;
    public static final int CMD_EFFECT_SINGLECOLOR = 36;
    public static final int CMD_EFFECT_VINTAGE = 33;
    public static final int CMD_FACEBEAUTIFY = 7;
    public static final int CMD_GETIMAGERECT = 18;
    public static final int CMD_REFRESH_DISPLAY = 5;
    public static final int CMD_REMOVEAUTOFIX = 19;
    public static final int CMD_REMOVEMINIATURE = 21;
    public static final int CMD_REVERSE = 6;
    public static final int CMD_SAVE_AND_SHARE = 39;
    public static final int CMD_SAVE_NOT_SHARE = 38;
    public static final int CMD_SELTOOL = 0;
    public static final int CMD_SHARE = 2;

    public abstract int onCommand(int i, Object obj, Object obj1);
}
