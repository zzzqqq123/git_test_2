package com.utils.ftp;

import com.jcraft.jsch.*;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.commons.CommonsMultipartFile;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;

import javax.servlet.http.HttpServletRequest;
import java.io.*;
import java.util.Date;


/**
 * @ProjectName: springboot-utils
 * @Package: com.utils.ftp
 * @ClassName: UploadFileUtil
 * @Author: zhangqiang
 * @Description:
 * @Date: 2019/11/27 2:12 下午
 * @Version: 1.0
 */
public class UploadFileUtil {
    private static ChannelSftp sftp;

    public static void upLoadToIgenetech(String content, String fileName) {
        String dPath = "/root/demo/";
        JSch jsch = new JSch();
        Session session = null;
        try {
            //用户名、ip地址、端口号
            session = jsch.getSession("root", "49.232.153.99", 22);
        } catch (JSchException e) {
            e.printStackTrace();
        }
        // 设置登陆主机的密码
        session.setPassword("zhang@123");// 设置密码
        // 设置第一次登陆的时候提示，可选值：(ask | yes | no)
        session.setConfig("StrictHostKeyChecking", "no");
        // 设置登陆超时时间
        try {
            session.connect(300000);
        } catch (JSchException e) {
            e.printStackTrace();
        }
        Channel channel = null;
        try {
            channel = (Channel) session.openChannel("sftp");
            channel.connect(10000000);
            ChannelSftp sftp = (ChannelSftp) channel;
            String lastPath = "123";
            try {
                sftp.cd(dPath + lastPath);
            } catch (SftpException e) {
                sftp.mkdir(dPath + lastPath);
                sftp.cd(dPath + lastPath);
            }
            OutputStream o = null;
            File file = new File(dPath + lastPath + "/" + fileName);
            o = sftp.put(file.getName());
            o.write(content.toString().getBytes("UTF-8"));
            o.close();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            session.disconnect();
            channel.disconnect();
        }
    }


    public static ChannelSftp upLoadToIgenetech() {
        String dPath = "/root/demo/";
        JSch jsch = new JSch();
        Session session = null;
        try {
            //用户名、ip地址、端口号
            session = jsch.getSession("root", "49.232.153.99", 22);
        } catch (JSchException e) {
            e.printStackTrace();
        }
        // 设置登陆主机的密码
        session.setPassword("zhang@123");// 设置密码
        // 设置第一次登陆的时候提示，可选值：(ask | yes | no)
        session.setConfig("StrictHostKeyChecking", "no");
        // 设置登陆超时时间
        try {
            session.connect(300000);
        } catch (JSchException e) {
            e.printStackTrace();
        }
        Channel channel = null;
        try {
            channel = (Channel) session.openChannel("sftp");
            channel.connect(10000000);
            sftp = (ChannelSftp) channel;
            String lastPath = "123";
            try {
                sftp.cd(dPath + lastPath);
            } catch (SftpException e) {
                sftp.mkdir(dPath + lastPath);
                sftp.cd(dPath + lastPath);
            }
            return sftp;

        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public static void test(String filePath, String fileName, String value) throws SftpException, IOException {
        OutputStream o = null;
        File file = new File(filePath + "/" + fileName);
        if (sftp == null) {
            upLoadToIgenetech();
        }
        o = sftp.put(file.getName());
        o.write(value.toString().getBytes("UTF-8"));
        o.flush();
        o.close();

    }

    public static void main(String[] args) throws IOException, SftpException {

        upLoadToIgenetech("123", "test");
        for (int i = 0; i < 100; i++) {
            test("/root/demo/", "21", "测试一个故事");
        }
        System.out.println("完成");

    }

}
