// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package android.media;


// Referenced classes of package android.media:
//            DecoderCapabilities

public static final class  extends Enum
{

    private static final .VALUES $VALUES[];
    public static final .VALUES AUDIO_DECODER_WMA;

    public static  valueOf(String s)
    {
        return ()Enum.valueOf(android/media/DecoderCapabilities$AudioDecoder, s);
    }

    public static [] values()
    {
        return ([])$VALUES.clone();
    }

    static 
    {
        AUDIO_DECODER_WMA = new <init>("AUDIO_DECODER_WMA", 0);
        r_3B_.clone aclone[] = new <init>[1];
        aclone[0] = AUDIO_DECODER_WMA;
        $VALUES = aclone;
    }

    private (String s, int i)
    {
        super(s, i);
    }
}
