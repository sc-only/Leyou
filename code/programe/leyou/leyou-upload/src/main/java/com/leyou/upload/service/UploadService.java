package com.leyou.upload.service;

import com.leyou.upload.provider.ALiYunProvider;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

@Service
public class UploadService {

    @Autowired
    private ALiYunProvider aLiYunProvider;

    public static final List<String> CONTENT_TYPE= Arrays.asList("image/gif","image/jpeg","application/x-png");

    public static final Logger LOGGER = LoggerFactory.getLogger(UploadService.class);

    public String uploadImage(MultipartFile file) {

        String originalFilename = file.getOriginalFilename();
//        StringUtils.substringAfterLast(originalFilename,".");
        //TODO:: 校验文件类型
        String contentType = file.getContentType();
        if(!CONTENT_TYPE.contains(contentType)){
            LOGGER.info("文件类型不合法：{}", originalFilename);
            return null;
        }
        try {
            //TODO:: 校验文件内容
            BufferedImage bufferedImage = ImageIO.read(file.getInputStream());
            if(bufferedImage==null){
                LOGGER.info("文件内容不合法：{}",originalFilename);
                return null;
            }
            //TODO:: 保存到服务器
//            file.transferTo(new File("E:\\Leyou\\tools\\image\\"+originalFilename));
            String url = aLiYunProvider.upload(file.getInputStream(), file.getOriginalFilename());
            //TODO:: 返回url，进行回显
//            return "http://image.leyou.com/"+originalFilename;
            return url;
        } catch (IOException e) {
            LOGGER.info("服务器内部错误：{}",originalFilename);
            e.printStackTrace();
        }
        return null;
    }
}
