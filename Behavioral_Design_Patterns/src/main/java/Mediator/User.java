package Mediator;

public class User {

    protected String name;
    protected ChatRoomMediator chatRoomMediator;

    public User(String name, ChatRoomMediator chatRoomMediator) {
        this.name = name;
        this.chatRoomMediator = chatRoomMediator;
    }

    public String getName() {
        return name;
    }

    public void send(String mess) {
        chatRoomMediator.showMessage(this, mess);
    }
}
