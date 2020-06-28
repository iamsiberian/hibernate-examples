package com.lineate.api.core.domain.examples.onetoone.mapsid;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.MapsId;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "post_details")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class PostDetails {
    @Id
    @Column(name = "id")
    private Long id;

    private String details;

    @OneToOne
    @MapsId
    private Post post;
}
