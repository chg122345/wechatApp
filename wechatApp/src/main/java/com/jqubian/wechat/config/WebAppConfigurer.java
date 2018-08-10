package com.jqubian.wechat.config;

import com.jqubian.wechat.common.util.ImgPathUtils;
import com.jqubian.wechat.interceptor.AdminInter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.filter.HttpPutFormContentFilter;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

/**
 * @Copyright (c) 2018, Chen_9g 陈刚 (80588183@qq.com).
 * @DateTime 2018/4/26
 *
 * <p>
 * A novice on the road, please give me a suggestion.
 * 众里寻他千百度，蓦然回首，那人却在，灯火阑珊处。
 * Find a way for success and not make excuses for failure.
 * </p>
 * 全局配置信息
 */
@Configuration
public class WebAppConfigurer extends WebMvcConfigurerAdapter {

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new AdminInter()).addPathPatterns("/**")
                .excludePathPatterns("/login", "/check", "/api/**","/**/*.css", "/**/*.js", "/**/*.png", "/**/*.jpg", "/**/*.jpeg", "/**/fonts/*");
//        registry.addInterceptor(new UserInter()).addPathPatterns("/api/**")
//                .excludePathPatterns("/login","/check","/api/check","/api/unLogin","/**/*.css", "/**/*.js", "/**/*.png", "/**/*.jpg", "/**/*.jpeg", "/**/fonts/*");
        super.addInterceptors(registry);
    }

    @Bean
    public LocaleResolver localeResolver() {
        return new MyLocale();
    }

    @Bean
    public HttpPutFormContentFilter httpPutFormContentFilter(){
        return new HttpPutFormContentFilter();
    }

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {

        //addResourceHandler是指你想在url请求的路径

        //addResourceLocations是图片存放的真实路径

        registry.addResourceHandler("/icon/**").addResourceLocations("file:"+ ImgPathUtils.getUploadImgBasePath());
        super.addResourceHandlers(registry);
    }

}
