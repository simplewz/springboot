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
	@JsonIgnoreProperties(ignoreUnknown = true)
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
  
### 四.使用Maven构建Java项目
  maven是一个用于管理Java项目的工具,用途其一是采用约定的项目目录结构规范管理Java项目的目录结构，其二是统一维护Java项目依赖的jar包。这里我们以纯手工的方式创建并构建一个Maven项目，主要目的是熟悉Maven项目的目录结构，练习使用基本的Maven命令。
  
1. 创建一个gs-maven的工程项目，项目目录结构如下。

	<pre>
		gs-maven                           项目名称
		     |---src/main/java             存放Java代码的目录
		     		|---maven	   存放Java代码的包(不同的类放在不同的包下)
		     |---src/test/java		   存放测试所用的Java目录
		     		|---test	   存放测试Java代码
	</pre>

  以上的目录结构就是一个可用maven构建的普通Java项目的目录结构，不同的项目类型目录结构会有所区别，其核心的目录结构就是这里介绍的目录结构。

2. 在maven包下新增Greeter.java和HelloWorld.java文件，其代码如下：

   #### Greeter.java
  
	  ```
	  public class Greeter{
		public String sayHello(){
			return "Hello World";
		}
	  }
	  ```
  
   #### HelloWord.java
  
	  ```
	  public class HelloWorld {
	    	public static void main(String[] args) {
			Greeter greeter = new Greeter();
			System.out.println(greeter.sayHello());
	    	}
	  }
	  ```

  经过步骤1、步骤2以后，项目已经可以用Maven工具进行构建了，接下来就是去下载安装Maven。
  
3. Maven的安装地址[ https://maven.apache.org/download.cgi]( https://maven.apache.org/download.cgi),将下载好的Maven进行解压缩，然后将Maven的安装路径配置到系统的环境变量中，在命令行中输入mvn -v 命令测试Maven是否安装成功。
  
  ![Maven安装成功效果图](https://github.com/simplewz/springboot/blob/master/images/Maven安装成功效果图.jpg)
  
4. Maven管理项目都是通过pom.xml(project object model的简写)配置文件进行管理的。pom文件中会详细配置Java项目的名称、版本、依赖的第三方jar包等信息，所以为了能够使用Maven工具构建Java项目，我们还需要在项目中创建这个pom.xml配置文件，**注意pom.xml配置文件所在的路径需要与src在同一级下**。pom.xml配置文件的内容如下：

	```
	<?xml version="1.0" encoding="UTF-8"?>
		<project xmlns="http://maven.apache.org/POM/4.0.0" xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
		    xsi:schemaLocation="http://maven.apache.org/POM/4.0.0 http://maven.apache.org/maven-v4_0_0.xsd">
		    <modelVersion>4.0.0</modelVersion>

		    <groupId>org.springframework</groupId>
		    <artifactId>gs-maven</artifactId>
		    <packaging>jar</packaging>
		    <version>0.1.0</version>

		    <properties>
			<maven.compiler.source>1.8</maven.compiler.source>
			<maven.compiler.target>1.8</maven.compiler.target>
		    </properties>

		    <build>
			<plugins>
			    <plugin>
				<groupId>org.apache.maven.plugins</groupId>
				<artifactId>maven-shade-plugin</artifactId>
				<version>2.1</version>
				<executions>
				    <execution>
					<phase>package</phase>
					<goals>
					    <goal>shade</goal>
					</goals>
					<configuration>
					    <transformers>
						<transformer
						    implementation="org.apache.maven.plugins.shade.resource.ManifestResourceTransformer">
						    <mainClass>maven.HelloWorld</mainClass>
						</transformer>
					    </transformers>
					</configuration>
				    </execution>
				</executions>
			    </plugin>
			</plugins>
		    </build>
		</project>	
	```
	
pom.xml配置文件中的一些标签说明：

<modelVersion> 项目对象模型版本信息,一般是4.0.0。  	
<groupId> 项目的组织信息。  
<artifactId>项目名称。  
<packaging>项目打包的方式，默认打包方式为jar包的形式。  
<version>版本信息。  

5. 使用maven命令构建项目。

mvn compile：编译项目。命令执行成功后将会在工程目录下多出target/classes这个目录，这个目录下存放着编译后的.class文件。

![mvn compile编译成功运行截图](https://github.com/simplewz/springboot/blob/master/images/maven-compile.png)

mvn package:打包项目。对项目代码进行编译，执行项目中的所有测试用例，并将项目代码打包成一个jar包(如果在pom.xml配置文件中配置了package为war包，则会将项目打包为war包)。打包后的文件名称会依据配置文件中的<artifactId>和<version>进行命名。如上的pom.xml文件中的配置打包后的项目jar包应该为：gs-maven-0.1.0.jar。
	
mvn install:安装依赖。对项目代码进行编译、执行项目中的测试用例，将项目打包并将项目所依赖的第三方jar包也打包进来，防止别的项目依赖本项目时，本项目依赖的第三方jar包缺失。

6. 添加第三方jar包依赖。

  对之前的HelloWorld.java文件进行编辑，编辑后的代码如下：
  
  #### HelloWorld.java

	  ```
		package maven;

		import org.joda.time.LocalTime;
		public class HelloWorld{
			public static void main(String[] args){
				Greeter greeter=new Greeter();
				LocalTime currentTime=new LocalTime();
				System.out.println("The current loal time is:"+currentTime);
				System.out.println(greeter.sayHello());
			}
		}
	  ```

  代码中我们想使用joda-time中的LocalTime类来构造一个当前日期，因为项目中没有依赖joda-time的jar包，所以代码会进行报错。我们需要进行第三方jar包依赖。

  maven添加第三方jar包依赖的方式是在pom.xml配置文件中的<dependencies>标签下添加子标签<dependencie>，这里以依赖处理时间的第三方jar包joda-time为例进行示例配置如下：

	  <pre>
		<dependencies>
			<!-- tag::joda[] -->
			<dependency>
				<groupId>joda-time</groupId>
				<artifactId>joda-time</artifactId>
				<version>2.9.2</version>
			</dependency>
			<!-- end::joda[] -->
			<!-- tag::junit[] -->
			<dependency>
				<groupId>junit</groupId>
				<artifactId>junit</artifactId>
				<version>4.12</version>
				<scope>test</scope>
			</dependency>
			<!-- end::junit[] -->
		</dependencies>
	  </pre>

可以看到依赖jar包配置的三个重要标签配置分别是：

<groupId>：项目组织。  
<artifactId>：项目名称。  
<version>：项目版本。  
<scope>:其值为provided时该jar包为项目编译和运行时需要的jar包，test编译该jar包用于项目编译和运行测试用例时所用，项目实际运行时不需要。

这三个标签与pom.xml配置文件中配置项目信息的标签的含义是一致的。我们的项目打包成jar包后，别人依赖我们项目时，添加依赖时的信息与pom.xml文件中的配置信息就是用这三个标签配置的。添加依赖以后，我们重新使用mvn compile命令编译项目，然后再次运行项目可以看到项目正常运行。

7. 使用Maven进行项目测试

  首先在pom.xml配置文件中添加junit单元测试依赖包,添加配置的在上面添加joda-time依赖时一起添加了。然后再src/test/java下的test包下添加Greeter.java文件，代码如下：
  
	  ```
		package test;

		import static org.hamcrest.CoreMatchers.containsString;
		import static org.junit.Assert.*;
		import org.junit.Test;

		import maven.Greeter;
		public class GreeterTest{
	
			private Greeter greeter=new Greeter();

			@Test
			public void greeterSayHello(){
				assertThat(greeter.sayHello(),containsString("Hello"));
			}
		}
	  ```

  Maven使用surefire插件进行单元测试，默认配置是该插件编译运行src/test/java目录下的\*Test.java中的测试用例，注意在编写的测试用例方法中需要加上@Test注解。使用mvn test执行项目中的测试用例:
  
  ![mvn test命令运行成功截图](https://github.com/simplewz/springboot/blob/master/images/maven-test.png)  
  
 #### 实际工作中使用maven都是在IDE中进行集成，在IDE中使用Maven时会更加方便，不过手动使用命令行的方式构建Java项目是会加深对Maven这个项目管理工具的使用理解。
 

### 五.SpringBoot项目中使用JDBC访问数据库
### 六.SpringBoot项目中的文件上传于下载
### 七.

   
   





   
