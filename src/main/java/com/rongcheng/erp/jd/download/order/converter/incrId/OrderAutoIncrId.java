package com.rongcheng.erp.jd.download.order.converter.incrId;

import java.math.BigInteger;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.rongcheng.erp.dao.Jxb_OrderDAO;

@Service
public class OrderAutoIncrId {
	@Resource
	private Jxb_OrderDAO dao;
	private static BigInteger id;
	private final static BigInteger ONE = new BigInteger("1");

	@PostConstruct
	private void init() {
		id = dao.getMaxId();
		if(id == null){
			id = new BigInteger("0");
		}
	}

	public static BigInteger getNextId() {
		id = id.add(ONE);
		return id;
	}
}
