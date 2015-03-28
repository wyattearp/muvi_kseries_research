// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.arcsoft.mediaplus.playengine;

import android.net.Uri;
import com.arcsoft.util.debug.Log;
import java.util.ArrayList;

public abstract class AbsPlayList
{
    public static interface IOnPlaylistChangeListener
    {

        public abstract void onPlayIndexChanged(int i);

        public abstract void onPlayIndexReady(int i);

        public abstract void onPlaylistUpdated(int i, int j, boolean flag);
    }

    public static final class RepeatMode extends Enum
    {

        private static final RepeatMode $VALUES[];
        public static final RepeatMode NoRepeat;
        public static final RepeatMode RepeatAll;
        public static final RepeatMode RepeatOne;

        public static RepeatMode valueOf(String s)
        {
            return (RepeatMode)Enum.valueOf(com/arcsoft/mediaplus/playengine/AbsPlayList$RepeatMode, s);
        }

        public static RepeatMode[] values()
        {
            return (RepeatMode[])$VALUES.clone();
        }

        static 
        {
            NoRepeat = new RepeatMode("NoRepeat", 0);
            RepeatOne = new RepeatMode("RepeatOne", 1);
            RepeatAll = new RepeatMode("RepeatAll", 2);
            RepeatMode arepeatmode[] = new RepeatMode[3];
            arepeatmode[0] = NoRepeat;
            arepeatmode[1] = RepeatOne;
            arepeatmode[2] = RepeatAll;
            $VALUES = arepeatmode;
        }

        private RepeatMode(String s, int i)
        {
            super(s, i);
        }
    }


    private long mCurrentId;
    private int mCurrentIndex;
    private IOnPlaylistChangeListener mListener;
    protected RepeatMode mRepeatMode;
    protected boolean mShuffle;
    private ArrayList mShuffleMapList;

    public AbsPlayList()
    {
        mShuffle = false;
        mRepeatMode = RepeatMode.NoRepeat;
        mShuffleMapList = new ArrayList();
        mCurrentIndex = -1;
        mCurrentId = 0L;
        mListener = null;
    }

    private void buildShuffleList(int i)
    {
        mShuffleMapList.clear();
        for (int j = 0; j < i; j++)
        {
            mShuffleMapList.add(Integer.valueOf(j));
        }

        for (int k = i + 1; k < getCount(); k++)
        {
            mShuffleMapList.add(Integer.valueOf(k));
        }

        for (int l = 0; l < mShuffleMapList.size(); l++)
        {
            int i1 = (int)(Math.random() * (double)mShuffleMapList.size() - 1.0D);
            int j1 = ((Integer)mShuffleMapList.get(0)).intValue();
            mShuffleMapList.set(0, mShuffleMapList.get(i1));
            mShuffleMapList.set(i1, Integer.valueOf(j1));
        }

        if (i >= 0)
        {
            mShuffleMapList.add(0, Integer.valueOf(i));
        }
        if (mShuffleMapList.size() != getCount())
        {
            throw new UnknownError("mShuffleMapList.size() != getCount()");
        } else
        {
            return;
        }
    }

    private int getMediaIndexByPlayListIndex(int i)
    {
        if (i < 0 || i >= getCount())
        {
            return -1;
        }
        if (isShuffled())
        {
            i = ((Integer)mShuffleMapList.get(i)).intValue();
        }
        return i;
    }

    private int getNextPlaylistIndex(boolean flag)
    {
        int i;
        RepeatMode repeatmode;
        if (getCount() <= 0)
        {
            return -1;
        }
        i = -1;
        repeatmode = mRepeatMode;
        if (flag && repeatmode == RepeatMode.RepeatOne)
        {
            repeatmode = RepeatMode.NoRepeat;
        }
        static class _cls1
        {

            static final int $SwitchMap$com$arcsoft$mediaplus$playengine$AbsPlayList$RepeatMode[];

            static 
            {
                $SwitchMap$com$arcsoft$mediaplus$playengine$AbsPlayList$RepeatMode = new int[RepeatMode.values().length];
                try
                {
                    $SwitchMap$com$arcsoft$mediaplus$playengine$AbsPlayList$RepeatMode[RepeatMode.RepeatAll.ordinal()] = 1;
                }
                catch (NoSuchFieldError nosuchfielderror) { }
                try
                {
                    $SwitchMap$com$arcsoft$mediaplus$playengine$AbsPlayList$RepeatMode[RepeatMode.RepeatOne.ordinal()] = 2;
                }
                catch (NoSuchFieldError nosuchfielderror1) { }
                try
                {
                    $SwitchMap$com$arcsoft$mediaplus$playengine$AbsPlayList$RepeatMode[RepeatMode.NoRepeat.ordinal()] = 3;
                }
                catch (NoSuchFieldError nosuchfielderror2)
                {
                    return;
                }
            }
        }

        _cls1..SwitchMap.com.arcsoft.mediaplus.playengine.AbsPlayList.RepeatMode[repeatmode.ordinal()];
        JVM INSTR tableswitch 1 3: default 64
    //                   1 66
    //                   2 94
    //                   3 114;
           goto _L1 _L2 _L3 _L4
_L1:
        return i;
_L2:
        if (mCurrentIndex >= -1 + getCount())
        {
            i = 0;
        } else
        {
            i = 1 + mCurrentIndex;
        }
        continue; /* Loop/switch isn't completed */
_L3:
        if (mCurrentIndex < 0)
        {
            i = 0;
        } else
        {
            i = mCurrentIndex;
        }
        continue; /* Loop/switch isn't completed */
_L4:
        if (mCurrentIndex >= -1 + getCount())
        {
            i = -1;
        } else
        {
            i = 1 + mCurrentIndex;
        }
        if (true) goto _L1; else goto _L5
_L5:
    }

    private int getPlayListIndexByMediaIndex(int i)
    {
        if (i < 0 || i >= getCount())
        {
            return -1;
        }
        if (!isShuffled()) goto _L2; else goto _L1
_L1:
        int j = 0;
_L7:
        if (j >= getCount()) goto _L2; else goto _L3
_L3:
        if (((Integer)mShuffleMapList.get(j)).intValue() != i) goto _L5; else goto _L4
_L4:
        i = j;
_L2:
        return i;
_L5:
        j++;
        if (true) goto _L7; else goto _L6
_L6:
    }

    private int getPrevPlaylistIndex(boolean flag)
    {
        int i;
        RepeatMode repeatmode;
        if (getCount() <= 0)
        {
            return -1;
        }
        i = -1;
        repeatmode = mRepeatMode;
        if (flag && repeatmode == RepeatMode.RepeatOne)
        {
            repeatmode = RepeatMode.NoRepeat;
        }
        _cls1..SwitchMap.com.arcsoft.mediaplus.playengine.AbsPlayList.RepeatMode[repeatmode.ordinal()];
        JVM INSTR tableswitch 1 3: default 64
    //                   1 66
    //                   2 93
    //                   3 101;
           goto _L1 _L2 _L3 _L4
_L1:
        return i;
_L2:
        if (mCurrentIndex <= 0)
        {
            i = -1 + getCount();
        } else
        {
            i = -1 + mCurrentIndex;
        }
        continue; /* Loop/switch isn't completed */
_L3:
        i = mCurrentIndex;
        continue; /* Loop/switch isn't completed */
_L4:
        if (mCurrentIndex <= 0)
        {
            i = -1;
        } else
        {
            i = -1 + mCurrentIndex;
        }
        if (true) goto _L1; else goto _L5
_L5:
    }

    public abstract int getChannelID(int i);

    public abstract int getCount();

    public int getCurrentIndex()
    {
        return getMediaIndexByPlayListIndex(mCurrentIndex);
    }

    public abstract long getId(int i);

    public int getNextIndex(boolean flag)
    {
        int i = getNextPlaylistIndex(flag);
        if (i >= 0)
        {
            i = getMediaIndexByPlayListIndex(i);
        }
        return i;
    }

    public int getPrevIndex(boolean flag)
    {
        int i = getPrevPlaylistIndex(flag);
        if (i >= 0)
        {
            i = getMediaIndexByPlayListIndex(i);
        }
        return i;
    }

    public abstract com.arcsoft.adk.atv.UPnP.RemoteItemDesc getRemoteItemDesc(int i);

    public RepeatMode getRepeatMode()
    {
        return mRepeatMode;
    }

    public abstract Uri getUri(int i);

    protected void invalidCount()
    {
        int i;
        long l;
        int j;
        boolean flag;
        mCurrentIndex = -1;
        mShuffleMapList.clear();
        i = -1;
        l = mCurrentId;
        mCurrentId = 0L;
        j = l != 0L;
        flag = false;
        if (j == 0) goto _L2; else goto _L1
_L1:
        int k = 0;
_L7:
        int i1;
        i1 = getCount();
        flag = false;
        if (k >= i1) goto _L2; else goto _L3
_L3:
        if (getId(k) != l) goto _L5; else goto _L4
_L4:
        flag = true;
        i = k;
        mCurrentId = getId(k);
_L2:
        if (isShuffled())
        {
            buildShuffleList(i);
        }
        mCurrentIndex = getPlayListIndexByMediaIndex(i);
        if (mListener != null)
        {
            mListener.onPlaylistUpdated(getCount(), i, flag);
        }
        return;
_L5:
        k++;
        if (true) goto _L7; else goto _L6
_L6:
    }

    public boolean isShuffled()
    {
        return mShuffle;
    }

    public int next(boolean flag)
    {
        int i = getNextPlaylistIndex(flag);
        if (i >= 0)
        {
            int j = getMediaIndexByPlayListIndex(mCurrentIndex);
            mCurrentIndex = i;
            i = getMediaIndexByPlayListIndex(mCurrentIndex);
            mCurrentId = getId(i);
            if (j != i && mListener != null)
            {
                mListener.onPlayIndexChanged(i);
            }
        }
        return i;
    }

    protected void notifyDelaySetCurrentIndex()
    {
        if (mListener != null)
        {
            mListener.onPlayIndexReady(mCurrentIndex);
        }
    }

    public int prev(boolean flag)
    {
        int i = getPrevPlaylistIndex(flag);
        if (i >= 0)
        {
            int j = getMediaIndexByPlayListIndex(mCurrentIndex);
            mCurrentIndex = i;
            i = getMediaIndexByPlayListIndex(mCurrentIndex);
            mCurrentId = getId(i);
            if (j != i && mListener != null)
            {
                mListener.onPlayIndexChanged(i);
            }
        }
        return i;
    }

    public void setCurrentIndex(int i)
    {
        if (i < 0 || i >= getCount())
        {
            Log.w("Playlist", "Index is invalid");
        } else
        if (getMediaIndexByPlayListIndex(mCurrentIndex) != i)
        {
            mCurrentId = getId(i);
            mCurrentIndex = getPlayListIndexByMediaIndex(i);
            if (mListener != null)
            {
                mListener.onPlayIndexChanged(i);
                return;
            }
        }
    }

    public void setOnPlaylistChangeListener(IOnPlaylistChangeListener ionplaylistchangelistener)
    {
        mListener = ionplaylistchangelistener;
    }

    public void setRepeatMode(RepeatMode repeatmode)
    {
        mRepeatMode = repeatmode;
    }

    public void setShuffle(boolean flag)
    {
        if (mShuffle == flag)
        {
            return;
        }
        int i = getMediaIndexByPlayListIndex(mCurrentIndex);
        if (!flag)
        {
            mShuffleMapList.clear();
        } else
        {
            buildShuffleList(i);
        }
        mShuffle = flag;
        mCurrentIndex = getPlayListIndexByMediaIndex(i);
    }
}
