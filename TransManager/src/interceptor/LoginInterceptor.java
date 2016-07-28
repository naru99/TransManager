package interceptor;

import javax.annotation.Priority;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.interceptor.AroundInvoke;
import javax.interceptor.Interceptor;
import javax.interceptor.InvocationContext;

import annotation.Login;
import beans.UserBean;

@Priority(Interceptor.Priority.LIBRARY_BEFORE)
@Interceptor
@Login
public class LoginInterceptor {


	@Inject
	private UserBean user;

	public LoginInterceptor() {

	}

	@AroundInvoke
	public Object intercept(InvocationContext ic) throws Exception {

		String clsName = ic.getMethod().getDeclaringClass().getName();

		System.out.println("LoginInterceptor : " + ic.getMethod().getName());

//		// ログイン処理は除外する
//		if(clsName.equals("beans.LoginBean")) {
//			return ic.proceed();
//		}

		System.out.print("★★ loginInterceptor");

		if(user.getUserName() == null) {
			System.out.println(" not login ★★");

			FacesContext ctx = FacesContext.getCurrentInstance();
			String contextPath = ctx.getExternalContext().getRequestContextPath();
			ctx.getExternalContext().redirect(contextPath + "/error.xhtml");
//			return null;
//			throw new NoLoginException();
//			return "error.xhtml";
		}else {
			System.out.println( user.getUserName() + " login ★★");
			return ic.proceed();
		}

		System.out.println("☆☆ loginInterceptor END ☆☆");
		return null;

	}

}
