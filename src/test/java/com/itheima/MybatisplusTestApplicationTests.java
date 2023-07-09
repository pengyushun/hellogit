package com.itheima;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.itheima.dao.UserDao;
import com.itheima.domain.User;
import com.itheima.domain.query.UserQuery;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@SpringBootTest
class
MybatisplusTestApplicationTests {
	@Autowired
	private UserDao userDao;


	@Test
	void testGetAll1() {
		//按条件查询
//		QueryWrapper qw = new QueryWrapper();
//		qw.lt("age","18");
//		List list = userDao.selectList(qw);
//		System.out.println(list);

		//lambda格式按条件查询
//		QueryWrapper<User> qw = new QueryWrapper<User>();
//		qw.lambda().lt(User::getAge, 18);
//		List list = userDao.selectList(qw);
//		System.out.println(list);

		//lambda格式链式编程,and/or 且条件或条件
//		LambdaQueryWrapper<User> lqw=new LambdaQueryWrapper<User>();
//	    lqw.lt(User::getAge, 18).gt(User::getAge,2);
//		lqw.lt(User::getAge,10).or().gt(User::getAge,20);
//		List<User> userList = userDao.selectList(lqw);
//		System.out.println(userList);

		//
		UserQuery uq = new UserQuery();
		uq.setAge(5);
		uq.setAge2(20);

		LambdaQueryWrapper<User> lqw = new LambdaQueryWrapper<User>();
		lqw.lt(null!=uq.getAge2(),User::getAge,uq.getAge2());
		lqw.gt(null!=uq.getAge(),User::getAge,uq.getAge());
		List<User> userList = userDao.selectList(lqw);
		System.out.println(userList);


	}

	@Test
	void testbyselect1(){
//		LambdaQueryWrapper<User> ulw = new LambdaQueryWrapper<>();
//		ulw.select(User::getAge,User::getId,User::getName);
//		List<User> userList = userDao.selectList(ulw);
//		System.out.println(userList);

//		QueryWrapper<User> qw = new QueryWrapper<>();
//		qw.select("age","name","id");
//		List<User> userList = userDao.selectList(qw);
//		System.out.println(userList);


		//查询投影
//		QueryWrapper<User> qw = new QueryWrapper<>();//意思是查询结果放在User里,还可以对查询条件做检查属性是否有错
//		qw.select("count(*) as count,tel");
//		qw.groupBy("tel");
//		List<Map<String, Object>> maps = userDao.selectMaps(qw);//这里数据只放到User里行不通所以
//		System.out.println(maps);

		//查询投影and分组查询
//		QueryWrapper<User> qw = new QueryWrapper<>();
//		qw.select("count(*) as count,tel,age");
//		qw.groupBy("age");
//		List<Map<String, Object>> maps = userDao.selectMaps(qw);
//		System.out.println(maps);

		//条件查询
//		LambdaQueryWrapper<User> lqw=new LambdaQueryWrapper<>();
//		lqw.eq(User::getName,"彭家").eq(User::getAge,12);
//		List<User> userList = userDao.selectList(lqw);
//		System.out.println(userList);

		LambdaQueryWrapper<User> lqw=new LambdaQueryWrapper<>();
		lqw.like(User::getAge,"1");
		List<User> userList = userDao.selectList(lqw);
		System.out.println(userList);


	}

	@Test
	void testsave(){
		User user = new User();
		user.setName("w2ew");
		user.setPassword("542w2");
		user.setTel("199181");
		user.setAge(12);
		userDao.insert(user);
	}
	@Test
	void testDelete(){
		ArrayList<Long> lists = new ArrayList<>();
		lists.add(1L);
		lists.add(2L);
		userDao.deleteBatchIds(lists);
	}

	@Test
	void testUpdate(){
		//user
		User user = new User();
		user.setId(4l);
		userDao.selectById(user.getId());
		//user2
		User user2 = new User();
		user2.setId(4l);
		userDao.selectById(user2.getId());

		user2.setName("彭家2");
		user2.setPassword("123456");
		user2.setTel("400618618");
		user2.setAge(12);
		userDao.updateById(user2);

		user.setName("彭家0");
		user.setPassword("123456");
		user.setTel("400618618");
		user.setAge(12);
		userDao.updateById(user);
	}

	@Test
	//分页查询
	void  testSeleteByPage(){
		IPage iPage=new Page(2,3);
		userDao.selectPage(iPage,null);
		System.out.println("当前页码值:"+iPage.getCurrent());
		System.out.println("每页显示数:"+iPage.getSize());
		System.out.println("一共多少页:"+iPage.getPages());
		System.out.println("总数居条数:"+iPage.getTotal());
		System.out.println("当前页数据记录:"+iPage.getRecords());

	}

	@Test
	void testGetAll() {
		List<User> userList = userDao.selectList(null);
		System.out.println(userList);
	}

}
