package com.tang.uploaddemo;

import lombok.Builder;
import lombok.ToString;

import java.io.Serializable;

/**
 * @Description
 * @Author tang
 * @Date 2019-06-17 21:39
 * @Version 1.0
 **/
@Builder
@ToString
public class UploadStatus implements Serializable {

    /**
     * 已上传的字节数
     */
    private long bytesRead;

    /**
     * 上传的总长度（如果为-1则表示总长度未知）
     */
    private long contentLength;

    /**
     * 正在上传第几个文件
     */
    private int items;

    /**
     * 上传速度
     */
    private long uploadRate;

    /**
     * 上传占比
     */
    private int percent;

    /**
     * 上传开始时间
     */
    private long startTime = System.currentTimeMillis();

    public UploadStatus() {
    }

    public UploadStatus(long bytesRead, long contentLength, int items, long uploadRate, int percent, long startTime) {
        this.bytesRead = bytesRead;
        this.contentLength = contentLength;
        this.items = items;
        this.uploadRate = uploadRate;
        this.percent = percent;
        this.startTime = startTime;
    }

    public long getBytesRead() {
        return bytesRead;
    }

    public void setBytesRead(long bytesRead) {
        this.bytesRead = bytesRead;
    }

    public long getContentLength() {
        return contentLength;
    }

    public void setContentLength(long contentLength) {
        this.contentLength = contentLength;
    }

    public int getItems() {
        return items;
    }

    public void setItems(int items) {
        this.items = items;
    }

    public long getUploadRate() {
        return uploadRate;
    }

    public void setUploadRate(long uploadRate) {
        this.uploadRate = uploadRate;
    }

    public long getStartTime() {
        return startTime;
    }

    public void setStartTime(long startTime) {
        this.startTime = startTime;
    }

    public int getPercent() {
        return percent;
    }

    public void setPercent(int percent) {
        this.percent = percent;
    }
}
