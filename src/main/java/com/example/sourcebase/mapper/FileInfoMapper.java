package com.example.sourcebase.mapper;

import com.example.sourcebase.domain.FileInfo;
import com.example.sourcebase.domain.dto.resdto.FileInfoResDTO;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface FileInfoMapper {
    FileInfoResDTO toResDto(FileInfo fileInfo);
}
