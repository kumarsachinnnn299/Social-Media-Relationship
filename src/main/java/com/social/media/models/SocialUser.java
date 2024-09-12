package com.social.media.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.*;

//here this class is the owner of the relationship between SocialProfile and SocialUser since the
//Social user is mapped by the attribute of this class i.e socialProfile
@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
public class SocialUser {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    @OneToOne//we have added this in both the entities to make the relations ship one to one  as well as bidirectional
    @JoinColumn(name = "social_profile_id")//by this annotation we define the name of the foreign key
    private SocialProfile socialProfile;

    @OneToMany(mappedBy = "socialUser")
    private List<Post> posts=new ArrayList<Post>();

    @ManyToMany
    @JoinTable(
            name = "user_group",
            joinColumns = @JoinColumn(name="user_id"),
            inverseJoinColumns = @JoinColumn(name = "group_id")
    )
    private Set<SocialGroup> socialGroups =new HashSet<SocialGroup>();

    //This is just to give our own implementation of calculating hash. If we don't do this then it will give
    //Stack overflow error
    @Override
    public int hashCode()
    {
        return Objects.hash(id);
    }
}
