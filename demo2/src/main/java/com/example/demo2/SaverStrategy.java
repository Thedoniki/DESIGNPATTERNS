package com.example.demo2;


import com.example.demo2.Models.User;

import java.util.ArrayList;
import java.util.List;

public interface SaverStrategy  {
    public double save(User user);
}
