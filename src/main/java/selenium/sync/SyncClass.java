package selenium.sync;

public class SyncClass {

    private static final Object locker = new Object();

    public static void syncTest(boolean lock) {
        synchronized (locker) {
            if (lock) {
                try {
                    locker.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            } else {
                locker.notifyAll();
            }
        }
    }

}
