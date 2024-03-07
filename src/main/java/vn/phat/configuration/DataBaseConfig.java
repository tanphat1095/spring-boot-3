package vn.phat.configuration;

import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import vn.phat.repositories.impl.BaseMasterRepositoryImpl;

@EnableJpaRepositories(basePackages = "vn.phat.*", repositoryBaseClass = BaseMasterRepositoryImpl.class)
@Configuration
@EntityScan("vn.phat.*")
@EnableTransactionManagement
public class DataBaseConfig {
}
