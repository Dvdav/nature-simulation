package dvdav;

import javafx.application.Application;
import javafx.application.HostServices;
import javafx.application.Platform;
import javafx.stage.Stage;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ApplicationContextInitializer;
import org.springframework.context.ApplicationEvent;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.GenericApplicationContext;

public class JavaFxApplication extends Application {
    private ConfigurableApplicationContext context;

    @Override
    public void init() {
        ApplicationContextInitializer<GenericApplicationContext> initializer = applicationContext -> {
            applicationContext.registerBean(Application.class, () -> JavaFxApplication.this);
            applicationContext.registerBean(Parameters.class, this::getParameters);
            applicationContext.registerBean(HostServices.class, this::getHostServices);
        };

        String[] parameters = getParameters().getRaw().toArray(new String[0]);

        context = new SpringApplicationBuilder()
                .sources(NatureSimulation.class)
                .initializers(initializer)
                .run(parameters);
    }

    @Override
    public void start(Stage primaryStage) {
        context.publishEvent(new StageReadyEvent(primaryStage));
    }

    @Override
    public void stop() {
        context.stop();
        Platform.exit();
    }

    static class StageReadyEvent extends ApplicationEvent {
        public StageReadyEvent(Stage source) {
            super(source);
        }

        public Stage getStage() {
            return (Stage) getSource();
        }
    }
}
