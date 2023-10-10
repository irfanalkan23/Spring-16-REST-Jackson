package com.cydeo.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;



@Entity
@Data
@NoArgsConstructor
@Table(name = "user_account")
@JsonIgnoreProperties(value = {"hibernateLazyInitializer"},ignoreUnknown = true)
    //*put the Json annotations in DTO. not Entity. this is just as an example.
    //ignore the field created because of (fetch = FetchType.LAZY)
    //ignoreUnknown = true --> used against attacks
public class User extends BaseEntity {

    //    @JsonIgnore
    private String email;


    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    //do I want to see password in the output?
    //execute Setter only, not Getter! show it to me when I'm Post'ing (PostMapping), don't show me when I'm Get'ing
    private String password;


    private String username;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "account_details_id")
    @JsonManagedReference //is the forward part of reference - the one that gets serialized normally
    //we will see Account information (json object) in User json object
    private Account account;

}