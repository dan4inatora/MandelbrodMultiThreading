
public class Timer {
    private long time;

    float stop() {
        long currentTimeMillis = System.currentTimeMillis();
        return currentTimeMillis- time;
    }

    private long getCurrentTime()
    {
        return System.currentTimeMillis();
    }

    public void start()
    {
        time = getCurrentTime();
    }
}
