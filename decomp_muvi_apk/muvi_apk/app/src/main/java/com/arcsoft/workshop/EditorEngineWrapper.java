// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.arcsoft.workshop;

import com.arcsoft.util.debug.Log;
import com.arcsoft.workshop.plugin.ip.PhotoFixIP;
import powermobia.photoeditor.EditorEngine;
import powermobia.photoeditor.IPAdaptor;
import powermobia.photoeditor.tools.BasicEditor;
import powermobia.photoeditor.tools.Beautifier;
import powermobia.photoeditor.tools.Effect;
import powermobia.photoeditor.tools.Frame;
import powermobia.photoeditor.tools.PhotoFix;
import powermobia.photoeditor.tools.RedeyeRemoval;
import powermobia.photoeditor.tools.Resize;
import powermobia.photoeditor.tools.ToolBase;
import powermobia.utils.DisplayContext;
import powermobia.utils.MBitmap;
import powermobia.utils.MPoint;
import powermobia.utils.MRect;
import powermobia.utils.MStream;

public class EditorEngineWrapper
{

    public static final int ENGINE_AUTOFIX_TOOL = 2;
    public static final int ENGINE_BRIGHTNESS_TOOL = 1;
    public static final int ENGINE_CONTRAST_TOOL = 0;
    public static final int ENGINE_CROP_TOOL = 12;
    public static final int ENGINE_EFFECT_TOOL = 9;
    public static final int ENGINE_FACEBEAUTIFY_TOOL = 10;
    public static final int ENGINE_FRAME_TOOL = 11;
    public static final int ENGINE_HIGHLIGHTS_TOOL = 4;
    public static final int ENGINE_MINIATURE_TOOL = 13;
    public static final int ENGINE_REDEYES_TOOL = 7;
    public static final int ENGINE_RESIZE_TOOL = 14;
    public static final int ENGINE_REVERSEFILM_TOOL = 8;
    public static final int ENGINE_SATURATION_TOOL = 6;
    public static final int ENGINE_SHADOWS_TOOL = 5;
    public static final int ENGINE_SHARPNESS_TOOL = 3;
    private static final String TAG = "EditorTools_Time";
    private static final int UNDO_STEPS_MAX = 32;
    public static final String mSychronizedString = "Synchronized";
    private static final int mThreadNumber = 1;
    private EditorEngine mEditorEngine;
    private ToolBase mEditorEngineTool;
    private int mEditorEngineToolCur;

    public EditorEngineWrapper()
    {
        mEditorEngine = null;
        mEditorEngineTool = null;
    }

    private int initEditorEngine(powermobia.photoeditor.EditorEngine.Notify notify, DisplayContext displaycontext)
    {
        mEditorEngine = new EditorEngine();
        mEditorEngine.setThreadNumber(1);
        mEditorEngine.init();
        mEditorEngine.setNotify(notify);
        mEditorEngine.setDisplayContext(displaycontext);
        mEditorEngine.setProp(0xa6d00a, Boolean.valueOf(true));
        mEditorEngine.setProp(0xa6d006, Integer.valueOf(2));
        mEditorEngine.setProp(0xa6d001, Integer.valueOf(32));
        mEditorEngine.setProp(0xa6d00b, Boolean.valueOf(true));
        mEditorEngine.setProp(0xa6d00c, Boolean.valueOf(true));
        mEditorEngine.setProp(0xa60019, Boolean.valueOf(true));
        switchEditorEngineTool(2);
        return 0;
    }

    public int apply()
    {
        "Synchronized";
        JVM INSTR monitorenter ;
        int i = mEditorEngine.apply();
        "Synchronized";
        JVM INSTR monitorexit ;
        return i;
        Exception exception;
        exception;
        "Synchronized";
        JVM INSTR monitorexit ;
        throw exception;
    }

    public int attach(ToolBase toolbase)
    {
        "Synchronized";
        JVM INSTR monitorenter ;
        int i = mEditorEngine.attach(toolbase);
        "Synchronized";
        JVM INSTR monitorexit ;
        return i;
        Exception exception;
        exception;
        "Synchronized";
        JVM INSTR monitorexit ;
        throw exception;
    }

    public int autoFix()
    {
        "Synchronized";
        JVM INSTR monitorenter ;
        int i;
        if (!(mEditorEngineTool instanceof RedeyeRemoval))
        {
            break MISSING_BLOCK_LABEL_29;
        }
        i = ((RedeyeRemoval)mEditorEngineTool).autoFix();
        "Synchronized";
        JVM INSTR monitorexit ;
        return i;
        "Synchronized";
        JVM INSTR monitorexit ;
        return 5;
        Exception exception;
        exception;
        "Synchronized";
        JVM INSTR monitorexit ;
        throw exception;
    }

    public int beautify(MRect mrect, int i)
    {
        "Synchronized";
        JVM INSTR monitorenter ;
        int j;
        if (!(mEditorEngineTool instanceof Beautifier))
        {
            break MISSING_BLOCK_LABEL_33;
        }
        j = ((Beautifier)mEditorEngineTool).beautify(mrect, i);
        "Synchronized";
        JVM INSTR monitorexit ;
        return j;
        "Synchronized";
        JVM INSTR monitorexit ;
        return 5;
        Exception exception;
        exception;
        "Synchronized";
        JVM INSTR monitorexit ;
        throw exception;
    }

    public int crop(MRect mrect)
    {
        "Synchronized";
        JVM INSTR monitorenter ;
        int i;
        if (!(mEditorEngineTool instanceof BasicEditor))
        {
            break MISSING_BLOCK_LABEL_30;
        }
        i = ((BasicEditor)mEditorEngineTool).crop(mrect);
        "Synchronized";
        JVM INSTR monitorexit ;
        return i;
        "Synchronized";
        JVM INSTR monitorexit ;
        return 5;
        Exception exception;
        exception;
        "Synchronized";
        JVM INSTR monitorexit ;
        throw exception;
    }

    public int detach()
    {
        "Synchronized";
        JVM INSTR monitorenter ;
        int i = mEditorEngine.detach();
        "Synchronized";
        JVM INSTR monitorexit ;
        return i;
        Exception exception;
        exception;
        "Synchronized";
        JVM INSTR monitorexit ;
        throw exception;
    }

    public void detachAttach(ToolBase toolbase)
    {
        "Synchronized";
        JVM INSTR monitorenter ;
        mEditorEngine.detach();
        mEditorEngine.attach(toolbase);
        "Synchronized";
        JVM INSTR monitorexit ;
        return;
        Exception exception;
        exception;
        "Synchronized";
        JVM INSTR monitorexit ;
        throw exception;
    }

    public int detachTools()
    {
        "Synchronized";
        JVM INSTR monitorenter ;
        mEditorEngine.detach();
        mEditorEngine.attach(mEditorEngineTool);
        "Synchronized";
        JVM INSTR monitorexit ;
        return 0;
        Exception exception;
        exception;
        "Synchronized";
        JVM INSTR monitorexit ;
        throw exception;
    }

    public int doAdaptiveAnalyze(PhotoFixIP photofixip)
    {
        "Synchronized";
        JVM INSTR monitorenter ;
        int i;
        if (!(mEditorEngineTool instanceof PhotoFix))
        {
            break MISSING_BLOCK_LABEL_30;
        }
        i = ((PhotoFix)mEditorEngineTool).doAdaptiveAnalyze(photofixip);
        "Synchronized";
        JVM INSTR monitorexit ;
        return i;
        "Synchronized";
        JVM INSTR monitorexit ;
        return 5;
        Exception exception;
        exception;
        "Synchronized";
        JVM INSTR monitorexit ;
        throw exception;
    }

    public int doAdaptiveEffect(IPAdaptor ipadaptor)
    {
        "Synchronized";
        JVM INSTR monitorenter ;
        int i;
        if (!(mEditorEngineTool instanceof Effect))
        {
            break MISSING_BLOCK_LABEL_30;
        }
        i = ((Effect)mEditorEngineTool).doAdaptiveEffect(ipadaptor);
        "Synchronized";
        JVM INSTR monitorexit ;
        return i;
        "Synchronized";
        JVM INSTR monitorexit ;
        return 5;
        Exception exception;
        exception;
        "Synchronized";
        JVM INSTR monitorexit ;
        throw exception;
    }

    public int doAdaptiveEnhance(PhotoFixIP photofixip)
    {
        "Synchronized";
        JVM INSTR monitorenter ;
        int i;
        if (!(mEditorEngineTool instanceof PhotoFix))
        {
            break MISSING_BLOCK_LABEL_30;
        }
        i = ((PhotoFix)mEditorEngineTool).doAdaptiveEnhance(photofixip);
        "Synchronized";
        JVM INSTR monitorexit ;
        return i;
        "Synchronized";
        JVM INSTR monitorexit ;
        return 5;
        Exception exception;
        exception;
        "Synchronized";
        JVM INSTR monitorexit ;
        throw exception;
    }

    public int doEffect(int i)
    {
        "Synchronized";
        JVM INSTR monitorenter ;
        int j;
        if (!(mEditorEngineTool instanceof Effect))
        {
            break MISSING_BLOCK_LABEL_30;
        }
        j = ((Effect)mEditorEngineTool).doEffect(i);
        "Synchronized";
        JVM INSTR monitorexit ;
        return j;
        "Synchronized";
        JVM INSTR monitorexit ;
        return 5;
        Exception exception;
        exception;
        "Synchronized";
        JVM INSTR monitorexit ;
        throw exception;
    }

    public int doEffect(int i, int ai[])
    {
        "Synchronized";
        JVM INSTR monitorenter ;
        int j;
        if (!(mEditorEngineTool instanceof Effect))
        {
            break MISSING_BLOCK_LABEL_33;
        }
        j = ((Effect)mEditorEngineTool).doEffect(i, ai);
        "Synchronized";
        JVM INSTR monitorexit ;
        return j;
        "Synchronized";
        JVM INSTR monitorexit ;
        return 5;
        Exception exception;
        exception;
        "Synchronized";
        JVM INSTR monitorexit ;
        throw exception;
    }

    public int doStep()
    {
        "Synchronized";
        JVM INSTR monitorenter ;
        int i = mEditorEngine.doStep();
        "Synchronized";
        JVM INSTR monitorexit ;
        return i;
        Exception exception;
        exception;
        "Synchronized";
        JVM INSTR monitorexit ;
        throw exception;
    }

    public int endErase()
    {
        "Synchronized";
        JVM INSTR monitorenter ;
        int i = mEditorEngine.endErase();
        "Synchronized";
        JVM INSTR monitorexit ;
        return i;
        Exception exception;
        exception;
        "Synchronized";
        JVM INSTR monitorexit ;
        throw exception;
    }

    public int eraseTo(MPoint mpoint)
    {
        "Synchronized";
        JVM INSTR monitorenter ;
        int i = mEditorEngine.eraseTo(mpoint);
        "Synchronized";
        JVM INSTR monitorexit ;
        return i;
        Exception exception;
        exception;
        "Synchronized";
        JVM INSTR monitorexit ;
        throw exception;
    }

    public int exifEnable(boolean flag, boolean flag1)
    {
        "Synchronized";
        JVM INSTR monitorenter ;
        int i = mEditorEngine.exifEnable(flag, flag1);
        "Synchronized";
        JVM INSTR monitorexit ;
        return i;
        Exception exception;
        exception;
        "Synchronized";
        JVM INSTR monitorexit ;
        throw exception;
    }

    public MRect[] faceDetect(Integer integer)
    {
        "Synchronized";
        JVM INSTR monitorenter ;
        MRect amrect[];
        if (!(mEditorEngineTool instanceof Beautifier))
        {
            break MISSING_BLOCK_LABEL_30;
        }
        amrect = ((Beautifier)mEditorEngineTool).faceDetect(integer);
        "Synchronized";
        JVM INSTR monitorexit ;
        return amrect;
        "Synchronized";
        JVM INSTR monitorexit ;
        return null;
        Exception exception;
        exception;
        "Synchronized";
        JVM INSTR monitorexit ;
        throw exception;
    }

    public int fix(MRect mrect)
    {
        "Synchronized";
        JVM INSTR monitorenter ;
        int i;
        if (!(mEditorEngineTool instanceof RedeyeRemoval))
        {
            break MISSING_BLOCK_LABEL_30;
        }
        i = ((RedeyeRemoval)mEditorEngineTool).fix(mrect);
        "Synchronized";
        JVM INSTR monitorexit ;
        return i;
        "Synchronized";
        JVM INSTR monitorexit ;
        return 5;
        Exception exception;
        exception;
        "Synchronized";
        JVM INSTR monitorexit ;
        throw exception;
    }

    public int getDisplayData(int i, int j, MRect mrect, MBitmap mbitmap, boolean flag)
    {
        "Synchronized";
        JVM INSTR monitorenter ;
        int k = mEditorEngine.getDisplayData(i, j, mrect, mbitmap, flag);
        "Synchronized";
        JVM INSTR monitorexit ;
        return k;
        Exception exception;
        exception;
        "Synchronized";
        JVM INSTR monitorexit ;
        throw exception;
    }

    public int getDisplayData(MBitmap mbitmap)
    {
        "Synchronized";
        JVM INSTR monitorenter ;
        int i = mEditorEngine.getDisplayData(mbitmap);
        "Synchronized";
        JVM INSTR monitorexit ;
        return i;
        Exception exception;
        exception;
        "Synchronized";
        JVM INSTR monitorexit ;
        throw exception;
    }

    public int getDisplayDataFast(MBitmap mbitmap)
    {
        "Synchronized";
        JVM INSTR monitorenter ;
        int i = mEditorEngine.getDisplayDataFast(mbitmap);
        "Synchronized";
        JVM INSTR monitorexit ;
        return i;
        Exception exception;
        exception;
        "Synchronized";
        JVM INSTR monitorexit ;
        throw exception;
    }

    public int getEditorEngineToolCur()
    {
        return mEditorEngineToolCur;
    }

    public int getHistogramGray(MBitmap mbitmap, int ai[])
    {
        "Synchronized";
        JVM INSTR monitorenter ;
        int i;
        if (!(mEditorEngineTool instanceof PhotoFix))
        {
            break MISSING_BLOCK_LABEL_33;
        }
        i = ((PhotoFix)mEditorEngineTool).getHistogramGray(mbitmap, ai);
        "Synchronized";
        JVM INSTR monitorexit ;
        return i;
        "Synchronized";
        JVM INSTR monitorexit ;
        return 5;
        Exception exception;
        exception;
        "Synchronized";
        JVM INSTR monitorexit ;
        throw exception;
    }

    public int getImgRect(MRect mrect)
    {
        "Synchronized";
        JVM INSTR monitorenter ;
        int i;
        powermobia.photoeditor.EditorEngine.State state = new powermobia.photoeditor.EditorEngine.State();
        mEditorEngine.getState(state);
        MRect mrect1 = new MRect();
        mrect1.set(0, 0, state.iImgWidth, state.iImgHeight);
        i = mEditorEngine.rectImgToScreen(mrect1, mrect);
        "Synchronized";
        JVM INSTR monitorexit ;
        return i;
        Exception exception;
        exception;
        "Synchronized";
        JVM INSTR monitorexit ;
        throw exception;
    }

    public int getImgScreenRect(MRect mrect)
    {
        "Synchronized";
        JVM INSTR monitorenter ;
        powermobia.photoeditor.EditorEngine.State state;
        int i;
        state = new powermobia.photoeditor.EditorEngine.State();
        i = mEditorEngine.getState(state);
        if (i != 0)
        {
            break MISSING_BLOCK_LABEL_40;
        }
        i = mEditorEngine.rectImgToScreen(state.rtImgDisplayRect, mrect);
        "Synchronized";
        JVM INSTR monitorexit ;
        return i;
        Exception exception;
        exception;
        "Synchronized";
        JVM INSTR monitorexit ;
        throw exception;
    }

    public int getImgSize(MPoint mpoint)
    {
        "Synchronized";
        JVM INSTR monitorenter ;
        int i;
        powermobia.photoeditor.EditorEngine.State state = new powermobia.photoeditor.EditorEngine.State();
        i = mEditorEngine.getState(state);
        mpoint.x = state.iImgWidth;
        mpoint.y = state.iImgHeight;
        "Synchronized";
        JVM INSTR monitorexit ;
        return i;
        Exception exception;
        exception;
        "Synchronized";
        JVM INSTR monitorexit ;
        throw exception;
    }

    public int getPreviousDisplayData(int i, int j, MRect mrect, MBitmap mbitmap, boolean flag)
    {
        "Synchronized";
        JVM INSTR monitorenter ;
        int k = mEditorEngine.getPreviousDisplayData(i, j, mrect, mbitmap, flag);
        "Synchronized";
        JVM INSTR monitorexit ;
        return k;
        Exception exception;
        exception;
        "Synchronized";
        JVM INSTR monitorexit ;
        throw exception;
    }

    public int getPreviousDisplayData(MBitmap mbitmap, boolean flag)
    {
        "Synchronized";
        JVM INSTR monitorenter ;
        int i = mEditorEngine.getPreviousDisplayData(mbitmap, flag);
        "Synchronized";
        JVM INSTR monitorexit ;
        return i;
        Exception exception;
        exception;
        "Synchronized";
        JVM INSTR monitorexit ;
        throw exception;
    }

    public Object getProp(int i, Integer integer)
    {
        "Synchronized";
        JVM INSTR monitorenter ;
        Object obj = mEditorEngine.getProp(i, integer);
        "Synchronized";
        JVM INSTR monitorexit ;
        return obj;
        Exception exception;
        exception;
        "Synchronized";
        JVM INSTR monitorexit ;
        throw exception;
    }

    public int getState(powermobia.photoeditor.EditorEngine.State state)
    {
        "Synchronized";
        JVM INSTR monitorenter ;
        int i = mEditorEngine.getState(state);
        "Synchronized";
        JVM INSTR monitorexit ;
        return i;
        Exception exception;
        exception;
        "Synchronized";
        JVM INSTR monitorexit ;
        throw exception;
    }

    public int getThreadNumber()
    {
        return 1;
    }

    public int init(powermobia.photoeditor.EditorEngine.Notify notify, DisplayContext displaycontext)
    {
        uninit();
        return initEditorEngine(notify, displaycontext);
    }

    public int isModified(Boolean boolean1)
    {
        "Synchronized";
        JVM INSTR monitorenter ;
        int i = mEditorEngine.isModified(boolean1);
        "Synchronized";
        JVM INSTR monitorexit ;
        return i;
        Exception exception;
        exception;
        "Synchronized";
        JVM INSTR monitorexit ;
        throw exception;
    }

    public int load(int i, String s)
    {
        "Synchronized";
        JVM INSTR monitorenter ;
        int j = mEditorEngine.load(i, s);
        "Synchronized";
        JVM INSTR monitorexit ;
        return j;
        Exception exception;
        exception;
        "Synchronized";
        JVM INSTR monitorexit ;
        throw exception;
    }

    public int load(int i, MStream mstream)
    {
        "Synchronized";
        JVM INSTR monitorenter ;
        int j = mEditorEngine.load(i, mstream);
        "Synchronized";
        JVM INSTR monitorexit ;
        return j;
        Exception exception;
        exception;
        "Synchronized";
        JVM INSTR monitorexit ;
        throw exception;
    }

    public int pan(int i, int j)
    {
        "Synchronized";
        JVM INSTR monitorenter ;
        int k = mEditorEngine.pan(i, j);
        "Synchronized";
        JVM INSTR monitorexit ;
        return k;
        Exception exception;
        exception;
        "Synchronized";
        JVM INSTR monitorexit ;
        throw exception;
    }

    public int rectImgToScreen(MRect mrect, MRect mrect1)
    {
        "Synchronized";
        JVM INSTR monitorenter ;
        int i = mEditorEngine.rectImgToScreen(mrect, mrect1);
        "Synchronized";
        JVM INSTR monitorexit ;
        return i;
        Exception exception;
        exception;
        "Synchronized";
        JVM INSTR monitorexit ;
        throw exception;
    }

    public int rectScreenToImg(MRect mrect, MRect mrect1)
    {
        "Synchronized";
        JVM INSTR monitorenter ;
        int i = mEditorEngine.rectScreenToImg(mrect, mrect1);
        "Synchronized";
        JVM INSTR monitorexit ;
        return i;
        Exception exception;
        exception;
        "Synchronized";
        JVM INSTR monitorexit ;
        throw exception;
    }

    public int redo()
    {
        "Synchronized";
        JVM INSTR monitorenter ;
        int i = mEditorEngine.redo();
        "Synchronized";
        JVM INSTR monitorexit ;
        return i;
        Exception exception;
        exception;
        "Synchronized";
        JVM INSTR monitorexit ;
        throw exception;
    }

    public int refreshDisplay()
    {
        "Synchronized";
        JVM INSTR monitorenter ;
        int i = mEditorEngine.refreshDisplay();
        "Synchronized";
        JVM INSTR monitorexit ;
        return i;
        Exception exception;
        exception;
        "Synchronized";
        JVM INSTR monitorexit ;
        throw exception;
    }

    public int refreshDisplay(MRect mrect, boolean flag)
    {
        "Synchronized";
        JVM INSTR monitorenter ;
        int i = mEditorEngine.refreshDisplay(mrect, flag);
        "Synchronized";
        JVM INSTR monitorexit ;
        return i;
        Exception exception;
        exception;
        "Synchronized";
        JVM INSTR monitorexit ;
        throw exception;
    }

    public int reinit(powermobia.photoeditor.EditorEngine.Notify notify, DisplayContext displaycontext)
    {
        if (mEditorEngine != null)
        {
            mEditorEngine.setNotify(notify);
            return mEditorEngine.setDisplayContext(displaycontext);
        } else
        {
            return 5;
        }
    }

    public int reset()
    {
        "Synchronized";
        JVM INSTR monitorenter ;
        int i = mEditorEngine.reset();
        "Synchronized";
        JVM INSTR monitorexit ;
        return i;
        Exception exception;
        exception;
        "Synchronized";
        JVM INSTR monitorexit ;
        throw exception;
    }

    public int resize(int i, int j, boolean flag)
    {
        "Synchronized";
        JVM INSTR monitorenter ;
        int k;
        if (!(mEditorEngineTool instanceof Resize))
        {
            break MISSING_BLOCK_LABEL_34;
        }
        k = ((Resize)mEditorEngineTool).resize(i, j, flag);
        "Synchronized";
        JVM INSTR monitorexit ;
        return k;
        "Synchronized";
        JVM INSTR monitorexit ;
        return 5;
        Exception exception;
        exception;
        "Synchronized";
        JVM INSTR monitorexit ;
        throw exception;
    }

    public int saveDoStep(int i)
    {
        return mEditorEngine.saveDoStep(i);
    }

    public int saveEnd(Boolean boolean1)
    {
        "Synchronized";
        JVM INSTR monitorenter ;
        int i = mEditorEngine.saveEnd(boolean1);
        "Synchronized";
        JVM INSTR monitorexit ;
        return i;
        Exception exception;
        exception;
        "Synchronized";
        JVM INSTR monitorexit ;
        throw exception;
    }

    public int saveStart(int i, String s, int j, Integer integer, int ai[])
    {
        "Synchronized";
        JVM INSTR monitorenter ;
        int k = mEditorEngine.saveStart(i, s, j, integer, ai);
        "Synchronized";
        JVM INSTR monitorexit ;
        return k;
        Exception exception;
        exception;
        "Synchronized";
        JVM INSTR monitorexit ;
        throw exception;
    }

    public int saveStart(int i, MStream mstream, int j, Integer integer, int ai[])
    {
        "Synchronized";
        JVM INSTR monitorenter ;
        int k = mEditorEngine.saveStart(i, mstream, j, integer, ai);
        "Synchronized";
        JVM INSTR monitorexit ;
        return k;
        Exception exception;
        exception;
        "Synchronized";
        JVM INSTR monitorexit ;
        throw exception;
    }

    public int setEraserStyle(powermobia.photoeditor.EditorEngine.EraserStyle eraserstyle)
    {
        "Synchronized";
        JVM INSTR monitorenter ;
        int i = mEditorEngine.setEraserStyle(eraserstyle);
        "Synchronized";
        JVM INSTR monitorexit ;
        return i;
        Exception exception;
        exception;
        "Synchronized";
        JVM INSTR monitorexit ;
        throw exception;
    }

    public int setFillColor(int i)
    {
        "Synchronized";
        JVM INSTR monitorenter ;
        int j;
        if (!(mEditorEngineTool instanceof Frame))
        {
            break MISSING_BLOCK_LABEL_30;
        }
        j = ((Frame)mEditorEngineTool).setFillColor(i);
        "Synchronized";
        JVM INSTR monitorexit ;
        return j;
        "Synchronized";
        JVM INSTR monitorexit ;
        return 5;
        Exception exception;
        exception;
        "Synchronized";
        JVM INSTR monitorexit ;
        throw exception;
    }

    public int setFrame(int i, String s, int j, String s1, int k)
    {
        "Synchronized";
        JVM INSTR monitorenter ;
        int l;
        if (!(mEditorEngineTool instanceof Frame))
        {
            break MISSING_BLOCK_LABEL_38;
        }
        l = ((Frame)mEditorEngineTool).setFrame(i, s, j, s1, k);
        "Synchronized";
        JVM INSTR monitorexit ;
        return l;
        "Synchronized";
        JVM INSTR monitorexit ;
        return 5;
        Exception exception;
        exception;
        "Synchronized";
        JVM INSTR monitorexit ;
        throw exception;
    }

    public int setFrame(int i, MStream mstream, int j, MStream mstream1, int k)
    {
        "Synchronized";
        JVM INSTR monitorenter ;
        int l;
        if (!(mEditorEngineTool instanceof Frame))
        {
            break MISSING_BLOCK_LABEL_38;
        }
        l = ((Frame)mEditorEngineTool).setFrame(i, mstream, j, mstream1, k);
        "Synchronized";
        JVM INSTR monitorexit ;
        return l;
        "Synchronized";
        JVM INSTR monitorexit ;
        return 5;
        Exception exception;
        exception;
        "Synchronized";
        JVM INSTR monitorexit ;
        throw exception;
    }

    public int setImgBorderDes(MRect mrect)
    {
        "Synchronized";
        JVM INSTR monitorenter ;
        int i = mEditorEngine.setImgBorderDes(mrect);
        "Synchronized";
        JVM INSTR monitorexit ;
        return i;
        Exception exception;
        exception;
        "Synchronized";
        JVM INSTR monitorexit ;
        throw exception;
    }

    public int setProp(int i, boolean flag)
    {
        "Synchronized";
        JVM INSTR monitorenter ;
        int j;
        Boolean boolean1 = new Boolean(flag);
        j = mEditorEngine.setProp(i, boolean1);
        "Synchronized";
        JVM INSTR monitorexit ;
        return j;
        Exception exception;
        exception;
        "Synchronized";
        JVM INSTR monitorexit ;
        throw exception;
    }

    public int startErase(MPoint mpoint)
    {
        "Synchronized";
        JVM INSTR monitorenter ;
        int i = mEditorEngine.startErase(mpoint);
        "Synchronized";
        JVM INSTR monitorexit ;
        return i;
        Exception exception;
        exception;
        "Synchronized";
        JVM INSTR monitorexit ;
        throw exception;
    }

    public void switchEditorEngineTool(int i)
    {
        "Synchronized";
        JVM INSTR monitorenter ;
        if (i != mEditorEngineToolCur)
        {
            break MISSING_BLOCK_LABEL_15;
        }
        "Synchronized";
        JVM INSTR monitorexit ;
        return;
        long l = System.currentTimeMillis();
        mEditorEngine.apply();
        Log.v("EditorTools_Time", (new StringBuilder()).append("apply Time is : ").append(System.currentTimeMillis() - l).toString());
        long l1 = System.currentTimeMillis();
        mEditorEngine.detach();
        Log.v("EditorTools_Time", (new StringBuilder()).append("detach Time is : ").append(System.currentTimeMillis() - l1).toString());
        i;
        JVM INSTR tableswitch 0 14: default 176
    //                   0 246
    //                   1 246
    //                   2 246
    //                   3 246
    //                   4 246
    //                   5 246
    //                   6 246
    //                   7 274
    //                   8 288
    //                   9 302
    //                   10 316
    //                   11 344
    //                   12 260
    //                   13 330
    //                   14 358;
           goto _L1 _L2 _L2 _L2 _L2 _L2 _L2 _L2 _L3 _L4 _L5 _L6 _L7 _L8 _L9 _L10
_L1:
        break; /* Loop/switch isn't completed */
_L10:
        break MISSING_BLOCK_LABEL_358;
_L11:
        if (mEditorEngineTool != null)
        {
            long l2 = System.currentTimeMillis();
            mEditorEngine.attach(mEditorEngineTool);
            Log.v("EditorTools_Time", (new StringBuilder()).append("attach Time is : ").append(System.currentTimeMillis() - l2).toString());
        }
        mEditorEngineToolCur = i;
        "Synchronized";
        JVM INSTR monitorexit ;
        return;
        Exception exception;
        exception;
        "Synchronized";
        JVM INSTR monitorexit ;
        throw exception;
_L2:
        mEditorEngineTool = new PhotoFix();
          goto _L11
_L8:
        mEditorEngineTool = new BasicEditor();
          goto _L11
_L3:
        mEditorEngineTool = new RedeyeRemoval();
          goto _L11
_L4:
        mEditorEngineTool = new Effect();
          goto _L11
_L5:
        mEditorEngineTool = new Effect();
          goto _L11
_L6:
        mEditorEngineTool = new Beautifier();
          goto _L11
_L9:
        mEditorEngineTool = new Effect();
          goto _L11
_L7:
        mEditorEngineTool = new Frame();
          goto _L11
        mEditorEngineTool = new Resize();
          goto _L11
    }

    public int undo()
    {
        "Synchronized";
        JVM INSTR monitorenter ;
        int i = mEditorEngine.undo();
        "Synchronized";
        JVM INSTR monitorexit ;
        return i;
        Exception exception;
        exception;
        "Synchronized";
        JVM INSTR monitorexit ;
        throw exception;
    }

    public int undoPointSet(boolean flag)
    {
        "Synchronized";
        JVM INSTR monitorenter ;
        int i = mEditorEngine.undoPointSet(flag);
        "Synchronized";
        JVM INSTR monitorexit ;
        return i;
        Exception exception;
        exception;
        "Synchronized";
        JVM INSTR monitorexit ;
        throw exception;
    }

    public void uninit()
    {
        "Synchronized";
        JVM INSTR monitorenter ;
        if (mEditorEngine != null)
        {
            mEditorEngine.destroy();
            mEditorEngine = null;
        }
        "Synchronized";
        JVM INSTR monitorexit ;
        return;
        Exception exception;
        exception;
        "Synchronized";
        JVM INSTR monitorexit ;
        throw exception;
    }

    public int zoom(int i, MPoint mpoint)
    {
        "Synchronized";
        JVM INSTR monitorenter ;
        int j = mEditorEngine.zoom(i, mpoint);
        "Synchronized";
        JVM INSTR monitorexit ;
        return j;
        Exception exception;
        exception;
        "Synchronized";
        JVM INSTR monitorexit ;
        throw exception;
    }

}
