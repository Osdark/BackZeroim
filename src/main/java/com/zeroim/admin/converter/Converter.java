package com.zeroim.admin.converter;

import com.zeroim.admin.populators.Populator;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Converter<SOURCE, TARGET> {

    private Class<TARGET> target;

    private List<Populator> populators = new ArrayList<>();

    public Converter(Class<TARGET> target) {
        this.target = target;
    }

    public static <SOURCE, TARGET> Converter<SOURCE, TARGET> of(Class<TARGET> target) {
        return new Converter<SOURCE, TARGET>(target);
    }

    public TARGET convert(SOURCE source) {
        TARGET target = createTargetInstance();
        for (Populator populator : populators) {
            populator.populate(source, target);
        }
        return target;
    }

    public List<TARGET> convertAll(Iterable<SOURCE> sources) {
        List<TARGET> targets = new ArrayList<>();
        for (SOURCE objectToConvert : sources) {
            targets.add(convert(objectToConvert));
        }
        return targets;
    }

    public void addPopulator(Populator<SOURCE, TARGET> populator) {
        if (Objects.nonNull(populator)) {
            populators.add(populator);
        }
    }

    private TARGET createTargetInstance() {
        try {
            return target.newInstance();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public <SOURCE, TARGET> Converter<SOURCE, TARGET> withPopulator(Populator<SOURCE, TARGET> populator) {
        if (Objects.nonNull(populator)) {
            populators.add(populator);
        }
        return (Converter<SOURCE, TARGET>) this;
    }
}
