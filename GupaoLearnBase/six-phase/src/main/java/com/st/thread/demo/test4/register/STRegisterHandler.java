/**
 * <p>Copyright (c) TongCheng 2019</p>
 */
package com.st.thread.demo.test4.register;

import com.st.thread.demo.test4.protocol.STProtocol;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

import java.io.File;
import java.lang.reflect.Method;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @Title: STRegisterHandler
 * @Description:
 * @Author zhujing
 * @Date 2019/6/19
 * @Version V1.0
 */
public class STRegisterHandler extends ChannelInboundHandlerAdapter {

    private List<String> classNameList = new ArrayList<>();

    private Map<String, Object> mapping = new ConcurrentHashMap<>();

    public STRegisterHandler() {
        scannerClass("com.st.thread.demo.test4.provider");
        regist();
    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        Object result = new Object();
        STProtocol protocol = (STProtocol) msg;
        if(mapping.containsKey(protocol.getClassName())){
            Object obj = mapping.get(protocol.getClassName());
            Method method = obj.getClass().getMethod(protocol.getMethodName(), protocol.getParamTypes());
            result = method.invoke(obj, protocol.getParams());
        }
        ctx.write(result);
        ctx.flush();
        ctx.close();
    }

    private void regist() {
        try {
            if( !classNameList.isEmpty() ){
                for (String className : classNameList) {
                    Class<?> clazz = Class.forName(className);
                    Class<?> clazzInterface = clazz.getInterfaces()[0];
                    mapping.put(clazzInterface.getName(), clazz.newInstance());
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void scannerClass(String packageName) {
        String packageNameStr = packageName.replaceAll("\\.", "/");
        URL url = this.getClass().getClassLoader().getResource(packageNameStr);
        File file = new File(url.getFile());
        for (File f : file.listFiles()) {
            if(f.isDirectory()){
                scannerClass(packageName + "." + f.getName());
            }else {
                classNameList.add(packageName + "." + f.getName().replaceAll(".class", "").trim());
            }
        }
    }

}
