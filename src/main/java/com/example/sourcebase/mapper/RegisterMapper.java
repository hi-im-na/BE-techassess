package com.example.sourcebase.mapper;

import com.example.sourcebase.domain.dto.reqdto.user.RegisterReqDTO;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface RegisterMapper {
    RegisterReqDTO readValue(String value, Class<RegisterReqDTO> type);
}
