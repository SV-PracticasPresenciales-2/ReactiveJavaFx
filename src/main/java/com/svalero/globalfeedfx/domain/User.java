package com.svalero.globalfeedfx.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class User {
    public int id;
    public String username;
    public String password;
    public String name;
    public String biography;
    public String registerDate;
    public int phoneNumber;
}
