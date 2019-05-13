package org.superbiz.moviefun;

import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.services.s3.AmazonS3Client;
import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;
import org.superbiz.moviefun.blobstore.BlobStore;
import org.superbiz.moviefun.blobstore.S3Store;
import org.superbiz.moviefun.blobstore.ServiceCredentials;
import org.superbiz.moviefun.stories.Story;

import java.util.Arrays;

@SpringBootApplication
public class Application {
//
//    private int rating;
//
//    private void test() {
//        String idsUrl = "https://hacker-news.firebaseio.com/v0/topstories.json/";
//        String storyUrl = "https://hacker-news.firebaseio.com/v0/item/";
//        String ending = ".json/";
//        rating = 0;
//
//        String storyIds = new RestTemplate().getForObject(idsUrl, String.class);
//        Arrays.asList(Arrays.copyOf(storyIds.substring(1,storyIds.length()-1).split(","), 10)
//        ).stream().forEach(x ->{
//            String storyObject = new RestTemplate().getForObject((storyUrl + x + ending), String.class);
//            JsonObject jsonObject = new Gson().fromJson (storyObject, JsonElement.class).getAsJsonObject();
//            String title = jsonObject.get("title").getAsString();
//            String url = jsonObject.get("url").getAsString();
//            Story s = new Story(title, url, getRating());
// //           this.storiesBean.addStory(new Story(title, url, getRating()));
//        });
//    }

//    private int getRating() {
//        return ++rating;
//    }
    public static void main(String[] args) {
  //      new Application().test();
        SpringApplication.run(Application.class, args);
    }

    @Bean
    public ServletRegistrationBean actionServletRegistration(ActionServlet actionServlet) {
        return new ServletRegistrationBean(actionServlet, "/moviefun/*");
    }

    @Bean
    ServiceCredentials serviceCredentials(@Value("${vcap.services}") String vcapServices) {
        return new ServiceCredentials(vcapServices);
    }

    @Bean
    public BlobStore blobStore(
        ServiceCredentials serviceCredentials,
        @Value("${vcap.services.photo-storage.credentials.endpoint:#{null}}") String endpoint
    ) {
        String photoStorageAccessKeyId = serviceCredentials.getCredential("photo-storage", "user-provided", "access_key_id");
        String photoStorageSecretKey = serviceCredentials.getCredential("photo-storage", "user-provided", "secret_access_key");
        String photoStorageBucket = serviceCredentials.getCredential("photo-storage", "user-provided", "bucket");

        AWSCredentials credentials = new BasicAWSCredentials(photoStorageAccessKeyId, photoStorageSecretKey);
        AmazonS3Client s3Client = new AmazonS3Client(credentials);

        if (endpoint != null) {
            s3Client.setEndpoint(endpoint);
        }

        return new S3Store(s3Client, photoStorageBucket);
    }
}
