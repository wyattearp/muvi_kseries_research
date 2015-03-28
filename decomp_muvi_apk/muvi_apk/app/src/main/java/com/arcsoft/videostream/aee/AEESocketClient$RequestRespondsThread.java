// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.arcsoft.videostream.aee;

import com.arcsoft.util.SystemUtils;
import com.arcsoft.util.debug.Log;
import java.io.IOException;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

// Referenced classes of package com.arcsoft.videostream.aee:
//            AEESocketClient

private class <init> extends Thread
{

    private static final int MAX_GET_CURRENT_COUNT = 5;
    private int countException;
    private int curCmd;
    private boolean isRecting;
    private boolean stop;
    final AEESocketClient this$0;

    public void run()
    {
        this;
        JVM INSTR monitorenter ;
        Log.e("SocketClient", "testP RequestRespondsThread start --------------");
_L17:
        if (!stop) goto _L2; else goto _L1
_L1:
        this;
        JVM INSTR monitorexit ;
        return;
_L2:
        int i;
        String s;
        int j;
        String s1;
        i = 1;
        s = null;
        j = 0;
        s1 = null;
        String s2;
        if (AEESocketClient.access$300(AEESocketClient.this) == -1 && curCmd != -1)
        {
            setCurCMD(-1);
        }
        Log.e("SocketClient", (new StringBuilder()).append("RequestRespondsThread mCurCmdType = ").append(AEESocketClient.access$300(AEESocketClient.this)).toString());
        s2 = readLine();
        Log.e("SocketClient", (new StringBuilder()).append("testP RequestRespondsThread respondLine = ").append(s2).toString());
        if (s2 == null) goto _L4; else goto _L3
_L3:
        JSONObject jsonobject = new JSONObject(s2);
        Log.e("SocketClient", (new StringBuilder()).append("testP RequestRespondsThread jsonObj = ").append(jsonobject).toString());
        if (!jsonobject.has("rval")) goto _L6; else goto _L5
_L5:
        i = jsonobject.getInt("rval");
_L21:
        Log.e("SocketClient", (new StringBuilder()).append("testP getRespondParams() errNum = ").append(i).toString());
        AEESocketClient.access$300(AEESocketClient.this);
        JVM INSTR tableswitch 268435457 268435512: default 452
    //                   268435457 691
    //                   268435458 452
    //                   268435459 1348
    //                   268435460 1383
    //                   268435461 452
    //                   268435462 1295
    //                   268435463 452
    //                   268435464 1328
    //                   268435465 452
    //                   268435466 452
    //                   268435467 452
    //                   268435468 452
    //                   268435469 452
    //                   268435470 452
    //                   268435471 452
    //                   268435472 452
    //                   268435473 452
    //                   268435474 452
    //                   268435475 452
    //                   268435476 1310
    //                   268435477 452
    //                   268435478 452
    //                   268435479 452
    //                   268435480 452
    //                   268435481 452
    //                   268435482 452
    //                   268435483 452
    //                   268435484 452
    //                   268435485 452
    //                   268435486 452
    //                   268435487 452
    //                   268435488 979
    //                   268435489 979
    //                   268435490 979
    //                   268435491 979
    //                   268435492 979
    //                   268435493 1218
    //                   268435494 979
    //                   268435495 979
    //                   268435496 979
    //                   268435497 979
    //                   268435498 979
    //                   268435499 979
    //                   268435500 979
    //                   268435501 979
    //                   268435502 979
    //                   268435503 979
    //                   268435504 979
    //                   268435505 979
    //                   268435506 979
    //                   268435507 979
    //                   268435508 979
    //                   268435509 1135
    //                   268435510 1135
    //                   268435511 1135
    //                   268435512 1135;
           goto _L7 _L8 _L7 _L9 _L10 _L7 _L11 _L7 _L12 _L7 _L7 _L7 _L7 _L7 _L7 _L7 _L7 _L7 _L7 _L7 _L13 _L7 _L7 _L7 _L7 _L7 _L7 _L7 _L7 _L7 _L7 _L7 _L14 _L14 _L14 _L14 _L14 _L15 _L14 _L14 _L14 _L14 _L14 _L14 _L14 _L14 _L14 _L14 _L14 _L14 _L14 _L14 _L14 _L16 _L16 _L16 _L16
_L7:
        AEESocketClient.access$302(AEESocketClient.this, -1);
_L26:
        if (AEESocketClient.access$400(AEESocketClient.this) != null)
        {
            countException = 0;
            AEESocketClient.access$400(AEESocketClient.this).onRequestRespondsFinished(curCmd, s2, i, s, j, s1);
        }
_L36:
        Exception exception;
        Exception exception1;
        IOException ioexception;
        AEESocketClient aeesocketclient;
        JSONException jsonexception;
        boolean flag;
        boolean flag1;
        long l;
        boolean flag2;
        boolean flag3;
        JSONArray jsonarray;
        if (AEESocketClient.access$300(AEESocketClient.this) == -1)
        {
            l = 300L;
        } else
        {
            l = 100L;
        }
        Thread.sleep(l);
          goto _L17
        jsonexception;
_L40:
        jsonexception.printStackTrace();
        AEESocketClient.access$302(AEESocketClient.this, -1);
        if (AEESocketClient.access$400(AEESocketClient.this) != null)
        {
            AEESocketClient.access$400(AEESocketClient.this).onRequestRespondsFinished(curCmd, null, 2, null, 0, null);
        }
          goto _L17
        exception;
        this;
        JVM INSTR monitorexit ;
        throw exception;
_L6:
        flag = jsonobject.has("msg_id");
        s = null;
        if (!flag) goto _L19; else goto _L18
_L18:
        s = jsonobject.getString("msg_id");
_L19:
        flag1 = jsonobject.has("param_size");
        j = 0;
        if (!flag1)
        {
            continue; /* Loop/switch isn't completed */
        }
        j = jsonobject.getInt("param_size");
        if (!s.contains("16777217") && !s.contains("16777218")) goto _L21; else goto _L20
_L20:
        if (AEESocketClient.access$400(AEESocketClient.this) != null)
        {
            countException = 0;
            AEESocketClient.access$400(AEESocketClient.this).onRequestRespondsFinished(-1, s2, i, s, j, null);
        }
          goto _L17
_L8:
        if (i != 0) goto _L23; else goto _L22
_L22:
        flag3 = jsonobject.has("param");
        jsonarray = null;
        if (!flag3) goto _L25; else goto _L24
_L24:
        jsonarray = jsonobject.getJSONArray("param");
_L25:
        if (jsonarray.length() >= 1)
        {
            AEESocketClient.access$502(AEESocketClient.this, ((Integer)jsonarray.get(0)).intValue());
        }
        Log.e("FENG", (new StringBuilder()).append("FENG token = ").append(jsonarray).toString());
        Log.e("FENG", (new StringBuilder()).append("FENG mTokenId = ").append(AEESocketClient.access$500(AEESocketClient.this)).toString());
        s = (new StringBuilder()).append(AEESocketClient.access$500(AEESocketClient.this)).append("").toString();
_L23:
        AEESocketClient.access$302(AEESocketClient.this, -1);
        s1 = null;
          goto _L26
        ioexception;
_L37:
        ioexception.printStackTrace();
        if (AEESocketClient.access$300(AEESocketClient.this) != -1 && !isRecting)
        {
            countException = 1 + countException;
        }
        if (AEESocketClient.access$400(AEESocketClient.this) == null || countException != 5) goto _L28; else goto _L27
_L27:
        AEESocketClient.access$302(AEESocketClient.this, -1);
        aeesocketclient = AEESocketClient.access$600();
        if (aeesocketclient == null) goto _L30; else goto _L29
_L29:
        AEESocketClient.access$600().close();
_L38:
        AEESocketClient.access$600().stopRespondParams();
        AEESocketClient.access$600().releaseCurTokenId();
        AEESocketClient.access$600().realseClient();
        AEESocketClient.access$602(null);
_L30:
        AEESocketClient.access$400(AEESocketClient.this).onRequestRespondsFinished(curCmd, null, 2, null, 0, null);
        countException = 0;
          goto _L17
_L14:
        if (i != 0) goto _L32; else goto _L31
_L31:
        if (jsonobject.has("param"))
        {
            s = jsonobject.getString("param");
            if (s.contains("record"))
            {
                isRecting = true;
            }
        }
        if (jsonobject.has("param_size"))
        {
            j = jsonobject.getInt("param_size");
        }
_L32:
        AEESocketClient.access$302(AEESocketClient.this, -1);
        s1 = null;
          goto _L26
        exception1;
_L39:
        exception1.printStackTrace();
        if (AEESocketClient.access$300(AEESocketClient.this) != -1)
        {
            countException = 1 + countException;
        }
        if (AEESocketClient.access$400(AEESocketClient.this) != null && countException == 5)
        {
            AEESocketClient.access$302(AEESocketClient.this, -1);
            AEESocketClient.access$400(AEESocketClient.this).onRequestRespondsFinished(curCmd, null, 2, null, 0, null);
            countException = 0;
        }
          goto _L17
_L16:
        s1 = null;
        if (i != 0)
        {
            break MISSING_BLOCK_LABEL_1206;
        }
        if (jsonobject.has("param"))
        {
            s = jsonobject.getString("param");
        }
        if (jsonobject.has("param_size"))
        {
            j = jsonobject.getInt("param_size");
        }
        flag2 = jsonobject.has("settable");
        s1 = null;
        if (!flag2)
        {
            break MISSING_BLOCK_LABEL_1206;
        }
        s1 = jsonobject.getString("settable");
        AEESocketClient.access$302(AEESocketClient.this, -1);
          goto _L26
_L15:
        if (i != 0)
        {
            break MISSING_BLOCK_LABEL_1280;
        }
        if (jsonobject.has("param"))
        {
            s = jsonobject.getString("param");
        }
        if (jsonobject.has("param_size"))
        {
            j = jsonobject.getInt("param_size");
        }
        if (s == null)
        {
            break MISSING_BLOCK_LABEL_1280;
        }
        if (s.contains("record"))
        {
            isRecting = true;
        }
        AEESocketClient.access$302(AEESocketClient.this, -1);
        s1 = null;
          goto _L26
_L11:
        AEESocketClient.access$302(AEESocketClient.this, -1);
        s1 = null;
          goto _L26
_L13:
        s1 = null;
        if (i != 0) goto _L26; else goto _L33
_L33:
        isRecting = true;
        s1 = null;
          goto _L26
_L12:
        AEESocketClient.access$302(AEESocketClient.this, -1);
        isRecting = false;
        s1 = null;
          goto _L26
_L9:
        if (i != 0) goto _L35; else goto _L34
_L34:
        isRecting = true;
        s1 = null;
          goto _L26
_L35:
        AEESocketClient.access$302(AEESocketClient.this, -1);
        isRecting = false;
        s1 = null;
          goto _L26
_L10:
        AEESocketClient.access$302(AEESocketClient.this, -1);
        isRecting = false;
        s1 = null;
          goto _L26
_L4:
        AEESocketClient.access$302(AEESocketClient.this, -1);
        if (AEESocketClient.access$400(AEESocketClient.this) != null)
        {
            AEESocketClient.access$400(AEESocketClient.this).onRequestRespondsFinished(curCmd, s2, 1, null, 0, null);
        }
          goto _L36
        ioexception;
          goto _L37
        IOException ioexception1;
        ioexception1;
        ioexception1.printStackTrace();
          goto _L38
_L28:
        SystemUtils.safeSleep(10L);
          goto _L17
        exception1;
          goto _L39
        jsonexception;
          goto _L40
    }

    public void setCurCMD(int i)
    {
        curCmd = i;
    }

    public void stopRequest()
    {
        stop = true;
    }

    private ner()
    {
        this$0 = AEESocketClient.this;
        super();
        stop = false;
        curCmd = -1;
        countException = 0;
        isRecting = false;
    }

    isRecting(isRecting isrecting)
    {
        this();
    }
}
