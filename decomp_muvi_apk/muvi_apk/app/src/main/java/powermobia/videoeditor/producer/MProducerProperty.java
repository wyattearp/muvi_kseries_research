// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package powermobia.videoeditor.producer;


public class MProducerProperty
{

    public static final int AUDIO_FORMAT = 2;
    public static final int FILE_FORMAT = 0;
    public static final int VIDEO_BITRATE = 4;
    public static final int VIDEO_FORMAT = 1;
    public static final int VIDEO_FRAME_RATE = 3;
    private int audioFormat;
    private String destFile;
    private int fileFormat;
    private long maxFileSize;
    private int videoBitrate;
    private int videoFormat;
    private int videoFrameRate;

    public MProducerProperty()
    {
        fileFormat = 0;
        videoFormat = 0;
        audioFormat = 0;
        videoFrameRate = 0;
        videoBitrate = 0;
        maxFileSize = 0L;
        destFile = null;
    }

    public MProducerProperty(int i, int j, int k, int l, int i1, long l1, 
            String s)
    {
        fileFormat = 0;
        videoFormat = 0;
        audioFormat = 0;
        videoFrameRate = 0;
        videoBitrate = 0;
        maxFileSize = 0L;
        destFile = null;
        fileFormat = i;
        videoFormat = j;
        audioFormat = k;
        videoFrameRate = l;
        videoBitrate = i1;
        maxFileSize = l1;
        destFile = s;
    }

    public int get(int i)
    {
        switch (i)
        {
        default:
            return 0;

        case 0: // '\0'
            return fileFormat;

        case 1: // '\001'
            return videoFormat;

        case 2: // '\002'
            return audioFormat;

        case 3: // '\003'
            return videoFrameRate;

        case 4: // '\004'
            return videoBitrate;
        }
    }

    public String getDestFile()
    {
        return destFile;
    }

    public long getMaxFileSize()
    {
        return maxFileSize;
    }

    public void set(int i, int j)
    {
        switch (i)
        {
        default:
            return;

        case 0: // '\0'
            fileFormat = j;
            return;

        case 1: // '\001'
            videoFormat = j;
            return;

        case 2: // '\002'
            audioFormat = j;
            return;

        case 3: // '\003'
            videoFrameRate = j;
            return;

        case 4: // '\004'
            videoBitrate = j;
            break;
        }
    }

    public void setDestFile(String s)
    {
        destFile = s;
    }

    public void setMaxFileSize(long l)
    {
        maxFileSize = l;
    }
}
