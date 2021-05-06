package Model.Data;

import Model.Enums.MessageType;

public class DataForClientFromServer {

    private String message;
    private MessageType messageType;

    public DataForClientFromServer(String data, MessageType messageType){
        setMessage(data);
        setMessageType(messageType);
    }


    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public MessageType getMessageType() {
        return messageType;
    }

    public void setMessageType(MessageType messageType) {
        this.messageType = messageType;
    }
}
