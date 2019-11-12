package mnd.ftp;

import java.io.File;

import org.apache.ftpserver.FtpServer;
import org.apache.ftpserver.FtpServerFactory;
import org.apache.ftpserver.ftplet.FtpException;
import org.apache.ftpserver.ftplet.UserManager;
import org.apache.ftpserver.listener.ListenerFactory;
import org.apache.ftpserver.usermanager.PropertiesUserManagerFactory;
import org.apache.ftpserver.usermanager.impl.BaseUser;
import org.junit.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class ConnectionServerTests_1_start {

	public static void main(String[] args) throws FtpException {
		PropertiesUserManagerFactory userManagerFactory = new PropertiesUserManagerFactory();
		UserManager userManeger = userManagerFactory.createUserManager();

		BaseUser user1 = new BaseUser();
		user1.setName("user1");
		user1.setPassword("user1");
		user1.setHomeDirectory("C:/Users/kimhj/Documents/FTPTest/user1");
		userManeger.save(user1);

		BaseUser user2 = new BaseUser();
		user2.setName("user2");
		user2.setPassword("user2");
		user2.setHomeDirectory("C:/Users/kimhj/Documents/FTPTest/user2");
		userManeger.save(user2);

		ListenerFactory factory = new ListenerFactory();
		factory.setPort(2221);

		FtpServerFactory serverFactory = new FtpServerFactory();
		serverFactory.setUserManager(userManeger);
		serverFactory.addListener("default", factory.createListener());

		FtpServer server = serverFactory.createServer();
		server.start();
	}

	@Test
	public void findDirectory() {
		File file = new File("C:/Users/kimhj/Downloads");
		if(file.exists()) {
			System.out.println("OK >>>>>>>>>> " + file.getName());
		}
	}

}
