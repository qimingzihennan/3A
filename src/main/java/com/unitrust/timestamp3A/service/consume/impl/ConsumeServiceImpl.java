package com.unitrust.timestamp3A.service.consume.impl;

import java.util.*;

import com.unitrust.timestamp3A.common.util.Common;
import com.unitrust.timestamp3A.common.util.ResultBean;
import com.unitrust.timestamp3A.dao.order.OrderExtendFieldDao;
import com.unitrust.timestamp3A.redis.model.OrderExtVO;
import com.unitrust.timestamp3A.service.order.OrderService;
import com.unitrust.timestamp3A.vo.CusConsumeInventoryModel;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.unitrust.timestamp3A.common.interceptor.page.Page;
import com.unitrust.timestamp3A.common.util.DateUtil;
import com.unitrust.timestamp3A.dao.consume.ConsumeDao;
import com.unitrust.timestamp3A.model.consume.CusConsumeInventory;
import com.unitrust.timestamp3A.model.consume.CusConsumeInventoryVO;
import com.unitrust.timestamp3A.model.consume.CusConsumeLog;
import com.unitrust.timestamp3A.model.consume.CusConsumeLogVO;
import com.unitrust.timestamp3A.model.enterprise.PIN_SD;
import com.unitrust.timestamp3A.model.order.Order;
import com.unitrust.timestamp3A.redis.dao.JeditsSpringDao;
import com.unitrust.timestamp3A.redis.model.CCIVO;
import com.unitrust.timestamp3A.service.consume.ConsumeService;
import com.unitrust.timestamp3A.service.enterprise.EnterpriseService;

@Service(value = "consumeService")
public class ConsumeServiceImpl implements ConsumeService {

    private static final Logger logger = LoggerFactory.getLogger(ConsumeServiceImpl.class);

    @Autowired
    private ConsumeDao consumeDao;
    @Autowired
    private EnterpriseService enterpriseService;
    @Autowired
    private JeditsSpringDao cusConsumeInventoryJeditsSpringDao;

    @Autowired
    private OrderService orderService;
    @Autowired
    private OrderExtendFieldDao orderExtendFieldDao;

    @Override
    public List<CusConsumeInventoryVO> queryCusConsumeInventory(Page<CusConsumeInventory> page) {
        // TODO Auto-generated method stub
        return consumeDao.queryCusConsumeInventory(page);
    }

    @Override
    public void switchStatus(Map queryMap) {
        // TODO Auto-generated method stub
        consumeDao.switchStatus(queryMap);
        Integer changeStatus = (Integer) queryMap.get("changeStatus");
        CusConsumeInventory cci = consumeDao.findCusConsumeInventoryById(queryMap);
        cci.setStatus(changeStatus.toString());
        cusConsumeInventoryJeditsSpringDao.switchStatus(cci);
    }

    @Override
    public List<CusConsumeLogVO> queryCusConsumeLog(Page<CusConsumeLog> page) {
        // TODO Auto-generated method stub
        return consumeDao.queryCusConsumeLog(page);
    }

    @Override
    public List<CusConsumeLogVO> queryEnterpriseCusConsumeLog(Page<CusConsumeLog> page) {
        // TODO Auto-generated method stub
        return consumeDao.queryEnterpriseCusConsumeLog(page);
    }

    @Override
    public void saveCusConsumeInventory(CusConsumeInventory cci) {
        // TODO Auto-generated method stub
        consumeDao.saveCusConsumeInventory(cci);
    }

    @Override
    public CusConsumeInventory createCusConsumeInventory(Order order, String status) {
        // TODO Auto-generated method stub
        CusConsumeInventory cci = new CusConsumeInventory();
        cci.setBusinessName(order.getBusinessName()); // 业务模块名称
        cci.setBkey(order.getBkey()); // 业务模块key
        Integer cusId = order.getCusId();
        cci.setCusId(cusId); // 客户ID
        cci.setTotalNumber(order.getNumber()); // 总次数
        cci.setResidueNumber(order.getNumber()); // 剩余次数
        cci.setStartTime(order.getStartTime()); // 开始时间
        cci.setEndTime(order.getEndTime());// 结束时间
        cci.setPaidMode(order.getPaidMode()); // 计费模式 1次数、2天数、3次数+天数
        cci.setStatus(status); // 状态0可用、1不可用、2待使用
        cci.setOrderId(order.getId());// 订单id
        String orderType = order.getOrderType();
        cci.setOrderType(orderType); // 类型1个人2企业
        if (CusConsumeInventory.CusConsumeInventory_orderType_enterprise.equals(orderType)) {
            // Enterprise enterprise =
            // enterpriseService.getEnterpriseById(cusId);
            PIN_SD pinSD = enterpriseService.getPSByEnterpriseId(cusId);
            String PIN = pinSD.getPIN();
            String sdCode = pinSD.getSD();
            cci.setPIN(PIN);// 企业PIN码
            cci.setSdCode(sdCode);// 企业PIN码对应的sd码
        }

        return cci;
    }

    public void updateCusConsumeInventory(CusConsumeInventory cci) {
        // TODO Auto-generated method stub
        consumeDao.updateCusConsumeInventory(cci);
    }

    @Override
    public CusConsumeInventory createCusConsumeInventory(Map<String, String> cusConsumeInventoryRedis) {
        // TODO Auto-generated method stub
        CusConsumeInventory cci = new CusConsumeInventory();
        String id = cusConsumeInventoryRedis.get("id");
        cci.setId(Integer.valueOf(id));
        String status = cusConsumeInventoryRedis.get("status");
        cci.setStatus(status);
        String paidMode = cusConsumeInventoryRedis.get("paidMode");
        cci.setPaidMode(paidMode);

        String dateFormat = "yyyy-MM-dd";
        String startTime = cusConsumeInventoryRedis.get("startTime");
        Date sTime = new DateUtil(dateFormat).getDateByString(startTime);
        if (sTime != null) {
            cci.setStartTime(sTime);
        }
        String endTime = cusConsumeInventoryRedis.get("endTime");
        Date eTime = new DateUtil(dateFormat).getDateByString(endTime);
        if (eTime != null) {
            cci.setEndTime(eTime);
        }
        Double num = Double.valueOf(cusConsumeInventoryRedis.get("num"));
        cci.setResidueNumber(num.intValue());

        return cci;
    }

    @Transactional
    public void updateCusConsumeInventoryFromRedis() {
        List<Map<String, String>> result = cusConsumeInventoryJeditsSpringDao.getCopyCusConsumeInventory();
        for (Map<String, String> cusConsumeInventoryRedis : result) {
            CusConsumeInventory cci = this.createCusConsumeInventory(cusConsumeInventoryRedis);
            this.updateCusConsumeInventory(cci);
        }
    }

    @Transactional
    public void saveCusConsumeInventoryLogFromRedis() {
        Integer timeout = 5; // 获取Redis list中的数据 等待时间5s
        String result = "";
        do {
            // logger.info("获取数据，如果无数据将延迟等待" + timeout + "秒");
            result = cusConsumeInventoryJeditsSpringDao.getCusConsumeInventoryLogJSON(timeout);
            if (result != null) {
                JSONObject jo = new JSONObject(result);
                String id = jo.getString("id");
                String cusIdOrPIN = jo.getString("cusIdOrPIN");
                String operateTime = jo.getString("operateTime");
                String cusType = jo.getString("cusType");
                String dateFormat = "yyyy-MM-dd hh:mm:ss";
                Date oTime = new DateUtil(dateFormat).getDateByString(operateTime);

                CusConsumeLog ccl = new CusConsumeLog(Integer.valueOf(id), cusIdOrPIN, oTime, cusType);
                logger.info("结果为：" + "id:" + id + " ,cusIdOrPIN:" + cusIdOrPIN + " ,operateTime:" + operateTime
                        + " ,cusType:" + cusType);
                this.saveCusConsumeInventoryLog(ccl);
            }

        } while (result != null && !result.equals(""));
    }

    @Override
    public void saveCusConsumeInventoryLog(CusConsumeLog ccl) {
        // TODO Auto-generated method stub
        String cusType = ccl.getCusType();
        if (CusConsumeLog.CusConsumeLog_type_enterprise.equals(cusType)) {
            String cusIdOrPIN = ccl.getCusIdOrPIN();
            PIN_SD pin_sd = enterpriseService.findPSByPIN(cusIdOrPIN);
            Integer eterpriseid = pin_sd.getEnterpriseId();
            ccl.setCusIdOrPIN(eterpriseid.toString());
        }

        consumeDao.saveCusConsumeInventoryLog(ccl);
    }

    @Override
    public Boolean validateCusConsumeInventoryAviableIsExist(Map searchMap) {
        // TODO Auto-generated method stub

        CusConsumeInventory cci = consumeDao.findCusConsumeInventoryAvilableByCusIdAndBkey(searchMap);
        if (cci != null) {
            return true;
        } else {
            return false;
        }
    }

    @Override
    public void addCusConsumeInventory(CusConsumeInventory cci) {
        // TODO Auto-generated method stub
        consumeDao.addCusConsumeInventory(cci);
    }

    @Override
    public CusConsumeInventoryModel queryRedisData(CCIVO redis_key) {
        CusConsumeInventoryModel cusConsumeInventoryModel = new CusConsumeInventoryModel();
        String key = redis_key.getKey();
        String cusIdOrPIN = redis_key.getCusIdOrPIN();
        String type = redis_key.getType();
        String redisKey = key + "_" + cusIdOrPIN + "_" + type;
        Boolean bkeyExist = cusConsumeInventoryJeditsSpringDao.exists(redisKey);
        if (bkeyExist){
            Map<String, String> result = cusConsumeInventoryJeditsSpringDao.get(redis_key);

            String paidMode = result.get("paidMode");
            String sdCode = result.get("sdCode");
            String id = result.get("id");
            String status = result.get("status");
            String startTime = result.get("startTime");
            String endTime = result.get("endTime");
            String num = result.get("num");
            String content = result.get("content");
            cusConsumeInventoryModel.setPaidMode(paidMode);
            cusConsumeInventoryModel.setSdCode(sdCode==null?"":sdCode);
            cusConsumeInventoryModel.setId(Integer.valueOf(id));
            cusConsumeInventoryModel.setStatus(status);
            cusConsumeInventoryModel.setStartTime(startTime==null?"":startTime);
            cusConsumeInventoryModel.setEndTime(endTime==null?"":endTime);
            cusConsumeInventoryModel.setNum(num);
            cusConsumeInventoryModel.setContent(content==null?"":content);
        }

        return cusConsumeInventoryModel;
    }

    @Override
    public List<CusConsumeInventoryVO> queryEnterpriseCusConsumeInventory(Page<CusConsumeInventory> page) {


        return consumeDao.queryEnterpriseCusConsumeInventory(page);
    }

    @Override
    public ResultBean comboFixSynsAll(String bkey, String type) {
        ResultBean resultBean = new ResultBean();
        int number = 0;
        String orderType = null;
        List<CusConsumeInventoryModel> cusList = new ArrayList<CusConsumeInventoryModel>();
        Map searchMap = new HashMap<>();
        searchMap.put("bkey", bkey);
        if (!Common.isEmpty(type) && "person".equals(type)) {
            orderType = CusConsumeInventory.CusConsumeInventory_orderType_person; // 个人
            searchMap.put("orderType", orderType);
            cusList = consumeDao.findCusConsumeInventory(searchMap);
            number = this.sysComboPerson(cusList);
        } else if (!Common.isEmpty(type) && "enterprise".equals(type)) {
            orderType = CusConsumeInventory.CusConsumeInventory_orderType_enterprise; // 企业
            searchMap.put("orderType", orderType);
            cusList = consumeDao.findCusConsumeInventory(searchMap);
            number = this.sysComboEnterprise(cusList);
        }
        resultBean.putData("number",number);
        return resultBean;
    }

    /**
     * 同步企业消费清单数据
     *
     * @param cusList
     * @return
     */
    private int sysComboEnterprise(List<CusConsumeInventoryModel> cusList) {
        for (CusConsumeInventoryModel ccim : cusList) {
            Integer cusId = ccim.getCusId();
            PIN_SD pin_sd = enterpriseService.getPSByEnterpriseId(cusId);
            String pin = pin_sd.getPIN();
            String sdCode = pin_sd.getSD();
            String bKey = ccim.getBkey();
            StringBuilder redisKey = new StringBuilder();
            redisKey.append(bKey).append("_").append(pin).append("_").append("enterprise");
            String enterpriseRedisPIN = null;
            // 判断当前PIN所组成的redisKey在Redis中是否存在
            Boolean validate = cusConsumeInventoryJeditsSpringDao.exists(redisKey.toString());
            if (!validate) {
                // 不存在,则寻找此企业用户的历史PIN码,在redis中是否存在数据
                String oldRedisPINKey = this.findRedisByOtherPIN(cusId, bKey);
                if (Common.isEmpty(oldRedisPINKey)) {
                    //当前pin及历史pin码在redis中都不存在,则将当前企业用户的CusConsumeInventory数据同步至Redis中
                    Order newOrder =  orderService.findOrderById(ccim.getOrderId().toString());
                    CCIVO result = new CCIVO();
                    result.setContent(newOrder.getContent());
                    result.setStatus(ccim.getStatus());
                    result.setPaidMode(ccim.getPaidMode());
                    result.setStartTime(ccim.getStartTime());
                    result.setEndTime(ccim.getEndTime());
                    result.setId(ccim.getId().toString());
                    Integer orderId = newOrder.getId();
                    List<OrderExtVO> ext = orderExtendFieldDao.queryOrderExtVOListByOrderId(orderId);
                    result.setExt(ext);
                    result.setKey(ccim.getBkey());
                    result.setSdCode(sdCode);
                    result.setCusIdOrPIN(pin);
                    result.setType(CCIVO.CCIVO_type_enterprise);
                    if (ccim.getResidueNumber() == null || ccim.getResidueNumber() == 0) {
                        result.setNum(0.0);
                    } else {
                        Double num = ccim.getResidueNumber().doubleValue();
                        result.setNum(num);
                    }
                    cusConsumeInventoryJeditsSpringDao.save(result);
                } else {
                    //历史pin码在redis中存在,将进行替换redis 键值操作
                    Map<String, String> result = cusConsumeInventoryJeditsSpringDao.get(oldRedisPINKey);
                    cusConsumeInventoryJeditsSpringDao.save(result, redisKey.toString());
                    cusConsumeInventoryJeditsSpringDao.delete(oldRedisPINKey);
                }
            }

        }


        return cusList.size();
    }

    /**
     * 查找旧pin redis数据
     *
     * @param cusId
     * @param bKey
     * @return
     */
    private String findRedisByOtherPIN(Integer cusId, String bKey) {
        String oldRedisPIN = null;
        List<PIN_SD> pin_sdList = enterpriseService.findAllPSPINbyId(cusId);
        for (PIN_SD pin_sd : pin_sdList) {
            String pin = pin_sd.getPIN();
            StringBuilder redisKey = new StringBuilder();
            redisKey.append(bKey).append("_").append(pin).append("_").append("enterprise");
            Boolean validate = cusConsumeInventoryJeditsSpringDao.exists(redisKey.toString());
            if (validate) {
                return redisKey.toString();
            }
        }
        return oldRedisPIN;
    }

    private int sysComboPerson(List<CusConsumeInventoryModel> cusList) {
        for (CusConsumeInventoryModel ccim : cusList) {
            Integer cusId = ccim.getCusId();
            String bKey = ccim.getBkey();
            StringBuilder redisKey =new StringBuilder();
            redisKey.append(bKey).append("_").append(cusId.toString()).append("_").append("person");
            Boolean validate = cusConsumeInventoryJeditsSpringDao.exists(redisKey.toString());
            // 判断当前PIN所组成的redisKey在Redis中是否存在
            if (!validate){
               //不存在,需要根据则将当前用户的CusConsumeInventory数据同步至Redis中
                Order newOrder =  orderService.findOrderById(ccim.getOrderId().toString());
                CCIVO result = new CCIVO();
                result.setContent(newOrder.getContent());
                result.setStatus(ccim.getStatus());
                result.setPaidMode(ccim.getPaidMode());
                result.setStartTime(ccim.getStartTime());
                result.setEndTime(ccim.getEndTime());
                result.setId(ccim.getId().toString());
                Integer orderId = newOrder.getId();
                List<OrderExtVO> ext = orderExtendFieldDao.queryOrderExtVOListByOrderId(orderId);
                result.setExt(ext);
                result.setKey(ccim.getBkey());
                result.setSdCode("");
                result.setCusIdOrPIN(cusId.toString());
                result.setType(CCIVO.CCIVO_type_person);
                if (ccim.getResidueNumber() == null || ccim.getResidueNumber() == 0) {
                    result.setNum(0.0);
                } else {
                    Double num = ccim.getResidueNumber().doubleValue();
                    result.setNum(num);
                }
                cusConsumeInventoryJeditsSpringDao.save(result);
            }

        }
        return cusList.size();
    }


    @Override
    public ResultBean comboFixSynsOne(String bkey, String type, String cusId) {
        ResultBean resultBean = new ResultBean();
        int number = 0;
        String orderType = null;
        ArrayList<CusConsumeInventoryModel> cusList = new ArrayList<CusConsumeInventoryModel>();
        Map searchMap = new HashMap<>();
        searchMap.put("Bkey", bkey);
        searchMap.put("cusId",cusId);
        CusConsumeInventory cusConsumeInventory=null;
        if (!Common.isEmpty(type) && "person".equals(type)) {
            orderType = CusConsumeInventory.CusConsumeInventory_orderType_person; // 个人
            searchMap.put("orderType", orderType);
            cusConsumeInventory = consumeDao.findCusConsumeInventoryAvilableByCusIdAndBkey(searchMap);
            CusConsumeInventoryModel ccim = this.coverCusConsumeInventoryToCusConsumeInventoryModel(cusConsumeInventory);
            cusList.add(ccim);
            number = this.sysComboPerson(cusList);
        } else if (!Common.isEmpty(type) && "enterprise".equals(type)) {
            orderType = CusConsumeInventory.CusConsumeInventory_orderType_enterprise; // 企业
            searchMap.put("orderType", orderType);
            cusConsumeInventory = consumeDao.findCusConsumeInventoryAvilableByCusIdAndBkey(searchMap);
            CusConsumeInventoryModel ccim = this.coverCusConsumeInventoryToCusConsumeInventoryModel(cusConsumeInventory);
            cusList.add(ccim);
            number = this.sysComboEnterprise(cusList);
        }

        resultBean.putData("number",number);
        return resultBean;
    }

    private CusConsumeInventoryModel coverCusConsumeInventoryToCusConsumeInventoryModel(CusConsumeInventory cusConsumeInventory) {
        CusConsumeInventoryModel ccim = new  CusConsumeInventoryModel();
        ccim.setCusId(cusConsumeInventory.getCusId());
        ccim.setBkey(cusConsumeInventory.getBkey());
        ccim.setStatus(cusConsumeInventory.getStatus());
        ccim.setPaidMode(cusConsumeInventory.getPaidMode());
        Date sTime = cusConsumeInventory.getStartTime();
        if (sTime != null){
            String startTime = new DateUtil("yyyy-MM-dd hh:mm:ss").getDateStringByDate(sTime);
            ccim.setStartTime(startTime);
        }
        Date eTime = cusConsumeInventory.getEndTime();
        if (eTime != null){
            String endTime = new DateUtil("yyyy-MM-dd hh:mm:ss").getDateStringByDate(eTime);
            ccim.setStartTime(endTime);
        }
        ccim.setId(cusConsumeInventory.getOrderId());
        ccim.setBkey(cusConsumeInventory.getBkey());
        ccim.setSdCode(cusConsumeInventory.getSdCode());
        ccim.setResidueNumber(cusConsumeInventory.getResidueNumber());
        return ccim;
    }
}
