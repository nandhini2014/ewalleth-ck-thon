package com.ewallet.order.dto;

import lombok.Data;
import java.util.List;

@Data
public class MerchantDTO {

    private String merchantId;
    private String merchantName;
    private List<ProductDTO> products;
}
