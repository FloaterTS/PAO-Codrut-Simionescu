package com.files;

import java.io.FileWriter;
import java.io.IOException;
import java.sql.Timestamp;
import java.util.Date;

public class Audit
{
    private static Audit audit = null;

    private final FileWriter auditWriter;
    private Date date;
    private long time;
    private Timestamp ts;

    private Audit() throws IOException
    {
        String auditFile = "files//audit.csv";
        auditWriter = new FileWriter(auditFile);

        date = new Date();
        time = date.getTime();
        ts = new Timestamp(time);

        auditWriter.write( "Query pornit, " + ts + "\n");
        auditWriter.flush();
    }

    public static Audit getAudit() throws IOException
    {
        if (audit == null)
            audit = new Audit();
        return audit;
    }

    public void saveQuery(String cmd) throws IOException
    {
        date = new Date();
        time = date.getTime();
        ts = new Timestamp(time);

        auditWriter.append(cmd).append(", ").append(String.valueOf(ts)).append("\n");
        auditWriter.flush();
    }

    public void closeQuery() throws IOException
    {
        date = new Date();
        time = date.getTime();
        ts = new Timestamp(time);

        auditWriter.append("Query terminat, ").append(String.valueOf(ts));
        auditWriter.flush();

        auditWriter.close();
    }
}








