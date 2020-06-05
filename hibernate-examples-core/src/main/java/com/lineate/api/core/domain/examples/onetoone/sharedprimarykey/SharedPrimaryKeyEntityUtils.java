package com.lineate.api.core.domain.examples.onetoone.sharedprimarykey;

public class SharedPrimaryKeyEntityUtils {
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
