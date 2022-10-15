import org.telegram.telegrambots.ApiContextInitializer;
import org.telegram.telegrambots.TelegramBotsApi;
import org.telegram.telegrambots.api.methods.send.SendMessage;
import org.telegram.telegrambots.api.objects.Message;
import org.telegram.telegrambots.api.objects.Update;
import org.telegram.telegrambots.api.objects.replykeyboard.ReplyKeyboardMarkup;
import org.telegram.telegrambots.api.objects.replykeyboard.buttons.KeyboardButton;
import org.telegram.telegrambots.api.objects.replykeyboard.buttons.KeyboardRow;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.exceptions.TelegramApiException;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class bot extends TelegramLongPollingBot {
    public static void main(String[] args) {
        ApiContextInitializer.init();
        TelegramBotsApi telegramBotsApi= new TelegramBotsApi();
        try{
            telegramBotsApi.registerBot(new bot());
        }catch (TelegramApiException e){
            e.printStackTrace();
        }
    }
    public void sendmsg (Message message, String text){
        SendMessage sendMessage = new SendMessage();
        sendMessage.enableMarkdown(true);
        sendMessage.setChatId(message.getChatId().toString());
        sendMessage.setReplyToMessageId(message.getMessageId());
        sendMessage.setText(text);
        try {
            setButton(sendMessage);
            sendMessage(sendMessage);
        } catch (TelegramApiException e) {
            e.printStackTrace();

        }
    }
    public void setButton(SendMessage message){
        ReplyKeyboardMarkup replyKeyboardMarkup =new ReplyKeyboardMarkup();
        message.setReplyMarkup(replyKeyboardMarkup);
        replyKeyboardMarkup.setSelective(true);
        replyKeyboardMarkup.setResizeKeyboard(true);
        replyKeyboardMarkup.setOneTimeKeyboard(false);
        List<KeyboardRow> list =new ArrayList<>();
        KeyboardRow keyboardButtons =new KeyboardRow();
        keyboardButtons.add(new KeyboardButton("/help"));
        keyboardButtons.add(new KeyboardButton("/weather"));
        keyboardButtons.add(new KeyboardButton("/WSUS"));
       list.add(keyboardButtons);
       replyKeyboardMarkup.setKeyboard(list);
    }
    @Override
    public void onUpdateReceived(Update update) {
        Model model =new Model();
        Message message = update.getMessage();
        if (message != null && message.hasText()) {
            switch (message.getText()) {
                case "/help":
                    sendmsg(message, "Чем могу помочь?");
                    break;
                case "/weather":
                    sendmsg(message, "Выберите город");
                    break;
                case "KGBISSVR":
                    sendmsg(message,"http://KGBISSVRWSUS001");
                    break;
                case "/WSUS":
                    sendmsg(message,"http://KGBISSVRWSUS001-ГО " + "\n" + "http://KGBISSVTRRWSUS001-TOKMOK " +"\n"+
                                         "http://KGBIКЕА0001-КАНТ");

                default:
                    try {
                        sendmsg(message,weather.getweather(message.getText(),model));
                    } catch (IOException e) {
                       sendmsg(message,"такой город не найден");
                    }
            }
        }
    }

    @Override
    public String getBotUsername() {
      return "FirtstAndGoodBot";
    }

    @Override
    public String getBotToken() {
        return "5410345252:AAEfEEe3dC26UAmgkWmcBcp3MXj57KeFIzU";
    }
}
