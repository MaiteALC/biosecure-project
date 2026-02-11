package br.com.biosecure.dto;

import br.com.biosecure.model.Cnae;
import br.com.biosecure.model.Cnpj;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;
import java.time.LocalDate;

public record FinancialDataInputDto(
        @NotNull(message = "Activities start date is required")
        LocalDate activitiesStartDate,

        @NotNull(message = "Cnae number is required")
        Cnae cnae,

        @NotNull(message = "CNPJ is required")
        Cnpj cnpj,

        @NotNull(message = "Share capital is required")
        BigDecimal shareCapital
) {}
