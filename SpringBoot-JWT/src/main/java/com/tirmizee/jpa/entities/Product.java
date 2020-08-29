package com.tirmizee.jpa.entities;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Index;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.hibernate.annotations.NaturalId;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.tirmizee.jpa.converters.ProductCodeConverter;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "E_PRODUCT", indexes = {@Index(name = "IDX_E_PRODUCT", columnList = "NAME", unique = true)} )
public class Product extends BaseEntity implements Serializable {

	private static final Logger LOGGER = LoggerFactory.getLogger(Product.class);
	
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "ID")
	@SequenceGenerator(name = "seqProduct", sequenceName = "SEQ_E_PRODUCT", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seqProduct")
	private Long id;
	
	@NaturalId
	@Convert(converter = ProductCodeConverter.class)
	@Column(name = "CODE")
	private String code;
	
	@Column(name = "CATEGORY_CODE", insertable = false, updatable = false)
	private String categoryCode;
	
	@Column(name = "NAME")
	private String name;
	
	@Column(name = "DESCRIPTION")
	private String description;
	
	@Column(name = "PRICE")
	private BigDecimal price;
	
	@Column(name = "QUATITY")
	private Integer quatity;
	
	@Column(name = "IMAGE")
	private String image;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "CATEGORY_CODE", referencedColumnName = "CODE", foreignKey = @ForeignKey(name = "E_PRODUCT_FK1"))
	private Category category;
	
	@OneToMany(mappedBy = "product")
	private List<Card> cards;
	
	@OneToMany(mappedBy = "product")
	private Set<OrderProduct> orderProducts;
	
	@PrePersist
    private void prePersist() {
		LOGGER.info("Order Pre Persist");
	}
	
	@PreUpdate
	private void preUpdate() {
		LOGGER.info("Order Pre Update");
	}
	
}
