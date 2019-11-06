package hu.me.fdsz.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.core.annotation.Order;

@Configuration
@Import({
        SpringFoxConfig.class,
        ModelMapperConfig.class
})
public class MainConfiguration {

    public MainConfiguration(){
        System.out.println("Start main configuration!");
    }

}
