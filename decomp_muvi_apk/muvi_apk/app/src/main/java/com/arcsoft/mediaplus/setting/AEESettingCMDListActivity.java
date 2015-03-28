// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.arcsoft.mediaplus.setting;

import android.app.Activity;
import android.content.Context;
import android.content.res.Resources;
import android.graphics.Typeface;
import android.graphics.drawable.BitmapDrawable;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.Toast;
import com.arcsoft.mediaplus.listview.LoadingDialog;
import com.arcsoft.util.SystemUtils;
import com.arcsoft.util.debug.Log;
import com.arcsoft.util.os.NetworkTool;
import com.arcsoft.videostream.aee.AEEParameterMaps;
import com.arcsoft.videostream.aee.AEESocketClient;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;

public class AEESettingCMDListActivity extends Activity
{
    private class SettingCMDAdapter extends BaseAdapter
    {

        private final Context mContext;
        private final LayoutInflater mInflater;
        final AEESettingCMDListActivity this$0;

        private void setConfigSetting(ViewHolder viewholder, final int index)
        {
            Log.e("AEESettingCMDListActivity", (new StringBuilder()).append("setConfigSetting position = ").append(index).toString());
            if (mCurSettingValList != null && index < mCurSettingValList.length) goto _L2; else goto _L1
_L1:
            return;
_L2:
            int i;
            String s;
            String s1;
            i = mCurSettingValList[index];
            s = mSettingWholeList[i];
            s1 = s;
            Log.e("AEESettingCMDListActivity", (new StringBuilder()).append("setConfigSetting index = ").append(index).toString());
            Log.e("AEESettingCMDListActivity", (new StringBuilder()).append("setConfigSetting mCurSettingValList[").append(index).append("] = ").append(mCurSettingValList[index]).toString());
            Log.e("AEESettingCMDListActivity", (new StringBuilder()).append("setConfigSetting stringKey = ").append(s).toString());
            i;
            JVM INSTR lookupswitch 8: default 244
        //                       0: 631
        //                       9: 631
        //                       18: 631
        //                       33: 750
        //                       34: 750
        //                       35: 750
        //                       36: 925
        //                       37: 925;
               goto _L3 _L4 _L4 _L4 _L5 _L5 _L5 _L6 _L6
_L3:
            if (AEEParameterMaps.mAEESettingMap.containsKey(s))
            {
                s1 = getString(((Integer)AEEParameterMaps.mAEESettingMap.get(s)).intValue());
            }
            com.arcsoft.videostream.aee.AEESocketClient.ConfigDATA configdata = mRtspSocketClient.getSttingDataByKey(s);
            viewholder.title.setText(s1);
            viewholder.title.setTypeface(Typeface.defaultFromStyle(0));
            int i1 = (int)getResources().getDimension(0x7f0800e2);
            viewholder.title.setPadding(i1, 0, i1, 0);
            int j;
            String s2;
            android.widget.LinearLayout.LayoutParams layoutparams;
            android.widget.LinearLayout.LayoutParams layoutparams1;
            int k;
            SimpleDateFormat simpledateformat;
            int l;
            if (configdata != null && configdata.options.length == 2 && (configdata.stateVal.trim().equalsIgnoreCase("off") || configdata.stateVal.trim().equalsIgnoreCase("on")) && configdata.permission.contains("settable"))
            {
                android.widget.LinearLayout.LayoutParams layoutparams4 = (android.widget.LinearLayout.LayoutParams)viewholder.title.getLayoutParams();
                layoutparams4.weight = 1.0F;
                viewholder.title.setLayoutParams(layoutparams4);
                viewholder.item.setVisibility(8);
                viewholder.switcher.setVisibility(0);
                viewholder.switcher.setClickable(true);
                viewholder.switcher.setOnClickListener(configdata. new android.view.View.OnClickListener() {

                    final SettingCMDAdapter this$1;
                    final com.arcsoft.videostream.aee.AEESocketClient.ConfigDATA val$data;
                    final int val$index;

                    public void onClick(View view)
                    {
                        sendHandleWithTime(0x1100005, 0, null, 1, -1);
                        mCurCmd = index;
                        mCurVal = 1 - data.stateIndex;
                        String s = data.options[mCurVal];
                        sendHandleWithTime(0x1100002, 0, (new StringBuilder()).append(data.name).append(";").append(s).toString(), mCurCmd, mCurVal);
                    }

            
            {
                this$1 = final_settingcmdadapter;
                index = i;
                data = com.arcsoft.videostream.aee.AEESocketClient.ConfigDATA.this;
                super();
            }
                });
                setSwitcherVal(configdata.stateVal, viewholder.switcher);
                viewholder.layout.setClickable(true);
            } else
            {
                android.widget.LinearLayout.LayoutParams layoutparams2 = (android.widget.LinearLayout.LayoutParams)viewholder.title.getLayoutParams();
                layoutparams2.weight = 0.0F;
                viewholder.title.setLayoutParams(layoutparams2);
                android.widget.LinearLayout.LayoutParams layoutparams3 = (android.widget.LinearLayout.LayoutParams)viewholder.item.getLayoutParams();
                layoutparams3.weight = 1.0F;
                viewholder.item.setLayoutParams(layoutparams3);
                viewholder.item.setVisibility(0);
                viewholder.switcher.setVisibility(8);
                TextView textview = viewholder.item;
                String s3;
                if (configdata == null)
                {
                    s3 = "";
                } else
                {
                    s3 = getChoiceItemShowString(index, configdata.stateVal);
                }
                textview.setText(s3);
                if (configdata != null && configdata.permission.contains("settable"))
                {
                    viewholder.layout.setClickable(false);
                } else
                {
                    viewholder.layout.setClickable(true);
                }
            }
            if (configdata != null && s.equals("video_resolution"))
            {
                mCurVideoResolution = configdata.stateVal;
            } else
            if (configdata != null && s.equals("video_fov"))
            {
                mCurVideoFov = configdata.stateVal;
                if (!isResolution1080P(mCurVideoResolution) && !isFovWide(mCurVideoFov))
                {
                    sendHandleWithTime(0x1100005, 0, null, 1, -1);
                    sendHandleWithTime(0x1100002, 0, "video_fov;video_fov_wid", index, 0);
                }
            }
_L8:
            if (i == 9 || i == 18 || i == 0) goto _L1; else goto _L7
_L4:
            viewholder.item.setVisibility(8);
            viewholder.switcher.setVisibility(8);
            if (AEEParameterMaps.mAEESettingMap.containsKey(s))
            {
                s1 = getString(((Integer)AEEParameterMaps.mAEESettingMap.get(s)).intValue());
            }
            viewholder.title.setText(s1);
            viewholder.title.setTypeface(Typeface.defaultFromStyle(1));
            l = (int)getResources().getDimension(0x7f0800e1);
            viewholder.title.setPadding(l, 0, l, 0);
            viewholder.layout.setBackgroundDrawable(null);
            viewholder.layout.setClickable(false);
              goto _L8
_L5:
            if (i == 33)
            {
                viewholder.item.setVisibility(0);
                simpledateformat = new SimpleDateFormat("yyyy/MM/dd HH:mm");
                mCurSysTime = simpledateformat.format(new Date());
                viewholder.item.setText(mCurSysTime);
            } else
            {
                viewholder.item.setVisibility(8);
            }
            viewholder.switcher.setVisibility(8);
            if (AEEParameterMaps.mAEESettingMap.containsKey(s))
            {
                s1 = getString(((Integer)AEEParameterMaps.mAEESettingMap.get(s)).intValue());
            }
            viewholder.title.setText(s1);
            viewholder.title.setTypeface(Typeface.defaultFromStyle(0));
            k = (int)getResources().getDimension(0x7f0800e2);
            viewholder.title.setPadding(k, 0, k, 0);
            viewholder.layout.setClickable(false);
              goto _L8
_L6:
            if (AEEParameterMaps.mAEESettingMap.containsKey(s))
            {
                s1 = getString(((Integer)AEEParameterMaps.mAEESettingMap.get(s)).intValue());
            }
            viewholder.title.setText(s1);
            viewholder.title.setTypeface(Typeface.defaultFromStyle(0));
            j = (int)getResources().getDimension(0x7f0800e2);
            viewholder.title.setPadding(j, 0, j, 0);
            if (i == 36)
            {
                s2 = mStrVersion;
            } else
            {
                s2 = null;
                if (i == 37)
                {
                    s2 = mDeviceName;
                }
            }
            if (s2 != null && s2.length() > 0)
            {
                layoutparams = (android.widget.LinearLayout.LayoutParams)viewholder.title.getLayoutParams();
                layoutparams.weight = 0.0F;
                viewholder.title.setLayoutParams(layoutparams);
                layoutparams1 = (android.widget.LinearLayout.LayoutParams)viewholder.item.getLayoutParams();
                layoutparams1.weight = 1.0F;
                viewholder.item.setLayoutParams(layoutparams1);
                viewholder.item.setVisibility(0);
                viewholder.switcher.setVisibility(8);
                viewholder.item.setText(s2);
            } else
            {
                viewholder.item.setVisibility(8);
                viewholder.switcher.setVisibility(8);
            }
            viewholder.layout.setBackgroundResource(0x7f02024d);
            viewholder.layout.setClickable(true);
              goto _L8
              goto _L1
_L7:
            if (index == 1 + ((Integer)mTabPosition.get(Integer.valueOf(9))).intValue() || index == 1 + ((Integer)mTabPosition.get(Integer.valueOf(18))).intValue() || index == 1 + ((Integer)mTabPosition.get(Integer.valueOf(0))).intValue())
            {
                viewholder.layout.setBackgroundResource(0x7f020252);
                return;
            }
            if (index == -1 + ((Integer)mTabPosition.get(Integer.valueOf(9))).intValue() || index == -1 + ((Integer)mTabPosition.get(Integer.valueOf(18))).intValue() || index == -1 + ((Integer)mTabPosition.get(Integer.valueOf(0))).intValue() || index == -1 + mCurSettingValList.length)
            {
                viewholder.layout.setBackgroundResource(0x7f02024d);
                return;
            } else
            {
                viewholder.layout.setBackgroundResource(0x7f02024f);
                return;
            }
        }

        private void setDefaultSetting(ViewHolder viewholder, int i)
        {
            Log.e("AEESettingCMDListActivity", (new StringBuilder()).append("setDefaultSetting index = ").append(i).toString());
            if (mCurSettingValList == null || i >= mCurSettingValList.length)
            {
                return;
            }
            viewholder.item.setVisibility(8);
            viewholder.switcher.setVisibility(8);
            String s = mSettingWholeList[mCurSettingValList[i]];
            String s1 = s;
            if (AEEParameterMaps.mAEESettingMap.containsKey(s))
            {
                s1 = getString(((Integer)AEEParameterMaps.mAEESettingMap.get(s)).intValue());
            }
            viewholder.title.setText(s1);
            if (i == 9 || i == 18 || i == 0)
            {
                viewholder.title.setTypeface(Typeface.defaultFromStyle(1));
                int j = (int)getResources().getDimension(0x7f0800e1);
                viewholder.title.setPadding(j, 0, j, 0);
                viewholder.layout.setBackgroundDrawable(null);
            } else
            {
                viewholder.title.setTypeface(Typeface.defaultFromStyle(0));
                int k = (int)getResources().getDimension(0x7f0800e2);
                viewholder.title.setPadding(k, 0, k, 0);
                if (i == 10 || i == 19 || i == 1)
                {
                    viewholder.layout.setBackgroundResource(0x7f020252);
                } else
                if (i == 8 || i == 17 || i == -1 || i == -1 + mCurSettingValList.length)
                {
                    viewholder.layout.setBackgroundResource(0x7f02024d);
                } else
                {
                    viewholder.layout.setBackgroundResource(0x7f02024f);
                }
            }
            viewholder.layout.setClickable(false);
        }

        private ViewHolder setSettingView(ViewHolder viewholder, int i)
        {
            Log.e("AEESettingCMDListActivity", (new StringBuilder()).append("setSettingView position = ").append(i).append(" mRtspSocketClient.getSettingDataNum() = ").append(mRtspSocketClient.getSettingDataNum()).toString());
            Log.e("AEESettingCMDListActivity", (new StringBuilder()).append("setSettingView position = ").append(i).append(" mCurSettingValList.length() = ").append(mCurSettingValList.length).toString());
            Log.e("AEESettingCMDListActivity", (new StringBuilder()).append("setSettingView position = ").append(i).append(" mSettingWholeList.length() = ").append(mSettingWholeList.length).toString());
            try
            {
                mRtspSocketClient = AEESocketClient.getInstanceClient();
            }
            catch (IOException ioexception)
            {
                ioexception.printStackTrace();
            }
            if (mRtspSocketClient.getSettingDataNum() == 0 || mRtspSocketClient.getSettingDataNum() < -3 + mCurSettingValList.length)
            {
                setDefaultSetting(viewholder, i);
                return viewholder;
            } else
            {
                setConfigSetting(viewholder, i);
                return viewholder;
            }
        }

        public int getCount()
        {
            if (mCurSettingValList != null)
            {
                Log.e("AEESettingCMDListActivity", (new StringBuilder()).append("getCount mCurSettingValList.length = ").append(mCurSettingValList.length).toString());
                return mCurSettingValList.length;
            } else
            {
                return 0;
            }
        }

        public com.arcsoft.videostream.aee.AEESocketClient.ConfigDATA getItem(int i)
        {
            if (mCurSettingValList == null || i >= mCurSettingValList.length)
            {
                return null;
            }
            String s = mSettingWholeList[mCurSettingValList[i]];
            try
            {
                mRtspSocketClient = AEESocketClient.getInstanceClient();
            }
            catch (IOException ioexception)
            {
                ioexception.printStackTrace();
            }
            return mRtspSocketClient.getSttingDataByKey(s);
        }

        public volatile Object getItem(int i)
        {
            return getItem(i);
        }

        public long getItemId(int i)
        {
            return (long)i;
        }

        public View getView(int i, View view, ViewGroup viewgroup)
        {
            ViewHolder viewholder;
            if (view == null)
            {
                view = mInflater.inflate(0x7f030004, null);
                viewholder = new ViewHolder();
                viewholder.title = (TextView)view.findViewById(0x7f090019);
                viewholder.item = (TextView)view.findViewById(0x7f09001a);
                viewholder.switcher = (TextView)view.findViewById(0x7f09001d);
                viewholder.layout = (LinearLayout)view.findViewById(0x7f09001c);
                view.setTag(viewholder);
            } else
            {
                viewholder = (ViewHolder)view.getTag();
            }
            setSettingView(viewholder, i);
            return view;
        }

        public boolean isEnabled(int i)
        {
            return true;
        }

        public SettingCMDAdapter(Context context)
        {
            this$0 = AEESettingCMDListActivity.this;
            super();
            mContext = context;
            mInflater = LayoutInflater.from(context);
        }
    }

    private class UpdateAsyncTask extends AsyncTask
    {

        final AEESettingCMDListActivity this$0;

        protected transient Boolean doInBackground(Integer ainteger[])
        {
            boolean flag;
            mRtspSocketClient = AEESocketClient.getInstanceClient();
            if (!mRtspSocketClient.isConnected())
            {
                break MISSING_BLOCK_LABEL_323;
            }
            mRtspSocketClient.sendCommand(0x10000013, (String)null);
            if (!getRespond())
            {
                break MISSING_BLOCK_LABEL_309;
            }
            Log.e("AEESettingCMDListActivity", (new StringBuilder()).append("UpdateAsyncTask 2 respond = ").append(false).toString());
            mRtspSocketClient.sendCommand(0x10000034, mRequstStr);
            flag = getRespond();
            Log.e("AEESettingCMDListActivity", (new StringBuilder()).append("UpdateAsyncTask 3 respond = ").append(flag).toString());
            mRtspSocketClient.sendCommand(0x10000012, (String)null);
            getRespond();
            Log.e("AEESettingCMDListActivity", (new StringBuilder()).append("UpdateAsyncTask 4 respond = ").append(flag).toString());
            if (!flag)
            {
                break MISSING_BLOCK_LABEL_303;
            }
            mRtspSocketClient.sendCommand(0x1000001f, null);
            getRespond();
            Log.e("AEESettingCMDListActivity", (new StringBuilder()).append("UpdateAsyncTask 5 respond = ").append(flag).toString());
            sendHandleWithTime(0x1100001, 50, mRequstStr, ainteger[0].intValue(), ainteger[1].intValue());
            Log.e("AEESettingCMDListActivity", (new StringBuilder()).append("UpdateAsyncTask 5 params[0] = ").append(ainteger[0]).append(" params[1] = ").append(ainteger[1]).toString());
            return Boolean.valueOf(flag);
            Boolean boolean1 = Boolean.valueOf(false);
            return boolean1;
            IOException ioexception;
            ioexception;
            ioexception.printStackTrace();
            return Boolean.valueOf(false);
        }

        protected volatile Object doInBackground(Object aobj[])
        {
            return doInBackground((Integer[])aobj);
        }

        protected void onPostExecute(Boolean boolean1)
        {
            sendHandleWithTime(0x1100005, 0, null, 0, -1);
            if (!boolean1.booleanValue())
            {
                sendHandleWithTime(0x1100003, 0, null, 0x7f0b018e, -1);
            }
            super.onPostExecute(boolean1);
        }

        protected volatile void onPostExecute(Object obj)
        {
            onPostExecute((Boolean)obj);
        }

        private UpdateAsyncTask()
        {
            this$0 = AEESettingCMDListActivity.this;
            super();
        }
    }

    public final class ViewHolder
    {

        public TextView item;
        public LinearLayout layout;
        public TextView switcher;
        final AEESettingCMDListActivity this$0;
        public TextView title;

        public ViewHolder()
        {
            this$0 = AEESettingCMDListActivity.this;
            super();
        }
    }


    private static final int INDEX_CMD_DEFAULT = 35;
    private static final int INDEX_CMD_FORMAT = 34;
    private static final int INDEX_DEVICE_NAME = 37;
    private static final int INDEX_LANGUAGE = 32;
    private static final int INDEX_PHOTO_CAP_MODE = 11;
    private static final int INDEX_PHOTO_CONTINUE_FAST = 13;
    private static final int INDEX_PHOTO_LOOP = 15;
    private static final int INDEX_PHOTO_SELFTIMER = 16;
    private static final int INDEX_PHOTO_SHOT_MODE = 12;
    private static final int INDEX_PHOTO_SIZE = 10;
    private static final int INDEX_PHOTO_STAMP = 17;
    private static final int INDEX_PHOTO_TLM = 14;
    private static final int INDEX_RECORD_MODE = 1;
    private static final int INDEX_SETUP_DETECT_FACE = 25;
    private static final int INDEX_SETUP_IMAGE_WB = 27;
    private static final int INDEX_SETUP_KEY_TONE = 19;
    private static final int INDEX_SETUP_LOOP_BACK = 22;
    private static final int INDEX_SETUP_OSD = 21;
    private static final int INDEX_SETUP_POWEROFF = 23;
    private static final int INDEX_SETUP_SELFLAMP = 20;
    private static final int INDEX_SETUP_SYSTEM_TYPE = 24;
    private static final int INDEX_SETUP_TIME = 33;
    private static final int INDEX_SETUP_VERSION = 36;
    private static final int INDEX_SET_GSENSOR = 31;
    private static final int INDEX_SET_IMAGE_CONTRAST = 28;
    private static final int INDEX_SET_IMAGE_ISO = 29;
    private static final int INDEX_SET_LIGHT_MODE = 30;
    private static final int INDEX_SET_PROTUNE = 26;
    private static final int INDEX_TAB_PHOTO = 9;
    private static final int INDEX_TAB_SETUP = 18;
    private static final int INDEX_TAB_VIDEO = 0;
    private static final int INDEX_VIDEO_FLIP_ROTATE = 7;
    private static final int INDEX_VIDEO_FOV = 3;
    private static final int INDEX_VIDEO_QUALITY = 4;
    private static final int INDEX_VIDEO_RESOLUTION = 2;
    private static final int INDEX_VIDEO_SELFTIMER = 6;
    private static final int INDEX_VIDEO_STAMP = 8;
    private static final int INDEX_VIDEO_TIME_LAPSE = 5;
    private static final int INVILAIDVAL = -1;
    private static final int MSG_CMD_VAL_CHANGED = 0x1100001;
    private static final int MSG_CMD_VAL_UPDATE = 0x1100002;
    private static final int MSG_DISMISS_WAITING_DIALOG = 0x1100009;
    private static final int MSG_REFRESH_ADAPTER = 0x1100007;
    private static final int MSG_REQUEST_CONFIG = 0x1100006;
    private static final int MSG_SEND_CMD_SHORT = 0x1100004;
    private static final int MSG_SEND_COMMAND_FAILED = 0x1100008;
    private static final int MSG_SHOW_PROGRESS_VIEW = 0x1100005;
    private static final int MSG_SHOW_TOAST_SHORT = 0x1100003;
    private static final String RESOLUTION_1080P = "1920x1080";
    private static final String TAG = "AEESettingCMDListActivity";
    private static final int TIME_SEND_CMD_DELAY = 500;
    private static final int TYPE_AEE_DEVICE_S25W_VAL = 7;
    private static final int TYPE_AEE_DEVICE_S50_VAL = 2;
    private static final int TYPE_AEE_DEVICE_S51_VAL = 5;
    private static final int TYPE_AEE_DEVICE_S70_VAL = 1;
    private static final int TYPE_AEE_DEVICE_SD21W_VAL = 4;
    private static final int TYPE_AEE_DEVICE_SD22W_VAL = 3;
    private static final int TYPE_AEE_DEVICE_SD23W_VAL = 6;
    protected static final int TYPE_CONSHOT = 6;
    protected static final int TYPE_FASTSHOT = 5;
    protected static final int TYPE_PHOTO = 4;
    protected static final int TYPE_PLAYBACK = 9;
    protected static final int TYPE_ROLLOVER = 7;
    private static final String TYPE_TIME_DATE_N_TIME_STRING = "yyyy/MM/dd HH:mm";
    protected static final int TYPE_VIDEO = 3;
    protected static final int TYPE_VOICE = 8;
    private SettingCMDAdapter mAdapter;
    private int mCurCmd;
    private int mCurDeviceType;
    private int mCurExecuteStatus;
    private int mCurSettingValList[];
    private String mCurSysTime;
    private int mCurVal;
    private String mCurVideoFov;
    private String mCurVideoResolution;
    private String mDeviceName;
    private final Handler mHandler = new Handler() {

        final AEESettingCMDListActivity this$0;

        public void handleMessage(Message message)
        {
            super.handleMessage(message);
            message.what;
            JVM INSTR tableswitch 17825793 17825801: default 60
        //                       17825793 61
        //                       17825794 84
        //                       17825795 107
        //                       17825796 156
        //                       17825797 305
        //                       17825798 329
        //                       17825799 380
        //                       17825800 414
        //                       17825801 562;
               goto _L1 _L2 _L3 _L4 _L5 _L6 _L7 _L8 _L9 _L10
_L1:
            return;
_L2:
            onItemValChanged((String)message.obj, message.arg1, message.arg2);
            return;
_L3:
            onItemValUpdated((String)message.obj, message.arg1, message.arg2);
            return;
_L4:
            Toast.makeText(AEESettingCMDListActivity.this, message.arg1, 0).show();
            if (mUpdateWaitDialog != null && mUpdateWaitDialog.isShowing())
            {
                mUpdateWaitDialog.dismiss();
                return;
            }
            continue; /* Loop/switch isn't completed */
_L5:
            int k;
            int l;
            String s1;
            k = message.arg1;
            l = message.arg2;
            s1 = (String)message.obj;
            if (k == -1 && l == -1)
            {
                continue; /* Loop/switch isn't completed */
            }
            mRtspSocketClient = AEESocketClient.getInstanceClient();
            if (mRtspSocketClient == null || !mRtspSocketClient.isConnected())
            {
                continue; /* Loop/switch isn't completed */
            }
            if (k == -1)
            {
                try
                {
                    mRtspSocketClient.startRespondParams(l);
                    mRtspSocketClient.sendCommand(l, s1);
                    return;
                }
                catch (IOException ioexception1)
                {
                    ioexception1.printStackTrace();
                }
                return;
            }
            mRtspSocketClient.startRespondParams(k);
            mRtspSocketClient.sendCommand(k, s1);
            mRtspSocketClient.setNextCMD(l);
            return;
_L6:
            if (message.arg1 == 1)
            {
                showUpdateWaitDialog();
                return;
            } else
            {
                dmsUpdateWaitDialog();
                return;
            }
_L7:
            try
            {
                mRtspSocketClient = AEESocketClient.getInstanceClient();
                mRtspSocketClient.stopRespondParams();
                mRtspSocketClient.setOnRequestRespondsListener(null);
                mRtspSocketClient.requestConfig();
                return;
            }
            catch (IOException ioexception)
            {
                ioexception.printStackTrace();
            }
            return;
_L8:
            sendHandleWithTime(0x1100005, 0, null, 0, -1);
            if (mAdapter != null)
            {
                mAdapter.notifyDataSetChanged();
                return;
            }
            if (true) goto _L1; else goto _L9
_L9:
            String s;
            int i;
            int j;
            message.arg2;
            s = (String)message.obj;
            i = message.arg1;
            j = 0;
            i;
            JVM INSTR tableswitch 1 1: default 456
        //                       1 508;
               goto _L11 _L12
_L11:
            if (j != 0)
            {
                Toast.makeText(AEESettingCMDListActivity.this, j, 0).show();
            }
            if (mUpdateWaitDialog != null && mUpdateWaitDialog.isShowing())
            {
                mUpdateWaitDialog.dismiss();
                return;
            }
            break; /* Loop/switch isn't completed */
_L12:
            j = 0x7f0b018e;
            if (s != null && s.contains("16777218"))
            {
                j = 0x7f0b018d;
            } else
            if (s != null && s.contains("16777220"))
            {
                j = 0x7f0b0198;
            }
            mResultCode = j;
            if (true) goto _L11; else goto _L13
_L13:
            if (false)
            {
            }
            continue; /* Loop/switch isn't completed */
_L10:
            if (mUpdateWaitDialog != null && mUpdateWaitDialog.isShowing())
            {
                mUpdateWaitDialog.dismiss();
                return;
            }
            if (true) goto _L1; else goto _L14
_L14:
        }

            
            {
                this$0 = AEESettingCMDListActivity.this;
                super();
            }
    };
    private ListView mListView;
    private final com.arcsoft.util.os.NetworkTool.IOnConnectivityChangeListener mNetworkConnectivityListener = new com.arcsoft.util.os.NetworkTool.IOnConnectivityChangeListener() {

        final AEESettingCMDListActivity this$0;

        public void OnConnectivityChanged(com.arcsoft.util.os.NetworkTool.NetworkStateInfo networkstateinfo)
        {
            while (networkstateinfo.networkInfo == null || networkstateinfo.networkInfo.getType() != 1 || networkstateinfo.networkInfo.isConnected()) 
            {
                return;
            }
            sendHandleWithTime(0x1100003, 0, null, 0x7f0b0059, -1);
            finish();
        }

            
            {
                this$0 = AEESettingCMDListActivity.this;
                super();
            }
    };
    private final com.arcsoft.util.os.NetworkTool.IOnSettingChangeListener mNetworkSettingListener = new com.arcsoft.util.os.NetworkTool.IOnSettingChangeListener() {

        final AEESettingCMDListActivity this$0;

        public void OnBackgroundDataSettingChanged()
        {
            Log.e("FENG", "FENG OnBackgroundDataSettingChanged");
        }

            
            {
                this$0 = AEESettingCMDListActivity.this;
                super();
            }
    };
    private NetworkTool mNetworkTool;
    private final android.view.View.OnClickListener mOnClickListener = new android.view.View.OnClickListener() {

        final AEESettingCMDListActivity this$0;

        public void onClick(View view)
        {
            view.getId();
            JVM INSTR tableswitch 2131296481 2131296484: default 36
        //                       2131296481 59
        //                       2131296482 223
        //                       2131296483 36
        //                       2131296484 37;
               goto _L1 _L2 _L3 _L1 _L4
_L1:
            return;
_L4:
            setResult(mResultCode);
            finish();
            return;
_L2:
            if (mCurSettingValList == null || mCurCmd >= mCurSettingValList.length)
            {
                continue; /* Loop/switch isn't completed */
            }
            mCurSettingValList[mCurCmd];
            JVM INSTR tableswitch 33 35: default 128
        //                       33 175
        //                       34 149
        //                       35 162;
               goto _L5 _L6 _L7 _L8
_L5:
            break; /* Loop/switch isn't completed */
_L7:
            break; /* Loop/switch isn't completed */
_L10:
            if (mPopupWindow != null)
            {
                mPopupWindow.dismiss();
                return;
            }
            if (true) goto _L1; else goto _L9
_L9:
            sendCommandOther(0x1000000f, null);
              goto _L10
_L8:
            sendCommandOther(0x10000010, null);
              goto _L10
_L6:
            SimpleDateFormat simpledateformat = new SimpleDateFormat("yyyy/MM/dd HH:mm");
            mCurSysTime = simpledateformat.format(new Date());
            sendCommandOther(0x10000039, mCurSysTime);
              goto _L10
_L3:
            if (mPopupWindow != null)
            {
                mPopupWindow.dismiss();
                return;
            }
            if (true) goto _L1; else goto _L11
_L11:
        }

            
            {
                this$0 = AEESettingCMDListActivity.this;
                super();
            }
    };
    private final android.widget.AdapterView.OnItemClickListener mOnItemClickListener = new android.widget.AdapterView.OnItemClickListener() {

        final AEESettingCMDListActivity this$0;

        public void onItemClick(AdapterView adapterview, View view, int i, long l)
        {
            Log.e("AEESettingCMDListActivity", (new StringBuilder()).append("onItemClick position = ").append(i).append(" isClickable = ").append(view.isClickable()).toString());
            if (mCurSettingValList != null && i < mCurSettingValList.length) goto _L2; else goto _L1
_L1:
            return;
_L2:
            mCurSettingValList[i];
            JVM INSTR tableswitch 33 35: default 96
        //                       33 187
        //                       34 121
        //                       35 154;
               goto _L3 _L4 _L5 _L6
_L3:
            if (!view.isClickable())
            {
                mCurCmd = i;
                showChoiceCmdView(i);
                return;
            }
              goto _L7
_L5:
            showAlertDialog(i, getResources().getString(0x7f0b01a5), getResources().getString(0x7f0b01a6));
            return;
_L6:
            showAlertDialog(i, getResources().getString(0x7f0b01a5), getResources().getString(0x7f0b01a7));
            return;
_L4:
            showAlertDialog(i, getResources().getString(0x7f0b01a5), getResources().getString(0x7f0b01a8));
            if (true) goto _L3; else goto _L7
_L7:
            if (true) goto _L1; else goto _L8
_L8:
        }

            
            {
                this$0 = AEESettingCMDListActivity.this;
                super();
            }
    };
    private PopupWindow mPopupWindow;
    private final com.arcsoft.videostream.aee.AEESocketClient.OnRequestConfigListener mRequestConfigListener = new com.arcsoft.videostream.aee.AEESocketClient.OnRequestConfigListener() {

        final AEESettingCMDListActivity this$0;

        public void onRequestConfigFinished(String s)
        {
            Log.e("AEESettingCMDListActivity", (new StringBuilder()).append("onRequestConfigFinished configStr = ").append(s).toString());
            try
            {
                mRtspSocketClient = AEESocketClient.getInstanceClient();
                mRtspSocketClient.stopRequestConfig();
                mRtspSocketClient.setOnRequestRespondsListener(mRequestRespondsListener);
                mRtspSocketClient.startRespondParams(-1);
            }
            catch (IOException ioexception)
            {
                ioexception.printStackTrace();
                dismissWaitingDialog();
            }
            if (s != null)
            {
                sendCommandOther(0x10000027, null);
                sendHandleWithTime(0x1100007, 0, null, -1, -1);
                return;
            } else
            {
                sendHandleWithTime(0x1100005, 0, null, 0, -1);
                sendHandleWithTime(0x1100003, 0, null, 0x7f0b0160, -1);
                finish();
                return;
            }
        }

            
            {
                this$0 = AEESettingCMDListActivity.this;
                super();
            }
    };
    private final com.arcsoft.videostream.aee.AEESocketClient.OnRequestRespondsListener mRequestRespondsListener = new com.arcsoft.videostream.aee.AEESocketClient.OnRequestRespondsListener() {

        final AEESettingCMDListActivity this$0;

        public void onRequestRespondsFinished(int i, String s, int j, String s1, int k, String s2)
        {
            int l;
            Log.e("AEESettingCMDListActivity", (new StringBuilder()).append("onRequestRespondsFinished: curCmdType = ").append(i).append(" num = ").append(j).append(", param = ").append(s1).append(" respond = ").append(s).append(" others = ").append(s2).toString());
            l = -1;
            i;
            JVM INSTR lookupswitch 7: default 132
        //                       268435471: 913
        //                       268435472: 980
        //                       268435474: 631
        //                       268435475: 173
        //                       268435487: 726
        //                       268435495: 777
        //                       268435508: 268;
               goto _L1 _L2 _L3 _L4 _L5 _L6 _L7 _L8
_L1:
            if (j != 0)
            {
                Log.e("testP", (new StringBuilder()).append("sendCommandFailed curCmdType = ").append(i).toString());
                sendCommandFailed(j, i, s1);
            }
_L12:
            return;
_L5:
            int k1;
            mRtspSocketClient = AEESocketClient.getInstanceClient();
            k1 = mRtspSocketClient.getNextCMD();
            if (k1 != 0x1100006)
            {
                break MISSING_BLOCK_LABEL_255;
            }
            synchronized (mHandler)
            {
                mHandler.sendEmptyMessage(0x1100006);
            }
            return;
            exception1;
            handler1;
            JVM INSTR monitorexit ;
            try
            {
                throw exception1;
            }
            catch (IOException ioexception3)
            {
                ioexception3.printStackTrace();
            }
            dismissWaitingDialog();
            return;
            sendHandleCMD(k1, -1, null, 0L);
            return;
_L8:
            int j1;
            mRtspSocketClient = AEESocketClient.getInstanceClient();
            j1 = mRtspSocketClient.getNextCMD();
            l = j1;
_L10:
            if (j != 0)
            {
                break; /* Loop/switch isn't completed */
            }
            sendHandleCMD(l, -1, null, 0L);
            if (mRequstStr.contains("video_fov"))
            {
                AEESocketClient aeesocketclient5 = mRtspSocketClient;
                if (s1 == null)
                {
                    s1 = mSelectedValue;
                }
                aeesocketclient5.setDVInfo(13, s1);
                return;
            }
            break; /* Loop/switch isn't completed */
            IOException ioexception2;
            ioexception2;
            ioexception2.printStackTrace();
            dismissWaitingDialog();
            if (true) goto _L10; else goto _L9
_L9:
            if (mRequstStr.contains("video_resolution"))
            {
                AEESocketClient aeesocketclient4 = mRtspSocketClient;
                if (s1 == null)
                {
                    s1 = mSelectedValue;
                }
                aeesocketclient4.setDVInfo(12, s1);
                return;
            }
            if (mRequstStr.contains("photo_size"))
            {
                AEESocketClient aeesocketclient3 = mRtspSocketClient;
                if (s1 == null)
                {
                    s1 = mSelectedValue;
                }
                aeesocketclient3.setDVInfo(14, s1);
                return;
            }
            if (mRequstStr.contains("photo_shot_mode"))
            {
                AEESocketClient aeesocketclient2 = mRtspSocketClient;
                if (s1 == null)
                {
                    s1 = mSelectedValue;
                }
                aeesocketclient2.setDVInfo(15, s1);
                return;
            }
            if (mRequstStr.contains("photo_continue_fast"))
            {
                AEESocketClient aeesocketclient1 = mRtspSocketClient;
                if (s1 == null)
                {
                    s1 = mSelectedValue;
                }
                aeesocketclient1.setDVInfo(16, s1);
                return;
            }
            if (mRequstStr.contains("photo_tlm"))
            {
                AEESocketClient aeesocketclient = mRtspSocketClient;
                if (s1 == null)
                {
                    s1 = mSelectedValue;
                }
                aeesocketclient.setDVInfo(17, s1);
                return;
            }
            if (true) goto _L12; else goto _L11
_L11:
            sendHandleWithTime(0x1100003, 0, null, 0x7f0b018e, -1);
            return;
_L4:
            int i1;
            mRtspSocketClient = AEESocketClient.getInstanceClient();
            i1 = mRtspSocketClient.getNextCMD();
            if (i1 != 0x1100006)
            {
                break MISSING_BLOCK_LABEL_713;
            }
            synchronized (mHandler)
            {
                mHandler.sendEmptyMessage(0x1100006);
            }
            return;
            exception;
            handler;
            JVM INSTR monitorexit ;
            try
            {
                throw exception;
            }
            catch (IOException ioexception1)
            {
                ioexception1.printStackTrace();
            }
            dismissWaitingDialog();
            return;
            sendHandleCMD(i1, -1, null, 0L);
            return;
_L6:
            sendHandleWithTime(0x1100001, 50, mRequstStr, mCurCmd, mCurVal);
            if (j != 0)
            {
                sendHandleWithTime(0x1100003, 0, null, 0x7f0b018e, -1);
                return;
            }
            continue; /* Loop/switch isn't completed */
_L7:
            if (j == 0 && s1 != null)
            {
                String as[] = s1.split(";");
                String s3 = as[-2 + as.length];
                String as1[] = as[-1 + as.length].split(":");
                String s4 = as1[-1 + as1.length];
                mDeviceName = s3;
                mStrVersion = s4;
                Log.v("zdf", (new StringBuilder()).append("onRequestRespondsFinished: mDeviceName = ").append(mDeviceName).append(", mStrVersion = ").append(mStrVersion).toString());
                sendHandleWithTime(0x1100007, 200, null, -1, -1);
                return;
            }
            continue; /* Loop/switch isn't completed */
_L2:
            if (j == 0 || j == 2)
            {
                try
                {
                    mRtspSocketClient = AEESocketClient.getInstanceClient();
                    mRtspSocketClient.setIsNeedFreshFiles(true);
                }
                catch (IOException ioexception)
                {
                    ioexception.printStackTrace();
                    dismissWaitingDialog();
                }
            }
            if (j != 0)
            {
                sendHandleWithTime(0x1100003, 0, null, 0x7f0b018e, -1);
                return;
            }
            continue; /* Loop/switch isn't completed */
_L3:
            if (j != 0)
            {
                sendHandleWithTime(0x1100003, 0, null, 0x7f0b018e, -1);
                return;
            }
            if (true) goto _L12; else goto _L13
_L13:
        }

            
            {
                this$0 = AEESettingCMDListActivity.this;
                super();
            }
    };
    private String mRequstStr;
    private int mResultCode;
    private AEESocketClient mRtspSocketClient;
    private String mSelectedValue;
    private int mSettingValListS25W[];
    private int mSettingValListS50[];
    private int mSettingValListS51[];
    private int mSettingValListS70[];
    private int mSettingValListSD21W[];
    private int mSettingValListSD22W[];
    private int mSettingValListSD23W[];
    private String mSettingWholeList[];
    private String mStrVersion;
    private HashMap mTabPosition;
    private UpdateAsyncTask mUpdateAsyncTask;
    private LoadingDialog mUpdateWaitDialog;

    public AEESettingCMDListActivity()
    {
        mListView = null;
        mAdapter = null;
        mPopupWindow = null;
        mCurVal = -1;
        mCurCmd = -1;
        mRtspSocketClient = null;
        mRequstStr = null;
        mUpdateAsyncTask = null;
        mUpdateWaitDialog = null;
        mSettingWholeList = null;
        mSettingValListS70 = null;
        mSettingValListS50 = null;
        mSettingValListSD22W = null;
        mSettingValListSD21W = null;
        mSettingValListS51 = null;
        mSettingValListSD23W = null;
        mSettingValListS25W = null;
        mCurSettingValList = null;
        mCurDeviceType = -1;
        mDeviceName = null;
        mStrVersion = null;
        mCurSysTime = null;
        mTabPosition = null;
        mResultCode = -1;
        mCurVideoResolution = null;
        mCurVideoFov = null;
        mSelectedValue = null;
        mNetworkTool = null;
        mCurExecuteStatus = -1;
    }

    private void dismissWaitingDialog()
    {
        synchronized (mHandler)
        {
            if (mHandler != null)
            {
                Message message = mHandler.obtainMessage(0x1100009);
                mHandler.removeMessages(0x1100009);
                mHandler.sendMessage(message);
            }
        }
        return;
        exception;
        handler;
        JVM INSTR monitorexit ;
        throw exception;
    }

    private void dmsUpdateWaitDialog()
    {
        if (mUpdateWaitDialog != null)
        {
            mUpdateWaitDialog.dismiss();
        }
    }

    private String getChoiceItemShowString(int i, String s)
    {
        Log.e("AEESettingCMDListActivity", (new StringBuilder()).append("getChoiceItemShowString index = ").append(i).append(" cmdKey = ").append(s).toString());
        if (mCurSettingValList != null && i < mCurSettingValList.length) goto _L2; else goto _L1
_L1:
        s = null;
_L34:
        return s;
_L2:
        int j;
        String s1;
        Log.e("AEESettingCMDListActivity", (new StringBuilder()).append("getChoiceItemShowString mCurSettingValList[itemIndex] = ").append(mCurSettingValList[i]).toString());
        j = mCurSettingValList[i];
        s1 = null;
        j;
        JVM INSTR tableswitch 1 32: default 244
    //                   1 252
    //                   2 291
    //                   3 330
    //                   4 369
    //                   5 408
    //                   6 447
    //                   7 486
    //                   8 525
    //                   9 244
    //                   10 564
    //                   11 603
    //                   12 642
    //                   13 244
    //                   14 681
    //                   15 720
    //                   16 759
    //                   17 798
    //                   18 244
    //                   19 837
    //                   20 876
    //                   21 915
    //                   22 954
    //                   23 993
    //                   24 1032
    //                   25 1071
    //                   26 1110
    //                   27 1149
    //                   28 1188
    //                   29 1227
    //                   30 1266
    //                   31 1305
    //                   32 1344;
           goto _L3 _L4 _L5 _L6 _L7 _L8 _L9 _L10 _L11 _L3 _L12 _L13 _L14 _L3 _L15 _L16 _L17 _L18 _L3 _L19 _L20 _L21 _L22 _L23 _L24 _L25 _L26 _L27 _L28 _L29 _L30 _L31 _L32
_L32:
        break MISSING_BLOCK_LABEL_1344;
_L3:
        break; /* Loop/switch isn't completed */
_L4:
        break; /* Loop/switch isn't completed */
_L35:
        if (s1 != null)
        {
            return s1;
        }
        if (true) goto _L34; else goto _L33
_L33:
        boolean flag28 = AEEParameterMaps.mAEERecordModeMap.containsKey(s);
        s1 = null;
        if (flag28)
        {
            s1 = getString(((Integer)AEEParameterMaps.mAEERecordModeMap.get(s)).intValue());
        }
          goto _L35
_L5:
        boolean flag27 = AEEParameterMaps.mAEEVideoResolutionMap.containsKey(s);
        s1 = null;
        if (flag27)
        {
            s1 = getString(((Integer)AEEParameterMaps.mAEEVideoResolutionMap.get(s)).intValue());
        }
          goto _L35
_L6:
        boolean flag26 = AEEParameterMaps.mAEEVideoFovMap.containsKey(s);
        s1 = null;
        if (flag26)
        {
            s1 = getString(((Integer)AEEParameterMaps.mAEEVideoFovMap.get(s)).intValue());
        }
          goto _L35
_L7:
        boolean flag25 = AEEParameterMaps.mAEEVideoQualityMap.containsKey(s);
        s1 = null;
        if (flag25)
        {
            s1 = getString(((Integer)AEEParameterMaps.mAEEVideoQualityMap.get(s)).intValue());
        }
          goto _L35
_L8:
        boolean flag24 = AEEParameterMaps.mAEEVideoTimeLapseMap.containsKey(s);
        s1 = null;
        if (flag24)
        {
            s1 = getString(((Integer)AEEParameterMaps.mAEEVideoTimeLapseMap.get(s)).intValue());
        }
          goto _L35
_L9:
        boolean flag23 = AEEParameterMaps.mAEEVideoSelfTimerMap.containsKey(s);
        s1 = null;
        if (flag23)
        {
            s1 = getString(((Integer)AEEParameterMaps.mAEEVideoSelfTimerMap.get(s)).intValue());
        }
          goto _L35
_L10:
        boolean flag22 = AEEParameterMaps.mAEEVideoFlipRotateMap.containsKey(s);
        s1 = null;
        if (flag22)
        {
            s1 = getString(((Integer)AEEParameterMaps.mAEEVideoFlipRotateMap.get(s)).intValue());
        }
          goto _L35
_L11:
        boolean flag21 = AEEParameterMaps.mAEEVideoStampMap.containsKey(s);
        s1 = null;
        if (flag21)
        {
            s1 = getString(((Integer)AEEParameterMaps.mAEEVideoStampMap.get(s)).intValue());
        }
          goto _L35
_L12:
        boolean flag20 = AEEParameterMaps.mAEEPhotoSizeMap.containsKey(s);
        s1 = null;
        if (flag20)
        {
            s1 = getString(((Integer)AEEParameterMaps.mAEEPhotoSizeMap.get(s)).intValue());
        }
          goto _L35
_L13:
        boolean flag19 = AEEParameterMaps.mAEEPhotoCapModeMap.containsKey(s);
        s1 = null;
        if (flag19)
        {
            s1 = getString(((Integer)AEEParameterMaps.mAEEPhotoCapModeMap.get(s)).intValue());
        }
          goto _L35
_L14:
        boolean flag18 = AEEParameterMaps.mAEEPhotoShotModeMap.containsKey(s);
        s1 = null;
        if (flag18)
        {
            s1 = getString(((Integer)AEEParameterMaps.mAEEPhotoShotModeMap.get(s)).intValue());
        }
          goto _L35
_L15:
        boolean flag17 = AEEParameterMaps.mAEEPhotoTlmMap.containsKey(s);
        s1 = null;
        if (flag17)
        {
            s1 = getString(((Integer)AEEParameterMaps.mAEEPhotoTlmMap.get(s)).intValue());
        }
          goto _L35
_L16:
        boolean flag16 = AEEParameterMaps.mAEEPhotoLoopMap.containsKey(s);
        s1 = null;
        if (flag16)
        {
            s1 = getString(((Integer)AEEParameterMaps.mAEEPhotoLoopMap.get(s)).intValue());
        }
          goto _L35
_L17:
        boolean flag15 = AEEParameterMaps.mAEEPhotoSelfTimerMap.containsKey(s);
        s1 = null;
        if (flag15)
        {
            s1 = getString(((Integer)AEEParameterMaps.mAEEPhotoSelfTimerMap.get(s)).intValue());
        }
          goto _L35
_L18:
        boolean flag14 = AEEParameterMaps.mAEEPhotoStampMap.containsKey(s);
        s1 = null;
        if (flag14)
        {
            s1 = getString(((Integer)AEEParameterMaps.mAEEPhotoStampMap.get(s)).intValue());
        }
          goto _L35
_L19:
        boolean flag13 = AEEParameterMaps.mAEESetupKeyToneMap.containsKey(s);
        s1 = null;
        if (flag13)
        {
            s1 = getString(((Integer)AEEParameterMaps.mAEESetupKeyToneMap.get(s)).intValue());
        }
          goto _L35
_L20:
        boolean flag12 = AEEParameterMaps.mAEESetupselflampeMap.containsKey(s);
        s1 = null;
        if (flag12)
        {
            s1 = getString(((Integer)AEEParameterMaps.mAEESetupselflampeMap.get(s)).intValue());
        }
          goto _L35
_L21:
        boolean flag11 = AEEParameterMaps.mAEESetupOsdMap.containsKey(s);
        s1 = null;
        if (flag11)
        {
            s1 = getString(((Integer)AEEParameterMaps.mAEESetupOsdMap.get(s)).intValue());
        }
          goto _L35
_L22:
        boolean flag10 = AEEParameterMaps.mAEESetupLoopBackMap.containsKey(s);
        s1 = null;
        if (flag10)
        {
            s1 = getString(((Integer)AEEParameterMaps.mAEESetupLoopBackMap.get(s)).intValue());
        }
          goto _L35
_L23:
        boolean flag9 = AEEParameterMaps.mAEESetupPowerOffMap.containsKey(s);
        s1 = null;
        if (flag9)
        {
            s1 = getString(((Integer)AEEParameterMaps.mAEESetupPowerOffMap.get(s)).intValue());
        }
          goto _L35
_L24:
        boolean flag8 = AEEParameterMaps.mAEESetupSystemTypeMap.containsKey(s);
        s1 = null;
        if (flag8)
        {
            s1 = getString(((Integer)AEEParameterMaps.mAEESetupSystemTypeMap.get(s)).intValue());
        }
          goto _L35
_L25:
        boolean flag7 = AEEParameterMaps.mAEESetupSystemTypeMap.containsKey(s);
        s1 = null;
        if (flag7)
        {
            s1 = getString(((Integer)AEEParameterMaps.mAEESetupDetectFaceMap.get(s)).intValue());
        }
          goto _L35
_L26:
        boolean flag6 = AEEParameterMaps.mAEESetupProtuneMap.containsKey(s);
        s1 = null;
        if (flag6)
        {
            s1 = getString(((Integer)AEEParameterMaps.mAEESetupProtuneMap.get(s)).intValue());
        }
          goto _L35
_L27:
        boolean flag5 = AEEParameterMaps.mAEESetupImageWbMap.containsKey(s);
        s1 = null;
        if (flag5)
        {
            s1 = getString(((Integer)AEEParameterMaps.mAEESetupImageWbMap.get(s)).intValue());
        }
          goto _L35
_L28:
        boolean flag4 = AEEParameterMaps.mAEESetupImageContrastMap.containsKey(s);
        s1 = null;
        if (flag4)
        {
            s1 = getString(((Integer)AEEParameterMaps.mAEESetupImageContrastMap.get(s)).intValue());
        }
          goto _L35
_L29:
        boolean flag3 = AEEParameterMaps.mAEESetupImageISOMap.containsKey(s);
        s1 = null;
        if (flag3)
        {
            s1 = getString(((Integer)AEEParameterMaps.mAEESetupImageISOMap.get(s)).intValue());
        }
          goto _L35
_L30:
        boolean flag2 = AEEParameterMaps.mAEESetupLightModeMap.containsKey(s);
        s1 = null;
        if (flag2)
        {
            s1 = getString(((Integer)AEEParameterMaps.mAEESetupLightModeMap.get(s)).intValue());
        }
          goto _L35
_L31:
        boolean flag1 = AEEParameterMaps.mAEESetupGesensorMap.containsKey(s);
        s1 = null;
        if (flag1)
        {
            s1 = getString(((Integer)AEEParameterMaps.mAEESetupGesensorMap.get(s)).intValue());
        }
          goto _L35
        boolean flag = AEEParameterMaps.mAEELanguageMap.containsKey(s);
        s1 = null;
        if (flag)
        {
            s1 = getString(((Integer)AEEParameterMaps.mAEELanguageMap.get(s)).intValue());
        }
          goto _L35
    }

    private void initCurSettingList()
    {
        String s;
        try
        {
            mRtspSocketClient = AEESocketClient.getInstanceClient();
        }
        catch (IOException ioexception)
        {
            ioexception.printStackTrace();
        }
        if (-1 != mCurDeviceType) goto _L2; else goto _L1
_L1:
        s = mRtspSocketClient.getDVInfo(18);
        if (s == null || s.length() == 0)
        {
            s = mRtspSocketClient.getDVInfo(11);
            if (s == null || s.length() == 0)
            {
                mCurDeviceType = 2;
            }
        }
        if (!s.contains("S50") && !s.contains("S60") && !s.contains("S70")) goto _L4; else goto _L3
_L3:
        mCurDeviceType = 2;
_L2:
        Log.e("AEESettingCMDListActivity", (new StringBuilder()).append("initCurSettingList mCurDeviceType = ").append(mCurDeviceType).toString());
        if (-1 == mCurDeviceType) goto _L6; else goto _L5
_L5:
        mCurDeviceType;
        JVM INSTR tableswitch 1 7: default 188
    //                   1 416
    //                   2 434
    //                   3 452
    //                   4 470
    //                   5 488
    //                   6 506
    //                   7 524;
           goto _L7 _L8 _L9 _L10 _L11 _L12 _L13 _L14
_L7:
        if (mCurSettingValList != null)
        {
            if (mTabPosition == null)
            {
                mTabPosition = new HashMap();
            }
            mTabPosition.clear();
            for (int i = 0; i < mCurSettingValList.length; i++)
            {
                if (mCurSettingValList[i] == 9 || mCurSettingValList[i] == 0 || mCurSettingValList[i] == 18)
                {
                    mTabPosition.put(Integer.valueOf(mCurSettingValList[i]), Integer.valueOf(i));
                }
            }

        }
        break; /* Loop/switch isn't completed */
_L4:
        if (s.contains("SD22W") || s.contains("Navcam"))
        {
            mCurDeviceType = 3;
        } else
        if (s.contains("SD21W"))
        {
            mCurDeviceType = 4;
        } else
        if (s.contains("S51"))
        {
            mCurDeviceType = 2;
        } else
        if (s.contains("SD23W") || s.contains("5S"))
        {
            mCurDeviceType = 6;
        } else
        if (s.contains("S25W"))
        {
            mCurDeviceType = 7;
        }
        continue; /* Loop/switch isn't completed */
_L8:
        if (mSettingValListS70 != null)
        {
            mCurSettingValList = mSettingValListS70;
        }
        continue; /* Loop/switch isn't completed */
_L9:
        if (mSettingValListS50 != null)
        {
            mCurSettingValList = mSettingValListS50;
        }
        continue; /* Loop/switch isn't completed */
_L10:
        if (mSettingValListSD22W != null)
        {
            mCurSettingValList = mSettingValListSD22W;
        }
        continue; /* Loop/switch isn't completed */
_L11:
        if (mSettingValListSD21W != null)
        {
            mCurSettingValList = mSettingValListSD21W;
        }
        continue; /* Loop/switch isn't completed */
_L12:
        if (mSettingValListS51 != null)
        {
            mCurSettingValList = mSettingValListS51;
        }
        continue; /* Loop/switch isn't completed */
_L13:
        if (mSettingValListSD23W != null)
        {
            mCurSettingValList = mSettingValListSD23W;
        }
        continue; /* Loop/switch isn't completed */
_L14:
        if (mSettingValListS25W != null)
        {
            mCurSettingValList = mSettingValListS25W;
        }
        if (true) goto _L7; else goto _L6
_L6:
        return;
        if (true) goto _L2; else goto _L15
_L15:
    }

    private void initDefault()
    {
        ((TextView)findViewById(0x7f090007)).setText(getString(0x7f0b01d5));
        if (mSettingWholeList == null)
        {
            mSettingWholeList = (new String[] {
                "tab_video", "record_mode", "video_resolution", "video_fov", "video_quality", "video_time_lapse", "video_selftimer", "video_flip_rotate", "video_stamp", "tab_photo", 
                "photo_size", "photo_cap_mode", "photo_shot_mode", "photo_continue_fast", "photo_tlm", "photo_loop", "photo_selftimer", "photo_stamp", "tab_setup", "setup_key_tone", 
                "setup_selflamp", "setup_osd", "setup_loop_back", "setup_poweroff", "setup_system_type", "setup_detect_face", "set_protune", "setup_image_wb", "set_image_contrast", "set_image_iso", 
                "set_light_mode", "set_gsensor", "language", "setup_time", "format sdcard", "reset default setting", "version", "device_name"
            });
        }
        if (mSettingValListS70 == null)
        {
            mSettingValListS70 = (new int[] {
                0, 2, 3, 4, 9, 10, 12, 13, 14, 15, 
                18, 19, 20, 22, 24, 25, 26, 27, 32, 33, 
                34, 36, 37
            });
        }
        if (mSettingValListS50 == null)
        {
            mSettingValListS50 = (new int[] {
                0, 2, 3, 4, 9, 10, 12, 14, 18, 19, 
                20, 22, 24, 32, 33, 34, 36, 37
            });
        }
        if (mSettingValListSD22W == null)
        {
            mSettingValListSD22W = (new int[] {
                0, 2, 3, 4, 9, 10, 12, 14, 18, 19, 
                20, 22, 24, 28, 29, 30, 32, 33, 34, 36, 
                37
            });
        }
        if (mSettingValListSD21W == null)
        {
            mSettingValListSD21W = (new int[] {
                0, 2, 3, 4, 9, 10, 12, 14, 18, 19, 
                20, 22, 24, 28, 29, 30, 32, 33, 34, 36, 
                37
            });
        }
        if (mSettingValListS51 == null)
        {
            mSettingValListS51 = (new int[] {
                0, 2, 3, 4, 9, 10, 12, 14, 18, 19, 
                20, 22, 24, 26, 27, 32, 33, 34, 36, 37
            });
        }
        if (mSettingValListSD23W == null)
        {
            mSettingValListSD23W = (new int[] {
                0, 2, 3, 4, 9, 10, 12, 14, 18, 19, 
                20, 22, 24, 28, 29, 30, 32, 33, 34, 36, 
                37
            });
        }
        if (mSettingValListS25W == null)
        {
            mSettingValListS25W = (new int[] {
                0, 2, 3, 4, 9, 10, 12, 14, 18, 19, 
                20, 22, 24, 26, 27, 28, 29, 30, 32, 33, 
                34, 36, 37
            });
        }
        initCurSettingList();
    }

    private void initList()
    {
        mListView = (ListView)findViewById(0x7f0900e5);
        mListView.setDivider(null);
        mListView.setBackgroundDrawable(null);
        mAdapter = new SettingCMDAdapter(getApplicationContext());
        mListView.setAdapter(mAdapter);
        mListView.setOnItemClickListener(mOnItemClickListener);
    }

    private void initSocket()
    {
        try
        {
            mRtspSocketClient = AEESocketClient.getInstanceClient();
            mRtspSocketClient.setOnRequestConfigListener(mRequestConfigListener);
            mRtspSocketClient.setOnRequestRespondsListener(mRequestRespondsListener);
            return;
        }
        catch (IOException ioexception)
        {
            ioexception.printStackTrace();
        }
    }

    private boolean isFovWide(String s)
    {
        if (s == null)
        {
            return false;
        } else
        {
            return s.equals("video_fov_wid");
        }
    }

    private boolean isResolution1080P(String s)
    {
        if (s == null)
        {
            return false;
        } else
        {
            return s.startsWith("1920x1080");
        }
    }

    private void onItemValChanged(String s, int i, int j)
    {
        Log.e("AEESettingCMDListActivity", (new StringBuilder()).append("onItemValChanged valStr = ").append(s).append(" cmdTYpe = ").append(i).append(" value = ").append(j).toString());
        Log.e("zdf", (new StringBuilder()).append("onItemValChanged valStr = ").append(s).append(" cmdTYpe = ").append(i).append(" value = ").append(j).toString());
        if (mCurSettingValList != null && i < mCurSettingValList.length) goto _L2; else goto _L1
_L1:
        sendHandleWithTime(0x1100005, 0, null, 0, -1);
_L12:
        return;
_L2:
        boolean flag = true;
        com.arcsoft.videostream.aee.AEESocketClient.ConfigDATA configdata;
        mRtspSocketClient = AEESocketClient.getInstanceClient();
        String s1 = mSettingWholeList[mCurSettingValList[i]];
        configdata = mRtspSocketClient.getSttingDataByKey(s1);
        String s2;
        s2 = null;
        if (configdata == null)
        {
            break MISSING_BLOCK_LABEL_176;
        }
        s2 = getChoiceItemShowString(i, configdata.options[j]);
        if (s2 == null || configdata == null)
        {
            break MISSING_BLOCK_LABEL_204;
        }
        configdata.stateVal = configdata.options[j];
        configdata.stateIndex = j;
        updateItemVal(i, s2);
        if (configdata == null) goto _L4; else goto _L3
_L3:
        if (!configdata.name.equals("video_resolution")) goto _L6; else goto _L5
_L5:
        mCurVideoResolution = configdata.stateVal;
        if (isResolution1080P(mCurVideoResolution) || isFovWide(mCurVideoFov)) goto _L4; else goto _L7
_L7:
        sendHandleWithTime(0x1100002, 0, "video_fov;video_fov_wid", 1 + mCurCmd, 0);
        flag = false;
_L4:
        if (flag)
        {
            sendHandleWithTime(0x1100005, 0, null, 0, -1);
            return;
        }
        break; /* Loop/switch isn't completed */
_L6:
        try
        {
            if (!configdata.name.equals("video_fov"))
            {
                continue; /* Loop/switch isn't completed */
            }
            mCurVideoFov = configdata.stateVal;
        }
        catch (IOException ioexception)
        {
            ioexception.printStackTrace();
        }
        if (true) goto _L4; else goto _L8
_L8:
        if (!configdata.name.equals("setup_system_type") && !configdata.name.equals("photo_shot_mode")) goto _L4; else goto _L9
_L9:
        Handler handler = mHandler;
        handler;
        JVM INSTR monitorenter ;
        mHandler.sendEmptyMessage(0x1100006);
        flag = false;
        handler;
        JVM INSTR monitorexit ;
        flag = false;
        if (true) goto _L4; else goto _L10
_L10:
        if (true) goto _L12; else goto _L11
_L11:
        Exception exception;
        exception;
        handler;
        JVM INSTR monitorexit ;
        throw exception;
    }

    private void onItemValUpdated(String s, int i, int j)
    {
        Log.e("AEESettingCMDListActivity", (new StringBuilder()).append("onItemValUpdated 1 reqString = ").append(s).append(" cmdTYpe = ").append(i).append(" val = ").append(j).toString());
        mRequstStr = s;
        mCurCmd = i;
        mCurVal = j;
        sendHandleCMD(0x10000034, 0x1000001f, mRequstStr, 0L);
    }

    private void releaseDefault()
    {
        if (mSettingWholeList != null)
        {
            mSettingWholeList = null;
        }
        if (mSettingValListS70 != null)
        {
            mSettingValListS70 = null;
        }
        if (mSettingValListS50 != null)
        {
            mSettingValListS50 = null;
        }
        if (mSettingValListSD22W != null)
        {
            mSettingValListSD22W = null;
        }
        if (mSettingValListSD21W != null)
        {
            mSettingValListSD21W = null;
        }
        if (mSettingValListS51 != null)
        {
            mSettingValListSD21W = null;
        }
        if (mSettingValListSD23W != null)
        {
            mSettingValListSD23W = null;
        }
        if (mSettingValListS25W != null)
        {
            mSettingValListS25W = null;
        }
        if (mTabPosition != null)
        {
            mTabPosition.clear();
            mTabPosition = null;
        }
    }

    private void sendCommandFailed(int i, int j, String s)
    {
        synchronized (mHandler)
        {
            if (mHandler != null)
            {
                Message message = mHandler.obtainMessage(0x1100008);
                message.arg1 = i;
                message.arg2 = j;
                message.obj = s;
                mHandler.removeMessages(0x1100008);
                mHandler.sendMessage(message);
            }
        }
        return;
        exception;
        handler;
        JVM INSTR monitorexit ;
        throw exception;
    }

    private void sendCommandOther(int i, String s)
    {
        try
        {
            mRtspSocketClient = AEESocketClient.getInstanceClient();
            if (mRtspSocketClient.isConnected())
            {
                mRtspSocketClient.stopRequestConfig();
                mRtspSocketClient.startRespondParams(i);
                mRtspSocketClient.sendCommand(i, s);
            }
            return;
        }
        catch (IOException ioexception)
        {
            ioexception.printStackTrace();
        }
    }

    private void sendHandleCMD(int i, int j, String s, long l)
    {
        synchronized (mHandler)
        {
            Message message = new Message();
            message.what = 0x1100004;
            message.arg1 = i;
            message.arg2 = j;
            message.obj = s;
            mHandler.sendMessageDelayed(message, l);
        }
        return;
        exception;
        handler;
        JVM INSTR monitorexit ;
        throw exception;
    }

    private void sendHandleWithTime(int i, int j, Object obj, int k, int l)
    {
        Message message;
label0:
        {
            if (mHandler != null)
            {
                mHandler.removeMessages(i);
                message = mHandler.obtainMessage(i);
                message.what = i;
                if (k != -1)
                {
                    message.arg1 = k;
                }
                if (l != -1)
                {
                    message.arg2 = l;
                }
                if (obj != null)
                {
                    message.obj = obj;
                }
                if (j <= 0)
                {
                    break label0;
                }
                mHandler.sendMessageDelayed(message, j);
            }
            return;
        }
        mHandler.sendMessage(message);
    }

    private void setSwitcherVal(String s, View view)
    {
        TextView textview = (TextView)view;
        int i = getResources().getInteger(0x7f0a000c);
        if (s.contains("on"))
        {
            textview.setBackgroundResource(0x7f020288);
            textview.setText(0x7f0b01a0);
            textview.setGravity(19);
            textview.setPadding(i, 0, 0, 0);
            return;
        } else
        {
            textview.setBackgroundResource(0x7f020287);
            textview.setText(0x7f0b01a1);
            textview.setGravity(21);
            textview.setPadding(0, 0, i, 0);
            return;
        }
    }

    private void showAlertDialog(int i, String s, String s1)
    {
        mCurCmd = i;
        mCurVal = -1;
        View view = LayoutInflater.from(this).inflate(0x7f03002e, null);
        ((TextView)view.findViewById(0x7f0900dd)).setText(s);
        ((TextView)view.findViewById(0x7f0900df)).setText(s1);
        ((Button)view.findViewById(0x7f0900e1)).setOnClickListener(mOnClickListener);
        ((Button)view.findViewById(0x7f0900e2)).setOnClickListener(mOnClickListener);
        showPopWindow(view);
    }

    private void showChoiceCmdView(int i)
    {
        Log.e("AEESettingCMDListActivity", (new StringBuilder()).append("showChoiceCmdView position = ").append(i).toString());
        if (mCurSettingValList != null && mCurSettingValList[i] < 33)
        {
            String s;
            try
            {
                mRtspSocketClient = AEESocketClient.getInstanceClient();
            }
            catch (IOException ioexception)
            {
                ioexception.printStackTrace();
            }
            s = mSettingWholeList[mCurSettingValList[i]];
            if (s.equals("video_fov") && !isResolution1080P(mCurVideoResolution) || s.equals("photo_tlm"))
            {
                Toast.makeText(this, 0x7f0b001c, 0).show();
                return;
            }
            if ((mCurExecuteStatus == 4 || mCurExecuteStatus == 5 || mCurExecuteStatus == 6) && s.equals("video_resolution"))
            {
                Toast.makeText(this, 0x7f0b001c, 0).show();
                return;
            }
            final com.arcsoft.videostream.aee.AEESocketClient.ConfigDATA data = mRtspSocketClient.getSttingDataByKey(s);
            if (data != null)
            {
                mCurVal = data.stateIndex;
                View view = LayoutInflater.from(this).inflate(0x7f030032, null);
                String s1 = data.name;
                boolean flag = AEEParameterMaps.mAEESettingMap.containsKey(s1);
                String s2 = null;
                if (flag)
                {
                    s2 = getString(((Integer)AEEParameterMaps.mAEESettingMap.get(s1)).intValue());
                }
                ((TextView)view.findViewById(0x7f0900dd)).setText(s2);
                Button button = (Button)view.findViewById(0x7f0900ed);
                android.view.View.OnClickListener onclicklistener = new android.view.View.OnClickListener() {

                    final AEESettingCMDListActivity this$0;

                    public void onClick(View view1)
                    {
                        mCurVal = -1;
                        if (mPopupWindow != null)
                        {
                            mPopupWindow.dismiss();
                        }
                    }

            
            {
                this$0 = AEESettingCMDListActivity.this;
                super();
            }
                };
                button.setOnClickListener(onclicklistener);
                String as[] = new String[data.options.length];
                for (int j = 0; j < data.options.length; j++)
                {
                    as[j] = getChoiceItemShowString(i, data.options[j]);
                }

                ListView listview = (ListView)view.findViewById(0x7f0900ec);
                ArrayAdapter arrayadapter = new ArrayAdapter(this, 0x7f030034, 0x7f090100, as);
                listview.setChoiceMode(1);
                listview.setAdapter(arrayadapter);
                Log.e("AEESettingCMDListActivity", (new StringBuilder()).append("showChoiceCmdView mCurVal = ").append(mCurVal).toString());
                listview.setItemChecked(mCurVal, true);
                if (as != null && as.length > 3)
                {
                    android.widget.RelativeLayout.LayoutParams layoutparams = (android.widget.RelativeLayout.LayoutParams)listview.getLayoutParams();
                    layoutparams.height = getResources().getInteger(0x7f0a0011);
                    listview.setLayoutParams(layoutparams);
                    listview.setSelection(mCurVal);
                }
                android.widget.AdapterView.OnItemClickListener onitemclicklistener = new android.widget.AdapterView.OnItemClickListener() {

                    final AEESettingCMDListActivity this$0;
                    final com.arcsoft.videostream.aee.AEESocketClient.ConfigDATA val$data;

                    public void onItemClick(AdapterView adapterview, View view1, int k, long l)
                    {
                        mCurVal = k;
                        Log.i("zdf", (new StringBuilder()).append("showChoiceCmdView, sendHandleWithTime, data.name = ").append(data.name).append(", data.options[").append(k).append("] = ").append(data.options[k]).append(", mCurCmd = ").append(mCurCmd).append(", mCurVal = ").append(mCurVal).toString());
                        sendHandleWithTime(0x1100002, 0, (new StringBuilder()).append(data.name).append(";").append(data.options[k]).toString(), mCurCmd, mCurVal);
                        mSelectedValue = data.options[k];
                        if (mPopupWindow != null)
                        {
                            mPopupWindow.dismiss();
                        }
                        sendHandleWithTime(0x1100005, 0, null, 1, -1);
                    }

            
            {
                this$0 = AEESettingCMDListActivity.this;
                data = configdata;
                super();
            }
                };
                listview.setOnItemClickListener(onitemclicklistener);
                showPopWindow(view);
                return;
            }
        }
    }

    private void showPopWindow(View view)
    {
        Log.e("AEESettingCMDListActivity", "showPopWindow");
        if (mPopupWindow == null)
        {
            mPopupWindow = new PopupWindow(view, -2, -2, true);
        }
        mPopupWindow.setOnDismissListener(new android.widget.PopupWindow.OnDismissListener() {

            final AEESettingCMDListActivity this$0;

            public void onDismiss()
            {
                Log.e("AEESettingCMDListActivity", (new StringBuilder()).append("onDismiss mCurVal = ").append(mCurVal).append(" mCurCmd = ").append(mCurCmd).toString());
                mPopupWindow = null;
                mCurCmd = -1;
                mCurVal = -1;
            }

            
            {
                this$0 = AEESettingCMDListActivity.this;
                super();
            }
        });
        mPopupWindow.setOutsideTouchable(true);
        mPopupWindow.setBackgroundDrawable(new BitmapDrawable());
        mPopupWindow.setAnimationStyle(0x7f0d0008);
        View view1 = getWindow().getDecorView();
        mPopupWindow.showAtLocation(view1, 129, 0, 0);
    }

    private void showUpdateWaitDialog()
    {
        if (mUpdateWaitDialog == null)
        {
            mUpdateWaitDialog = new LoadingDialog(this, 0x7f0d0004);
            mUpdateWaitDialog.setCancelable(false);
        }
        try
        {
            mUpdateWaitDialog.show();
            return;
        }
        catch (Exception exception)
        {
            return;
        }
    }

    private void updateItemVal(int i, String s)
    {
        int j = mListView.getFirstVisiblePosition();
        View view = mListView.getChildAt(i - j);
        if (view == null)
        {
            return;
        }
        TextView textview = (TextView)view.findViewById(0x7f09001d);
        if (textview != null && textview.getVisibility() == 0)
        {
            int k = getResources().getInteger(0x7f0a000c);
            if (s.contains("on"))
            {
                textview.setBackgroundResource(0x7f020288);
                textview.setText(0x7f0b01a0);
                textview.setGravity(19);
                textview.setPadding(k, 0, 0, 0);
                return;
            } else
            {
                textview.setBackgroundResource(0x7f020287);
                textview.setText(0x7f0b01a1);
                textview.setGravity(21);
                textview.setPadding(0, 0, k, 0);
                return;
            }
        } else
        {
            ((TextView)view.findViewById(0x7f09001a)).setText(s);
            return;
        }
    }

    protected boolean getRespond()
    {
        int i;
        try
        {
            mRtspSocketClient = AEESocketClient.getInstanceClient();
            mRtspSocketClient.stopRequestConfig();
            mRtspSocketClient.stopRespondParams();
            i = mRtspSocketClient.getRespondParams();
        }
        catch (IOException ioexception)
        {
            ioexception.printStackTrace();
            return false;
        }
        if (i == 0)
        {
            return true;
        }
        sendHandleWithTime(0x1100003, 0, null, 0x7f0b018e, i);
        return false;
    }

    public void onBackPressed()
    {
        Log.e("AEESettingCMDListActivity", "onBackPressed");
        setResult(mResultCode);
        super.onBackPressed();
    }

    protected void onCreate(Bundle bundle)
    {
        Log.e("AEESettingCMDListActivity", "onCreate");
        super.onCreate(bundle);
        setContentView(0x7f03002f);
        initDefault();
        initList();
        initSocket();
        findViewById(0x7f0900e4).setOnClickListener(mOnClickListener);
        sendHandleWithTime(0x1100005, 0, null, 1, -1);
        sendHandleCMD(0x10000013, 0x1100006, null, 0L);
        mNetworkTool = new NetworkTool(this);
        mNetworkTool.setOnSettingChangeListener(mNetworkSettingListener);
        mNetworkTool.setOnConnectivityChangeListener(mNetworkConnectivityListener);
        if (mRtspSocketClient != null)
        {
            mCurExecuteStatus = mRtspSocketClient.getCurEXEState();
        }
    }

    protected void onDestroy()
    {
        releaseDefault();
        if (mNetworkTool != null)
        {
            mNetworkTool.recycle();
            mNetworkTool = null;
        }
        try
        {
            mRtspSocketClient = AEESocketClient.getInstanceClient();
            mRtspSocketClient.stopRequestConfig();
        }
        catch (IOException ioexception)
        {
            ioexception.printStackTrace();
        }
        super.onDestroy();
    }

    protected void onPause()
    {
        SystemUtils.keepScreenOn(this, false);
        super.onPause();
    }

    protected void onResume()
    {
        Log.e("AEESettingCMDListActivity", "onResume");
        SystemUtils.keepScreenOn(this, true);
        super.onResume();
    }





/*
    static String access$1002(AEESettingCMDListActivity aeesettingcmdlistactivity, String s)
    {
        aeesettingcmdlistactivity.mCurSysTime = s;
        return s;
    }

*/




/*
    static String access$1202(AEESettingCMDListActivity aeesettingcmdlistactivity, String s)
    {
        aeesettingcmdlistactivity.mStrVersion = s;
        return s;
    }

*/



/*
    static String access$1302(AEESettingCMDListActivity aeesettingcmdlistactivity, String s)
    {
        aeesettingcmdlistactivity.mDeviceName = s;
        return s;
    }

*/



/*
    static AEESocketClient access$1402(AEESettingCMDListActivity aeesettingcmdlistactivity, AEESocketClient aeesocketclient)
    {
        aeesettingcmdlistactivity.mRtspSocketClient = aeesocketclient;
        return aeesocketclient;
    }

*/





/*
    static String access$1702(AEESettingCMDListActivity aeesettingcmdlistactivity, String s)
    {
        aeesettingcmdlistactivity.mCurVideoResolution = s;
        return s;
    }

*/



/*
    static String access$1802(AEESettingCMDListActivity aeesettingcmdlistactivity, String s)
    {
        aeesettingcmdlistactivity.mCurVideoFov = s;
        return s;
    }

*/
















/*
    static int access$302(AEESettingCMDListActivity aeesettingcmdlistactivity, int i)
    {
        aeesettingcmdlistactivity.mCurCmd = i;
        return i;
    }

*/







/*
    static int access$502(AEESettingCMDListActivity aeesettingcmdlistactivity, int i)
    {
        aeesettingcmdlistactivity.mCurVal = i;
        return i;
    }

*/



/*
    static PopupWindow access$602(AEESettingCMDListActivity aeesettingcmdlistactivity, PopupWindow popupwindow)
    {
        aeesettingcmdlistactivity.mPopupWindow = popupwindow;
        return popupwindow;
    }

*/



/*
    static String access$702(AEESettingCMDListActivity aeesettingcmdlistactivity, String s)
    {
        aeesettingcmdlistactivity.mSelectedValue = s;
        return s;
    }

*/



/*
    static int access$802(AEESettingCMDListActivity aeesettingcmdlistactivity, int i)
    {
        aeesettingcmdlistactivity.mResultCode = i;
        return i;
    }

*/

}
