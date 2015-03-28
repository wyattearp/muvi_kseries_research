// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.arcsoft.mediaplus.setting;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Typeface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.arcsoft.util.debug.Log;
import com.arcsoft.videostream.aee.AEEParameterMaps;
import com.arcsoft.videostream.aee.AEESocketClient;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;

// Referenced classes of package com.arcsoft.mediaplus.setting:
//            AEESettingCMDListActivity

private class mInflater extends BaseAdapter
{

    private final Context mContext;
    private final LayoutInflater mInflater;
    final AEESettingCMDListActivity this$0;

    private void setConfigSetting(mInflater minflater, final int index)
    {
        Log.e("AEESettingCMDListActivity", (new StringBuilder()).append("setConfigSetting position = ").append(index).toString());
        if (AEESettingCMDListActivity.access$100(AEESettingCMDListActivity.this) != null && index < AEESettingCMDListActivity.access$100(AEESettingCMDListActivity.this).length) goto _L2; else goto _L1
_L1:
        return;
_L2:
        int i;
        String s;
        String s1;
        i = AEESettingCMDListActivity.access$100(AEESettingCMDListActivity.this)[index];
        s = AEESettingCMDListActivity.access$1100(AEESettingCMDListActivity.this)[i];
        s1 = s;
        Log.e("AEESettingCMDListActivity", (new StringBuilder()).append("setConfigSetting index = ").append(index).toString());
        Log.e("AEESettingCMDListActivity", (new StringBuilder()).append("setConfigSetting mCurSettingValList[").append(index).append("] = ").append(AEESettingCMDListActivity.access$100(AEESettingCMDListActivity.this)[index]).toString());
        Log.e("AEESettingCMDListActivity", (new StringBuilder()).append("setConfigSetting stringKey = ").append(s).toString());
        i;
        JVM INSTR lookupswitch 8: default 244
    //                   0: 631
    //                   9: 631
    //                   18: 631
    //                   33: 750
    //                   34: 750
    //                   35: 750
    //                   36: 925
    //                   37: 925;
           goto _L3 _L4 _L4 _L4 _L5 _L5 _L5 _L6 _L6
_L3:
        if (AEEParameterMaps.mAEESettingMap.containsKey(s))
        {
            s1 = getString(((Integer)AEEParameterMaps.mAEESettingMap.get(s)).intValue());
        }
        final com.arcsoft.videostream.aee.r data = AEESettingCMDListActivity.access$1400(AEESettingCMDListActivity.this).getSttingDataByKey(s);
        minflater._fld0.setText(s1);
        minflater._fld0.setTypeface(Typeface.defaultFromStyle(0));
        int i1 = (int)getResources().getDimension(0x7f0800e2);
        minflater._fld0.setPadding(i1, 0, i1, 0);
        int j;
        String s2;
        android.widget.tingCMDAdapter tingcmdadapter;
        android.widget.tingCMDAdapter tingcmdadapter1;
        int k;
        SimpleDateFormat simpledateformat;
        int l;
        if (data != null && data..length == 2 && (data..trim().equalsIgnoreCase("off") || data..trim().equalsIgnoreCase("on")) && data..contains("settable"))
        {
            android.widget.tingCMDAdapter tingcmdadapter4 = (android.widget.ission)minflater._fld0.getLayoutParams();
            tingcmdadapter4.title = 1.0F;
            minflater._fld0.setLayoutParams(tingcmdadapter4);
            minflater._fld0.setVisibility(8);
            minflater.r.setVisibility(0);
            minflater.r.setClickable(true);
            minflater.r.setOnClickListener(new android.view.View.OnClickListener() {

                final AEESettingCMDListActivity.SettingCMDAdapter this$1;
                final com.arcsoft.videostream.aee.AEESocketClient.ConfigDATA val$data;
                final int val$index;

                public void onClick(View view)
                {
                    AEESettingCMDListActivity.access$000(this$0, 0x1100005, 0, null, 1, -1);
                    AEESettingCMDListActivity.access$302(this$0, index);
                    AEESettingCMDListActivity.access$502(this$0, 1 - data.stateIndex);
                    String s4 = data.options[AEESettingCMDListActivity.access$500(this$0)];
                    AEESettingCMDListActivity.access$000(this$0, 0x1100002, 0, (new StringBuilder()).append(data.name).append(";").append(s4).toString(), AEESettingCMDListActivity.access$300(this$0), AEESettingCMDListActivity.access$500(this$0));
                }

            
            {
                this$1 = AEESettingCMDListActivity.SettingCMDAdapter.this;
                index = i;
                data = configdata;
                super();
            }
            });
            AEESettingCMDListActivity.access$1500(AEESettingCMDListActivity.this, data._fld0, minflater.r);
            minflater.r.setClickable(true);
        } else
        {
            android.widget.tingCMDAdapter tingcmdadapter2 = (android.widget.wHolder.layout)minflater.r.getLayoutParams();
            tingcmdadapter2.title = 0.0F;
            minflater.r.setLayoutParams(tingcmdadapter2);
            android.widget.tingCMDAdapter tingcmdadapter3 = (android.widget.wHolder.title)minflater.r.getLayoutParams();
            tingcmdadapter3.item = 1.0F;
            minflater.r.setLayoutParams(tingcmdadapter3);
            minflater.r.setVisibility(0);
            minflater.r.setVisibility(8);
            TextView textview = minflater.r;
            String s3;
            if (data == null)
            {
                s3 = "";
            } else
            {
                s3 = AEESettingCMDListActivity.access$1600(AEESettingCMDListActivity.this, index, data._fld0);
            }
            textview.setText(s3);
            if (data != null && data._fld0.contains("settable"))
            {
                minflater._fld0.setClickable(false);
            } else
            {
                minflater._fld0.setClickable(true);
            }
        }
        if (data != null && s.equals("video_resolution"))
        {
            AEESettingCMDListActivity.access$1702(AEESettingCMDListActivity.this, data._fld0);
        } else
        if (data != null && s.equals("video_fov"))
        {
            AEESettingCMDListActivity.access$1802(AEESettingCMDListActivity.this, data._fld0);
            if (!AEESettingCMDListActivity.access$1900(AEESettingCMDListActivity.this, AEESettingCMDListActivity.access$1700(AEESettingCMDListActivity.this)) && !AEESettingCMDListActivity.access$2000(AEESettingCMDListActivity.this, AEESettingCMDListActivity.access$1800(AEESettingCMDListActivity.this)))
            {
                AEESettingCMDListActivity.access$000(AEESettingCMDListActivity.this, 0x1100005, 0, null, 1, -1);
                AEESettingCMDListActivity.access$000(AEESettingCMDListActivity.this, 0x1100002, 0, "video_fov;video_fov_wid", index, 0);
            }
        }
_L8:
        if (i == 9 || i == 18 || i == 0) goto _L1; else goto _L7
_L4:
        minflater._fld0.setVisibility(8);
        minflater.r.setVisibility(8);
        if (AEEParameterMaps.mAEESettingMap.containsKey(s))
        {
            s1 = getString(((Integer)AEEParameterMaps.mAEESettingMap.get(s)).intValue());
        }
        minflater.r.setText(s1);
        minflater.r.setTypeface(Typeface.defaultFromStyle(1));
        l = (int)getResources().getDimension(0x7f0800e1);
        minflater.r.setPadding(l, 0, l, 0);
        minflater.r.setBackgroundDrawable(null);
        minflater.r.setClickable(false);
          goto _L8
_L5:
        if (i == 33)
        {
            minflater.r.setVisibility(0);
            simpledateformat = new SimpleDateFormat("yyyy/MM/dd HH:mm");
            AEESettingCMDListActivity.access$1002(AEESettingCMDListActivity.this, simpledateformat.format(new Date()));
            minflater._fld0.setText(AEESettingCMDListActivity.access$1000(AEESettingCMDListActivity.this));
        } else
        {
            minflater._fld0.setVisibility(8);
        }
        minflater.r.setVisibility(8);
        if (AEEParameterMaps.mAEESettingMap.containsKey(s))
        {
            s1 = getString(((Integer)AEEParameterMaps.mAEESettingMap.get(s)).intValue());
        }
        minflater.r.setText(s1);
        minflater.r.setTypeface(Typeface.defaultFromStyle(0));
        k = (int)getResources().getDimension(0x7f0800e2);
        minflater.r.setPadding(k, 0, k, 0);
        minflater.r.setClickable(false);
          goto _L8
_L6:
        if (AEEParameterMaps.mAEESettingMap.containsKey(s))
        {
            s1 = getString(((Integer)AEEParameterMaps.mAEESettingMap.get(s)).intValue());
        }
        minflater.r.setText(s1);
        minflater.r.setTypeface(Typeface.defaultFromStyle(0));
        j = (int)getResources().getDimension(0x7f0800e2);
        minflater.r.setPadding(j, 0, j, 0);
        if (i == 36)
        {
            s2 = AEESettingCMDListActivity.access$1200(AEESettingCMDListActivity.this);
        } else
        {
            s2 = null;
            if (i == 37)
            {
                s2 = AEESettingCMDListActivity.access$1300(AEESettingCMDListActivity.this);
            }
        }
        if (s2 != null && s2.length() > 0)
        {
            tingcmdadapter = (android.widget.tingCMDAdapter.this._cls0)minflater._fld0.getLayoutParams();
            tingcmdadapter.title = 0.0F;
            minflater._fld0.setLayoutParams(tingcmdadapter);
            tingcmdadapter1 = (android.widget.wHolder.title)minflater._fld0.getLayoutParams();
            tingcmdadapter1.item = 1.0F;
            minflater._fld0.setLayoutParams(tingcmdadapter1);
            minflater._fld0.setVisibility(0);
            minflater.r.setVisibility(8);
            minflater.r.setText(s2);
        } else
        {
            minflater.r.setVisibility(8);
            minflater.r.setVisibility(8);
        }
        minflater.r.setBackgroundResource(0x7f02024d);
        minflater.r.setClickable(true);
          goto _L8
          goto _L1
_L7:
        if (index == 1 + ((Integer)AEESettingCMDListActivity.access$2100(AEESettingCMDListActivity.this).get(Integer.valueOf(9))).intValue() || index == 1 + ((Integer)AEESettingCMDListActivity.access$2100(AEESettingCMDListActivity.this).get(Integer.valueOf(18))).intValue() || index == 1 + ((Integer)AEESettingCMDListActivity.access$2100(AEESettingCMDListActivity.this).get(Integer.valueOf(0))).intValue())
        {
            minflater._fld0.setBackgroundResource(0x7f020252);
            return;
        }
        if (index == -1 + ((Integer)AEESettingCMDListActivity.access$2100(AEESettingCMDListActivity.this).get(Integer.valueOf(9))).intValue() || index == -1 + ((Integer)AEESettingCMDListActivity.access$2100(AEESettingCMDListActivity.this).get(Integer.valueOf(18))).intValue() || index == -1 + ((Integer)AEESettingCMDListActivity.access$2100(AEESettingCMDListActivity.this).get(Integer.valueOf(0))).intValue() || index == -1 + AEESettingCMDListActivity.access$100(AEESettingCMDListActivity.this).length)
        {
            minflater._fld0.setBackgroundResource(0x7f02024d);
            return;
        } else
        {
            minflater._fld0.setBackgroundResource(0x7f02024f);
            return;
        }
    }

    private void setDefaultSetting(this._cls0 _pcls0, int i)
    {
        Log.e("AEESettingCMDListActivity", (new StringBuilder()).append("setDefaultSetting index = ").append(i).toString());
        if (AEESettingCMDListActivity.access$100(AEESettingCMDListActivity.this) == null || i >= AEESettingCMDListActivity.access$100(AEESettingCMDListActivity.this).length)
        {
            return;
        }
        _pcls0._fld0.setVisibility(8);
        _pcls0.r.setVisibility(8);
        String s = AEESettingCMDListActivity.access$1100(AEESettingCMDListActivity.this)[AEESettingCMDListActivity.access$100(AEESettingCMDListActivity.this)[i]];
        String s1 = s;
        if (AEEParameterMaps.mAEESettingMap.containsKey(s))
        {
            s1 = getString(((Integer)AEEParameterMaps.mAEESettingMap.get(s)).intValue());
        }
        _pcls0._fld0.setText(s1);
        if (i == 9 || i == 18 || i == 0)
        {
            _pcls0._fld0.setTypeface(Typeface.defaultFromStyle(1));
            int j = (int)getResources().getDimension(0x7f0800e1);
            _pcls0._fld0.setPadding(j, 0, j, 0);
            _pcls0._fld0.setBackgroundDrawable(null);
        } else
        {
            _pcls0._fld0.setTypeface(Typeface.defaultFromStyle(0));
            int k = (int)getResources().getDimension(0x7f0800e2);
            _pcls0._fld0.setPadding(k, 0, k, 0);
            if (i == 10 || i == 19 || i == 1)
            {
                _pcls0._fld0.setBackgroundResource(0x7f020252);
            } else
            if (i == 8 || i == 17 || i == -1 || i == -1 + AEESettingCMDListActivity.access$100(AEESettingCMDListActivity.this).length)
            {
                _pcls0._fld0.setBackgroundResource(0x7f02024d);
            } else
            {
                _pcls0._fld0.setBackgroundResource(0x7f02024f);
            }
        }
        _pcls0._fld0.setClickable(false);
    }

    private this._cls0 setSettingView(this._cls0 _pcls0, int i)
    {
        Log.e("AEESettingCMDListActivity", (new StringBuilder()).append("setSettingView position = ").append(i).append(" mRtspSocketClient.getSettingDataNum() = ").append(AEESettingCMDListActivity.access$1400(AEESettingCMDListActivity.this).getSettingDataNum()).toString());
        Log.e("AEESettingCMDListActivity", (new StringBuilder()).append("setSettingView position = ").append(i).append(" mCurSettingValList.length() = ").append(AEESettingCMDListActivity.access$100(AEESettingCMDListActivity.this).length).toString());
        Log.e("AEESettingCMDListActivity", (new StringBuilder()).append("setSettingView position = ").append(i).append(" mSettingWholeList.length() = ").append(AEESettingCMDListActivity.access$1100(AEESettingCMDListActivity.this).length).toString());
        try
        {
            AEESettingCMDListActivity.access$1402(AEESettingCMDListActivity.this, AEESocketClient.getInstanceClient());
        }
        catch (IOException ioexception)
        {
            ioexception.printStackTrace();
        }
        if (AEESettingCMDListActivity.access$1400(AEESettingCMDListActivity.this).getSettingDataNum() == 0 || AEESettingCMDListActivity.access$1400(AEESettingCMDListActivity.this).getSettingDataNum() < -3 + AEESettingCMDListActivity.access$100(AEESettingCMDListActivity.this).length)
        {
            setDefaultSetting(_pcls0, i);
            return _pcls0;
        } else
        {
            setConfigSetting(_pcls0, i);
            return _pcls0;
        }
    }

    public int getCount()
    {
        if (AEESettingCMDListActivity.access$100(AEESettingCMDListActivity.this) != null)
        {
            Log.e("AEESettingCMDListActivity", (new StringBuilder()).append("getCount mCurSettingValList.length = ").append(AEESettingCMDListActivity.access$100(AEESettingCMDListActivity.this).length).toString());
            return AEESettingCMDListActivity.access$100(AEESettingCMDListActivity.this).length;
        } else
        {
            return 0;
        }
    }

    public com.arcsoft.videostream.aee.r getItem(int i)
    {
        if (AEESettingCMDListActivity.access$100(AEESettingCMDListActivity.this) == null || i >= AEESettingCMDListActivity.access$100(AEESettingCMDListActivity.this).length)
        {
            return null;
        }
        String s = AEESettingCMDListActivity.access$1100(AEESettingCMDListActivity.this)[AEESettingCMDListActivity.access$100(AEESettingCMDListActivity.this)[i]];
        try
        {
            AEESettingCMDListActivity.access$1402(AEESettingCMDListActivity.this, AEESocketClient.getInstanceClient());
        }
        catch (IOException ioexception)
        {
            ioexception.printStackTrace();
        }
        return AEESettingCMDListActivity.access$1400(AEESettingCMDListActivity.this).getSttingDataByKey(s);
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
        getItem getitem;
        if (view == null)
        {
            view = mInflater.inflate(0x7f030004, null);
            getitem = new mInflater(AEESettingCMDListActivity.this);
            getitem._fld0 = (TextView)view.findViewById(0x7f090019);
            getitem._fld0 = (TextView)view.findViewById(0x7f09001a);
            getitem.r = (TextView)view.findViewById(0x7f09001d);
            getitem.r = (LinearLayout)view.findViewById(0x7f09001c);
            view.setTag(getitem);
        } else
        {
            getitem = (r)view.getTag();
        }
        setSettingView(getitem, i);
        return view;
    }

    public boolean isEnabled(int i)
    {
        return true;
    }

    public _cls1.val.data(Context context)
    {
        this$0 = AEESettingCMDListActivity.this;
        super();
        mContext = context;
        mInflater = LayoutInflater.from(context);
    }
}
