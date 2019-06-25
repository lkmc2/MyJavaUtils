package xml;

import com.sun.xml.internal.bind.marshaller.CharacterEscapeHandler;

import javax.xml.bind.JAXB;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import java.io.IOException;
import java.io.StringReader;
import java.io.StringWriter;
import java.io.Writer;

/**
 * xml 解析工具类
 * @author lkmc2
 */
public class XmlUtils {

    /**
     * 将 xml 字符串转换成对象的公共逻辑
     * @param xmlText xml字符串
     * @param clazz 返回值对象的类型
     * @return 对象
     */
    public static <T> T parseXmlStringToObj(String xmlText, Class<T> clazz) {
        return JAXB.unmarshal(new StringReader(xmlText.trim()), clazz);
    }

    /**
     * 将对象转换成 xml 字符串
     * @param obj 对象
     * @param clazz 对象的类型
     * @return xml 字符串
     */
    public static <T> String parseObjToXmlString(T obj, Class<T> clazz) {
        String result = "";

        try {
            JAXBContext jaxbContext = JAXBContext.newInstance(clazz);
            Marshaller marshaller = jaxbContext.createMarshaller();

            // 格式化输出
            marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
            // 编码格式
            marshaller.setProperty(Marshaller.JAXB_ENCODING, "UTF-8");
            // 去掉默认报文头
            marshaller.setProperty(Marshaller.JAXB_FRAGMENT, true);
            // 不进行转义字符的处理
            marshaller.setProperty(CharacterEscapeHandler.class.getName(), new CharacterEscapeHandler() {
                @Override
                public void escape(char[] ch, int start,int length, boolean isAttVal, Writer writer) throws IOException {
                    writer.write(ch, start, length);
                }
            });

            StringWriter writer = new StringWriter();
            // 将对象转换成 xml 字符串，存入 writer 中
            marshaller.marshal(obj, writer);

            result = writer.toString();
        } catch (JAXBException e) {
            e.printStackTrace();
        }

        return result;
    }

}
