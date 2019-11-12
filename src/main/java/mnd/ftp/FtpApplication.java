package mnd.ftp;

import org.apache.ftpserver.FtpServer;
import org.apache.ftpserver.ftplet.FtpException;
import org.apache.ftpserver.ftplet.User;
import org.apache.ftpserver.ftplet.UserManager;
import org.apache.ftpserver.impl.DefaultFtpServer;
import org.apache.ftpserver.usermanager.impl.BaseUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class FtpApplication implements CommandLineRunner {

	@Autowired
	FtpServer ftpServer;

	public static void main(String[] args) {
		SpringApplication.run(FtpApplication.class, args);
	}

	@Override
	public void run(String... args) throws FtpException {
		ftpServer.start();

		String userName = "user1";
		UserManager userManager = ((DefaultFtpServer) ftpServer).getUserManager();

		// base user
		User user = userManager.getUserByName(userName);
		if("".equals(user)) {
			BaseUser baseUser = new BaseUser();
			baseUser.setName(userName);
			baseUser.setPassword(userName);
			baseUser.setHomeDirectory("C:/Users/kimhj/Documents/FTPTest/" + userName);
			userManager.save(baseUser);
		}
	}
}
