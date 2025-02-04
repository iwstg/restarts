package com.example.demo.data.dto;

import lombok.*;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserProfilDTO {
    private String userID;
    private String userName;
    private String userEmail;
    private String userIntroduce;

}
