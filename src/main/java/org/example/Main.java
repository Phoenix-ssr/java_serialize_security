package org.example;

import com.sun.org.apache.xalan.internal.xsltc.DOM;
import com.sun.org.apache.xalan.internal.xsltc.TransletException;
import com.sun.org.apache.xalan.internal.xsltc.runtime.AbstractTranslet;
import com.sun.org.apache.xalan.internal.xsltc.trax.TemplatesImpl;
import com.sun.org.apache.xalan.internal.xsltc.trax.TransformerFactoryImpl;
import com.sun.org.apache.xml.internal.dtm.DTMAxisIterator;
import com.sun.org.apache.xml.internal.serializer.SerializationHandler;
import javassist.ClassClassPath;
import javassist.ClassPool;
import javassist.CtClass;
import javassist.CtConstructor;
import org.apache.commons.collections.functors.ChainedTransformer;
import org.apache.commons.collections.functors.ConstantTransformer;
import org.apache.commons.collections.functors.InvokerTransformer;

import java.io.*;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.net.InetAddress;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLStreamHandler;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;

import static jdk.nashorn.internal.objects.Global.print;

public class Main {
    public static void serialize(String filename, Object obj) throws Exception {
        // 生成字节输出流对象
        ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(filename));
        // 写入对象
        out.writeObject(obj);
        out.flush();
        out.close();
    }

    public static void unserialize(String filename) throws Exception {
        //读取字节流
        ObjectInputStream in = new ObjectInputStream(new FileInputStream(filename));
        //反序列化
        in.readObject();
        in.close();
    }



    public static void main(String[] args) throws Exception {
        /*DNSURL*/
        /*
        String URL = "http://v8svo4.dnslog.cn";
        serilize("URLDNS",DNSURL.getObject(URL));
        unserilize("URLDNS");
        */

        /*Transformer*/
        //Transformer_test.test();

        /*CC5*/
        //CC5.getobject();
        //serilize("CC5",CC5.getobject());
        //unserilize("CC5");

        /*xalan*/
        //CC3.getobject();
        //serialize("CC3",CC3.getobject());
        //unserialize("CC3");


}

}