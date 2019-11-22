package hu.me.fdsz.service.impl;

import hu.me.fdsz.dto.FeedPostDTO;
import hu.me.fdsz.model.FeedPost;
import hu.me.fdsz.model.Image;
import hu.me.fdsz.model.User;
import hu.me.fdsz.repository.FeedPostRepository;
import hu.me.fdsz.repository.ImageContentStore;
import hu.me.fdsz.repository.ImageRepository;
import hu.me.fdsz.service.api.FeedService;
import hu.me.fdsz.service.api.JwtTokenProvider;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import javax.persistence.EntityNotFoundException;
import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class FeedServiceImpl implements FeedService {

    private static final Logger logger = LoggerFactory.getLogger(FeedService.class.getName());

    private final FeedPostRepository feedPostRepository;

    private final ModelMapper modelMapper;

    private final JwtTokenProvider jwtTokenProvider;

    private final ImageRepository imageRepository;

    private final ImageContentStore imageContentStore;

    @Autowired
    public FeedServiceImpl(FeedPostRepository feedPostRepository, ModelMapper modelMapper, JwtTokenProvider jwtTokenProvider, ImageRepository imageRepository, ImageContentStore imageContentStore) {
        this.feedPostRepository = feedPostRepository;
        this.modelMapper = modelMapper;
        this.jwtTokenProvider = jwtTokenProvider;
        this.imageRepository = imageRepository;
        this.imageContentStore = imageContentStore;
    }

    @Override
    public List<FeedPostDTO> getAll() {
        return feedPostRepository.findAll().stream()
                .map(feedPost -> modelMapper.getTypeMap(FeedPost.class, FeedPostDTO.class).map(feedPost))
                .collect(Collectors.toList());
    }

    @Override
    public FeedPostDTO add(FeedPostDTO feedPostDTO, MultipartFile multipartFile) throws IOException {
        FeedPost newFeedPost = modelMapper.map(feedPostDTO, FeedPost.class);
        // Azért kell így haszánlni a modelMappert-t, hogy biztosan a megfelelő convertert használja.
        // A MultipartFile Interfacet realizáló osztályok nem találnak rá autómatikusan a megfelelő TypeMap-re.
        Image image = modelMapper.getTypeMap(MultipartFile.class, Image.class).map(multipartFile);
        //add neki contentId-t
        //majd mentjük, mert contentId-val együtt kell perzisztálni.
        image = imageRepository.save(image); //Visszaadja a már perzisztált Entitást
        newFeedPost.setImage(image);
        User currentUser = jwtTokenProvider.getAuthenticatedUser();
        newFeedPost.setAuthor(currentUser);
        newFeedPost = feedPostRepository.save(newFeedPost);
        return modelMapper.map(newFeedPost, FeedPostDTO.class);
    }

    @Override
    public ResponseEntity<HttpStatus> setContent(Long feedPostId, MultipartFile file) {
        return null;
    }

    @Override
    public FeedPostDTO getContent(Long feedPostId) {
        return feedPostRepository.findById(feedPostId).map(feedPost -> {
            FeedPostDTO result = modelMapper.map(feedPost, FeedPostDTO.class);
            return result;
        }).orElseThrow(EntityNotFoundException::new);
    }

    @Override
    public List<FeedPostDTO> getPostsWithLimit(int limit) {
        return feedPostRepository.findByOrderByLastModifiedDate(PageRequest.of(0, 5))
                .stream().map(feedPost -> modelMapper.map(feedPost, FeedPostDTO.class))
                .collect(Collectors.toList());
    }

    @Override
    public FeedPostDTO findById(long postId) {
        return feedPostRepository.findById(postId)
                .map(feedPost -> modelMapper.getTypeMap(FeedPost.class, FeedPostDTO.class).map(feedPost))
                .orElseThrow(EntityNotFoundException::new);
    }

    @Override
    public FeedPostDTO update(FeedPostDTO feedPostDTO, MultipartFile multipartFile) throws RuntimeException, IOException {
        if (feedPostDTO == null || feedPostDTO.getId() == null) {
            throw new NullPointerException("A frissítendő post nem lehet null");
        }

        if (feedPostRepository.findById(feedPostDTO.getId()).isPresent()) {
            //létezik a frissítendő post
            FeedPost newFeedPost = modelMapper.map(feedPostDTO, FeedPost.class);

            //Azért, hogy módosítás esetén törölni is lehessen a képet, mindig frissítjük a képt is.

            if (newFeedPost.getImage() != null) {
                //ha volt régi kép akkor először minden féle képpen töröljük
                Image oldImage = newFeedPost.getImage();
                //TODO: nem elég csak a rekordot törlni, és akkor törlődik a fájl is?
                imageContentStore.unsetContent(oldImage); //fájl törlése
                imageRepository.delete(oldImage); //rekord törlése
            }

            if (multipartFile != null) {
                //ha jött kép az FE-ről akkor elmentjük.

                //csinálunk az input-ból Image objektumot, ez menti is a fájlt
                Image newImage = modelMapper.getTypeMap(MultipartFile.class, Image.class).map(multipartFile);
                newImage = imageRepository.save(newImage); //mentjük a rekordot
                newFeedPost.setImage(newImage);
            } else {
                //ha nem jött kép, akkor nem akarunk újatt beállítani
                //biztonság kedvéért azért nullozunk egyet.
                newFeedPost.setImage(null);
            }

            //majd mentjük a változásokat, ez elvileg ráment a régire mert az ID-ja megegyezik a feedPost objektuméval.
            newFeedPost = feedPostRepository.save(newFeedPost);
            return modelMapper.map(newFeedPost, FeedPostDTO.class);
        } else {
            // nem létezik a frissítendő post, nem baj ezt loggoljuk és csinálunk egyett
            return this.add(feedPostDTO, multipartFile);
        }
    }

    @Transactional
    @Override
    public boolean delete(long postId) {
        try {
            feedPostRepository.findById(postId).ifPresent(feedPost -> {
                if (feedPost.getImage() != null) {
                    imageContentStore.unsetContent(feedPost.getImage());
                }
                feedPostRepository.delete(feedPost);
            });
            return true;
        } catch (Exception e) {
            return false;
        }
    }


}
