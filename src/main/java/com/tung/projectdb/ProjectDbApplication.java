package com.tung.projectdb;

import com.tung.projectdb.model.Data;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ProjectDbApplication {

    public static void main(String[] args) {
        SpringApplication.run(ProjectDbApplication.class, args);
        Data.create();
    }

}
