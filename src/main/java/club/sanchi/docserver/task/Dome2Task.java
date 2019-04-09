package club.sanchi.docserver.task;

import java.util.Calendar;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

/**
 * Created by wangpeng on 2019/4/8 17:23
 */
public class Dome2Task {

    public static void add() {

        Calendar startDate = Calendar.getInstance();

        //设置开始执行的时间为 某年-某月-某月 00:00:00
        startDate.set(startDate.get(Calendar.YEAR), startDate.get(Calendar.MONTH), startDate.get(Calendar.DATE), 11, 36, 0);
        System.out.println(startDate.getTime());

        Timer t = new Timer();
        t.schedule(new TimerTask() {
            /**
             * The action to be performed by this timer task.
             */
            @Override
            public void run() {
                System.out.println("添加任务...");
                TaskJob.offer(new Job(3, "job 3", new Date(System.currentTimeMillis() + 120000)));
                TaskJob.offer(new Job(4, "job 4", new Date(System.currentTimeMillis() + 360000)));
            }
        }, startDate.getTime());
    }
}