package com.tirmizee.jpa.entities;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.ForeignKey;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MapsId;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "E_ORDER_PRODUCT")
public class OrderProduct {
	
	@EmbeddedId
	private OrderItemKey id;
	
	@ManyToOne
	@MapsId("ORDER_ID")
    @JoinColumn(name = "ORDER_ID", foreignKey = @ForeignKey(name = "E_ORDER_PRODUCT_FK1"))
	private Order order;
	 
    @ManyToOne
    @MapsId("PRODUCT_ID")
    @JoinColumn(name = "PRODUCT_ID", foreignKey = @ForeignKey(name = "E_ORDER_PRODUCT_FK2"))
    private Product product;
	
	@Column(name = "QUATITY")
	private Integer quatity;
	
}
