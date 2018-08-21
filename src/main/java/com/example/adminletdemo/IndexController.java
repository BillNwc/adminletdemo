package com.example.adminletdemo;

import com.alibaba.fastjson.JSONObject;
import com.example.adminletdemo.page.PageInfo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 这里需要写注释
 */

@RestController
@RequestMapping("/index/")
@Slf4j
public class IndexController {

    @RequestMapping("find")
    public PageInfo<DemoEntity> findData(@RequestBody JSONObject data){
        log.info("find data's "+data);
        List<DemoEntity> list = new ArrayList<>(10);
        for (int i=0;i<5;i++){
            DemoEntity demo = new DemoEntity();
            demo.setId((long)i);
            demo.setFirstname("firstname"+i);
            demo.setLastname("lname"+i);
            demo.setEmail("email1@cc.com");
            demo.setPhone("phone"+i);
            list.add(demo);
        }
        return new PageInfo<>(list);
    }

    @PostMapping
    public Map save(DemoEntity data){
        log.info("save data's "+data);
        Map m = new HashMap();
        m.put("success",true);
        return m;
    }
    @GetMapping(path = "/{id:.+}")
    public DemoEntity queryOne(String id){
        log.info("query id's "+id);
        DemoEntity demo = new DemoEntity();
        demo.setId(33L);
        demo.setFirstname("firstnama"+33);
        demo.setFirstname("firstname"+33);
        demo.setLastname("lname"+33);
        demo.setEmail("email1@cc.com");
        demo.setPhone("phone"+33);
        return demo;
    }
    @DeleteMapping(path = "/{id:.+}")
    public Map delete(@PathVariable Long id){
        log.info("del id's "+id);
        Map m = new HashMap();
        m.put("success",true);
        return m;
    }
}
