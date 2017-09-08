# NewsSite
一个简单的新闻发布网站Demo，使用SSH框架搭建，主要功能就是数据库的增删改查、分类查询以及分页显示。

## 系统与需求分析

- 管理员
    - 添加主题
    - 管理主题
    - 查看新闻内容
    - 添加新闻
    - 管理新闻
- 未登录用户
    - 查看主题
    - 查看新闻标题
    - 浏览新闻内容
- 登录用户
    - 查看主题
    - 查看新闻标题
    - 浏览新闻内容
    - 评论新闻

## 数据库设计

- 主题表：Topic
  - ID
  - Name
- 新闻信息表：Newsinfo
  - ID
  - Title   新闻标题
  - Author    作者
  - PublishDate    发布时间
  - UpdateDate    更新时间
  - Content       内容
  - Summary   摘要
  - Tid      外键，所属主题
- 管理员信息表：Admin
  - ID
  - LoginName    登录名
  - LoginPwd       登录密码 
- 用户信息表：User
  - ID
  - LoginName   登录名
  - LoginPwd      登陆密码
- 评论：Comments
  - ID
  - Content   内容
  - PostDate     发布时间
  - Uid       外键，所属用户
  - Nid       外键，所属新闻

