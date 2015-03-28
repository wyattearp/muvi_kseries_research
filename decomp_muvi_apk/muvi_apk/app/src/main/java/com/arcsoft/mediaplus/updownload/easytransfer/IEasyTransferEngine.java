// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.arcsoft.mediaplus.updownload.easytransfer;


public interface IEasyTransferEngine
{
    public static interface IOnEasyTransferEngineListener
    {

        public abstract void onEasyTransferFinish(String s, long l);

        public abstract void onEasyTransferStart(String s, long l, int i);

        public abstract void onEasyTransfering(String s, long l, int i, int j);
    }

    public static interface RecordDay
    {

        public static final int DAY_THREE = 3;
        public static final int NO_LIMITE = 0;
        public static final int WEEK_ONE = 7;
        public static final int WEEK_THREE = 14;
    }

    public static class Request
    {

        public boolean enableAllow;
        public boolean enablePlugIn;
        public int recordDay;
        public String servername;
        public String serverudn;
        public int startHour;
        public int startMinute;

        public Request()
        {
            recordDay = 0;
            enableAllow = false;
            enablePlugIn = false;
        }
    }

    public static class Result
    {

        public Request request;
        public int serverState;

        public Result()
        {
            serverState = 1;
        }
    }

    public static interface ServerState
    {

        public static final int STATE_CANCELLING = 4;
        public static final int STATE_IMMEDIATE = 3;
        public static final int STATE_NONE = 0;
        public static final int STATE_PENDING = 1;
        public static final int STATE_RETRY = 5;
        public static final int STATE_RETRY_WAIT = 7;
        public static final int STATE_RUNNING = 2;
        public static final int STATE_RUNNING_WAIT = 6;
    }


    public static final boolean ALLOW_MOVE = false;
    public static final boolean ENABLE_PLUGIN = false;
    public static final int RETRY_COUNT = 5;
    public static final int RETRY_PERIOD = 0x1d4c0;

    public abstract boolean cancelEasyTransfer(long l);

    public abstract boolean cancelEasyTransfer(String s);

    public abstract boolean deleteEasyTransfer(long l);

    public abstract boolean deleteEasyTransfer(String s);

    public abstract boolean executeEasyTransfer(long l);

    public abstract boolean executeEasyTransfer(String s);

    public abstract int getCount();

    public abstract Result getEasyTransfer(long l);

    public abstract Result getEasyTransfer(String s);

    public abstract int getServerState(long l);

    public abstract int getServerState(String s);

    public abstract long getTableid(int i);

    public abstract boolean registerEasyTransfer(Request request);

    public abstract void registerListener(IOnEasyTransferEngineListener ioneasytransferenginelistener);

    public abstract void start();

    public abstract void stop();

    public abstract void unregisterListener(IOnEasyTransferEngineListener ioneasytransferenginelistener);

    public abstract boolean updateEasyTransfer(long l, Request request);

    public abstract boolean updateEasyTransfer(String s, Request request);
}
