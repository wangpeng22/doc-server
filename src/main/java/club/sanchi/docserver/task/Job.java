package club.sanchi.docserver.task;

import java.util.Date;

/**
 * Created by wangpeng on 2019/4/8 19:52
 */
public class Job {
    private Integer id;
    private String name;
    private Date date;
    public Job(){

    }

    public Job(Integer id, String name, Date date) {
        this.id = id;
        this.name = name;
        this.date = date;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
}
