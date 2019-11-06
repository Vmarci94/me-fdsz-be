package hu.me.fdsz.config;

import hu.me.fdsz.dto.FeedPostDTO;
import hu.me.fdsz.model.FeedPost;
import hu.me.fdsz.model.Image;
import hu.me.fdsz.repository.ImageContentStore;
import hu.me.fdsz.repository.ImageRepository;
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


    @Bean
    @Scope(value = ConfigurableBeanFactory.SCOPE_SINGLETON, proxyMode = ScopedProxyMode.TARGET_CLASS)
    @Autowired
    public ModelMapper modelMapper(ImageContentStore imageContentStore) {
        ModelMapper singletonModelMapper = new ModelMapper();
        setCustomConverters(singletonModelMapper, imageContentStore);
        return singletonModelMapper;
    }

    /**
     * @param singletonModelMapper referenciaként hivatkozva, beállítja a szükséges convertereket.
     * @param imageContentStore
     */
    private void setCustomConverters(final ModelMapper singletonModelMapper, ImageContentStore imageContentStore) {
        singletonModelMapper.addConverter(
                new AbstractConverter<MultipartFile, Image>() {
                    @Override
                    protected Image convert(MultipartFile source) {
                        Image resultImage = new Image();
                        resultImage.setContentLength(source.getSize());
                        resultImage.setMimeType(source.getContentType());
                        resultImage.setImageName(source.getOriginalFilename());
                        try {
                            resultImage.setInputStream(source.getInputStream());
                        } catch (IOException e) {
                            e.printStackTrace(); //FIXME logger kellene
                        }
                        return resultImage;
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
                        try {
                            result.setImage( imageContentStore.getContent(source.getImage()).readAllBytes() );
                        } catch (IOException e) {
                            e.printStackTrace(); //FIXME kell log arról, hogy nagy baj van
                        }
                        return result;
                    }
                }
        );

    }
}
