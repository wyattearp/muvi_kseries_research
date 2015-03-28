// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.arcsoft.mediaplus.dmc;

import android.content.Context;
import android.content.SharedPreferences;
import com.arcsoft.mediaplus.setting.DigitalMediaItem;

public class DmcUtils
{
    public static final class COVER_TYPE extends Enum
    {

        private static final COVER_TYPE $VALUES[];
        public static final COVER_TYPE TYPE_THUMBNAIL;
        public static final COVER_TYPE TYPE_TV_COVER;

        public static COVER_TYPE valueOf(String s)
        {
            return (COVER_TYPE)Enum.valueOf(com/arcsoft/mediaplus/dmc/DmcUtils$COVER_TYPE, s);
        }

        public static COVER_TYPE[] values()
        {
            return (COVER_TYPE[])$VALUES.clone();
        }

        static 
        {
            TYPE_THUMBNAIL = new COVER_TYPE("TYPE_THUMBNAIL", 0);
            TYPE_TV_COVER = new COVER_TYPE("TYPE_TV_COVER", 1);
            COVER_TYPE acover_type[] = new COVER_TYPE[2];
            acover_type[0] = TYPE_THUMBNAIL;
            acover_type[1] = TYPE_TV_COVER;
            $VALUES = acover_type;
        }

        private COVER_TYPE(String s, int i)
        {
            super(s, i);
        }
    }

    public static final class PROVIDER_TYPE extends Enum
    {

        private static final PROVIDER_TYPE $VALUES[];
        public static final PROVIDER_TYPE TYPE_FROM_LARGE_VIEW;
        public static final PROVIDER_TYPE TYPE_FROM_MULTI_VIEW;

        public static PROVIDER_TYPE valueOf(String s)
        {
            return (PROVIDER_TYPE)Enum.valueOf(com/arcsoft/mediaplus/dmc/DmcUtils$PROVIDER_TYPE, s);
        }

        public static PROVIDER_TYPE[] values()
        {
            return (PROVIDER_TYPE[])$VALUES.clone();
        }

        static 
        {
            TYPE_FROM_LARGE_VIEW = new PROVIDER_TYPE("TYPE_FROM_LARGE_VIEW", 0);
            TYPE_FROM_MULTI_VIEW = new PROVIDER_TYPE("TYPE_FROM_MULTI_VIEW", 1);
            PROVIDER_TYPE aprovider_type[] = new PROVIDER_TYPE[2];
            aprovider_type[0] = TYPE_FROM_LARGE_VIEW;
            aprovider_type[1] = TYPE_FROM_MULTI_VIEW;
            $VALUES = aprovider_type;
        }

        private PROVIDER_TYPE(String s, int i)
        {
            super(s, i);
        }
    }

    public static final class TV_STATUS extends Enum
    {

        private static final TV_STATUS $VALUES[];
        public static final TV_STATUS TV_PLAY_STARTUS_PLAYING;
        public static final TV_STATUS TV_PLAY_STARTUS_PLAYLIST;

        public static TV_STATUS valueOf(String s)
        {
            return (TV_STATUS)Enum.valueOf(com/arcsoft/mediaplus/dmc/DmcUtils$TV_STATUS, s);
        }

        public static TV_STATUS[] values()
        {
            return (TV_STATUS[])$VALUES.clone();
        }

        static 
        {
            TV_PLAY_STARTUS_PLAYING = new TV_STATUS("TV_PLAY_STARTUS_PLAYING", 0);
            TV_PLAY_STARTUS_PLAYLIST = new TV_STATUS("TV_PLAY_STARTUS_PLAYLIST", 1);
            TV_STATUS atv_status[] = new TV_STATUS[2];
            atv_status[0] = TV_PLAY_STARTUS_PLAYING;
            atv_status[1] = TV_PLAY_STARTUS_PLAYLIST;
            $VALUES = atv_status;
        }

        private TV_STATUS(String s, int i)
        {
            super(s, i);
        }
    }


    public static final int DURATION_DMC_BUFFERING = 40000;
    public static final int DURATION_IMAGE_DISPLAYING = 5000;
    public static final int INTERNAL_BUFFER_TIMER = 50;
    public static final int INTERVAL_DISMISS_RENDER_SELECTOR = 200;
    public static final int INTERVAL_IMAGE_TIMER = 50;
    public static final String KEY_ALWAYS_PLAY_2_RENDER = "alway_play_to";
    public static final String KEY_DEFAULT_RENDER_NAME = "default_render_name";
    public static final String KEY_DEFAULT_RENDER_UDN = "default_render_udn";
    public static final String KEY_RESET_DEFAULT_RENDER = "reset_default_render";
    public static final String KEY_START_PLAY_FROM_INDEX = "play_from_index";
    public static final int MSG_BASE = 0;
    public static final int MSG_DECODE_FINISH = 4;
    public static final int MSG_DISMISS_RENDER_SELECTOR = 1;
    public static final int MSG_DLNA_FILE_SERVER_DISMISS = 19;
    public static final int MSG_DLNA_FILE_SERVER_ENABLE_FAILED = 21;
    public static final int MSG_DLNA_FILE_SERVER_ENABLE_SUCCESS = 20;
    public static final int MSG_DMR_TOAST_DATASOURCE_DISMISS = 13;
    public static final int MSG_DMR_TOAST_ERROR_OPEN = 17;
    public static final int MSG_DMR_TOAST_ERROR_PLAY = 16;
    public static final int MSG_DMR_TOAST_ERROR_RENDER_DISCONNECTED = 14;
    public static final int MSG_DMR_TOAST_ERROR_UNSUPPORT = 15;
    public static final int MSG_DMR_TOAST_ERROR_URL_NULL = 18;
    public static final int MSG_DMR_TOAST_STOP = 12;
    public static final int MSG_DRM_BUFFERING_LONG = 22;
    public static final int MSG_IMAGE_DISPLAYING = 3;
    public static final int MSG_IMAGE_DISP_TIMER_INIT = 2;
    public static final int MSG_IMAGE_DISP_TIMER_PAUSE = 10;
    public static final int MSG_IMAGE_DISP_TIMER_RESUME = 11;
    public static final int MSG_PLAYBACK_FINISH = 9;
    public static final int MSG_RENDER_ADPTER_CHANGED = 5;
    public static final int MSG_RENDER_NOT_FOUND = 6;
    public static final int MSG_RENDER_OFFLINE = 8;
    public static final int MSG_RENDER_ONLINE = 7;
    public static final String TAG = "Dmc";

    public DmcUtils()
    {
    }

    public static boolean alwaysPlay2DefaultRender(Context context)
    {
        boolean flag;
        try
        {
            flag = context.getSharedPreferences(context.getPackageName(), 1).getBoolean("alway_play_to", false);
        }
        catch (Exception exception)
        {
            return false;
        }
        return flag;
    }

    public static String getDefaultRenderName(Context context)
    {
        String s;
        try
        {
            s = context.getSharedPreferences(context.getPackageName(), 1).getString("default_render_name", null);
        }
        catch (Exception exception)
        {
            exception.printStackTrace();
            return null;
        }
        return s;
    }

    public static String getDefaultRenderUDN(Context context)
    {
        String s;
        try
        {
            s = context.getSharedPreferences(context.getPackageName(), 1).getString("default_render_udn", null);
        }
        catch (Exception exception)
        {
            exception.printStackTrace();
            return null;
        }
        return s;
    }

    public static boolean getStatusOfRenderResetSwitcher(Context context)
    {
        boolean flag;
        try
        {
            flag = context.getSharedPreferences(context.getPackageName(), 1).getBoolean("reset_default_render", false);
        }
        catch (Exception exception)
        {
            return false;
        }
        return flag;
    }

    public static void resetDefaultRender(Context context)
    {
        try
        {
            SharedPreferences sharedpreferences = context.getSharedPreferences(context.getPackageName(), 1);
            if (sharedpreferences.contains("default_render_name"))
            {
                sharedpreferences.edit().remove("default_render_name").commit();
            }
            if (sharedpreferences.contains("default_render_udn"))
            {
                sharedpreferences.edit().remove("default_render_udn").commit();
            }
            if (sharedpreferences.contains("alway_play_to"))
            {
                sharedpreferences.edit().remove("alway_play_to").commit();
            }
            return;
        }
        catch (Exception exception)
        {
            return;
        }
    }

    public static void saveDefaultRenderInfo(Context context, DigitalMediaItem digitalmediaitem)
    {
        try
        {
            SharedPreferences sharedpreferences = context.getSharedPreferences(context.getPackageName(), 1);
            sharedpreferences.edit().putString("default_render_name", digitalmediaitem.name).commit();
            sharedpreferences.edit().putString("default_render_udn", digitalmediaitem.udn).commit();
            sharedpreferences.edit().putBoolean("alway_play_to", true).commit();
            return;
        }
        catch (Exception exception)
        {
            exception.printStackTrace();
        }
    }

    public static void setStatusOfRenderResetSwitcher(Context context, boolean flag)
    {
        try
        {
            context.getSharedPreferences(context.getPackageName(), 1).edit().putBoolean("reset_default_render", flag).commit();
            return;
        }
        catch (Exception exception)
        {
            return;
        }
    }
}
