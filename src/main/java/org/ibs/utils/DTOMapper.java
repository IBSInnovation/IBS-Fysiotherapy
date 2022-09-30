package org.ibs.utils;

import java.util.List;
import java.util.stream.Collectors;

public interface DTOMapper<DTOO extends DTO, O> {

    /**
     *This method converts an object to a corresponding DTO
     *@param o Object to convert
     * @return: converted DTO
     */
    public DTOO toDTO(O o);

    /**
     *This method converts a DTO to the corresponding object
     * @param o DTO to convert
     * @return converted Object
     */
    public O fromDTO(DTOO o);

    /**
     * Calls the toDTO method in a stream, calling it for every object in the given list
     * @param objects List of objects to convert to a DTO
     * @return List of converted Objects
     */
    public default List<DTOO> toMultipleDTO(List<O> objects) {
        return objects.stream().map(this::toDTO).collect(Collectors.toList());
    }
    public default List<O> fromMultipleDTO(List<DTOO> objects) {
        return objects.stream().map(this::fromDTO).collect(Collectors.toList());
    }
}
