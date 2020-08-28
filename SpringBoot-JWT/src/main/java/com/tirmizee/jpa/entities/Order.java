package com.tirmizee.jpa.entities;

import java.io.Serializable;
import java.util.Date;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Index;
import javax.persistence.OneToMany;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.aspectj.weaver.patterns.ThisOrTargetAnnotationPointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "E_ORDER", indexes = {@Index(name = "IDX_E_ORDER", columnList = "ORDER_NO", unique = true)} )
public class Order extends BaseEntity implements Serializable {

	private static final Logger LOGGER = LoggerFactory.getLogger(Order.class);
	
	private static final long serialVersionUID = 1L;
	
	@Id
	@Column(name = "ID")
	@SequenceGenerator(name = "seqOrder", sequenceName = "SEQ_E_ORDER", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seqOrder")
	private Long id;
	
	@Column(name = "ORDER_NO")
	private String orderNo;
	
	@Column(name = "LAST_UPDATE")
	private Date lastUpdate;

	@Column(name = "STATUS")
	private String status;
	
	@Column(name = "SHIPPING_FIRST_NAME")
	private String shippingFirstName;
	
	@Column(name = "SHIPPING_LAST_NAME")
	private String shippingLastName;
	
	@OneToMany(mappedBy = "order")
	private Set<OrderProduct> orderProducts;
	
}
