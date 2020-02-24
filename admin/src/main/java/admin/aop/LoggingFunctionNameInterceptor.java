package admin.aop;

import admin.common.FunctionNameAware;
import admin.constant.web.WebConst;
import lombok.extern.slf4j.Slf4j;
import lombok.val;
import org.slf4j.MDC;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 機能名をログに出力する
 */
@Slf4j
public class LoggingFunctionNameInterceptor extends BaseHandlerInterceptor {

    private static final String MDC_FUNCTION_NAME = WebConst.MDC_FUNCTION_NAME;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
            throws Exception {
        // コントローラーの動作前

        val fna = getBean(handler, FunctionNameAware.class);
        if (fna != null) {
            val functionName = fna.getFunctionName();
            MDC.put(MDC_FUNCTION_NAME, functionName);
        }

        return true;
    }
}
