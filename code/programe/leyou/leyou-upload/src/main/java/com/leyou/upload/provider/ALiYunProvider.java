package com.leyou.upload.provider;

import com.aliyun.oss.OSS;
import com.aliyun.oss.OSSClientBuilder;
import com.aliyun.oss.model.PutObjectResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.io.InputStream;
import java.net.URL;
import java.util.Date;
import java.util.UUID;

@Service
public class ALiYunProvider {
    // Endpoint以杭州为例，其它Region请按实际情况填写。
    String endpoint= "http://oss-cn-hangzhou.aliyuncs.com";
    // 阿里云主账号AccessKey拥有所有API的访问权限，风险很高。强烈建议您创建并使用RAM账号进行API访问或日常运维，请登录 https://ram.console.aliyun.com 创建RAM账号。

    String bucketName="community-images";

    private String accessKeyId="LTAI4FtHbA2qd7XSj622shtm";

    public static final Logger LOGGER = LoggerFactory.getLogger(ALiYunProvider.class);

    private String accessKeySecret="DaHFKZ7u6CWyeRxYmv4oROmbvvuwww";

    private String packageName="leyou/";

    // 创建OSSClient实例。
    OSS ossClient = new OSSClientBuilder().build(endpoint, accessKeyId, accessKeySecret);
    public String upload(InputStream  fileInputStream,String objectName){
        objectName= UUID.randomUUID()+objectName;
        PutObjectResult putObjectResult = ossClient.putObject(bucketName, packageName+objectName, fileInputStream);
        if(putObjectResult.getETag()==null){
            LOGGER.error("{}图片保存失败",objectName);
            return null;
        }else{
            Date expiration = new Date(System.currentTimeMillis() + 3600L*1000*24*365*10);
            URL url = ossClient.generatePresignedUrl(bucketName, packageName+objectName, expiration);
//            ossClient.shutdown();
//            System.out.println(url.toString());
            return url.toString();
        }
    }
}
