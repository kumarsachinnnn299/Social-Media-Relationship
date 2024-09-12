package com.social.media.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
public class SocialGroup {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @JsonIgnore
    @ManyToMany(mappedBy = "socialGroups")
    private Set<SocialUser> socialUsers=new HashSet<SocialUser>();

    //This is just to give our own implementation of calculating hash. If we don't do this then it will give
    //Stack overflow error
    @Override
    public int hashCode()
    {
        return Objects.hash(id);
    }
}
