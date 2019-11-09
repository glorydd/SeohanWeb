package com.seohan.Config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

import lombok.Data;

@Data
@Component
@Configuration
public class FileUploadProperties {
	@Value("${file.upload-dir}")
    private String uploadDir;
}
