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


    /* Cascading type we can either use CascadeType.All
     or
    @OneToOne(mappedBy = "socialUser", cascade = {CascadeType.Remove, CascadeType.Persist,
    CascadeType.Merge}) whatever we want
    * */
    @OneToOne(mappedBy = "socialUser", cascade = CascadeType.ALL)//we have added this in both the entities to make the relations ship one to one  as well as bidirectional
    private SocialProfile socialProfile;

    @OneToMany(mappedBy = "socialUser",cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    private List<Post> posts=new ArrayList<Post>();


    @ManyToMany()
    //    @ManyToMany(fetch = FetchType.EAGER)//We can use this to change the fetch type
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

    //this custom setter method is crucial for maintaining the consistency in bidirectional relationship between socialUser and socialProfile
    public void setSocialProfile(SocialProfile socialProfile)
    {
        socialProfile.setSocialUser(this);
        this.socialProfile=socialProfile;
    }
}
