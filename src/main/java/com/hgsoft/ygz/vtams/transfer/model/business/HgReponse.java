package com.hgsoft.ygz.vtams.transfer.model.business;


import java.io.Serializable;

/**
 * 部中心反馈的结果
 *
 * @author 赖少涯
 * @version 1.0
 * @date 2017-11-04 07:48:34
 * @since 1.7
 */
public class HgReponse implements Serializable {

    private static final long serialVersionUID = 1L;

    private Integer code;

    private MsgContent msgContent;

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public MsgContent getMsgContent() {
        return msgContent;
    }

    public void setMsgContent(MsgContent msgContent) {
        this.msgContent = msgContent;
    }

    class MsgContent implements Serializable {

        private String info;
        private String receiveTime;
        private String result;
        private String reason;

        public MsgContent() {

        }

        public String getInfo() {
            return info;
        }

        public void setInfo(String info) {
            this.info = info;
        }

        public String getReceiveTime() {
            return receiveTime;
        }

        public void setReceiveTime(String receiveTime) {
            this.receiveTime = receiveTime;
        }

        public String getResult() {
            return result;
        }

        public void setResult(String result) {
            this.result = result;
        }

        public String getReason() {
            return reason;
        }

        public void setReason(String reason) {
            this.reason = reason;
        }

        @Override
        public String toString() {
            return "MsgContent{" +
                    "info='" + info + '\'' +
                    ", receiveTime='" + receiveTime + '\'' +
                    ", result='" + result + '\'' +
                    ", reason='" + reason + '\'' +
                    '}';
        }
    }

    @Override
    public String toString() {
        return "HgReponse{" +
                "code='" + code + '\'' +
                ", msgContent=" + msgContent +
                '}';
    }
}
