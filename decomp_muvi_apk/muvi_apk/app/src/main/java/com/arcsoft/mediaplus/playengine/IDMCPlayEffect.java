// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.arcsoft.mediaplus.playengine;


public interface IDMCPlayEffect
{
    public static final class EFFECT extends Enum
    {

        private static final EFFECT $VALUES[];
        public static final EFFECT EFFECT_NONE;
        public static final EFFECT EFFECT_START_PLAY_FADE;
        public static final EFFECT EFFECT_START_PLAY_SLIDE;
        public static final EFFECT EFFECT_STOP_PLAY_FADE;
        public static final EFFECT EFFECT_STOP_PLAY_SLIDE;
        public static final EFFECT EFFECT_TURNTO_PLAY;

        public static EFFECT valueOf(String s)
        {
            return (EFFECT)Enum.valueOf(com/arcsoft/mediaplus/playengine/IDMCPlayEffect$EFFECT, s);
        }

        public static EFFECT[] values()
        {
            return (EFFECT[])$VALUES.clone();
        }

        static 
        {
            EFFECT_NONE = new EFFECT("EFFECT_NONE", 0);
            EFFECT_START_PLAY_SLIDE = new EFFECT("EFFECT_START_PLAY_SLIDE", 1);
            EFFECT_START_PLAY_FADE = new EFFECT("EFFECT_START_PLAY_FADE", 2);
            EFFECT_TURNTO_PLAY = new EFFECT("EFFECT_TURNTO_PLAY", 3);
            EFFECT_STOP_PLAY_SLIDE = new EFFECT("EFFECT_STOP_PLAY_SLIDE", 4);
            EFFECT_STOP_PLAY_FADE = new EFFECT("EFFECT_STOP_PLAY_FADE", 5);
            EFFECT aeffect[] = new EFFECT[6];
            aeffect[0] = EFFECT_NONE;
            aeffect[1] = EFFECT_START_PLAY_SLIDE;
            aeffect[2] = EFFECT_START_PLAY_FADE;
            aeffect[3] = EFFECT_TURNTO_PLAY;
            aeffect[4] = EFFECT_STOP_PLAY_SLIDE;
            aeffect[5] = EFFECT_STOP_PLAY_FADE;
            $VALUES = aeffect;
        }

        private EFFECT(String s, int i)
        {
            super(s, i);
        }
    }

}
