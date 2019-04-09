package club.sanchi.docserver.task;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;
import org.springframework.scheduling.Trigger;
import org.springframework.scheduling.TriggerContext;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.SchedulingConfigurer;
import org.springframework.scheduling.config.ScheduledTaskRegistrar;
import org.springframework.scheduling.support.CronTrigger;
import org.springframework.stereotype.Component;

import java.util.Date;

/**
 * Created by wangpeng on 2019/4/1 17:06
 */
@Component
@Configuration
@EnableScheduling
@Scope("singleton")
public class DomeTask implements SchedulingConfigurer {


    @Override
    public void configureTasks(ScheduledTaskRegistrar scheduledTaskRegistrar) {

//        scheduledTaskRegistrar.addTriggerTask(new Runnable() {
//            @Override
//            public void run() {
//                System.out.println("开始执行...");
//            }
//        }, new Trigger() {
//            @Override
//            public Date nextExecutionTime(TriggerContext triggerContext) {
//                return new CronTrigger("0/20 * * * * ?").nextExecutionTime(triggerContext);
//            }
//        });
    }
}
