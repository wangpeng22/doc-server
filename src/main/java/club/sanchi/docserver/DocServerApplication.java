package club.sanchi.docserver;

import club.sanchi.docserver.task.Dome2Task;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class DocServerApplication {

    public static void main(String[] args) throws InterruptedException {
        Dome2Task.add();
        SpringApplication.run(DocServerApplication.class, args);
    }
}
