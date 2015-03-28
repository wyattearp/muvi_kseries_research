// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package powermobia.ve.utils;


public final class MColorSpace
{

    static final int MPAF_16BITS = 0x5000000;
    static final int MPAF_1BITS = 0x1000000;
    static final int MPAF_24BITS = 0x6000000;
    static final int MPAF_2BITS = 0x2000000;
    static final int MPAF_32BITS = 0x7000000;
    static final int MPAF_4BITS = 0x3000000;
    static final int MPAF_8BITS = 0x4000000;
    static final int MPAF_BGR = 4096;
    static final int MPAF_BT601_YCBCR = 4096;
    static final int MPAF_BT601_YUV = 0;
    static final int MPAF_BT709_YCBCR = 12288;
    static final int MPAF_BT709_YUV = 8192;
    public static final int MPAF_GRAY1 = 0x61000000;
    public static final int MPAF_GRAY16 = 0x65000000;
    public static final int MPAF_GRAY2 = 0x62000000;
    public static final int MPAF_GRAY4 = 0x63000000;
    public static final int MPAF_GRAY8 = 0x64000000;
    static final int MPAF_GRAY_BASE = 0x60000000;
    public static final int MPAF_I420 = 0;
    public static final int MPAF_I422H = 0;
    public static final int MPAF_I422V = 0;
    public static final int MPAF_I444 = 0;
    static final int MPAF_OTHERS = 0x70000000;
    public static final int MPAF_OTHERS_DCT = 0x70000001;
    public static final int MPAF_OTHERS_NV21 = 0x70000002;
    public static final int MPAF_RGB16_B4G4R4 = 0;
    public static final int MPAF_RGB16_B5G5R5 = 0;
    public static final int MPAF_RGB16_B5G6R5 = 0;
    public static final int MPAF_RGB16_R4G4B4 = 0;
    public static final int MPAF_RGB16_R5G5B5 = 0;
    public static final int MPAF_RGB16_R5G6B5 = 0;
    public static final int MPAF_RGB16_TR5G5B5 = 0;
    static final int MPAF_RGB1_PAL = 0x41000000;
    public static final int MPAF_RGB24_B6G6R6 = 0;
    public static final int MPAF_RGB24_B8G8R8 = 0;
    public static final int MPAF_RGB24_R6G6B6 = 0;
    public static final int MPAF_RGB24_R8G8B8 = 0;
    public static final int MPAF_RGB24_TR6G6B6 = 0;
    public static final int MPAF_RGB32_A8R8G8B8 = 0;
    public static final int MPAF_RGB32_B8G8R8 = 0;
    public static final int MPAF_RGB32_B8G8R8A8 = 0;
    public static final int MPAF_RGB32_R8G8B8 = 0;
    static final int MPAF_RGB4_PAL = 0x43000000;
    static final int MPAF_RGB8_PAL = 0x44000000;
    static final int MPAF_RGBA_BASE = 0x30000000;
    static final int MPAF_RGBP_BASE = 0x40000000;
    static final int MPAF_RGBT_BASE = 0x20000000;
    static final int MPAF_RGB_BASE = 0x10000000;
    public static final int MPAF_UVY = 0;
    public static final int MPAF_UYVY = 0;
    public static final int MPAF_UYVY2 = 0;
    public static final int MPAF_VUY = 0;
    public static final int MPAF_VYUY = 0;
    public static final int MPAF_VYUY2 = 0;
    public static final int MPAF_YUV = 0;
    static final int MPAF_YUV_BASE = 0x50000000;
    static final int MPAF_YUV_PLANAR = 2048;
    static final int MPAF_YUV_UVY = 1024;
    static final int MPAF_YUV_VU = 512;
    static final int MPAF_YUV_Y1Y0 = 256;
    public static final int MPAF_YUYV;
    public static final int MPAF_YUYV2;
    public static final int MPAF_YV12 = 0x200 | MPAF_I420;
    public static final int MPAF_YV16H = 0x200 | MPAF_I422H;
    public static final int MPAF_YV16V = 0x200 | MPAF_I422V;
    public static final int MPAF_YV24 = 0x200 | MPAF_I444;
    public static final int MPAF_YVU = 0x200 | MPAF_YUV;
    public static final int MPAF_YVYU;
    public static final int MPAF_YVYU2;

    public MColorSpace()
    {
    }

    static final int MPAF_MAKE_B(int i)
    {
        return i - 1 << 0;
    }

    static final int MPAF_MAKE_G(int i)
    {
        return i - 1 << 4;
    }

    static final int MPAF_MAKE_H(int i)
    {
        return i - 1 << 4;
    }

    static final int MPAF_MAKE_R(int i)
    {
        return i - 1 << 8;
    }

    static final int MPAF_MAKE_V(int i)
    {
        return i - 1 << 0;
    }

    public static final int MRGB(int i, int j, int k)
    {
        return (k & 0xff) << 16 | (j & 0xff) << 8 | i & 0xff;
    }

    public static final int MRGBA(int i, int j, int k, int l)
    {
        return (l & 0xff) << 24 | (k & 0xff) << 16 | (j & 0xff) << 8 | i & 0xff;
    }

    public static final int MRGBA_A(int i)
    {
        return 0xff & i >> 24;
    }

    public static final int MRGB_B(int i)
    {
        return 0xff & i >> 16;
    }

    public static final int MRGB_G(int i)
    {
        return 0xff & i >> 8;
    }

    public static final int MRGB_R(int i)
    {
        return i & 0xff;
    }

    public static final int MakeARGB(int i, int j, int k, int l)
    {
        return (i & 0xff) << 24 | (j & 0xff) << 16 | (k & 0xff) << 8 | l & 0xff;
    }

    public static final int MakeRGB(int i, int j, int k)
    {
        return (i & 0xff) << 16 | (j & 0xff) << 8 | k & 0xff;
    }

    static 
    {
        MPAF_RGB16_R5G6B5 = 0x15000000 | MPAF_MAKE_R(5) | MPAF_MAKE_G(6) | MPAF_MAKE_B(5);
        MPAF_RGB16_R5G5B5 = 0x15000000 | MPAF_MAKE_R(5) | MPAF_MAKE_G(5) | MPAF_MAKE_B(5);
        MPAF_RGB16_R4G4B4 = 0x15000000 | MPAF_MAKE_R(4) | MPAF_MAKE_G(4) | MPAF_MAKE_B(4);
        MPAF_RGB16_TR5G5B5 = 0x25000000 | MPAF_MAKE_R(5) | MPAF_MAKE_G(5) | MPAF_MAKE_B(5);
        MPAF_RGB16_B5G6R5 = 0x1000 | MPAF_RGB16_R5G6B5;
        MPAF_RGB16_B5G5R5 = 0x1000 | MPAF_RGB16_R5G5B5;
        MPAF_RGB16_B4G4R4 = 0x1000 | MPAF_RGB16_R4G4B4;
        MPAF_RGB24_R8G8B8 = 0x16000000 | MPAF_MAKE_R(8) | MPAF_MAKE_G(8) | MPAF_MAKE_B(8);
        MPAF_RGB24_R6G6B6 = 0x16000000 | MPAF_MAKE_R(6) | MPAF_MAKE_G(6) | MPAF_MAKE_B(6);
        MPAF_RGB24_TR6G6B6 = 0x26000000 | MPAF_MAKE_R(5) | MPAF_MAKE_G(5) | MPAF_MAKE_B(5);
        MPAF_RGB24_B8G8R8 = 0x1000 | MPAF_RGB24_R8G8B8;
        MPAF_RGB24_B6G6R6 = 0x1000 | MPAF_RGB24_R6G6B6;
        MPAF_RGB32_R8G8B8 = 0x17000000 | MPAF_MAKE_R(8) | MPAF_MAKE_G(8) | MPAF_MAKE_B(8);
        MPAF_RGB32_A8R8G8B8 = 0x37000000 | MPAF_MAKE_R(8) | MPAF_MAKE_G(8) | MPAF_MAKE_B(8);
        MPAF_RGB32_B8G8R8 = 0x1000 | MPAF_RGB32_R8G8B8;
        MPAF_RGB32_B8G8R8A8 = 0x1000 | MPAF_RGB32_A8R8G8B8;
        MPAF_YUV = 0x50000000 | MPAF_MAKE_H(1) | MPAF_MAKE_V(1);
        MPAF_UVY = 0x400 | MPAF_YUV;
        MPAF_VUY = 0x400 | (0x200 | MPAF_YUV);
        MPAF_YUYV = 0x50000000 | MPAF_MAKE_H(2) | MPAF_MAKE_V(1);
        MPAF_YVYU = 0x200 | MPAF_YUYV;
        MPAF_UYVY = 0x400 | MPAF_YUYV;
        MPAF_VYUY = 0x400 | (0x200 | MPAF_YUYV);
        MPAF_YUYV2 = 0x100 | MPAF_YUYV;
        MPAF_YVYU2 = 0x100 | MPAF_YVYU;
        MPAF_UYVY2 = 0x100 | MPAF_UYVY;
        MPAF_VYUY2 = 0x100 | MPAF_VYUY;
        MPAF_I420 = 0x50000800 | MPAF_MAKE_H(2) | MPAF_MAKE_V(2);
        MPAF_I422V = 0x50000800 | MPAF_MAKE_H(1) | MPAF_MAKE_V(2);
        MPAF_I422H = 0x50000800 | MPAF_MAKE_H(2) | MPAF_MAKE_V(1);
        MPAF_I444 = 0x50000800 | MPAF_MAKE_H(1) | MPAF_MAKE_V(1);
    }
}
