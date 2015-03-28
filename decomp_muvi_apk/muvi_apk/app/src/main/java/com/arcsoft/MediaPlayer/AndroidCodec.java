// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.arcsoft.MediaPlayer;

import android.media.MediaCodec;
import android.media.MediaCodecInfo;
import android.media.MediaCodecList;
import android.media.MediaFormat;
import android.util.Log;
import android.view.Surface;
import java.nio.ByteBuffer;

public class AndroidCodec
{

    private static final int ANDROID_INFO_BUFFER_INVALID_PARAM = -100;
    private static final String TAG = "AndroidCodec";
    private static final int TIMEOUT_US = 1000;
    private boolean bInit;
    private MediaCodec mCodec;
    private ByteBuffer mCodecInputBuffers[];
    private ByteBuffer mCodecOutputBuffers[];
    private ByteBuffer mCurInputByteBuffer;
    private MediaFormat mFormat;
    private android.media.MediaCodec.BufferInfo mInfo;
    private byte mInputBuffer[];
    private int mMaxInputSize;
    private String mMime;
    private byte mOutputBuffer[];
    private Surface mSurface;
    private boolean m_bOutputLog;
    private boolean mbInited;
    private boolean mbInputEOS;
    private boolean mbOutputEOS;
    private int miColorFormat;
    private int miCsdIndex;
    private int miCurrentInputBufIndex;
    private int miHeight;
    private int miInTimeStamp;
    private int miInputSize;
    private int miOutTimeStamp;
    private int miOutputBufferIndex;
    private int miWidth;

    private AndroidCodec()
    {
        mSurface = null;
        mFormat = null;
        mMime = null;
        mCodec = null;
        mCodecInputBuffers = null;
        mCodecOutputBuffers = null;
        miWidth = 0;
        miHeight = 0;
        mbInputEOS = false;
        mbOutputEOS = false;
        mMaxInputSize = 0;
        bInit = false;
        miCsdIndex = 0;
        miOutputBufferIndex = -1;
        mInfo = new android.media.MediaCodec.BufferInfo();
        mInputBuffer = null;
        mOutputBuffer = null;
        mCurInputByteBuffer = null;
        miInputSize = 0;
        miInTimeStamp = 0;
        miOutTimeStamp = 0;
        miColorFormat = 0;
        m_bOutputLog = false;
        miCurrentInputBufIndex = -1;
        mbInited = false;
        outputLog("AndroidCodec", (new StringBuilder("Max Memory = ")).append(Runtime.getRuntime().maxMemory()).append(", Runtime.getRuntime().totalMemory() = ").append(Runtime.getRuntime().totalMemory()).toString());
    }

    private void addConfigData(byte abyte0[])
    {
        outputLog("AndroidCodec", "AddCsdData in");
        ByteBuffer bytebuffer = ByteBuffer.wrap(abyte0);
        String s = (new StringBuilder("csd-")).append(miCsdIndex).toString();
        outputLog("AndroidCodec", (new StringBuilder("AddCsdData in strCsdTag ")).append(s).toString());
        mFormat.setByteBuffer(s, bytebuffer);
        miCsdIndex = 1 + miCsdIndex;
        outputLog("AndroidCodec", "AddCsdData out");
    }

    private int dequeueInputBuffer()
    {
        outputLog("AndroidCodec", (new StringBuilder("dequeueInputBuffer in++, mbOutputEOS: ")).append(mbOutputEOS).append(", mbInputEOS:").append(mbInputEOS).toString());
        if (mCodec != null)
        {
            if (mCodecInputBuffers == null)
            {
                mCodecInputBuffers = mCodec.getInputBuffers();
            }
            if (mCodecInputBuffers != null && !mbInputEOS)
            {
                try
                {
                    miCurrentInputBufIndex = mCodec.dequeueInputBuffer(1000L);
                }
                catch (Exception exception)
                {
                    exception.printStackTrace();
                    miCurrentInputBufIndex = -100;
                }
                outputLog("AndroidCodec", (new StringBuilder("dequeueInputBuffer miCurrentInputBufIndex = ")).append(miCurrentInputBufIndex).toString());
                if (miCurrentInputBufIndex < mCodecInputBuffers.length)
                {
                    if (miCurrentInputBufIndex >= 0)
                    {
                        mCurInputByteBuffer = mCodecInputBuffers[miCurrentInputBufIndex];
                    }
                    return miCurrentInputBufIndex;
                }
            }
        }
        return -100;
    }

    private int dequeueOutputBuffer()
    {
        outputLog("AndroidCodec", (new StringBuilder("dequeueOutputBuffer in++, mbOutputEOS: ")).append(mbOutputEOS).append(", mbInputEOS:").append(mbInputEOS).toString());
        if (mCodec != null) goto _L2; else goto _L1
_L1:
        int i = -100;
_L6:
        return i;
_L2:
        outputLog("AndroidCodec", (new StringBuilder("dequeueOutputBuffer,  mCodecOutputBuffers:")).append(mCodecOutputBuffers).toString());
        if (mCodecOutputBuffers == null)
        {
            mCodecOutputBuffers = mCodec.getOutputBuffers();
        }
        if (mCodecOutputBuffers == null)
        {
            return -100;
        }
        if (mbOutputEOS)
        {
            return -100;
        }
        int j = mCodec.dequeueOutputBuffer(mInfo, 1000L);
        i = j;
_L4:
        outputLog("AndroidCodec", (new StringBuilder("dequeueOutputBuffer outputBufIndex: ")).append(i).toString());
        if (i >= mCodecOutputBuffers.length)
        {
            return -100;
        }
        break; /* Loop/switch isn't completed */
        Exception exception;
        exception;
        exception.printStackTrace();
        i = -100;
        if (true) goto _L4; else goto _L3
_L3:
        if (i >= 0)
        {
            if (mSurface == null)
            {
                mCodecOutputBuffers[i].position(mInfo.offset);
                mCodecOutputBuffers[i].limit(mInfo.offset + mInfo.size);
            }
            if ((4 & mInfo.flags) != 0)
            {
                Log.d("AndroidCodec", "saw output EOS.");
                mbOutputEOS = true;
                return i;
            } else
            {
                miOutTimeStamp = (int)mInfo.presentationTimeUs;
                return i;
            }
        }
        if (i == -3)
        {
            mCodecOutputBuffers = mCodec.getOutputBuffers();
            outputLog("AndroidCodec", (new StringBuilder("output buffers have changed.mCodecOutputBuffers ")).append(mCodecOutputBuffers.length).toString());
            return i;
        }
        if (i == -2)
        {
            MediaFormat mediaformat = mCodec.getOutputFormat();
            outputLog("AndroidCodec", (new StringBuilder("output format has changed to ")).append(mediaformat).toString());
            miWidth = mediaformat.getInteger("width");
            miHeight = mediaformat.getInteger("height");
            miColorFormat = mediaformat.getInteger("color-format");
            switch (miColorFormat)
            {
            default:
                outputLog("AndroidCodec", (new StringBuilder("dequeueOutputBuffer ")).append(miColorFormat).toString());
                return i;

            case 19: // '\023'
                outputLog("AndroidCodec", (new StringBuilder("dequeueOutputBuffer colorFormats, COLOR_FormatYUV420Planar:")).append(miColorFormat).toString());
                return i;

            case 20: // '\024'
                outputLog("AndroidCodec", (new StringBuilder("dequeueOutputBuffer colorFormats, COLOR_FormatYUV420PackedPlanar:")).append(miColorFormat).toString());
                return i;

            case 21: // '\025'
                outputLog("AndroidCodec", "dequeueOutputBuffer colorFormats, COLOR_FormatYUV420SemiPlanar:");
                return i;

            case 39: // '\''
                outputLog("AndroidCodec", (new StringBuilder("dequeueOutputBuffer colorFormats, COLOR_FormatYUV420PackedSemiPlanar:")).append(miColorFormat).toString());
                return i;

            case 2130706688: 
                outputLog("AndroidCodec", (new StringBuilder("dequeueOutputBuffer, COLOR_TI_FormatYUV420PackedSemiPlanar:")).append(miColorFormat).toString());
                break;
            }
            return i;
        }
        if (true) goto _L6; else goto _L5
_L5:
    }

    private void flush()
    {
        if (mCodec != null)
        {
            mCodec.flush();
        }
        mbOutputEOS = false;
        mbInputEOS = false;
    }

    private int getInputBuffersCnt()
    {
        if (mCodecInputBuffers == null)
        {
            return 0;
        } else
        {
            outputLog("AndroidCodec", (new StringBuilder("getInputBuffersCnt mCodecInputBuffers.length =")).append(mCodecInputBuffers.length).toString());
            return mCodecInputBuffers.length;
        }
    }

    private byte[] getOutputBuffer(int i)
    {
        if (mCodecOutputBuffers == null || mSurface != null)
        {
            return null;
        }
        ByteBuffer bytebuffer = mCodecOutputBuffers[i];
        outputLog("AndroidCodec", (new StringBuilder("dequeueOutputBuffer, buf.limit())")).append(bytebuffer.limit()).append(", buf.position() ").append(bytebuffer.position()).toString());
        if (mOutputBuffer == null)
        {
            mOutputBuffer = new byte[bytebuffer.limit() - bytebuffer.position()];
        }
        bytebuffer.get(mOutputBuffer, bytebuffer.position(), bytebuffer.limit());
        return mOutputBuffer;
    }

    private int getOutputBuffersCnt()
    {
        if (mCodecOutputBuffers == null)
        {
            return 0;
        } else
        {
            outputLog("AndroidCodec", (new StringBuilder("getOutputBuffersCnt mCodecOutputBuffers.length =")).append(mCodecOutputBuffers.length).toString());
            return mCodecOutputBuffers.length;
        }
    }

    private int initCodec()
    {
        outputLog("AndroidCodec", (new StringBuilder("initCodec in bInited = ")).append(mbInited).toString());
        if (mbInited)
        {
            return -1;
        }
        mCodec = MediaCodec.createDecoderByType(mMime);
        if (mCodec == null)
        {
            outputLog("AndroidCodec", "initDecoder null == mCodec");
            return -1;
        }
        outputLog("AndroidCodec", (new StringBuilder("initCodec decode configure begin mFormat: ")).append(mFormat).toString());
        outputLog("AndroidCodec", (new StringBuilder("initCodec decode configure begin mSurface: ")).append(mSurface).toString());
        try
        {
            mCodec.configure(mFormat, mSurface, null, 0);
            outputLog("AndroidCodec", "Decoder configure end");
            mCodec.setVideoScalingMode(1);
            outputLog("AndroidCodec", "Decoder start");
            mCodec.start();
            mCodecInputBuffers = mCodec.getInputBuffers();
            outputLog("AndroidCodec", (new StringBuilder("decodeFrame mCodecInputBuffers ")).append(mCodecInputBuffers.length).toString());
            mCodecOutputBuffers = mCodec.getOutputBuffers();
            outputLog("AndroidCodec", (new StringBuilder("decodeFrame mCodecOutputBuffers length =  ")).append(mCodecOutputBuffers.length).toString());
        }
        catch (Exception exception)
        {
            exception.printStackTrace();
            mCodec.release();
            mCodec = null;
            mCodecInputBuffers = null;
            mCodecOutputBuffers = null;
            mFormat = null;
            return -1;
        }
        return 0;
    }

    private int initMediaFormat(String s, int i, int j, int k)
    {
        outputLog("AndroidCodec", (new StringBuilder("initMediaFormat in, mime: ")).append(s).append("width: ").append(i).append(" iHeight ").append(j).append(" iMaxInputSize ").append(k).toString());
        mFormat = MediaFormat.createVideoFormat(s, i, j);
        mFormat.setInteger("max-input-size", k);
        mMime = s;
        outputLog("AndroidCodec", (new StringBuilder("initMediaFormat in, createVideoFormat mFormat : ")).append(mFormat).toString());
        mInputBuffer = new byte[k];
        if (mInputBuffer == null)
        {
            outputLog("AndroidCodec", "initMediaFormat failed for mInputBuffer is too big");
            return -1;
        } else
        {
            outputLog("AndroidCodec", "initMediaFormat out");
            return 0;
        }
    }

    private boolean isSupportedMime(String s)
    {
        int i;
        outputLog("queryCodecInfo", (new StringBuilder("isSupportedMime : ")).append(s).toString());
        i = 0;
_L3:
        MediaCodecInfo mediacodecinfo;
        if (i >= MediaCodecList.getCodecCount())
        {
            return false;
        }
        mediacodecinfo = MediaCodecList.getCodecInfoAt(i);
        outputLog("queryCodecInfo", (new StringBuilder("codecInfo:Name = ")).append(mediacodecinfo.getName()).append(",isEncoder:").append(mediacodecinfo.isEncoder()).toString());
        if (mediacodecinfo.getName().contains("OMX.google.") || mediacodecinfo.isEncoder()) goto _L2; else goto _L1
_L1:
        String as[];
        int j;
        as = mediacodecinfo.getSupportedTypes();
        j = 0;
_L4:
        if (j < as.length)
        {
            break MISSING_BLOCK_LABEL_123;
        }
_L2:
        i++;
          goto _L3
        outputLog("queryCodecInfo", (new StringBuilder("strSupportType[")).append(j).append("] : ").append(as[j]).toString());
        if (s.equalsIgnoreCase(as[j]))
        {
            return true;
        }
        j++;
          goto _L4
    }

    private void outputLog(String s, String s1)
    {
        if (m_bOutputLog)
        {
            Log.d(s, s1);
        }
    }

    private static boolean querySupportCodecInfo(String s, int i, int j)
    {
        boolean flag;
        int k;
        flag = true;
        k = 0;
_L6:
        if (k < MediaCodecList.getCodecCount()) goto _L2; else goto _L1
_L1:
        flag = false;
_L15:
        return flag;
_L2:
        MediaCodecInfo mediacodecinfo = MediaCodecList.getCodecInfoAt(k);
        if (mediacodecinfo.getName().contains("OMX.google.") || mediacodecinfo.isEncoder()) goto _L4; else goto _L3
_L3:
        String as[];
        int l;
        as = mediacodecinfo.getSupportedTypes();
        l = 0;
_L9:
        if (l < as.length && as[l].startsWith("video/")) goto _L5; else goto _L4
_L4:
        k++;
          goto _L6
_L5:
        if (as[l].compareTo(s) == 0) goto _L8; else goto _L7
_L7:
        l++;
          goto _L9
_L8:
        if (as[l].contentEquals("video/avc") || as[l].contentEquals("video/3gpp") || as[l].contentEquals("video/sorenson") || as[l].contentEquals("video/mp4v-es") || as[l].contentEquals("video/mpeg2"))
        {
            break MISSING_BLOCK_LABEL_182;
        }
        if (i != 0 || j != 0) goto _L7; else goto _L10
_L10:
        return flag;
        android.media.MediaCodecInfo.CodecCapabilities codeccapabilities = mediacodecinfo.getCapabilitiesForType(as[l]);
        if (codeccapabilities == null) goto _L7; else goto _L11
_L11:
        android.media.MediaCodecInfo.CodecProfileLevel acodecprofilelevel[] = codeccapabilities.profileLevels;
        if (acodecprofilelevel == null) goto _L7; else goto _L12
_L12:
        int i1 = 0;
_L16:
        if (i1 >= acodecprofilelevel.length) goto _L7; else goto _L13
_L13:
        if (i > acodecprofilelevel[i1].profile || j > acodecprofilelevel[i1].level)
        {
            break MISSING_BLOCK_LABEL_280;
        }
        if (!mediacodecinfo.getName().contains("OMX.MTK")) goto _L15; else goto _L14
_L14:
        boolean flag1 = as[l].contentEquals("video/avc");
        if (flag1)
        {
            return false;
        }
          goto _L15
        i1++;
          goto _L16
        Exception exception;
        exception;
        exception.printStackTrace();
          goto _L7
    }

    private int queueInputBuffer(int i, int j, int k)
    {
        outputLog("AndroidCodec", (new StringBuilder("queueInputBuffer in++, iinputBufIndex:")).append(i).append(", iSampleSize:").append(j).append(", iSampleTime:").append(k).append(" mbOutputEOS: ").append(mbOutputEOS).append(", mbInputEOS:").append(mbInputEOS).toString());
        if (i >= 0) goto _L2; else goto _L1
_L1:
        i = -100;
_L4:
        return i;
_L2:
        if (mCodec == null)
        {
            return -100;
        }
        if (mCodecInputBuffers == null)
        {
            mCodecInputBuffers = mCodec.getInputBuffers();
        }
        if (mCodecInputBuffers == null)
        {
            return -100;
        }
        if (i >= mCodecInputBuffers.length)
        {
            return -100;
        }
        if (mbInputEOS)
        {
            return -1;
        }
        if (j <= 0)
        {
            mbInputEOS = true;
        }
        outputLog("AndroidCodec", (new StringBuilder("queueInputBuffer inputBufIndex = ")).append(i).toString());
        if (i < 0) goto _L4; else goto _L3
_L3:
        if (!mbInputEOS)
        {
            mCodecInputBuffers[i].clear();
            outputLog("AndroidCodec", (new StringBuilder("queueInputBuffer mCodecInputBuffers[")).append(i).append("].capacity(): ").append(mCodecInputBuffers[i].capacity()).append(", input iSampleSize = ").append(j).toString());
            mCodecInputBuffers[i].put(mInputBuffer, 0, j);
        }
        MediaCodec mediacodec;
        long l;
        boolean flag;
        byte byte0;
        try
        {
            mediacodec = mCodec;
        }
        catch (Exception exception)
        {
            exception.printStackTrace();
            return -100;
        }
        l = k;
        flag = mbInputEOS;
        byte0 = 0;
        if (flag)
        {
            byte0 = 4;
        }
        mediacodec.queueInputBuffer(i, 0, j, l, byte0);
        outputLog("AndroidCodec", (new StringBuilder("queueInputBuffer queueInputBuffer  = ")).append(i).toString());
        return i;
    }

    private void releaseOutputBuffer(int i, boolean flag)
    {
        outputLog("AndroidCodec", (new StringBuilder("releaseOutputBuffer in, iOutputBuffIdx :")).append(i).append(", bDraw:").append(flag).toString());
        if (mCodec == null || i < 0)
        {
            return;
        }
        MediaCodec mediacodec = mCodec;
        if (mSurface == null)
        {
            flag = false;
        }
        mediacodec.releaseOutputBuffer(i, flag);
        outputLog("AndroidCodec", "releaseOutputBuffer out");
    }

    private void unInitDecoder()
    {
        outputLog("AndroidCodec", "unInitDecoder in");
        mCodecInputBuffers = null;
        mCodecOutputBuffers = null;
        mInfo = null;
        if (mCodec != null)
        {
            mCodec.stop();
            mCodec.release();
            mCodec = null;
        }
        mSurface = null;
        mFormat = null;
        mInputBuffer = null;
        mOutputBuffer = null;
        outputLog("AndroidCodec", "unInitDecoder out");
    }
}
