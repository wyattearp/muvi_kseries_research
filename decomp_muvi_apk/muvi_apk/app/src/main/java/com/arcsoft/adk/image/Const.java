// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.arcsoft.adk.image;


public class Const
{
    public static final class ColorSpace extends Enum
    {

        private static final ColorSpace $VALUES[];
        public static final ColorSpace RGB565;
        final int nativeValue;

        public static ColorSpace valueOf(String s)
        {
            return (ColorSpace)Enum.valueOf(com/arcsoft/adk/image/Const$ColorSpace, s);
        }

        public static ColorSpace[] values()
        {
            return (ColorSpace[])$VALUES.clone();
        }

        static 
        {
            RGB565 = new ColorSpace("RGB565", 0, 0x15000454);
            ColorSpace acolorspace[] = new ColorSpace[1];
            acolorspace[0] = RGB565;
            $VALUES = acolorspace;
        }

        private ColorSpace(String s, int i, int j)
        {
            super(s, i);
            nativeValue = j;
        }
    }

    public static final class DisplayMode extends Enum
    {

        private static final DisplayMode $VALUES[];
        public static final DisplayMode BEST_FIT;
        public static final DisplayMode FIT_IN;
        public static final DisplayMode FIT_OUT;
        final int nativeValue;

        public static DisplayMode valueOf(String s)
        {
            return (DisplayMode)Enum.valueOf(com/arcsoft/adk/image/Const$DisplayMode, s);
        }

        public static DisplayMode[] values()
        {
            return (DisplayMode[])$VALUES.clone();
        }

        static 
        {
            FIT_IN = new DisplayMode("FIT_IN", 0, -15);
            FIT_OUT = new DisplayMode("FIT_OUT", 1, -14);
            BEST_FIT = new DisplayMode("BEST_FIT", 2, -13);
            DisplayMode adisplaymode[] = new DisplayMode[3];
            adisplaymode[0] = FIT_IN;
            adisplaymode[1] = FIT_OUT;
            adisplaymode[2] = BEST_FIT;
            $VALUES = adisplaymode;
        }

        private DisplayMode(String s, int i, int j)
        {
            super(s, i);
            nativeValue = j;
        }
    }

    static class NotifyCode
    {

        public static final int DECODER_ERROR = 0xa71001;
        public static final int NO_MEMORY = 0xa71002;
        public static final int PAGE_CHECKED = 0xa71005;
        public static final int PAGE_READY = 0xa71004;
        public static final int THUMB_BLUR = 0xa71003;
        public static final int THUMB_READY = 0xa71000;

        NotifyCode()
        {
        }
    }

    static class ReturnValue
    {

        public static final int INVALID_PARAM = 2;
        public static final int NO_MORE = 0x80002;
        public static final int OK = 0;
        public static final int UNKNOWN = 1;
        public static final int UNSUPPORTED = 3;

        ReturnValue()
        {
        }
    }


    public Const()
    {
    }
}
