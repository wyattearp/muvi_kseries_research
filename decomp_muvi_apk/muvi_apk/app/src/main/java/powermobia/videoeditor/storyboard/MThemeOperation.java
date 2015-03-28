// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package powermobia.videoeditor.storyboard;


public class MThemeOperation
{

    public static int TYPE_ADD_COVER = 6;
    public static int TYPE_ADD_EFFECT = 1;
    public static int TYPE_ADD_TRANSITION = 3;
    public static int TYPE_REMOVE_COVER = 5;
    public static int TYPE_REMOVE_EFFECT = 2;
    public static int TYPE_REMOVE_TRANSITION = 4;
    private int clipIndex;
    private int effectIndex;
    private int effectLayerID;
    private int effectTrackType;
    private int errorCode;
    private boolean onStoryboard;
    private Object opData;
    private boolean opFinish;
    private int operationType;

    private MThemeOperation()
    {
        operationType = 0;
        onStoryboard = false;
        clipIndex = 0;
        effectTrackType = 0;
        effectLayerID = 0;
        effectIndex = 0;
        errorCode = 0;
        opFinish = false;
        opData = null;
        operationType = 0;
        onStoryboard = false;
        clipIndex = 0;
        effectTrackType = 0;
        effectLayerID = 0;
        effectIndex = 0;
    }

    public int getClipIndex()
    {
        return clipIndex;
    }

    public int getEffectIndex()
    {
        return effectIndex;
    }

    public int getEffectLayerID()
    {
        return effectLayerID;
    }

    public int getEffectTrackType()
    {
        return effectTrackType;
    }

    public int getErrorCode()
    {
        return errorCode;
    }

    public Object getOperatorData()
    {
        return opData;
    }

    public int getType()
    {
        return operationType;
    }

    public boolean operateOnStoryboard()
    {
        return onStoryboard;
    }

    public boolean operatorFinish()
    {
        return opFinish;
    }

    public void setEffectLayerID(int i)
    {
        effectLayerID = i;
    }

    public void setEffectTrackType(int i)
    {
        effectTrackType = i;
    }

}
