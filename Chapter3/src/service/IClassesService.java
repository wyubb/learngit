package service;

import java.util.List;
import domain.Classes;
import domain.User;



public interface IClassesService {
		//添加一条发布信息到数据库
		public void addClasses(Classes classes);
		//获取所有信息列表
		public List<Classes> getAllClasses();
		//更新个人资料
				public void updateClasses(Classes classes);
				
			//根据班级编号删除班级
				public void deleteClasses(String classes_id);
}
