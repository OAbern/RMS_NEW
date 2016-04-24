package com.cqupt.mis.rms.vo;

/**
 * 操作结果对象
 * <p>封装部分逻辑层的操作结果</p>
 * @author bern
 * Created by bern on 2016/4/23.
 */
public class ResultInfo<T> {
    /**
     * 需要返回的数据对象
     */
    T object;

    /**
     * 操作的结果
     */
    boolean result;

    /**
     * 失败的原因
     */
    String failedReason;

    public ResultInfo() {

    }

    public ResultInfo(T object, boolean result) {
        this(object, result, null);
    }

    public ResultInfo(boolean result, String failedReason) {
        this(null, result, failedReason);
    }

    public ResultInfo(T object, boolean result, String failedReason) {
        this.object = object;
        this.result = result;
        this.failedReason = failedReason;
    }

    public boolean isResult() {
        return result;
    }

    public void setResult(boolean result) {
        this.result = result;
    }

    public T getObject() {
        return object;
    }

    public void setObject(T object) {
        this.object = object;
    }

    public String getFailedReason() {
        return failedReason;
    }

    public void setFailedReason(String failedReason) {
        this.failedReason = failedReason;
    }
}
