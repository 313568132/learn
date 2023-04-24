package com.leon.hutool;

import cn.hutool.core.lang.Console;
import com.theokanning.openai.OpenAiApi;
import com.theokanning.openai.OpenAiResponse;
import com.theokanning.openai.OpenAiService;
import com.theokanning.openai.completion.CompletionRequest;
import com.theokanning.openai.completion.CompletionResult;
import com.theokanning.openai.completion.chat.ChatCompletionChoice;
import com.theokanning.openai.completion.chat.ChatCompletionRequest;
import com.theokanning.openai.completion.chat.ChatCompletionResult;
import com.theokanning.openai.completion.chat.ChatMessage;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

public class TestChatGPT {
    public static void main(String[] args) {
        String apiKey = "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";
        sendMsg(apiKey);
    }
    public static void sendMsg2() {
        // String apiKey = "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";
        String apiKey = "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";
        // String modelId = "text-davinci-003";
        String modelId = "gpt-3.5-turbo";
        //String modelId = "gpt-3.5-turbo-0301";
        OpenAiService openAiService = new OpenAiService(apiKey, Duration.ofSeconds(100));
        CompletionRequest completionRequest = CompletionRequest.builder()
                .prompt("手机壳在中国的税号是什么 只返回税号")
                .model(modelId)
                .echo(true)
                .temperature(0.7)
                .topP(1d)
                .frequencyPenalty(0d)
                .presencePenalty(0d)
                .maxTokens(1000)
                .build();
        CompletionResult completionResult = openAiService.createCompletion(completionRequest);
        String text = completionResult.getChoices().get(0).getText();
        System.out.println(text);
    }
    public static void sendMsg( String apiKey) {
        // 消息列表
        List<ChatMessage> list = new ArrayList<>();

        // 给chatGPT定义一个身份，是一个助手
//        ChatMessage chatMessage = new ChatMessage();
//        chatMessage.setRole("system");
//        // chatMessage.setContent("You are a helpful assistant.");
//        chatMessage.setContent("You are a helpful tool.");
//        list.add(chatMessage);

        /*// 定义一个用户身份，content是用户写的内容*/
        ChatMessage userMessage = new ChatMessage();
        userMessage.setRole("system");
        userMessage.setContent("我有点想你了娜娜 翻译成英语.");
        list.add(userMessage);

        ChatMessage userMessage2 = new ChatMessage();
        userMessage2.setRole("user");
        userMessage2.setContent("blush 米色、小牛皮、内置logo贴花、可调可拆卸肩带、一个平顶手柄、吊坠细节、翻盖饰有磁扣、主夹层。|面料 小牛皮: 100%;里料 棉: 100%" +
                "的hscode是什么? 只返回hscode.");
        list.add(userMessage2);

        ChatCompletionRequest request = ChatCompletionRequest.builder()
                .messages(list)
                .model("gpt-3.5-turbo")
                .build();
        OpenAiService service = new OpenAiService(apiKey);

        // chatCompletion 对象就是chatGPT响应的数据了
        ChatCompletionResult chatCompletion = service.createChatCompletion(request);
        Console.log("ChatCompletionResult: " + chatCompletion);
        List<ChatCompletionChoice> choices = chatCompletion.getChoices();
        Console.log("choices: " + choices);
        for (ChatCompletionChoice choice : choices) {
            ChatMessage message = choice.getMessage();
            System.out.println("Role: " + message.getRole() + " ---> Content: " + message.getContent());
        }
    }
}
