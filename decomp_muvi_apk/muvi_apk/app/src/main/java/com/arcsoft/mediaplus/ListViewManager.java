// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.arcsoft.mediaplus;

import android.content.Context;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.GridView;
import android.widget.RelativeLayout;
import com.arcsoft.mediaplus.datasource.IDataSource;
import com.arcsoft.mediaplus.datasource.MediaItem;
import com.arcsoft.mediaplus.datasource.Property;
import com.arcsoft.mediaplus.listview.IItemListView;
import com.arcsoft.mediaplus.listview.LocalContentListView;
import com.arcsoft.mediaplus.listview.MediaSeeListView;
import com.arcsoft.mediaplus.listview.OnlineContentListView;
import com.arcsoft.mediaplus.oem.OEMConfig;
import com.arcsoft.mediaplus.picture.ui.ListViewGL;
import com.arcsoft.mediaplus.picture.ui.LocalListViewGL;
import com.arcsoft.mediaplus.picture.ui.RemoteListViewGL;
import com.arcsoft.mediaplus.updownload.service.UpDownloadEngine;
import com.arcsoft.util.debug.Log;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

// Referenced classes of package com.arcsoft.mediaplus:
//            MediaPlusActivity, RemoteListView, LocalListView, MediaPlusListView, 
//            ViewManager

public class ListViewManager
{

    public static final int LIST_TYPE_LOCAL = 1;
    public static final int LIST_TYPE_REMOTE;
    public static HashMap mIDsItems = null;
    public static HashMap mSelectedItems = null;
    public static HashMap mUpdateItems = null;
    protected static Map type_layout_Map;
    private RelativeLayout MainLayout;
    private final String TAG = "ListViewManager";
    protected HashMap mContentListView;
    private Context mContext;
    private int mCurrentType;
    HashMap mListViews;
    private UpDownloadEngine mUpDownloadEngine;

    public ListViewManager(Context context, RelativeLayout relativelayout)
    {
        mContext = null;
        mCurrentType = 0;
        mUpDownloadEngine = null;
        MainLayout = null;
        mContentListView = new HashMap();
        mListViews = new HashMap();
        mContext = context;
        MainLayout = relativelayout;
        mContentListView.put(Integer.valueOf(0), new OnlineContentListView(mContext));
        mContentListView.put(Integer.valueOf(1), new LocalContentListView(mContext));
        mSelectedItems = new HashMap();
        prepareListView(mCurrentType);
    }

    private void addToView()
    {
        RelativeLayout relativelayout = (RelativeLayout)MainLayout.findViewById(0x7f0900a2);
        relativelayout.removeAllViews();
        relativelayout.addView((View)mListViews.get(Integer.valueOf(mCurrentType)), new android.view.ViewGroup.LayoutParams(-1, -1));
    }

    public static void clearUpdateItems()
    {
        if (mUpdateItems != null)
        {
            mUpdateItems.clear();
            mUpdateItems = null;
        }
    }

    public static void decreaseSelectedItemKey(int i)
    {
        if (mSelectedItems == null)
        {
            return;
        }
        HashMap hashmap = new HashMap();
        int j;
        MediaItem mediaitem;
        for (Iterator iterator = mSelectedItems.entrySet().iterator(); iterator.hasNext(); hashmap.put(Integer.valueOf(j), mediaitem))
        {
            java.util.Map.Entry entry = (java.util.Map.Entry)iterator.next();
            j = ((Integer)entry.getKey()).intValue() - i;
            mediaitem = (MediaItem)entry.getValue();
        }

        mSelectedItems.clear();
        mSelectedItems.putAll(hashmap);
    }

    public static String getObjId(String s)
    {
        if (mUpdateItems != null && mUpdateItems.containsKey(s))
        {
            return (String)mUpdateItems.get(s);
        } else
        {
            return null;
        }
    }

    public static MediaItem[] getSelectedItemListEx()
    {
        if (mSelectedItems == null)
        {
            return null;
        } else
        {
            ArrayList arraylist = new ArrayList(mSelectedItems.values());
            return (MediaItem[])arraylist.toArray(new MediaItem[arraylist.size()]);
        }
    }

    public static int getSelectedItemsNum()
    {
        if (mSelectedItems != null && mSelectedItems.size() > 0)
        {
            return mSelectedItems.size();
        } else
        {
            return 0;
        }
    }

    private int getType()
    {
        return 64;
    }

    public static boolean isSelectedItem(Integer integer)
    {
        if (mSelectedItems == null || mSelectedItems.size() == 0)
        {
            return false;
        } else
        {
            return mSelectedItems.containsKey(integer);
        }
    }

    private boolean prepareListView(int i)
    {
        IItemListView iitemlistview = (IItemListView)mListViews.get(Integer.valueOf(i));
        boolean flag = false;
        if (iitemlistview == null)
        {
            final View view = LayoutInflater.from(mContext).inflate(((Integer)type_layout_Map.get(Integer.valueOf(i))).intValue(), null);
            iitemlistview = (IItemListView)view;
            iitemlistview.setListItemOpListener(new com.arcsoft.mediaplus.listview.IItemListView.IListOpListener() {

                final ListViewManager this$0;
                final View val$view;

                public void OnItemClick(int j, long l)
                {
                    com.arcsoft.adk.atv.UPnP.RemoteItemDesc remoteitemdesc = ((IItemListView)mListViews.get(Integer.valueOf(mCurrentType))).getDataSource().getRemoteItemDesc(j);
                    ((MediaPlusActivity)mContext).doItemSelect(j, remoteitemdesc, l);
                }

                public void OnItemLongPress(int j)
                {
                    view.setTag(Integer.valueOf(j));
                }

            
            {
                this$0 = ListViewManager.this;
                view = view1;
                super();
            }
            });
            mListViews.put(Integer.valueOf(i), iitemlistview);
            iitemlistview.init((RelativeLayout)MainLayout.findViewById(0x7f0900a2), true);
            flag = true;
        }
        iitemlistview.setDataSource(((MediaSeeListView)mContentListView.get(Integer.valueOf(mCurrentType))).initDataSource(getType()));
        return flag;
    }

    public static void putSelectedItem(Integer integer, MediaItem mediaitem)
    {
        if (mSelectedItems == null)
        {
            return;
        } else
        {
            mSelectedItems.put(integer, mediaitem);
            return;
        }
    }

    public static void putUpdateItem(String s, String s1)
    {
        if (mUpdateItems == null)
        {
            mUpdateItems = new HashMap();
        }
        if (mUpdateItems.containsKey(s))
        {
            mUpdateItems.remove(s);
        }
        mUpdateItems.put(s, s1);
    }

    public static void removeSelectedItem(Integer integer)
    {
        if (mSelectedItems == null)
        {
            return;
        } else
        {
            mSelectedItems.remove(integer);
            return;
        }
    }

    public static void removeUpdateItem(String s)
    {
        if (mUpdateItems == null)
        {
            return;
        } else
        {
            mUpdateItems.remove(s);
            return;
        }
    }

    private void showListView(IItemListView iitemlistview, int i)
    {
        if (OEMConfig.OPENGL_OPTIMIZE && iitemlistview.isOpenGl())
        {
            ((ListViewGL)iitemlistview).setVisibility(i);
        } else
        {
            ((GridView)iitemlistview).setVisibility(i);
        }
        ((MediaPlusActivity)mContext).dismissLoadingDialog();
    }

    public void addUpdownload(Boolean boolean1, int i)
    {
        ((IItemListView)mListViews.get(Integer.valueOf(mCurrentType))).addUpdownload(true, i);
    }

    public void cancelAllUpdownload()
    {
        ((IItemListView)mListViews.get(Integer.valueOf(mCurrentType))).cancelAllUpdownload();
    }

    public void cancelUpdownload(Boolean boolean1, int i)
    {
        ((IItemListView)mListViews.get(Integer.valueOf(mCurrentType))).cancelUpdownload(true, i);
    }

    public void changeGridLayoutParams()
    {
        if (OEMConfig.OPENGL_OPTIMIZE)
        {
            if (!((IItemListView)mListViews.get(Integer.valueOf(mCurrentType))).isOpenGl())
            {
                ((RemoteListView)mListViews.get(Integer.valueOf(mCurrentType))).changeGridLayoutParams();
            }
            return;
        }
        if (isRemote())
        {
            ((RemoteListView)mListViews.get(Integer.valueOf(mCurrentType))).changeGridLayoutParams();
            return;
        } else
        {
            ((LocalListView)mListViews.get(Integer.valueOf(mCurrentType))).changeGridLayoutParams();
            return;
        }
    }

    public void clearIDsItems()
    {
        if (mIDsItems != null)
        {
            mIDsItems.clear();
            mIDsItems = null;
        }
    }

    public void clearSelectItems()
    {
        if (mSelectedItems != null)
        {
            mSelectedItems.clear();
        }
    }

    public void destroy()
    {
        mContext = null;
        releaseLists(true);
        if (mSelectedItems != null)
        {
            mSelectedItems.clear();
            mSelectedItems = null;
        }
        clearUpdateItems();
        clearIDsItems();
        if (mListViews != null)
        {
            mListViews.clear();
            mListViews = null;
        }
        if (mContentListView != null)
        {
            mContentListView.clear();
            mContentListView = null;
        }
    }

    public IItemListView getCurrentListView()
    {
        return (IItemListView)mListViews.get(Integer.valueOf(mCurrentType));
    }

    public int getCurrentType()
    {
        return mCurrentType;
    }

    public IDataSource getDataSource()
    {
        return ((IItemListView)mListViews.get(Integer.valueOf(mCurrentType))).getDataSource();
    }

    public int getDownloadCount()
    {
        if (OEMConfig.OPENGL_OPTIMIZE && ((IItemListView)mListViews.get(Integer.valueOf(mCurrentType))).isOpenGl())
        {
            return ((ListViewGL)mListViews.get(Integer.valueOf(mCurrentType))).getDownLoadCount();
        } else
        {
            return ((MediaPlusListView)mListViews.get(Integer.valueOf(mCurrentType))).getDownLoadCount();
        }
    }

    public Uri getDownloadedUriInSelected(int i)
    {
        if (mSelectedItems != null && i >= 0 && i < mSelectedItems.size())
        {
            int j = 0;
            Iterator iterator = mSelectedItems.entrySet().iterator();
            while (iterator.hasNext()) 
            {
                java.util.Map.Entry entry = (java.util.Map.Entry)iterator.next();
                if (j == i)
                {
                    Integer integer = (Integer)entry.getKey();
                    return ((ListViewGL)mListViews.get(Integer.valueOf(mCurrentType))).getUriByIndex(integer.intValue());
                }
                j++;
            }
        }
        return null;
    }

    public Integer getIndex(String s)
    {
        if (mIDsItems != null && mIDsItems.containsKey(s))
        {
            return (Integer)mIDsItems.get(s);
        } else
        {
            return null;
        }
    }

    public com.arcsoft.mediaplus.datasource.DataSourceFactory.DataSourceFilter getPlayDataSourceFilter()
    {
        return ((MediaSeeListView)mContentListView.get(Integer.valueOf(mCurrentType))).getPlayDataSourceFilter(getType(), getDataSource());
    }

    public int getPlayDataSourceIndex(int i)
    {
        return ((MediaSeeListView)mContentListView.get(Integer.valueOf(mCurrentType))).getPlayDataSourceIndex(i, getDataSource());
    }

    public MediaItem getSelectedItem(Integer integer)
    {
        while (mSelectedItems == null || !isSelectedItem(integer)) 
        {
            return null;
        }
        return (MediaItem)mSelectedItems.get(integer);
    }

    public MediaItem[] getSelectedItemList()
    {
        if (mSelectedItems == null)
        {
            return null;
        } else
        {
            ArrayList arraylist = new ArrayList(mSelectedItems.values());
            return (MediaItem[])arraylist.toArray(new MediaItem[arraylist.size()]);
        }
    }

    public Integer[] getSelectedKeyList()
    {
        if (mSelectedItems == null)
        {
            return null;
        }
        Iterator iterator = mSelectedItems.entrySet().iterator();
        ArrayList arraylist = new ArrayList();
        java.util.Map.Entry entry;
        for (; iterator.hasNext(); arraylist.add((Integer)entry.getKey()))
        {
            entry = (java.util.Map.Entry)iterator.next();
            Log.e("testP", (new StringBuilder()).append("testP key index = ").append(entry.getKey()).toString());
        }

        return (Integer[])arraylist.toArray(new Integer[arraylist.size()]);
    }

    public int getTotalMediaCount()
    {
        if (OEMConfig.OPENGL_OPTIMIZE && ((IItemListView)mListViews.get(Integer.valueOf(mCurrentType))).isOpenGl())
        {
            return ((ListViewGL)mListViews.get(Integer.valueOf(mCurrentType))).getTotalMediaCount();
        } else
        {
            return ((MediaPlusListView)mListViews.get(Integer.valueOf(mCurrentType))).getTotalMediaCount();
        }
    }

    public boolean isRemote()
    {
        return mCurrentType == 0;
    }

    public void onFileDeleted()
    {
        if (!isRemote()) goto _L2; else goto _L1
_L1:
        clearUpdateItems();
        clearIDsItems();
_L4:
        clearSelectItems();
        refreshCurrentView(false);
        refreshTextView();
        return;
_L2:
        if (mSelectedItems != null && mSelectedItems.size() != 0)
        {
            ArrayList arraylist = new ArrayList(mSelectedItems.values());
            if (OEMConfig.OPENGL_OPTIMIZE)
            {
                ((LocalListViewGL)mListViews.get(Integer.valueOf(mCurrentType))).onDeleted(arraylist);
            } else
            {
                ((LocalListView)mListViews.get(Integer.valueOf(mCurrentType))).onDeleted(arraylist);
            }
        }
        if (true) goto _L4; else goto _L3
_L3:
    }

    public void onSharingConfigurationChanged()
    {
        IItemListView iitemlistview = (IItemListView)mListViews.get(Integer.valueOf(mCurrentType));
        if (iitemlistview != null)
        {
            ((View)iitemlistview).setFocusable(true);
            ((View)iitemlistview).requestFocus();
        }
    }

    public void pause()
    {
        ((IItemListView)mListViews.get(Integer.valueOf(mCurrentType))).onPause();
    }

    public void putIDSItem(String s, Integer integer)
    {
        if (mIDsItems == null)
        {
            mIDsItems = new HashMap();
        }
        if (mIDsItems.containsKey(s))
        {
            mIDsItems.remove(s);
        }
        mIDsItems.put(s, integer);
    }

    public void refreshCurrentView(boolean flag)
    {
        if (OEMConfig.OPENGL_OPTIMIZE && ((IItemListView)mListViews.get(Integer.valueOf(mCurrentType))).isOpenGl())
        {
            ((ListViewGL)(ListViewGL)mListViews.get(Integer.valueOf(mCurrentType))).refresh(flag);
        } else
        {
            ((GridView)mListViews.get(Integer.valueOf(mCurrentType))).invalidateViews();
        }
        if (ViewManager.getViewStatus() == 2)
        {
            ((MediaPlusActivity)mContext).refreshSelectorTitle(getSelectedItemsNum());
        }
    }

    public void refreshTextView()
    {
label0:
        {
            if (OEMConfig.OPENGL_OPTIMIZE && ((IItemListView)mListViews.get(Integer.valueOf(mCurrentType))).isOpenGl())
            {
                if (!isRemote())
                {
                    break label0;
                }
                ((RemoteListViewGL)(RemoteListViewGL)mListViews.get(Integer.valueOf(mCurrentType))).refreshTextView();
            }
            return;
        }
        ((LocalListViewGL)(LocalListViewGL)mListViews.get(Integer.valueOf(mCurrentType))).refreshTextView();
    }

    protected void releaseLists(boolean flag)
    {
        if (mListViews != null)
        {
            Iterator iterator = mListViews.entrySet().iterator();
            while (iterator.hasNext()) 
            {
                java.util.Map.Entry entry = (java.util.Map.Entry)iterator.next();
                IItemListView iitemlistview = (IItemListView)entry.getValue();
                iitemlistview.onPause();
                ((Integer)entry.getKey()).intValue();
                IDataSource idatasource = iitemlistview.getDataSource();
                iitemlistview.setDataSource(null);
                if (flag)
                {
                    iitemlistview.uninit();
                }
                if (idatasource != null)
                {
                    try
                    {
                        ((MediaSeeListView)mContentListView.get(Integer.valueOf(mCurrentType))).releaseDataSource(getType(), idatasource);
                    }
                    catch (IllegalArgumentException illegalargumentexception)
                    {
                        Log.e("zdf", "releaseLists, IllegalArgumentException");
                    }
                }
            }
        }
    }

    public void removeIDsItem(String s)
    {
        if (mIDsItems == null)
        {
            return;
        } else
        {
            mIDsItems.remove(s);
            return;
        }
    }

    public void removeListView()
    {
        ((IItemListView)mListViews.get(Integer.valueOf(mCurrentType))).removeListView();
    }

    public void resume()
    {
        if (mListViews.get(Integer.valueOf(mCurrentType)) == null)
        {
            return;
        } else
        {
            ((IItemListView)mListViews.get(Integer.valueOf(mCurrentType))).onResume();
            return;
        }
    }

    public void selectAll()
    {
        if (OEMConfig.OPENGL_OPTIMIZE && ((IItemListView)mListViews.get(Integer.valueOf(mCurrentType))).isOpenGl())
        {
            ((ListViewGL)mListViews.get(Integer.valueOf(mCurrentType))).selectAll();
            return;
        } else
        {
            ((MediaPlusListView)mListViews.get(Integer.valueOf(mCurrentType))).selectAll();
            return;
        }
    }

    public void setCurrentListView(int i, boolean flag)
    {
        boolean flag1;
        if (i == mCurrentType)
        {
            flag1 = true;
        } else
        {
            flag1 = false;
        }
        if (!flag1 || flag)
        {
            IItemListView iitemlistview = (IItemListView)mListViews.get(Integer.valueOf(mCurrentType));
            if (iitemlistview != null)
            {
                iitemlistview.getDataSource();
                iitemlistview.onPause();
            }
            mCurrentType = i;
            boolean flag2 = prepareListView(i);
            IItemListView iitemlistview1 = (IItemListView)mListViews.get(Integer.valueOf(i));
            if (iitemlistview1 == null)
            {
                if (iitemlistview != null)
                {
                    iitemlistview.setDataSource(((MediaSeeListView)mContentListView.get(Integer.valueOf(mCurrentType))).initDataSource(getType()));
                    iitemlistview.onResume();
                    showListView(iitemlistview, 0);
                    return;
                }
            } else
            {
                iitemlistview1.addListView();
                iitemlistview1.setUpDownloadContext(mUpDownloadEngine);
                iitemlistview1.onResume();
                iitemlistview1.setItemVisibleInScreen(0);
                Log.e("ListViewManager", "setCurrentListView");
                if (!flag2)
                {
                    Log.e("ListViewManager", "setCurrentListView 1111");
                    showListView(iitemlistview1, 0);
                    if (iitemlistview != null && !flag1)
                    {
                        showListView(iitemlistview, 8);
                    }
                }
                ((View)iitemlistview1).setFocusable(true);
                ((View)iitemlistview1).requestFocus();
                return;
            }
        }
    }

    public void setItemVisibleInScreen(int i)
    {
        ((IItemListView)mListViews.get(Integer.valueOf(mCurrentType))).setItemVisibleInScreen(i);
    }

    public void setUpDownloadContext(UpDownloadEngine updownloadengine)
    {
        if (updownloadengine == null)
        {
            return;
        } else
        {
            mUpDownloadEngine = updownloadengine;
            ((IItemListView)mListViews.get(Integer.valueOf(mCurrentType))).setUpDownloadContext(updownloadengine);
            return;
        }
    }

    public void showHideViews()
    {
        if (mListViews != null)
        {
            int i = 0;
            while (i < mListViews.size()) 
            {
                IItemListView iitemlistview = (IItemListView)mListViews.get(Integer.valueOf(i));
                if (iitemlistview != null)
                {
                    if (i == mCurrentType)
                    {
                        showListView(iitemlistview, 0);
                    } else
                    {
                        showListView(iitemlistview, 8);
                    }
                }
                i++;
            }
        }
    }

    public void sort(Property property, boolean flag)
    {
        IItemListView iitemlistview = (IItemListView)mListViews.get(Integer.valueOf(mCurrentType));
        boolean flag1;
        if (!flag)
        {
            flag1 = true;
        } else
        {
            flag1 = false;
        }
        iitemlistview.sort(property, flag1);
    }

    public void switchList(int i)
    {
        if (i == mCurrentType)
        {
            return;
        } else
        {
            ((MediaPlusActivity)mContext).switchContentView(i);
            return;
        }
    }

    public void switchView(int i, int j)
    {
        Log.e("ListViewManager", (new StringBuilder()).append("switchView : fromView = ").append(i).toString());
        Log.e("ListViewManager", (new StringBuilder()).append("switchView : status = ").append(j).toString());
        switch (j)
        {
        case 3: // '\003'
        default:
            return;

        case 0: // '\0'
            if (i == 1)
            {
                switchList(0);
                return;
            }
            if (i == 2)
            {
                clearSelectItems();
                refreshCurrentView(false);
                return;
            }
            if (i == 4)
            {
                refreshCurrentView(false);
                return;
            } else
            {
                refreshCurrentView(true);
                return;
            }

        case 1: // '\001'
            if (i == 0)
            {
                switchList(1);
                return;
            }
            if (i == 2)
            {
                clearSelectItems();
                refreshCurrentView(false);
                return;
            } else
            {
                clearSelectItems();
                refreshCurrentView(true);
                return;
            }

        case 2: // '\002'
            ((MediaPlusActivity)mContext).refreshSelectorTitle(getSelectedItemsNum());
            return;

        case 4: // '\004'
            refreshCurrentView(false);
            return;
        }
    }

    static 
    {
        type_layout_Map = new HashMap();
        Map map = type_layout_Map;
        Integer integer = Integer.valueOf(0);
        int i;
        Map map1;
        Integer integer1;
        int j;
        if (OEMConfig.OPENGL_OPTIMIZE)
        {
            i = 0x7f03002b;
        } else
        {
            i = 0x7f03002a;
        }
        map.put(integer, Integer.valueOf(i));
        map1 = type_layout_Map;
        integer1 = Integer.valueOf(1);
        if (OEMConfig.OPENGL_OPTIMIZE)
        {
            j = 0x7f03001f;
        } else
        {
            j = 0x7f03001e;
        }
        map1.put(integer1, Integer.valueOf(j));
    }


}
