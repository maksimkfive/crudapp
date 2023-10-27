package org.example.crudprogramveb.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ViewResolverRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.thymeleaf.spring6.SpringTemplateEngine;
import org.thymeleaf.spring6.templateresolver.SpringResourceTemplateResolver;
import org.thymeleaf.spring6.view.ThymeleafViewResolver;
@Configuration
@ComponentScan("org.example.crudprogramveb")
@EnableWebMvc
public class NotesConfig implements WebMvcConfigurer { // Класс конфигурации, Аннотация @EnableWebMvc включает поддержку Spring MVC.
    private final ApplicationContext applicationContext;
    @Autowired
    public NotesConfig(ApplicationContext applicationContext) {
        this.applicationContext = applicationContext;
    }
    @Bean
    public SpringResourceTemplateResolver templateResolver() { // настраивает SpringResourceTemplateResolver, указывая префикс и суффикс для файлов шаблонов.
        SpringResourceTemplateResolver templateResolver = new SpringResourceTemplateResolver();
        templateResolver.setApplicationContext(applicationContext);
        templateResolver.setPrefix("/WEB-INF/views/");
        templateResolver.setSuffix(".html");
        return templateResolver;
    }
    @Bean
    public SpringTemplateEngine templateEngine() { //создает SpringTemplateEngine и устанавливает разрешитель шаблонов.
        SpringTemplateEngine templateEngine = new SpringTemplateEngine();
        templateEngine.setTemplateResolver(templateResolver());
        templateEngine.setEnableSpringELCompiler(true);
        return templateEngine;
    }
    @Override
    public void configureViewResolvers(ViewResolverRegistry registry) { // настраивает ThymeleafViewResolver и устанавливает шаблонный движок.
        ThymeleafViewResolver resolver = new ThymeleafViewResolver();
        resolver.setTemplateEngine(templateEngine());
        registry.viewResolver(resolver);
    }


}
