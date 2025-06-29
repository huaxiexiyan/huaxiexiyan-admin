package com.huaxiexiyan.erp.presentation;


import com.huaxiexiyan.api.common.api.ApiResponse;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author xiyan
 * @date 2024/1/8 15:35
 */
@AllArgsConstructor
@Slf4j
@RequestMapping("/menus")
@RestController
public class TestResource {

    @GetMapping("/tree")
    public ApiResponse<Void> test1() {
        return ApiResponse.ok();
    }


}

