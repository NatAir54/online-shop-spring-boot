package com.nataliiakoval.onlineshopspringboot.entity;

import lombok.*;

@Data
@Builder
public class Client {
    private int id;
    private String email;
    private String password;
    private String sole;
}
