package interceptor;

import javax.annotation.Priority;
import javax.faces.context.FacesContext;
import javax.interceptor.AroundInvoke;
import javax.interceptor.Interceptor;
import javax.interceptor.InvocationContext;

import annotation.Logged;

@Priority(Interceptor.Priority.LIBRARY_BEFORE)
@Interceptor
@Logged
public class LoggedInterceptor {

	public LoggedInterceptor() {

	}

	@AroundInvoke
	public Object logMethodEntry(InvocationContext ic) throws Exception {

		System.out.println("◆◆ Logged ◆◆");

		FacesContext ctx = FacesContext.getCurrentInstance();
		int level = Integer.parseInt(ctx.getExternalContext().getInitParameter("debug.level"));

		if(level == 0) {
			return ic.proceed();
		}

		Object[] params = ic.getParameters();

		// パラメータ表示テスト
		for (Object param : params) {
			System.out.println("@Logged : param = " + (String) param);
		}

		System.out.println("@@Logged Interceptor, before: " + ic.getMethod().getName() + " in class "
				+ ic.getMethod().getDeclaringClass().getName());
		Object obj = ic.proceed();

		// パラメータ表示テスト
		for (Object param : params) {
			System.out.println("@Logged : param = " + (String) param);
		}
		System.out.println("@@Logged Interceptor, after: " + ic.getMethod().getName() + " in class "
				+ ic.getMethod().getDeclaringClass().getName());
		return obj;
	}

}
