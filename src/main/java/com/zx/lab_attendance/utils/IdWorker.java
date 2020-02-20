package com.zx.lab_attendance.utils;


/**
 * @author zx
 * @version 1.0
 * @date 2020/2/10 0:31
 * @Description
 */
public class IdWorker {
    /** 开始时间截(2017-01-01)，单位毫秒 */
    private static final long START_TIMESTAMP = 1483228800000L;

    /** 机器 ID 所占的位数 */
    private static final long WORKER_ID_BITS = 5L;

    /** 数据标识 ID 所占的位数 */
    private static final long DATACENTER_ID_BITS = 5L;

    /** 支持的最大机器 ID，结果是 31: 0B11111 */
    private static final long MAX_WORKER_ID = -1L ^ (-1L << WORKER_ID_BITS);

    /** 支持的最大数据中心 ID，结果是 31: 0B11111 */
    private static final long MAX_DATACENTER_ID = -1L ^ (-1L << DATACENTER_ID_BITS);

    /** 序列在 ID 中占的位数 */
    private static final long SEQUENCE_BITS = 12L;

    /** 机器 ID 向左移 12 位 */
    private static final long WORKER_ID_SHIFT = SEQUENCE_BITS;

    /** 数据中心 ID 向左移 17 位(12+5) */
    private static final long DATACENTER_ID_SHIFT = SEQUENCE_BITS + WORKER_ID_BITS;

    /** 时间截向左移 22 位(5+5+12) */
    private static final long TIMESTAMP_LEFT_SHIFT = SEQUENCE_BITS + WORKER_ID_BITS + DATACENTER_ID_BITS;

    /** 生成序列的掩码，这里为 4095(0B111111111111=0xFFF=4095) */
    private static final long SEQUENCE_MASK = -1L ^ (-1L << SEQUENCE_BITS);

    /** 工作机器 ID(0~31) */
    private long workerId;

    /** 数据中心 ID(0~31) */
    private long datacenterId;

    /** 毫秒内序列(0~4095) */
    private long sequence = 0L;

    /** 上次生成 ID 的时间截 */
    private long lastTimestamp = -1L;

    /**
     * 创建 ID 生成器的方式一: 使用工作机器的序号，范围是 [0, 1023]，优点是方便给机器编号
     *
     * @param workerId 工作机器 ID
     */
    public IdWorker(long workerId) {
        long maxMachineId = (MAX_DATACENTER_ID +1) * (MAX_WORKER_ID +1) - 1; // 1023

        if (workerId < 0 || workerId > maxMachineId) {
            throw new IllegalArgumentException(String.format("Worker ID can't be greater than %d or less than 0", maxMachineId));
        }

        this.datacenterId = (workerId >> WORKER_ID_BITS) & MAX_DATACENTER_ID;
        this.workerId = workerId & MAX_WORKER_ID;
    }

    /**
     * 创建 ID 生成器的方式二: 使用工作机器 ID 和数据中心 ID，优点是方便分数据中心管理
     *
     * @param datacenterId 数据中心 ID (0~31)
     * @param workerId     工作机器 ID (0~31)
     */
    public IdWorker(long datacenterId, long workerId) {
        if (workerId > MAX_WORKER_ID || workerId < 0) {
            throw new IllegalArgumentException(String.format("Worker ID can't be greater than %d or less than 0", MAX_WORKER_ID));
        }
        if (datacenterId > MAX_DATACENTER_ID || datacenterId < 0) {
            throw new IllegalArgumentException(String.format("Datacenter ID can't be greater than %d or less than 0", MAX_DATACENTER_ID));
        }

        this.workerId = workerId;
        this.datacenterId = datacenterId;
    }

    /**
     * 获得下一个 ID(该方法是线程安全的)，同一机器同一时间可产生 4096 个 ID，70 年内不生成重复的 ID
     *
     * @return long 类型的 ID
     */
    public synchronized long nextId() {
        long timestamp = timeGen();

        // 如果当前时间小于上一次 ID 生成的时间戳，说明系统时钟回退过这个时候应当抛出异常
        if (timestamp < lastTimestamp) {
            throw new RuntimeException(String.format("Clock moved backwards. Refusing to generate id for %d milliseconds", lastTimestamp - timestamp));
        }

        // 如果是同一时间生成的，则进行毫秒内序列
        if (lastTimestamp == timestamp) {
            sequence = (sequence + 1) & SEQUENCE_MASK;

            // 毫秒内序列溢出
            if (sequence == 0) {
                // 阻塞到下一个毫秒，获得新的时间戳
                timestamp = tilNextMillis(lastTimestamp);
            }
        } else {
            sequence = 0L; // 时间戳改变，毫秒内序列重置
        }

        // 上次生成 ID 的时间截
        lastTimestamp = timestamp;

        // 移位并通过或运算拼到一起组成 64 位的 ID
        return ((timestamp - START_TIMESTAMP) << TIMESTAMP_LEFT_SHIFT)
                | (datacenterId << DATACENTER_ID_SHIFT)
                | (workerId << WORKER_ID_SHIFT)
                | sequence;
    }

    /**
     * 阻塞到下一个毫秒，直到获得新的时间戳
     *
     * @param lastTimestamp 上次生成 ID 的时间截
     * @return 当前时间戳(毫秒)
     */
    protected long tilNextMillis(long lastTimestamp) {
        long timestamp = timeGen();

        while (timestamp <= lastTimestamp) {
            timestamp = timeGen();
        }

        return timestamp;
    }

    /**
     * 返回当前时间，以毫秒为单位
     *
     * @return 当前时间(毫秒)
     */
    protected long timeGen() {
        return System.currentTimeMillis();
    }

//    public static String getUid(){
//        return String.valueOf(new IdWorker(0,0).nextId());
//    }

}
