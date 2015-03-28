// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.arcsoft.mediaplus;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Resources;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.os.Parcelable;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;
import com.arcsoft.mediaplus.collage.Collage;
import com.arcsoft.mediaplus.datasource.MediaItem;
import com.arcsoft.mediaplus.dmc.RenderDevSelector;
import com.arcsoft.mediaplus.picture.ui.RemoteListViewGL;
import com.arcsoft.util.FileUtils;
import com.arcsoft.util.debug.Log;
import java.util.ArrayList;
import java.util.Iterator;

// Referenced classes of package com.arcsoft.mediaplus:
//            ListViewManager, MediaPlusActivity, DownloadFacade, ViewManager, 
//            RemoteListView

public class ControlBar extends RelativeLayout
{
    static interface ToastType
    {

        public static final int TOAST_DECODE_ERROR = 4;
        public static final int TOAST_MIXED = 1;
        public static final int TOAST_NONE = 3;
        public static final int TOAST_OVER_MAXIMUM = 0;
        public static final int TOAST_ZERO_PIC = 2;
    }


    static final int COLLAGE = 1;
    static final int NONE = 0;
    static final int SHARE = 2;
    private boolean isShowPreviewBtn;
    private ImageView mBackToPreviewBtn;
    private int mBadFileNum;
    private String mBadNames;
    private TextView mCancelAllBtn;
    private ImageView mChangeToSelectBtn;
    private long mClickedTime;
    private ImageView mCollageBtn;
    private Context mContext;
    private ImageView mDeleteBtn;
    RenderDevSelector mDevSelector;
    private TextView mDownloadAllBtn;
    private ImageView mDownloadBtn;
    DownloadFacade.IPreDownloadListener mDownloadListener = new DownloadFacade.IPreDownloadListener() {

        final ControlBar this$0;

        public void onDownloadBegin()
        {
        }

        public void onDownloadFinish(ArrayList arraylist)
        {
            if (arraylist != null && arraylist.size() != 0)
            {
                if (1 == getRemoteBtnClickType())
                {
                    if (mSelectedPicData == null)
                    {
                        mSelectedPicData = new ArrayList();
                    }
                    mSelectedPicData.clear();
                    Uri uri;
                    for (Iterator iterator = arraylist.iterator(); iterator.hasNext(); mSelectedPicData.add(converUri2Path(uri)))
                    {
                        uri = (Uri)iterator.next();
                    }

                    mImageSelectedCount = arraylist.size();
                    mTotalSelectedCount = mImageSelectedCount;
                    startCollageActivity();
                    return;
                }
                if (2 == getRemoteBtnClickType())
                {
                    for (int i = 0; i < arraylist.size(); i++)
                    {
                        Log.e("zdf", (new StringBuilder()).append("####### onDownloadFinish, Share uri(").append(i).append(") = ").append(arraylist.get(i)).toString());
                    }

                    ControlBar.share(mContext, arraylist, getShareMimeType());
                    return;
                }
            }
        }

            
            {
                this$0 = ControlBar.this;
                super();
            }
    };
    private int mImageSelectedCount;
    private ListViewManager mListViewManager;
    private ImageView mPlayToBtn;
    int mRemoteBtnClickType;
    private RelativeLayout mRemoteCtrlViewLayout;
    private ImageView mRemoteSelectBtn;
    private LinearLayout mSelectViewLayout;
    private ArrayList mSelectedPicData;
    private ImageView mShareBtn;
    private int mTotalSelectedCount;

    public ControlBar(Context context)
    {
        super(context);
        mContext = null;
        mDownloadAllBtn = null;
        mCancelAllBtn = null;
        mChangeToSelectBtn = null;
        mShareBtn = null;
        mDeleteBtn = null;
        mCollageBtn = null;
        mRemoteSelectBtn = null;
        mDownloadBtn = null;
        mPlayToBtn = null;
        mBackToPreviewBtn = null;
        mSelectViewLayout = null;
        mRemoteCtrlViewLayout = null;
        isShowPreviewBtn = false;
        mClickedTime = 0L;
        mDevSelector = null;
        mRemoteBtnClickType = 0;
        mListViewManager = null;
        mSelectedPicData = null;
        mTotalSelectedCount = 0;
        mImageSelectedCount = 0;
        mBadNames = "";
        mBadFileNum = 0;
        mContext = context;
    }

    public ControlBar(Context context, AttributeSet attributeset)
    {
        super(context, attributeset);
        mContext = null;
        mDownloadAllBtn = null;
        mCancelAllBtn = null;
        mChangeToSelectBtn = null;
        mShareBtn = null;
        mDeleteBtn = null;
        mCollageBtn = null;
        mRemoteSelectBtn = null;
        mDownloadBtn = null;
        mPlayToBtn = null;
        mBackToPreviewBtn = null;
        mSelectViewLayout = null;
        mRemoteCtrlViewLayout = null;
        isShowPreviewBtn = false;
        mClickedTime = 0L;
        mDevSelector = null;
        mRemoteBtnClickType = 0;
        mListViewManager = null;
        mSelectedPicData = null;
        mTotalSelectedCount = 0;
        mImageSelectedCount = 0;
        mBadNames = "";
        mBadFileNum = 0;
        mContext = context;
    }

    public ControlBar(Context context, AttributeSet attributeset, int i)
    {
        super(context, attributeset, i);
        mContext = null;
        mDownloadAllBtn = null;
        mCancelAllBtn = null;
        mChangeToSelectBtn = null;
        mShareBtn = null;
        mDeleteBtn = null;
        mCollageBtn = null;
        mRemoteSelectBtn = null;
        mDownloadBtn = null;
        mPlayToBtn = null;
        mBackToPreviewBtn = null;
        mSelectViewLayout = null;
        mRemoteCtrlViewLayout = null;
        isShowPreviewBtn = false;
        mClickedTime = 0L;
        mDevSelector = null;
        mRemoteBtnClickType = 0;
        mListViewManager = null;
        mSelectedPicData = null;
        mTotalSelectedCount = 0;
        mImageSelectedCount = 0;
        mBadNames = "";
        mBadFileNum = 0;
        mContext = context;
    }

    private boolean checkInvalidPath(String as[])
    {
        long l = System.currentTimeMillis();
        boolean flag = false;
        if (as != null)
        {
            int i = as.length;
            mBadFileNum = 0;
            mBadNames = "";
            for (int j = 0; j < i; j++)
            {
                android.graphics.BitmapFactory.Options options = new android.graphics.BitmapFactory.Options();
                options.inJustDecodeBounds = true;
                BitmapFactory.decodeFile(as[j], options);
                if (options.outHeight <= 0 || options.outWidth <= 0)
                {
                    flag = true;
                    mBadNames = (new StringBuilder()).append(mBadNames).append(" <").append(mListViewManager.getSelectedItemList()[j].title).append("> ").toString();
                    mBadFileNum = 1 + mBadFileNum;
                }
            }

        }
        Log.d("Controlbar", (new StringBuilder()).append("checkInvalidPath costs:  ").append(System.currentTimeMillis() - l).toString());
        return flag;
    }

    private String converUri2Path(Uri uri)
    {
        String s;
        if (uri == null)
        {
            s = "";
        } else
        {
            s = uri.toString();
            if (s != null && s.startsWith("file://"))
            {
                return Uri.decode(s.toString()).substring(7);
            }
        }
        return s;
    }

    private void getCollageData()
    {
        if (mListViewManager == null)
        {
            return;
        }
        MediaItem amediaitem[];
        int i;
        if (mSelectedPicData == null)
        {
            mSelectedPicData = new ArrayList();
        } else
        {
            mSelectedPicData.clear();
            mTotalSelectedCount = 0;
            mImageSelectedCount = 0;
        }
        amediaitem = mListViewManager.getSelectedItemList();
        i = amediaitem.length;
        for (int j = 0; j < i; j++)
        {
            MediaItem mediaitem = amediaitem[j];
            if (mediaitem.mime_type.contains("image"))
            {
                mSelectedPicData.add(converUri2Path(mediaitem.uri));
            }
        }

        mTotalSelectedCount = amediaitem.length;
        mImageSelectedCount = mSelectedPicData.size();
    }

    private Uri getLocalContentUri(MediaItem mediaitem)
    {
        return FileUtils.filePathToContentUri(mediaitem.uri, mediaitem._id, mediaitem.videoOrImage);
    }

    private void initListener()
    {
        if (mBackToPreviewBtn != null)
        {
            mBackToPreviewBtn.setOnClickListener(new android.view.View.OnClickListener() {

                final ControlBar this$0;

                public void onClick(View view)
                {
                    if (mContext != null)
                    {
                        ((MediaPlusActivity)mContext).onBackPressed();
                    }
                }

            
            {
                this$0 = ControlBar.this;
                super();
            }
            });
        }
        if (mPlayToBtn == null);
        if (mChangeToSelectBtn != null)
        {
            mChangeToSelectBtn.setOnClickListener(new android.view.View.OnClickListener() {

                final ControlBar this$0;

                public void onClick(View view)
                {
                    ((MediaPlusActivity)mContext).switchView(2);
                }

            
            {
                this$0 = ControlBar.this;
                super();
            }
            });
        }
        if (mRemoteSelectBtn != null)
        {
            mRemoteSelectBtn.setOnClickListener(new android.view.View.OnClickListener() {

                final ControlBar this$0;

                public void onClick(View view)
                {
                    if (mContext != null)
                    {
                        ((MediaPlusActivity)mContext).switchView(2);
                    }
                }

            
            {
                this$0 = ControlBar.this;
                super();
            }
            });
        }
        if (mDownloadBtn != null)
        {
            mDownloadBtn.setOnClickListener(new android.view.View.OnClickListener() {

                final ControlBar this$0;

                public void onClick(View view)
                {
                    ((MediaPlusActivity)mContext).switchView(4);
                }

            
            {
                this$0 = ControlBar.this;
                super();
            }
            });
        }
        if (mDownloadAllBtn != null)
        {
            mDownloadAllBtn.setOnClickListener(new android.view.View.OnClickListener() {

                final ControlBar this$0;

                public void onClick(View view)
                {
                    com.arcsoft.mediaplus.listview.IItemListView iitemlistview = mListViewManager.getCurrentListView();
                    if (iitemlistview instanceof RemoteListView)
                    {
                        ((RemoteListView)iitemlistview).downloadAll();
                    }
                    if (iitemlistview instanceof RemoteListViewGL)
                    {
                        ((RemoteListViewGL)iitemlistview).downloadAll();
                    }
                }

            
            {
                this$0 = ControlBar.this;
                super();
            }
            });
        }
        if (mCancelAllBtn != null)
        {
            mCancelAllBtn.setOnClickListener(new android.view.View.OnClickListener() {

                final ControlBar this$0;

                public void onClick(View view)
                {
                    com.arcsoft.mediaplus.listview.IItemListView iitemlistview = mListViewManager.getCurrentListView();
                    if (iitemlistview instanceof RemoteListViewGL)
                    {
                        ((RemoteListViewGL)iitemlistview).cancelAll();
                    }
                }

            
            {
                this$0 = ControlBar.this;
                super();
            }
            });
        }
        if (mDeleteBtn != null)
        {
            mDeleteBtn.setOnClickListener(new android.view.View.OnClickListener() {

                final ControlBar this$0;

                public void onClick(View view)
                {
                    (new android.app.AlertDialog.Builder(mContext)).setTitle(0x7f0b011e).setMessage(0x7f0b011f).setPositiveButton(0x7f0b001f, new android.content.DialogInterface.OnClickListener() {

                        final _cls8 this$1;

                        public void onClick(DialogInterface dialoginterface, int i)
                        {
                            ((MediaPlusActivity)mContext).deleteFiles();
                            dialoginterface.dismiss();
                        }

            
            {
                this$1 = _cls8.this;
                super();
            }
                    }).setNegativeButton(0x7f0b0020, new android.content.DialogInterface.OnClickListener() {

                        final _cls8 this$1;

                        public void onClick(DialogInterface dialoginterface, int i)
                        {
                            dialoginterface.dismiss();
                        }

            
            {
                this$1 = _cls8.this;
                super();
            }
                    }).show();
                }

            
            {
                this$0 = ControlBar.this;
                super();
            }
            });
        }
        if (mCollageBtn != null)
        {
            mCollageBtn.setOnClickListener(new android.view.View.OnClickListener() {

                final ControlBar this$0;

                public void onClick(View view)
                {
                    if (System.currentTimeMillis() - mClickedTime <= 1000L)
                    {
                        return;
                    }
                    if (1 != getCurrentViewType()) goto _L2; else goto _L1
_L1:
                    getCollageData();
                    startCollageActivity();
_L4:
                    mClickedTime = System.currentTimeMillis();
                    return;
_L2:
                    if (getCurrentViewType() == 0)
                    {
                        mRemoteBtnClickType = 1;
                        preDownload(0x7f0b0180);
                    }
                    if (true) goto _L4; else goto _L3
_L3:
                }

            
            {
                this$0 = ControlBar.this;
                super();
            }
            });
        }
        if (mShareBtn != null)
        {
            mShareBtn.setOnClickListener(new android.view.View.OnClickListener() {

                final ControlBar this$0;

                public void onClick(View view)
                {
                    if (System.currentTimeMillis() - mClickedTime <= 1000L)
                    {
                        return;
                    }
                    if (1 != getCurrentViewType()) goto _L2; else goto _L1
_L1:
                    localShare();
_L4:
                    mClickedTime = System.currentTimeMillis();
                    return;
_L2:
                    if (getCurrentViewType() == 0)
                    {
                        mRemoteBtnClickType = 2;
                        preDownload(0x7f0b017f);
                    }
                    if (true) goto _L4; else goto _L3
_L3:
                }

            
            {
                this$0 = ControlBar.this;
                super();
            }
            });
        }
    }

    private boolean isValidNumbers()
    {
        boolean flag = true;
        if (mImageSelectedCount > 9)
        {
            toastXXX(0);
            flag = false;
        } else
        {
            if (mTotalSelectedCount == 0)
            {
                toastXXX(3);
                return false;
            }
            if (mImageSelectedCount == 0)
            {
                toastXXX(2);
                return false;
            }
            if (mTotalSelectedCount != mImageSelectedCount)
            {
                toastXXX(1);
                return flag;
            }
        }
        return flag;
    }

    public static void share(Context context, ArrayList arraylist, String s)
    {
        Intent intent;
        if (context == null || arraylist == null || arraylist.size() == 0)
        {
            return;
        }
        arraylist.size();
        intent = new Intent("android.intent.action.SEND_MULTIPLE");
        intent.setType(s);
        if (arraylist.size() != 1) goto _L2; else goto _L1
_L1:
        intent.setAction("android.intent.action.SEND");
        intent.putExtra("android.intent.extra.STREAM", (Parcelable)arraylist.get(0));
_L4:
        MediaPlusActivity.mIsSharing = true;
        ((Activity)context).startActivityForResult(Intent.createChooser(intent, context.getString(0x7f0b011d)), 0x800004);
        return;
_L2:
        if (arraylist.size() >= 1)
        {
            intent.setAction("android.intent.action.SEND_MULTIPLE");
            intent.addFlags(1);
            intent.putExtra("android.intent.extra.STREAM", arraylist);
        }
        if (true) goto _L4; else goto _L3
_L3:
    }

    private void startCollageActivity()
    {
        if (mSelectedPicData != null)
        {
            String as[] = new String[mImageSelectedCount];
            for (int i = 0; i < mImageSelectedCount; i++)
            {
                as[i] = (String)mSelectedPicData.get(i);
            }

            if (checkInvalidPath(as))
            {
                toastXXX(4);
                return;
            }
            if (isValidNumbers())
            {
                Intent intent = new Intent(mContext, com/arcsoft/mediaplus/collage/Collage);
                Bundle bundle = new Bundle();
                bundle.putStringArray("collagepaths", as);
                intent.putExtras(bundle);
                ((MediaPlusActivity)mContext).startActivityForResult(intent, 0x800003);
                return;
            }
        }
    }

    private void toastXXX(int i)
    {
        String s;
        if (mContext == null)
        {
            return;
        }
        s = "";
        i;
        JVM INSTR tableswitch 0 4: default 48
    //                   0 61
    //                   1 78
    //                   2 195
    //                   3 212
    //                   4 229;
           goto _L1 _L2 _L3 _L4 _L5 _L6
_L1:
        Toast.makeText(mContext, s, 0).show();
        return;
_L2:
        s = mContext.getResources().getString(0x7f0b016e);
        continue; /* Loop/switch isn't completed */
_L3:
        StringBuilder stringbuilder1 = new StringBuilder();
        Resources resources1 = mContext.getResources();
        int k = mImageSelectedCount;
        Object aobj1[] = new Object[1];
        aobj1[0] = Integer.valueOf(mImageSelectedCount);
        StringBuilder stringbuilder2 = stringbuilder1.append(resources1.getQuantityString(0x7f0c0000, k, aobj1)).append(" ");
        String s1 = getContext().getResources().getString(0x7f0b016f);
        Object aobj2[] = new Object[1];
        aobj2[0] = Integer.valueOf(mTotalSelectedCount);
        s = stringbuilder2.append(String.format(s1, aobj2)).toString();
        continue; /* Loop/switch isn't completed */
_L4:
        s = mContext.getResources().getString(0x7f0b0170);
        continue; /* Loop/switch isn't completed */
_L5:
        s = mContext.getResources().getString(0x7f0b0171);
        continue; /* Loop/switch isn't completed */
_L6:
        StringBuilder stringbuilder = new StringBuilder();
        Resources resources = getResources();
        int j = mBadFileNum;
        Object aobj[] = new Object[1];
        aobj[0] = Integer.valueOf(mBadFileNum);
        s = stringbuilder.append(resources.getQuantityString(0x7f0c0001, j, aobj)).append(mBadNames).append(getResources().getString(0x7f0b01ba)).toString();
        if (true) goto _L1; else goto _L7
_L7:
    }

    public void InitUI()
    {
        mBackToPreviewBtn = (ImageView)findViewById(0x7f09002e);
        mDownloadAllBtn = (TextView)findViewById(0x7f09002d);
        mCancelAllBtn = (TextView)findViewById(0x7f09002c);
        setCancelAllBtnEnabled(false);
        mChangeToSelectBtn = (ImageView)findViewById(0x7f09002f);
        mSelectViewLayout = (LinearLayout)findViewById(0x7f090030);
        mRemoteCtrlViewLayout = (RelativeLayout)findViewById(0x7f090035);
        mShareBtn = (ImageView)findViewById(0x7f090031);
        mDeleteBtn = (ImageView)findViewById(0x7f090034);
        mCollageBtn = (ImageView)findViewById(0x7f090033);
        mRemoteSelectBtn = (ImageView)findViewById(0x7f090036);
        mDownloadBtn = (ImageView)findViewById(0x7f090037);
        mPlayToBtn = (ImageView)findViewById(0x7f090032);
        if (mPlayToBtn != null)
        {
            mPlayToBtn.setVisibility(8);
        }
        if (mCollageBtn != null)
        {
            mCollageBtn.setVisibility(8);
        }
        initListener();
        setAllBtnEnable(false);
        ((MediaPlusActivity)mContext).getDownloadFacade().setPreDownloadListener(mDownloadListener);
        boolean flag = mContext.getSharedPreferences(mContext.getPackageName(), 0).getBoolean("key_support_living_view", true);
        isShowPreviewBtn = flag;
        showBackToPreviewBtn(flag, false);
    }

    public void destroy()
    {
        mContext = null;
        mDownloadAllBtn = null;
        mChangeToSelectBtn = null;
        mSelectViewLayout = null;
        mRemoteCtrlViewLayout = null;
        mShareBtn = null;
        mDeleteBtn = null;
        mCollageBtn = null;
        mRemoteSelectBtn = null;
        mDownloadBtn = null;
        mCancelAllBtn = null;
        if (mDevSelector != null)
        {
            mDevSelector.destroy();
            mDevSelector = null;
        }
    }

    int getCurrentViewType()
    {
        if (mContext == null)
        {
            return -1;
        } else
        {
            return ((MediaPlusActivity)mContext).getCurrentViewType();
        }
    }

    int getRemoteBtnClickType()
    {
        return mRemoteBtnClickType;
    }

    String getShareMimeType()
    {
        MediaItem amediaitem[] = mListViewManager.getSelectedItemList();
        if (amediaitem == null)
        {
            return "*/*";
        }
        boolean flag = false;
        boolean flag1 = false;
        int i = amediaitem.length;
        int j = 0;
        while (j < i) 
        {
            MediaItem mediaitem = amediaitem[j];
            if (mediaitem != null)
            {
                if (mediaitem.videoOrImage)
                {
                    flag = true;
                } else
                {
                    flag1 = true;
                }
            }
            j++;
        }
        if (flag1 && !flag)
        {
            return "image/*";
        }
        if (!flag1 && flag)
        {
            return "video/*";
        } else
        {
            return "*/*";
        }
    }

    void localShare()
    {
        MediaItem amediaitem[] = mListViewManager.getSelectedItemList();
        ArrayList arraylist = new ArrayList();
        for (int i = 0; i < amediaitem.length; i++)
        {
            arraylist.add(getLocalContentUri(amediaitem[i]));
        }

        share(mContext, arraylist, getShareMimeType());
    }

    public void onConfigurationChanged()
    {
        if (mDevSelector != null)
        {
            mDevSelector.onConfigurationChanged();
        }
    }

    void preDownload(int i)
    {
        MediaItem amediaitem[];
        if (mContext != null)
        {
            if ((amediaitem = mListViewManager.getSelectedItemList()) != null && amediaitem.length != 0)
            {
                ArrayList arraylist = new ArrayList();
                int j = amediaitem.length;
                int k = 0;
                int l = 0;
                while (k < j) 
                {
                    MediaItem mediaitem = amediaitem[k];
                    ListViewManager listviewmanager = mListViewManager;
                    int i1 = l + 1;
                    com.arcsoft.mediaplus.updownload.service.UpDownloadEngine.DownloadTask downloadtask = DownloadFacade.mediaItemToDownloadTask(mediaitem, 1, listviewmanager.getDownloadedUriInSelected(l));
                    if (1 != getRemoteBtnClickType() || !mediaitem.videoOrImage)
                    {
                        arraylist.add(downloadtask);
                    }
                    k++;
                    l = i1;
                }
                if (arraylist.size() == 0)
                {
                    arraylist.clear();
                    toastXXX(2);
                    return;
                } else
                {
                    Log.e("zdf", "####### preDownload(Share)");
                    ((MediaPlusActivity)mContext).getDownloadFacade().downloadAllWithConfirmDlg(i, arraylist);
                    return;
                }
            }
        }
    }

    public void setAllBtnEnable(boolean flag)
    {
        switch (ViewManager.getViewStatus())
        {
        default:
            return;

        case 0: // '\0'
            mRemoteSelectBtn.setEnabled(flag);
            mDownloadBtn.setEnabled(flag);
            return;

        case 1: // '\001'
            mChangeToSelectBtn.setEnabled(flag);
            return;

        case 2: // '\002'
            mDeleteBtn.setEnabled(flag);
            break;
        }
        mShareBtn.setEnabled(flag);
        mCollageBtn.setEnabled(flag);
        mPlayToBtn.setEnabled(flag);
    }

    public void setCancelAllBtnEnabled(boolean flag)
    {
        if (mCancelAllBtn == null)
        {
            return;
        }
        mCancelAllBtn.setEnabled(flag);
        TextView textview = mCancelAllBtn;
        int i;
        if (flag)
        {
            i = mContext.getResources().getColor(0x7f070006);
        } else
        {
            i = mContext.getResources().getColor(0x7f070001);
        }
        textview.setTextColor(i);
    }

    public void setExcuting(boolean flag)
    {
        if (mBackToPreviewBtn != null)
        {
            ImageView imageview = mBackToPreviewBtn;
            boolean flag1;
            if (!flag)
            {
                flag1 = true;
            } else
            {
                flag1 = false;
            }
            imageview.setEnabled(flag1);
        }
    }

    public void setListViewManager(ListViewManager listviewmanager)
    {
        mListViewManager = listviewmanager;
    }

    public void setVisibility(int i)
    {
        super.setVisibility(i);
    }

    public void showBackToPreviewBtn(boolean flag, boolean flag1)
    {
        if (flag)
        {
            if (mBackToPreviewBtn != null)
            {
                mBackToPreviewBtn.setVisibility(0);
                setVisibility(0);
            }
            if (!flag1)
            {
                if (mRemoteSelectBtn != null)
                {
                    android.widget.RelativeLayout.LayoutParams layoutparams2 = new android.widget.RelativeLayout.LayoutParams(-2, -2);
                    layoutparams2.addRule(13);
                    mRemoteSelectBtn.setLayoutParams(layoutparams2);
                }
                if (mChangeToSelectBtn != null)
                {
                    android.widget.RelativeLayout.LayoutParams layoutparams3 = new android.widget.RelativeLayout.LayoutParams(-2, -2);
                    layoutparams3.addRule(11);
                    layoutparams3.addRule(15);
                    layoutparams3.setMargins(0, 0, (int)getResources().getDimension(0x7f0800a5), 0);
                    mChangeToSelectBtn.setLayoutParams(layoutparams3);
                }
                invalidate();
            }
        } else
        {
            if (mBackToPreviewBtn != null)
            {
                mBackToPreviewBtn.setVisibility(8);
            }
            if (!flag1)
            {
                if (mRemoteSelectBtn != null)
                {
                    android.widget.RelativeLayout.LayoutParams layoutparams = new android.widget.RelativeLayout.LayoutParams(-2, -2);
                    layoutparams.addRule(9);
                    layoutparams.addRule(15);
                    mRemoteSelectBtn.setLayoutParams(layoutparams);
                }
                if (mChangeToSelectBtn != null)
                {
                    android.widget.RelativeLayout.LayoutParams layoutparams1 = new android.widget.RelativeLayout.LayoutParams(-2, -2);
                    layoutparams1.addRule(13);
                    mChangeToSelectBtn.setLayoutParams(layoutparams1);
                }
                invalidate();
                return;
            }
        }
    }

    public void switchControlBar(int i)
    {
        mRemoteCtrlViewLayout.setVisibility(8);
        switch (i)
        {
        case 3: // '\003'
        default:
            return;

        case 0: // '\0'
            mDownloadAllBtn.setVisibility(8);
            mCancelAllBtn.setVisibility(8);
            mChangeToSelectBtn.setVisibility(8);
            mSelectViewLayout.setVisibility(8);
            mRemoteCtrlViewLayout.setVisibility(0);
            showBackToPreviewBtn(isShowPreviewBtn, true);
            return;

        case 1: // '\001'
            mCancelAllBtn.setVisibility(8);
            mDownloadAllBtn.setVisibility(8);
            mChangeToSelectBtn.setVisibility(0);
            mSelectViewLayout.setVisibility(8);
            showBackToPreviewBtn(isShowPreviewBtn, true);
            return;

        case 2: // '\002'
            mCancelAllBtn.setVisibility(8);
            mDownloadAllBtn.setVisibility(8);
            mChangeToSelectBtn.setVisibility(8);
            mSelectViewLayout.setVisibility(0);
            showBackToPreviewBtn(false, true);
            setAllBtnEnable(false);
            return;

        case 4: // '\004'
            mCancelAllBtn.setVisibility(0);
            break;
        }
        mDownloadAllBtn.setVisibility(0);
        mChangeToSelectBtn.setVisibility(8);
        mSelectViewLayout.setVisibility(8);
        showBackToPreviewBtn(false, true);
        setCancelAllBtnEnabled(((RemoteListViewGL)mListViewManager.getCurrentListView()).isFileDownloading());
    }





/*
    static long access$202(ControlBar controlbar, long l)
    {
        controlbar.mClickedTime = l;
        return l;
    }

*/





/*
    static ArrayList access$502(ControlBar controlbar, ArrayList arraylist)
    {
        controlbar.mSelectedPicData = arraylist;
        return arraylist;
    }

*/




/*
    static int access$702(ControlBar controlbar, int i)
    {
        controlbar.mImageSelectedCount = i;
        return i;
    }

*/


/*
    static int access$802(ControlBar controlbar, int i)
    {
        controlbar.mTotalSelectedCount = i;
        return i;
    }

*/

    // Unreferenced inner class com/arcsoft/mediaplus/ControlBar$2

/* anonymous class */
    class _cls2
        implements android.view.View.OnClickListener
    {

        final ControlBar this$0;

        public void onClick(View view)
        {
            if (mDevSelector == null)
            {
                mDevSelector = new RenderDevSelector((Activity)mContext);
            }
            mDevSelector.setDataSourceFilter(mListViewManager.getPlayDataSourceFilter());
            RenderDevSelector.CURRENT_PROVIDER_TYPE = com.arcsoft.mediaplus.dmc.DmcUtils.PROVIDER_TYPE.TYPE_FROM_MULTI_VIEW;
            boolean flag;
            if (getCurrentViewType() == 1)
            {
                flag = true;
            } else
            {
                flag = false;
            }
            RenderDevSelector.CURRENT_PROVIDER_FROM_LOCAL = flag;
            mDevSelector.start();
        }

            
            {
                this$0 = ControlBar.this;
                super();
            }
    }

}
