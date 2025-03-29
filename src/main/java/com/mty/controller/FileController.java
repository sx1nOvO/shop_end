package com.mty.controller;


import com.aliyun.oss.OSS;
import com.aliyun.oss.OSSClientBuilder;
import com.aliyun.oss.model.GeneratePresignedUrlRequest;
import com.mty.config.PassToken;
import org.apache.commons.io.FilenameUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * 系统操作控制器
 */
@RestController
@RequestMapping("file")
public class FileController {

	@Value("${uploadDir}")
	private String uploadDir;

	@Value("${aliyun.oss.endpoint}")
	private String endpoint;

	@Value("${aliyun.oss.accessKeyId}")
	private String accessKeyId;

	@Value("${aliyun.oss.accessKeySecret}")
	private String accessKeySecret;

	@Value("${aliyun.oss.bucketName}")
	private String bucketName;

	@Value("${aliyun.oss.basePath}")
	private String basePath;

	@Value("${aliyun.oss.urlExpireSeconds}")
	private long urlExpireSeconds;


	private OSS ossClient;

	@PostConstruct
	public void init() {
		ossClient = new OSSClientBuilder().build(endpoint, accessKeyId, accessKeySecret);
	}

	@PreDestroy
	public void destroy() {
		if (ossClient != null) {
			ossClient.shutdown();
		}
	}

//	@RequestMapping("/imgUpload")
//	public Map<String, Object> yunUploadFile(@RequestParam("file") MultipartFile multiFile) {
//		Map<String, Object> outMap = new HashMap<>();
//		try {
//			SimpleDateFormat sf = new SimpleDateFormat("yyyyMMddHHmmss");
//			String name = sf.format(new Date());
//			//获取文件的扩展名
//			String ext = FilenameUtils.getExtension(multiFile.getOriginalFilename());
//			//以绝对路径保存重名命后的图片
//			multiFile.transferTo(new File(uploadDir+"/"+name + "." + ext));
//			//jsonObject.put("code",name + "." + ext);
//			outMap.put("imgUrl", "/api/upload/"+name + "." + ext);
//			outMap.put("url", "/api/upload/"+name + "." + ext);
//			outMap.put("message", "上传成功！");
//			outMap.put("result", "true");
//			return outMap;
//		} catch (IOException e) {
//			e.printStackTrace();
//			outMap.put("result", "false");
//			outMap.put("message", "上传失败，请重新上传！");
//		}
//		return outMap;
//	}


	@RequestMapping("/imgUpload")
	public Map<String, Object> yunUploadFile(@RequestParam("file") MultipartFile multiFile) {
		Map<String, Object> outMap = new HashMap<>();
		try {
			SimpleDateFormat sf = new SimpleDateFormat("yyyyMMddHHmmss");
			String name = sf.format(new Date());
			String ext = FilenameUtils.getExtension(multiFile.getOriginalFilename());

			// 本地保存（如果不需要本地保存，可以去掉下面这两行）
//			File localFile = new File(uploadDir + "/" + name + "." + ext);
//			multiFile.transferTo(localFile);

			// 上传到 OSS
			String key = basePath + name + "." + ext; // OSS上的文件路径，比如 "image/20250616123456.jpg"
			// 上传 MultipartFile 转成 InputStream 到 OSS
			try (InputStream inputStream = multiFile.getInputStream()) {
				ossClient.putObject(bucketName, key, inputStream);
			}

			// 拼接访问 URL
			String ossUrlPrefix = "https://sun-close-shop.oss-cn-beijing.aliyuncs.com/";
			String fileUrl = ossUrlPrefix + key;

			// 返回 OSS 访问路径（这里是你自己写的静态资源映射或签名URL接口）
			// 直接返回 /api/upload/... 就是本地映射的地址
			// 这里可以返回 OSS 的 key 路径，让前端调用 getSignedUrl 获取签名URL
			outMap.put("imgUrl", fileUrl);    // 直接返回 OSS 路径（相对路径）
//			outMap.put("url", key);
			outMap.put("message", "上传成功！");
			outMap.put("result", "true");
			return outMap;

		} catch (IOException e) {
			e.printStackTrace();
			outMap.put("result", "false");
			outMap.put("message", "上传失败，请重新上传！");
		}
		return outMap;
	}



	@PassToken
	@GetMapping("/ossImg")
	public void getOssImage(@RequestParam("fileName") String fileName, HttpServletResponse response) {
		String key = basePath + fileName; // 完整路径

		try (InputStream inputStream = ossClient.getObject(bucketName, key).getObjectContent();
			 OutputStream outputStream = response.getOutputStream()) {

			String contentType = "image/upload/jpeg";
			if (fileName.endsWith(".png")) {
				contentType = "image/upload/png";
			} else if (fileName.endsWith(".jpg")) {
				contentType = "image/upload/jpg";
			}
			response.setContentType(contentType);

			byte[] buffer = new byte[8192];
			int length;
			while ((length = inputStream.read(buffer)) > 0) {
				outputStream.write(buffer, 0, length);
			}
			outputStream.flush();
		} catch (Exception e) {
			// 日志异常信息
			e.printStackTrace();
			response.setStatus(404);
		}
	}


	/**
	 * 获取 OSS 私有图片的临时访问链接
	 * @param fileName 传入文件名，例如 test.jpg（不要加 image/）
	 * @return 可访问 URL
	 */
	@PassToken
	@GetMapping("/getSignedUrl")
	public String getSignedUrl(@RequestParam("fileName") String fileName) {
		try {
			String key = basePath + fileName; // 例如 image/test.jpg
			Date expiration = new Date(System.currentTimeMillis() + urlExpireSeconds * 1000);

			GeneratePresignedUrlRequest request = new GeneratePresignedUrlRequest(bucketName, key);
			request.setExpiration(expiration);

			URL signedUrl = ossClient.generatePresignedUrl(request);
			return signedUrl.toString();
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("失败");
			return "生成签名URL失败";
		}
	}


}
