package oit.is.chat4.lec06team4.model;

import java.util.ArrayList;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

@Mapper
public interface Room2Mapper {

  //全試合結果を取得
  @Select("SELECT * from Room2")
  ArrayList<Room2> selectAllRoom2();

}
