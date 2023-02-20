package com.example.testproject.controller;

import com.example.testproject.data.dto.MemberDTO;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/v1/get-api")
public class GetController {

    @RequestMapping(value="/hello", method = RequestMethod.GET)
    public String getHello(){
        return "Hello world";
    }

    @RequestMapping(value="/name")
    public String getName(){
        System.out.println("getName 실행");
        return "Flature";
    }
    @RequestMapping(value="/variable1/{variable}")
    public String getVar(@PathVariable String variable){
        return variable;
    }
    @RequestMapping(value="/variable2/{variable}")
    public String getVar2(@PathVariable("variable") String var){
        return var;
    }
    @RequestMapping(value="/request1")
    public String getRequestParam1(
            @RequestParam String name,
            @RequestParam String email,
            @RequestParam String organization){

        return name+"  " + email + "  " + organization;
    }
    @RequestMapping(value="/request2")
    public String getRequestParam2(@RequestParam Map<String,String> param){
        StringBuilder sb = new StringBuilder();

        param.entrySet().forEach(map -> {
            sb.append(map.getKey() + " : " + map.getValue() + "\n");
        });
        return sb.toString();
    }
    @RequestMapping(value="/request3")
    public String getRequestParam2(MemberDTO memberDto){

        return memberDto.toString();
    }
}
