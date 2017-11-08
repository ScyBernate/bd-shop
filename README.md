------------------------------------------------

> BDShop 

BDShop是国内少有前后端完全分离的java商城项目.
## BDShop 系统简介
BDShop商城系统是B2C模式的电子商城

后端基于springboot 职责单一提供api服务

前端基于nodejs 纯html5 css3 通过json 和后端api交互。


实现的前后端分离架构的项目

![前后端分离架构](https://git.oschina.net/uploads/images/2017/0426/154407_2f5bf086_127930.png "前后端分离架构")





相关技术文章

[前后端分离的思考与实践](http://blog.jobbole.com/65513/)

[浅谈前后端分离技术](http://www.jianshu.com/p/f1287e1aee50)


##BDShop技术路线
后端构建:spring-boot+mybatis+pagehelper+Swagger2构建RESTful API

前端构建:nodejs+gulp+requirejs+art-template+bootstrap+weui


安装运行步骤



## 此项目用到的技术
  spring boot mybatis node.js

## 注意事项
   
   $ mvn clean install
   $ mvn spring-boot:run

http://localhost:10001/swagger-ui.html
 
需要安装node.js,配置环境变量
##NODE_PATH=C:\Program Files\nodejs\node_global\node_modules
##PATH C:\Program Files\nodejs\node_global;C:\Program Files\nodejs\

1.创建node_global、node_cache文件夹
2.npm install cnpm -g --registry=https://registry.npm.taobao.org  安装cnpm
3.cnpm install -g gulp   安装gulp
4.cnpm i node-sass -g    安装node-sass
5.跳转前端项目 cd front-projec , cnpm install
6.gulp.  如果遇到报错缺少module，cnpm install xxx

注意：如果查询语句报错 "0" not found，将#{0}改为#{arg0},mybatis版本不支持


   


   
   
