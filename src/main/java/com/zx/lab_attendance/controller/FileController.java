package com.zx.lab_attendance.controller;

import com.mysql.cj.jdbc.util.ResultSetUtil;
import com.zx.lab_attendance.entity.JsonData;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileInputStream;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * @author zx
 * @version 1.0
 * @date 2020/2/19 12:22
 * @Description
 */
@Controller
@RequestMapping("/file")
public class FileController {

    @Value("${img.upload-path}")
    private String uploadPath;

    @Value("${img.img-url}")
    private String imgUrl;

    @RequestMapping("/upload")
    @ResponseBody
    public JsonData upload (@RequestParam("files") MultipartFile[] files) {
        List<String> urls = new ArrayList<>();

        if (files.length == 0){
            return JsonData.buildError("接受不到图片");
        }
        try {
            for (MultipartFile file : files) {
                String fileName = UUID.randomUUID()+file.getOriginalFilename();
                Files.write(Paths.get("g:/Ideaspace/lab_attendance/src/main/resources/static/" + fileName),file.getBytes());
                urls.add(imgUrl + fileName);
            }
        }catch (Exception e){
            e.printStackTrace();
            return JsonData.buildError("上传失败");
        }
        return JsonData.buildSuccess(urls);
//        // 获取原始名字
//        String fileName = file.getOriginalFilename();
//        // 获取后缀名
//        // String suffixName = fileName.substring(fileName.lastIndexOf("."));
//        // 文件保存路径
//        String filePath = "d:/upload/";
//        // 文件重命名，防止重复
//        fileName = filePath + UUID.randomUUID() + fileName;
//        // 文件对象
//        File dest = new File(fileName);
//        // 判断路径是否存在，如果不存在则创建
//        if(!dest.getParentFile().exists()) {
//            dest.getParentFile().mkdirs();
//        }
//        try {
//            // 保存到服务器中
//            file.transferTo(dest);
//            return "上传成功";
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        return "上传失败";
    }

    @RequestMapping("/download")
    public void download(HttpServletResponse response,@RequestParam("fileName")String fileName) throws Exception {
        // 文件地址，真实环境是存放在数据库中的 7a31c4c3-ffb9-48c9-a0b3-c2b4c4412a91我愿意平凡的陪在你身旁guitar.png
        File file = new File("D:\\upload\\" + fileName);
        // 穿件输入对象
        FileInputStream fis = new FileInputStream(file);
        // 设置相关格式
        response.setContentType("application/force-download");
        // 设置下载后的文件名以及header
        response.addHeader("Content-disposition", "attachment;fileName=" + "a.png");
        // 创建输出对象
        OutputStream os = response.getOutputStream();
        // 常规操作
        byte[] buf = new byte[1024];
        int len = 0;
        while((len = fis.read(buf)) != -1) {
            os.write(buf, 0, len);
        }
        fis.close();
    }

}

