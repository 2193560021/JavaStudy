package com.lyy.ioc;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Data
@Component("DataConfig")
public class DataConfig {

    @Value("localhost")
    private String url;

    @Value("my_db")
    private String driverName;

    @Value("root")
    private String userName;

    @Value("123456")
    private String passWord;

}
