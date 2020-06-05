package com.lineate.api.core.domain.examples.onetoone.foreigngenerator;

public class ForeignKeyEntityUtils {
    public static Post createPost(String text) {
        Post post = new Post();
        post.setText(text);
        return post;
    }

    public static PostDetails createPostDetails(String details) {
        PostDetails postDetails = new PostDetails();
        postDetails.setDetails(details);
        return postDetails;
    }
}
