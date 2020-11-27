package com.hexenwerk.tutorial.javafx.crm;

import com.hexenwerk.tutorial.javafx.crm.client.StageManager;
import com.hexenwerk.tutorial.javafx.crm.client.mvvc.login.LoginPaneVC;
import com.hexenwerk.tutorial.javafx.crm.client.mvvc.main.center.PersonListMV;
import com.hexenwerk.tutorial.javafx.crm.service.PersonService;
import com.hexenwerk.tutorial.javafx.crm.service.PersonServiceImpl;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.stage.Stage;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ApplicationContextInitializer;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.GenericApplicationContext;

import java.util.Locale;

/**
 * JavaFX Application:
 * * initializes Spring Boot application context inside init() method
 * * send and event once the initialization is done. This event is send from start() method and contains javafx.Stage. Any listener can catch the event, get
 * the stage and start building JavaFx UI
 */
public class JavaFxRunner extends Application {

    private static Locale LOCALE = Locale.ENGLISH;

    private ConfigurableApplicationContext springAppContext;

    // 2. init SpringBoot application context
    @Override
    public void init() throws Exception {

        ApplicationContextInitializer<GenericApplicationContext> initializer =
                context ->
                {
                    context.registerBean(Application.class, () -> JavaFxRunner.this); // register this class as a Spring bean
                    context.registerBean(Parameters.class, this::getParameters); // for demonstration, not really needed
                };

        // 3. initialize Spring application and run it. In the next step:
        // *  all @SpringBootApplication and @Configuration classes are going to be initialized
        // *  all @Bean classes are going to be initialized. Both inside our application and libraries (most importantly @Component SpringFxWeaver)
        this.springAppContext = new SpringApplicationBuilder()
                .sources(CrmApplication.class) // register @SpringBootApplication class
                .initializers(initializer)
                .run(getParameters().getRaw().toArray(new String[0])); // run with application parameters (from command line args of JavaFx parameters)
    }

    // 4. run JavaFx app
    @Override
    public void start(Stage primaryStage) {
        StageManager stageManager = springAppContext.getBean(StageManager.class);
        stageManager.setPrimaryStage(primaryStage);
        stageManager.displayScene(LoginPaneVC.class, ResourceBundleUtil.getValue("loginPane.title"));

        PersonService personService = springAppContext.getBean(PersonServiceImpl.class);
        PersonListMV personListModelView = springAppContext.getBean(PersonListMV.class);

        // load model from DB
        personListModelView.setModel(personService.findAll());
    }

    @Override
    public void stop() {
        this.springAppContext.close();
        Platform.exit();
    }

    public static void setLOCALE(Locale locale) {
        LOCALE = locale;
    }

    public static Locale getLOCALE() {
        return LOCALE;
    }
}
