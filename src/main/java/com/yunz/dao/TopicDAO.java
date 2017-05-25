package com.yunz.dao;

import java.util.List;
import java.util.Map;

import com.yunz.entity.Topic;


public interface TopicDAO {
	//��ȡ��������
	public List getAllTopics();
	
	//ͨ�����ƻ�ȡ����
	public List getTopicByName(String name);
	
	//ͨ����Ż�ȡ����
	public Topic getTopicById(int id);	
	
	//ɾ������
	public void deleteTopic(Topic topic);	
	
	//����������Ŀ
	public int countTopics();
	
	//��������
	public void updateTopic(Topic topic);
	
	//�������
	public void addTopic(Topic topic);

}
