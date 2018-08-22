package com.example.adminletdemo;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSONObject;
import com.example.adminletdemo.page.PageInfo;

/**
 * 这里需要写注释
 */

@RestController
@RequestMapping("/index/")
//@Slf4j
public class IndexController {
	private Logger log = LoggerFactory.getLogger(getClass());
	
    @RequestMapping("find")
    public PageInfo<DemoEntity> findData(@RequestBody JSONObject data){
        log.info("find data's "+data);
        List<DemoEntity> list = new ArrayList<>(10);
        for (int i=0;i<5;i++){
            DemoEntity demo = new DemoEntity();
            demo.setId((long)i);
            demo.setLoginId("099"+i);
            demo.setUserName("张"+i);
            demo.setDeptName("研发部");
            demo.setSex("女");
            demo.setIsAdmin("是");
            demo.setIsStart("是");
            demo.setUpdateTime(new Date());
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
        demo.setId(33l);
        demo.setLoginId("091");
        demo.setUserName("张三");
        demo.setDeptName("研发部");
        demo.setSex("女");
        demo.setIsAdmin("否");
        demo.setIsStart("否");
        demo.setUpdateTime(new Date());
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
