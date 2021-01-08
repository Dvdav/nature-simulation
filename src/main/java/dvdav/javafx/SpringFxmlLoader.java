package dvdav.javafx;

import javafx.fxml.FXMLLoader;
import org.springframework.context.ApplicationContext;
import org.springframework.core.io.Resource;

import java.io.IOException;

public class SpringFxmlLoader extends FXMLLoader {
    public SpringFxmlLoader(Resource location, ApplicationContext applicationContext) throws IOException {
        super(location.getURL());
        setControllerFactory(applicationContext::getBean);
    }
}
