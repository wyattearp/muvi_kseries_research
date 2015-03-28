// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.arcsoft.MediaPlayer.audiofx;

import android.util.Log;
import com.arcsoft.MediaPlayer.ArcMediaPlayer;
import java.io.UnsupportedEncodingException;

// Referenced classes of package com.arcsoft.MediaPlayer.audiofx:
//            AudioEffect

public class Equalizer extends AudioEffect
{

    public static final int PARAM_BAND_LEVEL = 4100;
    public static final int PARAM_CURRENT_BANDS = 4107;
    public static final int PARAM_CURRENT_PRESET = 4101;
    public static final int PARAM_ENABLE = 4097;
    public static final int PARAM_GET_NUM_OF_PRESETS = 4102;
    public static final int PARAM_GET_PRESET_NAME = 4103;
    public static final int PARAM_LEVEL_RANGE = 4099;
    public static final int PARAM_MAX_PRESETNAME_LEN = 32;
    public static final int PARAM_NUM_BANDS = 4098;
    public static final int PARAM_NUM_SPEC = 4105;
    public static final int PARAM_SPEC_DATA = 4106;
    public static final int PARAM_STRING_SIZE_MAX = 4104;
    private int mBandsData[];
    private int mNumBands;
    private int mNumPresets;
    private int mNumSpec;
    private String mPresetNames[];
    private int mSpecData[];

    public Equalizer(ArcMediaPlayer arcmediaplayer)
    {
        super(arcmediaplayer);
        mNumBands = 0;
        mNumSpec = 0;
        mSpecData = null;
        mNumPresets = 0;
        mPresetNames = null;
        mBandsData = null;
    }

    private int init()
    {
        getNumberOfBands();
        if (mNumBands != 0)
        {
            mNumPresets = getNumberOfPresets();
            Log.e("EQUALIZER", (new StringBuilder("mNumPresets:")).append(mNumPresets).toString());
            if (mNumPresets != 0)
            {
                mPresetNames = new String[mNumPresets];
                byte abyte0[] = new byte[32];
                int ai[] = new int[1];
                int i = 0;
                do
                {
label0:
                    {
                        if (i >= mNumPresets)
                        {
                            return 0;
                        }
                        ai[0] = i;
                        checkStatus(getAudioEffectParam(4103, ai, abyte0));
                        for (int j = 0; abyte0[j] != 0; j++)
                        {
                            break label0;
                        }

                        try
                        {
                            mPresetNames[i] = new String(abyte0, 0, j, "ISO-8859-1");
                            Log.e("EQUALIZER", (new StringBuilder("mPresetNames:")).append(i).append(" ").append(mPresetNames[i]).toString());
                        }
                        catch (UnsupportedEncodingException unsupportedencodingexception)
                        {
                            Log.e("EQUALIZER", "preset name decode error");
                        }
                        i++;
                    }
                } while (true);
            }
        }
        return -3;
    }

    public int getBandLevel(int i)
    {
        int ai[] = new int[1];
        int ai1[] = new int[1];
        ai[0] = i;
        checkStatus(getAudioEffectParam(4100, ai, ai1));
        return ai1[0];
    }

    public int[] getBandLevelRange()
    {
        int ai[] = new int[2];
        checkStatus(getAudioEffectParam(4099, new int[1], ai));
        return ai;
    }

    public int[] getBands()
    {
        if (getNumberOfBands() == 0)
        {
            checkStatus(-4);
            return null;
        }
        if (mBandsData == null)
        {
            mBandsData = new int[mNumBands];
        }
        checkStatus(getAudioEffectParam(4107, null, mBandsData));
        return mBandsData;
    }

    public int getCurrentPreset()
    {
        int ai[] = new int[1];
        checkStatus(getAudioEffectParam(4101, new int[1], ai));
        return ai[0];
    }

    public int getNumberOfBands()
    {
        if (mNumBands != 0)
        {
            return mNumBands;
        } else
        {
            int ai[] = new int[1];
            int ai1[] = new int[1];
            Log.e("EQ", "getNumberOfBands");
            checkStatus(getAudioEffectParam(4098, ai1, ai));
            Log.e("EQ", "getNumberOfBands end");
            mNumBands = ai[0];
            return mNumBands;
        }
    }

    public int getNumberOfPresets()
    {
        int ai[] = new int[1];
        checkStatus(getAudioEffectParam(4102, null, ai));
        return ai[0];
    }

    public String getPresetName(int i)
    {
        if (mPresetNames != null) goto _L2; else goto _L1
_L1:
        byte abyte0[];
        int ai[];
        int j;
        mNumPresets = getNumberOfPresets();
        if (mNumPresets == 0)
        {
            return "";
        }
        mPresetNames = new String[mNumPresets];
        abyte0 = new byte[32];
        ai = new int[1];
        j = 0;
_L5:
        if (j < mNumPresets) goto _L3; else goto _L2
_L2:
        if (i >= 0 && i < mNumPresets)
        {
            return mPresetNames[i];
        } else
        {
            return "";
        }
_L3:
label0:
        {
            ai[0] = j;
            Log.e("EQUALIZER", "EQUALIZER_PARAM_GET_PRESET_NAME begin");
            checkStatus(getAudioEffectParam(4103, ai, abyte0));
            Log.e("EQUALIZER", "EQUALIZER_PARAM_GET_PRESET_NAME end");
            for (int k = 0; abyte0[k] != 0; k++)
            {
                break label0;
            }

            try
            {
                mPresetNames[j] = new String(abyte0, 0, k, "ISO-8859-1");
            }
            catch (UnsupportedEncodingException unsupportedencodingexception)
            {
                Log.e("EQUALIZER", "preset name decode error");
            }
            j++;
        }
        if (true) goto _L5; else goto _L4
_L4:
    }

    public int[] getSpecData()
    {
        int ai[] = new int[1];
        int ai1[] = new int[1];
        if (mNumSpec == 0)
        {
            checkStatus(getAudioEffectParam(4105, ai1, ai));
            mNumSpec = ai[0];
        }
        if (mSpecData == null)
        {
            mSpecData = new int[mNumSpec];
        }
        checkStatus(getAudioEffectParam(4106, ai1, mSpecData));
        return mSpecData;
    }

    public boolean isEnabled()
    {
        new int[] {
            1
        };
        int ai[] = new int[1];
        checkStatus(getAudioEffectParam(4097, null, ai));
        return ai[0] != 0;
    }

    public void setBandLevel(int i, int j)
    {
        checkStatus(setAudioEffectParam(4100, new int[] {
            i, j
        }));
    }

    public void setBands(int ai[])
    {
        if (ai == null || ai.length != getNumberOfBands())
        {
            checkStatus(-4);
            return;
        } else
        {
            checkStatus(setAudioEffectParam(4107, ai));
            return;
        }
    }

    public void setEnabled(boolean flag)
    {
        int i = 1;
        int ai[] = new int[i];
        if (!flag)
        {
            i = 0;
        }
        ai[0] = i;
        checkStatus(setAudioEffectParam(4097, ai));
    }

    public void usePreset(int i)
    {
        checkStatus(setAudioEffectParam(4101, new int[] {
            i
        }));
    }
}
