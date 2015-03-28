// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package powermobia.videoeditor.base;


public class MVideoInfo
{

    public static final int AUDIOFORMAT_AACLC = 4;
    public static final int AUDIOFORMAT_AMRNB = 2;
    public static final int AUDIOFORMAT_AMRWB = 7;
    public static final int AUDIOFORMAT_AUTO = 1;
    public static final int AUDIOFORMAT_EVRC = 5;
    public static final int AUDIOFORMAT_MP3 = 6;
    public static final int AUDIOFORMAT_NONE = 0;
    public static final int AUDIOFORMAT_QCELP = 3;
    public static final int AUDIOFORMAT_UNKNOWN = 1;
    public static final int AUDIOFORMAT_WMA_9LOS = 11;
    public static final int AUDIOFORMAT_WMA_9PRO = 10;
    public static final int AUDIOFORMAT_WMA_V1 = 8;
    public static final int AUDIOFORMAT_WMA_V2 = 9;
    public static final int AUDIO_BITS_PER_SAMPLE = 14;
    public static final int AUDIO_BIT_RATE = 13;
    public static final int AUDIO_BLOCK_ALIGN = 15;
    public static final int AUDIO_CHANNEL = 12;
    public static final int AUDIO_DURATION = 6;
    public static final int AUDIO_FORMAT = 2;
    public static final int AUDIO_SAMPLE_RATE = 11;
    public static final int BIT_RATE = 8;
    public static final int FILEFORMAT_3G2 = 3;
    public static final int FILEFORMAT_3GP = 2;
    public static final int FILEFORMAT_AAC = 6;
    public static final int FILEFORMAT_AMR = 5;
    public static final int FILEFORMAT_ASF = 12;
    public static final int FILEFORMAT_AVI = 9;
    public static final int FILEFORMAT_K3G = 13;
    public static final int FILEFORMAT_M4A = 4;
    public static final int FILEFORMAT_MP3 = 14;
    public static final int FILEFORMAT_MP4 = 1;
    public static final int FILEFORMAT_QCP = 7;
    public static final int FILEFORMAT_SKM = 8;
    public static final int FILEFORMAT_UNKNOWN = 0;
    public static final int FILEFORMAT_WMA = 10;
    public static final int FILEFORMAT_WMV = 11;
    public static final int FILE_FORMAT = 0;
    public static final int FILE_SIZE = 7;
    public static final int FRAME_HEIGHT = 4;
    public static final int FRAME_WIDTH = 3;
    public static final int VIDEOFORMAT_AUTO = 1;
    public static final int VIDEOFORMAT_DX50 = 5;
    public static final int VIDEOFORMAT_H263 = 3;
    public static final int VIDEOFORMAT_H264 = 4;
    public static final int VIDEOFORMAT_MPEG4 = 2;
    public static final int VIDEOFORMAT_NONE = 0;
    public static final int VIDEOFORMAT_UNKNOWN = 1;
    public static final int VIDEOFORMAT_WMV = 6;
    public static final int VIDEOFORMAT_XVID = 7;
    public static final int VIDEO_BIT_RATE = 10;
    public static final int VIDEO_DURATION = 5;
    public static final int VIDEO_FORMAT = 1;
    public static final int VIDEO_FRAME_RATE = 9;
    private int audioBitrate;
    private int audioBitsPerSample;
    private int audioBlockAlign;
    private int audioChannel;
    private int audioDuration;
    private int audioFormat;
    private int audioSampleRate;
    private int bitrate;
    private int fileFormat;
    private int fileSize;
    private int frameHeight;
    private int frameWidth;
    private int videoBitrate;
    private int videoDuration;
    private int videoFormat;
    private int videoFrameRate;

    public MVideoInfo()
    {
        fileFormat = 0;
        videoFormat = 0;
        audioFormat = 0;
        frameWidth = 0;
        frameHeight = 0;
        videoDuration = 0;
        audioDuration = 0;
        fileSize = 0;
        bitrate = 0;
        videoFrameRate = 0;
        videoBitrate = 0;
        audioSampleRate = 0;
        audioChannel = 0;
        audioBitrate = 0;
        audioBitsPerSample = 0;
        audioBlockAlign = 0;
    }

    public MVideoInfo(int i, int j, int k, int l, int i1, int j1, int k1, 
            int l1, int i2, int j2, int k2, int l2, int i3, int j3, 
            int k3, int l3)
    {
        fileFormat = 0;
        videoFormat = 0;
        audioFormat = 0;
        frameWidth = 0;
        frameHeight = 0;
        videoDuration = 0;
        audioDuration = 0;
        fileSize = 0;
        bitrate = 0;
        videoFrameRate = 0;
        videoBitrate = 0;
        audioSampleRate = 0;
        audioChannel = 0;
        audioBitrate = 0;
        audioBitsPerSample = 0;
        audioBlockAlign = 0;
        fileFormat = i;
        videoFormat = j;
        audioFormat = k;
        frameWidth = l;
        frameHeight = i1;
        videoDuration = j1;
        audioDuration = k1;
        fileSize = l1;
        bitrate = i2;
        videoFrameRate = j2;
        videoBitrate = k2;
        audioSampleRate = l2;
        audioChannel = i3;
        audioBitrate = j3;
        audioBitsPerSample = k3;
        audioBlockAlign = l3;
    }

    public MVideoInfo(MVideoInfo mvideoinfo)
    {
        fileFormat = 0;
        videoFormat = 0;
        audioFormat = 0;
        frameWidth = 0;
        frameHeight = 0;
        videoDuration = 0;
        audioDuration = 0;
        fileSize = 0;
        bitrate = 0;
        videoFrameRate = 0;
        videoBitrate = 0;
        audioSampleRate = 0;
        audioChannel = 0;
        audioBitrate = 0;
        audioBitsPerSample = 0;
        audioBlockAlign = 0;
        fileFormat = mvideoinfo.fileFormat;
        videoFormat = mvideoinfo.videoFormat;
        audioFormat = mvideoinfo.audioFormat;
        frameWidth = mvideoinfo.frameWidth;
        frameHeight = mvideoinfo.frameHeight;
        videoDuration = mvideoinfo.videoDuration;
        audioDuration = mvideoinfo.audioDuration;
        fileSize = mvideoinfo.fileSize;
        bitrate = mvideoinfo.bitrate;
        videoFrameRate = mvideoinfo.videoFrameRate;
        videoBitrate = mvideoinfo.videoBitrate;
        audioSampleRate = mvideoinfo.audioSampleRate;
        audioChannel = mvideoinfo.audioChannel;
        audioBitrate = mvideoinfo.audioBitrate;
        audioBitsPerSample = mvideoinfo.audioBitsPerSample;
        audioBlockAlign = mvideoinfo.audioBlockAlign;
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
            return frameWidth;

        case 4: // '\004'
            return frameHeight;

        case 5: // '\005'
            return videoDuration;

        case 6: // '\006'
            return audioDuration;

        case 7: // '\007'
            return fileSize;

        case 8: // '\b'
            return bitrate;

        case 9: // '\t'
            return videoFrameRate;

        case 10: // '\n'
            return videoBitrate;

        case 11: // '\013'
            return audioSampleRate;

        case 12: // '\f'
            return audioChannel;

        case 13: // '\r'
            return audioBitrate;

        case 14: // '\016'
            return audioBitsPerSample;

        case 15: // '\017'
            return audioBlockAlign;
        }
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
            frameWidth = j;
            return;

        case 4: // '\004'
            frameHeight = j;
            return;

        case 5: // '\005'
            videoDuration = j;
            return;

        case 6: // '\006'
            audioDuration = j;
            return;

        case 7: // '\007'
            fileSize = j;
            return;

        case 8: // '\b'
            bitrate = j;
            return;

        case 9: // '\t'
            videoFrameRate = j;
            return;

        case 10: // '\n'
            videoBitrate = j;
            return;

        case 11: // '\013'
            audioSampleRate = j;
            return;

        case 12: // '\f'
            audioChannel = j;
            return;

        case 13: // '\r'
            audioBitrate = j;
            return;

        case 14: // '\016'
            audioBitsPerSample = j;
            return;

        case 15: // '\017'
            audioBlockAlign = j;
            break;
        }
    }
}
