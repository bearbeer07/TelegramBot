import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardRow;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import java.util.ArrayList;

public class Bot extends TelegramLongPollingBot {
    ServiceRequest serviceRequest = new ServiceRequest();
    private long chat_id;

    String lastmessage = "";
    ReplyKeyboardMarkup replyKeyboardMarkup = new ReplyKeyboardMarkup();

    @Override
    public void onUpdateReceived(Update update) {
        update.getUpdateId();

        SendMessage sendMessage = new SendMessage().setChatId(update.getMessage().getChatId());
        chat_id = update.getMessage().getChatId();
        sendMessage.setText(input(update.getMessage().getText()));


        String text = update.getMessage().getText();
        sendMessage.setReplyMarkup(replyKeyboardMarkup);
/*
        if (update.getMessage().getText().equalsIgnoreCase("/start")) {
            sendMessage.setText("Добро пожаловать в бота по приему сервисных заявок");*/
        try {
            execute(sendMessage);
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
        //  }
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
            return "Выберете необходимое вам действие";
        }
        return "Если возникли проблемы, воспользуйтесь /start";
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
            return "Добро пожаловать в бота по приему сервисных заявок";
        }
        return msg;
    }
}
