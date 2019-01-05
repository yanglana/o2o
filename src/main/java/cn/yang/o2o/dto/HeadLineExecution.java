package cn.yang.o2o.dto;

import cn.yang.o2o.entity.HeadLine;
import cn.yang.o2o.enums.HeadLineStateEnum;

/**
 * @Description TODO
 * @Author yanglan
 * @Date 2018/12/29 14:41
 */
public class HeadLineExecution {
    private int state;
    private String stateInfo;

    private HeadLine headLine;

    public HeadLineExecution(HeadLineStateEnum headLineStateEnum) {
        this.state = headLineStateEnum.getState();
        this.stateInfo = headLineStateEnum.getStateInfo();
    }

    public HeadLineExecution(HeadLineStateEnum headLineStateEnum, HeadLine headLine) {
        this.state = headLineStateEnum.getState();
        this.stateInfo = headLineStateEnum.getStateInfo();
        this.headLine = headLine;
    }

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }

    public String getStateInfo() {
        return stateInfo;
    }

    public void setStateInfo(String stateInfo) {
        this.stateInfo = stateInfo;
    }

    public HeadLine getHeadLine() {
        return headLine;
    }

    public void setHeadLine(HeadLine headLine) {
        this.headLine = headLine;
    }
}
