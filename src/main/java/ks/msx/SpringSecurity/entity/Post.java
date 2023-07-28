package ks.msx.SpringSecurity.entity;

import jakarta.persistence.*;
import ks.msx.SpringSecurity.service.UserService;
import lombok.Data;

import java.util.Date;
import java.util.Optional;

@Entity
@Table(name = "posts_database")
@Data
public class Post {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "post_title")
    private String post_title;

    @Column(name = "post_author")
    private Long post_author;

    @Column(name = "post_date")
    private Date post_date;

    @Column(name = "post_non_locked")
    private boolean post_non_locked;

    @Column(name = "post")
    private String post;
}
