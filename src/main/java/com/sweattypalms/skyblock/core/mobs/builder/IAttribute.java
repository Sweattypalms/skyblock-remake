package com.sweattypalms.skyblock.core.mobs.builder;

public interface IAttribute<T> {
    Class<T> getType();

    public final class BooleanAttribute implements IAttribute<Boolean> {
        @Override
        public Class<Boolean> getType() {
            return Boolean.class;
        }
    }

    public final class IntAttribute implements IAttribute<Integer> {
        @Override
        public Class<Integer> getType() {
            return Integer.class;
        }
    }

    public final class DoubleAttribute implements IAttribute<Double> {
        @Override
        public Class<Double> getType() {
            return Double.class;
        }
    }

    public final class StringAttribute implements IAttribute<String> {
        @Override
        public Class<String> getType() {
            return String.class;
        }
    }

}
