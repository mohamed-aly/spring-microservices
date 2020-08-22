package stationery.store.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.hibernate5.Hibernate5Module;
import org.apache.catalina.servlets.WebdavServlet;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.List;

@Configuration
public class WebConfig implements WebMvcConfigurer {


    //Hibernate lazy collections configurations
    public void extendMessageConverters(List<HttpMessageConverter<?>> converters) {
        for (HttpMessageConverter converter : converters) {
            if (converter instanceof MappingJackson2HttpMessageConverter) {
                ObjectMapper mapper = ((MappingJackson2HttpMessageConverter) converter).getObjectMapper();
                mapper.registerModule(new Hibernate5Module());
            }
        }
    }

    @Bean
    ServletRegistrationBean h2servletRegistration() {
        ServletRegistrationBean registrationBean = new ServletRegistrationBean(new WebdavServlet());
        registrationBean.addUrlMappings("/console/*");
        return registrationBean;
    }



}
