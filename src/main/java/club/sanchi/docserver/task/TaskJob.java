package club.sanchi.docserver.task;

import com.alibaba.fastjson.JSON;
import org.apache.poi.ss.formula.functions.T;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.*;
import java.util.concurrent.LinkedBlockingQueue;

/**
 * Created by wangpeng on 2019/4/8 19:11
 */
@Scope("singleton")
@Service
public class TaskJob {

    private static Logger logger = LoggerFactory.getLogger(TaskJob.class);


    // 升级任务队列
    private static Queue<Job> queue = new LinkedBlockingQueue<Job>();

    @PostConstruct
    public void init () throws InterruptedException {
        logger.info("初始化任务列表...");
        List<Job> jobs = new ArrayList<>();
        Job j1 = new Job(1, "job 1", new Date(System.currentTimeMillis() + 60000));
        jobs.add(j1);
        Job j2 = new Job(2, "job 2", new Date(System.currentTimeMillis() + 300000));
        jobs.add(j2);

        queue.addAll(jobs);
        logger.info("queue = {}", JSON.toJSONString(queue));

        execute();
    }
    public static void offer (Job job) {
        queue.offer(job);
    }

    public static Job poll() {
        return queue.poll();
    }

    public void execute (){

        Thread t = new Thread(new Runnable() {
            @Override
            public void run() {
                logger.info("任务开始...");
                while (true) {
                    final Job job = TaskJob.poll();
                    if ( null != job) {
                        Timer t = new Timer();
                        t.schedule(new TimerTask() {
                            /**
                             * The action to be performed by this timer task.
                             */
                            @Override
                            public void run() {
                                logger.info("Job = {}", JSON.toJSONString(job));
                            }
                        }, job.getDate());
                    }
                }
            }
        });
        t.start();
    }
}