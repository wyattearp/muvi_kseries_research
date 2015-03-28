// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package powermobia.photoeditor;

import powermobia.photoeditor.tools.ToolBase;
import powermobia.utils.DisplayContext;
import powermobia.utils.MBitmap;
import powermobia.utils.MPoint;
import powermobia.utils.MRect;
import powermobia.utils.MStream;

public class EditorEngine
{
    public static class EraserStyle
    {

        public int iThickness;

        public EraserStyle()
        {
        }
    }

    public static interface Notify
    {

        public abstract int cbFaceDecteted(int i, int j, int k, int l);

        public abstract int cbNotify(int i, int j);
    }

    public static class State
    {

        public boolean bIsFitIn;
        public int iImgHeight;
        public int iImgWidth;
        public int iRedoSteps;
        public int iUndoSteps;
        public int iZoom;
        public MRect rtImgDisplayRect;

        public State()
        {
        }
    }


    public static final int CLR_TRANSPARENT = -1;
    public static final int COORDINATE_SYS_IMG = 0xa00001;
    public static final int COORDINATE_SYS_SCREEN = 0xa00002;
    private static final int DATASOURCE_BITMAP = 3;
    private static final int DATASOURCE_FILE = 1;
    private static final int DATASOURCE_STREAM = 2;
    public static final int MSG_ASYNC_TASK = 0xa7e001;
    public static final int MSG_PROGRESS = 0xa7d001;
    public static final int PROP_AUTOREFRESH = 0xa60002;
    public static final int PROP_COORDINATE_SYS = 0xa60003;
    public static final int PROP_DISPLAY_ASYNCHRONOUS = 0xa6d00a;
    public static final int PROP_DRAW_REFRESH_STEPS = 0xa6d002;
    public static final int PROP_ERASE_REFRESH_STEPS = 0xa6d003;
    public static final int PROP_FITIN_ZOOM_LEVEL = 0xa6d007;
    public static final int PROP_FITOUT_ZOOM_LEVEL = 0xa6d008;
    public static final int PROP_LIMIT_FREEPAN_RANGE = 0xa6d00c;
    public static final int PROP_MAXRAM = 0xa60001;
    public static final int PROP_MAX_DETECT_FACES = 0xa6d005;
    public static final int PROP_MAX_ZOOM_LEVEL = 0xa6d009;
    public static final int PROP_PAN_FREELY = 0xa6d00b;
    public static final int PROP_SUPPORT_EXIF_ORIENTATION = 0xa60019;
    public static final int PROP_TEMP_FOLDER = 0xa6d004;
    public static final int PROP_UNDO_MAXSTEP = 0xa6d001;
    public static final int PROP_UNDO_MODE = 0xa6d006;
    public static final int TASK_PRIORITY_HIGH = 0xa00020;
    public static final int TASK_PRIORITY_LOW = 0xa00022;
    public static final int TASK_PRIORITY_NORMAL = 0xa00021;
    public static final int UNDO_MODE_COMBINED_STEPS = 2;
    public static final int UNDO_MODE_CUSTOMIZED = 1;
    public static final int UNDO_MODE_STEP_BY_STEP;
    private DisplayContext mDisplayContext;
    private int mNativeEngine;
    private Notify mNotify;
    private powermobia.photoeditor.tools.Overlay.TextInfo mTextInfo;

    public EditorEngine()
    {
        mNotify = null;
        mDisplayContext = null;
        mTextInfo = null;
        mNativeEngine = native_Create();
    }

    private int cbBitBlt(MBitmap mbitmap, MRect mrect)
    {
        if (mDisplayContext != null)
        {
            return mDisplayContext.bitBlt(mbitmap, mrect);
        } else
        {
            return 0;
        }
    }

    private MBitmap cbDrawText(String s)
    {
        if (mTextInfo != null)
        {
            return mTextInfo.cbDrawText(s);
        } else
        {
            return null;
        }
    }

    private int cbEraseBackground(MBitmap mbitmap, MRect mrect)
    {
        if (mDisplayContext != null)
        {
            return mDisplayContext.eraseBackground(mbitmap, mrect);
        } else
        {
            return 0;
        }
    }

    private int cbFaceDecteted(int i, int j, int k, int l)
    {
        if (mNotify != null)
        {
            return mNotify.cbFaceDecteted(i, j, k, l);
        } else
        {
            return 0;
        }
    }

    private int cbFreeText()
    {
        if (mTextInfo != null)
        {
            return mTextInfo.cbFreeText();
        } else
        {
            return 0;
        }
    }

    private int cbNotify(int i, int j)
    {
        if (mNotify != null)
        {
            return mNotify.cbNotify(i, j);
        } else
        {
            return 0;
        }
    }

    private MBitmap cbOffScreenBufferLock(int i, int j)
    {
        if (mDisplayContext != null)
        {
            return mDisplayContext.lockOffScreenBuffer(i, i);
        } else
        {
            return null;
        }
    }

    private int cbOffScreenBufferUnlock(MBitmap mbitmap)
    {
        if (mDisplayContext != null)
        {
            return mDisplayContext.unlockOffScreenBuffer(mbitmap);
        } else
        {
            return 0;
        }
    }

    private native int native_Apply(int i);

    private native int native_Create();

    private native int native_Destroy(int i);

    private native int native_DoStep(int i);

    private native int native_EXIF_Enable(int i, boolean flag, boolean flag1);

    private native Object native_EXIF_GetFieldInfo(int i, int j, Integer integer);

    private native int native_EXIF_SetFieldInfo(int i, int j, Object obj);

    private native int native_EndErase(int i);

    private native int native_ErasePolyLine(int i, MPoint ampoint[]);

    private native int native_EraseTo(int i, MPoint mpoint);

    private native int native_GetDisplayData(int i, MBitmap mbitmap);

    private native int native_GetDisplayDataEx(int i, int j, int k, MRect mrect, MBitmap mbitmap, boolean flag, boolean flag1);

    private native int native_GetDisplayDataFast(int i, MBitmap mbitmap);

    private native int native_GetPreviousDisplayData(int i, MBitmap mbitmap, boolean flag);

    private native Object native_GetProp(int i, int j, Integer integer);

    private native int native_GetSpecialDispData(int i, MBitmap mbitmap);

    private native int native_GetState(int i, State state);

    private native int native_Init(int i);

    private native int native_IsModified(int i, Boolean boolean1);

    private native int native_Load(int i, int j, int k, Object obj);

    private native int native_Pan(int i, int j, int k);

    private native int native_RectImgToScreen(int i, MRect mrect, MRect mrect1);

    private native int native_RectScreenToImg(int i, MRect mrect, MRect mrect1);

    private native int native_Redo(int i);

    private native int native_RefreshDisplay(int i);

    private native int native_RefreshDisplayEx(int i, MRect mrect, boolean flag);

    private native int native_Reset(int i);

    private native int native_Save(int i, int j, int k, Object obj, int l);

    private native int native_SaveDoStep(int i, int j);

    private native int native_SaveEnd(int i, Boolean boolean1);

    private native int native_SaveStart(int i, int j, int k, Object obj, int l, Integer integer, int ai[]);

    private native int native_SelectTool(int i, int j);

    private native int native_SetCache(int i, int j, int k, Object obj);

    private native int native_SetDisplayContext(int i, int j, int k, int l);

    private native int native_SetEraseStyle(int i, EraserStyle eraserstyle);

    private native int native_SetImgBorderDes(int i, MRect mrect);

    private native int native_SetImgSize(int i, int j, int k);

    private native int native_SetProp(int i, int j, Object obj);

    private native int native_SetThreadNumber(int i, int j);

    private native int native_StartErase(int i, MPoint mpoint);

    private native int native_Undo(int i);

    private native int native_UndoPointSet(int i, boolean flag);

    private native int native_Zoom(int i, int j, MPoint mpoint);

    private int selectTool(int i)
    {
        return native_SelectTool(mNativeEngine, i);
    }

    public int apply()
    {
        return native_Apply(mNativeEngine);
    }

    public int attach(ToolBase toolbase)
    {
        toolbase.setEngine(this);
        return selectTool(toolbase.getToolId());
    }

    public int destroy()
    {
        int i = mNativeEngine;
        int j = 0;
        if (i != 0)
        {
            j = native_Destroy(mNativeEngine);
            mNativeEngine = 0;
        }
        return j;
    }

    public int detach()
    {
        return 0;
    }

    public int doStep()
    {
        return native_DoStep(mNativeEngine);
    }

    public int endErase()
    {
        return native_EndErase(mNativeEngine);
    }

    public int erasePolyLine(MPoint ampoint[])
    {
        return native_ErasePolyLine(mNativeEngine, ampoint);
    }

    public int eraseTo(MPoint mpoint)
    {
        return native_EraseTo(mNativeEngine, mpoint);
    }

    public int exifEnable(boolean flag, boolean flag1)
    {
        return native_EXIF_Enable(mNativeEngine, flag, flag1);
    }

    public Object exifGetFieldInfo(int i, Integer integer)
    {
        return native_EXIF_GetFieldInfo(mNativeEngine, i, integer);
    }

    public int exifSetFieldInfo(int i, Object obj)
    {
        return native_EXIF_SetFieldInfo(mNativeEngine, i, obj);
    }

    protected void finalize()
        throws Throwable
    {
        super.finalize();
        if (mNativeEngine != 0)
        {
            native_Destroy(mNativeEngine);
            mNativeEngine = 0;
        }
    }

    public int getDisplayData(int i, int j, MRect mrect, MBitmap mbitmap, boolean flag)
    {
        return native_GetDisplayDataEx(mNativeEngine, i, j, mrect, mbitmap, flag, false);
    }

    public int getDisplayData(MBitmap mbitmap)
    {
        return native_GetDisplayData(mNativeEngine, mbitmap);
    }

    public int getDisplayDataFast(MBitmap mbitmap)
    {
        return native_GetDisplayDataFast(mNativeEngine, mbitmap);
    }

    public int getNativeEngine()
    {
        return mNativeEngine;
    }

    public int getPreviousDisplayData(int i, int j, MRect mrect, MBitmap mbitmap, boolean flag)
    {
        return native_GetDisplayDataEx(mNativeEngine, i, j, mrect, mbitmap, flag, true);
    }

    public int getPreviousDisplayData(MBitmap mbitmap, boolean flag)
    {
        return native_GetPreviousDisplayData(mNativeEngine, mbitmap, flag);
    }

    public Object getProp(int i, Integer integer)
    {
        return native_GetProp(mNativeEngine, i, integer);
    }

    public int getSpecialDispData(MBitmap mbitmap)
    {
        return native_GetSpecialDispData(mNativeEngine, mbitmap);
    }

    public int getState(State state)
    {
        return native_GetState(mNativeEngine, state);
    }

    public int init()
    {
        return native_Init(mNativeEngine);
    }

    public int isModified(Boolean boolean1)
    {
        return native_IsModified(mNativeEngine, boolean1);
    }

    public int load(int i, String s)
    {
        return native_Load(mNativeEngine, 1, i, s);
    }

    public int load(int i, MStream mstream)
    {
        return native_Load(mNativeEngine, 2, i, mstream);
    }

    public int pan(int i, int j)
    {
        return native_Pan(mNativeEngine, i, j);
    }

    public int rectImgToScreen(MRect mrect, MRect mrect1)
    {
        return native_RectImgToScreen(mNativeEngine, mrect, mrect1);
    }

    public int rectScreenToImg(MRect mrect, MRect mrect1)
    {
        return native_RectScreenToImg(mNativeEngine, mrect, mrect1);
    }

    public int redo()
    {
        return native_Redo(mNativeEngine);
    }

    public int refreshDisplay()
    {
        return native_RefreshDisplay(mNativeEngine);
    }

    public int refreshDisplay(MRect mrect, boolean flag)
    {
        return native_RefreshDisplayEx(mNativeEngine, mrect, flag);
    }

    public int reset()
    {
        return native_Reset(mNativeEngine);
    }

    public int save(int i, String s, int j)
    {
        return native_Save(mNativeEngine, 1, i, s, j);
    }

    public int save(int i, MStream mstream, int j)
    {
        return native_Save(mNativeEngine, 2, i, mstream, j);
    }

    public int saveDoStep(int i)
    {
        return native_SaveDoStep(mNativeEngine, i);
    }

    public int saveEnd(Boolean boolean1)
    {
        return native_SaveEnd(mNativeEngine, boolean1);
    }

    public int saveStart(int i, String s, int j, Integer integer, int ai[])
    {
        return native_SaveStart(mNativeEngine, 1, i, s, j, integer, ai);
    }

    public int saveStart(int i, MStream mstream, int j, Integer integer, int ai[])
    {
        return native_SaveStart(mNativeEngine, 1, i, mstream, j, integer, ai);
    }

    public int setCache(int i, String s)
    {
        return native_SetCache(mNativeEngine, 1, i, s);
    }

    public int setCache(int i, MStream mstream)
    {
        return native_SetCache(mNativeEngine, 2, i, mstream);
    }

    public int setCache(MBitmap mbitmap)
    {
        return native_SetCache(mNativeEngine, 3, 0, mbitmap);
    }

    public int setDisplayContext(DisplayContext displaycontext)
    {
        mDisplayContext = displaycontext;
        return native_SetDisplayContext(mNativeEngine, mDisplayContext.displayWidth, mDisplayContext.displayHeight, mDisplayContext.pixelArrayFormat);
    }

    public int setEraserStyle(EraserStyle eraserstyle)
    {
        return native_SetEraseStyle(mNativeEngine, eraserstyle);
    }

    public int setImgBorderDes(MRect mrect)
    {
        return native_SetImgBorderDes(mNativeEngine, mrect);
    }

    public int setImgSize(int i, int j)
    {
        return native_SetImgSize(mNativeEngine, i, j);
    }

    public int setNotify(Notify notify)
    {
        mNotify = notify;
        return 0;
    }

    public int setProp(int i, Object obj)
    {
        return native_SetProp(mNativeEngine, i, obj);
    }

    public int setTextInfo(powermobia.photoeditor.tools.Overlay.TextInfo textinfo)
    {
        mTextInfo = textinfo;
        return 0;
    }

    public int setThreadNumber(int i)
    {
        return native_SetThreadNumber(mNativeEngine, i);
    }

    public int startErase(MPoint mpoint)
    {
        return native_StartErase(mNativeEngine, mpoint);
    }

    public int undo()
    {
        return native_Undo(mNativeEngine);
    }

    public int undoPointSet(boolean flag)
    {
        return native_UndoPointSet(mNativeEngine, flag);
    }

    public int zoom(int i, MPoint mpoint)
    {
        return native_Zoom(mNativeEngine, i, mpoint);
    }
}
