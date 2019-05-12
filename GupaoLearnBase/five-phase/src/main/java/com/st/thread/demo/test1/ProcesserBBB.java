/**
 * <p>Copyright (c) TongCheng 2019</p>
 */
package com.st.thread.demo.test1;

import java.util.concurrent.LinkedBlockingQueue;

/**
 * @Title: ProcesserBBB
 * @Description:
 * @Author zhujing
 * @Date 2019/5/9
 * @Version V1.0
 */
public class ProcesserBBB extends Thread implements IProcesser{

    private LinkedBlockingQueue<Request> queues = new LinkedBlockingQueue<>();

    private IProcesser processer;

    public ProcesserBBB(IProcesser processer){
        this.processer = processer;
    }

    @Override
    public void run() {
        while (true){
            try {
                Request request = queues.take();
                System.out.println("BBBBB-----> " + request.getName());
                processer.processRequest(request);
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
