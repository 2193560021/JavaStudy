package com.hmall.item.domain.dto;

import com.hmall.item.enums.ItemPerate;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ItemMQDTO implements Serializable {
    private ItemPerate itemPerate;
    private ItemDTO itemDTO;

}
