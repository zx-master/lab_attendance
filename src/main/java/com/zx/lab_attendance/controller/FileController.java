package com.zx.lab_attendance.controller;

import com.mysql.cj.jdbc.util.ResultSetUtil;
import com.zx.lab_attendance.entity.JsonData;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import springfox.documentation.spring.web.json.Json;
import sun.misc.BASE64Decoder;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
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
@CrossOrigin
@Controller
@RequestMapping("/file")
@Api(value = "文件上传")
public class FileController {

    @Value("${img.upload-path}")
    private String uploadPath;

    @Value("${img.img-url}")
    private String imgUrl;

    @PostMapping("/upload")
    @ResponseBody
    public JsonData upload (@RequestParam("base64Data") String base64Data, HttpServletResponse response, HttpServletRequest request) {
        response.setHeader("Access-Control-Allow-Origin","*");
        String dataPrix = ""; //base64格式前头
        String data = "";//实体部分数据
        if(base64Data==null||"".equals(base64Data)){
            return JsonData.buildError("上传失败，上传图片数据为空");
        }else {
            String [] d = base64Data.split("base64,");//将字符串分成数组
            if(d != null && d.length == 2){
                dataPrix = d[0];
                data = d[1];
            }else {
                return JsonData.buildError("上传失败，数据不合法");
            }
        }
        String suffix = "";//图片后缀，用以识别哪种格式数据
        //data:image/jpeg;base64,base64编码的jpeg图片数据
        if("data:image/jpeg;".equalsIgnoreCase(dataPrix)){
            suffix = ".jpg";
        }else if("data:image/x-icon;".equalsIgnoreCase(dataPrix)){
            //data:image/x-icon;base64,base64编码的icon图片数据
            suffix = ".ico";
        }else if("data:image/gif;".equalsIgnoreCase(dataPrix)){
            //data:image/gif;base64,base64编码的gif图片数据
            suffix = ".gif";
        }else if("data:image/png;".equalsIgnoreCase(dataPrix)){
            //data:image/png;base64,base64编码的png图片数据
            suffix = ".png";
        }else {
            return JsonData.buildError("上传图片格式不合法");
        }
        String uuid = UUID.randomUUID().toString().replaceAll("-", "");
        String tempFileName=uuid+suffix;
        String imgFilePath = "g:/Ideaspace/lab_attendance/src/main/resources/static/"+tempFileName;//新生成的图片
        BASE64Decoder decoder = new BASE64Decoder();
        try {
            //Base64解码
            byte[] b = decoder.decodeBuffer(data);
            for(int i=0;i<b.length;++i) {
                if(b[i]<0) {
                    //调整异常数据
                    b[i]+=256;
                }
            }
            OutputStream out = new FileOutputStream(imgFilePath);
            out.write(b);
            out.flush();
            out.close();
            String imgurl="http://localhost:8080/"+tempFileName;
            //imageService.save(imgurl);
            return JsonData.buildSuccess(imgurl,"上传成功");
        } catch (IOException e) {
            e.printStackTrace();
            return JsonData.buildError("上传图片失败");
        }
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

