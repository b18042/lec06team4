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

import oit.is.chat4.lec06team4.model.Room2;
import oit.is.chat4.lec06team4.model.Room2Mapper;

@Service
public class AsyncChatlog2 {
  boolean dbUpdated = false;

  private final Logger logger = LoggerFactory.getLogger(AsyncChatlog2.class);

  @Autowired
  Room2Mapper Room2Mapper;

  public ArrayList<Room2> syncShowRoom2() {
    return Room2Mapper.selectAllRoom2();
  }

  /**
   * @param room2
   * @return
   */
  @Transactional
  public Room2 syncInsertRoom2(Room2 room2) {
    Room2Mapper.insertRoom2(room2);
    this.dbUpdated = true;
    return room2;
  }

  /**
   * @param emitter
   */
  @Async
  public void asyncShowRoom2(SseEmitter emitter) {
    try {
      while (true) {
        TimeUnit.SECONDS.sleep(5);
        if (false == dbUpdated) {
          continue;
        }
        ArrayList<Room2> room2 = this.syncShowRoom2();
        emitter.send(room2);
        dbUpdated = false;
      }
    } catch (Exception e) {
      // 例外の名前とメッセージだけ表示する
      logger.warn("Exception:" + e.getClass().getName() + ":" + e.getMessage());
    } finally {
      emitter.complete();
    }
    System.out.println("asyncShowRoom2 complete");
  }

}
