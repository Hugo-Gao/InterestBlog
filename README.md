# InterestBlog

一个基于Spring Boot（服务端主力）+Redis（缓存点击量等数据库）+Nginx(静态资源处理)+MySQL(储存文章等数据)+ElasticSearch(全局高亮搜索)的功能完备的个人博客系统，欢迎一起学习

## Usage

1. clone
2. 在你的机器上MySQL创建一个database供博客系统使用
3. 在MySQL客户端执行resources下的init.sql文件
4. 在工程application.properties下配置你的MySQL、Redis、Nginx、Elastic连接信息
5. 启动工程，访问localhost:8080/backend进入后端管理界面
6. 访问localhost:8080进入博客首页

## Demo

### 博客首页

![image-20190512115848906](https://ws3.sinaimg.cn/large/006tNc79gy1g2yeckmqkbj31ha0quwjs.jpg)

### 全局搜索

![image-20190517160722622](http://ww1.sinaimg.cn/large/006tNc79gy1g34dmmkpipj31j60u0u0x.jpg)

### Tags页

![image-20190512122607576](https://ws4.sinaimg.cn/large/006tNc79gy1g2yf4zdazhj31hc0qxjsp.jpg)

### aboutme页

![image-20190512122705054](https://ws3.sinaimg.cn/large/006tNc79gy1g2yf5ydvmsj31hb0qvad9.jpg)

### 后台登录

![image-20190512122745417](https://ws3.sinaimg.cn/large/006tNc79gy1g2yf76sjvlj31hb0qtjsa.jpg)

### 控制台(未完成)

![image-20190517160622779](http://ww3.sinaimg.cn/large/006tNc79gy1g34dln2vk7j31iq0u07ek.jpg)

## 写博客

![image-20190512122854851](https://ws4.sinaimg.cn/large/006tNc79gy1g2yf7xajt2j31hc0qwgmy.jpg)

### 文章管理

![image-20190512122925947](https://ws2.sinaimg.cn/large/006tNc79gy1g2yf8dzeatj31hb0r0tav.jpg)

### 个人设置

![image-20190512122946472](https://ws4.sinaimg.cn/large/006tNc79gy1g2yf8nztm5j31hc0r0af6.jpg)

## Comming

* ~~丰富控制台内容~~ 
* ~~全局搜索~~
* ~~文章编辑功能~~

* 更多的数据统计功能
* 打包到docker，使其更容易部署