1、依赖common fileupload。
2、编写监听器。写一个类实现ProgressListener。
3、自定义MultipartResolver。写一个类继承CommonsMultipartResolver。
4、配置一个MultipartResolver实现类。
5、要移除spring boot 默认的配置MultiPartAutoConfiguration。

注意：servletFileUpload.parseRequest 解析为空。 存在其他的resolver已经解析了。所以需要自定义Resolver。