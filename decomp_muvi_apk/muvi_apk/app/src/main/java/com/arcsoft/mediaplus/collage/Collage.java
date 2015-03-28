// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.arcsoft.mediaplus.collage;

import android.app.Activity;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.BitmapFactory;
import android.graphics.Point;
import android.graphics.Rect;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import com.arcsoft.mediaplus.widget.PopupMenuWindow;
import com.arcsoft.util.FileUtils;
import com.arcsoft.util.debug.Log;
import com.arcsoft.workshop.ui.UISaveDialog;
import com.arcsoft.workshop.utils.WorkShopUtils;
import java.io.File;
import java.util.ArrayList;
import powermobia.utils.MBitmap;
import powermobia.utils.MBitmapFactory;
import powermobia.utils.MColorSpace;
import powermobia.utils.MPoint;
import powermobia.utils.MRect;

// Referenced classes of package com.arcsoft.mediaplus.collage:
//            CollageSurfaceView, Config, ZoomPanController

public class Collage extends Activity
{
    private class TemplateUpdateThread extends Thread
    {

        private int mPicNum;
        final Collage this$0;

        public void run()
        {
            mThreadRunning = true;
            int i = doChange(mPicNum);
            if (mHandler != null)
            {
                Message message = new Message();
                message.what = 0;
                message.arg1 = i;
                mHandler.sendMessage(message);
            }
        }

        public TemplateUpdateThread(int i)
        {
            this$0 = Collage.this;
            super();
            mPicNum = 0;
            mPicNum = i;
        }
    }


    private final int COLLAGE_MSG_OK = 0;
    private final int COLLAGE_TOAST_FAILED_DECODE = 3;
    private final int COLLAGE_TOAST_FIRST_TEMPLATE = 1;
    private final int COLLAGE_TOAST_LAST_TEMPLATE = 2;
    private final int MAX_NUMS[] = {
        1, 2, 4, 6, 6, 4, 4, 4, 3
    };
    private final String TAG = "Collage";
    private Button mBackBtn;
    private final ArrayList mBitmapList = new ArrayList();
    private final android.view.View.OnClickListener mClickListener = new android.view.View.OnClickListener() {

        final Collage this$0;

        public void onClick(View view)
        {
            int i;
            if (System.currentTimeMillis() - mClickedTime <= 1000L)
            {
                return;
            }
            i = mCurrentType;
            view.getId();
            JVM INSTR tableswitch 2131296287 2131296295: default 80
        //                       2131296287 145
        //                       2131296288 80
        //                       2131296289 80
        //                       2131296290 137
        //                       2131296291 80
        //                       2131296292 80
        //                       2131296293 125
        //                       2131296294 80
        //                       2131296295 131;
               goto _L1 _L2 _L1 _L1 _L3 _L1 _L1 _L4 _L1 _L5
_L1:
            break; /* Loop/switch isn't completed */
_L2:
            break MISSING_BLOCK_LABEL_145;
_L6:
            if (!mThreadRunning)
            {
                changeTemplate(mPicNum, i, false);
            }
            refreshPreviewBtn();
            mClickedTime = System.currentTimeMillis();
            return;
_L4:
            i--;
            continue; /* Loop/switch isn't completed */
_L5:
            i++;
            if (true) goto _L6; else goto _L3
_L3:
            showMenuWidow();
            return;
            finish();
            return;
        }

            
            {
                this$0 = Collage.this;
                super();
            }
    };
    private long mClickedTime;
    private TextView mContentTxv;
    private int mCurrentType;
    private int mDisHeight;
    private final ArrayList mDisRects2Img = new ArrayList();
    private int mDisWidth;
    private final ArrayList mFitoutImgRects = new ArrayList();
    private final Handler mHandler = new Handler() {

        final Collage this$0;

        public void handleMessage(Message message)
        {
            switch (message.what)
            {
            default:
                return;

            case 0: // '\0'
                doPostExecute(message.arg1);
                break;
            }
        }

            
            {
                this$0 = Collage.this;
                super();
            }
    };
    private PopupMenuWindow mMenuWindow;
    private Button mMoreMenuBtn;
    private Button mNextBtn;
    private final ArrayList mOriBitmapList = new ArrayList();
    private final ArrayList mOriFitoutImgRects = new ArrayList();
    private int mPicNum;
    private Button mPrevBtn;
    private UISaveDialog mProcessDlg;
    private final ArrayList mRotateAngles = new ArrayList();
    private CollageSurfaceView mSurfaceView;
    private MRect mTemplateDirtyRect;
    private final ArrayList mTemplateThumbnailRects = new ArrayList();
    private TemplateUpdateThread mThread;
    private boolean mThreadRunning;
    private ZoomPanController mZoomPanController;
    private final ArrayList mZoomScales = new ArrayList();
    com.arcsoft.mediaplus.widget.PopupMenuWindow.OnMenuClickedListener onMenuClickedListener;
    private String picPaths[];

    public Collage()
    {
        mTemplateDirtyRect = null;
        mDisWidth = 0;
        mDisHeight = 0;
        picPaths = null;
        mCurrentType = 0;
        mPicNum = 1;
        mPrevBtn = null;
        mNextBtn = null;
        mContentTxv = null;
        mBackBtn = null;
        mMoreMenuBtn = null;
        mMenuWindow = null;
        mProcessDlg = null;
        mClickedTime = 0L;
        onMenuClickedListener = new com.arcsoft.mediaplus.widget.PopupMenuWindow.OnMenuClickedListener() {

            final Collage this$0;

            public void onClicked(int i)
            {
                if (i != 0) goto _L2; else goto _L1
_L1:
                setResult(-1);
                saveWithShareOrNot(false);
_L4:
                if (mMenuWindow != null)
                {
                    mMenuWindow.hidePopopMenuWindow();
                }
                return;
_L2:
                if (i == 1)
                {
                    setResult(-1);
                    saveWithShareOrNot(true);
                }
                if (true) goto _L4; else goto _L3
_L3:
            }

            
            {
                this$0 = Collage.this;
                super();
            }
        };
        mThreadRunning = false;
        mThread = null;
    }

    public static int computeSampleSize(android.graphics.BitmapFactory.Options options, int i, int j)
    {
        int k = options.outWidth;
        int l = options.outHeight;
        int i1 = Math.max(k / i, l / j);
        if (i1 == 0)
        {
            return 1;
        }
        if (i1 > 1 && k > i && k / i1 < i)
        {
            i1--;
        }
        if (i1 > 1 && l > j && l / i1 < j)
        {
            i1--;
        }
        return i1;
    }

    private void destroyTempChangeThread()
    {
        if (mThread != null)
        {
            try
            {
                mThread.join();
                mThread = null;
            }
            catch (InterruptedException interruptedexception)
            {
                interruptedexception.printStackTrace();
                mThread = null;
            }
        }
        mThreadRunning = false;
    }

    private int doChange(int i)
    {
        this;
        JVM INSTR monitorenter ;
        if (mTemplateThumbnailRects != null && !mTemplateThumbnailRects.isEmpty())
        {
            mTemplateThumbnailRects.clear();
        }
        if (mFitoutImgRects != null && !mFitoutImgRects.isEmpty())
        {
            mFitoutImgRects.clear();
        }
        if (mOriFitoutImgRects != null && !mOriFitoutImgRects.isEmpty())
        {
            mOriFitoutImgRects.clear();
        }
        if (mDisRects2Img != null && !mDisRects2Img.isEmpty())
        {
            mDisRects2Img.clear();
        }
        generateDisplayRects(i, mCurrentType);
        if (mBitmapList == null || mBitmapList.isEmpty())
        {
            break MISSING_BLOCK_LABEL_167;
        }
        int i1 = 0;
_L2:
        if (i1 >= mBitmapList.size())
        {
            break; /* Loop/switch isn't completed */
        }
        ((MBitmap)mBitmapList.get(i1)).recycle();
        i1++;
        if (true) goto _L2; else goto _L1
_L1:
        mBitmapList.clear();
        if (mOriBitmapList == null || mOriBitmapList.isEmpty())
        {
            break MISSING_BLOCK_LABEL_227;
        }
        int l = 0;
_L4:
        if (l >= mOriBitmapList.size())
        {
            break; /* Loop/switch isn't completed */
        }
        ((MBitmap)mOriBitmapList.get(l)).recycle();
        l++;
        if (true) goto _L4; else goto _L3
_L3:
        mOriBitmapList.clear();
        if (!mZoomScales.isEmpty())
        {
            mZoomScales.clear();
        }
        if (!mRotateAngles.isEmpty())
        {
            mRotateAngles.clear();
        }
        if (generateFitoutBitmaps(picPaths, mTemplateThumbnailRects, i) == 0) goto _L6; else goto _L5
_L5:
        Log.d("Collage", "generateFitoutBitmaps failed");
        byte byte0 = 3;
_L11:
        this;
        JVM INSTR monitorexit ;
        return byte0;
_L6:
        int j = 0;
_L8:
        if (j >= mFitoutImgRects.size())
        {
            break; /* Loop/switch isn't completed */
        }
        MRect mrect = (MRect)mFitoutImgRects.get(j);
        MRect mrect1 = new MRect();
        mrect1.set(mrect);
        mOriFitoutImgRects.add(mrect1);
        j++;
        if (true) goto _L8; else goto _L7
_L7:
        if (generateDis2ImgRects())
        {
            break MISSING_BLOCK_LABEL_437;
        }
        Log.d("Collage", "generateDis2ImgRects failed");
        byte0 = 3;
        continue; /* Loop/switch isn't completed */
_L9:
        Exception exception;
        int k;
        for (; k >= mBitmapList.size(); k = 0)
        {
            break MISSING_BLOCK_LABEL_426;
        }

        mZoomScales.add(new Integer(1000));
        mRotateAngles.add(new Integer(0));
        k++;
          goto _L9
        byte0 = 0;
        if (true) goto _L11; else goto _L10
_L10:
        exception;
        throw exception;
    }

    private void doPostExecute(int i)
    {
        int j;
        j = 1;
        if (mProcessDlg != null && mProcessDlg.isShowing())
        {
            mProcessDlg.dismiss();
        }
        if (mSurfaceView != null)
        {
            mSurfaceView.setBackgroundDrawable(null);
        }
        if (!isPreNextBtnShowing())
        {
            showPreNextBtn();
        }
        if (mSurfaceView == null) goto _L2; else goto _L1
_L1:
        if (i == 0) goto _L4; else goto _L3
_L3:
        toastXXX(i);
_L2:
        mThreadRunning = false;
        return;
_L4:
        mSurfaceView.doDraw4Collage(mTemplateThumbnailRects, getBitmapList());
        if (mCurrentType != j)
        {
            break; /* Loop/switch isn't completed */
        }
_L6:
        toastXXX(j);
        if (true) goto _L2; else goto _L5
_L5:
        if (mCurrentType == getMaxNum())
        {
            j = 2;
        } else
        {
            j = 0;
        }
          goto _L6
        if (true) goto _L2; else goto _L7
_L7:
    }

    private boolean generateDis2ImgRects()
    {
        int i = getOffsetX();
        int j = getOffsetY();
        for (int k = 0; k < mTemplateThumbnailRects.size(); k++)
        {
            MRect mrect = new MRect((MRect)mTemplateThumbnailRects.get(k));
            translateRect(mrect, i, j);
            int l = (((MRect)mFitoutImgRects.get(k)).width() - mrect.width()) / 2;
            int i1 = (((MRect)mFitoutImgRects.get(k)).height() - mrect.height()) / 2;
            MRect mrect1 = new MRect(l, i1, l + mrect.width(), i1 + mrect.height());
            if (isRectInvalid(mrect1))
            {
                return false;
            }
            mDisRects2Img.add(mrect1);
        }

        return true;
    }

    private void generateDisplayRects(int i, int j)
    {
        int k = getResources().getInteger(0x7f0a001d);
        int l = getResources().getInteger(0x7f0a001e);
        switch (i)
        {
        default:
            return;

        case 1: // '\001'
            mTemplateThumbnailRects.add(new MRect(4, 4, k - 4, l - 4));
            return;

        case 2: // '\002'
            switch (j)
            {
            default:
                return;

            case 1: // '\001'
                mTemplateThumbnailRects.add(new MRect(4, 4, k - 4, -3 + l / 2));
                mTemplateThumbnailRects.add(new MRect(4, 2 + l / 2, k - 4, l - 4));
                return;

            case 2: // '\002'
                mTemplateThumbnailRects.add(new MRect(4, 4, -3 + k / 2, l - 4));
                break;
            }
            mTemplateThumbnailRects.add(new MRect(2 + k / 2, 3, k - 4, l - 4));
            return;

        case 3: // '\003'
            switch (j)
            {
            default:
                return;

            case 1: // '\001'
                mTemplateThumbnailRects.add(new MRect(4, 4, k - 4, -3 + l / 2));
                mTemplateThumbnailRects.add(new MRect(4, 2 + l / 2, -3 + k / 2, l - 4));
                mTemplateThumbnailRects.add(new MRect(2 + k / 2, 2 + l / 2, k - 4, l - 4));
                return;

            case 2: // '\002'
                mTemplateThumbnailRects.add(new MRect(4, 4, -3 + k / 2, -3 + l / 2));
                mTemplateThumbnailRects.add(new MRect(2 + k / 2, 4, k - 4, l - 4));
                mTemplateThumbnailRects.add(new MRect(4, 2 + l / 2, -3 + k / 2, l - 4));
                return;

            case 3: // '\003'
                mTemplateThumbnailRects.add(new MRect(4, 4, -3 + k / 2, -3 + l / 2));
                mTemplateThumbnailRects.add(new MRect(2 + k / 2, 4, k - 4, -3 + l / 2));
                mTemplateThumbnailRects.add(new MRect(4, 2 + l / 2, k - 4, l - 4));
                return;

            case 4: // '\004'
                mTemplateThumbnailRects.add(new MRect(4, 4, -3 + k / 2, l - 4));
                break;
            }
            mTemplateThumbnailRects.add(new MRect(2 + k / 2, 4, k - 4, -3 + l / 2));
            mTemplateThumbnailRects.add(new MRect(2 + k / 2, 2 + l / 2, k - 4, l - 4));
            return;

        case 4: // '\004'
            switch (j)
            {
            default:
                return;

            case 1: // '\001'
                mTemplateThumbnailRects.add(new MRect(4, 4, -3 + k / 2, -3 + l / 2));
                mTemplateThumbnailRects.add(new MRect(2 + k / 2, 4, k - 4, -3 + l / 2));
                mTemplateThumbnailRects.add(new MRect(4, 2 + l / 2, -3 + k / 2, l - 4));
                mTemplateThumbnailRects.add(new MRect(2 + k / 2, 2 + l / 2, k - 4, l - 4));
                return;

            case 2: // '\002'
                mTemplateThumbnailRects.add(new MRect(4, 4, -3 + k / 2, -3 + l / 3));
                mTemplateThumbnailRects.add(new MRect(4, 2 + l / 3, -3 + k / 2, l - 4));
                mTemplateThumbnailRects.add(new MRect(2 + k / 2, 4, k - 4, -3 + (l * 2) / 3));
                mTemplateThumbnailRects.add(new MRect(2 + k / 2, 2 + (l * 2) / 3, k - 4, l - 4));
                return;

            case 3: // '\003'
                mTemplateThumbnailRects.add(new MRect(4, 4, -3 + k / 3, -3 + l / 2));
                mTemplateThumbnailRects.add(new MRect(4, 2 + l / 2, -3 + (k * 2) / 3, l - 4));
                mTemplateThumbnailRects.add(new MRect(2 + k / 3, 4, k - 4, -3 + l / 2));
                mTemplateThumbnailRects.add(new MRect(2 + (k * 2) / 3, 2 + l / 2, k - 4, l - 4));
                return;

            case 4: // '\004'
                mTemplateThumbnailRects.add(new MRect(4, 4, k - 4, -3 + l / 3));
                mTemplateThumbnailRects.add(new MRect(4, 2 + l / 3, -3 + k / 2, l - 4));
                mTemplateThumbnailRects.add(new MRect(2 + k / 2, 2 + l / 3, k - 4, -3 + (l * 2) / 3));
                mTemplateThumbnailRects.add(new MRect(2 + k / 2, 2 + (l * 2) / 3, k - 4, l - 4));
                return;

            case 5: // '\005'
                mTemplateThumbnailRects.add(new MRect(4, 4, k - 4, -3 + (l * 2) / 3));
                mTemplateThumbnailRects.add(new MRect(4, 2 + (l * 2) / 3, -3 + k / 3, l - 4));
                mTemplateThumbnailRects.add(new MRect(2 + k / 3, 2 + (l * 2) / 3, -3 + (k * 2) / 3, l - 4));
                mTemplateThumbnailRects.add(new MRect(2 + (k * 2) / 3, 2 + (l * 2) / 3, k - 4, l - 4));
                return;

            case 6: // '\006'
                mTemplateThumbnailRects.add(new MRect(4, 4, -3 + (k * 2) / 3, l - 4));
                break;
            }
            mTemplateThumbnailRects.add(new MRect(2 + (k * 2) / 3, 4, k - 4, -3 + l / 3));
            mTemplateThumbnailRects.add(new MRect(2 + (k * 2) / 3, 2 + l / 3, k - 4, -3 + (l * 2) / 3));
            mTemplateThumbnailRects.add(new MRect(2 + (k * 2) / 3, 2 + (l * 2) / 3, k - 4, l - 4));
            return;

        case 5: // '\005'
            switch (j)
            {
            default:
                return;

            case 1: // '\001'
                mTemplateThumbnailRects.add(new MRect(4, 4, -3 + (k * 2) / 4, -3 + l / 3));
                mTemplateThumbnailRects.add(new MRect(4, 2 + l / 3, -3 + (k * 2) / 4, -3 + (l * 2) / 3));
                mTemplateThumbnailRects.add(new MRect(4, 2 + (l * 2) / 3, -3 + (k * 2) / 4, l - 4));
                mTemplateThumbnailRects.add(new MRect(2 + (k * 2) / 4, 4, k - 4, -3 + (l * 2) / 3));
                mTemplateThumbnailRects.add(new MRect(2 + (k * 2) / 4, 2 + (l * 2) / 3, k - 4, l - 4));
                return;

            case 2: // '\002'
                mTemplateThumbnailRects.add(new MRect(4, 4, -3 + k / 2, -3 + l / 3));
                mTemplateThumbnailRects.add(new MRect(2 + k / 2, 4, k - 4, -3 + l / 3));
                mTemplateThumbnailRects.add(new MRect(4, 2 + l / 3, k - 4, -3 + (l * 2) / 3));
                mTemplateThumbnailRects.add(new MRect(4, 2 + (l * 2) / 3, -3 + k / 2, l - 4));
                mTemplateThumbnailRects.add(new MRect(2 + k / 2, 2 + (l * 2) / 3, k - 4, l - 4));
                return;

            case 3: // '\003'
                mTemplateThumbnailRects.add(new MRect(4, 4, -3 + k / 2, -3 + l / 2));
                mTemplateThumbnailRects.add(new MRect(2 + k / 2, 4, k - 4, -3 + l / 3));
                mTemplateThumbnailRects.add(new MRect(4, 2 + l / 2, -3 + k / 2, l - 4));
                mTemplateThumbnailRects.add(new MRect(2 + k / 2, 2 + l / 3, k - 4, -3 + (l * 2) / 3));
                mTemplateThumbnailRects.add(new MRect(2 + k / 2, 2 + (l * 2) / 3, k - 4, l - 4));
                return;

            case 4: // '\004'
                mTemplateThumbnailRects.add(new MRect(4, 4, -3 + k / 3, -3 + l / 3));
                mTemplateThumbnailRects.add(new MRect(4, 2 + l / 3, -3 + k / 3, -3 + (l * 2) / 3));
                mTemplateThumbnailRects.add(new MRect(4, 2 + (l * 2) / 3, -3 + k / 3, l - 4));
                mTemplateThumbnailRects.add(new MRect(2 + k / 3, 4, k - 4, -3 + l / 2));
                mTemplateThumbnailRects.add(new MRect(2 + k / 3, 2 + l / 2, k - 4, l - 4));
                return;

            case 5: // '\005'
                mTemplateThumbnailRects.add(new MRect(4, 4, k - 4, -3 + l / 3));
                mTemplateThumbnailRects.add(new MRect(4, 2 + l / 3, -3 + k / 2, -3 + (l * 2) / 3));
                mTemplateThumbnailRects.add(new MRect(4, 2 + (l * 2) / 3, -3 + k / 2, l - 4));
                mTemplateThumbnailRects.add(new MRect(2 + k / 2, 2 + l / 3, k - 4, -3 + (l * 2) / 3));
                mTemplateThumbnailRects.add(new MRect(2 + k / 2, 2 + (l * 2) / 3, k - 4, l - 4));
                return;

            case 6: // '\006'
                mTemplateThumbnailRects.add(new MRect(4, 4, -3 + (k * 2) / 3, -3 + (l * 2) / 3));
                break;
            }
            mTemplateThumbnailRects.add(new MRect(2 + (k * 2) / 3, 4, k - 4, -3 + (l * 2) / 9));
            mTemplateThumbnailRects.add(new MRect(2 + (k * 2) / 3, 2 + (l * 2) / 9, k - 4, -3 + (l * 4) / 9));
            mTemplateThumbnailRects.add(new MRect(2 + (k * 2) / 3, 2 + (l * 4) / 9, k - 4, -3 + (l * 2) / 3));
            mTemplateThumbnailRects.add(new MRect(4, 2 + (l * 2) / 3, k - 4, l - 4));
            return;

        case 6: // '\006'
            switch (j)
            {
            default:
                return;

            case 1: // '\001'
                mTemplateThumbnailRects.add(new MRect(4, 4, -3 + k / 3, -3 + l / 3));
                mTemplateThumbnailRects.add(new MRect(4, 2 + l / 3, -3 + k / 3, -3 + (l * 2) / 3));
                mTemplateThumbnailRects.add(new MRect(2 + k / 3, 4, k - 4, -3 + (l * 2) / 3));
                mTemplateThumbnailRects.add(new MRect(4, 2 + (l * 2) / 3, -3 + k / 3, l - 4));
                mTemplateThumbnailRects.add(new MRect(2 + k / 3, 2 + (l * 2) / 3, -3 + (k * 2) / 3, l - 4));
                mTemplateThumbnailRects.add(new MRect(2 + (k * 2) / 3, 2 + (l * 2) / 3, k - 4, l - 4));
                return;

            case 2: // '\002'
                mTemplateThumbnailRects.add(new MRect(4, 4, -3 + k / 2, -3 + l / 3));
                mTemplateThumbnailRects.add(new MRect(4, 2 + l / 3, -3 + k / 2, -3 + (l * 2) / 3));
                mTemplateThumbnailRects.add(new MRect(4, 2 + (l * 2) / 3, -3 + k / 2, l - 4));
                mTemplateThumbnailRects.add(new MRect(2 + k / 2, 4, k - 4, -3 + l / 3));
                mTemplateThumbnailRects.add(new MRect(2 + k / 2, 2 + l / 3, k - 4, -3 + (l * 2) / 3));
                mTemplateThumbnailRects.add(new MRect(2 + k / 2, 2 + (l * 2) / 3, k - 4, l - 4));
                return;

            case 3: // '\003'
                mTemplateThumbnailRects.add(new MRect(4, 4, -3 + (k * 2) / 3, -3 + l / 2));
                mTemplateThumbnailRects.add(new MRect(2 + (k * 2) / 3, 4, k - 4, -3 + l / 4));
                mTemplateThumbnailRects.add(new MRect(2 + (k * 2) / 3, 2 + l / 4, k - 4, -3 + l / 2));
                mTemplateThumbnailRects.add(new MRect(4, 2 + l / 2, -3 + (k * 2) / 3, l - 4));
                mTemplateThumbnailRects.add(new MRect(2 + (k * 2) / 3, 2 + l / 2, k - 4, -3 + (l * 3) / 4));
                mTemplateThumbnailRects.add(new MRect(2 + (k * 2) / 3, 2 + (l * 3) / 4, k - 4, l - 4));
                return;

            case 4: // '\004'
                mTemplateThumbnailRects.add(new MRect(4, 4, k - 4, -3 + l / 3));
                break;
            }
            mTemplateThumbnailRects.add(new MRect(4, 2 + l / 3, -3 + k / 2, -3 + (l * 2) / 3));
            mTemplateThumbnailRects.add(new MRect(2 + k / 2, 2 + l / 3, k - 4, -3 + (l * 2) / 3));
            mTemplateThumbnailRects.add(new MRect(4, 2 + (l * 2) / 3, -2 + k / 3, l - 4));
            mTemplateThumbnailRects.add(new MRect(2 + k / 3, 2 + (l * 2) / 3, -3 + (k * 2) / 3, l - 4));
            mTemplateThumbnailRects.add(new MRect(2 + (k * 2) / 3, 2 + (l * 2) / 3, k - 4, l - 4));
            return;

        case 7: // '\007'
            switch (j)
            {
            default:
                return;

            case 1: // '\001'
                mTemplateThumbnailRects.add(new MRect(4, 4, -3 + k / 2, -3 + (l * 4) / 11));
                mTemplateThumbnailRects.add(new MRect(4, 2 + (l * 4) / 11, -3 + k / 2, -3 + (l * 8) / 11));
                mTemplateThumbnailRects.add(new MRect(2 + k / 2, 4, k - 4, -3 + (l * 4) / 11));
                mTemplateThumbnailRects.add(new MRect(2 + k / 2, 2 + (l * 4) / 11, k - 4, -3 + (l * 8) / 11));
                mTemplateThumbnailRects.add(new MRect(4, 2 + (l * 8) / 11, -3 + k / 3, l - 4));
                mTemplateThumbnailRects.add(new MRect(2 + k / 3, 2 + (l * 8) / 11, -2 + (k * 2) / 3, l - 4));
                mTemplateThumbnailRects.add(new MRect(2 + (k * 2) / 3, 2 + (l * 8) / 11, k - 4, l - 4));
                return;

            case 2: // '\002'
                mTemplateThumbnailRects.add(new MRect(4, 4, -3 + k / 3, -3 + (l * 3) / 11));
                mTemplateThumbnailRects.add(new MRect(2 + k / 3, 4, -3 + (k * 2) / 3, -3 + (l * 3) / 11));
                mTemplateThumbnailRects.add(new MRect(2 + (k * 2) / 3, 4, k - 4, -3 + (l * 3) / 11));
                mTemplateThumbnailRects.add(new MRect(4, 2 + (l * 3) / 11, k - 4, -3 + (l * 8) / 11));
                mTemplateThumbnailRects.add(new MRect(4, 2 + (l * 8) / 11, -3 + k / 3, l - 4));
                mTemplateThumbnailRects.add(new MRect(2 + k / 3, 2 + (l * 8) / 11, -3 + (k * 2) / 3, l - 4));
                mTemplateThumbnailRects.add(new MRect(2 + (k * 2) / 3, 2 + (l * 8) / 11, k - 4, l - 4));
                return;

            case 3: // '\003'
                mTemplateThumbnailRects.add(new MRect(4, 4, -3 + k / 3, -3 + l / 3));
                mTemplateThumbnailRects.add(new MRect(4, 2 + l / 3, -3 + k / 3, -3 + (l * 2) / 3));
                mTemplateThumbnailRects.add(new MRect(2 + k / 3, 4, k - 4, -3 + l / 3));
                mTemplateThumbnailRects.add(new MRect(2 + k / 3, 2 + l / 3, k - 4, -3 + (l * 2) / 3));
                mTemplateThumbnailRects.add(new MRect(4, 2 + (l * 2) / 3, -3 + k / 3, l - 4));
                mTemplateThumbnailRects.add(new MRect(2 + k / 3, 2 + (l * 2) / 3, -2 + (k * 2) / 3, l - 4));
                mTemplateThumbnailRects.add(new MRect(2 + (k * 2) / 3, 2 + (l * 2) / 3, k - 4, l - 4));
                return;

            case 4: // '\004'
                mTemplateThumbnailRects.add(new MRect(4, 4, -3 + k / 3, -3 + l / 2));
                break;
            }
            mTemplateThumbnailRects.add(new MRect(4, 2 + l / 2, -3 + k / 3, l - 4));
            mTemplateThumbnailRects.add(new MRect(2 + k / 3, 4, -3 + (k * 2) / 3, -3 + l / 3));
            mTemplateThumbnailRects.add(new MRect(2 + k / 3, 2 + l / 3, -3 + (k * 2) / 3, -3 + (l * 2) / 3));
            mTemplateThumbnailRects.add(new MRect(2 + k / 3, 2 + (l * 2) / 3, -3 + (k * 2) / 3, l - 4));
            mTemplateThumbnailRects.add(new MRect(2 + (k * 2) / 3, 4, k - 4, -3 + l / 2));
            mTemplateThumbnailRects.add(new MRect(2 + (k * 2) / 3, 2 + l / 2, k - 4, l - 4));
            return;

        case 8: // '\b'
            switch (j)
            {
            default:
                return;

            case 1: // '\001'
                mTemplateThumbnailRects.add(new MRect(4, 4, -3 + k / 4, -3 + l / 4));
                mTemplateThumbnailRects.add(new MRect(4, 2 + l / 4, -3 + k / 4, -3 + (l * 2) / 4));
                mTemplateThumbnailRects.add(new MRect(4, 2 + l / 2, -3 + k / 4, -3 + (l * 3) / 4));
                mTemplateThumbnailRects.add(new MRect(4, 2 + (l * 3) / 4, -3 + k / 4, l - 4));
                mTemplateThumbnailRects.add(new MRect(2 + k / 4, 4, k - 4, -3 + (l * 3) / 4));
                mTemplateThumbnailRects.add(new MRect(2 + k / 4, 2 + (l * 3) / 4, -3 + k / 2, l - 4));
                mTemplateThumbnailRects.add(new MRect(2 + k / 2, 2 + (l * 3) / 4, -3 + (k * 3) / 4, l - 4));
                mTemplateThumbnailRects.add(new MRect(2 + (k * 3) / 4, 2 + (l * 3) / 4, k - 4, l - 4));
                return;

            case 2: // '\002'
                mTemplateThumbnailRects.add(new MRect(4, 4, -3 + k / 2, -3 + (l * 3) / 8));
                mTemplateThumbnailRects.add(new MRect(4, 2 + (l * 3) / 8, -3 + k / 2, -3 + (l * 6) / 8));
                mTemplateThumbnailRects.add(new MRect(4, 2 + (l * 6) / 8, -3 + k / 4, l - 4));
                mTemplateThumbnailRects.add(new MRect(2 + k / 4, 2 + (l * 6) / 8, -3 + k / 2, l - 4));
                mTemplateThumbnailRects.add(new MRect(2 + k / 2, 4, k - 4, -3 + (l * 3) / 8));
                mTemplateThumbnailRects.add(new MRect(2 + k / 2, 2 + (l * 3) / 8, k - 4, -3 + (l * 6) / 8));
                mTemplateThumbnailRects.add(new MRect(2 + k / 2, 2 + (l * 6) / 8, -3 + (k * 3) / 4, l - 4));
                mTemplateThumbnailRects.add(new MRect(2 + (k * 3) / 4, 2 + (l * 6) / 8, k - 4, l - 4));
                return;

            case 3: // '\003'
                mTemplateThumbnailRects.add(new MRect(4, 4, -3 + k / 2, -3 + l / 3));
                mTemplateThumbnailRects.add(new MRect(2 + k / 2, 4, k - 4, -3 + l / 3));
                mTemplateThumbnailRects.add(new MRect(4, 2 + l / 3, -3 + k / 3, -3 + (l * 2) / 3));
                mTemplateThumbnailRects.add(new MRect(2 + k / 3, 2 + l / 3, -3 + (k * 2) / 3, -3 + (l * 2) / 3));
                mTemplateThumbnailRects.add(new MRect(2 + (k * 2) / 3, 2 + l / 3, k - 4, -3 + (l * 2) / 3));
                mTemplateThumbnailRects.add(new MRect(4, 2 + (l * 2) / 3, -3 + k / 3, l - 4));
                mTemplateThumbnailRects.add(new MRect(2 + k / 3, 2 + (l * 2) / 3, -3 + (k * 2) / 3, l - 4));
                mTemplateThumbnailRects.add(new MRect(2 + (k * 2) / 3, 2 + (l * 2) / 3, k - 4, l - 4));
                return;

            case 4: // '\004'
                mTemplateThumbnailRects.add(new MRect(4, 4, -3 + k / 3, -3 + l / 3));
                break;
            }
            mTemplateThumbnailRects.add(new MRect(4, 2 + l / 3, -3 + k / 3, -3 + (l * 2) / 3));
            mTemplateThumbnailRects.add(new MRect(4, 2 + (l * 2) / 3, -3 + k / 3, l - 4));
            mTemplateThumbnailRects.add(new MRect(2 + k / 3, 4, -3 + (k * 2) / 3, -3 + l / 2));
            mTemplateThumbnailRects.add(new MRect(2 + k / 3, 2 + l / 2, -3 + (k * 2) / 3, l - 4));
            mTemplateThumbnailRects.add(new MRect(2 + (k * 2) / 3, 4, k - 4, -3 + l / 3));
            mTemplateThumbnailRects.add(new MRect(2 + (k * 2) / 3, 2 + l / 3, k - 4, -3 + (l * 2) / 3));
            mTemplateThumbnailRects.add(new MRect(2 + (k * 2) / 3, 2 + (l * 2) / 3, k - 4, l - 4));
            return;

        case 9: // '\t'
            switch (j)
            {
            default:
                return;

            case 1: // '\001'
                mTemplateThumbnailRects.add(new MRect(4, 4, -3 + k / 3, -3 + l / 3));
                mTemplateThumbnailRects.add(new MRect(4, 2 + l / 3, -3 + k / 3, -3 + (l * 2) / 3));
                mTemplateThumbnailRects.add(new MRect(4, 2 + (l * 2) / 3, -3 + k / 3, l - 4));
                mTemplateThumbnailRects.add(new MRect(2 + k / 3, 4, -3 + (k * 2) / 3, -3 + l / 3));
                mTemplateThumbnailRects.add(new MRect(2 + k / 3, 2 + l / 3, -3 + (k * 2) / 3, -3 + (l * 2) / 3));
                mTemplateThumbnailRects.add(new MRect(2 + k / 3, 2 + (l * 2) / 3, -3 + (k * 2) / 3, l - 4));
                mTemplateThumbnailRects.add(new MRect(2 + (k * 2) / 3, 4, k - 4, -3 + l / 3));
                mTemplateThumbnailRects.add(new MRect(2 + (k * 2) / 3, 2 + l / 3, k - 4, -3 + (l * 2) / 3));
                mTemplateThumbnailRects.add(new MRect(2 + (k * 2) / 3, 2 + (l * 2) / 3, k - 4, l - 4));
                return;

            case 2: // '\002'
                mTemplateThumbnailRects.add(new MRect(4, 4, k - 4, -3 + l / 2));
                mTemplateThumbnailRects.add(new MRect(4, 2 + l / 2, -3 + k / 4, -3 + (l * 3) / 4));
                mTemplateThumbnailRects.add(new MRect(2 + k / 4, 2 + l / 2, -3 + k / 2, -3 + (l * 3) / 4));
                mTemplateThumbnailRects.add(new MRect(2 + k / 2, 2 + l / 2, -3 + (k * 3) / 4, -3 + (l * 3) / 4));
                mTemplateThumbnailRects.add(new MRect(2 + (k * 3) / 4, 2 + l / 2, k - 4, -3 + (l * 3) / 4));
                mTemplateThumbnailRects.add(new MRect(4, 2 + (l * 3) / 4, -3 + k / 4, l - 4));
                mTemplateThumbnailRects.add(new MRect(2 + k / 4, 2 + (l * 3) / 4, -3 + k / 2, l - 4));
                mTemplateThumbnailRects.add(new MRect(2 + k / 2, 2 + (l * 3) / 4, -3 + (k * 3) / 4, l - 4));
                mTemplateThumbnailRects.add(new MRect(2 + (k * 3) / 4, 2 + (l * 3) / 4, k - 4, l - 4));
                return;

            case 3: // '\003'
                mTemplateThumbnailRects.add(new MRect(4, 4, -3 + k / 4, -3 + l / 4));
                break;
            }
            break;
        }
        mTemplateThumbnailRects.add(new MRect(2 + k / 4, 4, -3 + k / 2, -3 + l / 4));
        mTemplateThumbnailRects.add(new MRect(2 + k / 2, 4, -3 + (k * 3) / 4, -3 + l / 4));
        mTemplateThumbnailRects.add(new MRect(2 + (k * 3) / 4, 4, k - 4, -3 + l / 4));
        mTemplateThumbnailRects.add(new MRect(4, 2 + l / 4, k - 4, -3 + (l * 3) / 4));
        mTemplateThumbnailRects.add(new MRect(4, 2 + (l * 3) / 4, -3 + k / 4, l - 4));
        mTemplateThumbnailRects.add(new MRect(2 + k / 4, 2 + (l * 3) / 4, -3 + k / 2, l - 4));
        mTemplateThumbnailRects.add(new MRect(2 + k / 2, 2 + (l * 3) / 4, -3 + (k * 3) / 4, l - 4));
        mTemplateThumbnailRects.add(new MRect(2 + (k * 3) / 4, 2 + (l * 3) / 4, k - 4, l - 4));
    }

    private int generateFitoutBitmaps(String as[], ArrayList arraylist, int i)
    {
        for (int j = 0; j < i; j++)
        {
            android.graphics.BitmapFactory.Options options = new android.graphics.BitmapFactory.Options();
            options.inJustDecodeBounds = true;
            BitmapFactory.decodeFile(as[j], options);
            int k = Config.getThumbFromExif(as[j]);
            Rect rect = new Rect();
            int l;
            int i1;
            String s;
            int j1;
            int k1;
            MBitmap mbitmap;
            if (k == 0)
            {
                l = options.outWidth;
            } else
            {
                l = options.outHeight;
            }
            if (k == 0)
            {
                i1 = options.outHeight;
            } else
            {
                i1 = options.outWidth;
            }
            WorkShopUtils.getFitoutRect(l, i1, ((MRect)arraylist.get(j)).width(), ((MRect)arraylist.get(j)).height(), rect);
            s = as[j];
            if (k == 0)
            {
                j1 = rect.width();
            } else
            {
                j1 = rect.height();
            }
            if (k == 0)
            {
                k1 = rect.height();
            } else
            {
                k1 = rect.width();
            }
            mbitmap = MBitmapFactory.createMBitmapFromFile(s, j1, k1, MColorSpace.MPAF_RGB32_B8G8R8A8);
            if (mbitmap == null)
            {
                return -1;
            }
            if (k != 0)
            {
                mbitmap = mbitmap.rotate(k);
            }
            MRect mrect = new MRect(rect.left, rect.top, rect.right, rect.bottom);
            mFitoutImgRects.add(mrect);
            mBitmapList.add(mbitmap);
            MBitmap mbitmap1 = MBitmapFactory.createMBitmapBlank(mbitmap.getWidth(), mbitmap.getHeight(), mbitmap.getColorSpace());
            WorkShopUtils.copyBitmap(mbitmap1, mbitmap, new MPoint(0, 0));
            if (mbitmap1 == null)
            {
                return -1;
            }
            if (k != 0)
            {
                mbitmap1 = mbitmap1.rotate(k);
            }
            mOriBitmapList.add(mbitmap1);
        }

        return 0;
    }

    private MRect getTemplateRect()
    {
        MRect mrect = new MRect();
        mrect.set(getOffsetX(), getOffsetY(), mDisWidth, mDisHeight);
        return mrect;
    }

    private void hidePreNextBtn()
    {
        if (mPrevBtn != null)
        {
            mPrevBtn.setVisibility(4);
        }
        if (mNextBtn != null)
        {
            mNextBtn.setVisibility(4);
        }
    }

    private boolean isPreNextBtnShowing()
    {
        while (mPrevBtn == null || mPrevBtn.getVisibility() != 0) 
        {
            return false;
        }
        return true;
    }

    private boolean isRectInvalid(MRect mrect)
    {
        return mrect == null || mrect.top < 0 || mrect.left < 0 || mrect.right < 0 || mrect.bottom < 0;
    }

    private void refreshPreviewBtn()
    {
        if (mPrevBtn == null || mNextBtn == null)
        {
            return;
        }
        boolean flag;
        boolean flag1;
        Button button;
        boolean flag2;
        Button button1;
        boolean flag3;
        Button button2;
        Resources resources;
        int i;
        Button button3;
        Resources resources1;
        int j;
        if (1 == getMaxNum() || mCurrentType <= 1)
        {
            flag = true;
        } else
        {
            flag = false;
        }
        if (1 == getMaxNum() || mCurrentType >= getMaxNum())
        {
            flag1 = true;
        } else
        {
            flag1 = false;
        }
        button = mPrevBtn;
        if (flag)
        {
            flag2 = false;
        } else
        {
            flag2 = true;
        }
        button.setEnabled(flag2);
        button1 = mNextBtn;
        flag3 = false;
        if (!flag1)
        {
            flag3 = true;
        }
        button1.setEnabled(flag3);
        button2 = mPrevBtn;
        resources = getResources();
        if (flag)
        {
            i = 0x7f0200b5;
        } else
        {
            i = 0x7f0200b4;
        }
        button2.setBackgroundDrawable(resources.getDrawable(i));
        button3 = mNextBtn;
        resources1 = getResources();
        if (flag1)
        {
            j = 0x7f0200b1;
        } else
        {
            j = 0x7f0200b0;
        }
        button3.setBackgroundDrawable(resources1.getDrawable(j));
    }

    private void saveWithShareOrNot(boolean flag)
    {
        String s = mSurfaceView.save(mTemplateThumbnailRects, mBitmapList, flag);
        if (flag && s != null)
        {
            if (android.os.Build.VERSION.SDK_INT >= 14)
            {
                s = s.toLowerCase();
            }
            WorkShopUtils.sharePhotoByUri(Uri.fromFile(new File(s)), this);
        }
    }

    private void showMenuWidow()
    {
        if (mMenuWindow == null)
        {
            mMenuWindow = new PopupMenuWindow(this);
            mMenuWindow.setWindowParameter(getResources().getDimensionPixelSize(0x7f08001b), getResources().getDimensionPixelSize(0x7f08001a));
            mMenuWindow.setOnMenuClickedListener(onMenuClickedListener);
        }
        if (!mMenuWindow.isShowing())
        {
            mMenuWindow.showPopupMenuWindow(mMoreMenuBtn, 0, getResources().getInteger(0x7f0a0021));
        }
    }

    private void showPreNextBtn()
    {
        if (mPrevBtn != null)
        {
            mPrevBtn.setVisibility(0);
        }
        if (mNextBtn != null)
        {
            mNextBtn.setVisibility(0);
        }
    }

    private void startTempChangeThread()
    {
        mThread = new TemplateUpdateThread(mPicNum);
        mThread.start();
    }

    private void toastXXX(int i)
    {
        Object obj = null;
        i;
        JVM INSTR tableswitch 1 3: default 28
    //                   1 42
    //                   2 53
    //                   3 64;
           goto _L1 _L2 _L3 _L4
_L1:
        if (obj != null)
        {
            Toast.makeText(this, ((CharSequence) (obj)), 0).show();
        }
        return;
_L2:
        obj = getString(0x7f0b01b7);
        continue; /* Loop/switch isn't completed */
_L3:
        obj = getString(0x7f0b01b8);
        continue; /* Loop/switch isn't completed */
_L4:
        obj = getString(0x7f0b01b9);
        if (true) goto _L1; else goto _L5
_L5:
    }

    public static void translateRect(Rect rect, int i, int j)
    {
        rect.top = j + rect.top;
        rect.bottom = j + rect.bottom;
        rect.left = i + rect.left;
        rect.right = i + rect.right;
    }

    public static void translateRect(MRect mrect, int i, int j)
    {
        mrect.top = j + mrect.top;
        mrect.bottom = j + mrect.bottom;
        mrect.left = i + mrect.left;
        mrect.right = i + mrect.right;
    }

    public void changeTemplate(int i, int j, boolean flag)
    {
        if (j == mCurrentType && !flag)
        {
            return;
        }
        if (j <= 1)
        {
            mCurrentType = 1;
        } else
        if (j >= getMaxNum())
        {
            mCurrentType = getMaxNum();
        } else
        {
            mCurrentType = j;
        }
        if (mSurfaceView != null)
        {
            mSurfaceView.changeTemplete();
        }
        if (flag)
        {
            if (mSurfaceView != null)
            {
                mSurfaceView.setBackgroundDrawable(getResources().getDrawable(0x7f0201b0));
            }
            hidePreNextBtn();
        }
        if (mContentTxv != null)
        {
            mContentTxv.setText((new StringBuilder()).append(" ").append(mCurrentType).append(" / ").append(getMaxNum()).toString());
        }
        if (mProcessDlg == null)
        {
            mProcessDlg = new UISaveDialog(this, 0x7f0d0003);
        }
        mProcessDlg.show();
        startTempChangeThread();
    }

    public void drawPart(int i)
    {
        mSurfaceView.drawPart(i);
    }

    public ArrayList getBitmapList()
    {
        this;
        JVM INSTR monitorenter ;
        ArrayList arraylist = mBitmapList;
        this;
        JVM INSTR monitorexit ;
        return arraylist;
        Exception exception;
        exception;
        throw exception;
    }

    public int getCurrRectIndex(int i, int j)
    {
        ArrayList arraylist = getTemplateThumbnailRects();
        if (!arraylist.isEmpty()) goto _L2; else goto _L1
_L1:
        int i1 = -1;
_L4:
        return i1;
_L2:
        int k = getOffsetX();
        int l = getOffsetY();
        i1 = 0;
label0:
        do
        {
label1:
            {
                if (i1 >= arraylist.size())
                {
                    break label1;
                }
                MRect mrect = new MRect((MRect)arraylist.get(i1));
                translateRect(mrect, k, l);
                if (WorkShopUtils.isPointInRect(mrect, i, j))
                {
                    break label0;
                }
                i1++;
            }
        } while (true);
        if (true) goto _L4; else goto _L3
_L3:
        return -1;
    }

    public int getDisH()
    {
        return mDisHeight;
    }

    public ArrayList getDisRects2Img()
    {
        this;
        JVM INSTR monitorenter ;
        ArrayList arraylist = mDisRects2Img;
        this;
        JVM INSTR monitorexit ;
        return arraylist;
        Exception exception;
        exception;
        throw exception;
    }

    public int getDisW()
    {
        return mDisWidth;
    }

    public ArrayList getFitoutImgRects()
    {
        this;
        JVM INSTR monitorenter ;
        ArrayList arraylist = mFitoutImgRects;
        this;
        JVM INSTR monitorexit ;
        return arraylist;
        Exception exception;
        exception;
        throw exception;
    }

    public int getMaxNum()
    {
        return MAX_NUMS[-1 + mPicNum];
    }

    public int getOffsetX()
    {
        return 0;
    }

    public int getOffsetY()
    {
        return 0;
    }

    public ArrayList getOriBitmapList()
    {
        this;
        JVM INSTR monitorenter ;
        ArrayList arraylist = mOriBitmapList;
        this;
        JVM INSTR monitorexit ;
        return arraylist;
        Exception exception;
        exception;
        throw exception;
    }

    public MRect getOriFitoutRect(int i)
    {
        this;
        JVM INSTR monitorenter ;
        if (mOriFitoutImgRects.size() <= 0 || i <= -1) goto _L2; else goto _L1
_L1:
        if (i >= mOriFitoutImgRects.size()) goto _L2; else goto _L3
_L3:
        MRect mrect = (MRect)mOriFitoutImgRects.get(i);
_L5:
        this;
        JVM INSTR monitorexit ;
        return mrect;
_L2:
        mrect = null;
        if (true) goto _L5; else goto _L4
_L4:
        Exception exception;
        exception;
        throw exception;
    }

    public int getPicNum()
    {
        return mPicNum;
    }

    String[] getPicPaths()
    {
        return picPaths;
    }

    public ArrayList getRotateAngles()
    {
        return mRotateAngles;
    }

    public MRect getTemplateDirtyRect()
    {
        return mTemplateDirtyRect;
    }

    public ArrayList getTemplateThumbnailRects()
    {
        this;
        JVM INSTR monitorenter ;
        ArrayList arraylist = mTemplateThumbnailRects;
        this;
        JVM INSTR monitorexit ;
        return arraylist;
        Exception exception;
        exception;
        throw exception;
    }

    public int getType()
    {
        return mCurrentType;
    }

    public Point getZoomFirstPointerPos()
    {
        return mSurfaceView.getZoomFirstPointerPos();
    }

    public ArrayList getZoomScales()
    {
        this;
        JVM INSTR monitorenter ;
        ArrayList arraylist = mZoomScales;
        this;
        JVM INSTR monitorexit ;
        return arraylist;
        Exception exception;
        exception;
        throw exception;
    }

    public boolean isThreadRunning()
    {
        return mThreadRunning;
    }

    protected void onCreate(Bundle bundle)
    {
        super.onCreate(bundle);
        setContentView(0x7f030005);
        mZoomPanController = new ZoomPanController(this);
        Intent intent = getIntent();
        if (intent != null)
        {
            Bundle bundle1 = intent.getExtras();
            if (bundle1 != null)
            {
                picPaths = bundle1.getStringArray("collagepaths");
            }
        }
        if (picPaths != null)
        {
            mPicNum = picPaths.length;
        }
        mSurfaceView = (CollageSurfaceView)findViewById(0x7f090026);
        mPrevBtn = (Button)findViewById(0x7f090025);
        mNextBtn = (Button)findViewById(0x7f090027);
        mContentTxv = (TextView)findViewById(0x7f090021);
        mBackBtn = (Button)findViewById(0x7f09001f);
        if (mBackBtn != null)
        {
            mBackBtn.setOnClickListener(mClickListener);
        }
        mMoreMenuBtn = (Button)findViewById(0x7f090022);
        if (mMoreMenuBtn != null)
        {
            mMoreMenuBtn.setOnClickListener(mClickListener);
        }
        if (mPrevBtn != null)
        {
            mPrevBtn.setOnClickListener(mClickListener);
        }
        if (mNextBtn != null)
        {
            mNextBtn.setOnClickListener(mClickListener);
        }
        refreshPreviewBtn();
        mSurfaceView.setOnPanGestureListener(mZoomPanController.createOnPanGestureListener());
        mSurfaceView.setOnZoomGestureListener(mZoomPanController.createOnZoomGestureListener());
        mSurfaceView.setOnRotateGestureListener(mZoomPanController.createOnRotateGestureListener());
        mDisHeight = getResources().getDimensionPixelSize(0x7f08001d);
        mDisWidth = getResources().getDimensionPixelSize(0x7f08001c);
        mTemplateDirtyRect = getTemplateRect();
    }

    protected void onDestroy()
    {
        super.onDestroy();
        destroyTempChangeThread();
        if (mProcessDlg != null)
        {
            mProcessDlg.dismiss();
            mProcessDlg = null;
        }
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
        if (mMenuWindow != null && mMenuWindow.isShowing())
        {
            mMenuWindow.hidePopopMenuWindow();
        }
        return super.onPrepareOptionsMenu(menu);
    }

    public void setDis2ImgRect(int i)
    {
        int j = getOffsetX();
        int k = getOffsetY();
        MRect mrect = new MRect((MRect)mTemplateThumbnailRects.get(i));
        translateRect(mrect, j, k);
        int l = (((MRect)mFitoutImgRects.get(i)).width() - mrect.width()) / 2;
        int i1 = (((MRect)mFitoutImgRects.get(i)).height() - mrect.height()) / 2;
        ((MRect)mDisRects2Img.get(i)).set(l, i1, l + mrect.width(), i1 + mrect.height());
    }

    static 
    {
        System.loadLibrary("arcplatform");
        System.loadLibrary("arcimgutilsbase");
        if (android.os.Build.VERSION.SDK_INT >= 19)
        {
            break MISSING_BLOCK_LABEL_70;
        }
        FileUtils.CopyFile((new StringBuilder()).append("/data/data/com.MUVI.MediaPlus/lib/").append("libarcimgutils.so").toString(), "/data/data/com.MUVI.MediaPlus/libarcimgutils.so");
_L2:
        System.load("/data/data/com.MUVI.MediaPlus/libarcimgutils.so");
        System.loadLibrary("workshopplatform");
        System.loadLibrary("arcphotoeditormt");
        System.loadLibrary("frameutils");
        System.loadLibrary("workshoputils");
        break MISSING_BLOCK_LABEL_104;
        FileUtils.CopyFile((new StringBuilder()).append("/data/data/com.MUVI.MediaPlus/lib/").append("libarcimgutils_4_4.so").toString(), "/data/data/com.MUVI.MediaPlus/libarcimgutils.so");
        if (true) goto _L2; else goto _L1
_L1:
        UnsatisfiedLinkError unsatisfiedlinkerror;
        unsatisfiedlinkerror;
        unsatisfiedlinkerror.printStackTrace();
    }



/*
    static long access$002(Collage collage, long l)
    {
        collage.mClickedTime = l;
        return l;
    }

*/






/*
    static boolean access$302(Collage collage, boolean flag)
    {
        collage.mThreadRunning = flag;
        return flag;
    }

*/






}
