package com.bombaycomputers.dto.user;

import java.time.LocalDateTime;

public class UserRead {
    public long id;
    public String name;
    public String email;
    public String phone;
    public String address;
    public String role;
    public boolean isAdmin;
    public LocalDateTime createdAt;  

}