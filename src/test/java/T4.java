import java.math.BigInteger;
import java.net.MalformedURLException;
import java.net.URL;
import java.sql.Timestamp;
import java.util.List;
import java.util.Map;

import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.rongcheng.erp.dao.ZB_InventoryDAO;
import com.rongcheng.erp.entity.ItemCommonInfo;
import com.rongcheng.erp.entity.LocationItemStock;
import com.rongcheng.erp.entity.StocklocationInfo;
import com.rongcheng.erp.entity.WarehouseInfo;

public class T4 {
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
	public void t11() {
//	    0,2,1,,0
	    
	    List<Map<String, Object>> list = 
	            dao.listItemCommonStockByKeywords(0, 5, new BigInteger("1"), "", new BigInteger("0"), false);
	    
	    for (int i = 0; i < list.size(); i++) {
	        System.out.println(list.get(i));
            
        }
	}
	
	@Test
	public void t12() {
	    
	    BigInteger a = new BigInteger("0");
	    
        System.out.println(a.equals(new BigInteger("0")));
        System.out.println();
	    
	}
	
	@Test
	public void t13() {
	    
	    List<WarehouseInfo> list = dao.listWarehouseInfoByKeywords(new BigInteger("1"), 1, "", false, 0, 5);
	    
	    for (int i = 0; i < list.size(); i++) {
            System.out.println(list.get(i));
        }
	    
	}
	
	@Test
	public void t14() {
	    
	    int row = dao.countWarehouseInfoByKeywords(new BigInteger("1"), 0, "", false);
	    
	    System.out.println(row);
	    
	}
	
	@Test
	public void t15() {
	    
	    Timestamp time = new Timestamp(System.currentTimeMillis());
	    
	    WarehouseInfo w = new WarehouseInfo(null, "2", "Wname", 
	            "Cname", "Ctel", "cMobile", new BigInteger("1"), 
	            "uAddres", 1, new BigInteger("1"), "note", 
	            new BigInteger("1"), new BigInteger("1"), 
	            true, time, time);
	    
	    System.out.println(dao.saveWarehouseInfo(w));
	    
	}
	
	@Test
	public void t16() {
	    
	    Timestamp time = new Timestamp(System.currentTimeMillis());
	    
	    WarehouseInfo w = new WarehouseInfo(new BigInteger("3"), "2", "Wname1", 
                "Cname2", "Ctel3", "cMobile4", new BigInteger("1"), 
                "uAddres5", 1, new BigInteger("1"), "note6", 
                new BigInteger("1"), new BigInteger("1"), 
                true, time, time);
        
        System.out.println(dao.modifyWarehouseInfo(w));
	    
	}
	
	@Test
	public void t17() {
	    
	    WarehouseInfo w = dao.getWarehouseInfoById(new BigInteger("3"), new BigInteger("1"));
	    
	    System.out.println(w);
	    
	}
	
	@Test
	public void t18() {
	    
	    System.out.println(dao.getLocationItemStockByItemCommonId(new BigInteger("3"), new BigInteger("1")));
	    
	}
	//-
	@Test
	public void t19() {
	    
	    System.out.println(dao.listStocklocationInfoByCode("1", new BigInteger("1"), 0, 5));
	    
	}
	
    @Test
    public void t20() {
        
        System.out.println(dao.countStocklocationInfoByCode("1", new BigInteger("1")));
        
    }
    
    @Test
    public void t21() {
        
        Timestamp time = new Timestamp(System.currentTimeMillis());
        
        StocklocationInfo s = new StocklocationInfo(null, new BigInteger("1"), "useCode", "name", 1, 
                new BigInteger("1"), new BigInteger("1"), "note", new BigInteger("1"), new BigInteger("1"), 
                true, time, time);
        
        System.out.println(dao.saveStocklocationInfoByCode(s));
        
    }
    
    @Test
    public void t22() {
        
        StocklocationInfo s = dao.getStocklocationInfoById(new BigInteger("42"), new BigInteger("1"));
        
        System.out.println(s);
        
    }
    
    @Test
    public void t23() {
        
        StocklocationInfo s = dao.getStocklocationInfoById(new BigInteger("52"), new BigInteger("1"));
        
        s.setName("aa");
        
        dao.modifyStocklocationInfoByCode(s);
        
    }
    
    @Test
    public void t24() {
        
        dao.removeStocklocationInfoById(new BigInteger("52"), new BigInteger("1"));
        
    }
	
    @Test
    public void t25() {
        
        System.out.println(dao.listItemByLocationId(new BigInteger("51"), new BigInteger("1")));
        
    }
    
    @Test
    public void t26() {
        
        List<ItemCommonInfo> list = dao.listItemByKeywords("", new BigInteger("1"), 0, 5);
        
        for (int i = 0; i < list.size(); i++) {
            System.out.println(list.get(i));
        }
        
    }
    
    @Test
    public void t27() {
        
        System.out.println(dao.countItemByKeywords("1", new BigInteger("1")));
        
    }
    
    @Test
    public void t28() {
        
        String pageVisited = "rongcheng-tech.com";
        
        URL url = null;
        try {
            url = new URL(pageVisited);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        
        // 获取主机名 
        String host = url.getHost();
        
        //主机名分段
        String str[] = host.split("\\.");
        
        for (int i = 0; i < str.length; i++) {
            
            System.out.println(str[i]);
            
        }
        
        //截取域名  http://www.baidu.com/gas9gfd89sd  变成  baidu.com
        String domain = str[0]+"."+str[1]+"."+str[2];
        
        System.out.println(domain);
    }
    
    
}
