package com.files;

import com.components.*;
import com.helper.ZTime;
import com.main.Agenda;
import com.main.Service;

import java.io.*;

public class FileAlarmMgr
{
    private static FileAlarmMgr alarm_file_mgr = null;

    private final Agenda agenda;
    private final Service serv;
    private final String alarmsFile = "files//alarms.csv";

    private FileAlarmMgr()
    {
        agenda = Agenda.getAgenda();
        serv = Service.getService();
    }

    public static FileAlarmMgr getAlarm_file_mgr()
    {
        if (alarm_file_mgr == null)
            alarm_file_mgr = new FileAlarmMgr();
        return alarm_file_mgr;
    }

    public void saveAlarms() throws IOException
    {
        FileWriter alarmFileWriter = new FileWriter(alarmsFile);
        PrintWriter alarmPrintWriter = new PrintWriter(alarmFileWriter);

        for(Alarm alarm : agenda.getAllAlarms())
        {
            alarmPrintWriter.printf("%d,%d,%d,", alarm.getTime().ora, alarm.getTime().minute, alarm.getImportant() ? 1 : 0);
            if(alarm instanceof AlarmReminder)
                alarmPrintWriter.printf("%s\n",((AlarmReminder) alarm).getTextRemind());
            else
                alarmPrintWriter.printf("%s\n", "null");
        }
        alarmPrintWriter.flush();
        alarmPrintWriter.close();
    }

    public void loadAlarms() throws IOException
    {
        BufferedReader alarmReader;
        try {
            alarmReader = new BufferedReader(new FileReader(alarmsFile));
        } catch(FileNotFoundException fnfe) {
            return;
        }
        String line;
        while ((line = alarmReader.readLine()) != null)
        {
            String[] alarms = line.split(",");
            ZTime time = new ZTime(Integer.parseInt(alarms[0]),Integer.parseInt(alarms[1]));
            String rem = alarms[3].equals("null") ? null : alarms[3];
            boolean imp = Integer.parseInt(alarms[2]) != 0;

            serv.sAddAlarm(time.ora,time.minute, rem, imp);
        }
        alarmReader.close();
    }

}
