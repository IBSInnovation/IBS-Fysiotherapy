package org.ibs.utils;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public interface DTOMapper<DTOO extends DTO, O> {

    /**
     *Converts an object to a corresponding DTO
     *@param o Object to convert
     * @return: converted DTO
     */
    public DTOO toDTO(O o);

    /**
     *Converts a DTO to the corresponding object
     * @param o DTO to convert
     * @return converted object
     */
    public O fromDTO(DTOO o) throws Exception;

    /**
     * Calls the toDTO method in a stream, calling it for every object in the given list
     * @param objects List of objects to convert to a list of DTOs
     * @return List of DTOs
     */
    public default List<DTOO> toMultipleDTO(List<O> objects) {
        return objects.stream().map(this::toDTO).collect(Collectors.toList());
    }

    /**
     * Calls the fromDTO method in a stream, calling it for every object in the given list
     * @param objects List of DTOs to convert to a list of objects
     * @return List of objects
     */
    public default List<O> fromMultipleDTO(List<DTOO> objects) throws Exception {
        List<O> list = new ArrayList<>();
        for (DTOO object : objects) {
            O o = fromDTO(object);
            list.add(o);
        }
        return list;
    }
}
