// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.arcsoft.mediaplus.widget;

import android.app.Activity;
import android.content.Context;
import android.content.res.Resources;
import android.graphics.drawable.BitmapDrawable;
import android.os.Handler;
import android.os.Message;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.TextView;

public class PopupMenuWindow
{
    class MenuAdapter extends BaseAdapter
    {

        private Context mContext;
        private LayoutInflater mInflater;
        final PopupMenuWindow this$0;

        protected MenuViewHolder createHolder(View view)
        {
            if (view == null)
            {
                return null;
            }
            if (view.getTag() == null)
            {
                MenuViewHolder menuviewholder = new MenuViewHolder();
                menuviewholder.menuTitle = (TextView)view.findViewById(0x7f090029);
                menuviewholder.menuCover = (ImageView)view.findViewById(0x7f090028);
                view.setTag(menuviewholder);
                return menuviewholder;
            } else
            {
                return (MenuViewHolder)view.getTag();
            }
        }

        protected boolean fillHolder(MenuViewHolder menuviewholder, int i)
        {
            if (mContext == null || menuviewholder == null || mMenuData == null || mMenuData.namesIds == null)
            {
                return false;
            } else
            {
                menuviewholder.menuTitle.setText(mContext.getResources().getString(mMenuData.namesIds[i]));
                menuviewholder.menuCover.setBackgroundResource(mMenuData.iconIds[i]);
                menuviewholder.menuTitle.setTextColor(-1);
                return true;
            }
        }

        public int getCount()
        {
            if (mMenuData == null || mMenuData.namesIds == null)
            {
                return 0;
            } else
            {
                return mMenuData.namesIds.length;
            }
        }

        public Object getItem(int i)
        {
            return Integer.valueOf(i);
        }

        public long getItemId(int i)
        {
            return (long)i;
        }

        public View getView(int i, View view, ViewGroup viewgroup)
        {
            if (view == null)
            {
                view = mInflater.inflate(0x7f030006, null);
                if (view == null)
                {
                    return null;
                }
            }
            fillHolder(createHolder(view), i);
            return view;
        }

        public MenuAdapter(Context context)
        {
            this$0 = PopupMenuWindow.this;
            super();
            mContext = null;
            mInflater = null;
            mContext = context;
            mInflater = LayoutInflater.from(mContext);
        }
    }

    class MenuAdapter.MenuViewHolder
    {

        ImageView menuCover;
        TextView menuTitle;
        final MenuAdapter this$1;

        MenuAdapter.MenuViewHolder()
        {
            this$1 = MenuAdapter.this;
            super();
        }
    }

    public static interface OnMenuClickedListener
    {

        public abstract void onClicked(int i);
    }

    public class PopupMenuData
    {

        public int iconIds[];
        public int namesIds[];
        final PopupMenuWindow this$0;

        public PopupMenuData()
        {
            this$0 = PopupMenuWindow.this;
            super();
            namesIds = null;
            iconIds = null;
        }
    }


    private final android.widget.PopupWindow.OnDismissListener listener;
    private Activity mContext;
    private final Handler mHandler;
    private final android.widget.AdapterView.OnItemClickListener mListItemClickListener;
    private ListView mListView;
    private OnMenuClickedListener mMenuClickedListener;
    private PopupMenuData mMenuData;
    private PopupWindow mPopupMenuWindow;
    private int mPopupWindowHeight;
    private int mPopupWindowWidth;

    public PopupMenuWindow(Activity activity)
    {
        mContext = null;
        mMenuData = null;
        mPopupMenuWindow = null;
        mListView = null;
        mMenuClickedListener = null;
        mPopupWindowHeight = 150;
        mPopupWindowWidth = 250;
        mListItemClickListener = new android.widget.AdapterView.OnItemClickListener() {

            final PopupMenuWindow this$0;

            public void onItemClick(AdapterView adapterview, View view, int i, long l)
            {
                mMenuClickedListener.onClicked(i);
            }

            
            {
                this$0 = PopupMenuWindow.this;
                super();
            }
        };
        listener = new android.widget.PopupWindow.OnDismissListener() {

            final PopupMenuWindow this$0;

            public void onDismiss()
            {
                setFocusable(false);
            }

            
            {
                this$0 = PopupMenuWindow.this;
                super();
            }
        };
        mHandler = new Handler() {

            final PopupMenuWindow this$0;

            public void handleMessage(Message message)
            {
                while (message.what != 0 || mPopupMenuWindow == null || !mPopupMenuWindow.isShowing()) 
                {
                    return;
                }
                mPopupMenuWindow.dismiss();
            }

            
            {
                this$0 = PopupMenuWindow.this;
                super();
            }
        };
        mContext = activity;
        mMenuData = new PopupMenuData();
        mMenuData.namesIds = (new int[] {
            0x7f0b0172, 0x7f0b0173
        });
        mMenuData.iconIds = (new int[] {
            0x7f0200bf, 0x7f0200c0
        });
        initPopupMenuWindow();
    }

    public PopupMenuWindow(Activity activity, PopupMenuData popupmenudata)
    {
        mContext = null;
        mMenuData = null;
        mPopupMenuWindow = null;
        mListView = null;
        mMenuClickedListener = null;
        mPopupWindowHeight = 150;
        mPopupWindowWidth = 250;
        mListItemClickListener = new _cls1();
        listener = new _cls3();
        mHandler = new _cls4();
        mContext = activity;
        mMenuData = popupmenudata;
        initPopupMenuWindow();
    }

    private void initPopupMenuWindow()
    {
        if (mContext != null)
        {
            View view = mContext.getLayoutInflater().inflate(0x7f030007, null);
            mListView = (ListView)view.findViewById(0x7f09002a);
            if (mListView != null)
            {
                mListView.setAdapter(new MenuAdapter(mContext));
                mListView.setOnItemClickListener(mListItemClickListener);
                mPopupMenuWindow = new PopupWindow(view, mPopupWindowWidth, mPopupWindowHeight);
                mPopupMenuWindow.setBackgroundDrawable(new BitmapDrawable());
                mPopupMenuWindow.setOutsideTouchable(true);
                mPopupMenuWindow.setTouchable(true);
                mPopupMenuWindow.setOnDismissListener(listener);
                mPopupMenuWindow.setTouchInterceptor(new android.view.View.OnTouchListener() {

                    final PopupMenuWindow this$0;

                    public boolean onTouch(View view1, MotionEvent motionevent)
                    {
                        setFocusable(true);
                        return false;
                    }

            
            {
                this$0 = PopupMenuWindow.this;
                super();
            }
                });
                return;
            }
        }
    }

    public void hidePopopMenuWindow()
    {
        if (mHandler != null)
        {
            mHandler.sendEmptyMessageDelayed(0, 100L);
        }
    }

    public boolean isShowing()
    {
        if (mPopupMenuWindow == null)
        {
            return false;
        } else
        {
            return mPopupMenuWindow.isShowing();
        }
    }

    public void setFocusable(boolean flag)
    {
        if (mPopupMenuWindow == null)
        {
            return;
        } else
        {
            mPopupMenuWindow.setFocusable(flag);
            mPopupMenuWindow.update();
            return;
        }
    }

    public void setOnMenuClickedListener(OnMenuClickedListener onmenuclickedlistener)
    {
        mMenuClickedListener = onmenuclickedlistener;
    }

    public void setWindowParameter(int i, int j)
    {
        mPopupWindowWidth = i;
        mPopupWindowHeight = j;
        if (mPopupMenuWindow != null)
        {
            mPopupMenuWindow.setWidth(i);
            mPopupMenuWindow.setHeight(j);
        }
    }

    public void showPopupMenuWindow(View view, int i, int j)
    {
        if (mPopupMenuWindow == null || mPopupMenuWindow.isShowing())
        {
            return;
        } else
        {
            mPopupMenuWindow.showAsDropDown(view, i, j);
            return;
        }
    }



}
