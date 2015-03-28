// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.arcsoft.workshop;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.DisplayMetrics;
import android.view.Display;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.WindowManager;
import android.widget.Toast;
import com.arcsoft.util.debug.Log;
import com.arcsoft.workshop.tools.EditorTools;
import com.arcsoft.workshop.tools.EditorToolsBase;
import com.arcsoft.workshop.tools.EditorToolsManager;
import com.arcsoft.workshop.ui.UIManagerConsole;
import com.arcsoft.workshop.ui.UISaveDialog;
import com.arcsoft.workshop.utils.WorkShopUtils;
import java.io.File;
import powermobia.utils.DisplayContext;
import powermobia.utils.MBitmap;
import powermobia.utils.MBitmapFactory;
import powermobia.utils.MColorSpace;
import powermobia.utils.MPoint;
import powermobia.utils.MRect;

// Referenced classes of package com.arcsoft.workshop:
//            OnCommandListener, SaveThreadForShare, EditorEngineWrapper, ZoomPanController, 
//            AssetsCopier, AsyncTaskRunner

public class WorkShop extends Activity
    implements OnCommandListener
{
    private class EditorDisplayContext extends DisplayContext
    {

        final WorkShop this$0;

        public int bitBlt(MBitmap mbitmap, MRect mrect)
        {
            Log.v("Miniature", "bitBlt");
            if (!isSurfaceDestory()) goto _L2; else goto _L1
_L1:
            return 0;
_L2:
            if (mEditorToolsManager == null)
            {
                break; /* Loop/switch isn't completed */
            }
            EditorToolsBase editortoolsbase = mEditorToolsManager.getEditorTools();
            if (editortoolsbase == null || !(editortoolsbase instanceof EditorTools))
            {
                break; /* Loop/switch isn't completed */
            }
            EditorTools editortools = (EditorTools)editortoolsbase;
            if (!editortools.isNeedToDraw())
            {
                continue; /* Loop/switch isn't completed */
            }
            String s = editortools.getFramePath();
            String s1 = editortools.getTexturePath();
            if (s != null || s1 != null)
            {
                MRect mrect1 = new MRect();
                mEditorEngineWrapper.getImgScreenRect(mrect1);
                int i = WorkShopUtils.addFrame(mbitmap, mrect1, s, s1);
                Log.v("Frame", (new StringBuilder()).append("addFrame : ").append(i).toString());
            }
            break; /* Loop/switch isn't completed */
            if (true) goto _L1; else goto _L3
_L3:
            updateView(mrect);
            return 0;
        }

        public int eraseBackground(MBitmap mbitmap, MRect mrect)
        {
            if (mBgState == 0)
            {
                WorkShop.mMBitmap.fillColor(0x383837, new MRect(0, 0, mEditorViewWidth, mEditorViewHeight), null, 100);
            } else
            if (mBgState == 1)
            {
                WorkShopUtils.copyBitmap(WorkShop.mMBitmap, mMBitmapBkg, new MPoint());
                return 0;
            }
            return 0;
        }

        public MBitmap lockOffScreenBuffer(int i, int j)
        {
            screenBufferNullProcess();
            return WorkShop.mMBitmap;
        }

        public int unlockOffScreenBuffer(MBitmap mbitmap)
        {
            return 0;
        }

        private EditorDisplayContext()
        {
            this$0 = WorkShop.this;
            super();
        }

    }

    private class EditorNotify
        implements powermobia.photoeditor.EditorEngine.Notify
    {

        final WorkShop this$0;

        public int cbFaceDecteted(int i, int j, int k, int l)
        {
            return 0;
        }

        public int cbNotify(int i, int j)
        {
            if (i != 0xa7e001 || !isNeedDoAsyncTask())
            {
                return 0;
            } else
            {
                mAsyncTaskRunner.notifyAsyncTask();
                return 0;
            }
        }

        private EditorNotify()
        {
            this$0 = WorkShop.this;
            super();
        }

    }

    public static interface IImageNotifyChange
    {

        public abstract void imageChanged();
    }


    private static final String INPUTFILENAME = "INPUTFILENAME";
    public static final int MESSAGE_SAVE_ALREADY = 12;
    public static final int MESSAGE_SAVE_FAILED = 11;
    public static final int MESSAGE_SAVE_SUCCESS = 10;
    public static final String SAVE_COUNT = "save_count";
    private static Bitmap mBitmap;
    private static MBitmap mMBitmap;
    private AsyncTaskRunner mAsyncTaskRunner;
    private boolean mBNeedDelTmpFile;
    private final int mBgColorForCrop = 0x383837;
    private int mBgState;
    private String mCurrentFilePath;
    private EditorEngineWrapper mEditorEngineWrapper;
    private EditorToolsManager mEditorToolsManager;
    private int mEditorViewHeight;
    private int mEditorViewWidth;
    private final Handler mHandlerForShare = new Handler() ;
    private int mHeightOffset;
    private final IImageNotifyChange mImageNotify = new IImageNotifyChange() {

        final WorkShop this$0;

        public void imageChanged()
        {
            mShoudProcessPic = true;
            mShareUriPicChanged = null;
        }

            
            {
                this$0 = WorkShop.this;
                super();
            }
    };
    private MBitmap mMBitmapBkg;
    private int mSaveCount;
    private UISaveDialog mSaveDlg;
    private Intent mSaveIntent;
    private SaveThreadForShare mSaveThreadForShave;
    private Uri mShareUriPicChanged;
    private Uri mShareUriPicNotChanged;
    private boolean mShoudProcessPic;
    private String mTmpFileName;
    private UIManagerConsole mUIManagerConsole;
    private String mWillOpenFilePath;
    private ZoomPanController mZoomPanController;
    private boolean mbLoadingPicture;

    public WorkShop()
    {
        mEditorEngineWrapper = null;
        mEditorToolsManager = null;
        mUIManagerConsole = null;
        mZoomPanController = null;
        mAsyncTaskRunner = null;
        mBgState = 0;
        mMBitmapBkg = null;
        mEditorViewWidth = 0;
        mEditorViewHeight = 0;
        mHeightOffset = 0;
        mBNeedDelTmpFile = false;
        mTmpFileName = null;
        mCurrentFilePath = null;
        mWillOpenFilePath = null;
        mbLoadingPicture = false;
        mSaveCount = 0;
        mSaveIntent = null;
        mSaveDlg = null;
        mSaveThreadForShave = null;
        mShoudProcessPic = true;
        mShareUriPicChanged = null;
        mShareUriPicNotChanged = null;
    }

    private void createSaveThread(final boolean changed, boolean flag)
    {
        mSaveDlg = new UISaveDialog(this, 0x7f0d0003);
        mSaveDlg.show();
        mSaveThreadForShave = new SaveThreadForShare(this, flag);
        mSaveThreadForShave.setOnSaveProcessTaskListener(new SaveThreadForShare.OnSaveProcessTaskListener() {

            final WorkShop this$0;
            final boolean val$changed;

            public void onSaveProcessCancel()
            {
                mSaveThreadForShave = null;
                mSaveDlg.dismiss();
                resetZoomPaningState();
                mAsyncTaskRunner.forceAsyncTask();
            }

            public void onSaveProcessDone(Uri uri, boolean flag1)
            {
                mSaveThreadForShave = null;
                mSaveDlg.dismiss();
                resetZoomPaningState();
                Message message;
                if (changed)
                {
                    mShoudProcessPic = false;
                    mShareUriPicChanged = uri;
                } else
                {
                    mShareUriPicNotChanged = uri;
                }
                if (flag1)
                {
                    WorkShopUtils.sharePhotoByUri(uri, WorkShop.this);
                }
                message = new Message();
                message.what = 10;
                message.obj = Boolean.valueOf(flag1);
                mHandlerForShare.sendMessage(message);
            }

            public void onSaveProcessErrorOccured(boolean flag1)
            {
                mSaveThreadForShave = null;
                mSaveDlg.dismiss();
                resetZoomPaningState();
                mAsyncTaskRunner.forceAsyncTask();
                Handler handler = mHandlerForShare;
                int i;
                if (flag1)
                {
                    i = 0;
                } else
                {
                    i = 1;
                }
                handler.sendEmptyMessage(i);
                mHandlerForShare.sendEmptyMessage(11);
            }

            
            {
                this$0 = WorkShop.this;
                changed = flag;
                super();
            }
        });
        mSaveThreadForShave.start();
    }

    private void finishWorkShop()
    {
        finish();
        overridePendingTransition(0x7f040001, 0x7f040002);
    }

    private void initEditorEngineWrapper()
    {
        screenBufferNullProcess();
        EditorNotify editornotify = new EditorNotify();
        EditorDisplayContext editordisplaycontext = new EditorDisplayContext();
        editordisplaycontext.displayWidth = mEditorViewWidth;
        editordisplaycontext.displayHeight = mEditorViewHeight;
        editordisplaycontext.pixelArrayFormat = MColorSpace.MPAF_RGB32_B8G8R8A8;
        mEditorEngineWrapper.init(editornotify, editordisplaycontext);
    }

    private int loadNewPicture(String s)
    {
        mEditorEngineWrapper.setProp(0xa60002, false);
        mbLoadingPicture = true;
        int i;
        if (s == null)
        {
            i = 2;
        } else
        {
            i = mEditorEngineWrapper.load(0, s);
        }
        if (i == 0)
        {
            mCurrentFilePath = s;
            mWillOpenFilePath = null;
            if (mUIManagerConsole != null)
            {
                mUIManagerConsole.onChange(0, null, null);
            }
            mEditorEngineWrapper.setProp(0xa60002, true);
            mbLoadingPicture = false;
            WorkShopUtils.back2BestFitMode(this);
            return i;
        } else
        {
            finishWorkShop();
            return i;
        }
    }

    private void resetZoomPaningState()
    {
        mZoomPanController.resetZoomPaningState();
        if (mUIManagerConsole != null)
        {
            mUIManagerConsole.resetEditorViewState();
        }
    }

    private void saveWithShareOrNot(boolean flag)
    {
label0:
        {
label1:
            {
                if (mCurrentFilePath != null)
                {
                    Boolean boolean1 = new Boolean(false);
                    mEditorEngineWrapper.isModified(boolean1);
                    if (!boolean1.booleanValue())
                    {
                        break label0;
                    }
                    if (!mShoudProcessPic && mShareUriPicChanged != null)
                    {
                        break label1;
                    }
                    createSaveThread(true, flag);
                }
                return;
            }
            if (flag)
            {
                WorkShopUtils.sharePhotoByUri(mShareUriPicChanged, this);
                return;
            } else
            {
                toastForSave(12, this);
                return;
            }
        }
        if (mShareUriPicNotChanged == null)
        {
            createSaveThread(false, flag);
            return;
        }
        if (flag)
        {
            WorkShopUtils.sharePhotoByUri(mShareUriPicNotChanged, this);
            return;
        } else
        {
            toastForSave(12, this);
            return;
        }
    }

    public static void toastForSave(int i, Context context)
    {
        if (context != null) goto _L2; else goto _L1
_L1:
        return;
_L2:
        int j = 0;
        i;
        JVM INSTR tableswitch 10 12: default 36
    //                   10 57
    //                   11 64
    //                   12 71;
           goto _L3 _L4 _L5 _L6
_L6:
        break MISSING_BLOCK_LABEL_71;
_L3:
        break; /* Loop/switch isn't completed */
_L4:
        break; /* Loop/switch isn't completed */
_L8:
        if (j != 0)
        {
            Toast.makeText(context, context.getResources().getString(j), 0).show();
            return;
        }
        if (true) goto _L1; else goto _L7
_L7:
        j = 0x7f0b0174;
          goto _L8
_L5:
        j = 0x7f0b0175;
          goto _L8
        j = 0x7f0b0176;
          goto _L8
    }

    public int getBackgroundColorForCrop()
    {
        return 0x383837;
    }

    public MBitmap getBackgroundMBitmap()
    {
        return mMBitmapBkg;
    }

    public int getColorSpace()
    {
        return MColorSpace.MPAF_RGB32_B8G8R8A8;
    }

    public String getCurrentFilePath()
    {
        return mCurrentFilePath;
    }

    public Bitmap getDisplayBitmap()
    {
        return mBitmap;
    }

    public int getDisplayH()
    {
        return mEditorViewHeight;
    }

    public MBitmap getDisplayMBitmap()
    {
        return mMBitmap;
    }

    public int getDisplayW()
    {
        return mEditorViewWidth;
    }

    public EditorEngineWrapper getEditorEngineWrapper()
    {
        return mEditorEngineWrapper;
    }

    public EditorToolsManager getEditorToolsManager()
    {
        return mEditorToolsManager;
    }

    public com.arcsoft.workshop.ui.UIManagerConsole.IUIMethodForTools getIUIMethodForTools()
    {
        if (mUIManagerConsole != null)
        {
            return mUIManagerConsole.getIUIMethodForTools();
        } else
        {
            return null;
        }
    }

    public IImageNotifyChange getImageNotifyChange()
    {
        return mImageNotify;
    }

    public boolean getSaveStart()
    {
        if (mSaveThreadForShave != null)
        {
            return mSaveThreadForShave.getSaveStart();
        } else
        {
            return false;
        }
    }

    public UIManagerConsole getUIManagerConsole()
    {
        return mUIManagerConsole;
    }

    public boolean isNeedDoAsyncTask()
    {
        if (mEditorToolsManager != null)
        {
            EditorToolsBase editortoolsbase = mEditorToolsManager.getEditorTools();
            if (editortoolsbase != null && (editortoolsbase instanceof EditorTools))
            {
                return ((EditorTools)editortoolsbase).isNeedDoAsyncTask();
            }
        }
        return true;
    }

    public boolean isSurfaceDestory()
    {
        if (mUIManagerConsole != null)
        {
            return mUIManagerConsole.isSurfaceDestory();
        } else
        {
            return false;
        }
    }

    public boolean isZoomPaning()
    {
        return mZoomPanController.isZoomPaning();
    }

    public int loadNewPicture(String s, boolean flag)
    {
        mEditorEngineWrapper.setProp(0xa60002, false);
        mbLoadingPicture = true;
        int i;
        if (s == null)
        {
            i = 2;
        } else
        {
            i = mEditorEngineWrapper.load(0, s);
        }
        if (i == 0)
        {
            mTmpFileName = s;
            mBNeedDelTmpFile = flag;
            mEditorEngineWrapper.setProp(0xa60002, true);
            mbLoadingPicture = false;
            return i;
        } else
        {
            Toast.makeText(this, 0x7f0b0158, 1).show();
            finishWorkShop();
            return i;
        }
    }

    public int onCommand(int i, Object obj, Object obj1)
    {
        if (isFinishing())
        {
            Log.v("WorkShop", "isFinishing");
            return 0;
        }
        if (mSaveThreadForShave != null && mSaveThreadForShave.getSaveStart())
        {
            Log.v("WorkShop", "Save Start");
            return 0;
        }
        i;
        JVM INSTR lookupswitch 5: default 100
    //                   4: 187
    //                   5: 207
    //                   18: 221
    //                   38: 137
    //                   39: 162;
           goto _L1 _L2 _L3 _L4 _L5 _L6
_L1:
        int j;
        EditorToolsManager editortoolsmanager = mEditorToolsManager;
        j = 0;
        if (editortoolsmanager == null)
        {
            break; /* Loop/switch isn't completed */
        }
        if (i != 0) goto _L8; else goto _L7
_L7:
        j = mEditorToolsManager.selectTools(((Integer)obj).intValue());
_L10:
        return j;
_L5:
        String s1 = mCurrentFilePath;
        j = 0;
        if (s1 != null)
        {
            saveWithShareOrNot(false);
            j = 0;
        }
        continue; /* Loop/switch isn't completed */
_L6:
        String s = mCurrentFilePath;
        j = 0;
        if (s != null)
        {
            saveWithShareOrNot(true);
            j = 0;
        }
        continue; /* Loop/switch isn't completed */
_L2:
        Log.v("WorkShop", "CMD_CANCEL");
        finishWorkShop();
        j = 0;
        continue; /* Loop/switch isn't completed */
_L3:
        mEditorEngineWrapper.refreshDisplay();
        j = 0;
        continue; /* Loop/switch isn't completed */
_L4:
        if (obj == null)
        {
            return 2;
        }
        MRect mrect = (MRect)obj;
        mEditorEngineWrapper.getImgScreenRect(mrect);
        j = 0;
        continue; /* Loop/switch isn't completed */
_L8:
        EditorToolsBase editortoolsbase = mEditorToolsManager.getEditorTools();
        j = 0;
        if (editortoolsbase != null)
        {
            j = mEditorToolsManager.getEditorTools().onCommand(i, obj, obj1);
        }
        if (true) goto _L10; else goto _L9
_L9:
    }

    public void onConfigurationChanged(Configuration configuration)
    {
        super.onConfigurationChanged(configuration);
        setRequestedOrientation(1);
    }

    protected void onCreate(Bundle bundle)
    {
        super.onCreate(bundle);
        Intent intent = getIntent();
        if (intent == null)
        {
            finishWorkShop();
        } else
        {
            mWillOpenFilePath = intent.getStringExtra("INPUTFILENAME");
            if (mWillOpenFilePath == null)
            {
                finishWorkShop();
                return;
            }
            if (!(new File(mWillOpenFilePath)).exists())
            {
                finishWorkShop();
                return;
            }
            (new AssetsCopier(getAssets())).copyAssetFile();
            if (android.os.Build.VERSION.SDK_INT == 11 || android.os.Build.VERSION.SDK_INT == 12)
            {
                mHeightOffset = 48;
            }
            Display display = getWindowManager().getDefaultDisplay();
            if (display.getWidth() > display.getHeight())
            {
                mEditorViewWidth = display.getHeight();
                mEditorViewHeight = display.getWidth() - mHeightOffset;
            } else
            {
                mEditorViewWidth = display.getWidth();
                mEditorViewHeight = display.getHeight() - mHeightOffset;
            }
            mEditorEngineWrapper = new EditorEngineWrapper();
            initEditorEngineWrapper();
            mZoomPanController = new ZoomPanController(this);
            mUIManagerConsole = new UIManagerConsole(this, this);
            mUIManagerConsole.initUI(mZoomPanController.createOnZoomGestureListener(), mZoomPanController.createOnPanGestureListener());
            mEditorToolsManager = new EditorToolsManager(this);
            mZoomPanController.setEditorEngineWrapper(mEditorEngineWrapper);
            mZoomPanController.setImageEventListener(mUIManagerConsole);
            mAsyncTaskRunner = new AsyncTaskRunner(this, mEditorEngineWrapper);
            if (loadNewPicture(mWillOpenFilePath) == 0)
            {
                mUIManagerConsole.initCropView();
                return;
            }
        }
    }

    protected void onDestroy()
    {
        super.onDestroy();
        Log.v("EditorTools_Time", "onDestroy");
        if (mEditorEngineWrapper != null)
        {
            mEditorEngineWrapper.uninit();
            mEditorEngineWrapper = null;
        }
        WorkShopUtils.destory_workshoputils();
        if (mMBitmap != null)
        {
            mMBitmap.recycle();
            mMBitmap = null;
        }
        if (mBitmap != null)
        {
            mBitmap.recycle();
            mBitmap = null;
        }
        if (mMBitmapBkg != null)
        {
            mMBitmapBkg.recycle();
            mMBitmapBkg = null;
        }
        if (mUIManagerConsole != null)
        {
            mUIManagerConsole.uninitUI();
            mUIManagerConsole = null;
        }
        if (mSaveThreadForShave != null)
        {
            mSaveThreadForShave.destroy();
            mSaveThreadForShave = null;
        }
        if (mSaveDlg != null)
        {
            mSaveDlg.dismiss();
            mSaveDlg = null;
        }
        if (mBNeedDelTmpFile && mTmpFileName != null)
        {
            File file = new File(mTmpFileName);
            if (file.exists())
            {
                file.delete();
            }
        }
    }

    public boolean onKeyDown(int i, KeyEvent keyevent)
    {
        i;
        JVM INSTR tableswitch 4 4: default 20
    //                   4 27;
           goto _L1 _L2
_L1:
        return super.onKeyDown(i, keyevent);
_L2:
        Log.v("WorkShop", "KEYCODE_BACK");
        finishWorkShop();
        if (true) goto _L1; else goto _L3
_L3:
    }

    protected void onPause()
    {
        super.onPause();
        if (android.os.Build.VERSION.SDK_INT > 8)
        {
            WorkShopUtils.releaseANativeWindow();
        }
    }

    public boolean onPrepareOptionsMenu(Menu menu)
    {
        if (mUIManagerConsole != null)
        {
            mUIManagerConsole.onPrepareOptionsMenu(menu);
        }
        return super.onPrepareOptionsMenu(menu);
    }

    protected void onResume()
    {
        super.onResume();
        if (mSaveDlg != null)
        {
            Log.d("WorkShop", (new StringBuilder()).append("onResume mSaveDlg visibility : ").append(mSaveDlg.isShowing()).toString());
        }
        screenBufferNullProcess();
        if (mUIManagerConsole != null)
        {
            mUIManagerConsole.resumeProcess();
        }
    }

    protected void onStop()
    {
        Log.v("EditorTools_Time", "onStop");
        super.onStop();
    }

    public boolean reDraw4Resume()
    {
        if (mMBitmap != null && mBitmap != null)
        {
            updateView(null);
            return true;
        } else
        {
            return false;
        }
    }

    public void reinitEditorEngineWrapper(int i, int j)
    {
        if (mBitmap != null)
        {
            mBitmap.recycle();
            mBitmap = null;
        }
        if (mMBitmap != null)
        {
            mMBitmap.recycle();
            mMBitmap = null;
        }
        if (mMBitmapBkg != null)
        {
            mMBitmapBkg.recycle();
            mMBitmapBkg = null;
        }
        mEditorViewWidth = i;
        mEditorViewHeight = j;
        mHeightOffset = getWindowManager().getDefaultDisplay().getHeight() - j;
        screenBufferNullProcess();
        EditorNotify editornotify = new EditorNotify();
        EditorDisplayContext editordisplaycontext = new EditorDisplayContext();
        editordisplaycontext.displayWidth = mEditorViewWidth;
        editordisplaycontext.displayHeight = mEditorViewHeight;
        editordisplaycontext.pixelArrayFormat = MColorSpace.MPAF_RGB32_B8G8R8A8;
        if (mUIManagerConsole != null)
        {
            mUIManagerConsole.onChange(2, null, null);
        }
        mEditorEngineWrapper.reinit(editornotify, editordisplaycontext);
        if (mUIManagerConsole != null)
        {
            mUIManagerConsole.onChange(3, null, null);
        }
    }

    public void resetBkgBitmap()
    {
        screenBufferNullProcess();
        if (mBgState == 0)
        {
            mMBitmap.fillColor(0x383837, new MRect(0, 0, mEditorViewWidth, mEditorViewHeight), null, 100);
        } else
        if (mBgState == 1)
        {
            WorkShopUtils.copyBitmap(mMBitmap, mMBitmapBkg, new MPoint());
            return;
        }
    }

    public void resumeAysnTask()
    {
        mAsyncTaskRunner.resumeAysnTask();
    }

    public void screenBufferNullProcess()
    {
        if (mBgState == 0)
        {
            if (mBitmap == null)
            {
                mMBitmap = MBitmapFactory.createMBitmapBlank(mEditorViewWidth, mEditorViewHeight, MColorSpace.MPAF_RGB32_B8G8R8A8);
                mBitmap = MBitmapFactory.createBitmapFromMBitmap(mMBitmap, false);
                if (mMBitmap != null)
                {
                    mMBitmap.recycle();
                    mMBitmap = null;
                }
                mMBitmap = MBitmapFactory.createMBitmapFromBitmap(mBitmap, true);
                mMBitmap.fillColor(0x383837, new MRect(0, 0, mEditorViewWidth, mEditorViewHeight), null, 100);
                return;
            }
            break MISSING_BLOCK_LABEL_323;
        }
        if (mBgState != 1)
        {
            break MISSING_BLOCK_LABEL_323;
        }
        if (mMBitmapBkg != null) goto _L2; else goto _L1
_L1:
        String s1;
        if (getResources().getDisplayMetrics().densityDpi != 240)
        {
            break MISSING_BLOCK_LABEL_292;
        }
        s1 = (new StringBuilder()).append("/data/data/com.arcsoft.mediaplus/data/").append("contents/background/e_bg_800.png").toString();
_L3:
        mMBitmapBkg = MBitmapFactory.createMBitmapFromFile(s1, mEditorViewWidth, mEditorViewHeight, MColorSpace.MPAF_RGB32_B8G8R8A8);
_L2:
        Exception exception;
        if (mBitmap == null)
        {
            mMBitmap = MBitmapFactory.createMBitmapBlank(mEditorViewWidth, mEditorViewHeight, MColorSpace.MPAF_RGB32_B8G8R8A8);
            mBitmap = MBitmapFactory.createBitmapFromMBitmap(mMBitmap, false);
            if (mMBitmap != null)
            {
                mMBitmap.recycle();
                mMBitmap = null;
            }
            mMBitmap = MBitmapFactory.createMBitmapFromBitmap(mBitmap, true);
            mMBitmap.fillColor(0x383837, new MRect(0, 0, mEditorViewWidth, mEditorViewHeight), null, 100);
            return;
        }
        break MISSING_BLOCK_LABEL_323;
        String s;
        try
        {
            s = (new StringBuilder()).append("/data/data/com.arcsoft.mediaplus/data/").append("contents/background/e_bg_1280.png").toString();
        }
        // Misplaced declaration of an exception variable
        catch (Exception exception)
        {
            Log.v("WorkShop", (new StringBuilder()).append("screenBufferNullProcess Exception : ").append(exception.getMessage()).toString());
            return;
        }
        s1 = s;
          goto _L3
    }

    public void setBgState(int i)
    {
        mBgState = i;
    }

    public void startAysnTask()
    {
        mAsyncTaskRunner.startAysnTask();
    }

    public void stopAysnTask()
    {
        mAsyncTaskRunner.stopAysnTask();
    }

    public void updateView(MRect mrect)
    {
        while (mbLoadingPicture || mUIManagerConsole == null) 
        {
            return;
        }
        mUIManagerConsole.doDraw(mMBitmap, mBitmap, mrect);
    }

    static 
    {
        System.loadLibrary("arcplatform");
        System.loadLibrary("arcimgutilsbase");
        System.loadLibrary("arcimgutils");
        System.loadLibrary("workshopplatform");
        System.loadLibrary("arcphotoeditormt");
        System.loadLibrary("frameutils");
        if (android.os.Build.VERSION.SDK_INT > 8) goto _L2; else goto _L1
_L1:
        System.loadLibrary("workshoputils_froyo");
_L4:
        mMBitmap = null;
        mBitmap = null;
        return;
_L2:
        try
        {
            System.loadLibrary("workshoputils");
        }
        catch (UnsatisfiedLinkError unsatisfiedlinkerror)
        {
            unsatisfiedlinkerror.printStackTrace();
        }
        if (true) goto _L4; else goto _L3
_L3:
    }




/*
    static boolean access$1002(WorkShop workshop, boolean flag)
    {
        workshop.mShoudProcessPic = flag;
        return flag;
    }

*/


/*
    static Uri access$1102(WorkShop workshop, Uri uri)
    {
        workshop.mShareUriPicChanged = uri;
        return uri;
    }

*/



/*
    static int access$1208(WorkShop workshop)
    {
        int i = workshop.mSaveCount;
        workshop.mSaveCount = i + 1;
        return i;
    }

*/



/*
    static Intent access$1302(WorkShop workshop, Intent intent)
    {
        workshop.mSaveIntent = intent;
        return intent;
    }

*/


/*
    static SaveThreadForShare access$1402(WorkShop workshop, SaveThreadForShare savethreadforshare)
    {
        workshop.mSaveThreadForShave = savethreadforshare;
        return savethreadforshare;
    }

*/





/*
    static Uri access$1802(WorkShop workshop, Uri uri)
    {
        workshop.mShareUriPicNotChanged = uri;
        return uri;
    }

*/






}
