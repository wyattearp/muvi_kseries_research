// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.arcsoft.adk.atv;


public interface MRCPCallback
{
    public static class DataOnGetMediaInfo
    {

        public int m_nTrack;
        public String m_strCurUri;
        public String m_strCurUriMetadata;
        public String m_strMediaDuration;
        public String m_strNextUri;
        public String m_strNextUriMetadata;
        public String m_strPlayMedium;
        public String m_strRecordMedium;
        public String m_strWriteStatus;

        public DataOnGetMediaInfo()
        {
        }
    }

    public static class DataOnGetPositionInfo
    {

        public int m_iAbsCount;
        public int m_iRelCount;
        public int m_nTrack;
        public String m_strAbsTime;
        public String m_strRelTime;
        public String m_strTrackDuration;
        public String m_strTrackMetadata;
        public String m_strTrackUri;

        public DataOnGetPositionInfo()
        {
        }
    }

    public static class DataOnGetProtocolInfo
    {

        public String m_SinkValues[];
        public String m_strSourceValues[];

        public DataOnGetProtocolInfo()
        {
        }
    }

    public static class DataOnGetTransportInfo
    {

        public String strCurrentSpeed;
        public String strCurrentTransportState;
        public String strCurrentTransportStatus;

        public DataOnGetTransportInfo()
        {
        }
    }

    public static class DataOnGetTransportSettings
    {

        public String strPlayMode;
        public String strRecQualityMode;

        public DataOnGetTransportSettings()
        {
        }
    }

    public static class DataOnRenderAdded
    {

        public UPnP.MediaRenderDesc m_RenderDesc;

        public DataOnRenderAdded()
        {
        }
    }

    public static class DataOnRenderRemoved
    {

        public UPnP.MediaRenderDesc m_RenderDesc;

        public DataOnRenderRemoved()
        {
        }
    }


    public abstract void OnGetCurrentTransportActions(int i, String s, String s1);

    public abstract void OnGetMeidaInfo(int i, DataOnGetMediaInfo dataongetmediainfo, String s);

    public abstract void OnGetMute(int i, boolean flag, String s);

    public abstract void OnGetPositionInfo(int i, DataOnGetPositionInfo dataongetpositioninfo, String s);

    public abstract void OnGetProtocolInfo(int i, DataOnGetProtocolInfo dataongetprotocolinfo, String s);

    public abstract void OnGetTransportInfo(int i, DataOnGetTransportInfo dataongettransportinfo, String s);

    public abstract void OnGetTransportSettings(int i, DataOnGetTransportSettings dataongettransportsettings, String s);

    public abstract void OnGetVolume(int i, int j, String s);

    public abstract void OnMediaNext(int i, String s);

    public abstract void OnMediaPause(int i, String s);

    public abstract void OnMediaPlay(int i, String s);

    public abstract void OnMediaPrevious(int i, String s);

    public abstract void OnMediaSeek(int i, String s);

    public abstract void OnMediaStop(int i, String s);

    public abstract void OnRenderAdded(DataOnRenderAdded dataonrenderadded);

    public abstract void OnRenderInstalled(UPnP.MediaRenderDesc mediarenderdesc, boolean flag, boolean flag1, boolean flag2);

    public abstract void OnRenderRemoved(DataOnRenderRemoved dataonrenderremoved);

    public abstract void OnSetAVTransportURI(int i, String s);

    public abstract void OnSetMute(int i, String s);

    public abstract void OnSetVolume(int i, String s);
}
