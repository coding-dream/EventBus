package com.ruoxu.eventbus;

/**
 * Created by wangli on 16/12/23.
 */

/**
 * <p>
 * 该类是描述一个函数唯一性的，参数类型、tag两个条件
 * Subscription}, 并且在接到消息时调用所有订阅者对应的函数.
 */


public class EventType {

    EventType(String tag){
        this.tag = tag;
    }
    /**
     * 默认的tag
     */
    public static final String DEFAULT_TAG = "default_tag";

    public String tag = DEFAULT_TAG;



    //重写hashCode 和 equals 修复 mSubcriberMap.get(new EventType(tag));获取不到的问题
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((tag == null) ? 0 : tag.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        EventType other = (EventType) obj;

        if (tag == null) {
            if (other.tag != null)
                return false;
        } else if (!tag.equals(other.tag))
            return false;
        return true;
    }

}
