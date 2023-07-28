package ks.msx.SpringSecurity.service;

import ks.msx.SpringSecurity.entity.Post;
import ks.msx.SpringSecurity.repository.PostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;


@Service
@RequiredArgsConstructor
public class PostService{
    private final PostRepository postRepository;
    private final UserService userService;
    private Post post;
    public List<Post> getAllPosts(){
        return postRepository.findAll().stream().filter(Post::isPost_non_locked).toList();
    }

    public ResponseEntity<Optional<Post>> getPostById(Long post_id){
        if(postRepository.existsById(post_id)){
            postRepository.findById(post_id);
            return new ResponseEntity<>(postRepository.findById(post_id), HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }


    public void createPost(String postTitle, String postText, String username){
        Post post = new Post();
        post.setPost_author(userService.returnCurentUser(username).getId());
        post.setPost_title(postTitle);
        post.setPost(postText);
        post.setPost_date(new Date());
        post.setPost_non_locked(true);
        new ResponseEntity<>(postRepository.save(post), HttpStatus.CREATED);
    }
    
    public void deletePost(Long post_id, String username){
        if (postRepository.existsById(post_id) && getUserEqualsWithPostAuthor(post_id, username)) {
            postRepository.deleteById(post_id);
            new ResponseEntity<>(HttpStatus.OK);
            return;
        }
        new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    public void updatePost(Long post_id, String username, String post_title, String post_text){
        if (getUserEqualsWithPostAuthor(post_id, username)){
            Post post = new Post();
            post.setId(post_id);
            post.setPost_author(userService.returnCurentUser(username).getId());
            post.setPost_title(post_title);
            post.setPost(post_text);
            post.setPost_non_locked(true);
            post.setPost_date(new Date());
            postRepository.save(post);
        }
    }
    public boolean getUserEqualsWithPostAuthor(Long post_id, String username){
        Long user = userService.returnCurentUser(username).getId();
        Long postAuthor = postRepository.findById(post_id).get().getPost_author();
        return user.equals(postAuthor);
    }

    public void setPostBlocked(Long post_id){
        Post post = getPostById(post_id).getBody().get();
        post.setPost_non_locked(false);
        postRepository.save(post);
    }
}
