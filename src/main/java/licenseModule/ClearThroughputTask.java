package licenseModule;

import java.util.TimerTask;

/**
 * Created by joshoy on 16/4/12.
 */
public class ClearThroughputTask extends TimerTask {

    protected Integer throughputValue;

    public ClearThroughputTask(Integer throughputValueRef) {
        this.throughputValue = throughputValueRef;
    }

    @Override
    public void run() {
        this.throughputValue = new Integer(0);
    }
}
