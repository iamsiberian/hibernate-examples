package com.lineate.api.core.domain.onetoone.mapsid;

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
@Table(name = "user_profile")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UserProfile {
    @Id
    @Column(name = "id")
    private Long id;

    @Column(name = "profile_info")
    private String profileInfo;

    @OneToOne
    @MapsId
    private User user;
}
