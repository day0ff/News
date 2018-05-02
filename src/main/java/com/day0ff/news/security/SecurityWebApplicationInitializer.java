package com.day0ff.news.security;

import org.springframework.security.web.context.AbstractSecurityWebApplicationInitializer;
/**
 * The class SecurityWebApplicationInitializer map the springSecurityFilterChain by extending AbstractSecurityWebApplicationInitializer
 * springSecurityFilterChain is mapped to "/*"
 * springSecurityFilterChain uses the dispatch types of ERROR and REQUEST
 * The springSecurityFilterChain mapping is inserted before any servlet Filter mappings that have already been configured *
 */
public class SecurityWebApplicationInitializer extends AbstractSecurityWebApplicationInitializer {

}
