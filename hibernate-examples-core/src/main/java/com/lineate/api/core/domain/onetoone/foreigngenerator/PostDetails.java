package com.lineate.api.core.domain.onetoone.foreigngenerator;

import com.sun.istack.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

@Entity
@Table(name = "post_details")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class PostDetails {
    @Id
    @GeneratedValue(generator = "postKeyGenerator")
    @org.hibernate.annotations.GenericGenerator(
        name = "postKeyGenerator",
        strategy = "foreign",
        parameters =
            @org.hibernate.annotations.Parameter(
                name = "property", value = "post"
            )
    )
    private Long id;

    @NotNull
    private String details;

    @OneToOne(optional = false)
    @PrimaryKeyJoinColumn
    private Post post;
}
