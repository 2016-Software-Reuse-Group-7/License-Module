package licenseModule;

import java.util.Timer;

/**
 * Created by joshoy on 16/4/12.
 */
public class LicenseImpl implements License {

    /**
     * license名称
     */
    protected String licenseTypename;

    /**
     * 当前吞吐量
     */
    protected Integer currentThroughput;
    /**
     * 最大吞吐量
     */
    protected Integer maxThroughPut;

    /**
     * 该license支持的最大流量
     */
    protected Integer maxCapacity;

    /**
     * 当前接收过的消息数量总计
     */
    protected Integer messageTotalCount;

    /**
     * 清除吞吐量的时间间隔(秒)
     */
    protected Double clearPeriodSeconds;

    protected ClearThroughputTask clearIntervalTask;
    protected Timer clearTimer;

    /**
     * @param typeName
     * @param maxThroughPut
     * @param maxCapacity
     * @param clearPeriodSecond
     * @throws InvalidLicenseNumberException
     */
    public LicenseImpl(String typeName, int maxThroughPut, int maxCapacity, int clearPeriodSecond) throws InvalidLicenseNumberException {
        this.setLicenseTypename(typeName);
        this.setMaxThroughput(maxThroughPut);
        this.setMaxCapacity(maxCapacity);

        this.currentThroughput = new Integer(0);
        this.messageTotalCount = new Integer(0);

        this.clearTimer = new Timer();
        this.clearIntervalTask = new ClearThroughputTask(this.currentThroughput);
        this.clearTimer.scheduleAtFixedRate(this.clearIntervalTask, 0, (long)(this.clearPeriodSeconds * 1000));
    }

    /**
     * @param typeName: license type name
     */
    public void setLicenseTypename(String typeName) {
        this.licenseTypename = typeName;
    }

    /**
     * @return license type name
     */
    public String getLicenseTypename() {
        return this.licenseTypename;
    }

    /**
     * 当server端接收到新的消息时, 应做的逻辑判断
     */
    public void onReceiveNewRequest() {
        this.currentThroughput += 1;
        this.messageTotalCount += 1;
    }

    /**
     * 检查吞吐量是否超过上限
     * @return true: 未超过上限 / false: 已超过上限
     */
    public boolean checkThroughput() {
        return (this.currentThroughput <= this.maxThroughPut);
    }

    /**
     * 检查总消息量是否超过上限
     * @return true: 未超过上限 / false: 已超过上限
     */
    public boolean checkCapacity() {
        return (this.messageTotalCount <= this.maxCapacity);
    }

    /**
     * @param maxThroughput: 最大吞吐量
     * @throws InvalidLicenseNumberException (设置值 < 0)
     */
    public void setMaxThroughput(int maxThroughput) throws InvalidLicenseNumberException {
        if (this.maxThroughPut < 0) {
            throw new InvalidLicenseNumberException();
        }
        this.maxThroughPut = new Integer(maxThroughput);
    }

    /**
     * @param maxCapacity
     * @throws InvalidLicenseNumberException
     */
    public void setMaxCapacity(int maxCapacity) throws InvalidLicenseNumberException {
        if (this.maxCapacity < 0) {
            throw new InvalidLicenseNumberException();
        }
        this.maxCapacity = new Integer(maxCapacity);
    }

    /**
     * @param clearPeriodSecond
     * @throws InvalidLicenseNumberException
     */
    public void setClearPeriodSeconds(double clearPeriodSecond) throws InvalidLicenseNumberException {
        if (clearPeriodSecond < 0) {
            throw new InvalidLicenseNumberException();
        }
        this.clearPeriodSeconds = new Double(clearPeriodSecond);
    }
}
