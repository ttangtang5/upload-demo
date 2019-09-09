package com.tang.uploaddemo;

import org.apache.commons.fileupload.ProgressListener;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpSession;
import java.awt.*;
import java.math.BigDecimal;
import java.time.Duration;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.temporal.Temporal;


/**
 * @Description 上传监听器
 * @Author tang
 * @Date 2019-06-17 21:37
 * @Version 1.0
 **/
public class UploadListener implements ProgressListener {

    private HttpSession session;

    public HttpSession getSession() {
        return session;
    }

    public void setSession(HttpSession session) {
        this.session = session;
        UploadStatus uploadStatus = new UploadStatus();
        session.setAttribute("uploadStatus", uploadStatus);
    }

    /**
     *
     * @param l 已上传的字节数
     * @param l1 上传的总长度
     * @param i 正在上传第几个文件
     */
    @Override
    public void update(long l, long l1, int i) {
        UploadStatus uploadStatus = (UploadStatus) session.getAttribute("uploadStatus");
        BigDecimal bytesRead = BigDecimal.valueOf(l);
        BigDecimal contextLength = BigDecimal.valueOf(l1);
        BigDecimal resultBigDecimal = bytesRead.divide(contextLength, 2, BigDecimal.ROUND_DOWN);
        int result = (int)(resultBigDecimal.floatValue() * 100);
        System.out.println("percent = " + result);
        long oldBytesRead = uploadStatus.getBytesRead();

        long currentTime = System.currentTimeMillis();
        long startTime = uploadStatus.getStartTime();
        long seconds = (currentTime - startTime) / 1000 + 1;
        System.out.println("seconds = " + seconds);
        long rateKb = oldBytesRead / 1024 / seconds;
        System.out.println("rateKb = " + rateKb);
        System.out.println(" ===========");
        uploadStatus.setBytesRead(l);
        uploadStatus.setContentLength(l1);
        uploadStatus.setItems(i);
        uploadStatus.setUploadRate(rateKb);
        uploadStatus.setPercent(result);
    }

}
