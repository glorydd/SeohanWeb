package com.seohan.config;

import org.springframework.boot.context.properties.ConfigurationProperties;

import lombok.Data;

@Data
@ConfigurationProperties(prefix="file")
public class FileUploadProperties {
    private String uploadDir;
}
