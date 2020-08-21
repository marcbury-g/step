package com.example.step;

import com.google.inject.servlet.ServletModule;
import com.google.inject.Provides;
import javax.inject.Singleton;

class MyServletModule extends ServletModule {
    @Override protected void configureServlets() {
        bind(MyServlet.class).in(Singleton.class);
        serve("/*").with(MyServlet.class);
    }

    @Provides
    Database provideDatabase() {
        return new RealDatabase("real value");
    }
}