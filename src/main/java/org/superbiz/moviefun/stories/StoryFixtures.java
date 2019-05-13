package org.superbiz.moviefun.stories;

import org.springframework.stereotype.Component;
import org.superbiz.moviefun.albums.Album;

import java.util.ArrayList;
import java.util.List;

import static java.util.Arrays.asList;

@Component
public class StoryFixtures {
    public List<Story> load() {
        int id = 1;
        List<Story> stories = new ArrayList<Story>();
        while (id < 11) {
            stories.add(new Story(id,"Story " + id, "url " + id, id++));
        }
        return stories;
    }
}
