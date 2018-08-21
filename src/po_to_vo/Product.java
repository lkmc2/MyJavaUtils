package po_to_vo;

import java.util.Date;

/**
 * @author lkmc2
 * @date 2018/8/21
 * @description 产品
 */

public class Product {
    private int id;
    private String name;
    private int age;
    private Date birthday;
    private int count;

    public Product() {
    }

    public Product(int id, String name, int age, Date birthday, int count) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.birthday = birthday;
        this.count = count;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    @Override
    public String toString() {
        return "Product{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", age=" + age +
                ", birthday=" + birthday +
                ", count=" + count +
                '}';
    }
}
