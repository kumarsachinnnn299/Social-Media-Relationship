package com.social.media.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
public class SocialProfile {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String description;
    @JsonIgnore
    @OneToOne()
    @JoinColumn(name = "Social_User_ID")//by this annotation we define the name of the foreign key
    private SocialUser socialUser;

    //custom setter method is curcial to set the bidirectional relationship between socialUser and socialProfile
    public void setSocialUser(SocialUser socialUser)
    {

            this.socialUser=socialUser;
            if(socialUser.getSocialProfile()!=this)
            {
                socialUser.setSocialProfile(this);
            }
    }
}
