//package uasz.sn.microservice_repartition.CONFIG;
//
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.http.MediaType;
//import org.springframework.http.converter.HttpMessageConverter;
//import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
//import org.springframework.web.servlet.config.annotation.ContentNegotiationConfigurer;
//import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
//
//import java.nio.charset.StandardCharsets;
//import java.util.Arrays;
//
//@Configuration
//public class WebConfig implements WebMvcConfigurer {
//    @Override
//    public void configureContentNegotiation(ContentNegotiationConfigurer configurer) {
//        configurer.defaultContentType(MediaType.APPLICATION_JSON);
//    }
//
//    @Bean
//    public HttpMessageConverter<Object> jsonHttpMessageConverter() {
//        MappingJackson2HttpMessageConverter converter = new MappingJackson2HttpMessageConverter();
//        converter.setSupportedMediaTypes(Arrays.asList(
//                MediaType.APPLICATION_JSON,
//                new MediaType("application", "json"   , StandardCharsets.UTF_8),
//                new MediaType("application", "*+json", StandardCharsets.UTF_8)
//        ));
//        return converter;
//    }
//}