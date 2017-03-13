package security.config;

import org.springframework.boot.autoconfigure.condition.ConditionalOnExpression;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

/**
 * Created by sangeet on 3/12/2017.
 */
@ConditionalOnExpression("${database.enabled:true}")
@EntityScan("security.entity*")
@EnableJpaRepositories("security.repository*")
@Configuration()
public class config {
}
