package com.yunz.biz.impl;

import java.util.List;

import com.yunz.biz.AdminBiz;
import com.yunz.dao.AdminDAO;
import com.yunz.entity.Admin;

public class AdminBizImpl implements AdminBiz {

	//ʹ��AdminDAO�ӿ���������adminDAO�������set������������ע��
	AdminDAO adminDAO;		
	public void setAdminDAO(AdminDAO adminDAO) {
		this.adminDAO = adminDAO;
	}

	@Override
	public List login(Admin condition) {		
		return adminDAO.search(condition);
	}

}
