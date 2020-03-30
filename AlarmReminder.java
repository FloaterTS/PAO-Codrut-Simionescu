package clase;

public class AlarmReminder extends Alarm
{
    String textRemind;

    public AlarmReminder(int _ora, int minut, String remind)
    {
        super(_ora, minut);
        textRemind = remind;
    }

    public void setTextRemind(String remind)
    {
        textRemind = remind;
    }

    public String getTextRemind()
    {
        return textRemind;
    }

    public void getInfo()
    {
        System.out.print("-reminder: " + textRemind + "; ");
    }
}
