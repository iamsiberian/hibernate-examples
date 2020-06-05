package com.lineate.api.core.domain.onetoone.jointable;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "posts")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Post {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String text;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinTable(
        name = "post_details_post",
        joinColumns =
            @JoinColumn(name = "post_details_id",
                nullable = false,
                unique = true),
        inverseJoinColumns =
            @JoinColumn(name = "post_id")
    )
    private PostDetails postDetails;
}
