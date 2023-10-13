package com.example.module4_shoesshop.model.Message;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Message {
    private String receiverName;
    private String senderName;
    private String message;
    private String date;
    private Status status;
}
