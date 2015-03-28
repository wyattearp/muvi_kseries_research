// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package powermobia.videoeditor.storyboard;

import powermobia.videoeditor.MEngine;
import powermobia.videoeditor.base.ISessionStateListener;
import powermobia.videoeditor.base.MSession;
import powermobia.videoeditor.clip.MClip;

// Referenced classes of package powermobia.videoeditor.storyboard:
//            IThemeOperationListener, MThemeOperation

public class MStoryboard extends MSession
{

    public static final int DEFAULT_THEME_ID = 0x2fbe4a40;
    public static final int PROP_AUTO_APPLY_THEME = 16387;
    public static final int PROP_BACK_COVER = 16390;
    private static final int PROP_BASE = 16384;
    public static final int PROP_COVER = 16389;
    public static final int PROP_FIT_TRACK = 16385;
    public static final int PROP_THEME_BACK_COVER = 16393;
    public static final int PROP_THEME_COVER = 16392;
    public static final int PROP_THEME_ID = 16394;
    public static final int PROP_THEME_TEMPLATE = 16391;
    private MThemeOperation themeOPData;
    private IThemeOperationListener themeOPListener;

    public MStoryboard()
    {
        themeOPListener = null;
        themeOPData = null;
    }

    private native int nativeApplyTheme(MStoryboard mstoryboard, String s);

    private native int nativeCreate(MEngine mengine, MStoryboard mstoryboard);

    private native int nativeDestroy(MStoryboard mstoryboard);

    private native int nativeDuplicate(MStoryboard mstoryboard, MStoryboard mstoryboard1);

    private native MClip nativeGetClip(long l, int i);

    private native int nativeGetClipCount(long l);

    private native MClip nativeGetDataClip(long l);

    private native int nativeGetDuration(long l);

    private native int nativeInsertClip(long l, MClip mclip, int i);

    private native int nativeLoadProject(MStoryboard mstoryboard, String s);

    private native int nativeMoveClip(long l, MClip mclip, int i);

    private native int nativeRemoveAllClip(long l);

    private native int nativeRemoveClip(long l, MClip mclip);

    private native int nativeSaveProject(MStoryboard mstoryboard, String s);

    private int onThemeOperation(MThemeOperation mthemeoperation)
    {
        if (themeOPListener == null)
        {
            return 0;
        } else
        {
            return themeOPListener.onThemeOperation(themeOPData);
        }
    }

    public int applyTheme(String s, ISessionStateListener isessionstatelistener)
    {
        if (0L == handle)
        {
            return 0x80006;
        } else
        {
            listener = isessionstatelistener;
            return nativeApplyTheme(this, s);
        }
    }

    public int duplicate(MStoryboard mstoryboard)
    {
        return nativeDuplicate(this, mstoryboard);
    }

    public MClip getClip(int i)
    {
        if (0L == handle)
        {
            return null;
        } else
        {
            return nativeGetClip(handle, i);
        }
    }

    public int getClipCount()
    {
        if (0L == handle)
        {
            return 0x80006;
        } else
        {
            return nativeGetClipCount(handle);
        }
    }

    public MClip getDataClip()
    {
        if (0L == handle)
        {
            return null;
        } else
        {
            return nativeGetDataClip(handle);
        }
    }

    public int getDuration()
    {
        if (0L == handle)
        {
            return 0x80006;
        } else
        {
            return nativeGetDuration(handle);
        }
    }

    public int init(MEngine mengine, ISessionStateListener isessionstatelistener)
    {
        super.init(mengine, isessionstatelistener);
        return nativeCreate(mengine, this);
    }

    public int insertClip(MClip mclip, int i)
    {
        if (0L == handle)
        {
            return 0x80006;
        } else
        {
            return nativeInsertClip(handle, mclip, i);
        }
    }

    public int loadProject(String s, ISessionStateListener isessionstatelistener)
    {
        if (0L == handle)
        {
            return 0x80006;
        } else
        {
            listener = isessionstatelistener;
            return nativeLoadProject(this, s);
        }
    }

    public int moveClip(MClip mclip, int i)
    {
        if (0L == handle)
        {
            return 0x80006;
        } else
        {
            return nativeMoveClip(handle, mclip, i);
        }
    }

    public int removeAllClip()
    {
        if (0L == handle)
        {
            return 0x80006;
        } else
        {
            return nativeRemoveAllClip(handle);
        }
    }

    public int removeClip(MClip mclip)
    {
        if (0L == handle)
        {
            return 0x80006;
        } else
        {
            return nativeRemoveClip(handle, mclip);
        }
    }

    public int saveProject(String s, ISessionStateListener isessionstatelistener)
    {
        if (0L == handle)
        {
            return 0x80006;
        } else
        {
            listener = isessionstatelistener;
            return nativeSaveProject(this, s);
        }
    }

    public int setThemeOperationListener(IThemeOperationListener ithemeoperationlistener)
    {
        themeOPListener = ithemeoperationlistener;
        return 0;
    }

    public int unInit()
    {
        if (0L == handle)
        {
            return 0x80006;
        } else
        {
            return nativeDestroy(this);
        }
    }
}
