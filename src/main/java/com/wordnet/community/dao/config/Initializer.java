package com.wordnet.community.dao.config;

import org.springframework.session.web.context.AbstractHttpSessionApplicationInitializer;

/**
 * @author 003418
 */
public class Initializer extends AbstractHttpSessionApplicationInitializer {

    public Initializer() {
        super(HttpSessionConfig.class);
    }
}
