package Mediator;

public class Main {
    public static void main(String[] args) {
        ChatRoomMediator mediator = new ChatRoom();

        User john = new User("john", mediator);
        User ivan = new User("ivan", mediator);

        john.send("kuku");
        ivan.send("mumu");
    }
}
