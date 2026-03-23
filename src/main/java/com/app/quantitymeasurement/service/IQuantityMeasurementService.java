package com.app.quantitymeasurement.service;

import com.app.quantitymeasurement.model.QuantityDTO;
import com.app.quantitymeasurement.model.QuantityMeasurementDTO;

import java.util.List;

/**
 * IQuantityMeasurementService
 *
 * Service interface defining the business operations available in the Quantity
 * Measurement application.
 */
public interface IQuantityMeasurementService {

    
    QuantityMeasurementDTO compare(QuantityDTO thisQuantityDTO, QuantityDTO thatQuantityDTO);

     QuantityMeasurementDTO convert(QuantityDTO thisQuantityDTO, QuantityDTO thatQuantityDTO);

    
    QuantityMeasurementDTO add(QuantityDTO thisQuantityDTO, QuantityDTO thatQuantityDTO);

    
    QuantityMeasurementDTO add(
            QuantityDTO thisQuantityDTO,
            QuantityDTO thatQuantityDTO,
            QuantityDTO targetUnitDTO);

    
    QuantityMeasurementDTO subtract(QuantityDTO thisQuantityDTO, QuantityDTO thatQuantityDTO);

    
    QuantityMeasurementDTO subtract(
            QuantityDTO thisQuantityDTO,
            QuantityDTO thatQuantityDTO,
            QuantityDTO targetUnitDTO);

   
    QuantityMeasurementDTO divide(QuantityDTO thisQuantityDTO, QuantityDTO thatQuantityDTO);

    
    List<QuantityMeasurementDTO> getHistoryByOperation(String operation);

   
    List<QuantityMeasurementDTO> getHistoryByMeasurementType(String measurementType);

  
    long getOperationCount(String operation);

    
    List<QuantityMeasurementDTO> getErrorHistory();
}