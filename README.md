###hsession介绍
hsession是一个解决j2ee 项目session共享问题的一个小工具，能够让同一个用户请求被转发到多台web服务器时，回话依然有效。
###web.xml配置
```
<!-- 此过滤器 放在filter的最上面,否则可能 在此 filter 上面的filter 获取的session
  不是从memcache获取的,如果上面的filter 没有用到session则不影响-->
  <filter>
    <filter-name>sessionFilter</filter-name>
    <filter-class>com.hsession.filter.HSessionFilter</filter-class>
  </filter>
  <filter-mapping>
    <filter-name>sessionFilter</filter-name>
    <url-pattern>/*</url-pattern>
  </filter-mapping>
```
###cache.properties配置
```
#cache类型,可以使用自己写的其他类来实现com.hsession.cache.Cache
cache.type=com.hsession.memcached.MemCached
#memcached 缓存ip:port ，用,分隔 使用分布式memcache服务器
cache.servers=121.43.106.57:11211
cache.server.weights=10
```
###Maven第三方依赖
```
<dependency>
    <groupId>javax.servlet</groupId>
    <artifactId>servlet-api</artifactId>
    <version>2.5</version>
</dependency>
<dependency>
    <groupId>org.slf4j</groupId>
    <artifactId>slf4j-api</artifactId>
    <version>1.7.7</version>
</dependency>
<dependency>
    <groupId>com.whalin</groupId>
    <artifactId>Memcached-Java-Client</artifactId>
    <version>3.0.0</version>
</dependency>
```
>但是hsession可能会遇到的问题，需要考虑：

 - 分布式memcached服务器是通过hash算法来分别落到不同的服务器上，如果一台memcached服务器的down机可能造成一批用户的登陆态丢失，需要重新登录。
 - memcached 分块存储，如果优化不好可能会在成大量的内存浪费 或者数据丢失
 - memcached不是一个稳定的数据存储服务器，可能会出现不确定的数据丢失，用户可能会莫名其妙下线，作为优化，可以用数据库作为辅助，或者直接使用redis