package com.wordnet.community.dao.entity;

import lombok.Builder;
import lombok.Data;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.web.multipart.MultipartFile;

import javax.persistence.Entity;
import javax.validation.constraints.Size;
import java.io.File;

@Data
public class Profile {

    @Size(max = 1)
    private String sex;

    private Integer age;

    private MultipartFile avatar;

    @Size(min = 1, max = 250)
    private String nickname;

    @Size(max =250)
    private String introduction;
}
