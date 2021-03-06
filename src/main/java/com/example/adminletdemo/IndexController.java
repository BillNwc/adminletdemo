package com.example.adminletdemo;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.example.adminletdemo.page.PageInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 这里需要写注释
 */

@RestController
@RequestMapping("/index/")
// @Slf4j
public class IndexController {
	private Logger log = LoggerFactory.getLogger(getClass());

	@RequestMapping("find")
	public PageInfo<DemoEntity> findData(@RequestBody JSONObject data) {
		log.info("find data's " + data);
		List<DemoEntity> list = new ArrayList<>(10);
		for (int i = 0; i < 5; i++) {
			DemoEntity demo = new DemoEntity();
			demo.setId((long) i);
			demo.setLoginId("099" + i);
			demo.setUserName("张" + (i + 1));
			demo.setCompanyName("上海润迅");
			demo.setDeptName("研发部");
			demo.setSex("女");
			demo.setIsAdmin("是");
			demo.setIsStart("是");
			demo.setUpdateTime(new Date());
			demo.setPhoneNumber("17718181919");
			list.add(demo);
		}
		return new PageInfo<>(list);
	}

	@RequestMapping("findLog")
	public PageInfo<LogEntity> findLogData(@RequestBody JSONObject data) {
		log.info("find data's " + data);
		List<LogEntity> list = new ArrayList<>(10);
		for (int i = 0; i < 5; i++) {
			LogEntity demo = new LogEntity();
			demo.setLogid(i);
			demo.setLoginId("099" + i);
			demo.setUserName("张" + (i + 1));
			demo.setDeptName("研发部");
			demo.setLoginIp("192.168.0.1");
			demo.setUrl("http://localhost:8080/log.html");
			demo.setMenuName("大屏显示");
			demo.setUpdateTime(new Date());
			list.add(demo);
		}
		return new PageInfo<>(list);
	}
	@RequestMapping("findRole")
	public PageInfo<RoleEntity> findRoleData(@RequestBody JSONObject data) {
		log.info("find data's " + data);
		List<RoleEntity> list = new ArrayList<>(10);
		for (int i = 0; i < 5; i++) {
			RoleEntity demo = new RoleEntity();
			demo.setRoleId(i);
			demo.setRoleName("张" + (i + 1));
			demo.setRemark("研发部人的角色信息");
			demo.setUpdateName("张三");
			demo.setUpdateTime(new Date());
			list.add(demo);
		}
		return new PageInfo<>(list);
	}

	@PostMapping
	public Map save(DemoEntity data) {
		log.info("save data's " + data);
		Map m = new HashMap();
//		m.put("success", false);
//		m.put("message", "错了哦");
		m.put("success", true);
		return m;
	}

	@GetMapping(path = "/{id:.+}")
	public DemoEntity queryOne(String id) {
		log.info("query id's " + id);
		DemoEntity demo = new DemoEntity();
		demo.setId(33l);
		demo.setLoginId("091");
		demo.setUserName("张三");
		demo.setCompanyName("上海润迅");
		demo.setDeptName("研发部");
		demo.setSex("女");
		demo.setIsAdmin("否");
		demo.setIsStart("否");
		demo.setPhoneNumber("17718181919");
		demo.setUpdateTime(new Date());
		return demo;
	}

	@DeleteMapping(path = "/{id:.+}")
	public Map delete(@PathVariable Long id) {
		log.info("del id's " + id);
		Map m = new HashMap();
		m.put("success", true);
		return m;
	}

	@RequestMapping(path = "/treeData")
	public JSONArray treeData() {
		//mock data
		JSONArray datas = new JSONArray();
		JSONObject root = new JSONObject();
		root.put("id",1);
		root.put("text","根节点1");
		JSONObject rootState = new JSONObject();
		rootState.put("opened",true);
		root.put("state",rootState);
		JSONArray childrens = new JSONArray();
		for(int i =0;i<20;i++){
			JSONObject children1 = new JSONObject();
			children1.put("id",2+""+i);
			children1.put("text","根节点-sub1");
			JSONObject children1State = new JSONObject();
			children1State.put("selected",true);
			children1.put("state",children1State);
			JSONArray childrens1 = new JSONArray();
			JSONObject children2 = new JSONObject();
			children2.put("id",3+""+i);
			children2.put("text","根节点-sub1-sub1");
			childrens1.add(children2);
			children1.put("children",childrens1);
			childrens.add(children1);
		}
		root.put("children",childrens);
		datas.add(root);
		log.info(datas.toJSONString());
		return datas;
	}
}
