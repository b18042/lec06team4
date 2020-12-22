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
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

import oit.is.chat4.lec06team4.model.Room1;
import oit.is.chat4.lec06team4.model.Room1Mapper;
import oit.is.chat4.lec06team4.model.Room2;
import oit.is.chat4.lec06team4.model.Room2Mapper;
import oit.is.chat4.lec06team4.service.AsyncChatlog1;

@Controller
public class RoomController {

  @Autowired
  Room1Mapper Room1Mapper;
  @Autowired
  Room2Mapper Room2Mapper;
  @Autowired
  AsyncChatlog1 syncChatlog;

  /**
   * @param model
   * @param prin
   * @return
   */
  @GetMapping("/Room1")
  public String Room1Entry(ModelMap model, Principal prin) {
    String loginUser = prin.getName(); // ログインユーザ情報
      model.addAttribute("login_user", loginUser);
    ArrayList<Room1> room1 = syncChatlog.syncShowRoom1();
      model.addAttribute("Room1", room1);
    return "Room1.html";
  }

  /**
   * @param chat
   * @param model
   * @param prin
   * @return
   */
  @PostMapping("/Room1")
  @Transactional
  public String room1(@RequestParam String chat, ModelMap model, Principal prin) {
    Room1 room1 = new Room1();
     String loginUser = prin.getName();
     room1.setUser(loginUser);
     room1.setChatlog(chat);
    Room1 gabage = this.syncChatlog.syncInsertRoom1(room1);
     model.addAttribute("gabage", gabage);
    ArrayList<Room1> a_room1 = syncChatlog.syncShowRoom1();
      model.addAttribute("Room1", a_room1);
    return "Room1.html";
  }

    /**
   * @return
   */
  @GetMapping("/step1")
  public SseEmitter Room1Step1() {
    final SseEmitter sseEmitter = new SseEmitter();
    this.syncChatlog.asyncShowRoom1(sseEmitter);
    return sseEmitter;
  }

  /**
   * @param model
   * @param prin
   * @return
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
  * @return
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
