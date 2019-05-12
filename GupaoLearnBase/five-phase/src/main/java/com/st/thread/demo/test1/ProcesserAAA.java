/**
 * <p>Copyright (c) TongCheng 2019</p>
 */
package com.st.thread.demo.test1;

import java.util.concurrent.LinkedBlockingQueue;

/**
 * @Title: ProcesserAAA
 * @Description:
 * @Author zhujing
 * @Date 2019/5/9
 * @Version V1.0
 */
public class ProcesserAAA extends Thread implements IProcesser{

    private LinkedBlockingQueue<Request> queues = new LinkedBlockingQueue<>();

    private IProcesser processer;

    public ProcesserAAA(IProcesser processer) {
        this.processer = processer;
    }

    @Override
    public void processRequest(Request request) {
        queues.add(request);
    }

    @Override
    public void run() {
        while (true){
            try {
                Request request = queues.take();
                System.out.println("AAAAA -----> " + request.getName());
                processer.processRequest(request);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
