// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.arcsoft.workshop;

import android.content.ContentResolver;
import android.content.ContentValues;
import android.net.Uri;
import android.os.Environment;
import com.arcsoft.workshop.tools.EditorTools;
import com.arcsoft.workshop.tools.EditorToolsManager;
import com.arcsoft.workshop.utils.WorkShopUtils;
import java.io.File;
import powermobia.utils.MBitmap;
import powermobia.utils.MBitmapFactory;
import powermobia.utils.MColorSpace;
import powermobia.utils.MRect;

// Referenced classes of package com.arcsoft.workshop:
//            WorkShop, EditorEngineWrapper

public class SaveThreadForShare extends Thread
{
    public static interface OnSaveProcessTaskListener
    {

        public abstract void onSaveProcessCancel();

        public abstract void onSaveProcessDone(Uri uri, boolean flag);

        public abstract void onSaveProcessErrorOccured(boolean flag);
    }

    private class SaveProcessStatus
    {

        boolean bNoSdCard;
        boolean needShare;
        final SaveThreadForShare this$0;
        Uri uri;
        int what;

        private SaveProcessStatus()
        {
            this$0 = SaveThreadForShare.this;
            super();
            uri = null;
            needShare = false;
            bNoSdCard = false;
            what = 0;
        }

    }


    private final int MSG_SAVE_CANCEL = 2;
    private final int MSG_SAVE_DONE = 1;
    private final int MSG_SAVE_ERROR = 0;
    private String mFileOpened;
    private boolean mNeedExitThread;
    private boolean mNeedShare;
    private OnSaveProcessTaskListener mOnSaveProcessTaskListener;
    private volatile boolean mSaveStart;
    private WorkShop mWorkShop;

    public SaveThreadForShare(WorkShop workshop, boolean flag)
    {
        mWorkShop = null;
        mSaveStart = false;
        mFileOpened = null;
        mNeedShare = false;
        mNeedExitThread = false;
        mWorkShop = workshop;
        mFileOpened = mWorkShop.getCurrentFilePath();
        mNeedShare = flag;
    }

    public boolean checkSaveThread(SaveProcessStatus saveprocessstatus)
    {
        if (mNeedExitThread)
        {
            mSaveStart = false;
            return true;
        }
        saveprocessstatus.what;
        JVM INSTR tableswitch 0 2: default 44
    //                   0 51
    //                   1 74
    //                   2 101;
           goto _L1 _L2 _L3 _L4
_L1:
        mSaveStart = false;
        return false;
_L2:
        if (mOnSaveProcessTaskListener != null)
        {
            mOnSaveProcessTaskListener.onSaveProcessErrorOccured(saveprocessstatus.bNoSdCard);
        }
        continue; /* Loop/switch isn't completed */
_L3:
        if (mOnSaveProcessTaskListener != null)
        {
            mOnSaveProcessTaskListener.onSaveProcessDone(saveprocessstatus.uri, saveprocessstatus.needShare);
        }
        continue; /* Loop/switch isn't completed */
_L4:
        if (mOnSaveProcessTaskListener != null)
        {
            mOnSaveProcessTaskListener.onSaveProcessCancel();
        }
        if (true) goto _L1; else goto _L5
_L5:
    }

    public void destroy()
    {
        mNeedExitThread = true;
        try
        {
            join();
        }
        catch (InterruptedException interruptedexception)
        {
            interruptedexception.printStackTrace();
        }
        if (mOnSaveProcessTaskListener != null)
        {
            mOnSaveProcessTaskListener = null;
        }
    }

    public boolean getSaveStart()
    {
        return mSaveStart;
    }

    public void run()
    {
        if (!mSaveStart) goto _L2; else goto _L1
_L1:
        return;
_L2:
        SaveProcessStatus saveprocessstatus;
        String s2;
        int i;
        MBitmap mbitmap;
        mSaveStart = true;
        saveprocessstatus = new SaveProcessStatus();
        if (!Environment.getExternalStorageState().equals("mounted") && checkSaveThread(saveprocessstatus))
        {
            continue; /* Loop/switch isn't completed */
        }
        String s = WorkShopUtils.checkSaveDir();
        if (s == null && checkSaveThread(saveprocessstatus))
        {
            continue; /* Loop/switch isn't completed */
        }
        String s1 = mFileOpened.substring(mFileOpened.lastIndexOf("/"));
        s2 = WorkShopUtils.getOutputFilePath((new StringBuilder()).append(s).append(s1).toString());
        if (!WorkShopUtils.checkSaveDir(s2) && checkSaveThread(saveprocessstatus))
        {
            continue; /* Loop/switch isn't completed */
        }
        i = WorkShopUtils.getImageFormat(mFileOpened);
        powermobia.photoeditor.EditorEngine.State state = new powermobia.photoeditor.EditorEngine.State();
        EditorEngineWrapper editorenginewrapper = mWorkShop.getEditorEngineWrapper();
        int j = editorenginewrapper.getState(state);
        if (j != 0 && checkSaveThread(saveprocessstatus))
        {
            continue; /* Loop/switch isn't completed */
        }
        int k = state.iImgWidth;
        int l = state.iImgHeight;
        MRect mrect = new MRect();
        mrect.left = 0;
        mrect.top = 0;
        mrect.right = k;
        mrect.bottom = l;
        mbitmap = MBitmapFactory.createMBitmapBlank(k, l, MColorSpace.MPAF_RGB32_B8G8R8A8);
        if (mbitmap != null)
        {
            j = editorenginewrapper.getDisplayData(k, l, mrect, mbitmap, false);
        } else
        if (checkSaveThread(saveprocessstatus))
        {
            return;
        }
        if (j == 0 || !checkSaveThread(saveprocessstatus))
        {
            break; /* Loop/switch isn't completed */
        }
        if (mbitmap != null)
        {
            mbitmap.recycle();
            return;
        }
        if (true) goto _L1; else goto _L3
_L3:
        com.arcsoft.workshop.tools.EditorToolsBase editortoolsbase = mWorkShop.getEditorToolsManager().getEditorTools();
        if (editortoolsbase == null || !(editortoolsbase instanceof EditorTools))
        {
            break; /* Loop/switch isn't completed */
        }
        EditorTools editortools = (EditorTools)editortoolsbase;
        String s4 = editortools.getFramePath();
        String s5 = editortools.getTexturePath();
        if (s4 == null && s5 == null)
        {
            break; /* Loop/switch isn't completed */
        }
        MRect mrect1 = new MRect(0, 0, mbitmap.getWidth(), mbitmap.getHeight());
        if (WorkShopUtils.addFrame(mbitmap, mrect1, s4, s5) == 0 || !checkSaveThread(saveprocessstatus))
        {
            break; /* Loop/switch isn't completed */
        }
        if (mbitmap != null)
        {
            mbitmap.recycle();
            return;
        }
        if (true) goto _L1; else goto _L4
_L4:
        if (i == 4)
        {
            i = 2;
            StringBuilder stringbuilder = new StringBuilder();
            int j1 = 1 + s2.lastIndexOf(".");
            s2 = stringbuilder.append(s2.substring(0, j1)).append("jpg").toString();
        }
        if (mbitmap.save(i, s2, 100) == 0 || !checkSaveThread(saveprocessstatus))
        {
            break; /* Loop/switch isn't completed */
        }
        if (mbitmap != null)
        {
            mbitmap.recycle();
            return;
        }
        if (true) goto _L1; else goto _L5
_L5:
        Uri uri;
        if (mbitmap != null)
        {
            mbitmap.recycle();
        }
        File file = new File(s2);
        if (!file.exists() && checkSaveThread(saveprocessstatus))
        {
            continue; /* Loop/switch isn't completed */
        }
        ContentResolver contentresolver = mWorkShop.getContentResolver();
        uri = null;
        if (file == null)
        {
            break; /* Loop/switch isn't completed */
        }
        String s3 = file.getName();
        int i1 = s3.lastIndexOf(".");
        if (i1 < 0)
        {
            continue; /* Loop/switch isn't completed */
        }
        ContentValues contentvalues = new ContentValues(6);
        contentvalues.put("title", s3.substring(0, i1));
        contentvalues.put("_display_name", file.getName());
        contentvalues.put("_data", file.getPath());
        contentvalues.put("date_modified", Long.valueOf(System.currentTimeMillis() / 1000L));
        contentvalues.put("_size", Long.valueOf(file.length()));
        contentvalues.put("mime_type", "image/jpeg");
        uri = contentresolver.insert(android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI, contentvalues);
        break; /* Loop/switch isn't completed */
        if (true) goto _L1; else goto _L6
_L6:
        saveprocessstatus.uri = uri;
        saveprocessstatus.what = 1;
        saveprocessstatus.needShare = mNeedShare;
        if (checkSaveThread(saveprocessstatus))
        {
            return;
        }
        if (true) goto _L1; else goto _L7
_L7:
    }

    public void setOnSaveProcessTaskListener(OnSaveProcessTaskListener onsaveprocesstasklistener)
    {
        mOnSaveProcessTaskListener = onsaveprocesstasklistener;
    }
}
