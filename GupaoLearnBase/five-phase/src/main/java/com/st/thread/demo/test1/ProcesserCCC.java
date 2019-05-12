/**
 * <p>Copyright (c) TongCheng 2019</p>
 */
package com.st.thread.demo.test1;

import java.util.concurrent.LinkedBlockingQueue;

/**
 * @Title: ProcesserCCC
 * @Description:
 * @Author zhujing
 * @Date 2019/5/9
 * @Version V1.0
 */
public class ProcesserCCC extends Thread implements IProcesser{

    private LinkedBlockingQueue<Request> queues = new LinkedBlockingQueue<>();

    @Override
    public void run() {
        while (true) {
            try {
                Request request = queues.take();
                System.out.println("CCCCC-----> " + request.getName());
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void processRequest(Request request) {
        queues.add(request);
    }
}
