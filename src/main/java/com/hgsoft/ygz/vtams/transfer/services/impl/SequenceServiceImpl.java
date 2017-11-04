package com.hgsoft.ygz.vtams.transfer.services.impl;

import com.hgsoft.ygz.vtams.transfer.dao.SequenceMapper;
import com.hgsoft.ygz.vtams.transfer.model.map.*;
import com.hgsoft.ygz.vtams.transfer.services.ISequenceService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;

/**
 * 顺序码获取
 *
 * @author 赖少涯
 * @version 1.0
 * @date 2017-10-19 14:19:34
 * @since 1.7
 */
@Service
public class SequenceServiceImpl implements ISequenceService {

    @Autowired
    private SequenceMapper sequenceMapper;

    private final Logger log = LoggerFactory.getLogger(SequenceServiceImpl.class);

    /**
     * 最大尝试次数
     */
    private final int maxTimes = 3;

    /**
     * 休眠时间，单位毫秒
     */
    private final int sleeMilliSeconds = 300;
    private ThreadLocal<Integer> maxAttemptTimesForHallSeq = new ThreadLocal<Integer>() {
        @Override
        protected Integer initialValue() {
            return maxTimes;
        }
    };

    private ThreadLocal<Integer> maxAttemptTimesForMobileServiceSeq = new ThreadLocal<Integer>() {
        @Override
        protected Integer initialValue() {
            return maxTimes;
        }
    };

    private ThreadLocal<Integer> maxAttemptTimesForTerminalSeq = new ThreadLocal<Integer>() {
        @Override
        protected Integer initialValue() {
            return maxTimes;
        }
    };

    private ThreadLocal<Integer> maxAttemptTimesForOnlineSeq = new ThreadLocal<Integer>() {
        @Override
        protected Integer initialValue() {
            return maxTimes;
        }
    };

    private ThreadLocal<Integer> maxAttemptTimesForCustomerSeq = new ThreadLocal<Integer>() {
        @Override
        protected Integer initialValue() {
            return maxTimes;
        }
    };

    private ThreadLocal<Integer> maxAttemptTimesForRefundSeq = new ThreadLocal<Integer>() {
        @Override
        protected Integer initialValue() {
            return maxTimes;
        }
    };

    private ThreadLocal<Integer> maxAttemptTimesForRechargeSeq = new ThreadLocal<Integer>() {
        @Override
        protected Integer initialValue() {
            return maxTimes;
        }
    };

    @Override
    @Transactional(rollbackFor = Exception.class)
    public String getHallSeq(final HallSeq hallSeq) {
        //有效尝试次数小于或等于0时跳出
        if (maxAttemptTimesForHallSeq.get() <= 0) {
            //为了应对在同一线程内，多次处理同种消息，这里必须重置
            maxAttemptTimesForHallSeq.set(maxTimes);
            log.error("获取服务网点顺序码时，尝试次数超过3次，获取失败");
            return null;
        }

        //尝试次数小于3，说明上一次失败，则休眠300ms后再次尝试，且尝试次数减一
        if (maxAttemptTimesForHallSeq.get() < maxTimes) {
            log.info("上次获取失败，休眠{}ms后，将再次尝试获取服务网点[agencyId={},cityCode={},districtCode={}]顺序码，当前剩余尝试次数：{}",
                    sleeMilliSeconds, hallSeq.getAgencyId(), hallSeq.getCityCode(), hallSeq.getDistrictCode(), maxAttemptTimesForHallSeq.get() - 1);
            try {
                Thread.sleep(sleeMilliSeconds);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        } else {
            log.info("开始尝试获取服务网点[agencyId={},cityCode={},districtCode={}]顺序码，当前剩余尝试次数：{}",
                    hallSeq.getAgencyId(), hallSeq.getCityCode(), hallSeq.getDistrictCode(), maxAttemptTimesForHallSeq.get() - 1);
        }
        maxAttemptTimesForHallSeq.set(maxAttemptTimesForHallSeq.get() - 1);

        //构造一个新的对象，之后插入数据库
        Date curDate = new Date();
        final HallSeq newInstance = new HallSeq();
        newInstance.setAgencyId(hallSeq.getAgencyId());
        newInstance.setCityCode(hallSeq.getCityCode());
        newInstance.setDistrictCode(hallSeq.getDistrictCode());
        newInstance.setStatus(1);
        newInstance.setCreationDate(curDate);
        newInstance.setLastUpdateDate(curDate);

        try {
            Integer seq = null;
            HallSeq lastHallSeq = sequenceMapper.getLastHallSeq(hallSeq);
            //如果是第一条记录，直接赋值保存，返回"0001"
            if (null == lastHallSeq) {
                newInstance.setSeq(1);
                sequenceMapper.saveHallSeq(newInstance);
                //为了应对在同一线程内，多次处理同种消息，这里必须重置
                maxAttemptTimesForHallSeq.set(maxTimes);
                return "0001";
            }
            //如果lastHallSeq存在，则取出seq+1后，尝试新增一条记录
            seq = lastHallSeq.getSeq() + 1;
            newInstance.setSeq(seq);
            sequenceMapper.saveHallSeq(newInstance);

            //返回4位序列码，不足则高位补0
            String seqStr = "0000" + seq;
            seqStr = seqStr.substring(seqStr.length() - 4);

            //为了应对在同一线程内，多次处理同种消息，这里必须重置
            maxAttemptTimesForHallSeq.set(maxTimes);
            return seqStr;
        } catch (Exception e) {
            e.printStackTrace();
            return getHallSeq(hallSeq);
        }
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public String getMobileServiceSeq(final MobileServiceSeq mobileServiceSeq) {
        //有效尝试次数小于或等于0时跳出
        if (maxAttemptTimesForMobileServiceSeq.get() <= 0) {
            //为了应对在同一线程内，多次处理同种消息，这里必须重置
            maxAttemptTimesForMobileServiceSeq.set(maxTimes);
            log.error("获取流动服务网点顺序码时，尝试次数超过3次，获取失败");
            return null;
        }

        //尝试次数小于3，说明上一次失败，则休眠300ms后再次尝试，且尝试次数减一
        if (maxAttemptTimesForMobileServiceSeq.get() < maxTimes) {
            log.info("上次获取失败，休眠{}ms后，将再次尝试获取流动服务网点[agencyId={}]顺序码，当前剩余尝试次数：{}",
                    sleeMilliSeconds, mobileServiceSeq.getAgencyId(), maxAttemptTimesForMobileServiceSeq.get() - 1);
            try {
                Thread.sleep(sleeMilliSeconds);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        } else {
            log.info("开始尝试获取流动服务网点[agencyId={}]顺序码，当前剩余尝试次数：{}",
                    mobileServiceSeq.getAgencyId(), maxAttemptTimesForMobileServiceSeq.get() - 1);
        }
        maxAttemptTimesForMobileServiceSeq.set(maxAttemptTimesForMobileServiceSeq.get() - 1);

        //构造一个新的对象，之后插入数据库
        Date curDate = new Date();
        final MobileServiceSeq newInstance = new MobileServiceSeq();
        newInstance.setAgencyId(mobileServiceSeq.getAgencyId());
        newInstance.setStatus(1);
        newInstance.setCreationDate(curDate);
        newInstance.setLastUpdateDate(curDate);

        try {
            Integer seq = null;
            MobileServiceSeq lastMobileServiceSeq = sequenceMapper.getLastMobileServiceSeq(mobileServiceSeq);
            //如果是第一条记录，直接赋值保存，返回"0001"
            if (null == lastMobileServiceSeq) {
                newInstance.setSeq(1);
                sequenceMapper.saveMobileServiceSeq(newInstance);
                //为了应对在同一线程内，多次处理同种消息，这里必须重置
                maxAttemptTimesForMobileServiceSeq.set(maxTimes);
                return "0001";
            }
            //如果lastHallSeq存在，则取出seq+1后，尝试新增一条记录
            seq = lastMobileServiceSeq.getSeq() + 1;
            newInstance.setSeq(seq);
            sequenceMapper.saveMobileServiceSeq(newInstance);

            //返回4位序列码，不足则高位补0
            String seqStr = "0000" + seq;
            seqStr = seqStr.substring(seqStr.length() - 4);

            //为了应对在同一线程内，多次处理同种消息，这里必须重置
            maxAttemptTimesForMobileServiceSeq.set(maxTimes);
            return seqStr;
        } catch (Exception e) {
            e.printStackTrace();
            return getMobileServiceSeq(mobileServiceSeq);
        }
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public String getTerminalSeq(final TerminalSeq terminalSeq) {
        //有效尝试次数小于或等于0时跳出
        if (maxAttemptTimesForTerminalSeq.get() <= 0) {
            //为了应对在同一线程内，多次处理同种消息，这里必须重置
            maxAttemptTimesForTerminalSeq.set(maxTimes);
            log.error("获取自助服务终端顺序码时，尝试次数超过3次，获取失败");
            return null;
        }

        //尝试次数小于3，说明上一次失败，则休眠300ms后再次尝试，且尝试次数减一
        if (maxAttemptTimesForTerminalSeq.get() < maxTimes) {
            log.info("上次获取失败，休眠{}ms后，将再次尝试获取自助服务终端[agencyId={},cityCode={},districtCode={}]顺序码，当前剩余尝试次数：{}",
                    sleeMilliSeconds, terminalSeq.getAgencyId(), terminalSeq.getCityCode(), terminalSeq.getDistrictCode(), maxAttemptTimesForTerminalSeq.get() - 1);
            try {
                Thread.sleep(sleeMilliSeconds);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        } else {
            log.info("开始尝试获取自助服务终端[agencyId={},cityCode={},districtCode={}]顺序码，当前剩余尝试次数：{}",
                    terminalSeq.getAgencyId(), terminalSeq.getCityCode(), terminalSeq.getDistrictCode(), maxAttemptTimesForTerminalSeq.get() - 1);
        }
        maxAttemptTimesForTerminalSeq.set(maxAttemptTimesForTerminalSeq.get() - 1);

        //构造一个新的对象，之后插入数据库
        Date curDate = new Date();
        final TerminalSeq newInstance = new TerminalSeq();
        newInstance.setAgencyId(terminalSeq.getAgencyId());
        newInstance.setCityCode(terminalSeq.getCityCode());
        newInstance.setDistrictCode(terminalSeq.getDistrictCode());
        newInstance.setStatus(1);
        newInstance.setCreationDate(curDate);
        newInstance.setLastUpdateDate(curDate);

        try {
            Integer seq = null;
            TerminalSeq lastTerminalSeq = sequenceMapper.getLastTerminalSeq(terminalSeq);
            //如果是第一条记录，直接赋值保存，返回"0001"
            if (null == lastTerminalSeq) {
                newInstance.setSeq(1);
                sequenceMapper.saveTerminalSeq(newInstance);
                //为了应对在同一线程内，多次处理同种消息，这里必须重置
                maxAttemptTimesForTerminalSeq.set(maxTimes);
                return "0001";
            }
            //如果lastHallSeq存在，则取出seq+1后，尝试新增一条记录
            seq = lastTerminalSeq.getSeq() + 1;
            newInstance.setSeq(seq);
            sequenceMapper.saveTerminalSeq(newInstance);

            //返回4位序列码，不足则高位补0
            String seqStr = "0000" + seq;
            seqStr = seqStr.substring(seqStr.length() - 4);

            //为了应对在同一线程内，多次处理同种消息，这里必须重置
            maxAttemptTimesForTerminalSeq.set(maxTimes);
            return seqStr;
        } catch (Exception e) {
            e.printStackTrace();
            return getTerminalSeq(terminalSeq);
        }
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public String getOnlineSeq(final OnlineSeq onlineSeq) {
        //有效尝试次数小于或等于0时跳出
        if (maxAttemptTimesForOnlineSeq.get() <= 0) {
            //为了应对在同一线程内，多次处理同种消息，这里必须重置
            maxAttemptTimesForOnlineSeq.set(maxTimes);
            log.error("获取线上服务渠道顺序码时，尝试次数超过3次，获取失败");
            return null;
        }

        //尝试次数小于3，说明上一次失败，则休眠300ms后再次尝试，且尝试次数减一
        if (maxAttemptTimesForOnlineSeq.get() < maxTimes) {
            log.info("上次获取失败，休眠{}ms后，将再次尝试获取线上服务渠道[agencyId={},channel={}]顺序码，当前剩余尝试次数：{}",
                    sleeMilliSeconds, onlineSeq.getAgencyId(), onlineSeq.getChannel(), maxAttemptTimesForOnlineSeq.get() - 1);
            try {
                Thread.sleep(300);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        } else {
            log.info("开始尝试获取线上服务渠道[agencyId={},channel={}]顺序码，当前剩余尝试次数：{}",
                    onlineSeq.getAgencyId(), onlineSeq.getChannel(), maxAttemptTimesForOnlineSeq.get() - 1);
        }
        maxAttemptTimesForOnlineSeq.set(maxAttemptTimesForOnlineSeq.get() - 1);

        //构造一个新的对象，之后插入数据库
        Date curDate = new Date();
        final OnlineSeq newInstance = new OnlineSeq();
        newInstance.setAgencyId(onlineSeq.getAgencyId());
        newInstance.setChannel(onlineSeq.getChannel());
        newInstance.setStatus(1);
        newInstance.setCreationDate(curDate);
        newInstance.setLastUpdateDate(curDate);

        try {
            Integer seq = null;
            OnlineSeq lastOnlineSeq = sequenceMapper.getLastOnlineSeq(onlineSeq);
            //如果是第一条记录，直接赋值保存，返回"0001"
            if (null == lastOnlineSeq) {
                newInstance.setSeq(1);
                sequenceMapper.saveOnlineSeq(newInstance);
                //为了应对在同一线程内，多次处理同种消息，这里必须重置
                maxAttemptTimesForOnlineSeq.set(maxTimes);
                return "01";
            }
            //如果lastHallSeq存在，则取出seq+1后，尝试新增一条记录
            seq = lastOnlineSeq.getSeq() + 1;
            newInstance.setSeq(seq);
            sequenceMapper.saveOnlineSeq(newInstance);

            //返回2位序列码，不足则高位补0
            String seqStr = "00" + seq;
            seqStr = seqStr.substring(seqStr.length() - 2);

            //为了应对在同一线程内，多次处理同种消息，这里必须重置
            maxAttemptTimesForOnlineSeq.set(maxTimes);
            return seqStr;
        } catch (Exception e) {
            e.printStackTrace();
            return getOnlineSeq(onlineSeq);
        }
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public String getCustomerSeq(final CustomerSeq customerSeq) {
        //有效尝试次数小于或等于0时跳出
        if (maxAttemptTimesForCustomerSeq.get() <= 0) {
            //为了应对在同一线程内，多次处理同种消息，这里必须重置
            maxAttemptTimesForCustomerSeq.set(maxTimes);
            log.error("获取客户信息顺序码时，尝试次数超过3次，获取失败");
            return null;
        }

        //尝试次数小于3，说明上一次失败，则休眠300ms后再次尝试，且尝试次数减一
        if (maxAttemptTimesForCustomerSeq.get() < maxTimes) {
            log.info("上次获取失败，休眠{}ms后，将再次尝试获取客户信息[issuserId={},yearMonthDay={}]顺序码，当前剩余尝试次数：{}",
                    sleeMilliSeconds, customerSeq.getIssuserId(), customerSeq.getYearMonthDay(), maxAttemptTimesForCustomerSeq.get() - 1);
            try {
                Thread.sleep(sleeMilliSeconds);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        } else {
            log.info("开始尝试获取客户信息[issuserId={},yearMonthDay={}]顺序码，当前剩余尝试次数：{}",
                    customerSeq.getIssuserId(), customerSeq.getYearMonthDay(), maxAttemptTimesForCustomerSeq.get() - 1);
        }
        maxAttemptTimesForCustomerSeq.set(maxAttemptTimesForCustomerSeq.get() - 1);

        //构造一个新的对象，之后插入数据库
        Date curDate = new Date();
        final CustomerSeq newInstance = new CustomerSeq();
        newInstance.setIssuserId(customerSeq.getIssuserId());
        newInstance.setYearMonthDay(customerSeq.getYearMonthDay());
        newInstance.setStatus(1);
        newInstance.setCreationDate(curDate);
        newInstance.setLastUpdateDate(curDate);

        try {
            Integer seq = null;
            CustomerSeq lastCustomerSeq = sequenceMapper.getLastCustomerSeq(customerSeq);
            //如果是第一条记录，直接赋值保存，返回"0001"
            if (null == lastCustomerSeq) {
                newInstance.setSeq(1);
                sequenceMapper.saveCustomerSeq(newInstance);
                //为了应对在同一线程内，多次处理同种消息，这里必须重置
                maxAttemptTimesForCustomerSeq.set(maxTimes);
                return "00001";
            }
            //如果lastHallSeq存在，则取出seq+1后，尝试新增一条记录
            seq = lastCustomerSeq.getSeq() + 1;
            newInstance.setSeq(seq);
            sequenceMapper.saveCustomerSeq(newInstance);

            //返回5位序列码，不足则高位补0
            String seqStr = "00000" + seq;
            seqStr = seqStr.substring(seqStr.length() - 5);

            //为了应对在同一线程内，多次处理同种消息，这里必须重置
            maxAttemptTimesForCustomerSeq.set(maxTimes);
            return seqStr;
        } catch (Exception e) {
            e.printStackTrace();
            return getCustomerSeq(customerSeq);
        }
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public String getRefundSeq(final RefundSeq refundSeq) {
        //有效尝试次数小于或等于0时跳出
        if (maxAttemptTimesForRefundSeq.get() <= 0) {
            //为了应对在同一线程内，多次处理同种消息，这里必须重置
            maxAttemptTimesForRefundSeq.set(maxTimes);
            log.error("获取退款信息顺序码时，尝试次数超过3次，获取失败");
            return null;
        }

        //尝试次数小于3，说明上一次失败，则休眠300ms后再次尝试，且尝试次数减一
        if (maxAttemptTimesForRefundSeq.get() < maxTimes) {
            log.info("上次获取失败，休眠{}ms后，将再次尝试获取退款信息[channelType={},pointCode={},dateStr={}]顺序码，当前剩余尝试次数：{}",
                    sleeMilliSeconds, refundSeq.getChannelType(), refundSeq.getPointCode(),
                    refundSeq.getDateStr(), maxAttemptTimesForRefundSeq.get() - 1);
            try {
                Thread.sleep(sleeMilliSeconds);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        } else {
            log.info("开始尝试获取退款信息[channelType={},pointCode={},dateStr={}]顺序码，当前剩余尝试次数：{}",
                    refundSeq.getChannelType(), refundSeq.getPointCode(),
                    refundSeq.getDateStr(), maxAttemptTimesForRefundSeq.get() - 1);
        }
        maxAttemptTimesForRefundSeq.set(maxAttemptTimesForRefundSeq.get() - 1);

        //构造一个新的对象，之后插入数据库
        Date curDate = new Date();
        final RefundSeq newInstance = new RefundSeq();
        newInstance.setChannelType(refundSeq.getChannelType());
        newInstance.setPointCode(refundSeq.getPointCode());
        newInstance.setDateStr(refundSeq.getDateStr());
        newInstance.setStatus(1);
        newInstance.setCreationDate(curDate);
        newInstance.setLastUpdateDate(curDate);

        try {
            Integer seq = null;
            CustomerSeq lastCustomerSeq = sequenceMapper.getLastRefundSeq(refundSeq);
            //如果是第一条记录，直接赋值保存，返回"01"
            if (null == lastCustomerSeq) {
                newInstance.setSeq(1);
                sequenceMapper.saveRefundSeq(newInstance);
                //为了应对在同一线程内，多次处理同种消息，这里必须重置
                maxAttemptTimesForRefundSeq.set(maxTimes);
                return "01";
            }
            //如果lastHallSeq存在，则取出seq+1后，尝试新增一条记录
            seq = lastCustomerSeq.getSeq() + 1;
            newInstance.setSeq(seq);
            sequenceMapper.saveRefundSeq(newInstance);

            //返回2位序列码，不足则高位补0
            String seqStr = "00" + seq;
            seqStr = seqStr.substring(seqStr.length() - 2);

            //为了应对在同一线程内，多次处理同种消息，这里必须重置
            maxAttemptTimesForRefundSeq.set(maxTimes);
            return seqStr;
        } catch (Exception e) {
            e.printStackTrace();
            return getRefundSeq(refundSeq);
        }
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public String getRechargeSeq(final RechargeSeq rechargeSeq) {
        //有效尝试次数小于或等于0时跳出
        if (maxAttemptTimesForRechargeSeq.get() <= 0) {
            //为了应对在同一线程内，多次处理同种消息，这里必须重置
            maxAttemptTimesForRechargeSeq.set(maxTimes);
            log.error("获取充值信息顺序码时，尝试次数超过3次，获取失败");
            return null;
        }

        //尝试次数小于3，说明上一次失败，则休眠300ms后再次尝试，且尝试次数减一
        if (maxAttemptTimesForRechargeSeq.get() < maxTimes) {
            log.info("上次获取失败，休眠{}ms后，将再次尝试获取充值信息[channelType={},pointCode={},dateStr={}]顺序码，当前剩余尝试次数：{}",
                    sleeMilliSeconds, rechargeSeq.getChannelType(), rechargeSeq.getPointCode(),
                    rechargeSeq.getDateStr(), maxAttemptTimesForRechargeSeq.get() - 1);
            try {
                Thread.sleep(sleeMilliSeconds);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        } else {
            log.info("开始尝试获取充值信息[channelType={},pointCode={},dateStr={}]顺序码，当前剩余尝试次数：{}",
                    rechargeSeq.getChannelType(), rechargeSeq.getPointCode(),
                    rechargeSeq.getDateStr(), maxAttemptTimesForRechargeSeq.get() - 1);
        }
        maxAttemptTimesForRechargeSeq.set(maxAttemptTimesForRechargeSeq.get() - 1);

        //构造一个新的对象，之后插入数据库
        Date curDate = new Date();
        final RechargeSeq newInstance = new RechargeSeq();
        newInstance.setChannelType(rechargeSeq.getChannelType());
        newInstance.setPointCode(rechargeSeq.getPointCode());
        newInstance.setDateStr(rechargeSeq.getDateStr());
        newInstance.setStatus(1);
        newInstance.setCreationDate(curDate);
        newInstance.setLastUpdateDate(curDate);

        try {
            Integer seq = null;
            RechargeSeq lastRechargeSeq = sequenceMapper.getLastRechargeSeq(rechargeSeq);
            //如果是第一条记录，直接赋值保存，返回"01"
            if (null == lastRechargeSeq) {
                newInstance.setSeq(1);
                sequenceMapper.saveRechargeSeq(newInstance);
                //为了应对在同一线程内，多次处理同种消息，这里必须重置
                maxAttemptTimesForRechargeSeq.set(maxTimes);
                return "01";
            }
            //如果lastHallSeq存在，则取出seq+1后，尝试新增一条记录
            seq = lastRechargeSeq.getSeq() + 1;
            newInstance.setSeq(seq);
            sequenceMapper.saveRechargeSeq(newInstance);

            //返回2位序列码，不足则高位补0
            String seqStr = "00" + seq;
            seqStr = seqStr.substring(seqStr.length() - 2);

            //为了应对在同一线程内，多次处理同种消息，这里必须重置
            maxAttemptTimesForRechargeSeq.set(maxTimes);
            return seqStr;
        } catch (Exception e) {
            e.printStackTrace();
            return getRechargeSeq(rechargeSeq);
        }
    }
}
