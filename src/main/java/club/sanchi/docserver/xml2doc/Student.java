package club.sanchi.docserver.xml2doc;

public class Student {
    private String name;
    private String sex;

    private Integer imgIndex;
    private String imgBase64;
    private Integer imgWidth;
    private Integer imgHeight;

    public Student() {

    }

    public Student(String name, String sex, Integer imgIndex, String imgBase64, Integer imgWidth, Integer imgHeight) {
        this.name = name;
        this.sex = sex;
        this.imgIndex = imgIndex;
        this.imgBase64 = imgBase64;
        this.imgWidth = imgWidth;
        this.imgHeight = imgHeight;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public Integer getImgIndex() {
        return imgIndex;
    }

    public void setImgIndex(Integer imgIndex) {
        this.imgIndex = imgIndex;
    }

    public String getImgBase64() {
        return imgBase64;
    }

    public void setImgBase64(String imgBase64) {
        this.imgBase64 = imgBase64;
    }

    public Integer getImgWidth() {
        return imgWidth;
    }

    public void setImgWidth(Integer imgWidth) {
        this.imgWidth = imgWidth;
    }

    public Integer getImgHeight() {
        return imgHeight;
    }

    public void setImgHeight(Integer imgHeight) {
        this.imgHeight = imgHeight;
    }
}
