package dev.nikhil.lld2Repo.Module0_singleton;

public class MessageBuilder {

//    private MessageType messageType;
    private String content;
    private String sender;
    private String recipient;
    private boolean isDelivered;
    private long timestamp;

    public MessageBuilder(String content, String sender, String recipient, boolean isDelivered, long timestamp) {
//        this.messageType = messageType;
        this.content = content;
        this.sender = sender;
        this.recipient = recipient;
        this.isDelivered = isDelivered;
        this.timestamp = timestamp;
    }

    public static class Builder{
//        private MessageType messageType;
        private String content;
        private String sender;
        private String recipient;
        private boolean isDelivered;
        private long timestamp;

//        public Builder setMessageType(MessageType messageType) {
//            this.messageType = messageType;
//            return this;
//        }

        public Builder setContent(String content) {
            this.content = content;
            return this;
        }

        public Builder setSender(String sender) {
            this.sender = sender;
            return this;
        }

        public Builder setRecipient(String recipient) {
            this.recipient = recipient;
            return this;
        }

        public Builder setDelivered(boolean delivered) {
            isDelivered = delivered;
            return this;
        }

        public Builder setTimestamp(long timestamp) {
            this.timestamp = timestamp;
            return this;
        }

        public MessageBuilder build(){
            MessageBuilder mb = new MessageBuilder(content, sender,recipient, isDelivered, timestamp);
            return mb;
        }
    }
}