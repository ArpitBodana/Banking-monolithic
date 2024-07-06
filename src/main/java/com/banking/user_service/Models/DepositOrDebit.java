package com.banking.user_service.Models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class DepositOrDebit {
   private Long accountId;
   private Long amount;
}
