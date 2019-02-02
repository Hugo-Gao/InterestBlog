package com.gaoyunfan.service;

import com.google.common.collect.Lists;
import com.google.common.io.Files;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.time.Instant;
import java.util.List;

/**
 * @author yunfan.gyf
 **/
@Service
public class FileService {
    @Value("${file.path}")
    private String filePath;

    /**
     * 对每个图片文件，都保存到本地，并且返回相对路径集合
     *
     * @param files
     * @return
     */
    public List<String> getImgPath(List<MultipartFile> files) {
        List<String> paths = Lists.newArrayList();
        for (MultipartFile file : files) {
            File localFile;
            if (!file.isEmpty()) {
                try {
                    localFile = saveToLocal(file, filePath);
                    String path = StringUtils.substringAfterLast(localFile.getAbsolutePath(), filePath);
                    paths.add(path);
                } catch (IOException e) {
                    e.printStackTrace();
                }
                //去除相对路径
            }
        }
        return paths;
    }

    private File saveToLocal(MultipartFile file, String filePath) throws IOException {
        File newFile = new File(filePath + "/" + Instant.now().getEpochSecond() + "/" + file.getOriginalFilename());
        if (!newFile.exists()) {
            newFile.getParentFile().mkdir();
            newFile.createNewFile();
        }
        Files.write(file.getBytes(), newFile);
        return newFile;
    }


}
