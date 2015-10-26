title: JQuery easyUI + SpringMVC
---
JQuery easyUI + SpringMVC做项目会非常快。JQuery easyUI提供了丰富的控件来供使用。可以查看提供的demo和文档：http://www.jeasyui.net/


## JQuery easyUI简介

``` 
easyui是一种基于jQuery的用户界面插件集合。
easyui为创建现代化，互动，JavaScript应用程序，提供必要的功能。
使用easyui你不需要写很多代码，你只需要通过编写一些简单HTML标记，就可以定义用户界面。
easyui是个完美支持HTML5网页的完整框架。
easyui节省您网页开发的时间和规模。
easyui很简单但功能强大的。
```


## SpringMVC简介

``` SpringMVC是一个典型的教科书式的mvc构架。Spring MVC 分离了控制器、模型对象、分派器以及处理程序对象的角色，这种分离让它们更容易进行定制。
```

### web.xml部分配置
``` <?xml version="1.0" encoding="UTF-8"?>
<web-app xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance" 
xmlns="http://java.sun.com/xml/ns/javaee" 
xmlns:web="http://java.sun.com/xml/ns/javaee/web-app_2_5.xsd" xsi:schemaLocation="http://java.sun.com/xml/ns/javaee 
http://java.sun.com/xml/ns/javaee/web-app_2_5.xsd" version="2.5">
  <context-param>
    <param-name>contextConfigLocation</param-name>
    <param-value>/WEB-INF/springmvc-servlet.xml</param-value>
  </context-param>
  <listener>
    <listener-class>org.springframework.web.context.ContextLoaderListener</listener-class>
  </listener>
  <servlet>
    <servlet-name>springmvc</servlet-name>
    <servlet-class>org.springframework.web.servlet.DispatcherServlet</servlet-class>
    <init-param>
      <param-name>contextConfigLocation</param-name>
      <param-value>/WEB-INF/*-servlet.xml</param-value>
    </init-param>
    <load-on-startup>1</load-on-startup>
  </servlet>
  <servlet-mapping>
    <servlet-name>springmvc</servlet-name>
    <url-pattern>*.htm</url-pattern>
  </servlet-mapping>
  <filter>  
    <filter-name>sessionFilter</filter-name>  
    <filter-class>com.dygkd.interceptor.LoginFilter</filter-class>  
</filter>  
<filter-mapping>  
    <filter-name>sessionFilter</filter-name>  
    <url-pattern>*.jsp</url-pattern>  
</filter-mapping>  
</web-app>
```


### controller注解

```@Controller
public class FastmailAction 
@RequestMapping(value="/showinfo") 影射请求uri
@ModelAttribute 	参数
@ResponseBody		返回JSON
```


