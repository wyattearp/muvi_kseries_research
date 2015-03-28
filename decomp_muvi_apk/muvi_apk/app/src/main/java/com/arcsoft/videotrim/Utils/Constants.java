// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.arcsoft.videotrim.Utils;


public class Constants
{
    public static final class SDCardEvent extends Enum
    {

        private static final SDCardEvent $VALUES[];
        public static final SDCardEvent BAD_REMOVAL;
        public static final SDCardEvent EJECT;
        public static final SDCardEvent MOUNTED;
        public static final SDCardEvent REMOVED;
        public static final SDCardEvent SCANNER_FINISHED;
        public static final SDCardEvent SCANNER_STARTED;
        public static final SDCardEvent SHARED;
        public static final SDCardEvent UNMOUNTED;

        public static SDCardEvent valueOf(String s)
        {
            return (SDCardEvent)Enum.valueOf(com/arcsoft/videotrim/Utils/Constants$SDCardEvent, s);
        }

        public static SDCardEvent[] values()
        {
            return (SDCardEvent[])$VALUES.clone();
        }

        static 
        {
            MOUNTED = new SDCardEvent("MOUNTED", 0);
            UNMOUNTED = new SDCardEvent("UNMOUNTED", 1);
            REMOVED = new SDCardEvent("REMOVED", 2);
            BAD_REMOVAL = new SDCardEvent("BAD_REMOVAL", 3);
            SHARED = new SDCardEvent("SHARED", 4);
            EJECT = new SDCardEvent("EJECT", 5);
            SCANNER_STARTED = new SDCardEvent("SCANNER_STARTED", 6);
            SCANNER_FINISHED = new SDCardEvent("SCANNER_FINISHED", 7);
            SDCardEvent asdcardevent[] = new SDCardEvent[8];
            asdcardevent[0] = MOUNTED;
            asdcardevent[1] = UNMOUNTED;
            asdcardevent[2] = REMOVED;
            asdcardevent[3] = BAD_REMOVAL;
            asdcardevent[4] = SHARED;
            asdcardevent[5] = EJECT;
            asdcardevent[6] = SCANNER_STARTED;
            asdcardevent[7] = SCANNER_FINISHED;
            $VALUES = asdcardevent;
        }

        private SDCardEvent(String s, int i)
        {
            super(s, i);
        }
    }


    public static final int DEFAULT_IMAGE_CLIP_DURATION = 2000;
    public static final String KEY_DISKCHANGE_ABOUT_TO_REMOVE = "diskchange_about_to_remove";
    public static final String KEY_DISKCHANGE_CARD_NAME = "diskchange_card_name";
    public static final String KEY_DISKCHANGE_EVENTID = "diskchange_eventid";
    public static final String KEY_DISKCHANGE_REMOVE_COMPLETE = "diskchange_remove_complete";
    public static final String KEY_FILECHANGE_EVENTID = "filechange_eventid";
    public static final String KEY_FILECHANGE_ITEM_NAME = "filechange_item_name";
    public static final String KEY_FILECHANGE_ITEM_NAME_2 = "filechange_item_name_2";
    public static final String KEY_MEDIA_SCANNER_FINISHED = "media scanner finished";
    public static final String KEY_MEDIA_SCANNER_STARTED = "media scanner started";
    public static int LOAD_STORY_ANIM_DURATION = 0;
    public static final int MAX_IMAGE_CLIP_DURATION = 10000;
    public static final String MVE_TMP_SAVEFILENAME = "mve_tmp_file_arcsoft_mgl_888";
    public static final int RESOL_1080P_CX = 1080;
    public static final int RESOL_1080P_CY = 1920;
    public static final int RESOL_320X180_CX = 180;
    public static final int RESOL_320X180_CY = 320;
    public static final int RESOL_320X480_CX = 320;
    public static final int RESOL_320X480_CY = 480;
    public static final int RESOL_360X640_CX = 360;
    public static final int RESOL_360X640_CY = 640;
    public static final int RESOL_480X854_CX = 480;
    public static final int RESOL_480X854_CY = 856;
    public static final int RESOL_720P_CX = 720;
    public static final int RESOL_720P_CY = 1280;
    public static final int RESOL_AUTO_CX = 0;
    public static final int RESOL_AUTO_CY = 0;
    public static final int RESOL_CIF_CX = 288;
    public static final int RESOL_CIF_CY = 352;
    public static final int RESOL_DONE_CX = 480;
    public static final int RESOL_DONE_CY = 720;
    public static final int RESOL_MAX_CX = 1088;
    public static final int RESOL_MAX_CY = 1920;
    public static final int RESOL_QCIF_CX = 144;
    public static final int RESOL_QCIF_CY = 176;
    public static final int RESOL_QVGA_CX = 240;
    public static final int RESOL_QVGA_CY = 320;
    public static final int RESOL_SQUARE320_CX = 320;
    public static final int RESOL_SQUARE320_CY = 320;
    public static final int RESOL_VGA_CX = 480;
    public static final int RESOL_VGA_CY = 640;
    public static final int RESOL_WQVGA_CX = 240;
    public static final int RESOL_WQVGA_CY = 400;
    public static final int RESOL_WVGA_CX = 480;
    public static final int RESOL_WVGA_CY = 800;
    public static final int SYSEVENT_ID_DISKCHANGE = 1;
    public static final int SYSEVENT_ID_FILECHANGE = 2;
    public static final int SYSEVENT_ID_MEDIA_SCANNER = 3;
    public static final int SYSEVENT_ID_NONE = 0;
    public static final int UMWP_DEVICEARRIVAL = 1;
    public static final int UMWP_DEVICEREMOVECOMPLETE = 2;
    public static final int UMWP_INTERNALCARDABOUT2REMOVE = 3;
    public static final int UMWP_MEMORYCARDABOUT2REMOVE = 4;
    public static final long UM_DELETE = 2L;
    public static final long UM_RENAMEFOLDER = 4L;
    public static final long UM_RENAMEITEM = 1L;
    public static final long UM_RMDIR = 3L;

    public Constants()
    {
    }

    static 
    {
        LOAD_STORY_ANIM_DURATION = 400;
    }
}
