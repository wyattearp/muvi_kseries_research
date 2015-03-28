// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.arcsoft.mediaplus.playengine;


public interface IPlayer
{
    public static interface IPlayerListener
    {

        public abstract void onBuffering();

        public abstract void onCompleted();

        public abstract void onError(PlayerError playererror);

        public abstract void onMute(boolean flag);

        public abstract void onPaused();

        public abstract void onPlayIndexChanged(int i);

        public abstract void onPlayStarted();

        public abstract void onProgressChanged(long l, long l1);

        public abstract void onSeeked();

        public abstract void onStopped();

        public abstract void onVolumeChanged(int i);
    }

    public static final class PlayerError extends Enum
    {

        private static final PlayerError $VALUES[];
        public static final PlayerError ERROR_OPENFILE;
        public static final PlayerError ERROR_PLAY;
        public static final PlayerError ERROR_PLAYLIST_URI_NULL;
        public static final PlayerError ERROR_RENDER_DISCONNECTED;
        public static final PlayerError ERROR_SEEK;
        public static final PlayerError ERROR_SERVER_DISCONNECTED;
        public static final PlayerError ERROR_UNENCRYPTED_WIFI;
        public static final PlayerError ERROR_UNKNOWN;
        public static final PlayerError ERROR_UNSUPPORT;

        public static PlayerError valueOf(String s)
        {
            return (PlayerError)Enum.valueOf(com/arcsoft/mediaplus/playengine/IPlayer$PlayerError, s);
        }

        public static PlayerError[] values()
        {
            return (PlayerError[])$VALUES.clone();
        }

        static 
        {
            ERROR_UNENCRYPTED_WIFI = new PlayerError("ERROR_UNENCRYPTED_WIFI", 0);
            ERROR_RENDER_DISCONNECTED = new PlayerError("ERROR_RENDER_DISCONNECTED", 1);
            ERROR_SERVER_DISCONNECTED = new PlayerError("ERROR_SERVER_DISCONNECTED", 2);
            ERROR_UNKNOWN = new PlayerError("ERROR_UNKNOWN", 3);
            ERROR_OPENFILE = new PlayerError("ERROR_OPENFILE", 4);
            ERROR_PLAY = new PlayerError("ERROR_PLAY", 5);
            ERROR_SEEK = new PlayerError("ERROR_SEEK", 6);
            ERROR_UNSUPPORT = new PlayerError("ERROR_UNSUPPORT", 7);
            ERROR_PLAYLIST_URI_NULL = new PlayerError("ERROR_PLAYLIST_URI_NULL", 8);
            PlayerError aplayererror[] = new PlayerError[9];
            aplayererror[0] = ERROR_UNENCRYPTED_WIFI;
            aplayererror[1] = ERROR_RENDER_DISCONNECTED;
            aplayererror[2] = ERROR_SERVER_DISCONNECTED;
            aplayererror[3] = ERROR_UNKNOWN;
            aplayererror[4] = ERROR_OPENFILE;
            aplayererror[5] = ERROR_PLAY;
            aplayererror[6] = ERROR_SEEK;
            aplayererror[7] = ERROR_UNSUPPORT;
            aplayererror[8] = ERROR_PLAYLIST_URI_NULL;
            $VALUES = aplayererror;
        }

        private PlayerError(String s, int i)
        {
            super(s, i);
        }
    }

    public static final class Status extends Enum
    {

        private static final Status $VALUES[];
        public static final Status STATUS_BUFFERING;
        public static final Status STATUS_IDLE;
        public static final Status STATUS_OPENING;
        public static final Status STATUS_PAUSED;
        public static final Status STATUS_PAUSING;
        public static final Status STATUS_PLAYING;
        public static final Status STATUS_SEEKING;
        public static final Status STATUS_STARTSEEK;
        public static final Status STATUS_STOPPED;
        public static final Status STATUS_STOPPING;

        public static Status valueOf(String s)
        {
            return (Status)Enum.valueOf(com/arcsoft/mediaplus/playengine/IPlayer$Status, s);
        }

        public static Status[] values()
        {
            return (Status[])$VALUES.clone();
        }

        static 
        {
            STATUS_IDLE = new Status("STATUS_IDLE", 0);
            STATUS_OPENING = new Status("STATUS_OPENING", 1);
            STATUS_PLAYING = new Status("STATUS_PLAYING", 2);
            STATUS_BUFFERING = new Status("STATUS_BUFFERING", 3);
            STATUS_PAUSING = new Status("STATUS_PAUSING", 4);
            STATUS_PAUSED = new Status("STATUS_PAUSED", 5);
            STATUS_STOPPING = new Status("STATUS_STOPPING", 6);
            STATUS_STOPPED = new Status("STATUS_STOPPED", 7);
            STATUS_STARTSEEK = new Status("STATUS_STARTSEEK", 8);
            STATUS_SEEKING = new Status("STATUS_SEEKING", 9);
            Status astatus[] = new Status[10];
            astatus[0] = STATUS_IDLE;
            astatus[1] = STATUS_OPENING;
            astatus[2] = STATUS_PLAYING;
            astatus[3] = STATUS_BUFFERING;
            astatus[4] = STATUS_PAUSING;
            astatus[5] = STATUS_PAUSED;
            astatus[6] = STATUS_STOPPING;
            astatus[7] = STATUS_STOPPED;
            astatus[8] = STATUS_STARTSEEK;
            astatus[9] = STATUS_SEEKING;
            $VALUES = astatus;
        }

        private Status(String s, int i)
        {
            super(s, i);
        }
    }


    public static final long PLAY_FAST_BACKWARD = 10000L;
    public static final long PLAY_FAST_FORWARD = 30000L;
    public static final int PLAY_PREV_RESTRICT = 5000;

    public abstract void endSeek();

    public abstract boolean fastBackward();

    public abstract boolean fastForward();

    public abstract long getDuration();

    public abstract long getPosition();

    public abstract Status getStatus();

    public abstract int getVolume();

    public abstract boolean isMute();

    public abstract boolean pause();

    public abstract boolean play(int i, long l);

    public abstract boolean playnext();

    public abstract boolean playprev();

    public abstract boolean resume();

    public abstract void seekTo(long l);

    public abstract boolean setMute(boolean flag);

    public abstract boolean setVolume(int i);

    public abstract boolean startSeek();

    public abstract void stop();
}
