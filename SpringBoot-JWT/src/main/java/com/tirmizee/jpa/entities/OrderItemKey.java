package com.tirmizee.jpa.entities;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;

import lombok.Data;

@Data
@Embeddable
public class OrderItemKey implements Serializable {
	
	private static final long serialVersionUID = 1L;

	@Column(name = "ORDER_ID")
    Long orderId;
 
    @Column(name = "PRODUCT_ID")
    Long productId;

}
