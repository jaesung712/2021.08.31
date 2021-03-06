package com.koreait.day3.controller.api;

import com.koreait.day3.controller.CrudController;
import com.koreait.day3.ifs.CrudInterface;
import com.koreait.day3.model.entity.Users;
import com.koreait.day3.model.network.Header;
import com.koreait.day3.model.network.request.UserApiRequest;
import com.koreait.day3.model.network.response.UserApiResponse;
import com.koreait.day3.model.network.response.UserOrderInfoApiResponse;
import com.koreait.day3.service.UserApiLogicService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.*;


import java.util.List;

@RestController
@RequestMapping("/api/user")
@RequiredArgsConstructor
public class UserApiController extends CrudController<UserApiRequest ,UserApiResponse , Users> {

    private final UserApiLogicService userApiLogicService;

    /*
        {
            "transaction_time":"2021-08-23",
            "resultCode":"OK",
            "description":"OK",
            "data":{
                "userid":"cherry",
                "userpw":"1234",
                "email":"cherry@cherry.com",
                "hp":"010-1234-1234"
            }
        }
     */
    @Override
    @PostMapping("")    // http://127.0.0.1:9090/api/user (post)
    public Header<UserApiResponse> create(@RequestBody Header<UserApiRequest> request) {
        System.out.println(request);
        return userApiLogicService.create(request);
    }


    @Override
    @GetMapping("{id}") // /api/user/{id} (get) // http://127.0.0.1:9090/api/user/42
    public Header<UserApiResponse> read(@PathVariable(name="id") Long id) {
        return userApiLogicService.read(id);
    }

    /*
         {
            "transaction_time":"2021-08-23",
            "resultCode":"OK",
            "description":"OK",
            "data":{
                "id":42,
                "userid":"cherry",
                "userpw":"1010",
                "email":"cherry@naver.com",
                "hp":"010-0000-0000"
            }
        }
     */
    @Override
    @PutMapping("") // /api/user (put)
    public Header<UserApiResponse> update(@RequestBody Header<UserApiRequest> request) {
        return userApiLogicService.update(request);
    }

    @Override
    @DeleteMapping("{id}") // /api/user/{id} // http://127.0.0.1:9090/api/user/42
    public Header<UserApiResponse> delete(@PathVariable(name="id") Long id) {
        return userApiLogicService.delete(id);
    }


//    @GetMapping("/{id}/orderInfo") //
//    public Header<UserOrderInfoApiResponse> orderInfo(@PathVariable Long id){
//        return userApiLogicService.orderInfo(id);
//    }

    @GetMapping("")  //  /api/user
    public Header<List<UserApiResponse>> findAll(@PageableDefault(sort={"id"} , direction= Sort.Direction.DESC) Pageable pageable){
        return userApiLogicService.search(pageable);
    }

}
