package web;

import com.mchange.v2.c3p0.ComboPooledDataSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.filter.CharacterEncodingFilter;
import org.springframework.web.multipart.MultipartResolver;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.DefaultServletHandlerConfigurer;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import org.springframework.web.servlet.view.json.MappingJackson2JsonView;
import web.Interceptor.SsoInteceptor;
import web.Interceptor.sessionInterceptor;

import javax.sql.DataSource;
import java.beans.PropertyVetoException;
import java.io.IOException;

/*
 * Created by tanjian on 16/9/14.
 * 配置类
 */
@Configuration
@EnableWebMvc
@ComponentScan({"web"})
public class WebConfig extends WebMvcConfigurerAdapter {

//    @Bean
//    public FreeMarkerConfigurer freeMarkerConfigurer() {
//        FreeMarkerConfigurer freeMarkerConfigurer = new FreeMarkerConfigurer();
//        freeMarkerConfigurer.setTemplateLoaderPath("/resources/views/freemakerTepl");
//        freeMarkerConfigurer.setDefaultEncoding("UTF-8");
//        return freeMarkerConfigurer;
//    }

//    @Bean
//    public FreeMarkerViewResolver freeMarkerViewResolver() {
//        FreeMarkerViewResolver freeMarkerViewResolver = new FreeMarkerViewResolver();
//        freeMarkerViewResolver.setViewClass(FreeMarkerView.class);
//        freeMarkerViewResolver.setContentType("text/html; charset=utf-8");
//        freeMarkerViewResolver.setSuffix(".ftl");
//        freeMarkerViewResolver.setCache(true);
//        freeMarkerViewResolver.setOrder(0);
//        return freeMarkerViewResolver;
//    }

    @Bean
    public ViewResolver viewResolver() {
        InternalResourceViewResolver resolver = new InternalResourceViewResolver();
        resolver.setPrefix("/resources/views/");
        resolver.setSuffix(".html");
        resolver.setExposeContextBeansAsAttributes(true);
        return resolver;
    }

    @Bean
    public CharacterEncodingFilter encodingFilter(){
        return new CharacterEncodingFilter("UTF-8",true);
    }

    @Override
    public void configureDefaultServletHandling(DefaultServletHandlerConfigurer configurer) {
        configurer.enable();
    }

    /*配置C3P0数据库连接池*/
    @Bean
    public ComboPooledDataSource dataSource() throws PropertyVetoException {
        ComboPooledDataSource ds = new ComboPooledDataSource();
        ds.setDriverClass("com.mysql.jdbc.Driver");
        ds.setJdbcUrl("jdbc:mysql://115.159.216.56:3306/TGAdmin");
        ds.setUser("dev");
        ds.setPassword("123456");
        ds.setInitialPoolSize(5);
        ds.setMaxPoolSize(10);
        ds.setMinPoolSize(3);
        ds.setMaxIdleTime(600);
        return ds;
    }

    @Bean
    public JdbcTemplate jdbcTemplate(DataSource dataSource) {
        return new JdbcTemplate(dataSource);
    }

    //注册拦截器
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(ssoInteceptor()).excludePathPatterns("/login").excludePathPatterns("/order/add");/*除了"/login路由不会被拦截器拦截，其余均会被拦截"*/
    }

    //自定义拦截器
    @Bean
    public SsoInteceptor ssoInteceptor(){
        return new SsoInteceptor();
    }


    //自定义session拦截器,但为注册
    @Bean
    public sessionInterceptor sessionInterceptor(){
        return new sessionInterceptor();
    }

    @Bean
    public MultipartResolver multipartResolver() throws IOException {
        CommonsMultipartResolver multipartResolver=new CommonsMultipartResolver();
        multipartResolver.setDefaultEncoding("UTF-8");
        multipartResolver.setMaxUploadSize(6291456);
        multipartResolver.setMaxInMemorySize(6291456);
        return multipartResolver;
    }

    /*Json 化*/
    @Bean
    public MappingJackson2JsonView mappingJackson2JsonView() {
        MappingJackson2JsonView mappingJackson2JsonView = new MappingJackson2JsonView();
        mappingJackson2JsonView.setContentType("application/json");
        return mappingJackson2JsonView;
    }
}
