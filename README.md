# 开发平台使用示例

## 使用步骤
* 搭建工程
 * 新建maven工程，pom继承webapp-plugin，版本号根据实际情况改，**建议用最新稳定版本**
 
```xml
    <parent>
        <groupId>com.hgsoft.yfzx.webapp</groupId>
        <artifactId>webapp-parent</artifactId>
        <version>1.3.0-SNAPSHOT</version>
    </parent>
``` 
 * 引入开发平台的核心模块：webapp-web和webapp-plugin-log模块，版本号根据实际情况改，**建议用最新稳定版本**
 
```xml
    <dependency>
        <groupId>com.hgsoft.yfzx.webapp</groupId>
        <artifactId>webapp-web</artifactId>
        <version>1.3.0-SNAPSHOT</version>
    </dependency>

    <dependency>
        <groupId>com.hgsoft.yfzx.plugin</groupId>
        <artifactId>webapp-plugin-log</artifactId>
        <version>1.5.0-SNAPSHOT</version>
    </dependency>
```    
 
 * 再增加maven的build配置：
 
```xml
    <build>
        <finalName>webapp-demo</finalName>
        <resources>
            <resource>
                <directory>src/main/java</directory>
                <includes>
                    <include>**/*.xml</include>
                </includes>
                <filtering>false</filtering>
            </resource>
            <resource>
                <directory>src/main/resources</directory>
                <excludes>
                    <exclude>**/META-INF/**</exclude>
                </excludes>
                <filtering>false</filtering>
            </resource>
        </resources>
        <testResources>
            <testResource>
                <directory>src/test/resources</directory>
                <filtering>false</filtering>
            </testResource>
        </testResources>
    </build>
```
 
 * **resources** 文件夹下增加**config.properties**，**log4j2.xml**，**spring-shiro.xml**
 * **WEB-INF** 文件夹下增加 **mvc-dispatcher-servlet.xml**，**web.xml**
 * 到目前为止即可根据下面的 [**安装步骤**](#%E5%AE%89%E8%A3%85%E6%AD%A5%E9%AA%A4) 将项目跑起来
 
* 增加业务模块
 * 增加页面模块首先在界面上配置菜单并添加对应的路由配置
   * 在 **菜单管理** 增加对应的菜单
   * 在 **功能模块** 配置菜单的url和对应的路由
 * 后端代码的包名约定如下：
   * 控制层包名：com.hgsoft.组id.\*.\*.\*.controller（如：com.hgsoft.yfzx.demo.test.web.controller）
    * Service层包名：com.hgsoft.组id.\*.\*.service （如：com.hgsoft.yfzx.demo.test.service）
    * Dao层包名： com.hgsoft.组id.\*.\*.dao （如：com.hgsoft.yfzx.demo.test.dao）
    * 实体层包名： com.hgsoft.组id.\*.\*.entity （如：com.hgsoft.yfzx.demo.test.entity）
 * 增加好对应的代码后重启服务即可看到对应的功能模块

## 环境要求
1. jdk7及以上，tomcat7及以上
2. ie浏览器支持ie8及以上

## 安装步骤
1. 修改config.properties里的数据库（默认为sqlserver）连接信息，并创建对应的数据库
2. 配合ide将项目跑在tomcat上面或者mvn clean package 打包后将对应的war包直接放在tomcat运行
3. 1.3版本开始引入了flyway，需要自动执行自己的脚本可以先增加以下配置到spring配置文件

    ```xml
    <bean class="com.hgsoft.yfzx.webapp.util.CustomFlyway" init-method="init" depends-on="flyway">
        <property name="flyway">
            <bean class="org.flywaydb.core.Flyway">
                <property name="dataSource" ref="dataSource"/>
                <property name="baselineOnMigrate" value="true"/>
                <property name="table" value="schema_examples"/>
                <property name="locations" value="db/examples/demo/${webapp_web_dbType:''}"/>
            </bean>
        </property>
    </bean>
    ```
    
    table以schema开头加上对应的项目名，locations以db开头，${webapp_web_dbType:''}结尾，中间可以是项目名+模块名
    脚本命名以V+版本号+__+脚本名字+.sql，如V2__sys_order_init.sql，版本号需从2开始，可以加.号，如2.1

4. 1.3版本增加多配置文件支持，默认config.properties，开发config-dev.properties，测试config-test.properties，通过maven的profile实现切换，原理是通过修改web.xml里的spring.profiles.active参数

## svn地址

* <a href="http://svn.hgits.cn:81/svn/研发中心/基础技术研究/开发平台框架/11 公共资源/代码/demo">http://svn.hgits.cn:81/svn/研发中心/基础技术研究/开发平台框架/11 公共资源/代码/demo</a>

## 常见问题

1. 用一段时间提示内存溢出，请修改JVM参数：-Xmx512m -XX:MaxPermSize=256m
2. 有时出现文字乱码：修改Tomcat的server.xml文件的Connector项，增加URIEncoding="UTF-8"
3. 为什么新建菜单后看不到新建的菜单？因为授权问题，需要给对应的角色分配菜单（最高管理员默认账号：admin 密码：123456）
