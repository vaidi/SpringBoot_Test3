package com.cn.service.testDisruptor;

/**
 * @Auther: YUANEL
 * @Date: 2019/1/3 21:55
 * @Description:
 * 定义事件
 */
public class LongEvent {
    private long value;
    public long getValue() {
        return value;
    }
    public void setValue(long value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return "LongEvent{" +
                "value=" + value +
                '}';
    }
}
