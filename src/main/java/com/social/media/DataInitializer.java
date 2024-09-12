package com.social.media;
//	This java class is for Creating a Bean that is executed when spring context is initialized: This will
//initialize data when spring context is initialized

import com.social.media.models.Post;
import com.social.media.models.SocialGroup;
import com.social.media.models.SocialProfile;
import com.social.media.models.SocialUser;
import com.social.media.repositories.PostRepository;
import com.social.media.repositories.SocialGroupRepository;
import com.social.media.repositories.SocialProfileRepository;
import com.social.media.repositories.SocialUserRepository;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@AllArgsConstructor
@Data
@Configuration
public class DataInitializer {

    private final SocialUserRepository socialUserRepository;
    private final SocialGroupRepository socialGroupRepository;
    private final SocialProfileRepository socialProfileRepository;
    private final PostRepository postRepository;

    @Bean
    public CommandLineRunner initializeData(){
        return args -> {
            //Create Some users
            SocialUser socialUser1=new SocialUser();
            SocialUser socialUser2=new SocialUser();
            SocialUser socialUser3=new SocialUser();

            //Save Users to DB
            socialUserRepository.save(socialUser1);
            socialUserRepository.save(socialUser2);
            socialUserRepository.save(socialUser3);

            //Create some groups
            SocialGroup socialGroup1=new SocialGroup();
            SocialGroup socialGroup2=new SocialGroup();


            //Associate groups to users
            socialGroup1.getSocialUsers().add(socialUser1);
            socialGroup1.getSocialUsers().add(socialUser2);

            socialGroup2.getSocialUsers().add(socialUser2);
            socialGroup2.getSocialUsers().add(socialUser3);

            //Save groups to DB
            socialGroupRepository.save(socialGroup1);
            socialGroupRepository.save(socialGroup2);

            //Associate Users to groups
            socialUser1.getSocialGroups().add(socialGroup1);
            socialUser2.getSocialGroups().add(socialGroup1);
            socialUser2.getSocialGroups().add(socialGroup2);
            socialUser3.getSocialGroups().add(socialGroup2);

            //save users back to DB to update association
            socialUserRepository.save(socialUser1);
            socialUserRepository.save(socialUser2);
            socialUserRepository.save(socialUser3);



            //Create some posts
            Post post1=new Post();
            Post post2=new Post();
            Post post3=new Post();

            //Associate Posts with users
            post1.setSocialUser(socialUser1);
            post2.setSocialUser(socialUser2);
            post3.setSocialUser(socialUser3);

            //Saving posts to DB
            postRepository.save(post1);
            postRepository.save(post2);
            postRepository.save(post3);

            //Create SOme Social profile
            SocialProfile socialProfile1=new SocialProfile();
            SocialProfile socialProfile2=new SocialProfile();
            SocialProfile socialProfile3=new SocialProfile();

            //Associate profile with users
            socialProfile1.setSocialUser(socialUser1);
            socialProfile2.setSocialUser(socialUser2);
            socialProfile3.setSocialUser(socialUser3);

            //Saving social profile to DB
            socialProfileRepository.save(socialProfile1);
            socialProfileRepository.save(socialProfile2);
            socialProfileRepository.save(socialProfile3);

        };
    }

}
