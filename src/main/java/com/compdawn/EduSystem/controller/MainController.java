/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.compdawn.EduSystem.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 *
 * @author subhash
 */
@Controller
public class MainController {
    @RequestMapping("/")
    public String main(){
        return("index");
    }
    
    @RequestMapping("/hello")
    public String hello(){
        return("hello");
    }
}
