package cache;

import static org.junit.Assert.*;

/**
 * 缓存工具测试
 * @author lkmc2
 * @date 2019/8/5 12:47
 */
public class CacheUtilsTest {

    public static void main(String[] args) {
        CacheUtils.put("name", "jack");
        CacheUtils.put("age", 18);


        String name = (String) CacheUtils.get("name");
        System.out.println("name = " + name);

        int age = (int) CacheUtils.get("age");
        System.out.println("age = " + age);

        String oldName = (String) CacheUtils.put("name", "andy");
        String newName = (String) CacheUtils.get("name");
        System.out.println("oldName = " + oldName + ", newName = " + newName);
    }

}