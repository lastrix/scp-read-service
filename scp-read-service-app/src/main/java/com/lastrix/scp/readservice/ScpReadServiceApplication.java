package com.lastrix.scp.readservice;

import com.lastrix.scp.lib.db.SchemaInit;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Import;

@SpringBootApplication(scanBasePackages = "com.lastrix.scp.readservice")
@Import(SchemaInit.class)
public class ScpReadServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(ScpReadServiceApplication.class, args);
    }

}
