package com.mkr.backend.mapper;

import com.mkr.backend.dto.ReportDto;
import com.mkr.backend.entity.Report;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ReportMapper {

    ReportDto map(Report report);

    Report map(ReportDto report);

}
