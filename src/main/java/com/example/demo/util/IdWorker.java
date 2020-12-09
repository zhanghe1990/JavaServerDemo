package com.example.demo.util;

public class IdWorker {
    private final static long twepoch = 1288834974657L;

    private final static long workerIdBits = 3L;

    private final static long datacenterIdBits = 2L;

    private final static long sequenceBits = 6L;

    private final static long workerIdShift = sequenceBits;

    private final static long datacenterIdShift = sequenceBits + workerIdBits;

    private final static long timestampLeftShift = sequenceBits + workerIdBits + datacenterIdBits;

    private final static long sequenceMask = -1L ^ (-1L << sequenceBits);

    private static long workerId = 0L;

    private static long datacenterId = 0L;

    private static long sequence = 0L;

    private static long lastTimestamp = -1L;

    public static void setWorkerId(long workId1) {
        workerId = workId1;
    }

    public static void setDatacenterId(long datacenterId1) {
        workerId = datacenterId1;
    }

    public static String generateId() {
        long id = nextId();
        return String.valueOf(id);
    }

    public synchronized static long nextId() {
        long timestamp = timeGen();
        if (timestamp < lastTimestamp) {
            throw new RuntimeException(String.format(
                    "Clock moved backwards.  Refusing to generate id for %d milliseconds", lastTimestamp - timestamp));
        }
        if (lastTimestamp == timestamp) {
            sequence = (sequence + 1) & sequenceMask;
            if (sequence == 0) {
                timestamp = tilNextMillis(lastTimestamp);
            }
        } else {
            sequence = 0L;
        }

        lastTimestamp = timestamp;

        return ((timestamp - twepoch) << timestampLeftShift) | (datacenterId << datacenterIdShift)
                | (workerId << workerIdShift) | sequence;

    }

    protected static long tilNextMillis(long lastTimestamp) {
        long timestamp = timeGen();
        while (timestamp <= lastTimestamp) {
            timestamp = timeGen();
        }
        return timestamp;
    }

    protected static long timeGen() {
        return System.currentTimeMillis();
    }

    public static void main(String[] args) {
        System.out.println(nextId());
    }

}
