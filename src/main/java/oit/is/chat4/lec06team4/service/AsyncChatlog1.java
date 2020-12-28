package oit.is.chat4.lec06team4.service;

import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

import oit.is.chat4.lec06team4.model.Room1;
import oit.is.chat4.lec06team4.model.Room1Mapper;

@Service
public class AsyncChatlog1 {
  boolean dbUpdated = false;

  private final Logger logger = LoggerFactory.getLogger(AsyncChatlog1.class);

  @Autowired
  Room1Mapper Room1Mapper;

  public ArrayList<Room1> syncShowRoom1() {
    return Room1Mapper.selectAllRoom1();
  }

  /**
   * @param room1
   * @return
   */
  @Transactional
  public Room1 syncInsertRoom1(Room1 room1) {
    Room1Mapper.insertRoom1(room1);
    this.dbUpdated = true;
    return room1;
  }

  /**
   * @param emitter
   */
  @Async
  public void asyncShowRoom1(SseEmitter emitter) {
    try {
      while (true) {
        TimeUnit.SECONDS.sleep(5);
        if (false == dbUpdated) {
          continue;
        }
        ArrayList<Room1> room1 = this.syncShowRoom1();
        emitter.send(room1);
        dbUpdated = false;
      }
    } catch (Exception e) {
      // 例外の名前とメッセージだけ表示する
      logger.warn("Exception:" + e.getClass().getName() + ":" + e.getMessage());
    } finally {
      emitter.complete();
    }
    System.out.println("asyncShowRoom1 complete");
  }

}
