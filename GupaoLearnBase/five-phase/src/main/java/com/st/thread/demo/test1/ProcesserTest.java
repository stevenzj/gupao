/**
 * <p>Copyright (c) TongCheng 2019</p>
 */
package com.st.thread.demo.test1;

/**
 * @Title: ProcesserTest
 * @Description:
 * @Author zhujing
 * @Date 2019/5/9
 * @Version V1.0
 */
public class ProcesserTest {

    ProcesserAAA processerAAA;

    public ProcesserTest(){
        ProcesserCCC processerCCC = new ProcesserCCC();
        processerCCC.start();

        ProcesserBBB processerBBB = new ProcesserBBB(processerCCC);
        processerBBB.start();

        processerAAA = new ProcesserAAA(processerBBB);
        processerAAA.start();
    }

    public void doProcess(Request request){
        processerAAA.processRequest(request);
    }

    public static void main(String[] args) {
        Request request = new Request();
        request.setName("steven");
        new ProcesserTest().doProcess(request);
    }

}
