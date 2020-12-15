package oit.is.chat4.lec06team4.controller;

import java.security.Principal;
import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import oit.is.chat4.lec06team4.model.Room1;
import oit.is.chat4.lec06team4.model.Room1Mapper;
import oit.is.chat4.lec06team4.model.Room2;
import oit.is.chat4.lec06team4.model.Room2Mapper;

@Controller
public class RoomController {

  @Autowired
  Room1Mapper Room1Mapper;
  @Autowired
  Room2Mapper Room2Mapper;

  /**
   * @param model
   * @param prin
   * @param return
   */
  @GetMapping("/Room1")
  public String Room1Entry(ModelMap model, Principal prin) {
    String loginUser = prin.getName(); // ログインユーザ情報
    model.addAttribute("login_user", loginUser);
    ArrayList<Room1> room1 = Room1Mapper.selectAllRoom1();
    model.addAttribute("Room1", room1);
    return "Room1.html";
  }

  /**
  * @param chat
  * @param model
  * @param prin
  * @param return
  */
  @PostMapping("/Room1")
  @Transactional
  public String room1(@RequestParam String chat, ModelMap model, Principal prin) {
    String loginUser = prin.getName();
    Room1 Room1 = new Room1();
    Room1.setUser(loginUser);
    Room1.setChatlog(chat);
    Room1Mapper.insertRoom1(Room1);
    model.addAttribute("log", chat);  //送信されたテキストボックスの内容を取得
    ArrayList<Room1> room1 = Room1Mapper.selectAllRoom1();
    model.addAttribute("Room1", room1);
    return "Room1.html";
  }

  /**
   * @param model
   * @param prin
   * @param return
   */
  @GetMapping("/Room2")
  public String Room2Entry(ModelMap model, Principal prin) {
    String loginUser = prin.getName(); // ログインユーザ情報
    model.addAttribute("login_user", loginUser);
    ArrayList<Room2> room2 = Room2Mapper.selectAllRoom2();
    model.addAttribute("Room2", room2);
    return "Room2.html";
  }

  /**
  * @param chat
  * @param model
  * @param prin
  * @param return
  */
  @PostMapping("/Room2")
  @Transactional
  public String room2(@RequestParam String chat, ModelMap model, Principal prin) {
    String loginUser = prin.getName();
    Room2 Room2 = new Room2();
    Room2.setUser(loginUser);
    Room2.setChatlog(chat);
    Room2Mapper.insertRoom2(Room2);
    model.addAttribute("log", chat);
    ArrayList<Room2> room2 = Room2Mapper.selectAllRoom2();
    model.addAttribute("Room2", room2);
    return "Room2.html";
  }

}
