package hu.me.fdsz.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@Import({
        SpringFoxConfig.class,
        ModelMapperConfig.class,
        PersistenceConfig.class
})
public class MainConfiguration {

    private static final Logger logger = LoggerFactory.getLogger(MainConfiguration.class.getName());

    public MainConfiguration(){
        logger.info("Start main configuration!");
    }

}
