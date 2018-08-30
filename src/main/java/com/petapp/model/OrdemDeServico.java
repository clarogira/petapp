package com.petapp.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Transient;

@Entity
public class OrdemDeServico implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long codigo;

	private LocalDate dataCriacao;
	
	private BigDecimal valorDesconto;
	
	private BigDecimal valorFrete;
	
	private BigDecimal valorTotal  = BigDecimal.ZERO;
	
	private String observacao;
	
	private LocalDate dataAgendamento;
	
	@Enumerated(EnumType.STRING)
	private Status status = Status.ABERTO;
	
	@Enumerated(EnumType.STRING)
	private FormaDePagamento formaDePagamento;
	
	@ManyToOne
	@JoinColumn(name = "codigo_cliente")
	private Cliente cliente;
	
	@OneToMany(mappedBy = "ordemDeServico", cascade = CascadeType.ALL , orphanRemoval = true)
	private List<ItemServico> itens = new ArrayList<>();

	@Transient
	private String uuid;
	
	public boolean isSalvarPermitido() {
		return !status.equals(Status.CANCELADA);
	}
	
	public boolean isSalvarProibido() {
		return !isSalvarPermitido();
	}
	
	public boolean isNova() {
		return codigo == null;
	}
	
	public void adicionarItens(List<ItemServico> itens) {
		this.itens = itens;
		this.itens.forEach(i -> i.setOrdemDeServico(this));
	}

	public void calcularValorTotal() {
		BigDecimal valorTotalItens = getItens().stream()
				.map(ItemServico::getValorTotal)
				.reduce(BigDecimal::add)
				.orElse(BigDecimal.ZERO);
		
		this.valorTotal = calcularValorTotal(valorTotalItens, getValorFrete(), getValorDesconto());
	}
	
	public Long getDiasCriacao() {
		LocalDate inicio = dataCriacao != null ? dataCriacao : LocalDate.now();
		return ChronoUnit.DAYS.between(inicio, LocalDate.now());
	}
	
	private BigDecimal calcularValorTotal(BigDecimal valorTotalItens, BigDecimal valorFrete, BigDecimal valorDesconto) {
		BigDecimal valorTotal = valorTotalItens
				.add(Optional.ofNullable(valorFrete).orElse(BigDecimal.ZERO))
				.subtract(Optional.ofNullable(valorDesconto).orElse(BigDecimal.ZERO));
		return valorTotal;
	}

	

	public Long getCodigo() {
		return codigo;
	}

	public void setCodigo(Long codigo) {
		this.codigo = codigo;
	}

	public LocalDate getDataCriacao() {
		return dataCriacao;
	}

	public BigDecimal getValorFrete() {
		return valorFrete;
	}

	public void setValorFrete(BigDecimal valorFrete) {
		this.valorFrete = valorFrete;
	}

	public void setDataCriacao(LocalDate dataCriacao) {
		this.dataCriacao = dataCriacao;
	}

	public BigDecimal getValorDesconto() {
		return valorDesconto;
	}

	public void setValorDesconto(BigDecimal valorDesconto) {
		this.valorDesconto = valorDesconto;
	}

	public BigDecimal getValorTotal() {
		return valorTotal;
	}

	public void setValorTotal(BigDecimal valorTotal) {
		this.valorTotal = valorTotal;
	}

	public String getObservacao() {
		return observacao;
	}

	public void setObservacao(String observacao) {
		this.observacao = observacao;
	}

	public LocalDate getDataAgendamento() {
		return dataAgendamento;
	}

	public void setDataAgendamento(LocalDate dataAgendamento) {
		this.dataAgendamento = dataAgendamento;
	}

	public Status getStatus() {
		return status;
	}

	public void setStatus(Status status) {
		this.status = status;
	}

	public FormaDePagamento getFormaDePagamento() {
		return formaDePagamento;
	}

	public void setFormaDePagamento(FormaDePagamento formaDePagamento) {
		this.formaDePagamento = formaDePagamento;
	}

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	public List<ItemServico> getItens() {
		return itens;
	}

	public void setItens(List<ItemServico> itens) {
		this.itens = itens;
	}

	public String getUuid() {
		return uuid;
	}

	public void setUuid(String uuid) {
		this.uuid = uuid;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((codigo == null) ? 0 : codigo.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		OrdemDeServico other = (OrdemDeServico) obj;
		if (codigo == null) {
			if (other.codigo != null)
				return false;
		} else if (!codigo.equals(other.codigo))
			return false;
		return true;
	}

	
	

}
