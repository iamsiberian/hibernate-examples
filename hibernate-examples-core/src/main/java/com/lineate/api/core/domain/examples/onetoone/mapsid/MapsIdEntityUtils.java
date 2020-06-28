package com.lineate.api.core.domain.examples.onetoone.mapsid;

public class MapsIdEntityUtils {
    public static Post createPost(String text) {
        Post post = new Post();
        post.setText(text);
        return post;
    }

    public static PostDetails createPostDetails(String details, Post post) {
        PostDetails postDetails = new PostDetails();
        postDetails.setDetails(details);
        postDetails.setPost(post);
        return postDetails;
    }
}
