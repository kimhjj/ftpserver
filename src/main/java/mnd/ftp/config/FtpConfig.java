package mnd.ftp.config;

import javax.sql.DataSource;

import org.apache.ftpserver.FtpServer;
import org.apache.ftpserver.FtpServerFactory;
import org.apache.ftpserver.ftplet.UserManager;
import org.apache.ftpserver.listener.ListenerFactory;
import org.apache.ftpserver.usermanager.DbUserManagerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@Configuration
@PropertySource("classpath:sql.properties")
public class FtpConfig {

	@Autowired
	DataSource DataSource;

	@Value("${sql.user.insert}")
	private String INSERT_SQL;
	@Value("${sql.user.update}")
	private String UPDATE_SQL;
	@Value("${sql.user.delete}")
	private String DELETE_SQL;
	@Value("${sql.user.select}")
	private String SELECT_SQL;
	@Value("${sql.user.select.all}")
	private String SELECT_ALL_SQL;
	@Value("${sql.user.is.admin}")
	private String IS_ADMIN_SQL;
	@Value("${sql.user.authenticate}")
	private String AUTHENTICATE_SQL;

	@Bean
	public FtpServer ftpServer(DataSource dataSource) {
		// user manager factory
		DbUserManagerFactory userManagerFactory = new DbUserManagerFactory();
		userManagerFactory.setDataSource(dataSource);
		userManagerFactory.setSqlUserInsert(INSERT_SQL);
		userManagerFactory.setSqlUserUpdate(UPDATE_SQL);
		userManagerFactory.setSqlUserDelete(DELETE_SQL);
		userManagerFactory.setSqlUserSelect(SELECT_SQL);
		userManagerFactory.setSqlUserSelectAll(SELECT_ALL_SQL);
		userManagerFactory.setSqlUserAdmin(IS_ADMIN_SQL);
		userManagerFactory.setSqlUserAuthenticate(AUTHENTICATE_SQL);

		// user manager
		UserManager userManager = userManagerFactory.createUserManager();

		// listener
		ListenerFactory factory = new ListenerFactory();
		factory.setPort(2221);

		// server
		FtpServerFactory serverFactory = new FtpServerFactory();
		serverFactory.setUserManager(userManager);
		serverFactory.addListener("default", factory.createListener());

		FtpServer server = serverFactory.createServer();

		return server;
	}

}
