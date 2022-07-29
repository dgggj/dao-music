package com.che.smartkitchen.service.serviceImpl;

import com.che.smartkitchen.dto.FileOSSUploadDto;
import com.che.smartkitchen.dto.FileUploadDto;
import com.che.smartkitchen.exception.BizException;
import com.che.smartkitchen.exception.ExceptionType;
import com.che.smartkitchen.service.StorageService;
import com.tencent.cloud.CosStsClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.TreeMap;

//阿里云OSS存储的令牌获取方法
@Service("COS")
public class OssStorageServiceImpl implements StorageService {
//    @Autowired
//    OSS ossClient;
//
//    @Value("${spring.cloud.alicloud.oss.endpoint}")
//    private String endpoint;
//
//    @Value("${spring.cloud.alicloud.oss.bucket}")
//    private String bucket1;
//
//    @Value("${spring.cloud.alicloud.access-key}")
//    private String accessId;

    @Override
    public FileOSSUploadDto initOSSFileUpload() {
//        String host = "https://" + bucket1 + "." + endpoint; // host的格式为 bucketname.endpoint
//        // callbackUrl为上传回调服务器的URL，请将下面的IP和Port配置为您自己的真实信息。
////        String callbackUrl = "http://88.88.88.88:8888";
//        String format = new SimpleDateFormat("yyyy-MM-dd").format(new Date());
//        String dir = format + "/"; // 用户上传文件时指定的前缀。
//        Map<String, String> respMap = null;
//        FileOSSUploadDto fileOSSUploadDto = new FileOSSUploadDto();
//
//        // 创建OSSClient实例。
//        try {
//            long expireTime = 60;
//            long expireEndTime = System.currentTimeMillis() + expireTime * 1000;
//            Date expiration = new Date(expireEndTime);
//            // PostObject请求最大可支持的文件大小为5 GB，即CONTENT_LENGTH_RANGE为5*1024*1024*1024。
//            PolicyConditions policyConds = new PolicyConditions();
//            policyConds.addConditionItem(PolicyConditions.COND_CONTENT_LENGTH_RANGE, 0, 1048576000);
//            policyConds.addConditionItem(MatchMode.StartWith, PolicyConditions.COND_KEY, dir);
//
//            String postPolicy = ossClient.generatePostPolicy(expiration, policyConds);
//            byte[] binaryData = postPolicy.getBytes("utf-8");
//            String encodedPolicy = BinaryUtil.toBase64String(binaryData);
//            String postSignature = ossClient.calculatePostSignature(postPolicy);
//            fileOSSUploadDto.setAccessId(accessId);
//            fileOSSUploadDto.setDir(dir);
//            fileOSSUploadDto.setExpire(String.valueOf(expireEndTime / 1000));
//            fileOSSUploadDto.setHost(host);
//            fileOSSUploadDto.setPolicy(encodedPolicy);
//            fileOSSUploadDto.setSignature(postSignature);
//
//
//        } catch (Exception e) {
//            // Assert.fail(e.getMessage());
//            System.out.println(e.getMessage());
//        } finally {
//            ossClient.shutdown();
//        }
//        return fileOSSUploadDto;
        return null;
    }

    @Value("qiamusic-1311097617")
    private String bucket;

    @Value("AKIDkgmoY1C6jBMGvQebHhrlRu774uWv76tT")
    private String secretId;

    @Value("QZR4UlrjMlvqa5NQFvOccOu98tPinfJ1")
    private String secretKey;

    @Value("ap-nanjing")
    private String region;

    @Override
    public FileUploadDto initFileUpload() throws IOException {
        try {
            com.tencent.cloud.Response response = CosStsClient.getCredential(getCosStsConfig());
            FileUploadDto fileUploadDto = new FileUploadDto();

            fileUploadDto.setSecretId(response.credentials.tmpSecretId);
            fileUploadDto.setSecretKey(response.credentials.tmpSecretKey);

            fileUploadDto.setSessionToken(response.credentials.sessionToken);
            fileUploadDto.setStartTime(response.startTime);
            fileUploadDto.setExpiredTime(response.expiredTime);
            return fileUploadDto;
        } catch (Exception e) {
            e.printStackTrace();
            throw new BizException(ExceptionType.INNER_ERROR);
        }
    }

    private TreeMap<String, Object> getCosStsConfig() {
        TreeMap<String, Object> config = new TreeMap<String, Object>();
        config.put("secretId", secretId);
        config.put("secretKey", secretKey);

        // 临时密钥有效时长，单位是秒
        config.put("durationSeconds", 1800);
        config.put("allowPrefixes", new String[]{
                "*"
        });
        config.put("bucket", bucket);
        config.put("region", region);
        String[] allowActions = new String[]{
                // 简单上传
                "name/cos:PutObject",
                "name/cos:PostObject",
                // 分片上传
                "name/cos:InitiateMultipartUpload",
                "name/cos:ListMultipartUploads",
                "name/cos:ListParts",
                "name/cos:UploadPart",
                "name/cos:CompleteMultipartUpload"
        };
        config.put("allowActions", allowActions);
        return config;
    }
}
