package com.comet;

import java.util.Random;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.Callable;
import java.util.concurrent.ConcurrentHashMap;

public class Producer implements Runnable {

    BlockingQueue<Message> queue;



    Random random=new Random(73);

    public Producer(BlockingQueue<Message> queue){
        this.queue=queue;
    }

    @Override
    public void run()  {

        while(true){
            try {
                Thread.sleep(20000);
                queue.offer(new Message(random.nextInt()+""));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

    }
}
