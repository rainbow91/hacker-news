package org.superbiz.moviefun.stories;
import org.apache.tika.io.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.superbiz.moviefun.blobstore.Blob;
import org.superbiz.moviefun.blobstore.BlobStore;

import java.io.IOException;
import java.io.InputStream;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import static java.lang.String.format;
import static org.springframework.http.MediaType.IMAGE_JPEG_VALUE;

@Controller
@RequestMapping("/stories")
public class StoriesController {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());
    private final StoriesBean storiesBean;

    public StoriesController(StoriesBean storiesBean, BlobStore blobStore) {
        this.storiesBean = storiesBean;
    }

    @GetMapping
    public String index(Map<String, Object> model) {
        model.put("stories", storiesBean.getStories());
        return "stories";
    }

    @GetMapping("/{storyId}")
    public String get(@PathVariable long storyId, Map<String, Object> model) {
        List<Story> stories = storiesBean.getStories();
        List<Story> storyList =  stories.stream().filter(x -> x.getId() == storyId).collect(Collectors.toList());
        model.put("story", storyList.get(0));
        model.put("stories", stories);
        return "stories";
    }

    @GetMapping("/{storyId}/{title}")
    public String update(@PathVariable long storyId, @PathVariable String title, Map<String, Object> model) {
        Story story =  storiesBean.find(storyId);
        story.setTitle(title);
        storiesBean.updateStory(story);
        model.put("stories", storiesBean.getStories());
        return "stories";
    }

}
