package com.example.sourcebase.service;

import com.example.sourcebase.domain.dto.resdto.custom.OverallRatedResDto;

public interface IRatedRankService {
    OverallRatedResDto getOverallRatedOfAUserInAllProject(Long userId);

    OverallRatedResDto getOverallRatedOfAUserByProject(Long toUserId, Long projectId);
}
