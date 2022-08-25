package Mediator;

import java.time.LocalDateTime;

public class ChatRoom implements ChatRoomMediator {

    @Override
    public void showMessage(User user, String message) {

        System.out.println(LocalDateTime.now() + " : " + user.getName() + " : " + message);
    }
}
