package beans;

import java.io.Serializable;

import javax.enterprise.context.SessionScoped;

@SessionScoped
public class UserBean implements Serializable {

	private String userName;

	private String loginTime;

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getLoginTime() {
		return loginTime;
	}

	public void setLoginTime(String loginTime) {
		this.loginTime = loginTime;
	}

}
