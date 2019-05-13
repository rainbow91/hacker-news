package org.superbiz.moviefun.stories;

import com.fasterxml.jackson.databind.ObjectReader;
import com.fasterxml.jackson.dataformat.csv.CsvMapper;
import com.fasterxml.jackson.dataformat.csv.CsvSchema;
import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.superbiz.moviefun.albums.Album;
import org.superbiz.moviefun.stories.Story;
import org.superbiz.moviefun.stories.StoriesBean;
import org.superbiz.moviefun.blobstore.Blob;
import org.superbiz.moviefun.blobstore.BlobStore;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import static com.fasterxml.jackson.dataformat.csv.CsvSchema.ColumnType.NUMBER;
import static org.superbiz.moviefun.CsvUtils.readFromCsv;

@Service
public class StoriesUpdater {

    private final Logger logger = LoggerFactory.getLogger(getClass());
    private final StoriesBean storiesBean;
    private int rating;

    public StoriesUpdater(StoriesBean storiesBean) {
        this.storiesBean = storiesBean;
    }

    public void update() throws IOException {
        logger.debug("Delete all stories");
        this.storiesBean.deleteAll();
        getStories();
    }

    private void getStories() {
        String idsUrl = "https://hacker-news.firebaseio.com/v0/topstories.json/";
        String storyUrl = "https://hacker-news.firebaseio.com/v0/item/";
        String ending = ".json/";
        rating = 0;
        final Gson gson = new Gson();
        final RestTemplate restTemplate = new RestTemplate();

        String storyIds = new RestTemplate().getForObject(idsUrl, String.class);
        Arrays.asList(Arrays.copyOf(storyIds.substring(1,storyIds.length()-1).split(","), 10))
            .stream().forEach(x ->{
                JsonObject jsonObject = gson.fromJson(restTemplate.getForObject((storyUrl + x + ending), String.class), JsonElement.class)
                    .getAsJsonObject();
                String title = jsonObject.get("title").getAsString();
                String url = jsonObject.get("url").getAsString();
                this.storiesBean.addStory(new Story(title, url, getRating()));
        });
    }

    private int getRating() {
        return ++rating;
    }
}
