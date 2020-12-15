package oit.is.chat4.lec06team4.model;

import java.util.ArrayList;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

@Mapper
public interface Room1Mapper {

  //全試合結果を取得
  @Select("SELECT * from Room1")
  ArrayList<Room1> selectAllRoom1();

  /**
   * @param room1
   */
  @Insert("INSERT INTO Room1 (user,chatlog) VALUES (#{user},#{chatlog})")
  @Options(useGeneratedKeys = true, keyColumn = "id", keyProperty = "id")
  void insertRoom1(Room1 room1);
}
