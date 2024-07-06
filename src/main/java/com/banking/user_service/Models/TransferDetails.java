package com.banking.user_service.Models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TransferDetails {
    private Long senderId;
    private Long receiverId;
   private Long transferAmount;
}
