package com.ims.claim.service.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class User {

	private String userId;
    private String firstname;
    private String lastname;
    private String email;
    private String password;
    private String address;
    private String marital_status;
    private String phone_number;
    
}
