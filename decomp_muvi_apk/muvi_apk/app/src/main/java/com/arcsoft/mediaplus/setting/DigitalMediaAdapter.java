// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.arcsoft.mediaplus.setting;

import android.content.Context;
import android.graphics.Bitmap;
import android.os.RemoteException;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.TextView;
import com.arcsoft.adk.atv.DLNA;
import com.arcsoft.adk.atv.ServerManager;
import com.arcsoft.mediaplus.listview.CheckedLayout;
import com.arcsoft.mediaplus.service.util.IDlnaSettingBinder;
import com.arcsoft.util.debug.Log;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.Set;
import java.util.Vector;

// Referenced classes of package com.arcsoft.mediaplus.setting:
//            DigitalMediaItem, Settings

public class DigitalMediaAdapter extends BaseAdapter
{
    public static interface IDMPStatusChanged
    {

        public abstract void notifyChanged();

        public abstract void notifyDismiss();

        public abstract void postInvalidate(int i);

        public abstract void setItemChecked(int i);
    }

    public final class ViewHolder
    {

        public RadioButton check;
        public ImageView icon;
        final DigitalMediaAdapter this$0;
        public TextView title;

        public ViewHolder()
        {
            this$0 = DigitalMediaAdapter.this;
            super();
        }
    }


    protected String TAG;
    private int mActiveIndex;
    protected boolean mBindServiceSuccessful;
    private IDlnaSettingBinder mBinder;
    private final Context mContext;
    private final Vector mDMItemArray = new Vector();
    private final Object mIconSyncObj = new Object();
    protected final LayoutInflater mInflater;
    private final boolean mNeedMatchDMS = false;
    private IDMPStatusChanged mNotify;
    private Hashtable mServerIconCache;
    private int mType;
    private final Vector mUDNArray = new Vector();

    public DigitalMediaAdapter(Context context, int i, IDlnaSettingBinder idlnasettingbinder)
    {
        mType = -1;
        mActiveIndex = -1;
        mServerIconCache = null;
        mNotify = null;
        mBinder = null;
        mBindServiceSuccessful = false;
        TAG = "DigitalMediaAdapter";
        mContext = context;
        mInflater = LayoutInflater.from(context);
        mBinder = idlnasettingbinder;
        mType = i;
        init();
    }

    private void clear()
    {
        mUDNArray.clear();
        Iterator iterator = mDMItemArray.iterator();
        do
        {
            if (!iterator.hasNext())
            {
                break;
            }
            DigitalMediaItem digitalmediaitem = (DigitalMediaItem)iterator.next();
            if (digitalmediaitem.deviceBitmap != null)
            {
                digitalmediaitem.deviceBitmap.recycle();
                digitalmediaitem.deviceBitmap = null;
            }
        } while (true);
        Object obj = mIconSyncObj;
        obj;
        JVM INSTR monitorenter ;
        Iterator iterator1;
        if (mServerIconCache == null)
        {
            break MISSING_BLOCK_LABEL_153;
        }
        iterator1 = mServerIconCache.entrySet().iterator();
_L2:
        java.util.Map.Entry entry;
        do
        {
            if (!iterator1.hasNext())
            {
                break MISSING_BLOCK_LABEL_153;
            }
            entry = (java.util.Map.Entry)iterator1.next();
        } while (entry == null);
        Bitmap bitmap = (Bitmap)entry.getValue();
        if (bitmap == null) goto _L2; else goto _L1
_L1:
        if (bitmap.isRecycled()) goto _L2; else goto _L3
_L3:
        bitmap.recycle();
          goto _L2
        Exception exception;
        exception;
        obj;
        JVM INSTR monitorexit ;
        throw exception;
        obj;
        JVM INSTR monitorexit ;
        mDMItemArray.clear();
        mActiveIndex = -1;
        return;
    }

    private DigitalMediaItem createItem(String s)
    {
        DigitalMediaItem digitalmediaitem;
        digitalmediaitem = new DigitalMediaItem();
        digitalmediaitem.udn = s;
        digitalmediaitem.state = 500;
        if (mType != 0) goto _L2; else goto _L1
_L1:
        digitalmediaitem.name = mBinder.getDmsFriendlyName(digitalmediaitem.udn);
_L4:
        if (digitalmediaitem.name == null)
        {
            digitalmediaitem.name = digitalmediaitem.udn;
        }
        return digitalmediaitem;
_L2:
        try
        {
            digitalmediaitem.name = mBinder.getDmrFriendlyName(digitalmediaitem.udn);
        }
        catch (RemoteException remoteexception)
        {
            digitalmediaitem.name = null;
        }
        if (true) goto _L4; else goto _L3
_L3:
    }

    private void remove(int i)
    {
        mUDNArray.remove(i);
        mDMItemArray.remove(i);
    }

    protected void add(DigitalMediaItem digitalmediaitem)
    {
        mUDNArray.add(digitalmediaitem.udn);
        mDMItemArray.add(digitalmediaitem);
    }

    public void dmrOffLine(String s)
    {
        dmsOffLine(s);
    }

    public void dmrOnLine(String s)
    {
        if (s == null)
        {
            return;
        }
        if (mUDNArray.contains(s))
        {
            int i = mUDNArray.indexOf(s);
            ((DigitalMediaItem)mDMItemArray.get(i)).state = 500;
            if (mNotify != null)
            {
                mNotify.postInvalidate(i);
            }
        } else
        {
            add(createItem(s));
        }
        notifyDataSetChanged();
    }

    public void dmsOffLine(String s)
    {
        if (s != null && mUDNArray != null && mUDNArray.contains(s))
        {
            int i = mUDNArray.indexOf(s);
            ((DigitalMediaItem)mDMItemArray.get(i)).state = 501;
            remove(i);
            notifyDataSetChanged();
            if (mNotify != null)
            {
                mNotify.postInvalidate(i);
                return;
            }
        }
    }

    public void dmsOnLine(String s)
    {
        if (s == null)
        {
            return;
        }
        if (mUDNArray.contains(s))
        {
            int i = mUDNArray.indexOf(s);
            ((DigitalMediaItem)mDMItemArray.get(i)).state = 500;
            if (mNotify != null)
            {
                mNotify.postInvalidate(i);
            }
        } else
        {
            add(createItem(s));
        }
        notifyDataSetChanged();
    }

    public int getActiveIndex()
    {
        return mActiveIndex;
    }

    public int getCount()
    {
        if (mDMItemArray.size() == 0 && mNotify != null)
        {
            mNotify.notifyDismiss();
        }
        return mDMItemArray.size();
    }

    public Object getItem(int i)
    {
        if (i < getCount())
        {
            return mDMItemArray.get(i);
        } else
        {
            return null;
        }
    }

    public long getItemId(int i)
    {
        return (long)i;
    }

    public String getItemUDN(int i)
    {
        if (i < getCount())
        {
            return (String)mUDNArray.get(i);
        } else
        {
            return null;
        }
    }

    protected Bitmap getServerIcon(String s)
    {
label0:
        {
            Bitmap bitmap1;
            synchronized (mIconSyncObj)
            {
                if (mServerIconCache == null)
                {
                    mServerIconCache = new Hashtable();
                }
                if (!mServerIconCache.containsKey(s))
                {
                    break label0;
                }
                bitmap1 = (Bitmap)mServerIconCache.get(s);
            }
            return bitmap1;
        }
        com.arcsoft.adk.atv.UPnP.MediaServerDesc mediaserverdesc = DLNA.instance().getServerManager().getServerDesc(s);
        if (mediaserverdesc == null)
        {
            break MISSING_BLOCK_LABEL_125;
        }
        Bitmap bitmap;
        if (mediaserverdesc.m_DeviceIcon == null || mediaserverdesc.m_DeviceIcon.isRecycled())
        {
            break MISSING_BLOCK_LABEL_125;
        }
        bitmap = mediaserverdesc.m_DeviceIcon.copy(mediaserverdesc.m_DeviceIcon.getConfig(), true);
        mServerIconCache.put(s, bitmap);
        return bitmap;
        obj;
        JVM INSTR monitorexit ;
        return null;
        exception;
        obj;
        JVM INSTR monitorexit ;
        throw exception;
    }

    public int getType()
    {
        return mType;
    }

    public View getView(int i, View view, ViewGroup viewgroup)
    {
        ViewHolder viewholder;
        DigitalMediaItem digitalmediaitem;
        if (view == null)
        {
            view = mInflater.inflate(0x7f03000c, null);
            viewholder = new ViewHolder();
            viewholder.title = (TextView)view.findViewById(0x7f090045);
            viewholder.check = (RadioButton)view.findViewById(0x7f090043);
            viewholder.icon = (ImageView)view.findViewById(0x7f090044);
            view.setTag(viewholder);
            ((CheckedLayout)view).setCheckMarkView(viewholder.check);
        } else
        {
            viewholder = (ViewHolder)view.getTag();
        }
        digitalmediaitem = (DigitalMediaItem)getItem(i);
        Log.e(TAG, (new StringBuilder()).append("mBindServiceSuccessful = ").append(mBindServiceSuccessful).toString());
        if (mBindServiceSuccessful && digitalmediaitem.state == 500)
        {
            Bitmap bitmap = getServerIcon(digitalmediaitem.udn);
            String s;
            if (bitmap != null && !bitmap.isRecycled())
            {
                viewholder.icon.setImageBitmap(bitmap);
            } else
            {
                viewholder.icon.setImageResource(0x7f0202cd);
            }
        } else
        {
            viewholder.icon.setImageResource(0x7f0202cd);
        }
        viewholder.title.setText(digitalmediaitem.name);
        if (digitalmediaitem.udn != null)
        {
            s = digitalmediaitem.udn;
            String s1;
            if (mType == 1)
            {
                s1 = Settings.instance().getDefaultDMRUDN();
            } else
            {
                s1 = Settings.instance().getDefaultDMSUDN();
            }
            if (s.equals(s1))
            {
                mActiveIndex = i;
                if (mNotify != null)
                {
                    mNotify.setItemChecked(getActiveIndex());
                }
            }
        }
        return view;
    }

    public boolean init()
    {
        String as1[];
        if (mBinder == null)
        {
            return false;
        }
        clear();
        DigitalMediaItem digitalmediaitem = new DigitalMediaItem();
        int i;
        DigitalMediaItem digitalmediaitem1;
        String as2[];
        if (mType == 0)
        {
            digitalmediaitem.udn = Settings.instance().getDefaultDMSUDN();
            digitalmediaitem.name = Settings.instance().getDefaultDMSName();
        } else
        {
            digitalmediaitem.udn = Settings.instance().getDefaultDMRUDN();
            digitalmediaitem.name = Settings.instance().getDefaultDMRName();
        }
        if (digitalmediaitem.udn != null)
        {
            if (mType == 1);
            add(digitalmediaitem);
            mActiveIndex = 0;
        }
        if (mType != 0) goto _L2; else goto _L1
_L1:
        as2 = mBinder.getOnlineDmsUDN();
        as1 = as2;
_L4:
        if (as1 != null && as1.length > 0)
        {
            for (i = 0; i < as1.length; i++)
            {
                digitalmediaitem1 = createItem(as1[i]);
                Log.d(TAG, (new StringBuilder()).append("manufacture :\nname :").append(digitalmediaitem1.name).toString());
                if (mType != 0);
                if (digitalmediaitem != null && digitalmediaitem1.udn.equalsIgnoreCase(digitalmediaitem.udn))
                {
                    digitalmediaitem.state = 500;
                    remove(0);
                    mActiveIndex = mDMItemArray.size();
                }
                add(digitalmediaitem1);
            }

        }
        break; /* Loop/switch isn't completed */
_L2:
        String as[];
        try
        {
            as = mBinder.getOnlineDmrUDN();
        }
        catch (Exception exception)
        {
            return false;
        }
        as1 = as;
        if (true) goto _L4; else goto _L3
_L3:
        return true;
    }

    public boolean isDmrOnLine(String s)
    {
        while (s == null || mUDNArray == null) 
        {
            return false;
        }
        return mUDNArray.contains(s);
    }

    public boolean isEnabled(int i)
    {
        int j = getCount();
        boolean flag = false;
        if (i < j)
        {
            int k = ((DigitalMediaItem)getItem(i)).state;
            flag = false;
            if (k == 500)
            {
                flag = true;
            }
        }
        return flag;
    }

    public void reSearch()
    {
        init();
        notifyDataSetChanged();
    }

    public void setActiveIndex(int i)
    {
        mActiveIndex = i;
    }

    public void setStatusChangedListener(IDMPStatusChanged idmpstatuschanged)
    {
        if (idmpstatuschanged != null)
        {
            mNotify = idmpstatuschanged;
        }
    }

    public String toString()
    {
        StringBuilder stringbuilder = new StringBuilder();
        int i = mDMItemArray.size();
        stringbuilder.append((new StringBuilder()).append(" ").append(i).append(": \n").toString());
        for (int j = 0; j < i; j++)
        {
            stringbuilder.append((new StringBuilder()).append(j).append(": ").toString());
            DigitalMediaItem digitalmediaitem = (DigitalMediaItem)mDMItemArray.get(j);
            stringbuilder.append((new StringBuilder()).append("{ ").append(digitalmediaitem.udn).append(", ").append(digitalmediaitem.state).append(" } \n").toString());
        }

        return stringbuilder.toString();
    }
}
