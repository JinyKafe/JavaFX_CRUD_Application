package com.codetreatise;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.stage.Stage;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ApplicationContextInitializer;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.GenericApplicationContext;

/**
 * JavaFX Application which purpose is to:
 * * initializes Spring Boot application context inside init() method
 * * send and event once the initialization is done. This event is send from start() method and contains javafx.Stage. Any listener can catch the event, get
 * the stage and start building JavaFx UI
 */
public class SpringbootJavaFxApplication extends Application
{

    private ConfigurableApplicationContext context;

    // 3. JavaFx application thread. Started in MainApp#main method
    @Override
    public void init() throws Exception
    {

        ApplicationContextInitializer<GenericApplicationContext> initializer =
                context ->
                {
                    context.registerBean(Application.class, () -> SpringbootJavaFxApplication.this); // register this class as a Spring bean
                    context.registerBean(Parameters.class, this::getParameters); // for demonstration, not really needed
                };

        // 4. initialize Spring application and run it. In the next step:
        // *  all @SpringBootApplication and @Configuration classes are going to be initialized
        // *  all @Bean classes are going to be initialized. Both inside our application and libraries (most importantly @Component SpringFxWeaver)
        this.context = new SpringApplicationBuilder()
                .sources(MainApp.class) // register @SpringBootApplication class
                .initializers(initializer)
                .run(getParameters().getRaw().toArray(new String[0])); // run with application parameters (from command line args of JavaFx parameters)
    }

    @Override
    public void start(Stage primaryStage) throws Exception
    {
        // 5. we are done with spring initialization in #init method. Now, throw Spring's ApplicationEvent which contains the primary stage. This stage
        // is meant to be used by any registered event listener to build JavaFX UI
        context.publishEvent(new StageReadyEvent(primaryStage));
    }

    @Override
    public void stop() throws Exception
    {
        this.context.close();
        Platform.exit();
    }
}
