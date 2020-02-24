package admin.config;

import admin.aop.LoggingFunctionNameInterceptor;
import org.springframework.boot.web.server.WebServerFactoryCustomizer;
import org.springframework.boot.web.servlet.server.ConfigurableServletWebServerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class BaseApplicationConfig
        implements WebServerFactoryCustomizer<ConfigurableServletWebServerFactory>, WebMvcConfigurer {
    @Override
    public void customize(ConfigurableServletWebServerFactory factory) {

    }

    @Bean
    public LoggingFunctionNameInterceptor loggingFunctionNameInterceptor() {
        // MDCに機能名を設定してログに出力する
        return new LoggingFunctionNameInterceptor();
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(loggingFunctionNameInterceptor());
    }
}
