package com.tirmizee.jpa.entities;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Index;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "E_CARD", indexes = {@Index(name = "IDX_E_CARD", columnList = "UUID", unique = true)} )
public class Card extends BaseEntity implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "ID")
	@SequenceGenerator(name = "seqProduct", sequenceName = "SEQ_E_PRODUCT", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seqProduct")
	private Long id;
	
	@Column(name = "UUID")
	private String uuid;
	
	@Column(name = "PRODUCT_CODE", insertable = false, updatable = false)
	private Long productCode;
	
	@Column(name = "QUATITY")
	private Integer quatity;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "PRODUCT_CODE", referencedColumnName = "CODE", foreignKey = @ForeignKey(name = "E_CARD_FK1"))
	private Product product;
	
}
