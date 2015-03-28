// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.arcsoft.workshop.tools;

import android.graphics.Rect;
import android.os.Environment;
import com.arcsoft.util.debug.Log;
import com.arcsoft.workshop.EditorEngineWrapper;
import com.arcsoft.workshop.WorkShop;
import com.arcsoft.workshop.plugin.ip.EffectColdBlueIP;
import com.arcsoft.workshop.plugin.ip.EffectColorfilterIP;
import com.arcsoft.workshop.plugin.ip.EffectDeepquiteIP;
import com.arcsoft.workshop.plugin.ip.EffectFisheyeIP;
import com.arcsoft.workshop.plugin.ip.EffectGothicIP;
import com.arcsoft.workshop.plugin.ip.EffectMiniatureIP;
import com.arcsoft.workshop.plugin.ip.EffectNewSketchIP;
import com.arcsoft.workshop.plugin.ip.EffectPolaroidIP;
import com.arcsoft.workshop.plugin.ip.EffectSweetvintageIP;
import com.arcsoft.workshop.plugin.ip.EffectWashedawayIP;
import com.arcsoft.workshop.plugin.ip.PhotoFixIP;
import com.arcsoft.workshop.utils.WorkShopUtils;
import powermobia.photoeditor.IPAdaptor;
import powermobia.utils.MBitmap;
import powermobia.utils.MBitmapFactory;
import powermobia.utils.MColorSpace;
import powermobia.utils.MRect;

// Referenced classes of package com.arcsoft.workshop.tools:
//            EditorToolsBase

public class EditorTools extends EditorToolsBase
{

    private static final int DOTYPE_DO1 = 1;
    private static final int DOTYPE_DO12 = 3;
    private static final int DOTYPE_DO123 = 7;
    private static final int DOTYPE_DO13 = 5;
    private static final int DOTYPE_DO2 = 2;
    private static final int DOTYPE_DO23 = 6;
    private static final int DOTYPE_DO3 = 4;
    private static final int DOTYPE_NONE = 0;
    private static final String TAG = "EditorTools";
    private static final String TAG_TIME = "EditorTools_Time";
    static final int mCartoonParamsDef[] = {
        4
    };
    static final int mNewsketchParamsDef[] = {
        10
    };
    static final int mNostalgicParamsDef[] = {
        1, 0, 0, 0, 0
    };
    static final int mVintageParamsDef[] = {
        1, 0, 0, 0, 0
    };
    private MRect mCropRect;
    private int mCurDoType;
    private boolean mDoAutoFixSucc;
    private boolean mDoCropSucc;
    private boolean mDoEffectSucc;
    private boolean mDoMiniatureSucc;
    private int mDoTypeMask;
    private int mEffectID;
    private int mEffectParam[];
    private IPAdaptor mEffectPlugin;
    private int mEffectPluginParam[];
    private int mEffectType;
    private int mEffectTypeForFilter;
    private MRect mFaceBeautifyRect[];
    private String mFramePath;
    private boolean mHasCroped;
    private boolean mHasDetectedFaces;
    private boolean mHasFaces;
    private int mMiniatureParam[];
    private IPAdaptor mMiniaturePlugin;
    private boolean mNeedDoFaceBeautify;
    private boolean mNeedToDraw;
    private boolean mNeedToRefresh;
    private final PhotoFixIP mPhotoFixIP = new PhotoFixIP();
    private int mPhotoFixIPParamArray[];
    private boolean mShouldProcess;
    private boolean mShouldUseReset;
    private String mTexturePath;
    private boolean mUsePlugin;
    private int mWillDoType;

    public EditorTools(WorkShop workshop)
    {
        super(workshop);
        mDoTypeMask = 7;
        mCurDoType = 0;
        mWillDoType = 0;
        mNeedToDraw = true;
        mNeedToRefresh = true;
        mDoCropSucc = false;
        mDoAutoFixSucc = false;
        mDoMiniatureSucc = false;
        mDoEffectSucc = false;
        mPhotoFixIPParamArray = null;
        mMiniatureParam = null;
        mMiniaturePlugin = null;
        mNeedDoFaceBeautify = false;
        mEffectType = -1;
        mEffectID = 0xaad300;
        mUsePlugin = false;
        mEffectParam = null;
        mEffectPlugin = null;
        mEffectPluginParam = null;
        mEffectTypeForFilter = 0;
        mFramePath = null;
        mTexturePath = null;
        mShouldUseReset = false;
        mHasCroped = false;
        mCropRect = null;
        mFaceBeautifyRect = null;
        mHasFaces = false;
        mHasDetectedFaces = false;
        mShouldProcess = true;
    }

    private int cropProcess(MRect mrect)
    {
        MBitmap mbitmap;
        int k1;
label0:
        {
            int i = mEditorEngineWrapper.crop(mrect);
            if (i == 0)
            {
                powermobia.photoeditor.EditorEngine.State state = new powermobia.photoeditor.EditorEngine.State();
                EditorEngineWrapper editorenginewrapper = mWorkShop.getEditorEngineWrapper();
                int j = editorenginewrapper.getState(state);
                if (j != 0)
                {
                    mHasCroped = true;
                    WorkShopUtils.back2BestFitModeForEdit(mWorkShop);
                    return j;
                }
                if (!Environment.getExternalStorageState().equals("mounted"))
                {
                    mHasCroped = true;
                    WorkShopUtils.back2BestFitModeForEdit(mWorkShop);
                    return 0;
                }
                String s = WorkShopUtils.checkSaveTmpDir();
                if (s == null)
                {
                    mHasCroped = true;
                    WorkShopUtils.back2BestFitModeForEdit(mWorkShop);
                    return 0;
                }
                String s1 = mWorkShop.getCurrentFilePath();
                String s2 = s1.substring(s1.lastIndexOf("/"));
                int k = s2.lastIndexOf(".");
                String s3;
                String s4;
                if (k != -1)
                {
                    String s5 = s2.substring(k);
                    s3 = (new StringBuilder()).append("/TmpFile").append(s5).toString();
                } else
                {
                    s3 = "/TmpFile";
                }
                s4 = WorkShopUtils.getOutputFilePath((new StringBuilder()).append(s).append(s3).toString());
                if (!WorkShopUtils.checkSaveDir(s4))
                {
                    mHasCroped = true;
                    WorkShopUtils.back2BestFitModeForEdit(mWorkShop);
                    return 0;
                }
                int l = WorkShopUtils.getImageFormat(s1);
                int ai[] = WorkShopUtils.getResizeSize(state.iImgWidth, state.iImgHeight, 1280, 800);
                int i1 = ai[0];
                int j1 = ai[1];
                MRect mrect1 = new MRect();
                mrect1.left = 0;
                mrect1.top = 0;
                mrect1.right = i1;
                mrect1.bottom = j1;
                mbitmap = MBitmapFactory.createMBitmapBlank(i1, j1, MColorSpace.MPAF_RGB32_B8G8R8A8);
                if (mbitmap != null)
                {
                    k1 = editorenginewrapper.getDisplayData(i1, j1, mrect1, mbitmap, false);
                    if (k1 != 0)
                    {
                        break label0;
                    }
                    int l1 = mbitmap.save(l, s4, 100);
                    if (l1 != 0)
                    {
                        if (mbitmap != null)
                        {
                            mbitmap.recycle();
                        }
                        mHasCroped = true;
                        WorkShopUtils.back2BestFitModeForEdit(mWorkShop);
                        return l1;
                    }
                } else
                {
                    mHasCroped = true;
                    WorkShopUtils.back2BestFitModeForEdit(mWorkShop);
                    return 4;
                }
                if (mbitmap != null)
                {
                    mbitmap.recycle();
                }
                i = mWorkShop.loadNewPicture(s4, true);
                if (i != 0)
                {
                    mHasCroped = true;
                    WorkShopUtils.back2BestFitModeForEdit(mWorkShop);
                    return i;
                }
                Log.v("EditorTools_Time", (new StringBuilder()).append("loadNewPicture : ").append(i).toString());
            }
            mHasCroped = false;
            WorkShopUtils.back2BestFitModeForEdit(mWorkShop);
            return i;
        }
        if (mbitmap != null)
        {
            mbitmap.recycle();
        }
        mHasCroped = true;
        WorkShopUtils.back2BestFitModeForEdit(mWorkShop);
        return k1;
    }

    private int errorProcess()
    {
        Log.v("ERROR", "ERRORPROCESS");
        if (!mShouldUseReset || !mHasCroped || mDoCropSucc) goto _L2; else goto _L1
_L1:
        Log.v("EditorTools", "errorProcess crop Failed");
        mCurDoType = 0;
        mUIMethodForTools.resetUI();
_L4:
        return 0;
_L2:
        if (!mShouldUseReset)
        {
            break; /* Loop/switch isn't completed */
        }
        Log.v("EditorTools", "errorProcess in mShouldUseUndo");
        mCurDoType = 0;
        if ((1 & mWillDoType) == 1)
        {
            Log.v("EditorTools", "errorProcess in DOTYPE_DO1");
            Log.v("EditorTools", (new StringBuilder()).append("errorProcess mDoAutoFixSucc : ").append(mDoAutoFixSucc).toString());
            if (mDoAutoFixSucc)
            {
                mCurDoType = 1;
                mUIMethodForTools.autofixUIProcess(true);
            } else
            {
                mUIMethodForTools.resetUI();
            }
            Log.v("EditorTools", (new StringBuilder()).append("errorProcess out DOTYPE_DO1 mCurDoType : ").append(mCurDoType).toString());
        }
        if ((2 & mWillDoType) == 2)
        {
            Log.v("EditorTools", "errorProcess in DOTYPE_DO2");
            Log.v("EditorTools", (new StringBuilder()).append("errorProcess mDoMiniatureSucc : ").append(mDoMiniatureSucc).toString());
            if (mDoMiniatureSucc)
            {
                mCurDoType = 2 & mCurDoType;
                mUIMethodForTools.miniatureUIProcess(mMiniatureParam[1]);
            } else
            {
                mUIMethodForTools.miniatureUIProcess(-1);
                mUIMethodForTools.effectUIProcess(true, 0xaad300, 0xaad300, mEffectTypeForFilter);
            }
            Log.v("EditorTools", (new StringBuilder()).append("errorProcess out DOTYPE_DO2 mCurDoType : ").append(mCurDoType).toString());
        }
        if ((4 & mWillDoType) == 4)
        {
            Log.v("EditorTools", "errorProcess in DOTYPE_DO3");
            Log.v("EditorTools", (new StringBuilder()).append("errorProcess mDoEffectSucc : ").append(mDoEffectSucc).toString());
            if (mDoEffectSucc)
            {
                mCurDoType = 4 & mCurDoType;
                mUIMethodForTools.effectUIProcess(true, mEffectType, mEffectID, mEffectTypeForFilter);
            } else
            {
                mUIMethodForTools.effectUIProcess(true, 0xaad300, 0xaad300, mEffectTypeForFilter);
            }
            Log.v("EditorTools", (new StringBuilder()).append("errorProcess out DOTYPE_DO3 mCurDoType : ").append(mCurDoType).toString());
            return 0;
        }
        if (true) goto _L4; else goto _L3
_L3:
        int i;
        Log.v("EditorTools", "errorProcess not in mShouldUseUndo");
        i = mCurDoType;
        mCurDoType = 0;
        if ((1 & mWillDoType) == 1)
        {
            Log.v("EditorTools", "errorProcess in DOTYPE_DO1");
            Log.v("EditorTools", (new StringBuilder()).append("errorProcess mDoAutoFixSucc : ").append(mDoAutoFixSucc).toString());
            if (!mDoAutoFixSucc)
            {
                break MISSING_BLOCK_LABEL_812;
            }
            mCurDoType = 1;
            mUIMethodForTools.autofixUIProcess(true);
            Log.v("EditorTools", (new StringBuilder()).append("errorProcess out DOTYPE_DO1 mCurDoType : ").append(mCurDoType).toString());
        }
        if ((2 & mWillDoType) != 2)
        {
            continue; /* Loop/switch isn't completed */
        }
        Log.v("EditorTools", "errorProcess in DOTYPE_DO2");
        Log.v("EditorTools", (new StringBuilder()).append("errorProcess mDoMiniatureSucc : ").append(mDoMiniatureSucc).toString());
        if (!mDoMiniatureSucc)
        {
            break MISSING_BLOCK_LABEL_823;
        }
        mCurDoType = 2 & mCurDoType;
        mUIMethodForTools.miniatureUIProcess(mMiniatureParam[1]);
        Log.v("EditorTools", (new StringBuilder()).append("errorProcess out DOTYPE_DO2 mCurDoType : ").append(mCurDoType).toString());
        if ((4 & mWillDoType) != 4) goto _L4; else goto _L5
_L5:
        Log.v("EditorTools", "errorProcess in DOTYPE_DO3");
        Log.v("EditorTools", (new StringBuilder()).append("errorProcess mDoEffectSucc : ").append(mDoEffectSucc).toString());
        if (mDoEffectSucc)
        {
            mCurDoType = 4 & mCurDoType;
            mUIMethodForTools.effectUIProcess(true, mEffectType, mEffectID, mEffectTypeForFilter);
            Log.v("EditorTools", (new StringBuilder()).append("errorProcess out DOTYPE_DO3 mCurDoType : ").append(mCurDoType).toString());
            return 0;
        }
        break MISSING_BLOCK_LABEL_870;
        mUIMethodForTools.resetUI();
        return 0;
        if ((i & 1) == 1)
        {
            mCurDoType = 1 & mCurDoType;
        }
        mUIMethodForTools.miniatureUIProcess(-1);
        mUIMethodForTools.effectUIProcess(true, 0xaad300, 0xaad300, mEffectTypeForFilter);
        return 0;
        if ((i & 1) == 1)
        {
            mCurDoType = 1 & mCurDoType;
        }
        if ((i & 2) == 2)
        {
            mCurDoType = 2 & mCurDoType;
        }
        mUIMethodForTools.effectUIProcess(true, 0xaad300, 0xaad300, mEffectTypeForFilter);
        return 0;
    }

    private int faceBeautiferProcess()
    {
        if (!mHasDetectedFaces) goto _L2; else goto _L1
_L1:
        if (!mHasFaces) goto _L4; else goto _L3
_L3:
        long l2;
        int k;
        l2 = System.currentTimeMillis();
        k = 0;
_L7:
        if (k < mFaceBeautifyRect.length && mEditorEngineWrapper.beautify(mFaceBeautifyRect[k], 5) == 0) goto _L6; else goto _L5
_L5:
        Log.v("EditorTools_Time", (new StringBuilder()).append("beautify fix Time is : ").append(System.currentTimeMillis() - l2).toString());
_L4:
        return 0;
_L6:
        k++;
        if (true) goto _L7; else goto _L2
_L2:
        Integer integer = new Integer(0);
        long l = System.currentTimeMillis();
        MRect amrect[] = mEditorEngineWrapper.faceDetect(integer);
        Log.v("EditorTools_Time", (new StringBuilder()).append("beautify detect Time is : ").append(System.currentTimeMillis() - l).toString());
        mHasDetectedFaces = true;
        if (integer.intValue() == 0)
        {
            if (amrect.length > 0)
            {
                mHasFaces = true;
                mFaceBeautifyRect = new MRect[amrect.length];
                for (int j = 0; j < amrect.length; j++)
                {
                    mFaceBeautifyRect[j] = new MRect();
                    mFaceBeautifyRect[j].set(amrect[j]);
                }

            } else
            {
                mHasFaces = false;
            }
            long l1 = System.currentTimeMillis();
            int i = 0;
            do
            {
                if (i >= amrect.length || mEditorEngineWrapper.beautify(amrect[i], 5) != 0)
                {
                    Log.v("EditorTools_Time", (new StringBuilder()).append("beautify fix Time is : ").append(System.currentTimeMillis() - l1).toString());
                    return 0;
                }
                i++;
            } while (true);
        } else
        {
            mHasFaces = false;
            return 0;
        }
    }

    private int judgmentUndoTypeAndDoType(int i, Object obj, Object obj1)
    {
        switch (i)
        {
        default:
            return 0;

        case 1: // '\001'
            if (mCurDoType == 0)
            {
                mWillDoType = 1;
                return 0;
            }
            if (mCurDoType == 2)
            {
                mShouldUseReset = true;
                mWillDoType = 3;
                return 0;
            }
            if (mCurDoType == 4)
            {
                mShouldUseReset = true;
                mWillDoType = 5;
                return 0;
            }
            if (mCurDoType == 6)
            {
                mShouldUseReset = true;
                mWillDoType = 7;
                return 0;
            } else
            {
                Log.v("EditorTools", (new StringBuilder()).append("add autofix error mCurDoType : ").append(mCurDoType).toString());
                return 0;
            }

        case 19: // '\023'
            mDoTypeMask = 6;
            if (mCurDoType == 1)
            {
                mShouldUseReset = true;
                return 0;
            }
            if (mCurDoType == 3)
            {
                mShouldUseReset = true;
                mWillDoType = 2;
                return 0;
            }
            if (mCurDoType == 5)
            {
                mShouldUseReset = true;
                mWillDoType = 4;
                return 0;
            }
            if (mCurDoType == 7)
            {
                mShouldUseReset = true;
                mWillDoType = 6;
                return 0;
            } else
            {
                Log.v("EditorTools", (new StringBuilder()).append("judgment remove autofix error mCurDoType : ").append(mCurDoType).toString());
                return 0;
            }

        case 20: // '\024'
            if (mCurDoType == 0 || mCurDoType == 1 || mCurDoType == 2 || mCurDoType == 3)
            {
                mWillDoType = 2;
                return 0;
            }
            if (mCurDoType == 4)
            {
                mShouldUseReset = true;
                mWillDoType = 6;
                return 0;
            }
            if (mCurDoType == 5)
            {
                mShouldUseReset = true;
                mWillDoType = 7;
                return 0;
            }
            if (mCurDoType == 6)
            {
                mShouldUseReset = true;
                mWillDoType = 6;
                return 0;
            }
            if (mCurDoType == 7)
            {
                mShouldUseReset = true;
                mWillDoType = 7;
                return 0;
            } else
            {
                Log.v("EditorTools", (new StringBuilder()).append("judgment add miniature error mCurDoType : ").append(mCurDoType).toString());
                return 0;
            }

        case 21: // '\025'
            mDoTypeMask = 5;
            if (mCurDoType == 2)
            {
                mShouldUseReset = true;
                return 0;
            }
            if (mCurDoType == 3)
            {
                mShouldUseReset = true;
                mWillDoType = 1;
                return 0;
            }
            if (mCurDoType == 7)
            {
                mShouldUseReset = true;
                mWillDoType = 5;
                return 0;
            }
            if (mCurDoType == 6)
            {
                mShouldUseReset = true;
                mWillDoType = 4;
                return 0;
            } else
            {
                Log.v("EditorTools", (new StringBuilder()).append("judgment remove miniature error mCurDoType : ").append(mCurDoType).toString());
                return 0;
            }

        case 9: // '\t'
        case 22: // '\026'
            mWillDoType = 4;
            mNeedDoFaceBeautify = false;
            return 0;

        case 7: // '\007'
            mWillDoType = 4;
            mNeedDoFaceBeautify = true;
            return 0;
        }
    }

    private void preProcessBasedOnParam(int i, Object obj, Object obj1)
    {
        i;
        JVM INSTR tableswitch 20 20: default 20
    //                   20 21;
           goto _L1 _L2
_L1:
        return;
_L2:
        int ai[] = (int[])(int[])obj;
        if (mMiniatureParam == null) goto _L1; else goto _L3
_L3:
        int j = 0;
_L6:
        if (j < ai.length && ai[j] == mMiniatureParam[j]) goto _L5; else goto _L4
_L4:
        if (j >= ai.length && ai[-1 + ai.length] == mMiniatureParam[-1 + ai.length])
        {
            mShouldProcess = false;
            return;
        }
          goto _L1
_L5:
        j++;
          goto _L6
    }

    private int processWillDoTypeNone()
    {
        boolean flag = mShouldUseReset;
        int i = 0;
        if (flag)
        {
            boolean flag1 = mHasCroped;
            i = 0;
            if (flag1)
            {
                mEditorEngineWrapper.switchEditorEngineTool(12);
                MRect mrect = new MRect();
                mEditorEngineWrapper.rectImgToScreen(mCropRect, mrect);
                mEditorEngineWrapper.crop(mrect);
                i = WorkShopUtils.back2BestFitModeForEdit(mWorkShop);
                mEditorEngineWrapper.apply();
            }
        }
        mCurDoType = (mCurDoType | mWillDoType) & mDoTypeMask;
        return i;
    }

    private int processWillDoTypeNotNone()
    {
        if (mWillDoType != 0) goto _L2; else goto _L1
_L1:
        int i = 0;
_L4:
        return i;
_L2:
        if (mWillDoType == 0)
        {
            mNeedToDraw = true;
            mEditorEngineWrapper.setProp(0xa60002, true);
        }
        boolean flag = mShouldUseReset;
        i = 0;
        if (!flag)
        {
            break; /* Loop/switch isn't completed */
        }
        boolean flag1 = mHasCroped;
        i = 0;
        if (!flag1)
        {
            break; /* Loop/switch isn't completed */
        }
        long l9 = System.currentTimeMillis();
        mEditorEngineWrapper.switchEditorEngineTool(12);
        MRect mrect = new MRect();
        mEditorEngineWrapper.rectImgToScreen(mCropRect, mrect);
        mEditorEngineWrapper.crop(mrect);
        i = WorkShopUtils.back2BestFitModeForEdit(mWorkShop);
        Log.v("EditorTools_Time", (new StringBuilder()).append("Crop Sum Time is : ").append(System.currentTimeMillis() - l9).toString());
        if (i != 0)
        {
            continue; /* Loop/switch isn't completed */
        }
        mDoCropSucc = true;
        break; /* Loop/switch isn't completed */
        if (true) goto _L4; else goto _L3
_L3:
        if ((1 & mWillDoType) == 1)
        {
            Log.v("EditorTools", "In DOTYPE_DO1");
            long l6 = System.currentTimeMillis();
            mEditorEngineWrapper.switchEditorEngineTool(2);
            Log.v("EditorTools_Time", (new StringBuilder()).append("SwitchAutoFix Time is : ").append(System.currentTimeMillis() - l6).toString());
            if ((1 & mWillDoType) == 1 && (2 & mWillDoType) != 2 && (4 & mWillDoType) != 4)
            {
                mNeedToDraw = true;
                mEditorEngineWrapper.setProp(0xa60002, true);
            }
            long l7 = System.currentTimeMillis();
            if (mPhotoFixIPParamArray == null)
            {
                mPhotoFixIPParamArray = new int[11];
                mPhotoFixIP.setParameter(mPhotoFixIPParamArray);
                mEditorEngineWrapper.doAdaptiveAnalyze(mPhotoFixIP);
            }
            Log.v("EditorTools_Time", (new StringBuilder()).append("doAdaptiveAnalyze Time is : ").append(System.currentTimeMillis() - l7).toString());
            long l8 = System.currentTimeMillis();
            i = mEditorEngineWrapper.doAdaptiveEnhance(mPhotoFixIP);
            Log.v("EditorTools_Time", (new StringBuilder()).append("doAdaptiveEnhance Time is : ").append(System.currentTimeMillis() - l8).toString());
            Log.v("EditorTools", (new StringBuilder()).append("In DOTYPE_DO1 res : ").append(i).toString());
            if (i != 0)
            {
                break MISSING_BLOCK_LABEL_734;
            }
            mDoAutoFixSucc = true;
        }
        if ((2 & mWillDoType) != 2)
        {
            continue; /* Loop/switch isn't completed */
        }
        Log.v("EditorTools", "In DOTYPE_DO2");
        long l4 = System.currentTimeMillis();
        mEditorEngineWrapper.switchEditorEngineTool(13);
        Log.v("EditorTools_Time", (new StringBuilder()).append("SwitchMiniature Time is : ").append(System.currentTimeMillis() - l4).toString());
        if ((2 & mWillDoType) == 2 && (4 & mWillDoType) != 4)
        {
            mNeedToDraw = true;
            mEditorEngineWrapper.setProp(0xa60002, true);
        }
        long l5 = System.currentTimeMillis();
        Log.v("EditorTools_Time", (new StringBuilder()).append("Miniature doEffect Time is : ").append(System.currentTimeMillis() - l5).toString());
        i = mEditorEngineWrapper.doAdaptiveEffect(mMiniaturePlugin);
        Log.v("EditorTools", (new StringBuilder()).append("In DOTYPE_DO2 res : ").append(i).toString());
        if (i != 0)
        {
            break MISSING_BLOCK_LABEL_745;
        }
        mDoMiniatureSucc = true;
        if ((4 & mWillDoType) != 4) goto _L4; else goto _L5
_L5:
        Log.v("EditorTools", "In DOTYPE_DO3");
        if (mShouldUseReset)
        {
            Log.v("EditorTools", "In DOTYPE_DO3 mShouldUseUndo True");
            int k;
            if (mNeedDoFaceBeautify)
            {
                mEffectType = 1;
                mEditorEngineWrapper.switchEditorEngineTool(10);
                if ((4 & mWillDoType) == 4)
                {
                    mNeedToDraw = true;
                    mEditorEngineWrapper.setProp(0xa60002, true);
                }
                k = faceBeautiferProcess();
            } else
            {
                mEffectType = 0;
                long l2 = System.currentTimeMillis();
                mEditorEngineWrapper.switchEditorEngineTool(9);
                Log.v("EditorTools_Time", (new StringBuilder()).append("SwitchEffect Time is : ").append(System.currentTimeMillis() - l2).toString());
                if ((4 & mWillDoType) == 4)
                {
                    mNeedToDraw = true;
                    mEditorEngineWrapper.setProp(0xa60002, true);
                }
                long l3 = System.currentTimeMillis();
                if (mUsePlugin)
                {
                    k = mEditorEngineWrapper.doAdaptiveEffect(mEffectPlugin);
                } else
                {
                    k = mEditorEngineWrapper.doEffect(mEffectID, mEffectParam);
                }
                Log.v("EditorTools_Time", (new StringBuilder()).append("doEffect Time is : ").append(System.currentTimeMillis() - l3).toString());
            }
            Log.v("EditorTools", (new StringBuilder()).append("In DOTYPE_DO3 res : ").append(k).toString());
            if (k == 0)
            {
                mDoEffectSucc = true;
                return k;
            } else
            {
                Log.v("EditorTools", "DoEffect NoUseUndo Failed");
                Log.v("EditorTools", (new StringBuilder()).append("DoEffect NoUseUndo Failed mNeedDoFaceBeautify : ").append(mNeedDoFaceBeautify).toString());
                return k;
            }
        }
        break MISSING_BLOCK_LABEL_953;
        Log.v("EditorTools", "DoAutofix Failed");
        return i;
        Log.v("EditorTools", "DoMiniature Failed");
        return i;
        int j;
        if (mNeedDoFaceBeautify)
        {
            Log.v("EditorTools", "In DOTYPE_DO3 mNeedDoFaceBeautify true");
            if (mEffectType == 0)
            {
                Log.v("EditorTools", "In DOTYPE_DO3 mEffectType=0");
                mEditorEngineWrapper.detachTools();
            }
            mEffectType = 1;
            mEditorEngineWrapper.switchEditorEngineTool(10);
            if ((4 & mWillDoType) == 4)
            {
                mNeedToDraw = true;
                mEditorEngineWrapper.setProp(0xa60002, true);
            }
            j = faceBeautiferProcess();
        } else
        {
            Log.v("EditorTools", "In DOTYPE_DO3 mNeedDoFaceBeautify false");
            if (mEffectType == 1)
            {
                Log.v("EditorTools", "In DOTYPE_DO3 mEffectType=1");
                mEditorEngineWrapper.detachTools();
            }
            mEffectType = 0;
            long l = System.currentTimeMillis();
            mEditorEngineWrapper.switchEditorEngineTool(9);
            Log.v("EditorTools_Time", (new StringBuilder()).append("SwitchEffect Time is : ").append(System.currentTimeMillis() - l).toString());
            if ((4 & mWillDoType) == 4)
            {
                mNeedToDraw = true;
                mEditorEngineWrapper.setProp(0xa60002, true);
            }
            long l1 = System.currentTimeMillis();
            if (mUsePlugin)
            {
                j = mEditorEngineWrapper.doAdaptiveEffect(mEffectPlugin);
            } else
            {
                j = mEditorEngineWrapper.doEffect(mEffectID, mEffectParam);
            }
            Log.v("EditorTools_Time", (new StringBuilder()).append("doEffect Time is : ").append(System.currentTimeMillis() - l1).toString());
        }
        Log.v("EditorTools", (new StringBuilder()).append("In DOTYPE_DO3 res : ").append(j).toString());
        mEditorEngineWrapper.undoPointSet(false);
        if (j == 0)
        {
            mDoEffectSucc = true;
            return j;
        } else
        {
            Log.v("EditorTools", "DoEffect NoUseUndo Failed");
            Log.v("EditorTools", (new StringBuilder()).append("DoEffect NoUseUndo Failed mNeedDoFaceBeautify : ").append(mNeedDoFaceBeautify).toString());
            return j;
        }
    }

    private int resetProcess()
    {
        int i = mEditorEngineWrapper.reset();
        Log.v("ERROR", (new StringBuilder()).append("reset res : ").append(i).toString());
        if (i != 0)
        {
            mCurDoType = 0;
            mUIMethodForTools.errorForReset();
        }
        return i;
    }

    private void resetState()
    {
        mShouldUseReset = false;
        mDoAutoFixSucc = false;
        mDoMiniatureSucc = false;
        mDoEffectSucc = false;
        mDoCropSucc = false;
        mWillDoType = 0;
        mDoTypeMask = 7;
        mShouldProcess = true;
    }

    private void updateEffectPluginParam(Object obj, Object obj1)
    {
        ((Integer)obj).intValue();
        JVM INSTR tableswitch 23 37: default 80
    //                   23 81
    //                   24 256
    //                   25 334
    //                   26 80
    //                   27 80
    //                   28 80
    //                   29 80
    //                   30 80
    //                   31 80
    //                   32 177
    //                   33 412
    //                   34 490
    //                   35 569
    //                   36 649
    //                   37 735;
           goto _L1 _L2 _L3 _L4 _L1 _L1 _L1 _L1 _L1 _L1 _L5 _L6 _L7 _L8 _L9 _L10
_L1:
        return;
_L2:
        if (mEffectPlugin != null && (mEffectPlugin == null || (mEffectPlugin instanceof EffectPolaroidIP)))
        {
            break; /* Loop/switch isn't completed */
        }
        mEffectPlugin = new EffectPolaroidIP();
        if (mEffectPlugin == null)
        {
            continue; /* Loop/switch isn't completed */
        }
        mEffectPluginParam = new int[6];
        break; /* Loop/switch isn't completed */
        if (true) goto _L1; else goto _L11
_L11:
        Integer integer1 = (Integer)obj1;
        mEffectPluginParam[0] = integer1.intValue();
        mEffectPluginParam[1] = 1;
        mEffectPlugin.setParameter(mEffectPluginParam);
        mFramePath = null;
        mTexturePath = null;
        return;
_L5:
        if (mEffectPlugin != null && (mEffectPlugin == null || (mEffectPlugin instanceof EffectFisheyeIP)))
        {
            break; /* Loop/switch isn't completed */
        }
        mEffectPlugin = new EffectFisheyeIP();
        if (mEffectPlugin == null)
        {
            continue; /* Loop/switch isn't completed */
        }
        mEffectPluginParam = new int[1];
        break; /* Loop/switch isn't completed */
        if (true) goto _L1; else goto _L12
_L12:
        mEffectPluginParam[0] = 50;
        mEffectPlugin.setParameter(mEffectPluginParam);
        mFramePath = null;
        mTexturePath = null;
        return;
_L3:
        if (mEffectPlugin != null && (mEffectPlugin == null || (mEffectPlugin instanceof EffectGothicIP)))
        {
            break; /* Loop/switch isn't completed */
        }
        mEffectPlugin = new EffectGothicIP();
        if (mEffectPlugin == null)
        {
            continue; /* Loop/switch isn't completed */
        }
        mEffectPluginParam = new int[5];
        break; /* Loop/switch isn't completed */
        if (true) goto _L1; else goto _L13
_L13:
        mEffectPluginParam[0] = 1;
        mEffectPlugin.setParameter(mEffectPluginParam);
        mFramePath = null;
        mTexturePath = null;
        return;
_L4:
        if (mEffectPlugin != null && (mEffectPlugin == null || (mEffectPlugin instanceof EffectDeepquiteIP)))
        {
            break; /* Loop/switch isn't completed */
        }
        mEffectPlugin = new EffectDeepquiteIP();
        if (mEffectPlugin == null)
        {
            continue; /* Loop/switch isn't completed */
        }
        mEffectPluginParam = new int[5];
        break; /* Loop/switch isn't completed */
        if (true) goto _L1; else goto _L14
_L14:
        mEffectPluginParam[0] = 1;
        mEffectPlugin.setParameter(mEffectPluginParam);
        mFramePath = null;
        mTexturePath = null;
        return;
_L6:
        if (mEffectPlugin != null && (mEffectPlugin == null || (mEffectPlugin instanceof EffectSweetvintageIP)))
        {
            break; /* Loop/switch isn't completed */
        }
        mEffectPlugin = new EffectSweetvintageIP();
        if (mEffectPlugin == null)
        {
            continue; /* Loop/switch isn't completed */
        }
        mEffectPluginParam = new int[5];
        break; /* Loop/switch isn't completed */
        if (true) goto _L1; else goto _L15
_L15:
        mEffectPluginParam[0] = 1;
        mEffectPlugin.setParameter(mEffectPluginParam);
        mFramePath = null;
        mTexturePath = null;
        return;
_L7:
        if (mEffectPlugin != null && (mEffectPlugin == null || (mEffectPlugin instanceof EffectNewSketchIP)))
        {
            break; /* Loop/switch isn't completed */
        }
        mEffectPlugin = new EffectNewSketchIP();
        if (mEffectPlugin == null)
        {
            continue; /* Loop/switch isn't completed */
        }
        mEffectPluginParam = new int[1];
        break; /* Loop/switch isn't completed */
        if (true) goto _L1; else goto _L16
_L16:
        mEffectPluginParam[0] = 10;
        mEffectPlugin.setParameter(mEffectPluginParam);
        mFramePath = null;
        mTexturePath = null;
        return;
_L8:
        if (mEffectPlugin != null && (mEffectPlugin == null || (mEffectPlugin instanceof EffectWashedawayIP)))
        {
            break; /* Loop/switch isn't completed */
        }
        mEffectPlugin = new EffectWashedawayIP();
        if (mEffectPlugin == null)
        {
            continue; /* Loop/switch isn't completed */
        }
        mEffectPluginParam = new int[5];
        break; /* Loop/switch isn't completed */
        if (true) goto _L1; else goto _L17
_L17:
        mEffectPluginParam[0] = 1;
        mEffectPlugin.setParameter(mEffectPluginParam);
        mFramePath = null;
        mTexturePath = "/data/data/com.arcsoft.mediaplus/data/contents/frames/frames/washaway.png";
        return;
_L9:
        if (mEffectPlugin != null && (mEffectPlugin == null || (mEffectPlugin instanceof EffectColorfilterIP)))
        {
            break; /* Loop/switch isn't completed */
        }
        mEffectPlugin = new EffectColorfilterIP();
        if (mEffectPlugin == null)
        {
            continue; /* Loop/switch isn't completed */
        }
        mEffectPluginParam = new int[5];
        break; /* Loop/switch isn't completed */
        if (true) goto _L1; else goto _L18
_L18:
        Integer integer = (Integer)obj1;
        mEffectPluginParam[0] = integer.intValue();
        mEffectPlugin.setParameter(mEffectPluginParam);
        mFramePath = null;
        mTexturePath = null;
        return;
_L10:
        if (mEffectPlugin != null && (mEffectPlugin == null || (mEffectPlugin instanceof EffectColdBlueIP)))
        {
            break; /* Loop/switch isn't completed */
        }
        mEffectPlugin = new EffectColdBlueIP();
        if (mEffectPlugin == null)
        {
            continue; /* Loop/switch isn't completed */
        }
        mEffectPluginParam = new int[5];
        break; /* Loop/switch isn't completed */
        if (true) goto _L1; else goto _L19
_L19:
        mEffectPluginParam[0] = 1;
        mEffectPlugin.setParameter(mEffectPluginParam);
        mFramePath = "/data/data/com.arcsoft.mediaplus/data/contents/frames/frames/gentlecool320.9.png";
        mTexturePath = null;
        return;
    }

    private void updateParam(int i, Object obj, Object obj1)
    {
        switch (i)
        {
        default:
            return;

        case 20: // '\024'
            if (mMiniatureParam == null)
            {
                mMiniatureParam = new int[10];
            }
            int ai1[] = (int[])(int[])obj;
            for (int j = 0; j < 10; j++)
            {
                mMiniatureParam[j] = ai1[j];
            }

            mMiniaturePlugin = new EffectMiniatureIP();
            mMiniaturePlugin.setParameter(mMiniatureParam);
            return;

        case 21: // '\025'
            mMiniatureParam = null;
            mMiniaturePlugin = null;
            return;

        case 9: // '\t'
            mEffectID = ((Integer)obj).intValue();
            mUsePlugin = false;
            mEffectPlugin = null;
            mEffectPluginParam = null;
            switch (mEffectID)
            {
            default:
                mEffectParam = null;
                mFramePath = null;
                mTexturePath = null;
                return;

            case 11195180: 
                Integer integer = (Integer)obj1;
                mEffectTypeForFilter = integer.intValue();
                int ai[] = new int[5];
                ai[0] = integer.intValue();
                ai[1] = 0;
                ai[2] = 0;
                ai[3] = 0;
                ai[4] = 0;
                mEffectParam = ai;
                if (mEffectTypeForFilter == 0xaad4e4)
                {
                    mFramePath = "/data/data/com.arcsoft.mediaplus/data/contents/frames/frames/softlomo3202.9.png";
                    mTexturePath = "/data/data/com.arcsoft.mediaplus/data/contents/frames/frames/softlomo1280.png";
                    return;
                } else
                {
                    mFramePath = null;
                    mTexturePath = null;
                    return;
                }

            case 11195167: 
                mEffectParam = mCartoonParamsDef;
                mFramePath = null;
                mTexturePath = null;
                return;
            }

        case 22: // '\026'
            mUsePlugin = true;
            mEffectID = 0xaad300;
            mEffectParam = null;
            updateEffectPluginParam(obj, obj1);
            return;

        case 7: // '\007'
            mEffectID = 0xaad300;
            mEffectParam = null;
            mFramePath = null;
            return;
        }
    }

    public String getFramePath()
    {
        return mFramePath;
    }

    public String getTexturePath()
    {
        return mTexturePath;
    }

    public boolean isNeedDoAsyncTask()
    {
        return mNeedToRefresh;
    }

    public boolean isNeedToDraw()
    {
        return mNeedToDraw;
    }

    public int onCommand(int i, Object obj, Object obj1)
    {
        long l = System.currentTimeMillis();
        mNeedToRefresh = false;
        mWorkShop.stopAysnTask();
        if (i == 17)
        {
            if (obj != null)
            {
                Rect rect = (Rect)obj;
                MRect mrect = new MRect(rect.left, rect.top, rect.right, rect.bottom);
                if (mrect.height() == 0 || mrect.width() == 0)
                {
                    mNeedToRefresh = true;
                    mWorkShop.resumeAysnTask();
                    return 2;
                }
                if (mCropRect == null)
                {
                    mCropRect = new MRect();
                }
                mEditorEngineWrapper.rectScreenToImg(mrect, mCropRect);
                mNeedToDraw = false;
                mEditorEngineWrapper.switchEditorEngineTool(12);
                int i1 = cropProcess(mrect);
                mNeedToDraw = true;
                mWorkShop.updateView(null);
                mImageNotify.imageChanged();
                mNeedToRefresh = true;
                mWorkShop.resumeAysnTask();
                return i1;
            } else
            {
                mNeedToRefresh = true;
                mWorkShop.resumeAysnTask();
                return 2;
            }
        }
        Log.v("EditorTools", (new StringBuilder()).append("beign mCurDoType : ").append(mCurDoType).toString());
        resetState();
        Log.v("EditorTools", (new StringBuilder()).append("to judgment : ").append(mWillDoType).toString());
        int j = judgmentUndoTypeAndDoType(i, obj, obj1);
        Log.v("EditorTools", (new StringBuilder()).append("leave judgment : ").append(mWillDoType).toString());
        if (j != 0)
        {
            mNeedToRefresh = true;
            mWorkShop.resumeAysnTask();
            return j;
        }
        preProcessBasedOnParam(i, obj, obj1);
        if (!mShouldProcess)
        {
            Log.v("EditorTools", "should not process");
            mNeedToRefresh = true;
            mWorkShop.resumeAysnTask();
            return 0;
        }
        if (mShouldUseReset && (mWillDoType != 0 || mHasCroped))
        {
            Log.v("EditorTools", "In PROP_AUTOREFRESH False");
            mNeedToDraw = false;
            mEditorEngineWrapper.setProp(0xa60002, false);
        }
        if (mShouldUseReset)
        {
            Log.v("EditorTools", "In mShouldUseReset");
            long l3 = System.currentTimeMillis();
            j = resetProcess();
            Log.v("EditorTools_Time", (new StringBuilder()).append("resetProcess Time is : ").append(System.currentTimeMillis() - l3).toString());
            if (j != 0)
            {
                Log.v("EditorTools", "resetProcess error");
                mEditorEngineWrapper.setProp(0xa60002, true);
                mNeedToDraw = true;
                mEditorEngineWrapper.refreshDisplay();
                return 0;
            }
            Log.v("EditorTools", "Out mShouldUseReset");
        }
        updateParam(i, obj, obj1);
        if (j == 0 && mWillDoType == 0)
        {
            Log.v("EditorTools", "In processWillDoTypeNone");
            long l2 = System.currentTimeMillis();
            processWillDoTypeNone();
            Log.v("EditorTools_Time", (new StringBuilder()).append("processWillDoTypeNone Time is : ").append(System.currentTimeMillis() - l2).toString());
            Log.v("EditorTools", (new StringBuilder()).append("processWillDoTypeNone mCurDoType : ").append(mCurDoType).toString());
            mImageNotify.imageChanged();
            mNeedToRefresh = true;
            mWorkShop.resumeAysnTask();
            return 0;
        }
        Log.v("EditorTools", "to processWillDoTypeNotNone");
        long l1 = System.currentTimeMillis();
        int k = processWillDoTypeNotNone();
        Log.v("EditorTools_Time", (new StringBuilder()).append("processWillDoTypeNotNone Time is : ").append(System.currentTimeMillis() - l1).toString());
        Log.v("EditorTools", (new StringBuilder()).append("afterprocess mCurDoType : ").append(mCurDoType).toString());
        Log.v("EditorTools", (new StringBuilder()).append("afterprocess mWillDoType : ").append(mWillDoType).toString());
        Log.v("EditorTools", (new StringBuilder()).append("afterprocess mDoTypeMask : ").append(mDoTypeMask).toString());
        Log.v("EditorTools", "leave processWillDoTypeNotNone");
        Log.v("EditorTools", (new StringBuilder()).append("processWillDoType res : ").append(k).toString());
        if (k == 0)
        {
            mCurDoType = (mCurDoType | mWillDoType) & mDoTypeMask;
        } else
        {
            k = errorProcess();
        }
        Log.v("EditorTools", "leave processWillDoTypeNotNone");
        Log.v("EditorTools", (new StringBuilder()).append("updateDoType mCurDoType : ").append(mCurDoType).toString());
        if (mShouldUseReset && (mWillDoType != 0 || mHasCroped))
        {
            mEditorEngineWrapper.setProp(0xa60002, true);
            mNeedToDraw = true;
        }
        mNeedToDraw = true;
        mNeedToRefresh = true;
        mWorkShop.resumeAysnTask();
        mImageNotify.imageChanged();
        Log.v("EditorTools_Time", (new StringBuilder()).append("Sum Time is : ").append(System.currentTimeMillis() - l).toString());
        return k;
    }

}
