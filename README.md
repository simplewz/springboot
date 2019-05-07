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
   
   ![mvn运行效果](https://github.com/simplewz/springboot/blob/master/images/1557109717.jpg)
   
   3. 也可以将项目打包成jar包，用java运行jar包的方式运行。首先使用maven将工程打包为jar包，其方式是切换到工程目录，使用mvn package命令即可将工程打包为jar包，然后使用java运行jar包的命令运行应用即可，运行效果如下：
   
   ![java运行jar包运行效果](https://github.com/simplewz/springboot/blob/master/images/1557111566.jpg)
   
7. 运行效果

  在浏览器中输入[http://localhost:8080/greeting](http://localhost:8080/greeting) ，浏览器返回如下结果：
  
  ![运行效果图](https://github.com/simplewz/springboot/blob/master/images/1557112203.jpg)
  
  在请求中可以带参数进行请求，在浏览器中输入[http://localhost:8080/greeting?name=Simple](http://localhost:8080/greeting?name=Simple) ,浏览器返回如下结果：
  
  ![带参数请求运行效果](https://github.com/simplewz/springboot/blob/master/images/1557112428.jpg)
  
  可以看到第二次请求的id自增了，而且请求的参数也被接收到了。
  
  
  
  ### 二.使用SpringBoot创建定时任务
  1. 创建一个名为gs-scheduling-tasks的maven工程，并且在maven工程中添加pom文件，pom文件代码可参考spring官网 https://spring.io/guides 官网中的scheduling tasks应用的pom.xml文件。
  2. 在新建的maven工程中创建如下的目录结构：
  <pre>
  	src
	 |__main
	      |__java
	           |__org.simple.schedul
		   |__org.simple.schedul.sercice
  </pre>
  
  3.在包org.simple.schedul下创建Application.java文件，其代码如下：
 ```
		@EnableScheduling
		@SpringBootApplication
		public class Application {

			public static void main(String[] args) {
				SpringApplication.run(Application.class, args);
			}
		}
  ```
	
**注意：与普通的SpringBoot应用程序相比，该应用的不仅需要@SpringBootApplication注解，还需要@EnableScheduling开启定时任务，如果没有该注解，定时任务将不会得到执行。**

4. 在org.simple.schedul.service包下创建ScheduledTasks.java文件，其代码如下：
```
	@Component
	public class ScheduledTasks {
	
		private static final Logger log =LoggerFactory.getLogger(ScheduledTasks.class);
	
		private static final SimpleDateFormat dateFormat=new SimpleDateFormat("HH:mm:ss");
	
		@Scheduled(fixedRate=5000)
		public void reportCurrentTime() {
			log.info("The time is now(){}",dateFormat.format(new Date()));
		}
	}
```

**注意：在该类上使用@Component注解将该类纳入Spring容器进行管理，在该类中只有一个报告时间的方法reportCurrentTime，在该方法上我们使用了@Scheduled(fixedRate=5000)注解，其含义是每隔5秒钟在控制台上打印当前时间。**

5. 运行SpringBoot应用程序
运行该应用程序将会看到每隔5秒钟控制台上打印当前时间，运行效果如下：

   ![定时任务运行效果图](https://github.com/simplewz/springboot/blob/master/images/1557123832.jpg)
   
6. 关于@Scheduled注解
    在我们的示例应用中，我们在@Scheduled注解里指定fixRate=5000，fixRate后面指定的值是时间，单位是毫秒，所以在我们的应用正常运行之后，我们看到控制台上每个5秒总打印一次当前时间。
   
   关于更多@Scheduled注解的参数信息，可参考简书文章[@Scheduled注解各参数详解](https://www.jianshu.com/p/1defb0f22ed1)
   
   
 ### 三.使用SpringBoot接收一个符合RESTful风格的接口数据
   
   我们知道两个应用之间的相互通信一般是通过接口实现的，这里，我们将创建一个应用接收来自 http://gturnquist-quoters.cfapps.io/api/random 的数据。可以在浏览器中输入该地址，我们看到该地址返回的数据是标准的json格式的数据，示例返回数据如下：
   
 <pre>
	{
		"type": "success",
		"value": {
			"id": 11,
			"quote": "I have two hours today to build an app from scratch. @springboot to the rescue!"
		}
	}
</pre>
	
1. 创建一个名称为consuming-restful-service的maven工程，并在工程中添加pom文件，pom文件的代码可参考pom文件代码可参考spring官网 https://spring.io/guides 官网中的Consuming a RESTful Web Service应用的pom.xml文件。

2. 在Maven工程中建立如下项目结构：
   
   <pre>
	  src
	    |__main
	         |__java
		      |__org.simple
		      |__org.simple.entity
   </pre>

3.在org.simple.entity包下创建Quote.java和Value.java文件，两个文件的代码分别如下：
   #### 1. Quote.java
   
   ```
  	@JsonIgnoreProperties(ignoreUnknown = true)
	public class Quote {
	
		@JsonProperty(value="type")
		private String type;

		private Value value;

		public Quote() {
			super();
		}

		public String getType() {
			return type;
		}

		public void setType(String type) {
			this.type = type;
		}

		public Value getValue() {
			return value;
		}

		public void setValue(Value value) {
			this.value = value;
		}

		@Override
		public String toString() {
			return "Quote [type=" + type + ", value=" + value + "]";
		}		
	}
   ```
   
   #### 2. Value.java
   
   ```
        @JsonIgnoreProperties(ignoreUnknow = true)
	public class Value {
	
		private long id;

		private String quote;

		public Value() {
			super();
		}

		public long getId() {
			return id;
		}

		public void setId(long id) {
			this.id = id;
		}

		public String getQuote() {
			return quote;
		}

		public void setQuote(String quote) {
			this.quote = quote;
		}

		@Override
		public String toString() {
			return "Value [id=" + id + ", quote=" + quote + "]";
		}
	}
   ```
   
   上面的两个类都是普通的java对象，在这两个类上我们都加上了@JsonIgnoreProperties(ignoreUnknown = true)注解，加上这个注解的目的是在后面接收从接口返回的数据时，如果用于接收数据绑定的类没有json中的属性，就不进行数据绑定。在spring的示例应用中还提到，如果返回的json数据格式的key值与接收数据的对象的属性名称不一致时，可以使用@JsonProperty注解进行处理。
   
4. 在org.simple包下建立Application.java文件，其代码如下：
   
   	```
	@SpringBootApplication
	public class Application {
	
		private static final Logger logger=LoggerFactory.getLogger(Application.class);

		public static void main(String[] args) {
			SpringApplication.run(Application.class, args);
		}


		@Bean
		public RestTemplate restTemplate(RestTemplateBuilder builder) {
			return builder.build();
		}

		@Bean
		public CommandLineRunner run(RestTemplate restTemplate) throws Exception {
			return args -> {
				Quote quote = restTemplate.getForObject(
						"http://gturnquist-quoters.cfapps.io/api/random", Quote.class);
				logger.info(quote.toString());
			};
		}
	}
	```
	
   程序中我们是用spring提供的restTemplate模拟发出请求，并接受请求响应的结果，然后使用getForObject方法将返回的结果将数据绑定到我们quote对象中。
	
5. 运行应用看到如下结果

   ![运行效果图](https://github.com/simplewz/springboot/blob/master/images/1557199103.jpg)
   
   





   
