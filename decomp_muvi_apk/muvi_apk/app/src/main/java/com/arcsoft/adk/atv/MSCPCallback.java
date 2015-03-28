// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.arcsoft.adk.atv;

import java.util.List;

public interface MSCPCallback
{
    public static class DataOnDirContentUpdated
    {

        public List m_Contents;
        public int m_nCount;
        public int m_nIndex;
        public int m_nRes;
        public int m_nTotalSize;
        public int m_nUpdateId;

        public DataOnDirContentUpdated()
        {
        }
    }

    public static class DataOnRecordSchedule
    {

        public boolean m_bAbnormalTasksExist;
        public int m_nCurrentRecordTaskCount;
        public int m_nVliIppltvContentUnuseTimeout;
        public int m_nVliIppltvMode;
        public String m_strClass;
        public String m_strDesireRecordQuality_type;
        public String m_strDesireRecordQuality_value;
        public String m_strPriority;
        public String m_strRecordDestination_mediatype;
        public String m_strRecordDestination_preference;
        public String m_strRecordDestination_value;
        public String m_strRecordScheduleID;
        public String m_strScheduleState_CurrentErrors;
        public String m_strScheduleState_value;
        public String m_strScheduledCDSObjectID;
        public String m_strScheduledDuration;
        public String m_strScheduledStartDateTime;
        public String m_strTitle;
        public String m_strVliIppltvTerminateMode;
        public String m_strVliReserveType;
        public String m_strVliggChannelID_type;
        public String m_strVliggChannelID_value;
        public String m_strVliggProgramID_type;
        public String m_strVliggProgramID_value;

        public DataOnRecordSchedule()
        {
        }
    }

    public static class DataOnRecordTaskItem
    {

        public String m_RecordQuality_type;
        public String m_RecordQuality_value;
        public boolean m_bTaskState_FatalError;
        public boolean m_bTaskState_Recording;
        public boolean m_bTaskState_SomeBitsMissing;
        public boolean m_bTaskState_SomeBitsRecorded;
        public String m_strClass;
        public String m_strDesireRecordQuality_type;
        public String m_strDesireRecordQuality_value;
        public String m_strDlnaDesiredPN;
        public String m_strDlnaPN;
        public String m_strID;
        public String m_strPriority;
        public String m_strRecordDestination_mediatype;
        public String m_strRecordDestination_preference;
        public String m_strRecordDestination_value;
        public String m_strRecordScheduleID;
        public String m_strRecordedCDSObjectID;
        public String m_strTaskChannelID_type;
        public String m_strTaskChannelID_value;
        public String m_strTaskDuration;
        public String m_strTaskStartDateTime;
        public String m_strTaskState_CurrentErrors;
        public String m_strTaskState_ErrorHistory;
        public String m_strTaskState_InfoList;
        public String m_strTaskState_PendingErrors;
        public String m_strTaskState_Phase;
        public String m_strTaskState_value;
        public String m_strTitle;
        public String m_strVliReserveType;
        public String m_strVliggChannelID_type;
        public String m_strVliggChannelID_value;
        public String m_strVliggProgramID_type;
        public String m_strVliggProgramID_value;
        public String m_strVliggReserveParam;

        public DataOnRecordTaskItem()
        {
        }
    }

    public static class DataOnRecordTasks
    {

        public List m_TaskItem;
        public int m_nNumber;
        public int m_nTotalMatches;

        public DataOnRecordTasks()
        {
        }
    }

    public static class DataOnServerAdded
    {

        public UPnP.MediaServerDesc m_ServerDesc;

        public DataOnServerAdded()
        {
        }
    }

    public static class DataOnServerGetProtocolInfo
    {

        public String m_SinkValues[];
        public String m_strSourceValues[];

        public DataOnServerGetProtocolInfo()
        {
        }
    }

    public static class DataOnServerRemoved
    {

        public UPnP.MediaServerDesc m_ServerDesc;

        public DataOnServerRemoved()
        {
        }
    }


    public abstract void OnDestroyObject(int i, String s);

    public abstract void OnDigaBrowseRecordTasks(int i, DataOnRecordTasks dataonrecordtasks, String s);

    public abstract void OnDigaCreateRecordSchedule(int i, DataOnRecordSchedule dataonrecordschedule, String s);

    public abstract void OnDigaDeleteRecordSchedule(int i, String s);

    public abstract void OnDigaDisableRecordSchedule(int i, String s);

    public abstract void OnDigaEnableRecordSchedule(int i, String s);

    public abstract void OnDigaXP9eGetContainerIds(int i, String s, String s1);

    public abstract void OnDirContentUpdated(DataOnDirContentUpdated dataondircontentupdated, String s, String s1);

    public abstract void OnGetSearchCapabilities(int i, String s, String s1);

    public abstract void OnGetSortCapabilities(int i, String s, String s1);

    public abstract void OnServerAdded(DataOnServerAdded dataonserveradded);

    public abstract void OnServerGetProtocolInfo(int i, DataOnServerGetProtocolInfo dataonservergetprotocolinfo, String s);

    public abstract void OnServerRemoved(DataOnServerRemoved dataonserverremoved);

    public abstract void OnXGetDLNAUploadProfiles(int i, String s, String s1);
}
