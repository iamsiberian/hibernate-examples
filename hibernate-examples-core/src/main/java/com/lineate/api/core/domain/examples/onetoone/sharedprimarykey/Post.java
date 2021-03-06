package com.lineate.api.core.domain.examples.onetoone.sharedprimarykey;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

@Entity
@Table(name = "shpk_posts")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Post {
    @Id
    private Long id;

    private String text;

    @OneToOne(
        fetch = FetchType.LAZY,
        optional = false
    )
    @PrimaryKeyJoinColumn
    private PostDetails postDetails;
}
