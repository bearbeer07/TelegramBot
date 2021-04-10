import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.User;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardRow;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import java.util.ArrayList;

public class Bot extends TelegramLongPollingBot {
    ServiceRequest serviceRequest = new ServiceRequest();
    private long chat_id;
    ReplyKeyboardMarkup replyKeyboardMarkup = new ReplyKeyboardMarkup();

    @Override
   /* public void onUpdateReceived(Update update) {
        chat_id = update.getMessage().getChatId();
        SendMessage sendMessage = new SendMessage().setChatId(chat_id).setText(getMessage(update.getMessage().getText()));
        sendMessage.setText(input(update.getMessage().getText()));
        String text = update.getMessage().getText();
        sendMessage.setReplyMarkup(replyKeyboardMarkup);
        try {
            execute(sendMessage);
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }*/

    public void onUpdateReceived(Update update) {
        chat_id = update.getMessage().getChatId();

        SendMessage sendMessage = new SendMessage().setChatId(chat_id).setText(getMessage(update.getMessage().getText()));
      //  sendMessage.setText(input();
        sendMessage.setReplyMarkup(replyKeyboardMarkup);
        try {
            execute(sendMessage);
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }


    public String getMessage(String msg) {
        ArrayList<KeyboardRow> keyboard = new ArrayList<>();
        KeyboardRow keyboardFirstRow = new KeyboardRow();
        KeyboardRow keyboardSecondRow = new KeyboardRow();

        replyKeyboardMarkup.setSelective(true);
        replyKeyboardMarkup.setResizeKeyboard(true);
        replyKeyboardMarkup.setOneTimeKeyboard(false);

        if (msg.equalsIgnoreCase("/start")) {
            keyboard.clear();
            keyboardFirstRow.clear();
            keyboardFirstRow.add("Создание заявки");
            keyboardFirstRow.add("Узнать статус заявки");
            keyboardSecondRow.add("Отменить заявку");
            keyboardSecondRow.add("Связаться с поддержкой");
            keyboard.add(keyboardFirstRow);
            keyboard.add(keyboardSecondRow);
            replyKeyboardMarkup.setKeyboard(keyboard);
            return "Добро пожаловать в бота по приему сервисных заявок.\n" + "Выберете необходимое вам действие.";
        }
        return "Данная команда не поддерживается ботом.\n"+ "Повторите команду";
    }

    @Override
    public String getBotUsername() {
        return "@MyServiceRequestbot";
    }

    @Override
    public String getBotToken() {
        return "1684623169:AAFdBT_-aXOYdvI9fasGttSnyLtn53Lvo3o";
    }

    public String input(String msg) {
        if (msg.equalsIgnoreCase("Привет") || msg.equals("/start") ||
                msg.equalsIgnoreCase("Hello") || msg.equalsIgnoreCase("Hi")) {
            return "Добро пожаловать в бота по приему сервисных заявок.\n" + "Выберете необходимое вам действие.";
        }
        return msg;
    }
}
