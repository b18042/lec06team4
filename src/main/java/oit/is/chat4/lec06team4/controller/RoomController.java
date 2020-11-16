package oit.is.chat4.lec06team4.controller;

import org.springframework.stereotype.Controller;

import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;


@Controller
public class RoomController {
  @GetMapping("/Room1")
  public String Room1Entry() {

    //model.addAttribute("User1", User1);
    // model.addAttribute("User2", User2);
    //model.addAttribute("Match1", Match1);

    return "Room1.html";
  }

  @GetMapping("/Room2")
  public String Room2Entry() {

    // model.addAttribute("User1", User1);
    // model.addAttribute("User2", User2);
    // model.addAttribute("Match1", Match1);

    return "Room2.html";
  }
}
