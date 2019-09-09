package com.tang.uploaddemo;

import org.apache.commons.fileupload.*;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.*;
import java.util.Iterator;
import java.util.List;

/**
 * @Description
 * @Author tang
 * @Date 2019-06-16 21:32
 * @Version 1.0
 **/
@Controller
@RequestMapping("upload")
public class UploadController {

    @RequestMapping("/")
    public String toUpload() {
        return "index";
    }

    @RequestMapping("handler")
    @ResponseBody
    public String hanlder(HttpServletRequest request) {
        InputStream inputStream =null;
        OutputStream outputStream = null;
        BufferedInputStream bis = null;
        BufferedOutputStream bos = null;

        try {
            /*DiskFileItemFactory factory = new DiskFileItemFactory();

            ServletFileUpload servletFileUpload = new ServletFileUpload(factory);
            servletFileUpload.setHeaderEncoding("UTF-8");

            UploadListener uploadListener = new UploadListener();
            uploadListener.setSession(request.getSession());
            servletFileUpload.setProgressListener(uploadListener);*/
            /*servletFileUpload.setProgressListener((bytesRate, contextLength, item) -> {
                HttpSession session = request.getSession();

                UploadStatus uploadStatus = UploadStatus.builder()
                        .bytesRead(bytesRate)
                        .contentLength(contextLength)
                        .items(item)
                        .build();

                session.setAttribute("uploadStatus", uploadStatus);

                System.out.println("uploadStatus = " + uploadStatus.toString());
            });*/

            // servletFileUpload.setSizeMax(1024 * 102);
            /*List<FileItem> items = servletFileUpload.parseRequest(request);
            Iterator<FileItem> itemIterator = items.iterator();
            while (itemIterator.hasNext()) {
                FileItem fileItem = itemIterator.next();
                inputStream = fileItem.getInputStream();

                bis = new BufferedInputStream(inputStream);
                System.out.println("fileItem.getName() = " + fileItem.getName());
                System.out.println("fileItem.getFieldName() = " + fileItem.getFieldName());
                outputStream = new FileOutputStream("/Users/tang/Desktop/" + fileItem.getName());
                bos = new BufferedOutputStream(outputStream);
                //bos.write(bytes);
                byte[] data = new byte[2048];
                int flag = 0;
                while ((flag = bis.read(data)) != -1) {
                    bos.write(data, 0, flag);
                }

                bos.flush();
            }*/

            MultipartHttpServletRequest multipartHttpServletRequest = (MultipartHttpServletRequest) request;
            MultipartFile uploadFile = multipartHttpServletRequest.getFile("uploadFile");
            inputStream = uploadFile.getInputStream();

            bis = new BufferedInputStream(inputStream);
            outputStream = new FileOutputStream("/Users/tang/Desktop/" + uploadFile.getOriginalFilename());
            bos = new BufferedOutputStream(outputStream);
            //bos.write(bytes);
            byte[] data = new byte[2048];
            int flag = 0;
            while ((flag = bis.read(data)) != -1) {
                bos.write(data, 0, flag);
            }

            bos.flush();

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (bos != null) {
                    bos.close();
                }
                if (outputStream != null) {
                    outputStream.close();
                }
                if (bis != null) {
                    bis.close();
                }
                if (inputStream != null) {
                    inputStream.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        return "";
    }

    @RequestMapping("getMsg")
    @ResponseBody
    public UploadStatus getUploadMsg(HttpServletRequest request) {
        HttpSession session = request.getSession();
        UploadStatus uploadStatus = (UploadStatus) session.getAttribute("uploadStatus");
        return uploadStatus;
    }
}
