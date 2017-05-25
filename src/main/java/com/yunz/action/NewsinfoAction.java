package com.yunz.action;

import com.opensymphony.xwork2.ActionSupport;
import com.sun.corba.se.spi.ior.ObjectKey;
import com.yunz.biz.NewsinfoBiz;
import com.yunz.biz.TopicBiz;
import com.yunz.entity.Newsinfo;
import com.yunz.entity.Pager;
import com.yunz.entity.Topic;
import org.apache.struts2.interceptor.RequestAware;
import org.apache.struts2.interceptor.SessionAware;

import java.util.List;
import java.util.Map;

/**
 *
 * Created by Administrator on 2017/5/24 0024.
 */
public class NewsinfoAction extends ActionSupport implements RequestAware, SessionAware{
    int id;   //封装表单传递的新闻编号
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }

    Newsinfo newsinfo;   // 封装表单数据
    TopicBiz topicBiz;   // 主题业务逻辑接口
    NewsinfoBiz newsinfoBiz;        // 新闻业务逻辑接口
    private Pager pager;           // 分页实体类
    Map<String, Object> request;    // request
    Map<String, Object> session;    // session

    public Newsinfo getNewsinfo() {
        return newsinfo;
    }

    public void setNewsinfo(Newsinfo newsinfo) {
        this.newsinfo = newsinfo;
    }

    public void setTopicBiz(TopicBiz topicBiz) {
        this.topicBiz = topicBiz;
    }

    public void setNewsinfoBiz(NewsinfoBiz newsinfoBiz) {
        this.newsinfoBiz = newsinfoBiz;
    }

    public Pager getPager() {
        return pager;
    }

    public void setPager(Pager pager) {
        this.pager = pager;
    }

    @Override
    public void setRequest(Map<String, Object> request) {
        this.request = request;
    }

    @Override
    public void setSession(Map<String, Object> session) {
        this.session = session;
    }

    // 根据条件和页码获取新闻列表，再转向新闻浏览页面 index.jsp
    public String index() throws Exception {
        int curPage = 1;

        if (pager != null) {
            curPage = pager.getCurPage();
        }

        List newsinfoList = null;
        if (newsinfo == null) {
            // 如果没有指定查询条件，获取指定页码的新闻列表
            newsinfoList = newsinfoBiz.getAllNewsinfoByPage(curPage, 10);
            // 在获取所有新闻总数，用以初始化分页类pager对象
            pager = newsinfoBiz.getPagerOfAllNewsinfo(10);
        } else {
            // 如果指定了查询条件，根据条件获取指定页码的新闻列表
            newsinfoList = newsinfoBiz.getNewsinfoByConditionAndPage(newsinfo, curPage, 10);
            pager = newsinfoBiz.getPagerOfNewsinfo(newsinfo, 10);
        }
        // 设置pager对象中待显示页页码
        pager.setCurPage(curPage);
        // 将待显示的当前页新闻列表存入request范围
        request.put("newsinfoList", newsinfoList);
        // 获取所有主题
        List topicList = topicBiz.getAllTopics();
        // 将主题列表存入request范围
        request.put("topicList", topicList);
        // 转到index.jsp显示
        return "index";
    }

    // 从国内新闻和国际新闻各获取5条新闻，再转到index_sidebar.jsp
    public String indexsidebar() throws Exception {
        // 获取5条国内新闻
        Newsinfo condition = new Newsinfo();
        Topic topic = new Topic();
        topic.setId(1);
        condition.setTopic(topic);
        List domesticNewsList = newsinfoBiz.getNewsinfoByConditionAndPage(condition, 1, 5);
        // 获取5条国际新闻
        topic.setId(2);
        condition.setTopic(topic);
        List internationalNewsList = newsinfoBiz.getNewsinfoByConditionAndPage(condition, 1, 5);
        request.put("domesticNewsList", domesticNewsList);
        request.put("internationalNewsList", internationalNewsList);
        return "index_sidebar";
    }

    // 浏览新闻内容
    public String newsread() throws Exception {
        //根据新闻编号获取新闻
        Newsinfo newsinfo=newsinfoBiz.getNewsinfoById(id);
        //将新闻存入request范围
        request.put("newsinfo", newsinfo);
        //获取5条国内新闻
        Newsinfo conditon =new Newsinfo();
        Topic topic =new Topic();
        topic.setId(1);
        conditon.setTopic(topic);
        List domesticNewsList=newsinfoBiz.getNewsinfoByConditionAndPage(conditon, 1,5);
        //获取5条国际新闻
        topic.setId(2);
        conditon.setTopic(topic);
        List internationalNewsList=newsinfoBiz.getNewsinfoByConditionAndPage(conditon, 1,5);
        request.put("domesticNewsList", domesticNewsList);
        request.put("internationalNewsList", internationalNewsList);
        //获取所有主题，并存入request范围
        List topicList=topicBiz.getAllTopics();
        request.put("topicList", topicList);
        //转到新闻内容浏览页
        return "news_read";
    }
}
