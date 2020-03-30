package clase;

import java.util.*;

public class Service
{

    public void sAddTask(Agenda agendaMain, String titlu, boolean job, String desc, ZDate deadline, boolean obl, boolean important)
    {
        Task t;
        if (job)
        {
            TaskJob jt = new TaskJob(titlu);
            if (deadline != null)
                jt.setDeadline(deadline);
            t = jt;
        } else
        {
            TaskHome ht = new TaskHome(titlu);
            ht.setMust(obl);
            t = ht;
        }
        if (desc != null)
            t.setDescriere(desc);
        t.setImportant(important);
        agendaMain.addTask(t);
    }

    public void sAddMeeting(Agenda agendaMain, int zi, int luna, int an, boolean job, String desc, boolean withBoss, boolean family, boolean important)
    {
        Meeting m;
        if (job)
        {
            MeetingJob jm = new MeetingJob(zi, luna, an);
            jm.setWithBoss(withBoss);
            m = jm;
        } else
        {
            MeetingHome hm = new MeetingHome(zi, luna, an);
            hm.setFamily(family);
            m = hm;
        }
        if (desc != null)
            m.setDescriere(desc);
        m.setImportant(important);
        agendaMain.addMeeting(m);
        Collections.sort(agendaMain.getAllMeets());
    }

    public void sAddAlarm(Agenda agendaMain, int ora, int minut, String rem, boolean important)
    {
        Alarm a;
        if (rem != null)
        {
            a = new AlarmReminder(ora, minut, rem);
        } else
        {
            a = new Alarm(ora, minut);
        }
        a.setImportant(important);
        agendaMain.addAlarm(a);
    }

    public void selectTasks(Agenda agendaMain, int id, boolean imp)
    {
        List<Task> tasks = agendaMain.getAllTasks();
        if(tasks.size() == 0)
        {
            System.out.println("Nu a fost setata nicio sarcina.\n");
            return;
        }
        System.out.print("Sarcini:\n");
        for (Task task : tasks)
        {
            if (imp)
                if (!task.getImportant())
                    continue;
            if (id != -1)
                System.out.print(++id + ". ");
            System.out.print(task.getTitle() + " ");
            if (task.getDesc() != null)
                System.out.print("-" + task.getDesc() + "; ");
            task.getInfo();
            if (task.getImportant())
                System.out.print("-important;");
            System.out.print("\n");
        }
        System.out.print("\n");
    }

    public void selectMeets(Agenda agendaMain, int id, boolean imp)
    {
        List<Meeting> meets = agendaMain.getAllMeets();
        if(meets.size() == 0)
        {
            System.out.println("Nu a fost setata nicio intalnire.\n");
            return;
        }
        System.out.print("Intalniri pe data de:\n");
        for (Meeting meet : meets)
        {
            if (imp)
                if (!meet.getImportant())
                    continue;
            if (id != -1)
                System.out.print(++id + ". ");
            System.out.print(meet.getDate().zi + "/" + meet.getDate().luna + "/" + meet.getDate().an + " ");
            if (meet.getDesc() != null)
                System.out.print("-" + meet.getDesc() + "; ");
            meet.getInfo();
            if (meet.getImportant())
                System.out.print("-important;");
            System.out.print("\n");
        }
        System.out.print("\n");
    }

    public void selectAlarms(Agenda agendaMain, int id, boolean imp)
    {
        Set<Alarm> alarms = agendaMain.getAllAlarms();
        if(alarms.size() == 0)
        {
            System.out.println("Nu a fost setata nicio alarma.\n");
            return;
        }
        System.out.print("Alarme la orele:\n");
        for (Alarm alarm : alarms)
        {
            if(imp)
                if(!alarm.getImportant())
                    continue;
            if(id != -1)
                System.out.print(++id + ". ");
            System.out.print(alarm.getTime().ora + ":" + alarm.getTime().minute + " ");
            alarm.getInfo();
            if(alarm.getImportant())
                System.out.print("-important;");
            System.out.print("\n");
        }
        System.out.print("\n");
    }

    public int selectAll(Agenda agendaMain, int id, boolean imp)
    {
        if(id != -1)
            id = 0;
        selectTasks(agendaMain, id, imp);
        if(id != -1)
            id += agendaMain.getAllTasks().size();
        selectMeets(agendaMain, id, imp);
        if(id != -1)
            id += agendaMain.getAllMeets().size();
        selectAlarms(agendaMain, id, imp);
        if(id != -1)
            id += agendaMain.getAllAlarms().size();
        return id;
    }

    public void deleteContents(Agenda agendaMain)
    {
        agendaMain.deleteAll();
    }
}