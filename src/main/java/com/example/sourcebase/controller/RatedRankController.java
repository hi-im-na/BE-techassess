package com.example.sourcebase.controller;

import com.example.sourcebase.service.IRatedRankService;
import com.example.sourcebase.util.ResponseData;
import com.example.sourcebase.util.SuccessCode;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/rated-rank")
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@CrossOrigin(origins = {"http://192.168.0.152:5000", "http://192.168.0.152:6123", "http://localhost:5000", "http://localhost:6123"})

public class RatedRankController {
    IRatedRankService ratedRankService;

    @GetMapping("/overall-rated/{userId}")
    public ResponseEntity<ResponseData<?>> getOverallRatedOfAUser(@PathVariable Long userId) {
        return ResponseEntity.ok(
                ResponseData.builder()
                        .code(SuccessCode.GET_SUCCESSFUL.getCode())
                        .message(SuccessCode.GET_SUCCESSFUL.getMessage())
                        .data(ratedRankService.getOverallRatedOfAUser(userId))
                        .build());
    }

}
