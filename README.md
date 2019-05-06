# springboot
# springboot教程

### 一.使用SpringBoot构建一个RESTful风格的web应用
1. 创建一个名为gs-rest-service的maven工程，并且在maven工程中添加pom文件，pom文件代码可参考spring官网 https://spring.io/guides 官网中的第一个实例的pom.xml文件。

2. 在新建的maven工程中建立如下项目结构：
<pre>
   src
    |--main
         |--java
              |--org.simple                       存放启动springboot应用的包
              |--org.simple.controller            存放拦截器的包
              |--org.simple.entity                存放实体的包
</pre>
3. 在org.simple包下创建Application.java文件，其代码如下：
```
@SpringBootApplication
public class Application {

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}
}
```

注意需要在该类上加@SpringBootApplication注解，主方法中的关键代码格式写法固定。

4. 在org.simple.entity中创建Greeting.java文件，顾名思义这是一个普通的java实体类，其代码如下：
```
public class Greeting {
	
	private long id;
	
	private String content;
	
	public Greeting(long id, String content) {
		super();
		this.id = id;
		this.content = content;
	}

	public long getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}
}
```

5. 在org.simple.controller包下创建GreetingController.java文件，其代码如下：
```
@RestController
public class GreetingController {
	
	private static final String template = "Hello, %s!";
    private final AtomicLong counter = new AtomicLong();

    @RequestMapping("/greeting")
    public Greeting greeting(@RequestParam(value="name", defaultValue="World") String name) {
        return new Greeting(counter.incrementAndGet(),
                            String.format(template, name));
    }
}
```

注意需要在该类上加@RestController注解，方法中 @RequestMapping("/greeting")注解将浏览器的请求映射到greeting方法处理，greeting方法中的@RequestParam(value="name", defaultValue="World"注解用于接收请求所带的参数，如果请求不带任何参数，则使用默认值World。

6. 运行SpringBoot应用
   1. 使用IDE运行SprngBoot应用只需用运行普通Java程序的方式运行前面所创建的Application.java程序即可。
   2. 使用maven运行需要安装maven，在命令行界面中切换到该应用所在的目录，然后使用mvn spring-boot:run命令即可运行，其运行效果图如下：
   
