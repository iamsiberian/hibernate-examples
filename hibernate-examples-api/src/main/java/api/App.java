package api;

import com.lineate.api.ApiConfiguration;
import com.lineate.api.core.DatabaseConfiguration;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Import;

import java.util.TimeZone;

import static java.lang.String.format;

@SpringBootApplication()
@Import({DatabaseConfiguration.class, ApiConfiguration.class})
public class App {
    private static final Logger LOGGER = LoggerFactory.getLogger(App.class);

    public static void main(final String[] args) {
        TimeZone.setDefault(TimeZone.getTimeZone("Etc/UTC"));
        try {
            SpringApplication.run(App.class, args);
        } catch (Exception ex) {
            LOGGER.error(format("Can not start API: %s", ex));
        }
    }
}
