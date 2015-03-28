// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.arcsoft.mediaplus.datasource;

import android.database.Cursor;
import com.arcsoft.util.debug.Log;
import com.arcsoft.util.tool.DynamicDataStateMachine;
import java.util.ArrayList;

// Referenced classes of package com.arcsoft.mediaplus.datasource:
//            IDataSource, Property, MediaItem

public abstract class AbsDataSource
    implements IDataSource
{
    protected static class Controller
        implements IDataSource.IController
    {

        private boolean isReleased;
        protected final AbsDataSource mDataSource;
        protected final int mSession;

        public final void loadMore(int i)
        {
            mDataSource.loadMore(mSession, i);
        }

        public final void pause()
        {
            mDataSource.pause(mSession);
        }

        public final transient void prefetch(int i, int j, Property aproperty[])
        {
            mDataSource.prefetch(mSession, i, j, aproperty);
        }

        public final transient void prefetchEx(int ai[], Property aproperty[])
        {
            mDataSource.prefetchEx(mSession, ai, aproperty);
        }

        public final void refresh()
        {
            mDataSource.refresh(mSession);
        }

        public void release()
        {
            if (!isReleased)
            {
                isReleased = true;
                mDataSource.releaseController(mSession);
            }
        }

        public final void resume()
        {
            mDataSource.resume(mSession);
        }

        public final void setEnable(boolean flag)
        {
            mDataSource.setEnable(mSession, flag);
            if (!flag)
            {
                release();
            }
        }

        public final void setResourceType(boolean flag)
        {
            mDataSource.setResourceType(mSession, flag);
        }

        public final void sort(Property property, boolean flag)
        {
            mDataSource.sort(mSession, property, flag);
        }

        public Controller(AbsDataSource absdatasource, int i)
        {
            isReleased = false;
            mDataSource = absdatasource;
            mSession = i;
        }
    }


    protected static final String TAG = "AbsDataSource";
    private final com.arcsoft.util.tool.DynamicDataStateMachine.OnStateChangeActions mActions = new com.arcsoft.util.tool.DynamicDataStateMachine.OnStateChangeActions() {

        final AbsDataSource this$0;

        public void onDisable()
        {
            AbsDataSource.this.onDisable();
        }

        public void onEnable()
        {
            AbsDataSource.this.onEnable();
        }

        public void onInit()
        {
            AbsDataSource.this.onInit();
        }

        public void onPause()
        {
            AbsDataSource.this.onPause();
        }

        public void onResume()
        {
            AbsDataSource.this.onResume();
        }

        public void onUninit()
        {
            AbsDataSource.this.onUninit();
        }

            
            {
                this$0 = AbsDataSource.this;
                super();
            }
    };
    protected ArrayList mDataBuildListeners;
    protected ArrayList mDataChangeListeners;
    private final com.arcsoft.util.tool.DynamicDataStateMachine.OnStateChangeFinishActions mFinishActions = new com.arcsoft.util.tool.DynamicDataStateMachine.OnStateChangeFinishActions() {

        final AbsDataSource this$0;

        public void onAfterDisable()
        {
            AbsDataSource.this.onAfterDisable();
        }

        public void onAfterEnable()
        {
            AbsDataSource.this.onAfterEnable();
        }

        public void onAfterInit()
        {
            AbsDataSource.this.onAfterInit();
        }

        public void onAfterPause()
        {
            AbsDataSource.this.onAfterPause();
        }

        public void onAfterResume()
        {
            AbsDataSource.this.onAfterResume();
        }

        public void onAfterUninit()
        {
            AbsDataSource.this.onAfterUninit();
        }

            
            {
                this$0 = AbsDataSource.this;
                super();
            }
    };
    private boolean mIsControllerAssigned;
    private int mSessionKey;
    private final DynamicDataStateMachine mStateMachine = new DynamicDataStateMachine("AbsDataSource");

    public AbsDataSource()
    {
        mDataChangeListeners = new ArrayList();
        mDataBuildListeners = new ArrayList();
        mIsControllerAssigned = false;
        mSessionKey = 0;
        mStateMachine.setOnStateChangeActions(mActions);
        mStateMachine.setOnStateChangeFinishActions(mFinishActions);
    }

    private IDataSource.OnDataBuildListener[] getDataBuiltListenersCopy()
    {
        ArrayList arraylist = mDataBuildListeners;
        arraylist;
        JVM INSTR monitorenter ;
        int i = mDataBuildListeners.size();
        IDataSource.OnDataBuildListener aondatabuildlistener[];
        aondatabuildlistener = null;
        if (i <= 0)
        {
            break MISSING_BLOCK_LABEL_48;
        }
        IDataSource.OnDataBuildListener aondatabuildlistener1[] = new IDataSource.OnDataBuildListener[mDataBuildListeners.size()];
        aondatabuildlistener = (IDataSource.OnDataBuildListener[])mDataBuildListeners.toArray(aondatabuildlistener1);
        arraylist;
        JVM INSTR monitorexit ;
        return aondatabuildlistener;
        Exception exception;
        exception;
        arraylist;
        JVM INSTR monitorexit ;
        throw exception;
    }

    private IDataSource.OnDataChangeListener[] getDataChangeListenersCopy()
    {
        ArrayList arraylist = mDataChangeListeners;
        arraylist;
        JVM INSTR monitorenter ;
        int i = mDataChangeListeners.size();
        IDataSource.OnDataChangeListener aondatachangelistener[];
        aondatachangelistener = null;
        if (i <= 0)
        {
            break MISSING_BLOCK_LABEL_48;
        }
        IDataSource.OnDataChangeListener aondatachangelistener1[] = new IDataSource.OnDataChangeListener[mDataChangeListeners.size()];
        aondatachangelistener = (IDataSource.OnDataChangeListener[])mDataChangeListeners.toArray(aondatachangelistener1);
        arraylist;
        JVM INSTR monitorexit ;
        return aondatachangelistener;
        Exception exception;
        exception;
        arraylist;
        JVM INSTR monitorexit ;
        throw exception;
    }

    private boolean isValidSession(int i)
    {
        return mIsControllerAssigned && i == mSessionKey;
    }

    private void loadMore(int i, int j)
    {
        checkSession(i);
        loadMore(j);
    }

    private void pause(int i)
    {
        checkSession(i);
        mStateMachine.pause();
    }

    private transient void prefetch(int i, int j, int k, Property aproperty[])
    {
        checkSession(i);
        prefetch(j, k, aproperty);
    }

    private transient void prefetchEx(int i, int ai[], Property aproperty[])
    {
        checkSession(i);
        prefetchEx(ai, aproperty);
    }

    private void refresh(int i)
    {
        checkSession(i);
        refresh();
    }

    private void releaseController(int i)
    {
        checkSession(i);
        mIsControllerAssigned = false;
        onReleaseController();
    }

    private void resume(int i)
    {
        checkSession(i);
        mStateMachine.resume();
    }

    private void setEnable(int i, boolean flag)
    {
        checkSession(i);
        if (flag)
        {
            mStateMachine.enable();
            return;
        } else
        {
            mStateMachine.disable();
            return;
        }
    }

    private void setResourceType(int i, boolean flag)
    {
        checkSession(i);
        setResourceType(flag);
    }

    private void sort(int i, Property property, boolean flag)
    {
        checkSession(i);
        sort(property, flag);
    }

    protected void NotifyOnDataBuiltBegin()
    {
        IDataSource.OnDataBuildListener aondatabuildlistener[] = getDataBuiltListenersCopy();
        if (aondatabuildlistener != null)
        {
            int i = aondatabuildlistener.length;
            for (int j = 0; j < i; j++)
            {
                aondatabuildlistener[j].onDataBuiltBegin();
            }

        }
    }

    protected void NotifyOnDataBuiltFinish()
    {
        IDataSource.OnDataBuildListener aondatabuildlistener[] = getDataBuiltListenersCopy();
        if (aondatabuildlistener != null)
        {
            int i = aondatabuildlistener.length;
            for (int j = 0; j < i; j++)
            {
                aondatabuildlistener[j].onDataBuiltFinish();
            }

        }
    }

    protected void checkSession(int i)
    {
        if (!isValidSession(i))
        {
            throw new IllegalStateException("Controller is invalid");
        } else
        {
            return;
        }
    }

    protected IDataSource.IController createController(int i)
    {
        return new Controller(this, i);
    }

    public boolean delete(int i)
    {
        return false;
    }

    public boolean delete(MediaItem mediaitem)
    {
        return false;
    }

    protected void finalize()
        throws Throwable
    {
        unInit();
        super.finalize();
    }

    public boolean getBooleanProp(int i, Property property, boolean flag)
    {
        Object obj = getObjectProp(i, property, null);
        if (obj == null)
        {
            return flag;
        }
        boolean flag1;
        try
        {
            flag1 = ((Boolean)obj).booleanValue();
        }
        catch (ClassCastException classcastexception)
        {
            return flag;
        }
        return flag1;
    }

    public byte getByteProp(int i, Property property, byte byte0)
    {
        Object obj = getObjectProp(i, property, null);
        if (obj == null)
        {
            return byte0;
        }
        byte byte1;
        try
        {
            byte1 = ((Byte)obj).byteValue();
        }
        catch (ClassCastException classcastexception)
        {
            return byte0;
        }
        return byte1;
    }

    public final IDataSource.IController getController()
    {
        if (mIsControllerAssigned)
        {
            return null;
        } else
        {
            mSessionKey = 1 + mSessionKey;
            mIsControllerAssigned = true;
            return onCreateController(mSessionKey);
        }
    }

    public int getCount()
    {
        return 0;
    }

    public int getIntProp(int i, Property property, int j)
    {
        Object obj = getObjectProp(i, property, null);
        if (obj == null)
        {
            return j;
        }
        int k;
        try
        {
            k = ((Integer)obj).intValue();
        }
        catch (ClassCastException classcastexception)
        {
            return j;
        }
        return k;
    }

    public MediaItem getItem(int i)
    {
        return null;
    }

    public long getLongProp(int i, Property property, long l)
    {
        Object obj = getObjectProp(i, property, null);
        if (obj == null)
        {
            return l;
        }
        long l1;
        try
        {
            l1 = ((Long)obj).longValue();
        }
        catch (ClassCastException classcastexception)
        {
            return l;
        }
        return l1;
    }

    public Object getObjectProp(int i, Property property, Object obj)
    {
        return obj;
    }

    public com.arcsoft.adk.atv.UPnP.RemoteItemDesc getRemoteItemDesc(int i)
    {
        return null;
    }

    public ArrayList getRemoteItemResourceDesc(int i)
    {
        return null;
    }

    public String getRemoteItemUUID(int i)
    {
        return null;
    }

    public Property[] getSortCapability()
    {
        return new Property[0];
    }

    public Property getSortProperty()
    {
        return null;
    }

    public String getStringProp(int i, Property property, String s)
    {
        Object obj = getObjectProp(i, property, null);
        if (obj == null)
        {
            return s;
        }
        String s1;
        try
        {
            s1 = (String)obj;
        }
        catch (ClassCastException classcastexception)
        {
            return s;
        }
        return s1;
    }

    public Property[] getSupportedProperties()
    {
        return new Property[0];
    }

    public int getTotalCount()
    {
        return -1;
    }

    public boolean hasMore()
    {
        return false;
    }

    protected void init()
    {
        mStateMachine.init();
    }

    public final boolean isEnable()
    {
        return mStateMachine.isEnable();
    }

    protected final boolean isInit()
    {
        return mStateMachine.isInit();
    }

    public boolean isReady()
    {
        while (getTotalCount() == -1 || getCount() < getTotalCount()) 
        {
            return false;
        }
        return true;
    }

    public final boolean isResume()
    {
        return mStateMachine.isResume();
    }

    protected void loadMore(int i)
    {
    }

    protected void notifyOnCountChanged(int i, int j)
    {
        IDataSource.OnDataChangeListener aondatachangelistener[] = getDataChangeListenersCopy();
        if (aondatachangelistener != null)
        {
            int k = aondatachangelistener.length;
            for (int l = 0; l < k; l++)
            {
                aondatachangelistener[l].onCountChanged(i, j);
            }

        }
    }

    protected void notifyOnDataChanged(int i, Property property)
    {
        IDataSource.OnDataChangeListener aondatachangelistener[] = getDataChangeListenersCopy();
        if (aondatachangelistener != null)
        {
            int j = aondatachangelistener.length;
            for (int k = 0; k < j; k++)
            {
                aondatachangelistener[k].onDataChanged(i, property);
            }

        }
    }

    protected void onAfterDisable()
    {
    }

    protected void onAfterEnable()
    {
    }

    protected void onAfterInit()
    {
    }

    protected void onAfterPause()
    {
    }

    protected void onAfterResume()
    {
    }

    protected void onAfterUninit()
    {
    }

    protected IDataSource.IController onCreateController(int i)
    {
        return createController(i);
    }

    protected void onDisable()
    {
    }

    protected void onEnable()
    {
    }

    protected void onInit()
    {
    }

    protected void onPause()
    {
    }

    protected void onReleaseController()
    {
    }

    protected void onResume()
    {
    }

    protected void onUninit()
    {
    }

    protected transient void prefetch(int i, int j, Property aproperty[])
    {
    }

    protected transient void prefetchEx(int ai[], Property aproperty[])
    {
    }

    protected void refresh()
    {
    }

    public void registerOnDataBuildListener(IDataSource.OnDataBuildListener ondatabuildlistener)
    {
        if (!mDataBuildListeners.contains(ondatabuildlistener))
        {
            mDataBuildListeners.add(ondatabuildlistener);
        }
    }

    public void registerOnDataChangeListener(IDataSource.OnDataChangeListener ondatachangelistener)
    {
        if (!mDataChangeListeners.contains(ondatachangelistener))
        {
            mDataChangeListeners.add(ondatachangelistener);
        }
    }

    protected void setResourceType(boolean flag)
    {
    }

    protected void sort(Property property, boolean flag)
    {
    }

    void testCursor(String s, Cursor cursor)
    {
        if (cursor == null)
        {
            Log.d("test", (new StringBuilder()).append(s).append(" cursor is null").toString());
            return;
        } else
        {
            Log.d("test", (new StringBuilder()).append(s).append(" cursor's count is = ").append(cursor.getCount()).toString());
            return;
        }
    }

    protected void unInit()
    {
        mStateMachine.unInit();
    }

    public void unregisterOnDataBuildListener(IDataSource.OnDataBuildListener ondatabuildlistener)
    {
        if (mDataBuildListeners.contains(ondatabuildlistener))
        {
            mDataBuildListeners.remove(ondatabuildlistener);
        }
    }

    public void unregisterOnDataChangeListener(IDataSource.OnDataChangeListener ondatachangelistener)
    {
        if (mDataChangeListeners.contains(ondatachangelistener))
        {
            mDataChangeListeners.remove(ondatachangelistener);
        }
    }










}
