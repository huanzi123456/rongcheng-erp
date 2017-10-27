import com.rongcheng.erp.dao.ZB_InventoryDAO;
import com.rongcheng.erp.entity.ItemCommonInfo;
import com.rongcheng.erp.entity.StocklocationInfo;
import com.rongcheng.erp.entity.WarehouseInfo;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.math.BigInteger;
import java.net.MalformedURLException;
import java.net.URL;
import java.sql.Timestamp;
import java.util.List;
import java.util.Map;

public class T5 {
	private ZB_InventoryDAO dao;
	@Before
	public void init(){
		String[] conf={"conf/spring-mvc.xml",
				"conf/spring-mybatis.xml"};
		ApplicationContext context
			= new ClassPathXmlApplicationContext(conf);
		dao=context.getBean("ZB_InventoryDAO", ZB_InventoryDAO.class);
	}
	
	@Test
	public void t1() {
//	    0,2,1,,0
	    
	    List<Map<String, Object>> list = 
	            dao.listItemCommonSyncByKeywords(0, 5, new BigInteger("1"), "", 1, true);

	    for (int i = 0; i < list.size(); i++) {
	        System.out.println(list.get(i));
            
        }
	}

    @Test
    public void t2() {

        System.out.println(
                dao.countItemCommonSyncByKeywords(new BigInteger("1"), "", 0, false)
        );

    }


}
