package jpa.converter;

import jpa.entity.Direction;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;
import java.util.Arrays;
import java.util.Objects;

/**
 * @author Georgy Sorokin
 */
@Converter(autoApply = true)
public class DirectionConverter implements AttributeConverter<Direction, Integer> {

    @Override
    public Integer convertToDatabaseColumn(Direction attribute) {
        Objects.requireNonNull(attribute);
        return attribute.id;
    }

    @Override
    public Direction convertToEntityAttribute(Integer dbData) {
        Objects.requireNonNull(dbData);
        return Arrays.stream(Direction.values())
                .filter(direction -> direction.id.equals(dbData))
                .findFirst()
                .orElseThrow();
    }
}
