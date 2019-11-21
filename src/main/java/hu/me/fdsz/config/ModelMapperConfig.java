package hu.me.fdsz.config;

import hu.me.fdsz.dto.FeedPostDTO;
import hu.me.fdsz.model.FeedPost;
import hu.me.fdsz.model.Image;
import hu.me.fdsz.service.api.ImageService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.modelmapper.AbstractConverter;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Configuration
public class ModelMapperConfig {

    private static final Logger logger = LogManager.getLogger("Model mapping");

    private final ImageService imageService;

    @Autowired
    public ModelMapperConfig(ImageService imageService) {
        this.imageService = imageService;
    }


    @Bean
    @Scope(value = ConfigurableBeanFactory.SCOPE_SINGLETON, proxyMode = ScopedProxyMode.TARGET_CLASS)
    public ModelMapper modelMapper() {
        ModelMapper singletonModelMapper = new ModelMapper();
        setCustomConverters(singletonModelMapper);
        return singletonModelMapper;
    }

    /**
     * @param singletonModelMapper referenciaként hivatkozva, beállítja a szükséges convertereket.
     */
    private void setCustomConverters(final ModelMapper singletonModelMapper) {
        singletonModelMapper.addConverter(
                new AbstractConverter<MultipartFile, Image>() {
                    @Override
                    protected Image convert(MultipartFile source) {
                        try {
                            return imageService.createImageFromMultipartFile(source);
                        } catch (IOException e) {
                            logger.error("Nem sikerült létrehozni a image fájlt.");
                        }
                        return null;
                    }
                }
        );

        singletonModelMapper.addConverter(
                new AbstractConverter<FeedPost, FeedPostDTO>() {
                    @Override
                    protected FeedPostDTO convert(FeedPost source) {
                        FeedPostDTO result = new FeedPostDTO();
                        result.setTitle(source.getTitle());
                        result.setContentText(source.getContentText());
                        result.setIntroductionText(source.getIntroduction());
                        result.setImageId(source.getImage().getId());
                        return result;
                    }
                }
        );

    }
}
