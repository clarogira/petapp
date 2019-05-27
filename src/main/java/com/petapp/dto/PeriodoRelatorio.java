package com.petapp.dto;

import java.time.LocalDate;

public class PeriodoRelatorio {

	private LocalDate dataInicio =LocalDate.now();
	private LocalDate dataFim = LocalDate.parse("2022-05-01");
	private Integer min = -50;
	private Integer max = 100;
	
	
	public Integer getMin() {
		return min;
	}

	public void setMin(Integer min) {
		this.min = min;
	}

	public Integer getMax() {
		return max;
	}

	public void setMax(Integer max) {
		this.max = max;
	}

	public LocalDate getDataInicio() {
		return dataInicio;
	}

	public void setDataInicio(LocalDate dataInicio) {
		this.dataInicio = dataInicio;
	}

	public LocalDate getDataFim() {
		return dataFim;
	}

	public void setDataFim(LocalDate dataFim) {
		this.dataFim = dataFim;
	}

}