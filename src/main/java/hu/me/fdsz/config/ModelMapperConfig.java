package hu.me.fdsz.config;

import hu.me.fdsz.dto.FeedPostDTO;
import hu.me.fdsz.dto.ImageDTO;
import hu.me.fdsz.model.FeedPost;
import hu.me.fdsz.model.Image;
import hu.me.fdsz.repository.ImageContentStore;
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


    private final ImageContentStore imageContentStore;

    @Autowired
    public ModelMapperConfig(ImageContentStore imageContentStore) {
        this.imageContentStore = imageContentStore;
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
                        ImageDTO imageDTO = new ImageDTO();
                        try {
                            imageDTO.setRawImage(imageContentStore.getContent(source.getImage()).readAllBytes());
                            imageDTO.setImageType(source.getImage().getMimeType());
                            imageDTO.setImageId(source.getImage().getId());
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                        result.setImage(imageDTO);
                        return result;
                    }
                }
        );

    }
}
