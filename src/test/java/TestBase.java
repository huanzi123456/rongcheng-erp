import com.rongcheng.erp.dao.ZB_InventoryDAO;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.core.LoggerContext;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.io.File;
import java.math.BigInteger;

/**
 * 测试基础类 使用说明：
 *
 * 1.请先复制本类，在新生成的测试类中使用
 * 2.在DAO层接口处，init方法中，更换需要测试的DAO
 * 3.日志对象中的参数为，当前测试类
 * 4.在@Test标注的测试方法中，使用logger.debug（dao.方法）直接测试
 * 5.生成的测试日志在logs文件夹下
 *
 * @author 赵滨
 */
public class TestBase {

    //日志对象
    private final static Logger logger = LogManager.getLogger(TestBase.class.getName());

    //DAO层接口
    private ZB_InventoryDAO dao;

    //初始化
    @Before
    public void init(){
        String[] config={"config/spring-mvc.xml", "config/spring-mybatis.xml", "config/mybatis-config.xml"};
        ApplicationContext context = new ClassPathXmlApplicationContext(config);
        dao=context.getBean("ZB_InventoryDAO", ZB_InventoryDAO.class);

        //配置日志路径
        loggerSetConfigLocation();
    }

    //配置日志路径
    public void loggerSetConfigLocation() {
        LoggerContext logContext = (LoggerContext) LogManager.getContext(false);
        File conFile = new File("src/main/resources/config/log4j2.xml");
        logContext.setConfigLocation(conFile.toURI());
        logContext.reconfigure();
    }

    @Test
    public void Demo() {
        logger.debug(dao.listItemCommonStockByKeywords(0, 5, new BigInteger("1"), "", new BigInteger("0"), false));
    }

}
