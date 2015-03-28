// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.arcsoft.adk.atv;


public interface BrowseCallback
{
    public static class DataOnBrowseRequest
    {

        public int lReqId;
        public int lTimeOut;
        public String szClientAddress;
        public String szObjId;
        public String szTitle;

        public DataOnBrowseRequest()
        {
        }
    }

    public static class DataOnBrowseRequestRsp
    {

        public int lRsp;

        public DataOnBrowseRequestRsp()
        {
        }
    }


    public static final int ATVUPNP_BROWSE_ALLOW = 1;
    public static final int ATVUPNP_BROWSE_DENY = 2;
    public static final int ATVUPNP_BROWSE_WAIT_VALIDATE;

    public abstract void OnBrowseRequest(DataOnBrowseRequest dataonbrowserequest, DataOnBrowseRequestRsp dataonbrowserequestrsp);
}
