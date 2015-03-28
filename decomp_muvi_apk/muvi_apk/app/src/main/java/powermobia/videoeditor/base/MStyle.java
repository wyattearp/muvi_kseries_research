// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package powermobia.videoeditor.base;

import powermobia.ve.utils.MBitmap;
import powermobia.ve.utils.MRect;
import powermobia.videoeditor.MEngine;
import powermobia.videoeditor.storyboard.MStoryboard;

// Referenced classes of package powermobia.videoeditor.base:
//            MSession, ISessionStateListener

public class MStyle
{
    public static class MFinder
    {

        private long handle;

        private native int nativeCreate(String s, int i, String s1);

        private native int nativeDestroy();

        private native int nativeGetCount(long l);

        private native String nativeGetFileName(long l, int i);

        private native int nativeUpdate(long l);

        public int create(String s, int i, String s1)
        {
            if (s == null)
            {
                return 0x80003;
            }
            if (handle != 0L)
            {
                destroy();
            }
            return nativeCreate(s, i, s1);
        }

        public int destroy()
        {
            if (handle == 0L)
            {
                return 0x80006;
            } else
            {
                return nativeDestroy();
            }
        }

        public int getCount()
        {
            if (handle == 0L)
            {
                return 0x80006;
            } else
            {
                return nativeGetCount(handle);
            }
        }

        public String getFileName(int i)
        {
            if (handle == 0L)
            {
                return null;
            } else
            {
                return nativeGetFileName(handle, i);
            }
        }

        public int update()
        {
            if (handle == 0L)
            {
                return 0x80006;
            } else
            {
                return nativeUpdate(handle);
            }
        }

        public MFinder()
        {
            handle = 0L;
        }
    }

    public static class MTextInfo
    {

        public static final int TEXTINFO_ALIGNMENT = 2;
        public static final int TEXTINFO_ALIGNMENT_BOTTOM = 8;
        public static final int TEXTINFO_ALIGNMENT_LEFT = 1;
        public static final int TEXTINFO_ALIGNMENT_MIDDLE = 16;
        public static final int TEXTINFO_ALIGNMENT_RIGHT = 2;
        public static final int TEXTINFO_ALIGNMENT_TOP = 4;
        public static final int TEXTINFO_COLOR = 1;
        public static final int TEXTINFO_EDITABLE = 5;
        public static final int TEXTINFO_EDITABLE_MASK_ALIGNMENT = 8;
        public static final int TEXTINFO_EDITABLE_MASK_COLOR = 4;
        public static final int TEXTINFO_EDITABLE_MASK_REGION = 1;
        public static final int TEXTINFO_EDITABLE_MASK_ROTATE = 16;
        public static final int TEXTINFO_EDITABLE_MASK_SIZE = 2;
        public static final int TEXTINFO_EDITABLE_MASK_TEXTALL = 31;
        public static final int TEXTINFO_ROTATE = 3;
        public static final int TEXTINFO_SIZE = 0;
        public static final int TEXTINFO_TIMESTAMP = 4;
        private int alignment;
        private int color;
        private int editable;
        private MRect region;
        private int rotate;
        private int size;
        private int timestamp;
        private String words;

        public int GetTextInfo(int i)
        {
            switch (i)
            {
            default:
                return 0;

            case 0: // '\0'
                return size;

            case 1: // '\001'
                return color;

            case 2: // '\002'
                return alignment;

            case 3: // '\003'
                return rotate;

            case 4: // '\004'
                return timestamp;

            case 5: // '\005'
                return editable;
            }
        }

        public MRect GetTextRect()
        {
            return region;
        }

        public String GetTextWords()
        {
            return words;
        }

        public MTextInfo()
        {
            region = null;
            size = 0;
            color = 0;
            alignment = 0;
            rotate = 0;
            timestamp = 0;
            editable = 0;
            words = null;
        }

        public MTextInfo(MTextInfo mtextinfo)
        {
            if (mtextinfo != null)
            {
                region = mtextinfo.region;
                size = mtextinfo.size;
                color = mtextinfo.color;
                alignment = mtextinfo.alignment;
                rotate = mtextinfo.rotate;
                timestamp = mtextinfo.timestamp;
                editable = mtextinfo.editable;
                words = mtextinfo.words;
            }
        }
    }


    public static final int MODE_MASK_ALL = 63;
    public static final int MODE_MASK_AUTOCUT = 2;
    public static final int MODE_MASK_BACKCOVER = 0x20000000;
    public static final int MODE_MASK_EFFECT = 8;
    public static final int MODE_MASK_FRONTCOVER = 0x10000000;
    public static final int MODE_MASK_MSS = 1;
    public static final int MODE_MASK_NONE = 0;
    public static final int MODE_MASK_TEXT_FRAME = 16;
    public static final int MODE_MASK_THEME = 32;
    public static final int MODE_MASK_TRANSITION = 4;
    public static final int TRANSITION_DURATION_UNEDITABLE = 0;
    public static final int TRANSITION_EDITABLE = 1;
    private long handle;

    public MStyle()
    {
        handle = 0L;
    }

    public static int getVersion()
    {
        return nativeGetVersion();
    }

    private native int nativeCreate(String s, String s1);

    private native int nativeDestroy();

    private native String nativeGetDescription(long l, int i);

    private native int nativeGetID(long l);

    private native int nativeGetMode(long l);

    private native int nativeGetPreviewData(long l, MStoryboard mstoryboard);

    private native MTextInfo[] nativeGetTextInfo(long l);

    private native MRect[] nativeGetTextRegion(long l);

    private native int[] nativeGetTextTimeStamp(long l);

    private native int nativeGetThumbnail(MEngine mengine, long l, MBitmap mbitmap);

    private native String nativeGetTitle(long l, int i);

    private native int nativeGetTransDuration(long l);

    private native int nativeGetTransEditable(long l);

    private static native int nativeGetVersion();

    public int create(String s, String s1)
    {
        if (s == null)
        {
            return 0x80003;
        }
        if (handle != 0L)
        {
            destroy();
        }
        return nativeCreate(s, s1);
    }

    public int destroy()
    {
        if (handle == 0L)
        {
            return 0x80006;
        } else
        {
            return nativeDestroy();
        }
    }

    public String getDescription(int i)
    {
        if (handle == 0L)
        {
            return null;
        } else
        {
            return nativeGetDescription(handle, i);
        }
    }

    public int getID()
    {
        if (handle == 0L)
        {
            return 0x80006;
        } else
        {
            return nativeGetID(handle);
        }
    }

    public int getMode()
    {
        if (handle == 0L)
        {
            return 0x80006;
        } else
        {
            return nativeGetMode(handle);
        }
    }

    public int getPreviewData(MStoryboard mstoryboard, ISessionStateListener isessionstatelistener)
    {
        if (handle == 0L)
        {
            return 0x80006;
        }
        if (mstoryboard == null || isessionstatelistener == null)
        {
            return 0x80003;
        } else
        {
            mstoryboard.setSessionStateListener(isessionstatelistener);
            return nativeGetPreviewData(handle, mstoryboard);
        }
    }

    public MTextInfo[] getTextInfo()
    {
        if (handle == 0L)
        {
            return null;
        } else
        {
            return nativeGetTextInfo(handle);
        }
    }

    public MRect[] getTextRegion()
    {
        if (handle == 0L)
        {
            return null;
        } else
        {
            return nativeGetTextRegion(handle);
        }
    }

    public int[] getTextTimeStamp()
    {
        if (handle == 0L)
        {
            return null;
        } else
        {
            return nativeGetTextTimeStamp(handle);
        }
    }

    public int getThumbnail(MEngine mengine, MBitmap mbitmap)
    {
        if (handle == 0L)
        {
            return 0x80006;
        }
        if (mengine == null || mbitmap == null)
        {
            return 0x80003;
        } else
        {
            return nativeGetThumbnail(mengine, handle, mbitmap);
        }
    }

    public String getTitle(int i)
    {
        if (handle == 0L)
        {
            return null;
        } else
        {
            return nativeGetTitle(handle, i);
        }
    }

    public int getTransitionDuration()
    {
        if (handle == 0L)
        {
            return 0x80006;
        } else
        {
            return nativeGetTransDuration(handle);
        }
    }

    public int getTransitionEditable()
    {
        if (handle == 0L)
        {
            return 0x80006;
        } else
        {
            return nativeGetTransEditable(handle);
        }
    }
}
