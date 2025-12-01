interface iAlarm
{
    void changeAlarmName(String alarmName);
    void setSnooze(int snoozeCount, int snoozeTimer);
}

class AlarmObject
{
    String alarmName;

}
class Alarm implements iAlarm
{

    @Override
    public void changeAlarmName(String alarmName) {

    }

    @Override
    public void setSnooze(int snoozeCount, int snoozeTimer) {

    }
}
public class AlarmApp {
}
