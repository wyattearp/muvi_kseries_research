// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.arcsoft.mediaplus.playengine;

import android.net.Uri;

public interface IPlayEngine
{
    public static interface IPlayEngineListener
    {

        public abstract void OnBuffering(int i);

        public abstract void OnComplete();

        public abstract void OnError(PlayEngineError playengineerror);

        public abstract void OnMute(boolean flag);

        public abstract void OnOpened();

        public abstract void OnOpening();

        public abstract void OnPaused();

        public abstract void OnPausing();

        public abstract void OnProgressChanged(long l, long l1);

        public abstract void OnSeeked(long l);

        public abstract void OnSeeking();

        public abstract void OnStartedPlay();

        public abstract void OnStartingPlay();

        public abstract void OnStopped();

        public abstract void OnStopping();

        public abstract void OnVolumeChanged(int i);
    }

    public static final class PLAYSTATUS extends Enum
    {

        private static final PLAYSTATUS $VALUES[];
        public static final PLAYSTATUS STATUS_BUFFERING;
        public static final PLAYSTATUS STATUS_IDLE;
        public static final PLAYSTATUS STATUS_OPENED;
        public static final PLAYSTATUS STATUS_OPENING;
        public static final PLAYSTATUS STATUS_PAUSED;
        public static final PLAYSTATUS STATUS_PAUSING;
        public static final PLAYSTATUS STATUS_PLAYING;
        public static final PLAYSTATUS STATUS_SEEKING;
        public static final PLAYSTATUS STATUS_STARTINGPLAY;
        public static final PLAYSTATUS STATUS_STOPPED;
        public static final PLAYSTATUS STATUS_STOPPING;

        public static PLAYSTATUS valueOf(String s)
        {
            return (PLAYSTATUS)Enum.valueOf(com/arcsoft/mediaplus/playengine/IPlayEngine$PLAYSTATUS, s);
        }

        public static PLAYSTATUS[] values()
        {
            return (PLAYSTATUS[])$VALUES.clone();
        }

        static 
        {
            STATUS_IDLE = new PLAYSTATUS("STATUS_IDLE", 0);
            STATUS_OPENING = new PLAYSTATUS("STATUS_OPENING", 1);
            STATUS_OPENED = new PLAYSTATUS("STATUS_OPENED", 2);
            STATUS_STARTINGPLAY = new PLAYSTATUS("STATUS_STARTINGPLAY", 3);
            STATUS_PLAYING = new PLAYSTATUS("STATUS_PLAYING", 4);
            STATUS_BUFFERING = new PLAYSTATUS("STATUS_BUFFERING", 5);
            STATUS_PAUSING = new PLAYSTATUS("STATUS_PAUSING", 6);
            STATUS_PAUSED = new PLAYSTATUS("STATUS_PAUSED", 7);
            STATUS_STOPPING = new PLAYSTATUS("STATUS_STOPPING", 8);
            STATUS_STOPPED = new PLAYSTATUS("STATUS_STOPPED", 9);
            STATUS_SEEKING = new PLAYSTATUS("STATUS_SEEKING", 10);
            PLAYSTATUS aplaystatus[] = new PLAYSTATUS[11];
            aplaystatus[0] = STATUS_IDLE;
            aplaystatus[1] = STATUS_OPENING;
            aplaystatus[2] = STATUS_OPENED;
            aplaystatus[3] = STATUS_STARTINGPLAY;
            aplaystatus[4] = STATUS_PLAYING;
            aplaystatus[5] = STATUS_BUFFERING;
            aplaystatus[6] = STATUS_PAUSING;
            aplaystatus[7] = STATUS_PAUSED;
            aplaystatus[8] = STATUS_STOPPING;
            aplaystatus[9] = STATUS_STOPPED;
            aplaystatus[10] = STATUS_SEEKING;
            $VALUES = aplaystatus;
        }

        private PLAYSTATUS(String s, int i)
        {
            super(s, i);
        }
    }

    public static final class PlayEngineError extends Enum
    {

        private static final PlayEngineError $VALUES[];
        public static final PlayEngineError ERROR_GETMUTE;
        public static final PlayEngineError ERROR_GETVOLUME;
        public static final PlayEngineError ERROR_OPENFILE;
        public static final PlayEngineError ERROR_PAUSE;
        public static final PlayEngineError ERROR_PLAY;
        public static final PlayEngineError ERROR_RENDER_DISCONNECTED;
        public static final PlayEngineError ERROR_SEEK;
        public static final PlayEngineError ERROR_SERVER_DISCONNECTED;
        public static final PlayEngineError ERROR_STOP;
        public static final PlayEngineError ERROR_UNENCRYPTED_WIFI;
        public static final PlayEngineError ERROR_UNKNOWN;
        public static final PlayEngineError ERROR_UNSUPPORT;

        public static PlayEngineError valueOf(String s)
        {
            return (PlayEngineError)Enum.valueOf(com/arcsoft/mediaplus/playengine/IPlayEngine$PlayEngineError, s);
        }

        public static PlayEngineError[] values()
        {
            return (PlayEngineError[])$VALUES.clone();
        }

        static 
        {
            ERROR_UNENCRYPTED_WIFI = new PlayEngineError("ERROR_UNENCRYPTED_WIFI", 0);
            ERROR_SERVER_DISCONNECTED = new PlayEngineError("ERROR_SERVER_DISCONNECTED", 1);
            ERROR_RENDER_DISCONNECTED = new PlayEngineError("ERROR_RENDER_DISCONNECTED", 2);
            ERROR_UNKNOWN = new PlayEngineError("ERROR_UNKNOWN", 3);
            ERROR_OPENFILE = new PlayEngineError("ERROR_OPENFILE", 4);
            ERROR_PLAY = new PlayEngineError("ERROR_PLAY", 5);
            ERROR_PAUSE = new PlayEngineError("ERROR_PAUSE", 6);
            ERROR_SEEK = new PlayEngineError("ERROR_SEEK", 7);
            ERROR_STOP = new PlayEngineError("ERROR_STOP", 8);
            ERROR_GETVOLUME = new PlayEngineError("ERROR_GETVOLUME", 9);
            ERROR_GETMUTE = new PlayEngineError("ERROR_GETMUTE", 10);
            ERROR_UNSUPPORT = new PlayEngineError("ERROR_UNSUPPORT", 11);
            PlayEngineError aplayengineerror[] = new PlayEngineError[12];
            aplayengineerror[0] = ERROR_UNENCRYPTED_WIFI;
            aplayengineerror[1] = ERROR_SERVER_DISCONNECTED;
            aplayengineerror[2] = ERROR_RENDER_DISCONNECTED;
            aplayengineerror[3] = ERROR_UNKNOWN;
            aplayengineerror[4] = ERROR_OPENFILE;
            aplayengineerror[5] = ERROR_PLAY;
            aplayengineerror[6] = ERROR_PAUSE;
            aplayengineerror[7] = ERROR_SEEK;
            aplayengineerror[8] = ERROR_STOP;
            aplayengineerror[9] = ERROR_GETVOLUME;
            aplayengineerror[10] = ERROR_GETMUTE;
            aplayengineerror[11] = ERROR_UNSUPPORT;
            $VALUES = aplayengineerror;
        }

        private PlayEngineError(String s, int i)
        {
            super(s, i);
        }
    }


    public static final int GET_POSITION_INTERVAL = 1000;
    public static final int MAX_BUFFERING_PERCENT = 100;
    public static final int MAX_VOLUME = 100;
    public static final int MV2_AUDIO_OUTPUT_LEFT = 1;
    public static final int MV2_AUDIO_OUTPUT_RIGHT = 2;
    public static final int MV2_AUDIO_OUTPUT_STEREO = 3;

    public abstract long getDuration();

    public abstract long getPosition();

    public abstract PLAYSTATUS getStatus();

    public abstract int getVolume();

    public abstract boolean isMute();

    public abstract boolean open(Uri uri);

    public abstract boolean pause();

    public abstract boolean play();

    public abstract boolean seekTo(long l);

    public abstract boolean setMute(boolean flag);

    public abstract boolean setVolume(int i);

    public abstract boolean stop();
}
