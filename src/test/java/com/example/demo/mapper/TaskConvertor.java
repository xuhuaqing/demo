package com.example.demo.mapper;

import com.example.demo.entity.UserDo;
import com.example.demo.entity.UserPo;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface TaskConvertor{
    TaskConvertor INSTANCE = Mappers.getMapper( TaskConvertor.class );


      @Mapping(source = "numberOfSeats",target = "seatCount")
      UserDo po2do(UserPo userPo);

}
